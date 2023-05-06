package Metodos;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 *
 * @author isai_
 */
public abstract class mGenerales {
    
    
    public static final Image IMG = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/logo.jpg"));
    
    public static final String CREARUSER = "INSERT INTO usuarios(id_usuario,nombres,apellidos,username,contraseña,tipo_usuario,telefono,correo) VALUES (?,?,?,?,?,?,?,?)";
    public static final String ACTUALIZARUSER = "UPDATE usuarios SET nombres=?,apellidos=?,username=?,contraseña=?,tipo_usuario=?,telefono=?,correo=? WHERE id_usuario=?";
    public static final Color COLORERROR = new Color(255,153,153);
    public static final String USER = "";
    public static final String PASSWORD ="";
    
    public abstract void CargarFrame();

    public abstract void Close();
    
    //Método para obtener atributos de la imagen como "Icon".
    public Icon icono(String path, int width, int height){      
        Icon ic = new ImageIcon(new ImageIcon(this.getClass().getResource(path))
        .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        return ic;
    }
    
    //Método para restringir solo carácteres alfabéticos
    public void validarSoloLetras(KeyEvent evt, String txt, short max){
        
        int key = evt.getKeyChar();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;
        if (!(minusculas || mayusculas || espacio) || txt.length()>=max ) evt.consume();
    }
    
    //Método para restringir solo carácteres numéricos
    public void validarSoloNumeros(KeyEvent evt, String txt, short max){
        
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros || (txt.length()>=max)) evt.consume();
    }
    
    //Método para li0mitar cantidad de digitos
    public  void validarLength(KeyEvent evt, String txt, short max){
        if (txt.length() == max) {
            evt.consume();
        }
    }
}