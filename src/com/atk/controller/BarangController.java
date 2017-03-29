/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.controller;

import com.atk.MenuUtama;
import com.atk.dao.BarangDao;
import com.atk.dao.KategoriDao;
import com.atk.dialog.PencarianDialog;
import com.atk.model.Barang;
import com.atk.model.Kategori;
import com.atk.service.BarangService;
import com.atk.service.KategoriService;
import com.atk.view.BarangView;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dicky-java
 */
public class BarangController {
    
    private MenuUtama mu;
    private BarangService barangdao;
    private KategoriService kategoridao;
    private Barang barang;
    private Kategori kategori;

    public BarangController(BarangView barangView) {
        barangdao=new BarangDao();
        kategoridao=new KategoriDao();
        mu=new MenuUtama();
        tampilanAwal(barangView);
    }
    
    public boolean validasi(BarangView view){
        boolean valid=false;
        if(view.getTxt_nama().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nama barang tidak boleh kosong");
        }else if(view.getTxt_kodekate().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kategori Belum dipilih");
        }else if(view.getTxt_jumlah().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Jumlah tidak boleh kosong");
        }else if(view.getTxt_harga().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Harga tidak boleh kosong");
        }else{
            valid=true;
        }
        return valid;
    }
    
    private void pemberitahuan(BarangView view){
        List<Barang> pesan = barangdao.getPesan();
        if(!pesan.isEmpty()){
            JOptionPane.showMessageDialog(view, "Ada Barang Yang Berkurang Silahkan Cek Di Tombol Pesan");
        }
    }
    
    private void tampilanAwal(BarangView view){
        view.getBtn_simpan().setEnabled(false);
        view.getBtn_rubah().setEnabled(false);
        view.getBtn_hapus().setEnabled(false);
        view.getTxt_nama().setEnabled(false);
        view.getTxt_jumlah().setEnabled(false);
        view.getTxt_harga().setEnabled(false);
        view.getBtn_add().setEnabled(false);
        
        view.getBtn_tambah().setEnabled(true);
        view.getjProgressBar1().setIndeterminate(false);
    }
    
    public void resetForm(BarangView view){
        view.getTxt_kodekate().setText("");
        view.getTxt_kategori().setText("");
        view.getTxt_kode().setText("");
        view.getTxt_nama().setText("");
        view.getTxt_jumlah().setText("");
        view.getTxt_harga().setText("");
        view.getBtn_tambah().setEnabled(true);
        view.getBtn_hapus().setEnabled(false);
        view.getBtn_simpan().setEnabled(false);
        view.getBtn_rubah().setEnabled(false);
        tampilanAwal(view);
    }
    
    public Barang getBarang(BarangView view){
        Barang b=new Barang();
        kategori=new Kategori();
            String kode=view.getTxt_kode().getText();
            String nama=view.getTxt_nama().getText();
            int jumlah=Integer.valueOf(view.getTxt_jumlah().getText());
            int harga=Integer.valueOf(view.getTxt_harga().getText());
            String idkategori=view.getTxt_kodekate().getText();
            
            b.setId_barang(kode);
            b.setNama(nama);
            kategori.setId_kategori(idkategori);
            b.setKategori(kategori);
            b.setJumlah(jumlah);
            b.setHarga(harga);
            return b;
    }
    
    public void tambah(BarangView view){
        String setId = barangdao.setId();
        view.getTxt_kode().setText(setId);
        view.getTxt_nama().setEnabled(true);
        view.getTxt_jumlah().setEnabled(true);
        view.getTxt_harga().setEnabled(true);
        
        view.getBtn_add().setEnabled(true);
        view.getBtn_simpan().setEnabled(true);
        view.getBtn_tambah().setEnabled(false);
    }
    
    public void simpan(BarangView view){
        if(validasi(view)){
            barang = getBarang(view);
            view.getjProgressBar1().setIndeterminate(true);
            if(barangdao.simpan(barang)){
                JOptionPane.showMessageDialog(view, "Data berhasil disimpan");
                resetForm(view);
                tampilanAwal(view);
                view.getjProgressBar1().setIndeterminate(false);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal disimpan");
                resetForm(view);
                tampilanAwal(view);
                view.getjProgressBar1().setIndeterminate(false);
            }
        }
    }
    
    public void rubah(BarangView view){
        if(validasi(view)){
            view.getjProgressBar1().setIndeterminate(true);
            barang = getBarang(view);
            if(barangdao.rubah(barang)){
                JOptionPane.showMessageDialog(view, "Data berhasil dirubah");
                resetForm(view);
                tampilanAwal(view);
                view.getjProgressBar1().setIndeterminate(false);
                mu.setEnable();
                pemberitahuan(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal dirubah");
                resetForm(view);
                tampilanAwal(view);
                view.getjProgressBar1().setIndeterminate(false);
                mu.setEnable();
            }
        }
    }
    
    public void hapus(BarangView view){
        if(JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus data ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                JOptionPane.OK_OPTION){
            if(barang!=null){
                view.getjProgressBar1().setIndeterminate(true);
                boolean hapus = barangdao.hapus(barang);
                if(hapus){
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
                    resetForm(view);
                    tampilanAwal(view);
                    view.getjProgressBar1().setIndeterminate(false);
                }else{
                    JOptionPane.showMessageDialog(view, "Data gagal dihapus");
                    resetForm(view);
                    tampilanAwal(view);
                    view.getjProgressBar1().setIndeterminate(false);
                }
            }
        }
    }
    
    public void cariKategori(BarangView view){
        List<Kategori> kategoris = kategoridao.getKategoris();
        if(!kategoris.isEmpty()){
            DynamicTableModel tableModel=new DynamicTableModel(kategoris, Kategori.class);
            PencarianDialog dialog=new PencarianDialog();
            dialog.setTitle("Search Kategori");
            dialog.setTableModel(tableModel);
            dialog.loadLokasi();
            String ambilData = dialog.ambilData();
            if(!ambilData.equals("")){
                kategori=kategoridao.getKategori(ambilData);
                if(kategori!=null){
                    view.getTxt_kodekate().setText(kategori.getId_kategori());
                    view.getTxt_kategori().setText(kategori.getNama());
                }
            }
        }else{
            JOptionPane.showMessageDialog(view, "Kategori Masih Kosong");
        }
    }
    
    public void cariBarang(BarangView view){
        List<Barang> bBarangs = barangdao.getbBarangs();
        if(!bBarangs.isEmpty()){
            DynamicTableModel tableModel=new DynamicTableModel(bBarangs, Barang.class);
            PencarianDialog dialog=new PencarianDialog();
            dialog.setTitle("Search Barang");
            dialog.setTableModel(tableModel);
            dialog.loadLokasi();
            String ambilData = dialog.ambilData();
            if(!ambilData.equals("")){
                barang=barangdao.getBarang(ambilData);
                if(barang!=null){
                    view.getTxt_kode().setText(barang.getId_barang());
                    view.getTxt_nama().setText(barang.getNama());
                    view.getTxt_jumlah().setText(String.valueOf(barang.getJumlah()));
                    view.getTxt_harga().setText(String.valueOf(barang.getHarga()));
                    view.getTxt_kodekate().setText(barang.getKategori().getId_kategori());
                    view.getTxt_kategori().setText(barang.getKategori().getNama());
                    view.getTxt_nama().setEnabled(true);
                    view.getTxt_jumlah().setEnabled(true);
                    view.getTxt_harga().setEnabled(true);
                    
                    view.getBtn_tambah().setEnabled(false);
                    view.getBtn_simpan().setEnabled(false);
                    view.getBtn_rubah().setEnabled(true);
                    view.getBtn_hapus().setEnabled(true);
                    view.getBtn_add().setEnabled(true);
                }
            }
        }else{
            JOptionPane.showMessageDialog(view, "Data barang masih kosong");
        }
    }
}
