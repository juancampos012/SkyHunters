package elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Representa la visualización del puntaje en el juego.
 * 
 * @author juancamposbetancourth
 * @version 27112023
 */
public class ScorePainter extends Sprite {

    /**
     * Puntaje actual.
     */
    private int score;

    /**
     * Constructor de la clase ScorePainter.
     * Inicializa la posición del objeto en la pantalla y establece el puntaje inicial en cero.
     */
    public ScorePainter() {
        super(670, 60, 0, 0);
        this.score = 0;
    }

    /**
     * Establece el puntaje.
     * 
     * @param score El puntaje a establecer.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Obtiene el puntaje actual.
     * 
     * @return El puntaje actual.
     */
    public int getScore() {
        return score;
    }

    /**
     * Dibuja el puntaje en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el puntaje.
     */
    @Override
    public void draw(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, getX(), getY());
    }
}
