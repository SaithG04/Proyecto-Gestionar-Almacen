package Clases;

import Metodos.mLogueo;
import Metodos.mSQL;
import java.sql.SQLException;

/**
 *
 * @author DREP
 */
public class cAdministrador extends mSQL {

    cAlertas oA = new cAlertas();

    public boolean ComprobarConexion() {
        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
        } catch (ClassNotFoundException | SQLException e) {
            oA.errorC(e.toString());
            return false;
        }
        return true;
    }
    
}
