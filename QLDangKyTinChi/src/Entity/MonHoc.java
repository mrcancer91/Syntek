/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MrOnly
 */
public class MonHoc {

    private String MaMon, TenMon, LoaiMon, Khoa;
    private float HeSo;
    private int SoTinChi, KyDuKien;

    public MonHoc() {
    }

    public MonHoc(String MaMon, String TenMon, String LoaiMon, String Khoa, float HeSo, int SoTinChi, int KyDuKien) {
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.LoaiMon = LoaiMon;
        this.Khoa = Khoa;
        this.HeSo = HeSo;
        this.SoTinChi = SoTinChi;
        this.KyDuKien = KyDuKien;
    }

    public float getHeSo() {
        return HeSo;
    }

    public void setHeSo(float HeSo) {
        this.HeSo = HeSo;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public int getKyDuKien() {
        return KyDuKien;
    }

    public void setKyDuKien(int KyDuKien) {
        this.KyDuKien = KyDuKien;
    }

    public String getLoaiMon() {
        return LoaiMon;
    }

    public void setLoaiMon(String LoaiMon) {
        this.LoaiMon = LoaiMon;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int SoTinChi) {
        this.SoTinChi = SoTinChi;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }
}
