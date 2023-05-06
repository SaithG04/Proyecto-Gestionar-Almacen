package Formularios;

import Clases.cAdministradorProveedores;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class frmAdministradorProveedores extends javax.swing.JFrame {

    //Variables globales
    cAdministradorProveedores oProveedores = new cAdministradorProveedores();
//    cLogueo oLogueo = new cLogueo();
    DefaultTableModel modelo = new DefaultTableModel();
    ; //modelo de la clase default
    ResultSet rsProveedores, rsDepartamentos, rsNumDep, rsRespuesta;
    Object[] c = new Object[1];
    ArrayList<String> arraicito = new ArrayList<>();

    //Constructor
    public frmAdministradorProveedores() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cboDepartamento = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProveedores = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1123, 655));
        setResizable(false);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpiar.setBackground(new java.awt.Color(0, 102, 255));
        btnLimpiar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/limpieza-de-datos.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseExited(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 146, 42));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("IdProveedores:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, 20));

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Razón Social:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 130, 29));

        txtId.setBackground(new java.awt.Color(0, 51, 102));
        txtId.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setName(""); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 29, 113, 30));

        txtRazonSocial.setBackground(new java.awt.Color(0, 51, 102));
        txtRazonSocial.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtRazonSocial.setForeground(new java.awt.Color(255, 255, 255));
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyTyped(evt);
            }
        });
        jPanel2.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 402, -1));

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("R.U.C.:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 70, -1));

        txtRUC.setBackground(new java.awt.Color(0, 51, 102));
        txtRUC.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtRUC.setForeground(new java.awt.Color(255, 255, 255));
        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUCKeyTyped(evt);
            }
        });
        jPanel2.add(txtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 210, -1));

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dirección:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        txtDireccion.setBackground(new java.awt.Color(0, 51, 102));
        txtDireccion.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 402, 29));

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Contacto:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, -1));

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Teléfono:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 142, -1));

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Email:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 70, -1));

        jLabel10.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("DEPARTAMENTO:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        txtContacto.setBackground(new java.awt.Color(0, 51, 102));
        txtContacto.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtContacto.setForeground(new java.awt.Color(255, 255, 255));
        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactoKeyTyped(evt);
            }
        });
        jPanel2.add(txtContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 402, -1));

        txtTelefono.setBackground(new java.awt.Color(0, 51, 102));
        txtTelefono.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 210, -1));

        txtEmail.setBackground(new java.awt.Color(0, 51, 102));
        txtEmail.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 402, -1));

        cboDepartamento.setBackground(new java.awt.Color(0, 51, 102));
        cboDepartamento.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        cboDepartamento.setForeground(new java.awt.Color(255, 255, 255));
        cboDepartamento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(cboDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 168, -1));

        btnAgregar.setBackground(new java.awt.Color(0, 102, 255));
        btnAgregar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/agregar-usuario.png"))); // NOI18N
        btnAgregar.setText("INSERTAR");
        btnAgregar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarMouseExited(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, 146, 40));

        btnModificar.setBackground(new java.awt.Color(0, 102, 255));
        btnModificar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarMouseExited(evt);
            }
        });
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 146, 42));

        btnEliminar.setBackground(new java.awt.Color(0, 102, 255));
        btnEliminar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, 146, 42));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Copperplate Gothic Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jtbProveedores.setBackground(new java.awt.Color(0, 0, 51));
        jtbProveedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtbProveedores.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jtbProveedores.setForeground(new java.awt.Color(255, 255, 255));
        jtbProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Razon Social", "R.U.C.", "Direccion", "Contacto", "Teléfono", "Email", "Departamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbProveedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbProveedores.setSelectionBackground(new java.awt.Color(153, 255, 153));
        jtbProveedores.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jtbProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbProveedoresMouseClicked(evt);
            }
        });
        jtbProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbProveedoresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtbProveedores);
        if (jtbProveedores.getColumnModel().getColumnCount() > 0) {
            jtbProveedores.getColumnModel().getColumn(0).setMinWidth(40);
            jtbProveedores.getColumnModel().getColumn(0).setMaxWidth(40);
            jtbProveedores.getColumnModel().getColumn(2).setMinWidth(101);
            jtbProveedores.getColumnModel().getColumn(2).setMaxWidth(101);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1105, 350));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/819282.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Eventos
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (Validar() == true && CompararClave() == true) {
            try {
                oProveedores.setIdProveedor(Integer.parseInt(txtId.getText()));
                oProveedores.setRazonSocial(txtRazonSocial.getText());
                oProveedores.setRuc(txtRUC.getText());
                oProveedores.setDireccion(txtDireccion.getText());
                oProveedores.setContacto(txtContacto.getText());
                oProveedores.setTelefono(txtTelefono.getText());
                oProveedores.setEmail(txtEmail.getText());
                oProveedores.setDepartamento(cboDepartamento.getSelectedItem().toString());
                oProveedores.Insertar();

                rsNumDep = oProveedores.ObtNumDep();
                while (rsNumDep.next()) {
                    c[0] = rsNumDep.getInt("Num_Proveedores");
                    oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) + 1);
                    oProveedores.ActDep();
                }
                JOptionPane.showMessageDialog(null, "El proveedor se ha agregado correctamente.");
//                Mostrar();
                Limpiar();
            } catch (SQLException | ClassNotFoundException ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Error al agregar.");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        if (Conf() == true) {
            int fila = jtbProveedores.getSelectedRow();
            String dep = jtbProveedores.getValueAt(fila, 7).toString();
            if (Validar() == true) {
                try {
                    oProveedores.setIdProveedor(Integer.parseInt(txtId.getText()));
                    oProveedores.setRazonSocial(txtRazonSocial.getText());
                    oProveedores.setRuc(txtRUC.getText());
                    oProveedores.setDireccion(txtDireccion.getText());
                    oProveedores.setContacto(txtContacto.getText());
                    oProveedores.setTelefono(txtTelefono.getText());
                    oProveedores.setEmail(txtEmail.getText());
                    oProveedores.setDepartamento(dep);
                    if (!(dep.equalsIgnoreCase(cboDepartamento.getSelectedItem().toString()))) {

                        rsNumDep = oProveedores.ObtNumDep();
                        while (rsNumDep.next()) {
                            c[0] = rsNumDep.getInt("Num_Proveedores");
                            oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) - 1);
                            oProveedores.ActDep();
                        }
                        oProveedores.setDepartamento(cboDepartamento.getSelectedItem().toString());
                        rsNumDep = oProveedores.ObtNumDep();
                        while (rsNumDep.next()) {
                            c[0] = rsNumDep.getInt("Num_Proveedores");
                            oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) + 1);
                            oProveedores.ActDep();
                        }
                    } else {
                        oProveedores.setDepartamento(dep);
                    }
                    oProveedores.Modificar();
//                    Mostrar();
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "Modificado con éxito.");
                } catch (SQLException | ClassNotFoundException ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Error al modificar.");
                    System.err.println(ex);
                }
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (Conf() == true) {
            Toolkit.getDefaultToolkit().beep();
            String opciones[] = {"Sí", "No"};
            int s = JOptionPane.showOptionDialog(
                    null,
                    "¿Está seguro de eliminar?",
                    " Confirmación",
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, this);
            if (s == 0) {
                try {
                    int fila = jtbProveedores.getSelectedRow();
                    oProveedores.setIdProveedor(Integer.parseInt(jtbProveedores.getValueAt(fila, 0).toString()));
                    oProveedores.setDepartamento(jtbProveedores.getValueAt(fila, 7).toString());
                    rsNumDep = oProveedores.ObtNumDep();

                    while (rsNumDep.next()) {
                        c[0] = rsNumDep.getInt("Num_Proveedores");
                        oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) - 1);
                        oProveedores.ActDep();
                    }
                    oProveedores.Eliminar();
                    oProveedores.setRazonSocial(jtbProveedores.getValueAt(fila, 1).toString());
                    oProveedores.EliminarDePrecio();
//                        Mostrar();
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "El proveedor ha sido eliminado correctamente.");
                } catch (SQLException | ClassNotFoundException ex) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                    System.err.println(ex);
                }
            }

        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void jtbProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbProveedoresMouseClicked
        try {
            Seleccionar();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_jtbProveedoresMouseClicked
    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        char dig = evt.getKeyChar();
        if ((((dig < '0') || (dig > '9')) && (dig != '\b' /*Barra espaciadora*/)) || txtId.getText().length() == 5) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtIdKeyTyped
    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char dig = evt.getKeyChar();
        if ((((dig < '0') || (dig > '9')) && (dig != '\b')) || txtTelefono.getText().length() == 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped
    private void txtContactoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyTyped
        char dig = evt.getKeyChar();
        //Si la primera letra es de un dígito o mide a 200 caráteres
        if (Character.isDigit(dig) == true || txtContacto.getText().length() == 200) {
            evt.consume();
        }
    }//GEN-LAST:event_txtContactoKeyTyped
    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
        if (txtRazonSocial.getText().length() == 30) {
            evt.consume();//nombre del evento  y consume bloquea el teclado después de llegar al tamaño indicado
        }
    }//GEN-LAST:event_txtRazonSocialKeyTyped
    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (txtDireccion.getText().length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped
    private void btnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseEntered
        btnAgregar.setBackground(new Color(255, 204, 102));
    }//GEN-LAST:event_btnAgregarMouseEntered
    private void btnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseExited
        btnAgregar.setBackground(new Color(0, 102, 255));
    }//GEN-LAST:event_btnAgregarMouseExited
    private void btnLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseEntered
        btnLimpiar.setBackground(new Color(255, 204, 102));
    }//GEN-LAST:event_btnLimpiarMouseEntered
    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited
        btnLimpiar.setBackground(new Color(0, 102, 255));
    }//GEN-LAST:event_btnLimpiarMouseExited
    private void btnModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseEntered
        btnModificar.setBackground(new Color(255, 204, 102));
    }//GEN-LAST:event_btnModificarMouseEntered
    private void btnModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseExited
        btnModificar.setBackground(new Color(0, 102, 255));
    }//GEN-LAST:event_btnModificarMouseExited
    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        btnEliminar.setBackground(new Color(255, 204, 102));
    }//GEN-LAST:event_btnEliminarMouseEntered
    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setBackground(new Color(0, 102, 255));
    }//GEN-LAST:event_btnEliminarMouseExited
    private void txtRUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyTyped
        char dig = evt.getKeyChar();
        if ((((dig < '0') || (dig > '9')) && (dig != '\b')) || txtRUC.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRUCKeyTyped
    private void jtbProveedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbProveedoresKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
            try {
                Seleccionar();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_jtbProveedoresKeyReleased

    //Métodos
    private void Seleccionar() throws ClassNotFoundException {
        int fila = jtbProveedores.getSelectedRow();
        if (Conf() == true) {
            try {
                EncontrarDepartamento();
                txtId.setText(jtbProveedores.getValueAt(fila, 0).toString());
                txtRazonSocial.setText(jtbProveedores.getValueAt(fila, 1).toString());
                txtRUC.setText(jtbProveedores.getValueAt(fila, 2).toString());
                txtDireccion.setText(jtbProveedores.getValueAt(fila, 3).toString());
                txtContacto.setText(jtbProveedores.getValueAt(fila, 4).toString());
                txtTelefono.setText(jtbProveedores.getValueAt(fila, 5).toString());
                txtEmail.setText(jtbProveedores.getValueAt(fila, 6).toString());
            } catch (SQLException ex) {
                Toolkit.getDefaultToolkit().beep();
                String b[] = {"Aceptar"};
                String e = "com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure\n"
                        + "\n"
                        + "The last packet sent successfully to the server was 0 milliseconds ago. "
                        + "The driver has not received any packets from the server.";

                if (e.equals(ex.toString())) {
                    JOptionPane.showOptionDialog(null,
                            " Desconexión de la Base de Datos.", "  Error", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, icono("/Resources/den.png", 30, 30),
                            b, b[0]);
                    frmLogueo oFLogueo = new frmLogueo();
                    oFLogueo.setVisible(true);
                    dispose();
                } else {
                    System.err.println(ex);
                }
            }
        }
    }

    private boolean CompararClave() {
        //En caso no quiera usar id incrementable
        int fila = jtbProveedores.getRowCount();
        int i;
        boolean b = true;
        String[] valores = new String[fila];
        for (i = 0; i < fila; i++) {
            valores[i] = jtbProveedores.getValueAt(i, 0).toString();
            if (valores[i].equals(txtId.getText())) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "El proveedor ya existe.");
                b = false;
                i = fila;
            }
        }
        return b;
    }

//    private void EncontrarDepartamento() throws SQLException, ClassNotFoundException{   
//        String fila = jtbProveedores.getValueAt(jtbProveedores.getSelectedRow(), 7).toString();
//        String[] depart = new String[25];
//        rsDepartamentos = oProveedores.ListarDepartamentos();
//        for (int i=0; rsDepartamentos.next();i++) {
//            depart[i] = rsDepartamentos.getString("Departamento");
//            if(depart[i].equals(fila)){
//                cboDepartamento.setSelectedIndex(i);
//            }
//        }
//    } 
//    public final void MostrarDepartamentos(){
//        try {
//            rsDepartamentos = oProveedores.ListarDepartamentos();      
//             while (rsDepartamentos .next()) {  
//                cboDepartamento.addItem(rsDepartamentos.getString("Departamento"));
//            }
//            cboDepartamento.setSelectedItem(null);
//          }
//        catch (SQLException|ClassNotFoundException ex) {
//            System.err.println(ex);
//        }
//    }
//    public final void Close(){
//        addWindowListener(new WindowAdapter(){
//            @Override
//            public void windowClosing(WindowEvent evt){
//                String RS,R,D,C,T,E,P;
//                RS = txtRazonSocial.getText();
//                R = txtRUC.getText();
//                D = txtDireccion.getText();
//                C = txtContacto.getText();
//                T = txtTelefono.getText();        
//                E = txtEmail.getText();
//                
//                if(!RS.isEmpty()||!R.isEmpty()||!D.isEmpty()||!C.isEmpty()||!T.isEmpty()||!E.isEmpty()){
//                    Toolkit.getDefaultToolkit().beep();
//                    String b[] = {"Aceptar","Cancelar"};
//                    int e = JOptionPane.showOptionDialog(null,
//                        " ¿Salir sin guardar?"," Confirmar", JOptionPane.DEFAULT_OPTION,
//                        JOptionPane.PLAIN_MESSAGE,icono("/Resources/den.png",30,30), 
//                        b, b[1]);
//                    if(e==0){
//                        frmAdministrador oFA = new frmAdministrador();
//                        oFA.setVisible(true);
//                        dispose();
//                    }
//                }else{
//                    frmAdministrador oFA = new frmAdministrador();
//                    oFA.setVisible(true);
//                    dispose();
//                } 
//            }
//        }); 
//    }
    public Icon icono(String url, int width, int height) {
        //Método para obtener atributos de la imagen como "Icon".
        Icon ic = new ImageIcon(new ImageIcon(this.getClass().getResource(url))
                .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return ic;
    }

    public JComboBox<String> getCboDepartamento() {
        return cboDepartamento;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JTable getJtbProveedores() {
        return jtbProveedores;
    }

    public JTextField getTxtContacto() {
        return txtContacto;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtRUC() {
        return txtRUC;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboDepartamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    public javax.swing.JTable jtbProveedores;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
