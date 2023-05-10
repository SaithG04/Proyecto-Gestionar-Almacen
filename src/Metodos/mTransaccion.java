package Metodos;

import Clases.cAlertas;
import Clases.cTransaccion;
import Formularios.frmTransacciones;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mTransaccion extends mGenerales {

    private final cTransaccion oP;
    DefaultTableModel modelo;
    private final frmTransacciones fP;
    private final JTable transacciones;
    cAlertas oA = new cAlertas();

    public mTransaccion(cTransaccion oP) {
        fP = new frmTransacciones();
        transacciones = fP.getJtbTransacciones();
        this.oP = oP;
    }

    void MostrarTransacciones() {
        modelo = (DefaultTableModel) transacciones.getModel();
        modelo.setRowCount(0);
        Object[][] prec = oP.ListarTransacciones();
        if (prec != null) {
            for (Object[] fila : prec) {
                modelo.addRow(fila);
            }
        }
        transacciones.setModel(modelo);
    }

    @Override
    public void CargarFrame() {
        MostrarTransacciones();
        Close();
        if (transacciones.getRowCount() == 0) {
            oA.error("Aun no hay transacciones registradas.", "");
            fP.dispose();
        } else {
            fP.setVisible(true);
            fP.setLocationRelativeTo(null);
            fP.setAlwaysOnTop(true);
            fP.setResizable(false);
            transacciones.setEnabled(false);
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
