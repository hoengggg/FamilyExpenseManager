package Controller;

import Entity.DangNhap;
import Entity.GiaoDichVaTongQuan;
import Entity.MucTieu;
import Repository.GiaoDichVaTongQuanRepository;
import Repository.MucTieuRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/muc_tieu/thuc_hien")
public class ThucHienMucTieuServlet extends HttpServlet {
    GiaoDichVaTongQuanRepository repogd = new GiaoDichVaTongQuanRepository();
    MucTieuRepository repo = new MucTieuRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/muc_tieu");
        //chuyển người dùng về trang mục tiêu và tránh lỗi f5 thực hiện lại hành động
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //lấy user đang đăng nhập
        DangNhap currentUser = (DangNhap) req.getSession().getAttribute("currentUser");

        // kiểm tra xem người dùng đã đăng nhập chưa, nếu chưa mà vào trang này sẽ bị chuyển về trang đăng nhập
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang_nhap");
            return;
        }


        Long id = Long.parseLong(req.getParameter("id")); //lấy trong cái mapping ở cái nút bên jsp

        //tìm mục tiêu trong db theo id
        MucTieu mt = repo.Detail(id.intValue());

        //nếu ko tìm thấy sẽ chuyển về trang mục tiêu nhờ resp
        //sẽ tạo ra 1 request vs mapping /muc_tieu và controller sẽ nhận lại và chạy ns thì khi chạy xong sẽ quay về trang mục tiêu
        if (mt == null) {
            resp.sendRedirect(req.getContextPath() + "/muc_tieu");
            return;
        }


        if (!mt.getCreateById().getId().equals(currentUser.getId()) //nếu đây ko phải là người tạo ra mục tiêu
                && !currentUser.getPhanQuyen().equals("Parents")) { //nếu phân quyền của người này ko phải parents

            resp.sendRedirect(req.getContextPath() + "/muc_tieu");   //thì sẽ chạy resp để quay lại trang mục tiêu
            return;
        }

        //nếu tiến độ mà bé hơn 100 thì sẽ quay lại trang mục tiêu nhờ resp
        if(mt.getTienDo() < 100){
            req.getSession().setAttribute("errorMessage", "Chua du 100% muc tieu");
            resp.sendRedirect(req.getContextPath() + "/muc_tieu");
            return;
        }

        //tạo một giao dịch mới
        GiaoDichVaTongQuan gdvtq = new GiaoDichVaTongQuan();

        //bắt đầu set dữ liệu cho giao dịch
        gdvtq.setNgayThang(LocalDate.now());  //đây là lấy ngày hiện tại
        gdvtq.setLoai("Chi ra");
        gdvtq.setDanhMuc("Thực hiện mục tiêu");
        gdvtq.setMoTa(mt.getTen());  //mô tả sẽ là tên mục tiêu
        gdvtq.setSoTien(mt.getTienMucTieu()); //số tiền sẽ là số tiền mục tiêu
        gdvtq.setCreateById(currentUser);  //gắn vào đó id của user đang đăng nhập

        //gọi repo để repo thao tác vs csdl và lưu và db
        repogd.them(gdvtq);

        // xoá mục tiêu sau khi thực hiện
        repo.xoa(id.intValue());

        //quay về trang giao dịch
        resp.sendRedirect(req.getContextPath() + "/giao_dich");
    }
}
