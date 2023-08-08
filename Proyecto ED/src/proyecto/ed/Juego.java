package proyecto.ed;

import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import proyecto.ed.CintaTransportadora;
import proyecto.ed.Ingrediente;
import proyecto.ed.Orden;

public class Juego {
    private int puntajeFinal;
    private int tiempoRestante;
    private Orden[] ordenes;
    private CintaTransportadora cintaTransportadora;
    private Chef chef;

    public Juego() {
        puntajeFinal = 0;
        tiempoRestante = 300; // 5 minutos
        ordenes = new Orden[3];
        cintaTransportadora = new CintaTransportadora(5);
        chef = new Chef("Nombre del Chef");

        iniciarJuego();
    }

    public void mostrarTiempoRestante(int segundosRestantes) {
        long minutos = TimeUnit.SECONDS.toMinutes(segundosRestantes);
        long segundos = segundosRestantes - TimeUnit.MINUTES.toSeconds(minutos);
        System.out.println("Tiempo restante: " + minutos + " minutos y " + segundos + " segundos");
    }

    public void iniciarJuego() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                recibirOrden();
            }
        }, 20000, 20000);
    }

    public void recibirOrden() {
        if (ordenes[0] == null || ordenes[1] == null || ordenes[2] == null) {
            Random random = new Random();
            int tipoOrden = random.nextInt(3);

            String tipo;

            switch (tipoOrden) {
                case 0:
                    tipo = "Hamburguesa de carne";
                    break;
                case 1:
                    tipo = "Hamburguesa con queso";
                    break;
                case 2:
                    tipo = "Hamburguesa clásica";
                    break;
                default:
                    tipo = "Hamburguesa de carne";
                    break;
            }
            Orden orden = new Orden(tipo);
            for (int i = 0; i < 3; i++) {
                if (ordenes[i] == null) {
                    ordenes[i] = orden;
                    break;
                }
            }
            System.out.println("Nueva orden generada: " + tipo + ". Tiempo restante: " + tiempoRestante);
        } else {
            System.out.println("No se puede generar una nueva orden, ya hay 3 órdenes solicitadas.");
        }
    }

public void jugar() {
    while (tiempoRestante > 0) {
        mostrarTiempoRestante(tiempoRestante);
        mostrarOrdenes();

        String opcion = JOptionPane.showInputDialog(
                "Elige una opción:\n1. Tomar ingrediente\n2. Tirar ingrediente\n3. Salir");

        if (opcion != null) {
            switch (opcion) {
                case "1":
                    chef.tomarIngrediente(cintaTransportadora);
                    verificarOrdenesCompletas();
                    break;
                case "2":
                    chef.tirarIngrediente(cintaTransportadora);
                    break;
                case "3":
                    mostrarResultado();
                    return; // Termina el juego
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }

        tiempoRestante--;
    }

    mostrarResultado();
}


    public void mostrarOrdenes() {
        System.out.println("Órdenes:");
        for (int i = 0; i < 3; i++) {
            if (ordenes[i] != null) {
                String estado = ordenes[i].isCompletada()? "Completada" : "Pendiente";
                System.out.println((i + 1) + ". " + ordenes[i].getTipo() + " - Estado: " + estado);
            }
        }
    }

    public void verificarOrdenesCompletas() {
        Ingrediente ingredienteTomado = cintaTransportadora.tomarIngrediente();

        for (Orden orden : ordenes) {
            if (orden != null && !orden.isCompletada()&& orden.contieneIngrediente(ingredienteTomado)) {
                orden.agregarIngrediente(ingredienteTomado);

                if (orden.verificarOrden(cintaTransportadora)) {
                    puntajeFinal += calcularPuntajeOrden(orden);
                    System.out.println("¡Orden completada! Puntaje obtenido: " + calcularPuntajeOrden(orden));
                }
            }
        }
    }

    public int calcularPuntajeOrden(Orden orden) {
        int puntaje = 0;
        switch (orden.getTipo()) {
            case "Hamburguesa de carne":
                puntaje = 5;
                break;
            case "Hamburguesa con queso":
                puntaje = 10;
                break;
            case "Hamburguesa clásica":
                puntaje = 15;
                break;
        }
        return puntaje;
    }

    public void mostrarResultado() {
        System.out.println("¡Tiempo agotado! Puntaje total: " + puntajeFinal);
    }

}
