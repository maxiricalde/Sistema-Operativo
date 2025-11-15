import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class MainFilosofo {
        public static int NUM_FILOSOFOS = 5;
        private static Semaphore mutex = new Semaphore(1);
        private static Semaphore[] S = new Semaphore[NUM_FILOSOFOS];
        public static ArrayList<Integer> estado = new ArrayList<>(NUM_FILOSOFOS);
        public static ArrayList<Integer> comio = new ArrayList<>(NUM_FILOSOFOS);
        private static int pensando = 0;
        int hambriento = 1;
        int comiendo = 2;
    
        public static void main(String[] args) {
        for(int i=0;i<NUM_FILOSOFOS;i++){
        estado.add(0);
        S[i]=new Semaphore(pensando);
        comio.add(0);
        }
        Filosofo filosofo0=new Filosofo(mutex, S, estado,comio, NUM_FILOSOFOS, 0);
        Filosofo filosofo1=new Filosofo(mutex, S, estado,comio, NUM_FILOSOFOS, 1);
        Filosofo filosofo2=new Filosofo(mutex, S, estado,comio, NUM_FILOSOFOS, 2);
        Filosofo filosofo3=new Filosofo(mutex, S, estado,comio, NUM_FILOSOFOS, 3);
        Filosofo filosofo4=new Filosofo(mutex, S, estado,comio, NUM_FILOSOFOS, 4);
        System.out.println("Simulacion de los filosofos comensales");
        
        Thread hiloFilosofo0 = new Thread(filosofo0);
        hiloFilosofo0.start();
        Thread hiloFilosofo1 = new Thread(filosofo1);
        hiloFilosofo1.start();
        Thread hiloFilosofo2 = new Thread(filosofo2);
        hiloFilosofo2.start();
        Thread hiloFilosofo3 = new Thread(filosofo3);
        hiloFilosofo3.start();
        Thread hiloFilosofo4 = new Thread(filosofo4);
        hiloFilosofo4.start();


        try{
        Thread.sleep(100000);
        
        hiloFilosofo0.interrupt();
        hiloFilosofo1.interrupt();
        hiloFilosofo2.interrupt();
        hiloFilosofo3.interrupt();
        hiloFilosofo4.interrupt();

        }catch(InterruptedException e){}
        
}
}