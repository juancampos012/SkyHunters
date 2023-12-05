package elements;

import java.awt.Image;
import java.util.LinkedList;
import models.Drawable;

/**
 * Representa una nave espacial en el juego, sirve como base para las naves espaciales utilizadas en el juego.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public abstract class SpaceCraft extends Sprite implements Drawable {

    /**
     * Ancho predeterminado de la nave espacial.
     */
    public static final int WIDTH = 100;

    /**
     * Altura predeterminada de la nave espacial.
     */
    public static final int HEIGHT = 130;

    /**
     * Lista de balas disparadas por la nave.
     */
    private LinkedList<Bullet> bullets;

    /**
     * Objeto Boundable asociado a la nave espacial.
     */
    protected Boundable boundable;

    /**
     * Número de vidas de la nave espacial.
     */
    private int numberLives;

    /**
     * Objeto Drawable asociado a la nave espacial.
     */
    private Drawable drawable;
    
    /**
     * Imagen asociada al la nave espacial.
     */
    private Image image;

    /**
     * Constructor de la clase SpaceCraft.
     * Inicializa la posición y tamaño de la nave, así como la lista de proyectiles.
     * 
     * @param x La coordenada x inicial de la nave.
     * @param y La coordenada y inicial de la nave.
     */
    public SpaceCraft(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        this.bullets = new LinkedList<>();
    }

    /**
     * Sive para que las subclases implementen el disparo de la nave.
     */
    public abstract void shoot();

    /**
     * Elimina una bala de la lista de proyectiles de la nave.
     * 
     * @param bullet La bala a eliminar.
     */
    public void deleteBullet(Bullet bullet) {
        getBullets().remove(bullet);
    }

    /**
     * Establece el objeto Boundable asociado a la nave espacial.
     * 
     * @param boundable El objeto Boundable a establecer.
     */
    public void setBoundable(Boundable boundable) {
        this.boundable = boundable;
    }

    /**
     * Obtiene el número de vidas de la nave espacial.
     * 
     * @return El número de vidas de la nave espacial.
     */
    public int getNumberLives() {
        return numberLives;
    }

    /**
     * Establece el número de vidas de la nave espacial.
     * 
     * @param numberLives El número de vidas a establecer.
     */
    public void setNumberLives(int numberLives) {
        this.numberLives = numberLives;
    }

    /**
     * Establece el objeto Drawable asociado a la nave espacial.
     * 
     * @param drawable El objeto Drawable a establecer.
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
     * Obtiene el objeto Drawable asociado a la nave espacial.
     * 
     * @return El objeto Drawable asociado a la nave espacial.
     */
    public Drawable getDrawable() {
        return drawable;
    }

    /**
     * Obtiene la lista de balas disparadas por la nave espacial.
     * 
     * @return La lista balas.
     */
    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
