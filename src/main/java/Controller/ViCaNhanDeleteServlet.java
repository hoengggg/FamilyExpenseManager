package Controller;

import Entity.DangNhap;
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
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        Long userId = currentUser.getId();
        repo.xoa(id, userId);
        resp.sendRedirect(req.getContextPath() + "/vi_ca_nhan");
    }
}
