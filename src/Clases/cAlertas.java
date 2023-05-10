package Clases;

import Metodos.mGenerales;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class cAlertas{

    private final String b[] = {"Aceptar"};
    private final String b2[] = {"Aceptar", "Cancelar"};
    private static final String ERRORCONECTION
            = "Communications link failure\n"
            + "\nThe last packet sent successfully to the server was 0 milliseconds ago. "
            + "The driver has not received any packets from the server."; //Mensaje por defecto de error de conexion

    public void error(String mensaje, String error) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, mensaje, " Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, mGenerales.icono("/Resources/den.png", 30, 30), b, b[0]);
        if(!error.isEmpty()) System.err.println(error);
    }

    public void errorC(String error) {
        if (error.equals(ERRORCONECTION)) {
            Toolkit.getDefaultToolkit().beep();
            error("No se ha podido conectar a la base de datos.", error);
        } else {
            error("Error desconocido.", error);
            Toolkit.getDefaultToolkit().beep();
            System.err.println(error); //Muestra error de inicio de sesión
        }
    }

    public void aviso(String mensaje) {
        JOptionPane.showOptionDialog(null, mensaje, " Aviso", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, mGenerales.icono("/Resources/profesionales.png", 30, 30), b, b[0]);
    }
    
    public void info(String mensaje) {
//        JOptionPane.showOptionDialog(null, mensaje, " Información", JOptionPane.DEFAULT_OPTION,
//                JOptionPane.PLAIN_MESSAGE, icono("/Resources/96094.jpg", 30, 30), b, b[0]);
        JOptionPane.showMessageDialog(null, mensaje, " Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void faltanDatos() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, "Faltan datos.", " Advertencia", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, mGenerales.icono("/Resources/den.png", 30, 30), b, b[0]);
    }

    public int confirmación(String txt) {
        Toolkit.getDefaultToolkit().beep();
        return JOptionPane.showOptionDialog(null, txt, " Confirmar", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, mGenerales.icono("/Resources/den.png", 30, 30), b2, b2[1]);
    }

    public String entrada(String mensaje, String titulo) {
        // Crear un panel personalizado con un campo de texto y un mensaje
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensaje));
        JTextField textField = new JTextField(10);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                mGenerales.validarValorDecimal(evt, textField.getText());
            }
        });

        panel.add(textField);
        String texto = "";

        // Mostrar el cuadro de diálogo de entrada con el panel personalizado
        int option = JOptionPane.showOptionDialog(null, panel, titulo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        // Obtener el valor ingresado por el usuario
        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        }
        return texto;
    }
    
}
