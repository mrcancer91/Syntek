/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entity.Account;
import Entity.GiangVien;
import Entity.MonHoc;
import Entity.SinhVien;
import EntityAction.GiangVienAction;
import EntityAction.MonHocAction;
import EntityAction.SinhVienAction;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import util.ConSQL;
import util.DatePicker;
import util.StaticMethod;

/**
 *
 * @author MrOnly
 */
public class FormAdmin extends javax.swing.JFrame {

    /**
     * Creates new form FormAdmin
     */
    StaticMethod method = new StaticMethod();
    LoginDialog loginDialog = null;

    /**
     * Gọi OptionForm của từng dòng trong JTable bất kỳ
     */
    private void callOptionSinhVien(SinhVien sv) {
        DialogSinhVien dl = new DialogSinhVien(this, true, sv);
        dl.setVisible(true);
    }

    private void callOptionMon(MonHoc mon) {
        DialogMonHoc dl = new DialogMonHoc(this, true, mon);
        dl.setVisible(true);
    }

    private void callOptionGiangVien(GiangVien gv) {
        DialogGiangVien dl = new DialogGiangVien(this, true, gv);
        dl.setVisible(true);
    }

    public void loadTableDK() {
        Vector tableTit = new Vector();
        tableTit.add("Mã lớp");
        tableTit.add("Mã môn");
        tableTit.add("Mã giảng viên");
        tableTit.add("Số sinh viên");
        tableTit.add("Ngày học");
        tableTit.add("Tiết bắt đầu");
        tableTit.add("Tiết kết thúc");

        // method.getTableData(tableDangKy, tableTit, "Lopdoclap");
        tableDangKy.setModel(method.getDataFromTable("LopDocLap", tableTit));
        setAtr4Table(tableDangKy);
        try {
            Statement stm = ConSQL.connection.createStatement();
            ResultSet rs = stm.executeQuery("select ChoPhepSVDangKy from Config");
            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                    chkChoSVDangKy.setSelected(true);
                } else {
                    chkChoSVDangKy.setSelected(false);
                }
            }
            ResultSet rs1 = stm.executeQuery("select ChoPhepGVDangKy from Config");
            while (rs1.next()) {
                if (rs1.getString(1).equals("0")) {
                    chkChoGVDangKy.setSelected(true);
                } else {
                    chkChoGVDangKy.setSelected(false);
                }
            }
            ResultSet rs2 = stm.executeQuery("select NgayHetHan from Config");
            String ngayhethan = "";
            while (rs2.next()) {
                ngayhethan = rs2.getString(1);
            }
            String[] s = ngayhethan.split("-");
            String hethan = "";
            hethan += s[2] + "-";
            hethan += s[1] + "-";
            hethan += s[0];
            txtNgayHetHan.setText(hethan);
        } catch (SQLException ex) {
            Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//<editor-fold defaultstate="collapsed" desc="phương thức load bảng Môn học">
    private void loadTableMon() {
        tableMonHoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Vector k = new Vector();
        k.add("Mã môn");
        k.add("Tên môn");
        k.add("Loại môn");
        k.add("Hệ số");
        k.add("Khoa");
        k.add("Số tín chỉ");
        k.add("Kỳ Dự kiến");
        tableMonHoc.setModel(method.getDataFromTable("MonHoc", k));
        setAtr4Table(tableMonHoc);
        tableMonHoc.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableMonHoc.getSelectedColumn() > 0) {
                    MonHoc mon = new MonHoc();
                    int a = tableMonHoc.getSelectedRow();
                    String Ma = tableMonHoc.getValueAt(a, 1).toString();
                    String Ten = tableMonHoc.getValueAt(a, 2).toString();
                    String Loai = tableMonHoc.getValueAt(a, 3).toString();
                    String HeSo = tableMonHoc.getValueAt(a, 4).toString();
                    String Khoa = tableMonHoc.getValueAt(a, 5).toString();
                    String SoTinChi = tableMonHoc.getValueAt(a, 6).toString();
                    String KyDuKien = tableMonHoc.getValueAt(a, 7).toString();
                    mon.setMaMon(Ma);
                    mon.setTenMon(Ten);
                    mon.setLoaiMon(Loai);
                    mon.setHeSo(Float.parseFloat(HeSo));
                    mon.setKhoa(Khoa);
                    mon.setSoTinChi(Integer.parseInt(SoTinChi));
                    mon.setKyDuKien(Integer.parseInt(KyDuKien));
                    callOptionMon(mon);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        tableMonHoc.lostFocus(null, this);
        txtSearchMon.requestFocus();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="phương thức load bảng Giảng viên">
    public void loadTableGV() {
        Vector k = new Vector();
        k.add("Mã");
        k.add("Họ Tên");
        k.add("Khoa");
        k.add("Địa Chỉ");
        k.add("SĐT");
        k.add("Email");
        tableGiangVien.setModel(method.getDataFromTable("GiangVien", k));
        setAtr4Table(tableGiangVien);
        tableGiangVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && tableGiangVien.getSelectedColumn() > 0) {
                    GiangVien gv = new GiangVien();
                    int a = tableGiangVien.getSelectedRow();
                    String Ma = tableGiangVien.getValueAt(a, 1).toString();
                    String Ten = tableGiangVien.getValueAt(a, 2).toString();
                    String Khoa = tableGiangVien.getValueAt(a, 3).toString();
                    String DiaChi = tableGiangVien.getValueAt(a, 4).toString();
                    String SDT = tableGiangVien.getValueAt(a, 5).toString();
                    String Email = tableGiangVien.getValueAt(a, 6).toString();
                    gv.setMaGV(Ma);
                    gv.setTenGV(Ten);
                    gv.setDiaChi(DiaChi);
                    gv.setEmail(Email);
                    gv.setKhoa(Khoa);
                    gv.setSDT(SDT);
                    callOptionGiangVien(gv);
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //tableSinhVien.setRowHeight(35);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                //tableSinhVien.setRowHeight(30);
            }
        });
        txtSearchGV.requestFocus();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="phương thức load panel Account">
    private void loadTableAccount() {
        try {
            Vector k = new Vector();
            k.add("");
            k.add("Tài khoản");
            k.add("Thông báo");
            DefaultTableModel defaultModel = new DefaultTableModel() {
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
            defaultModel.setColumnIdentifiers(k);
            setAtr4Table(tableAccount);
            String sql = "select Name, ThongBao from Account";
            ResultSet rs1 = ConSQL.connection.createStatement().executeQuery(sql);
            while (rs1.next())
            {
                Vector temp = new Vector();
                temp.add(false);
                temp.add(rs1.getString(1));
                temp.add(rs1.getString(2));
                defaultModel.addRow(temp);
            }
            tableAccount.setModel(defaultModel);
            PreparedStatement ps = ConSQL.connection.prepareStatement("select distinct Khoa from SinhVien ");
            ResultSet rs = ps.executeQuery();
            cbbThongBaoKhoa.removeAllItems();
            cbbThongBaoKhoa.addItem("Tất cả");
            cbbThongBaoKhoa.setSelectedIndex(0);
            cbbThongBaoLop.setSelectedItem("Tất cả");
            while (rs.next()) {
                cbbThongBaoKhoa.addItem(rs.getObject(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = ConSQL.connection.prepareStatement("select distinct Khoa from GiangVien ");
            ResultSet rs = ps.executeQuery();
            cbbThongBaoKhoaGV.setSelectedItem("Tất cả");

            while (rs.next()) {
                cbbThongBaoKhoaGV.addItem(rs.getObject(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="phương thức load bảng Sinh Viên">
    private void loadTableSinhVien() {
        Vector k = new Vector();
        k.add("Mã");
        k.add("Họ");
        k.add("Tên");
        k.add("Niên Khóa");
        k.add("Lớp");
        k.add("Khoa");
        tableSinhVien.setModel(method.getDataFromTable("SinhVien", k));
        setAtr4Table(tableSinhVien);
        tableSinhVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && tableSinhVien.getSelectedColumn() > 0) {
                    SinhVien sv = new SinhVien();
                    int a = tableSinhVien.getSelectedRow();
                    String MaSV = tableSinhVien.getValueAt(a, 1).toString();
                    String Ho = tableSinhVien.getValueAt(a, 2).toString();
                    String Ten = tableSinhVien.getValueAt(a, 3).toString();
                    String NienKhoa = tableSinhVien.getValueAt(a, 4).toString();
                    String Lop = tableSinhVien.getValueAt(a, 5).toString();
                    String Khoa = tableSinhVien.getValueAt(a, 6).toString();
                    sv.setMa(MaSV);
                    sv.setHo(Ho);
                    sv.setTen(Ten);
                    sv.setNien_Khoa(NienKhoa);
                    sv.setLop(Lop);
                    sv.setKhoa(Khoa);
                    callOptionSinhVien(sv);
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //tableSinhVien.setRowHeight(35);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                //tableSinhVien.setRowHeight(30);
            }
        });
        txtSearchSinhVien.requestFocus();
    }
    //</editor-fold>

    private void setAtr4Table(JTable target) {
        TableColumn col;
        col = target.getColumnModel().getColumn(0);
        col.setPreferredWidth(50);
        for (int i = 1; i < target.getColumnCount() - 1; i++) {
            col = target.getColumnModel().getColumn(i);
            col.setPreferredWidth(150);

        }
        target.setFont(new Font("Tahoma", Font.PLAIN, 13));
        target.setRowHeight(30);
        target.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
        target.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    public boolean thongBao(String Ma, String msg) throws SQLException {
        PreparedStatement ps = ConSQL.connection.prepareStatement("Update Account set ThongBao=? where Name=?");
        ps.setString(1, msg);
        ps.setString(2, Ma);
        return ps.executeUpdate() > 0;
    }

    public FormAdmin() {
        ConSQL.getConnectToMSSQL();
        initComponents();
        loadTableSinhVien();
        loadTableAccount();
        loadTableDK();
        loadTableGV();
        loadTableMon();
    }

    public FormAdmin(LoginDialog dialog) {
        initComponents();
        this.loginDialog = dialog;
        loadTableSinhVien();
        loadTableAccount();
        loadTableDK();
        loadTableGV();
        loadTableMon();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnQuanlyDangKy = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        chkChoSVDangKy = new javax.swing.JCheckBox();
        chkChoGVDangKy = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        txtNgayHetHan = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableDangKy = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        pnQuanLyTaiKhoan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAccount = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbbThongBaoKhoa = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbbThongBaoLop = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtThongBaoSV = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbbThongBaoKhoaGV = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtThongBaoGV = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        pnQuanLyMonHoc = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMonHoc = new javax.swing.JTable();
        chkChonMon = new javax.swing.JCheckBox();
        lbThemMonHoc = new javax.swing.JLabel();
        lbSuaMonHoc = new javax.swing.JLabel();
        lbXoaMonHoc = new javax.swing.JLabel();
        cbbSearchMon = new javax.swing.JComboBox();
        txtSearchMon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pnQuanLySinhVien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSinhVien = new javax.swing.JTable();
        chkChon = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtSearchSinhVien = new javax.swing.JTextField();
        cbbSearchSinhVien = new javax.swing.JComboBox();
        lbSuaSV = new javax.swing.JLabel();
        lbThemMoiSV = new javax.swing.JLabel();
        lbXoaSV = new javax.swing.JLabel();
        pnQuanLyGiangVien = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGiangVien = new javax.swing.JTable();
        chkChon1 = new javax.swing.JCheckBox();
        lbThemGV = new javax.swing.JLabel();
        lbSuaGV = new javax.swing.JLabel();
        lbXoaGV = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbSearchGV = new javax.swing.JComboBox();
        txtSearchGV = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        chkChoSVDangKy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkChoSVDangKy.setText("Cho phép sinh viên đăng ký");
        chkChoSVDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkChoSVDangKyActionPerformed(evt);
            }
        });

        chkChoGVDangKy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkChoGVDangKy.setText("Cho phép giảng viên đăng ký");
        chkChoGVDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkChoGVDangKyActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Hạn cuối đăng ký: ");

        txtNgayHetHan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayHetHanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(326, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(chkChoGVDangKy)
                        .addComponent(chkChoSVDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(227, 227, 227))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkChoSVDangKy)
                .addGap(18, 18, 18)
                .addComponent(chkChoGVDangKy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableDangKy.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tableDangKy);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Danh sách các lớp độc lập");

        javax.swing.GroupLayout pnQuanlyDangKyLayout = new javax.swing.GroupLayout(pnQuanlyDangKy);
        pnQuanlyDangKy.setLayout(pnQuanlyDangKyLayout);
        pnQuanlyDangKyLayout.setHorizontalGroup(
            pnQuanlyDangKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanlyDangKyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnQuanlyDangKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(pnQuanlyDangKyLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 92, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnQuanlyDangKyLayout.setVerticalGroup(
            pnQuanlyDangKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanlyDangKyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý đăng ký", pnQuanlyDangKy);

        pnQuanLyTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnQuanLyTaiKhoanMouseClicked(evt);
            }
        });

        tableAccount.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableAccount);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông báo đến sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Khoa: ");

        cbbThongBaoKhoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbbThongBaoKhoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cbbThongBaoKhoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThongBaoKhoaItemStateChanged(evt);
            }
        });
        cbbThongBaoKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThongBaoKhoaActionPerformed(evt);
            }
        });
        cbbThongBaoKhoa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbbThongBaoKhoaFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Lớp: ");

        cbbThongBaoLop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbbThongBaoLop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cbbThongBaoLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbThongBaoLopMouseClicked(evt);
            }
        });
        cbbThongBaoLop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThongBaoLopItemStateChanged(evt);
            }
        });
        cbbThongBaoLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThongBaoLopActionPerformed(evt);
            }
        });
        cbbThongBaoLop.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbbThongBaoLopFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbbThongBaoLopFocusLost(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nội dung thông báo:");

        txtThongBaoSV.setColumns(20);
        txtThongBaoSV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtThongBaoSV.setLineWrap(true);
        txtThongBaoSV.setRows(5);
        jScrollPane5.setViewportView(txtThongBaoSV);

        jButton1.setText("Gửi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbThongBaoKhoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbThongBaoLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbThongBaoKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbThongBaoLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông báo đến giảng viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        cbbThongBaoKhoaGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbbThongBaoKhoaGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cbbThongBaoKhoaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThongBaoKhoaGVActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Khoa: ");

        jButton2.setText("Gửi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtThongBaoGV.setColumns(20);
        txtThongBaoGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtThongBaoGV.setLineWrap(true);
        txtThongBaoGV.setRows(5);
        jScrollPane6.setViewportView(txtThongBaoGV);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nội dung thông báo:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(40, 40, 40)
                        .addComponent(cbbThongBaoKhoaGV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbThongBaoKhoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnQuanLyTaiKhoanLayout = new javax.swing.GroupLayout(pnQuanLyTaiKhoan);
        pnQuanLyTaiKhoan.setLayout(pnQuanLyTaiKhoanLayout);
        pnQuanLyTaiKhoanLayout.setHorizontalGroup(
            pnQuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnQuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnQuanLyTaiKhoanLayout.setVerticalGroup(
            pnQuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnQuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnQuanLyTaiKhoanLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông báo", pnQuanLyTaiKhoan);

        tableMonHoc.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tableMonHoc);

        chkChonMon.setText("Chọn tất cả");
        chkChonMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkChonMonActionPerformed(evt);
            }
        });

        lbThemMonHoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbThemMonHoc.setText("Thêm mới");
        lbThemMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThemMonHocMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbThemMonHocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbThemMonHocMouseExited(evt);
            }
        });

        lbSuaMonHoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSuaMonHoc.setText("Sửa thông tin");
        lbSuaMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSuaMonHocMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSuaMonHocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSuaMonHocMouseExited(evt);
            }
        });

        lbXoaMonHoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbXoaMonHoc.setText("Xóa");
        lbXoaMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbXoaMonHocMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbXoaMonHocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbXoaMonHocMouseExited(evt);
            }
        });

        cbbSearchMon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbbSearchMon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mã môn học", "Tên môn học" }));

        txtSearchMon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSearchMon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMonKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tìm kiếm: ");

        javax.swing.GroupLayout pnQuanLyMonHocLayout = new javax.swing.GroupLayout(pnQuanLyMonHoc);
        pnQuanLyMonHoc.setLayout(pnQuanLyMonHocLayout);
        pnQuanLyMonHocLayout.setHorizontalGroup(
            pnQuanLyMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnQuanLyMonHocLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbbSearchMon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearchMon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
            .addGroup(pnQuanLyMonHocLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnQuanLyMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(pnQuanLyMonHocLayout.createSequentialGroup()
                        .addComponent(chkChonMon)
                        .addGap(97, 97, 97)
                        .addComponent(lbThemMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lbSuaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lbXoaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnQuanLyMonHocLayout.setVerticalGroup(
            pnQuanLyMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyMonHocLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnQuanLyMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearchMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSearchMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnQuanLyMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkChonMon)
                    .addGroup(pnQuanLyMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSuaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbThemMonHoc)
                        .addComponent(lbXoaMonHoc)))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("Quản lý môn học", pnQuanLyMonHoc);

        pnQuanLySinhVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnQuanLySinhVienKeyReleased(evt);
            }
        });

        tableSinhVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSinhVienMouseClicked(evt);
            }
        });
        tableSinhVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableSinhVienKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableSinhVien);

        chkChon.setText("Chọn tất cả");
        chkChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkChonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm: ");

        txtSearchSinhVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSearchSinhVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSinhVienKeyReleased(evt);
            }
        });

        cbbSearchSinhVien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mã sinh viên", "Họ Tên" }));

        lbSuaSV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSuaSV.setText("Sửa thông tin");
        lbSuaSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSuaSVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSuaSVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSuaSVMouseExited(evt);
            }
        });

        lbThemMoiSV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbThemMoiSV.setText("Thêm mới");
        lbThemMoiSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThemMoiSVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbThemMoiSVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbThemMoiSVMouseExited(evt);
            }
        });

        lbXoaSV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbXoaSV.setText("Xóa");
        lbXoaSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbXoaSVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbXoaSVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbXoaSVMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnQuanLySinhVienLayout = new javax.swing.GroupLayout(pnQuanLySinhVien);
        pnQuanLySinhVien.setLayout(pnQuanLySinhVienLayout);
        pnQuanLySinhVienLayout.setHorizontalGroup(
            pnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLySinhVienLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbbSearchSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearchSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnQuanLySinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnQuanLySinhVienLayout.createSequentialGroup()
                        .addComponent(chkChon)
                        .addGap(97, 97, 97)
                        .addComponent(lbThemMoiSV, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lbSuaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lbXoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 354, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnQuanLySinhVienLayout.setVerticalGroup(
            pnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLySinhVienLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSearchSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSuaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbThemMoiSV)
                        .addComponent(lbXoaSV))
                    .addComponent(chkChon))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý sinh viên", pnQuanLySinhVien);

        tableGiangVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tableGiangVien.setPreferredSize(new java.awt.Dimension(752, 427));
        jScrollPane3.setViewportView(tableGiangVien);

        chkChon1.setText("Chọn tất cả");
        chkChon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkChon1ActionPerformed(evt);
            }
        });

        lbThemGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbThemGV.setText("Thêm mới");
        lbThemGV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThemGVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbThemGVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbThemGVMouseExited(evt);
            }
        });

        lbSuaGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSuaGV.setText("Sửa thông tin");
        lbSuaGV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSuaGVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSuaGVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSuaGVMouseExited(evt);
            }
        });

        lbXoaGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbXoaGV.setText("Xóa");
        lbXoaGV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbXoaGVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbXoaGVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbXoaGVMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm: ");

        cbbSearchGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mã giảng viên", "Tên giảng viên" }));

        txtSearchGV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSearchGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchGVKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnQuanLyGiangVienLayout = new javax.swing.GroupLayout(pnQuanLyGiangVien);
        pnQuanLyGiangVien.setLayout(pnQuanLyGiangVienLayout);
        pnQuanLyGiangVienLayout.setHorizontalGroup(
            pnQuanLyGiangVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyGiangVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnQuanLyGiangVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQuanLyGiangVienLayout.createSequentialGroup()
                        .addComponent(chkChon1)
                        .addGap(93, 93, 93)
                        .addComponent(lbThemGV, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbSuaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbXoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(pnQuanLyGiangVienLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbbSearchGV, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearchGV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        pnQuanLyGiangVienLayout.setVerticalGroup(
            pnQuanLyGiangVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyGiangVienLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnQuanLyGiangVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbSearchGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnQuanLyGiangVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQuanLyGiangVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSuaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbThemGV)
                        .addComponent(lbXoaGV))
                    .addComponent(chkChon1))
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Quản lý giảng viên", pnQuanLyGiangVien);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, 570));

        getContentPane().add(jPanel1, "card2");

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jMenu1.setText("Công việc");

        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Thoát");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tài khoản");

        jMenuItem1.setText("Đổi mật khẩu");
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Trợ giúp");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        loginDialog.clearText();
        this.setVisible(false);
        this.loginDialog.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tableSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSinhVienMouseClicked
    }//GEN-LAST:event_tableSinhVienMouseClicked

    private void chkChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkChonActionPerformed
        if (chkChon.getText().equals("Chọn tất cả")) {
            for (int i = 0; i < tableSinhVien.getRowCount(); i++) {
                tableSinhVien.setValueAt(true, i, 0);
            }
            chkChon.setText("Bỏ chọn");
        } else {
            for (int i = 0; i < tableSinhVien.getRowCount(); i++) {
                tableSinhVien.setValueAt(false, i, 0);
            }
            chkChon.setText("Chọn tất cả");
        }
    }//GEN-LAST:event_chkChonActionPerformed

    private void lbXoaSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaSVMouseClicked
        int dem = 0;
        for (int i = 0; i < tableSinhVien.getRowCount(); i++) {
            if ("true".equals(tableSinhVien.getValueAt(i, 0).toString())) {
                dem++;
            }
        }
        if (dem > 0) {

            if (JOptionPane.showConfirmDialog(this, "Bạn thực sự muốn xóa " + dem + " sinh viên", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                SinhVienAction svac = new SinhVienAction();
                dem = 0;
                for (int i = 0; i < tableSinhVien.getRowCount(); i++) {
                    if ("true".equals(tableSinhVien.getValueAt(i, 0).toString())) {
                        if (svac.Delete(tableSinhVien.getValueAt(i, 1).toString()) > 0) {
                            dem++;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Đã xóa thành công " + dem + " sinh viên");
                loadTableSinhVien();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải đánh dấu chọn ít nhất 1 hàng trong bảng");
        }

    }//GEN-LAST:event_lbXoaSVMouseClicked

    private void lbThemMoiSVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemMoiSVMouseEntered
        StaticMethod.Label_HieuUngVao(lbThemMoiSV);
    }//GEN-LAST:event_lbThemMoiSVMouseEntered

    private void lbThemMoiSVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemMoiSVMouseExited
        StaticMethod.Label_HieuUngRa(lbThemMoiSV);
    }//GEN-LAST:event_lbThemMoiSVMouseExited

    private void lbSuaSVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaSVMouseEntered
        StaticMethod.Label_HieuUngVao(lbSuaSV);
    }//GEN-LAST:event_lbSuaSVMouseEntered

    private void lbSuaSVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaSVMouseExited
        StaticMethod.Label_HieuUngRa(lbSuaSV);
    }//GEN-LAST:event_lbSuaSVMouseExited

    private void lbXoaSVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaSVMouseEntered
        StaticMethod.Label_HieuUngVao(lbXoaSV);
    }//GEN-LAST:event_lbXoaSVMouseEntered

    private void lbXoaSVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaSVMouseExited
        StaticMethod.Label_HieuUngRa(lbXoaSV);
    }//GEN-LAST:event_lbXoaSVMouseExited

    private void txtSearchSinhVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSinhVienKeyReleased
        SinhVienAction svacc = new SinhVienAction();
        Vector newVec = new Vector();
        if (cbbSearchSinhVien.getSelectedIndex() == 0) {
            newVec = svacc.searchSV(txtSearchSinhVien.getText(), null);
        } else {
            newVec = svacc.searchSV(null, txtSearchSinhVien.getText());
        }
        DefaultTableModel model = (DefaultTableModel) tableSinhVien.getModel();
        Vector k = new Vector();
        k.add("Mã");
        k.add("Họ");
        k.add("Tên");
        k.add("Niên Khóa");
        k.add("Lớp");
        k.add("Khoa");
        k.add(0, "");
        model.setDataVector(newVec, k);
        tableSinhVien.getColumnModel().getColumn(0).setPreferredWidth(1);
        tableSinhVien.setModel(model);

    }//GEN-LAST:event_txtSearchSinhVienKeyReleased

    private void lbSuaSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaSVMouseClicked
        SinhVien sv = new SinhVien();
        int a = tableSinhVien.getSelectedRow();
        String MaSV = tableSinhVien.getValueAt(a, 1).toString();
        String Ho = tableSinhVien.getValueAt(a, 2).toString();
        String Ten = tableSinhVien.getValueAt(a, 3).toString();
        String NienKhoa = tableSinhVien.getValueAt(a, 4).toString();
        String Lop = tableSinhVien.getValueAt(a, 5).toString();
        String Khoa = tableSinhVien.getValueAt(a, 6).toString();
        sv.setMa(MaSV);
        sv.setHo(Ho);
        sv.setTen(Ten);
        sv.setNien_Khoa(NienKhoa);
        sv.setLop(Lop);
        sv.setKhoa(Khoa);
        callOptionSinhVien(sv);
        loadTableSinhVien();
    }//GEN-LAST:event_lbSuaSVMouseClicked

    private void lbThemMoiSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemMoiSVMouseClicked
        callOptionSinhVien(null);
        loadTableSinhVien();
    }//GEN-LAST:event_lbThemMoiSVMouseClicked

    private void pnQuanLySinhVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnQuanLySinhVienKeyReleased
    }//GEN-LAST:event_pnQuanLySinhVienKeyReleased

    private void tableSinhVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableSinhVienKeyReleased
        if (evt.getKeyCode() == evt.VK_DELETE) {
            lbXoaSVMouseClicked(new MouseEvent(this, 1, 0, 1, 1, 1, 1, false));
        }
    }//GEN-LAST:event_tableSinhVienKeyReleased

    private void pnQuanLyTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLyTaiKhoanMouseClicked
    }//GEN-LAST:event_pnQuanLyTaiKhoanMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int index = jTabbedPane1.getSelectedIndex();
        switch (index) {
            case 1:
                loadTableAccount();
                break;
            default:
                break;

        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void chkChon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkChon1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkChon1ActionPerformed

    private void chkChonMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkChonMonActionPerformed
        if (chkChonMon.getText().equals("Chọn tất cả")) {
            for (int i = 0; i < tableMonHoc.getRowCount(); i++) {
                tableMonHoc.setValueAt(true, i, 0);
            }
            chkChonMon.setText("Bỏ chọn");
        } else {
            for (int i = 0; i < tableMonHoc.getRowCount(); i++) {
                tableMonHoc.setValueAt(false, i, 0);
            }
            chkChonMon.setText("Chọn tất cả");
        }
    }//GEN-LAST:event_chkChonMonActionPerformed

    private void lbThemMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemMonHocMouseClicked
        callOptionMon(null);
        loadTableMon();
    }//GEN-LAST:event_lbThemMonHocMouseClicked

    private void lbThemMonHocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemMonHocMouseEntered
        StaticMethod.Label_HieuUngVao(lbThemMonHoc);
    }//GEN-LAST:event_lbThemMonHocMouseEntered

    private void lbThemMonHocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemMonHocMouseExited
        StaticMethod.Label_HieuUngRa(lbThemMonHoc);
    }//GEN-LAST:event_lbThemMonHocMouseExited

    private void lbSuaMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaMonHocMouseClicked
        MonHoc mon = new MonHoc();
        int a = tableMonHoc.getSelectedRow();
        if (a >= 0) {
            String Ma = tableMonHoc.getValueAt(a, 1).toString();
            String Ten = tableMonHoc.getValueAt(a, 2).toString();
            String Loai = tableMonHoc.getValueAt(a, 3).toString();
            String HeSo = tableMonHoc.getValueAt(a, 4).toString();
            String Khoa = tableMonHoc.getValueAt(a, 5).toString();
            String SoTinChi = tableMonHoc.getValueAt(a, 6).toString();
            String KyDuKien = tableMonHoc.getValueAt(a, 7).toString();
            mon.setMaMon(Ma);
            mon.setTenMon(Ten);
            mon.setLoaiMon(Loai);
            mon.setHeSo(Float.parseFloat(HeSo));
            mon.setKhoa(Khoa);
            mon.setSoTinChi(Integer.parseInt(SoTinChi));
            mon.setKyDuKien(Integer.parseInt(KyDuKien));
            callOptionMon(mon);
            loadTableMon();
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng nào trong bảng");
        }

    }//GEN-LAST:event_lbSuaMonHocMouseClicked

    private void lbSuaMonHocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaMonHocMouseEntered
        StaticMethod.Label_HieuUngVao(lbSuaMonHoc);
    }//GEN-LAST:event_lbSuaMonHocMouseEntered

    private void lbSuaMonHocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaMonHocMouseExited
        StaticMethod.Label_HieuUngRa(lbSuaMonHoc);
    }//GEN-LAST:event_lbSuaMonHocMouseExited

    private void lbXoaMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaMonHocMouseClicked
        int dem = 0;
        for (int i = 0; i < tableMonHoc.getRowCount(); i++) {
            if ("true".equals(tableMonHoc.getValueAt(i, 0).toString())) {
                dem++;
            }
        }
        if (dem > 0) {

            if (JOptionPane.showConfirmDialog(this, "Bạn thực sự muốn xóa " + dem + " môn học", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                MonHocAction svac = new MonHocAction();
                dem = 0;
                for (int i = 0; i < tableMonHoc.getRowCount(); i++) {
                    if ("true".equals(tableMonHoc.getValueAt(i, 0).toString())) {
                        if (svac.Xoa(tableMonHoc.getValueAt(i, 1).toString()) > 0) {
                            dem++;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Đã xóa thành công " + dem + " Môn học");
                loadTableSinhVien();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải đánh dấu chọn ít nhất 1 hàng trong bảng");
        }


    }//GEN-LAST:event_lbXoaMonHocMouseClicked

    private void lbXoaMonHocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaMonHocMouseEntered
        StaticMethod.Label_HieuUngVao(lbXoaMonHoc);
    }//GEN-LAST:event_lbXoaMonHocMouseEntered

    private void lbXoaMonHocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaMonHocMouseExited
        StaticMethod.Label_HieuUngRa(lbXoaMonHoc);
    }//GEN-LAST:event_lbXoaMonHocMouseExited

    private void lbThemGVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemGVMouseClicked
        callOptionGiangVien(null);
    }//GEN-LAST:event_lbThemGVMouseClicked

    private void lbThemGVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemGVMouseEntered
        StaticMethod.Label_HieuUngVao(lbThemGV);
    }//GEN-LAST:event_lbThemGVMouseEntered

    private void lbThemGVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThemGVMouseExited
        StaticMethod.Label_HieuUngRa(lbThemGV);
    }//GEN-LAST:event_lbThemGVMouseExited

    private void lbSuaGVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaGVMouseClicked

        int a = tableGiangVien.getSelectedRow();
        if (a >= 0) {
            GiangVien gv = new GiangVien();
            String Ma = tableGiangVien.getValueAt(a, 1).toString();
            String Ten = tableGiangVien.getValueAt(a, 2).toString();
            String Khoa = tableGiangVien.getValueAt(a, 3).toString();
            String DiaChi = tableGiangVien.getValueAt(a, 4).toString();
            String SDT = tableGiangVien.getValueAt(a, 5).toString();
            String Email = tableGiangVien.getValueAt(a, 6).toString();
            gv.setMaGV(Ma);
            gv.setTenGV(Ten);
            gv.setDiaChi(DiaChi);
            gv.setEmail(Email);
            gv.setKhoa(Khoa);
            gv.setSDT(SDT);
            callOptionGiangVien(gv);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Bạn cần chọn 1 dòng trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_lbSuaGVMouseClicked

    private void lbSuaGVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaGVMouseEntered
        StaticMethod.Label_HieuUngVao(lbSuaGV);
    }//GEN-LAST:event_lbSuaGVMouseEntered

    private void lbSuaGVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSuaGVMouseExited
        StaticMethod.Label_HieuUngRa(lbSuaGV);
    }//GEN-LAST:event_lbSuaGVMouseExited

    private void lbXoaGVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaGVMouseClicked
        int dem = 0;
        for (int i = 0; i < tableGiangVien.getRowCount(); i++) {
            if ("true".equals(tableGiangVien.getValueAt(i, 0).toString())) {
                dem++;
            }
        }
        if (dem > 0) {

            if (JOptionPane.showConfirmDialog(this, "Bạn thực sự muốn xóa " + dem + " giảng viên?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                GiangVienAction svac = new GiangVienAction();
                dem = 0;
                for (int i = 0; i < tableGiangVien.getRowCount(); i++) {
                    if ("true".equals(tableGiangVien.getValueAt(i, 0).toString())) {
                        if (svac.Xoa(tableGiangVien.getValueAt(i, 1).toString().trim()) > 0) {
                            dem++;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Đã xóa thành công " + dem + " giảng viên");
                loadTableGV();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải đánh dấu chọn ít nhất 1 hàng trong bảng");
        }
    }//GEN-LAST:event_lbXoaGVMouseClicked

    private void lbXoaGVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaGVMouseEntered
        StaticMethod.Label_HieuUngVao(lbXoaGV);
    }//GEN-LAST:event_lbXoaGVMouseEntered

    private void lbXoaGVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXoaGVMouseExited
        StaticMethod.Label_HieuUngRa(lbXoaGV);
    }//GEN-LAST:event_lbXoaGVMouseExited

    private void txtSearchMonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMonKeyReleased
        MonHocAction svacc = new MonHocAction();
        Vector newVec = new Vector();
        if (cbbSearchMon.getSelectedIndex() == 0) {
            newVec = svacc.searchMonHoc(txtSearchMon.getText(), null);
        } else {
            newVec = svacc.searchMonHoc(null, txtSearchMon.getText());
        }
        DefaultTableModel model = (DefaultTableModel) tableMonHoc.getModel();
        Vector k = new Vector();
        k.add("Mã môn");
        k.add("Tên môn");
        k.add("Loại môn");
        k.add("Hệ số");
        k.add("Khoa");
        k.add("Số tín chỉ");
        k.add("Kỳ Dự kiến");
        k.add(0, "");
        model.setColumnIdentifiers(k);
        model.setDataVector(newVec, k);
        tableMonHoc.getColumnModel().getColumn(0).setPreferredWidth(10);
        tableMonHoc.setModel(model);
    }//GEN-LAST:event_txtSearchMonKeyReleased

    private void txtSearchGVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchGVKeyReleased
        GiangVienAction svacc = new GiangVienAction();
        Vector newVec = new Vector();
        if (cbbSearchGV.getSelectedIndex() == 0) {
            newVec = svacc.searchGiangVien(txtSearchGV.getText(), null);
        } else {
            newVec = svacc.searchGiangVien(null, txtSearchGV.getText());
        }
        DefaultTableModel model = (DefaultTableModel) tableGiangVien.getModel();
        Vector k = new Vector();
        k.add("Mã");
        k.add("Họ Tên");
        k.add("Khoa");
        k.add("Địa Chỉ");
        k.add("SĐT");
        k.add("Email");
        k.add(0, "");
        model.setColumnIdentifiers(k);
        model.setDataVector(newVec, k);
        tableGiangVien.getColumnModel().getColumn(0).setPreferredWidth(15);
        tableGiangVien.setModel(model);
    }//GEN-LAST:event_txtSearchGVKeyReleased

    private void cbbThongBaoLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbThongBaoLopMouseClicked
    }//GEN-LAST:event_cbbThongBaoLopMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tb = txtThongBaoSV.getText();
        try {
            if ("Tất cả".equals(cbbThongBaoKhoa.getSelectedItem().toString())) {
                PreparedStatement ps = ConSQL.connection.prepareStatement("Select MaSV from SinhVien");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    thongBao(rs.getString(1).trim(), tb);
                }
            } else {
                if ("Tất cả".equals(cbbThongBaoLop.getSelectedItem().toString())) {
                    PreparedStatement ps = ConSQL.connection.prepareStatement("Select MaSV from SinhVien where Khoa=?");
                    ps.setString(1, cbbThongBaoKhoa.getSelectedItem().toString().trim());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        thongBao(rs.getString(1).trim(), tb);
                    }
                } else {
                    String s = cbbThongBaoLop.getSelectedItem().toString();
                    String[] mang = s.split("-");
                    String Lop = mang[0];
                    String Nien_Khoa = mang[1];
                    PreparedStatement ps = ConSQL.connection.prepareStatement("Select MaSV from SinhVien where Nien_Khoa=? and Lop=?");
                    ps.setString(2, Lop.trim());
                    ps.setString(1, Nien_Khoa.trim());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        thongBao(rs.getString(1).trim(), tb);
                    }
                }
            }
        } catch (SQLException exx) {
            exx.printStackTrace();
        }
        loadTableAccount();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbThongBaoKhoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThongBaoKhoaItemStateChanged
    }//GEN-LAST:event_cbbThongBaoKhoaItemStateChanged

    private void cbbThongBaoLopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThongBaoLopItemStateChanged
    }//GEN-LAST:event_cbbThongBaoLopItemStateChanged

    private void cbbThongBaoKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThongBaoKhoaActionPerformed
        cbbThongBaoLop.requestFocus();
    }//GEN-LAST:event_cbbThongBaoKhoaActionPerformed

    private void cbbThongBaoLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThongBaoLopActionPerformed
        txtThongBaoSV.requestFocus();
    }//GEN-LAST:event_cbbThongBaoLopActionPerformed

    private void cbbThongBaoKhoaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbbThongBaoKhoaFocusLost
//        cbbThongBaoLop.removeAllItems();
//        int item = cbbThongBaoKhoa.getSelectedIndex();
//        if (0 != item) {
//            cbbThongBaoLop.removeAllItems();
//            cbbThongBaoLop.addItem("Tất cả");
//            try {
//                PreparedStatement ps = ConSQL.connection.prepareStatement("select distinct Lop from SinhVien where Khoa=?");
//                ps.setString(1, cbbThongBaoKhoa.getItemAt(item).toString());
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    cbbThongBaoLop.addItem(rs.getObject(1));
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            cbbThongBaoLop.setSelectedIndex(0);
//        }
    }//GEN-LAST:event_cbbThongBaoKhoaFocusLost

    private void cbbThongBaoLopFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbbThongBaoLopFocusLost
    }//GEN-LAST:event_cbbThongBaoLopFocusLost

    private void cbbThongBaoKhoaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThongBaoKhoaGVActionPerformed
    }//GEN-LAST:event_cbbThongBaoKhoaGVActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if ("Tất cả".equals(cbbThongBaoKhoaGV.getSelectedItem().toString())) {
            try {
                PreparedStatement ps = ConSQL.connection.prepareStatement("select MaGV from GiangVien");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String Ma = rs.getString(1);
                    thongBao(Ma, txtThongBaoGV.getText());
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String Khoa = cbbThongBaoKhoaGV.getSelectedItem().toString();
            try {
                PreparedStatement ps = ConSQL.connection.prepareStatement("select MaGV from GiangVien where Khoa=?");
                ps.setString(1, Khoa);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String Ma = rs.getString(1);
                    thongBao(Ma, txtThongBaoGV.getText());
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cbbThongBaoKhoa.removeAllItems();
        loadTableAccount();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void chkChoSVDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkChoSVDangKyActionPerformed

        try {
            Statement srm = ConSQL.connection.createStatement();
            if (chkChoSVDangKy.isSelected()) {
                if (srm.executeUpdate("Update Config set ChoPhepSVDangKy=1") > 0) {
                    JOptionPane.showMessageDialog(null, "Đã cho phép sinh viên đăng ký học phần");
                }
            } else {
                if (srm.executeUpdate("Update Config set ChoPhepSVDangKy=0") > 0) {
                    JOptionPane.showMessageDialog(null, "Đã ngừng cho phép sinh viên đăng ký học phần");
                }
            }

        } catch (SQLException ex) {

            Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_chkChoSVDangKyActionPerformed

    private void txtNgayHetHanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayHetHanMouseClicked
        if (evt.getClickCount() == 2) {
            DatePicker dp = new DatePicker(null);
            txtNgayHetHan.setText(dp.setPickedDate());
            String[] s = txtNgayHetHan.getText().split("-");
            String ngayhethan = "";
            ngayhethan += s[2] + "-";
            ngayhethan += s[1] + "-";
            ngayhethan += s[0];
            try {

                Statement stm = ConSQL.connection.createStatement();
                if (stm.executeUpdate("Update Config set NgayHetHan='" + ngayhethan + "'") == 1) {
                    JOptionPane.showMessageDialog(null, "Thay đổi hạn đăng ký thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi! Thay đổi ngày hết hạn thất bại");
                Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtNgayHetHanMouseClicked

    private void chkChoGVDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkChoGVDangKyActionPerformed
        try {
            Statement srm = ConSQL.connection.createStatement();
            if (chkChoGVDangKy.isSelected()) {
                if (srm.executeUpdate("Update Config set ChoPhepGVDangKy=1") > 0) {
                    JOptionPane.showMessageDialog(null, "Đã cho phép giảng viên đăng ký học phần");
                }
            } else {
                if (srm.executeUpdate("Update Config set ChoPhepGVDangKy=0") > 0) {
                    JOptionPane.showMessageDialog(null, "Đã ngừng cho phép giảng viên đăng ký lớp giảng");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_chkChoGVDangKyActionPerformed

    private void cbbThongBaoLopFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbbThongBaoLopFocusGained
        cbbThongBaoLop.removeAllItems();
        cbbThongBaoLop.addItem("Tất cả");
        int item = cbbThongBaoKhoa.getSelectedIndex();
        if (0 != item) {
            try {
                PreparedStatement ps = ConSQL.connection.prepareStatement("select distinct Lop+'-'+Nien_Khoa from SinhVien where Khoa=?");
                ps.setString(1, cbbThongBaoKhoa.getItemAt(item).toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    cbbThongBaoLop.addItem(rs.getObject(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cbbThongBaoLop.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cbbThongBaoLopFocusGained

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        loginDialog.clearText();
        this.setVisible(false);
        this.loginDialog.setVisible(true);

    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(FormAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                new FormAdmin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbbSearchGV;
    private javax.swing.JComboBox cbbSearchMon;
    private javax.swing.JComboBox cbbSearchSinhVien;
    private javax.swing.JComboBox cbbThongBaoKhoa;
    private javax.swing.JComboBox cbbThongBaoKhoaGV;
    private javax.swing.JComboBox cbbThongBaoLop;
    private javax.swing.JCheckBox chkChoGVDangKy;
    private javax.swing.JCheckBox chkChoSVDangKy;
    private javax.swing.JCheckBox chkChon;
    private javax.swing.JCheckBox chkChon1;
    private javax.swing.JCheckBox chkChonMon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbSuaGV;
    private javax.swing.JLabel lbSuaMonHoc;
    private javax.swing.JLabel lbSuaSV;
    private javax.swing.JLabel lbThemGV;
    private javax.swing.JLabel lbThemMoiSV;
    private javax.swing.JLabel lbThemMonHoc;
    private javax.swing.JLabel lbXoaGV;
    private javax.swing.JLabel lbXoaMonHoc;
    private javax.swing.JLabel lbXoaSV;
    private javax.swing.JPanel pnQuanLyGiangVien;
    private javax.swing.JPanel pnQuanLyMonHoc;
    private javax.swing.JPanel pnQuanLySinhVien;
    private javax.swing.JPanel pnQuanLyTaiKhoan;
    private javax.swing.JPanel pnQuanlyDangKy;
    private javax.swing.JTable tableAccount;
    private javax.swing.JTable tableDangKy;
    private javax.swing.JTable tableGiangVien;
    private javax.swing.JTable tableMonHoc;
    private javax.swing.JTable tableSinhVien;
    private javax.swing.JTextField txtNgayHetHan;
    private javax.swing.JTextField txtSearchGV;
    private javax.swing.JTextField txtSearchMon;
    private javax.swing.JTextField txtSearchSinhVien;
    private javax.swing.JTextArea txtThongBaoGV;
    private javax.swing.JTextArea txtThongBaoSV;
    // End of variables declaration//GEN-END:variables
}
