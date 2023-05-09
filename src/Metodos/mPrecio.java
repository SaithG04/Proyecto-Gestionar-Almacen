package Metodos;

import Clases.cAlertas;
import Clases.cPrecio;
import Formularios.infoProductos;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mPrecio extends mGenerales {

    private final cPrecio oP;
    DefaultTableModel modelo;
    private final infoProductos fP;
    private final JTable precios;
    cAlertas oA = new cAlertas();

    public mPrecio(cPrecio oP) {
        fP = new infoProductos();
        precios = fP.getJtbPrecios();
        this.oP = oP;
    }

    void MostrarPrecios() {
        modelo = (DefaultTableModel) precios.getModel();
        modelo.setRowCount(0);
        Object[][] prec = oP.ListarPrecios();
        if (prec != null) {
            for (Object[] fila : prec) {
                modelo.addRow(fila);
            }
        }
        precios.setModel(modelo);
    }

    @Override
    public void CargarFrame() {
        MostrarPrecios();
        if (precios.getRowCount() == 0) {
            oA.error("Aun no hay precios registrados.", "");
            fP.dispose();
        } else {
            fP.setVisible(true);
            fP.setLocationRelativeTo(null);
            fP.setAlwaysOnTop(true);
            fP.setResizable(false);
            precios.setEnabled(false);
        }
    }

    @Override
    public void Close() {

    }

}
