package Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;

import Model.Contenido.Contenido;

public class GestionarFavoritos {
    private static final String FILE_PATH = "favs.csv";

    public static void guardarFavorito(HashSet<Contenido> favoritos) throws IOException {
        String linea = "";

        for (Contenido fav : favoritos) {
            linea += fav + "\n";
        }

        Files.write(Paths.get(FILE_PATH), linea.getBytes(), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void mostrarFavoritos() {
        try {
            String contenido = Files.readString(Paths.get(FILE_PATH));
            System.out.println(contenido);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}