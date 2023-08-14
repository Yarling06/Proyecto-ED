
package proyecto.ed;

import java.util.List;

public class CintaTransportadora {
    private List<Ingrediente> ingredientes;
    private int currentPosition;

    public CintaTransportadora(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
        this.currentPosition = 0;
    }
   public Ingrediente tomarIngrediente() {
        Ingrediente ingrediente = ingredientes.get(currentPosition);
        ingredientes.set(currentPosition, null);
        currentPosition = (currentPosition + 1) % ingredientes.size();
        return ingrediente;
    }

    public void moverCinta() {
        currentPosition = (currentPosition + 1) % ingredientes.size();
    }
        public List<Ingrediente> getIngredientesEnCinta() {
        return ingredientes;
    }
}