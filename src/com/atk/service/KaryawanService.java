/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Karyawan;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface KaryawanService {
    String setId();
    boolean simpan(Karyawan k);
    boolean rubah(Karyawan k);
    boolean hapus(Karyawan k);
    Karyawan getKaryawan(String id);
    List<Karyawan> getKaryawans();
}
