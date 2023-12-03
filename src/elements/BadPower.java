package elements;

import java.awt.Graphics;

/**
 * Representa un poder "malo" en el juego, el cual, al colisionar con el avión "bueno", le quita una vida.
 * 
 * @author juancamposbetancourth
 * @version 27112023
 */
public class BadPower extends Power {

    /**
     * Constructor de la clase BadPower.
     * 
     * @param x La coordenada x inicial del poder "malo".
     */
    public BadPower(int x) {
        super(x);
        setImage(loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/BadPower.jpeg"));
    }

    /**
     * Dibuja el poder "malo" en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el poder "malo".
     */
    @Override
    public void draw(Graphics g) {
        if (getImage() != null) {
            g.drawImage(getImage(), getX(), getY(), null);
        }
    }

    /**
     * Redibuja el poder "malo" llamando al método redraw del objeto drawable asociado.
     */
    @Override
    public void redraw() {
        getDrawable().redraw();
    }
}
