package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author isai_
 */
public class cAdministradorProductos {
    
    private String categoria;
    private String producto;
    private int cantidad;
    private String precio;
    private String fechaCaducidad;
    private String proveedor;
    private int idProducto;
    
    
    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
//    cLogueo oLogueo = new cLogueo();

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public ResultSet ListaProductos() throws SQLException, ClassNotFoundException { 
              
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos");

        return rs;  
    }

    public ResultSet ListarProveedores() throws SQLException, ClassNotFoundException { 
              
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores");

        return rs;  
    }
    public ResultSet ListarCategorias() throws SQLException, ClassNotFoundException { 
              
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from categorias");

        return rs;  
    }
    public ResultSet ListarProductos() throws SQLException, ClassNotFoundException { 
              
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos where Categoria='"+categoria+"'");

        return rs;  
    }
    public void NuevaCategoria() throws SQLException, ClassNotFoundException {      

//        con = oLogueo.Conectar();
            
        psInsertar = con.prepareStatement("INSERT INTO categorias(Categoria) VALUES (?)");
        psInsertar.setString(1, categoria);
        psInsertar.executeUpdate();  
    }
    public void InsertarNuevo() throws SQLException, ClassNotFoundException {      

//        con = oLogueo.Conectar();
            
        psInsertar = con.prepareStatement("INSERT INTO productos(IdProducto,Nombre,Categoria,Stock) VALUES (?,?,?,?)");
        psInsertar.setInt(1, idProducto);
        psInsertar.setString(2, producto);
        psInsertar.setString(3, categoria);
        psInsertar.setInt(4, cantidad);
        psInsertar.executeUpdate();  
    }
    public void InsertarPrecioDeNuevo() throws SQLException, ClassNotFoundException {      

//        con = oLogueo.Conectar();
            
        psInsertar = con.prepareStatement("INSERT INTO precios(IdPrecio,Producto,Proveedor,Precio,FechaCaducidad) VALUES (?,?,?,?,?)");
        psInsertar.setInt(1, 0);
        psInsertar.setString(2, producto);
        psInsertar.setString(3, proveedor);
        psInsertar.setString(4, precio);
        psInsertar.setString(5, fechaCaducidad);
        psInsertar.executeUpdate();  
    }
    
    public void InsertarPrecioDeExistente() throws SQLException, ClassNotFoundException {      

//        con = oLogueo.Conectar();
            
        psInsertar = con.prepareStatement("INSERT INTO precios(IdPrecio,Producto,Proveedor,Precio,FechaCaducidad) VALUES (?,?,?,?,?)");
        psInsertar.setInt(1, 0);
        psInsertar.setString(2, producto);
        psInsertar.setString(3, proveedor);
        psInsertar.setString(4, precio);
        psInsertar.setString(5, fechaCaducidad);
        psInsertar.executeUpdate();  
    }

    public ResultSet ObtCant() throws ClassNotFoundException, SQLException{
        
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select Stock from productos where Nombre= '"+producto+"'");
        
        return rs;
    }

    public void AgStock() throws ClassNotFoundException, SQLException{
//        con = oLogueo.Conectar();
        psInsertar = con.prepareStatement("UPDATE productos SET Stock=? WHERE Nombre=?");
        psInsertar.setInt(1, cantidad);
        psInsertar.setString(2, producto);
        psInsertar.executeUpdate();
    }
    public void Eliminar() throws SQLException, ClassNotFoundException{

//        con = oLogueo.Conectar();
        st=con.createStatement();
        st.executeUpdate("DELETE FROM productos WHERE IdProducto='"+idProducto+"'");
        st.executeUpdate("DELETE FROM precios WHERE Producto='"+producto+"'");
    }    
}