/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.controller;

import com.atk.dao.KaryawanDao;
import com.atk.dialog.PencarianDialog;
import com.atk.model.Karyawan;
import com.atk.view.KaryawanView;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dicky-java
 */
public class KaryawanController {
    private KaryawanDao karyawanDao;
    private Karyawan karyawan;

    public KaryawanController(KaryawanView view) {
        karyawanDao=new KaryawanDao();
        tampilanAwal(view);
    }
    
    private boolean validasi(KaryawanView view){
        boolean valid=false;
        if(view.getTxt_nik().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nik karyawan masih kosong");
        }else if(view.getTxt_nama().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nama karyawan tidak boleh kosong");
        }else if(view.getTxt_telepon().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "No telepon tidak boleh kosong");
        }else if(view.getCbo_jk().getSelectedIndex()==0){
            JOptionPane.showMessageDialog(view, "Jenis kelamin belum dipilih");
        }else if(view.getTxt_alamat().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Alamat tidak boleh kosong");
        }else{
            valid=true;
        }
        return valid;
    }
    
    public void resetForm(KaryawanView view){
        view.getTxt_nik().setText("");
        view.getTxt_nama().setText("");
        view.getTxt_telepon().setText("");
        view.getTxt_alamat().setText("");
        view.getCbo_jk().setSelectedIndex(0);
        tampilanAwal(view);
    }
    
    private void tampilanAwal(KaryawanView view){
        view.getBtn_simpan().setEnabled(false);
        view.getBtn_rubah().setEnabled(false);
        view.getBtn_hapus().setEnabled(false);
        view.getTxt_nama().setEnabled(false);
        view.getTxt_telepon().setEnabled(false);
        view.getTxt_alamat().setEnabled(false);
        view.getCbo_jk().setEnabled(false);
        
        view.getBtn_tambah().setEnabled(true);
        view.getjProgressBar1().setIndeterminate(false);
    }
    
    public Karyawan getKaryawan(KaryawanView view){
        Karyawan k=new Karyawan();
            String nik=view.getTxt_nik().getText();
            String nama=view.getTxt_nama().getText();
            String telepon = view.getTxt_telepon().getText();
            String alamat = view.getTxt_alamat().getText();
            String jenis_kelamin=(String) view.getCbo_jk().getSelectedItem();
            
            k.setNik(nik);
            k.setNama(nama);
            k.setTelepon(telepon);
            k.setAlamat(alamat);
            k.setJenis_kelamin(jenis_kelamin);
        return k;
    }
    
    
    public void tambah(KaryawanView view){
        String setId = karyawanDao.setId();
        view.getTxt_nik().setText(setId);
        view.getTxt_nama().setEnabled(true);
        view.getTxt_telepon().setEnabled(true);
        view.getTxt_alamat().setEnabled(true);
        view.getCbo_jk().setEnabled(true);
        
        view.getBtn_simpan().setEnabled(true);
        view.getBtn_tambah().setEnabled(false);
    }
    
    public void simpan(KaryawanView view){
        if(validasi(view)){
            view.getjProgressBar1().setIndeterminate(true);
            karyawan = getKaryawan(view);
            if(karyawanDao.simpan(karyawan)){
                JOptionPane.showMessageDialog(view, "Data Berhasil Ditambahkan");
                resetForm(view);
                tampilanAwal(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data Gagal Ditambahkan");
                resetForm(view);
                tampilanAwal(view);
            }
        }
    }
    
    public void rubah(KaryawanView view){
        if(validasi(view)){
            view.getjProgressBar1().setIndeterminate(true);
            karyawan = getKaryawan(view);
            if(karyawanDao.rubah(karyawan)){
                JOptionPane.showMessageDialog(view, "Data Berhasil Dirubah");
                resetForm(view);
                tampilanAwal(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data  Gagal Dirubah");
                resetForm(view);
                tampilanAwal(view);
            }
        }
    }
    
    public void hapus(KaryawanView view){
            if(JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus data ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                    JOptionPane.OK_OPTION){
                if(karyawan!=null){
                    view.getjProgressBar1().setIndeterminate(true);
                    if(karyawanDao.hapus(karyawan)){
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
    
    public void cariKaryawan(KaryawanView view){
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
                    view.getTxt_nik().setText(karyawan.getNik());
                    view.getTxt_nama().setText(karyawan.getNama());
                    view.getCbo_jk().setSelectedItem(karyawan.getJenis_kelamin());
                    view.getTxt_telepon().setText(karyawan.getTelepon());
                    view.getTxt_alamat().setText(karyawan.getAlamat());
                    view.getTxt_nama().setEnabled(true);
                    view.getTxt_telepon().setEnabled(true);
                    view.getTxt_alamat().setEnabled(true);
                    view.getCbo_jk().setEnabled(true);
                    
                    view.getBtn_tambah().setEnabled(false);
                    view.getBtn_simpan().setEnabled(false);
                    view.getBtn_rubah().setEnabled(true);
                    view.getBtn_hapus().setEnabled(true);
                    
                }
            }
        }else{
            JOptionPane.showMessageDialog(view, "Data Karyawan Masih Kosong");
        }
    }
}
