/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase1;

/**
 *
 * @author micha
 */
public class vector {
     private int[] vector;

    public void crearVector(int tamaño) {
        vector = new int[tamaño];
    }

    public void agregar(int valor, int posicion) throws misExcepciones {

        if (posicion < 0 || posicion >= vector.length) {
            throw new misExcepciones("La posición no existe en el vector");
        }

        vector[posicion] = valor;
    }

    public int consultar(int posicion) throws misExcepciones {

        if (posicion < 0 || posicion >= vector.length) {
            throw new misExcepciones("La posición no existe en el vector");
        }

        return vector[posicion];
    }
}
