package elements;

import java.awt.Graphics;
import java.awt.Image;
import models.Drawable;

/**
 * Representa un poder en el juego, puede ser bueno o malo,
 * 
 * @author juancamposbetancourth
 * @version 27112023
 */
public abstract class Power extends Sprite implements Runnable, Drawable {

    /**
     * Objeto drawable asociado al poder.
     */
    private Drawable drawable;

    /**
     * Imagen asociada al poder.
     */
    private Image image;

    /**
     * Paso predeterminado del poder.
     */
    public static final int STEEP = 20;

    /**
     * Constructor de la clase Power.
     * 
     * @param x La coordenada x inicial del poder.
     */
    public Power(int x) {
        super(x, 0, 34, 32);
    }

    /**
     * Dibuja el poder en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el poder.
     */
    @Override
    public abstract void draw(Graphics g);

    /**
     * Mueve el poder hacia abajo.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(80);
                setY(getY() + 10);
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Establece el objeto drawable asociado al poder.
     * 
     * @param drawable El objeto drawable a establecer.
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
     * Obtiene el objeto drawable asociado al poder.
     * 
     * @return El objeto drawable asociado al poder.
     */
    public Drawable getDrawable() {
        return drawable;
    }

    /**
     * Obtiene la imagen asociada al poder.
     * 
     * @return La imagen asociada al poder.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Establece la imagen asociada al poder.
     * 
     * @param image La imagen a establecer.
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
