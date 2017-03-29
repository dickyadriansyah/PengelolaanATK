/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Barang;
import com.atk.model.ReportTambahBarang;
import com.atk.model.Supplier;
import com.atk.model.TambahBarang;
import com.atk.model.TambahBarangDetil;
import com.atk.service.BarangService;
import com.atk.service.SupplierService;
import com.atk.service.TambahBarangService;
import java.sql.Connection;
import java.sql.Date;
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
public class TambahBarangDao implements TambahBarangService{

    private Connection koneksi;
    private TambahBarangDetilDao tambahBarangDetilDao;
    private SupplierService supplierDao;
    private BarangService barangDao;

    public TambahBarangDao() {
        koneksi=DBkoneksi.getConnection();
        tambahBarangDetilDao=new TambahBarangDetilDao();
        supplierDao=new SupplierDao();
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
        String sql="select max(right(no_tambah, 5)) from tambah_barang";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="TB/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="TB/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(TambahBarang tb) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into tambah_barang (no_tambah, id_supplier, tanggal, total) values (?,?,?,?)";
        try {
            koneksi.setAutoCommit(false);
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, tb.getNo_tambah());
            statement.setString(2, tb.getSupplier().getId_supplier());
            statement.setDate(3, new Date(tb.getTanggal().getTime()));
            statement.setInt(4, tb.getTotal());
            statement.executeUpdate();
            
            for(TambahBarangDetil tbd:tb.getTambahbarangdetil()){
                tambahBarangDetilDao.simpan(koneksi, tbd);
            }
            
            koneksi.commit();
            valid=true;
            koneksi.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                koneksi.rollback();
                koneksi.setAutoCommit(true);
                Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                valid=false;
            } catch (SQLException ex1) {
                Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(TambahBarang tb) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from tambah_barang where no_tambah=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, tb.getNo_tambah());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapusBarang(TambahBarangDetil tbd) {
        boolean hapus = tambahBarangDetilDao.hapus(koneksi, tbd);
        return hapus;
    }

    @Override
    public TambahBarang getTambahBarang(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        TambahBarang tb=null;
        String sql = "select * from tambah_barang where no_tambah=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                tb=new TambahBarang();
                tb.setNo_tambah(rs.getString("no_tambah"));
                String idsupplier=rs.getString("id_supplier");
                Supplier supplier = supplierDao.getSupplier(idsupplier);
                tb.setSupplier(supplier);
                tb.setTanggal(rs.getDate("tanggal"));
                tb.setTotal(rs.getInt("total"));
            }
            return tb;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TambahBarang> getTambahBarangs() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from tambah_barang";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                TambahBarang tb=new TambahBarang();
                tb.setNo_tambah(rs.getString("no_tambah"));
                String idsupplier=rs.getString("id_supplier");
                Supplier supplier = supplierDao.getSupplier(idsupplier);
                tb.setSupplier(supplier);
                tb.setTanggal(rs.getDate("tanggal"));
                tb.setTotal(rs.getInt("total"));
                list.add(tb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TambahBarangDetil> getTambahBarangDetils(String idtambah) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from tambah_barang_detil where no_tambah=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, idtambah);
            rs=statement.executeQuery();
            while(rs.next()){
                TambahBarangDetil detil=new TambahBarangDetil();
                detil.setTambahBarang(getTambahBarang(rs.getString("no_tambah")));
                String idbarang=rs.getString("id_barang");
                Barang barang = barangDao.getBarang(idbarang);
                detil.setBarang(barang);
                detil.setJumlah(rs.getInt("jumlah"));
                detil.setHarga(rs.getInt("harga"));
                list.add(detil);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TambahBarang> getTambahBarangs(java.util.Date tanggal) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from tambah_barang where tanggal=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setDate(1, new Date(tanggal.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                TambahBarang tb=new TambahBarang();
                tb.setNo_tambah(rs.getString("no_tambah"));
                String idsupplier=rs.getString("id_supplier");
                Supplier supplier = supplierDao.getSupplier(idsupplier);
                tb.setSupplier(supplier);
                tb.setTanggal(rs.getDate("tanggal"));
                tb.setTotal(rs.getInt("total"));
                list.add(tb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<ReportTambahBarang> getReportTambahBarangs(java.util.Date tglAwal, java.util.Date tglAkhir) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from tambah_barang inner join tambah_barang_detil on(tambah_barang.no_tambah=tambah_barang_detil.no_tambah) where (tanggal>=?) and (tanggal<=?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setDate(1, new Date(tglAwal.getTime()));
            statement.setDate(2, new Date(tglAkhir.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                ReportTambahBarang rtb=new ReportTambahBarang();
                    TambahBarang tb=new TambahBarang();
                    tb.setNo_tambah(rs.getString("no_tambah"));
                    String idsupplier=rs.getString("id_supplier");
                    Supplier supplier = supplierDao.getSupplier(idsupplier);
                    tb.setSupplier(supplier);
                    tb.setTanggal(rs.getDate("tanggal"));
               rtb.setTambahBarang(tb);
                    TambahBarangDetil detil=new TambahBarangDetil();
                    String idbarang=rs.getString("id_barang");
                    Barang barang = barangDao.getBarang(idbarang);
                    detil.setBarang(barang);
                    detil.setJumlah(rs.getInt("jumlah"));
                    detil.setHarga(rs.getInt("harga"));
              rtb.setTambahBarangDetil(detil);
              list.add(rtb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<ReportTambahBarang> getReportTambahBarangs(java.util.Date tglAwal, java.util.Date tglAkhir, Supplier supplier) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from tambah_barang inner join tambah_barang_detil on(tambah_barang.no_tambah=tambah_barang_detil.no_tambah) where (tanggal>=?) and (tanggal<=?) and id_supplier=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setDate(1, new Date(tglAwal.getTime()));
            statement.setDate(2, new Date(tglAkhir.getTime()));
            statement.setString(3, supplier.getId_supplier());
            rs=statement.executeQuery();
            while(rs.next()){
                ReportTambahBarang rtb=new ReportTambahBarang();
                    TambahBarang tb=new TambahBarang();
                    tb.setNo_tambah(rs.getString("no_tambah"));
                    String idsupplier=rs.getString("id_supplier");
                    Supplier supplier1 = supplierDao.getSupplier(idsupplier);
                    tb.setSupplier(supplier1);
                    tb.setTanggal(rs.getDate("tanggal"));
               rtb.setTambahBarang(tb);
                    TambahBarangDetil detil=new TambahBarangDetil();
                    String idbarang=rs.getString("id_barang");
                    Barang barang = barangDao.getBarang(idbarang);
                    detil.setBarang(barang);
                    detil.setJumlah(rs.getInt("jumlah"));
                    detil.setHarga(rs.getInt("harga"));
              rtb.setTambahBarangDetil(detil);
              list.add(rtb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean rubah(TambahBarang tb) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update tambah_barang set total=? where no_tambah=?";
        try {
            koneksi.setAutoCommit(false);
            statement=koneksi.prepareStatement(sql);
            statement.setInt(1, tb.getTotal());
            statement.setString(2, tb.getNo_tambah());
            statement.executeUpdate();
            
            for(TambahBarangDetil tbd: tb.getTambahbarangdetil()){
                if(tbd.getModeUpdate()==0){
                    tambahBarangDetilDao.simpan(koneksi, tbd);
                }
                if(tbd.getModeUpdate()==0){
                    tambahBarangDetilDao.rubah(koneksi, tbd);
                }
            }
            
            koneksi.commit();
            valid=true;
            koneksi.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                koneksi.rollback();
                koneksi.setAutoCommit(true);
                Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                valid=false;
            } catch (SQLException ex1) {
                Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public void kurangJumlah(int jumlah, Barang barang, TambahBarang tambahBarang) {
        PreparedStatement statement=null;
        String sql = "update tambah_barang_detil set jumlah=jumlah-? where id_barang=? and no_tambah=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setInt(1, jumlah);
            statement.setString(2, barang.getId_barang());
            statement.setString(3, tambahBarang.getNo_tambah());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TambahBarangDetil> getTambahBarangDetils() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from tambah_barang_detil";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                TambahBarangDetil detil=new TambahBarangDetil();
                detil.setTambahBarang(getTambahBarang(rs.getString("no_tambah")));
                String idbarang=rs.getString("id_barang");
                Barang barang = barangDao.getBarang(idbarang);
                detil.setBarang(barang);
                detil.setJumlah(rs.getInt("jumlah"));
                detil.setHarga(rs.getInt("harga"));
                list.add(detil);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TambahBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
