/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase2;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author joygu
 */
public class usgTextoMio extends JTextField {

    public usgTextoMio() {
        this.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                ucgTextoMioKeyTyped(evt);
            }
    });
        
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usgTextoMioFocusLost(evt);
            }
        });
        
                }     
 
    
    private void ucgTextoMioKeyTyped(java.awt.event.KeyEvent evt) {                                       
       
        char caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter) && caracter != '\b') {
            evt.consume(); // Ignora el carácter si no es un número
        }
    
    }
    
        private void usgTextoMioFocusLost(java.awt.event.FocusEvent evt) {                                       
        if(this.getText().isEmpty()){
        this.setBorder(new LineBorder(Color.RED));
        }else{
            this.setBorder(new LineBorder(Color.BLACK));
        }
        }
        
        
        public Integer getTextAsInteger(){
           
            Integer retorno= Integer.valueOf(this.getText());
           return retorno;
            
        }
            
        public void setTextAsInteger(Integer valor){
            this.setText(String.valueOf(valor));
        }
        
        
}
