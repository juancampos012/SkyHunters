/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public abstract class Bullet extends Sprite implements Runnable, Drawable{
    ///Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/Asunto.png
    public static final int WIDTH = 7;
    public static final int HEIGHT = 32;
    public static final int STEEP = 40;
    protected Drawable drawable;
    
    public Bullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
    
    //hello 
   
    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract void run();
    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
    
    @Override
    public void redraw() {
        drawable.redraw();
    }
}
