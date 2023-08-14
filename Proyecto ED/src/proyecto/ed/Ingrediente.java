package proyecto.ed;
public class Ingrediente {
     private String nombre;
    private String tipo;

    public Ingrediente(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
}
