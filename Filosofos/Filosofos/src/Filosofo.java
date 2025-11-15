import java.util.concurrent.Semaphore;
import java.util.ArrayList;
public class Filosofo implements Runnable {
    private Semaphore mutex;
    private Semaphore[] S;  
    private ArrayList<Integer> estado;
    private ArrayList<Integer> comio;
    private int NUM_FILOSOFOS;
    private int i;
    private int pensando = 0;
    private int hambriento = 1;
    private int comiendo = 2;
   

    public Filosofo(Semaphore mutex, Semaphore[] S, ArrayList<Integer> estado,ArrayList<Integer> comio, int NUM_FILOSOFOS, int i) {
        this.mutex = mutex;
        this.S = S; 
        this.estado = estado;
        this.comio = comio;
        this.NUM_FILOSOFOS = NUM_FILOSOFOS;
        this.i = i;
        this.estado.set(i, pensando);

    }
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            pensar();
            agarrar_tenedores();
            comer();
            soltar_tenedores();
        }
    }
    public int izquierda() {
        return (this.i + NUM_FILOSOFOS - 1) % NUM_FILOSOFOS;
    }
   public  int derecha() {
        return (this.i + 1) % NUM_FILOSOFOS;
    }

    void pensar(){
         try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }       
    }

    void comer(){
       try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void agarrar_tenedores(){
        try{mutex.acquire();
        estado.set(this.i, hambriento);
        test(this.i);
        mutex.release();
        S[this.i].acquire();}catch(InterruptedException e){
            Thread.currentThread().interrupt();
        } //baja el semaforo
    }
    private void soltar_tenedores(){
       try{ mutex.acquire();
        estado.set(this.i, pensando);
        System.out.println("El  filosofo "+  this.i +" esta pensando");
        test(izquierda());
        test(derecha());
        mutex.release();}catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    private void test(int i){
        if(estado.get(i)==hambriento  && estado.get(izquierda())!=comiendo && estado.get(derecha())!=comiendo){
            estado.set(i, comiendo);
            comio.set(i, comio.get(i)+1);
            System.out.println("El filosofo "+  i +" esta comiendo");
            System.out.println(comio);
            S[i].release();
        }
    }
}

















