/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Supplier;
import com.atk.service.SupplierService;
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
public class SupplierDao implements SupplierService{

    private Connection koneksi;

    public SupplierDao() {
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
        String sql="select max(right(id_supplier, 5)) from supplier";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="S/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="S/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(Supplier s) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into supplier (id_supplier, nama, telepon, alamat) values (?,?,?,?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, s.getId_supplier());
            statement.setString(2, s.getNama());
            statement.setString(3, s.getTelepon());
            statement.setString(4, s.getAlamat());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean rubah(Supplier s) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update supplier set nama=?, telepon=?, alamat=? where id_supplier=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, s.getNama());
            statement.setString(2, s.getTelepon());
            statement.setString(3, s.getAlamat());
            statement.setString(4, s.getId_supplier());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(Supplier s) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from supplier where id_supplier=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, s.getId_supplier());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Supplier getSupplier(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        Supplier s=null;
        String sql = "select * from supplier where id_supplier=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                s=new Supplier();
                s.setId_supplier(rs.getString("id_supplier"));
                s.setNama(rs.getString("nama"));
                s.setTelepon(rs.getString("telepon"));
                s.setAlamat(rs.getString("alamat"));
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Supplier> getsSuppliers() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from supplier";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Supplier s=new Supplier();
                s.setId_supplier(rs.getString("id_supplier"));
                s.setNama(rs.getString("nama"));
                s.setTelepon(rs.getString("telepon"));
                s.setAlamat(rs.getString("alamat"));
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
