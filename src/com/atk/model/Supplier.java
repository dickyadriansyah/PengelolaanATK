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
public class Supplier {
    @TableColumn(name = "Kode Supplier", number = 1, size = 12)
    private String id_supplier;
    @TableColumn(name = "Nama", number = 2, size = 35)
    private String nama;
    @TableColumn(name = "Telepon", number = 3, size = 20)
    private String telepon;
    @TableColumn(name = "Alamat", number = 4, size = 40)
    private String alamat;

    public String getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(String id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return nama;
    }
    
    
}
