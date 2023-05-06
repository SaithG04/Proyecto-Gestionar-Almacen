package Metodos;

import Clases.*;
import Formularios.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mLogueo extends mGenerales implements ActionListener {

    public static cLogueo oL;
    private final cAlertas oAlerta = new cAlertas();
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
            oL = new cLogueo();
            String iniciarSesion = oL.iniciarSesion(user, contrasena);
            switch (iniciarSesion) {
                case "correctoAdmin":
                    oL = new cLogueo(user, contrasena);
                    logueo.dispose();
                    new mAdministrador().CargarFrame();
                    break;
                case "correctoUser":
                    oL = new cLogueo(user, contrasena);
                    logueo.dispose();
//                    new frmUsuarioProveedores().setVisible(true);
                    break;
                case " Contraseña incorrecta.\n Verifique nuevamente.":
                    txtContrasena.requestFocus();
                    txtContrasena.setText(null);
                    oAlerta.error(" Contraseña incorrecta.\n" + " Verifique nuevamente.", "");
                    break;
                case "El usuario no existe.":
                    txtUsuario.requestFocus();
                    txtContrasena.setText(null);
                    oAlerta.error("El usuario no existe.", "");
                    break;
                default:
                    txtUsuario.setText(null);
                    txtContrasena.setText(null);
                    txtUsuario.requestFocus();
                    oAlerta.errorC(iniciarSesion);
                    break;
            }
        }
    }

    @Override
    public final void Close() {
        logueo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }

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
        Close();
    }
}
