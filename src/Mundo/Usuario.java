package Mundo;

import java.util.ArrayList;

public class Usuario {

    private String id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private ArrayList<PlayList> listas;

    public Usuario() {
    }
//CREAR USUARIO
    public Usuario(String id, String nombre, String correoElectronico, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        listas = new ArrayList<>();
    }
//USUARIO EXISTENTE DEL QUE SE CONOCEN SUS LISTAS
    public Usuario(String id, String nombre, String correoElectronico, String contrasena, ArrayList<PlayList> listas) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.listas = listas;
    }
    
    

    public int sizeListas() {
        return listas.size();
    }

    public void agregarLista(String id, String nombre, String descripcion, boolean colaborativo, boolean publico, Usuario usuario) {
        listas.add(new PlayList(id, nombre, descripcion, colaborativo, publico));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<PlayList> getListas() {
        return listas;
    }

    public void setListas(ArrayList<PlayList> listas) {
        this.listas = listas;
    }

    public String toRecord() {
        String stlistas = "";
        for (PlayList p:listas){
            stlistas += ","+p.getId();
        }
        return id + "," + nombre + "," + contrasena + "," + correoElectronico + stlistas;
    }

    @Override
    public String toString() {
        return "Usuario" + "id=" + id + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + ", listas=" + listas + '}';
    }
    
    

}
