package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.sql.*;
import javax.swing.table.*;

/**
 *
 * @author isai_
 */
public class cGestionarUsuarios extends mSQL{
    
    private final cAlertas oA = new cAlertas();
    private Connection con;
    private Statement st;
    private ResultSet rs, rsUsuarios;
    private DefaultTableModel modelo;
    
    public boolean Conectado() {

        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
            return false;
        }
        return true;
    }
    
    public ResultSet ListaUsuarios() throws SQLException, ClassNotFoundException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from usuarios");

        return rs;
    }

    public void EliminarUsuario(String user) {
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.execute("DROP USER '" + user + "'@'%'");
            st.executeUpdate("DELETE FROM usuarios WHERE username='" + user + "'");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }
    
    public DefaultTableModel MostrarUsuarios(TableModel usuarios) {

        try {
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
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.toString());
        }
        return null;
    }
    
}