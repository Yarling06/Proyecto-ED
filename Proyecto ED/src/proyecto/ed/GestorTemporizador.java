
package proyecto.ed;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorTemporizador {
    private Timer timer;
    private int tiempoTotal;
    private int tiempoRestante;
    private ActionListener listener;

    public GestorTemporizador(int tiempoTotal, ActionListener listener) {
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.listener = listener;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante >= 0) {
                    listener.actionPerformed(e);
                } else {
                    detenerTemporizador();
                }
            }
        });
    }

    public void iniciarTemporizador() {
        timer.start();
    }

    public void detenerTemporizador() {
        timer.stop();
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }
}
