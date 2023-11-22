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
public class KhoaHoc {
    int maKH;
    String maCD;
    double hocPhi;
    int thoiLuong;
    Date ngayKG;
    String ghiChu;
    String maNV;
    private Date ngayTao = new Date();

    public KhoaHoc() {
    }

    public KhoaHoc(int maKH, String maCD, double hocPhi, int thoiLuong, Date ngayKG, String ghiChu, String maNV, Date ngayTao) {
        this.maKH = maKH;
        this.maCD = maCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayKG = ngayKG;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Date getNgayKG() {
        return ngayKG;
    }

    public void setNgayKG(Date ngayKG) {
        this.ngayKG = ngayKG;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return this.maCD + " (" + this.ngayKG + ")";
    }

}
