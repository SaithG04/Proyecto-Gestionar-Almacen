package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isai_
 */
public class cAdministradorProductos extends mSQL {

    private String categoria;
    private String producto;
    private int cantidad;
    private String precio;
    private String fechaCaducidad;
    private String proveedor;
    private int idProducto;
    private final cAlertas oA = new cAlertas();

    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs, rsProductos;
    PreparedStatement psInsertar = null;

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

    public Object[][] ListarProductos() {
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rsProductos = st.executeQuery("SELECT * FROM productos");

            List<Object[]> productosList = new ArrayList<>();

            while (rsProductos.next()) {
                Object[] prod = new Object[4];
                prod[0] = rsProductos.getInt("IdProducto");
                prod[1] = rsProductos.getString("Nombre");
                prod[2] = rsProductos.getString("Categoria");
                prod[3] = rsProductos.getString("Stock");
                productosList.add(prod);
            }

            Object[][] productos = new Object[productosList.size()][4];
            productosList.toArray(productos);

            return productos;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }

        return null;
    }

    public ResultSet ListarProveedores() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from proveedores");

            return rs;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public ResultSet ListarCategorias() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from categorias");

            return rs;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public ResultSet ListarProductosPorCategoria() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from productos where Categoria='" + categoria + "'");

            return rs;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public void NuevaCategoria() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO categorias(Categoria) VALUES (?)");
            psInsertar.setString(1, categoria);
            psInsertar.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public void InsertarNuevo() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO productos(IdProducto,Nombre,Categoria,Stock) VALUES (?,?,?,?)");
            psInsertar.setInt(1, idProducto);
            psInsertar.setString(2, producto);
            psInsertar.setString(3, categoria);
            psInsertar.setInt(4, cantidad);
            psInsertar.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public void InsertarPrecioDeNuevo() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO precios(IdPrecio,Producto,Proveedor,Precio,FechaCaducidad) VALUES (?,?,?,?,?)");
            psInsertar.setInt(1, 0);
            psInsertar.setString(2, producto);
            psInsertar.setString(3, proveedor);
            psInsertar.setString(4, precio);
            psInsertar.setString(5, fechaCaducidad);
            psInsertar.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public void InsertarPrecioDeExistente() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO precios(IdPrecio,Producto,Proveedor,Precio,FechaCaducidad) VALUES (?,?,?,?,?)");
            psInsertar.setInt(1, 0);
            psInsertar.setString(2, producto);
            psInsertar.setString(3, proveedor);
            psInsertar.setString(4, precio);
            psInsertar.setString(5, fechaCaducidad);
            psInsertar.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public ResultSet ObtCant() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select Stock from productos where Nombre= '" + producto + "'");

            return rs;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public void AgStock() {
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            psInsertar = con.prepareStatement("UPDATE productos SET Stock=? WHERE Nombre=?");
            psInsertar.setInt(1, cantidad);
            psInsertar.setString(2, producto);
            psInsertar.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public void EliminarProducto() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.executeUpdate("DELETE FROM productos WHERE IdProducto='" + idProducto + "'");
//            st.executeUpdate("DELETE FROM precios WHERE Producto='" + producto + "'");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

//    public int NumProductos() {
//        try {
//            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//            st = con.createStatement();
//            rs = st.executeQuery("SELECT COUNT(*) AS num_filas FROM productos");//Cantidad de productos
//            
//            int totalFilas = 0;
//            if (rs.next()) {
//                totalFilas = rs.getInt("num_filas");             
//            }
//            return totalFilas;
//        } catch (ClassNotFoundException | SQLException ex) {
//            oA.errorC(ex.getMessage());
//        }
//        return 0;
//    }
}
