package Controller;

import Repository.MucTieuRepository;
import Repository.ViCaNhanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/muc_tieu/search")
public class MucTieuSearchServlet extends HttpServlet {
    MucTieuRepository repo = new MucTieuRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ten = req.getParameter("ten");
        req.setAttribute("mucTieus", repo.timkiem(ten));
        req.getRequestDispatcher("/View/muc_tieu.jsp").forward(req, resp);
    }
}
