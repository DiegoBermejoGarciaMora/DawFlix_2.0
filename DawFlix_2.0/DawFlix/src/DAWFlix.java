import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.ContenidoDAO;
import Exceptions.AdministradorBaneadoExcepcion;
import Exceptions.FavoritoDuplicadoExcepcion;
import Exceptions.UsusarioBaneadoExcepcion;
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

    public static Scanner sc = new Scanner(System.in);
    // DAO
    static ContenidoDAO dao = new ContenidoDAO();
    public static List<Contenido> contenidos = dao.selectContenidos();
    public static List<Usuario> usuarios = dao.selectUsuarios();
    public static List<Usuario> usuariosAdmin = dao.selectUsuariosAdmin();
    public static List<Usuario> usuariosBaneados = dao.selectUsuariosBaneados();

    // Variables bucles menus
    public static String op;
    public static boolean isAdmin;
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

    public static void main(String[] args) throws Exception {
        login();
        menuPrincipal();
    }

    public static void login() throws UsusarioBaneadoExcepcion {
        String nombre, email, contraseña;
        Boolean login = false, usuarioBaneado = false;
        isAdmin = false;

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
                    try {
                        System.out.print("Correo Electronico: ");
                        email = sc.next();
                        System.out.print("Contraseña: ");
                        contraseña = sc.next();
                        Usuario userLogin = new Usuario(email, contraseña);

                        // Comprobar si el usuario esta baneado
                        for (Usuario usuario : usuariosBaneados) {
                            if (userLogin.equals(usuario)) {
                                throw new UsusarioBaneadoExcepcion("USUARIO BANEADO PERMANENTEMENTE");
                            }
                        }

                        boolean usuarioEncontrado = false;
                        for (Usuario usuario : usuarios) {
                            if (userLogin.equals(usuario)) {
                                usuarioEncontrado = true;
                                usuarioLogeado = usuario;
                                break;
                            }
                        }

                        // Comprueba si el usuario es el administrador (mas opciones en el menu)
                        for (Usuario usuarioAdmin : usuariosAdmin) {
                            if (userLogin.equals(usuarioAdmin)) {
                                isAdmin = true;
                                usuarioLogeado = usuarioAdmin;
                                break;
                            }
                        }

                        if (usuarioEncontrado || isAdmin) {
                            login = true;
                            System.out.println("Sesion Iniciada Con Exito");
                        } else {
                            System.out.println("[ERROR] Email o Contraseña Incorrectas");
                        }
                    } catch (UsusarioBaneadoExcepcion e) {
                        System.out.println(e.getMessage());
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
                    for (Usuario usuarioBan : usuariosBaneados) {
                        if (usuarioBan.getEmail().equals(email)) {
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

    public static void menuPrincipal()
            throws UsusarioBaneadoExcepcion, AdministradorBaneadoExcepcion, FavoritoDuplicadoExcepcion {
        while (keepGoing) {
            System.out.println("");
            System.out.println(ANSI_RED + "------ DAWFlix | MENU PRINCIPAL ------" + ANSI_RESET);
            System.out.println("1. Mostrar Todos Los Contenidos");
            System.out.println("2. Seleccionar Contenido");
            System.out.println("3. Visualizar Cuenta");
            System.out.println("--------------------------------");
            if (isAdmin) {
                System.out.println("4. Añadir Contenido");
                System.out.println("5. Borrar Contenido");
                System.out.println("--------------------------------");
                System.out.println("6. Mostrar Usuarios Baneados");
                System.out.println("7. Banear Usuario");
                System.out.println("8. Desbanear Usuario");
                System.out.println("--------------------------------");
            }
            System.out.println("0. Cerrar Sesion");
            System.out.println("");
            System.out.print("OPCION: ");
            op = sc.next();

            if (!isAdmin) {
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
                    case "0":
                        login();
                        break;

                    default:
                        System.out.println("[ERROR] OPCION NO VALIDA");
                        break;
                }
            } else {
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
                        añadirContenido();
                        break;
                    case "5":
                        eliminarContenido();
                        break;
                    case "6":
                        System.out.println("");
                        System.out.println(ANSI_RED + "------ DAWFlix | LISTA DE BANEADOS ------" + ANSI_RESET);
                        for (Usuario usuario : usuariosBaneados) {
                            System.out.println(usuario.toString());
                        }
                        break;
                    case "7":
                        banearUsuario();
                        break;
                    case "8":
                        desbanearUsuario();
                        break;
                    case "0":
                        login();
                        break;
                    default:
                        System.out.println("[ERROR] OPCION NO VALIDA");
                        break;
                }
            }

        }
    }

    public static void mostrarContenidos() {
        System.out.println("");
        Collections.sort(contenidos, new ComparatorByName());
        System.out.println(ANSI_RED + "Peliculas" + ANSI_RESET);
        for (Contenido contenido : contenidos) {
            if (contenido instanceof Pelicula) {
                System.out.println(contenido.toString());
            }
        }

        System.out.println(ANSI_RED + "Series" + ANSI_RESET);
        for (Contenido contenido : contenidos) {
            if (contenido instanceof Serie) {
                System.out.println(contenido.toString());
            }
        }

        System.out.println(ANSI_RED + "Canciones" + ANSI_RESET);
        for (Contenido contenido : contenidos) {
            if (contenido instanceof Musica) {
                System.out.println(contenido.toString());
            }
        }
    }

    public static void añadirContenido() {
        while (keepGoing) {
            System.out.println("");
            System.out.println(
                    ANSI_RED + "------ DAWFlix | OPCIONES DE ADMINISTRADOR - AÑADIR CONTENIDO ------" + ANSI_RESET);
            System.out.println("Que Tipo de Contenido Quiere Añadir");
            System.out.println("1. Peliculas");
            System.out.println("2. Series");
            System.out.println("3. Musica");
            System.out.println("4. Salir");
            System.out.print("OPCION: ");
            op = sc.next();
            sc.nextLine();

            switch (op) {
                // Peliculas
                case "1":
                    System.out.print("Titulo: ");
                    titulo = sc.nextLine();

                    System.out.print("Descripcion: ");
                    descripcion = sc.nextLine();

                    System.out.print("Puntuacion: ");
                    puntuacion = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Genero: ");
                    for (Genero gen : Genero.values()) {
                        System.out.print(gen + " ");
                    }
                    System.out.println("");
                    generoS = sc.nextLine().toUpperCase();
                    try {
                        genero = Genero.valueOf(generoS);
                    } catch (Exception e) {
                        System.out.println("[ERROR] Genero No Valido");
                        break;
                    }

                    System.out.print("Duracion: ");
                    duracion = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Director: ");
                    director = sc.nextLine();

                    contenidos.add(new Pelicula(titulo, descripcion, puntuacion, genero, duracion, director));
                    System.out.println("Pelicula " + titulo + " añadida");
                    break;

                // Series
                case "2":
                    System.out.println("Ha elegido: Añadir Serie");
                    System.out.print("Titulo: ");
                    titulo = sc.nextLine();

                    System.out.print("Descripcion: ");
                    descripcion = sc.nextLine();

                    System.out.print("Puntuacion: ");
                    puntuacion = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Genero: ");
                    for (Genero gen : Genero.values()) {
                        System.out.print(gen + " ");
                    }
                    System.out.println("");
                    generoS = sc.nextLine().toUpperCase();
                    try {
                        genero = Genero.valueOf(generoS);
                    } catch (Exception e) {
                        System.out.println("[ERROR] Genero No Valido");
                        break;
                    }

                    System.out.print("Capitulos: ");
                    capitulos = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Temporadas: ");
                    temporadas = sc.nextInt();
                    sc.nextLine();

                    contenidos.add(new Serie(titulo, descripcion, puntuacion, genero, capitulos, temporadas));
                    System.out.println("Serie " + titulo + " añadida");
                    break;

                // Musica
                case "3":
                    System.out.println("Ha elegido: Añadir Musica");
                    System.out.print("Titulo: ");
                    titulo = sc.nextLine();

                    System.out.print("Descripcion: ");
                    descripcion = sc.nextLine();

                    System.out.print("Puntuacion: ");
                    puntuacion = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Generos Disponibles: ");
                    for (GeneroMusica gen : GeneroMusica.values()) {
                        System.out.print(gen + " ");
                    }
                    System.out.println("");
                    generoS = sc.nextLine().toUpperCase();
                    try {
                        generoMusica = GeneroMusica.valueOf(generoS);
                    } catch (Exception e) {
                        System.out.println("[ERROR] Genero No Valido");
                        break;
                    }

                    System.out.print("Duracion: ");
                    duracion = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Artista: ");
                    artista = sc.nextLine();

                    contenidos.add(new Musica(titulo, descripcion, puntuacion, generoMusica, duracion, artista));
                    System.out.println("Cancion " + titulo + " añadida");
                    break;

                case "4":
                    return;
                default:
                    System.out.println("[ERROR] OPCION NO VALIDA");
                    break;
            }
        }
    }

    public static void eliminarContenido() {
        Iterator<Contenido> iter = contenidos.iterator();
        while (keepGoing) {
            contenidoEncontrado = false;
            System.out.println("");
            System.out.println(
                    ANSI_RED + "------ DAWFlix | OPCIONES DE ADMINISTRADOR - BORRAR CONTENIDO ------" + ANSI_RESET);
            System.out.println("Que Tipo de Contenido Quiere Eliminar");
            System.out.println("1. Peliculas");
            System.out.println("2. Series");
            System.out.println("3. Musica");
            System.out.println("4. Atrás");
            System.out.print("OPCION: ");
            op = sc.next();
            sc.nextLine();

            switch (op) {
                // Peliculas
                case "1":
                    System.out.print("Titulo: ");
                    titulo = sc.nextLine().toLowerCase();
                    contenidoEncontrado = false;

                    iter = contenidos.iterator(); // Reiniciar el iterator
                    while (iter.hasNext()) {
                        Contenido pelicula = iter.next();
                        if (pelicula.getTitulo().toLowerCase().equals(titulo)) {
                            iter.remove();
                            contenidoEncontrado = true;
                        }
                    }
                    if (contenidoEncontrado)
                        System.out.println("Pelicula Borrada Con Exito");
                    else
                        System.out.println("Pelicula No Encontrada");

                    break;

                // Series
                case "2":
                    System.out.print("Titulo: ");
                    titulo = sc.nextLine().toLowerCase();
                    contenidoEncontrado = false;

                    iter = contenidos.iterator();
                    while (iter.hasNext()) {
                        Contenido serie = iter.next();
                        if (serie.getTitulo().toLowerCase().equals(titulo)) {
                            iter.remove();
                            contenidoEncontrado = true;
                        }
                    }
                    if (contenidoEncontrado)
                        System.out.println("Serie Borrada Con Exito");
                    else
                        System.out.println("Serie No Encontrada");

                    break;

                // Musica
                case "3":
                    System.out.print("Titulo: ");
                    titulo = sc.nextLine().toLowerCase();
                    contenidoEncontrado = false;

                    iter = contenidos.iterator();
                    while (iter.hasNext()) {
                        Contenido musica = iter.next();
                        if (musica.getTitulo().toLowerCase().equals(titulo)) {
                            iter.remove();
                            contenidoEncontrado = true;
                        }
                    }
                    if (contenidoEncontrado)
                        System.out.println("Musica Borrada Con Exito");
                    else
                        System.out.println("Musica No Encontrada");
                    break;

                case "4":
                    return;
                default:
                    System.out.println("[ERROR] OPCION NO VALIDA");
                    break;
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
        for (Contenido contenido : contenidos) {
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

    public static void banearUsuario() throws AdministradorBaneadoExcepcion {
        sc.nextLine();
        System.out.println("");
        boolean usuarioEncontrado = false;
        Usuario usuarioBanear = null;
        String email;
        System.out.print("Email: ");
        email = sc.nextLine().toLowerCase();

        try {
            for (Usuario usuario : usuariosAdmin) {
                if (usuario.getEmail().toLowerCase().equals(email)) {
                    throw new AdministradorBaneadoExcepcion("No Puedes Banear A Un Administrador");
                }
            }
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().toLowerCase().equals(email)) {
                    usuarioBanear = usuario;
                    usuarioEncontrado = true;
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("Usuario No Encontrado");
                return;
            }

            usuariosBaneados.add(usuarioBanear);
            usuarios.remove(usuarioBanear);
            System.out.println(usuarioBanear.getEmail() + " Baneado Permanentemente");
        } catch (AdministradorBaneadoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    public static void desbanearUsuario() {
        sc.nextLine();
        System.out.println("");
        boolean usuarioEncontrado = false;
        Usuario usuarioDesBanear = null;
        String email;
        System.out.print("Email: ");
        email = sc.nextLine().toLowerCase();

        for (Usuario usuario : usuariosBaneados) {
            if (usuario.getEmail().toLowerCase().equals(email)) {
                usuarioDesBanear = usuario;
                usuarioEncontrado = true;
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("Usuario No Encontrado");
            return;
        }

        usuariosBaneados.remove(usuarioDesBanear);
        usuarios.add(usuarioDesBanear);
        System.out.println(usuarioDesBanear.getEmail() + " Desbaneado");
    }

}