package Entity;

public class DangNhap {
    private Long id;
    private String phanQuyen;
    private String tenDangNhap;
    private String matKhau;

    public DangNhap() {
    }

    public DangNhap(Long id, String phanQuyen, String tenDangNhap, String matKhau) {
        this.id = id;
        this.phanQuyen = phanQuyen;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
