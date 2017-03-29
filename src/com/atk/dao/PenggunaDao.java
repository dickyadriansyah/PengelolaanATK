/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Pengguna;
import com.atk.service.PenggunaService;
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
public class PenggunaDao implements PenggunaService{
    
    private Connection koneksi;

    public PenggunaDao() {
        koneksi=DBkoneksi.getConnection();
    }
    
    

    @Override
    public boolean simpan(Pengguna p) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into pengguna (id_pengguna, username, password) values (?,?,?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, p.getId_pengguna());
            statement.setString(2, p.getUsername());
            statement.setString(3, p.getPassword());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(Pengguna p) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from pengguna where id_pengguna=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, p.getId_pengguna());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Pengguna getpPengguna(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        Pengguna p=null;
        String sql = "select * from pengguna where id_pengguna=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                p=new Pengguna();
                p.setId_pengguna(rs.getString("id_pengguna"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Pengguna> getPenggunas() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from pengguna";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Pengguna p=new Pengguna();
                p.setId_pengguna(rs.getString("id_pengguna"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Pengguna login(String username, String password) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        Pengguna p=null;
        String sql="select * from pengguna where username=? and password=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            rs=statement.executeQuery();
            while(rs.next()){
                p=new Pengguna();
                p.setId_pengguna(rs.getString("id_pengguna"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
            }
            return p;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean rubahPassword(String usernamelama, String passwordlama, String usernamebaru, String passwordbaru) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update pengguna set username=?, password=? where username=? and password=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, usernamebaru);
            statement.setString(2, passwordbaru);
            statement.setString(3, usernamelama);
            statement.setString(4, passwordlama);
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public String setId() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        String kode=null;
        String s, s1;
        Integer j;
        Integer panjang = 5;
        String sql="select max(right(id_pengguna, 5)) from pengguna";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="P/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="P/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenggunaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
