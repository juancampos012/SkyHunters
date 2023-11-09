/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author juancamposbetancourth
 */
public abstract class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean checkCollision (Sprite sprite) {
        if(this.getX() < sprite.getX() + sprite.getWidth() &&
           this.getX() + this.getWidth() > sprite.getX() &&
           this.getY() < sprite.getY() + sprite.getHeight() &&
           this.getY() + this.getHeight() > sprite.getY())
            return true;
        return false;
    }
    
    public Image loadImage(String name){
        Image image = null;
        try{
            image = ImageIO.read(new File(name));
        }catch (IOException e){
            System.err.println("Imagen no existe");
            System.exit(0);
        }
        return image;
    }
    
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
