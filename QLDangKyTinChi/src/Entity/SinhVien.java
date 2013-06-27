/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MrOnly
 */
public class SinhVien {

    private String Ma, Ho, Ten, Nien_Khoa, Lop, Khoa;

    public SinhVien() {
    }

    public SinhVien(String Ma, String Ho, String Ten, String Nien_Khoa, String Lop, String Khoa) {
        this.Ma = Ma;
        this.Ho = Ho;
        this.Ten = Ten;
        this.Nien_Khoa = Nien_Khoa;
        this.Lop = Lop;
        this.Khoa = Khoa;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String Lop) {
        this.Lop = Lop;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getNien_Khoa() {
        return Nien_Khoa;
    }

    public void setNien_Khoa(String Nien_Khoa) {
        this.Nien_Khoa = Nien_Khoa;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }
}
