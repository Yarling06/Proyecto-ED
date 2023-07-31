package proyecto.ed;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Juego {
    private int puntajeFinal;
    private int tiempoRestante;
    private Orden [] ordenes;
    private Nodo<Ingrediente> cintaTransportadoraInicio;
    private Nodo<Ingrediente> ultimoIngredienteCinta;

    public Juego() {
        puntajeFinal = 0;
        tiempoRestante = 300; // osea 5 minutos
        ordenes = new Orden[3];
        cintaTransportadoraInicio = null;
        ultimoIngredienteCinta = null;
    }

    public void iniciarJuego() {
        Timer timer = new Timer();//timer programa tareas para que se ejecuten 
        //de forma repetitiva en intervalos especificos
        timer.scheduleAtFixedRate(new TimerTask() {//representa la tarea a ejecutar
           //y dos valores tiempo inicio y periodo entre cada ejecucion del 
            //juego (rate)
            @Override
            public void run() {//se define adentro para no crear una clase aparte
                recibirOrden();
            }
        }, 20000, 20000);//se configura intervalo cada 20 segundos
    }

    public void recibirOrden() {
        if (ordenes[0] == null || ordenes[1] == null || ordenes[2] == null) {
            Random random = new Random();
            int tipoOrden = random.nextInt(3); //genera un numero entre 1
            //y 3

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

            
     public void tomarIngrediente() {
    }

    public void verificarOrdenesCompletas(Ingrediente ingredienteTomado) {
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

    public int calcularPuntaje() {
        return puntajeFinal;
    }

    public void mostrarResultado() {
        System.out.println("¡Tiempo terminado! Puntaje final: " + puntajeFinal);
    }
    
}