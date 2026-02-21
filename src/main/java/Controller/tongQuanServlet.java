package Controller;

import Entity.DangNhap;
import Entity.GiaoDichVaTongQuan;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/tong_quan")
public class tongQuanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }

        String lang = req.getParameter("lang"); // "vi" hoặc "en"
        if (lang != null) {
            Locale locale = new Locale(lang);
            req.getSession().setAttribute("locale", locale);
        }

        double tongThu = GiaoDichVaTongQuan.getTongThu();
        double tongChi = GiaoDichVaTongQuan.getTongChi();
        double tongTien = GiaoDichVaTongQuan.getTongTien();

        // đưa vào request để JSP dùng
        req.setAttribute("tongThu", tongThu);
        req.setAttribute("tongChi", tongChi);
        req.setAttribute("tongTien", tongTien);

        //hỗ trợ tiếng việt
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // forward sang JSP
        req.getRequestDispatcher("/View/tong_quan.jsp").forward(req, resp);
    }
}
