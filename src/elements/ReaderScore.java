/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Se encarga de leer los puntajes de el juego desde un archivo.
 *
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 27112023
 */
public class ReaderScore extends Reader{

    public ReaderScore() {
        setNameFile("/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/file/puntajes.txt");
        try {
            setReader(new BufferedReader(new FileReader(getNameFile())));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + getNameFile());
        }
    }

    public LinkedList<Integer> readLine() {
        return null;
    }
    
    public int readLinee() {
        try {
            String linea = getReader().readLine();
            if (linea != null) {
                return Integer.parseInt(linea);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en el archivo - " + getNameFile());
        } catch (IOException e) {
            System.out.println("Error de lectura en el archivo - " + getNameFile());
        }
        return -1;
    }
}

