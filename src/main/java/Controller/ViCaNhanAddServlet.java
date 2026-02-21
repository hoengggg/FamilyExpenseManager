package Controller;

import Entity.DangNhap;
import Entity.ViCaNhan;
import Repository.ViCaNhanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/vi_ca_nhan/add")
public class ViCaNhanAddServlet extends HttpServlet {
    ViCaNhanRepository repo = new ViCaNhanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        req.getRequestDispatcher("/View/AddViCaNhan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate ngayThang = LocalDate.parse(req.getParameter("ngayThang"));
        String loai = req.getParameter("loai");
        String danhmuc = req.getParameter("danhMuc");
        String mota = req.getParameter("moTa");
        Double sotien = Double.parseDouble(req.getParameter("soTien"));
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        Long createdById = currentUser.getId();
        repo.them(ngayThang, loai, danhmuc, mota, sotien, createdById);
        resp.sendRedirect(req.getContextPath() + "/vi_ca_nhan");
    }
}
