package Controller;

import View.frmAdministrador;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author isai_
 */
public class mAdministrador extends mGenerales {

    private final frmAdministrador a;
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnGestionarUsuarios, btnNuevoUsuario, btnProductos, btnProveedores, btnTransacciones;
    boolean value = false;

    public mAdministrador() {
        a = new frmAdministrador();
        lblTitle = a.getLblTitle();
        btnCerrarSesion = a.getBtnCerrarSesion();
        btnGestionarUsuarios = a.getBtnGestionarUsuarios();
        btnNuevoUsuario = a.getBtnNuevoUsuario();
        btnProductos = a.getBtnProductos();
        btnProveedores = a.getBtnProveedores();
        btnTransacciones = a.getBtnTransacciones();
    }

    @Override
    public final void Close() {
        a.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (!value) {
                    if (oA.confirmación("¿Salir de la aplicación?") == 0) {
                        System.exit(0);
                    }
                }
            }
        });
    }

    public final void MouseListeners() {
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (oA.confirmación("¿Cerrar sesión?") == 0) {
                    value = true;
                    a.dispose();
                    new mLogueo().CargarFrame();
                    value = false;
                }
            }
        });
        btnGestionarUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mGestionarUsuarios().CargarFrame();
            }
        });
        btnProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mProveedores().CargarFrame();
            }
        });
        btnProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mProductos().CargarFrame();
            }
        });
        btnNuevoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mRegistroUsuarios().CargarFrame();
            }
        });
        btnTransacciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mTransaccion().CargarTransacciones();
            }
        });
    }

    @Override
    public void CargarFrame() {
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        MouseListeners();
        Close();
        lblTitle.setText("Has iniciado sesión como: " + mLogueo.oL.getUsuario().toUpperCase());
    }
}
