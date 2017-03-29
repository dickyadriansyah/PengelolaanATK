/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.controller;

import com.atk.dao.KategoriDao;
import com.atk.dialog.PencarianDialog;
import com.atk.model.Kategori;
import com.atk.view.KategoriView;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dicky-java
 */
public class KategoriController {
    private final KategoriDao kategoriDao;
    private Kategori kategori;

    public KategoriController(KategoriView kategoriView) {
        kategoriDao=new KategoriDao();
        tampilanAwal(kategoriView);
    }
    
    private boolean validasiInput(KategoriView view){
        boolean vaidasi=false;
        
        if(view.getTxt_kode().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kode Kategori Masih Kosong !");
        }else if(view.getTxt_nama().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nama Kategori Masih Kosong !");
        }else{
            vaidasi=true;
        }
        return vaidasi;
    }
    
    public void resetForm(KategoriView view){
        view.getTxt_kode().setText("");
        view.getTxt_nama().setText("");
        tampilanAwal(view);
    }
    
    private void tampilanAwal(KategoriView view){
        view.getBtn_simpan().setEnabled(false);
        view.getBtn_rubah().setEnabled(false);
        view.getBtn_hapus().setEnabled(false);
        view.getTxt_nama().setEnabled(false);
        
        view.getBtn_tambah().setEnabled(true);
        view.getjProgressBar1().setIndeterminate(false);
    }
    
    public Kategori getKategori(KategoriView view){
        Kategori k=new Kategori();
            String kode=view.getTxt_kode().getText();
            String nama=view.getTxt_nama().getText();
            
            k.setId_kategori(kode);
            k.setNama(nama);
        return k;
    }
    
    public void tambah(KategoriView view){
        String setId = kategoriDao.setId();
        view.getTxt_kode().setText(setId);
        view.getTxt_nama().setEnabled(true);
        
        view.getBtn_simpan().setEnabled(true);
        view.getBtn_tambah().setEnabled(false);
    }
    
    public void simpan(KategoriView view){
        if(validasiInput(view)){
            view.getjProgressBar1().setIndeterminate(true);
            kategori = getKategori(view);
            if(kategoriDao.simpan(kategori)){
                JOptionPane.showMessageDialog(view, "Data Kategori Berhasil Ditambahkan");
                resetForm(view);
                tampilanAwal(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data Kategori Gagal Ditambahkan");
                resetForm(view);
                tampilanAwal(view);
            }
        }
    }
    
    public void rubah(KategoriView view){
         if(validasiInput(view)){
            view.getjProgressBar1().setIndeterminate(true);
            kategori = getKategori(view);
            if(kategoriDao.rubah(kategori)){
                JOptionPane.showMessageDialog(view, "Data Kategori Berhasil Dirubah");
                resetForm(view);
                tampilanAwal(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data Kategori Gagal Dirubah");
                resetForm(view);
                tampilanAwal(view);
            }
        }
    }
    
    public void hapus(KategoriView view){
            if(JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus data ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                    JOptionPane.OK_OPTION){
                if(kategori!=null){
                    view.getjProgressBar1().setIndeterminate(true);
                    if(kategoriDao.hapus(kategori)){
                        JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
                        resetForm(view);
                        tampilanAwal(view);
                    }else{
                        JOptionPane.showMessageDialog(view, "Data gagal dihapus");
                        resetForm(view);
                        tampilanAwal(view);
                    }
                }
            }
    }
    
    public void cariKategori(KategoriView view){
        List<Kategori> kategoris = kategoriDao.getKategoris();
        if(!kategoris.isEmpty()){
            DynamicTableModel tableModel=new DynamicTableModel(kategoris, Kategori.class);
            PencarianDialog dialog=new PencarianDialog();
            dialog.setTitle("Search Kategori");
            dialog.setTableModel(tableModel);
            dialog.loadLokasi();
            String ambilData = dialog.ambilData();
            if(!ambilData.equals("")){
                kategori=kategoriDao.getKategori(ambilData);
                if(kategori!=null){
                    view.getTxt_kode().setText(kategori.getId_kategori());
                    view.getTxt_nama().setText(kategori.getNama());
                    view.getTxt_nama().setEnabled(true);
                    
                    view.getBtn_tambah().setEnabled(false);
                    view.getBtn_simpan().setEnabled(false);
                    view.getBtn_rubah().setEnabled(true);
                    view.getBtn_hapus().setEnabled(true);
                }
            }
        }else{
            JOptionPane.showMessageDialog(view, "Data Kategori Masih Kosong");
        }
        
    }
    
}
