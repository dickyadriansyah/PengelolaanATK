/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.view;

import com.atk.dao.BarangDao;
import com.atk.dao.SupplierDao;
import com.atk.dao.TambahBarangDao;
import com.atk.dialog.PencarianBeliBarang;
import com.atk.dialog.PencarianDialog;
import com.atk.dialog.TambahJumlah;
import com.atk.dialog.TambahJumlah2;
import com.atk.model.Supplier;
import com.atk.model.TambahBarang;
import com.atk.model.TambahBarangDetil;
import com.atk.service.BarangService;
import com.atk.service.SupplierService;
import com.atk.service.TambahBarangService;
import com.atk.tabelmodel.TabelModelTambahBarangDetil;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dicky-java
 */
public class TambahBarangView extends javax.swing.JInternalFrame {

    private TambahBarangService tambahDao;
    private BarangService barangDao;
    private Supplier supplier;
    private SupplierService supplierDao;
    private TabelModelTambahBarangDetil tabelModelTambahBarangDetil;
    private List<TambahBarangDetil> list;
    private TambahBarang tb;
    
    public TambahBarangView() {
        initComponents();
        tambahDao=new TambahBarangDao();
        barangDao=new BarangDao();
        supplierDao=new SupplierDao();
        list=new ArrayList<>();
        tabelModelTambahBarangDetil=new TabelModelTambahBarangDetil(list, TambahBarangDetil.class);
        tbl_tambah.setDynamicModel(tabelModelTambahBarangDetil);
        tampilanAwal();
    }
    
    private boolean validasi(){
        boolean valid=false;
        if(txt_supplier.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Supplier tidak boleh kosong");
        }else if(txt_tanggal.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal tidak boleh kosong");
        }else if(txt_total.getText().trim().isEmpty() || txt_total.equals("0")){
            JOptionPane.showMessageDialog(null, "Total tidak boleh kosong");
        }else if(tbl_tambah.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data Item Barang Belum Ada Yang dimasukkan");
        }else{
            valid=true;
        }
        return valid;
    }
    
    private List<TambahBarangDetil> getTambahBarangDetils(){
        List lst=new ArrayList();
        for(int i=0; i<tbl_tambah.getRowCount(); i++){
            TambahBarangDetil get = tabelModelTambahBarangDetil.get(tbl_tambah.convertRowIndexToModel(i));
            get.setTambahBarang(tb);
            lst.add(get);
            barangDao.tambahJumlahStok(get.getJumlah(), get.getBarang());
        }
        return lst;
    }
    
    private TambahBarang gettTambahBarang(){
        tb=new TambahBarang();
        tb.setNo_tambah(txt_id.getText());
        tb.setSupplier(supplier);
        tb.setTanggal(txt_tanggal.getDate());
        tb.setTotal(Integer.valueOf(txt_total.getText()));
        tb.setTambahbarangdetil(getTambahBarangDetils());
        return tb;
    }
    
    private List<TambahBarangDetil> getTambahBarangDetilsUpdate(){
        List lst=new ArrayList();
        for(int i=0; i<tbl_tambah.getRowCount(); i++){
            TambahBarangDetil get = tabelModelTambahBarangDetil.get(tbl_tambah.convertRowIndexToModel(i));
            get.setTambahBarang(tb);
            lst.add(get);
        }
        return lst;
    }
    
    private TambahBarang gettTambahBarangUpdate(){
        tb=new TambahBarang();
        tb.setNo_tambah(txt_id.getText());
        tb.setTanggal(txt_tanggal.getDate());
        tb.setTotal(Integer.valueOf(txt_total.getText()));
        tb.setTambahbarangdetil(getTambahBarangDetilsUpdate());
        return tb;
    }

    private void tampilanAwal(){
        btn_cari.setEnabled(false);
        btn_search.setEnabled(false);
        btn_simpan.setEnabled(false);
        btn_hapusbarang.setEnabled(false);
        btn_rubahbarang.setEnabled(false);
        jProgressBar.setIndeterminate(false);
        jCheckBox1.setSelected(false);
        
        txt_id.setText("");
        txt_supplier.setText("");
        txt_tanggal.setDate(null);
        txt_total.setText("");
        
        btn_tambah.setEnabled(true);
        btn_reset.setEnabled(true);
        btn_caridata.setEnabled(true);
        tabelModelTambahBarangDetil.clear();
    }
    
    private int validasiJumlah(){
        int jumlah=0;
        for(int i=0; i<tbl_tambah.getRowCount(); i++){
            jumlah=(Integer) tbl_tambah.getValueAt(i, 1);
        }
        return  jumlah;
    }
   
    private void hitungTotal(){
        int jumlahBaris = tbl_tambah.getRowCount();
        int total = 0;
        int harga, jumlah;
        for(int i=0; i<jumlahBaris; i++){
            jumlah = Integer.parseInt(tabelModelTambahBarangDetil.getValueAt(i, 1).toString());
            harga = Integer.parseInt(tabelModelTambahBarangDetil.getValueAt(i, 2).toString());
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
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_supplier = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        txt_tanggal = new com.toedter.calendar.JDateChooser();
        txt_total = new javax.swing.JTextField();
        jProgressBar = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        btn_cari = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_caridata = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tambah = new com.stripbandunk.jwidget.JDynamicTable();
        btn_hapusbarang = new javax.swing.JButton();
        btn_rubahbarang = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setTitle("Transaksi Tambah Barang");

        jLabel1.setText("No Tambah :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Supplier :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Total :");

        jLabel4.setText("Tanggal :");

        txt_id.setEnabled(false);

        txt_supplier.setEnabled(false);

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/document_new.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tambah))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_tambah))
                    .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_search)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_cari.setText("Cari Barang");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cari);

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

        btn_caridata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/search.png"))); // NOI18N
        btn_caridata.setText("Cari Data");
        btn_caridata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caridataActionPerformed(evt);
            }
        });
        jPanel2.add(btn_caridata);

        jScrollPane1.setViewportView(tbl_tambah);

        btn_hapusbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/delete_1.png"))); // NOI18N
        btn_hapusbarang.setText("Hapus Barang");
        btn_hapusbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusbarangActionPerformed(evt);
            }
        });

        btn_rubahbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/update.png"))); // NOI18N
        btn_rubahbarang.setText("Rubah Barang");
        btn_rubahbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rubahbarangActionPerformed(evt);
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
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_hapusbarang, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(btn_rubahbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btn_hapusbarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_rubahbarang)
                        .addContainerGap(338, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        String setId = tambahDao.setId();
        txt_id.setText(setId);
        txt_tanggal.setDate(new Date());
        btn_search.setEnabled(true);
        btn_tambah.setEnabled(false);
        btn_caridata.setEnabled(false);
        btn_cari.setEnabled(true);
        jCheckBox1.setSelected(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        List<Supplier> sSuppliers = supplierDao.getsSuppliers();
        if(!sSuppliers.isEmpty()){
            DynamicTableModel tableModel=new DynamicTableModel(sSuppliers, Supplier.class);
            PencarianDialog dialog=new PencarianDialog();
            dialog.setTableModel(tableModel);
            dialog.setTitle("Search SUpplier");
            dialog.loadLokasi();
            String ambilData = dialog.ambilData();
            if(!ambilData.equals("")){
                supplier=supplierDao.getSupplier(ambilData);
                if(supplier!=null){
                    txt_supplier.setText(supplier.getNama());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Data Supplier Masih Kosong");
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        boolean inputan=false;
        PencarianBeliBarang pbb=new PencarianBeliBarang();
        pbb.loadCariBarang();
        List<com.atk.model.BeliBarang> ambilData = pbb.ambilData();
        if(!ambilData.isEmpty()){
            for(com.atk.model.BeliBarang bb: ambilData){
                TambahBarangDetil tbd=new TambahBarangDetil();
                tbd.setBarang(bb.getBarang());
                tbd.setJumlah(0);
                tbd.setHarga(bb.getHarga());
                
                for(int i=0; i<tbl_tambah.getRowCount(); i++){
                    TambahBarangDetil get = tabelModelTambahBarangDetil.get(tbl_tambah.convertRowIndexToModel(i));
                    if(get.getBarang().getNama().equals(tbd.getBarang().getNama())){
                        inputan=true;
                    }
                }
                
                if(inputan==false){
                    tabelModelTambahBarangDetil.add(tbd);
                    btn_simpan.setEnabled(true);
                    btn_rubahbarang.setEnabled(true);
                    btn_hapusbarang.setEnabled(true);
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

    private void btn_caridataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caridataActionPerformed
        // TODO add your handling code here:
        if(txt_tanggal.getDate()==null){
            JOptionPane.showMessageDialog(null, "Silahkan isi tanggal terlebih dahulu");
        }else{
            List<TambahBarang> tambahBarangs = tambahDao.getTambahBarangs(txt_tanggal.getDate());
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
                        txt_id.setText(tb.getNo_tambah());
                        txt_supplier.setText(tb.getSupplier().getNama());
                        txt_tanggal.setDate(tb.getTanggal());
                        txt_total.setText(String.valueOf(tb.getTotal()));
                        
                        for(TambahBarangDetil tbd:tambahDao.getTambahBarangDetils(ambilData)){
                            tabelModelTambahBarangDetil.add(tbd);
                        }
                        
                        btn_tambah.setEnabled(false);
                        btn_search.setEnabled(false);
                        btn_cari.setEnabled(true);
                        btn_simpan.setEnabled(true);
                        btn_hapusbarang.setEnabled(true);
                        btn_rubahbarang.setEnabled(true);
                        btn_reset.setEnabled(false);
                        jCheckBox1.setSelected(false);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Data Tambah Barang Pada Tanggal "+ txt_tanggal.getDate()+ " Tidak ada");
            }
        }
    }//GEN-LAST:event_btn_caridataActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()==true){
            if(validasi()){
                if(validasiJumlah()==0){
                    JOptionPane.showMessageDialog(null, "Jumlah Qty Masih ada yang 0");
                }else{
                    jProgressBar.setIndeterminate(true);
                    TambahBarang tTambahBarang = gettTambahBarang();
                    boolean simpan = tambahDao.simpan(tTambahBarang);
                    if(simpan){
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                        tampilanAwal();
                    }
                }
            }
        }else{
            if(validasi()){
                if(validasiJumlah()==0){
                    JOptionPane.showMessageDialog(null, "Jumlah Qty Masih ada yang 0");
                }else{
                    jProgressBar.setIndeterminate(true);
                    TambahBarang tTambahBarangUpdate = gettTambahBarangUpdate();
                    boolean rubah = tambahDao.rubah(tTambahBarangUpdate);
                    if(rubah){
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                        tampilanAwal();
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusbarangActionPerformed
        // TODO add your handling code here:
        int index=tbl_tambah.getSelectedRow();
        if(jCheckBox1.isSelected()==true){
            if(index!=-1){
                if(JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                        JOptionPane.OK_OPTION){
                    tabelModelTambahBarangDetil.remove(index);
                    hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleksi tabel yang ingin dihapus");
            }
        }else{
            if(index!=-1){
                if(JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                        JOptionPane.OK_OPTION){
                    TambahBarangDetil get = tabelModelTambahBarangDetil.get(tbl_tambah.convertRowIndexToModel(index));
                    tambahDao.hapusBarang(get);
                    tabelModelTambahBarangDetil.remove(index);
                    hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleksi tabel yang ingin dihapus");
            }
        }
    }//GEN-LAST:event_btn_hapusbarangActionPerformed

    private void btn_rubahbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rubahbarangActionPerformed
        // TODO add your handling code here:
        int index=tbl_tambah.getSelectedRow();
        if(jCheckBox1.isSelected()==true){
            if(index!=-1){
                TambahBarangDetil get = tabelModelTambahBarangDetil.get(tbl_tambah.convertRowIndexToModel(index));
                TambahJumlah tj=new TambahJumlah();
                TambahBarangDetil rubahJumlah = tj.rubahJumlah(get);
                if(rubahJumlah!=null){
                    tabelModelTambahBarangDetil.set(index, get);
                    hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleksi salah satu baris tabel");
            }
        }else{
            if(index!=-1){
                TambahBarangDetil get = tabelModelTambahBarangDetil.get(tbl_tambah.convertRowIndexToModel(index));
                TambahJumlah2 tj=new TambahJumlah2();
                TambahBarangDetil rubahJumlah = tj.rubahJumlah(get);
                if(rubahJumlah!=null){
                    tabelModelTambahBarangDetil.set(index, get);
                    hitungTotal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Seleksi salah satu baris tabel");
            }
        }
    }//GEN-LAST:event_btn_rubahbarangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_caridata;
    private javax.swing.JButton btn_hapusbarang;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_rubahbarang;
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
    private com.stripbandunk.jwidget.JDynamicTable tbl_tambah;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_supplier;
    private com.toedter.calendar.JDateChooser txt_tanggal;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
