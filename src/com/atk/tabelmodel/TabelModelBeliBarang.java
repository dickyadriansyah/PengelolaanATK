/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.tabelmodel;

import com.atk.model.Barang;
import com.atk.model.BeliBarang;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TabelModelBeliBarang extends DynamicTableModel<BeliBarang>{

    private List<BeliBarang> list=new ArrayList<>();
    
    public TabelModelBeliBarang(List<BeliBarang> list, Class<BeliBarang> class1) {
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
            case 1: return Barang.class;
            case 2: return Integer.class;
                default:return Object.class;
        }
    }
    
    public List getBarang() {
        List ls=new ArrayList();
        for(BeliBarang bb : list) {
          if(bb.isPilih()) {
            ls.add(bb);
          }
        }
        return ls;
    }
    
}
