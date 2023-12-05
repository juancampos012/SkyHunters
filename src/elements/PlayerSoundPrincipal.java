package elements;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

/**
 * Se utiliza para cargar y reproducir un sonido en un hilo de ejecución separado.
 *
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class PlayerSoundPrincipal extends PlayerSound implements Runnable{
    
    /**
     * Constructor de la clase PlayerSoundPrincipal.
     * Carga el sonido principal y abre el clip para su reproducción.
     *
     * @param name El nombre del archivo de sonido a cargar.
     */
    public PlayerSoundPrincipal(String name) {
        super(name);
        setPrincipalSound(loadClip(name));
        try {
            setClip(AudioSystem.getClip());
        } catch (LineUnavailableException ex) {
            // Manejo de excepción si la línea de audio no está disponible.
        }
        try {
            getClip().open(getPrincipalSound());
        } catch (LineUnavailableException ex) {
            // Manejo de excepción si la línea de audio no está disponible.
        } catch (IOException ex) {
            // Manejo de excepción si ocurre un error de entrada/salida.
        }
    }
  
    /**
     * Inicia la reproducción del sonido en un hilo de ejecución separado.
     */
    @Override
    public void run() {
        while (true) {
            if (getPrincipalSound() != null) {
                try {
                    getClip().setFramePosition(0);
                    getClip().start();
                    Thread.sleep(21800);
                } catch (InterruptedException ex) {
                    System.out.println("Error al pausar");
                }
            }
        }
    }
}
