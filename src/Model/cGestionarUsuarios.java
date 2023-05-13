package Model;

import Controller.mLogueo;
import java.sql.*;
import javax.swing.table.*;

/**
 *
 * @author isai_
 */
public class cGestionarUsuarios extends cSQL {

    private Connection con;
    private Statement st;
    private ResultSet rs, rsUsuarios;
    private DefaultTableModel modelo;

    public boolean Conectado() {
        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }

    public ResultSet ListaUsuarios() throws SQLException, ClassNotFoundException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from usuarios");

        return rs;
    }

    public void EliminarUsuario(String user) throws ClassNotFoundException, SQLException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("DROP USER '" + user + "'@'%'");
        st.executeUpdate("DELETE FROM usuarios WHERE username='" + user + "'");
    }

    public DefaultTableModel MostrarUsuarios(TableModel usuarios) throws SQLException, ClassNotFoundException {


            rsUsuarios = ListaUsuarios();
            Object[] usuariosArray = new Object[8];
            modelo = (DefaultTableModel) usuarios;

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
            return modelo;
//            usuarios.setModel(modelo); //aqui va enviar los datos de "modelo" a nuestra jtbUsuarios
    }

}
