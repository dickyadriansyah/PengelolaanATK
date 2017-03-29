/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.db.DBkoneksi;
import com.atk.model.Barang;
import com.atk.model.Karyawan;
import com.atk.model.PengelolaanBarang;
import com.atk.model.PengelolaanBarangDetil;
import com.atk.model.ReportPengelolaanBarang;
import com.atk.service.BarangService;
import com.atk.service.KaryawanService;
import com.atk.service.PengelolaanBarangService;
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
public class PengelolaanBarangDao implements PengelolaanBarangService{

    private Connection koneksi;
    private PengelolaanBarangDetilDao barangDetilDao;
    private KaryawanService karyawanDao;
    private BarangService barangDao;

    public PengelolaanBarangDao() {
        koneksi=DBkoneksi.getConnection();
        barangDetilDao=new PengelolaanBarangDetilDao();
        karyawanDao=new KaryawanDao();
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
        String sql="select max(right(no_pengelolaan, 5)) from pengelolaan_barang";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            if(rs.first()==false){
                kode="PB/00001";
            }else{
                rs.last();
                s=Integer.toString(rs.getInt(1) + 1);
                j=s.length();
                s1="";
                for(int i=1;i<=panjang-j;i++){
                    s1=s1+"0";
                }
                kode="PB/"+s1+s;
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean simpan(PengelolaanBarang pb) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "insert into pengelolaan_barang (no_pengelolaan, tanggal, nik, total) values (?,?,?,?)";
        try {
            koneksi.setAutoCommit(false);
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, pb.getNo_pengelolaan());
            statement.setDate(2, new Date(pb.getTanggal().getTime()));
            statement.setString(3, pb.getKaryawan().getNik());
            statement.setInt(4, pb.getTotal());
            statement.executeUpdate();
            
            for(PengelolaanBarangDetil pbd:pb.getPengelolaanBarangDetil()){
                barangDetilDao.simpan(koneksi, pbd);
            }
            
            koneksi.commit();
            valid=true;
            koneksi.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                koneksi.rollback();
                koneksi.setAutoCommit(true);
                Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                valid=false;
            } catch (SQLException ex1) {
                Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapus(PengelolaanBarang pb) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "delete from pengelolaan_barang where no_pengelolaan=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, pb.getNo_pengelolaan());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean hapusbarang(PengelolaanBarangDetil pbd) {
        boolean hapus = barangDetilDao.hapus(koneksi, pbd);
        return hapus;
    }

    @Override
    public PengelolaanBarang getPengelolaanBarang(String id) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        PengelolaanBarang pb=null;
        String sql = "select * from pengelolaan_barang where no_pengelolaan=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                pb=new PengelolaanBarang();
                pb.setNo_pengelolaan(rs.getString("no_pengelolaan"));
                pb.setTanggal(rs.getDate("tanggal"));
                String nik=rs.getString("nik");
                Karyawan karyawan = karyawanDao.getKaryawan(nik);
                pb.setKaryawan(karyawan);
                pb.setTotal(rs.getInt("total"));
            }
            return pb;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<PengelolaanBarang> getPengelolaanBarangs() {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from pengelolaan_barang";
        try {
            statement=koneksi.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                PengelolaanBarang pb=new PengelolaanBarang();
                pb.setNo_pengelolaan(rs.getString("no_pengelolaan"));
                pb.setTanggal(rs.getDate("tanggal"));
                String nik=rs.getString("nik");
                Karyawan karyawan = karyawanDao.getKaryawan(nik);
                pb.setKaryawan(karyawan);
                pb.setTotal(rs.getInt("total"));
                list.add(pb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<PengelolaanBarangDetil> getPengelolaanBarangDetils(String idpengelolaan) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from pengelolaan_barang_detil where no_pengelolaan=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setString(1, idpengelolaan);
            rs=statement.executeQuery();
            while(rs.next()){
                PengelolaanBarangDetil pbd=new PengelolaanBarangDetil();
                pbd.setPengelolaanBarang(getPengelolaanBarang(rs.getString("no_pengelolaan")));
                String id_barang=rs.getString("id_barang");
                Barang barang = barangDao.getBarang(id_barang);
                pbd.setBarang(barang);
                pbd.setJumlah(rs.getInt("jumlah"));
                pbd.setHarga(rs.getInt("harga"));
                list.add(pbd);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<PengelolaanBarang> getPengelolaanBarangs(java.util.Date tanggal) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from pengelolaan_barang where tanggal=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setDate(1, new Date(tanggal.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                PengelolaanBarang pb=new PengelolaanBarang();
                pb.setNo_pengelolaan(rs.getString("no_pengelolaan"));
                pb.setTanggal(rs.getDate("tanggal"));
                String nik=rs.getString("nik");
                Karyawan karyawan = karyawanDao.getKaryawan(nik);
                pb.setKaryawan(karyawan);
                pb.setTotal(rs.getInt("total"));
                list.add(pb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<ReportPengelolaanBarang> getPengelolaanBarangs(java.util.Date tglAwal, java.util.Date tglAkhir) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from pengelolaan_barang inner join pengelolaan_barang_detil on(pengelolaan_barang.no_pengelolaan=pengelolaan_barang_detil.no_pengelolaan) where (tanggal>=?) and (tanggal<=?)";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setDate(1, new Date(tglAwal.getTime()));
            statement.setDate(2, new Date(tglAkhir.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                ReportPengelolaanBarang rpb=new ReportPengelolaanBarang();
                    PengelolaanBarang pb=new PengelolaanBarang();
                    pb.setNo_pengelolaan(rs.getString("no_pengelolaan"));
                    pb.setTanggal(rs.getDate("tanggal"));
                    String idkaryawan=rs.getString("nik");
                    Karyawan karyawan = karyawanDao.getKaryawan(idkaryawan);
                    pb.setKaryawan(karyawan);
                rpb.setPengelolaanBarang(pb);
                    PengelolaanBarangDetil pbd=new PengelolaanBarangDetil();
                    String idbarang=rs.getString("id_barang");
                    Barang barang = barangDao.getBarang(idbarang);
                    pbd.setBarang(barang);
                    pbd.setJumlah(rs.getInt("jumlah"));
                    pbd.setHarga(rs.getInt("harga"));
                rpb.setPengelolaanBarangDetil(pbd);
                list.add(rpb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<ReportPengelolaanBarang> getPengelolaanBarangs(java.util.Date tglAwal, java.util.Date tglAkhir, Karyawan karyawan) {
        PreparedStatement statement=null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql = "select * from pengelolaan_barang inner join pengelolaan_barang_detil on(pengelolaan_barang.no_pengelolaan=pengelolaan_barang_detil.no_pengelolaan) where (tanggal>=?) and (tanggal<=?) and nik=?";
        try {
            statement=koneksi.prepareStatement(sql);
            statement.setDate(1, new Date(tglAwal.getTime()));
            statement.setDate(2, new Date(tglAkhir.getTime()));
            statement.setString(3, karyawan.getNik());
            rs=statement.executeQuery();
            while(rs.next()){
                ReportPengelolaanBarang rpb=new ReportPengelolaanBarang();
                    PengelolaanBarang pb=new PengelolaanBarang();
                    pb.setNo_pengelolaan(rs.getString("no_pengelolaan"));
                    pb.setTanggal(rs.getDate("tanggal"));
                    String idkaryawan=rs.getString("nik");
                    Karyawan karyawan1 = karyawanDao.getKaryawan(idkaryawan);
                    pb.setKaryawan(karyawan1);
                rpb.setPengelolaanBarang(pb);
                    PengelolaanBarangDetil pbd=new PengelolaanBarangDetil();
                    String idbarang=rs.getString("id_barang");
                    Barang barang = barangDao.getBarang(idbarang);
                    pbd.setBarang(barang);
                    pbd.setJumlah(rs.getInt("jumlah"));
                    pbd.setHarga(rs.getInt("harga"));
                rpb.setPengelolaanBarangDetil(pbd);
                list.add(rpb);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean rubah(PengelolaanBarang pb) {
        boolean valid=false;
        PreparedStatement statement=null;
        String sql = "update pengelolaan_barang set total=? where no_pengelolaan=?";
        try {
            koneksi.setAutoCommit(false);
            statement=koneksi.prepareStatement(sql);
            statement.setInt(1, pb.getTotal());
            statement.setString(2, pb.getNo_pengelolaan());
            statement.executeUpdate();
            
            for(PengelolaanBarangDetil pbd: pb.getPengelolaanBarangDetil()){
                if(pbd.getModeUpdate()==0){
                    barangDetilDao.simpan(koneksi, pbd);
                }
                if(pbd.getModeUpdate()==0){
                    barangDetilDao.rubah(koneksi, pbd);
                }
            }
            
            koneksi.commit();
            valid=true;
            koneksi.setAutoCommit(true);
            
        } catch (SQLException ex) {
            try {
                koneksi.rollback();
                koneksi.setAutoCommit(true);
                Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                valid=false;
            } catch (SQLException ex1) {
                Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PengelolaanBarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    
}
