
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joygu
 */
public class Mascota {
    private String nombre;
    private int edad;
    private String tipo;

    public Mascota(String nombre, int edad, String tipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
    }
    
    public void guardar (DefaultTableModel modelo){
        Object[] fila ={nombre, edad, tipo};
        modelo.addRow(fila);
    }
    
    public void editar(DefaultTableModel modelo, int fila){
        
        modelo.setValueAt(nombre, fila, 0);
        modelo.setValueAt(edad, fila, 1);
        modelo.setValueAt(tipo, fila, 2);
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
   
    
    
}


