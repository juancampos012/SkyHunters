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
public class ReaderPositions extends Reader{      
    /**
     * Constructor de la clase `LectorPosiciones`.
     * 
     * Inicializa la ruta del archivo y abre el lector del archivo para lectura.
     */
    public ReaderPositions() {
        setNameFile("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/file/obstaculos.txt");
        try {
            setReader(new BufferedReader(new FileReader(getNameFile())));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + getNameFile());
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
            String linea = getReader().readLine();
            if (linea != null) {
                setNumberLine(getNumberLine()+1);
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
            System.out.println("Error de lectura en el archivo - " + getNameFile());
        }
        return null;
    }
}
