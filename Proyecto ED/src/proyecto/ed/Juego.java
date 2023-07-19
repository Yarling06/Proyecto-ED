
package proyecto.ed;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Juego {
    private int puntajeFinal;
    private int tiempoRestante;
    private ArrayList<Orden> ordenes;
    private ArrayList<Ingrediente> cintaTransportadora;

    public void Juego() {
        puntajeFinal = 0;
        tiempoRestante = 300; 
        ordenes = new ArrayList<>();
        cintaTransportadora = new ArrayList<>();
    }

    public void iniciarJuego() {
        // falta implementar contenido del metodo
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                recibirOrden();
            }
        }, 20000, 20000);
    }

    public void recibirOrden() {
        // falta implementar contenido
    }

    public void tomarIngrediente() {
         // falta implementar contenido
    }

    public void tirarIngrediente() {
      // falta implementar contenido
    }

    public void actualizarCintaTransportadora() {
             // falta implementar contenido
    }

    public void verificarOrdenesCompletas() {
            // falta implementar contenido
    }

    public int calcularPuntaje() {
              // falta implementar contenido
        return puntajeFinal;
    }

    public void mostrarResultado() {
             // falta implementar contenido
    }
}


