
package proyecto.ed;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Juego {
    private int puntajeFinal;
    private int tiempoRestante;
    private ArrayList<Orden> ordenes;
    private Nodo<Ingrediente> cintaTransportadoraInicio;
    private Nodo<Ingrediente> ultimoIngredienteCinta;

    public Juego() {
        puntajeFinal = 0;
        tiempoRestante = 300; // 5 minutos 
        ordenes = new ArrayList<>();
        cintaTransportadoraInicio = null;
        ultimoIngredienteCinta = null;
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
  // implementar logica para recibir orden
        }
    

    public void tomarIngrediente() {
        if (cintaTransportadoraInicio != null) {
            Ingrediente ingredienteTomado = cintaTransportadoraInicio.getDato();
            cintaTransportadoraInicio = cintaTransportadoraInicio.getSiguiente();
            ultimoIngredienteCinta = ultimoIngredienteCinta.getSiguiente();
            
            // Verifica si la orden se completó con el ingrediente tomado
            verificarOrdenesCompletas(ingredienteTomado);
        }
    }

    public void verificarOrdenesCompletas(Ingrediente ingredienteTomado) {
        for (Orden orden : ordenes) {
            if (!orden.verificarOrden(cintaTransportadoraInicio)) {
                // Si la orden no está completa, no realiza ninguna acción
                continue;
            }
            
            // y si esta completa entonces suma los puntos finales
        }
    }

    public int calcularPuntaje() {
           return puntajeFinal;
    }

    public void mostrarResultado() {
   
        System.out.println("¡Tiempo terminado! Puntaje final: " + puntajeFinal);
    }


    }