package Model;

import Controller.mLogueo;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author isai_
 */
public class cAdministradorUsuarios extends cSQL {

    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private String tipoUsuario;
    private String telefono;
    private String correo;
    private int idAdmin;
    private java.sql.Timestamp fechaRegistro;

    private String Admin;
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
    private DefaultTableModel modelo;

    //CREAR USUARIO
    public cAdministradorUsuarios(String nombres, String apellidos, String username, String password, String tipoUsuario,
            String telefono, String correo, String Admin, java.sql.Timestamp fechaRegistro) throws ClassNotFoundException, SQLException {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.correo = correo;
        this.Admin = Admin;
        setIdAdmin();
        this.fechaRegistro = fechaRegistro;
    }

    //MODIFICAR USUARIO
    public cAdministradorUsuarios(int idUsuario, String nombres, String apellidos, String username, String password,
            String tipoUsuario, String telefono, String correo) throws ClassNotFoundException, SQLException {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.correo = correo;
        setIdAdminAndTime();
    }
    
    //SOLO INSTANCIAR CLASE PARA USAR SUS METODOS
    public cAdministradorUsuarios(){}
    
    public String getAdmin() {
        return Admin;
    }

    private void setIdAdmin() throws ClassNotFoundException, SQLException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        String query = "SELECT id FROM usuarios WHERE username = '" + Admin + "'";
        rs = st.executeQuery(query);
        if (rs.next()) {
            idAdmin = rs.getInt("id");
        }
        Finalize();
    }
    
    private void setIdAdminAndTime() throws ClassNotFoundException, SQLException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        String query = "SELECT id_admin,fecha_registro FROM usuarios WHERE id = '" + idUsuario + "'";
        rs = st.executeQuery(query);
        if (rs.next()) {
            idAdmin = rs.getInt("id_admin");
            fechaRegistro = rs.getTimestamp("fecha_registro");
        }
        Finalize();
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return username;
    }

    public void setUsuario(String username) {
        this.username = username;
    }

    public String getPasswotd() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void RegistrarUser(String usuario, String contraseña) throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("CREATE USER '" + usuario + "'@'localhost' IDENTIFIED BY '" + contraseña + "'");
        st.execute("GRANT SELECT ON * . * TO '" + usuario + "'@'localhost'");
        st.execute("FLUSH PRIVILEGES");
        /*
            % - Conexion Remota.
            localhost - Conexion Local.
         */
    }

    public void RegistrarAdmin(String usuario, String contraseña) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("CREATE USER '" + usuario + "'@'localhost' IDENTIFIED BY '" + contraseña + "';");
        st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario + "'@'localhost' WITH GRANT OPTION");
        st.execute("FLUSH PRIVILEGES");
    }

    public void ModificarUser(String usuario, String password) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("REVOKE ALL PRIVILEGES ON * . * FROM '" + usuario + "'@'localhost'");
        st.execute("GRANT SELECT ON * . * TO '" + usuario + "'@'localhost'");
//        st.execute("SET PASSWORD FOR '" + usuario + "'@'localhost' = PASSWORD('" + password + "')"); Para versiones anteriores a MySQL 8.0
        st.execute("ALTER USER '" + usuario + "'@'localhost' IDENTIFIED BY '" + password + "'");
        st.execute("FLUSH PRIVILEGES");
    }

    public void ModificarAdmin(String usuario, String password) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario + "'@'localhost' WITH GRANT OPTION");
//        st.execute("SET PASSWORD FOR '" + usuario + "'@'localhost' = PASSWORD('" + password + "')"); Para versiones anteriores a MySQL 8.0
        st.execute("ALTER USER '" + usuario + "'@'localhost' IDENTIFIED BY '" + password + "'");
        st.execute("FLUSH PRIVILEGES");

    }

    public void Registrar(String consulta) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement(consulta);
        psInsertar.setString(1, nombres);
        psInsertar.setString(2, apellidos);
        psInsertar.setString(3, username);
        psInsertar.setString(4, password);
        psInsertar.setString(5, tipoUsuario);
        psInsertar.setString(6, telefono);
        psInsertar.setString(7, correo);
        psInsertar.setInt(8, idAdmin);
        psInsertar.setTimestamp(9, fechaRegistro);
        psInsertar.executeUpdate();

    }

    public void Actualizar(String consulta) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement(consulta);
        psInsertar.setString(1, nombres);
        psInsertar.setString(2, apellidos);
        psInsertar.setString(3, username);
        psInsertar.setString(4, password);
        psInsertar.setString(5, tipoUsuario);
        psInsertar.setString(6, telefono);
        psInsertar.setString(7, correo);
        psInsertar.setInt(8, idAdmin);
        psInsertar.setTimestamp(9, fechaRegistro);
        psInsertar.setInt(10, idUsuario);
        psInsertar.executeUpdate();
    }

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
        st.execute("DROP USER '" + user + "'@'localhost'");
        st.executeUpdate("DELETE FROM usuarios WHERE username='" + user + "'");
    }

    public DefaultTableModel MostrarUsuarios(TableModel usuarios) throws SQLException, ClassNotFoundException {

        rs = ListaUsuarios();
        Object[] usuariosArray = new Object[8];
        modelo = (DefaultTableModel) usuarios;

        modelo.setRowCount(0);
        while (rs.next()) {
            usuariosArray[0] = rs.getInt("id");
            usuariosArray[1] = rs.getString("nombres");
            usuariosArray[2] = rs.getString("apellidos");
            usuariosArray[3] = rs.getString("username");
            usuariosArray[5] = rs.getString("tipo_usuario");
            usuariosArray[6] = rs.getLong("telefono");
            usuariosArray[7] = rs.getString("correo");
            int length = rs.getString("password").length();
            usuariosArray[4] = "";
            for (int i = 0; i < length; i++) {
                usuariosArray[4] = usuariosArray[4] + "*";
            }
            modelo.addRow(usuariosArray); //Va adheriendo en nuestro DefaultTableModel "modelo"
        }
        return modelo;
    }

    void Finalize() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (psInsertar != null) {
            psInsertar.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
}
