package proyecto.ed;

import java.util.List;

public class OrdenHamburguesa {
    private String tipoOrden;
    private int puntos;
    private String[] ingredientes;

    public OrdenHamburguesa(String tipoOrden, int puntos, String[] ingredientes) {
        this(tipoOrden, puntos);
    }

    public OrdenHamburguesa(String tipoOrden, int puntos) {
        this.tipoOrden = tipoOrden;
        this.puntos = puntos;
        this.ingredientes = ingredientes;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public int getPuntos() {
        return puntos;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public boolean cumpleConIngredientes(String[] ingredientesHamburguesa) {
        if (ingredientes.length != ingredientesHamburguesa.length) {
            return false;
        }

        for (int i = 0; i < ingredientes.length; i++) {
            if (!ingredientes[i].equalsIgnoreCase(ingredientesHamburguesa[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Orden: " + tipoOrden + ", Puntos: " + puntos;
    }
}

