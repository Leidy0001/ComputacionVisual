package deber;

import javax.swing.table.DefaultTableModel;

public class Persona {
    private String cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String estadoCivil;
    private String genero;
    private String hobbies;
    private int nivelSatisfaccion;

    public Persona() {
    }

    public Persona(String cedula, String nombre, String apellido, int edad, String estadoCivil, String genero, String hobbies, int nivelSatisfaccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.hobbies = hobbies;
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public static String evaluarGenero(boolean isMasculino, boolean isFemenino) {
        if (isMasculino) return "Masculino";
        if (isFemenino) return "Femenino";
        return "No seleccionado";
    }

    public static String evaluarHobbies(boolean futbol, boolean musica, boolean baile) {
        String h = "";
        if (futbol) h += "Futbol ";
        if (musica) h += "Musica ";
        if (baile) h += "Baile ";
        return h.isEmpty() ? "Ninguno" : h.trim();
    }

    public void guardar(DefaultTableModel modelo) {
        Object[] filaNueva = {
            this.cedula, this.nombre, this.apellido, this.edad, 
            this.estadoCivil, this.genero, this.hobbies, this.nivelSatisfaccion
        };
        modelo.addRow(filaNueva);
    }

    public void editar(DefaultTableModel modelo, int fila) {
        modelo.setValueAt(this.cedula, fila, 0);
        modelo.setValueAt(this.nombre, fila, 1);
        modelo.setValueAt(this.apellido, fila, 2);
        modelo.setValueAt(this.edad, fila, 3);
        modelo.setValueAt(this.estadoCivil, fila, 4);
        modelo.setValueAt(this.genero, fila, 5);
        modelo.setValueAt(this.hobbies, fila, 6);
        modelo.setValueAt(this.nivelSatisfaccion, fila, 7);
    }

    public static void eliminar(DefaultTableModel modelo, int fila) {
        if (fila >= 0) {
            modelo.removeRow(fila);
        }
    }

    public String getCedula() { 
        return cedula; 
    }
    public void setCedula(String cedula) { 
        this.cedula = cedula;
    }
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public String getApellido() { 
        return apellido; 
    }
    public void setApellido(String apellido) { 
        this.apellido = apellido; 
    }
    public int getEdad() { 
        return edad; 
    }
    public void setEdad(int edad) { 
        this.edad = edad;
    }
    public String getEstadoCivil() { 
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) { 
        this.estadoCivil = estadoCivil;
    }
    public String getGenero() {
        return genero; 
    }
    public void setGenero(String genero) { 
        this.genero = genero; 
    }
    public String getHobbies() { 
        return hobbies; 
    }
    public void setHobbies(String hobbies) { 
        this.hobbies = hobbies; 
    }
    public int getNivelSatisfaccion() { 
        return nivelSatisfaccion; 
    }
    public void setNivelSatisfaccion(int nivelSatisfaccion) { 
        this.nivelSatisfaccion = nivelSatisfaccion; 
    }
}