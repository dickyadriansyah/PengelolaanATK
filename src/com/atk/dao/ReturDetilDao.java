/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dao;

import com.atk.model.ReturDetil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dicky-java
 */
public class ReturDetilDao {

    public ReturDetilDao() {
    }
    
    public boolean simpan(Connection connection, ReturDetil rd){
        boolean valid=false;
        PreparedStatement statement=null;
        String sql2 = "insert into retur_detil (id_retur, id_barang, jumlah, harga, status) values (?,?,?,?,?)";
        try {
            statement=connection.prepareStatement(sql2);
            statement.setString(1, rd.getRetur().getId_retur());
            statement.setString(2, rd.getBarang().getId_barang());
            statement.setInt(3, rd.getJumlah());
            statement.setInt(4, rd.getHarga());
            statement.setString(5, rd.getStatus());
            statement.executeUpdate();
            valid=true;
        } catch (SQLException ex) {
            Logger.getLogger(ReturDetilDao.class.getName()).log(Level.SEVERE, null, ex);
            valid=false;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDetilDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }
}
