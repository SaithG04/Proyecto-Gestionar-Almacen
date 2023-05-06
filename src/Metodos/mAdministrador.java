package Metodos;

import Clases.cAlertas;
import Formularios.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author isai_
 */
public class mAdministrador extends mGenerales {

    private final cAlertas oA = new cAlertas();
    private final frmAdministrador a;
    private final JLabel lblUsuario;
    private final JButton btnCerrarSesion, btnGestionarUsuarios, btnNuevoUsuario, btnProductos, btnProveedores;

    public mAdministrador() {
        a = new frmAdministrador();
        lblUsuario = a.getLblUsuario();
        btnCerrarSesion = a.getBtnCerrarSesion();
        btnGestionarUsuarios = a.getBtnGestionarUsuarios();
        btnNuevoUsuario = a.getBtnNuevoUsuario();
        btnProductos = a.getBtnProductos();
        btnProveedores = a.getBtnProveedores();
    }

    public boolean ComprobarConexion() {
        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException e) {
            oA.errorC(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public final void Close() {
        a.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (oA.confCerrar(" ¿Cerrar?") == 0) {
                    System.exit(0);
                }
            }
        });
    }

    public final void Clicks() {
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (oA.confCerrar(" ¿Cerrar?") == 0) {
                    a.dispose();
                    new mLogueo().CargarFrame();
                }
            }
        });
        btnGestionarUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (ComprobarConexion()) {
                    a.dispose();
                    new mGestionarUsuarios().CargarFrame();                  
                } else {
                    a.dispose();
                    new mLogueo().CargarFrame();
                    
                }
            }
        });
        btnProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (ComprobarConexion()) {
                    a.dispose();
//                    new frmAdministradorProveedores().setVisible(true);             
                } else {
                    new mLogueo().CargarFrame();
                    a.dispose();
                }
            }
        });
        btnProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (ComprobarConexion()) {
                    a.dispose();
//                    new frmAdministradorProductos().setVisible(true);
                } else {
                    a.dispose();
                    new mLogueo().CargarFrame();
                }
            }
        });
        btnNuevoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (ComprobarConexion()) {                    
                    a.dispose();
                    new mRegistroUsuarios().CargarFrame();
                } else {                
                    a.dispose();
                    new mLogueo().CargarFrame();
                }
            }
        });
    }

    @Override
    public void CargarFrame() {
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        Clicks();
        Close();
        lblUsuario.setText(mLogueo.oL.getUsuario().toUpperCase());
    }
}
