package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Model.Contenido.Contenido;
import Model.Contenido.Generos.Genero;
import Model.Contenido.Generos.GeneroMusica;
import Model.Suscripciones.Suscripcion;
import Model.Contenido.Musica;
import Model.Contenido.Pelicula;
import Model.Contenido.Serie;
import Model.Usuarios.Usuario;

public class ContenidoDAO {
        private final TreeMap<Contenido, String> contenidos = new TreeMap<>();
        private final List<Usuario> usuarios = new ArrayList<>();
        private final List<Usuario> usuariosAdmin = new ArrayList<>();
        private final List<Usuario> usuariosBaneados = new ArrayList<>();

        public ContenidoDAO() {
                // ----------------- PELICULAS -----------------
                contenidos.put(new Pelicula("Piratas del Caribe: La maldición de la Perla Negra",
                                "Aventura pirata con Jack Sparrow buscando un barco maldito",
                                5,
                                Genero.ACCION,
                                143,
                                "Gore Verbinski"), "piratas.wav");

                contenidos.put(new Pelicula("Interestelar",
                "Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad.",
                4, Genero.CIENCIA_FICCION, 169, "Christopher Nolan"), "interestelar.wav");

                contenidos.put(new Pelicula("Los Juegos del Hambre",
                        "Katniss lucha en un reality show mortal en un futuro distópico",
                        4, Genero.ACCION, 142, "Gary Ross"), "juegos_hambre.wav");

                contenidos.put(new Pelicula("Transporter",
                        "Exmilitar transporta paquetes peligrosos con acción a alta velocidad",
                        3, Genero.ACCION, 92, "Louis Leterrier"), "transporter.wav");

                contenidos.put(new Pelicula("Salvar al Soldado Ryan",
                        "Intensa guerra tras el Día-D para rescatar a un soldado",
                        5, Genero.BELICO, 169, "Steven Spielberg"), "soldado_ryan.wav");

                // ----------------- SERIES -----------------
                contenidos.put(new Serie("Attack on Titan",
                "Mundo habitado de titanes que comen personas.", 5, Genero.DRAMA, 88, 4), "attack_on_titan.wav");

                contenidos.put(new Serie("Death Note",
                        "Light, cansado de la delincuencia encuentra una libreta que mata personas con solo escribir su nombre.",
                        4, Genero.DRAMA, 37, 1), "death_note.wav");

                contenidos.put(new Serie("South Park",
                        "Comedia de situación que sigue la vida cotidiana de los niños del pueblo.",
                        4, Genero.COMEDIA, 327, 26), "south_park.wav");

                contenidos.put(new Serie("Doraemon",
                        "Gato robotico del futuro viene del futuro para ayudar a Nobita a que no se meta en problemas.",
                        5, Genero.COMEDIA, 1700, 40), "doraemon.wav");

                contenidos.put(new Serie("The Last Of Us",
                        "serie postapocalíptica de HBO donde Joel y Ellie viajan en un mundo infectado, mezclando acción, terror y drama emocional.",
                        0, Genero.ACCION, 9, 1), "last_of_us.wav");

                // ----------------- MUSICA -----------------
                contenidos.put(new Musica("Don't Stop The Music",
                        "Canción dance-pop de Rihanna, ritmo contagioso, inspirada en Michael Jackson.",
                        5, GeneroMusica.POP, 239, "Rihanna"), "dont_stop_the_music.wav");

                contenidos.put(new Musica("California Gurls",
                        "Himno veraniego de Katy Perry con Snoop Dogg, celebra la diversión en la playa.",
                        5, GeneroMusica.POP, 236, "Katy Perry ft. Snoop Dogg"), "california_gurls.wav");

                contenidos.put(new Musica("P.Y.T",
                        "Clásico de Michael Jackson, groove funk y coros pegajosos sobre el amor juvenil.",
                        5, GeneroMusica.POP, 239, "Michael Jackson"), "pyt.wav");

                contenidos.put(new Musica("OIIA OIIA",
                        "Gato Giratorio", 5, GeneroMusica.ELECTRONICA, 134, "W&W"), "oiia_oiia.wav");

                contenidos.put(new Musica("BADLOBA BREAKBEAT",
                        "Track electrónico enérgico con ritmos rotos y sonidos futuristas, ideal para el baile.",
                        4, GeneroMusica.HIP_HOP, 150, "DJ Jilguero"), "badloba.wav");


                // ----------------- USUARIOS BASE -----------------
                usuarios.add(new Usuario("user1", "user1", "user1", Suscripcion.BASICA));
                usuarios.add(new Usuario("user2", "user2", "user2", Suscripcion.PREMIUM));
                usuarios.add(new Usuario("a", "a", "a", Suscripcion.PREMIUM_PLUS));
        }

        //Metodo para obtener todos los contenidos
        public TreeMap<Contenido, String> selectContenidos(){
                return contenidos;
        }

        public ArrayList<Usuario> selectUsuarios() {
                return new ArrayList<>(usuarios);
        }

        // Método para mostrar los contenidos ordenados
        public void mostrarContenidos() {
        for (Map.Entry<Contenido, String> entry : contenidos.entrySet()) {
        System.out.println(entry.getKey().getTitulo() + " - " + entry.getValue());
                }
        }
}