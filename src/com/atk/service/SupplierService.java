/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.Supplier;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface SupplierService {
    String setId();
    boolean simpan(Supplier s);
    boolean rubah(Supplier s);
    boolean hapus(Supplier s);
    Supplier getSupplier(String id);
    List<Supplier> getsSuppliers();
}
