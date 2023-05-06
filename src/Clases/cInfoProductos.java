package Clases;

import java.sql.*;

/**
 *
 * @author isai_
 */
public class cInfoProductos {
    
    private String producto;
    private String proveedor;
    private String precio;
    private String fecha;
    
    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
//    cLogueo oLogueo = new cLogueo();

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public ResultSet Lista() throws SQLException, ClassNotFoundException { 
              
//        con = oLogueo.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("Select * from precios where Producto='"+producto+"'");

        return rs;  
    }
}