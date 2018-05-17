package Interfaz;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Mundo.Cancion;
import Mundo.Cuenta;
import Mundo.PlayList;
import Mundo.Usuario;
import datos.Persistencia;

public class Spotify {

    //Todas las cuentas existentes
    public static ArrayList<Cuenta> cuentas = new ArrayList<>();
    //Todas las playlists, existentes, sin importar dueno
    //Para ver datos de usuario, deberia usar la del usuario, no plays
    public static ArrayList<PlayList> plays = new ArrayList<>();
    //Todas las canciones existentes
    public static ArrayList<Cancion> canciones = new ArrayList<>();
    public static int idactual;
    public static int idactualplay;
    public static int idactualcancion;

    public static Cancion buscarCancion(String nombre, ArrayList<Cancion> canciones) {
        //Busca cancion con su nombre
        for (Cancion i : canciones) {
            if (i.getNombre().equals(nombre)) {
                return i;
            }
        }
        return null;
    }

    public static Cancion buscarCancionId(int id, ArrayList<Cancion> canciones) {
        //busca cancion con su id
        for (Cancion i : canciones) {
            if (Integer.parseInt(i.getId()) == id) {
                return i;
            }
        }
        return null;
    }

    public static PlayList buscarPlayId(int id, ArrayList<PlayList> plays) {
        //busca playlist con su id
        for (PlayList p : plays) {
            if (Integer.parseInt(p.getId()) == id) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Persistencia p = new Persistencia();
        cuentas = p.leerPersonas();
        canciones = p.leerCanciones();
        plays = p.leerPlaylists();
        
        //Estos dos metodos buscan el valor de id mas alto en sus listas y le suman uno
        idactual = (cuentas.isEmpty()) ? 1 : Integer.parseInt(cuentas.get(cuentas.size() - 1).getId()) + 1;
        idactualplay = (plays.isEmpty()) ? 1 : Integer.parseInt(plays.get(plays.size() - 1).getId()) + 1;

        JOptionPane.showMessageDialog(null, "Hola, bienvenido a tu plataforma Spotify");

        int opc1 = -8;

        while (opc1 != 0) {
            String a = JOptionPane.showInputDialog("A continuación seleccione la opción que desee realizar\n"
                    + "1. Iniciar sesión\n"
                    + "2. Registrar cuenta\n"
                    + "0. Salir");
            opc1 = Integer.parseInt(a);

            switch (opc1) {
                case 1:
                    String nombreusu = JOptionPane.showInputDialog("Ingrese el nombre de usuario");
                    String contrasena = JOptionPane.showInputDialog("Ingrese la contrasena");
                    //*** Esta cuenta es importante, es la cuenta actual ***
                    Cuenta thiscuenta = null;
                    //*** Debe ser usada para ver los datos de usuario ***
                    for (Cuenta i : cuentas) {
                        if (i.getNombreUsuario().equals(nombreusu) && i.getUsuario().getContrasena().equals(contrasena)) {
                            thiscuenta = i;
                        }
                    }
                    if (thiscuenta != null) {
                        ArrayList<PlayList> playusu = thiscuenta.getUsuario().getListas();
                        int opc = -9;

                        while (opc != 0) {
                            String valor = JOptionPane.showInputDialog("Señor usuario por favor seleccione una opción:\n"
                                    + "1. Catálogo de canciones\n"
                                    + "2. Mis listas de reproducción\n"
                                    + "3. Ver mi cuenta\n"
                                    + "0. Cerrar sesión");
                            opc = Integer.parseInt(valor);
                            switch (opc) {

                                case 1:
                                    String canci = "";
                                    for (Cancion j : canciones) {
                                        canci += j.toString() + "\n";
                                    }
                                    JOptionPane.showMessageDialog(null, canci);
                                    break;
                                case 2:
                                    int opclista = -1;
                                    while (opclista != 0) {
                                        opclista = Integer.parseInt(JOptionPane.showInputDialog("1. Agregar nueva lista de reproducción"
                                                + "\n2. Ver mis listas de reproducción"
                                                + "\n0. Volver"));
                                        switch (opclista) {
                                            case 1:
                                                String nomlista = JOptionPane.showInputDialog("Ingrese el nombre de la lista");
                                                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la lista");
                                                int valortemp = JOptionPane.showConfirmDialog(null, "Es colaborativa?", "", JOptionPane.YES_NO_OPTION);
                                                boolean colaborativo = (valortemp == JOptionPane.YES_OPTION);
                                                valortemp = JOptionPane.showConfirmDialog(null, "Es publica?", "", JOptionPane.YES_NO_OPTION);
                                                boolean publica = (valortemp == JOptionPane.YES_OPTION);
                                                JOptionPane.showMessageDialog(null, "Lista creada");
                                                PlayList playlist = new PlayList(String.valueOf(idactualplay), nomlista, descripcion, colaborativo, publica);
                                                idactualplay++;
                                                plays.add(playlist);
                                                p.guardarPlaylists(plays);
                                                playusu.add(playlist);
                                                p.guardarPersonas(cuentas);
                                                break;
                                            case 2:
                                                int idrec = -3;
                                                while (idrec != 0) {
                                                    String listas = "Estas son tus listas de reproducción:";
                                                    for (PlayList l : playusu) {
                                                        listas += "\n" + l.toString();
                                                    }
                                                    listas += "\n0. Volver";
                                                    idrec = Integer.parseInt(JOptionPane.showInputDialog(listas));
                                                    if (idrec != 0) {
                                                        //Playlist en uso
                                                        PlayList mypl = null;
                                                        for (PlayList pl : playusu) {
                                                            if (Integer.parseInt(pl.getId()) == idrec) {
                                                                mypl = pl;
                                                                break;
                                                            }
                                                        }
                                                        if (mypl == null) {
                                                            JOptionPane.showMessageDialog(null, "Opcion invalida");
                                                            continue;
                                                        }
                                                        int opcthislist = -1;
                                                        while (opcthislist != 0 && opcthislist != 4) {
                                                            opcthislist = Integer.parseInt(JOptionPane.showInputDialog(mypl.getNombre()
                                                                    + "\n1. Ver lista de reproducción"
                                                                    + "\n2. Agregar canción"
                                                                    + "\n3. Eliminar cancion de la lista"
                                                                    + "\n4. Eliminar lista de reproducción"
                                                                    + "\n0. Volver"));
                                                            switch (opcthislist) {
                                                                case 1:
                                                                    String thismsj = mypl.toStringDescr() + "\n";
                                                                    thismsj += mypl.toCanciones();
                                                                    JOptionPane.showMessageDialog(null, thismsj);
                                                                    break;
                                                                case 2:
                                                                    String thiscanci = "Que canción desea agregar a la playlist?";
                                                                    for (Cancion j : canciones) {
                                                                        thiscanci += "\n" + j.toString();
                                                                    }
                                                                    int idcanci = Integer.parseInt(JOptionPane.showInputDialog(thiscanci));
                                                                    Cancion can = buscarCancionId(idcanci, canciones);
                                                                    if (can == null) {
                                                                        JOptionPane.showMessageDialog(null, "Seleccion inválida");
                                                                        break;
                                                                    }
                                                                    mypl.addcan(can);
                                                                    if (p.guardarPlaylists(plays)) {
                                                                        JOptionPane.showMessageDialog(null, "Canción guardada correctamente");
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Error, no se ha guardado la canción");
                                                                    }
                                                                    break;
                                                                case 3:
                                                                    String elim = "Que canción desea eliminar de la playlist?";
                                                                    for (Cancion j : mypl.getSound()) {
                                                                        elim += "\n" + j.toString();
                                                                    }
                                                                    int opcelim = Integer.parseInt(JOptionPane.showInputDialog(elim));
                                                                    if (mypl.removerCancionPorId(opcelim)) {
                                                                        JOptionPane.showMessageDialog(null, "Canción eliminada correctamente");
                                                                        p.guardarPlaylists(plays);
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Seleccion inválida");
                                                                    }
                                                                    break;
                                                                case 4:
                                                                    playusu.remove(mypl);
                                                                    plays.remove(mypl);
                                                                    p.guardarPersonas(cuentas);
                                                                    p.guardarPlaylists(plays);
                                                                    JOptionPane.showMessageDialog(null, "Playlist eliminada");
                                                                    break;
                                                                case 0:
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                }

                                                break;
                                            case 0:
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    break;
                                case 3:
                                    int s = -1;
                                    while (s != 0) {
                                        s = Integer.parseInt(JOptionPane.showInputDialog("\nPor favor, seleccione una opcion:\n" + thiscuenta
                                                + "1. Hacer premium\n"
                                                + "2. Cambiar nombre de usuario\n"
                                                + "3. Cambiar correo electrónico\n"
                                                + "4. Cambiar contraseña\n"
                                                + "0. Cerrar sesión"));
                                        switch (s) {
                                            case 1:
                                                thiscuenta.setTipo("premium");
                                                JOptionPane.showMessageDialog(null, "Ya eres premium");
                                                p.guardarPersonas(cuentas);
                                                break;
                                            case 2:

                                                String ss = JOptionPane.showInputDialog(null, "Ingrese su nuevo nombre de usuario");
                                                thiscuenta.setNombreUsuario(ss);
                                                JOptionPane.showMessageDialog(null, "Se ha cambiado su nombre de usuario");
                                                p.guardarPersonas(cuentas);break;

                                            case 3:
                                                String sss = JOptionPane.showInputDialog(null, "Ingrese su nuevo correo electronico: ");
                                                thiscuenta.getUsuario().setCorreoElectronico(sss);
                                                JOptionPane.showMessageDialog(null, "Se ha cambiado su correo electrónico");
                                                p.guardarPersonas(cuentas);break;
                                            case 4:
                                                String ssss = JOptionPane.showInputDialog(null, "Ingrese su nueva contraseÃ±a: ");
                                                thiscuenta.getUsuario().setContrasena(ssss);
                                                JOptionPane.showMessageDialog(null, "Se ha cambiado satisfactoriamente su contraseÃ±a");break;

                                            case 0:

                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    break;
                                case 0:
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales inválidas, probablemente no hayas creado una cuenta aún,\n"
                        		+ "asegúrate de haberlo hecho. Muchas gracias.");
                    }
                    break;

                case 2:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                    String password = JOptionPane.showInputDialog("Ingrese su contrasena");
                    String correo = JOptionPane.showInputDialog("Ingrese el correo electrónico");
                    cuentas.add(new Cuenta(String.valueOf(idactual), "free", (float) 0.0, new Usuario("0", nombre, correo, password)));
                    idactual++;
                    p.guardarPersonas(cuentas);
            }
        }
    }
}
