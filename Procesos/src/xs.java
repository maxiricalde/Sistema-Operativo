import java.util.Scanner;

public class xs {

    public static void main(String[] args) {
        Scanner scann=new Scanner(System.in);
        System.out.println("Ingrese el primer numero:");
        int num1=scann.nextInt();
        System.out.println("Ingrese el segundo numero:");
        int num2=scann.nextInt();
        System.out.println("La suma es:"+sumar(num1,num2));
        System.out.println("La cantidad de numeros primos del primer numero es:"+cantidad_primos(num1));
        System.out.println("La cantidad de numeros primos del segundo numero es:"+cantidad_primos(num2));
    }

    public static int sumar(int a, int b){
        return a+b;
    }
    public static int cantidad_primos(int n){
        int contador=0;
        for(int i=1; i<=n; i++){
            if(n%i==0){
                contador++;
            }
        }
        return contador;
    }

}
    

