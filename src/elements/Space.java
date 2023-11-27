/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import models.Drawable;

/**
 *
 * @author juancamposbetancourth
 */
public class Space extends Sprite implements Boundable, Drawable{
    public static final int GOOD_TYPE = 0;
    public static final int BAD_TYPE1 = 1;
    public static final int BAD_TYPE2 = 2;
    public static final int BAD_TYPE3 = 3;
    public static final int BAD_TYPE4 = 4;
    private LinkedList<SpaceCraft> SpaceCrafts;
    private LinkedList<Power> powers;
    private LinkedList<Meteorite> meteorites;
    private LectorPosiciones lector;
    private Drawable drawable;
    private ScoreDrawing score;
    private int cant;
    private long CRAFT_CREATION_TIME;
    private long ultimoTiempoCreacion = System.currentTimeMillis();
    private long BULLET_CREATION_TIME;
    private long ultimoTiempoCreacionBullet = System.currentTimeMillis();
    private long POWER_CREATION_TIME;
    private long ultimoTiempoCreacionPower = System.currentTimeMillis();
    private Image image;

    public Space(int width, int height){
        super(0, 0, width, height);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/space1.jpg");
        this.SpaceCrafts = new LinkedList<>();
        this.powers = new LinkedList<>();
        this.meteorites = new LinkedList<>(); 
        this.lector = new LectorPosiciones();
        this.score = new ScoreDrawing();
    }
    
    public void handleKey(int key) throws InterruptedException{
        if(key == KeyEvent.VK_UP |
           key == KeyEvent.VK_DOWN |
           key == KeyEvent.VK_LEFT | 
           key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_S)
        {
            try{
                GoodSpaceCraft good = (GoodSpaceCraft) getSpaceCrafts().get(0);
                good.handleKey(key);
            }catch(IndexOutOfBoundsException e){      
            }
        }
    }
    
    public void reStart(){
        createSpaceCraft(0);
        score.setScore(0);
        lector = new LectorPosiciones();
        createMeteorites();
        cant = 2;
        BULLET_CREATION_TIME = 1000;
        CRAFT_CREATION_TIME = 5000;
        POWER_CREATION_TIME = 10000;
    }
    
    public boolean start(){
        boolean result;
        boolean result1;
        while(true){
            try{
                if(SpaceCrafts.size() >=1){
                    Thread.sleep(175);
                    checkCollisionGood();
                    checkCollisionPower();
                    result1 = checkCollisionMeteorite();
                    result = checkCollisionBad();
                    if(result == true || result1 == true){
                        SpaceCrafts.clear();
                        powers.clear();
                        meteorites.clear();
                        return true;
                    }
                }
                long tiempoActual = System.currentTimeMillis();
                if (tiempoActual - ultimoTiempoCreacion >= CRAFT_CREATION_TIME) {
                    if(SpaceCrafts.size() < cant){
                        // Crear un nuevo avión
                        if(score.getScore() < 500){
                            int type = (int) ((Math.random() * 4) + 1);
                            createSpaceCraft(type);
                            cant = 4;
                        }else if(score.getScore() >= 500 && score.getScore() < 1000){
                            int type = (int) ((Math.random() * (4 - 2)) + 2);
                            createSpaceCraft(type);
                            createSpaceCraft(type);
                            cant = 5;
                        }else if(score.getScore() >= 1000){
                            createSpaceCraft(4);
                            createSpaceCraft(4);
                            cant = 6;
                        }
                        ultimoTiempoCreacion = tiempoActual;
                    }
                }if(tiempoActual - ultimoTiempoCreacionBullet >= BULLET_CREATION_TIME){
                    try{
                        for(SpaceCraft actual: SpaceCrafts){
                            if(actual instanceof BadSpaceCraft){
                                actual.shoot();
                            }
                    }
                    ultimoTiempoCreacionBullet = tiempoActual;
                    }catch(ConcurrentModificationException e){
                    }
                }if(tiempoActual - ultimoTiempoCreacionPower >= POWER_CREATION_TIME){
                    int type = (int) ((Math.random() * 2) + 0);
                    createPower(type);
                    ultimoTiempoCreacionPower = tiempoActual;
                }
            }catch(InterruptedException e){

            }
        }
    }
    
    public void createMeteorites(){
        Meteorite meteorite;
        while (true) {  
            int index = 0;
            LinkedList<Integer> positions = lector.leerLinea();
            if(positions == null){
                return;
            }else if(positions.size() == 17){
                for(Integer actual: positions){
                    if(actual == 1){
                        meteorite = new Meteorite((index * 50), (lector.getNumberLine() * 50));
                        meteorite.setDrawable(this);
                        Thread thread = new Thread(meteorite);
                        thread.start();
                        meteorites.add(meteorite);
                    }
                    index++;
                }
            }else{
                System.out.println("Formato incorrecto");
                System.exit(0);
            }
        }
    }
    
    public void createPower(int type){
        Power power = null;
        int x = (int) (Math.random() * (500 - 300) + 1) + 300;
        if(type == 0){
            power = new BadPower(x);      
        }else if(type == 1){
            power = new GoodPower(x);      
        }
        power.setDrawable(this);
        Thread actual = new Thread((Runnable) power);
        actual.start();
        powers.add(power);
    }
    
    public void createSpaceCraft(int type){
        SpaceCraft spaceCraft = null;
        int x = (int) (Math.random() * (800 - 50) + 1) + 50;
        if(type == 0){
            spaceCraft = new GoodSpaceCraft((this.getWidth()/2) - 50, 700);
        }if(type > 0 && type<=4){
            if(type == 1){
                spaceCraft = new BadSpaceCraft1(x, 1);
            }if(type == 2){
                spaceCraft = new BadSpaceCraft2(x, 1);
            }if(type == 3){
                spaceCraft = new BadSpaceCraft3(x, 1);
            }if(type == 4){
                spaceCraft = new BadSpaceCraft4(x, 1);
            }
            Thread actual = new Thread((Runnable) spaceCraft);
            actual.start();
        }
        spaceCraft.setBoundable(this);
        spaceCraft.setDrawable(this);
        getSpaceCrafts().add(spaceCraft);
    }
    
    public boolean deleteSpaceCraft(int index){
        SpaceCraft spaceCraft = SpaceCrafts.get(index);
        SpaceCraft aux = null;
        if(spaceCraft.getNumberLives() > 1 && spaceCraft instanceof BadSpaceCraft){
            if(spaceCraft.getNumberLives() == 4){
                aux = new BadSpaceCraft3(spaceCraft.getX(), spaceCraft.getY());
            }else if(spaceCraft.getNumberLives() == 3){
                aux = new BadSpaceCraft2(spaceCraft.getX(), spaceCraft.getY());
            }else if(spaceCraft.getNumberLives() == 2){
                aux = new BadSpaceCraft1(spaceCraft.getX(), spaceCraft.getY());
            }
            aux.setDrawable(this);
            Thread actual = new Thread((Runnable) aux);
            actual.start();
            SpaceCrafts.set(index, aux);
            score.setScore(20+score.getScore());
            CRAFT_CREATION_TIME = CRAFT_CREATION_TIME - 10;
            BULLET_CREATION_TIME = BULLET_CREATION_TIME - 10;
        }else if(spaceCraft.getNumberLives() == 1 && spaceCraft instanceof BadSpaceCraft){
            SpaceCrafts.remove(spaceCraft);
            score.setScore(20+score.getScore());
            CRAFT_CREATION_TIME = CRAFT_CREATION_TIME - 30;
            BULLET_CREATION_TIME = BULLET_CREATION_TIME - 30;
        }else if(spaceCraft instanceof GoodSpaceCraft){
            if(spaceCraft.getNumberLives() > 1){
                ((GoodSpaceCraft) spaceCraft).deleteLive();
            }else{
                ((GoodSpaceCraft) spaceCraft).deleteLive();
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCollisionMeteorite(){
        if(SpaceCrafts.size() != 0){
            GoodSpaceCraft good = (GoodSpaceCraft) SpaceCrafts.get(0);
            for(Meteorite actual: meteorites){
                if(actual.checkCollision(good)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void checkCollisionPower(){
        GoodSpaceCraft good = (GoodSpaceCraft) SpaceCrafts.get(0);
        int index = 0;
        for(Power actual: powers){
            if(actual instanceof BadPower){
                if(actual.checkCollision(good)){
                    good.deleteLive();
                    powers.remove(index);
                    break;
                }
            }else if(actual instanceof GoodPower){
                if(actual.checkCollision(good)){
                    good.addLive();
                    powers.remove(index);
                    break;
                }
            }
            index += 1;
        }
    }
   
    public void checkCollisionGood(){
        GoodSpaceCraft good = (GoodSpaceCraft) SpaceCrafts.get(0);
        int index = 0;
        if(SpaceCrafts.size() >= 1){
            try{
                for(SpaceCraft actual: SpaceCrafts){
                    if(actual instanceof BadSpaceCraft){
                        index += 1;
                        for(Bullet actualBullet: good.getBullets()){
                            if(actual.checkCollision(actualBullet)){
                                deleteSpaceCraft(index);
                                good.deleteBullet(actualBullet);
                                break;
                            }
                        }
                    }    
                }
            }catch(ConcurrentModificationException e){}
        }
    }
    
    public boolean checkCollisionBad(){
        boolean result = false;
        GoodSpaceCraft good = (GoodSpaceCraft) SpaceCrafts.get(0);
        if(SpaceCrafts.size() >= 1){
            for(SpaceCraft actual: SpaceCrafts){
                if(actual instanceof BadSpaceCraft){
                    for(Bullet actualBullet: actual.getBullets()){
                        if(good.checkCollision(actualBullet)){
                            result = deleteSpaceCraft(0);
                            actual.deleteBullet(actualBullet);
                            if(result == true){
                                return true;
                            }
                            break;
                        }
                    }
                }    
            }
        }
        return result;
    }
    
    
    @Override
    public void draw(Graphics g) {
        if(image != null){
            g.drawImage(image, getX(), getY(), null);
        }
        
        score.draw(g);
       
        try{
            int index = 0;
            for(Meteorite actual: meteorites){
                if(actual.getY() < 900){
                  actual.draw(g);
                }else{
                    Meteorite aux = new Meteorite(actual.getX(), 50);
                    aux.setDrawable(this);
                    Thread thread = new Thread(aux);
                    thread.start();
                    meteorites.remove(index);
                    meteorites.add(aux);
                }
                index++;
            }
            for(SpaceCraft actual: SpaceCrafts){
                actual.draw(g);
            }
            for(Power actual: powers){
                actual.draw(g);
            }
        }catch(ConcurrentModificationException e){
        }
    }

    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public boolean isValid(Sprite sprite) {
        if(sprite.getX() < this.getX() | sprite.getY() < this.getY() | sprite.getX()+sprite.getWidth() > this.getX()+this.getWidth() | sprite.getY()+sprite.getHeight() > this.getY()+this.getHeight())
            return false;

        return true;
    }

    /**
     * @param SpaceCrafts the SpaceCrafts to set
     */
    public void setSpaceCrafts(LinkedList<SpaceCraft> SpaceCrafts) {
        this.SpaceCrafts = SpaceCrafts;
    }

    /**
     * @return the SpaceCrafts
     */
    public LinkedList<SpaceCraft> getSpaceCrafts() {
        return SpaceCrafts;
    }

    @Override
    public void redraw() {
        drawable.redraw();
    }
    
    
}
