package ar.com.codoacodo;

import java.time.LocalDateTime;

import ar.com.codoacodo.DAO.DAO;
import ar.com.codoacodo.DAO.MySQLDAOImpl;
import ar.com.codoacodo.oop.Articulo;
import ar.com.codoacodo.oop.Libro;

public class AltaArticuloController 
{
    public static void main( String[] args )
    {
       String titulo = "el lalala";
       String imagen = "imagen 3";
       String autor = "yo";
       double precio = 1500;
       boolean novedad = true;
       String isbn = "9999asdfas";
       String codigo = "xxxxxxxx";
       LocalDateTime ldt = LocalDateTime.now();

       Articulo nuevo = new Libro(titulo, imagen, autor, precio, novedad, isbn, codigo, ldt);

       //System.out.println(nuevo);

       DAO dao = new MySQLDAOImpl();

       try {
        dao.create(nuevo);
    } catch (Exception e) {
        System.out.println("error al ingresar un nuevo dato");
        e.printStackTrace();
    }

    }
}
