package Repository;

import Entity.GiaoDichVaTongQuan;
import Entity.MucTieu;
import helper.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MucTieuRepository {
    private Connection con = null;//kết nối csdl
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public MucTieuRepository() {
        try {
            con = DbConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<MucTieu> mucTieus = new ArrayList<>();

    public List<MucTieu> getAll() {
        List<MucTieu> list = new ArrayList<>();
        try {
            Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from MucTieu");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String ten = rs.getString("ten");
                Double tienhientai = rs.getDouble("tienHienTai");
                java.sql.Date sqlDate = rs.getDate("thoiHan");
                LocalDate thoihan = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                Double tienmuctieu = rs.getDouble("tienMucTieu");
                Long createdbyid = rs.getLong("createdById");

                MucTieu muctieu = new MucTieu();
                muctieu.setId(id);
                muctieu.setTen(ten);
                muctieu.setTienHienTai(tienhientai);
                muctieu.setThoiHan(thoihan);
                muctieu.setTienMucTieu(tienmuctieu);
                muctieu.setCreatedById(createdbyid);

                list.add(muctieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        Connection connection = DbConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from MucTieu");
        while (rs.next()) {
            System.out.println(rs.getString("ten"));
        }
    }

    public MucTieu Detail(int id_Tim){
        sql = "select id, ten, tienHienTai, thoiHan, tienMucTieu, createdById from MucTieu\n" + "where id = ?";
        MucTieu mt = new MucTieu();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id_Tim);
            rs = ps.executeQuery();

            if(rs.next()){
                Long id, createdbyid;
                String ten;
                double tienhientai;
                java.sql.Date sqlDate;
                LocalDate thoihan;
                double tienmuctieu;

                id = rs.getLong(1);
                ten = rs.getString(2);
                tienhientai = rs.getDouble(3);
                sqlDate = rs.getDate(4);
                thoihan = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                tienmuctieu = rs.getDouble(5);
                createdbyid = rs.getLong(6);

                mt = new MucTieu(id, ten, tienhientai, thoihan, tienmuctieu, createdbyid);
            }
            return mt;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int xoa(int id){
        sql = "delete from MucTieu \n" + "where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int them(String ten, Double tienhientai, LocalDate thoiHan, Double tienmuctieu, Long createdbyid){
        sql = "insert into MucTieu(ten, tienHienTai, thoiHan, tienMucTieu, createdById)\n"
                + "values(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);

            java.sql.Date sqlDate = java.sql.Date.valueOf(thoiHan);
            ps.setString(1, ten);
            ps.setDouble(2, tienhientai);
            ps.setDate(3, sqlDate);
            ps.setDouble(4, tienmuctieu);
            ps.setLong(5, createdbyid);

            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(String ten, Double tienhientai, LocalDate thoiHan, Double tienmuctieu, Long createdbyid, int id_update){
        sql = "update MucTieu set ten=?, tienHienTai=?, thoiHan=?, tienMucTieu=?, createdById=? " +
                "where id=?";
        try {
            ps = con.prepareStatement(sql);

            java.sql.Date sqlDate = java.sql.Date.valueOf(thoiHan);
            ps.setString(1, ten);
            ps.setDouble(2, tienhientai);
            ps.setDate(3, sqlDate);
            ps.setDouble(4, tienmuctieu);
            ps.setLong(5, createdbyid);
            ps.setObject(6, id_update);

            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
