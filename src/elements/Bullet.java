/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Image;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public class Bullet extends Sprite implements Runnable, Drawable{
    private Image image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/Asunto.png");
    public static final int WIDTH = 7;
    public static final int HEIGHT = 32;
    private Drawable drawable;
    
    public Bullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
    

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }    
    }

    @Override
    public void run() {
        Thread bulletThread = new BulletThread(this);
        bulletThread.start();
    }

    @Override
    public void redraw() {
        drawable.redraw();
    } 

    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
