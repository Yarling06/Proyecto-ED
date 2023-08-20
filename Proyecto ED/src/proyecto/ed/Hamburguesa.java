package proyecto.ed;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Hamburguesa {

    private String tipo;
    private List<Ingrediente> ingredientes;
    private int cantidadIngredientes;

    public Hamburguesa(String tipo) {
        this.tipo = tipo;
        this.ingredientes = new LinkedList<>();
        this.cantidadIngredientes = 0;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (cantidadIngredientes < getMaximoIngredientes()) {
            ingredientes.add(ingrediente);
            cantidadIngredientes++;
        } else {
            System.out.println("La hamburguesa está completa, no se pueden agregar más ingredientes.");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public int getPuntos() {
        if (tipo.equals("Hamburguesa de carne")) {
            return 5;
        } else if (tipo.equals("Hamburguesa con queso")) {
            return 10;
        } else if (tipo.equals("Hamburguesa clásica")) {
            return 15;
        } else {
            return 0;
        }
    }

    public boolean estaCompleta(String[] ingredientesRequeridos) {
        Set<String> ingredientesFaltantes = new HashSet<>(Arrays.asList
        (ingredientesRequeridos));

        for (Ingrediente ingrediente : ingredientes) {
            ingredientesFaltantes.remove(ingrediente.getNombre());
        }

        return ingredientesFaltantes.isEmpty();
    }

    
    private int getMaximoIngredientes() {
        if (tipo.equals("Hamburguesa de carne")) {
            return 2;
        } else if (tipo.equals("Hamburguesa con queso")) {
            return 3;
        } else if (tipo.equals("Hamburguesa clásica")) {
            return 4;
        } else {
            return 0;
        }
    }
}
