package Metodos;

import Clases.cAdministradorProveedores;
import Clases.cAlertas;
import Formularios.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mProveedores extends mGenerales {

    private final cAlertas oA = new cAlertas();
    cAdministradorProveedores oProveedores = new cAdministradorProveedores();

    Object[] c = new Object[1];

    private final frmAdministradorProveedores fap;
    private final JButton agregar, eliminar, limpiar, modificar;
    private final JTextField id, razonSocial, ruc, direccion, contacto, telefono, email;
    private final JTable proveedores;
    private final JComboBox<String> departamentos;

    public mProveedores() {
        fap = new frmAdministradorProveedores();
        agregar = fap.getBtnAgregar();
        eliminar = fap.getBtnEliminar();
        limpiar = fap.getBtnLimpiar();
        modificar = fap.getBtnModificar();
        id = fap.getTxtId();
        razonSocial = fap.getTxtRazonSocial();
        ruc = fap.getTxtRUC();
        direccion = fap.getTxtDireccion();
        contacto = fap.getTxtContacto();
        telefono = fap.getTxtTelefono();
        email = fap.getTxtEmail();
        proveedores = fap.getJtbProveedores();
        departamentos = fap.getCboDepartamento();
    }

    boolean Conf() {
        int fila = proveedores.getSelectedRow();
        if (fila != -1) {
        } else {
            oA.error("Seleccione una fila primero.", "");
            return false;
        }
        return true;
    }

    void colorear1(JTextField txt) {
        txt.setBackground(new Color(255, 153, 153));
        txt.setForeground(Color.BLACK);
    }

    void colorear2(JTextField txt) {
        txt.setBackground(new Color(0, 51, 102));
        txt.setForeground(Color.WHITE);
    }

    void colorear3(JButton btn, String type) {
        if (type.equals("in")) {
            btn.setBackground(new Color(255, 204, 102));
        } else {
            btn.setBackground(new Color(0, 102, 255));
        }
    }

    void Limpiar() {
        id.setText(null);
        razonSocial.setText(null);
        ruc.setText(null);
        direccion.setText(null);
        contacto.setText(null);
        telefono.setText(null);
        email.setText(null);
        departamentos.setSelectedItem(null);

        colorear2(id);
        colorear2(razonSocial);
        colorear2(ruc);
        colorear2(direccion);
        colorear2(contacto);
        colorear2(telefono);
        colorear2(email);
        id.requestFocus();
    }

    void MouseListeners() {
        agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                AgregarProveedor();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                colorear3(agregar, "in");
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                colorear3(agregar, "out");
            }
        });
        limpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Limpiar();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                colorear3(limpiar, "in");
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                colorear3(limpiar, "out");
            }
        });
        modificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ModificarProveedor();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                colorear3(modificar, "in");
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                colorear3(modificar, "out");
            }
        });
        eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                EliminarProveedor();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                colorear3(eliminar, "in");
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                colorear3(eliminar, "out");
            }
        });
        proveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Seleccionar();
                int fila = proveedores.getSelectedRow();
                if (evt.getClickCount() == 2 && fila != -1) {
                    oA.info(proveedores.getValueAt(fila, proveedores.getSelectedColumn()).toString());
                }
            }
        });
    }

    void KeyListeners() {
        id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarSoloNumeros(e, id.getText(), (short) 5);
            }
        });
        telefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarSoloNumeros(e, telefono.getText(), (short) 9);
            }
        });
        contacto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ValidarSoloLetras(e, contacto.getText(), (short) 200);
            }
        });
        razonSocial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarLength(e, razonSocial.getText(), (short) 30);
            }
        });
        direccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarLength(e, direccion.getText(), (short) 50);
            }
        });
        ruc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarSoloNumeros(e, ruc.getText(), (short) 11);
            }
        });
        proveedores.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
                    Seleccionar();
                }
            }
        });
    }

    void Seleccionar() {
        int fila = proveedores.getSelectedRow();
        if (Conf() && oProveedores.Conectado()) {
            int EncontrarDepartamento = oProveedores.EncontrarDepartamento(proveedores.getValueAt(fila, 7).toString());
            id.setText(proveedores.getValueAt(fila, 0).toString());
            razonSocial.setText(proveedores.getValueAt(fila, 1).toString());
            ruc.setText(proveedores.getValueAt(fila, 2).toString());
            direccion.setText(proveedores.getValueAt(fila, 3).toString());
            contacto.setText(proveedores.getValueAt(fila, 4).toString());
            boolean noTelefono = proveedores.getValueAt(fila, 5) == null;
            boolean noEmail = proveedores.getValueAt(fila, 6) == null;
            if (noTelefono && noEmail) {
                telefono.setText(null);
                email.setText(null);
            } else if (!noTelefono && noEmail) {
                telefono.setText(proveedores.getValueAt(fila, 5).toString());
                email.setText(null);
            } else {
                telefono.setText(proveedores.getValueAt(fila, 5).toString());
                email.setText(proveedores.getValueAt(fila, 6).toString());
            }
            departamentos.setSelectedIndex(EncontrarDepartamento);
        }
    }

    boolean E(JTextField tf) {
        return tf.getText().isEmpty();
    }

    boolean Validar() {
        boolean name = departamentos.getSelectedIndex() == -1;
        if (E(razonSocial) || E(ruc) || E(direccion) || E(contacto) || name) {
            colorear1(razonSocial);
            colorear1(ruc);
            colorear1(direccion);
            colorear1(contacto);

            if (E(razonSocial)) {
                razonSocial.requestFocus();
                return false;
            } else if (E(ruc)) {
                colorear2(razonSocial);
                ruc.requestFocus();
                return false;
            } else if (ruc.getText().length() < 11) {
                colorear2(razonSocial);
                oA.error("R.U.C. inválido.", "");
                ruc.requestFocus();
                return false;
            } else if (E(direccion)) {
                colorear2(ruc);
                direccion.requestFocus();
                return false;
            } else if (E(contacto)) {
                colorear2(direccion);
                contacto.requestFocus();
                return false;
            } else if (name) {
                colorear2(razonSocial);
                colorear2(ruc);
                colorear2(direccion);
                colorear2(contacto);
                oA.error("Seleccione un departamento.", "");
                return false;
            }
        }
        return true;
    }

    void AgregarProveedor() {
        if (Validar()) {
            oProveedores.setIdProveedor(0); //Es autoincrementable
            oProveedores.setRazonSocial(razonSocial.getText());
            oProveedores.setRuc(ruc.getText());
            oProveedores.setDireccion(direccion.getText());
            oProveedores.setContacto(contacto.getText());
            oProveedores.setTelefono(telefono.getText());
            oProveedores.setEmail(email.getText());
            oProveedores.setDepartamento(departamentos.getSelectedItem().toString());
            DefaultTableModel AgregarProveedor = oProveedores.AgregarProveedor(proveedores.getModel());
            proveedores.setModel(AgregarProveedor);
            Limpiar();
        }
    }

    void ModificarProveedor() {
        if (Conf() == true) {
            int fila = proveedores.getSelectedRow();
            String dep = proveedores.getValueAt(fila, 7).toString();
            if (Validar() == true) {
                oProveedores.setIdProveedor(Integer.parseInt(id.getText()));
                oProveedores.setRazonSocial(razonSocial.getText());
                oProveedores.setRuc(ruc.getText());
                oProveedores.setDireccion(direccion.getText());
                oProveedores.setContacto(contacto.getText());
                oProveedores.setTelefono(telefono.getText());
                oProveedores.setEmail(email.getText());
                oProveedores.setDepartamento(dep);
                DefaultTableModel ModificarProveedor = oProveedores.ModificarProveedor(dep, departamentos.getSelectedItem().toString(), proveedores.getModel());
                Limpiar();
                proveedores.setModel(ModificarProveedor);
            }
        }
    }

    void EliminarProveedor() {
        if (Conf() == true) {
            if (oA.confirmación("¿Está seguro de eliminar?") == 0) {
                int fila = proveedores.getSelectedRow();
                oProveedores.setIdProveedor(Integer.parseInt(proveedores.getValueAt(fila, 0).toString()));
                oProveedores.setDepartamento(proveedores.getValueAt(fila, 7).toString());
                oProveedores.EliminarProveedor(proveedores.getValueAt(fila, 1).toString(), proveedores.getModel());
                Limpiar();
            }
        }
    }

    boolean CompararClave() {
        //En caso no quiera usar id incrementable
        int fila = proveedores.getRowCount();
        int i;
        String[] valores = new String[fila];
        for (i = 0; i < fila; i++) {
            valores[i] = proveedores.getValueAt(i, 0).toString();
            if (valores[i].equals(id.getText())) {
                oA.error("El proveedor ya existe", "Duplicado");
                return false;
            } else {
                i = fila;
            }
        }
        return true;
    }

    @Override
    public void CargarFrame() {
        fap.setVisible(true);
        proveedores.setModel(oProveedores.MostrarProveedores(proveedores.getModel()));
        String[] MostrarDepartamentos = oProveedores.MostrarDepartamentos();
        for (String item : MostrarDepartamentos) {
            departamentos.addItem(item);
        }
        departamentos.setSelectedIndex(-1);
        Close();
        MouseListeners();
        KeyListeners();
        fap.setTitle(" Lista de Clientes ");
        fap.setLocationRelativeTo(null);
    }

    @Override
    public final void Close() {
        fap.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {

                String RS = razonSocial.getText();
                String R = ruc.getText();
                String D = direccion.getText();
                String C = contacto.getText();
                String T = telefono.getText();
                String E = email.getText();

                if (!RS.isEmpty() || !R.isEmpty() || !D.isEmpty() || !C.isEmpty() || !T.isEmpty() || !E.isEmpty()) {
                    if (oA.confirmación("¿Salir sin guardar?") == 0) {
                        fap.dispose();
                        new mAdministrador().CargarFrame();
                    }
                } else {
                    fap.dispose();
                    new mAdministrador().CargarFrame();
                }
            }
        });
    }
}
