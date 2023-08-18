package proyecto.ed;

import java.util.Stack;

public class Chef {

    private Stack<Hamburguesa> hamburguesasEnProceso;
    private int puntaje;

    public Chef() {
        hamburguesasEnProceso = new Stack<>();
        puntaje = 0;
    }

    public void prepararHamburguesa(Hamburguesa hamburguesa) {
        hamburguesasEnProceso.push(hamburguesa);
    }

    public Hamburguesa completarHamburguesa() {
        if (!hamburguesasEnProceso.isEmpty()) {
            return hamburguesasEnProceso.pop();
        }
        return null;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!hamburguesasEnProceso.isEmpty()) {
            Hamburguesa hamburguesaActual = hamburguesasEnProceso.peek();
            hamburguesaActual.agregarIngrediente(ingrediente);
        }
    }

    public void desecharHamburguesa() {
        if (!hamburguesasEnProceso.isEmpty()) {
            hamburguesasEnProceso.pop();
        }
    }

    public boolean tieneHamburguesaEnProceso() {
        return !hamburguesasEnProceso.isEmpty();
    }

    public boolean hamburguesaCompleta() {
        if (!hamburguesasEnProceso.isEmpty()) {
            Hamburguesa hamburguesa = hamburguesasEnProceso.peek();
            return hamburguesa.estaCompleta();
        }
        return false;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void sumarPuntaje(int puntos) {
        puntaje += puntos;
    }

    public Hamburguesa obtenerHamburguesaEnProceso() {
        if (!hamburguesasEnProceso.isEmpty()) {
            return hamburguesasEnProceso.peek();
        }
        return null;
    }
}

