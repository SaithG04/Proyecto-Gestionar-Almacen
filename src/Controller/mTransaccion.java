package Controller;

import Model.cTransaccion;
import View.frmTransacciones;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    void MostrarTransacciones() throws ClassNotFoundException, SQLException {       
        DefaultTableModel MostrarProveedores = oP.MostrarProveedores(transacciones.getModel());
        transacciones.setModel(MostrarProveedores);
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
            Logger.getLogger(mTransaccion.class.getName()).log(Level.SEVERE, null, ex);
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

}
