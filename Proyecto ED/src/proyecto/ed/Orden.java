package proyecto.ed;

import java.util.ArrayList;

public class Orden {

    private String tipo;
    private ArrayList<Ingrediente> ingredientes;
    private boolean completada;

    public Orden() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Orden(String tipo) {
        this.tipo = tipo;
        this.ingredientes = new ArrayList<>();
        this.completada = false;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public boolean verificarOrden(Nodo<Ingrediente> cintaTransportadora) {
        // Implementar lógica para verificar si la orden está completa
        // Recorrer la cinta transportadora y comparar los ingredientes con los de la orden
        // Si todos los ingredientes están en la orden, marcarla como completada
        // Devolver true si la orden está completada, de lo contrario, false.

        // se crea una lista temporal para almacenar los ingredientes de la cinta transportadora
        ArrayList<Ingrediente> ingredientesCinta = new ArrayList<>();
        Nodo<Ingrediente> nodoActual = cintaTransportadora;

        // aqui se recorre la lista circular de la cinta transportadora
        do {
            ingredientesCinta.add(nodoActual.getDato());
            nodoActual = nodoActual.getSiguiente();
        } while (nodoActual != cintaTransportadora);

        // Verificamos si todos los ingredientes de la orden están en la lista de ingredientes de la cinta
        for (Ingrediente ingrediente : ingredientes) {
            if (!ingredientesCinta.contains(ingrediente)) {
                // Si algún ingrediente no está en la cinta transportadora, la orden no está completa
                completada = false;
                return false;
            }
        }
        // Si todos los ingredientes están en la cinta transportadora, la orden está completa
        completada = true;
        return true;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }
}
