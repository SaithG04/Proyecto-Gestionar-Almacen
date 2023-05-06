package Formularios;

import Clases.cUsuarioProveedores;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class frmUsuarioProveedores extends javax.swing.JFrame {


    cUsuarioProveedores oUP = new cUsuarioProveedores();
    ResultSet rsUP,rsColumnas;
    DefaultTableModel modelo;
    
    public frmUsuarioProveedores() {
        initComponents();
        this.setLocationRelativeTo(null);
        MostrarColumnas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        cboColumnas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProveedores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" Usuario - Proveedores");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrarSesion.setBackground(new java.awt.Color(0, 51, 102));
        btnCerrarSesion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("CERRAR SESION");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });
        jPanel2.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 240, 60));

        btnBuscar.setBackground(new java.awt.Color(0, 51, 102));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 110, -1));

        txtBusqueda.setBackground(new java.awt.Color(0, 51, 102));
        txtBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 310, 34));

        cboColumnas.setBackground(new java.awt.Color(0, 51, 102));
        cboColumnas.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(cboColumnas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 168, 34));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscar por:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 106, 34));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Copperplate Gothic Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jtbProveedores.setBackground(new java.awt.Color(0, 102, 153));
        jtbProveedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtbProveedores.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jtbProveedores.setForeground(new java.awt.Color(255, 255, 255));
        jtbProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdProveedor", "Razon Social", "R.U.C.", "Direccion", "Contacto", "Teléfono", "Email", "Departamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbProveedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtbProveedores.setSelectionBackground(new java.awt.Color(153, 255, 153));
        jtbProveedores.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jtbProveedores);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 1130, 410));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 590));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        if(Validar()==true){
            oUP.setCategoria(txtBusqueda.getText());
            oUP.setColumna(cboColumnas.getSelectedItem().toString());
            Mostrar();
        }       
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        Toolkit.getDefaultToolkit().beep();
        String b[] = {"Salir","Cancelar"};
        int e = JOptionPane.showOptionDialog(null,
            " ¿Cerrar sesión?"," Confirmar", JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,icono("/Resources/den.png",30,30), 
            b, b[1]);
        if(e==0){
            dispose();
            frmLogueo oFLogueo = new frmLogueo();
            oFLogueo.setVisible(true);
        }
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void Mostrar() {
        try {            
            rsUP = oUP.Buscar();
           
            if(rsUP.next()){
                Object[] proveedores = new Object[8];          
                modelo = (DefaultTableModel) jtbProveedores.getModel(); //modelo Creamos un Objeto de la clase Default...

                modelo.setRowCount(0);

                do{
                   proveedores[0] = rsUP.getInt("IdProveedor");
                   proveedores[1] = rsUP.getString("Razon_Social");
                   proveedores[2] = rsUP.getString("RUC");
                   proveedores[3] = rsUP.getString("Direccion");
                   proveedores[4] = rsUP.getString("Contacto");
                   proveedores[5] = rsUP.getString("Telefono");
                   proveedores[6] = rsUP.getString("Email");
                   proveedores[7] = rsUP.getString("Departamento");
                   modelo.addRow(proveedores); //Va aderiendo en nuestro DefaultTableModel "modelo"
                }while(rsUP.next());
                jtbProveedores.setModel(modelo); //aqui va enviar los datos de "modelo" a nuestra jtbCliente  
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "No existen coincidencias.");
            }
        }
        catch (SQLException |ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    public final void MostrarColumnas(){
        try {
            rsColumnas = oUP.ListarColumnas();      
             while (rsColumnas.next()) {  
                cboColumnas.addItem(rsColumnas.getString("COLUMN_NAME"));
            }
            cboColumnas.setSelectedItem(null);
        }
        catch (SQLException |ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    private boolean Validar(){

        boolean v = true;
        
        if(txtBusqueda.getText().isEmpty()){           
            Toolkit.getDefaultToolkit().beep();
            txtBusqueda.requestFocus();
            v = false;
        }else if(cboColumnas.getSelectedIndex()==-1){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Seleccione una categoría.");
            v = false;
        }
        return v;
    } 
    
    public Icon icono(String path, int width, int height){
        //Método para obtener atributos de la imagen como "Icon".
        Icon ic = new ImageIcon(new ImageIcon(this.getClass().getResource(path))
        .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return ic;
    }
    
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
            java.util.logging.Logger.getLogger(frmUsuarioProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUsuarioProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUsuarioProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUsuarioProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmUsuarioProveedores().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JComboBox<String> cboColumnas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProveedores;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}