package Controller;

import Entity.MucTieu;
import Entity.DangNhap;
import Repository.MucTieuRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/muc_tieu/update")
public class MucTieuDetailServlet extends HttpServlet {
    private final MucTieuRepository repo = new MucTieuRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));
        MucTieu mt = repo.Detail(id);
        req.setAttribute("mucTieuXem", mt);
        req.getRequestDispatcher("/View/DetailMucTieu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_update = Integer.parseInt(req.getParameter("id"));
        String ten = req.getParameter("ten");
        double tienHienTai = Double.parseDouble(req.getParameter("tienHienTai"));

        String thoiHanStr = req.getParameter("thoiHan");
        LocalDate thoiHan = null;
        if (thoiHanStr != null && !thoiHanStr.isEmpty()) {
            try {
                thoiHan = LocalDate.parse(thoiHanStr);
            } catch (Exception e) {
                resp.getWriter().println("<script>alert('Ngày tháng không hợp lệ, vui lòng nhập lại'); window.history.back();</script>");
                return;
            }
            if (thoiHan.isBefore(LocalDate.now())) {
                resp.getWriter().println("<script>alert('Không được đặt thời hạn là thời gian trong quá khứ'); window.history.back();</script>");
                return;
            }
        }

        double tienMucTieu = Double.parseDouble(req.getParameter("tienMucTieu"));

        HttpSession session = req.getSession();
        DangNhap currentUser = (DangNhap) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        MucTieu mt = repo.Detail(id_update);

        if (mt != null && (
                "Parents".equals(currentUser.getPhanQuyen())
                        || mt.getCreateById().getId().equals(currentUser.getId())
        )) {
            mt.setTen(ten);
            mt.setTienHienTai(tienHienTai);
            mt.setThoiHan(thoiHan);
            mt.setTienMucTieu(tienMucTieu);

            repo.sua(mt);
            resp.sendRedirect(req.getContextPath() + "/muc_tieu");
        } else {
            resp.getWriter().println("<script>alert('Ban khong co quyen sua muc tieu nay'); window.history.back();</script>");
        }
    }
}
