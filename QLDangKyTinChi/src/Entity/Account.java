/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MrOnly
 */
public class Account {

    private String Ten, MK, ThongBao;

    public Account() {
    }

    public Account(String Ten, String MK, String ThongBao) {
        this.Ten = Ten;
        this.MK = MK;
        this.ThongBao = ThongBao;
    }

    public String getMK() {
        return MK;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getThongBao() {
        return ThongBao;
    }

    public void setThongBao(String ThongBao) {
        this.ThongBao = ThongBao;
    }
}
