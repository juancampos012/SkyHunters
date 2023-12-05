package elements;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

/**
 * Se utiliza para cargar y reproducir un sonido.
 *
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class PlayerSoundSecond extends PlayerSound{
    
    /**
     * Constructor de la clase PlayerSoundSecond.
     * Carga el sonido principal y abre el clip para su reproducción.
     *
     * @param name El nombre del archivo de sonido a cargar.
     */
    public PlayerSoundSecond(String name) {
        super(name);
        setPrincipalSound(loadClip(name));
        try {
            setClip(AudioSystem.getClip());
        } catch (LineUnavailableException ex) {
            System.out.println("audio no está disponible.");
        }
        try {
            getClip().open(getPrincipalSound());
        } catch (LineUnavailableException ex) {
            System.out.println("audio no está disponible.");
        } catch (IOException ex) {
            System.out.println("error de entrada/salida.");
        }
    }
    
    /**
     * Inicia la reproducción del sonido, si el sonido principal está cargado, se reinicia el clip y se inicia la reproducción.
     */
    public void start(){
        if (getPrincipalSound() != null) {
            getClip().setFramePosition(0);
            getClip().start();
        }
    }
}
