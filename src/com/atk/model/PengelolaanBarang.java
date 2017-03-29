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
public class PengelolaanBarang {
    @TableColumn(name = "No Pengelolaan", number = 1, size = 17)
    private String no_pengelolaan;
    @TableColumn(name = "Tanggal", number = 2, size = 15)
    private Date tanggal;
    @TableColumn(name = "Karyawan", number = 3, size = 30)
    private Karyawan karyawan;
    @TableColumn(name = "Total", number = 4, size = 15)
    private int total;
    private List<PengelolaanBarangDetil> pengelolaanBarangDetil=new ArrayList<>();

    public String getNo_pengelolaan() {
        return no_pengelolaan;
    }

    public void setNo_pengelolaan(String no_pengelolaan) {
        this.no_pengelolaan = no_pengelolaan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PengelolaanBarangDetil> getPengelolaanBarangDetil() {
        return pengelolaanBarangDetil;
    }

    public void setPengelolaanBarangDetil(List<PengelolaanBarangDetil> pengelolaanBarangDetil) {
        this.pengelolaanBarangDetil = pengelolaanBarangDetil;
    }

    @Override
    public String toString() {
        return no_pengelolaan;
    }

     
    
}
