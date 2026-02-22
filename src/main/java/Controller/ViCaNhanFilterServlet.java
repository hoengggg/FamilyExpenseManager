package Controller;

import Entity.DangNhap;
import Entity.GiaoDichVaTongQuan;
import Entity.ViCaNhan;
import Repository.ViCaNhanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/vi_ca_nhan/filter")
public class ViCaNhanFilterServlet extends HttpServlet {
    ViCaNhanRepository repo = new ViCaNhanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DangNhap user = (DangNhap) req.getSession().getAttribute("currentUser");
        Long userId = user.getId();

        String loai = req.getParameter("loai");

        if(loai == null || loai.trim().isEmpty()){
            loai = null;
        }

        List<ViCaNhan> list = repo.loctheoloai(loai, userId);

        req.setAttribute("listViCaNhan", list);
        req.setAttribute("loai", loai);
        req.getRequestDispatcher("/View/vi_ca_nhan.jsp").forward(req, resp);
    }
}
