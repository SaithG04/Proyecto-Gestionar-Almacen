package Clases;

import java.sql.*;

/**
 *
 * @author isai_
 */
public class cSQL {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String BD = "jdbc:mysql://localhost/proyectoFinal";
    private Connection conex;
    private final cAlertas oA = new cAlertas();

    //Conector a la base de datos con usuario y contraseña para validar permisos
    public Connection Conectar(String usuario, String contraseña) {
        try {
            Class.forName(DRIVER); //Llamar al driver.
            conex = DriverManager.getConnection(BD, usuario, contraseña); //Establecer conexion
            return conex;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }

    //Conector a la base de datos por defecto
    public Connection Conectar() {
        try {
            //Connection solamente para buscar usuario.
            Class.forName(DRIVER); //Llamar al driver.
            conex = DriverManager.getConnection(BD, "root", ""); //Establecer conexion
            return conex;
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.getMessage());
        }
        return null;
    }
}
