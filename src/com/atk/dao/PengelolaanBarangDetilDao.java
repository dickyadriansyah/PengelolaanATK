/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.model.PengelolaanBarangDetil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dicky-java
 */
public class PengelolaanBarangDetilDao {

    public PengelolaanBarangDetilDao() {
    }
    
    public boolean simpan(Connection koneksi, PengelolaanBarangDetil pbd){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "insert into pengelolaan_barang_detil (no_pengelolaan, id_barang, jumlah, harga) values (?,?,?,?)";
        try {
            statement=koneksi.prepareStatement(sql2);
            statement.setString(1, pbd.getPengelolaanBarang().getNo_pengelolaan());
            statement.setString(2, pbd.getBarang().getId_barang());
            statement.setInt(3, pbd.getJumlah());
            statement.setInt(4, pbd.getHarga());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            System.out.println("Sukses");
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("Sukses");
                }
            }
        }
        return valid;
    }
    
    public boolean rubah(Connection koneksi, PengelolaanBarangDetil pbd){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "update pengelolaan_barang_detil set no_pengelolaan=?, id_barang=?, jumlah=?, harga=? where no_pengelolaan=? and id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql2);
            statement.setString(1, pbd.getPengelolaanBarang().getNo_pengelolaan());
            statement.setString(2, pbd.getBarang().getId_barang());
            statement.setInt(3, pbd.getJumlah());
            statement.setInt(4, pbd.getHarga());
            statement.setString(5, pbd.getPengelolaanBarang().getNo_pengelolaan());
            statement.setString(6, pbd.getBarang().getId_barang());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            System.out.println("Sukses");
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("Sukses");
                }
            }
        }
        return valid;
    }
    
    
    public boolean hapus(Connection koneksi, PengelolaanBarangDetil pbd){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "delete from pengelolaan_barang_detil where no_pengelolaan=? and id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql2);
            statement.setString(1, pbd.getPengelolaanBarang().getNo_pengelolaan());
            statement.setString(2, pbd.getBarang().getId_barang());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDetilDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDetilDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }
}
