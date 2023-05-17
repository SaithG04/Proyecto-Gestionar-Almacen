package Controller;

import Model.cTransaccion;
import View.frmTransacciones;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mTransaccion extends mGenerales {

    private final cTransaccion oP;
    private final frmTransacciones fP;
    private final JTable transacciones;
    final Class clase = mTransaccion.class;

    public mTransaccion(cTransaccion oP) {
        fP = new frmTransacciones();
        transacciones = fP.getJtbTransacciones();
        this.oP = oP;
    }

    public mTransaccion() {
        fP = new frmTransacciones();
        transacciones = fP.getJtbTransacciones();
        oP = new cTransaccion();
    }

    void MostrarTransacciones() throws ClassNotFoundException, SQLException {
        DefaultTableModel MostrarTransacciones = oP.MostrarTransacciones(transacciones.getModel());
        transacciones.setModel(MostrarTransacciones);
    }

    void MostrarHistorialTransacciones() throws ClassNotFoundException, SQLException {
        DefaultTableModel MostrarTransacciones = oP.MonstrarHistorialTransacciones(transacciones.getModel());
        transacciones.setModel(MostrarTransacciones);
    }

    @Override
    public void CargarFrame() {
        try {
            MostrarTransacciones();
            Close();
            if (transacciones.getRowCount() == 0) {
                oA.mostrarError(clase, "Aun no hay transacciones registradas.", null);
                fP.dispose();
            } else {
                fP.setVisible(true);
                fP.setLocationRelativeTo(null);
                fP.setAlwaysOnTop(true);
                fP.setResizable(false);
                transacciones.setEnabled(false);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            oA.manejarErrorConexion(clase, ex);
//            if(manejarErrorConexion) System.exit(0);
        }
    }

    public void CargarTransacciones() {
        try {
            MostrarHistorialTransacciones();
            Close2();
            fP.setVisible(true);
            fP.setLocationRelativeTo(null);
            fP.setAlwaysOnTop(true);
            fP.setResizable(false);
            transacciones.setEnabled(false);
        } catch (ClassNotFoundException | SQLException ex) {
            oA.manejarErrorConexion(clase, ex);
        }
    }

    @Override
    public void Close() {
        fP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                fP.dispose();
            }
        });
    }

    public void Close2() {
        fP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                fP.dispose();
                new mAdministrador().CargarFrame();
            }
        });
    }

}
