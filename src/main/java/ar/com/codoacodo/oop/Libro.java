package ar.com.codoacodo.oop;

import java.time.LocalDateTime;

public class Libro extends Articulo {

    private String isbn;

    public Libro(
        String titulo,
        String imagen,
        String autor,
        double precio,
        boolean novedad,
        String isbn,
        String codigo,
        LocalDateTime fechaCreacion ) {
        super(titulo, imagen, autor, precio, novedad, codigo, fechaCreacion);
        this.isbn = isbn;
    }
    
    public String obtenerIsbn() {
        return this.isbn;
    }

    @Override
    public String toString() {
        return super.toString() + ", Libro [isbn=" + isbn + "]";
    }
}
