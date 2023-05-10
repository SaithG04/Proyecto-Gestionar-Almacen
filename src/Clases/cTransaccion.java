package Clases;

import Metodos.mLogueo;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isai_
 */
public class cTransaccion extends cSQL {

    private int idTransaccion;
    private String tipoTrans;
    private String producto;
    private String unidad;
    private String proveedor;
    private BigDecimal monto;
    private String fecha;
    private BigDecimal stock;

    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
    cAlertas oA = new cAlertas();

    public String getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(String tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public void InsertarTransaccion() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());

            psInsertar = con.prepareStatement("INSERT INTO transacciones(IdTransaccion,TipoTransaccion,Producto,Monto,Cantidad,Unidad,Proveedor,FechaCaducidad,FechaTransaccion,UsuarioTransaccion) VALUES (?,?,?,?,?,?,?,?,?,?)");
            psInsertar.setInt(1, idTransaccion);
            psInsertar.setString(2, tipoTrans);
            psInsertar.setString(3, producto);
            psInsertar.setBigDecimal(4, monto);
            psInsertar.setBigDecimal(5, stock);
            psInsertar.setString(6, unidad);
            psInsertar.setString(7, proveedor);
            psInsertar.setString(8, fecha);
            java.sql.Date fechaSQL = new java.sql.Date(new java.util.Date().getTime());
            psInsertar.setDate(9, fechaSQL);
            psInsertar.setString(10, mLogueo.oL.getUsuario());           
            psInsertar.executeUpdate();
        } catch (SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }

    public Object[][] ListarTransacciones() {

        try {
            con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            st = con.createStatement();
            Statement st2 = con.createStatement();
            rs = st.executeQuery("SELECT * FROM transacciones WHERE Producto='" + producto + "'");
            List<Object[]> precioList = new ArrayList<>();

            while (rs.next()) {
                Object[] prec = new Object[8];
                prec[0] = rs.getString("TipoTransaccion");
                prec[1] = rs.getBigDecimal("Monto"); 
                prec[2] = rs.getBigDecimal("Cantidad");
                prec[3] = rs.getString("Unidad");   
                prec[4] = rs.getString("Proveedor");   
                prec[5] = rs.getString("FechaCaducidad");   
                prec[6] = rs.getString("FechaTransaccion");   
                prec[7] = rs.getString("UsuarioTransaccion");
                precioList.add(prec);
            }
            return precioList.toArray(new Object[precioList.size()][8]);
        } catch (SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }
}
