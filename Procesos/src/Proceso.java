public class Proceso {

    private double horaIni;
    private double minutoIni;    
    private int nProceso;
    private double tNecesario;
    private double tInicio;
    private boolean enEjecucion;
    private static int contadorProcesos = 0;

    public Proceso(double horaIni, double minutoIni, double tNecesario) {
        this.horaIni = horaIni;
        this.minutoIni = minutoIni;
        this.tNecesario = tNecesario;
        this.nProceso = this.setnProceso();
        this.tInicio = horaIni*60 + minutoIni;
        this.enEjecucion = false;
    }
    public double gettInicio() {
        return tInicio;
    }
    public void settInicio(double tInicio) {
        this.tInicio = tInicio;
    }

    public double getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(double horaIni) {
        this.horaIni = horaIni;
    }

    public double getMinutoIni() {
        return minutoIni;
    }

    public void setMinutoIni(double minutoIni) {
        this.minutoIni = minutoIni;
    }

    public int getnProceso() {
        return nProceso;
    }

    public int setnProceso() {
        contadorProcesos++;
        return contadorProcesos;
    }

    public double gettNecesario() {
        return tNecesario;
    }

    public void settNecesario(double tNecesario) {
        this.tNecesario = tNecesario;
    }
    public boolean isEnEjecucion() {
        return enEjecucion;
    }
    public void setEnEjecucion(boolean enEjecucion) {
        this.enEjecucion = enEjecucion;
    }
    public String toString(){
        return "Proceso N° "+nProceso+" inicializado a las "+horaIni+":"+minutoIni+" con tiempo estimado de ejecucion de "+tNecesario;
    }
}
