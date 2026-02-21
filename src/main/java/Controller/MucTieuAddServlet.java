package Controller;

import Entity.DangNhap;
import Repository.MucTieuRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/muc_tieu/add")
public class MucTieuAddServlet extends HttpServlet {
    private MucTieuRepository repo = new MucTieuRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        req.getRequestDispatcher("/View/AddMucTieu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ten = req.getParameter("ten");
        double tienHienTai = Double.parseDouble(req.getParameter("tienHienTai"));
        double tienMucTieu = Double.parseDouble(req.getParameter("tienMucTieu"));

        LocalDate thoiHan = null;
        try {
            thoiHan = LocalDate.parse(req.getParameter("thoiHan"));
        } catch (Exception e) {
            resp.getWriter().println("<script>alert('Ngay thang ko hop le'); window.history.back();</script>");
            return;
        }

        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        // ✅ Sửa: gán đúng ID user đăng nhập
        Long createdById = currentUser.getId();

        if (thoiHan.isBefore(LocalDate.now())) {
            resp.getWriter().println("<script>alert('Ko duoc dat thoi han la thoi gian trong qua khu'); window.history.back();</script>");
            return;
        }

        repo.them(ten, tienHienTai, thoiHan, tienMucTieu, createdById);

        req.setAttribute("message", "Them thanh cong");
        resp.sendRedirect(req.getContextPath() + "/muc_tieu");
    }
}
