package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cLogueo extends cSQL {

    public static cLogueo oL;
    private String usuario;
    private String contraseña;
    private PreparedStatement st;
    private ResultSet rs;

    public cLogueo(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public cLogueo() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String iniciarSesion(String user, String contrasena) {
        try {
            Connection con = Conectar();
            st = con.prepareStatement("Select username from usuarios where username COLLATE utf8_bin = '" + user + "'"); //utf8_bin para dif MAY de MIN
            rs = st.executeQuery();
            if (rs.next()) { //Validar usuario
                st = con.prepareStatement("Select contraseña from usuarios where username COLLATE utf8_bin = '" + user + "' and contraseña COLLATE utf8_bin = '" + contrasena + "'");
                rs = st.executeQuery();
                if (rs.next()) { //Validar contraseña
                    st = con.prepareStatement("Select tipo_usuario from usuarios where username COLLATE utf8_bin = '" + user + "'");
                    rs = st.executeQuery();
                    while (rs.next()) { //Verificar permisos de cuenta
                        String tu = rs.getString("tipo_usuario");
                        if (tu.equals("administrador")) {
                            return "correctoAdmin";
                        } else {
                            return "correctoUser";
                        }
                    }
                } else {
                    return "contraIncorrect";
                }
            } else {
                return "userNoExist";
            }          
        } catch (ClassNotFoundException | SQLException ex) {
            boolean manejarErrorConexion = oA.manejarErrorConexion(cLogueo.class, ex);
            if(manejarErrorConexion) System.exit(0);
        }
        return null;
    }
}
