package elements;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Maneja la reproducción de sonidos.
 *
 * @author juancamposbetancourth
 * @version 27112023
 */
public abstract class PlayerSound{
    /**
     * Flujo de entrada de audio principal
     */
    private AudioInputStream principalSound;
    /**
     * Clip de audio
     */
    private Clip clip; 

    /**
     * Constructor de la clase PlayerSound.
     * Carga el clip de audio y lo abre para su reproducción.
     *
     * @param name Nombre del archivo de audio a cargar.
     */
    public PlayerSound(String name) {
        this.principalSound = loadClip(name);
        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
        }
        try {
            clip.open(principalSound);
        } catch (LineUnavailableException ex) {
        } catch (IOException ex) {
        }
    }
    
    /**
     * Carga un clip de audio desde un archivo.
     *
     * @param name Nombre del archivo de audio a cargar.
     * @return Flujo de entrada de audio del archivo cargado.
     */
    public AudioInputStream loadClip(String name){
        AudioInputStream audioStream = null;
        try{
            File audioFile = new File(name);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            return audioStream;
        }catch(UnsupportedAudioFileException | IOException  ex){
            System.out.println("Error al cargar");
        }
        return audioStream;
    }
    
    /**
     * Detiene la reproducción del sonido.
     */
    public void stopSound() {
        getClip().stop();
    }

    /**
     * @return El flujo de entrada de audio principal.
     */
    public AudioInputStream getPrincipalSound() {
        return principalSound;
    }

    /**
     * Establece el flujo de entrada de audio principal.
     *
     * @param principalSound El nuevo flujo de entrada de audio principal.
     */
    public void setPrincipalSound(AudioInputStream principalSound) {
        this.principalSound = principalSound;
    }

    /**
     * @return El clip de audio.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Establece el clip de audio.
     *
     * @param clip El nuevo clip de audio.
     */
    public void setClip(Clip clip) {
        this.clip = clip;
    }
}

