package Repository;

import Entity.DangNhap;
import Entity.MucTieu;
import helper.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DangNhapRepository {
    public DangNhap login(String tenDangNhap, String matKhau) {
        String sql = "SELECT id, phanQuyen, tenDangNhap, matKhau FROM DangNhap WHERE tenDangNhap=? AND matKhau=?";
        try (Connection con = DbConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DangNhap(
                        rs.getLong("id"),
                        rs.getString("phanQuyen"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // đăng nhập thất bại
    }

    //public boolean register(String phanQuyen, String tenDangNhap, String matKhau) {
    //        String sql = "INSERT INTO DangNhap (phanQuyen, tenDangNhap, matKhau) VALUES (?, ?, ?)";
    //        try (Connection con = DbConnector.getConnection();
    //             PreparedStatement ps = con.prepareStatement(sql)) {
    //            ps.setString(1, phanQuyen);
    //            ps.setString(2, tenDangNhap);
    //            ps.setString(3, matKhau);
    //            int rows = ps.executeUpdate();
    //            return rows > 0;
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return false;
    //    }
}
