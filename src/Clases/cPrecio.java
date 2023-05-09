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
public class cPrecio extends mSQL {

    private int idPrecio;
    private int idProducto;
    private String unidad;
    private int idproveedor;
    private BigDecimal precio;
    private String fecha;

    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
    cAlertas oA = new cAlertas();

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String producto) {
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from productos where Nombre='" + producto + "'");
            if (rs.next()) {
                idProducto = rs.getInt("IdProducto");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
            idProducto = -1;
        }
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String nombreProveedor) {
        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            rs = st.executeQuery("Select * from proveedores where Razon_Social ='" + nombreProveedor + "'");
            if (rs.next()) {
                idproveedor = rs.getInt("IdProveedor");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            oA.errorC(ex.getMessage());
            idproveedor = -1;
        }
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void InsertarPrecio() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO precios(IdPrecio,IdProducto,Unidad,IdProveedor,Precio,FechaCaducidad) VALUES (?,?,?,?,?,?)");
            psInsertar.setInt(1, idPrecio);
            psInsertar.setInt(2, idProducto);
            psInsertar.setString(3, unidad);
            psInsertar.setInt(4, idproveedor);
            psInsertar.setBigDecimal(5, precio);
            psInsertar.setString(6, fecha);
            psInsertar.executeUpdate();
            oA.aviso("Registro exitoso.");
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public Object[][] ListarPrecios() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            Statement st2 = con.createStatement();
            rs = st.executeQuery("SELECT * FROM precios WHERE IdProducto=" + idProducto);
            List<Object[]> precioList = new ArrayList<>();

            while (rs.next()) {
                Object[] prec = new Object[4];
                prec[0] = rs.getBigDecimal("Precio");
                prec[1] = rs.getString("Unidad");
                prec[3] = rs.getString("FechaCaducidad");
                int idProv = rs.getInt("IdProveedor");
                ResultSet rsIdPv = st2.executeQuery("SELECT * FROM proveedores where IdProveedor=" + idProv);
                if (rsIdPv.next()) {
                    prec[2] = rsIdPv.getString("Razon_Social");
                }
                precioList.add(prec);
            }
            return precioList.toArray(new Object[precioList.size()][4]);
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }
}
