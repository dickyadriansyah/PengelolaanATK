/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.tabelmodel;

import com.atk.model.Barang;
import com.atk.model.CariPembelian;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TabelModelCariPembelian extends DynamicTableModel<CariPembelian>{

    private List<CariPembelian> list=new ArrayList<>();
    
    public TabelModelCariPembelian(List<CariPembelian> list, Class<CariPembelian> class1) {
        super(list, class1);
        this.list=list;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue!=null&&aValue instanceof Boolean&&columnIndex==0){
            boolean pilih= (boolean) aValue;
                list.get(rowIndex).setPilih(pilih);           
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==0){
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
    
    public List getPembelian() {
        List ls=new ArrayList();
        for(CariPembelian cp : list) {
          if(cp.isPilih()) {
            ls.add(cp);
          }
        }
        return ls;
    }
    
}
