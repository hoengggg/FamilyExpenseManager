package Controller;

import Entity.DangNhap;
import Entity.GiaoDichVaTongQuan;
import Repository.GiaoDichVaTongQuanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@WebServlet("/tong_quan")
public class TongQuanServlet extends HttpServlet {
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

        GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

        String yearParam = req.getParameter("year");
        int currentYear = java.time.Year.now().getValue();
        int year;

        // Mặc định chọn năm hiện tại
        if (yearParam == null || yearParam.isEmpty()) {
            year = currentYear;
        } else {
            year = Integer.parseInt(yearParam);
        }

        // Lấy danh sách năm trong DB
        List<Integer> years = repo.getAllYear();

        // Nếu năm hiện tại chưa có trong DB thì thêm vào
        if (!years.contains(currentYear)) {
            years.add(currentYear);
        }

        // Sắp xếp tăng dần
        Collections.sort(years);

        // Gửi qua JSP
        req.setAttribute("years", years);
        req.setAttribute("selectedYear", year);


        double tongThu = repo.tongThuTheoNam(year);  //sau đó truyền cái year đc khai báo ở trên và trong if/else kia xuống để gọi repo nhằm hiển thị tổng thu/chi và số tiến
        double tongChi = repo.tongChiTheoNam(year);
        double tongTien = tongThu - tongChi;

        req.setAttribute("selectedYear", year);
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
