/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.tabelmodel;

import com.atk.model.TambahBarangDetil;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TabelModelTambahBarangDetil extends DynamicTableModel<TambahBarangDetil>{

    
    private List<TambahBarangDetil> list=new ArrayList<>();
    public TabelModelTambahBarangDetil(List<TambahBarangDetil> list, Class<TambahBarangDetil> clss1) {
        super(list, clss1);
        this.list=list;
    }
    
}
