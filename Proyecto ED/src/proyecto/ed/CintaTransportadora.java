package proyecto.ed;

import java.util.ArrayList;

public class CintaTransportadora {
    private ArrayList<Ingrediente> ingredientesEnCinta;

    public CintaTransportadora() {
        ingredientesEnCinta = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientesEnCinta.add(ingrediente);
    }

    public void moverCinta() {
        Ingrediente ultimoIngrediente = ingredientesEnCinta.remove(ingredientesEnCinta.size() - 1);
        ingredientesEnCinta.add(0, ultimoIngrediente);
    }

    public ArrayList<Ingrediente> getIngredientesEnCinta() {
        return ingredientesEnCinta;
    }
}