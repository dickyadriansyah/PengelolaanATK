/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.service;

import com.atk.model.ReportRetur;
import com.atk.model.Retur;
import java.util.List;

/**
 *
 * @author dicky-java
 */
public interface ReturService {
    String setId();
    boolean simpan(Retur r);
    List<ReportRetur> getrReportReturs(String id);
}
