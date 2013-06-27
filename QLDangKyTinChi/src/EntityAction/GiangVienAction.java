/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAction;

import Entity.GiangVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConSQL;

/**
 *
 * @author Administrator
 */
public class GiangVienAction {
    
    public int Them(GiangVien gv)
    {
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement("Insert into GiangVien values (?,?,?,?,?,?)");
            ps.setString(1, gv.getMaGV());
            ps.setString(2, gv.getTenGV());
            ps.setString(3, gv.getKhoa());
            ps.setString(4, gv.getDiaChi());
            ps.setString(5, gv.getSDT());
            ps.setString(6, gv.getEmail());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public int Sua(GiangVien gv,String id)
    {
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement("Update GiangVien set MaGV=?,TenGV=?,Khoa=?,DiaChi=?,SDT=?,Email=? where MaGV='"+id+"'");
            ps.setString(1, gv.getMaGV());
            ps.setString(2, gv.getTenGV());
            ps.setString(3, gv.getKhoa());
            ps.setString(4, gv.getDiaChi());
            ps.setString(5, gv.getSDT());
            ps.setString(6, gv.getEmail());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public int Xoa(String id)
    {
        try {
            Statement stm = ConSQL.connection.createStatement();
            if(stm.executeUpdate("Delete from GiangVien where MaGV='"+id+"' ")==1)
                return 1;
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }
    public Vector searchGiangVien(String Ma, String Ten) {
        String sql = "";
        String Search = "";
        if (null == Ma) {
            Search = Ten;
            sql = "select * from GiangVien where TenGV like N'%" + Search + "%'";
        } else {
            Search = Ma;
            sql = "select * from GiangVien where MaGV like N'%" + Search + "%'";
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
}
