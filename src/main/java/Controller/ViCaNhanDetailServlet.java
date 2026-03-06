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

@WebServlet("/vi_ca_nhan/detail")
public class ViCaNhanDetailServlet extends HttpServlet {
    ViCaNhanRepository repo = new ViCaNhanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        req.setAttribute("VCN_Tim", repo.Detail(id, currentUser));
        req.getRequestDispatcher("/View/DetailViCaNhan.jsp").forward(req, resp);
    }

    @Override
    //đây là chức năng sửa
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
        Long id = Long.parseLong(req.getParameter("id"));

        if (!mota.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
            resp.getWriter().println("<script>alert('Mo ta khong hop le');history.back();</script>");
            return;
        }

        ViCaNhan existing = repo.Detail(id, currentUser);
        if (existing == null) {
            resp.getWriter().println("<script>alert('Không có quyền sửa'); window.history.back();</script>");
            return;
        }

        existing.setNgayThang(ngayThang);
        existing.setLoai(loai);
        existing.setDanhMuc(danhmuc);
        existing.setMoTa(mota);
        existing.setSoTien(sotien);

        repo.sua(existing);
        resp.sendRedirect(req.getContextPath() + "/vi_ca_nhan");
    }
}
