package Metodos;

import Clases.cAdministradorProductos;
import Clases.cAlertas;
import Clases.cTransaccion;
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
    private final JRadioButton rbElegir1, rbElegir2, rbEscribir1, rbEscribir2;
    private final JTextField stock, categoria, fecha, importe, producto, tipo;
    private final frmAdministradorProductos fAP;
    private final cAlertas oA = new cAlertas();
    private boolean procede = false;

    cAdministradorProductos oProductos = new cAdministradorProductos();
    cTransaccion oT = new cTransaccion();
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
        stock = fAP.getTxtStock();
        categoria = fAP.getTxtCategoria();
        fecha = fAP.getTxtFecha();
        importe = fAP.getTxtImporte();
        producto = fAP.getTxtProducto();
        tipo = fAP.getTxtTipo();
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
                    boolean OnlyProduc = !OnlyProduc();
                    if (OnlyProduc && procede) {
                        String proveedor = cbProv.getSelectedItem().toString();
                        BigDecimal cantidad = new BigDecimal(stock.getText());
                        BigDecimal Import = new BigDecimal(importe.getText());
                        String uni = tipo.getText();
                        oProductos.setUnidad(uni);

                        if (rbElegir1.isSelected() && rbElegir2.isSelected()) {
                            // Un producto existente tendrá una nueva transaccion
                            String prod = cbProd.getSelectedItem().toString();
                            oProductos.setProducto(prod);
                            oProductos.setCantidad(cantidad);
                            oProductos.AgregarStock();
                            InsertarTransaccionNueva(prod, proveedor, Import, cantidad, 
                                    tipo.getText(), "Compra",fecha.getText());
                        } else if (rbEscribir2.isSelected() && rbElegir1.isSelected()) {
                            // Nuevo producto de categoría existente con nueva transaccion
                            String categoria = cbCtg.getSelectedItem().toString();
                            String productoNuevo = producto.getText();
                            oProductos.setProducto(productoNuevo);
                            oProductos.setCategoria(categoria);
                            oProductos.setCantidad(cantidad);
                            oProductos.InsertarNuevo();
                            InsertarTransaccionNueva(productoNuevo, proveedor, Import, cantidad,
                                    tipo.getText(), "Compra",fecha.getText());
                        } else if (rbEscribir2.isSelected() && rbEscribir1.isSelected()) {
                            // Nuevo producto de nueva categoría con nueva transaccion 
                            String categoriaNueva = categoria.getText();
                            String productoNuevo = producto.getText();
                            oProductos.setProducto(productoNuevo);
                            oProductos.setCategoria(categoriaNueva);
                            oProductos.setCantidad(cantidad);
                            oProductos.InsertarNuevo();
                            InsertarTransaccionNueva(productoNuevo, proveedor, Import, cantidad,
                                    tipo.getText(), "Compra",fecha.getText());
                        }
                    } else if (!OnlyProduc && procede) {
                        if (rbEscribir2.isSelected() && rbElegir1.isSelected()) {
                            // Nuevo producto de categoría existente
                            String categoria = cbCtg.getSelectedItem().toString();
                            String productoNuevo = producto.getText();
                            oProductos.setProducto(productoNuevo);
                            oProductos.setCategoria(categoria);
                            oProductos.setCantidad(new BigDecimal(0));
                            oProductos.InsertarNuevo();
                            oA.aviso("Registro exitoso");
                            Limpiar();
                            MostrarProductos();
                            MostrarCategorias();
                        } else if (rbEscribir2.isSelected() && rbEscribir1.isSelected()) {
                            // Nuevo producto de nueva categoría
                            String categoriaNueva = categoria.getText();
                            String productoNuevo = producto.getText();
                            oProductos.setProducto(productoNuevo);
                            oProductos.setCategoria(categoriaNueva);
                            oProductos.setCantidad(new BigDecimal(0));
                            oProductos.InsertarNuevo();
                            oA.aviso("Registro exitoso");
                            Limpiar();
                            MostrarProductos();
                            MostrarCategorias();
                        }
                    }
                }
            }
        });
        importe.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt
            ) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected()
                        || rbEscribir2.isSelected())) {
                    importe.setEnabled(true);
                }
            }
        }
        );
        fecha.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt
            ) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected()
                        || rbEscribir2.isSelected())) {
                    fecha.setEnabled(true);
                }
            }
        }
        );
    }

    void KeyListeners() {
        stock.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarValorDecimal(evt, stock.getText());
            }
        });
        importe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarValorDecimal(evt, importe.getText());
            }
        });
        tipo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                ValidarSoloLetras(evt, tipo.getText(), (short) 10);
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

    private void InsertarTransaccionNueva(String producto, String proveedor, BigDecimal importe, BigDecimal stock,
            String unidad, String tipoTrans, String fechaC) {
        oT.setIdTransaccion(0);
        oT.setTipoTrans(tipoTrans);
        oT.setProducto(producto);
        oT.setUnidad(unidad);
        oT.setProveedor(proveedor);
        oT.setMonto(importe);
        oT.setFecha(fechaC);
        oT.setStock(stock);
        try {
            oT.InsertarTransaccion();
            oA.aviso("Stock modificado correctamente");
        } catch (Exception e) {
        }
        Limpiar();
        MostrarProductos();
        MostrarCategorias();
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
                    importe.setEnabled(false);
                    fecha.setEnabled(false);

                    String entrada = oA.entrada("Ingrese cantidad de productos salientes:", "");
                    if (!entrada.isEmpty()) {
                        BigDecimal cantidad = new BigDecimal(entrada);
                        oProductos.setCantidad(cantidad);
                        oProductos.setProducto(productos.getValueAt(fila, 1).toString());                      
                        oProductos.DisminuirStock();
                        String uni = productos.getValueAt(fila, 4).toString();
                        InsertarTransaccionNueva(oProductos.getProducto(), null,
                                new BigDecimal(0), cantidad, uni, "Venta", null);
//                        oT.InsertarTransaccion();
                        Limpiar();
                        MostrarProductos();
                    }
                } catch (Exception ex) {
                    oA.errorC(ex.getMessage());
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
                oT.setProducto(nproducto);
                new mTransaccion(oT).CargarFrame();
            } else {
                oA.error("Seleccione una fila primero.", "");
            }
        });
    }

    boolean E(JTextField tf) {
        return tf.getText().isEmpty();
    }

    boolean Validar() {
        boolean rbECS = rbElegir1.isSelected(); //Seleccionar categoria
        boolean rbEPS = rbElegir2.isSelected(); //Seleccionar producto
        boolean rbeCS = rbEscribir1.isSelected(); //Ingresar categoria
        boolean rbePS = rbEscribir2.isSelected(); //Ingresar producto
        boolean cS = cbCtg.getSelectedIndex() != -1;
        boolean pS = cbProd.getSelectedIndex() != -1;
        boolean ppS = cbProv.getSelectedIndex() != -1;
        boolean txtVacios = E(stock) && E(tipo) && E(importe) && E(fecha) && (cbProv.getSelectedIndex() == -1);
        boolean txtNoVacios = E(stock) || E(tipo) || E(importe) || E(fecha) || (cbProv.getSelectedIndex() == -1);
        boolean txtLlenos = !E(stock) && !E(tipo) && !E(importe) && !E(fecha) && (cbProv.getSelectedIndex() != -1);
        boolean comb = (rbECS && rbEPS) || (rbECS && rbePS) || (rbeCS && rbePS);

        if (comb && txtVacios) {
            return true; //Para crear un nuevo producto
        } else if ((comb && txtNoVacios) || (comb && txtLlenos)) {
            if (rbECS && rbEPS) { //Agregar stock de producto existente
                if (!cS) {
                    oA.error("Seleccione una categoría", "");
                } else if (!pS) {
                    oA.error("Seleccione un producto", "");
                } else if (E(stock)) {
                    oA.error("Ingrese stock de producto", "");
                } else if (E(tipo)) {
                    oA.error("Ingrese unidad de medida.", "");
                } else if (E(importe)) {
                    oA.error("Ingrese el monto.", "");
                } else if (E(fecha)) {
                    oA.error("Especifique fecha de caducidad.", "");
                } else if (!ppS) {
                    oA.error("Seleccione un proveedor", "");
                } else {
                    return true;
                }
            } else if (rbECS && rbePS) { //Agregar stock de nuevo producto de categoria existente
                if (!cS) {
                    oA.error("Seleccione una categoría", "");
                } else if (E(producto)) {
                    oA.error("Ingrese un producto", "");
                } else if (E(stock)) {
                    oA.error("Ingrese stock de producto", "");
                } else if (E(tipo)) {
                    oA.error("Ingrese unidad de medida.", "");
                } else if (E(importe)) {
                    oA.error("Ingrese el monto.", "");
                } else if (E(fecha)) {
                    oA.error("Especifique fecha de caducidad.", "");
                } else if (!ppS) {
                    oA.error("Seleccione un proveedor", "");
                } else {
                    return true;
                }
            } else { //Agregar stock de nuevo producto de nueva categoria
                if (E(categoria)) {
                    oA.error("Ingrese una categoría", "");
                } else if (E(producto)) {
                    oA.error("Ingrese un producto", "");
                } else if (E(stock)) {
                    oA.error("Ingrese stock de producto", "");
                } else if (E(tipo)) {
                    oA.error("Ingrese unidad de medida.", "");
                } else if (E(importe)) {
                    oA.error("Ingrese el monto.", "");
                } else if (E(fecha)) {
                    oA.error("Especifique fecha de caducidad.", "");
                } else if (!ppS) {
                    oA.error("Seleccione un proveedor", "");
                } else {
                    return true;
                }
            }
        } else if (!comb && txtLlenos) {
            oA.error("Faltan datos", "");
        } else {
            oA.error("Faltan datos", "");
        }
        return false;
    }

    boolean OnlyProduc() {

        procede = false;
        boolean ctgS = cbCtg.getSelectedIndex() != -1; //Categorias
        boolean pdtS = cbProd.getSelectedIndex() != -1; //Productos
        boolean rbECS = rbElegir1.isSelected(); //Seleccionar categoria
        boolean rbEPS = rbElegir2.isSelected(); //Seleccionar producto
        boolean rbeCS = rbEscribir1.isSelected(); //Ingresar categoria
        boolean rbePS = rbEscribir2.isSelected(); //Ingresar producto
        boolean txtVacios = E(stock) && E(tipo) && E(importe) && E(fecha) && (cbProv.getSelectedIndex() == -1);

        if (rbECS && rbEPS && txtVacios) {
            if (!ctgS) {
                oA.error("Seleccione una categoría", "");
            } else if (!pdtS) {
                oA.error("Seleccione un producto", "");
            } else {
                oA.error("El producto ya existe.", "");
            }
        } else if (rbECS && rbePS && txtVacios) { //Nuevo producto de categoria existente
            if (!ctgS) {
                oA.error("Seleccione una categoría", "");
            } else if (producto.getText().isEmpty()) {
                oA.error("Ingrese un producto", "");
            } else {
                procede = true;
                return true;
            }
        } else if (rbeCS && rbePS && txtVacios) { //Nuevo producto de nueva categoria
            if (E(categoria)) {
                oA.error("Ingrese una categoría", "");
            } else if (E(producto)) {
                oA.error("Ingrese un producto", "");
            } else {
                procede = true;
                return true;
            }
        } else {
            procede = true;
            return false;
        }
        return false;
    }

    void Limpiar() {
        bg1.clearSelection();
        bg2.clearSelection();
        bg3.clearSelection();
        rbElegir1.setEnabled(true);
        rbEscribir1.setEnabled(true);
        rbEscribir2.setEnabled(true);
        rbElegir2.setEnabled(false);
        producto.setEnabled(false);
        cbProd.setEnabled(false);
        cbCtg.setEnabled(false);
        categoria.setText(null);
        producto.setText(null);
        stock.setText(null);
        importe.setText(null);
        fecha.setText(null);
        tipo.setText(null);
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
        fAP.setResizable(false);
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
                if (oA.confirmación("¿Salir?") == 0) {
                    fAP.dispose();
                    new mAdministrador().CargarFrame();
                }
            }
        });
    }
}
