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
public class mRegistroUsuarios extends mGenerales {

    private final cAlertas oA = new cAlertas();
    private cUsuarios oU;
    private Connection con;
    private Statement st;
    private PreparedStatement psInsertar;

    public final JTextField id, nombres, apellidos, telefono, correo, usuario;
    public final JPasswordField contraseña;
    public final JRadioButton user, admin;
    public final JButton aceptar;
    public final ButtonGroup grupo;
    public final frmRegistroUsuarios fru;

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
                        oU = new cUsuarios(0, nombres.getText(), apellidos.getText(), usuario.getText(),
                                String.valueOf(contraseña.getPassword()), "administrador", telefono.getText(), correo.getText());
                        RegistrarUser();
                        Registrar(oU, mGenerales.CREARUSER);
                        Limpiar();
                        oA.aviso("Correcto");
                    } catch (ClassNotFoundException | SQLException ex) {
                        oA.error("Incorrecto", ex.toString());
                    }
                } else {
                    try {
                        oU = new cUsuarios(0, nombres.getText(), apellidos.getText(), usuario.getText(),
                                String.valueOf(contraseña.getPassword()), "usuario", telefono.getText(), correo.getText());
                        RegistrarUser();
                        Registrar(oU, mGenerales.CREARUSER);
                        Limpiar();
                        oA.aviso("Correcto");
                    } catch (ClassNotFoundException | SQLException ex) {
                        oA.error("Incorrecto", ex.toString());
                    }
                }
            } else {
                if (admin.isSelected()) {
                    if (usuario.getText().equals(mLogueo.oL.getUsuario())) {
                        try {
                            oU = new cUsuarios(Integer.parseInt(id.getText()), nombres.getText(), apellidos.getText(), usuario.getText(),
                                    String.valueOf(contraseña.getPassword()), "administrador", telefono.getText(), correo.getText());
                            JOptionPane.showMessageDialog(null, "Inicie sesión nuevamente");
                            Actualizar(oU.getTipoUsuario(), mGenerales.ACTUALIZARUSER);
                            ModificarAdmin();
                            fru.dispose();
                            new mLogueo().CargarFrame();
                        } catch (ClassNotFoundException | SQLException ex) {
                            oA.error("Incorrecto", ex.toString());
                        }
                    } else {
                        oU = new cUsuarios(Integer.parseInt(id.getText()), nombres.getText(), apellidos.getText(), usuario.getText(),
                                String.valueOf(contraseña.getPassword()), "administrador", telefono.getText(), correo.getText());
                        oA.aviso("Correcto");
                        fru.dispose();
                        new mGestionarUsuarios().CargarFrame();
                    }
                } else if (user.isSelected()) {
                    try {
                        oU = new cUsuarios(Integer.parseInt(id.getText()), nombres.getText(), apellidos.getText(),
                                usuario.getText(), String.valueOf(contraseña.getPassword()),
                                "usuario", telefono.getText(), correo.getText());
                        ModificarUser();
                        Actualizar(oU.getTipoUsuario(), mGenerales.ACTUALIZARUSER);
                        oA.aviso("Correcto");
                        fru.dispose();
                        new mGestionarUsuarios().CargarFrame();
                    } catch (ClassNotFoundException | SQLException ex) {
                        oA.error("Incorrecto", ex.toString());
                    }
                }
            }
        }
    }

    public boolean Validar() {

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
            oA.error("Número telefónico inválido.", "");
            telefono.requestFocus();
            return false;
        } else if (String.valueOf(contraseña.getPassword()).length() < 4) {
            nombres.setBackground(null);
            apellidos.setBackground(null);
            correo.setBackground(null);
            telefono.setBackground(null);
            usuario.setBackground(null);
            contraseña.setBackground(mGenerales.COLORERROR);
            oA.error("Contraseña demasiado corta.", "");
            contraseña.requestFocus();
            return false;
        } else if (!(admin.isSelected()) && !(user.isSelected())) {
            nombres.setBackground(null);
            apellidos.setBackground(null);
            correo.setBackground(null);
            telefono.setBackground(null);
            usuario.setBackground(null);
            contraseña.setBackground(null);
            oA.error("Seleccione una opción.", "");
            user.requestFocus();
            return false;
        }
        return true;
    }

    public boolean IfNotEmpty() {
        return nombres.getText().isEmpty() || apellidos.getText().isEmpty() || telefono.getText().isEmpty() || correo.getText().isEmpty()
                || usuario.getText().isEmpty() || String.valueOf(contraseña.getPassword()).isEmpty()
                || (!user.isSelected() && !admin.isSelected());
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

    public void RegistrarUser() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("CREATE USER '" + usuario.getText() + "'@'%' IDENTIFIED BY '" + String.valueOf(contraseña.getPassword()) + "'");
        st.execute("GRANT SELECT ON * . * TO '" + usuario.getText() + "'@'%'");
        /* 
        % - Conexion Remota.
        localhost - Conexion Local.
         */
    }

    public void RegistrarAdmin() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("CREATE USER '" + usuario.getText() + "'@'%' IDENTIFIED BY '" + String.valueOf(contraseña.getPassword()) + "'");
        st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario.getText() + "'@'%'");
        st.execute("GRANT GRANT OPTION ON * . * TO '" + usuario.getText() + "'@'%'");
    }

    public void ModificarUser() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("REVOKE ALL PRIVILEGES ON * . * FROM '" + usuario.getText() + "'@'%'");
        st.execute("GRANT SELECT ON * . * TO '" + usuario.getText() + "'@'%'");
        st.execute("SET PASSWORD FOR '" + usuario.getText() + "'@'%' = PASSWORD('" + String.valueOf(contraseña.getPassword()) + "')");
    }

    public void ModificarAdmin() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario.getText() + "'@'%'");
        st.execute("GRANT GRANT OPTION ON * . * TO '" + usuario.getText() + "'@'%'");
        st.execute("SET PASSWORD FOR '" + usuario.getText() + "'@'%' = PASSWORD('" + String.valueOf(contraseña.getPassword()) + "')");

    }

    public void Registrar(cUsuarios usuario, String consulta) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement(consulta);
        psInsertar.setInt(1, usuario.getIdUsuario());
        psInsertar.setString(2, usuario.getNombres());
        psInsertar.setString(3, usuario.getApellidos());
        psInsertar.setString(4, usuario.getUsuario());
        psInsertar.setString(5, usuario.getContraseña());
        psInsertar.setString(6, usuario.getTipoUsuario());
        psInsertar.setString(7, usuario.getTelefono());
        psInsertar.setString(8, usuario.getCorreo());
        psInsertar.executeUpdate();
    }

    public void Actualizar(String tipoUsuario, String consulta) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement(consulta);
        psInsertar.setString(1, nombres.getText());
        psInsertar.setString(2, apellidos.getText());
        psInsertar.setString(3, usuario.getText());
        psInsertar.setString(4, String.valueOf(contraseña.getPassword()));
        psInsertar.setString(5, tipoUsuario);
        psInsertar.setString(6, telefono.getText());
        psInsertar.setString(7, correo.getText());
        psInsertar.setInt(8, Integer.parseInt(id.getText()));
        psInsertar.executeUpdate();
    }

    @Override
    public void CargarFrame() {
        fru.setVisible(true);
        Close();
        btnClick();
        ClickEnter();
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

    public final void ClickEnter() {
        contraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { //Tecla ENTER
                    Aceptar();
                }
            }
        });
    }

    @Override
    public void Close() {
        fru.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {

                if (IfNotEmpty()) {
                    if (aceptar.getText().equalsIgnoreCase("registrar")) {
                        oA.confCerrar(fru, "administrador");
                    } else {
                        oA.confCerrar(fru, "gestUsuarios");
                    }
                } else {
                    if (aceptar.getText().equalsIgnoreCase("registrar")) {
                        oA.confCerrar(fru, "administrador");
                    } else {
                        oA.confCerrar(fru, "gestUsuarios");
                    }
                }
            }
        });
    }
}
