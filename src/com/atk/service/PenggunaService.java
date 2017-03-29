/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Pengguna;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface PenggunaService {
    String setId();
    boolean simpan(Pengguna p);
    boolean hapus(Pengguna p);
    Pengguna getpPengguna(String id);
    List<Pengguna> getPenggunas();
    Pengguna login(String username, String password);
    boolean rubahPassword(String usernamelama, String passwordlama, String usernamebaru, String passwordbaru);
}
