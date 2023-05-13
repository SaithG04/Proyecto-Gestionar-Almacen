package Model;

import Controller.mLogueo;
import java.sql.*;

/**
 *
 * @author isai_
 */
public class cRegistroUsuarios extends cSQL {

    private Connection con;
    private Statement st;
    private PreparedStatement psInsertar;

    public void RegistrarUser(String usuario, String contraseña) throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("CREATE USER '" + usuario + "'@'%' IDENTIFIED BY '" + contraseña + "'");
        st.execute("GRANT SELECT ON * . * TO '" + usuario + "'@'%'");
        /*
            % - Conexion Remota.
            localhost - Conexion Local.
         */

    }

    public void RegistrarAdmin(String usuario, String contraseña) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("CREATE USER '" + usuario + "'@'%' IDENTIFIED BY '" + String.valueOf(contraseña) + "'");
        st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario + "'@'%'");
        st.execute("GRANT GRANT OPTION ON * . * TO '" + usuario + "'@'%'");
    }

    public void ModificarUser(String usuario, String contraseña) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("REVOKE ALL PRIVILEGES ON * . * FROM '" + usuario + "'@'%'");
        st.execute("GRANT SELECT ON * . * TO '" + usuario + "'@'%'");
        st.execute("SET PASSWORD FOR '" + usuario + "'@'%' = PASSWORD('" + contraseña + "')");
    }

    public void ModificarAdmin(String usuario, String contraseña) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario + "'@'%'");
        st.execute("GRANT GRANT OPTION ON * . * TO '" + usuario + "'@'%'");
        st.execute("SET PASSWORD FOR '" + usuario + "'@'%' = PASSWORD('" + contraseña + "')");

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

    public void Actualizar(String tipoUsuario, String consulta, String nombres, String apellidos, String usuario,
            String contraseña, String telefono, String correo, int id) throws ClassNotFoundException, SQLException {

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

    }
}
