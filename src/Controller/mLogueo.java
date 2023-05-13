package Controller;

import View.frmLogueo;
import Model.cLogueo;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mLogueo extends mGenerales implements ActionListener {

    public static cLogueo oL;
    private final frmLogueo logueo;
    private int intentos = 0;

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
            oA.faltanDatos();
        } else {
            intentos++;
            oL = new cLogueo();
            String iniciarSesion = oL.iniciarSesion(user, contrasena);
//             else {
            switch (iniciarSesion) {
                case "correctoAdmin":
                    oL = new cLogueo(user, contrasena);
                    logueo.dispose();
                    new mAdministrador().CargarFrame();
                    intentos = 0;
                    break;
                case "correctoUser":
                    oL = new cLogueo(user, contrasena);
                    logueo.dispose();
                    intentos = 0;
//                    new frmUsuarioProveedores().setVisible(true);
                    break;
                case "contraIncorrect":
                    txtContrasena.requestFocus();
                    txtContrasena.setText(null);
                    oA.mostrarError(mLogueo.class, " Contraseña incorrecta.\n" + " Verifique nuevamente.", null);
                    break;
                case "userNoExist":
                    txtUsuario.requestFocus();
                    txtContrasena.setText(null);
                    oA.mostrarError(mLogueo.class, "El usuario no existe.", null);
                    break;
                default:
                    txtUsuario.setText(null);
                    txtContrasena.setText(null);
                    txtUsuario.requestFocus();
                    oA.mostrarError(mLogueo.class, iniciarSesion, null);
//                        oAlerta.errorC(iniciarSesion);
                    break;
//                }

            }
            if (intentos == 3) { //3 INTENTOS PERMITIDOS}
                oA.mostrarError(mLogueo.class, "Límite de intentos superados.", null);
                System.exit(0);
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
