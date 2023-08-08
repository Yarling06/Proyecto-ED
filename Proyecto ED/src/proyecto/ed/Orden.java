package proyecto.ed;

import java.util.ArrayList;

public class Orden {

    private String tipo;
    private ArrayList<Ingrediente> ingredientes;
    private boolean completada;

    public Orden(String tipo) {
        this.tipo = tipo;
        this.ingredientes = new ArrayList<>();
        this.completada = false;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public boolean contieneIngrediente(Ingrediente ingrediente) {
        return ingredientes.contains(ingrediente);
    }

    public boolean verificarOrden(CintaTransportadora cintaTransportadora) {
        for (Ingrediente ingrediente : ingredientes) {
            if (!cintaTransportadora.contieneIngrediente(ingrediente)) {
                return false;
            }
        }
        return true;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }
}
