package Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import Model.Contenido.Contenido;

public class GestionarRecientes {
    private static final String FILE_PATH = "reci.csv";

    public static void guardarReciente(List<Contenido> recientes) throws IOException {
        String linea = "";

        for (Contenido rec : recientes) {
            linea += rec + "\n";
        }

        Files.write(Paths.get(FILE_PATH), linea.getBytes(), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void mostrarRecientes() {
        try {
            String contenido = Files.readString(Paths.get(FILE_PATH));
            System.out.println(contenido);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
