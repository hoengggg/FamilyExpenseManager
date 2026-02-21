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
import java.util.List;

@WebServlet("/ThongKe")
public class ThongKeServlet extends HttpServlet {
    private final GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        List<GiaoDichVaTongQuan> ds = repo.getAll();

        double[] tongThu = new double[12];
        double[] tongChi = new double[12];

        for (int i = 1; i <= 12; i++){
            int month = i;
            tongThu[i - 1] = ds.stream()
                    .filter(gd -> "Thu vào".equalsIgnoreCase(gd.getLoai()))
                    .filter(gd -> gd.getNgayThang().getMonthValue() == month)
                    .mapToDouble(GiaoDichVaTongQuan::getSoTien)
                    .sum();

            tongChi[i - 1] = ds.stream()
                    .filter(gd -> "Chi ra".equalsIgnoreCase(gd.getLoai()))
                    .filter(gd -> gd.getNgayThang().getMonthValue() == month)
                    .mapToDouble(GiaoDichVaTongQuan::getSoTien)
                    .sum();
        }
        req.setAttribute("tongThu", tongThu);
        req.setAttribute("tongChi", tongChi);

        req.getRequestDispatcher("/View/ThongKe.jsp").forward(req, resp);
    }
}
