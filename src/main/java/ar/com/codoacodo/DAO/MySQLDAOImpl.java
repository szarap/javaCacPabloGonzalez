package ar.com.codoacodo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import ar.com.codoacodo.db.AdministradorDeConexiones;
import ar.com.codoacodo.oop.Articulo;

public class MySQLDAOImpl implements DAO {

    private String tableName;

    //constructor
    public MySQLDAOImpl()  {
        this.tableName = "articulos";
    }

    //metodos
    public Articulo getById(Long id) throws Exception {// 1
        String sql = "select * from " + this.tableName + " where id =?";

        // Obtener la Conection
        Connection con = AdministradorDeConexiones.getConnection();

        // PreparedStatement con mi sql 
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setLong(1, id);

        Articulo articulo = null;

        ResultSet res = pst.executeQuery();

        //el orden de los parametros deben coincidir con el orden de los datos en la DB
        if (res.next()) {
            Long _id = res.getLong(1);
            String titulo = res.getString(2);
            String imagen = res.getString(3);
            String autor = res.getString(4);
            String novedad = res.getString(5);
            Date fechaCreacion = res.getDate(6);
            String codigo = res.getString(7);
            Double precio = res.getDouble(8);

            articulo = new Articulo(_id, titulo, imagen, autor, precio, false, codigo, LocalDateTime.now());
        }
        return articulo;
    }

    @Override
    public void delete(Long id) throws Exception {
        String sql = "delete from " + this.tableName + " where id = ? ";

        Connection con = AdministradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public ArrayList<Articulo> findAll() throws Exception {
        String sql = "select * from " + this.tableName + "";

        return findBySQL(sql);
    }

    @Override
    public void update(Articulo articulo) {
        String sql = "update " + this.tableName + " set titulo= ? , precio= ?, autor= ? where id= ?";

        try (Connection con = AdministradorDeConexiones.getConnection();
        PreparedStatement statement = con.prepareStatement(sql)) {

       statement.setString(1, articulo.getTitulo());
       statement.setDouble(2, articulo.getPrecio());
       statement.setString(3, articulo.getAutor());
       statement.setLong(4, articulo.getId());

       statement.executeUpdate();


   } catch (SQLException e) {
       e.getMessage();
   }

    }

    @Override
    public void create(Articulo articulo) throws Exception {
        String sql = "insert into " + this.tableName;
        sql += "(titulo,autor,precio,fechaCreacion,novedad,codigo) ";
        sql += "values (?,?,?,?,?,?) ";
        
        Connection con = AdministradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        // sql injection!
        pst.setString(1, articulo.getTitulo());
        pst.setString(2, articulo.getAutor());
        pst.setDouble(3, articulo.getPrecio());
        pst.setDate(4, this.dateFrom(articulo.getFechaCreacion()));// fecha LocalDateTime > jdbc > java.sql.Date
        pst.setInt(5, articulo.isNovedad() ? 1 : 0);
        pst.setString(6, articulo.getCodigo());

        // RestultSet
        pst.executeUpdate();
    }

    private Date dateFrom(LocalDateTime ldt) {
        java.util.Date date = Date.from(ldt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new java.sql.Date(date.getTime());
    }

    @Override
    public ArrayList<Articulo> findAllByTitle(String clave) throws Exception {

        String sql = "select * from " + this.tableName + " where UPPER(titulo) like '%" + clave.toUpperCase() + "%'";

        return findBySQL(sql);
    }

    private ArrayList<Articulo> findBySQL(String sql) throws SQLException {
        
        Connection con = AdministradorDeConexiones.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);

        ArrayList<Articulo> listado = new ArrayList<>();

        ResultSet res = pst.executeQuery();

        while (res.next()) {
            Long id = res.getLong(1);
            String titulo = res.getString(2);
            String imagen = res.getString(3);
            String autor = res.getString(4);
            Long novedad = res.getLong(5);
            Date fechaCreacion = res.getDate(6);
            String codigo = res.getString(7);
            Double precio = res.getDouble(8);

            boolean esNovedad = novedad.equals(1L);

            listado.add(new Articulo(id, titulo, imagen, autor, precio, esNovedad, codigo, LocalDateTime.now()));
        }
        return listado;
    }


    
}
