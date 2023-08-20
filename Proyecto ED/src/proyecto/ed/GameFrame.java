package proyecto.ed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrame extends JFrame {

    private JPanel gamePanel;
    private JLabel orderLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel gameOverLabel; 
    private Timer timer;
    private Timer gameTimer; 
    private JButton[] ingredientButtons;
    private JButton trashButton;
    private Chef chef;

    private int timeRemaining = 5 * 60; // 5 minutos
    private GenerarOrdenes generarOrdenes;
    private Cola<Ingrediente> cintaTransportadora = new Cola<>();
    private GestorTemporizador gestorTemporizador;
    private GestorPuntaje gestorPuntaje;
    private int tiempoRestante = timeRemaining;
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
        initGestorTemporizador();
        addComponentsToFrame();

        generarOrdenes = new GenerarOrdenes();
        cintaTransportadora = new Cola<>();
        chef = new Chef();
        gestorTemporizador = new GestorTemporizador(timeRemaining, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInterfaz();
            }
        });
        gestorPuntaje = new GestorPuntaje();

   
        gestorTemporizador.iniciarTemporizador();

        startGameLoop();
    }

    private void initComponents() {
        gamePanel = new JPanel();
        gamePanel.setLayout(null);

        orderLabel = new JLabel("Órdenes pendientes:");
        timeLabel = new JLabel("Tiempo restante: " + calcularMinutos() + " min " + calcularSegundos() + " seg");
        scoreLabel = new JLabel("Puntaje: 0");
        gameOverLabel = new JLabel(""); 

        orderLabel.setBounds(10, 10, 300, 30);
        timeLabel.setBounds(10, 40, 300, 30);
        scoreLabel.setBounds(10, 70, 300, 30);
        gameOverLabel.setBounds(10, 100, 300, 30); 

        gamePanel.add(orderLabel);
        gamePanel.add(timeLabel);
        gamePanel.add(scoreLabel);
        gamePanel.add(gameOverLabel); 

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
                    agregarIngrediente(new Ingrediente(ingredientNames[finalI]));
                }
            });
            gamePanel.add(ingredientButtons[i]);
        }

        trashButton = new JButton("Tirar");
        trashButton.setBounds(10 + ingredientButtons.length * 90, 400, 80, 30);
        trashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desecharIngrediente();
            }
        });

        gamePanel.add(trashButton);
    actualizarInterfaz();
    }
    

    private void initGestorTemporizador() {
        ActionListener gameTimerAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante = gestorTemporizador.getTiempoRestante();
                if (tiempoRestante > 0) {
                    timeLabel.setText("Tiempo restante: " + calcularMinutos() + " min " + calcularSegundos() + " seg");
                } else {
                    gestorTemporizador.detenerTemporizador(); 
                    gameOverLabel.setText("¡Tiempo agotado!"); 
                
                }
            }
        };
        gestorTemporizador = new GestorTemporizador(timeRemaining, gameTimerAction);
        gestorTemporizador.iniciarTemporizador(); 
    }

    private void addComponentsToFrame() {
        getContentPane().add(gamePanel, BorderLayout.CENTER);
    }

    private void startGameLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (generarOrdenes.debeGenerarOrden()) {
                        generarOrdenesPendientes(); 
                    }

                    moverCinta();
                    actualizarInterfaz();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void generarOrdenesPendientes() {
        Random random = new Random();
        int tipoOrdenIdx = random.nextInt(3);
        String tipoOrden = tiposHamburguesa[tipoOrdenIdx];
        int puntos = puntosHamburguesa[tipoOrdenIdx];
        OrdenHamburguesa orden = new OrdenHamburguesa(tipoOrden, puntos);
        generarOrdenes.agregarOrden(orden);
    }

    
    private void entregarHamburguesa() {
        if (chef.tieneHamburguesaEnProceso()) {
            OrdenHamburguesa ordenPendiente = generarOrdenes.getOrdenesPendientes().peek();
            Hamburguesa hamburguesaChef = chef.obtenerHamburguesaEnProceso();

            if (ordenPendiente != null && ordenPendiente.cumpleConIngredientes(hamburguesaChef.getIngredientes())) {
                int puntosGanados = ordenPendiente.getPuntos();
                gestorPuntaje.sumarPuntaje(puntosGanados);
                scoreLabel.setText("Puntaje: " + gestorPuntaje.getPuntaje());
                chef.completarHamburguesa();
                generarOrdenes.getOrdenesPendientes().desencolar();
                actualizarInterfaz();
            } else {
                System.out.println("La hamburguesa no cumple con los ingredientes de la orden.");
            }
        } else {
            System.out.println("La hamburguesa no está completa aún.");
        }
    }

    private void moverCinta() {
        if (cintaTransportadora.size() < 5) {
            cintaTransportadora.encolar(generarIngredienteAleatorio());
        } else {
            cintaTransportadora.desencolar();
            cintaTransportadora.encolar(generarIngredienteAleatorio());
        }
    }

    private Ingrediente generarIngredienteAleatorio() {
        String[] nombresIngredientes = {"Pan", "Carne", "Queso", "Lechuga"};
        String nombre = nombresIngredientes[new Random().nextInt(nombresIngredientes.length)];
        return new Ingrediente(nombre);
    }

    private void agregarIngrediente(Ingrediente ingrediente) {
        if (chef.tieneHamburguesaEnProceso()) {
            chef.agregarIngrediente(ingrediente);
            if (chef.hamburguesaCompleta()) {
                int puntosGanados = chef.obtenerHamburguesaEnProceso().getPuntos();
                gestorPuntaje.sumarPuntaje(puntosGanados);
                scoreLabel.setText("Puntaje: " + gestorPuntaje.getPuntaje());
            }
        }
    }

    private void desecharIngrediente() {
        cintaTransportadora.desencolar(); 
        actualizarInterfaz(); 
    }

    private int calcularMinutos() {
        return tiempoRestante / 60;
    }

    private int calcularSegundos() {
        return tiempoRestante % 60;
    }

    private void actualizarInterfaz() {
        
        
        int posY = 100;
        for (OrdenHamburguesa orden : generarOrdenes.getOrdenesPendientes()) {
            String tipoHamburguesa = orden.getTipoHamburguesa();
            int puntos = orden.getPuntos();

            JLabel orderTextLabel = new JLabel(tipoHamburguesa + " (" + puntos + "pts)");
            orderTextLabel.setBounds(10, posY, 300, 30);
            gamePanel.add(orderTextLabel);
            posY += 40; 
        }


        int posX = 10;
        for (Ingrediente ingrediente : cintaTransportadora) {
            JButton ingredienteButton = new JButton(ingrediente.getNombre());
            ingredienteButton.setBounds(posX, 300, 80, 30);
            gamePanel.add(ingredienteButton);
            posX += 90;
        }

     
        timeLabel.setText("Tiempo restante: " + calcularMinutos() + " min " + calcularSegundos() + " seg");
        scoreLabel.setText("Puntaje: " + gestorPuntaje.getPuntaje());

        
        gamePanel.add(orderLabel);
        gamePanel.add(timeLabel);
        gamePanel.add(scoreLabel);
        gamePanel.add(gameOverLabel);

        revalidate();
        repaint();
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
