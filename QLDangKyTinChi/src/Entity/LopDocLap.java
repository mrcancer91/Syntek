/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MrOnly
 */
public class LopDocLap {

    private String MaLop, MaMon, MaGV, NgayHoc;
    private int TietS, TietE;
    private int SoSV;

    public LopDocLap() {
    }

    public LopDocLap(String MaLop, String MaMon, String MaGV, String NgayHoc, int TietS, int TietE, int SoSV) {
        this.MaLop = MaLop;
        this.MaMon = MaMon;
        this.MaGV = MaGV;
        this.NgayHoc = NgayHoc;
        this.TietS = TietS;
        this.TietE = TietE;
        this.SoSV = SoSV;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getNgayHoc() {
        return NgayHoc;
    }

    public void setNgayHoc(String NgayHoc) {
        this.NgayHoc = NgayHoc;
    }

    public int getSoSV() {
        return SoSV;
    }

    public void setSoSV(int SoSV) {
        this.SoSV = SoSV;
    }

    public int getTietE() {
        return TietE;
    }

    public void setTietE(int TietE) {
        this.TietE = TietE;
    }

    public int getTietS() {
        return TietS;
    }

    public void setTietS(int TietS) {
        this.TietS = TietS;
    }
}
