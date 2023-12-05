package elements;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa una bala "buena" en el juego, pueden ser disparadas por aviones "buenos" e impactan contra aviones "malos".
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class GoodBullet extends Bullet {
    /**
     * Imagen asociada a la bala "buena".
     */
    private Image image;

    /**
     * Constructor de la clase GoodBullet.
     * 
     * @param x La coordenada x inicial de la bala "buena".
     * @param y La coordenada y inicial de la bala "buena".
     */
    public GoodBullet(int x, int y) {
        super(x, y);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/goodBullet.png");
    }

    /**
     * Dibuja la bala "buena" en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja la bala "buena".
     */
    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), null);
        }
    }

    /**
     * Mueve la bala hacia arriba y redibuja.
     */
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
