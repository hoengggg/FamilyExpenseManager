package Controller;

import Repository.GiaoDichVaTongQuanRepository;
import Entity.GiaoDichVaTongQuan;
import Entity.DangNhap;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/giao_dich/delete")
public class GiaoDichDeleteServlet extends HttpServlet {
    private GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        GiaoDichVaTongQuan gd = repo.Detail(id);

        if (gd != null && (
                "Parents".equals(currentUser.getPhanQuyen())
                        || gd.getCreateById().getId().equals(currentUser.getId())
        )) {
            repo.xoa(id);
            resp.sendRedirect(req.getContextPath() + "/giao_dich");
        } else {
            // ✅ Giữ nguyên alert của bạn
            resp.getWriter().println("<script>alert('Ban khong co quyen xoa'); window.history.back();</script>");
        }
    }
}
