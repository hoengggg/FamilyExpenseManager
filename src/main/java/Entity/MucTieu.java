package Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class MucTieu {
    private Long id;
    private String ten;
    private double tienHienTai;
    private LocalDate thoiHan;
    private double tienMucTieu;
    private Long createdById;

    public MucTieu() {
    }

    public MucTieu(Long id, String ten, double tienHienTai, LocalDate thoiHan, double tienMucTieu, Long createdById) {
        this.id = id;
        this.ten = ten;
        this.tienHienTai = tienHienTai;
        this.thoiHan = thoiHan;
        this.tienMucTieu = tienMucTieu;
        this.createdById = createdById;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getTienHienTai() {
        return tienHienTai;
    }

    public void setTienHienTai(double tienHienTai) {
        this.tienHienTai = tienHienTai;
    }

    public LocalDate getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(LocalDate thoiHan) {
        this.thoiHan = thoiHan;
    }


    public String getThoiHanFormatted() {
        return thoiHan.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    public double getTienMucTieu() {
        return tienMucTieu;
    }

    public void setTienMucTieu(double tienMucTieu) {
        this.tienMucTieu = tienMucTieu;
    }

    public double getTienDo(){  //sau khi làm 2 cái get này thì lấy phần tên đằng sau chữ get của nó, chữ cái đầu ko vt hoa cho vào ${MucTieu....}
        if(tienMucTieu == 0) return 0;
        return (tienHienTai / tienMucTieu) * 100;
    }

    public double getTienConThieu(){ //
        return tienMucTieu - tienHienTai;
    }

    public Long getCreatedById() {
        return createdById;
    }
    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public long getNgayConLai() {
        if (thoiHan == null) return 0;
        LocalDate today = LocalDate.now();
        if (thoiHan.isBefore(today)) return 0;
        return ChronoUnit.DAYS.between(today, thoiHan);
    }

}
