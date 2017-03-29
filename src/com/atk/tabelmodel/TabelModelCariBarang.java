/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.tabelmodel;

import com.atk.model.Barang;
import com.atk.model.CariBarang;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TabelModelCariBarang extends DynamicTableModel<CariBarang>{

    private List<CariBarang> list=new ArrayList<>();
    
    public TabelModelCariBarang(List<CariBarang> list, Class<CariBarang> class1) {
        super(list, class1);
        this.list=list;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue!=null&&aValue instanceof Boolean&&columnIndex==0){
            boolean pilih= (boolean) aValue;
                list.get(rowIndex).setPilih(pilih);           
        }
        
        if(aValue!=null&&aValue instanceof Integer&&columnIndex==1){
            Integer jumlah= (Integer) aValue;
            list.get(rowIndex).setJumlah(jumlah);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==1 || columnIndex==0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0: return Boolean.class;
            case 1: return Integer.class;
            case 2: return Barang.class;
                default:return Object.class;
        }
    }
    
    public List getBarang() {
        List ls=new ArrayList();
        for(CariBarang cb : list) {
          if(cb.isPilih()) {
            ls.add(cb);
          }
        }
        return ls;
    }
    
}
