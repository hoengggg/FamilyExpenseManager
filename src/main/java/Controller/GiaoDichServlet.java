package Controller;

import Entity.DangNhap;
import Repository.GiaoDichVaTongQuanRepository;
import Entity.GiaoDichVaTongQuan;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/giao_dich")
public class GiaoDichServlet extends HttpServlet {
    private GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        List<GiaoDichVaTongQuan> list = repo.getAll();
        req.setAttribute("giaodichs", list);  //giaodichs tự đặt ở đây
        req.getRequestDispatcher("/View/giao_dich.jsp").forward(req, resp);
    }
}
