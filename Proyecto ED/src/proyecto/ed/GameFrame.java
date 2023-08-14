package proyecto.ed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;

public class GameFrame extends javax.swing.JFrame {

    private Chef chef;
    private CintaTransportadora cinta;
    private int puntajeTotal = 0;
    private Timer timer;

    private int tiempoRestante = 300;

    public GameFrame() {
        initComponents();
        chef = new Chef();
        List<Ingrediente> ingredientesCinta = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ingredientesCinta.add(new Ingrediente("Ingrediente " + (i + 1), "Tipo"));
        }
        cinta = new CintaTransportadora(ingredientesCinta);
        actualizarCintaLabel(ingredientesCinta);
      
           
            timer = new Timer(1000, e -> actualizarTiempo());

            timer.start(); // Inicia el timer
        }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        puntajeLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        tiempoLabel = new javax.swing.JLabel();
        imagenLabel = new javax.swing.JLabel();
        lechugaButton = new javax.swing.JButton();
        quesoButton = new javax.swing.JButton();
        carneButton = new javax.swing.JButton();
        panButton = new javax.swing.JButton();
        completarButton = new javax.swing.JButton();
        moverCintaButton = new javax.swing.JButton();
        desecharButton = new javax.swing.JButton();
        cintaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

        fondoPanel.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("OverCooked-Fide");

        puntajeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        puntajeLabel.setText("Puntaje: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(puntajeLabel)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(puntajeLabel)
                .addContainerGap())
        );

        tiempoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tiempoLabel.setText("Tiempo:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tiempoLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tiempoLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imagenLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/ed/imagenes/overcooked.jpg"))); // NOI18N

        javax.swing.GroupLayout fondoPanelLayout = new javax.swing.GroupLayout(fondoPanel);
        fondoPanel.setLayout(fondoPanelLayout);
        fondoPanelLayout.setHorizontalGroup(
            fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(imagenLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(fondoPanelLayout.createSequentialGroup()
                .addGroup(fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoPanelLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel2))
                    .addGroup(fondoPanelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        fondoPanelLayout.setVerticalGroup(
            fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagenLabel)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        lechugaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/ed/imagenes/lechuga.jpg"))); // NOI18N
        lechugaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lechugaButtonActionPerformed(evt);
            }
        });

        quesoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/ed/imagenes/queso.jpg"))); // NOI18N
        quesoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quesoButtonActionPerformed(evt);
            }
        });

        carneButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/ed/imagenes/hamburguesa.jpg"))); // NOI18N
        carneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carneButtonActionPerformed(evt);
            }
        });

        panButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/ed/imagenes/bollo.jpg"))); // NOI18N
        panButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panButtonActionPerformed(evt);
            }
        });

        completarButton.setText("Completar hamburguesa");
        completarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completarButtonActionPerformed(evt);
            }
        });

        moverCintaButton.setText("Mover cinta");
        moverCintaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moverCintaButtonActionPerformed(evt);
            }
        });

        desecharButton.setText("Desechar ingrediente");
        desecharButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desecharButtonActionPerformed(evt);
            }
        });

        cintaLabel.setText("*cinta transportadora*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fondoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cintaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desecharButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(panButton)
                                .addComponent(moverCintaButton)
                                .addComponent(carneButton)
                                .addComponent(quesoButton)
                                .addComponent(lechugaButton)))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(completarButton))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panButton)
                        .addGap(44, 44, 44)
                        .addComponent(carneButton)
                        .addGap(44, 44, 44)
                        .addComponent(quesoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lechugaButton)
                        .addGap(18, 18, 18)
                        .addComponent(moverCintaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(desecharButton)
                        .addGap(18, 18, 18)
                        .addComponent(completarButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fondoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cintaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void completarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completarButtonActionPerformed
        if (!chef.hamburguesasEnProceso.isEmpty()) {
            Hamburguesa hamburguesaActual = chef.hamburguesasEnProceso.peek();
            List<Ingrediente> ingredientesEnHamburguesa = hamburguesaActual.getIngredientes();

            boolean esCorrecta = verificarHamburguesa(ingredientesEnHamburguesa, hamburguesaActual.getNombre());

            if (esCorrecta) {
                int puntaje = hamburguesaActual.calcularPuntaje();
                chef.hamburguesasEnProceso.pop();
                puntajeTotal += puntaje;
                puntajeLabel.setText("Puntaje: " + puntajeTotal);
                actualizarTiempo();
            } else {
                // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
            }
        }

    }

    private boolean verificarHamburguesa(List<Ingrediente> ingredientes, String tipoHamburguesa) {
        // Aquí defines las recetas correctas para cada tipo de hamburguesa
        List<String> recetaHamburguesaCarne = Arrays.asList("Pan", "Carne");
        List<String> recetaHamburguesaConQueso = Arrays.asList("Pan", "Carne", "Queso");
        List<String> recetaHamburguesaClasica = Arrays.asList("Pan", "Carne", "Queso", "Lechuga");

        List<String> ingredientesEnHamburguesa = new ArrayList<>();
        for (Ingrediente ingrediente : ingredientes) {
            ingredientesEnHamburguesa.add(ingrediente.getNombre());
        }
        List<String> recetaCorrecta;
        if (tipoHamburguesa.equals("Hamburguesa de carne")) {
            recetaCorrecta = recetaHamburguesaCarne;
        } else if (tipoHamburguesa.equals("Hamburguesa con queso")) {
            recetaCorrecta = recetaHamburguesaConQueso;
        } else if (tipoHamburguesa.equals("Hamburguesa clásica")) {
            recetaCorrecta = recetaHamburguesaClasica;
        } else {
            return false; // Tipo de hamburguesa no reconocido
        }
        return ingredientesEnHamburguesa.equals(recetaCorrecta);
    }//GEN-LAST:event_completarButtonActionPerformed

    private void panButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panButtonActionPerformed
        if (!chef.hamburguesasEnProceso.isEmpty()) {
            Ingrediente pan = cinta.tomarIngrediente();
            chef.agregarIngrediente(pan);
        }
    }//GEN-LAST:event_panButtonActionPerformed

    private void carneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carneButtonActionPerformed
        if (!chef.hamburguesasEnProceso.isEmpty()) {
            Ingrediente carne = cinta.tomarIngrediente();
            chef.agregarIngrediente(carne);
        }
    }//GEN-LAST:event_carneButtonActionPerformed

    private void quesoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quesoButtonActionPerformed
        if (!chef.hamburguesasEnProceso.isEmpty()) {
            Ingrediente queso = cinta.tomarIngrediente();
            chef.agregarIngrediente(queso);
        }
    }//GEN-LAST:event_quesoButtonActionPerformed

    private void lechugaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lechugaButtonActionPerformed
        if (!chef.hamburguesasEnProceso.isEmpty()) {
            Ingrediente lechuga = cinta.tomarIngrediente();
            chef.agregarIngrediente(lechuga);
        }
    }//GEN-LAST:event_lechugaButtonActionPerformed

    private void moverCintaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moverCintaButtonActionPerformed
        cinta.moverCinta();
        List<Ingrediente> ingredientesEnCinta = cinta.getIngredientesEnCinta();
        actualizarCintaLabel(ingredientesEnCinta);
    }//GEN-LAST:event_moverCintaButtonActionPerformed

    private void desecharButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desecharButtonActionPerformed
        chef.desecharHamburguesa();
    }//GEN-LAST:event_desecharButtonActionPerformed
    private void actualizarCintaLabel(List<Ingrediente> ingredientesEnCinta) {
        StringBuilder cintaText = new StringBuilder("Cinta:\n");
        for (Ingrediente ingrediente : ingredientesEnCinta) {
            if (ingrediente != null) {
                cintaText.append(ingrediente.getNombre()).append("\n");
            } else {
                cintaText.append("Vacío\n");
            }
        }
        cintaLabel.setText(cintaText.toString());
        puntajeLabel.setText("Puntaje: " + puntajeTotal);
    }

    private void actualizarTiempo() {
        tiempoRestante--;
        if (tiempoRestante >= 0) {
            int minutos = tiempoRestante / 60;
            int segundos = tiempoRestante % 60;
            tiempoLabel.setText("Tiempo: " + minutos + ":" + String.format("%02d", segundos));
        } else {
            // Aquí puedes detener el juego o realizar alguna acción cuando se acabe el tiempo
        }
    }



    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton carneButton;
    private javax.swing.JLabel cintaLabel;
    private javax.swing.JButton completarButton;
    private javax.swing.JButton desecharButton;
    private javax.swing.JPanel fondoPanel;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton lechugaButton;
    private javax.swing.JButton moverCintaButton;
    private javax.swing.JButton panButton;
    private javax.swing.JLabel puntajeLabel;
    private javax.swing.JButton quesoButton;
    private javax.swing.JLabel tiempoLabel;
    // End of variables declaration//GEN-END:variables
}
