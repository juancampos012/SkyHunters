package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Drawable;

/**
 * Representa un meteorito en el juego, el cual se dibuja al iniciar el juego por medio del archivo de texto 
 * y al colisionar con el avion lo destruye.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class Meteorite extends Sprite implements Runnable {

    /**
     * Imagen asociada a el meteorito.
     */
    private Image image;

    /**
     * Objeto drawable asociado a el meteorito.
     */
    private Drawable drawable;

    /**
     * Paso predeterminado de el meteorito.
     */
    public static final int STEEP = 50;

    /**
     * Constructor de la clase Meteorite.
     * 
     * @param x La coordenada x inicial de el meteorito.
     * @param y La coordenada y inicial de el meteorito.
     */
    public Meteorite(int x, int y) {
        super(x, y, 50, 50);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/meteorite.png");
    }

    /**
     * Dibuja el meteorito en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el meteorito.
     */
    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), null);
        }
    }

    /**
     * Mueve el meteorito hacia abajo y redibuja.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                setY(getY() + STEEP);
                drawable.redraw();
            } catch (InterruptedException ex) {
                Logger.getLogger(Meteorite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Establece el objeto drawable asociado a el meteorito.
     * 
     * @param drawable El objeto drawable a establecer.
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
