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
public class Retur {
    @TableColumn(name = "ID", number = 1, size = 15)
    private String id_retur;
    @TableColumn(name = "Tanggal Retur", number = 3, size = 18)
    private Date tanggal_retur;
    @TableColumn(name = "Tanggal Beli", number = 4, size = 18)
    private Date tanggal_beli;
    @TableColumn(name = "Total Beli", number = 5, size = 15)
    private int total_beli;
    @TableColumn(name = "Total Retur", number = 6, size = 15)
    private int total_retur;
    private TambahBarang tambahBarang;
    @TableColumn(name = "Supplier", number = 2, size = 30)
    private String supplier;
    private List<ReturDetil> returDetils=new ArrayList<>();

    public String getId_retur() {
        return id_retur;
    }

    public void setId_retur(String id_retur) {
        this.id_retur = id_retur;
    }

    public Date getTanggal_retur() {
        return tanggal_retur;
    }

    public void setTanggal_retur(Date tanggal_retur) {
        this.tanggal_retur = tanggal_retur;
    }

    public Date getTanggal_beli() {
        return tanggal_beli;
    }

    public void setTanggal_beli(Date tanggal_beli) {
        this.tanggal_beli = tanggal_beli;
    }

    public int getTotal_beli() {
        return total_beli;
    }

    public void setTotal_beli(int total_beli) {
        this.total_beli = total_beli;
    }

    public int getTotal_retur() {
        return total_retur;
    }

    public void setTotal_retur(int total_retur) {
        this.total_retur = total_retur;
    }

    public TambahBarang getTambahBarang() {
        return tambahBarang;
    }

    public void setTambahBarang(TambahBarang tambahBarang) {
        this.tambahBarang = tambahBarang;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<ReturDetil> getReturDetils() {
        return returDetils;
    }

    public void setReturDetils(List<ReturDetil> returDetils) {
        this.returDetils = returDetils;
    }

    @Override
    public String toString() {
        return "Retur{" + "id_retur=" + id_retur + ", tanggal_retur=" + tanggal_retur + ", tanggal_beli=" + tanggal_beli + ", total_beli=" + total_beli + ", total_retur=" + total_retur + ", tambahBarang=" + tambahBarang + ", supplier=" + supplier + ", returDetils=" + returDetils + '}';
    }
    
    
}
