package proyecto.ed;

public class CintaTransportadora {
    private Cola<Ingrediente> cinta;

    public CintaTransportadora() {
        cinta = new Cola<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (cinta.size() < 5) {
            cinta.encolar(ingrediente);
        } else {
            cinta.desencolar();
            cinta.encolar(ingrediente);
        }
    }

    public Cola<Ingrediente> getCinta() {
        return cinta;
    }
}
