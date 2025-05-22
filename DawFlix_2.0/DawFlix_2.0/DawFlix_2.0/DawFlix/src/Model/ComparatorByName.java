package Model;

import java.util.Comparator;

import Model.Contenido.Contenido;

public class ComparatorByName implements Comparator<Contenido>{

    @Override
    public int compare(Contenido c1, Contenido c2) {
        return c1.getTitulo().compareTo(c2.getTitulo());
    }
    
}
