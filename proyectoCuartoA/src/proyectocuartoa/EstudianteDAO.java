/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocuartoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EstudianteDAO {
    //INTERMEDIARIO ENTRE MI OBJETO Y PROCESOS CRUD
    //La peticion se envia al dao
    //verifica que la peticion sea correcta
    //las vistas son las interfaces(el frontent/ mi NewJFrame)
    //si todo es correcto todo se pasa al modelo/base de datos
    
    public static void GuardarEstudiante(Estudiantes estudiante ){
            try{
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            String sql="insert into estudiante values(?,?,?,?,?)";
            PreparedStatement pst=cc.prepareStatement(sql);
            pst.setString(1, estudiante.getCedula());
            pst.setString(2, estudiante.getNombre());
            pst.setString(3, estudiante.getApellido());
            pst.setString(4, estudiante.getDireccion());
            pst.setString(5, estudiante.getTelefono());
            
            int n=  pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "se guardo el estudiante correctamente");
                //cargarEstudiantes();
            }
            
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    //cogo el list del java.util el de awt ya es antugua
    public static List<Estudiantes> cargarEstudiantes(){
        List<Estudiantes> listaA=new ArrayList<>();
        try {
            
            //SI NECESITO LA CONECION
            conexion cn=new conexion();
            Connection cc=cn.conectar(); //poner el import, tener cuidado de quien se coje la conexion
            String sql="select * from estudiante"; //esta es mi sentencia
            Statement st=cc.prepareStatement(sql);
            ResultSet rs= st.executeQuery(sql); //que hace: le coge al statement y lo divide en partes. como casillita para cedula, otra para nombre, etc.
            while(rs.next()){//mientras tenga algo para recorrer lo va a hacer
                Estudiantes est=new Estudiantes();
                est.setCedula(rs.getString("cedula"));
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                est.setDireccion(rs.getString("direccion"));
                est.setTelefono(rs.getString("telefono"));
                listaA.add(est);
            }
            
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        return listaA;
    }
    
    public static void eliminarEstudiantes(String cedula) {
            
        try {
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            
            String sql= "delete from estudiante where cedula='"+cedula+"'";
            PreparedStatement psd=cc.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(null, "se elimino correctamente");
            
            }
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        
        }

    }
    public static void actualizarEstudiante(Estudiantes est){
        
        try {
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            String sql= "update estudiante set nombre= '"+est.getNombre()
                        + "', apellido= '"+est.getApellido()
                        + "', direccion= '"+est.getDireccion()
                        + "', telefono= '"+est.getTelefono()
                        + "' where cedula = '"+est.getCedula()+"'";
            PreparedStatement psd= cc.prepareStatement(sql);
            
            int n=psd.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(null, "se actualizo");
            cargarEstudiantes();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
    
    
}
