/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.tabelmodel;

import com.atk.model.ReturDetil;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public class TabelModelReturDetil extends DynamicTableModel<ReturDetil>{

    private List<ReturDetil> list=new ArrayList<>();
    
    public TabelModelReturDetil(List<ReturDetil> list, Class<ReturDetil> class1) {
        super(list, class1);
        this.list=list;
    }
    
}
