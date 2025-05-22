package Model.Usuarios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Model.Contenido.Contenido;
import Model.Suscripciones.Suscripcion;

public class Usuario {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    String nombre, email, contraseña;
    HashSet<Contenido> favoritos = new HashSet<>();
    List<Contenido> recientes = new ArrayList<>();
    Suscripcion suscripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public HashSet<Contenido> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(HashSet<Contenido> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Contenido> getRecientes() {
        return recientes;
    }

    public void setRecientes(List<Contenido> recientes) {
        this.recientes = recientes;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Usuario(String nombre, String email, String contraseña, Suscripcion suscripcion) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.suscripcion = suscripcion;
    }

    public Usuario(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }

    // HASCODE Y EQUALS: EMAIL Y CONTRASEÑA
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((contraseña == null) ? 0 : contraseña.hashCode());
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
        Usuario other = (Usuario) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (contraseña == null) {
            if (other.contraseña != null)
                return false;
        } else if (!contraseña.equals(other.contraseña))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario " + nombre + ", email: " + email;
    }

    public void añadirFavorito(Contenido contenidoFav) {
        favoritos.add(contenidoFav);
        System.out.println("Añadido a 'Favoritos' " + contenidoFav);
    }

    public void mostrarFavoritos() {
        System.out.println(ANSI_GREEN + "- FAVORITOS" + ANSI_RESET);
        if (favoritos.isEmpty()) {
            System.out.println("Lista 'Favoritos' Vacía");
        } else {
            for (Contenido contenido : favoritos) {
                System.out.println(contenido.toString());
            }
        }
    }

    public void añadirRecientes(Contenido contenidoReciente) {
        recientes.add(contenidoReciente);
    }

    public void mostrarRecientes() {
        System.out.println(ANSI_BLUE + "- RECIENTE" + ANSI_RESET);
        if (recientes.isEmpty()) {
            System.out.println("Lista 'RECIENTE' Vacía");
        } else {
            for (Contenido contenido : recientes) {
                System.out.println(contenido.toString());
            }
        }
    }
}