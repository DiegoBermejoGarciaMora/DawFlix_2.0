package Model.Contenido;

import Model.Contenido.Generos.GeneroMusica;

public class Musica extends Contenido {
    int duracion; // En segundos
    String artista;

    public int getDuracion() {
        return duracion / 60;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Musica(String titulo, String descripcion, int puntuacion, GeneroMusica generoMusica, int duracion, String artista) {
        super(titulo, descripcion, puntuacion, generoMusica);
        this.duracion = duracion;
        this.artista = artista;
    }

    public Musica(String titulo) {
        super(titulo);
    }

    @Override
    public void reproducir(){
        System.out.println("Reproduciendo Cancion: " + titulo);
    }
    @Override
    public String toString() {
        return "· " + titulo + " (" + artista + ")" + ", " + getDuracion() + " minutos";
    }
}
