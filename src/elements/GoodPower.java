package elements;

import java.awt.Graphics;

/**
 * Representa un poder "bueno" en el juego, el cual, al colisionar con el avión "bueno" le aumenta una vida.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class GoodPower extends Power {

    /**
     * Constructor de la clase GoodPower.
     * 
     * @param x La coordenada x inicial del poder "bueno".
     */
    public GoodPower(int x) {
        super(x);
        setImage(loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/goodPower.png"));
    }

    /**
     * Dibuja el poder "bueno" en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el poder "bueno".
     */
    @Override
    public void draw(Graphics g) {
        if (getImage() != null) {
            g.drawImage(getImage(), getX(), getY(), null);
        }
    }

    /**
     * Redibuja el poder "bueno" llamando al método redraw del objeto drawable asociado.
     */
    @Override
    public void redraw() {
        getDrawable().redraw();
    }
}
