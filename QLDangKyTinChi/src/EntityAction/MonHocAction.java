/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAction;

import Entity.MonHoc;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import util.ConSQL;

/**
 *
 * @author Administrator
 */
public class MonHocAction {

    public int Them(MonHoc mon) {
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement("Insert into MonHoc values(?,?,?,?,?,?,?)");
            ps.setString(1, mon.getMaMon());
            ps.setString(2, mon.getTenMon());
            ps.setString(3, mon.getLoaiMon());
            ps.setFloat(4, mon.getHeSo());
            ps.setString(5, mon.getKhoa());
            ps.setInt(6, mon.getSoTinChi());
            ps.setInt(7, mon.getKyDuKien());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MonHocAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int Sua(MonHoc mon, String Ma) {
        try {
            int e = 0;
            PreparedStatement ps = ConSQL.connection.prepareStatement("Update MonHoc set Mamon=?,Tenmon=?,Loaimon=?,HeSo=?,Khoa=?,SoTinChi=?,KyDukien=? where Mamon=?");
            ps.setString(1, mon.getMaMon());
            ps.setString(2, mon.getTenMon());
            ps.setString(3, mon.getLoaiMon());
            ps.setFloat(4, mon.getHeSo());
            ps.setString(5, mon.getKhoa());
            ps.setInt(6, mon.getSoTinChi());
            ps.setInt(7, mon.getKyDuKien());
            ps.setString(8, Ma);
            e = ps.executeUpdate();
            return e;

        } catch (SQLException ex) {
            Logger.getLogger(MonHocAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int Xoa(String ma) {

        try {
            int re = 0;
            PreparedStatement ps = ConSQL.connection.prepareStatement("Delete MonHoc where Mamon=?");
            ps.setString(1, ma);
            return re = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MonHocAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public Vector searchMonHoc(String Ma, String Ten) {
        String sql = "";
        String Search = "";
        if (null == Ma) {
            Search = Ten;
            sql = "select * from MonHoc where Tenmon like N'%" + Search + "%'";
        } else {
            Search = Ma;
            sql = "select * from MonHoc where Mamon like N'%" + Search + "%'";
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
                k.add(rs.getString(7));
                result.add(k);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
