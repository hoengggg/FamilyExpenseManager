package Controller;

import Entity.DangNhap;
import Repository.DangNhapRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dang_nhap")
public class DangNhapServlet extends HttpServlet {

    private DangNhapRepository repository = new DangNhapRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/View/dang_nhap.jsp").forward(req, resp);  //đại khái là nếu muốn hiển thị jsp thì phải có dòng này, nếu ko có dòng này thì khi gọi tên webServlet thì chỉ hiển thị 1 trang trắng vì nó chỉ gọi lên servlet này thôi, muốn hiển thị jsp mik làm khi gọi tên webServlet này thì phải có dòng này
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenDangNhap = req.getParameter("username");  //cái trong nội dung trong dấu "" của dòng này thì nội dung của nó là lấy từ ô name trong input chứ ko phải entity, hiểu đơn giản cái req.getParameter này thì nội dung trong "" ko phải là gọi từ entity ra mà là gọi từ nội dung trong thẻ name trong ô input bên jsp
        // <input type="text" name="  username"     required>
        String matKhau = req.getParameter("password");

        DangNhap user = repository.login(tenDangNhap, matKhau);

        if(user != null){
            req.getSession().setAttribute("currentUser", user);

            if("Parents".equals(user.getPhanQuyen())){
                resp.sendRedirect(req.getContextPath() + "/tong_quan");  //đoạn này là điền tên webServlet
            }else{
                resp.sendRedirect(req.getContextPath() + "/tong_quan");
            }
        }else{
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/View/dang_nhap.jsp").forward(req, resp);
        }
    }
}
