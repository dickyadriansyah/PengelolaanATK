/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.model;

import com.stripbandunk.jwidget.annotation.TableColumn;

/**
 *
 * @author dicky-java
 */
public class TambahBarangDetil {
    private TambahBarang tambahBarang;
    @TableColumn(name = "Barang", number = 1, size = 30)
    private Barang barang;
    @TableColumn(name = "Qty", number = 2, size = 5)
    private int jumlah;
    @TableColumn(name = "Harga", number = 3, size = 15)
    private int harga;
    private int modeUpdate;

    public TambahBarang getTambahBarang() {
        return tambahBarang;
    }

    public void setTambahBarang(TambahBarang tambahBarang) {
        this.tambahBarang = tambahBarang;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getModeUpdate() {
        return modeUpdate;
    }

    public void setModeUpdate(int modeUpdate) {
        this.modeUpdate = modeUpdate;
    }
    
    

    @Override
    public String toString() {
        return "TambahBarangDetil{" + "tambahBarang=" + tambahBarang + ", barang=" + barang + ", jumlah=" + jumlah + ", harga=" + harga + '}';
    }
    
    
}
