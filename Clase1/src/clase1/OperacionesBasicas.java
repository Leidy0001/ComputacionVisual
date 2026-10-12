/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase1;

/**
 *
 * @author micha
 */
public class OperacionesBasicas {
    private Integer NumeroUno;
    private Integer NumeroDos;
    private Integer Resultado;

    public OperacionesBasicas(Integer NumeroUno, Integer NumeroDos, Integer Resultado) {
        this.NumeroUno = NumeroUno;
        this.NumeroDos = NumeroDos;
        this.Resultado = Resultado;
    }

    public OperacionesBasicas() {
    }

    public OperacionesBasicas(Integer NumeroUno, Integer NumeroDos) {
        this.NumeroUno = NumeroUno;
        this.NumeroDos = NumeroDos;
    }

    
    
    public Integer getNumeroUno() {
        return NumeroUno;
    }

    public void setNumeroUno(Integer NumeroUno) {
        this.NumeroUno = NumeroUno;
    }

    public Integer getNumeroDos() {
        return NumeroDos;
    }

    public void setNumeroDos(Integer NumeroDos) {
        this.NumeroDos = NumeroDos;
    }

    public Integer getResultado() {
        return Resultado;
    }

    public void setResultado(Integer Resultado) {
        this.Resultado = Resultado;
    }
    
    public Integer Sumar(Integer Numero1, Integer Numero2){
    return Numero1+Numero2;
    }
    
    public Integer Restar(Integer Numero1, Integer Numero2){
    return Numero1-Numero2;
    }
    public Integer Division(Integer Numero1, Integer Numero2){
    return Numero1/Numero2;
    }    
    
}
