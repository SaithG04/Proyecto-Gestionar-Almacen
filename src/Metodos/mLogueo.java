package Metodos;

import Clases.*;
import Formularios.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mLogueo extends mGenerales implements ActionListener{

    public static cLogueo oL;
    private final cAlertas oAlerta = new cAlertas();
    private PreparedStatement st;
    private ResultSet rs;
    private final frmLogueo logueo;

    private final JButton btnAceptar, btnRecuperar;
    private final JPasswordField txtContrasena;
    private final JTextField txtUsuario;

    public mLogueo() {
        logueo = new frmLogueo();
        btnAceptar = logueo.getBtnAceptar();
        btnRecuperar = logueo.getBtnRecuperar();
        txtContrasena = logueo.getTxtContraseña();
        txtUsuario = logueo.getTxtUsuario();
    }

    public final void ClickEnter() {
        txtContrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { //Tecla ENTER
                    //Cambiar de frame en caso de inicio de sesión correcto
                    iniciarSesion();
                }
            }
        });
    }

    //Validar inicio de sesión en caso de existir usuario y contraseña
    public void iniciarSesion() {
        String user = txtUsuario.getText();
        String contrasena = String.valueOf(txtContrasena.getPassword());
        if (user.isEmpty() || contrasena.isEmpty()) {  //Validar campos vacíos         
            txtUsuario.requestFocus();
            txtContrasena.setText(null);
            oAlerta.faltanDatos();
        } else {
            try {
                Connection con = Conectar();
                st = con.prepareStatement("Select username from usuarios where username= '" + user + "'");
                rs = st.executeQuery();
                if (rs.next()) { //Validar usuario
                    st = con.prepareStatement("Select contraseña from usuarios where username= '" + user + "' and contraseña= '" + contrasena + "'");
                    rs = st.executeQuery();
                    if (rs.next()) { //Validar contraseña
                        st = con.prepareStatement("Select tipo_usuario from usuarios where username = '" + user + "'");
                        rs = st.executeQuery();
                        while (rs.next()) { //Verificar permisos de cuenta
                            String tu = rs.getString("tipo_usuario");
                            if (tu.equals("administrador")) {
                                oL = new cLogueo(user, contrasena);                            
                                logueo.dispose();
                                new mAdministrador().CargarFrame();
                            } else {
                                oL = new cLogueo(user, contrasena);                               
                                logueo.dispose();
//                                new frmUsuarioProveedores().setVisible(true);
                            }
                        }
                    } else {
                        txtContrasena.requestFocus();
                        txtContrasena.setText(null);
                        oAlerta.error(" Contraseña incorrecta.\n" + " Verifique nuevamente.", "");
                    }
                } else {
                    txtUsuario.requestFocus();
                    txtContrasena.setText(null);
                    oAlerta.error("El usuario no existe.", "");
                }
            } catch (ClassNotFoundException | SQLException ex) { //Error de conexión u otro
                txtUsuario.setText(null);
                txtContrasena.setText(null);
                txtUsuario.requestFocus();
                oAlerta.errorC(ex.toString());
            }
        }
    }

    @Override
    public final void Close() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            iniciarSesion();
        }
    }

    @Override
    public void CargarFrame() {
        logueo.setVisible(true);
        logueo.setLocationRelativeTo(null);
        btnAceptar.addActionListener(this);
        ClickEnter();
    }
}
