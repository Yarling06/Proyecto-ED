
package proyecto.ed;
public class CintaTransportadora {

    private Nodo<Ingrediente> inicio;
    private int capacidad;
    private int cantidadIngredientes;

    public CintaTransportadora(int capacidad) {
        this.capacidad = capacidad;
        this.cantidadIngredientes = 0;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (cantidadIngredientes < capacidad) {
            Nodo<Ingrediente> nuevoNodo = new Nodo<>(ingrediente);
            if (inicio == null) {
                inicio = nuevoNodo;
                inicio.setSiguiente(inicio);
            } else {
                Nodo<Ingrediente> ultimo = inicio;
                while (ultimo.getSiguiente() != inicio) {
                    ultimo = ultimo.getSiguiente();
                }
                ultimo.setSiguiente(nuevoNodo);
                nuevoNodo.setSiguiente(inicio);
            }
            cantidadIngredientes++;
        }
    }

    public void moverCinta() {
        if (cantidadIngredientes > 1) {
            inicio = inicio.getSiguiente();
        }
    }

    public boolean contieneIngrediente(Ingrediente ingrediente) {
        if (cantidadIngredientes > 0) {
            Nodo<Ingrediente> actual = inicio;
            for (int i = 0; i < cantidadIngredientes; i++) {
                if (actual.getValor().equals(ingrediente)) {
                    return true;
                }
                actual = actual.getSiguiente();
            }
        }
        return false;
    }

    public Ingrediente tomarIngrediente() {
        if (cantidadIngredientes > 0) {
            Nodo<Ingrediente> nodoTomado = inicio;
            inicio = inicio.getSiguiente();
            cantidadIngredientes--;
            return nodoTomado.getValor();
        } else {
            return null;
        }
    }

    public void tirarIngrediente() {
        if (cantidadIngredientes > 0) {
            inicio = inicio.getSiguiente();
            cantidadIngredientes--;
        }
    }

    public int getCantidadIngredientesEnCinta() {
        return cantidadIngredientes;
    }
}
