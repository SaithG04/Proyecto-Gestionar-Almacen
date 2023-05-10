package Clases;

import Metodos.mLogueo;

/**
 *
 * @author DREP
 */
public class cAdministrador extends cSQL {

    cAlertas oA = new cAlertas();

    public boolean ComprobarConexion() {
        try {
            Conectar(mLogueo.oL.getUsuario(), mLogueo.oL.getContraseña());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
