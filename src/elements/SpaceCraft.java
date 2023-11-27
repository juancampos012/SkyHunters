/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import static elements.GoodSpaceCraft.WIDTH;
import java.util.LinkedList;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public abstract class SpaceCraft extends Sprite implements Drawable{

    /**
     * @return the bullets
     */
    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * @param bullets the bullets to set
     */
    public void setBullets(LinkedList<Bullet> bullets) {
        this.bullets = bullets;
    }
    public static final int WIDTH = 100;
    public static final int HEIGHT = 130;
    private LinkedList<Bullet> bullets;
    protected Boundable boundable;
    private int numberLives;
    private Drawable drawable;
    
    public SpaceCraft(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        this.bullets = new LinkedList<>();
    }
    public abstract void shoot();
    
    public void deleteBullet(Bullet bullet){
        getBullets().remove(bullet);
    }
    
    /**
     * @param boundable the boundable to set
     */
    public void setBoundable(Boundable boundable) {
        this.boundable = boundable;
    }    
    
    /**
     * @return the numberLives
     */
    public int getNumberLives() {
        return numberLives;
    }

    /**
     * @param numberLives the numberLives to set
     */
    public void setNumberLives(int numberLives) {
        this.numberLives = numberLives;
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


}
