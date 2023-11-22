/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;


import DAO.ChuyenDeDAO;
import DAO.HocVienDAO;
import DAO.KhoaHocDAO;
import DAO.NguoiHocDAO;
import Entity.ChuyenDe;
import Entity.HocVien;
import Entity.KhoaHoc;
import Entity.NguoiHoc;
import ThuVien.Auth;
import ThuVien.DialogHelper;
import TienIch.XDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author balis
 */
public class QuanLyHocVien extends javax.swing.JDialog {

    ChuyenDeDAO cdDAO = new ChuyenDeDAO();
    KhoaHocDAO khDAO = new KhoaHocDAO();
    NguoiHocDAO nhDAO = new NguoiHocDAO();
    HocVienDAO hvDAO = new HocVienDAO();

    /**
     * Creates new form StudentJDialog
     */
    public QuanLyHocVien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.fillComboBoxChuyenDe();
        setTitle("Quản lý học viên");
        setLocationRelativeTo(parent);
    }

    void fillComboBoxChuyenDe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbochuyenDe.getModel();
        model.removeAllElements();
        List<ChuyenDe> list = cdDAO.selectAll();
        for (ChuyenDe cd : list) {
            //add name of the subject to cbo
            model.addElement(cd);
        }
        this.fillComboBoxKhoaHoc();
    }

    void fillComboBoxKhoaHoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbkhoaHoc.getModel();
        model.removeAllElements();
        ChuyenDe cd = (ChuyenDe) cbochuyenDe.getSelectedItem();
        if (cd != null) {
            List<KhoaHoc> list = khDAO.selectByChuyenDe(cd.getMaCD());
            for (KhoaHoc kh : list) {
                model.addElement(kh);
            }
            this.fillTableHocVien();
        }
    }

    void fillTableHocVien() {
        DefaultTableModel model = (DefaultTableModel) tablehocVien.getModel();
        model.setRowCount(0);
        KhoaHoc kh = (KhoaHoc) cbbkhoaHoc.getSelectedItem();
        if (kh != null) {
            List<HocVien> list = hvDAO.selectByKhoaHoc(kh.getMaKH());
            for (int i = 0; i < list.size(); i++) {
                HocVien hv = list.get(i);
                String hoten = nhDAO.selectById(hv.getMaNH()).getHoTen();
                model.addRow(new Object[]{i + 1, hv.getMaHV(), hv.getMaNH(), hoten, hv.getDiem()});
            }
            this.fillTableNguoiHoc();
        }
    }

    void fillTableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tablenguoiHoc.getModel();
        model.setRowCount(0);
        KhoaHoc kh = (KhoaHoc) cbbkhoaHoc.getSelectedItem();
        String keyword = txtnguoiHoc.getText();
        List<NguoiHoc> list = nhDAO.selectNotInCourse(kh.getMaKH(), keyword);
        for (NguoiHoc nh : list) {
            model.addRow(new Object[]{nh.getMaNH(), nh.getHoTen(), nh.isGioiTinh() ? "Nam" : "Nữ",
                XDate.toString(nh.getNgaySinh(), "dd-MM-yyyy"), nh.getDienThoai(), nh.getEmail()});
        }
    }

    void addHocVien() {
        KhoaHoc kh = (KhoaHoc) cbbkhoaHoc.getSelectedItem();
        int[] rows = tablenguoiHoc.getSelectedRows();
        for (int row : rows) {
            String manh = (String) tablenguoiHoc.getValueAt(row, 0);
            HocVien hv = new HocVien();
            hv.setMaKH(kh.getMaKH());
            hv.setDiem(0);
            hv.setMaNH(manh);
            hvDAO.insert(hv);
        }
        this.fillTableHocVien();
        this.fillTableNguoiHoc();
    }

    void removeHocVien() {
        if (!Auth.isManager()) {
            DialogHelper.alert(this, "Bạn không có quyền xóa học viên!");
        } else {
            int[] rows = tablehocVien.getSelectedRows();
            if (rows.length > 0 && DialogHelper.confirm(this, "Bạn có muốn xóa học viên này không?")) {
                for (int row : rows) {
                    int mahv = (Integer) tablehocVien.getValueAt(row, 1);
                    hvDAO.delete(mahv);
                }
                this.fillTableHocVien();
                this.fillTableNguoiHoc();
            }
        }
    }

    void updateDiem() {
        int n = tablehocVien.getRowCount();
        for (int i = 0; i < n; i++) {
            int mahv = (Integer) tablehocVien.getValueAt(i, 1);
            double diem = (Double) tablehocVien.getValueAt(i, 4);
           
            HocVien hv = hvDAO.selectById(mahv);
            hv.setDiem(diem);
            hvDAO.update(hv);
        }
        this.fillTableHocVien();
        DialogHelper.alert(this, "Chỉnh Sửa điểm thành công");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlSubjects = new javax.swing.JPanel();
        cbochuyenDe = new javax.swing.JComboBox<>();
        pnlCourses = new javax.swing.JPanel();
        cbbkhoaHoc = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        pnlStudents = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablehocVien = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        pnlLearners = new javax.swing.JPanel();
        pnlSearch = new javax.swing.JPanel();
        txtnguoiHoc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablenguoiHoc = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlSubjects.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chuyên đề", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 51, 51))); // NOI18N

        cbochuyenDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbochuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochuyenDeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSubjectsLayout = new javax.swing.GroupLayout(pnlSubjects);
        pnlSubjects.setLayout(pnlSubjectsLayout);
        pnlSubjectsLayout.setHorizontalGroup(
            pnlSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbochuyenDe, 0, 245, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSubjectsLayout.setVerticalGroup(
            pnlSubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbochuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCourses.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khóa học", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 51, 51))); // NOI18N

        cbbkhoaHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbkhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbkhoaHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCoursesLayout = new javax.swing.GroupLayout(pnlCourses);
        pnlCourses.setLayout(pnlCoursesLayout);
        pnlCoursesLayout.setHorizontalGroup(
            pnlCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCoursesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbkhoaHoc, 0, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCoursesLayout.setVerticalGroup(
            pnlCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCoursesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbkhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablehocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TT", "Mã HV", "Mã NH", "Họ và tên", "Điểm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablehocVien);

        btnUpdate.setText("Chỉnh sửa điểm");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setText("Xóa khỏi khóa học");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStudentsLayout = new javax.swing.GroupLayout(pnlStudents);
        pnlStudents.setLayout(pnlStudentsLayout);
        pnlStudentsLayout.setHorizontalGroup(
            pnlStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlStudentsLayout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addGap(37, 37, 37)
                        .addComponent(btnUpdate))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlStudentsLayout.setVerticalGroup(
            pnlStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStudentsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        tabs.addTab("Học viên", pnlStudents);

        pnlSearch.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        txtnguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnguoiHocActionPerformed(evt);
            }
        });
        txtnguoiHoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnguoiHocKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnguoiHoc)
                .addContainerGap())
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablenguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TT", "Mã HV", "Gioi tính", "Ngày sinh", "SDT", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablenguoiHoc);

        btnAdd.setText("Thêm vào khóa học");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLearnersLayout = new javax.swing.GroupLayout(pnlLearners);
        pnlLearners.setLayout(pnlLearnersLayout);
        pnlLearnersLayout.setHorizontalGroup(
            pnlLearnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLearnersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLearnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLearnersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd)))
                .addContainerGap())
        );
        pnlLearnersLayout.setVerticalGroup(
            pnlLearnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLearnersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tabs.addTab("Người học", pnlLearners);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(pnlCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCourses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSubjects, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addHocVien();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeHocVien();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cbochuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochuyenDeActionPerformed
        fillComboBoxKhoaHoc();
    }//GEN-LAST:event_cbochuyenDeActionPerformed

    private void cbbkhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbkhoaHocActionPerformed
        fillTableHocVien();
    }//GEN-LAST:event_cbbkhoaHocActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateDiem();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtnguoiHocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnguoiHocKeyReleased
        fillTableNguoiHoc();
    }//GEN-LAST:event_txtnguoiHocKeyReleased

    private void txtnguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnguoiHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnguoiHocActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyHocVien dialog = new QuanLyHocVien(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbkhoaHoc;
    private javax.swing.JComboBox<String> cbochuyenDe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCourses;
    private javax.swing.JPanel pnlLearners;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlStudents;
    private javax.swing.JPanel pnlSubjects;
    private javax.swing.JTable tablehocVien;
    private javax.swing.JTable tablenguoiHoc;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField txtnguoiHoc;
    // End of variables declaration//GEN-END:variables
}
