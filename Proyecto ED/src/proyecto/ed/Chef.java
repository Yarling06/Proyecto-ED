package proyecto.ed;
import proyecto.ed.CintaTransportadora;
import proyecto.ed.Ingrediente;

public class Chef {

    private String nombre;

    public Chef(String nombre) {
        this.nombre = nombre;
    }

    public void tomarIngrediente(CintaTransportadora cintaTransportadora) {
        Ingrediente ingredienteTomado = cintaTransportadora.tomarIngrediente();
        if (ingredienteTomado != null) {
            System.out.println(nombre + " ha tomado un " + ingredienteTomado.getTipo() + " de la cinta.");
        } else {
            System.out.println("La cinta transportadora está vacía, no se pudo tomar ningún ingrediente.");
        }
    }

    public void tirarIngrediente(CintaTransportadora cintaTransportadora) {
        cintaTransportadora.tirarIngrediente();
        System.out.println(nombre + " ha tirado un ingrediente a la basura.");
    }
}

