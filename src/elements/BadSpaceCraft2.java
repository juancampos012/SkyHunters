package elements;

import java.awt.Graphics;
import java.awt.Image;
import models.Drawable;

/**
 * Representa una nave espacial enemiga en el juego de tipo 2, se mueve automaticamente al ser creada y tambien dispara automaticamnete.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class BadSpaceCraft2 extends BadSpaceCraft implements Drawable{
    private Image image;

    /**
     * Constructor
     * 
     * @param x posicion x en la pantalla 
     * @param y posicion y en la pantalla 
     */
    public BadSpaceCraft2(int x, int y) {
        super(x, y);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/craft2.png");
        setNumberLives(2);
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
