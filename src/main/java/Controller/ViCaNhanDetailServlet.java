package Controller;

import Entity.DangNhap;
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
        Integer id = Integer.parseInt(req.getParameter("id"));
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        Long userId = currentUser.getId();
        req.setAttribute("VCN_Tim", repo.Detail(id, userId));
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
        Integer id = Integer.parseInt(req.getParameter("id"));
        repo.sua(ngayThang, loai, danhmuc, mota, sotien, createdById, id);
        resp.sendRedirect(req.getContextPath() + "/vi_ca_nhan");
    }
}
