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
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public class Meteorite extends Sprite implements Runnable{
    private Image image;
    private Drawable drawable;
    public static final int STEEP = 50;
    
    public Meteorite(int x, int y) {
        super(x, y, 50, 50);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/meteorite.png");
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
                Thread.sleep(5000);
                setY(getY() + STEEP);
                drawable.redraw();
            } catch (InterruptedException ex) {
                Logger.getLogger(Meteorite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
    
    
}
