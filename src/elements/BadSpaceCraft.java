/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;

/**
 *
 * @author juancamposbetancourth
 */
public abstract class BadSpaceCraft  extends SpaceCraft implements Runnable{
    private long CRAFT_CREATION_TIME; // 1000 milisegundos = 1 segundo
    private int STEEP = 15;
    private int SLEEP = 80;
    
    public BadSpaceCraft(int x, int y) {
        super(x, y);
    }

    @Override
    public void shoot(){
        BadBullet bullet = new BadBullet(getX()+47, getY()+130);
        bullet.setDrawable(this);
        getBullets().add(bullet);
        Thread actual = new Thread(bullet);
        actual.start();
    }

    @Override
    public abstract void draw(Graphics g); 
    
    @Override
    public void run() {
        if(getX() > 400){
            while (true) {   
                try {
                    Thread.sleep(SLEEP);
                    if(getY() < 200){
                        setY(getY() + STEEP);
                    }else{
                        if(getX() < 0) {
                            STEEP = -STEEP;
                        }
                        
                        if(getX() > 800) {
                            STEEP = -STEEP;
                        }
                        setX(getX() - STEEP);
                    }
                    getDrawable().redraw();
                } catch (InterruptedException ex) {
                }
            }
        }else{
            while (true) {   
                try {
                    Thread.sleep(SLEEP);
                    if(getY() < 200){
                        setY(getY() + STEEP);
                    }else{
                        if(getX() > 800) {
                            STEEP = -STEEP;
                        }
                        
                        if(getX() < 0) {
                            STEEP = -STEEP;
                        }
                        setX(getX() + STEEP);
                    }
                    getDrawable().redraw();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    /**
     * @return the CRAFT_CREATION_TIME
     */
    public long getCRAFT_CREATION_TIME() {
        return CRAFT_CREATION_TIME;
    }

    /**
     * @param CRAFT_CREATION_TIME the CRAFT_CREATION_TIME to set
     */
    public void setCRAFT_CREATION_TIME(long CRAFT_CREATION_TIME) {
        this.CRAFT_CREATION_TIME = CRAFT_CREATION_TIME;
    }
    
    public void move(){
    
    }
}
