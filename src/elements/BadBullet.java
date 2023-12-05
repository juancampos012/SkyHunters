package elements;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa una bala "mala" en el juego, pueden ser disparadas por aviones "malos" e impactan contra aviones "buenos".
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class BadBullet extends Bullet {

    /**
     * Imagen asociada a la bala "mala".
     */
    private Image image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/badBullet.png");

    /**
     * Constructor de la clase BadBullet.
     * 
     * @param x La coordenada x inicial de la bala "mala".
     * @param y La coordenada y inicial de la bala "mala".
     */
    public BadBullet(int x, int y) {
        super(x, y);
    }

    /**
     * Dibuja la bala "mala"." en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el proyectil "malo".
     */
    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), null);
        }
    }

    /**
     * Mueve la bala hacia abajo y redibuja.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60);
                this.setY(this.getY() + STEEP);
                this.drawable.redraw();
            } catch (InterruptedException ex) {
            }
        }
    }
}
