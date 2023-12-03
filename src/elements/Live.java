package elements;

import java.awt.Graphics;
import java.awt.Image;
import models.Drawable;

/**
 * Representa las vidas de el avion "bueno" en el juego.
 * 
 * @author juancamposbetancourth
 * @version 27112023
 */
public class Live extends Sprite implements Drawable {

    /**
     * Imagen asociada a la vida.
     */
    private Image image;

    /**
     * Objeto drawable asociado a la vida.
     */
    private Drawable drawable;

    /**
     * Constructor de la clase Live.
     * 
     * @param x La coordenada x inicial de la vida.
     * @param y La coordenada y inicial de la vida.
     */
    public Live(int x, int y) {
        super(x, y, 50, 50);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/live.png");
    }

    /**
     * Dibuja la vida en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja la vida.
     */
    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), null);
        }
    }

    /**
     * Redibuja la vida llamando al método redraw del objeto drawable asociado.
     */
    @Override
    public void redraw() {
        drawable.redraw();
    }
}
