package Metodos;

import Clases.cAdministradorProveedores;
import Clases.cAlertas;
import Formularios.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public class mProveedores extends mGenerales {
    
    private final cAlertas oA = new cAlertas();
    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
    private DefaultTableModel modelo;
    cAdministradorProveedores oProveedores = new cAdministradorProveedores();
    ResultSet rsProveedores, rsDepartamentos, rsNumDep;
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
        fap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = proveedores.getSelectedRow();
                if (evt.getClickCount() == 2 && fila != -1) {
                    JOptionPane.showMessageDialog(null, proveedores.getValueAt(fila, proveedores.getSelectedColumn()).toString());
                }
            }
        });
        proveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    Seleccionar();
                } catch (ClassNotFoundException ex) {
                    oA.error("Error desconocido", ex.getMessage());
                } catch (SQLException ex) {
                    oA.errorC(ex.getMessage());
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
                validarSoloLetras(e, contacto.getText(), (short) 200);
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
                    try {
                        Seleccionar();
                    } catch (ClassNotFoundException ex) {
                        oA.error("Error desconocido.", ex.getMessage());
                    } catch (SQLException ex) {
                        oA.errorC(ex.getMessage());
                    }
                }
            }
        });
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
    
    boolean Conectado() {
        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException ex) {
            oA.errorC(ex.toString());
            return false;
        }
        return true;
    }
    
    void AgregarProveedor() {
        if (Validar()) {
            try {
                oProveedores.setIdProveedor(0);
                oProveedores.setRazonSocial(razonSocial.getText());
                oProveedores.setRuc(ruc.getText());
                oProveedores.setDireccion(direccion.getText());
                oProveedores.setContacto(contacto.getText());
                oProveedores.setTelefono(telefono.getText());
                oProveedores.setEmail(email.getText());
                oProveedores.setDepartamento(departamentos.getSelectedItem().toString());
                Insertar();
                
                rsNumDep = ObtNumDep();
                while (rsNumDep.next()) {
                    c[0] = rsNumDep.getInt("Num_Proveedores");
                    oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) + 1);
                    ActDep();
                }
                oA.aviso("El proveedor se ha agregado correctamente.");
                MostrarProveedores();
                Limpiar();
            } catch (ClassNotFoundException ex) {
                    oA.error("Error al agregar.", ex.getMessage());
                } catch(SQLException ex){
                    oA.errorC(ex.getMessage());
                }
            
        }
    }
    
    void ModificarProveedor() {
        if (Conf() == true) {
            int fila = proveedores.getSelectedRow();
            String dep = proveedores.getValueAt(fila, 7).toString();
            if (Validar() == true) {
                try {
                    oProveedores.setIdProveedor(Integer.parseInt(id.getText()));
                    oProveedores.setRazonSocial(razonSocial.getText());
                    oProveedores.setRuc(ruc.getText());
                    oProveedores.setDireccion(direccion.getText());
                    oProveedores.setContacto(contacto.getText());
                    oProveedores.setTelefono(telefono.getText());
                    oProveedores.setEmail(email.getText());
                    oProveedores.setDepartamento(dep);
                    if (!(dep.equalsIgnoreCase(departamentos.getSelectedItem().toString()))) {
                        
                        rsNumDep = ObtNumDep();
                        while (rsNumDep.next()) {
                            c[0] = rsNumDep.getInt("Num_Proveedores");
                            oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) - 1);
                            ActDep();
                        }
                        oProveedores.setDepartamento(departamentos.getSelectedItem().toString());
                        rsNumDep = ObtNumDep();
                        while (rsNumDep.next()) {
                            c[0] = rsNumDep.getInt("Num_Proveedores");
                            oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) + 1);
                            ActDep();
                        }
                    } else {
                        oProveedores.setDepartamento(dep);
                    }
                    Modificar();
                    MostrarProveedores();
                    Limpiar();
                    oA.aviso("Modificado con éxito");
                } catch (ClassNotFoundException ex) {
                    oA.error("Error al modificar.", ex.getMessage());
                } catch(SQLException ex){
                    oA.errorC(ex.getMessage());
                }
            }
        }
    }
    
    void EliminarProveedor() {
        if (Conf() == true) {
            if (oA.confirmación("¿Está seguro de eliminar?") == 0) {
                try {
                    int fila = proveedores.getSelectedRow();
                    oProveedores.setIdProveedor(Integer.parseInt(proveedores.getValueAt(fila, 0).toString()));
                    oProveedores.setDepartamento(proveedores.getValueAt(fila, 7).toString());
                    rsNumDep = ObtNumDep();
                    
                    while (rsNumDep.next()) {
                        c[0] = rsNumDep.getInt("Num_Proveedores");
                        oProveedores.setNumProveedores(Integer.parseInt(c[0].toString()) - 1);
                        ActDep();
                    }
                    Eliminar();
                    oProveedores.setRazonSocial(proveedores.getValueAt(fila, 1).toString());
                    EliminarDePrecio();
                    MostrarProveedores();
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "El proveedor ha sido eliminado correctamente.");
                } catch (ClassNotFoundException ex) {
                    oA.error("Error desconocido.", ex.getMessage());
                } catch (SQLException ex) {
                    oA.errorC(ex.getMessage());
                }
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
    
    void MostrarProveedores() {
        try {
            rsProveedores = ListaProveedores();
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
    
    void MostrarDepartamentos() {
        try {
            rsDepartamentos = ListarDepartamentos();
            while (rsDepartamentos.next()) {
                departamentos.addItem(rsDepartamentos.getString("Departamento"));
            }
            departamentos.setSelectedItem(null);
        } catch (ClassNotFoundException ex) {
            oA.error("Error desconocido.", ex.getMessage());
        } catch (SQLException ex) {
            oA.errorC(ex.getMessage());
        }
    }
    
    @Override
    public void CargarFrame() {
        fap.setVisible(true);
        MostrarProveedores();
        MostrarDepartamentos();
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
                    oA.confCerrar(fap, "administrador");
                } else {
                    fap.dispose();
                    new mAdministrador().CargarFrame();
                }
            }
        });
    }
    
    void EncontrarDepartamento() throws SQLException, ClassNotFoundException {
        String fila = proveedores.getValueAt(proveedores.getSelectedRow(), 7).toString();
        String[] depart = new String[25];
        rsDepartamentos = ListarDepartamentos();
        for (int i = 0; rsDepartamentos.next(); i++) {
            depart[i] = rsDepartamentos.getString("Departamento");
            if (depart[i].equals(fila)) {
                departamentos.setSelectedIndex(i);
            }
        }
    }
    
    //SQL
        public ResultSet ListaProveedores() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores ");

        return rs;
    }

    public void Modificar() throws SQLException, ClassNotFoundException {

        con = Conectar();
        psInsertar = con.prepareStatement("UPDATE proveedores SET Razon_Social=?,RUC=?,Direccion=?,Contacto=?,Telefono=?,Email=?,Departamento=? WHERE IdProveedor=?");
        psInsertar.setString(1, oProveedores.getRazonSocial());
        psInsertar.setString(2, oProveedores.getRuc());
        psInsertar.setString(3, oProveedores.getDireccion());
        psInsertar.setString(4, oProveedores.getContacto());
        psInsertar.setString(5, oProveedores.getTelefono());
        psInsertar.setString(6, oProveedores.getEmail());
        psInsertar.setString(7, oProveedores.getDepartamento());
        psInsertar.setInt(8, oProveedores.getIdProveedor());
        psInsertar.executeUpdate();

    }

    public void Insertar() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement("INSERT INTO proveedores(IdProveedor,Razon_Social,RUC,Direccion,Contacto,Telefono,Email,Departamento) VALUES (?,?,?,?,?,?,?,?)");
        psInsertar.setInt(1, oProveedores.getIdProveedor());
        psInsertar.setString(2, oProveedores.getRazonSocial());
        psInsertar.setString(3, oProveedores.getRuc());
        psInsertar.setString(4, oProveedores.getDireccion());
        psInsertar.setString(5, oProveedores.getContacto());
        psInsertar.setString(6, oProveedores.getTelefono());
        psInsertar.setString(7, oProveedores.getEmail());
        psInsertar.setString(8, oProveedores.getDepartamento());
        psInsertar.executeUpdate();
    }

    public ResultSet ObtNumDep() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select Num_Proveedores from departamentos where Departamento = '" + oProveedores.getDepartamento() + "'");

        return rs;
    }

    public void ActDep() throws ClassNotFoundException, SQLException {
        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        psInsertar = con.prepareStatement("UPDATE departamentos SET Num_Proveedores=? WHERE Departamento=?");
        psInsertar.setInt(1, oProveedores.getNumProveedores());
        psInsertar.setString(2, oProveedores.getDepartamento());
        psInsertar.executeUpdate();
    }

    public void Eliminar() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        st.executeUpdate("DELETE FROM proveedores WHERE IdProveedor=" + oProveedores.getIdProveedor());
    }

    public ResultSet ListarDepartamentos() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from departamentos");
        return rs;
    }

    public ResultSet ListarProductos() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos");
        return rs;
    }

    public ResultSet Reporte() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores ");
        return rs;
    }

    public ResultSet ObtRespuesta() throws ClassNotFoundException, SQLException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());  
        st = con.createStatement();
        rs = st.executeQuery("Select Proveedor from productos");

        return rs;
    }

    public void EliminarDePrecio() throws SQLException, ClassNotFoundException {

        con = Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());  
        st = con.createStatement();
        st.executeUpdate("DELETE FROM precios WHERE Proveedor='" + razonSocial + "'");
    }
}
