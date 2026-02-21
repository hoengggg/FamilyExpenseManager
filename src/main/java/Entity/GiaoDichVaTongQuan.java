package Entity;

import Repository.GiaoDichVaTongQuanRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GiaoDichVaTongQuan {
    private Long id;
    private LocalDate ngayThang;
    private String loai;
    private String danhMuc;
    private String moTa;
    private double soTien;  //value="${mucTieuXem.tienMucTieu * 1}"   riêng cái tiền kiểu như này vì để double nên phải làm cái value như này cho ô input bên jsp để khi ấn xem ko bị lỗi định dạng
    private Long createdById;

    public GiaoDichVaTongQuan() {
    }

    public GiaoDichVaTongQuan(Long id, LocalDate ngayThang, String loai, String danhMuc, String moTa, double soTien, Long createdById) {
        this.id = id;
        this.ngayThang = ngayThang;
        this.loai = loai;
        this.danhMuc = danhMuc;
        this.moTa = moTa;
        this.soTien = soTien;
        this.createdById = createdById;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(LocalDate ngayThang) {
        this.ngayThang = ngayThang;
    }



    public String getNgayThangFormatted() {
        return ngayThang.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }



    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }


    public static double getTongThu() {
        GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();
        return repo.getAll().stream()
                .filter(gd -> "Thu vào".equalsIgnoreCase(gd.getLoai()))
                .mapToDouble(GiaoDichVaTongQuan::getSoTien)
                .sum();
    }

    public static double getTongChi() {
        GiaoDichVaTongQuanRepository repo = new GiaoDichVaTongQuanRepository();
        return repo.getAll().stream()
                .filter(gd -> "Chi ra".equalsIgnoreCase(gd.getLoai()))
                .mapToDouble(GiaoDichVaTongQuan::getSoTien)
                .sum();
    }

    public static double getTongTien() {
        return getTongThu() - getTongChi();
    }


}
