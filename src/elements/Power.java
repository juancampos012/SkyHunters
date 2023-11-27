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
public abstract class Power extends Sprite implements Runnable,Drawable{
    private Drawable drawable;
    private Image image;
    public static final int STEEP = 20;
    
    public Power(int x) {
        super(x, 0, 34, 32);
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public void run(){
        while (true) {      
            try {
                Thread.sleep(80);
                setY(getY() + 10);
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
     * @return the drawable
     */
    public Drawable getDrawable() {
        return drawable;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    
}
