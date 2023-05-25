package Controller;

import View.frmRegistroUsuarios;
import Model.cAdministradorUsuarios;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mRegistroUsuarios extends mGenerales {

    private cAdministradorUsuarios oU;

    final JTextField id, nombres, apellidos, telefono, correo, usuario;
    final JPasswordField contraseña;
    final JRadioButton user, admin;
    final JButton aceptar;
    final ButtonGroup grupo;
    final frmRegistroUsuarios fru;
    final Class clase = mRegistroUsuarios.class;

    public mRegistroUsuarios() {
        fru = new frmRegistroUsuarios();
        id = fru.getTxtId();
        nombres = fru.getTxtNombres();
        apellidos = fru.getTxtApellidos();
        telefono = fru.getTxtTelefono();
        correo = fru.getTxtCorreo();
        usuario = fru.getTxtUsuario();
        contraseña = fru.getTxtContraseña();
        user = fru.getRbUser();
        admin = fru.getRbAdmin();
        aceptar = fru.getBtnAceptar();
        grupo = fru.getbGrupo();
    }

    public void Aceptar() {
        if (Validar()) {
            if (aceptar.getText().equalsIgnoreCase("registrar")) {
                if (admin.isSelected()) {
                    try {
                        java.sql.Timestamp fechaR = new java.sql.Timestamp(System.currentTimeMillis());
                        oU = new cAdministradorUsuarios(nombres.getText(), apellidos.getText(), usuario.getText(),
                                String.valueOf(contraseña.getPassword()), "administrador",
                                telefono.getText(), correo.getText(), mLogueo.oL.getUsuario(), fechaR);
                        oU.RegistrarAdmin(usuario.getText(), String.valueOf(contraseña.getPassword()));
                        oU.Registrar(CREARUSER);
                        Limpiar();
                        oA.aviso("Registro exitoso");
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(mRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        java.sql.Timestamp fechaR = new java.sql.Timestamp(System.currentTimeMillis());
                        oU = new cAdministradorUsuarios(nombres.getText(), apellidos.getText(), usuario.getText(),
                                String.valueOf(contraseña.getPassword()), "usuario",
                                telefono.getText(), correo.getText(), mLogueo.oL.getUsuario(), fechaR);
                        oU.RegistrarUser(usuario.getText(), String.valueOf(contraseña.getPassword()));
                        oU.Registrar(CREARUSER);
                        Limpiar();
                        oA.aviso("Registro exitoso");
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(mRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else { //MODIFICANDO
                if (admin.isSelected()) {
                    if (usuario.getText().equals(mLogueo.oL.getUsuario())) {
                        try {
                            oU = new cAdministradorUsuarios(Integer.parseInt(id.getText()), nombres.getText(), apellidos.getText(), usuario.getText(),
                                    String.valueOf(contraseña.getPassword()), "administrador", telefono.getText(), correo.getText());
                            oU.Actualizar(mGenerales.ACTUALIZARUSER);
                            oU.ModificarAdmin(usuario.getText(), String.valueOf(contraseña.getPassword()));
                            oA.aviso("Inicie sesión nuevamente");
                            fru.dispose();
                            new mLogueo().CargarFrame();
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(mRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            oU = new cAdministradorUsuarios(Integer.parseInt(id.getText()), nombres.getText(), apellidos.getText(), usuario.getText(),
                                    String.valueOf(contraseña.getPassword()), "administrador", telefono.getText(), correo.getText());
                            oU.Actualizar(mGenerales.ACTUALIZARUSER);
                            oU.ModificarAdmin(usuario.getText(), String.valueOf(contraseña.getPassword()));
                            oA.aviso("Usuario modificado correctamente.");
                            fru.dispose();
                            new mGestionarUsuarios().CargarFrame();
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(mRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    try {
                        //if (user.isSelected())
                        oU = new cAdministradorUsuarios(Integer.parseInt(id.getText()), nombres.getText(), apellidos.getText(),
                                usuario.getText(), String.valueOf(contraseña.getPassword()),
                                "usuario", telefono.getText(), correo.getText());                    
                        oU.Actualizar(mGenerales.ACTUALIZARUSER);
                        oU.ModificarUser(usuario.getText(), String.valueOf(contraseña.getPassword()));
                        oA.aviso("Usuario modificado correctamente.");
                        fru.dispose();
                        new mGestionarUsuarios().CargarFrame();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(mRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public boolean Validar() {
        //AQUI
        if (nombres.getText().isEmpty() || apellidos.getText().isEmpty() || telefono.getText().isEmpty() || correo.getText().isEmpty()
                || usuario.getText().isEmpty() || String.valueOf(contraseña.getPassword()).isEmpty()) {

            nombres.setBackground(mGenerales.COLORERROR);
            apellidos.setBackground(mGenerales.COLORERROR);
            telefono.setBackground(mGenerales.COLORERROR);
            correo.setBackground(mGenerales.COLORERROR);
            usuario.setBackground(mGenerales.COLORERROR);
            contraseña.setBackground(mGenerales.COLORERROR);

            if (!String.valueOf(contraseña.getPassword()).isEmpty()) {
                contraseña.setBackground(null);
            } else {
                contraseña.requestFocus();
                return false;
            }

            if (!usuario.getText().isEmpty()) {
                usuario.setBackground(null);
            } else {
                usuario.requestFocus();
                return false;
            }

            if (!correo.getText().isEmpty()) {
                correo.setBackground(null);
            } else {
                correo.requestFocus();
                return false;
            }

            if (!telefono.getText().isEmpty()) {
                telefono.setBackground(null);
            } else {
                telefono.requestFocus();
                return false;
            }

            if (!apellidos.getText().isEmpty()) {
                apellidos.setBackground(null);
            } else {
                apellidos.requestFocus();
                return false;
            }

            if (!nombres.getText().isEmpty()) {
                nombres.setBackground(null);
            } else {
                nombres.requestFocus();
                return false;
            }

        } else if (telefono.getText().length() < 9) {
            nombres.setBackground(null);
            apellidos.setBackground(null);
            correo.setBackground(null);
            usuario.setBackground(null);
            contraseña.setBackground(null);
            telefono.setBackground(mGenerales.COLORERROR);
            oA.mostrarError(clase, "Número telefónico inválido.", null);
            telefono.requestFocus();
            return false;
        } else if (String.valueOf(contraseña.getPassword()).length() < 4) {
            nombres.setBackground(null);
            apellidos.setBackground(null);
            correo.setBackground(null);
            telefono.setBackground(null);
            usuario.setBackground(null);
            contraseña.setBackground(mGenerales.COLORERROR);
            oA.mostrarError(clase, "Contraseña demasiado corta.", null);
            contraseña.requestFocus();
            return false;
        } else if (!(admin.isSelected()) && !(user.isSelected())) {
            nombres.setBackground(null);
            apellidos.setBackground(null);
            correo.setBackground(null);
            telefono.setBackground(null);
            usuario.setBackground(null);
            contraseña.setBackground(null);
            oA.mostrarError(clase, "Seleccione una opción.", null);
            user.requestFocus();
            return false;
        }
        return true;
    }

    public void Limpiar() {
        id.setText(null);
        nombres.setText(null);
        apellidos.setText(null);
        telefono.setText(null);
        correo.setText(null);
        usuario.setText(null);
        contraseña.setText(null);
        grupo.clearSelection();
        nombres.requestFocus();
        id.setBackground(null);
        apellidos.setBackground(null);
        nombres.setBackground(null);
        correo.setBackground(null);
        telefono.setBackground(null);
        usuario.setBackground(null);
        contraseña.setBackground(null);

    }

    @Override
    public void CargarFrame() {
        fru.setVisible(true);
        Close();
        btnClick();
        KeyListeners();
        id.setEditable(false);
        fru.setLocationRelativeTo(null);
    }

    public void btnClick() {
        aceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Aceptar();
            }
        });
    }

    public final void KeyListeners() {
        contraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { //Tecla ENTER
                    Aceptar();
                }
            }
        });
        telefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarSoloNumeros(e, telefono.getText(), (short) 9);
            }
        });
        nombres.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ValidarSoloLetras(e, nombres.getText(), (short) 150);
            }
        });
        apellidos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ValidarSoloLetras(e, apellidos.getText(), (short) 150);
            }
        });
    }

    @Override
    public void Close() {
        fru.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {

                if (aceptar.getText().equalsIgnoreCase("registrar")) {
                    if (oA.confirmación("¿Salir?") == 0) {
                        fru.dispose();
                        new mAdministrador().CargarFrame();
                    }
                } else {
                    if (oA.confirmación("¿Salir?") == 0) {
                        fru.dispose();
                        new mGestionarUsuarios().CargarFrame();
                    }
                }

            }
        });
    }
}
