import java.math.BigDecimal;
import java.util.ArrayList;
import java.math.RoundingMode;

public class ListaProcesos {
    private ArrayList<Proceso> procesos;
    private BigDecimal promES;
    private static BigDecimal inf = new BigDecimal("1000000");
    private static BigDecimal epcilon = new BigDecimal("0.0001");
    private int cont;
    private BigDecimal pn;

    public ListaProcesos(BigDecimal promES) {
        this.procesos = new ArrayList<>();
        this.promES = promES;
        this.cont = 0;
        this.pn = new BigDecimal("0");
    }
    public void agregarProceso(Proceso proceso){
        procesos.add(proceso);
        cont++;
    }
    
    public void setearTiempo(){
        BigDecimal min=procesos.get(0).gettInicio();
        for(int i=0; i<procesos.size(); i++){
            procesos.get(i).settInicio(procesos.get(i).gettInicio().subtract(min));
        }
    }
    private BigDecimal minTesperado(BigDecimal pn){
        BigDecimal min= inf;
        BigDecimal temp;
        BigDecimal pnInv;
        BigDecimal uno= new BigDecimal("1");
        for(int i=0; i<procesos.size(); i++){
            //System.out.println(procesos.get(i).isEnEjecucion());
            if(procesos.get(i).isEnEjecucion()==true){
                pnInv=(BigDecimal.ONE).divide(pn,10,RoundingMode.HALF_UP);
                temp=procesos.get(i).gettNecesario().multiply(pnInv);
                if(temp.compareTo(min)<0){
                    min=temp;//min=procesos.get(i).gettInicio();
                }
            }
        }
        return min;
    }
    public BigDecimal calcularPn(){
        BigDecimal pn;
        BigDecimal promESbd= promES;
        BigDecimal n = BigDecimal.valueOf(procesosEnEjecucion()) ; 
        return (BigDecimal.ONE).subtract(promESbd.pow(procesosEnEjecucion())).divide(n,10,RoundingMode.HALF_UP);
        //return (double)Math.pow((double)1-promES,(double) procesosEnEjecucion());
    }
    public int procesosEnEjecucion(){
        int cant=0;
        for(int i=0; i<procesos.size(); i++){
            if(procesos.get(i).isEnEjecucion()==true){
                cant++;
            }
        }
        return cant;
    }


    public void ejecutar(){
        this.setearTiempo();
        BigDecimal tiempoAct= new BigDecimal("0");
        BigDecimal tiempoAux;
        int pos=0;
        mostrarlista();
        while(cont>0){
            tiempoAux=tiempoAct;
            pos=iniciaProceso(tiempoAct,pos);
            this.pn = calcularPn();
            //pos = pos + procesosEnEjecucion();
            System.out.println("pos ="+pos);
            BigDecimal tiempoParaFinProceso = tiempoAct.add(minTesperado(pn)); // Calcula el tiempo absoluto de finalización del próximo proceso en ejecución.
            if(pos < procesos.size()){
                // El próximo evento será lo que ocurra primero:
                // 1. Que termine un proceso en ejecución.
                // 2. Que llegue un nuevo proceso.
                tiempoAct = tiempoParaFinProceso.min(procesos.get(pos).gettInicio());
            } else {
                // Si no hay más procesos por llegar, el próximo evento es sí o sí que uno termine.
                tiempoAct = tiempoParaFinProceso;
            }
            for(int i=0;i<this.procesos.size();i++){
                if(this.procesos.get(i).isEnEjecucion()==true){
                    this.procesos.get(i).settNecesario(this.procesos.get(i).gettNecesario().subtract(tiempoAct.subtract(tiempoAux).multiply(pn)));
                    System.out.println("Proceso N° "+this.procesos.get(i).getnProceso()
                        +" le queda un tiempo de "+ this.procesos.get(i).gettNecesario());
                    //System.out.println(esCero(this.procesos.get(i).gettNecesario()));
                    if(esCero(this.procesos.get(i).gettNecesario())){
                        System.out.println("El proceso N° "+this.procesos.get(i).getnProceso()
                            +" ha terminado su ejecucion."+" Tiempo: "+ tiempoAct);
                        this.quitarProceso(i,tiempoAct);
                    }
                }
            }
        }
    }

    private void mostrarlista(){
        for(int i=0; i<procesos.size(); i++){
            System.out.println(procesos.get(i).toString());
        }
    }

    private void quitarProceso(int nProceso,BigDecimal tiempoAct){
        procesos.get(nProceso).setEnEjecucion(false);
        System.out.println("Proceso N° "+procesos.get(nProceso).getnProceso()
            +" tardo en ejecutarse "+ tiempoAct);
        cont--;      //se muere el proceso
    }
    
    private boolean esCero(BigDecimal num){
        
        num=num.abs();
        return num.compareTo(epcilon)<1;
    }
// ANTES (Incorrecto)
/*
private int iniciaProceso(double tiempo,int pos2){
    boolean ban=true;
    int pos=0;
    while(ban && pos<procesos.size()){
        if(esCero(tiempo-procesos.get(pos).gettInicio())){
            procesos.get(pos).setEnEjecucion(true);
            pos++;
            pos2++;
        }else{
            if(procesos.get(pos).gettInicio()<tiempo){
                pos++;
            }else ban=false;  
         }
    }
    return pos2;
}
*/

// DESPUÉS (Correcto y Eficiente)
private int iniciaProceso(BigDecimal tiempo, int posActual) {
    int pos = posActual; // Empezamos a revisar desde el último proceso que consideramos

    // Iteramos mientras haya procesos en la lista
    while (pos < procesos.size()) {
        Proceso p = procesos.get(pos);

        // Verificamos si la hora de llegada del proceso es ahora o ya pasó
        if (tiempo.compareTo(p.gettInicio())==1 || esCero(p.gettInicio().subtract(tiempo))) {
            
            // Solo lo iniciamos si no estaba ya en ejecución
            if (!p.isEnEjecucion()) {
                p.setEnEjecucion(true);
                // Imprimimos un mensaje para depuración (opcional)
                System.out.println("--- Iniciando Proceso N° " + p.getnProceso() + " en tiempo " + String.format("%.3f", tiempo) + " ---");
            }
            // Avanzamos al siguiente proceso de la lista
            pos++;
        } else {
            // Este proceso es del futuro, así que detenemos la revisión
            break;
        }
    }
    // Devolvemos el nuevo índice del próximo proceso por llegar
    return pos;
}
}
