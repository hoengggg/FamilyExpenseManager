package Controller;

import Repository.ViCaNhanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vi_ca_nhan/search")
public class ViCaNhanSearchServlet extends HttpServlet {
    ViCaNhanRepository repo = new ViCaNhanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mota = req.getParameter("mota");
        req.setAttribute("listViCaNhan", repo.timkiem(mota));
        req.getRequestDispatcher("/View/vi_ca_nhan.jsp").forward(req, resp);
    }
}
