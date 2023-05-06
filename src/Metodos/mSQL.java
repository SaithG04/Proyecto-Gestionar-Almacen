package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author isai_
 */
public class mSQL {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String BD = "jdbc:mysql://localhost/proyectoFinal";
    private Connection conex;

    //Conector a la base de datos con usuario y contraseña para validar permisos
    public Connection Conectar(String usuario, String contraseña) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER); //Llamar al driver.
        conex = DriverManager.getConnection(BD, usuario, contraseña); //Establecer conexion
        return conex;
    }

    //Conector a la base de datos por defecto
    public Connection Conectar() throws ClassNotFoundException, SQLException {
        //Connection solamente para buscar usuario.
        Class.forName(DRIVER); //Llamar al driver.
        conex = DriverManager.getConnection(BD, "root", ""); //Establecer conexion
        return conex;
    }
}
