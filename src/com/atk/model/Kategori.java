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
public class Kategori {
    @TableColumn(name = "Kode Kategori", number = 1, size = 15)
    private String id_kategori;
    @TableColumn(name = "Kategori", number = 2, size = 30)
    private String nama;

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return nama;
    }
    
    
}
