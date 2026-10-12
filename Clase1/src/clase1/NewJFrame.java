/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clase1;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author micha
 */
public class NewJFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());
    private int filaEditar = -1;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        CargarEstadoCivil();
        CargarTablaPersonas();
        cargarDatosEditar();
        InformacionInicio();
        BotonesInicio();
        eventosTexto();
    }

    public void CargarEstadoCivil() {
        String vectorEstadoCivil[] = {"Seleccione", "Soltero", "Casado", "Divorciado", "Viudo"};
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(vectorEstadoCivil);
        jcbxEstadoCivil.setModel(modelo);
    }

    public void CargarTablaPersonas() {
        String vectorTitulos[] = {"Cedula", "Nombre", "Apellido", "Edad"};
        DefaultTableModel modelo = new DefaultTableModel(null, vectorTitulos);
        jtblPersonas.setModel(modelo);
    }

    public void CargarDatosTablaPersona() {
        if (jtxtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ingrese la cedula");
            jtxtCedula.requestFocus();
            return;
        }

        if (jtxtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ingrese el nombre");
            jtxtNombre.requestFocus();
            return;
        }

        if (jtxtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ingrese el apellido");
            jtxtApellido.requestFocus();
            return;
        }

        Integer edad = (Integer) jspnEdad.getValue();
        if (edad <= 5) {
            JOptionPane.showMessageDialog(null, "ingrese una edad valida");
            jspnEdad.requestFocus();
            return;
        }

        if (jcbxEstadoCivil.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado civil");
            jcbxEstadoCivil.requestFocus();
            return;
        }

        if (!jrbtnFemenino.isSelected() && !jrbtnMasculino.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione un genero");
            return;
        }

        String dato[] = new String[4];

        dato[0] = jtxtCedula.getText();
        dato[1] = jtxtNombre.getText();
        dato[2] = jtxtApellido.getText();
        dato[3] = jspnEdad.getValue().toString();

        DefaultTableModel modeloDatos
                = (DefaultTableModel) jtblPersonas.getModel();

        if (filaEditar == -1) {

            modeloDatos.addRow(dato);

        } else {

            modeloDatos.setValueAt(dato[0], filaEditar, 0);
            modeloDatos.setValueAt(dato[1], filaEditar, 1);
            modeloDatos.setValueAt(dato[2], filaEditar, 2);
            modeloDatos.setValueAt(dato[3], filaEditar, 3);

            filaEditar = -1;
        }

        jtblPersonas.setModel(modeloDatos);
    }

    public void eliminarPersonaTabla() {
        if (jtblPersonas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        } else {
            int fila = jtblPersonas.getSelectedRow();
            DefaultTableModel modeloEliminar = (DefaultTableModel) jtblPersonas.getModel();
            modeloEliminar.removeRow(fila);
            actualizarLista();

        }
    }

    public void cargarDatosEditar() {

        jtblPersonas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtblPersonas.getSelectedRow() != -1) {
                    int fila = jtblPersonas.getSelectedRow();
                    filaEditar = fila;
                    jtxtCedula.setText(jtblPersonas.getValueAt(fila, 0).toString());
                    jtxtNombre.setText(jtblPersonas.getValueAt(fila, 1).toString());
                    jtxtApellido.setText(jtblPersonas.getValueAt(fila, 2).toString());

                    jbtnEditar.setEnabled(true);
                    jbtnEliminar.setEnabled(true);
                }
            }

        });
    }

    private void edicion() {

        if (filaEditar == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione una fila para editar"
            );

            return;
        }

        InformacionNuevo();

        jbtnGuardar.setEnabled(true);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
    }

    public void cojerDatos() {

        String dato = jtxtCedula.getText() +" "+ "\n"
                + jtxtNombre.getText() +" "+ "\n"
                + jtxtApellido.getText() +" "+ "\n"
                + jspnEdad.getValue() +" "+ "\n"
                + jcbxEstadoCivil.getSelectedItem()+" " + "\n"
                + jsldNivel.getValue()+" ";

        String genero = (jrbtnFemenino.isSelected() ? "\n femenino " : "")
                + (jrbtnMasculino.isSelected() ? "\n masculino " : "")
                + ((!jrbtnMasculino.isSelected() && !jrbtnFemenino.isSelected()) ? "\n no selecciono nada " : "");

        String hobbie = (jchkFutbol.isSelected() ? "Futbol " : "")
                + (jchkBaile.isSelected() ? "Baile " : "")
                + (jchkMusica.isSelected() ? "Musica " : "");

        hobbie = hobbie.isEmpty() ? "Ninguno" : hobbie.trim();

        dato = dato +" "+ genero +" "+ "\n" + hobbie;
        jtxtaInformacion.setText(dato);
        javax.swing.DefaultListModel<String> modelo;
        if (jlsDatos.getModel() instanceof javax.swing.DefaultListModel) {
        modelo = (javax.swing.DefaultListModel<String>) jlsDatos.getModel();
    } else {
        modelo = new javax.swing.DefaultListModel<>();
    }

    modelo.addElement(dato);

    jlsDatos.setModel(modelo);

    }

    public void BotonesInicio() {
        jbtnNuevo.setEnabled(true);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
        jspnEdad.setEnabled(false);
        jbtnGuardar.setEnabled(false);
    }

    public void InformacionInicio() {
        jtxtCedula.setEnabled(false);
        jtxtApellido.setEnabled(false);
        jtxtNombre.setEnabled(false);
        jcbxEstadoCivil.setEnabled(false);
        jrbtnFemenino.setEnabled(false);
        jrbtnMasculino.setEnabled(false);
        jchkBaile.setEnabled(false);
        jchkFutbol.setEnabled(false);
        jchkMusica.setEnabled(false);
        jsldNivel.setEnabled(false);
        jtbtnCondiciones.setEnabled(false);
        jbtnCancelar.setEnabled(false);

    }

    public void InformacionNuevo() {
        jtxtCedula.setEnabled(true);
        jtxtNombre.setEnabled(true);
        jtxtApellido.setEnabled(true);
        jspnEdad.setEnabled(true);
        jcbxEstadoCivil.setEnabled(true);
        jrbtnFemenino.setEnabled(true);
        jrbtnMasculino.setEnabled(true);
        jchkBaile.setEnabled(true);
        jchkFutbol.setEnabled(true);
        jchkMusica.setEnabled(true);
        jsldNivel.setEnabled(true);
        jtbtnCondiciones.setEnabled(true);
        jbtnCancelar.setEnabled(true);
    }

    public void condiciones() {
        if (jtbtnCondiciones.isSelected()) {
            jbtnGuardar.setEnabled(true);
        } else {
            jbtnGuardar.setEnabled(false);
        }
    }

    public void actualizarBarra() {
        int completados = 0;
        int total = 8;

        if (!jtxtCedula.getText().isEmpty()) {
            completados++;
        }

        if (!jtxtNombre.getText().isEmpty()) {
            completados++;
        }

        if (!jtxtApellido.getText().isEmpty()) {
            completados++;
        }

        if ((Integer) jspnEdad.getValue() > 0) {
            completados++;
        }

        if (jcbxEstadoCivil.getSelectedIndex() != 0) {
            completados++;
        }

        if (jrbtnFemenino.isSelected() || jrbtnMasculino.isSelected()) {
            completados++;
        }

        if (jchkBaile.isSelected() || jchkFutbol.isSelected()
                || jchkMusica.isSelected()) {
            completados++;
        }

        if (jsldNivel.getValue() > 0) {
            completados++;
        }

        int progreso = (completados * 100) / total;

        jpgrDatos.setValue(progreso);
    }

    public void eventosTexto() {

        DocumentListener listener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarBarra();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarBarra();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarBarra();
            }
        };

        jtxtCedula.getDocument().addDocumentListener(listener);
        jtxtNombre.getDocument().addDocumentListener(listener);
        jtxtApellido.getDocument().addDocumentListener(listener);
    }

    public void limpiarCampos() {

        jtxtCedula.setText("");
        jtxtNombre.setText("");
        jtxtApellido.setText("");

        jspnEdad.setValue(0);

        jcbxEstadoCivil.setSelectedIndex(0);

        jgbtnGenero.clearSelection();

        jchkFutbol.setSelected(false);
        jchkMusica.setSelected(false);
        jchkBaile.setSelected(false);

        jsldNivel.setValue(0);

        jtbtnCondiciones.setSelected(false);

        jtxtaInformacion.setText("");

        jpgrDatos.setValue(0);

        jtblPersonas.clearSelection();
    }

    public void actualizarLista() {

        javax.swing.DefaultListModel<String> modelo
                = new javax.swing.DefaultListModel<>();

        DefaultTableModel tabla
                = (DefaultTableModel) jtblPersonas.getModel();

        for (int i = 0; i < tabla.getRowCount(); i++) {

            String dato
                    = tabla.getValueAt(i, 0).toString()
                    + " - "
                    + tabla.getValueAt(i, 1).toString()
                    + " "
                    + tabla.getValueAt(i, 2).toString()
                    + " "
                    + tabla.getValueAt(i, 3).toString();

            modelo.addElement(dato);
        }

        jlsDatos.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jgbtnGenero = new javax.swing.ButtonGroup();
        jpnlDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jspnEdad = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jcbxEstadoCivil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jrbtnMasculino = new javax.swing.JRadioButton();
        jrbtnFemenino = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jchkFutbol = new javax.swing.JCheckBox();
        jchkMusica = new javax.swing.JCheckBox();
        jchkBaile = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jsldNivel = new javax.swing.JSlider();
        jtbtnCondiciones = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jpgrDatos = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtaInformacion = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlsDatos = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPersonas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnlDatos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Cedula");

        jtxtCedula.addActionListener(this::jtxtCedulaActionPerformed);

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jtxtNombre.addActionListener(this::jtxtNombreActionPerformed);

        jLabel4.setText("Edad");

        jspnEdad.addChangeListener(this::jspnEdadStateChanged);

        jLabel5.setText("Estado Civil");

        jcbxEstadoCivil.addActionListener(this::jcbxEstadoCivilActionPerformed);

        jLabel6.setText("Género");

        jgbtnGenero.add(jrbtnMasculino);
        jrbtnMasculino.setText("Masculino");
        jrbtnMasculino.addChangeListener(this::jrbtnMasculinoStateChanged);
        jrbtnMasculino.addActionListener(this::jrbtnMasculinoActionPerformed);

        jgbtnGenero.add(jrbtnFemenino);
        jrbtnFemenino.setText("Femenino");
        jrbtnFemenino.addChangeListener(this::jrbtnFemeninoStateChanged);

        jLabel7.setText("Hobbies");

        jchkFutbol.setText("Futbol");
        jchkFutbol.addActionListener(this::jchkFutbolActionPerformed);

        jchkMusica.setText("Musica");
        jchkMusica.addActionListener(this::jchkMusicaActionPerformed);

        jchkBaile.setText("Baile");

        jLabel8.setText("nivel de satisfaccion");

        jsldNivel.setMajorTickSpacing(1);
        jsldNivel.setMaximum(10);
        jsldNivel.setPaintLabels(true);
        jsldNivel.setPaintTicks(true);
        jsldNivel.setPaintTrack(false);
        jsldNivel.setValue(0);
        jsldNivel.addChangeListener(this::jsldNivelStateChanged);

        jtbtnCondiciones.setText("Acepto las condiciones");
        jtbtnCondiciones.addActionListener(this::jtbtnCondicionesActionPerformed);

        jLabel9.setText("Aceptar condiciones");

        javax.swing.GroupLayout jpnlDatosLayout = new javax.swing.GroupLayout(jpnlDatos);
        jpnlDatos.setLayout(jpnlDatosLayout);
        jpnlDatosLayout.setHorizontalGroup(
            jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDatosLayout.createSequentialGroup()
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbtnCondiciones))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlDatosLayout.createSequentialGroup()
                                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtCedula)
                                    .addComponent(jtxtNombre)
                                    .addComponent(jtxtApellido)
                                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                                        .addComponent(jspnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jpnlDatosLayout.createSequentialGroup()
                                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jchkFutbol)
                                            .addComponent(jchkMusica)
                                            .addComponent(jchkBaile)
                                            .addGroup(jpnlDatosLayout.createSequentialGroup()
                                                .addComponent(jrbtnMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jrbtnFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addGap(140, 140, 140))
            .addGroup(jpnlDatosLayout.createSequentialGroup()
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jsldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpgrDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlDatosLayout.setVerticalGroup(
            jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jspnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrbtnMasculino)
                        .addComponent(jrbtnFemenino))
                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jchkFutbol))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchkMusica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchkBaile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtbtnCondiciones))
                .addGap(38, 38, 38)
                .addComponent(jpgrDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(this::jbtnNuevoActionPerformed);

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(this::jbtnGuardarActionPerformed);

        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(this::jbtnEditarActionPerformed);

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(this::jbtnEliminarActionPerformed);

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(this::jbtnCancelarActionPerformed);

        jtxtaInformacion.setColumns(20);
        jtxtaInformacion.setRows(5);
        jScrollPane2.setViewportView(jtxtaInformacion);

        jScrollPane3.setViewportView(jlsDatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jbtnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGuardar)
                .addGap(12, 12, 12)
                .addComponent(jbtnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnCancelar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtblPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblPersonas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        actualizarBarra();
    }//GEN-LAST:event_jtxtCedulaActionPerformed

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        actualizarBarra();    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jcbxEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxEstadoCivilActionPerformed
        actualizarBarra();    }//GEN-LAST:event_jcbxEstadoCivilActionPerformed

    private void jrbtnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnMasculinoActionPerformed
        actualizarBarra();    }//GEN-LAST:event_jrbtnMasculinoActionPerformed

    private void jchkFutbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkFutbolActionPerformed
        actualizarBarra();    }//GEN-LAST:event_jchkFutbolActionPerformed

    private void jchkMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkMusicaActionPerformed
        actualizarBarra();    }//GEN-LAST:event_jchkMusicaActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        cojerDatos();
        CargarDatosTablaPersona();

    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        eliminarPersonaTabla();
    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed

        edicion();
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        InformacionNuevo();
        limpiarCampos();

    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jtbtnCondicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtnCondicionesActionPerformed
        condiciones();
        actualizarBarra();
    }//GEN-LAST:event_jtbtnCondicionesActionPerformed

    private void jsldNivelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsldNivelStateChanged
        actualizarBarra();
    }//GEN-LAST:event_jsldNivelStateChanged

    private void jspnEdadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspnEdadStateChanged
        Integer edad = (Integer) jspnEdad.getValue();
        if (edad >= 5) {
            actualizarBarra();
        }
    }//GEN-LAST:event_jspnEdadStateChanged

    private void jrbtnFemeninoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jrbtnFemeninoStateChanged
        actualizarBarra();    }//GEN-LAST:event_jrbtnFemeninoStateChanged

    private void jrbtnMasculinoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jrbtnMasculinoStateChanged
        actualizarBarra();
    }//GEN-LAST:event_jrbtnMasculinoStateChanged

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        limpiarCampos();
        filaEditar = -1;
        InformacionInicio();
        BotonesInicio();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JComboBox<String> jcbxEstadoCivil;
    private javax.swing.JCheckBox jchkBaile;
    private javax.swing.JCheckBox jchkFutbol;
    private javax.swing.JCheckBox jchkMusica;
    private javax.swing.ButtonGroup jgbtnGenero;
    private javax.swing.JList<String> jlsDatos;
    private javax.swing.JProgressBar jpgrDatos;
    private javax.swing.JPanel jpnlDatos;
    private javax.swing.JRadioButton jrbtnFemenino;
    private javax.swing.JRadioButton jrbtnMasculino;
    private javax.swing.JSlider jsldNivel;
    private javax.swing.JSpinner jspnEdad;
    private javax.swing.JTable jtblPersonas;
    private javax.swing.JToggleButton jtbtnCondiciones;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextArea jtxtaInformacion;
    // End of variables declaration//GEN-END:variables
}
