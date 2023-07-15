package ar.com.codoacodo;

import java.io.IOException;
import java.time.LocalDateTime;

import ar.com.codoacodo.DAO.DAO;
import ar.com.codoacodo.DAO.MySQLDAOImpl;
import ar.com.codoacodo.oop.Articulo;
import ar.com.codoacodo.oop.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/AltaArticuloController")
public class AltaArticuloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //CREATE CONTROLLER 
        //System.out.println("AltaArticuloController");

        //parametros del front (<form>)
        String titulo = req.getParameter("nombre");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String autor  = req.getParameter("autor");
        String codigo  = req.getParameter("codigo");
        String isbn  = "123465465456";
        LocalDateTime ldt = LocalDateTime.now();
        String imagen = "";
        
        Articulo nuevo = new Libro(titulo, imagen, autor, precio, false, isbn, codigo, ldt);
        
        DAO dao = new MySQLDAOImpl();
        
        try {
            dao.create(nuevo);
            //redirect
            //getServletContext().getRequestDispatcher("/ListadoArticuloController").forward(req, resp);//POST ListadoArticuloController
            resp.sendRedirect(req.getContextPath() + "/ListadoArticuloController");
        } catch (Exception e) {
            //redirect
            getServletContext().getRequestDispatcher("/nuevo.jsp").forward(req, resp);
            e.printStackTrace();//muestra los pasos hasta el error
            //e.getMessage();
        } 

    }
}
