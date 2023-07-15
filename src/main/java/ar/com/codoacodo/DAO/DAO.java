package ar.com.codoacodo.DAO;

import java.util.ArrayList;

import ar.com.codoacodo.oop.Articulo;

public interface DAO {

    /*  Utilizar un Data Access Object (DAO) para abstraer y encapsular todos los accesos a la fuente de datos. 
        Principalmente independizar la aplicación de la forma de acceder a la base de datos */

    public Articulo getById(Long id) throws Exception;//PK de la tabla
    public void delete(Long id) throws Exception;//PK DE LA TABLA
    public ArrayList<Articulo> findAll() throws Exception;
    public void update(Articulo articulo) throws Exception;//Tiene ID
    public void create(Articulo articulo) throws Exception;//NO Tiene ID

    public ArrayList<Articulo> findAllByTitle(String clave) throws Exception;
}

