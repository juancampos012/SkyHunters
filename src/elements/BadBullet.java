/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juancamposbetancourth
 */
public class BadBullet extends Bullet{
    private Image image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/badBullet.png");

    public BadBullet(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }
    }

    @Override
    public void run() {
        while (true) {   
            try {
                Thread.sleep(60);
                this.setY(this.getY() + STEEP);
                this.drawable.redraw();
            } catch (InterruptedException ex) {
            }
        }  
    }
    
}
