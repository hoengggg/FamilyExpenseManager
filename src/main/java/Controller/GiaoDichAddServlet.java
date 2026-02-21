package Controller;

import Entity.DangNhap;
import Entity.EmailData;
import Repository.GiaoDichVaTongQuanRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mail.GmailSender;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

@WebServlet("/giao_dich/add")
public class GiaoDichAddServlet extends HttpServlet {
    private GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        req.getRequestDispatcher("/View/AddGiaoDich.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate ngayThang = null;
        try {
            ngayThang = LocalDate.parse(req.getParameter("ngayThang"));
        } catch (Exception e) {
            resp.getWriter().println("<script>alert('Ngay thang ko hop le'); window.history.back();</script>");
            return;
        }

        String loai = req.getParameter("loai");
        String danhMuc = req.getParameter("danhMuc");
        String moTa = req.getParameter("moTa");
        Double soTien = Double.parseDouble(req.getParameter("soTien"));

        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        Long createdById = currentUser.getId();
        repo.them(ngayThang, loai, danhMuc, moTa, soTien, createdById);

        try {
            GmailSender sender = GmailSender.getInstance();
            String noiDungMail =
                    "<h3>Thông báo giao dịch mới</h3>" +
                            "<table border='1' cellpadding='5' cellspacing='0'>" +
                            "<tr><th>Loại</th><th>Danh mục</th><th>Mô tả</th><th>Số tiền</th><th>Ngày</th></tr>" +
                            "<tr>" +
                            "<td>" + loai + "</td>" +
                            "<td>" + danhMuc + "</td>" +
                            "<td>" + moTa + "</td>" +
                            "<td>" + soTien + "</td>" +
                            "<td>" + ngayThang + "</td>" +
                            "</tr>" +
                            "</table>";

            EmailData email = new EmailData(
                    Arrays.asList("hoanglhth05020@gmail.com"),
                    "Thông báo giao dịch mới",
                    noiDungMail
            );
            email.setHtml(true);
            sender.send(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/giao_dich");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String username = "hoanglhth05020@gmail.com";
        String appPassword = "kzlcbgrympjjvzog"; // App Password thật
        GmailSender.init(username, appPassword, true);
    }
}
