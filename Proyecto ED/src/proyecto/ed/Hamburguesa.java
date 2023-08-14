
package proyecto.ed;

import java.util.List;

public class Hamburguesa {
    private String nombre;
    private List<Ingrediente> ingredientes;

    public Hamburguesa(String nombre, List<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }
    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public int calcularPuntaje() {
        if (nombre.equals("Hamburguesa de carne")) {
            return 5;
        } else if (nombre.equals("Hamburguesa con queso")) {
            return 10;
        } else if (nombre.equals("Hamburguesa clásica")) {
            return 15;
        }
        return 0;
    }
}