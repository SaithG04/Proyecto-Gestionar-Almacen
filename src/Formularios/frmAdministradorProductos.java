package Formularios;

import Clases.cAdministradorProductos;
import Clases.cInfoProductos;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class frmAdministradorProductos extends javax.swing.JFrame {

    ResultSet rsProductos,rsProveedores,rsCategorias,rsIndice,rsCantidad,rsPrecio;
    cAdministradorProductos oProductos = new cAdministradorProductos();
    cInfoProductos oInfo = new cInfoProductos();
    DefaultTableModel modelo;
    Object[] c = new Object[3];
    ArrayList<String> productos, categorias, prov;
    
    
    public frmAdministradorProductos() {
        initComponents();
        Mostrar();
        this.setLocationRelativeTo(null);
        rsProductos = null;
        rsProveedores = null;
        rsCategorias = null;
        rsIndice = null;
        rsCantidad = null;
        rsPrecio = null;
        modelo = new DefaultTableModel();
        productos = new ArrayList<>();
        categorias = new ArrayList<>();
        prov = new ArrayList<>();
        MostrarProveedores();
        MostrarCategorias();
        Opciones();
        Close();
    }
    
    private void Mostrar() {
        try {
            
           rsProductos = oProductos.ListaProductos();
           Object[] proveedores = new Object[4];          
           modelo = (DefaultTableModel) jtbProductos.getModel(); //modelo Creamos un Objeto de la clase Default...
           
           modelo.setRowCount(0);
           
           while (rsProductos.next())
           {
               proveedores[0] = rsProductos.getInt("IdProducto");
               proveedores[1] = rsProductos.getString("Nombre");
               proveedores[2] = rsProductos.getString("Categoria");
               proveedores[3] = rsProductos.getString("Stock");

               modelo.addRow(proveedores); //Va aderiendo en nuestro DefaultTableModel "modelo"
           }
           jtbProductos.setModel(modelo);
        }
        catch (SQLException |ClassNotFoundException ex) {
            System.err.println(ex);
        }
    } 
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGrupo1 = new javax.swing.ButtonGroup();
        bGrupo2 = new javax.swing.ButtonGroup();
        bGrupo3 = new javax.swing.ButtonGroup();
        pmOpciones = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        txtCKilogramos = new javax.swing.JTextField();
        cbProducto = new javax.swing.JComboBox<>();
        rbKilos = new javax.swing.JRadioButton();
        rbUnidades = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbEscribir1 = new javax.swing.JRadioButton();
        rbElegir1 = new javax.swing.JRadioButton();
        txtCategoria = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtCUnidades = new javax.swing.JTextField();
        rbEscribir2 = new javax.swing.JRadioButton();
        rbElegir2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProductos = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        btnRProducto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        btnRCategoria = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCKilogramos.setBackground(new java.awt.Color(153, 204, 255));
        txtCKilogramos.setForeground(new java.awt.Color(0, 0, 0));
        txtCKilogramos.setEnabled(false);
        txtCKilogramos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCKilogramosKeyTyped(evt);
            }
        });
        jPanel1.add(txtCKilogramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 154, 159, 30));

        cbProducto.setBackground(new java.awt.Color(153, 204, 255));
        cbProducto.setForeground(new java.awt.Color(0, 0, 0));
        cbProducto.setEnabled(false);
        cbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbProductoMouseClicked(evt);
            }
        });
        jPanel1.add(cbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 243, 40));

        bGrupo3.add(rbKilos);
        rbKilos.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbKilos.setForeground(new java.awt.Color(255, 255, 255));
        rbKilos.setText("Kg");
        rbKilos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbKilos.setEnabled(false);
        rbKilos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbKilosMouseClicked(evt);
            }
        });
        jPanel1.add(rbKilos, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, 40));

        bGrupo3.add(rbUnidades);
        rbUnidades.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbUnidades.setForeground(new java.awt.Color(255, 255, 255));
        rbUnidades.setText("Unidades");
        rbUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbUnidades.setEnabled(false);
        rbUnidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbUnidadesMouseClicked(evt);
            }
        });
        jPanel1.add(rbUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PRODUCTO:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 88, -1));

        txtProducto.setBackground(new java.awt.Color(153, 204, 255));
        txtProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtProducto.setEnabled(false);
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 94, 240, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CATEGORÍA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 88, 29));

        bGrupo1.add(rbEscribir1);
        rbEscribir1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbEscribir1.setForeground(new java.awt.Color(255, 255, 255));
        rbEscribir1.setText("Nuevo");
        rbEscribir1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbEscribir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbEscribir1MouseClicked(evt);
            }
        });
        jPanel1.add(rbEscribir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        bGrupo1.add(rbElegir1);
        rbElegir1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbElegir1.setForeground(new java.awt.Color(255, 255, 255));
        rbElegir1.setText("Seleccionar");
        rbElegir1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbElegir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbElegir1MouseClicked(evt);
            }
        });
        jPanel1.add(rbElegir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, 40));

        txtCategoria.setBackground(new java.awt.Color(153, 204, 255));
        txtCategoria.setForeground(new java.awt.Color(0, 0, 0));
        txtCategoria.setEnabled(false);
        jPanel1.add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 34, 240, 30));

        cbCategoria.setBackground(new java.awt.Color(153, 204, 255));
        cbCategoria.setForeground(new java.awt.Color(0, 0, 0));
        cbCategoria.setEnabled(false);
        cbCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCategoriaMouseClicked(evt);
            }
        });
        jPanel1.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 243, 40));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CANTIDAD:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 88, 35));

        txtCUnidades.setBackground(new java.awt.Color(153, 204, 255));
        txtCUnidades.setForeground(new java.awt.Color(0, 0, 0));
        txtCUnidades.setEnabled(false);
        txtCUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCUnidadesKeyTyped(evt);
            }
        });
        jPanel1.add(txtCUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 154, 240, 30));

        bGrupo2.add(rbEscribir2);
        rbEscribir2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbEscribir2.setForeground(new java.awt.Color(255, 255, 255));
        rbEscribir2.setText("Nuevo");
        rbEscribir2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbEscribir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbEscribir2MouseClicked(evt);
            }
        });
        jPanel1.add(rbEscribir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        bGrupo2.add(rbElegir2);
        rbElegir2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbElegir2.setForeground(new java.awt.Color(255, 255, 255));
        rbElegir2.setText("Seleccionar");
        rbElegir2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbElegir2.setEnabled(false);
        rbElegir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbElegir2MouseClicked(evt);
            }
        });
        jPanel1.add(rbElegir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, 40));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PROVEEDOR:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        cbProveedor.setBackground(new java.awt.Color(153, 204, 255));
        cbProveedor.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(cbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 243, 40));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PRECIO POR UNIDAD / KG:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        txtPrecio.setBackground(new java.awt.Color(153, 204, 255));
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setEnabled(false);
        txtPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPrecioMouseClicked(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 204, 100, 30));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID DEL PRODUCTO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 140, -1));

        jtbProductos.setBackground(new java.awt.Color(102, 153, 255));
        jtbProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtbProductos.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jtbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Categoría", "Stock"
            }
        ));
        jScrollPane1.setViewportView(jtbProductos);
        if (jtbProductos.getColumnModel().getColumnCount() > 0) {
            jtbProductos.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 890, 350));

        txtId.setBackground(new java.awt.Color(153, 204, 255));
        txtId.setEnabled(false);
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 204, 90, 30));

        btnRProducto.setBackground(new java.awt.Color(153, 204, 255));
        btnRProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnRProducto.setText("REGISTRAR PRODUCTO");
        btnRProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRProductoMouseClicked(evt);
            }
        });
        jPanel1.add(btnRProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 186, 44));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FECHA CADUCIDAD:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, -1));

        txtFecha.setBackground(new java.awt.Color(153, 204, 255));
        txtFecha.setForeground(new java.awt.Color(0, 0, 0));
        txtFecha.setEnabled(false);
        txtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFechaMouseClicked(evt);
            }
        });
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 180, 30));

        btnRCategoria.setBackground(new java.awt.Color(153, 204, 255));
        btnRCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnRCategoria.setText("REGISTRAR CATEGORÍA");
        btnRCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRCategoriaMouseClicked(evt);
            }
        });
        jPanel1.add(btnRCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, 193, 44));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 690));

        jPanel2.setPreferredSize(new java.awt.Dimension(960, 690));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/IMG-20211202-WA0091.jpg"))); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(960, 690));
        jLabel9.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbElegir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbElegir1MouseClicked
        cbCategoria.setEnabled(true);
        txtCategoria.setEnabled(false);
        rbElegir2.setEnabled(true);

    }//GEN-LAST:event_rbElegir1MouseClicked

    private void rbEscribir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbEscribir1MouseClicked
        cbCategoria.setEnabled(false);
        txtCategoria.setEnabled(true);
        rbElegir2.setEnabled(false);
        cbProducto.setEnabled(false);
    }//GEN-LAST:event_rbEscribir1MouseClicked

    private void rbElegir2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbElegir2MouseClicked
        if(rbElegir2.isEnabled()){
            cbProducto.setEnabled(true);
            txtProducto.setEnabled(false);
            txtId.setEnabled(false);
        }
    }//GEN-LAST:event_rbElegir2MouseClicked

    private void rbEscribir2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbEscribir2MouseClicked
        cbProducto.setEnabled(false);
        txtProducto.setEnabled(true);
        txtId.setEnabled(true);
    }//GEN-LAST:event_rbEscribir2MouseClicked

    private void rbKilosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbKilosMouseClicked
        if((rbElegir1.isSelected()||rbEscribir1.isSelected())&&(rbElegir2.isSelected()||rbEscribir2.isSelected())){
            rbKilos.setEnabled(true);
            rbUnidades.setEnabled(false);
            txtCKilogramos.setEnabled(true);
            txtCUnidades.setEnabled(false);
        }   
    }//GEN-LAST:event_rbKilosMouseClicked

    private void rbUnidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbUnidadesMouseClicked
        if((rbElegir1.isSelected()||rbEscribir1.isSelected())&&(rbElegir2.isSelected()||rbEscribir2.isSelected())){
            rbUnidades.setEnabled(true);
            rbKilos.setEnabled(false);
            txtCKilogramos.setEnabled(false);
            txtCUnidades.setEnabled(true);
        }
    }//GEN-LAST:event_rbUnidadesMouseClicked

    private void cbProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbProductoMouseClicked
        if(cbProducto.isEnabled()){
            cbProducto.removeAllItems();
            if(cbCategoria.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(null,"Eliga una categoría primero.");
            }else{
                oProductos.setCategoria(cbCategoria.getSelectedItem().toString());
                MostrarProductos();
            }
        }
    }//GEN-LAST:event_cbProductoMouseClicked

    private void cbCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMouseClicked
        cbProducto.removeAllItems();
        cbProducto.setSelectedItem(null);
    }//GEN-LAST:event_cbCategoriaMouseClicked

    private void btnRCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRCategoriaMouseClicked
        if(!rbEscribir1.isSelected()){
            //beep
            JOptionPane.showMessageDialog(null, "No hay categoría nueva para agregar.");
        }else if(rbEscribir1.isSelected()&&txtCategoria.getText().isEmpty()){
            //beep
            txtCategoria.requestFocus();
        }else{
            try {
                oProductos.setCategoria(txtCategoria.getText());
                oProductos.NuevaCategoria();
                JOptionPane.showMessageDialog(null, "Correcto");
                txtCategoria.setText(null);
                cbCategoria.removeAllItems();
                cbCategoria.setSelectedIndex(-1);
                MostrarCategorias();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Incorrecto");
                System.err.println(ex);
            }
        }
    }//GEN-LAST:event_btnRCategoriaMouseClicked

    private void btnRProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRProductoMouseClicked
        
        if(Validar()&&CompararId()){
            if(rbElegir1.isSelected()&&rbElegir2.isSelected()){
                try {
                    //Un producto existente tendrá un nuevo proveedor
                    oProductos.setCategoria(cbCategoria.getSelectedItem().toString());
                    oProductos.setProducto(cbProducto.getSelectedItem().toString());
                    if(rbUnidades.isSelected()){
                        oProductos.setCantidad(Integer.parseInt(txtCUnidades.getText()));
                    }else{
                        oProductos.setCantidad(Integer.parseInt(txtCKilogramos.getText()));
                    }
                    oProductos.setPrecio(txtPrecio.getText());
                    oProductos.setFechaCaducidad(txtFecha.getText());
                    oProductos.setProveedor(cbProveedor.getSelectedItem().toString());
                    oProductos.InsertarPrecioDeExistente();
                    rsCantidad = oProductos.ObtCant();
                    while(rsCantidad.next()){
                        int stock = rsCantidad.getInt("Stock");
                        if(rbUnidades.isSelected()){
                            oProductos.setCantidad(stock+Integer.parseInt(txtCUnidades.getText()));
                        }else{
                            oProductos.setCantidad(stock+Integer.parseInt(txtCKilogramos.getText()));
                        }
                        oProductos.AgStock();
                    }
                    JOptionPane.showMessageDialog(null, "Correcto");
                    Limpiar();
                    Mostrar();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(frmAdministradorProductos.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else if(rbEscribir2.isSelected()&&rbElegir1.isSelected()){
                //Nuevo producto de categoria existente
                try {
                    oProductos.setCategoria(cbCategoria.getSelectedItem().toString());
                    oProductos.setProducto(txtProducto.getText());
                    if(rbUnidades.isSelected()){
                        oProductos.setCantidad(Integer.parseInt(txtCUnidades.getText()));
                    }else{
                        oProductos.setCantidad(Integer.parseInt(txtCKilogramos.getText()));
                    }
                    oProductos.setPrecio(txtPrecio.getText());
                    oProductos.setIdProducto(Integer.parseInt(txtId.getText()));
                    oProductos.setFechaCaducidad(txtFecha.getText());
                    oProductos.setProveedor(cbProveedor.getSelectedItem().toString());
                    oProductos.InsertarPrecioDeNuevo();
                    oProductos.InsertarNuevo();
                    JOptionPane.showMessageDialog(null, "Correcto");
                    Limpiar();
                    Mostrar();
                } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
                    System.err.println(e);
                }
            }else if(rbEscribir2.isSelected()&&rbEscribir1.isSelected()){
                //Nuevo producto de nueva categoria
                try {
                    oProductos.setCategoria(txtCategoria.getText());
                    oProductos.setProducto(txtProducto.getText());
                    if(rbUnidades.isSelected()){
                        oProductos.setCantidad(Integer.parseInt(txtCUnidades.getText()));
                    }else{
                        oProductos.setCantidad(Integer.parseInt(txtCKilogramos.getText()));
                    }
                    oProductos.setPrecio(txtPrecio.getText());
                    oProductos.setIdProducto(Integer.parseInt(txtId.getText()));
                    oProductos.setFechaCaducidad(txtFecha.getText());
                    oProductos.setProveedor(cbProveedor.getSelectedItem().toString());
                    oProductos.InsertarPrecioDeNuevo();
                    oProductos.InsertarNuevo();
                    JOptionPane.showMessageDialog(null, "Correcto");
                    Limpiar();
                    Mostrar();
                } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
                    System.err.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnRProductoMouseClicked

    private void txtPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrecioMouseClicked
        if((rbElegir1.isSelected()||rbEscribir1.isSelected())&&(rbElegir2.isSelected()
                ||rbEscribir2.isSelected())&&(rbUnidades.isSelected()||rbKilos.isSelected())){
            txtPrecio.setEnabled(true);
        }
    }//GEN-LAST:event_txtPrecioMouseClicked

    private void txtFechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaMouseClicked
        if((rbElegir1.isSelected()||rbEscribir1.isSelected())&&(rbElegir2.isSelected()
                ||rbEscribir2.isSelected())&&(rbUnidades.isSelected()||rbKilos.isSelected())){
            txtFecha.setEnabled(true);
        }
    }//GEN-LAST:event_txtFechaMouseClicked

    private void txtCUnidadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCUnidadesKeyTyped
        char dig = evt.getKeyChar();
        if(((dig < '0') || (dig > '9')) && (dig != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtCUnidadesKeyTyped

    private void txtCKilogramosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCKilogramosKeyTyped
        char dig = evt.getKeyChar();
        if(((dig < '0') || (dig > '9')) && (dig != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtCKilogramosKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char dig = evt.getKeyChar();
        if(((dig < '0') || (dig > '9')) && (dig != '\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped
    public final void MostrarProveedores(){
        try {
            rsProveedores = oProductos.ListarProveedores();
             while (rsProveedores.next()) {  
                cbProveedor.addItem(rsProveedores.getString("Razon_Social"));
            }
            cbProveedor.setSelectedItem(null);
          }
        catch (SQLException|ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
    public final void MostrarCategorias(){
        try {
            rsCategorias = oProductos.ListarCategorias();
             while (rsCategorias.next()) {  
                cbCategoria.addItem(rsCategorias.getString("Categoria"));
            }
            cbCategoria.setSelectedItem(null);
          }
        catch (SQLException|ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
    public final void MostrarProductos(){
        try {
            rsProductos = oProductos.ListarProductos();
            if(rsProductos.next()){ //Verificar si está vacío, pero desplaza el puntero al siguiente elemento
                //rsProductos.beforeFirst(); Esto regresa el puntero al inicio para no perder el 1er dato
                do {  
                    cbProducto.addItem(rsProductos.getString("Nombre"));
                }while(rsProductos.next());
                cbProducto.setSelectedItem(null);
            }else{
                JOptionPane.showMessageDialog(null, "No hay productos en esta categoría.");
            }
        }catch (SQLException|ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    public void ContinuarRegistro(){
        oProductos.setPrecio(txtPrecio.getText());
        oProductos.setIdProducto(Integer.parseInt(txtId.getText()));
        oProductos.setFechaCaducidad(txtFecha.getText());
        oProductos.setProveedor(cbProveedor.getSelectedItem().toString());
    }
    
    public boolean Validar(){
        
        boolean v = true;
        
        if(!(txtPrecio.isEnabled())||!(txtFecha.isEnabled())
                ||(cbProveedor.getSelectedIndex()==-1)||(txtId.isEnabled()&&txtId.getText().isEmpty())
                ||txtPrecio.getText().isEmpty()||txtFecha.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Faltan datos.");
            v = false;
        }
        return v;
        
    }
    
    public final void Close(){
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                Toolkit.getDefaultToolkit().beep();
                String b[] = {"Aceptar","Cancelar"};
                int e = JOptionPane.showOptionDialog(null,
                    " ¿Salir?"," Confirmar", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,icono("/Resources/den.png",30,30), 
                    b, b[1]);
                if(e==0){
                    frmAdministrador oFA = new frmAdministrador();
                    oFA.setVisible(true);
                    dispose(); 
                }
            }  
        }); 
    }
    
    private void Opciones(){
        JMenuItem upd = new JMenuItem("Modificar");
        JMenuItem dlt = new JMenuItem("Eliminar");
        JMenuItem inf = new JMenuItem("Ver información del producto");
        
        pmOpciones.add(upd);
        pmOpciones.add(dlt);
        pmOpciones.add(inf);
        
        jtbProductos.setComponentPopupMenu(pmOpciones);
        
        upd.addActionListener((ActionEvent e) -> {
            int fila = jtbProductos.getSelectedRow();
            if(fila!=-1){
                try {
                    rbEscribir1.setEnabled(false);
                    rbEscribir2.setEnabled(false);
                    rbElegir1.setEnabled(false);
                    txtId.setEnabled(false);
                    txtPrecio.setEnabled(false);
                    txtFecha.setEnabled(false);
                    
                    
                    int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de productos salientes:"));
                   
                    oProductos.setProducto(jtbProductos.getValueAt(fila, 1).toString());
                    rsCantidad = oProductos.ObtCant();
                    while(rsCantidad.next()){
                        int stock = rsCantidad.getInt("Stock");
                        if(rbUnidades.isSelected()){
                            oProductos.setCantidad(stock-cant);
                        }else{
                            oProductos.setCantidad(stock-cant);
                        }
                        oProductos.AgStock();
                    }
                    JOptionPane.showMessageDialog(null, "Correcto");
                    Limpiar();
                    Mostrar();
                } catch (ClassNotFoundException | SQLException err) {
                    System.err.println(err.getMessage());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "El valor ingresado no es un número");
                    System.err.println(ex.getMessage());
                }
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Seleccione una fila primero.");
            }
        });
        dlt.addActionListener((ActionEvent e) -> {
            int fila = jtbProductos.getSelectedRow();
            if(fila!=-1){               
                Toolkit.getDefaultToolkit().beep();
                String opciones[] ={"Sí","No"};
                int s = JOptionPane.showOptionDialog(
                        null,
                        "¿Está seguro de eliminar?",
                        " Confirmación",
                        JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE,null,opciones,this);
                if(s==0){
                    try {
                        String id = jtbProductos.getValueAt(fila, 0).toString();
                        String pd = jtbProductos.getValueAt(fila, 1).toString();
                        oProductos.setIdProducto(Integer.parseInt(id));
                        oProductos.setProducto(pd);
                        oProductos.Eliminar();
                        JOptionPane.showMessageDialog(null, "Producto eliminado.");
                        Limpiar();
                        Mostrar();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Error al eliminar");
                        System.err.println(ex);
                    }
                }
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Seleccione una fila primero.");
            }
        });
        inf.addActionListener((ActionEvent e) -> {
            int fila = jtbProductos.getSelectedRow();
            if(fila!=-1){               
                infoProductos ip = new infoProductos();
                ip.setVisible(true);
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Seleccione una fila primero.");
            }
        });
    }
    
    private void Limpiar(){
        bGrupo1.clearSelection();
        bGrupo2.clearSelection();
        bGrupo3.clearSelection();
        rbElegir2.setEnabled(false);
        rbUnidades.setEnabled(false);
        rbKilos.setEnabled(false);
        txtProducto.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtId.setEnabled(false);
        txtCKilogramos.setEnabled(false);
        txtFecha.setEnabled(false);
        cbProducto.setEnabled(false);
        txtCategoria.setText(null);
        txtProducto.setText(null);
        txtCUnidades.setText(null);
        txtCKilogramos.setText(null);
        txtPrecio.setText(null);
        txtId.setText(null);
        txtFecha.setText(null);
        cbCategoria.setSelectedIndex(-1);
        cbProducto.setSelectedIndex(-1);
        cbProveedor.setSelectedIndex(-1);
    }
    
    private boolean CompararId(){
        //En caso no quiera usar id incrementable
        int fila = jtbProductos.getRowCount();
        int i;
        boolean b = true;
        String[] valores = new String[fila];
        for (i=0; i < fila;i++) {
            valores [i] = jtbProductos.getValueAt(i, 0).toString();
            if(valores[i].equals(txtId.getText())){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"El ID ya está en uso.");
                b = false;
                i=fila;
            }
        } 
        return b;
    }
    
    public Icon icono(String path, int width, int height){
        //Método para obtener atributos de la imagen como "Icon".
        Icon ic = new ImageIcon(new ImageIcon(this.getClass().getResource(path))
        .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return ic;
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAdministradorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAdministradorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAdministradorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAdministradorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmAdministradorProductos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGrupo1;
    private javax.swing.ButtonGroup bGrupo2;
    private javax.swing.ButtonGroup bGrupo3;
    private javax.swing.JButton btnRCategoria;
    private javax.swing.JButton btnRProducto;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbProducto;
    private javax.swing.JComboBox<String> cbProveedor;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jtbProductos;
    private javax.swing.JPopupMenu pmOpciones;
    private javax.swing.JRadioButton rbElegir1;
    private javax.swing.JRadioButton rbElegir2;
    private javax.swing.JRadioButton rbEscribir1;
    private javax.swing.JRadioButton rbEscribir2;
    private javax.swing.JRadioButton rbKilos;
    private javax.swing.JRadioButton rbUnidades;
    private javax.swing.JTextField txtCKilogramos;
    private javax.swing.JTextField txtCUnidades;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
