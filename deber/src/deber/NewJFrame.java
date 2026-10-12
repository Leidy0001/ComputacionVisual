/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deber;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class NewJFrame extends javax.swing.JFrame {
    
    private boolean modoEdicion = false;
    private int filaEditando = -1;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());

    public NewJFrame() {
        initComponents();
        CargarEstadoCivil();
        CargarTablaPersonas();
        CargarDatosEditar();
        BotonesInicio();
        InformacionInicio();
    }

    public void CargarTablaPersonas() {
        String vectorTitulos[] = {
            "Cedula", "Nombre", "Apellido", "Edad", "Estado Civil", "Genero", "Hobbies", "Nivel Satisfaccion"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, vectorTitulos);
        jtblPersonas.setModel(modelo);
    }

    public void CargarEstadoCivil() {
        jcbxEstadoCivil.removeAllItems();
        jcbxEstadoCivil.addItem("Soltero/a");
        jcbxEstadoCivil.addItem("Casado/a");
        jcbxEstadoCivil.addItem("Divorciado/a");
        jcbxEstadoCivil.addItem("Viudo/a");
        jcbxEstadoCivil.addItem("Unión libre");
    }

    public void CargarDatosTablaPersona() {
        String cedula = jtxtCedula.getText().trim();
        String nombre = jtxtNombre.getText().trim();
        String apellido = jtxtApellido.getText().trim();

        if (cedula.isEmpty()) { JOptionPane.showMessageDialog(null, "Ingrese la cédula"); jtxtCedula.requestFocus(); return; }
        if (nombre.isEmpty()) { JOptionPane.showMessageDialog(null, "Ingrese el nombre"); jtxtNombre.requestFocus(); return; }
        if (apellido.isEmpty()) { JOptionPane.showMessageDialog(null, "Ingrese el apellido"); jtxtApellido.requestFocus(); return; }

        String genero = Persona.evaluarGenero(jrbtnMasculino.isSelected(), jrbtnFemenino.isSelected());
        String hobbies = Persona.evaluarHobbies(jchkFutbol.isSelected(), jchkMusica.isSelected(), jchkBaile.isSelected());

        
        
        Persona nuevaPersona = new Persona(
                cedula, nombre, apellido, (int) jspnEdad.getValue(), 
                jcbxEstadoCivil.getSelectedItem().toString(), genero, hobbies, jsldNivel.getValue()
        );

        DefaultTableModel modeloDatos = (DefaultTableModel) jtblPersonas.getModel();
        nuevaPersona.guardar(modeloDatos);
    }
    
    public void EliminarPersonaTabla() {
        int fila = jtblPersonas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Primero selecciona la persona que deseas eliminar");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta persona?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            DefaultTableModel modelo = (DefaultTableModel) jtblPersonas.getModel();
            Persona.eliminar(modelo, fila);
            
            JOptionPane.showMessageDialog(null, "Persona eliminada correctamente");

            BloquearCampos();
            jbtnNuevo.setEnabled(true);
            jbtnGuardar.setEnabled(false);
            jbtnEditar.setEnabled(false);
            jbtnEliminar.setEnabled(false);
            jbtnCancelar.setEnabled(false);
        }
    }
    
    public void InformacionInicio() {
        jtxtCedula.setEnabled(false);
        jtxtNombre.setEnabled(false);
        jtxtApellido.setEnabled(false);
        jspnEdad.setEnabled(false);
        jcbxEstadoCivil.setEnabled(false);
        jrbtnMasculino.setEnabled(false);
        jrbtnFemenino.setEnabled(false);
        jchkFutbol.setEnabled(false);
        jchkMusica.setEnabled(false);
        jchkBaile.setEnabled(false);
        jsldNivel.setEnabled(false);
        jtbtnAceptar.setEnabled(false);
    }          
    
    public void InformacionNuevo() {
        jtxtCedula.setEnabled(true);
        jtxtNombre.setEnabled(true);
        jtxtApellido.setEnabled(true);
        jspnEdad.setEnabled(true);
        jcbxEstadoCivil.setEnabled(true);
        jrbtnMasculino.setEnabled(true);
        jrbtnFemenino.setEnabled(true);
        jchkFutbol.setEnabled(true);
        jchkMusica.setEnabled(true);
        jchkBaile.setEnabled(true);
        jsldNivel.setEnabled(true);
        jtbtnAceptar.setEnabled(true);

        jtxtCedula.setText("");
        jtxtNombre.setText("");
        jtxtApellido.setText("");
        jspnEdad.setValue(18);
        jrbtnMasculino.setSelected(false);
        jrbtnFemenino.setSelected(false);
        jchkFutbol.setSelected(false);
        jchkMusica.setSelected(false);
        jchkBaile.setSelected(false);
        jsldNivel.setValue(0);
        
        jtbtnAceptar.setSelected(false); 
        jtxtCedula.requestFocus();
    }
    
    public void BloquearCampos() {
        jtxtCedula.setEnabled(false);
        jtxtNombre.setEnabled(false);
        jtxtApellido.setEnabled(false);
        jspnEdad.setEnabled(false);
        jcbxEstadoCivil.setEnabled(false);
        jrbtnMasculino.setEnabled(false);
        jrbtnFemenino.setEnabled(false);
        jchkFutbol.setEnabled(false);
        jchkMusica.setEnabled(false);
        jchkBaile.setEnabled(false);
        jsldNivel.setEnabled(false);
        jtbtnAceptar.setEnabled(false);
    }
            
    public void cojerDatos() {
        String genero = Persona.evaluarGenero(jrbtnMasculino.isSelected(), jrbtnFemenino.isSelected());
        String hobbies = Persona.evaluarHobbies(jchkFutbol.isSelected(), jchkMusica.isSelected(), jchkBaile.isSelected());

        Persona persona = new Persona(
                jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(),
                (int) jspnEdad.getValue(), jcbxEstadoCivil.getSelectedItem().toString(),
                genero, hobbies, jsldNivel.getValue()
        );

        String dato = persona.getCedula() + "\n" + persona.getNombre() + "\n" + persona.getApellido() + "\n"
                + persona.getEdad() + "\n" + persona.getEstadoCivil() + "\n" + persona.getNivelSatisfaccion() + "\n"
                + persona.getGenero() + "\n" + persona.getHobbies();

        jtxtaInformacion.setText(dato);
    }
    
    public void BotonesInicio() {
        jbtnNuevo.setEnabled(true);
        jbtnGuardar.setEnabled(false);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
        jbtnCancelar.setEnabled(false);
    }
    
    public void CargarDatosEditar() {
        jtblPersonas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && jtblPersonas.getSelectedRow() != -1 && !modoEdicion) {

                    int fila = jtblPersonas.getSelectedRow();
                    jtxtCedula.setText(jtblPersonas.getValueAt(fila, 0).toString());
                    jtxtNombre.setText(jtblPersonas.getValueAt(fila, 1).toString());
                    jtxtApellido.setText(jtblPersonas.getValueAt(fila, 2).toString());
                    jspnEdad.setValue(jtblPersonas.getValueAt(fila, 3));
                    jcbxEstadoCivil.setSelectedItem(jtblPersonas.getValueAt(fila, 4));

                    String genero = jtblPersonas.getValueAt(fila, 5).toString();
                    if (genero.equals("Masculino")) {
                        jrbtnMasculino.setSelected(true);
                    } else if (genero.equals("Femenino")) {
                        jrbtnFemenino.setSelected(true);
                    }

                    String hobbies = jtblPersonas.getValueAt(fila, 6).toString();
                    jchkFutbol.setSelected(hobbies.contains("Futbol"));
                    jchkMusica.setSelected(hobbies.contains("Musica"));
                    jchkBaile.setSelected(hobbies.contains("Baile"));

                    jsldNivel.setValue(Integer.parseInt(jtblPersonas.getValueAt(fila, 7).toString()));

                    jbtnEditar.setEnabled(true);
                    jbtnEliminar.setEnabled(true);
                }
            }
        });
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
        jtbtnAceptar = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtaInformacion = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
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

        jLabel5.setText("Estado Civil");

        jcbxEstadoCivil.addActionListener(this::jcbxEstadoCivilActionPerformed);

        jLabel6.setText("Género");

        jgbtnGenero.add(jrbtnMasculino);
        jrbtnMasculino.setText("Masculino");
        jrbtnMasculino.addActionListener(this::jrbtnMasculinoActionPerformed);

        jgbtnGenero.add(jrbtnFemenino);
        jrbtnFemenino.setText("Femenino");

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

        jtbtnAceptar.setText("Aceptar");
        jtbtnAceptar.addActionListener(this::jtbtnAceptarActionPerformed);

        jLabel9.setText("Aceptar Condiciones");

        javax.swing.GroupLayout jpnlDatosLayout = new javax.swing.GroupLayout(jpnlDatos);
        jpnlDatos.setLayout(jpnlDatosLayout);
        jpnlDatosLayout.setHorizontalGroup(
            jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatosLayout.createSequentialGroup()
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jsldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDatosLayout.createSequentialGroup()
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                .addComponent(jrbtnFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnlDatosLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtbtnAceptar)))
                .addGap(140, 140, 140))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jpnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtbtnAceptar)
                    .addComponent(jLabel9))
                .addGap(22, 22, 22))
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

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCedulaActionPerformed

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jcbxEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbxEstadoCivilActionPerformed

    private void jrbtnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbtnMasculinoActionPerformed

    private void jchkFutbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkFutbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchkFutbolActionPerformed

    private void jchkMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkMusicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchkMusicaActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
                                                   
        if (modoEdicion) {
            if (filaEditando == -1) return;

            String genero = Persona.evaluarGenero(jrbtnMasculino.isSelected(), jrbtnFemenino.isSelected());
            String hobbies = Persona.evaluarHobbies(jchkFutbol.isSelected(), jchkMusica.isSelected(), jchkBaile.isSelected());

            Persona personaEditada = new Persona(
                    jtxtCedula.getText().trim(), jtxtNombre.getText().trim(), jtxtApellido.getText().trim(),
                    (int) jspnEdad.getValue(), jcbxEstadoCivil.getSelectedItem().toString(), genero, hobbies, jsldNivel.getValue()
            );

            DefaultTableModel modelo = (DefaultTableModel) jtblPersonas.getModel();
            personaEditada.editar(modelo, filaEditando);

            JOptionPane.showMessageDialog(null, "Persona modificada correctamente");
        } else {
            CargarDatosTablaPersona();
            JOptionPane.showMessageDialog(null, "Persona guardada correctamente");
        }

        modoEdicion = false;
        filaEditando = -1;
        BloquearCampos();
        jbtnNuevo.setEnabled(true);
        jbtnGuardar.setEnabled(false);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
        jbtnCancelar.setEnabled(false);
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        EliminarPersonaTabla();
    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed

       modoEdicion = false;
        filaEditando = -1;
        InformacionNuevo();

        jbtnNuevo.setEnabled(false);
        jbtnGuardar.setEnabled(false); 
        jbtnCancelar.setEnabled(true);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jtbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtnAceptarActionPerformed
          if (jtbtnAceptar.isSelected()) {
            jbtnGuardar.setEnabled(true);
        } else {
            jbtnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_jtbtnAceptarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        int fila = jtblPersonas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Primero selecciona una persona de la tabla");
            return;
        }

        filaEditando = fila;
        modoEdicion = true;

        jtxtCedula.setEnabled(true);
        jtxtNombre.setEnabled(true);
        jtxtApellido.setEnabled(true);
        jspnEdad.setEnabled(true);
        jcbxEstadoCivil.setEnabled(true);
        jrbtnMasculino.setEnabled(true);
        jrbtnFemenino.setEnabled(true);
        jchkFutbol.setEnabled(true);
        jchkMusica.setEnabled(true);
        jchkBaile.setEnabled(true);
        jsldNivel.setEnabled(true);
        jtbtnAceptar.setEnabled(true);
        
        jtbtnAceptar.setSelected(false);
        jbtnGuardar.setEnabled(false); 
        
        jbtnCancelar.setEnabled(true);
        jbtnNuevo.setEnabled(false);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
   modoEdicion = false;
        filaEditando = -1;
        BloquearCampos();

        jbtnNuevo.setEnabled(true);
        jbtnGuardar.setEnabled(false);
        jbtnEditar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
        jbtnCancelar.setEnabled(false);

        jtblPersonas.clearSelection();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

     public static void main(String args[]) {
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
    private javax.swing.JList<String> jList1;
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
    private javax.swing.JPanel jpnlDatos;
    private javax.swing.JRadioButton jrbtnFemenino;
    private javax.swing.JRadioButton jrbtnMasculino;
    private javax.swing.JSlider jsldNivel;
    private javax.swing.JSpinner jspnEdad;
    private javax.swing.JTable jtblPersonas;
    private javax.swing.JToggleButton jtbtnAceptar;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextArea jtxtaInformacion;
    // End of variables declaration//GEN-END:variables
}

