package Controller;

import Repository.GiaoDichVaTongQuanRepository;
import Entity.GiaoDichVaTongQuan;
import Entity.DangNhap;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/giao_dich/detail")
public class GiaoDichDetailServlet extends HttpServlet {
    private final GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));
        GiaoDichVaTongQuan gd = repo.Detail(id);
        req.setAttribute("giaoDichXem", gd);
        req.getRequestDispatcher("/View/DetailGiaoDich.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_update = Integer.parseInt(req.getParameter("id"));

        LocalDate ngayThang = null;
        try {
            ngayThang = LocalDate.parse(req.getParameter("ngayThang"));
        } catch (Exception e) {
            resp.getWriter().println("<script>alert('Ngay thang ko hop le vui long nhap lai'); window.history.back();</script>");
            return;
        }

        String loai = req.getParameter("loai");
        String danhMuc = req.getParameter("danhMuc");
        String moTa = req.getParameter("moTa");
        double soTien = Double.parseDouble(req.getParameter("soTien"));

        HttpSession session = req.getSession();
        DangNhap currentUser = (DangNhap) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        if (!moTa.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
            resp.getWriter().println("<script>alert('Mo ta khong hop le');history.back();</script>");
            return;
        }

        GiaoDichVaTongQuan gd = repo.Detail(id_update); //tại sao lại cần cả cái này

        if (gd != null && (
                "Parents".equals(currentUser.getPhanQuyen())
                        || gd.getCreateById().getId().equals(currentUser.getId())
        )) {

            gd.setNgayThang(ngayThang);
            gd.setLoai(loai);
            gd.setDanhMuc(danhMuc);
            gd.setMoTa(moTa);
            gd.setSoTien(soTien);

            repo.sua(gd);
            resp.sendRedirect(req.getContextPath() + "/giao_dich");
        } else {
            resp.getWriter().println("<script>alert('Ban ko co quyen sua'); window.history.back();</script>");
        }
    }
}
