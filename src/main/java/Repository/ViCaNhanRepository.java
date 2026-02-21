package Repository;

import Entity.GiaoDichVaTongQuan;
import Entity.ViCaNhan;
import helper.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViCaNhanRepository {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ViCaNhanRepository() {
        try {
            con = DbConnector.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<ViCaNhan> viCaNhans = new ArrayList<>();

    public List<ViCaNhan> getAll(){
        List<ViCaNhan> list = new ArrayList<>();
        try {
            Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ViCaNhan");
            while (rs.next()){
                Long id = rs.getLong("id");
                LocalDate ngayThang = rs.getDate("ngayThang").toLocalDate();
                String loai = rs.getString("loai");
                String danhMuc= rs.getString("danhMuc");
                String moTa = rs.getString("moTa");
                Double soTien = rs.getDouble("soTien");
                Long createdById = rs.getLong("createdById");

                ViCaNhan viCaNhan = new ViCaNhan();
                viCaNhan.setId(id);
                viCaNhan.setNgayThang(ngayThang);
                viCaNhan.setLoai(loai);
                viCaNhan.setDanhMuc(danhMuc);
                viCaNhan.setMoTa(moTa);
                viCaNhan.setSoTien(soTien);
                viCaNhan.setCreatedById(createdById);

                list.add(viCaNhan);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        Connection connection = DbConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from ViCaNhan");
        while (rs.next()) {
            System.out.println(rs.getString("moTa"));
        }
    }

    public ViCaNhan Detail(int id_Tim){
        sql = "select id, ngayThang, loai, danhMuc, moTa, soTien, createdById from ViCaNhan\n" + "where id = ?";
        ViCaNhan vcn = new ViCaNhan();
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
                Double soTien;
                Long createdById;

                id = rs.getLong(1);
                sqlDate = rs.getDate(2);
                ngayThang = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                loai = rs.getString(3);
                danhMuc = rs.getString(4);
                moTa = rs.getString(5);
                soTien = rs.getDouble(6);
                createdById = rs.getLong(7);

                vcn = new ViCaNhan(id, ngayThang, loai, danhMuc, moTa, soTien, createdById);
            }
            return vcn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int xoa(int id){
        sql = "delete from ViCaNhan \n" + "where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int them(LocalDate ngayThang, String loai, String danhMuc, String moTa, Double soTien, Long createdById){
        sql = "insert into ViCaNhan(ngayThang, loai, danhMuc, moTa, soTien, createdById)\n" + "values(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);

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

    public int sua(LocalDate ngayThang, String loai, String danhMuc, String moTa, Double soTien, Long createdById, int id_update){
        sql = "update ViCaNhan set ngayThang=?, loai=?, danhMuc=?, moTa=?, soTien=?, createdById=? " +
                "where id=?";
        try {
            ps = con.prepareStatement(sql);

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

    public static List<ViCaNhan> getGiaoDich(){
        return viCaNhans; // giữ nguyên theo bản gốc
    }
}
