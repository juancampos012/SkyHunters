package elements;

import java.awt.Graphics;

/**
 * Representa una nave espacial enemiga en el juego, se mueve automaticamente al ser creada y tambien dispara automaticamnete.
 * 
 * @author juancamposbetancourth
 * @version 27112023
 */
public abstract class BadSpaceCraft extends SpaceCraft implements Runnable {

    /**
     * Velocidad vertical de la nave espacial enemiga.
     */
    private int STEEP = 15;

    /**
     * Tiempo de espera entre movimientos de la nave espacial enemiga.
     */
    private int SLEEP = 80;

    /**
     * Constructor de la clase BadSpaceCraft.
     * Inicializa la posición de la nave espacial enemiga.
     * 
     * @param x La coordenada x inicial de la nave espacial enemiga.
     * @param y La coordenada y inicial de la nave espacial enemiga.
     */
    public BadSpaceCraft(int x, int y) {
        super(x, y);
    }

    /**
     * Método para realizar el disparo desde la nave espacial enemiga.
     * Crea y lanza un proyectil de tipo BadBullet.
     */
    @Override
    public void shoot() {
        BadBullet bullet = new BadBullet(getX() + 47, getY() + 130);
        bullet.setDrawable(this);
        getBullets().add(bullet);
        Thread actual = new Thread(bullet);
        actual.start();
    }

    /**
     * Método abstracto para dibujar la nave espacial enemiga.
     * 
     * @param g El contexto gráfico en el que se dibuja la nave espacial enemiga.
     */
    @Override
    public abstract void draw(Graphics g);

    /**
     * Método run para el hilo que controla el movimiento de la nave espacial enemiga.
     * La nave espacial enemiga se mueve en patrones específicos según su posición inicial.
     */
    @Override
    public void run() {
        if (getX() > 400) {
            while (true) {
                try {
                    Thread.sleep(SLEEP);
                    if (getY() < 200) {
                        setY(getY() + STEEP);
                    } else {
                        if (getX() < 0) {
                            STEEP = -STEEP;
                        }

                        if (getX() > 800) {
                            STEEP = -STEEP;
                        }
                        setX(getX() - STEEP);
                    }
                    getDrawable().redraw();
                } catch (InterruptedException ex) {
                }
            }
        } else {
            while (true) {
                try {
                    Thread.sleep(SLEEP);
                    if (getY() < 200) {
                        setY(getY() + STEEP);
                    } else {
                        if (getX() > 800) {
                            STEEP = -STEEP;
                        }

                        if (getX() < 0) {
                            STEEP = -STEEP;
                        }
                        setX(getX() + STEEP);
                    }
                    getDrawable().redraw();
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
