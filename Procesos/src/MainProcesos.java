//import java.util.Scanner;
import java.math.BigDecimal;
public class MainProcesos {
    
    public static void main(String[] args) {
        //Scanner scann=new Scanner(System.in);
        ListaProcesos lista=new ListaProcesos(new BigDecimal ("0.90"));

        lista.agregarProceso(new Proceso("15","0","1.380"));
        lista.agregarProceso(new Proceso ("15","10","0.955"));
        lista.agregarProceso(new Proceso("15","15","1.655"));
        lista.agregarProceso(new Proceso("15","20","1.250"));
        lista.agregarProceso(new Proceso("15","25","0.960"));
        lista.ejecutar();


     /*    for(int i=0; i<cant; i++){
            System.out.println("Ingrese la hora de inicio del proceso "+(i+1)+":");
            horaIni=scann.nextdouble();
            System.out.println("Ingrese el minuto de inicio del proceso "+(i+1)+":");
            minutoIni=scann.nextdouble();
            System.out.println("Ingrese el tiempo necesario del proceso "+(i+1)+":");
            tNecesario=scann.nextdouble();
            Proceso nuevoProceso=new Proceso(horaIni,minutoIni,tNecesario);
            lista.agregarProceso(nuevoProceso);
        }*/
    }
}
