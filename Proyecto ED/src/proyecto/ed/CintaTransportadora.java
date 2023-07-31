package proyecto.ed;

public class CintaTransportadora {
    private Ingrediente[] ingredientesEnCinta;
    private int capacidad;
    private int cantidadIngredientes;

    public CintaTransportadora(int capacidad) {
        this.capacidad = capacidad;
        this.ingredientesEnCinta = new Ingrediente[capacidad];
        this.cantidadIngredientes = 0;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (cantidadIngredientes < capacidad) {
            ingredientesEnCinta[cantidadIngredientes] = ingrediente;
            cantidadIngredientes++;
        }
    }

    public void moverCinta() {
        if (cantidadIngredientes > 1) {
            Ingrediente primero = ingredientesEnCinta[0];
            for (int i = 0; i < cantidadIngredientes - 1; i++) {
                ingredientesEnCinta[i] = ingredientesEnCinta[i + 1];
            }
            ingredientesEnCinta[cantidadIngredientes - 1] = primero;
        }
    }
    
    public boolean contieneIngrediente (Ingrediente ingrediente){
        for (int i=0;i< cantidadIngredientes; i++){
            if(ingredientesEnCinta[i] == ingrediente){
                return true;
            }
        }
        return false;
    }

    public Ingrediente tomarIngrediente() {
        if (cantidadIngredientes > 0) {
            Ingrediente ingredienteTomado = ingredientesEnCinta[0];
            for (int i = 0; i < cantidadIngredientes - 1; i++) {
                ingredientesEnCinta[i] = ingredientesEnCinta[i + 1];
            }
            ingredientesEnCinta[cantidadIngredientes - 1] = null;
            cantidadIngredientes--;
            return ingredienteTomado;
        } else {
            return null;
        }
    }

    public void tirarIngrediente() {
        if (cantidadIngredientes > 0) {
            for (int i = 0; i < cantidadIngredientes - 1; i++) {
                ingredientesEnCinta[i] = ingredientesEnCinta[i + 1];
            }
            ingredientesEnCinta[cantidadIngredientes - 1] = null;
            cantidadIngredientes--;
        }
    }

    public int getCantidadIngredientesEnCinta() {
        return cantidadIngredientes;
    }
}



