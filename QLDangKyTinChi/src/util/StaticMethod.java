/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MrOnly
 */
public class StaticMethod {
    public static String nameOfLabel="";
    
    
    public static DefaultTableModel getDataFromTable(String tableName, Vector tableTitle) {
        tableTitle.add(0, "");
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int x, int y) {
                if (y > 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Class getColumnClass(int col) {
                switch (col) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        model.setColumnIdentifiers(tableTitle);
        try {
            Statement stm = ConSQL.connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmt = rs.getMetaData();
            while (rs.next()) {

                Vector k = new Vector();
                k.add(false);
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    k.add(rs.getString(i));
                }
                model.addRow(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaticMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    public static void Label_HieuUngVao(JLabel btn) {
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setFont(new java.awt.Font("Tahoma", 0, 15));
        //btn.setForeground(new java.awt.Color(255, 0, 51));
        nameOfLabel = btn.getText();
        btn.setText("<html><u>" + nameOfLabel + "</u></html>");
    }

    public static void Label_HieuUngRa(JLabel btn) {
        btn.setFont(new java.awt.Font("Tahoma", 0, 14));
        btn.setText(nameOfLabel);
        //btn.setForeground(new java.awt.Color(0,0,255));
    }
    
}
