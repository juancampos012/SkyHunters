package elements;

import java.awt.Graphics;
import models.Drawable;

/**
 * Representa una bala en el juego estas puden ser disparadas por los aviones e impactan contra los aviones tambien.
 * 
 * @author juancamposbetancourth
 * @version  27112023
 */
public abstract class Bullet extends Sprite implements Runnable, Drawable {

    /**
     * Ancho predeterminado del proyectil.
     */
    public static final int WIDTH = 7;

    /**
     * Altura predeterminada del proyectil.
     */
    public static final int HEIGHT = 32;

    /**
     * Paso predeterminado del proyectil.
     */
    public static final int STEEP = 40;

    /**
     * Objeto drawable asociado al proyectil.
     */
    protected Drawable drawable;

    /**
     * Constructor de la clase Bullet.
     * 
     * @param x La coordenada x inicial del proyectil.
     * @param y La coordenada y inicial del proyectil.
     */
    public Bullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    /**
     * Dibuja el proyectil en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el proyectil.
     */
    @Override
    public abstract void draw(Graphics g);

    /**
     * Se ejecuta cuando se inicia el hilo del proyectil.
     */
    @Override
    public abstract void run();

    /**
     * Establece el objeto drawable asociado al proyectil.
     * 
     * @param drawable El objeto drawable a establecer.
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
     * Redibuja una bala llamando al método redraw del objeto drawable asociado.
     */
    @Override
    public void redraw() {
        drawable.redraw();
    }
}
