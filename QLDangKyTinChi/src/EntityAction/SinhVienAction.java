/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAction;

import Entity.SinhVien;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.Vector;
import util.ConSQL;

/**
 *
 * @author Administrator
 */
public class SinhVienAction {

    public int AddNew(SinhVien sinhvien) {
        String sqlStr = "INSERT INTO SINHVIEN VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement(sqlStr);
            ps.setString(1, sinhvien.getMa());
            ps.setString(2, sinhvien.getHo());
            ps.setString(3, sinhvien.getTen());
            ps.setString(4, sinhvien.getNien_Khoa());
            ps.setString(5, sinhvien.getLop());
            ps.setString(6, sinhvien.getKhoa());
            if (ps.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienAction.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Lỗi! Thêm mới sinh viên thất bại");
        }
        return -1;
    }

    public int Edit(SinhVien sinhvien, String id) {

        String sqlStr = "Update SinhVien set MaSV=?,Ho=?,Ten=?,Nien_Khoa=?,Lop=?,Khoa=? where MaSV='" + id + "'";
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement(sqlStr);
            ps.setString(1, sinhvien.getMa());
            ps.setString(2, sinhvien.getHo());
            ps.setString(3, sinhvien.getTen());
            ps.setString(4, sinhvien.getNien_Khoa());
            ps.setString(5, sinhvien.getLop());
            ps.setString(6, sinhvien.getKhoa());
            if (ps.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienAction.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Lỗi! Sửa thông tin sinh viên thất bại");
        }
        return -1;
    }

    public int Delete(String id) {
        try {
            Statement stm = ConSQL.connection.createStatement();
            if (stm.executeUpdate("Delete from SinhVien where MaSV='" + id + "' ") == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Lỗi! Xóa thông tin sinh viên thất bại");
            Logger.getLogger(SinhVienAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int DangKyHocPhan(String MaSV, String MaLop) {
        int result = 0;
        try {
            Statement stm = ConSQL.connection.createStatement();
            ResultSet rs = stm.executeQuery("select count(*) from DangKy where MaSV='" + MaSV + "' and MaLop='" + MaLop + "'and TinhTrang=1");
            rs.next();
            if (rs.getString(1).equals("0")) {
                String sql = "select dbo.DK_check('" + MaSV + "','" + MaLop + "')";
                rs = stm.executeQuery(sql);
                rs.next();
                if (rs.getString(1).equals("0")) {
                    JOptionPane.showMessageDialog(null, "Môn này đã bị trùng tiết với môn trước đó! Yêu cầu chọn lại môn khác");
                } else {
                    Date today = new Date();
                    String ngay = "";
                    ngay += today.getYear() + 1900 + "-";
                    ngay += today.getMonth() + 1 + "-";
                    ngay += today.getDate();
                    PreparedStatement ps = ConSQL.connection.prepareStatement("INSERT INTO DANGKY VALUES (?,?,?,?)");
                    ps.setString(1, MaSV);
                    ps.setString(2, MaLop);
                    ps.setString(3, ngay);
                    ps.setBoolean(4, true);
                    result = ps.executeUpdate();
                   
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi! Đăng ký không thành công");
            Logger.getLogger(SinhVienAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Vector searchSV(String Ma, String Ten) {
        String sql = "";
        String Search = "";
        if (null == Ma) {
            Search = Ten;
            sql = "select * from SinhVien where Ten like N'%" + Search + "%'";
        } else {
            Search = Ma;
            sql = "select * from SinhVien where MaSV like N'%" + Search + "%'";
        }
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector result = new Vector();
            while (rs.next()) {
                Vector k = new Vector();
                k.add(false);
                k.add(rs.getString(1));
                k.add(rs.getString(2));
                k.add(rs.getString(3));
                k.add(rs.getString(4));
                k.add(rs.getString(5));
                k.add(rs.getString(6));
                result.add(k);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkTime() {
        return true;
    }
}
