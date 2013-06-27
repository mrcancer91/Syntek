/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entity.GiangVien;
import EntityAction.GiangVienAction;
import EntityAction.MonHocAction;
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
public class DialogGiangVien extends javax.swing.JDialog {

    /**
     * Creates new form DialogGiangVien
     */
    private GiangVien gv = new GiangVien();

    public DialogGiangVien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ConSQL.getConnectToMSSQL();
        initComponents();
        try {

            Statement stm = ConSQL.connection.createStatement();
            ResultSet rs = stm.executeQuery("select top 1 MaGV from GiangVien order by MaGV desc ");

            if (rs.next()) {
                String ma = rs.getString(1);
                ma = ma.trim();
                String ma1 = ma.substring(2, ma.length());
                Integer masv = Integer.parseInt(ma1);
                masv++;
                ma = "GV";
                while (ma.length() < 6 - masv.toString().length()) {
                    ma += "0";
                }
                ma += masv.toString();
                this.txtMaGV.setText(ma);
            }
            btnThem.setText("Thêm");
        } catch (SQLException ex) {
            Logger.getLogger(DialogSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DialogGiangVien(java.awt.Frame parent, boolean modal, GiangVien giangvien) {
        super(parent, modal);
        initComponents();
        if (null != giangvien) {
            gv = giangvien;
            txtDiaChiGV.setText(gv.getDiaChi());
            txtEmailGV.setText(gv.getEmail());
            txtKhoaGV.setText(gv.getKhoa());
            txtMaGV.setText(gv.getMaGV());
            txtTenGV.setText(gv.getTenGV());
            txtSDTGV.setText(gv.getSDT());
            btnThem.setText("Sửa");
        } else {
            try {

                Statement stm = ConSQL.connection.createStatement();
                ResultSet rs = stm.executeQuery("select top 1 MaGV from GiangVien order by MaGV desc ");

                if (rs.next()) {
                    String ma = rs.getString(1);
                    ma = ma.trim();
                    String ma1 = ma.substring(2, ma.length());
                    Integer masv = Integer.parseInt(ma1);
                    masv++;
                    ma = "GV";
                    while (ma.length() < 6 - masv.toString().length()) {
                        ma += "0";
                    }
                    ma += masv.toString();
                    this.txtMaGV.setText(ma);
                }
                btnThem.setText("Thêm");
            } catch (SQLException ex) {
                Logger.getLogger(DialogSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jLabel27 = new javax.swing.JLabel();
        txtKhoaGV = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtSDTGV = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtDiaChiGV = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtEmailGV = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTenGV = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtMaGV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Địa chỉ: ");

        txtKhoaGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Email: ");

        txtSDTGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Số Điện Thoại: ");

        txtDiaChiGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Mã Giảng Viên: ");

        txtEmailGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Tên Giảng Viên: ");

        txtTenGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Khoa: ");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtMaGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel24))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmailGV, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(txtSDTGV)
                    .addComponent(txtTenGV)
                    .addComponent(txtKhoaGV)
                    .addComponent(txtDiaChiGV)
                    .addComponent(txtMaGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtKhoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTGV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailGV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String action = btnThem.getText();
        if ("Sửa".equals(action)) {
            gv.setDiaChi(txtDiaChiGV.getText());
            gv.setEmail(txtEmailGV.getText());
            gv.setKhoa(txtKhoaGV.getText());
            gv.setMaGV(txtMaGV.getText());
            gv.setSDT(txtSDTGV.getText());
            gv.setTenGV(txtTenGV.getText());

            GiangVienAction ac = new GiangVienAction();
            int re = ac.Sua(gv, txtMaGV.getText());
            if (re > 0) {
                JOptionPane.showMessageDialog(rootPane, "Sửa giảng viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi sửa", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            gv.setDiaChi(txtDiaChiGV.getText());
            gv.setEmail(txtEmailGV.getText());
            gv.setKhoa(txtKhoaGV.getText());
            gv.setMaGV(txtMaGV.getText());
            gv.setSDT(txtSDTGV.getText());
            gv.setTenGV(txtTenGV.getText());
            GiangVienAction ac = new GiangVienAction();
            int re = ac.Them(gv);
            if (re > 0) {
                JOptionPane.showMessageDialog(rootPane, "Thêm giảng viên mới thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Lỗi khi thêm", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogGiangVien dialog = new DialogGiangVien(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDiaChiGV;
    private javax.swing.JTextField txtEmailGV;
    private javax.swing.JTextField txtKhoaGV;
    private javax.swing.JLabel txtMaGV;
    private javax.swing.JTextField txtSDTGV;
    private javax.swing.JTextField txtTenGV;
    // End of variables declaration//GEN-END:variables
}