package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.sql.*;
import javax.swing.table.*;

public class cAdministradorProveedores extends mSQL {

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
    ResultSet rsProveedores, rsDepartamentos, rsNumDep;
    private DefaultTableModel modelo;
    ResultSet rs;
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

    public boolean Conectado() {

        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
            return false;
        }
        return true;
    }

    public DefaultTableModel AgregarProveedor(TableModel modelo) {
        try {
            Insertar();
            Object[] c = new Object[1];
            rsNumDep = ObtNumDep();
            while (rsNumDep.next()) {
                c[0] = rsNumDep.getInt("Num_Proveedores");
                numProveedores = Integer.parseInt(c[0].toString()) + 1;
                ActDep();
            }
            oA.aviso("El proveedor se ha agregado correctamente.");
            DefaultTableModel MostrarProveedores = MostrarProveedores(modelo);
            return MostrarProveedores;
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public DefaultTableModel ModificarProveedor(String departT, String departS, TableModel model) {
        try {
            if (!(departT.equalsIgnoreCase(departS))) {

                rsNumDep = ObtNumDep();
                Object[] c = new Object[1];
                while (rsNumDep.next()) {
                    c[0] = rsNumDep.getInt("Num_Proveedores");
                    numProveedores = Integer.parseInt(c[0].toString()) - 1;
                    ActDep();
                }
                departamento = departS;
                rsNumDep = ObtNumDep();
                while (rsNumDep.next()) {
                    c[0] = rsNumDep.getInt("Num_Proveedores");
                    numProveedores = Integer.parseInt(c[0].toString()) + 1;
                    ActDep();
                }
            } else {
                departamento = departT;
            }
            Modificar();
            DefaultTableModel MostrarProveedores = MostrarProveedores(model);
            oA.aviso("Modificado con éxito");
            return MostrarProveedores;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public DefaultTableModel EliminarProveedor(String razonSocial, TableModel modelo) {
        try {
            rsNumDep = ObtNumDep();
            Object[] c = new Object[1];
            while (rsNumDep.next()) {

                c[0] = rsNumDep.getInt("Num_Proveedores");
                numProveedores = Integer.parseInt(c[0].toString()) - 1;
                ActDep();
            }
            this.razonSocial = razonSocial;
            EliminarProveedor();          
            EliminarDePrecio();
            DefaultTableModel MostrarProveedores = MostrarProveedores(modelo);
            oA.aviso("El proveedor ha sido eliminado correctamente.");
            return MostrarProveedores;
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public DefaultTableModel MostrarProveedores(TableModel proveedores) {
        try {
            rsProveedores = ListaProveedores();
            Object[] prov = new Object[8];
            modelo = (DefaultTableModel) proveedores;

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
            return modelo;
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public String[] MostrarDepartamentos() {
        try {
            String[] depart = new String[25];
            rsDepartamentos = ListarDepartamentos();
            for (int i = 0; rsDepartamentos.next(); i++) {
                depart[i] = rsDepartamentos.getString("Departamento");
            }
            return depart;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public int EncontrarDepartamento(String dep) {
        try {
            //        String fila = proveedores.getValueAt(proveedores.getSelectedRow(), 7).toString();
            String[] depart = new String[25];
            rsDepartamentos = ListarDepartamentos();
            for (int i = 0; rsDepartamentos.next(); i++) {
                depart[i] = rsDepartamentos.getString("Departamento");
                if (depart[i].equals(dep)) {
                    return i;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.getMessage());
        }
        return -1;
    }

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

    public void EliminarProveedor() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.executeUpdate("DELETE FROM proveedores WHERE Razon_Social='" + razonSocial +"'");
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

//    public ResultSet Reporte() throws SQLException, ClassNotFoundException {
//
//        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//        st = con.createStatement();
//        rs = st.executeQuery("Select * from proveedores");
//        return rs;
//    }

//    public ResultSet ObtRespuesta() throws ClassNotFoundException, SQLException {
//
//        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//        st = con.createStatement();
//        rs = st.executeQuery("Select Proveedor from productos");
//
//        return rs;
//    }

    public void EliminarDePrecio() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.executeUpdate("DELETE FROM precios WHERE IdProveedor=" + idProveedor);
    }
}
