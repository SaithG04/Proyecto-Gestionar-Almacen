package Controller;

import View.frmAdministrador;
import Model.cAdministrador;
import Model.cAlertas;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author isai_
 */
public class mAdministrador extends mGenerales {

    private final cAlertas oA = new cAlertas();
    private final cAdministrador oAd = new cAdministrador();
    private final frmAdministrador a;
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnGestionarUsuarios, btnNuevoUsuario, btnProductos, btnProveedores;
    boolean value = false;

    public mAdministrador() {
        a = new frmAdministrador();
        lblTitle = a.getLblTitle();
        btnCerrarSesion = a.getBtnCerrarSesion();
        btnGestionarUsuarios = a.getBtnGestionarUsuarios();
        btnNuevoUsuario = a.getBtnNuevoUsuario();
        btnProductos = a.getBtnProductos();
        btnProveedores = a.getBtnProveedores();
    }

//    public boolean ComprobarConexion() {
//        try {
//                Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//        } catch (ClassNotFoundException | SQLException e) {
//            oA.errorC(e.toString());
//            return false;
//        }
//        return true;
//    }

    @Override
    public final void Close() {
        a.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if(!value){
                    if(oA.confirmación("¿Salir de la aplicación?") == 0){
                        System.exit(0);
                    }
                }
            }
        });
    }

    public final void Clicks() {
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(oA.confirmación("¿Cerrar sesión?") == 0){
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
                if (oAd.ComprobarConexion()) {
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
                if (oAd.ComprobarConexion()) {
                    a.dispose();
                    new mProveedores().CargarFrame();
                } else {
                    new mLogueo().CargarFrame();
                    a.dispose();
                }
            }
        });
        btnProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (oAd.ComprobarConexion()) {
                    a.dispose();
                    new mProductos().CargarFrame();
                } else {
                    a.dispose();
                    new mLogueo().CargarFrame();
                }
            }
        });
        btnNuevoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (oAd.ComprobarConexion()) {                    
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
        lblTitle.setText("Has iniciado sesión como: "+mLogueo.oL.getUsuario().toUpperCase());
    }
}
