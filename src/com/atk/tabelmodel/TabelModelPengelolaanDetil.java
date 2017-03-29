/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.tabelmodel;

import com.atk.model.PengelolaanBarangDetil;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TabelModelPengelolaanDetil extends DynamicTableModel<PengelolaanBarangDetil>{

    private List<PengelolaanBarangDetil> list=new ArrayList<>();
    
    public TabelModelPengelolaanDetil(List<PengelolaanBarangDetil> list, Class<PengelolaanBarangDetil> class1) {
        super(list, class1);
        this.list=list;
    }
}
