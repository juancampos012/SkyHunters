/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import elements.Space;
import elements.SpaceCraft;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juancamposbetancourth
 */
public class VentanaPrincipal extends javax.swing.JFrame implements Drawable{
    private Space space;
    
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal(Space space) {
        this.space = space;
        initComponents();
    }
    
    public void paint(Graphics g){
        Dimension d = getSize();
        Image imagen = createImage(d.width, d.height);
        Graphics buff = imagen.getGraphics();
        
        space.draw(buff);
        
        g.drawImage(imagen, 0, 0, null);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    
    public void start(){
        space.reStart();
        boolean result = space.start();
        if(result == true){
            int aux = JOptionPane.showConfirmDialog(this, "Quieres volver a empezar?", "Game Over", JOptionPane.YES_NO_OPTION);
            if(aux == 0){
                start();
            }else{
                System.exit(0);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        try {
            // TODO add your handling code here:
            space.handleKey(evt.getKeyCode());
        } catch (InterruptedException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        Space space = new Space(900,900);
        VentanaPrincipal ventana = new VentanaPrincipal(space);
        space.setDrawable(ventana);
        ventana.setSize(900,900);
        ventana.setVisible(true);
        ventana.start();
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void redraw() {
        repaint();
    }
}
