package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class cUsuarioProveedores {
    
    private String columna;
    private String categoria;
    
    cLogueo cn; //Creo una variable de la clase Conexion
    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    
    public cUsuarioProveedores(){ 
//        cn = new cLogueo(); //Conectar cn a la base      
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public ResultSet ListarColumnas() throws SQLException, ClassNotFoundException { 
              
//        con = cn.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'proveedores';");

        return rs;  
    }
    
    public ResultSet Buscar() throws ClassNotFoundException, SQLException{
//        con = cn.Conectar();
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM proveedores WHERE "+columna+" LIKE '"+categoria+"%'");
        return rs;
    }
    
}