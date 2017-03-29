/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk;

import com.atk.dao.BarangDao;
import com.atk.dao.KaryawanDao;
import com.atk.dao.SupplierDao;
import com.atk.dialog.Login;
import com.atk.dialog.PesanJumlah;
import com.atk.model.Barang;
import com.atk.model.Karyawan;
import com.atk.model.Supplier;
import com.atk.service.BarangService;
import com.atk.service.KaryawanService;
import com.atk.service.SupplierService;
import com.atk.view.BarangView;
import com.atk.view.KaryawanView;
import com.atk.view.KategoriView;
import com.atk.view.PanelPengelolaanBulanView;
import com.atk.view.PanelTambahBulanView;
import com.atk.view.PengelolaanBarangView;
import com.atk.view.PenggunaView;
import com.atk.view.ReportView;
import com.atk.view.ReturView;
import com.atk.view.RubahPasswordView;
import com.atk.view.SupplierView;
import com.atk.view.TambahBarangView;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import usu.widget.Form;

/**
 *
 * @author dicky-java
 */
public final class MenuUtama extends Form {

    private Image image;
    private KaryawanService karyawanDao;
    private BarangService barangDao;
    private SupplierService supplierDao;
    
    public MenuUtama() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        KeyStroke open=KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke open1=KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke open2=KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke open3=KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
        mnu_barang.setAccelerator(open);
        mnu_kategori.setAccelerator(open1);
        mnu_pengelolaan_barang.setAccelerator(open2);
        mnu_quit.setAccelerator(open3);
        jTimeLabel1.startTimer();
        setEnable();
        image=new ImageIcon(getClass().getResource("/com/atk/gambar/logo.jpg")).getImage();
    }

    public JLabel getLbl_pengguna() {
        return lbl_pengguna;
    }

    public void setLbl_pengguna(JLabel lbl_pengguna) {
        this.lbl_pengguna = lbl_pengguna;
    }
    
    

    public void setEnable(){
        BarangService barangDao1=new BarangDao();
        List<Barang> pesan = barangDao1.getPesan();
        if(!pesan.isEmpty()){
            btn_pesan.setEnabled(true);
        }
    }
    
    private void setScreen(JInternalFrame frame){
        jDesktopPane1.add(frame);
        Dimension screen = this.getSize();
        Dimension dim = frame.getSize();
        frame.setLocation((screen.width-dim.width)/2, (screen.height-dim.height)/4);
        frame.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_pengguna = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTimeLabel1 = new com.stripbandunk.jwidget.JTimeLabel();
        btn_pesan = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnu_keluar = new javax.swing.JMenuItem();
        mnu_rubahpassword = new javax.swing.JMenuItem();
        mnu_quit = new javax.swing.JMenuItem();
        mnu_master = new javax.swing.JMenu();
        mnu_barang = new javax.swing.JMenuItem();
        mnu_kategori = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnu_supplier = new javax.swing.JMenuItem();
        mnu_pengguna = new javax.swing.JMenuItem();
        mnu_karyawan = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnu_pengelolaan_barang = new javax.swing.JMenuItem();
        mnu_tambah_barang = new javax.swing.JMenuItem();
        mnu_retur = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        mnu_lapbarang = new javax.swing.JMenuItem();
        mnu_lapkaryawan = new javax.swing.JMenuItem();
        mnu_lapsupplier = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mnu_lappengelolaan = new javax.swing.JMenuItem();
        mnu_lappenambahan = new javax.swing.JMenuItem();

        setUndecorated(true);

        jLabel2.setFont(new java.awt.Font("FZWeiBei-S03", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SELAMAT DATANG DI SISTEM PENGELOLAAN DATA ATK");
        jDesktopPane1.add(jLabel2);
        jLabel2.setBounds(20, 560, 710, 40);

        jLabel1.setFont(new java.awt.Font("FZWeiBei-S03", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama Pengguna :");
        jDesktopPane1.add(jLabel1);
        jLabel1.setBounds(30, 531, 170, 30);

        lbl_pengguna.setFont(new java.awt.Font("FZWeiBei-S03", 3, 18)); // NOI18N
        lbl_pengguna.setForeground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.add(lbl_pengguna);
        lbl_pengguna.setBounds(200, 530, 150, 30);

        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jTimeLabel1.setTimeFormatter("dd MMMM yyyy  -  HH:mm:ss");

        btn_pesan.setText("Pesan");
        btn_pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_pesan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 604, Short.MAX_VALUE)
                .addComponent(jTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btn_pesan)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jMenuBar1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jMenuBar1ComponentRemoved(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/file.png"))); // NOI18N
        jMenu1.setText("File");

        mnu_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/exit.png"))); // NOI18N
        mnu_keluar.setText("Keluar Pengguna");
        mnu_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_keluarActionPerformed(evt);
            }
        });
        jMenu1.add(mnu_keluar);

        mnu_rubahpassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/logout.png"))); // NOI18N
        mnu_rubahpassword.setText("Rubah Password");
        mnu_rubahpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_rubahpasswordActionPerformed(evt);
            }
        });
        jMenu1.add(mnu_rubahpassword);

        mnu_quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/Exit.png"))); // NOI18N
        mnu_quit.setText("Quit");
        mnu_quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_quitActionPerformed(evt);
            }
        });
        jMenu1.add(mnu_quit);

        jMenuBar1.add(jMenu1);

        mnu_master.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/master.png"))); // NOI18N
        mnu_master.setText("Master");

        mnu_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/tools(1).png"))); // NOI18N
        mnu_barang.setText("Barang");
        mnu_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_barangActionPerformed(evt);
            }
        });
        mnu_master.add(mnu_barang);

        mnu_kategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/category.png"))); // NOI18N
        mnu_kategori.setText("Kategori");
        mnu_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_kategoriActionPerformed(evt);
            }
        });
        mnu_master.add(mnu_kategori);
        mnu_master.add(jSeparator1);

        mnu_supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/karyawan.png"))); // NOI18N
        mnu_supplier.setText("Supplier");
        mnu_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_supplierActionPerformed(evt);
            }
        });
        mnu_master.add(mnu_supplier);

        mnu_pengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/user.png"))); // NOI18N
        mnu_pengguna.setText("Pengguna");
        mnu_pengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_penggunaActionPerformed(evt);
            }
        });
        mnu_master.add(mnu_pengguna);

        mnu_karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/karyawan.png"))); // NOI18N
        mnu_karyawan.setText("Karyawan");
        mnu_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_karyawanActionPerformed(evt);
            }
        });
        mnu_master.add(mnu_karyawan);

        jMenuBar1.add(mnu_master);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/transaction.png"))); // NOI18N
        jMenu3.setText("Transaksi");

        mnu_pengelolaan_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/product(1).png"))); // NOI18N
        mnu_pengelolaan_barang.setText("Transaksi Pengambilan Barang");
        mnu_pengelolaan_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_pengelolaan_barangActionPerformed(evt);
            }
        });
        jMenu3.add(mnu_pengelolaan_barang);

        mnu_tambah_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/raw_material.png"))); // NOI18N
        mnu_tambah_barang.setText("Transaksi Tambah  Barang");
        mnu_tambah_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_tambah_barangActionPerformed(evt);
            }
        });
        jMenu3.add(mnu_tambah_barang);

        mnu_retur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/audit.png"))); // NOI18N
        mnu_retur.setText("Transaksi Retur Barang");
        mnu_retur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_returActionPerformed(evt);
            }
        });
        jMenu3.add(mnu_retur);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/reports.png"))); // NOI18N
        jMenu4.setText("Laporan");

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/printer.png"))); // NOI18N
        jMenu5.setText("Laporan Data Master");

        mnu_lapbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/report1.png"))); // NOI18N
        mnu_lapbarang.setText("Laporan Data Barang");
        mnu_lapbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_lapbarangActionPerformed(evt);
            }
        });
        jMenu5.add(mnu_lapbarang);

        mnu_lapkaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/report1.png"))); // NOI18N
        mnu_lapkaryawan.setText("Laporan Data Karyawan");
        mnu_lapkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_lapkaryawanActionPerformed(evt);
            }
        });
        jMenu5.add(mnu_lapkaryawan);

        mnu_lapsupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/report1.png"))); // NOI18N
        mnu_lapsupplier.setText("Laporan Data Supplier");
        mnu_lapsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_lapsupplierActionPerformed(evt);
            }
        });
        jMenu5.add(mnu_lapsupplier);

        jMenu4.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/printer.png"))); // NOI18N
        jMenu6.setText("Laporan Transaksi");

        mnu_lappengelolaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/report1.png"))); // NOI18N
        mnu_lappengelolaan.setText("Laporan Transaksi Pengambilan Barang");
        mnu_lappengelolaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_lappengelolaanActionPerformed(evt);
            }
        });
        jMenu6.add(mnu_lappengelolaan);

        mnu_lappenambahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/report1.png"))); // NOI18N
        mnu_lappenambahan.setText("Laporan Transaksi Penambahan Barang");
        mnu_lappenambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_lappenambahanActionPerformed(evt);
            }
        });
        jMenu6.add(mnu_lappenambahan);

        jMenu4.add(jMenu6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnu_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_kategoriActionPerformed
        // TODO add your handling code here:
        KategoriView view=new KategoriView();
        setScreen(view);
    }//GEN-LAST:event_mnu_kategoriActionPerformed

    private void mnu_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_karyawanActionPerformed
        // TODO add your handling code here:
        KaryawanView view=new KaryawanView();
        setScreen(view);
    }//GEN-LAST:event_mnu_karyawanActionPerformed

    private void mnu_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_supplierActionPerformed
        // TODO add your handling code here:
        SupplierView view=new SupplierView();
        setScreen(view);
    }//GEN-LAST:event_mnu_supplierActionPerformed

    private void mnu_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_barangActionPerformed
        // TODO add your handling code here:
        BarangView view=new BarangView();
        setScreen(view);
    }//GEN-LAST:event_mnu_barangActionPerformed

    private void mnu_quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_quitActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "Apakah anda ingin keluar aplikasi ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                JOptionPane.OK_OPTION){
            exit();
        }
    }//GEN-LAST:event_mnu_quitActionPerformed

    private void jMenuBar1ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jMenuBar1ComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1ComponentRemoved

    private void mnu_penggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_penggunaActionPerformed
        // TODO add your handling code here:
        PenggunaView view=new PenggunaView();
        setScreen(view);
    }//GEN-LAST:event_mnu_penggunaActionPerformed

    private void mnu_rubahpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_rubahpasswordActionPerformed
        // TODO add your handling code here:
        RubahPasswordView view=new RubahPasswordView();
        setScreen(view);
    }//GEN-LAST:event_mnu_rubahpasswordActionPerformed

    private void mnu_pengelolaan_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_pengelolaan_barangActionPerformed
        // TODO add your handling code here:
        PengelolaanBarangView view=new PengelolaanBarangView();
        jDesktopPane1.add(view);
        view.setLocation(0,0);
        view.setSize(jDesktopPane1.getWidth(),jDesktopPane1.getHeight());
        view.setVisible(true);
    }//GEN-LAST:event_mnu_pengelolaan_barangActionPerformed

    private void mnu_tambah_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_tambah_barangActionPerformed
        // TODO add your handling code here:
        TambahBarangView view=new TambahBarangView();
        jDesktopPane1.add(view);
        view.setLocation(0,0);
        view.setSize(jDesktopPane1.getWidth(),jDesktopPane1.getHeight());
        view.setVisible(true);
    }//GEN-LAST:event_mnu_tambah_barangActionPerformed

    private void btn_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesanActionPerformed
        // TODO add your handling code here:
        BarangService barangDao=new BarangDao();
        List<Barang> pesan = barangDao.getPesan();
        for(Barang b: pesan){
            if(b!=null){
                PesanJumlah pj=new PesanJumlah();
                pj.setVisible(true);
                break;
            }
        }
        
    }//GEN-LAST:event_btn_pesanActionPerformed

    private void mnu_lapkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_lapkaryawanActionPerformed
        try {
            // TODO add your handling code here:
            karyawanDao=new KaryawanDao();
            List<Karyawan> karyawans = karyawanDao.getKaryawans();
            HashMap map=new HashMap();
            map.put("logo", image);
            JasperPrint jPrint=JasperFillManager.fillReport(this.getClass().getClassLoader().
                    getResourceAsStream("com/atk/laporan/KaryawanRpt.jasper"),map, new JRBeanCollectionDataSource(karyawans));
            JRViewer jrv=new JRViewer(jPrint);
            ReportView view=new ReportView(jrv);
            jDesktopPane1.add(view);
            view.setLocation(0,0);
            view.setSize(jDesktopPane1.getWidth(),jDesktopPane1.getHeight());
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnu_lapkaryawanActionPerformed

    private void mnu_lapbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_lapbarangActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            barangDao=new BarangDao();
            List<Barang> bBarangs = barangDao.getbBarangs();
            HashMap map=new HashMap();
            map.put("logo", image);
            JasperPrint jPrint=JasperFillManager.fillReport(this.getClass().getClassLoader().
                    getResourceAsStream("com/atk/laporan/BarangRpt.jasper"),map, new JRBeanCollectionDataSource(bBarangs));
            JRViewer jrv=new JRViewer(jPrint);
            ReportView view=new ReportView(jrv);
            jDesktopPane1.add(view);
            view.setLocation(0,0);
            view.setSize(jDesktopPane1.getWidth(),jDesktopPane1.getHeight());
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnu_lapbarangActionPerformed

    private void mnu_lapsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_lapsupplierActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            supplierDao=new SupplierDao();
            List<Supplier> sSuppliers = supplierDao.getsSuppliers();
            HashMap map=new HashMap();
            map.put("logo", image);
            JasperPrint jPrint=JasperFillManager.fillReport(this.getClass().getClassLoader().
                    getResourceAsStream("com/atk/laporan/SupplierRpt.jasper"),map, new JRBeanCollectionDataSource(sSuppliers));
            JRViewer jrv=new JRViewer(jPrint);
            ReportView view=new ReportView(jrv);
            jDesktopPane1.add(view);
            view.setLocation(0,0);
            view.setSize(jDesktopPane1.getWidth(),jDesktopPane1.getHeight());
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnu_lapsupplierActionPerformed

    private void mnu_lappengelolaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_lappengelolaanActionPerformed
        // TODO add your handling code here:
        PanelPengelolaanBulanView view=new PanelPengelolaanBulanView();
        setScreen(view);
    }//GEN-LAST:event_mnu_lappengelolaanActionPerformed

    private void mnu_lappenambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_lappenambahanActionPerformed
        // TODO add your handling code here:
        PanelTambahBulanView view=new PanelTambahBulanView();
        setScreen(view);
    }//GEN-LAST:event_mnu_lappenambahanActionPerformed

    private void mnu_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_keluarActionPerformed
        // TODO add your handling code here:
        Login l=new Login();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnu_keluarActionPerformed

    private void mnu_returActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_returActionPerformed
        // TODO add your handling code here:
        ReturView view=new ReturView();
        jDesktopPane1.add(view);
        view.setLocation(0,0);
        view.setSize(jDesktopPane1.getWidth(),jDesktopPane1.getHeight());
        view.setVisible(true);
    }//GEN-LAST:event_mnu_returActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pesan;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private com.stripbandunk.jwidget.JTimeLabel jTimeLabel1;
    private javax.swing.JLabel lbl_pengguna;
    private javax.swing.JMenuItem mnu_barang;
    private javax.swing.JMenuItem mnu_karyawan;
    private javax.swing.JMenuItem mnu_kategori;
    private javax.swing.JMenuItem mnu_keluar;
    private javax.swing.JMenuItem mnu_lapbarang;
    private javax.swing.JMenuItem mnu_lapkaryawan;
    private javax.swing.JMenuItem mnu_lappenambahan;
    private javax.swing.JMenuItem mnu_lappengelolaan;
    private javax.swing.JMenuItem mnu_lapsupplier;
    private javax.swing.JMenu mnu_master;
    private javax.swing.JMenuItem mnu_pengelolaan_barang;
    private javax.swing.JMenuItem mnu_pengguna;
    private javax.swing.JMenuItem mnu_quit;
    private javax.swing.JMenuItem mnu_retur;
    private javax.swing.JMenuItem mnu_rubahpassword;
    private javax.swing.JMenuItem mnu_supplier;
    private javax.swing.JMenuItem mnu_tambah_barang;
    // End of variables declaration//GEN-END:variables
}
