/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.view;

import com.atk.MenuUtama;
import com.atk.dao.BarangDao;
import com.atk.dao.KaryawanDao;
import com.atk.dao.PengelolaanBarangDao;
import com.atk.dialog.PencarianBarang;
import com.atk.dialog.PencarianBarangUpdate;
import com.atk.dialog.PencarianDialog;
import com.atk.dialog.PengelolaanRubah;
import com.atk.dialog.PengelolaanRubah2;
import com.atk.model.Barang;
import com.atk.model.Karyawan;
import com.atk.model.PengelolaanBarang;
import com.atk.model.PengelolaanBarangDetil;
import com.atk.service.BarangService;
import com.atk.service.KaryawanService;
import com.atk.service.PengelolaanBarangService;
import com.atk.tabelmodel.TabelModelPengelolaanDetil;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dicky-java
 */
public class PengelolaanBarangView extends javax.swing.JInternalFrame {

    private PengelolaanBarangService pengelolaanBarangDao;
    private BarangService barangDao;
    private Karyawan karyawan;
    private KaryawanService karyawanDao;
    private TabelModelPengelolaanDetil tabelModelPengelolaanDetil;
    private List<PengelolaanBarangDetil> list;
    private PengelolaanBarang pb;
    private MenuUtama mu=new MenuUtama();
    
    public PengelolaanBarangView() {
        initComponents();
        pengelolaanBarangDao=new PengelolaanBarangDao();
        barangDao=new BarangDao();
        karyawanDao=new KaryawanDao();
        list=new ArrayList<>();
        tabelModelPengelolaanDetil=new TabelModelPengelolaanDetil(list, PengelolaanBarangDetil.class);
        tbl_pengelolaan.setDynamicModel(tabelModelPengelolaanDetil);
        tampilanAwal();
    }
    
    private List<PengelolaanBarangDetil> getpPengelolaanBarangDetilsHapus(){
        List lst=new ArrayList();
        for(int i=0; i<tbl_pengelolaan.getRowCount(); i++){
            PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(i));
            get.setPengelolaanBarang(pb);
            lst.add(get);
            barangDao.tambahJumlahStok(get.getJumlah(), get.getBarang());
        }
        return lst;
    }
    
    private PengelolaanBarang getPengelolaanBarangHapus(){
        pb=new PengelolaanBarang();
        pb.setNo_pengelolaan(txt_id.getText());
        pb.setTanggal(txt_tanggal.getDate());
        pb.setKaryawan(karyawan);
        pb.setTotal(Integer.valueOf(txt_total.getText()));
        pb.setPengelolaanBarangDetil(getpPengelolaanBarangDetilsHapus());
        return pb;
    }
    
    private List<PengelolaanBarangDetil> getpPengelolaanBarangDetils(){
        List lst=new ArrayList();
        for(int i=0; i<tbl_pengelolaan.getRowCount(); i++){
            PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(i));
            get.setPengelolaanBarang(pb);
            lst.add(get);
            barangDao.kurangJumlahStok(get.getJumlah(), get.getBarang());
        }
        return lst;
    }
    
    private PengelolaanBarang getPengelolaanBarang(){
        pb=new PengelolaanBarang();
        pb.setNo_pengelolaan(txt_id.getText());
        pb.setTanggal(txt_tanggal.getDate());
        pb.setKaryawan(karyawan);
        pb.setTotal(Integer.valueOf(txt_total.getText()));
        pb.setPengelolaanBarangDetil(getpPengelolaanBarangDetils());
        return pb;
    }
    
    private List<PengelolaanBarangDetil> getpPengelolaanBarangDetilsUpdate(){
        List lst=new ArrayList();
        for(int i=0; i<tbl_pengelolaan.getRowCount(); i++){
            PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(i));
            get.setPengelolaanBarang(pb);
            lst.add(get);
        }
        return lst;
    }
    
    private void pemberitahuan(){
        List<Barang> pesan = barangDao.getPesan();
        if(!pesan.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ada Barang Yang Berkurang Silahkan Cek Di Tombol Pesan");
        }
    }
    
    private PengelolaanBarang getPengelolaanBarangUpdate(){
        pb=new PengelolaanBarang();
        pb.setNo_pengelolaan(txt_id.getText());
        pb.setTanggal(txt_tanggal.getDate());
        pb.setKaryawan(karyawan);
        pb.setTotal(Integer.valueOf(txt_total.getText()));
        pb.setPengelolaanBarangDetil(getpPengelolaanBarangDetilsUpdate());
        return pb;
    }

    private void tampilanAwal(){
        btn_search.setEnabled(false);
        btn_caribarang.setEnabled(false);
        btn_simpan.setEnabled(false);
        btn_hapus.setEnabled(false);
        jProgressBar.setEnabled(false);
        txt_tanggal.setDate(null);
        txt_total.setText("");
        tabelModelPengelolaanDetil.clear();
        jProgressBar.setIndeterminate(false);
        btn_hapus1.setEnabled(false);
        
        txt_id.setText("");
        txt_karyawan.setText("");
        btn_tambah.setEnabled(true);
        btn_caridata.setEnabled(true);
        jCheckBox1.setSelected(false);
        btn_rubah.setEnabled(false);
        btn_reset.setEnabled(true);
    }
    
    private boolean validasi(){
        boolean valid=false;
        if(txt_karyawan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Karyawan Tidak Boleh Kosong");
        }else if(tbl_pengelolaan.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data Item Barang Tidak Boleh Kosong");
        }else{
            valid=true;
        }
        return valid;
    }
    
    private void hitungTotal(){
        int jumlahBaris = tbl_pengelolaan.getRowCount();
        int total = 0;
        int harga, jumlah;
        for(int i=0; i<jumlahBaris; i++){
            jumlah = Integer.parseInt(tabelModelPengelolaanDetil.getValueAt(i, 1).toString());
            harga = Integer.parseInt(tabelModelPengelolaanDetil.getValueAt(i, 2).toString());
            total = total + (jumlah * harga);
        }
        txt_total.setText(String.valueOf(total));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_karyawan = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        txt_tanggal = new com.toedter.calendar.JDateChooser();
        btn_tambah = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_caribarang = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus1 = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_caridata = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pengelolaan = new com.stripbandunk.jwidget.JDynamicTable();
        btn_hapus = new javax.swing.JButton();
        btn_rubah = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setTitle("Transaksi Pengambilan Barang");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Id :");

        jLabel2.setText("Karyawan :");

        jLabel3.setText("Tanggal :");

        txt_id.setEnabled(false);

        txt_karyawan.setEnabled(false);

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

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Total :");

        txt_total.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tambah))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search)))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tambah))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_search))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btn_caribarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_caribarang.setText("Cari Barang");
        btn_caribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caribarangActionPerformed(evt);
            }
        });
        jPanel2.add(btn_caribarang);

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/save.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel2.add(btn_simpan);

        btn_hapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/delete_1.png"))); // NOI18N
        btn_hapus1.setText("Hapus All");
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_hapus1);

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/refresh.png"))); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        jPanel2.add(btn_reset);

        btn_caridata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_caridata.setText("Cari Data");
        btn_caridata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caridataActionPerformed(evt);
            }
        });
        jPanel2.add(btn_caridata);

        jScrollPane1.setViewportView(tbl_pengelolaan);

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/delete_1.png"))); // NOI18N
        btn_hapus.setText("Hapus Barang");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_rubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/update.png"))); // NOI18N
        btn_rubah.setText("Rubah Jumlah");
        btn_rubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rubahActionPerformed(evt);
            }
        });

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(btn_rubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btn_hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_rubah)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        List<Karyawan> karyawans = karyawanDao.getKaryawans();
        if(!karyawans.isEmpty()){
            DynamicTableModel tableModel=new DynamicTableModel(karyawans, Karyawan.class);
            PencarianDialog dialog=new PencarianDialog();
            dialog.setTitle("Search Karyawan");
            dialog.setTableModel(tableModel);
            dialog.loadLokasi();
            String ambilData = dialog.ambilData();
            if(!ambilData.equals("")){
                karyawan=karyawanDao.getKaryawan(ambilData);
                if(karyawan!=null){
                    txt_karyawan.setText(karyawan.getNama());
                    
                    btn_caribarang.setEnabled(true);
                    btn_simpan.setEnabled(true);
                    btn_hapus.setEnabled(true);
                    btn_search.setEnabled(false);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Data Karyawan Masih Kosong");
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        String setId = pengelolaanBarangDao.setId();
        txt_id.setText(setId);
        btn_tambah.setEnabled(false);
        btn_search.setEnabled(true);
        txt_tanggal.setDate(new Date());
        btn_caridata.setEnabled(false);
        jCheckBox1.setSelected(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        tampilanAwal();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_caribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caribarangActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()==true){
            boolean inputan=false;
            PencarianBarang pencarianBarang=new PencarianBarang();
            pencarianBarang.loadCariBarang();
            List<com.atk.model.CariBarang> ambilData = pencarianBarang.ambilData();
            if(!ambilData.isEmpty()){
                for(com.atk.model.CariBarang cb: ambilData){
                    PengelolaanBarangDetil detil=new PengelolaanBarangDetil();
                    detil.setBarang(cb.getBarang());
                    detil.setJumlah(cb.getJumlah());
                    detil.setHarga(cb.getHarga());
                
                for(int i=0; i<tbl_pengelolaan.getRowCount(); i++){
                    PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(i));
                    if(get.getBarang().getNama().equals(detil.getBarang().getNama())){
                        inputan=true;
                    }
                }
                
                    if(inputan==false){
                        tabelModelPengelolaanDetil.add(detil);
                        hitungTotal();
                        btn_simpan.setEnabled(true);
                        btn_hapus1.setEnabled(false);
                        btn_hapus.setEnabled(true);
                        btn_rubah.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Maaf Data Barang Sudah Ada");
                    }
                }
            }
        }else{
            boolean inputan=false;
            PencarianBarangUpdate pbu=new PencarianBarangUpdate();
            pbu.loadCariBarang();
            List<com.atk.model.CariBarang> ambilData = pbu.ambilData();
            if(!ambilData.isEmpty()){
                for(com.atk.model.CariBarang cb: ambilData){
                    PengelolaanBarangDetil detil=new PengelolaanBarangDetil();
                    detil.setBarang(cb.getBarang());
                    detil.setJumlah(cb.getJumlah());
                    detil.setHarga(cb.getHarga());
                    detil.setPengelolaanBarang(pb);
                
                for(int i=0; i<tbl_pengelolaan.getRowCount(); i++){
                    PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(i));
                    if(get.getBarang().getNama().equals(detil.getBarang().getNama())){
                        inputan=true;
                    }
                }
                
                    if(inputan==false){
                        tabelModelPengelolaanDetil.add(detil);
                        hitungTotal();
                    }else{
                        JOptionPane.showMessageDialog(null, "Maaf Data Barang Sudah Ada");
                        barangDao.tambahJumlahStok(cb.getJumlah(), cb.getBarang());
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_caribarangActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()==true){
            if(validasi()){
                jProgressBar.setIndeterminate(true);
                PengelolaanBarang pengelolaanBarang = getPengelolaanBarang();
                boolean simpan = pengelolaanBarangDao.simpan(pengelolaanBarang);
                    if(simpan){
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                        tampilanAwal();
                        mu.setEnable();
                        pemberitahuan();
                    }else{
                        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
                        tampilanAwal();
                    }
            }
        }else{
            if(validasi()){
                jProgressBar.setIndeterminate(true);
                PengelolaanBarang pengelolaanBarangUpdate = getPengelolaanBarangUpdate();
                boolean rubah = pengelolaanBarangDao.rubah(pengelolaanBarangUpdate);
                if(rubah){
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                        tampilanAwal();
                        mu.setEnable();
                        pemberitahuan();
                }else{
                        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
                        tampilanAwal();
                }
            }
        }
        
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int index=tbl_pengelolaan.getSelectedRow();
        if(jCheckBox1.isSelected()==true){
            if(index!=-1){
                if(JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                        JOptionPane.OK_OPTION){
                            PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(index));
                            tabelModelPengelolaanDetil.remove(index);
                            hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Silahkan  pilih salah satu baris");
            }
        }else{
            if(index!=-1){
                if(JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                        JOptionPane.OK_OPTION){
                            PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(index));
                            pengelolaanBarangDao.hapusbarang(get);
                            tabelModelPengelolaanDetil.remove(index);
                            barangDao.tambahJumlahStok(get.getJumlah(), get.getBarang());
                            hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Silahkan  pilih salah satu baris");
            }
        }
        
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_caridataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caridataActionPerformed
        // TODO add your handling code here:
        if(txt_tanggal.getDate()==null){
            JOptionPane.showMessageDialog(null, "Silahkan isi tanggal dahulu");
        }else{
            List<PengelolaanBarang> pengelolaanBarangs = pengelolaanBarangDao.getPengelolaanBarangs(txt_tanggal.getDate());
            if(!pengelolaanBarangs.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(pengelolaanBarangs, PengelolaanBarang.class);
                PencarianDialog dialog=new PencarianDialog();
                dialog.setTableModel(tableModel);
                dialog.loadLokasi();
                dialog.setTitle("Search Data Pengelolaan Barang");
                String ambilData = dialog.ambilData();
                if(!ambilData.equals("")){
                    pb=pengelolaanBarangDao.getPengelolaanBarang(ambilData);
                    if(pb!=null){
                        txt_id.setText(pb.getNo_pengelolaan());
                        txt_karyawan.setText(pb.getKaryawan().getNama());
                        txt_tanggal.setDate(pb.getTanggal());
                        txt_total.setText(String.valueOf(pb.getTotal()));
                        
                        for(PengelolaanBarangDetil pbd:pengelolaanBarangDao.getPengelolaanBarangDetils(ambilData)){
                            tabelModelPengelolaanDetil.add(pbd);
                        }
                        
                        btn_tambah.setEnabled(false);
                        btn_search.setEnabled(false);
                        btn_caribarang.setEnabled(false);
                        btn_simpan.setEnabled(true);
                        btn_hapus.setEnabled(true);
                        btn_hapus1.setEnabled(true);
                        btn_rubah.setEnabled(true);
                        jCheckBox1.setSelected(false);
                        btn_caribarang.setEnabled(true);
                        btn_reset.setEnabled(false);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data Pengelolaan Barang pada Tanggal "+ txt_tanggal.getDate() + "Tidak ada");
            }
        }
    }//GEN-LAST:event_btn_caridataActionPerformed

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data pengelolaan ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                JOptionPane.OK_OPTION){
            if(pb!=null){
                jProgressBar.setIndeterminate(true);
                PengelolaanBarang pengelolaanBarangHapus = getPengelolaanBarangHapus();
                boolean hapus = pengelolaanBarangDao.hapus(pengelolaanBarangHapus);
                if(hapus){
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    tampilanAwal();
                    mu.setEnable();
                    pemberitahuan();
                }else{
                    JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
                    tampilanAwal();
                }
            }
        }
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void btn_rubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rubahActionPerformed
        // TODO add your handling code here:
        int index=tbl_pengelolaan.getSelectedRow();
        if(jCheckBox1.isSelected()==true){
            if(index!=-1){
                PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(index));
                PengelolaanRubah pr=new PengelolaanRubah();
                PengelolaanBarangDetil rubahData = pr.rubahData(get);
                if(rubahData!=null){
                    tabelModelPengelolaanDetil.set(index, get);
                    hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleksi salah satu baris tabel");
            }
        }else{
            if(index!=-1){
                PengelolaanBarangDetil get = tabelModelPengelolaanDetil.get(tbl_pengelolaan.convertRowIndexToModel(index));
                PengelolaanRubah2 pr=new PengelolaanRubah2();
                PengelolaanBarangDetil rubahData = pr.rubahData(get);
                if(rubahData!=null){
                    tabelModelPengelolaanDetil.set(index, get);
                    hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleksi salah satu baris tabel");
            }
        }
    }//GEN-LAST:event_btn_rubahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_caribarang;
    private javax.swing.JButton btn_caridata;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_rubah;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private com.stripbandunk.jwidget.JDynamicTable tbl_pengelolaan;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_karyawan;
    private com.toedter.calendar.JDateChooser txt_tanggal;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
