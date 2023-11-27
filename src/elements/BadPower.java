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
public class BadPower extends Power{

    public BadPower(int x) {
        super(x);
        setImage(loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/BadPower.jpeg"));
    }
    

    @Override
    public void draw(Graphics g) {
        if(getImage() != null){
            g.drawImage(getImage(), getX(), getY(), null);
        }  
    }

    @Override
    public void redraw() {
        getDrawable().redraw();
    }
    
}