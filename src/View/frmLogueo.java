package View;

import Controller.mGenerales;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class frmLogueo extends javax.swing.JFrame {

    public frmLogueo() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
        //Icono del programa
        return mGenerales.IMG;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        btnRecuperar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        javax.swing.JPanel fondo = new javax.swing.JPanel();
        javax.swing.JLabel lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Acceder");
        setIconImage(getIconImage());
        setResizable(false);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Swis721 BlkEx BT", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INICIO DE SESION");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 53, 664, 90));

        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Hola, bienvenido(a):");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 177, -1, -1));

        jLabel5.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("USUARIO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 303, 120, 30));

        jLabel6.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("CONTRASEÑA:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 350, -1, 30));

        txtUsuario.setBackground(new java.awt.Color(102, 153, 255));
        txtUsuario.setFont(new java.awt.Font("Swis721 Ex BT", 0, 13)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 302, 208, 30));

        txtContraseña.setBackground(new java.awt.Color(102, 153, 255));
        txtContraseña.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 348, 208, 30));

        btnRecuperar.setBackground(new java.awt.Color(102, 153, 255));
        btnRecuperar.setFont(new java.awt.Font("Swis721 Ex BT", 1, 12)); // NOI18N
        btnRecuperar.setForeground(new java.awt.Color(0, 0, 0));
        btnRecuperar.setText("RECUPERAR CONTRASEÑA");
        btnRecuperar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 447, 246, 58));

        btnAceptar.setBackground(new java.awt.Color(102, 153, 255));
        btnAceptar.setFont(new java.awt.Font("Swis721 Ex BT", 1, 15)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(0, 0, 0));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 447, 155, 58));

        fondo.setPreferredSize(new java.awt.Dimension(829, 624));

        lbl_fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/IMG-20211202-WA0088.jpg"))); // NOI18N
        lbl_fondo.setOpaque(true);
        lbl_fondo.setPreferredSize(new java.awt.Dimension(829, 624));

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 1, -1, -1));

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

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnRecuperar() {
        return btnRecuperar;
    }

    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
