/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MrOnly
 */
public class GiangVien {
    private String MaGV,TenGV,Khoa,DiaChi,SDT,Email;

    public GiangVien() {
    }

    public GiangVien(String MaGV, String TenGV, String Khoa, String DiaChi, String SDT, String Email) {
        this.MaGV = MaGV;
        this.TenGV = TenGV;
        this.Khoa = Khoa;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String TenGV) {
        this.TenGV = TenGV;
    }
}

