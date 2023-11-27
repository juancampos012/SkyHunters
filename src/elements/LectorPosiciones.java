/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author juancamposbetancourth
 */
public class LectorPosiciones {
    /**
     * Ruta del archivo desde el cual se leerán los valores.
     */
    private String nombreArchivo;
    /**
     * Objeto BufferedReader utilizado para leer el archivo.
     */
    private BufferedReader lector;

    private int  numberLine;
            
    /**
     * Constructor de la clase `Lector`.
     * 
     * Inicializa la ruta del archivo y abre el lector del archivo para lectura.
     */
    public LectorPosiciones() {
        this.nombreArchivo = "/Users/juancamposbetancourth/NetBeansProjects/SkyHunters/src/file/obstaculos.txt";
        try {
            lector = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + nombreArchivo);
        }
    }

    /**
     * Lee una línea del archivo y la convierte a un entero.
     * 
     * @return El entero leído desde el archivo, o -1 si no hay más líneas para leer.
     */
    public LinkedList leerLinea() {
        LinkedList<Integer> positions = new LinkedList<>();
        String[] aux;
        try {
            String linea = lector.readLine();
            if (linea != null) {
                numberLine++;
                aux = linea.split("");
                for(String actual: aux){
                    if(actual.equals("-")){
                        positions.add(0);
                    }else if(actual.equals("*")){
                        positions.add(1);
                    }
                }
                return positions;
            }
        }catch (IOException e) {
            System.out.println("Error de lectura en el archivo - " + nombreArchivo);
        }
        return null;
    }

    /**
     * Cierra el lector del archivo.
     * 
     * Este método debe llamarse cuando ya no se necesite leer del archivo.
     */
    public void cerrarLector() {
        try {
            if (lector != null) {
                lector.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el lector del archivo - " + nombreArchivo);
        }
    }

    /**
     * @return the numberLine
     */
    public int getNumberLine() {
        return numberLine;
    }
}
