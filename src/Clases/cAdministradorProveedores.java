package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class cAdministradorProveedores extends mSQL{

    //Atributos
    private int idProveedor;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String contacto;
    private String telefono;
    private String email;
    private String departamento;
    private int numProveedores;
    private String proveedores;
    
    private final cAlertas oA = new cAlertas();
    private Connection con;
    private Statement st;
    private ResultSet rs, rsUsuarios;
    ResultSet rsProveedores, rsDepartamentos, rsNumDep;
//    private DefaultTableModel modelo;
//    Connection con;//Conectar a la base de datos
//    Statement st;
//    ResultSet rs;
    PreparedStatement psInsertar = null;

    //Getter and Setter
    public int getNumProveedores() {
        return numProveedores;
    }

    public void setNumProveedores(int numProveedores) {
        this.numProveedores = numProveedores;
    }

    public String getProveedores() {
        return proveedores;
    }

    public void setProveedores(String proveedores) {
        this.proveedores = proveedores;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public ResultSet ListaUsuarios() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from usuarios");

        return rs;
    }

    public void EliminarUsuario(String user) {
        try {
            //        String user = usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString();
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
    
    public boolean Conectado() {

        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
            return false;
        }
        return true;
    }
    
    void MostrarProveedores() {
        try {
            rsProveedores = ListaProveedores();
            Object[] prov = new Object[8];
            modelo = (DefaultTableModel) proveedores.getModel();
            
            modelo.setRowCount(0);
            
            while (rsProveedores.next()) {
                prov[0] = rsProveedores.getInt("IdProveedor");
                prov[1] = rsProveedores.getString("Razon_Social");
                prov[2] = rsProveedores.getString("RUC");
                prov[3] = rsProveedores.getString("Direccion");
                prov[4] = rsProveedores.getString("Contacto");
                prov[5] = rsProveedores.getString("Telefono");
                prov[6] = rsProveedores.getString("Email");
                prov[7] = rsProveedores.getString("Departamento");
                modelo.addRow(prov);
            }
            proveedores.setModel(modelo);
        } catch (SQLException ex) {
            oA.errorC(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            oA.error("Error desconocido", ex.getMessage());
        }
    }
    
    void MostrarDepartamentos() {
        try {
            rsDepartamentos = ListarDepartamentos();
            while (rsDepartamentos.next()) {
                departamentos.addItem(rsDepartamentos.getString("Departamento"));
            }
            departamentos.setSelectedItem(null);
        } catch (ClassNotFoundException ex) {
            oA.error("Error desconocido.", ex.getMessage());
        } catch (SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }
    void EncontrarDepartamento(int fila) throws SQLException, ClassNotFoundException {
//        String fila = proveedores.getValueAt(proveedores.getSelectedRow(), 7).toString();
        String[] depart = new String[25];
        rsDepartamentos = ListarDepartamentos();
        for (int i = 0; rsDepartamentos.next(); i++) {
            depart[i] = rsDepartamentos.getString("Departamento");
            if (depart[i].equals(fila)) {
                departamentos.setSelectedIndex(i);
            }
        }
    }
    
    //SQL
        public ResultSet ListaProveedores() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores ");

        return rs;
    }

    public void Modificar() throws SQLException, ClassNotFoundException {

        con = Conectar();
        psInsertar = con.prepareStatement("UPDATE proveedores SET Razon_Social=?,RUC=?,Direccion=?,Contacto=?,Telefono=?,Email=?,Departamento=? WHERE IdProveedor=?");
        psInsertar.setString(1, razonSocial);
        psInsertar.setString(2, ruc);
        psInsertar.setString(3, direccion);
        psInsertar.setString(4, contacto);
        psInsertar.setString(5, telefono);
        psInsertar.setString(6, email);
        psInsertar.setString(7, departamento);
        psInsertar.setInt(8, idProveedor);
        psInsertar.executeUpdate();

    }

    public void Insertar() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement("INSERT INTO proveedores(IdProveedor,Razon_Social,RUC,Direccion,Contacto,Telefono,Email,Departamento) VALUES (?,?,?,?,?,?,?,?)");
        psInsertar.setInt(1, idProveedor);
        psInsertar.setString(2, razonSocial);
        psInsertar.setString(3, ruc);
        psInsertar.setString(4, direccion);
        psInsertar.setString(5, contacto);
        psInsertar.setString(6, telefono);
        psInsertar.setString(7, email);
        psInsertar.setString(8, departamento);
        psInsertar.executeUpdate();
    }

    public ResultSet ObtNumDep() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select Num_Proveedores from departamentos where Departamento = '" + departamento + "'");

        return rs;
    }

    public void ActDep() throws ClassNotFoundException, SQLException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement("UPDATE departamentos SET Num_Proveedores=? WHERE Departamento=?");
        psInsertar.setInt(1, getNumProveedores());
        psInsertar.setString(2, departamento);
        psInsertar.executeUpdate();
    }

    public void Eliminar() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.executeUpdate("DELETE FROM proveedores WHERE IdProveedor=" + idProveedor);
    }

    public ResultSet ListarDepartamentos() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from departamentos");
        return rs;
    }

    public ResultSet ListarProductos() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos");
        return rs;
    }

    public ResultSet Reporte() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores ");
        return rs;
    }

    public ResultSet ObtRespuesta() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());  
        st = con.createStatement();
        rs = st.executeQuery("Select Proveedor from productos");

        return rs;
    }

    public void EliminarDePrecio() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());  
        st = con.createStatement();
        st.executeUpdate("DELETE FROM precios WHERE Proveedor='" + razonSocial + "'");
    }
}