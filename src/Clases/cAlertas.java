package Clases;

import Metodos.mGenerales;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class cAlertas extends mGenerales{

    private final String b[] = {"Aceptar"};
    private final String b2[] = {"Aceptar", "Cancelar"};
    private static final String ERRORCONECTION
            = "com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure\n"
            + "\nThe last packet sent successfully to the server was 0 milliseconds ago. "
            + "The driver has not received any packets from the server."; //Mensaje por defecto de error de conexion
//    private final mGenerales oG = new mGenerales();

    public void error(String mensaje, String error) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, mensaje, " Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, icono("/Resources/den.png", 30, 30), b, b[0]);
        System.err.println(error);
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
                JOptionPane.PLAIN_MESSAGE, icono("/Resources/profesionales.png", 30, 30), b, b[0]);
    }

    public void faltanDatos() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, "Faltan datos.", " Advertencia", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, icono("/Resources/den.png", 30, 30), b, b[0]);
    }

    public int confCerrar(String mensaje) {
        Toolkit.getDefaultToolkit().beep();
        return JOptionPane.showOptionDialog(null, " ¿Cerrar?", " Confirmar", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, icono("/Resources/den.png", 30, 30), b2, b2[1]);
    }

    @Override
    public void CargarFrame() {}

    @Override
    public void Close() {}
}
