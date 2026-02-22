package Controller;

import Entity.DangNhap;
import Entity.GiaoDichVaTongQuan;
import Entity.ViCaNhan;
import Repository.GiaoDichVaTongQuanRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/giao_dich/filter")
public class GiaoDichFilterServlet extends HttpServlet {
    GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loai = req.getParameter("loai");

        if(loai == null || loai.trim().isEmpty()){
            loai = null;
        }

        List<GiaoDichVaTongQuan> list = repo.loctheoloai(loai);

        req.setAttribute("giaodichs", list);
        req.setAttribute("loai", loai);
        req.getRequestDispatcher("/View/giao_dich.jsp").forward(req, resp);
    }
}
