/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAction;

import Entity.LopDocLap;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConSQL;

/**
 *
 * @author Administrator
 */
public class LopDocLapAction {


    public void Them(LopDocLap lop, String Tenmon) {
        if (lop.getTietS() >= lop.getTietE()) {
            JOptionPane.showMessageDialog(null, "Tiết bắt đầu không được lớn hơn hoặc bằng tiết kết thúc");
        } else {
            String s = lop.getMaGV() + lop.getMaMon();
            try {
                PreparedStatement ps = ConSQL.connection.prepareStatement("Insert into Lopdoclap values (?,?,?,?,?,?,?)");
                ps.setString(1, s);
                ps.setString(2, lop.getMaMon());
                ps.setString(3, lop.getMaGV());
                ps.setInt(4, 0);
                ps.setString(5, lop.getNgayHoc());
                ps.setInt(6, lop.getTietS());
                ps.setInt(7, lop.getTietE());
                if (ps.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm lớp độc lập thành công. \n"
                            + "Bạn đã đăng ký thành công môn " + Tenmon + " "
                            + "vào thứ " + lop.getNgayHoc() + "\n\t Tiết bắt đầu: " + lop.getTietS()
                            + " tiết kết thúc: " + lop.getTietE());
                }
            } catch (SQLException ex) {
                {
                    JOptionPane.showMessageDialog(null, "Đăng ký môn thất bại! Thử lại");
                }
                Logger.getLogger(LopDocLapAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Xoa(String id) {
        if (JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn hủy đăng ký lớp độc lập có mã "+id+" không?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
            try {

                String strSql = "Delete from Lopdoclap where Malop='" + id + "'";
                Statement ps = ConSQL.connection.createStatement();
                if (ps.executeUpdate(strSql) == 1) {
                    JOptionPane.showMessageDialog(null, "Xóa lớp độc lập thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi! Xóa lớp độc lập không thành công!");
                Logger.getLogger(LopDocLapAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
