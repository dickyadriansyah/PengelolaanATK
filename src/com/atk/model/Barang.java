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
public class Barang {
    @TableColumn(name = "Kode Barang", number = 1, size = 15)
    private String id_barang;
    @TableColumn(name = "Barang", number = 2, size = 35)
    private String nama;
    @TableColumn(name = "Kategori", number = 3, size = 25)
    private Kategori kategori;
    @TableColumn(name = "Qty", number = 4, size = 10)
    private int jumlah;
    @TableColumn(name = "Harga", number = 5, size = 15)
    private int harga;

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
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

    @Override
    public String toString() {
        return nama;
    }
    
    
}
