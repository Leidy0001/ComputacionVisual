/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deberreportes;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
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

public class NewJFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());

    public NewJFrame() {
    initComponents();
    jcbxGeneroEst.setModel(new DefaultComboBoxModel<>(new String[]{"M", "F"})); // genero
    cargarEstudiantes();
    cargarCursos();
    cargarInscripciones();
    seleccionarEstudiante();
    seleccionarCurso();
    seleccionarInscripcion();
}
    
    public Connection conectar(){
    Connection conn = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/curso", "root", "");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
        
    }
    return conn;
}
    
    public void cargarEstudiantes(){
    try {
        String titulos[] = {"Codigo", "Nombre", "Apellido", "Genero"};
        String registros[] = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        Connection cc = conectar();
        String sql = "select * from estudiantes";
        PreparedStatement pst = cc.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            registros[0] = rs.getString("estcod");
            registros[1] = rs.getString("estnom");
            registros[2] = rs.getString("estape");
            registros[3] = rs.getString("estgen");
            modelo.addRow(registros);
        }
        jtblTablaEst.setModel(modelo);
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void crearEstudiante(){
    try {
        Connection cc = conectar();
        String sql = "insert into estudiantes values(?,?,?,?)";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoEst.getText());
        pst.setString(2, jtxtNombreEst.getText());
        pst.setString(3, jtxtApellidoEst.getText());
        pst.setString(4, jcbxGeneroEst.getSelectedItem().toString());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se guardo el estudiante correctamente");
            cargarEstudiantes();
            limpiarEstudiante();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void editarEstudiante(){
    try {
        Connection cc = conectar();
        String sql = "update estudiantes set estnom=?, estape=?, estgen=? where estcod=?";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtNombreEst.getText());
        pst.setString(2, jtxtApellidoEst.getText());
        pst.setString(3, jcbxGeneroEst.getSelectedItem().toString());
        pst.setString(4, jtxtCodigoEst.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se actualizo el estudiante");
            cargarEstudiantes();
            limpiarEstudiante();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void eliminarEstudiante(){
    try {
        Connection cc = conectar();
        String sql = "delete from estudiantes where estcod=?";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoEst.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se elimino el estudiante");
            cargarEstudiantes();
            limpiarEstudiante();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void limpiarEstudiante(){
    jtxtCodigoEst.setText("");
    jtxtNombreEst.setText("");
    jtxtApellidoEst.setText("");
    jcbxGeneroEst.setSelectedIndex(0);
}

// al hacer clic en una fila, los datos pasan a los textfield
public void seleccionarEstudiante(){
    jtblTablaEst.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int fila = jtblTablaEst.getSelectedRow();
            if (fila != -1) {
                jtxtCodigoEst.setText(jtblTablaEst.getValueAt(fila, 0).toString());
                jtxtNombreEst.setText(jtblTablaEst.getValueAt(fila, 1).toString());
                jtxtApellidoEst.setText(jtblTablaEst.getValueAt(fila, 2).toString());
                jcbxGeneroEst.setSelectedItem(jtblTablaEst.getValueAt(fila, 3).toString());
            }
        }
    });
}



public void cargarCursos(){
    try {
        String titulos[] = {"Codigo", "Nombre", "Descripcion"};
        String registros[] = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        Connection cc = conectar();
        String sql = "select * from cursos";
        PreparedStatement pst = cc.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            registros[0] = rs.getString("curcod");
            registros[1] = rs.getString("curnom");
            registros[2] = rs.getString("curdes");
            modelo.addRow(registros);
        }
        jtblTablaCur.setModel(modelo);
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void crearCurso(){
    try {
        Connection cc = conectar();
        String sql = "insert into cursos values(?,?,?)";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoCur.getText());
        pst.setString(2, jtxtNombreCur.getText());
        pst.setString(3, jtxtDescripcionCur.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se guardo el curso correctamente");
            cargarCursos();
            limpiarCurso();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void editarCurso(){
    try {
        Connection cc = conectar();
        String sql = "update cursos set curnom=?, curdes=? where curcod=?";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtNombreCur.getText());
        pst.setString(2, jtxtDescripcionCur.getText());
        pst.setString(3, jtxtCodigoCur.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se actualizo el curso");
            cargarCursos();
            limpiarCurso();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void eliminarCurso(){
    try {
        Connection cc = conectar();
        String sql = "delete from cursos where curcod=?";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoCur.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se elimino el curso");
            cargarCursos();
            limpiarCurso();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void limpiarCurso(){
    jtxtCodigoCur.setText("");
    jtxtNombreCur.setText("");
    jtxtDescripcionCur.setText("");
}

public void seleccionarCurso(){
    jtblTablaCur.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int fila = jtblTablaCur.getSelectedRow();
            if (fila != -1) {
                jtxtCodigoCur.setText(jtblTablaCur.getValueAt(fila, 0).toString());
                jtxtNombreCur.setText(jtblTablaCur.getValueAt(fila, 1).toString());
                jtxtDescripcionCur.setText(jtblTablaCur.getValueAt(fila, 2).toString());
            }
        }
    });
}


public void cargarInscripciones(){
    try {
        String titulos[] = {"Cod Inscripcion", "Cod Estudiante", "Cod Curso", "Nota 1", "Nota 2"};
        String registros[] = new String[5];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        Connection cc = conectar();
        String sql = "select * from inscripcion";
        PreparedStatement pst = cc.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            registros[0] = rs.getString("inscod");
            registros[1] = rs.getString("estcodper");
            registros[2] = rs.getString("curcodper");
            registros[3] = rs.getString("insnot1");
            registros[4] = rs.getString("insnot2");
            modelo.addRow(registros);
        }
        jtblTablaIns.setModel(modelo);
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void crearInscripcion(){
    try {
        Connection cc = conectar();
        String sql = "insert into inscripcion values(?,?,?,?,?)";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoIns.getText());
        pst.setString(2, jtxtCodigoEstPert.getText());
        pst.setString(3, jtxtCodigoCurPert.getText());
        pst.setString(4, jtxtNota1.getText());
        pst.setString(5, jtxtNota2.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se guardo la inscripcion correctamente");
            cargarInscripciones();
            limpiarInscripcion();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void editarInscripcion(){
    try {
        Connection cc = conectar();
        String sql = "update inscripcion set estcodper=?, curcodper=?, insnot1=?, insnot2=? where inscod=?";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoEstPert.getText());
        pst.setString(2, jtxtCodigoCurPert.getText());
        pst.setString(3, jtxtNota1.getText());
        pst.setString(4, jtxtNota2.getText());
        pst.setString(5, jtxtCodigoIns.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se actualizo la inscripcion");
            cargarInscripciones();
            limpiarInscripcion();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void eliminarInscripcion(){
    try {
        Connection cc = conectar();
        String sql = "delete from inscripcion where inscod=?";
        PreparedStatement pst = cc.prepareStatement(sql);
        pst.setString(1, jtxtCodigoIns.getText());
        int n = pst.executeUpdate();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "se elimino la inscripcion");
            cargarInscripciones();
            limpiarInscripcion();
        }
        cc.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

public void limpiarInscripcion(){
    jtxtCodigoIns.setText("");
    jtxtCodigoEstPert.setText("");
    jtxtCodigoCurPert.setText("");
    jtxtNota1.setText("");
    jtxtNota2.setText("");
}

public void seleccionarInscripcion(){
    jtblTablaIns.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int fila = jtblTablaIns.getSelectedRow();
            if (fila != -1) {
                jtxtCodigoIns.setText(jtblTablaIns.getValueAt(fila, 0).toString());
                jtxtCodigoEstPert.setText(jtblTablaIns.getValueAt(fila, 1).toString());
                jtxtCodigoCurPert.setText(jtblTablaIns.getValueAt(fila, 2).toString());
                jtxtNota1.setText(jtblTablaIns.getValueAt(fila, 3).toString());
                jtxtNota2.setText(jtblTablaIns.getValueAt(fila, 4).toString());
            }
        }
    });
}

public void reporteCurso(){
    try {
        Connection cc = conectar();
        Map parametros = new HashMap();
        parametros.put("curcodParam", jtxtCodigoCur.getText());

        JasperReport reporte = JasperCompileManager.compileReport("c://reportes/ReporteCurso.jrxml");
        JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cc);
        JasperViewer.viewReport(imprimir, false); // false = al cerrar el reporte no se cierra el frame
    } catch (JRException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtCodigoEst = new javax.swing.JTextField();
        jtxtNombreEst = new javax.swing.JTextField();
        jtxtApellidoEst = new javax.swing.JTextField();
        jbtnCrearEst = new javax.swing.JButton();
        jbtnEditarEst = new javax.swing.JButton();
        jbtnEliminarEst = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTablaEst = new javax.swing.JTable();
        jcbxGeneroEst = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxtDescripcionCur = new javax.swing.JTextField();
        jtxtCodigoCur = new javax.swing.JTextField();
        jtxtNombreCur = new javax.swing.JTextField();
        jbtnCrearCur = new javax.swing.JButton();
        jbtnEditarCur = new javax.swing.JButton();
        jbtnEliminarCur = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblTablaCur = new javax.swing.JTable();
        jbtnReporteCur = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtxtCodigoIns = new javax.swing.JTextField();
        jtxtCodigoCurPert = new javax.swing.JTextField();
        jtxtCodigoEstPert = new javax.swing.JTextField();
        jtxtNota2 = new javax.swing.JTextField();
        jtxtNota1 = new javax.swing.JTextField();
        jbtnCrearIns = new javax.swing.JButton();
        jbtnEditarIns = new javax.swing.JButton();
        jbtnEliminarIns = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblTablaIns = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Genero");

        jtxtNombreEst.addActionListener(this::jtxtNombreEstActionPerformed);

        jbtnCrearEst.setText("Crear");
        jbtnCrearEst.addActionListener(this::jbtnCrearEstActionPerformed);

        jbtnEditarEst.setText("Editar");
        jbtnEditarEst.addActionListener(this::jbtnEditarEstActionPerformed);

        jbtnEliminarEst.setText("Eliminar");
        jbtnEliminarEst.addActionListener(this::jbtnEliminarEstActionPerformed);

        jtblTablaEst.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtblTablaEst);

        jcbxGeneroEst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtCodigoEst, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtApellidoEst, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtNombreEst, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnCrearEst)
                                    .addComponent(jbtnEditarEst)
                                    .addComponent(jbtnEliminarEst))
                                .addGap(75, 75, 75))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jcbxGeneroEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtCodigoEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCrearEst))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNombreEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditarEst))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtApellidoEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEliminarEst))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbxGeneroEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estudiantes", jPanel1);

        jLabel5.setText("Codigo");

        jLabel6.setText("Nombre");

        jLabel7.setText("Descripcion");

        jbtnCrearCur.setText("Crear");
        jbtnCrearCur.addActionListener(this::jbtnCrearCurActionPerformed);

        jbtnEditarCur.setText("Editar");
        jbtnEditarCur.addActionListener(this::jbtnEditarCurActionPerformed);

        jbtnEliminarCur.setText("Eliminar");
        jbtnEliminarCur.addActionListener(this::jbtnEliminarCurActionPerformed);

        jtblTablaCur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtblTablaCur);

        jbtnReporteCur.setText("Generar Reporte");
        jbtnReporteCur.addActionListener(this::jbtnReporteCurActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtCodigoCur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtNombreCur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtDescripcionCur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnEditarCur)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jbtnEliminarCur)
                                    .addGap(106, 106, 106))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jbtnReporteCur)
                                    .addContainerGap()))
                            .addComponent(jbtnCrearCur)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtxtCodigoCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbtnCrearCur)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtNombreCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtxtDescripcionCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnEditarCur)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEliminarCur)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnReporteCur)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cursos", jPanel2);

        jLabel8.setText("Codigo Inscripcion");

        jLabel9.setText("Codigo Estudiantes");

        jLabel10.setText("Codigo Curso");

        jLabel11.setText("Nota 1");

        jLabel12.setText("Nota 2");

        jtxtCodigoCurPert.addActionListener(this::jtxtCodigoCurPertActionPerformed);

        jtxtNota1.addActionListener(this::jtxtNota1ActionPerformed);

        jbtnCrearIns.setText("Crear");
        jbtnCrearIns.addActionListener(this::jbtnCrearInsActionPerformed);

        jbtnEditarIns.setText("Editar");
        jbtnEditarIns.addActionListener(this::jbtnEditarInsActionPerformed);

        jbtnEliminarIns.setText("Elimiar");
        jbtnEliminarIns.addActionListener(this::jbtnEliminarInsActionPerformed);

        jtblTablaIns.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jtblTablaIns);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jtxtCodigoCurPert, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(jbtnEliminarIns))
                            .addComponent(jtxtNota1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jtxtCodigoIns, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnCrearIns))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jtxtCodigoEstPert, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(79, 79, 79)
                                    .addComponent(jbtnEditarIns))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtxtCodigoIns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCrearIns))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtxtCodigoEstPert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditarIns))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtxtCodigoCurPert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEliminarIns))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtxtNota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtxtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inscripcion", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreEstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreEstActionPerformed

    private void jbtnEditarInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarInsActionPerformed
        editarInscripcion();
    }//GEN-LAST:event_jbtnEditarInsActionPerformed

    private void jtxtCodigoCurPertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCodigoCurPertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCodigoCurPertActionPerformed

    private void jtxtNota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNota1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNota1ActionPerformed

    private void jbtnCrearCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearCurActionPerformed
        crearCurso();
    }//GEN-LAST:event_jbtnCrearCurActionPerformed

    private void jbtnCrearEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearEstActionPerformed
        crearEstudiante();
    }//GEN-LAST:event_jbtnCrearEstActionPerformed

    private void jbtnEditarEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarEstActionPerformed
        editarEstudiante();
    }//GEN-LAST:event_jbtnEditarEstActionPerformed

    private void jbtnEliminarEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarEstActionPerformed
        eliminarEstudiante();
    }//GEN-LAST:event_jbtnEliminarEstActionPerformed

    private void jbtnEditarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarCurActionPerformed
        editarCurso();
    }//GEN-LAST:event_jbtnEditarCurActionPerformed

    private void jbtnEliminarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarCurActionPerformed
        eliminarCurso(); 
    }//GEN-LAST:event_jbtnEliminarCurActionPerformed

    private void jbtnReporteCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReporteCurActionPerformed
        reporteCurso();
    
    }//GEN-LAST:event_jbtnReporteCurActionPerformed

    private void jbtnCrearInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearInsActionPerformed
        crearInscripcion();
    }//GEN-LAST:event_jbtnCrearInsActionPerformed

    private void jbtnEliminarInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarInsActionPerformed
        eliminarInscripcion();
    }//GEN-LAST:event_jbtnEliminarInsActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnCrearCur;
    private javax.swing.JButton jbtnCrearEst;
    private javax.swing.JButton jbtnCrearIns;
    private javax.swing.JButton jbtnEditarCur;
    private javax.swing.JButton jbtnEditarEst;
    private javax.swing.JButton jbtnEditarIns;
    private javax.swing.JButton jbtnEliminarCur;
    private javax.swing.JButton jbtnEliminarEst;
    private javax.swing.JButton jbtnEliminarIns;
    private javax.swing.JButton jbtnReporteCur;
    private javax.swing.JComboBox<String> jcbxGeneroEst;
    private javax.swing.JTable jtblTablaCur;
    private javax.swing.JTable jtblTablaEst;
    private javax.swing.JTable jtblTablaIns;
    private javax.swing.JTextField jtxtApellidoEst;
    private javax.swing.JTextField jtxtCodigoCur;
    private javax.swing.JTextField jtxtCodigoCurPert;
    private javax.swing.JTextField jtxtCodigoEst;
    private javax.swing.JTextField jtxtCodigoEstPert;
    private javax.swing.JTextField jtxtCodigoIns;
    private javax.swing.JTextField jtxtDescripcionCur;
    private javax.swing.JTextField jtxtNombreCur;
    private javax.swing.JTextField jtxtNombreEst;
    private javax.swing.JTextField jtxtNota1;
    private javax.swing.JTextField jtxtNota2;
    // End of variables declaration//GEN-END:variables
}
