/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.model;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TambahBarang {
    @TableColumn(name = "ID", number = 1, size = 12)
    private String no_tambah;
    @TableColumn(name = "Supplier", number = 2, size = 30)
    private Supplier supplier;
    @TableColumn(name = "Tanggal", number = 3, size = 20)
    private Date tanggal;
    @TableColumn(name = "Total", number = 4, size = 15)
    private int total;
    private List<TambahBarangDetil> tambahbarangdetil=new ArrayList<>();

    public String getNo_tambah() {
        return no_tambah;
    }

    public void setNo_tambah(String no_tambah) {
        this.no_tambah = no_tambah;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TambahBarangDetil> getTambahbarangdetil() {
        return tambahbarangdetil;
    }

    public void setTambahbarangdetil(List<TambahBarangDetil> tambahbarangdetil) {
        this.tambahbarangdetil = tambahbarangdetil;
    }

    @Override
    public String toString() {
        return "TambahBarang{" + "no_tambah=" + no_tambah + ", supplier=" + supplier + ", tanggal=" + tanggal + ", total=" + total + ", tambahbarangdetil=" + tambahbarangdetil + '}';
    }

    
    
    
}
