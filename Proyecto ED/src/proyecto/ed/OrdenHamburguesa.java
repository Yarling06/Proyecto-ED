package proyecto.ed;

import java.util.List;

public class OrdenHamburguesa {
    private String tipoHamburguesa;
    private int puntos;
    private String[] ingredientes;

    public OrdenHamburguesa(String tipoHamburguesa, int puntos) {
        this.tipoHamburguesa = tipoHamburguesa;
        this.puntos = puntos;
        this.ingredientes = ingredientes;
    }

    public String getTipoHamburguesa() {
        return tipoHamburguesa;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean cumpleConIngredientes(List<Ingrediente> ingredientesHamburguesa) {
        for (String ingrediente : ingredientes) {
            boolean encontrado = false;
            for (Ingrediente ingredienteHamburguesa : ingredientesHamburguesa) {
                if (ingrediente.equalsIgnoreCase(ingredienteHamburguesa.getNombre())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }
}

