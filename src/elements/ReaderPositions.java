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
 * @version 27112023
 */
public class ReaderPositions {
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
     * Constructor de la clase `LectorPosiciones`.
     * 
     * Inicializa la ruta del archivo y abre el lector del archivo para lectura.
     */
    public ReaderPositions() {
        this.nameFile = "/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/file/obstaculos.txt";
        try {
            reader = new BufferedReader(new FileReader(nameFile));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + nameFile);
        }
    }

    /**
     * Lee una línea del archivo y la convierte a una lista de enteros.
     * 
     * @return Una lista de enteros que representa las posiciones leídas desde el archivo,
     *         o null si no hay más líneas para leer.
     */
    public LinkedList<Integer> readLine() {
        LinkedList<Integer> positions = new LinkedList<>();
        String[] aux;
        try {
            String linea = reader.readLine();
            if (linea != null) {
                numberLine++;
                aux = linea.split("");
                for(String actual: aux){
                    if(actual.equals("-")){
                        positions.add(0);
                    } else if(actual.equals("*")){
                        positions.add(1);
                    }
                }
                return positions;
            }
        } catch (IOException e) {
            System.out.println("Error de lectura en el archivo - " + nameFile);
        }
        return null;
    }

    /**
     * Cierra el lector del archivo.
     * 
     * Este método debe llamarse cuando ya no se necesite leer del archivo.
     */
    public void closereader() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el lector del archivo - " + nameFile);
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
}
