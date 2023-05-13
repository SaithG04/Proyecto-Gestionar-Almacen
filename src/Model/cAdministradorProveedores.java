package Model;

import Controller.mLogueo;
import java.sql.*;
import javax.swing.table.*;

public class cAdministradorProveedores extends cSQL {

    //Atributos
    private int idProveedor;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String contacto;
    private String telefono;
    private String email;
    private String web;
    private String departamento;

    private Connection con;
    private Statement st;
    private DefaultTableModel modelo;
    ResultSet rs;
    PreparedStatement psInsertar = null;

    public String getWeb() {
        return web;
    }

    //Getter and Setter
    public void setWeb(String web) {
        this.web = web;
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

    public DefaultTableModel EliminarProveedor(TableModel modelo) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.executeUpdate("DELETE FROM proveedores WHERE IdProveedor=" + idProveedor);
        DefaultTableModel MostrarProveedores = MostrarProveedores(modelo);
        Finalize();
        return MostrarProveedores;
    }

    public DefaultTableModel MostrarProveedores(TableModel proveedores) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores");
        Object[] prov = new Object[9];
        modelo = (DefaultTableModel) proveedores;

        modelo.setRowCount(0);

        while (rs.next()) {
            prov[0] = rs.getInt("IdProveedor");
            prov[1] = rs.getString("Razon_Social");
            prov[2] = rs.getString("RUC");
            prov[3] = rs.getString("Direccion");
            prov[4] = rs.getString("Contacto");
            prov[5] = rs.getString("Telefono");
            prov[6] = rs.getString("Email");
            prov[7] = rs.getString("Web");
            prov[8] = rs.getString("Departamento");
            modelo.addRow(prov);
        }
        Finalize();
        return modelo;

    }

    public DefaultTableModel ModificarProveedor(TableModel model) throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement("UPDATE proveedores SET Razon_Social=?,RUC=?,Direccion=?,Contacto=?,Telefono=?,Email=?,Web=?,Departamento=? WHERE IdProveedor=?");
        psInsertar.setString(1, razonSocial);
        psInsertar.setString(2, ruc);
        psInsertar.setString(3, direccion);
        psInsertar.setString(4, contacto);
        if (telefono.isEmpty()) {
            psInsertar.setString(5, null);
        } else {
            psInsertar.setString(5, telefono);
        }
        if (email.isEmpty()) {
            psInsertar.setString(6, null);
        } else {
            psInsertar.setString(6, email);
        }
        if (web.isEmpty()) {
            psInsertar.setString(7, null);
        } else {
            psInsertar.setString(7, web);
        }
        psInsertar.setString(8, departamento);
        psInsertar.setInt(9, idProveedor);
        psInsertar.executeUpdate();
        DefaultTableModel MostrarProveedores = MostrarProveedores(model);
        Finalize();
        return MostrarProveedores;
    }

    public DefaultTableModel InsertarProveedor(TableModel modelo) throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement("INSERT INTO proveedores(IdProveedor,Razon_Social,RUC,Direccion,Contacto,Telefono,Email,Departamento) VALUES (?,?,?,?,?,?,?,?)");
        psInsertar.setInt(1, idProveedor);
        psInsertar.setString(2, razonSocial);
        psInsertar.setString(3, ruc);
        psInsertar.setString(4, direccion);
        psInsertar.setString(5, contacto);
        if (telefono.isEmpty() && email.isEmpty()) {
            psInsertar.setString(6, null);
            psInsertar.setString(7, null);
        } else if (!telefono.isEmpty() && email.isEmpty()) {
            psInsertar.setString(6, telefono);
            psInsertar.setString(7, null);
        } else {
            psInsertar.setString(6, telefono);
            psInsertar.setString(7, email);
        }
        psInsertar.setString(8, departamento);
        psInsertar.executeUpdate();
        DefaultTableModel MostrarProveedores = MostrarProveedores(modelo);
        if (!con.isClosed()) {
            con.close();
        }
        if (!psInsertar.isClosed()) {
            psInsertar.close();
        }
        return MostrarProveedores;

    }

    public ResultSet ListarProductos() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos");
        return rs;
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
