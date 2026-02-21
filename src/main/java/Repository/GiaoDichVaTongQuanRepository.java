package Repository;

import Entity.EmailData;
import Entity.GiaoDichVaTongQuan;
import helper.DbConnector;
import mail.GmailSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
// import java.util.Date; // ✅ Xóa vì không dùng
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GiaoDichVaTongQuanRepository {
    //sau này chắc thay cái repository này thành các code để kết nối vs sql còn lại mấy cái entity, controller và view thì vẫn vậy nhỉ

    private Connection con = null;//kết nối csdl
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public GiaoDichVaTongQuanRepository() {
        try {
            con = DbConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<GiaoDichVaTongQuan> giaoDichVaTongQuans = new ArrayList<>();

    public List<GiaoDichVaTongQuan> getAll(){
        List<GiaoDichVaTongQuan> list = new ArrayList<>();
        try {
            Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from GiaoDichVaTongQuan");
            while (rs.next()){
                Long id = rs.getLong("id");
                LocalDate ngayThang = rs.getDate("ngayThang").toLocalDate();
                String loai = rs.getString("loai");
                String danhMuc = rs.getString("danhMuc");
                String mota = rs.getString("moTa");
                Double sotien = rs.getDouble("soTien");
                Long createdbyid = rs.getLong("createdById");

                GiaoDichVaTongQuan giaodichvatongquan = new GiaoDichVaTongQuan();
                giaodichvatongquan.setId(id);
                giaodichvatongquan.setNgayThang(ngayThang);
                giaodichvatongquan.setLoai(loai);
                giaodichvatongquan.setDanhMuc(danhMuc);
                giaodichvatongquan.setMoTa(mota);
                giaodichvatongquan.setSoTien(sotien);
                giaodichvatongquan.setCreatedById(createdbyid);

                list.add(giaodichvatongquan);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        Connection connection = DbConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GiaoDichVaTongQuan");
        while (rs.next()) {
            System.out.println(rs.getString("moTa"));
        }
    }

    public GiaoDichVaTongQuan Detail(int id_Tim){
        sql = "select id, ngayThang, loai, danhMuc, moTa, soTien, createdById from GiaoDichVaTongQuan\n" + "where id = ?";
        GiaoDichVaTongQuan gdvtq = new GiaoDichVaTongQuan();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id_Tim);
            rs = ps.executeQuery();

            if(rs.next()){
                Long id;
                java.sql.Date sqlDate;
                LocalDate ngayThang;
                String loai;
                String danhMuc;
                String moTa;
                double soTien;
                Long createdById;

                id = rs.getLong(1);

                sqlDate = rs.getDate(2);
                ngayThang = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                loai = rs.getString(3);
                danhMuc = rs.getString(4);
                moTa = rs.getString(5);
                soTien = rs.getDouble(6);
                createdById = rs.getLong(7);

                gdvtq = new GiaoDichVaTongQuan(id, ngayThang, loai, danhMuc, moTa, soTien, createdById);
            }

            return gdvtq;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int xoa(int id){
        sql = "delete from GiaoDichVaTongQuan \n" + "where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int them(LocalDate ngayThang, String loai, String danhMuc,
                    String moTa, Double soTien, Long createdById) {

        sql = "insert into GiaoDichVaTongQuan(ngayThang, loai, danhMuc, moTa, soTien, createdById)\n"
                + "values(?,?,?,?,?,?)";

        try{
            ps = con.prepareStatement(sql);

            // chuyển LocalDate sang java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(ngayThang);

            ps.setObject(1, sqlDate);
            ps.setObject(2, loai);
            ps.setObject(3, danhMuc);
            ps.setObject(4, moTa);
            ps.setObject(5, soTien);
            ps.setObject(6, createdById);

            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(LocalDate ngayThang, String loai, String danhMuc,
                   String moTa, Double soTien, Long createdById, int id_update) {

        sql = "update GiaoDichVaTongQuan set ngayThang=?, loai=?, danhMuc=?, moTa=?, soTien=?, createdById=? " +
                "where id=?";

        try{
            ps = con.prepareStatement(sql);

            // chuyển LocalDate sang java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(ngayThang);

            ps.setObject(1, sqlDate);
            ps.setObject(2, loai);
            ps.setObject(3, danhMuc);
            ps.setObject(4, moTa);
            ps.setObject(5, soTien);
            ps.setObject(6, createdById);
            ps.setObject(7, id_update);

            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static List<GiaoDichVaTongQuan> getGiaoDich(){
        return giaoDichVaTongQuans; // giữ nguyên theo bản gốc
    }
}
