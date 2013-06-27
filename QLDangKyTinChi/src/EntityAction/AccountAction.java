/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAction;

import Entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConSQL;

/**
 *
 * @author MrOnly
 */
public class AccountAction {

    public int insertNewAccount(Account account) {

        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement("insert into Account values(?,?,?)");
            ps.setString(1, account.getTen());
            ps.setString(2, account.getMK());
            ps.setString(3, account.getThongBao());
           return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int UpdateAccount(Account account, String id) {

        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement("Update Account set Name=?,Password=?,ThongBao=? where Name=?");
            ps.setString(1, account.getTen());
            ps.setString(2, account.getMK());
            ps.setString(3, account.getThongBao());
            ps.setString(4, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int DeleteAccount(String Name) {
            try {
                Statement stm = ConSQL.connection.createStatement();
                if (stm.executeUpdate("Delete from Account where Name='" + Name + "'") == 1) {
                    return 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        return 0;
    }

    public String checkLogin(String username, String password) {
        String kq = "NA";
        try {
            PreparedStatement stmt = ConSQL.connection.prepareStatement("select * from dbo.Login_check1(?,?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            while(result.next())
            {
                kq = result.getString("Ten");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
}
