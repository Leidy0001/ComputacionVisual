/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clase1.RepasoExamen;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author micha
 */
public class Practica1 extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Practica1.class.getName());

    /**
     * Creates new form Practica1
     */
    public Practica1() {
        initComponents();
        Genero.add(jrbtnMasculino);
        Genero.add(jrbtnFemenino);
        inicio();
    }

    ArrayList<Persona> personas = new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private int filaEditar = -1;

    public void inicio() {
        jbtnGuardar.setEnabled(false);
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cedula");
        modelo.addColumn("Edad");
        modelo.addColumn("Carrera");
        modelo.addColumn("Genero");
        modelo.addColumn("Intereses");
        modelo.addColumn("Satisfaccion");
        jtblResultados.setModel(modelo);
        jlstResultados.setModel(modeloLista);
    }

    public boolean guardar() {

        String Nombre = jtxtNombre.getText();
        String Apellido = jtxtApellido.getText();
        String Cedula = jtxtCedula.getText();
        int Carrera = jcbxCarrera.getSelectedIndex();
        if (Nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ingrese el nombre");
            return false;
        } else {

        }
        if (Apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ingrese el apellido");
            return false;
        }
        if (Cedula.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ingrese la cedula");
            return false;
        }

        if (Carrera == 0) {
            JOptionPane.showMessageDialog(null, "ingrese su Carrera");
            return false;
        }

        if (!jrbtnFemenino.isSelected() && !jrbtnMasculino.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione el genero");
            return false;
        }

        if (!jchkBasedeDatos.isSelected() && !jchkProgramacion.isSelected() && !jchkRedes.isSelected() && !jchkVideojuegos.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione sus intereses");
            return false;
        }

        if (jsldSatisfaccion.getValue() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione nivel de satisfaccion");
            return false;
        }

        int edad = Integer.parseInt(jspnEdad.getValue().toString());

        if (edad < 5) {
            JOptionPane.showMessageDialog(null, "Seleccione una edad valida");
            return false;
        }

        JOptionPane.showMessageDialog(null, "Guardado Correctamente");
        // JOptionPane.showMessageDialog(null, "Nombre: " + Nombre + " Apellido: " + Apellido + " Cedula: " + Cedula);
        
        return true;
    }

    public void LlenarTextA() {
        String gustos = "";
        String genero = jrbtnFemenino.isSelected() ? "Femenino" : "Masculino";
        gustos += jchkBasedeDatos.isSelected() ? "Base de datos " : "";
        gustos += jchkProgramacion.isSelected() ? "Programacion " : "";
        gustos += jchkRedes.isSelected() ? "Redes " : "";
        gustos += jchkVideojuegos.isSelected() ? "Videojuegos " : "";

        jtxtaResultados.setText(
                jtxtNombre.getText() + "\n"
                + jtxtApellido.getText() + "\n"
                + jtxtCedula.getText() + "\n"
                + jcbxCarrera.getSelectedItem() + "\n"
                + jspnEdad.getValue() + "\n"
                + genero + "\n"
                + gustos + "\n"
                + jsldSatisfaccion.getValue()
        );

    }

    public void llenarTablayLista() {
        String gustos = "";
        String Nombre = jtxtNombre.getText();
        String Apellido = jtxtApellido.getText();
        String Cedula = jtxtCedula.getText();
        String Carrera = jcbxCarrera.getSelectedItem().toString();
        int Edad = Integer.parseInt(jspnEdad.getValue().toString());
        String Genero = jrbtnFemenino.isSelected() ? "Femenino" : "Masculino";
        gustos += jchkBasedeDatos.isSelected() ? "Base de datos " : "";
        gustos += jchkProgramacion.isSelected() ? "Programacion " : "";
        gustos += jchkRedes.isSelected() ? "Redes " : "";
        gustos += jchkVideojuegos.isSelected() ? "Videojuegos " : "";
        int Satisfaccion = jsldSatisfaccion.getValue();
        modelo.addRow(new Object[]{Nombre, Apellido, Cedula, Edad, Carrera, Genero, gustos, Satisfaccion});
        modeloLista.addElement(Nombre + " " + Apellido + " " + Cedula + " " + Carrera + " " + Edad + " " + Genero + " " + gustos + " " + Satisfaccion);
        Persona persona = new Persona(Nombre,Apellido,Cedula,Carrera, Genero,gustos,Edad,Satisfaccion);
       personas.add(persona);
    }

    public void progreso() {

        int contador = 0;

        if (!jtxtNombre.getText().isEmpty()) {
            contador++;
        }

        if (!jtxtApellido.getText().isEmpty()) {
            contador++;
        }

        if (!jtxtCedula.getText().isEmpty()) {
            contador++;
        }

        if (jcbxCarrera.getSelectedIndex() != 0) {
            contador++;
        }

        int edad = Integer.parseInt(jspnEdad.getValue().toString());
        if (edad > 5) {
            contador++;
        }

        if (jrbtnMasculino.isSelected() || jrbtnFemenino.isSelected()) {
            contador++;
        }

        if (jchkBasedeDatos.isSelected()
                || jchkProgramacion.isSelected()
                || jchkRedes.isSelected()
                || jchkVideojuegos.isSelected()) {
            contador++;
        }

        if (jsldSatisfaccion.getValue() > 0) {
            contador++;
        }

        int porcentaje = (contador * 100) / 8;

        jprbProgreso.setValue(porcentaje);

        if (porcentaje == 100 && jtbtnCondiciones.isSelected()) {
            jbtnGuardar.setEnabled(true);
        } else {
            jbtnGuardar.setEnabled(false);
        }
    }
    
    public void cargarPersona(Persona persona) {

    jtxtNombre.setText(persona.getNombre());
    jtxtApellido.setText(persona.getApellido());
    jtxtCedula.setText(persona.getCedula());

    jcbxCarrera.setSelectedItem(persona.getCarrera());

    jspnEdad.setValue(persona.getEdad());

    if (persona.getGenero().equals("Masculino")) {
        jrbtnMasculino.setSelected(true);
    } else {
        jrbtnFemenino.setSelected(true);
    }

    String gustos = persona.getGustos();

    jchkBasedeDatos.setSelected(gustos.contains("Base de datos"));
    jchkProgramacion.setSelected(gustos.contains("Programacion"));
    jchkRedes.setSelected(gustos.contains("Redes"));
    jchkVideojuegos.setSelected(gustos.contains("Videojuegos"));

    jsldSatisfaccion.setValue(persona.getSatisfaccion());
}
    
    public void Editar() {

    if (filaEditar == -1) {
        JOptionPane.showMessageDialog(null, "Seleccione una fila para editar");
        return;
    }

    Persona persona = personas.get(filaEditar);

    persona.setNombre(jtxtNombre.getText());
    persona.setApellido(jtxtApellido.getText());
    persona.setCedula(jtxtCedula.getText());
    persona.setCarrera(jcbxCarrera.getSelectedItem().toString());
    persona.setEdad(Integer.parseInt(jspnEdad.getValue().toString()));

    String genero = jrbtnFemenino.isSelected()
            ? "Femenino"
            : "Masculino";

    persona.setGenero(genero);

    String gustos = "";

    gustos += jchkBasedeDatos.isSelected() ? "Base de datos " : "";
    gustos += jchkProgramacion.isSelected() ? "Programacion " : "";
    gustos += jchkRedes.isSelected() ? "Redes " : "";
    gustos += jchkVideojuegos.isSelected() ? "Videojuegos " : "";

    persona.setGustos(gustos);

    persona.setSatisfaccion(jsldSatisfaccion.getValue());

    modelo.setValueAt(persona.getNombre(), filaEditar, 0);
    modelo.setValueAt(persona.getApellido(), filaEditar, 1);
    modelo.setValueAt(persona.getCedula(), filaEditar, 2);
    modelo.setValueAt(persona.getEdad(), filaEditar, 3);
    modelo.setValueAt(persona.getCarrera(), filaEditar, 4);
    modelo.setValueAt(persona.getGenero(), filaEditar, 5);
    modelo.setValueAt(persona.getGustos(), filaEditar, 6);
    modelo.setValueAt(persona.getSatisfaccion(), filaEditar, 7);

    modeloLista.setElementAt(
            persona.getNombre() + " "
            + persona.getApellido() + " "
            + persona.getCedula() + " "
            + persona.getCarrera() + " "
            + persona.getEdad() + " "
            + persona.getGenero() + " "
            + persona.getGustos() + " "
            + persona.getSatisfaccion(),
            filaEditar
    );

    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Genero = new javax.swing.ButtonGroup();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbtnGuardar = new javax.swing.JButton();
        jcbxCarrera = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jrbtnMasculino = new javax.swing.JRadioButton();
        jrbtnFemenino = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jchkBasedeDatos = new javax.swing.JCheckBox();
        jchkProgramacion = new javax.swing.JCheckBox();
        jchkRedes = new javax.swing.JCheckBox();
        jchkVideojuegos = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jsldSatisfaccion = new javax.swing.JSlider();
        jtbtnCondiciones = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtaResultados = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlstResultados = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblResultados = new javax.swing.JTable();
        jspnEdad = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jprbProgreso = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        jbtnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtxtCedula.addActionListener(this::jtxtCedulaActionPerformed);

        jtxtNombre.addActionListener(this::jtxtNombreActionPerformed);

        jtxtApellido.addActionListener(this::jtxtApellidoActionPerformed);

        jLabel1.setText("Nombre");

        jLabel2.setText("Cedula");

        jLabel3.setText("Apellido");

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(this::jbtnGuardarActionPerformed);

        jcbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Software", "Industrial", "TI" }));
        jcbxCarrera.addActionListener(this::jcbxCarreraActionPerformed);

        jLabel4.setText("Carrera");

        jrbtnMasculino.setText("Masculino");
        jrbtnMasculino.addActionListener(this::jrbtnMasculinoActionPerformed);

        jrbtnFemenino.setText("Femenino");
        jrbtnFemenino.addActionListener(this::jrbtnFemeninoActionPerformed);

        jLabel5.setText("Sexo");

        jchkBasedeDatos.setText("Base de Datos");
        jchkBasedeDatos.addActionListener(this::jchkBasedeDatosActionPerformed);

        jchkProgramacion.setText("Programacion");
        jchkProgramacion.addActionListener(this::jchkProgramacionActionPerformed);

        jchkRedes.setText("Redes");
        jchkRedes.addActionListener(this::jchkRedesActionPerformed);

        jchkVideojuegos.setText("Videojuegos");
        jchkVideojuegos.addActionListener(this::jchkVideojuegosActionPerformed);

        jLabel6.setText("Intereses");

        jsldSatisfaccion.setMaximum(10);
        jsldSatisfaccion.setPaintLabels(true);
        jsldSatisfaccion.setPaintTicks(true);
        jsldSatisfaccion.setSnapToTicks(true);
        jsldSatisfaccion.setValue(0);
        jsldSatisfaccion.setAutoscrolls(true);
        jsldSatisfaccion.setOpaque(true);
        jsldSatisfaccion.addChangeListener(this::jsldSatisfaccionStateChanged);

        jtbtnCondiciones.setText("Acepto");
        jtbtnCondiciones.addActionListener(this::jtbtnCondicionesActionPerformed);

        jLabel7.setText("Acepto los terminos y condiciones");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtxtaResultados.setColumns(20);
        jtxtaResultados.setRows(5);
        jScrollPane1.setViewportView(jtxtaResultados);

        jlstResultados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlstResultados);

        jtblResultados.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblResultadosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtblResultados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jspnEdad.addChangeListener(this::jspnEdadStateChanged);

        jLabel8.setText("Edad");

        jLabel9.setText("Progreso");

        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(this::jbtnEditarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jrbtnFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jrbtnMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtxtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                        .addComponent(jtxtCedula)
                                        .addComponent(jtxtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                        .addComponent(jcbxCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jchkProgramacion)
                                            .addComponent(jchkRedes)
                                            .addComponent(jchkVideojuegos)
                                            .addComponent(jchkBasedeDatos)))
                                    .addComponent(jsldSatisfaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtbtnCondiciones))
                                    .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jspnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jprbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jbtnEditar)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkProgramacion)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jchkBasedeDatos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jchkRedes))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchkVideojuegos)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jspnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jsldSatisfaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnMasculino)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbtnFemenino)
                            .addComponent(jtbtnCondiciones)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jprbProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtnEditar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        guardar();
        LlenarTextA();
        llenarTablayLista();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jrbtnFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnFemeninoActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jrbtnFemeninoActionPerformed

    private void jchkProgramacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkProgramacionActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jchkProgramacionActionPerformed

    private void jtbtnCondicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtnCondicionesActionPerformed
    }//GEN-LAST:event_jtbtnCondicionesActionPerformed

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCedulaActionPerformed

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jtxtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtApellidoActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtApellidoActionPerformed

    private void jcbxCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxCarreraActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jcbxCarreraActionPerformed

    private void jrbtnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnMasculinoActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jrbtnMasculinoActionPerformed

    private void jchkBasedeDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkBasedeDatosActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jchkBasedeDatosActionPerformed

    private void jchkRedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkRedesActionPerformed
        progreso();        // TODO add your handling code here:
    }//GEN-LAST:event_jchkRedesActionPerformed

    private void jchkVideojuegosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkVideojuegosActionPerformed
        progreso();          // TODO add your handling code here:
    }//GEN-LAST:event_jchkVideojuegosActionPerformed

    private void jsldSatisfaccionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsldSatisfaccionStateChanged
        progreso();          // TODO add your handling code here:
    }//GEN-LAST:event_jsldSatisfaccionStateChanged

    private void jspnEdadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspnEdadStateChanged
        progreso();          // TODO add your handling code here:
    }//GEN-LAST:event_jspnEdadStateChanged

    private void jtblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblResultadosMouseClicked
filaEditar = jtblResultados.getSelectedRow();

    if (filaEditar >= 0) {
        Persona persona = personas.get(filaEditar);

        cargarPersona(persona);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jtblResultadosMouseClicked

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
 Editar();        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnEditarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Practica1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Genero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JComboBox<String> jcbxCarrera;
    private javax.swing.JCheckBox jchkBasedeDatos;
    private javax.swing.JCheckBox jchkProgramacion;
    private javax.swing.JCheckBox jchkRedes;
    private javax.swing.JCheckBox jchkVideojuegos;
    private javax.swing.JList<String> jlstResultados;
    private javax.swing.JProgressBar jprbProgreso;
    private javax.swing.JRadioButton jrbtnFemenino;
    private javax.swing.JRadioButton jrbtnMasculino;
    private javax.swing.JSlider jsldSatisfaccion;
    private javax.swing.JSpinner jspnEdad;
    private javax.swing.JTable jtblResultados;
    private javax.swing.JToggleButton jtbtnCondiciones;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextArea jtxtaResultados;
    // End of variables declaration//GEN-END:variables
}
