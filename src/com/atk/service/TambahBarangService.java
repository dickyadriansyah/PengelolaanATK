/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Barang;
import com.atk.model.ReportTambahBarang;
import com.atk.model.Supplier;
import com.atk.model.TambahBarang;
import com.atk.model.TambahBarangDetil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface TambahBarangService {
    String setId();
    boolean simpan(TambahBarang tb);
    boolean rubah(TambahBarang tb);
    boolean hapus(TambahBarang tb);
    boolean hapusBarang(TambahBarangDetil tbd);
    TambahBarang getTambahBarang(String id);
    List<TambahBarang> getTambahBarangs();
    List<TambahBarang> getTambahBarangs(Date tanggal);
    List<ReportTambahBarang> getReportTambahBarangs(Date tglAwal, Date tglAkhir);
    List<ReportTambahBarang> getReportTambahBarangs(Date tglAwal, Date tglAkhir, Supplier supplier);
    List<TambahBarangDetil> getTambahBarangDetils(String idtambah);
    List<TambahBarangDetil> getTambahBarangDetils();
    void kurangJumlah(int jumlah, Barang barang, TambahBarang tambahBarang);
}
