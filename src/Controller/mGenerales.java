package Controller;

import Model.cAlertas;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public abstract class mGenerales {

    public static final Image IMG = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/logo.jpg"));

    public static final String CREARUSER = "INSERT INTO usuarios(nombres,apellidos,username,password,tipo_usuario,telefono,correo,"
            + "id_admin,fecha_registro) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String ACTUALIZARUSER = "UPDATE usuarios SET nombres=?,apellidos=?,username=?,password=?,tipo_usuario=?,"
            + "telefono=?,correo=?,id_admin=?,fecha_registro=? WHERE id=?";
    public static final Color COLORERROR = new Color(255, 153, 153);
    public final cAlertas oA = new cAlertas();
    
    public abstract void CargarFrame();

    public abstract void Close();

    //Método para obtener atributos de la imagen como "Icon".
    public static Icon icono(String path, int width, int height) {
        Image img = new ImageIcon(mGenerales.class.getResource(path))
                .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        Icon ic = new ImageIcon(img);
        return ic;
    }

    //Método para restringir solo carácteres alfabéticos
    public void ValidarSoloLetras(KeyEvent evt, String txt, short max) {

        int key = evt.getKeyChar();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;
        if (!(minusculas || mayusculas || espacio) || txt.length() >= max) {
            evt.consume();
        }
    }

    //Método para restringir solo carácteres numéricos
    public void validarSoloNumeros(KeyEvent evt, String txt, short max) {

        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros || (txt.length() >= max)) {
            evt.consume();
        }
    }

    //Método para restringir solo carácteres numéricos decimales
    public static void validarValorDecimal(KeyEvent evt, String txt) {
        char key = evt.getKeyChar();
        boolean numeros = key >= '0' && key <= '9';
        if (key == '.') {
            if (txt.length() == 0) {
                evt.consume();
            } else {
                int contador = 0;
                for (int i = 0; i < txt.length(); i++) {
                    if (txt.charAt(i) == '.') {
                        contador++;
                    }
                }
                if (contador > 0) {
                    evt.consume();
                }
            }
        } else {
            if (!numeros || (txt.length() >= 11)) {
                evt.consume();
            }
        }
    }

    //Método para li0mitar cantidad de digitos
    public void validarLength(KeyEvent evt, String txt, short max) {
        if (txt.length() == max) {
            evt.consume();
        }
    }
}
