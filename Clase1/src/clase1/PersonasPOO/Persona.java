/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase1.PersonasPOO;

/**
 *
 * @author micha
 */
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

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public String getHobbies() {
        return hobbies;
    }

    public int getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setNivelSatisfaccion(int nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public void nuevo() {
        cedula = "";
        nombre = "";
        apellido = "";
        edad = 0;
        estadoCivil = "";
        genero = "";
        hobbies = "";
        nivelSatisfaccion = 0;
    }



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

    public void cancelar() {

        cedula = "";
        nombre = "";
        apellido = "";
        edad = 0;
        estadoCivil = "";
        genero = "";
        hobbies = "";
        nivelSatisfaccion = 0;
    }

    //para pasar la info a el jtext fiesl
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

    // para pasar la info al jlist
    public String listar() {

        return cedula + " - "
                + nombre + " "
                + apellido + " - Edad: "
                + edad;
    }

    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
