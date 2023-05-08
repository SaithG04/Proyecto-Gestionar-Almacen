package Metodos;

import Clases.cAlertas;
import Clases.cGestionarUsuarios;
import Formularios.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mGestionarUsuarios extends mGenerales {

    private final cAlertas oA = new cAlertas();
    private final cGestionarUsuarios oGU = new cGestionarUsuarios();
    private mRegistroUsuarios ru;
    private final frmAdministradorGestionarUsuarios fagu;
    private final JTable usuarios;
    private final JPopupMenu pmOpciones;

    public mGestionarUsuarios() {
        ru = null;
        fagu = new frmAdministradorGestionarUsuarios();
        usuarios = fagu.getJtbUsuarios();
        pmOpciones = fagu.getPmOpciones();
    }

    public void dobleClick() {
        usuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String dato = usuarios.getValueAt(usuarios.getSelectedRow(), usuarios.getSelectedColumn()).toString();
                    if (usuarios.getSelectedRow() != -1) {
                        JOptionPane.showMessageDialog(null, dato);
                    }
                }
            }
        });
    }

    public void Modificar() {
        switch (usuarios.getValueAt(usuarios.getSelectedRow(), 5).toString()) {
            case "administrador":
                ru.admin.setSelected(true);
                ru.usuario.setEditable(false);
                LlenarCampos();
                break;
            case "usuario":
                ru.user.setSelected(true);
                ru.usuario.setEditable(false);
                LlenarCampos();
                break;
        }
    }

    public void LlenarCampos() {
        int fila = usuarios.getSelectedRow();
        ru.id.setText(usuarios.getValueAt(fila, 0).toString());
        ru.nombres.setText(usuarios.getValueAt(fila, 1).toString());
        ru.apellidos.setText(usuarios.getValueAt(fila, 2).toString());
        ru.telefono.setText(usuarios.getValueAt(fila, 6).toString());
        ru.correo.setText(usuarios.getValueAt(fila, 7).toString());
        ru.usuario.setText(usuarios.getValueAt(fila, 3).toString());
        ru.contraseña.setText(null);
        ru.aceptar.setText("MODIFICAR");
        ru.fru.setTitle("Modificar usuarios");
    }

    public final void Opciones() {
        JMenuItem upd = new JMenuItem("Modificar");
        JMenuItem dlt = new JMenuItem("Eliminar");

        pmOpciones.add(upd);
        pmOpciones.add(dlt);

        usuarios.setComponentPopupMenu(pmOpciones);

        upd.addActionListener((ActionEvent e) -> {
            if (usuarios.getSelectedRow() != -1) {

                if (!usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString().equals(mLogueo.oL.getUsuario())) {                   
                    if (oGU.Conectado()) {
                        ru = new mRegistroUsuarios();
                        ru.CargarFrame();
                        Modificar();
                        fagu.dispose();
                    } else {
                        new mLogueo().CargarFrame();
                        fagu.dispose();
                    }
                } else {
                    if (oGU.Conectado()) {
                        ru = new mRegistroUsuarios();
                        ru.CargarFrame();
                        Modificar();
                        ru.usuario.setEditable(false);
                        ru.admin.setEnabled(false);
                        ru.user.setEnabled(false);
                        fagu.dispose();
                    } else {
                        new mLogueo().CargarFrame();
                        fagu.dispose();
                    }
                }
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });
        dlt.addActionListener((ActionEvent e) -> {
            if (usuarios.getSelectedRow() != -1) {
                if (!usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString().equals(mLogueo.oL.getUsuario())) {
                    if (oGU.Conectado()) {
                        if (oA.confirmación("¿Eliminar usuario?") == 0) {
                            String user = usuarios.getValueAt(usuarios.getSelectedRow(), 3).toString();
                            oGU.EliminarUsuario(user);
                            oA.aviso("Usuario eliminado.");
                            oGU.MostrarUsuarios(usuarios.getModel());
                        }
                    } else {
                        new mLogueo().CargarFrame();
                        fagu.dispose();
                    }
                } else {
                    oA.error("Cierre sesión primero.", "");
                }
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });
    }

    @Override
    public final void Close() {
        fagu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                new mAdministrador().CargarFrame();
                fagu.dispose();
            }
        });
    }

    @Override
    public void CargarFrame() {
        fagu.setVisible(true);
        fagu.setLocationRelativeTo(null);
        Close();
        oGU.MostrarUsuarios(usuarios.getModel());
        Opciones();
        dobleClick();
    }
}
