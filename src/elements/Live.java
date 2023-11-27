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
public class Live extends Sprite implements Drawable{
    private Image image;
    private Drawable drawable;
    public Live(int x, int y) {
        super(x, y, 50, 50);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/live.png");
    }

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }  
    }

    @Override
    public void redraw() {
        drawable.redraw();
    }
    
}
