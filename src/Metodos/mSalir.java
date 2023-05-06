package Metodos;

import Formularios.frmAdministradorProveedores;
import Formularios.frmSalir;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author isai_
 */
public class mSalir {
    
    private final frmSalir os;
    private final JButton btnClose, btnConfirmar;
    private final frmAdministradorProveedores fap;

    public mSalir(frmAdministradorProveedores fap) {
        os = new frmSalir();
        btnClose = os.getBtnClose();
        btnConfirmar = os.getBtnConfirmar();
        this.fap = fap;
    }
    
    public void CargarFrame(){
        os.setVisible(true);
        Clicks();
    }
    
    void Clicks(){
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                os.dispose(); 
            }
        });
        btnConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                fap.dispose();
                new mAdministrador().CargarFrame();
            }
        });
    }
    
}