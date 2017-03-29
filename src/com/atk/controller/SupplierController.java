/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.controller;

import com.atk.dao.SupplierDao;
import com.atk.dialog.PencarianDialog;
import com.atk.model.Supplier;
import com.atk.view.SupplierView;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dicky-java
 */
public class SupplierController {
    private SupplierDao dao;
    private Supplier supplier;

    public SupplierController(SupplierView view) {
        dao=new SupplierDao();
        tampilanAwal(view);
    }
    
    private boolean validasi(SupplierView view){
        boolean valid=false;
        if(view.getTxt_kode().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kode supplier tidak boleh kosong");
        }else if(view.getTxt_nama().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nama supplier tidak boleh kosong");
        }else if(view.getTxt_telepon().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "No telepon tidak boleh kosong");
        }else if(view.getTxt_alamat().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Alamat tidak boleh kosong");
        }else{
            valid=true;
        }
        return valid;
    }
    
    public void resetForm(SupplierView view){
        view.getTxt_kode().setText("");
        view.getTxt_nama().setText("");
        view.getTxt_telepon().setText("");
        view.getTxt_alamat().setText("");
        tampilanAwal(view);
    }
    
    private void tampilanAwal(SupplierView view){
        view.getBtn_simpan().setEnabled(false);
        view.getBtn_rubah().setEnabled(false);
        view.getBtn_hapus().setEnabled(false);
        view.getTxt_nama().setEnabled(false);
        view.getTxt_telepon().setEnabled(false);
        view.getTxt_alamat().setEnabled(false);
        
        view.getBtn_tambah().setEnabled(true);
        view.getjProgressBar1().setIndeterminate(false);
    }
    
    public Supplier getSupplier(SupplierView view){
        Supplier s=new Supplier();
            String kode=view.getTxt_kode().getText();
            String nama=view.getTxt_nama().getText();
            String telepon=view.getTxt_telepon().getText();
            String alamat=view.getTxt_alamat().getText();
            
            s.setId_supplier(kode);
            s.setNama(nama);
            s.setTelepon(telepon);
            s.setAlamat(alamat);
        return s;
    }
    
    public void tambah(SupplierView view){
        String setId = dao.setId();
        view.getTxt_kode().setText(setId);
        view.getTxt_nama().setEnabled(true);
        view.getTxt_telepon().setEnabled(true);
        view.getTxt_alamat().setEnabled(true);
        
        view.getBtn_simpan().setEnabled(true);
        view.getBtn_tambah().setEnabled(false);
    }
    
    public void simpan(SupplierView view){
        if(validasi(view)){
            view.getjProgressBar1().setIndeterminate(true);
            supplier = getSupplier(view);
            if(dao.simpan(supplier)){
                JOptionPane.showMessageDialog(view, "Data berhasil disimpan");
                resetForm(view);
                tampilanAwal(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal disimpan");
                resetForm(view);
                tampilanAwal(view);
            }
        }
    }
    
    
    public void rubah(SupplierView view){
        if(validasi(view)){
            view.getjProgressBar1().setIndeterminate(true);
            supplier = getSupplier(view);
            if(dao.rubah(supplier)){
                JOptionPane.showMessageDialog(view, "Data Berhasil Dirubah");
                resetForm(view);
                tampilanAwal(view);
            }else{
                JOptionPane.showMessageDialog(view, "Data Gagal Dirubah");
                resetForm(view);
                tampilanAwal(view);
            }
        }
    }
    
    public void hapus(SupplierView view){
            if(JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus data ini ?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==
                    JOptionPane.OK_OPTION){
                if(supplier!=null){
                    view.getjProgressBar1().setIndeterminate(true);
                    if(dao.hapus(supplier)){
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
    
    public void cariSupplier(SupplierView view){
        List<Supplier> sSuppliers = dao.getsSuppliers();
        if(!sSuppliers.isEmpty()){
            DynamicTableModel tableModel=new DynamicTableModel(sSuppliers, Supplier.class);
            PencarianDialog dialog=new PencarianDialog();
            dialog.setTitle("Search Suppliers");
            dialog.setTableModel(tableModel);
            dialog.loadLokasi();
            String ambilData = dialog.ambilData();
            if(!ambilData.equals("")){
                supplier=dao.getSupplier(ambilData);
                if(supplier!=null){
                    view.getTxt_kode().setText(supplier.getId_supplier());
                    view.getTxt_nama().setText(supplier.getNama());
                    view.getTxt_telepon().setText(supplier.getTelepon());
                    view.getTxt_alamat().setText(supplier.getAlamat());
                    view.getTxt_nama().setEnabled(true);
                    view.getTxt_telepon().setEnabled(true);
                    view.getTxt_alamat().setEnabled(true);
                    
                    view.getBtn_tambah().setEnabled(false);
                    view.getBtn_simpan().setEnabled(false);
                    view.getBtn_rubah().setEnabled(true);
                    view.getBtn_hapus().setEnabled(true);
                }
            }
        }else{
            JOptionPane.showMessageDialog(view, "Data Supplier Masih Kosong");
        }
    }
    
}
