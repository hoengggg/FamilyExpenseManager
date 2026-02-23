package Entity;
//value="${mucTieuXem.tienMucTieu * 1}"   riêng cái tiền kiểu như này vì để double nên phải làm cái value như này cho ô input bên jsp để khi ấn xem ko bị lỗi định dạng
import Repository.GiaoDichVaTongQuanRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GiaoDichVaTongQuan")
public class GiaoDichVaTongQuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngayThang")
    private LocalDate ngayThang;

    @Column(name = "loai")
    private String loai;

    @Column(name = "danhMuc")
    private String danhMuc;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "soTien")
    private Double soTien;

    @ManyToOne
    @JoinColumn(name = "createdById", referencedColumnName = "id")
    private DangNhap createById;

    public String getNgayThangFormatted() {
        return ngayThang.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
