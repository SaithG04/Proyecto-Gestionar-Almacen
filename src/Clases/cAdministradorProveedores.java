package Clases;

import Metodos.mGenerales;
import java.sql.Connection; //Librería de drivers
import java.sql.PreparedStatement;
import java.sql.ResultSet;//Permite almacenar valores de una consulta.
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class cAdministradorProveedores extends mGenerales{
    
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

    //Variables globales
    String usuario;
    String contraseña;
    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
    cLogueo oLogueo = new cLogueo(USER, PASSWORD);
    
    //Constructor
    public cAdministradorProveedores(){}
    
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
    
     
    //Métodos
    public ResultSet ListaProveedores() throws SQLException, ClassNotFoundException { 
              
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores ");

        return rs;  
    }
    public void Modificar() throws SQLException, ClassNotFoundException {
             
//        con = oLogueo.Conectar();
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

//        con = oLogueo.Conectar();
            
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
    
    public ResultSet ObtNumDep() throws ClassNotFoundException, SQLException{
        
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select Num_Proveedores from departamentos where Departamento = '"+departamento+"'");
        
        return rs;
    }
    
    public void ActDep() throws ClassNotFoundException, SQLException{
//        con = oLogueo.Conectar();
        psInsertar = con.prepareStatement("UPDATE departamentos SET Num_Proveedores=? WHERE Departamento=?");
        psInsertar.setInt(1, numProveedores);
        psInsertar.setString(2, departamento);
        psInsertar.executeUpdate();
    }
    
    public void Eliminar() throws SQLException, ClassNotFoundException{

//        con = oLogueo.Conectar();
        st=con.createStatement();
        st.executeUpdate("DELETE FROM proveedores WHERE IdProveedor="+idProveedor);
    } 
    
    public Icon icono(String path, int width, int height){
        //Método para obtener atributos de la imagen como "Icon".
        Icon ic = new ImageIcon(new ImageIcon(this.getClass().getResource(path))
        .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return ic;
    }
    public ResultSet ListarDepartamentos() throws SQLException, ClassNotFoundException{

//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from departamentos");
        return rs;
    }
    public ResultSet ListarProductos() throws SQLException, ClassNotFoundException{

//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos");
        return rs;
    }
    public ResultSet Reporte() throws SQLException, ClassNotFoundException{

//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores ");
        return rs;
    }
    public ResultSet ObtRespuesta() throws ClassNotFoundException, SQLException{
        
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select Proveedor from productos");
        
        return rs;
    }
    public void EliminarDePrecio() throws SQLException, ClassNotFoundException{

//        con = oLogueo.Conectar();
        st=con.createStatement();
        st.executeUpdate("DELETE FROM precios WHERE Proveedor='"+razonSocial+"'");
    }

    @Override
    public void CargarFrame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}