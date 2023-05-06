package Formularios;

import Clases.cInfoProductos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class infoProductos extends javax.swing.JFrame {
    
    cInfoProductos oInfo;
    DefaultTableModel modelo; //modelo de la clase default
    ResultSet rsProductos;
    
    public infoProductos() {
        initComponents();
        this.setLocationRelativeTo(null);
        oInfo = new cInfoProductos();
        modelo = new DefaultTableModel();
        Mostrar();
    }
    
    private void Mostrar(){
        try {
            int fila = frmAdministradorProductos.jtbProductos.getSelectedRow();
            String u = frmAdministradorProductos.jtbProductos.getValueAt(fila, 1).toString();
            oInfo.setProducto(u);
            rsProductos = oInfo.Lista();
            Object[] pro = new Object[3];          
            modelo = (DefaultTableModel) jtbProductos.getModel(); //modelo Creamos un Objeto de la clase Default...

            modelo.setRowCount(0);
            while (rsProductos.next())
            {
                pro[0] = rsProductos.getString("Proveedor");
                pro[1] = rsProductos.getString("Precio");
                pro[2] = rsProductos.getString("FechaCaducidad");

                modelo.addRow(pro); //Va aderiendo en nuestro DefaultTableModel "modelo"
            }
            jtbProductos.setModel(modelo); //aqui va enviar los datos de "modelo" a jtb 
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proveedor", "Precio", "Fecha de Caducidad"
            }
        ));
        jScrollPane1.setViewportView(jtbProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(infoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
                new infoProductos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProductos;
    // End of variables declaration//GEN-END:variables
}
