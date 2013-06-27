/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import EntityAction.SinhVienAction;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import util.ConSQL;

/**
 *
 * @author MrOnly
 */
public class FormSinhVien extends javax.swing.JFrame {

    Vector tableRecord = new Vector();
    Vector tableTitle = new Vector();
    SinhVienAction SVAc = new SinhVienAction();
    public String MaSV = null, MaLop = null;

    /**
     * Creates new form FormSinhVien
     */
    public FormSinhVien() {
        ConSQL.getConnectToMSSQL();
        initComponents();
        panelChiTiet.add(panelXemThoiKhoaBieu, "XemThoiKhoaBieu");
        panelChiTiet.add(panelDKHocPhan, "DangKyHocPhan");
        panelChiTiet.add(panelHuyHP, "HuyHocPhan");
        panelChiTiet.add(panelThongBao, "ThongBao");
        lbXemTKBMouseClicked(new MouseEvent(this, 1, 1, 1, 1, 1, 1, true));


    }

    public FormSinhVien(LoginDialog dialog, String name) {
        this.MaSV = name;
        initComponents();

        panelChiTiet.add(panelXemThoiKhoaBieu, "XemThoiKhoaBieu");
        panelChiTiet.add(panelDKHocPhan, "DangKyHocPhan");
        panelChiTiet.add(panelHuyHP, "HuyHocPhan");
      
        int rowHeight=40;
        tableHuyHocPhan.setRowHeight(rowHeight);
        tableLietKeMonCoTheDK.setRowHeight(rowHeight);
        tableThoiKhoaBieu.setRowHeight(rowHeight);
       
        try {
            ResultSet rs = ConSQL.connection.createStatement().executeQuery("select ThongBao from Account where Name='" + MaSV + "'");
            while (rs.next()) {
                txtAreaThongBao.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTacVu = new javax.swing.JPanel();
        lbDKHocPhan = new javax.swing.JLabel();
        lbHuyDKHocPhan = new javax.swing.JLabel();
        lbXemTKB = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelChiTiet = new javax.swing.JPanel();
        panelThongBao = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaThongBao = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        panelXemThoiKhoaBieu = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableThoiKhoaBieu = new javax.swing.JTable();
        panelDKHocPhan = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLietKeMonCoTheDK = new javax.swing.JTable();
        btnDangKy = new javax.swing.JButton();
        panelHuyHP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHuyHocPhan = new javax.swing.JTable();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(854, 621));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        lbDKHocPhan.setText("Đăng ký học phần");
        lbDKHocPhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDKHocPhanMouseClicked(evt);
            }
        });

        lbHuyDKHocPhan.setText("Hủy đăng ký học phần");
        lbHuyDKHocPhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHuyDKHocPhanMouseClicked(evt);
            }
        });

        lbXemTKB.setText("Xem thời khóa biểu");
        lbXemTKB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbXemTKBMouseClicked(evt);
            }
        });

        jLabel1.setText("Thông báo");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTacVuLayout = new javax.swing.GroupLayout(panelTacVu);
        panelTacVu.setLayout(panelTacVuLayout);
        panelTacVuLayout.setHorizontalGroup(
            panelTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTacVuLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(lbXemTKB)
                .addGap(45, 45, 45)
                .addComponent(lbDKHocPhan)
                .addGap(34, 34, 34)
                .addComponent(lbHuyDKHocPhan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTacVuLayout.setVerticalGroup(
            panelTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTacVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbDKHocPhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHuyDKHocPhan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbXemTKB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        panelChiTiet.setLayout(new java.awt.CardLayout());

        txtAreaThongBao.setEditable(false);
        txtAreaThongBao.setColumns(20);
        txtAreaThongBao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAreaThongBao.setForeground(new java.awt.Color(255, 0, 0));
        txtAreaThongBao.setLineWrap(true);
        txtAreaThongBao.setRows(5);
        jScrollPane4.setViewportView(txtAreaThongBao);

        jButton1.setText("Xóa thông báo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThongBaoLayout = new javax.swing.GroupLayout(panelThongBao);
        panelThongBao.setLayout(panelThongBaoLayout);
        panelThongBaoLayout.setHorizontalGroup(
            panelThongBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongBaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(panelThongBaoLayout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jButton1)
                .addContainerGap(381, Short.MAX_VALUE))
        );
        panelThongBaoLayout.setVerticalGroup(
            panelThongBaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongBaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        panelChiTiet.add(panelThongBao, "card5");

        tableThoiKhoaBieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableThoiKhoaBieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableThoiKhoaBieu);

        javax.swing.GroupLayout panelXemThoiKhoaBieuLayout = new javax.swing.GroupLayout(panelXemThoiKhoaBieu);
        panelXemThoiKhoaBieu.setLayout(panelXemThoiKhoaBieuLayout);
        panelXemThoiKhoaBieuLayout.setHorizontalGroup(
            panelXemThoiKhoaBieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemThoiKhoaBieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelXemThoiKhoaBieuLayout.setVerticalGroup(
            panelXemThoiKhoaBieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemThoiKhoaBieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelChiTiet.add(panelXemThoiKhoaBieu, "card2");

        tableLietKeMonCoTheDK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableLietKeMonCoTheDK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLietKeMonCoTheDK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLietKeMonCoTheDKMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableLietKeMonCoTheDK);

        btnDangKy.setText("Đăng ký");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDKHocPhanLayout = new javax.swing.GroupLayout(panelDKHocPhan);
        panelDKHocPhan.setLayout(panelDKHocPhanLayout);
        panelDKHocPhanLayout.setHorizontalGroup(
            panelDKHocPhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDKHocPhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDKHocPhanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
        );
        panelDKHocPhanLayout.setVerticalGroup(
            panelDKHocPhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDKHocPhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelChiTiet.add(panelDKHocPhan, "card3");

        tableHuyHocPhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableHuyHocPhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableHuyHocPhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHuyHocPhanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHuyHocPhan);

        btnHuy.setText("Hủy đăng ký");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHuyHPLayout = new javax.swing.GroupLayout(panelHuyHP);
        panelHuyHP.setLayout(panelHuyHPLayout);
        panelHuyHPLayout.setHorizontalGroup(
            panelHuyHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHuyHPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHuyHPLayout.createSequentialGroup()
                .addContainerGap(383, Short.MAX_VALUE)
                .addComponent(btnHuy)
                .addGap(378, 378, 378))
        );
        panelHuyHPLayout.setVerticalGroup(
            panelHuyHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHuyHPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuy)
                .addGap(21, 21, 21))
        );

        panelChiTiet.add(panelHuyHP, "card4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTacVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelTacVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void LoadCacMonDK() {
        tableRecord.clear();
        tableTitle.clear();

        tableTitle.add("Mã lớp");
        tableTitle.add("Thứ");
        tableTitle.add("Môn học");
        tableTitle.add("Giảng viên");
        tableTitle.add("Số sinh viên");
        tableTitle.add("Tiết bắt đầu");
        tableTitle.add("Tiết kết thúc");
        try {
            Statement stm = ConSQL.connection.createStatement();
            String sqlStr = "select MaLop,Ngayhoc, Tenmon,TenGV,SoSV, TietS, TietE from (Lopdoclap INNER JOIN MonHoc On Lopdoclap.Mamon = MonHoc.Mamon) INNER JOIN GiangVien On GiangVien.MaGV = Lopdoclap.maGV";
            ResultSet rs = stm.executeQuery(sqlStr);
            while (rs.next()) {
                Vector k = new Vector();
                k.add(rs.getString("MaLop"));
                k.add(rs.getString("Ngayhoc"));
                k.add(rs.getString("Tenmon"));
                k.add(rs.getString("TenGV"));
                k.add(rs.getString("SoSV"));
                k.add(rs.getInt("TietS"));
                k.add(rs.getInt("TietE"));
                tableRecord.add(k);
            }
            
            tableLietKeMonCoTheDK.setModel(new DefaultTableModel(tableRecord, tableTitle) {
                public boolean isCellEditable(int x, int y) {
                    return false;
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        int a = tableLietKeMonCoTheDK.getSelectedRow();
        int sosinhvien = Integer.parseInt(tableLietKeMonCoTheDK.getValueAt(a, 4).toString());
        if (sosinhvien>29) {
            JOptionPane.showMessageDialog(panelChiTiet, "Số lượng sinh viên đã đạt giới hạn. Không thể đăng ký thêm");
        } else {
            MaLop = tableLietKeMonCoTheDK.getValueAt(a, 0).toString();
            if(SVAc.DangKyHocPhan(MaSV, MaLop)>0)
                JOptionPane.showMessageDialog(panelChiTiet, "Đăng ký thành công");
            else JOptionPane.showMessageDialog(panelChiTiet, "Không thành công");
        }
        lbDKHocPhanMouseClicked(new MouseEvent(this, 1, 1, 1, 1, 1, 1, true));
    }//GEN-LAST:event_btnDangKyActionPerformed

    private void tableLietKeMonCoTheDKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLietKeMonCoTheDKMouseClicked
        btnDangKy.setEnabled(true);
        int a = tableLietKeMonCoTheDK.getSelectedRow();
        Vector k = (Vector) tableRecord.get(a);
        MaLop = k.get(0).toString();
    }//GEN-LAST:event_tableLietKeMonCoTheDKMouseClicked

    private void tableHuyHocPhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHuyHocPhanMouseClicked
        int a = tableHuyHocPhan.getSelectedRow();
        Vector k = (Vector) tableRecord.get(a);
        MaLop = k.get(0).toString();
        btnHuy.setEnabled(true);
    }//GEN-LAST:event_tableHuyHocPhanMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (JOptionPane.showConfirmDialog(panelChiTiet, "Bạn có chắc chắn muôn hủy học phần này?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Statement stm = ConSQL.connection.createStatement();
                if (stm.executeUpdate("Update DangKy set TinhTrang=0 where MaSV='" + MaSV + "' and MaLop = '" + MaLop + "'") == 1) {
                    JOptionPane.showMessageDialog(panelChiTiet, "Đã hủy học phần thành công");
                    lbHuyDKHocPhanMouseClicked(new MouseEvent(this, 1, 1, 1, 1, 1, 1, true));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panelChiTiet, "Xảy ra lỗi trong khi hủy học phần");
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lbHuyDKHocPhanMouseClicked(new MouseEvent(this, 1, 1, 1, 1, 1, 1, true));
    }//GEN-LAST:event_btnHuyActionPerformed

    private void lbDKHocPhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDKHocPhanMouseClicked
        CardLayout s = (CardLayout) panelChiTiet.getLayout();
        s.show(panelChiTiet, "DangKyHocPhan");
        LoadCacMonDK();
        if (btnDangKy.isEnabled()) {
            btnDangKy.setEnabled(false);
        }
    }//GEN-LAST:event_lbDKHocPhanMouseClicked

    private void lbXemTKBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXemTKBMouseClicked
        CardLayout s = (CardLayout) panelChiTiet.getLayout();
        s.show(panelChiTiet, "XemThoiKhoaBieu");

        tableRecord.clear();
        tableTitle.clear();
        tableTitle.add("Ngày học");
        tableTitle.add("Tên môn");
        tableTitle.add("Tiết bắt đầu");
        tableTitle.add("Tiết kết thúc");
        try {
            Statement stm = ConSQL.connection.createStatement();
            ResultSet rs = stm.executeQuery("select *from dbo.ThoiKhoabieu('" + MaSV + "')");
            while (rs.next()) {
                Vector k = new Vector();
                k.add(rs.getString("Ngayhoc"));
                k.add(rs.getString("Tenmon"));
                k.add(rs.getInt("TietS"));
                k.add(rs.getInt("TietE"));
                tableRecord.add(k);
            }
            tableThoiKhoaBieu.setModel(new DefaultTableModel(tableRecord, tableTitle) {
                public boolean isCellEditable(int x, int y) {
                    return false;
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbXemTKBMouseClicked

    private void lbHuyDKHocPhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHuyDKHocPhanMouseClicked
        CardLayout s = (CardLayout) panelChiTiet.getLayout();
        s.show(panelChiTiet, "HuyHocPhan");

        tableTitle.clear();
        tableTitle.add("Mã lớp");
        tableTitle.add("Ngày học");
        tableTitle.add("Tên môn");
        tableTitle.add("Tiết bắt đầu");
        tableTitle.add("Tiết kết thúc");
        tableRecord.clear();
        try {
            Statement stm = ConSQL.connection.createStatement();
            ResultSet rs = stm.executeQuery("select DangKy.MaLop,Ngayhoc, Tenmon, TietS, TietE from (Lopdoclap INNER JOIN MonHoc On Lopdoclap.Mamon = MonHoc.Mamon) INNER JOIN DangKy On DangKy.Malop = Lopdoclap.Malop where MaSV ='" + MaSV + "' and Tinhtrang = 1");
            while (rs.next()) {
                Vector k = new Vector();
                k.add(rs.getString("MaLop"));
                k.add(rs.getString("Ngayhoc"));
                k.add(rs.getString("Tenmon"));
                k.add(rs.getInt("TietS"));
                k.add(rs.getInt("TietE"));
                tableRecord.add(k);

            }
            tableHuyHocPhan.setModel(new DefaultTableModel(tableRecord, tableTitle) {
                public boolean isCellEditable(int x, int y) {
                    return false;
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnHuy.setEnabled(false);
    }//GEN-LAST:event_lbHuyDKHocPhanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(panelChiTiet, "Bạn muốn xóa thông báo?", "Xác nhận", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                if (ConSQL.connection.createStatement().executeUpdate("Update Account set ThongBao='' where Name='" + MaSV + "'") > 0) {
                    JOptionPane.showMessageDialog(panelChiTiet, "Thành công");
                } else {
                    JOptionPane.showMessageDialog(panelChiTiet, "Lỗi");
                }
                ResultSet rs = ConSQL.connection.createStatement().executeQuery("select ThongBao from Account where Name='" + MaSV + "'");
                while (rs.next()) {
                    txtAreaThongBao.setText(rs.getString(1));
                }

            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        CardLayout s = (CardLayout) panelChiTiet.getLayout();
        s.show(panelChiTiet, "ThongBao");
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSinhVien().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbDKHocPhan;
    private javax.swing.JLabel lbHuyDKHocPhan;
    private javax.swing.JLabel lbXemTKB;
    private javax.swing.JPanel panelChiTiet;
    private javax.swing.JPanel panelDKHocPhan;
    private javax.swing.JPanel panelHuyHP;
    private javax.swing.JPanel panelTacVu;
    private javax.swing.JPanel panelThongBao;
    private javax.swing.JPanel panelXemThoiKhoaBieu;
    private javax.swing.JTable tableHuyHocPhan;
    private javax.swing.JTable tableLietKeMonCoTheDK;
    private javax.swing.JTable tableThoiKhoaBieu;
    private javax.swing.JTextArea txtAreaThongBao;
    // End of variables declaration//GEN-END:variables
}
