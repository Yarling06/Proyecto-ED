package proyecto.ed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        setSize(1920, 1080);
        generarOrdenes = new GenerarOrdenes();
        gestorPuntaje = new GestorPuntaje();

        initComponents();
        initGestorTemporizador();
        addComponentsToFrame();

        cintaTransportadora = new Cola<>();
        chef = new Chef();
        gestorTemporizador = new GestorTemporizador(timeRemaining, 
                new ActionListener() {
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
        timeLabel = new JLabel("Tiempo restante: " + calcularMinutos()
                + " min " + calcularSegundos() + " seg");
        scoreLabel = new JLabel("Puntaje: 0");
        gameOverLabel = new JLabel("");

        orderLabel.setBounds(800, 80, 300, 30);
        timeLabel.setBounds(10, 40, 300, 30);
        scoreLabel.setBounds(10, 70, 300, 30);
        gameOverLabel.setBounds(10, 100, 200, 20);
        
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Maik\\OneDrive\\"
                + "Documentos\\NetBeansProjects\\Proyecto final datos\\"
                + "Proyecto ED\\overcocked imagen.jfif");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 10, logoIcon.getIconWidth(), 
        logoIcon.getIconHeight());
        gamePanel.add(logoLabel);


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
            ingredientButtons[i].setBounds(10 + i * 90, 400, 80,
                    30);
            int finalI = i;
            ingredientButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarIngrediente(new Ingrediente(ingredientNames[finalI])
                    );
                }
            });
            gamePanel.add(ingredientButtons[i]);
        }

        trashButton = new JButton("Tirar");
        trashButton.setBounds(10 + ingredientButtons.length * 90, 400, 
                80, 30);
        trashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desecharIngrediente();
                gamePanel.add(trashButton);
                actualizarInterfaz();
            }
        });

    }

    private void initGestorTemporizador() {
        ActionListener gameTimerAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante = gestorTemporizador.getTiempoRestante();
                if (tiempoRestante > 0) {
                    timeLabel.setText("Tiempo restante: " + calcularMinutos() 
                            + " min " + calcularSegundos() + " seg");
                } else {
                    gestorTemporizador.detenerTemporizador();
                    gameOverLabel.setText("¡Tiempo agotado!");

                }
            }
        };
        gestorTemporizador = new GestorTemporizador(timeRemaining, 
                gameTimerAction);
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
        OrdenHamburguesa orden = new OrdenHamburguesa(tipoOrden, puntos, 
                tiposHamburguesa);
        generarOrdenes.agregarOrden(orden);
        actualizarInterfaz();
    }

    private void entregarHamburguesa() {
        if (chef.tieneHamburguesaEnProceso()) {
            OrdenHamburguesa ordenPendiente = generarOrdenes.
                    getOrdenesPendientes().peek();
            Hamburguesa hamburguesaChef = chef.obtenerHamburguesaEnProceso();

            if (ordenPendiente != null) {
                boolean cumpleIngredientes = hamburguesaChef.estaCompleta
        (ordenPendiente.getIngredientes());

                if (cumpleIngredientes) {
                    int puntosGanados = ordenPendiente.getPuntos();
                    gestorPuntaje.sumarPuntaje(puntosGanados);
                    scoreLabel.setText("Puntaje: " + gestorPuntaje.getPuntaje()
                    );
                    chef.completarHamburguesa();
                    generarOrdenes.getOrdenesPendientes().desencolar();
                    actualizarInterfaz();
                } else {
                    System.out.println("La hamburguesa no cumple con los "
                            + "ingredientes de la orden.");
                }
            } else {
                System.out.println("No hay una orden pendiente para "
                        + "entregar.");
            }
        } else {
            System.out.println("La hamburguesa no está completa aún.");
        }
        actualizarInterfaz();
        ordenSeleccionada = null;
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
        String nombre = nombresIngredientes[new Random().nextInt(
                nombresIngredientes.length)];
        return new Ingrediente(nombre);
    }

    private void agregarIngrediente(Ingrediente ingrediente) {
        if (chef.tieneHamburguesaEnProceso()) {
            chef.agregarIngrediente(ingrediente);
            if (chef.hamburguesaCompleta()) {
                int puntosGanados = chef.obtenerHamburguesaEnProceso().
                        getPuntos();
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
    gamePanel.removeAll(); // Limpia el panel para evitar duplicados

    int posY = 100;
    for (OrdenHamburguesa orden : generarOrdenes.getOrdenesPendientes()) {
        String tipoHamburguesa = orden.getTipoOrden();
        int puntos = orden.getPuntos();

        JLabel orderTextLabel = new JLabel(tipoHamburguesa + " (" + puntos + 
                "pts)");
        orderTextLabel.setBounds(800, posY, 800, 80);
        orderTextLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ordenSeleccionada = orden; // Al hacer clic en una orden, seleccionarla
                actualizarInterfaz(); // Actualizar la interfaz para reflejar la orden seleccionada
            }
        });
        gamePanel.add(orderTextLabel);
        posY += 40;
    }
    posY = 150; // Posición vertical para la cinta transportadora
    int posX = 10;

    for (Ingrediente ingrediente : cintaTransportadora) {
        JButton ingredienteButton = new JButton(ingrediente.getNombre());
        ingredienteButton.setBounds(posX, posY, 80, 30);
        ingredienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarIngrediente(ingrediente); // Llamada a la función para agregar ingrediente
                actualizarInterfaz(); // Actualizar la interfaz después de agregar el ingrediente
            }
        });
        gamePanel.add(ingredienteButton);
        posX += 90;
    }

    timeLabel.setBounds(10, posY + 60, 300, 30);
    scoreLabel.setBounds(10, posY + 90, 300, 30);
    gameOverLabel.setBounds(10, posY + 120, 300, 30);

    JLabel instructionsLabel = new JLabel("Ingredientes para las "
            + "hamburguesas:");
    instructionsLabel.setBounds(10, posY + 160, 300, 30);
    gamePanel.add(instructionsLabel);

    JLabel burgerInfoLabel1 = new JLabel("● Hamburguesa de carne (pan,"
            + " carne) 5pts");
    burgerInfoLabel1.setBounds(10, posY + 190, 400, 30);
    gamePanel.add(burgerInfoLabel1);

    JLabel burgerInfoLabel2 = new JLabel("● Hamburguesa con queso "
            + "(pan, carne, queso) 10pts");
    burgerInfoLabel2.setBounds(10, posY + 220, 400, 30);
    gamePanel.add(burgerInfoLabel2);

    JLabel burgerInfoLabel3 = new JLabel("● Hamburguesa clásica "
            + "(pan, carne, lechuga, queso) 15pts");
    burgerInfoLabel3.setBounds(10, posY + 250, 400, 30);
    gamePanel.add(burgerInfoLabel3);

    gamePanel.add(orderLabel);
    gamePanel.add(timeLabel);
    gamePanel.add(scoreLabel);
    gamePanel.add(gameOverLabel);

    revalidate();
    repaint();
}


    private OrdenHamburguesa ordenSeleccionada; // 

    private void agregarIngredienteOrden(Ingrediente ingrediente) {
        if (ordenSeleccionada != null) {
            ordenSeleccionada.cumpleConIngredientes
        (tiposHamburguesa);
            actualizarInterfaz(); // Actualizar la interfaz después de agregar el ingrediente
        } else {
            System.out.println("Selecciona una orden antes de agregar "
                    + "ingredientes.");
        }
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
