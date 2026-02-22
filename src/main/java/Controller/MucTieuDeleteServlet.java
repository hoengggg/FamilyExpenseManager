package Controller;

import Entity.DangNhap;
import Entity.MucTieu;
import Repository.MucTieuRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/muc_tieu/delete")
public class MucTieuDeleteServlet extends HttpServlet {
    private MucTieuRepository repo = new MucTieuRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        MucTieu mt = repo.Detail(id);

        // ✅ Bổ sung kiểm tra quyền
        if (mt != null && (
                "Parents".equals(currentUser.getPhanQuyen())
                        || mt.getCreateById().getId().equals(currentUser.getId())
        )) {
            repo.xoa(id);
            req.setAttribute("message", "Xoa thanh cong");
            resp.sendRedirect(req.getContextPath() + "/muc_tieu");
        } else {
            resp.getWriter().println("<script>alert('Ban khong co quyen xoa muc tieu nay'); window.history.back();</script>");
        }
    }
}
