/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase1.RepasoExamen;

/**
 *
 * @author micha
 */
public class Persona {
    private String nombre;
    private String apellido;
    private String cedula;
    private String carrera;
    private String genero;
    private String gustos;
    private int edad;
    private int satisfaccion;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String cedula, String carrera, String genero, String gustos, int edad, int satisfaccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.carrera = carrera;
        this.genero = genero;
        this.gustos = gustos;
        this.edad = edad;
        this.satisfaccion = satisfaccion;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(int satisfaccion) {
        this.satisfaccion = satisfaccion;
    }
    
    
    
}


