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
public class Karyawan {
    @TableColumn(name = "NIK", number = 1, size = 6)
    private String nik;
    @TableColumn(name = "Nama", number = 2, size = 35)
    private String nama;
    @TableColumn(name = "Jenis Kelamin", number = 3, size = 25)
    private String jenis_kelamin;
    @TableColumn(name = "Telepon", number = 4, size = 15)
    private String telepon;
    @TableColumn(name = "Alamat", number = 5, size = 40)
    private String alamat;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
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
