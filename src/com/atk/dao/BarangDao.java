/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Barang;
import com.atk.model.Kategori;
import com.atk.service.BarangService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dicky-java
 */
public class BarangDao implements BarangService{

    private Connection koneksi;
    private KategoriDao kategoriDao=new KategoriDao();

    public BarangDao() {
        koneksi=DBkoneksi.getConnection();
    }
    
    
    @Override
    public String setId() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        String kode=null;
        String s, s1;
        Integer j;
        Integer panjang = 5;
        String sql="select max(right(id_barang, 5)) from barang";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="B/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="B/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(Barang b) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into barang (id_barang, nama, id_kategori, jumlah, harga) values (?,?,?,?,?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, b.getId_barang());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getKategori().getId_kategori());
            statement.setInt(4, b.getJumlah());
            statement.setInt(5, b.getHarga());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean rubah(Barang b) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update barang set nama=?, id_kategori=?, jumlah=?, harga=? where id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, b.getNama());
            statement.setString(2, b.getKategori().getId_kategori());
            statement.setInt(3, b.getJumlah());
            statement.setDouble(4, b.getHarga());
            statement.setString(5, b.getId_barang());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(Barang b) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from barang where id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, b.getId_barang());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Barang getBarang(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        Barang b=null;
        String sql = "select * from barang where id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                b=new Barang();
                b.setId_barang(rs.getString("id_barang"));
                b.setNama(rs.getString("nama"));
                String id_kategori=rs.getString("id_kategori");
                Kategori kategori = kategoriDao.getKategori(id_kategori);
                b.setKategori(kategori);
                b.setJumlah(rs.getInt("jumlah"));
                b.setHarga(rs.getInt("harga"));
            }
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Barang> getbBarangs() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from barang";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Barang b=new Barang();
                b.setId_barang(rs.getString("id_barang"));
                b.setNama(rs.getString("nama"));
                String id_kategori=rs.getString("id_kategori");
                Kategori kategori = kategoriDao.getKategori(id_kategori);
                b.setKategori(kategori);
                b.setJumlah(rs.getInt("jumlah"));
                b.setHarga(rs.getInt("harga"));
                list.add(b);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void kurangJumlahStok(int jumlah, Barang barang) {
        PreparedStatement statement=null;
        String sql = "update barang set jumlah=jumlah-? where id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setInt(1, jumlah);
            statement.setString(2, barang.getId_barang());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void tambahJumlahStok(int jumlah, Barang barang) {
        PreparedStatement statement=null;
        String sql = "update barang set jumlah=jumlah+? where id_barang=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setInt(1, jumlah);
            statement.setString(2, barang.getId_barang());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Barang> getPesan() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from barang where jumlah<='5'";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Barang b=new Barang();
                b.setId_barang(rs.getString("id_barang"));
                b.setNama(rs.getString("nama"));
                String id_kategori=rs.getString("id_kategori");
                Kategori kategori = kategoriDao.getKategori(id_kategori);
                b.setKategori(kategori);
                b.setJumlah(rs.getInt("jumlah"));
                b.setHarga(rs.getInt("harga"));
                list.add(b);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
