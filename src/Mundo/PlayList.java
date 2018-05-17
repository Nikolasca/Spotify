package Mundo;

import java.util.ArrayList;

public class PlayList {

    private String id;
    private String nombre;
    private String descripcion;
    private boolean colaborativo;
    private boolean publico;
    private ArrayList<Cancion> sound;

    public PlayList() {
    }

    public PlayList(String id, String nombre, String descripcion, boolean colaborativo, boolean publico) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colaborativo = colaborativo;
        this.publico = publico;
        sound = new ArrayList<>();
    }

    public void agregarCancion(Cancion cancion) {
        this.sound.add(cancion);
    }

    //Borra cancion de sound, teniendo posicion; no deberia usarse mas
    public void removerCancion(int i) {
        this.sound.remove(i);
    }

    //Borra cancion de sound, teniendo id; deberia usarse
    public boolean removerCancionPorId(int id) {
        for (Cancion c : sound) {
            if (Integer.parseInt(c.getId()) == id) {
                sound.remove(c);
                return true;
            }
        }
        return false;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isColaborativo() {
        return colaborativo;
    }

    public void setColaborativo(boolean colaborativo) {
        this.colaborativo = colaborativo;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public ArrayList<Cancion> getSound() {
        return sound;
    }

    public void setSound(ArrayList<Cancion> sound) {
        this.sound = sound; 
    }

    public void addcan(Cancion cancion) {
        sound.add(cancion);
    }

    //Para el archivo -IF ELSE COMPRIMIDO- SIRVE PAR ASIGNAR VALORES DEPENDIENDO DE UNA CONDICION EN UNA SOLA LINEA
    public String toRecord() {
        int col = (colaborativo) ? 1 : 0;
        int pub = (publico) ? 1 : 0;
        String letras = "";
        for (Cancion i : sound) {
            letras += "," + i.getId();
        }
        return id + "," + nombre + "," + descripcion + "," + col + "," + pub + letras;
    }

    @Override
    public String toString() {
        return id + ". " + nombre;
    }

    //toString con canciones
    public String toCanciones() {
        String canciones = "";
        for (Cancion i : sound) {
            canciones += "\n" + i.getId() + ". " + i.getNombre();
        }
        return canciones;
    }

    //toString con caracteristicas, sin canciones
    public String toStringDescr() {
        String cola = (colaborativo) ? "\nEs colaborativa" : "\nNo es colaborativa";
        String publi = (publico) ? "\nEs publica" : "\nNo es publica";
        return id + ". " + nombre + "\n" + descripcion + cola + publi;
    }

}
