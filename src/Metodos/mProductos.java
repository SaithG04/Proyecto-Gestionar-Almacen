package Metodos;

import Clases.cAdministradorProductos;
import Clases.cAlertas;
import Clases.cInfoProductos;
import Formularios.frmAdministradorProductos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mProductos extends mGenerales {

    private final JButton btnRP, btnRC;
    private final ButtonGroup bg1, bg2, bg3;
    private final JComboBox<String> cbCtg, cbProd, cbProv;
    private final JTable productos;
    private final JPopupMenu pmOpc;
    private final JRadioButton rbElegir1, rbElegir2, rbEscribir1, rbEscribir2, rbKilos, rbUnidades;
    private final JTextField cantKilo, cantUni, categoria, fecha, id, precio, producto;
    private final frmAdministradorProductos fAP;
    private final cAlertas oA = new cAlertas();

    ResultSet rsProductos, rsProveedores, rsCategorias, rsIndice, rsCantidad, rsPrecio;
    cAdministradorProductos oProductos = new cAdministradorProductos();
    cInfoProductos oInfo = new cInfoProductos();
    DefaultTableModel modelo;
    Object[] c = new Object[3];
//    ArrayList<String> productos, categorias, prov;

    public mProductos() {
        fAP = new frmAdministradorProductos();
        btnRP = fAP.getBtnRProducto();
        btnRC = fAP.getBtnRCategoria();
        bg1 = fAP.getbGrupo1();
        bg2 = fAP.getbGrupo2();
        bg3 = fAP.getbGrupo3();
        cbCtg = fAP.getCbCategoria();
        cbProd = fAP.getCbProducto();
        cbProv = fAP.getCbProveedor();
        productos = fAP.getJtbProductos();
        pmOpc = fAP.getPmOpciones();
        rbElegir1 = fAP.getRbElegir1();
        rbElegir2 = fAP.getRbElegir2();
        rbEscribir1 = fAP.getRbEscribir1();
        rbEscribir2 = fAP.getRbEscribir2();
        rbKilos = fAP.getRbKilos();
        rbUnidades = fAP.getRbUnidades();
        cantKilo = fAP.getTxtCKilogramos();
        cantUni = fAP.getTxtCUnidades();
        categoria = fAP.getTxtCategoria();
        fecha = fAP.getTxtFecha();
        id = fAP.getTxtId();
        precio = fAP.getTxtPrecio();
        producto = fAP.getTxtProducto();
    }

    void MouseListeners() {
        rbElegir1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbCtg.setEnabled(true);
                categoria.setEnabled(false);
                rbElegir2.setEnabled(true);
            }
        });
        rbElegir2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (rbElegir2.isEnabled()) {
                    cbProd.setEnabled(true);
                    producto.setEnabled(false);
                    id.setEnabled(false);
                }
            }
        });
        rbEscribir1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbCtg.setEnabled(false);
                categoria.setEnabled(true);
                rbElegir2.setEnabled(false);
                cbProd.setEnabled(false);
            }
        });
        rbEscribir2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbProd.setEnabled(false);
                producto.setEnabled(true);
                id.setEnabled(true);
            }
        });
        rbKilos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected() || rbEscribir2.isSelected())) {
                    rbKilos.setEnabled(true);
                    rbUnidades.setEnabled(false);
                    cantKilo.setEnabled(true);
                    cantUni.setEnabled(false);
                }
            }
        });
        rbUnidades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected() || rbEscribir2.isSelected())) {
                    rbUnidades.setEnabled(true);
                    rbKilos.setEnabled(false);
                    cantKilo.setEnabled(false);
                    cantUni.setEnabled(true);
                }
            }
        });
        rbEscribir1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

            }
        });
        rbEscribir1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

            }
        });

    }

    public void MostrarProductos() {
        modelo = (DefaultTableModel) productos.getModel();
        modelo.setRowCount(0);
        Object[][] prod = oProductos.ListarProductos();
        for (Object[] fila : prod) {
            modelo.addRow(fila);
        }
        productos.setModel(modelo);
    }

    public void ListarProductos() {
        Object[][] ListarProductos = oProductos.ListarProductos();
        for (Object[] prod : ListarProductos) {
            cbProd.addItem(prod[1].toString());
        }

        if (ListarProductos.length == 0) {
            oA.error("No hay productos en esta categoría.", "");
        } else {
            cbProd.setSelectedItem(null);
        }
    }

    public void ContinuarRegistro() {
        oProductos.setPrecio(precio.getText());
        oProductos.setIdProducto(Integer.parseInt(id.getText()));
        oProductos.setFechaCaducidad(fecha.getText());
        oProductos.setProveedor(cbProv.getSelectedItem().toString());
    }

    public void Limpiar() {
        bg1.clearSelection();
        bg2.clearSelection();
        bg3.clearSelection();
        rbElegir2.setEnabled(false);
        rbUnidades.setEnabled(false);
        rbKilos.setEnabled(false);
        producto.setEnabled(false);
        precio.setEnabled(false);
        id.setEnabled(false);
        cantKilo.setEnabled(false);
        fecha.setEnabled(false);
        cbProd.setEnabled(false);
        categoria.setText(null);
        producto.setText(null);
        cantUni.setText(null);
        cantKilo.setText(null);
        precio.setText(null);
        id.setText(null);
        fecha.setText(null);
        cbCtg.setSelectedIndex(-1);
        cbProd.setSelectedIndex(-1);
        cbProv.setSelectedIndex(-1);
    }

    @Override
    public void CargarFrame() {
        fAP.setVisible(true);
        MostrarProductos();
        fAP.setLocationRelativeTo(null);
//        MostrarProveedores();
//        MostrarCategorias();
//        Opciones();
        Close();
    }

    @Override
    public void Close() {
        fAP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                oA.confCerrar(fAP, "administrador");
            }
        });
    }
}
