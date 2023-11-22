/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import TienIch.XDate;
import java.util.Date;

/**
 *
 * @author nguyenvanquyet
 */
public class NguoiHoc {

    String maNH;
    String hoTen;
    Date ngaySinh;
    Boolean gioiTinh;
    String dienThoai;
    String email;
    String ghiChu;
    String maNV;
    Date ngayDk;

    public NguoiHoc() {
    }

    public NguoiHoc(String maNH, String hoTen, Date ngaySinh, Boolean gioiTinh, String dienThoai, String email, String ghiChu, String maNV, Date ngayDk) {
        this.maNH = maNH;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.email = email;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.ngayDk = ngayDk;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean isGioiTinh() {
        return gioiTinh;
    }

    public void isGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayDk() {
        return ngayDk;
    }

    public void setNgayDk(Date ngayDk) {
        this.ngayDk = ngayDk;
    }
    @Override
    public String toString() {
        return this.hoTen;
    }

}
