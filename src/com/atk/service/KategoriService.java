/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Kategori;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface KategoriService {
    String setId();
    boolean simpan(Kategori k);
    boolean rubah(Kategori k);
    boolean hapus(Kategori k);
    Kategori getKategori(String id);
    List<Kategori> getKategoris();
}
