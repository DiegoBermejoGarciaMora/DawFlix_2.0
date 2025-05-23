package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Contenido.Contenido;
import Model.Contenido.Musica;
import Model.Contenido.Pelicula;
import Model.Contenido.Serie;
import Model.Contenido.Generos.Genero;
import Model.Contenido.Generos.GeneroMusica;

public class ArchivoContenido {
    public static void guardarContenidos(List<Contenido> contenidos, String rutaArchivo) {
        List<String> lineas = contenidos.stream()
                .map(c -> c.getClass().getSimpleName() + ";" + c.getTitulo() + ";" + c.getDescripcion() + ";" + c.getPuntuacion()).collect(Collectors.toList());

        try {
            Files.write(Paths.get(rutaArchivo), lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Contenidos guardados en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Contenido> cargarContenidos(String rutaArchivo) {
        List<Contenido> lista = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));

            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String tipo = partes[0];
                String titulo = partes[1];
                String descripcion = partes[2];
                int puntuacion = Integer.parseInt(partes[3]);

                // Según el tipo, crear instancia:
                if (tipo.equals("Pelicula")) {
                    lista.add(new Pelicula(titulo, descripcion, puntuacion, Genero.ACCION, 0, "Desconocido"));
                } else if (tipo.equals("Serie")) {
                    lista.add(new Serie(titulo, descripcion, puntuacion, Genero.DRAMA, 0, 1));
                } else if (tipo.equals("Musica")) {
                    lista.add(new Musica(titulo, descripcion, puntuacion, GeneroMusica.POP, 0, "Artista"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
