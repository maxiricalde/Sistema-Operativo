
import java.math.BigDecimal;
//import java.math.RoundingMode;
public class Proceso {

    private BigDecimal horaIni;
    private BigDecimal minutoIni;    
    private int nProceso;
    private BigDecimal tNecesario;
    private BigDecimal tInicio;
    private boolean enEjecucion;
    private static int contadorProcesos = 0;

    public Proceso(String horaIni, String minutoIni, String tNecesario) {
        this.horaIni = new BigDecimal(horaIni);
        this.minutoIni = new BigDecimal(minutoIni);
        this.tNecesario = new BigDecimal (tNecesario);
        this.nProceso = this.setnProceso();
        this.tInicio = new BigDecimal("0");
        this.tInicio = this.horaIni.multiply(new BigDecimal("60")).add(this.minutoIni);
        this.enEjecucion = false;
    }
    public BigDecimal gettInicio() {
        return tInicio;
    }
    public void settInicio(BigDecimal tInicio) {
        this.tInicio = tInicio;
    }

    public BigDecimal getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(BigDecimal horaIni) {
        this.horaIni = horaIni;
    }

    public BigDecimal getMinutoIni() {
        return minutoIni;
    }

    public void setMinutoIni(BigDecimal minutoIni) {
        this.minutoIni = minutoIni;
    }

    public int getnProceso() {
        return nProceso;
    }

    public int setnProceso() {
        contadorProcesos++;
        return contadorProcesos;
    }

    public BigDecimal gettNecesario() {
        return tNecesario;
    }

    public void settNecesario(BigDecimal tNecesario) {
        this.tNecesario = tNecesario;
    }
    public boolean isEnEjecucion() {
        return enEjecucion;
    }
    public void setEnEjecucion(boolean enEjecucion) {
        this.enEjecucion = enEjecucion;
    }
    public String toString(){
        return "Proceso N° "+nProceso+" inicializado a las "+horaIni+":"+minutoIni+" con tiempo estimado de ejecucion de "+" minutos."+
               " Inicia en el minuto: "+tInicio;
    }
}
