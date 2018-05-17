package Mundo;

public class Cuenta {

    // propiedades
    private String id;
    private String tipo;
    private float precio;
    private Usuario usuario;

    // constructores
    public Cuenta(String idCuenta, String tipo, float precio, String idUsuario, String nombreUsuario,
            String correoElectronico, String contrasena) {
        this.id = idCuenta;
        this.tipo = tipo;
        this.precio = precio;
        this.usuario = new Usuario(idUsuario, nombreUsuario, correoElectronico, contrasena);
    }

    public Cuenta(String id, String tipo, float precio, Usuario usuario) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.usuario = usuario;
    }

    // m�todos solicitados en la presentaci�n
    public String getNombreUsuario() {
        return this.usuario.getNombre();
    }

    public void setNombreUsuario(String nombre) {
        this.usuario.setNombre(nombre);
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;

    }

    // getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String toRecord() {

        return id + "," + tipo + "," + precio + ","
                + usuario.toRecord();

    }

    @Override
    public String toString() {
        return "id=" + id + ", tipo=" + tipo + ", precio=" + precio + ", usuario=" + usuario + '}';
    }
}
