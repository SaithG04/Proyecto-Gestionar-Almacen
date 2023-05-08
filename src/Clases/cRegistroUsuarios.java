package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.sql.*;

/**
 *
 * @author isai_
 */
public class cRegistroUsuarios extends mSQL{
    
    private final cAlertas oA = new cAlertas();
//    private cUsuarios oU;
    private Connection con;
    private Statement st;
    private PreparedStatement psInsertar;
    
    public void RegistrarUser(String usuario, String contraseña){

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.execute("CREATE USER '" + usuario + "'@'%' IDENTIFIED BY '" + contraseña + "'");
            st.execute("GRANT SELECT ON * . * TO '" + usuario + "'@'%'");
            /*
            % - Conexion Remota.
            localhost - Conexion Local.
            */
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
        }
    }

    public void RegistrarAdmin(String usuario, String contraseña){
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.execute("CREATE USER '" + usuario + "'@'%' IDENTIFIED BY '" + String.valueOf(contraseña) + "'");
            st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario + "'@'%'");
            st.execute("GRANT GRANT OPTION ON * . * TO '" + usuario + "'@'%'");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
        }
    }

    public void ModificarUser(String usuario, String contraseña){

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.execute("REVOKE ALL PRIVILEGES ON * . * FROM '" + usuario + "'@'%'");
            st.execute("GRANT SELECT ON * . * TO '" + usuario + "'@'%'");
            st.execute("SET PASSWORD FOR '" + usuario + "'@'%' = PASSWORD('" + contraseña + "')");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
        }
    }

    public void ModificarAdmin(String usuario, String contraseña) {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario + "'@'%'");
            st.execute("GRANT GRANT OPTION ON * . * TO '" + usuario + "'@'%'");
            st.execute("SET PASSWORD FOR '" + usuario + "'@'%' = PASSWORD('" + contraseña + "')");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
        }

    }

    public void Registrar(cUsuarios usuario, String consulta){

        try {
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
            oA.aviso("Correcto");
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.toString());
        }
    }

    public void Actualizar(String tipoUsuario, String consulta, String nombres, String apellidos, String usuario,
            String contraseña, String telefono, String correo, int id){
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            psInsertar = con.prepareStatement(consulta);
            psInsertar.setString(1, nombres);
            psInsertar.setString(2, apellidos);
            psInsertar.setString(3, usuario);
            psInsertar.setString(4, contraseña);
            psInsertar.setString(5, tipoUsuario);
            psInsertar.setString(6, telefono);
            psInsertar.setString(7, correo);
            psInsertar.setInt(8, id);
            psInsertar.executeUpdate();
            oA.aviso("Correcto");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
        }
    }
}