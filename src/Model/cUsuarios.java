package Model;

/**
 *
 * @author isai_
 */
public class cUsuarios {
    
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String contraseña;
    private String tipoUsuario;
    private String telefono;
    private String correo;

    public cUsuarios(int idUsuario, String nombres, String apellidos, String usuario, String contraseña, String tipoUsuario, String telefono, String correo) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}