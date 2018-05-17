package datos;

import Interfaz.Spotify;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Mundo.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistencia {

    private String ruta;
    private String ruta1;
    private String ruta2;

    public Persistencia() {
            this.ruta = "datos.txt";
            this.ruta1 = "canciones.txt";
            this.ruta2 = "playlists.txt";
    }

    public boolean guardarPersonas(ArrayList<Cuenta> registros) {
        try {
            FileWriter fw = new FileWriter(this.ruta);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < registros.size(); i++) {

                String info = registros.get(i).toRecord();
                bw.write(info);
                bw.newLine();
            }
            bw.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean guardarPlaylists(ArrayList<PlayList> registros) {
        try {
            FileWriter fw = new FileWriter(this.ruta2);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < registros.size(); i++) {

                String info = registros.get(i).toRecord();
                bw.write(info);
                bw.newLine();
            }
            bw.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean guardarPersona(Cuenta lanina) {
        try {
            FileWriter fw = new FileWriter(this.ruta, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String info = lanina.toRecord();

            bw.write(info);
            bw.newLine();

            bw.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
    }

    public ArrayList<Cuenta> leerPersonas() {
        try {

            ArrayList<Cuenta> respuesta = new ArrayList<>();

            FileReader fr = new FileReader(this.ruta);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {

                String[] info = line.split(",");
                String idcuenta = info[0];
                String tipo = info[1];
                float precio = Float.parseFloat(info[2]);
                String idusuario = info[3];
                String nombre = info[4];
                String contrasena = info[5];
                String correo = info[6];
                ArrayList<PlayList> listas = new ArrayList<>();
                // A partir de la sexta coma, busca todas las playlists asociadas
                for (int i = 7; i < info.length; i++) {
                    PlayList li = Spotify.buscarPlayId(Integer.parseInt(info[i]),Spotify.plays);
                    if (li!=null)listas.add(li);
                }
                Cuenta p = new Cuenta(String.valueOf(idcuenta), tipo, precio, new Usuario(idusuario, nombre, correo, contrasena, listas));
                respuesta.add(p);
            }

            br.close();

            return respuesta;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<PlayList> leerPlaylists() {
        try {

            ArrayList<PlayList> respuesta = new ArrayList<>();

            FileReader fr = new FileReader(this.ruta2);
            BufferedReader br = new BufferedReader(fr);

            String line;
            int esteid = 0;
            while ((line = br.readLine()) != null) {

                String[] info = line.split(",");
                String idplay = info[0];
                String nombre = info[1];
                String descripcion = info[2];
                boolean colaborativo = info[3].equals("1");
                boolean publico = info[4].equals("1");
                ArrayList<Cancion> sounds = new ArrayList<>();
                //A partir de la 5 coma, busca canciones asociadas
                for (int i = 5; i < info.length; i++) {
                    Cancion c = Spotify.buscarCancionId(Integer.parseInt(info[i]),Spotify.canciones);
                    if(c!=null)sounds.add(c);
                }
                PlayList p = new PlayList(idplay, nombre, descripcion, colaborativo, publico);
                p.setSound(sounds);
                respuesta.add(p);
                esteid++;
            }

            br.close();

            return respuesta;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Cancion> leerCanciones() {
        try {

            ArrayList<Cancion> respuesta = new ArrayList<>();

            FileReader fr = new FileReader(this.ruta1);
            BufferedReader br = new BufferedReader(fr);

            String line;
            int esteid = 0;
            while ((line = br.readLine()) != null) {

                String[] info = line.split(",");
                String idcancion = info[0];
                String nombre = info[1];
                int num = Integer.parseInt(info[2]);
                int duracion = Integer.parseInt(info[3]);
                String genero = info[4];
                int popularidad = Integer.parseInt(info[5]);

                Cancion p = new Cancion(idcancion, nombre, num, duracion, genero, popularidad);
                respuesta.add(p);
                esteid++;
            }

            br.close();

            return respuesta;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
}
