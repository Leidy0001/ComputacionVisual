/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocuartoa;

import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author joygu
 */
public class conexion {
    public Connection conectar(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/cuartoa","root","");
            //JOptionPane.showMessageDialog(null, "ok estas conectdo");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return conn;
    }
}
