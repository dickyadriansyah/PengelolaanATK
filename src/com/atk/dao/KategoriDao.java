/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Kategori;
import com.atk.service.KategoriService;
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
public class KategoriDao implements KategoriService{

    private final Connection koneksi;

    public KategoriDao() {
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
        String sql="select max(right(id_kategori, 5)) from kategori";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="KT/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="KT/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(Kategori k) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into kategori (id_kategori, nama) values (?,?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, k.getId_kategori());
            statement.setString(2, k.getNama());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean rubah(Kategori k) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update kategori set nama=? where id_kategori=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, k.getNama());
            statement.setString(2, k.getId_kategori());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(Kategori k) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from kategori where id_kategori=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, k.getId_kategori());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Kategori getKategori(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        Kategori k=null;
        String sql = "select * from kategori where id_kategori=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                k=new Kategori();
                k.setId_kategori(rs.getString("id_kategori"));
                k.setNama(rs.getString("nama"));
            }
            return k;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Kategori> getKategoris() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from kategori";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Kategori k=new Kategori();
                k.setId_kategori(rs.getString("id_kategori"));
                k.setNama(rs.getString("nama"));
                list.add(k);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
