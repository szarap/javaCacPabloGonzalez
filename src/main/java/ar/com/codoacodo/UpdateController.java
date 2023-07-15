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

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        DAO dao = new MySQLDAOImpl();

        try {
            Articulo articulo = dao.getById(Long.parseLong(id));

            req.setAttribute("articulo", articulo);
        } catch (Exception e) {
            req.setAttribute("error", "No se ha eliminado el articulo");
            getServletContext().getRequestDispatcher("/ListadoArticuloController").forward(req, resp);
        }

        getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        DAO dao = new MySQLDAOImpl();
        try {
            Articulo articuloExistente = dao.getById(Long.parseLong(id));

            String titulo = req.getParameter("nombre");
            double precio = Double.parseDouble(req.getParameter("precio"));
            String autor = req.getParameter("autor");
            String codigo = req.getParameter("codigo");
            String isbn = "123465465456";
            LocalDateTime fechaCreacion = LocalDateTime.now();

            Articulo articuloActualizado = new Libro(titulo, codigo, autor, precio, false, isbn, codigo, fechaCreacion);
            articuloActualizado.setId(articuloExistente.getId());

            dao.update(articuloActualizado);

            resp.sendRedirect(req.getContextPath() + "/ListadoArticuloController");
        } catch (Exception e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
        }
    }
    
}
