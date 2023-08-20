package proyecto.ed;

import java.util.Random;

public class GenerarOrdenes {
    private String[] tiposHamburguesa = {
        "Hamburguesa de carne",
        "Hamburguesa con queso",
        "Hamburguesa clásica"
    };
    private int[] puntosHamburguesa = {5, 10, 15};
    private Cola<OrdenHamburguesa> ordenesPendientes;
    private int maximoOrdenesPendientes = 3; 
    private int tiempoParaNuevaOrden = 20000; // 20 segundos
    private long ultimoTiempoGeneracion = System.currentTimeMillis();

    public GenerarOrdenes() {
        ordenesPendientes = new Cola<>();
    }

    private void generarOrdenAleatoria() {
        Random random = new Random();
        if (debeGenerarOrden() && ordenesPendientes.size() < maximoOrdenesPendientes) {
            int tipoOrdenIdx = random.nextInt(3);
            String tipoOrden = tiposHamburguesa[tipoOrdenIdx];
            int puntos = puntosHamburguesa[tipoOrdenIdx];

            OrdenHamburguesa orden = new OrdenHamburguesa(tipoOrden, puntos);
            ordenesPendientes.encolar(orden);
        }
    }

    public boolean debeGenerarOrden() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - ultimoTiempoGeneracion >= tiempoParaNuevaOrden) {
            ultimoTiempoGeneracion = tiempoActual;
            return true;
        }
        return false;
    }

    public void agregarOrden(OrdenHamburguesa orden) {
        ordenesPendientes.encolar(orden);
    }

    public Cola<OrdenHamburguesa> getOrdenesPendientes() {
        return ordenesPendientes;
    }

    public String[] getTiposHamburguesa() {
        return tiposHamburguesa;
    }

    public int[] getPuntosHamburguesa() {
        return puntosHamburguesa;
    }
}
