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

/**
 *
 * @author juancamposbetancourth
 */
public class GoodSpaceCraft extends SpaceCraft{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 130;
    public static final int STEP = 20;
    private LinkedList<Bullet> bullets;
    private Image image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/craft.png");
    
    public GoodSpaceCraft(int x, int y) {
        super(x, y);
        this.bullets = new LinkedList<>();
    }
    
    
    
    public void handleKey(int key){
        if(key == KeyEvent.VK_UP |
           key == KeyEvent.VK_DOWN |
           key == KeyEvent.VK_LEFT |
           key == KeyEvent.VK_RIGHT){
            move(key);
            drawable.redraw();
        }if(key == KeyEvent.VK_S){
            shoot();
        }
        
    }
    
    @Override
    public void shoot(){
        Bullet bullet = new Bullet(x+47, y-13);
        bullet.setDrawable(drawable);
        bullet.run();
        bullets.add(bullet);
    }
    
    @Override
    public boolean move(int key){
        int xOriginal = x;
        int yOriginal = y;
        
        if(key == KeyEvent.VK_UP)
            y -= STEP;
        if(key == KeyEvent.VK_DOWN)
            y += STEP;
        if(key == KeyEvent.VK_LEFT)
            x -= STEP;
        if(key == KeyEvent.VK_RIGHT)
            x += STEP;

        if(!boundable.isValid(this))
        {
            this.setX(xOriginal);
            this.setY(yOriginal);
            
            return false;
        }
        
        return true;
    }

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).getY() < 0){
                bullets.remove(bullets.get(i));
            }else{
                bullets.get(i).draw(g);
            }
        }
    }
    
    
}
