/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Karyawan;
import com.atk.service.KaryawanService;
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
public class KaryawanDao implements KaryawanService{

    private Connection koneksi;

    public KaryawanDao() {
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
        String sql="select max(right(nik, 5)) from karyawan";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="NIK/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="NIK/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(Karyawan k) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into karyawan (nik, nama, jenis_kelamin, telepon, alamat) values (?,?,?,?,?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, k.getNik());
            statement.setString(2, k.getNama());
            statement.setString(3, k.getJenis_kelamin());
            statement.setString(4, k.getTelepon());
            statement.setString(5, k.getAlamat());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean rubah(Karyawan k) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update karyawan set nama=?, jenis_kelamin=?, telepon=?, alamat=? where nik=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, k.getNama());
            statement.setString(2, k.getJenis_kelamin());
            statement.setString(3, k.getTelepon());
            statement.setString(4, k.getAlamat());
            statement.setString(5, k.getNik());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(Karyawan k) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from karyawan where nik=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, k.getNik());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Karyawan getKaryawan(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        Karyawan k=null;
        String sql = "select * from karyawan where nik=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                k=new Karyawan();
                k.setNik(rs.getString("nik"));
                k.setNama(rs.getString("nama"));
                k.setJenis_kelamin(rs.getString("jenis_kelamin"));
                k.setTelepon(rs.getString("telepon"));
                k.setAlamat(rs.getString("alamat"));
            }
            return k;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Karyawan> getKaryawans() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from karyawan";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Karyawan k=new Karyawan();
                k.setNik(rs.getString("nik"));
                k.setNama(rs.getString("nama"));
                k.setJenis_kelamin(rs.getString("jenis_kelamin"));
                k.setTelepon(rs.getString("telepon"));
                k.setAlamat(rs.getString("alamat"));
                list.add(k);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
