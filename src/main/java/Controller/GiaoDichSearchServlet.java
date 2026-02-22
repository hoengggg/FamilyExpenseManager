package Controller;

import Repository.GiaoDichVaTongQuanRepository;
import Repository.ViCaNhanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/giao_dich/search")
public class GiaoDichSearchServlet extends HttpServlet {
    GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mota = req.getParameter("mota");
        req.setAttribute("giaodichs", repo.timkiem(mota));
        req.getRequestDispatcher("/View/giao_dich.jsp").forward(req, resp);
    }
}
