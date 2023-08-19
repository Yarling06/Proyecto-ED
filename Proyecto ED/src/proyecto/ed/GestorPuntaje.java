
package proyecto.ed;


public class GestorPuntaje {
    private int puntaje;

    public GestorPuntaje() {
        puntaje = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void sumarPuntaje(int puntos) {
        puntaje += puntos;
    }
}
