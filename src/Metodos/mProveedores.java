package Metodos;

import Clases.cAdministradorProveedores;
import Clases.cAlertas;
import Formularios.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mProveedores extends mGenerales {

    private final cAlertas oA = new cAlertas();
    private Connection con;
    private Statement st;
    private ResultSet rs, rsUsuarios;
    private DefaultTableModel modelo;
    cAdministradorProveedores oProveedores = new cAdministradorProveedores();
    ResultSet rsProveedores, rsDepartamentos, rsNumDep, rsRespuesta;
    Object[] c = new Object[1];
    ArrayList<String> arraicito = new ArrayList<>();

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

    public void dobleClick() {
        fap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String dato = proveedores.getValueAt(proveedores.getSelectedRow(), proveedores.getSelectedColumn()).toString();
                    if (proveedores.getSelectedRow() != -1) {
                        JOptionPane.showMessageDialog(null, dato);
                    }
                }
            }
        });

    }

    public ResultSet ListaUsuarios() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from usuarios");

        return rs;
    }

//    public void EliminarUsuario() throws ClassNotFoundException, SQLException{
//        String user = fap.jtbProveedores.getValueAt(fap.jtbProveedores.getSelectedRow(), 3).toString();
//        con = oG.Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
//        st = con.createStatement();
//        st.execute("DROP USER '"+user+"'@'%'");
//        st.executeUpdate("DELETE FROM usuarios WHERE username='"+user+"'");
//    }
    void Modificar() {
//        switch (proveedores.getValueAt(proveedores.getSelectedRow(), 5).toString()){
//            case "administrador":               
//                registroUsuarios.rbAdmin.setSelected(true);                
//                registroUsuarios.txtUsuario.setEditable(false);
//                LlenarCampos();
//                break;
//            case "usuario":
//                registroUsuarios.rbUser.setSelected(true);               
//                registroUsuarios.txtUsuario.setEditable(false);
//                LlenarCampos();
//                break;
//        }
    }

    void Seleccionar() throws ClassNotFoundException, SQLException {
        int fila = proveedores.getSelectedRow();
        if (Conf() == true && Conectado()) {
            EncontrarDepartamento();
            id.setText(proveedores.getValueAt(fila, 0).toString());
            razonSocial.setText(proveedores.getValueAt(fila, 1).toString());
            ruc.setText(proveedores.getValueAt(fila, 2).toString());
            direccion.setText(proveedores.getValueAt(fila, 3).toString());
            contacto.setText(proveedores.getValueAt(fila, 4).toString());
            telefono.setText(proveedores.getValueAt(fila, 5).toString());
            email.setText(proveedores.getValueAt(fila, 6).toString());
        }
    }

    void EncontrarDepartamento() throws SQLException, ClassNotFoundException {
        String fila = proveedores.getValueAt(proveedores.getSelectedRow(), 7).toString();
        String[] depart = new String[25];
        rsDepartamentos = oProveedores.ListarDepartamentos();
        for (int i = 0; rsDepartamentos.next(); i++) {
            depart[i] = rsDepartamentos.getString("Departamento");
            if (depart[i].equals(fila)) {
                departamentos.setSelectedIndex(i);
            }
        }
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

    void Mostrar() {
        try {
            rsProveedores = oProveedores.ListaProveedores();
            Object[] prov = new Object[8];
            modelo = (DefaultTableModel) proveedores.getModel();

            modelo.setRowCount(0);

            while (rsProveedores.next()) {
                prov[0] = rsProveedores.getInt("IdProveedor");
                prov[1] = rsProveedores.getString("Razon_Social");
                prov[2] = rsProveedores.getString("RUC");
                prov[3] = rsProveedores.getString("Direccion");
                prov[4] = rsProveedores.getString("Contacto");
                prov[5] = rsProveedores.getString("Telefono");
                prov[6] = rsProveedores.getString("Email");
                prov[7] = rsProveedores.getString("Departamento");
                modelo.addRow(prov);
            }
            proveedores.setModel(modelo);
        } catch (SQLException ex) {
            oA.errorC(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            oA.error("Error desconocido", ex.getMessage());
        }
    }

    void agregarProveedor() {
        if (Validar()) {
            try {
                oProveedores.setIdProveedor(Integer.parseInt(id.getText()));
                oProveedores.setRazonSocial(razonSocial.getText());
                oProveedores.setRuc(ruc.getText());
                oProveedores.setDireccion(direccion.getText());
                oProveedores.setContacto(contacto.getText());
                oProveedores.setTelefono(telefono.getText());
                oProveedores.setEmail(email.getText());
                oProveedores.setDepartamento(departamentos.getSelectedItem().toString());
                oProveedores.Insertar();

                rsNumDep = oProveedores.ObtNumDep();
                while (rsNumDep.next()) {
                    c[0] = rsNumDep.getInt("Num_Proveedores");
                    oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) + 1);
                    oProveedores.ActDep();
                }
                JOptionPane.showMessageDialog(null, "El proveedor se ha agregado correctamente.");
                Mostrar();
                Limpiar();
            } catch (SQLException | ClassNotFoundException ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Error al agregar.");
                System.out.println(ex);
            }

        }
    }

    void colorear1(JTextField txt) {
        txt.setBackground(new Color(255, 153, 153));
        txt.setForeground(Color.BLACK);
    }

    void colorear2(JTextField txt) {
        txt.setBackground(new Color(0, 51, 102));
        txt.setForeground(Color.WHITE);
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

    void MostrarDepartamentos() throws SQLException {
        if (Conectado()) {
            try {
                rsDepartamentos = oProveedores.ListarDepartamentos();
                while (rsDepartamentos.next()) {
                    departamentos.addItem(rsDepartamentos.getString("Departamento"));
                }
                departamentos.setSelectedItem(null);
            } catch (ClassNotFoundException ex) {
                oA.error("Error desconocido.", ex.getMessage());
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
                oA.error("El proveedor ya exista", "");
                i = fila;
                return false;
            }
        }
        return true;
    }

    boolean Validar() {

        String RS = razonSocial.getText();
        String R = ruc.getText();
        String D = direccion.getText();
        String C = contacto.getText();
        String T = telefono.getText();
        String E = email.getText();
        String I = id.getText();

        if (RS.isEmpty() || R.isEmpty() || D.isEmpty() || C.isEmpty() || T.isEmpty() || E.isEmpty() || I.isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            colorear1(razonSocial);
            colorear1(ruc);
            colorear1(direccion);
            colorear1(contacto);
            colorear1(telefono);
            colorear1(email);
            colorear1(id);

            if (!E.isEmpty()) {
                colorear2(email);
            } else {
                email.requestFocus();
                return false;
            }
            if (!T.isEmpty()) {
                colorear2(telefono);
            } else {
                telefono.requestFocus();
                return false;
            }
            if (!C.isEmpty()) {
                colorear2(contacto);
            } else {
                contacto.requestFocus();
                return false;
            }
            if (!D.isEmpty()) {
                colorear2(direccion);
            } else {
                direccion.requestFocus();
                return false;
            }
            if (!R.isEmpty()) {
                colorear2(ruc);
            } else {
                ruc.requestFocus();
                return false;
            }
            if (!RS.isEmpty()) {
                colorear2(razonSocial);
            } else {
                razonSocial.requestFocus();
                return false;
            }
            if (!I.isEmpty()) {
                colorear2(id);
            } else {
                id.requestFocus();
                return false;
            }
        } else if (departamentos.getSelectedIndex() == -1) {
            colorear2(id);
            colorear2(razonSocial);
            colorear2(ruc);
            colorear2(direccion);
            colorear2(contacto);
            colorear2(telefono);
            colorear2(email);
            oA.error("Seleccione un departamento.", "");
            return false;
        } else if (R.length() < 11) {
            colorear2(id);
            colorear2(razonSocial);
            colorear1(ruc);
            colorear2(direccion);
            colorear2(contacto);
            colorear2(telefono);
            colorear2(email);
            oA.error("R.U.C. inválido.", "");
            return false;
        } else if (T.length() < 9) {
            colorear2(id);
            colorear2(razonSocial);
            colorear2(ruc);
            colorear2(direccion);
            colorear2(contacto);
            colorear1(telefono);
            colorear2(email);
            oA.error("Número telefónico inválido.", "");
            return false;
        } else {
            colorear2(telefono);
        }
        return true;
    }

    public final void Opciones() {
        JMenuItem upd = new JMenuItem("Modificar");
        JMenuItem dlt = new JMenuItem("Eliminar");

//        fagu.pmOpciones.add(upd);
//        fagu.pmOpciones.add(dlt);
//        fagu.jtbUsuarios.setComponentPopupMenu(fagu.pmOpciones);
//        upd.addActionListener((ActionEvent e) -> {
//            if (fagu.jtbUsuarios.getSelectedRow() != -1) {
//
//                if (!fagu.jtbUsuarios.getValueAt(fagu.jtbUsuarios.getSelectedRow(), 3).toString().equals(mLogueo.oL.getUsuario())) {
//                    if (Conectado()) {
//                        registroUsuarios.setVisible(true);
//                        Modificar();
//                        fagu.dispose();
//                    } else {
//                        new frmLogueo().setVisible(true);
//                        fagu.dispose();
//                    }
//                } else {
//                    if (Conectado()) {
//                        registroUsuarios.setVisible(true);
//                        Modificar();
//                        registroUsuarios.txtUsuario.setEditable(false);
//                        registroUsuarios.rbAdmin.setEnabled(false);
//                        registroUsuarios.rbUser.setEnabled(false);
//                        fagu.dispose();
//                    } else {
//                        new frmLogueo().setVisible(true);
//                        fagu.dispose();
//                    }
//                }
//            } else {
//                oA.error("Seleccione una fila primero.", "");
//            }
//        });
//        dlt.addActionListener((ActionEvent e) -> {
//            if (fagu.jtbUsuarios.getSelectedRow() != -1) {
//                if (!fagu.jtbUsuarios.getValueAt(fagu.jtbUsuarios.getSelectedRow(), 3).toString().equals(mLogueo.oL.getUsuario())) {
//                    if (Conectado()) {
//
//                        if (oA.confCerrar("¿Está seguro de eliminar?") == 0) {
//                            try {
//                                EliminarUsuario();
//                                oA.aviso("Usuario eliminado.");
//                                Mostrar();
//                            } catch (ClassNotFoundException | SQLException ex) {
//                                oA.error("Error al eliminar", ex.toString());
//                            }
//                        }
//                    } else {
//                        new frmLogueo().setVisible(true);
//                        fagu.dispose();
//                    }
//                } else {
//                    oA.error("Cierre sesión primero.", "");
//                }
//            } else {
//                oA.error("Seleccione una fila primero.", "");
//            }
//        });
    }

    boolean Conectado() {
        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
            return false;
        }
        return true;
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
                    new mSalir(fap).CargarFrame();
                } else {
                    fap.dispose();
                    new mAdministrador().CargarFrame();
                }
            }
        });
    }

    @Override
    public void CargarFrame() {
        fap.setVisible(true);
        Mostrar();
        Close();
        dobleClick();
        fap.setTitle(" Lista de Clientes ");
        fap.setLocationRelativeTo(null);
    }
}
