/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.dialog;

import com.atk.dao.BarangDao;
import com.atk.model.Barang;
import com.atk.model.BeliBarang;
import com.atk.service.BarangService;
import com.atk.tabelmodel.TabelModelBeliBarang;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author dicky-java
 */
public class PencarianBeliBarang extends javax.swing.JDialog implements DocumentListener{

    private TableRowSorter sorter;
    private TabelModelBeliBarang tabelModelBeliBarang;
    private List data=new ArrayList();
    private BarangService barangDao;
    
    public PencarianBeliBarang() {
        setModal(true);
        initComponents();
        barangDao=new BarangDao();
        tbl_barang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void loadCariBarang(){
        tabelModelBeliBarang=new TabelModelBeliBarang(getCariBarang(), BeliBarang.class);
        tbl_barang.setDynamicModel(tabelModelBeliBarang);
        sorter = new TableRowSorter<>(tbl_barang.getModel());
        tbl_barang.setRowSorter(sorter);
        txt_cari.getDocument().addDocumentListener(this);
    }
    
    private List getCariBarang(){
        List list=new ArrayList();
        List<Barang> barangs = barangDao.getbBarangs();
        for(Barang b: barangs){
            BeliBarang beliBarang=new BeliBarang();
            beliBarang.setBarang(b);
            beliBarang.setPilih(false);
            beliBarang.setJumlah(b.getJumlah());
            beliBarang.setHarga(b.getHarga());
            list.add(beliBarang);
        }
        return list;
    }
    
    public List ambilData(){
        setLocationRelativeTo(null);
        setTitle("Search Tool");
        setVisible(true);
        return data;
    }

    private void saring(){
        String text = txt_cari.getText();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(Pattern.compile("(?i).*" + text + ".*",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL).toString()));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_barang = new com.stripbandunk.jwidget.JDynamicTable();
        jPanel1 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Cari Barang :");

        jScrollPane1.setViewportView(tbl_barang);

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/add.png"))); // NOI18N
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel1.add(btn_add);

        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/atk/gambar/close.png"))); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        data.clear();
        dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        data.clear();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        int  valid=0;
        List<BeliBarang> barang = tabelModelBeliBarang.getBarang();
        for(BeliBarang bb: barang){
            valid=valid+1;
        }
        if (valid==barang.size()) {
            data.clear();
            data=tabelModelBeliBarang.getBarang();
            dispose();
        }
    }//GEN-LAST:event_btn_addActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.stripbandunk.jwidget.JDynamicTable tbl_barang;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertUpdate(DocumentEvent e) {
        saring(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        saring(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        saring(); //To change body of generated methods, choose Tools | Templates.
    }
}
