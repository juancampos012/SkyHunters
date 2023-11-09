/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;

/**
 *
 * @author juancamposbetancourth
 */
public abstract class BadSpaceCraft  extends SpaceCraft{

    public BadSpaceCraft(int x, int y) {
        super(x, y);
    }

    @Override
    public abstract void shoot();

    @Override
    public abstract boolean move(int key);

    @Override
    public abstract void draw(Graphics g); 
}
