package Metodos;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public abstract class mGenerales {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String BD = "jdbc:mysql://localhost/proyectoFinal";
    public static final Image IMG = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/logo.jpg"));
    private Connection conex;
    public static final String CREARUSER = "INSERT INTO usuarios(id_usuario,nombres,apellidos,username,contraseña,tipo_usuario,telefono,correo) VALUES (?,?,?,?,?,?,?,?)";
    public static final String ACTUALIZARUSER = "UPDATE usuarios SET nombres=?,apellidos=?,username=?,contraseña=?,tipo_usuario=?,telefono=?,correo=? WHERE id_usuario=?";
    public static final Color COLORERROR = new Color(255,153,153);
    public static final String USER = "";
    public static final String PASSWORD ="";
    
    public abstract void CargarFrame();

    public abstract void Close();
    
    //Conector a la base de datos con usuario y contraseña para validar permisos
    public Connection Conectar(String usuario, String contraseña) throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER); //Llamar al driver.
        conex = DriverManager.getConnection(BD,usuario,contraseña); //Establecer conexion
        return conex;
    }
    
    //Conector a la base de datos por defecto
    public Connection Conectar() throws ClassNotFoundException, SQLException{
        //Connection solamente para buscar usuario.
        Class.forName(DRIVER); //Llamar al driver.
        conex = DriverManager.getConnection(BD,"root",""); //Establecer conexion
        return conex;
    }
    
    //Método para obtener atributos de la imagen como "Icon".
    public Icon icono(String path, int width, int height){      
        Icon ic = new ImageIcon(new ImageIcon(this.getClass().getResource(path))
        .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return ic;
    }
    
    //Método para restringir solo carácteres alfabéticos
    public void validarSoloLetras(java.awt.event.KeyEvent evt, String txt, short max){
        
        int key = evt.getKeyChar();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;
        if (!(minusculas || mayusculas || espacio) || txt.length()>=max ) evt.consume();
    }
    
    //Método para restringir solo carácteres numéricos
    public void validarSoloNumeros(java.awt.event.KeyEvent evt, String txt, short max){
        
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros || (txt.length()>=max)) evt.consume();
    }
}