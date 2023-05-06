package Metodos;

import Formularios.frmSalir;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author isai_
 */
public class mSalir {

    private final frmSalir os;
    private final JButton btnClose, btnConfirmar;
    public static boolean value = false;
    private final JFrame frmI;
    private final String frame;

    public mSalir(JFrame frmI, String frame) {
        this.frmI = frmI;
        this.frame = frame;
        os = new frmSalir();
        btnClose = os.getBtnClose();
        btnConfirmar = os.getBtnConfirmar();
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public void CargarFrame() {
        os.setVisible(true);
        os.setLocationRelativeTo(null);
        os.setResizable(false);
        Toolkit.getDefaultToolkit().beep();
        Clicks();
    }

    void Clicks() {
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                os.dispose();
            }
        });
        btnConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frmI.dispose();
                os.dispose();
                switch (frame) {
                    case "administrador":
                        new mAdministrador().CargarFrame();
                        break;
                    case "sistema":
                        System.exit(0);
                    case "gestUsuarios":
                        new mGestionarUsuarios().CargarFrame();
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
