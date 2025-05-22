package Model.Contenido;

import Model.Contenido.Generos.Genero;

public class Pelicula extends Contenido {
    int duracion; // En minutos
    String director;

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Pelicula(String titulo, String descripcion, int puntuacion, Genero genero, int duracion,
            String director) {
        super(titulo, descripcion, puntuacion, genero);
        this.duracion = duracion;
        this.director = director;
    }

    public Pelicula(String titulo) {
        super(titulo);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Pelicula: " + titulo + " diriga por " + director);
    }

    @Override
    public String toString() {
        return "· " + titulo + " (" + director + ")" + ", " + duracion + " minutos";
    }
}