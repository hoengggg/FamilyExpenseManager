package Controller;

import Repository.ViCaNhanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vi_ca_nhan/delete")
public class ViCaNhanDeleteServlet extends HttpServlet {
    ViCaNhanRepository repo = new ViCaNhanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        repo.xoa(id);
        resp.sendRedirect(req.getContextPath() + "/vi_ca_nhan");
    }
}
