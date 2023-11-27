/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class ScoreDrawing extends Sprite {
    private int score;

    public ScoreDrawing() {
        super(670, 60, 0, 0);
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void draw(Graphics g) {
        // Puedes personalizar el formato y estilo del texto según tus necesidades
        Font font = new Font("Arial", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("score: " + score, getX(), getY());
    }
}