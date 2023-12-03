/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import elements.Space;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juancamposbetancourth
 */
public class Juego extends javax.swing.JFrame implements Drawable{
    private Space space;
    
    /**
     * Creates new form Juego
     */
    public Juego(Space space) {
        this.space = space;
        space.setDrawable(this);
        initComponents();
    }

    public void paint(Graphics g){
        Dimension d = getSize();
        Image imagen = createImage(d.width, d.height);
        Graphics buff = imagen.getGraphics();
        
        getSpace().draw(buff);
        
        g.drawImage(imagen, 0, 0, null);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    
    public void start(){
        getSpace().reStart();
        new Thread(() -> {
            boolean result = getSpace().start();
            if(result == true){
            GameOver gameOver = new GameOver();
            gameOver.setVisible(true);
            this.dispose();
        }
        }).start();
    }
    
    @Override
    public void redraw() {
        repaint();
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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
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
        // TODO add your handling code here:
        getSpace().handleKey(evt.getKeyCode());
    }//GEN-LAST:event_formKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        Space space = new Space(900,900);
        Juego ventana = new Juego(space);
        ventana.setSize(900,900);
        ventana.setVisible(true);
        
        ventana.start();
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * @return the space
     */
    public Space getSpace() {
        return space;
    }
}
