/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase1.Formularios;


/**
 *
 * @author micha
 */
public class Persona {
    // Atributos
    private String cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String estadoCivil;
    private String genero;
    private String hobbies;
    private int nivelSatisfaccion;
    
    // Constructor vacío
    public Persona() {
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.estadoCivil = "";
        this.genero = "";
        this.hobbies = "";
        this.nivelSatisfaccion = 0;
    }
    // Constructor con parámetros
    public Persona(String cedula, String nombre, String apellido,
            int edad, String estadoCivil, String genero,
            String hobbies, int nivelSatisfaccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.hobbies = hobbies;
        this.nivelSatisfaccion = nivelSatisfaccion;
    }
    // Getters y Setters
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
    // 1. Método Nuevo: reinicia los atributos del objeto
    public void nuevo() {
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.estadoCivil = "";
        this.genero = "";
        this.hobbies = "";
        this.nivelSatisfaccion = 0;
    }
    // 2. Método Guardar: asigna los datos al objeto
    public void guardar(String cedula, String nombre, String apellido,
            int edad, String estadoCivil, String genero,
            String hobbies, int nivelSatisfaccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.hobbies = hobbies;
        this.nivelSatisfaccion = nivelSatisfaccion;
    }
    // 3. Método Editar: actualiza los datos del objeto
    public void editar(String cedula, String nombre, String apellido,
            int edad, String estadoCivil, String genero,
            String hobbies, int nivelSatisfaccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.hobbies = hobbies;
        this.nivelSatisfaccion = nivelSatisfaccion;
    }
    // 4. Método Eliminar: borra los datos del objeto
       // Método para eliminar / limpiar los datos del objeto
    public void eliminar() {
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.estadoCivil = "";
        this.genero = "";
        this.hobbies = "";
        this.nivelSatisfaccion = 0;
    }
    // 5. Método Cancelar: limpia el objeto
    public void cancelar() {
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.estadoCivil = "";
        this.genero = "";
        this.hobbies = "";
        this.nivelSatisfaccion = 0;
    }
    // Para pasar la información completa al JTextArea
    public String obtenerInformacion() {
        return "Cedula: " + cedula + "\n"
                + "Nombre: " + nombre + "\n"
                + "Apellido: " + apellido + "\n"
                + "Edad: " + edad + "\n"
                + "Estado Civil: " + estadoCivil + "\n"
                + "Genero: " + genero + "\n"
                + "Hobbies: " + hobbies + "\n"
                + "Nivel: " + nivelSatisfaccion;
    }
    // Para pasar la información resumida al JList
    public String listar() {
        return cedula + " - "
                + nombre + " "
                + apellido + " - Edad: "
                + edad;
    }
}