package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isai_
 */
public class cAdministradorProductos extends mSQL {

    private int idProducto;
    private String producto;
    private String categoria;
    private BigDecimal cantidad;

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

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
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
                prod[3] = rsProductos.getBigDecimal("Stock");
                productosList.add(prod);
            }

            return productosList.toArray(new Object[productosList.size()][4]);
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public List<String> ListarProveedores() {
        List<String> prov = new ArrayList<>();
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from proveedores");
            while (rs.next()) {
                prov.add(rs.getString("Razon_Social"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return prov;
    }

    public List<String> ListarCategorias() {
        List<String> elementosUnicos = new ArrayList<>();

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            String sql = "SELECT DISTINCT Categoria FROM productos";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String elemento = rs.getString("Categoria");
                elementosUnicos.add(elemento);
            }
        } catch (SQLException | ClassNotFoundException e) {
            oA.errorC(e.getMessage());
        }
        return elementosUnicos;
    }

    public List<String> ListarProductosPorCategoria() {
        List<String> elementos = new ArrayList<>();
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from productos where Categoria='" + categoria + "'");
            while (rs.next()) {
                elementos.add(rs.getString("Nombre"));
            }
            return elementos;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    public void InsertarNuevo() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO productos(IdProducto,Nombre,Categoria,Stock) VALUES (?,?,?,?)");
            psInsertar.setInt(1, 0);
            psInsertar.setString(2, producto);
            psInsertar.setString(3, categoria);
            psInsertar.setBigDecimal(4, cantidad);
            psInsertar.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

//    public int ObtIdProducto() {
//
//        try {
//            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//            st = con.createStatement();
//            rs = st.executeQuery("Select * from productos where Nombre='" + producto + "'");
//            if (rs.next()) {
//                return rs.getInt("IdProducto");
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            oA.errorC(ex.getMessage());
//        }
//        return 0;
//    }
//    public int ObtCant() {
//
//        try {
//            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//            st = con.createStatement();
//            rs = st.executeQuery("Select * from productos where Nombre= '" + producto + "'");
//            return rs.getInt("Stock");
//        } catch (ClassNotFoundException | SQLException ex) {
//            oA.errorC(ex.getMessage());
//        }
//        return 0;
//    }
    public void AgregarStock() {
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("SELECT Stock FROM productos WHERE Nombre= '" + producto + "'");
            while (rs.next()) {
                BigDecimal Oldstock = rs.getBigDecimal("Stock");
                cantidad = Oldstock.add(cantidad);
                psInsertar = con.prepareStatement("UPDATE productos SET Stock=? WHERE Nombre=?");
                psInsertar.setBigDecimal(1, cantidad);
                psInsertar.setString(2, producto);
                psInsertar.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public void DisminuirStock() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select Stock from productos where Nombre= '" + producto + "'");
            while (rs.next()) {
//                BigDecimal numero1 = new BigDecimal("10.5");
//                BigDecimal numero2 = new BigDecimal("5.5");
//
//                int resultado = numero1.compareTo(numero2);
//
//                if (resultado > 0) {
//                    System.out.println("numero1 es mayor que numero2");
//                } else if (resultado < 0) {
//                    System.out.println("numero1 es menor que numero2");
//                } else {
//                    System.out.println("numero1 es igual a numero2");
//                }

                BigDecimal stock = rs.getBigDecimal("Stock");
                if (stock.compareTo(cantidad) < 0) {
                    oA.error("Stock insuficiente.", "");
                } else {
                    cantidad = stock.subtract(cantidad);
                    psInsertar = con.prepareStatement("UPDATE productos SET Stock=? WHERE Nombre=?");
                    psInsertar.setBigDecimal(1, cantidad);
                    psInsertar.setString(2, producto);
                    psInsertar.executeUpdate();
                    oA.aviso("Stock modificado correctamente");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }

    }

    public void EliminarProducto() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            st.executeUpdate("DELETE FROM productos WHERE IdProducto='" + idProducto + "'");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }
}
