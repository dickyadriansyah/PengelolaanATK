/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.view;

import com.atk.dao.BarangDao;
import com.atk.dao.ReturDao;
import com.atk.dao.TambahBarangDao;
import com.atk.dialog.PencarianBeli;
import com.atk.dialog.PencarianDialog;
import com.atk.dialog.ReturJumlah;
import com.atk.dialog.TampilReport;
import com.atk.model.ReportRetur;
import com.atk.model.Retur;
import com.atk.model.ReturDetil;
import com.atk.model.TambahBarang;
import com.atk.service.BarangService;
import com.atk.service.ReturService;
import com.atk.service.TambahBarangService;
import com.atk.tabelmodel.TabelModelReturDetil;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author dicky-java
 */
public class ReturView extends javax.swing.JInternalFrame {

    private ReturService returDao;
    private BarangService barangDao;
    private TambahBarangService tambahDao;
    private TabelModelReturDetil tabelModelReturDetil;
    private List<ReturDetil> list;
    private TambahBarang tb;
    private Retur retur;
    private Image image;
    
    public ReturView() {
        initComponents();
        returDao=new ReturDao();
        barangDao=new BarangDao();
        tambahDao=new TambahBarangDao();
        list=new ArrayList<>();
        tabelModelReturDetil=new TabelModelReturDetil(list, ReturDetil.class);
        tbl_retur.setDynamicModel(tabelModelReturDetil);
        image=new ImageIcon(getClass().getResource("/com/atk/gambar/logo.jpg")).getImage();
        tampilanAwal();
    }
    
    private List<ReturDetil> getrReturDetils(){
        List lst=new ArrayList();
        for(int i=0; i<tbl_retur.getRowCount(); i++){
            ReturDetil get = tabelModelReturDetil.get(tbl_retur.convertRowIndexToModel(i));
            get.setRetur(retur);
            lst.add(get);
            barangDao.kurangJumlahStok(get.getJumlah(), get.getBarang());
            tambahDao.kurangJumlah(get.getJumlah(), get.getBarang(), tb);
        }
        return lst;
    }
    
    private Retur getrRetur(){
        retur=new Retur();
        retur.setId_retur(txt_id.getText());
        retur.setTanggal_retur(txt_tglretur.getDate());
        retur.setTanggal_beli(txt_tglbeli.getDate());
        retur.setTotal_beli(Integer.valueOf(txt_totalbeli.getText()));
        retur.setTotal_retur(Integer.valueOf(txt_totalretur.getText()));
        retur.setTambahBarang(tb);
        retur.setSupplier(txt_supplier.getText());
        retur.setReturDetils(getrReturDetils());
        return retur;
    }
    
    private TambahBarang getTambahBarang(){
        tb=new TambahBarang();
        tb.setNo_tambah(txt_nopem.getText());
        tb.setTotal(Integer.valueOf(txt_total.getText()));
        return tb;
    }
    
    private void tampilanAwal(){
        btn_search.setEnabled(false);
        btn_cari.setEnabled(false);
        btn_simpan.setEnabled(false);
        btn_tambah.setEnabled(true);
        btn_rubah.setEnabled(false);
        
        txt_id.setText("");
        txt_tglretur.setDate(null);
        txt_tglbeli.setDate(null);
        txt_nopem.setText("");
        txt_supplier.setText("");
        txt_totalbeli.setText("");
        txt_totalretur.setText("");
        jProgressBar.setIndeterminate(false);
        tabelModelReturDetil.clear();
        txt_total.setText("");
    }
    
    private boolean validasi(){
        boolean valid=false;
        if(txt_totalretur.getText().trim().isEmpty() || txt_totalretur.getText().equals("0")){
            JOptionPane.showMessageDialog(null, "Total Retur Tidak Boleh Kosong");
        }else if(tabelModelReturDetil.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Item Barang Tidak Boleh Kosong");
        }else{
            valid=true;
        }
        return valid;
    }

    private void hitungTotal(){
        int jumlahBaris = tbl_retur.getRowCount();
        int total = 0;
        int harga, jumlah;
        for(int i=0; i<jumlahBaris; i++){
            jumlah = Integer.parseInt(tabelModelReturDetil.getValueAt(i, 1).toString());
            harga = Integer.parseInt(tabelModelReturDetil.getValueAt(i, 2).toString());
            total = total + (jumlah * harga);
        }
        txt_totalretur.setText(String.valueOf(total));
    }
    
    private void hitungKurang(){
        int total1=Integer.valueOf(txt_totalbeli.getText());
        int total2=Integer.valueOf(txt_totalretur.getText());
        int total = total1 - total2;
        txt_total.setText(String.valueOf(total));
    }
    
    private String validasiStatus(){
        String status = "";
        for(int i=0; i<tbl_retur.getRowCount(); i++){
            status=(String) tbl_retur.getValueAt(i, 3);
        }
        return status;
    }
    
    private int validasiJumlah(){
        int jumlah=0;
        for(int i=0; i<tbl_retur.getRowCount(); i++){
            jumlah=(Integer) tbl_retur.getValueAt(i, 1);
        }
        return  jumlah;
    }
    
    private void cetak(){
        try {
            List<ReportRetur> rReportReturs = returDao.getrReportReturs(txt_id.getText());
            HashMap map=new HashMap();
            map.put("logo", image);
            JasperPrint jprint=JasperFillManager.fillReport(this.getClass().getClassLoader().
                    getResourceAsStream("com/atk/laporan/ReturRpt.jasper"),map, new JRBeanCollectionDataSource(rReportReturs));
            JRViewer jrv=new JRViewer(jprint);
            TampilReport report=new TampilReport("Laporan Retur", jrv);
            tampilanAwal();
        } catch (JRException ex) {
            Logger.getLogger(ReturView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        txt_total = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_tglretur = new com.toedter.calendar.JDateChooser();
        txt_nopem = new javax.swing.JTextField();
        txt_tglbeli = new com.toedter.calendar.JDateChooser();
        btn_search = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_supplier = new javax.swing.JTextField();
        txt_totalbeli = new javax.swing.JTextField();
        txt_totalretur = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_cari = new javax.swing.JButton();
        btn_rubah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_retur = new com.stripbandunk.jwidget.JDynamicTable();
        jProgressBar = new javax.swing.JProgressBar();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Status-", "Rusak" }));

        txt_total.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setTitle("Transaksi Retur Pembelian");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Id Retur :");

        jLabel2.setText("Tanggal Retur :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("No Pembelian :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tanggal Beli :");

        txt_id.setEnabled(false);

        txt_nopem.setEnabled(false);

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/document_new.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Supplier :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Total Beli :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Total Retur :");

        txt_supplier.setEnabled(false);

        txt_totalbeli.setEnabled(false);

        txt_totalretur.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_tambah))
                            .addComponent(txt_tglretur, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tglbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_nopem, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_totalbeli, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_totalretur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                .addGap(195, 195, 195))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tambah))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txt_tglretur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(txt_tglbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_totalbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(txt_totalretur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nopem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_cari.setText("Cari Barang");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cari);

        btn_rubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/update.png"))); // NOI18N
        btn_rubah.setText("Rubah Jumlah");
        btn_rubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rubahActionPerformed(evt);
            }
        });
        jPanel2.add(btn_rubah);

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/save.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel2.add(btn_simpan);

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/refresh.png"))); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        jPanel2.add(btn_reset);

        jScrollPane1.setViewportView(tbl_retur);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        String setId = returDao.setId();
        txt_id.setText(setId);
        btn_search.setEnabled(true);
        btn_tambah.setEnabled(false);
        txt_tglretur.setDate(new Date());
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        if(txt_tglbeli.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Beli Masih Kosong");
        }else{
            List<TambahBarang> tambahBarangs = tambahDao.getTambahBarangs(txt_tglbeli.getDate());
            if(!tambahBarangs.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(tambahBarangs, TambahBarang.class);
                PencarianDialog dialog=new PencarianDialog();
                dialog.setTableModel(tableModel);
                dialog.setTitle("Search Data Tambah Barang");
                dialog.loadLokasi();
                String ambilData = dialog.ambilData();
                if(!ambilData.equals("")){
                    tb=tambahDao.getTambahBarang(ambilData);
                    if(tb!=null){
                        txt_tglbeli.setDate(tb.getTanggal());
                        txt_supplier.setText(tb.getSupplier().getNama());
                        txt_totalbeli.setText(String.valueOf(tb.getTotal()));
                        txt_nopem.setText(tb.getNo_tambah());
                        
                        
                        btn_cari.setEnabled(true);
                        btn_simpan.setEnabled(true);
                        btn_rubah.setEnabled(true);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data Pembelian Pada Tanggal "+txt_tglbeli.getDate()+ " Tidak ada");
            }
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        boolean inputan=false;
        PencarianBeli pb=new PencarianBeli();
        pb.loadCariPembelian();
        pb.getTxt_cari().setText(txt_nopem.getText());
        List<com.atk.model.CariPembelian> ambilData = pb.ambilData();
        if(!ambilData.isEmpty()){
            for(com.atk.model.CariPembelian cp:ambilData){
                ReturDetil rd=new ReturDetil();
                rd.setBarang(cp.getBarang());
                rd.setJumlah(0);
                rd.setHarga(cp.getHarga());
                rd.setStatus("-Status-");
                
                for(int i=0; i<tbl_retur.getRowCount(); i++){
                    ReturDetil get = tabelModelReturDetil.get(tbl_retur.convertRowIndexToModel(i));
                    if(get.getBarang().getNama().equals(rd.getBarang().getNama())){
                        inputan=true;
                    }
                }
                
                if(inputan==false){
                    tabelModelReturDetil.add(rd);
                    hitungTotal();
                }else{
                    JOptionPane.showMessageDialog(null, "Maaf Data Barang Sudah Ada");
                }
            }
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        tampilanAwal();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(validasi()){
            if("-Status-".equals(validasiStatus())){
                JOptionPane.showMessageDialog(null, "Status Belum Dirubah");
            }else if(validasiJumlah()==0){
                JOptionPane.showMessageDialog(null, "Jumlah masih ada yang 0");
            }else{
                jProgressBar.setIndeterminate(true);
                Retur rRetur = getrRetur();
                TambahBarang tambahBarang = getTambahBarang();
                boolean simpan = returDao.simpan(rRetur);
                boolean rubah = tambahDao.rubah(tambahBarang);
                if(simpan && rubah){
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                    cetak();
                }
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_rubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rubahActionPerformed
        // TODO add your handling code here:
        int index=tbl_retur.getSelectedRow();
        if(index!=-1){
            ReturDetil get = tabelModelReturDetil.get(tbl_retur.convertRowIndexToModel(index));
            ReturJumlah rj=new ReturJumlah();
            ReturDetil rubahRetur = rj.rubahRetur(get);
            if(rubahRetur!=null){
                tabelModelReturDetil.set(index, get);
                hitungTotal();
                hitungKurang();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleksi salah satu baris tabel");
        }
    }//GEN-LAST:event_btn_rubahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_rubah;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private com.stripbandunk.jwidget.JDynamicTable tbl_retur;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nopem;
    private javax.swing.JTextField txt_supplier;
    private com.toedter.calendar.JDateChooser txt_tglbeli;
    private com.toedter.calendar.JDateChooser txt_tglretur;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalbeli;
    private javax.swing.JTextField txt_totalretur;
    // End of variables declaration//GEN-END:variables
}
