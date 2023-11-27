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
public class GoodSpaceCraft extends SpaceCraft implements Drawable{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 130;
    public static final int STEP = 40;
    private LinkedList<Live> lives;
    private int auxX = 10;
    private Image image;
    
    public GoodSpaceCraft(int x, int y) {
        super(x, y);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/craft.png");
        this.lives = new LinkedList<>();
        setNumberLives(4);
        createLive(getNumberLives());
    }
    
    public void createLive(int num){
        Live live;
        for(int i = 0; i < num; i++){
            live = new Live(this.auxX, 40);
            this.auxX = this.auxX + 50;
            lives.add(live);
        }
    }
    
    public void addLive(){
        Live live;
        live = new Live(this.auxX, 40);
        this.auxX = this.auxX + 50;
        lives.add(live);
        setNumberLives(getNumberLives()+1);
    }
    
    public void deleteLive(){
        Live live = null;
        for(Live actual: this.lives){
            live = actual;
        }
        this.auxX -= 50;
        setNumberLives(getNumberLives() - 1);
        lives.remove(live);
    }
    
    public void handleKey(int key){
        if(key == KeyEvent.VK_UP |
           key == KeyEvent.VK_DOWN |
           key == KeyEvent.VK_LEFT |
           key == KeyEvent.VK_RIGHT){
            move(key);
            getDrawable().redraw();
        }if(key == KeyEvent.VK_S){
            shoot();
        }
        
    }
    
    @Override
    public void shoot(){
        Bullet bullet = new GoodBullet(getX()+47, getY()-20);
        bullet.setDrawable(this);
        getBullets().add(bullet);
        Thread actual = new Thread(bullet);
        actual.start();
    }
    
    public boolean move(int key){
        int xOriginal = getX();
        int yOriginal = getY();
        
        if(key == KeyEvent.VK_UP)
            setY(getY() - STEP);
        if(key == KeyEvent.VK_DOWN)
            setY(getY() + STEP);
        if(key == KeyEvent.VK_LEFT)
            setX(getX() - STEP);
        if(key == KeyEvent.VK_RIGHT)
            setX(getX() + STEP);

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
        for(int i = 0; i < getBullets().size(); i++){
            if(getBullets().get(i).getY() < 0){
                getBullets().remove(getBullets().get(i));
            }else{
                getBullets().get(i).draw(g);
            }
        }
        for(Live actual: lives){
            actual.draw(g);
        }
    }
    
    @Override
    public void redraw() {
        getDrawable().redraw();
    }
 
}
