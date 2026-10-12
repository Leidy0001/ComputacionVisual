/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clase1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author micha
 */
public class Clase1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //CREACION DE VENTANAS
        JFrame ventana = new JFrame();
        ventana.setLayout(null);
        ventana.setVisible(true);
        ventana.setSize(500, 500);
        ventana.setTitle("mi primera ventana");
        
        //CREACION DE TEXTO
        JTextField texto = new JTextField();
        ventana.add(texto);
        texto.setBounds(10,10,200,50);
        
        //CREACION DE BOTONES
        JButton boton= new JButton();
        ventana.add(boton);
        boton.setBounds(300, 10, 200, 50);
        boton.setText("aceptar");
        
        //MOSTRAR QUE TECLA PRESIONE
        texto.addKeyListener(new KeyListener() {
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JOptionPane.showMessageDialog(
            ventana,
            "Usted aplasto la tecla: " + e.getKeyChar()
        );
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
});
        
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(ventana, texto.getText());
            }
        });
    }
    
}
