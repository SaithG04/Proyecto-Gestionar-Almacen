package Metodos;

import Clases.cAdministradorProductos;
import Clases.cAlertas;
import Clases.cPrecio;
import Formularios.frmAdministradorProductos;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mProductos extends mGenerales {

    private final JButton btnRP;
    private final ButtonGroup bg1, bg2, bg3;
    private final JComboBox<String> cbCtg, cbProd, cbProv;
    private final JTable productos;
    private final JPopupMenu pmOpc;
    private final JRadioButton rbElegir1, rbElegir2, rbEscribir1, rbEscribir2, rbKilos, rbUnidades;
    private final JTextField cantKilo, cantUni, categoria, fecha, precio, producto;
    private final frmAdministradorProductos fAP;
    private final cAlertas oA = new cAlertas();

    cAdministradorProductos oProductos = new cAdministradorProductos();
    cPrecio oP = new cPrecio();
    DefaultTableModel modelo;
    Object[] c = new Object[3];

    public mProductos() {
        fAP = new frmAdministradorProductos();
        btnRP = fAP.getBtnRProducto();
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
            }
        });
        rbKilos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected() || rbEscribir2.isSelected())) {
                    rbKilos.setEnabled(true);
                    rbKilos.setSelected(true);
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
                    rbUnidades.setSelected(true);
                    rbKilos.setEnabled(false);
                    cantKilo.setEnabled(false);
                    cantUni.setEnabled(true);
                }
            }
        });
        cbProd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (cbProd.isEnabled()) {
                    cbProd.removeAllItems();
                    if (cbCtg.getSelectedIndex() == -1) {
                        oA.error("Eliga una categoria primero.", "");
                    } else {
                        oProductos.setCategoria(cbCtg.getSelectedItem().toString());
                        MostrarProductosCB();
                    }
                }
            }
        });
        cbCtg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbProd.removeAllItems();
                cbProd.setSelectedItem(null);
            }
        });
        btnRP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (Validar()) {
                    String proveedorS = cbProv.getSelectedItem().toString();

                    if (rbUnidades.isSelected()) {
                        oProductos.setCantidad(new BigDecimal(cantUni.getText()));
                        oP.setUnidad("Und.");
                    } else {
                        oProductos.setCantidad(new BigDecimal(cantKilo.getText()));
                        oP.setUnidad("Kg.");
                    }

                    if (rbElegir1.isSelected() && rbElegir2.isSelected()) {
                        // Un producto existente tendrá un nuevo proveedor
                        String productoS = cbProd.getSelectedItem().toString();
                        oProductos.setProducto(productoS);
                        oProductos.AgregarStock();
                        
                        oP.setIdPrecio(0);
                        oP.setIdProducto(productoS);
                        oP.setIdproveedor(proveedorS);
                        oP.setPrecio(new BigDecimal(precio.getText()));
                        oP.setFecha(fecha.getText());
                        oP.InsertarPrecio();
                    } else if (rbEscribir2.isSelected() && rbElegir1.isSelected()) {
                        // Nuevo producto de categoría existente                  
                        oProductos.setProducto(producto.getText());
                        oProductos.setCategoria(cbCtg.getSelectedItem().toString());
                        oProductos.InsertarNuevo();

                        oP.setIdPrecio(0);
                        oP.setIdProducto(producto.getText());
                        oP.setIdproveedor(proveedorS);
                        oP.setPrecio(new BigDecimal(precio.getText()));
                        oP.setFecha(fecha.getText());
                        oP.InsertarPrecio();
                    } else if (rbEscribir2.isSelected() && rbEscribir1.isSelected()) {
                        // Nuevo producto de nueva categoría
                        oProductos.setProducto(producto.getText());
                        oProductos.setCategoria(categoria.getText());
                        oProductos.InsertarNuevo();

                        oP.setIdPrecio(0);
                        oP.setIdProducto(producto.getText());
                        oP.setIdproveedor(proveedorS);
                        oP.setPrecio(new BigDecimal(precio.getText()));
                        oP.setFecha(fecha.getText());
                        oP.InsertarPrecio();
                    }
                    Limpiar();
                    MostrarProductos();
                    MostrarCategorias();
                }

            }
        });
        precio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected()
                        || rbEscribir2.isSelected()) && (rbUnidades.isSelected() || rbKilos.isSelected())) {
                    precio.setEnabled(true);
                }
            }
        });
        fecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected()
                        || rbEscribir2.isSelected()) && (rbUnidades.isSelected() || rbKilos.isSelected())) {
                    fecha.setEnabled(true);
                }
            }
        });
    }

    void KeyListeners() {
        cantUni.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarSoloNumeros(evt, cantUni.getText(), (short) 7);
            }
        });
        cantKilo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarValorDecimal(evt, cantKilo.getText());
            }
        });
        precio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarValorDecimal(evt, precio.getText());
            }
        });
    }

    public final void MostrarProveedores() {
        List<String> ListarProveedores = oProductos.ListarProveedores();
        for (String elemento : ListarProveedores) {
            cbProv.addItem(elemento);
        }
        cbProv.setSelectedItem(null);
    }

    public final void MostrarCategorias() {
        List<String> ListarCategorias = oProductos.ListarCategorias();
        cbCtg.removeAllItems();
        for (String elemento : ListarCategorias) {
            cbCtg.addItem(elemento);
        }
        cbCtg.setSelectedItem(null);
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

    public void MostrarProductosCB() {
        List<String> ListarProductosPorCategoria = oProductos.ListarProductosPorCategoria();
        if (ListarProductosPorCategoria.isEmpty()) {
            oA.error("No hay productos en esta categoría.", "");
        } else {
            for (String elemento : ListarProductosPorCategoria) {
                cbProd.addItem(elemento);
            }
            cbProd.setSelectedItem(null);
        }
    }

    void opciones() {
        JMenuItem modificarItem = new JMenuItem("Modificar");
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        JMenuItem verInfoItem = new JMenuItem("Ver información del producto");

        pmOpc.add(modificarItem);
        pmOpc.add(eliminarItem);
        pmOpc.add(verInfoItem);

        productos.setComponentPopupMenu(pmOpc);

        modificarItem.addActionListener((ActionEvent e) -> {
            int fila = productos.getSelectedRow();
            if (fila != -1) {
                try {
                    rbEscribir1.setEnabled(false);
                    rbEscribir2.setEnabled(false);
                    rbElegir1.setEnabled(false);
                    precio.setEnabled(false);
                    fecha.setEnabled(false);

                    String entrada = oA.entrada("Ingrese cantidad de productos salientes:", "");
                    if (!entrada.isEmpty()) {
                        BigDecimal cantidad = new BigDecimal(entrada);
                        oProductos.setCantidad(cantidad);
                        oProductos.setProducto(productos.getValueAt(fila, 1).toString());
                        oProductos.DisminuirStock();
                        Limpiar();
                        MostrarProductos();
                    }
                } catch (NumberFormatException ex) {
                    oA.error("El valor ingresado es inválido", ex.getMessage());
                }
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });

        eliminarItem.addActionListener((ActionEvent e) -> {
            int fila = productos.getSelectedRow();
            if (fila != -1) {
                int confirmación = oA.confirmación("¿Está seguro de eliminar?");
                if (confirmación == 0) {
                    String ID = productos.getValueAt(fila, 0).toString();
                    oProductos.setIdProducto(Integer.parseInt(ID));
                    oProductos.EliminarProducto();
                    oA.aviso("Producto eliminado.");
                    Limpiar();
                    MostrarProductos();
                    MostrarCategorias();
                }
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });

        verInfoItem.addActionListener((ActionEvent e) -> {
            int fila = productos.getSelectedRow();
            if (fila != -1) {
                String nproducto = productos.getValueAt(fila, 1).toString();
                oP.setIdProducto(nproducto);
                new mPrecio(oP).CargarFrame();
            } else {
                oA.error("Seleccione una fila primero.", "");
                //FALTA AÑADIR STOCKS AGREGADO POR PRECIOS
            }
        });
    }
    
    public boolean Validar() {

        if (!(precio.isEnabled()) || !(fecha.isEnabled())
                || (cbProv.getSelectedIndex() == -1) || precio.getText().isEmpty() || fecha.getText().isEmpty()) {
            oA.error("Faltan datos.", "");
            return false;
        }
        return true;

    }

    void Limpiar() {
        bg1.clearSelection();
        bg2.clearSelection();
        bg3.clearSelection();
//        rbElegir2.setEnabled(false);
//        rbUnidades.setEnabled(false);
//        rbKilos.setEnabled(false);
//        producto.setEnabled(false);
//        precio.setEnabled(false);
//        cantKilo.setEnabled(false);
//        fecha.setEnabled(false);
//        cbProd.setEnabled(false);
//        cbCtg.setEnabled(false);
        categoria.setText(null);
        producto.setText(null);
        cantUni.setText(null);
        cantKilo.setText(null);
        precio.setText(null);
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
        fAP.setTitle("Productos");
        MostrarProveedores();
        MostrarCategorias();
        opciones();
        MouseListeners();
        KeyListeners();
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
