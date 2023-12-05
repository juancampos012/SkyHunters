package elements;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import models.Drawable;

/**
 * Representa una nave espacial controlada por el jugador en el juego, incluye funcionalidades para el movimiento,
 * disparo y manejo de vidas de la nave espacial del jugador.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class GoodSpaceCraft extends SpaceCraft implements Drawable {
    
    /**
     * Paso de movimiento de la nave espacial del jugador.
     */
    public static final int STEP = 40;
    
    /**
     * Lista de vidas asociadas a la nave espacial del jugador.
     */
    private LinkedList<Live> lives;
    
    /**
     * Posición auxiliar X utilizada para posicionar las vidas.
     */
    private int auxX = 10;
    private PlayerSound bulletSound;
    private PlayerSound goodLiveSound;
    private PlayerSound badLiveSound;
    
    /**
     * Constructor de la clase GoodSpaceCraft.
     * Inicializa la posición, tamaño, imagen, vidas y número de vidas iniciales de la nave espacial del jugador.
     * 
     * @param x La coordenada x inicial de la nave.
     * @param y La coordenada y inicial de la nave.
     */
    public GoodSpaceCraft(int x, int y) {
        super(x, y);
        setImage(loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/craft.png"));
        this.lives = new LinkedList<>();
        setNumberLives(4);
        createLive(getNumberLives());
        this.bulletSound = new PlayerSoundSecond("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/sounds/bullet.wav");
        this.goodLiveSound = new PlayerSoundSecond("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/sounds/goofLive.wav");
        this.badLiveSound = new PlayerSoundSecond("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/sounds/badLive.wav");
    }
    
    /**
     * Crea vidas asociadas a la nave espacial del jugador.
     * 
     * @param num El número de vidas a crear.
     */
    public void createLive(int num) {
        Live live;
        for (int i = 0; i < num; i++) {
            live = new Live(this.auxX, 40);
            this.auxX = this.auxX + 50;
            lives.add(live);
        }
    }
    
    /**
     * Agrega una vida a la nave espacial del jugador.
     */
    public void addLive() {
        new Thread(() -> {
            ((PlayerSoundSecond)goodLiveSound).start();
        }).start();
        Live live = new Live(this.auxX, 40);
        this.auxX = this.auxX + 50;
        lives.add(live);
        setNumberLives(getNumberLives() + 1);
    }
    
    /**
     * Elimina una vida de la nave espacial del jugador.
     */
    public void deleteLive() {
        new Thread(() -> {
            ((PlayerSoundSecond)badLiveSound).start();
        }).start();
        Live live = lives.pollLast();
        this.auxX -= 50;
        setNumberLives(getNumberLives() - 1);
    }
    
    /**
     * Maneja las teclas presionadas por el jugador.
     * 
     * @param key El código de la tecla presionada.
     */
    public void handleKey(int key) {
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            move(key);
            getDrawable().redraw();
        }
        if (key == KeyEvent.VK_S) {
            shoot();
        }
    }
    
    /**
     * Realiza el disparo desde la nave espacial del jugador.
     */
    @Override
    public void shoot() {
        new Thread(() -> {
            ((PlayerSoundSecond)bulletSound).start();
        }).start();
        Bullet bullet = new GoodBullet(getX() + 47, getY() - 20);
        bullet.setDrawable(this);
        getBullets().add(bullet);
        Thread actual = new Thread(bullet);
        actual.start();
    }
    
    /**
     * Realiza el movimiento de la nave espacial del jugador.
     * 
     * @param key El código de la tecla presionada que indica la dirección del movimiento.
     * @return true si el movimiento es válido, false si no es válido.
     */
    public boolean move(int key) {
        int xOriginal = getX();
        int yOriginal = getY();
        
        if (key == KeyEvent.VK_UP)
            setY(getY() - STEP);
        if (key == KeyEvent.VK_DOWN)
            setY(getY() + STEP);
        if (key == KeyEvent.VK_LEFT)
            setX(getX() - STEP);
        if (key == KeyEvent.VK_RIGHT)
            setX(getX() + STEP);

        if (!boundable.isValid(this)) {
            this.setX(xOriginal);
            this.setY(yOriginal);
            
            return false;
        }
        
        return true;
    }

    /**
     * Dibuja la nave espacial del jugador, sus balas y vidas en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja la nave espacial del jugador.
     */
    @Override
    public void draw(Graphics g) {
        if (getImage() != null) {
            g.drawImage(getImage(), getX(), getY(), null);
        }
        for (int i = 0; i < getBullets().size(); i++) {
            if (getBullets().get(i).getY() < 0) {
                getBullets().remove(getBullets().get(i));
            } else {
                getBullets().get(i).draw(g);
            }
        }
        for (Live actual : lives) {
            actual.draw(g);
        }
    }
    
    /**
     * Redibuja la nave espacial del jugador llamando al método redraw del objeto drawable asociado.
     */
    @Override
    public void redraw() {
        getDrawable().redraw();
    }
}
