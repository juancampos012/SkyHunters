/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juancamposbetancourth
 */
public class BulletThread extends Thread{
    private Bullet bullet;
    private static final int STEEP = 5;
    
    public BulletThread(Bullet bullet) {
        this.bullet = bullet;
    }
    
    @Override
    public void run(){
        while (true) {   
            try {
                Thread.sleep(50);
                bullet.setY(bullet.getY() - STEEP);
                bullet.redraw();
            } catch (InterruptedException ex) {
                Logger.getLogger(BulletThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
