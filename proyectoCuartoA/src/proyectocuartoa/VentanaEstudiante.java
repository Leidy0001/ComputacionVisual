/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectocuartoa;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author joygu
 */
public class VentanaEstudiante extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaEstudiante.class.getName());

    /**
     * Creates new form VentanaEstudiante
     */
    public VentanaEstudiante() {
        initComponents();
        cargarEstudiantes();
        selecionarEstudiante();
        cargarEstudiantesDAO();
        
    }
    //debo poner lo mismo que esta en mi base de datos, los nombres
    
    public void cargarEstudiantes(){
        try {
            String titulos []={"cedula", "nombre", "apellido", "dieccion", "telefono"};
            String registros []= new String[5];
            DefaultTableModel modelo=new DefaultTableModel(null, titulos);
            jtblEstudiantes.setModel(modelo);
            conexion cn=new conexion();
            Connection cc=cn.conectar(); //poner el import, tener cuidado de quien se coje la conexion
            String sql="select * from estudiante"; //esta es mi sentencia
            Statement st=cc.prepareStatement(sql);
            ResultSet rs= st.executeQuery(sql); //que hace: le coge al statement y lo divide en partes. como casillita para cedula, otra para nombre, etc.
            while(rs.next())//mientras tenga algo para recorrer lo va a hacer
            {
                registros[0]=rs.getString("cedula");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("apellido");
                registros[3]=rs.getString("direccion");
                registros[4]=rs.getString("telefono");
                modelo.addRow(registros);
            }
            jtblEstudiantes.setModel(modelo);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
        
        public void GuardarEstudiante(){
            try{
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            String sql="insert into estudiante values(?,?,?,?,?)";
            PreparedStatement pst=cc.prepareStatement(sql);
            pst.setString(1, jtxtCedula.getText());
            pst.setString(2, jtxtNombre.getText());
            pst.setString(3, jtxtApellido.getText());
            pst.setString(4, jtxtDireccion.getText());
            pst.setString(5, jtxtTelefono.getText());
            
            int n=  pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "se guardo el estudiante correctamente");
                cargarEstudiantes();
            }
            
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
        public void eliminarEstudiantes() {
            
        try {
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            String sql= "delete from estudiante where cedula = "+jtxtCedula.getText()+"";
            PreparedStatement psd=cc.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(null, "se elimino correctamente");
            cargarEstudiantes();
            }
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        
        }
        }
        
        public void actualizarEstudiante(){
        
        try {
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            String sql= "update estudiante set nombre= '"+jtxtNombre.getText()+ "' where cedula = '"+jtxtCedula.getText()+"'";
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
        
        public void selecionarEstudiante(){
            jtblEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                   
                    if (jtblEstudiantes.getSelectedRow()!=-1){
                    Integer fila = jtblEstudiantes.getSelectedRow();
                    jtxtCedula.setText(jtblEstudiantes.getValueAt(fila, 0).toString());
                    jtxtNombre.setText(jtblEstudiantes.getValueAt(fila, 1).toString());
                    jtxtApellido.setText(jtblEstudiantes.getValueAt(fila, 2).toString());
                    jtxtDireccion.setText(jtblEstudiantes.getValueAt(fila, 3).toString());
                    jtxtTelefono.setText(jtblEstudiantes.getValueAt(fila, 4).toString());
                }
                    
                }
            });
        }
        
        public void est(){
        Estudiantes est= new Estudiantes(jtxtCedula.getText(),
                                        jtxtNombre.getText(),
                                        jtxtApellido.getText(),
                                        jtxtDireccion.getText(),
                                        jtxtTelefono.getText());
       EstudianteDAO.GuardarEstudiante(est);
        }
        
        public void cargarEstudiantesDAO(){
            String titulos []={"cedula", "nombre", "apellido", "dieccion", "telefono"};
            
            DefaultTableModel modelo=new DefaultTableModel(null, titulos);
            jtblEstudiantes.setModel(modelo);
            List<Estudiantes> lista= EstudianteDAO.cargarEstudiantes();
            for(Estudiantes est:lista)
            {       //como no me djea mandat todos mis parametros en el modelo, devo ponerlo en un vector, por eso pone el new object[]y lo demas le pone dentro de las {}
                modelo.addRow(new Object[]{est.getCedula(),
                              est.getNombre(),
                              est.getApellido(),
                              est.getDireccion(),
                              est.getTelefono()});
            
            }
            jtblEstudiantes.setModel(modelo);
        }
        
        public void eliminarEstudianteDAO(){
            EstudianteDAO.eliminarEstudiantes(jtxtCedula.getText());
        }
        
        public void actualizarEstudianteDAO(){
            
            Estudiantes est = new Estudiantes(jtxtCedula.getText(),
                                                jtxtNombre.getText(),
                                                jtxtApellido.getText(),
                                                jtxtTelefono.getText(),
                                                jtxtDireccion.getText());
            EstudianteDAO.actualizarEstudiante(est);
        }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jtxtDireccion = new javax.swing.JTextField();
        jtxtTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jbtnReporteXcedula = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblEstudiantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Direccion");

        jLabel5.setText("Telefono");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jtxtNombre)
                    .addComponent(jtxtApellido)
                    .addComponent(jtxtDireccion)
                    .addComponent(jtxtTelefono))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(this::jbtnGuardarActionPerformed);

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(this::jbtnEliminarActionPerformed);

        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(this::jbtnEditarActionPerformed);

        jButton1.setText("Reportes");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jbtnReporteXcedula.setText("Reporte x Cedula");
        jbtnReporteXcedula.addActionListener(this::jbtnReporteXcedulaActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnReporteXcedula)
                    .addComponent(jButton1)
                    .addComponent(jbtnEditar)
                    .addComponent(jbtnEliminar)
                    .addComponent(jbtnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jbtnGuardar)
                .addGap(18, 18, 18)
                .addComponent(jbtnEliminar)
                .addGap(18, 18, 18)
                .addComponent(jbtnEditar)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jbtnReporteXcedula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblEstudiantes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        //GuardarEstudiante();
        //aqui instancio
       // EstudianteDAO dao = new EstudianteDAO();
       est();
       cargarEstudiantesDAO();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        //eliminarEstudiantes();
        eliminarEstudianteDAO();
        cargarEstudiantesDAO();
    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        actualizarEstudianteDAO();
        cargarEstudiantes();
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            JasperReport reporte=JasperCompileManager.compileReport("c://reportes/ReporteDos.jrxml");//mando la direc donde esta ubicado mi jlrm 
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, cc);//todos los filtros de reportes y aunq no eistan debe eistir esta line sino no corre
            JasperViewer.viewReport(imprimir);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnReporteXcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReporteXcedulaActionPerformed
        try {
            conexion cn= new conexion();
            Connection cc= cn.conectar();
            Map cedulaParam= new HashMap();
            cedulaParam.put("cedulaParam", jtxtCedula.getText());
            
            JasperReport reporte=JasperCompileManager.compileReport("c://reportes/ReporteEstCedula.jrxml");//mando la direc donde esta ubicado mi jlrm 
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, cedulaParam, cc);//todos los filtros de reportes y aunq no eistan debe eistir esta line sino no corre
            JasperViewer.viewReport(imprimir, false); //solo al poner este fals, si cierro el reporte ya o se cierra mi crud
            
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jbtnReporteXcedulaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaEstudiante().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnReporteXcedula;
    private javax.swing.JTable jtblEstudiantes;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
