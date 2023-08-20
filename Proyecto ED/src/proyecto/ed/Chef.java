package proyecto.ed;

public class Chef {
    private Hamburguesa hamburguesaEnProceso;
    private Pila<Hamburguesa> hamburguesasPreparadas;
    private int puntaje;

    public Chef() {
        hamburguesasPreparadas = new Pila<>();
        puntaje = 0;
    }

    public void prepararHamburguesa(Hamburguesa hamburguesa) {
        hamburguesaEnProceso = hamburguesa;
    }

    public Hamburguesa completarHamburguesa() {
        if (hamburguesaEnProceso != null) {
            hamburguesasPreparadas.push(hamburguesaEnProceso);
            puntaje += hamburguesaEnProceso.getPuntos();
            Hamburguesa completada = hamburguesaEnProceso;
            hamburguesaEnProceso = null;
            return completada;
        }
        return null;
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (hamburguesaEnProceso != null) {
            hamburguesaEnProceso.agregarIngrediente(ingrediente);
        }
    }

    public void desecharHamburguesa() {
        if (hamburguesaEnProceso != null) {
            hamburguesaEnProceso = null;
        }
    }

    public boolean tieneHamburguesaEnProceso() {
        return hamburguesaEnProceso != null;
    }

    public boolean hamburguesaCompleta() {
        String[] ingredientesRequeridos = null;
        return hamburguesaEnProceso != null && hamburguesaEnProceso.estaCompleta(ingredientesRequeridos);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Hamburguesa obtenerHamburguesaEnProceso() {
        return hamburguesaEnProceso;
    }
}
