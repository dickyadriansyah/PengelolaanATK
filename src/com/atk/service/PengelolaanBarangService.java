/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Karyawan;
import com.atk.model.PengelolaanBarang;
import com.atk.model.PengelolaanBarangDetil;
import com.atk.model.ReportPengelolaanBarang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface PengelolaanBarangService {
    String setId();
    boolean simpan(PengelolaanBarang pb);
    boolean rubah(PengelolaanBarang pb);
    boolean hapus(PengelolaanBarang pb);
    boolean hapusbarang(PengelolaanBarangDetil pbd);
    PengelolaanBarang getPengelolaanBarang(String id);
    List<PengelolaanBarang> getPengelolaanBarangs();
    List<PengelolaanBarang> getPengelolaanBarangs(Date tanggal);
    List<ReportPengelolaanBarang> getPengelolaanBarangs(Date tglAwal, Date tglAkhir);
    List<ReportPengelolaanBarang> getPengelolaanBarangs(Date tglAwal, Date tglAkhir, Karyawan karyawan);
    List<PengelolaanBarangDetil> getPengelolaanBarangDetils(String idpengelolaan);
}
