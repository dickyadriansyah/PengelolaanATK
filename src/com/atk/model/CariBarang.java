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
public class CariBarang {
    @TableColumn(name = "Barang", number = 3, size = 50)
    private Barang barang;
    @TableColumn(name = "Select", number = 1, size = 4)
    private boolean pilih;
    @TableColumn(name = "Qty", number = 2, size = 5)
    private int jumlah;
    @TableColumn(name = "Harga", number = 4, size = 15)
    private int harga;

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public boolean isPilih() {
        return pilih;
    }

    public void setPilih(boolean pilih) {
        this.pilih = pilih;
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
    
    
}
