/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import static elements.GoodSpaceCraft.STEP;
import static elements.GoodSpaceCraft.WIDTH;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public abstract class SpaceCraft extends Sprite{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 130;
    protected Drawable drawable;
    protected Boundable boundable;
    
    public SpaceCraft(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
    public abstract void shoot();
    
    public abstract boolean move(int key);
    
    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
     * @param boundable the boundable to set
     */
    public void setBoundable(Boundable boundable) {
        this.boundable = boundable;
    }    
}
