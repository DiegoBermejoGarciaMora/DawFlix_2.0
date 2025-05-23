package Model.Contenido;

import Interface.Reproducible;
import Model.Contenido.Generos.Genero;
import Model.Contenido.Generos.GeneroMusica;

public abstract class Contenido implements Reproducible, Comparable<Contenido> {
    String titulo, descripcion;
    int puntuacion;
    Genero genero;
    GeneroMusica generoMusica;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public GeneroMusica getGeneroMusica() {
        return generoMusica;
    }

    public void setGeneroMusica(GeneroMusica generoMusica) {
        this.generoMusica = generoMusica;
    }

    // Constructor para Peliculas / Series
    public Contenido(String titulo, String descripcion, int puntuacion, Genero genero) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.genero = genero;
    }

    public Contenido(String titulo) {
        this.titulo = titulo;
    }


    // Constructor para Musica
    public Contenido(String titulo, String descripcion, int puntuacion, GeneroMusica generoMusica) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.generoMusica = generoMusica;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contenido other = (Contenido) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo: " + titulo);
    }

    @Override
    public String toString() {
        return "Contenido " + titulo + ", descripcion: " + descripcion + ", puntuacion:" + puntuacion
                + ", genero:" + genero;
    }

    @Override
public int compareTo(Contenido otro) {
    return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    
}
