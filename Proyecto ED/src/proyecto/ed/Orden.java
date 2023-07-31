package proyecto.ed;

import java.util.ArrayList;

public class Orden {

    private String tipo;
    private Ingrediente [] ingredientes;
    private int numIngredientes;
    private boolean completada;

    public Orden(String tipo) {
        this.tipo = tipo;
        this.ingredientes = new Ingrediente[4];
        this.numIngredientes= 0;
        this.completada = false;
    }
    
    public void agregarIngrediente(Ingrediente ingrediente) {
        if (numIngredientes < 4) {
            ingredientes[numIngredientes] = ingrediente;
            numIngredientes++;
        }
    }    
    
  public boolean verificarOrden(CintaTransportadora cintaTransportadora) {
        boolean[] ingredientesPresentes = new boolean[numIngredientes];

        // Recorremos los ingredientes de la orden y marcamos si están presentes en la cinta
        for (int i = 0; i < numIngredientes; i++) {
            ingredientesPresentes[i] = cintaTransportadora.contieneIngrediente(ingredientes[i]);
        }

        // Verificamos si todos los ingredientes de la orden están presentes en la cinta
        for (boolean presente : ingredientesPresentes) {
            if (!presente) {
                completada = false;
                return false;
            }
        }

        completada = true;
        return true;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean Completada() {
        return completada;
    }

    public Ingrediente [] getIngredientes(){
        return ingredientes;
    }

   
}
