/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Barang;
import com.atk.model.ReportRetur;
import com.atk.model.Retur;
import com.atk.model.ReturDetil;
import com.atk.model.TambahBarang;
import com.atk.service.BarangService;
import com.atk.service.ReturService;
import com.atk.service.TambahBarangService;
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
public class ReturDao implements ReturService{

    private Connection koneksi;
    private ReturDetilDao returDetilDao;
    private TambahBarangService tambahDao;
    private BarangService barangDao;

    public ReturDao() {
        koneksi=DBkoneksi.getConnection();
        returDetilDao=new ReturDetilDao();
        tambahDao=new TambahBarangDao();
        barangDao=new BarangDao();
    }
    
    @Override
    public String setId() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        String kode=null;
        String s, s1;
        Integer j;
        Integer panjang = 5;
        String sql="select max(right(id_retur, 5)) from retur";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="RT/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="RT/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(Retur r) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into retur (id_retur, tanggal_retur, tanggal_beli, total_beli, total_retur, no_tambah, supplier) values (?,?,?,?,?,?,?)";
        try {
            koneksi.setAutoCommit(false);
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, r.getId_retur());
            statement.setDate(2, new java.sql.Date(r.getTanggal_retur().getTime()));
            statement.setDate(3, new java.sql.Date(r.getTanggal_beli().getTime()));
            statement.setInt(4, r.getTotal_beli());
            statement.setInt(5, r.getTotal_retur());
            statement.setString(6, r.getTambahBarang().getNo_tambah());
            statement.setString(7, r.getSupplier());
            statement.executeUpdate();
            
            for(ReturDetil rd:r.getReturDetils()){
                returDetilDao.simpan(koneksi, rd);
            }
            
            valid=true;
            koneksi.commit();
            koneksi.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                koneksi.rollback();
                koneksi.setAutoCommit(true);
                Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
                valid=false;
            } catch (SQLException ex1) {
                Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

   
    @Override
    public List<ReportRetur> getrReportReturs(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from retur inner join retur_detil on(retur.id_retur=retur_detil.id_retur) where retur.id_retur=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                ReportRetur rr=new ReportRetur();
                    Retur r=new Retur();
                    r.setId_retur(rs.getString("id_retur"));
                    r.setTanggal_retur(rs.getDate("tanggal_retur"));
                    r.setTanggal_beli(rs.getDate("tanggal_beli"));
                    r.setTotal_beli(rs.getInt("total_beli"));
                    r.setTotal_retur(rs.getInt("total_retur"));
                    String no_tambah=rs.getString("no_tambah");
                    TambahBarang tambahBarang = tambahDao.getTambahBarang(no_tambah);
                    r.setTambahBarang(tambahBarang);
                    r.setSupplier(rs.getString("supplier"));
                rr.setRetur(r);
                    ReturDetil rd=new ReturDetil();
                    String idbarang=rs.getString("id_barang");
                    Barang barang = barangDao.getBarang(idbarang);
                    rd.setBarang(barang);
                    rd.setJumlah(rs.getInt("jumlah"));
                    rd.setHarga(rs.getInt("harga"));
                    rd.setStatus(rs.getString("status"));
                rr.setReturDetil(rd);
                list.add(rr);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
