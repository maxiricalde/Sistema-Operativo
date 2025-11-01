import java.util.ArrayList;

public class ListaProcesos {
    private ArrayList<Proceso> procesos;
    private double promES;
    private static double  inf=1000000;
    private int cont;
    private double pn;

    public ListaProcesos(double promES) {
        this.procesos = new ArrayList<>();
        this.promES = promES;
        this.cont = 0;
        this.pn = 0;
    }
    public void agregarProceso(Proceso proceso){
        procesos.add(proceso);
        cont++;
    }
    
    public void setearTiempo(){
        double min=procesos.get(0).gettInicio();
        for(int i=0; i<procesos.size(); i++){
            procesos.get(i).settInicio(procesos.get(i).gettInicio()-min);
        }
    }
    private double minTesperado(double pn){
        double min=inf;
        for(int i=0; i<procesos.size(); i++){
            System.out.println(procesos.get(i).isEnEjecucion());
            if(procesos.get(i).isEnEjecucion()==true){
                if(procesos.get(i).gettNecesario()*(1/pn)<=min){
                    min=procesos.get(i).gettNecesario()*(1/pn);//min=procesos.get(i).gettInicio();
                }
            }
        }
        return min;
    }
    public double calcularPn(){
        return (double)Math.pow((double)1-promES,(double) procesosEnEjecucion());
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
        double tiempoAct=0;
        double tiempoAux;
        int pos=0;
        mostrarlista();
        while(cont>0){
            tiempoAux=tiempoAct;
            pos=iniciaProceso(tiempoAct,pos);
            this.pn = calcularPn();
            //pos = pos + procesosEnEjecucion();
            System.out.println("pos ="+pos);
            if(pos<procesos.size()){
                tiempoAct=Math.min(minTesperado(pn),procesos.get(pos).gettInicio());}
            else 
                tiempoAct=minTesperado(pn);
            for(int i=0;i<this.procesos.size();i++){
                if(this.procesos.get(i).isEnEjecucion()==true){
                    this.procesos.get(i).settNecesario(this.procesos.get(i).gettNecesario()-((tiempoAct-tiempoAux)*pn));
                    System.out.println("Proceso N° "+this.procesos.get(i).getnProceso()
                        +" le queda un tiempo de "+ this.procesos.get(i).gettNecesario());
                    System.out.println(esCero(this.procesos.get(i).gettNecesario()));
                    if(esCero(this.procesos.get(i).gettNecesario())){
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

    private void quitarProceso(int nProceso,double tiempoAct){
        procesos.get(nProceso).setEnEjecucion(false);
        System.out.println("Proceso N° "+procesos.get(nProceso).getnProceso()
            +" tardo en ejecutarse "+ tiempoAct);
        cont--;      //se muere el proceso
    }
    
    private boolean esCero(double num){
        return Math.abs(num) <= 0.001;
    }
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
}
