package Controller;

import Entity.DangNhap;
import Entity.GiaoDichVaTongQuan;
import Entity.MucTieu;
import Repository.MucTieuRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/muc_tieu")
public class MucTieuServlet extends HttpServlet {
    private final MucTieuRepository repo = new MucTieuRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }
        List<MucTieu> list = repo.getAll();
        req.setAttribute("mucTieus", list);  //trong jsp vt như nào thì servlet cx vậy và ngược lại
        req.getRequestDispatcher("/View/muc_tieu.jsp").forward(req, resp);
    }
}
