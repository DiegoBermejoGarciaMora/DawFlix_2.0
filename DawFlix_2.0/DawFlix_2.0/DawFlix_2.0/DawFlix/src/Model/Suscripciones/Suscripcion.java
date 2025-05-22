package Model.Suscripciones;

public enum Suscripcion {
    BASICA(3.99, 3),
    PREMIUM(5.99, 6),
    PREMIUM_PLUS(10.99, 12);

    double precio;
    int duracion; // En meses

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    private Suscripcion(double precio, int duracion) {
        this.precio = precio;
        this.duracion = duracion;
    }
}