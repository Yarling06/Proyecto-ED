
package proyecto.ed;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class OrdenGenerador {
    private Queue<Hamburguesa> ordenesPendientes;
    private String[] tiposHamburguesa = {"Hamburguesa de carne", "Hamburguesa con queso", "Hamburguesa clásica"};

    public OrdenGenerador(Queue<Hamburguesa> ordenesPendientes) {
        this.ordenesPendientes = ordenesPendientes;
    }

    public void generarOrdenAleatoria() {
        Random random = new Random();
        if (ordenesPendientes.size() < 3) {
            int tipoHamburguesaIdx = random.nextInt(tiposHamburguesa.length);
            String tipoHamburguesa = tiposHamburguesa[tipoHamburguesaIdx];
            Hamburguesa hamburguesa = crearHamburguesaAleatoria(tipoHamburguesa);
            ordenesPendientes.add(hamburguesa);
               }
    }

    private Hamburguesa crearHamburguesaAleatoria(String tipo) {
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente("Pan", "Pan"));
        ingredientes.add(new Ingrediente("Carne", "Carne"));
        if (tipo.equals("Hamburguesa con queso") || tipo.equals("Hamburguesa clásica")) {
            ingredientes.add(new Ingrediente("Queso", "Queso"));
        }
        if (tipo.equals("Hamburguesa clásica")) {
            ingredientes.add(new Ingrediente("Lechuga", "Lechuga"));
        }
        return new Hamburguesa(tipo, ingredientes);
    }}
