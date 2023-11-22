package Entity;

public class NhanVien {

    private String MaNV;
    private String matKhau;
    private String hoTen;
    private Boolean vaiTro = false;

    public NhanVien() {
    }
    public NhanVien(String maNV, String matKhau, String hoTen) {
        this.MaNV = MaNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
    }
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(Boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
       @Override
    public String toString() {
        return this.hoTen;
    }

}
