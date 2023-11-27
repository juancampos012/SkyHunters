/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author juancamposbetancourth
 */
public class GoodBullet extends Bullet{
    private Image image;
    
    public GoodBullet(int x, int y) {
        super(x, y);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/goodBullet.png");
    }

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }
    }

    @Override
    public void run() {
        while (true) {   
            try {
                Thread.sleep(60);
                this.setY(this.getY() - STEEP);
                this.drawable.redraw();
            } catch (InterruptedException ex) {
            }
        }
    }
    
}
