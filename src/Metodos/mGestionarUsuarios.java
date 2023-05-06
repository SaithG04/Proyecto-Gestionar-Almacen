package Metodos;

import Clases.cAlertas;
import Formularios.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mGestionarUsuarios extends mGenerales {

    private final cAlertas oA = new cAlertas();
    private Connection con;
    private Statement st;
    private ResultSet rs, rsUsuarios;
    private DefaultTableModel modelo;
    private mRegistroUsuarios ru;
    private final frmAdministradorGestionarUsuarios fagu;
    private final JTable usuarios;
    private final JPopupMenu pmOpciones;

    public mGestionarUsuarios() {
        ru = null;
        fagu = new frmAdministradorGestionarUsuarios();
        usuarios = fagu.getJtbUsuarios();
        pmOpciones = fagu.getPmOpciones();
    }

    public void dobleClick() {
        usuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String dato = usuarios.getValueAt(usuarios.getSelectedRow(), usuarios.getSelectedColumn()).toString();
                    if (usuarios.getSelectedRow() != -1) {
                        JOptionPane.showMessageDialog(null, dato);
                    }
                }
            }
        });
    }

    public ResultSet ListaUsuarios() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from usuarios");

        return rs;
    }

    public void EliminarUsuario() throws ClassNotFoundException, SQLException {
        String user = usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString();
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("DROP USER '" + user + "'@'%'");
        st.executeUpdate("DELETE FROM usuarios WHERE username='" + user + "'");
    }

    public void Modificar() {
        switch (usuarios.getValueAt(usuarios.getSelectedRow(), 5).toString()) {
            case "administrador":
                ru.admin.setSelected(true);
                ru.usuario.setEditable(false);
                LlenarCampos();
                break;
            case "usuario":
                ru.user.setSelected(true);
                ru.usuario.setEditable(false);
                LlenarCampos();
                break;
        }
    }

    public void LlenarCampos() {
        int fila = usuarios.getSelectedRow();
        ru.id.setText(usuarios.getValueAt(fila, 0).toString());
        ru.nombres.setText(usuarios.getValueAt(fila, 1).toString());
        ru.apellidos.setText(usuarios.getValueAt(fila, 2).toString());
        ru.telefono.setText(usuarios.getValueAt(fila, 6).toString());
        ru.correo.setText(usuarios.getValueAt(fila, 7).toString());
        ru.usuario.setText(usuarios.getValueAt(fila, 3).toString());
        ru.contraseña.setText(null);
        ru.aceptar.setText("MODIFICAR");
        ru.fru.setTitle("Modificar usuarios");
    }

    public void Mostrar() {

        try {
            rsUsuarios = ListaUsuarios();
            Object[] usuariosArray = new Object[8];
            modelo = (DefaultTableModel) usuarios.getModel();

            modelo.setRowCount(0);
            while (rsUsuarios.next()) {
                usuariosArray[0] = rsUsuarios.getInt("id_usuario");
                usuariosArray[1] = rsUsuarios.getString("nombres");
                usuariosArray[2] = rsUsuarios.getString("apellidos");
                usuariosArray[3] = rsUsuarios.getString("username");
                usuariosArray[5] = rsUsuarios.getString("tipo_usuario");
                usuariosArray[6] = rsUsuarios.getLong("telefono");
                usuariosArray[7] = rsUsuarios.getString("correo");
                int length = rsUsuarios.getString("contraseña").length();
                usuariosArray[4] = "";
                for (int i = 0; i < length; i++) {
                    usuariosArray[4] = usuariosArray[4] + "*";
                }
                modelo.addRow(usuariosArray); //Va adheriendo en nuestro DefaultTableModel "modelo"
            }
            usuarios.setModel(modelo); //aqui va enviar los datos de "modelo" a nuestra jtbUsuarios
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.toString());
        }
    }

    public final void Opciones() {
        JMenuItem upd = new JMenuItem("Modificar");
        JMenuItem dlt = new JMenuItem("Eliminar");

        pmOpciones.add(upd);
        pmOpciones.add(dlt);

        usuarios.setComponentPopupMenu(pmOpciones);

        upd.addActionListener((ActionEvent e) -> {
            if (usuarios.getSelectedRow() != -1) {

                if (!usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString().equals(mLogueo.oL.getUsuario())) {
                    if (Conectado()) {                       
                        ru = new mRegistroUsuarios();
                        ru.CargarFrame();
                        Modificar();
                        fagu.dispose();
                    } else {
                        new mLogueo().CargarFrame();
                        fagu.dispose();
                    }
                } else {
                    if (Conectado()) {
                        ru = new mRegistroUsuarios();
                        ru.CargarFrame();
                        Modificar();
                        ru.usuario.setEditable(false);
                        ru.admin.setEnabled(false);
                        ru.user.setEnabled(false);
                        fagu.dispose();
                    } else {
                        new mLogueo().CargarFrame();
                        fagu.dispose();
                    }
                }
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });
        dlt.addActionListener((ActionEvent e) -> {
            if (usuarios.getSelectedRow() != -1) {
                if (!usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString().equals(mLogueo.oL.getUsuario())) {
                    if (Conectado()) {

                        if (oA.confCerrar("¿Está seguro de eliminar?") == 0) {
                            try {
                                EliminarUsuario();
                                oA.aviso("Usuario eliminado.");
                                Mostrar();
                            } catch (ClassNotFoundException | SQLException ex) {
                                oA.error("Error al eliminar", ex.toString());
                            }
                        }
                    } else {
                        new mLogueo().CargarFrame();
                        fagu.dispose();
                    }
                } else {
                    oA.error("Cierre sesión primero.", "");
                }
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });
    }

    private boolean Conectado() {

        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public final void Close() {
        fagu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                new mAdministrador().CargarFrame();
                fagu.dispose();
            }
        });
    }

    @Override
    public void CargarFrame() {
        fagu.setVisible(true);
        fagu.setLocationRelativeTo(null);
        Close();
        Mostrar();
        Opciones();
        dobleClick();
    }
}
