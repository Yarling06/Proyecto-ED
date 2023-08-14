package proyecto.ed;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameFrame gameFrame = new GameFrame();
                gameFrame.setVisible(true); // Mostrar la interfaz
            }
        });
    }
}

