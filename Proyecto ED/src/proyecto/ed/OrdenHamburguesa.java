package proyecto.ed;

public class OrdenHamburguesa {

    private String tipoHamburguesa;
    private int puntos;

    public OrdenHamburguesa(String tipoHamburguesa, int puntos) {
        this.tipoHamburguesa = tipoHamburguesa;
        this.puntos = puntos;
    }

    public String getTipoHamburguesa() {
        return tipoHamburguesa;
    }

    public int getPuntos() {
        return puntos;
    }
}
