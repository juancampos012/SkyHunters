/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Escribe un archivo de texto el cual tiene guardados todos los puntajes y va añadiendo uno por uno.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class WriterScore {
    /**
     * Objeto BufferedWriter utilizado para escribir en el archivo.
     */
    private BufferedWriter writer;

    /**
     * Constructor de la clase `Escritor`.
     * 
     * Intenta abrir un archivo de historial para escritura.
     * Si el archivo no existe, se creará; si existe, se abrirá en modo de anexar.
     */
    public WriterScore() {
        try {
            writer = new BufferedWriter(new FileWriter("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/file/puntajes.txt", true));
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado.");
        }
    }

    /**
     * Escribe una línea en el archivo de historial.
     * 
     * @param line La línea que se va a escribir en el archivo.
     */
    public void writerScore(String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error de escritura.");
        }
    }

    /**
     * Cierra el escritor del archivo.
     * 
     * Este método debe llamarse cuando ya no se necesite escribir en el archivo.
     */
    public void exitWriter() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            // Puedes imprimir un mensaje aquí o considerar lanzar la excepción
            System.err.println("Error al cerrar el escritor del archivo.");
        }
    }
}

