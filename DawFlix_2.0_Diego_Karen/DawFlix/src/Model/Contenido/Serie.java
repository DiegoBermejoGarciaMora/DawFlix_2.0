package Model.Contenido;

import Model.Contenido.Generos.Genero;

public class Serie extends Contenido {
    int capitulos, temporadas;

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public Serie(String titulo, String descripcion, int puntuacion, Genero genero, int capitulos, int temporadas) {
        super(titulo, descripcion, puntuacion, genero);
        this.capitulos = capitulos;
        this.temporadas = temporadas;
    }

    public Serie(String titulo) {
        super(titulo);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Serie: " + titulo);
    }

    @Override
    public String toString() {
        return "· " + titulo + ", " + capitulos + " capitulos" + ", " + temporadas + " temporadas";
    }

}