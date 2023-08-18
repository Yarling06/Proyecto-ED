package proyecto.ed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameFrame extends JFrame {

    private JPanel gamePanel;
    private JLabel orderLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private Timer timer;
    private JButton[] ingredientButtons;
    private JButton trashButton;
    private Chef chef;

    private int timeRemaining = 5 * 60; // Tiempo en segundos (5 minutos)
    private Queue<OrdenHamburguesa> ordenesPendientes;
    private LinkedList<Ingrediente> cintaTransportadora;

    private String[] tiposHamburguesa = {
        "Hamburguesa de carne",
        "Hamburguesa con queso",
        "Hamburguesa clásica"
    };
    private int[] puntosHamburguesa = {5, 10, 15};

    public GameFrame() {
        setTitle("Overcooked Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        initComponents();
        initTimer();
        addComponentsToFrame();

        ordenesPendientes = new LinkedList<>();
        cintaTransportadora = new LinkedList<>();
        chef = new Chef();

        startGameLoop();
    }

    private void initComponents() {
        gamePanel = new JPanel();
        gamePanel.setLayout(null);

        orderLabel = new JLabel("Órdenes pendientes:");
        timeLabel = new JLabel("Tiempo restante: " + calcularMinutos() + " min " + calcularSegundos() + " seg");
        scoreLabel = new JLabel("Puntaje: 0");

        orderLabel.setBounds(10, 10, 300, 30);
        timeLabel.setBounds(10, 40, 300, 30);
        scoreLabel.setBounds(10, 70, 300, 30);

        gamePanel.add(orderLabel);
        gamePanel.add(timeLabel);
        gamePanel.add(scoreLabel);

        JButton deliverButton = new JButton("Entregar Hamburguesa");
        deliverButton.setBounds(10, 430, 150, 30);
        deliverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregarHamburguesa();
            }
        });
        gamePanel.add(deliverButton);

        ingredientButtons = new JButton[4];
        String[] ingredientNames = {"Pan", "Carne", "Queso", "Lechuga"};
        for (int i = 0; i < ingredientButtons.length; i++) {
            ingredientButtons[i] = new JButton(ingredientNames[i]);
            ingredientButtons[i].setBounds(10 + i * 90, 400, 80, 30);
            int finalI = i;
            ingredientButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarIngrediente(new Ingrediente(ingredientNames[finalI], ingredientNames[finalI]));
                }
            });
            gamePanel.add(ingredientButtons[i]);
        }

        // Botón de basurero
        trashButton = new JButton("Tirar");
        trashButton.setBounds(10 + ingredientButtons.length * 90, 400, 80, 30);
        trashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desecharIngrediente();
            }
        });
        gamePanel.add(trashButton);
    }

    private void initTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timeLabel.setText("Tiempo restante: " + calcularMinutos() + " min " + calcularSegundos() + " seg");
                actualizarInterfaz();
            }
        });
        timer.start();
    }

    private void addComponentsToFrame() {
        getContentPane().add(gamePanel, BorderLayout.CENTER);
    }

    private void startGameLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Generar órdenes aleatorias cada 20 segundos
                    if (timer.getDelay() * (300 - timeRemaining) % 20000 == 0) {
                        generarOrdenAleatoria();
                    }

                    // Mover cinta transportadora
                    moverCinta();

                    // Actualizar la interfaz con los ingredientes y órdenes
                    actualizarInterfaz();

                    try {
                        Thread.sleep(1000); // Esperar 1 segundo antes de la siguiente iteración
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void generarOrdenAleatoria() {
        Random random = new Random();
        if (ordenesPendientes.size() < 3) {
            int tipoOrdenIdx = random.nextInt(3);
            String tipoOrden = tiposHamburguesa[tipoOrdenIdx];
            int puntos = puntosHamburguesa[tipoOrdenIdx];
            OrdenHamburguesa orden = new OrdenHamburguesa(tipoOrden, puntos);
            ordenesPendientes.add(orden);
        }
    }

    private void entregarHamburguesa() {
        if (chef.hamburguesaCompleta()) {
            int puntosGanados = chef.obtenerHamburguesaEnProceso().getPuntos();
            chef.sumarPuntaje(puntosGanados);
            scoreLabel.setText("Puntaje: " + chef.getPuntaje());
            chef.completarHamburguesa(); // Limpiamos la hamburguesa en proceso
            actualizarInterfaz();
        } else {
            System.out.println("La hamburguesa no está completa aún.");
        }
    }

    private void moverCinta() {
        if (cintaTransportadora.size() < 5) {
            cintaTransportadora.addFirst(generarIngredienteAleatorio());
        } else {
            cintaTransportadora.addFirst(cintaTransportadora.removeLast());
        }
    }

    private Ingrediente generarIngredienteAleatorio() {
        String[] nombresIngredientes = {"Pan", "Carne", "Queso", "Lechuga"};
        String nombre = nombresIngredientes[new Random().nextInt(nombresIngredientes.length)];
        return new Ingrediente(nombre.toLowerCase(), nombre);
    }

    private void agregarIngrediente(Ingrediente ingrediente) {
        if (chef.tieneHamburguesaEnProceso()) {
            chef.agregarIngrediente(ingrediente);
            if (chef.hamburguesaCompleta()) {
                int puntosGanados = chef.obtenerHamburguesaEnProceso().getPuntos();
                chef.sumarPuntaje(puntosGanados);
                scoreLabel.setText("Puntaje: " + chef.getPuntaje());
            }
        }
    }

    private void desecharIngrediente() {
        if (!cintaTransportadora.isEmpty()) {
            cintaTransportadora.removeFirst();
        }
    }

    private int calcularMinutos() {
        return timeRemaining / 60;
    }

    private int calcularSegundos() {
        return timeRemaining % 60;
    }

private void actualizarInterfaz() {
    // Limpia el panel del juego antes de repintar
    gamePanel.removeAll();

    // Dibuja las órdenes pendientes
    int posY = 80;
    for (OrdenHamburguesa orden : ordenesPendientes) {
        JLabel orderTextLabel = new JLabel(orden.getTipoHamburguesa());
        orderTextLabel.setBounds(10, posY, 300, 30);
        gamePanel.add(orderTextLabel);
        posY += 30;
    }

    // Dibuja los ingredientes en la cinta transportadora
    int posX = 10;
    for (Ingrediente ingrediente : cintaTransportadora) {
        JButton ingredienteButton = new JButton(ingrediente.getNombre());
        ingredienteButton.setBounds(posX, 300, 80, 30);
        gamePanel.add(ingredienteButton);
        posX += 90;
    }

    // Dibuja los demás componentes
    gamePanel.add(orderLabel);
    gamePanel.add(timeLabel);
    gamePanel.add(scoreLabel);
    for (JButton button : ingredientButtons) {
        gamePanel.add(button);
    }
    gamePanel.add(trashButton);

    // Botón para entregar hamburguesa
    JButton deliverButton = new JButton("Entregar Hamburguesa");
    deliverButton.setBounds(10, 430, 150, 30);
    deliverButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            entregarHamburguesa();
        }
    });
    gamePanel.add(deliverButton);

    // Hace un refresh del panel para mostrar los cambios
    gamePanel.revalidate();
    gamePanel.repaint();
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }
}
