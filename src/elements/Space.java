/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public class Space extends Sprite implements Drawable, Boundable{
    public static final int GOOD_TYPE = 1;
    private LinkedList<SpaceCraft> SpaceCrafts;
    private Drawable drawable;
    private Image image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/space1.jpeg");

    public Space(int width, int height){
        super(0, 0, width, height);
        this.SpaceCrafts = new LinkedList<>();
        createSpaceCraft(1);
    }
    
    public void handleKey(int key) throws InterruptedException{
        if(key == KeyEvent.VK_UP |
           key == KeyEvent.VK_DOWN |
           key == KeyEvent.VK_LEFT | 
           key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_S)
        {
            GoodSpaceCraft good = (GoodSpaceCraft) SpaceCrafts.get(0);
            good.handleKey(key);
        }       
    }
    
    public void createSpaceCraft(int type){
        SpaceCraft spaceCraft = null;
        
        if(type == 1){
            spaceCraft = new GoodSpaceCraft((this.getHeight()/2) + 50, 650);
        }
        spaceCraft.setBoundable(this);
        spaceCraft.setDrawable(this);
        SpaceCrafts.add(spaceCraft);
    }

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }
        
        for(SpaceCraft actual:SpaceCrafts){
            actual.draw(g);
        }
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

    @Override
    public boolean isValid(Sprite sprite) {
        if(sprite.getX() < this.getX() | sprite.getY() < this.getY() | sprite.getX()+sprite.getWidth() > this.getX()+this.getWidth() | sprite.getY()+sprite.getHeight() > this.getY()+this.getHeight())
            return false;

        return true;
    }

    /**
     * @param SpaceCrafts the SpaceCrafts to set
     */
    public void setSpaceCrafts(LinkedList<SpaceCraft> SpaceCrafts) {
        this.SpaceCrafts = SpaceCrafts;
    }
    
    
}
