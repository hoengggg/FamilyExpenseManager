package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MucTieu")
public class MucTieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "tienHienTai")
    private Double tienHienTai;

    @Column(name = "thoiHan")
    private LocalDate thoiHan;

    @Column(name = "tienMucTieu")
    private Double tienMucTieu;

    @ManyToOne
    @JoinColumn(name = "createdById", referencedColumnName = "id")
    private DangNhap createById;

    public String getThoiHanFormatted() {
        return thoiHan.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public double getTienDo(){  //sau khi làm 2 cái get này thì lấy phần tên đằng sau chữ get của nó, chữ cái đầu ko vt hoa cho vào ${MucTieu....}
        if(tienMucTieu == 0) return 0;
        return (tienHienTai / tienMucTieu) * 100;
    }

    public double getTienConThieu(){ //
        return tienMucTieu - tienHienTai;
    }

    public long getNgayConLai() {
        if (thoiHan == null) return 0;
        LocalDate today = LocalDate.now();
        if (thoiHan.isBefore(today)) return 0;
        return ChronoUnit.DAYS.between(today, thoiHan);
    }
}
