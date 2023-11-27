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
public class GoodPower extends Power{

    public GoodPower(int x) {
        super(x);
        setImage(loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/GoodPower.jpeg"));
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
