/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Barang;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface BarangService {
    String setId();
    boolean simpan(Barang b);
    boolean rubah(Barang b);
    boolean hapus(Barang b);
    Barang getBarang(String id);
    List<Barang> getbBarangs();
    List<Barang> getPesan();
    void kurangJumlahStok(int jumlah, Barang barang);
    void tambahJumlahStok(int jumlah, Barang barang);
}
