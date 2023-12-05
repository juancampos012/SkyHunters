package elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Se encarga de leer las posiciones de los meteoritos desde un archivo.
 * 
 * El archivo debe contener líneas que representan las posiciones de los obstáculos, donde
 * cada carácter "-" indica una posición vacía y "*" indica la presencia de un obstáculo.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public abstract class Reader {
    /**
     * Ruta del archivo desde el cual se leerán los valores.
     */
    private String nameFile;
    /**
     * Objeto BufferedReader utilizado para leer el archivo.
     */
    private BufferedReader reader;

    /**
     * Número de líneas leídas del archivo.
     */
    private int numberLine;
    
    /**
     * Linea leida
     */
    private String line;
            
    /**
     * Constructor de la clase `LectorPosiciones`.
     * 
     * Inicializa la ruta del archivo y abre el lector del archivo para lectura.
     */
    public Reader() {
        
    }

    /**
     * Lee una línea del archivo y la convierte a una lista de enteros.
     * 
     * @return Una lista de enteros que representa las posiciones leídas desde el archivo,
     *         o null si no hay más líneas para leer.
     */
    public abstract LinkedList<Integer> readLine();

    /**
     * Cierra el lector del archivo.
     * 
     * Este método debe llamarse cuando ya no se necesite leer del archivo.
     */
    public void closereader() {
        try {
            if (getReader() != null) {
                getReader().close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el lector del archivo - " + getNameFile());
        }
    }

    /**
     * Obtiene el número de líneas leídas del archivo.
     * 
     * @return El número de líneas leídas.
     */
    public int getNumberLine() {
        return numberLine;
    }

    /**
     * @return the nameFile
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * @param nameFile the nameFile to set
     */
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    /**
     * @return the reader
     */
    public BufferedReader getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * @param numberLine the numberLine to set
     */
    public void setNumberLine(int numberLine) {
        this.numberLine = numberLine;
    }

    /**
     * @return the line
     */
    public String getLinea() {
        return line;
    }

    /**
     * @param linea the line to set
     */
    public void setLinea(String linea) {
        this.line = linea;
    }
}
