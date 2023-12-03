package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import models.Drawable;

/**
 * @author juancamposbetancourth
 * La clase Space extiende Sprite e implementa las interfaces Boundable y Drawable.
 */
public class Space extends Sprite implements Boundable, Drawable{

    /**
     * Tipos de naves espaciales
     */
    public static final int GOOD_TYPE = 0;
    public static final int BAD_TYPE1 = 1;
    public static final int BAD_TYPE2 = 2;
    public static final int BAD_TYPE3 = 3;
    public static final int BAD_TYPE4 = 4;

    /**
     * Lista de naves espaciales en el espacio
     */
    private LinkedList<SpaceCraft> SpaceCrafts;

    /**
     * Lista de poderes en el espacio
     */
    private LinkedList<Power> powers;

    /**
     * Lista de meteoritos
     */
    private LinkedList<Meteorite> meteorites;

    /**
     * Lector de posiciones de los meteoritos
     */
    private ReaderPositions reader;

    /**
     * Objeto drawable
     */
    private Drawable drawable;

    /**
     * Pintor de puntuaciones
     */
    private ScorePainter score;

    /**
     * Cantidad maxima de aviones malos
     */
    private int numerSpaceCrafts;

    /**
     * Tiempo de creación de la nave espacial
     */
    private long CRAFT_CREATION_TIME;

    /**
     * Último tiempo de creación
     */
    private long ultimoTiempoCreacion = System.currentTimeMillis();

    /**
     * Tiempo de creación de la bala
     */
    private long BULLET_CREATION_TIME;

    /**
     * Último tiempo de creación de la bala
     */
    private long ultimoTiempoCreacionBullet = System.currentTimeMillis();

    /**
     * Tiempo de creación del poder
     */
    private long POWER_CREATION_TIME;

    /**
     * Último tiempo de creación del poder
     */
    private long ultimoTiempoCreacionPower = System.currentTimeMillis();

    /**
     * Imagen
     */
    private Image image;

    /**
     * Reproductor de sonido principal
     */
    private PlayerSound principalPlayer;

    /**
     * Sonido de fin de juego
     */
    private PlayerSound gameOverSound;

    /**
    * Constructor para la clase Space.
    * Inicializa un nuevo objeto Space con el ancho y alto especificados.
    * Carga una imagen para el fondo del espacio y crea listas para las naves espaciales, poderes y meteoritos.
    * También inicializa un lector de posiciones, un pintor de puntuaciones y sonidos para el jugador principal y el fin del juego.
    *
    * @param width  El ancho del espacio.
    * @param height La altura del espacio.
    */
    public Space(int width, int height){
        super(0, 0, width, height);
        image = loadImage("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/images/space1.jpg");
        this.SpaceCrafts = new LinkedList<>();
        this.powers = new LinkedList<>();
        this.meteorites = new LinkedList<>(); 
        this.reader = new ReaderPositions();
        this.score = new ScorePainter();
        this.principalPlayer = new PlayerSoundPrincipal("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/sounds/space.wav");
        this.gameOverSound = new PlayerSoundSecond("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/sounds/gameOver.wav");
    }
    
    /**
    * Maneja las teclas presionadas por el usuario.
    * Si la tecla presionada es una de las teclas de dirección o la tecla 'S', 
    * Obtiene la primera nave espacial buena de la lista para ser manejada por el usuario.
    *
    * @param key La tecla presionada por el usuario.
    */
    public void handleKey(int key){
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
    
    /**
    * Reinicia el juego.
    * Crea una nueva nave espacial, restablece la puntuación, crea un nuevo lector de posiciones y meteoritos.
    * También restablece el número de naves espaciales y los tiempos de creación de balas, naves espaciales y poderes.
    */
    public void reStart(){
        createSpaceCraft(0);
        score.setScore(0);
        reader = new ReaderPositions();
        createMeteorites();
        numerSpaceCrafts = 2;
        BULLET_CREATION_TIME = 1000;
        CRAFT_CREATION_TIME = 5000;
        POWER_CREATION_TIME = 10000;
    }
    
    
    /**
    * Inicia el juego.
    * Redibuja el espacio, inicia el sonido principal y verifica si la lista de naves espaciales no está vacía.
    * Si no está vacía, entra en un bucle donde verifica las colisiones entre las naves espaciales, los poderes y los meteoritos.
    * Si hay una colisión, limpia las listas, detiene el sonido principal y reproduce el sonido de fin de juego.
    * También crea nuevas naves espaciales, balas y poderes en intervalos de tiempo específicos.
    *
    * @return Verdadero si se pierde el juego,.
    */
    public boolean start(){
        drawable.redraw();
        boolean result;
        boolean result1;
        boolean result2;
        if(!SpaceCrafts.isEmpty()){
            Thread principalSound = new Thread((Runnable) principalPlayer);
            principalSound.start();
            while(!SpaceCrafts.isEmpty()){
                try{
                    if(SpaceCrafts.size() >=1){
                        Thread.sleep(175);
                        checkCollisionGood();
                        result2 = checkCollisionPower();
                        result1 = checkCollisionMeteorite();
                        result = checkCollisionBad();
                        if(result == true || result1 == true || result2 == true){
                            SpaceCrafts.clear();
                            powers.clear();
                            meteorites.clear();
                            principalPlayer.stopSound();
                            new Thread(() -> {
                                ((PlayerSoundSecond)gameOverSound).start();
                            }).start();
                            return true;
                        }
                    }
                    long tiempoActual = System.currentTimeMillis();
                    if (tiempoActual - ultimoTiempoCreacion >= CRAFT_CREATION_TIME) {
                        if(SpaceCrafts.size() < numerSpaceCrafts){
                            // Crear un nuevo avión
                            if(score.getScore() < 500){
                                int type = (int) ((Math.random() * 4) + 1);
                                createSpaceCraft(type);
                                numerSpaceCrafts = 3;
                            }else if(score.getScore() >= 500 && score.getScore() < 1000){
                                int type = (int) ((Math.random() * (4 - 2)) + 2);
                                createSpaceCraft(type);
                                createSpaceCraft(type);
                                numerSpaceCrafts = 4;
                            }else if(score.getScore() >= 1000){
                                createSpaceCraft(4);
                                createSpaceCraft(4);
                                numerSpaceCrafts = 5;
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
        return false;
    }
 
    /**
    * Crea meteoritos en el juego.
    * Lee las posiciones de los meteoritos de un archivo y crea un nuevo meteorito en cada posición.
    * Si el formato del archivo es incorrecto, termina el programa.
    */
    public void createMeteorites(){
        Meteorite meteorite;
        while (true) {  
            int index = 0;
            LinkedList<Integer> positions = reader.readLine();
            if(positions == null){
                return;
            }else if(positions.size() == 17){
                for(Integer actual: positions){
                    if(actual == 1){
                        meteorite = new Meteorite((index * 50), (reader.getNumberLine() * 50));
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
    
    /**
    * Crea un nuevo poder en el juego.
    * El tipo de poder (bueno o malo) se determina por el parámetro.
    *
    * @param type El tipo de poder a crear (0 para malo, 1 para bueno).
    */
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
    
    /**
    * Crea una nueva nave espacial en el juego.
    * El tipo de nave espacial se determina por el parámetro.
    *
    * @param type El tipo de nave espacial a crear (0 para buena, 1-4 para mala).
    */
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
    
    /**
    * Elimina una nave espacial del juego.
    * Si la nave espacial tiene más de una vida, se reduce en uno y se reemplaza por una nave espacial de menor nivel.
    * Si la nave espacial tiene una vida, se elimina del juego.
    * Si la nave espacial es buena y tiene una vida, se elimina del juego y se devuelve verdadero.
    *
    * @param index El índice de la nave espacial a eliminar.
    * @return Verdadero si se eliminó una nave espacial buena con una vida, falso en caso contrario.
    */
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
    
    /**
    * Verifica si hay una colisión entre un meteorito y la nave espacial buena.
    * Si hay una colisión, devuelve verdadero es decir termina el juego.
    *
    * @return Verdadero si hay una colisión, falso en caso contrario.
    */
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
    
    /**
    * Verifica si hay una colisión entre un poder y la nave espacial buena.
    * Si hay una colisión con un poder malo, la nave espacial buena pierde una vida.
    * Si hay una colisión con un poder bueno, la nave espacial buena gana una vida.
    *
    * @return Verdadero si la nave espacial buena se queda sin vidas, falso en caso contrario.
    */
    public boolean checkCollisionPower(){
        GoodSpaceCraft good = (GoodSpaceCraft) SpaceCrafts.get(0);
        int index = 0;
        for(Power actual: powers){
            if(actual instanceof BadPower){
                if(actual.checkCollision(good)){
                    good.deleteLive();
                    powers.remove(index);
                    if(good.getNumberLives() == 0){
                        return true;
                    }
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
        return false;
    }
   
    /**
    * Verifica si hay una colisión entre una bala de la nave espacial buena y una nave espacial mala.
    * Si hay una colisión, la nave espacial mala se le elminima una vida
    */
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
    
    /**
    * Verifica si hay una colisión entre una bala de una nave espacial mala y la nave espacial buena.
    * Si hay una colisión, la nave espacial buena se le elminima una vida.
    *
    * @return Verdadero si la nave espacial buena es eliminada, falso en caso contrario.
    */
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
       
    /**
    * Dibuja el espacio, la puntuación, los meteoritos, las naves espaciales y los poderes en el juego.
    * Si un meteorito sale de la pantalla, se crea un nuevo meteorito en su lugar.
    *
    * @param g El objeto Graphics para dibujar en el juego.
    */
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
    * Establece el objeto Drawable para este objeto.
    *
    * @param drawable El objeto Drawable a establecer.
    */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
    * Verifica si un Sprite es válido en este objeto.
    * Un Sprite es válido si está completamente dentro de los límites de este objeto.
    *
    * @param sprite El Sprite a verificar.
    * @return Verdadero si el Sprite es válido, falso en caso contrario.
    */
    @Override
    public boolean isValid(Sprite sprite) {
        if(sprite.getX() < this.getX() | sprite.getY() < this.getY() | sprite.getX()+sprite.getWidth() > this.getX()+this.getWidth() | sprite.getY()+sprite.getHeight() > this.getY()+this.getHeight())
            return false;

        return true;
    }

    /**
    * Establece la lista de naves espaciales para este objeto.
    *
    * @param SpaceCrafts La lista de naves espaciales a establecer.
    */
    public void setSpaceCrafts(LinkedList<SpaceCraft> SpaceCrafts) {
        this.SpaceCrafts = SpaceCrafts;
    }

    /**
    * Devuelve la lista de naves espaciales de este objeto.
    *
    * @return La lista de naves espaciales.
    */
    public LinkedList<SpaceCraft> getSpaceCrafts() {
        return SpaceCrafts;
    }

    /**
    * Redibuja este objeto utilizando su objeto Drawable.
    */
    @Override
    public void redraw() {
        drawable.redraw();
    }
}
