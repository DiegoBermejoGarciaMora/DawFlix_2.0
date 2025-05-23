
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import DAO.ContenidoDAO;
import DAO.EstadisticasDAO;
import Exceptions.FavoritoDuplicadoExcepcion;
import Model.ComparatorByName;
import Model.Contenido.Contenido;
import Model.Contenido.Generos.Genero;
import Model.Contenido.Generos.GeneroMusica;
import Model.Suscripciones.Suscripcion;
import Model.Contenido.Musica;
import Model.Contenido.Pelicula;
import Model.Contenido.Serie;
import Model.Usuarios.Usuario;

public class DAWFlix {
    // Registrar reproducciones
    Map<Usuario, Map<Contenido, Integer>> reproducciones = new HashMap<>();

    // DAO
    static ContenidoDAO dao = new ContenidoDAO();
    public static TreeMap<Contenido, String> contenidos = dao.selectContenidos();
    public static List<Usuario> usuarios = dao.selectUsuarios();

    // Variables bucles menus
    public static String op;
    public static boolean keepGoing = true;
    public static boolean exit = false;
    public static Usuario usuarioLogeado;
    public static boolean contenidoEncontrado = false;

    // Variables Contenidos
    public static String titulo, descripcion, director, artista, generoS;
    public static int puntuacion, duracion, capitulos, temporadas;
    public static Genero genero;
    public static GeneroMusica generoMusica;

    // Colores para que este bonito
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    // Scanner
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        TreeMap<String, Integer> conteo = EstadisticasDAO.obtenerConteoReproducciones();

        conteo.forEach((usuario, veces) -> 
            System.out.println(usuario + " ha reproducido " + veces + " veces")
        );
        login();
        menuPrincipal();
    }

    public static void login() {
        String nombre, email, contraseña;
        Boolean login = false;

        while (!login) {
            exit = false;
            System.out.println("");
            System.out.println(ANSI_RED + "------ DAWFlix | LOGIN ------" + ANSI_RESET);
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Crear Cuenta");
            System.out.println("3. Salir");
            System.out.print("OPCION: ");
            op = sc.next();

            switch (op) {
                case "1":
                    System.out.print("Correo Electronico: ");
                    email = sc.next();
                    System.out.print("Contraseña: ");
                    contraseña = sc.next();
                    Usuario userLogin = new Usuario(email, contraseña);

                    boolean usuarioEncontrado = false;
                    for (Usuario usuario : usuarios) {
                        if (userLogin.equals(usuario)) {
                            usuarioEncontrado = true;
                            usuarioLogeado = usuario;
                            break;
                        }
                    }

                    if (usuarioEncontrado) {
                        login = true;
                        System.out.println("Sesion Iniciada Con Exito");
                    } else {
                        System.out.println("[ERROR] Email o Contraseña Incorrectas");
                    }
                    break;

                case "2":
                    System.out.println(ANSI_RED + "------ DAWFlix | REGISTRARSE ------" + ANSI_RESET);
                    System.out.print("Nombre de Usuario: ");
                    nombre = sc.next();
                    System.out.print("Correo Electronico: ");
                    email = sc.next();
                    boolean emailUsado = false;

                    for (Usuario usuario : usuarios) {
                        if (usuario.getEmail().equals(email)) {
                            emailUsado = true;
                            break;
                        }
                    }

                    if (!emailUsado) {
                        String contraseña1NuevoUsuario = "", contraseña2NuevoUsuario;
                        Boolean contraseñasIguales = false;
                        while (!contraseñasIguales) {
                            System.out.print("Contraseña: ");
                            contraseña1NuevoUsuario = sc.next();
                            System.out.print("Repita su Contraseña: ");
                            contraseña2NuevoUsuario = sc.next();
                            if (contraseña1NuevoUsuario.equals(contraseña2NuevoUsuario)) {
                                contraseñasIguales = true;
                            } else {
                                System.out.println("[ERROR] Las Contraseñas No Coinciden");
                            }
                        }
                        contratarSuscripcion(nombre, email, contraseña1NuevoUsuario);
                    } else {
                        System.out.println("[ERROR] Este Correo Ya Esta Existe");
                    }

                    break;
                case "3":
                    System.exit(0);
                    exit = true;
                    return;
                default:
                    System.out.println("[ERROR] OPCION NO VALIDA");
                    break;
            }
        }

    }

    public static void contratarSuscripcion(String nombre, String email, String contraseña) {
        while (keepGoing) {
            System.out.println("");
            System.out.println(ANSI_RED + "------ DAWFlix | SUSCRIPCION ------" + ANSI_RESET);
            System.out.println("1. Básica (3.99E / 3 Meses)");
            System.out.println("2. Premium (5.99E / 6 Meses)");
            System.out.println("3. Premium_Plus (10.99E / 12 Meses)");
            System.out.print("Elije Suscripcion: ");
            op = sc.next();

            switch (op) {
                case "1":
                    usuarios.add(new Usuario(nombre, email, contraseña, Suscripcion.BASICA));
                    return;

                case "2":
                    usuarios.add(new Usuario(nombre, email, contraseña, Suscripcion.PREMIUM));
                    return;
                case "3":
                    usuarios.add(new Usuario(nombre, email, contraseña, Suscripcion.PREMIUM_PLUS));
                    return;
                default:
                    System.out.println("[ERROR] OPCION NO VALIDA");
                    break;
            }
        }
    }

    public static void menuPrincipal() throws FavoritoDuplicadoExcepcion {
        while (keepGoing) {
            System.out.println("");
            System.out.println(ANSI_RED + "------ DAWFlix | MENU PRINCIPAL ------" + ANSI_RESET);
            System.out.println("1. Mostrar Todos Los Contenidos");
            System.out.println("2. Seleccionar Contenido");
            System.out.println("3. Visualizar Cuenta");
            System.out.println("4. Ver cantidad de reproducciones por usuario");
            System.out.println("--------------------------------");
            System.out.println("0. Cerrar Sesion");
            System.out.println("");
            System.out.print("OPCION: ");
            op = sc.next();

            switch (op) {
                case "1":
                    mostrarContenidos();
                    break;

                case "2":
                    seleccionarContenido();
                    break;

                case "3":
                    mostrarDetallesCuenta();
                    break;
                case "4":
                    mostrarConteoReproducciones();
                    break;
                default:
                    System.out.println("[ERROR] OPCION NO VALIDA");
                    break;
            }
        }
    }

    public static void mostrarContenidos() {
        System.out.println("");
        List<Contenido> listaContenidos = new ArrayList<>(contenidos.keySet());
Collections.sort(listaContenidos, new ComparatorByName());
        System.out.println(ANSI_RED + "Peliculas" + ANSI_RESET);
        for (Contenido contenido : listaContenidos) {
            if (contenido instanceof Pelicula) {
                System.out.println(contenido.toString());
            }
        }

        System.out.println(ANSI_RED + "Series" + ANSI_RESET);
        for (Contenido contenido : listaContenidos) {
            if (contenido instanceof Serie) {
                System.out.println(contenido.toString());
            }
        }

        System.out.println(ANSI_RED + "Canciones" + ANSI_RESET);
        for (Contenido contenido : listaContenidos) {
            if (contenido instanceof Musica) {
                System.out.println(contenido.toString());
            }
        }
    }

    public static void seleccionarContenido() {
        sc.nextLine();
        boolean contenidoEnFavoritos = false, contenidoEnRecientes = false;
        Contenido contenidoSeleccionado = null;
        System.out.println("");
        System.out.print("Titulo: ");
        titulo = sc.nextLine().toLowerCase();

        List<Contenido> listaContenidos = new ArrayList<>(dao.selectContenidos().keySet());
        
        for (Contenido contenido : listaContenidos) {
            if (contenido.getTitulo().toLowerCase().equals(titulo)) {
                contenidoSeleccionado = contenido;
                contenidoEncontrado = true;
            }
        }
        if (!contenidoEncontrado) {
            System.out.println("Contenido No Encontrado");
            return;
        }
        while (keepGoing) {
            contenidoEncontrado = false;
            System.out.println("");
            System.out.println(ANSI_RED + "------ DAWFlix | CONTENIDO SELECCIONADO " + "'"
                    + contenidoSeleccionado.getTitulo().toUpperCase() + "'"
                    + " ------" + ANSI_RESET);
            System.out.println("1. Reproducir");
            System.out.println("2. Añadir a Favoritos");
            System.out.println("3. Atras");
            System.out.print("OPCION: ");
            op = sc.next();
            sc.nextLine();

            switch (op) {
                case "1":
                    contenidoSeleccionado.reproducir();

                    for (Contenido contenido : usuarioLogeado.getRecientes()) {
                        if (contenido.equals(contenidoSeleccionado)) {
                            contenidoEnRecientes = true;
                            break;
                        }
                    }

                    if (!contenidoEnRecientes) {
                        usuarioLogeado.añadirRecientes(contenidoSeleccionado);
                    }

                    break;

                case "2":
                    usuarioLogeado.añadirFavorito(contenidoSeleccionado);
                    break;

                case "3":
                    System.out.println("Atras");
                    return;

                default:
                    System.out.println("[ERROR] OPCION NO VALIDA");
                    break;
            }
        }
    }

    public static void mostrarDetallesCuenta() {
        System.out.println("");
        System.out.println("Usuario: " + usuarioLogeado.getNombre());
        System.out.println("Email: " + usuarioLogeado.getEmail());
        usuarioLogeado.mostrarFavoritos();
        usuarioLogeado.mostrarRecientes();
        System.out.println("Suscricpcion: " + usuarioLogeado.getSuscripcion());
    }

    public static void mostrarConteoReproducciones() {
        TreeMap<String, Integer> conteo = EstadisticasDAO.obtenerConteoReproducciones();

        System.out.printf("%-10s | %s%n", "Usuario", "Veces Reproducido");
        System.out.println("-----------------------------");

        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            System.out.printf("%-10s | %d%n", entry.getKey(), entry.getValue());
        }
    }

    
}