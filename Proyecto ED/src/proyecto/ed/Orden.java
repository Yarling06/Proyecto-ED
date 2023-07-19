
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

    public boolean verificarOrden() {
//falta implementar el contenido del metodo
        return completada;
    }
}


