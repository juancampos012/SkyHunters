package elements;

import java.awt.Graphics;
import java.awt.Image;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public class BadSpaceCraft4 extends BadSpaceCraft implements Drawable{
    private Image image;

    public BadSpaceCraft4(int x, int y) {
        super(x, y);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/craft4.png");
        setNumberLives(4);
    }

    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }
        for(int i = 0; i < getBullets().size(); i++){
            if(getBullets().get(i).getY() > 900){
                getBullets().remove(getBullets().get(i));
            }else{
                getBullets().get(i).draw(g);
            }
        }
    }
    
    @Override
    public void redraw() {
        getDrawable().redraw();
    } 
}
