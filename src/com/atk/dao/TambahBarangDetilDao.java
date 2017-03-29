/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.model.TambahBarangDetil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dicky-java
 */
public class TambahBarangDetilDao {

    public TambahBarangDetilDao() {
    }
    
    public boolean simpan(Connection koneksi, TambahBarangDetil barangDetil){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "insert into tambah_barang_detil (no_tambah, id_barang, jumlah, harga) values (?,?,?,?)";
        try {
            statement=koneksi.prepareStatement(sql2);
            statement.setString(1, barangDetil.getTambahBarang().getNo_tambah());
            statement.setString(2, barangDetil.getBarang().getId_barang());
            statement.setInt(3, barangDetil.getJumlah());
            statement.setInt(4, barangDetil.getHarga());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            System.out.println("suskes");
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("suskes");
                }
            }
        }
        return valid;
    }
    
    public boolean rubah(Connection koneksi, TambahBarangDetil barangDetil){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "update tambah_barang_detil set no_tambah=?, id_barang=?, jumlah=?, harga=? where no_tambah=? and id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql2);
            statement.setString(1, barangDetil.getTambahBarang().getNo_tambah());
            statement.setString(2, barangDetil.getBarang().getId_barang());
            statement.setInt(3, barangDetil.getJumlah());
            statement.setInt(4, barangDetil.getHarga());
            statement.setString(5, barangDetil.getTambahBarang().getNo_tambah());
            statement.setString(6, barangDetil.getBarang().getId_barang());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            System.out.println("suskes");
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                   System.out.println("suskes");
                }
            }
        }
        return valid;
    }
    
    public boolean hapus(Connection koneksi, TambahBarangDetil barangDetil){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "delete from tambah_barang_detil where no_tambah=? and id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql2);
            statement.setString(1, barangDetil.getTambahBarang().getNo_tambah());
            statement.setString(2, barangDetil.getBarang().getId_barang());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            System.out.println("suskes");
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("suskes");
                }
            }
        }
        return valid;
    }
}
