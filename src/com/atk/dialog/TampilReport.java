/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atk.dialog;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JDialog;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author asep
 */
public class TampilReport extends JDialog{
    private String title;
    private JRViewer jv;
    

    
    public TampilReport(String title, JRViewer jv) {
        this.title = title;
        this.jv = jv;
        atur();
    }
    
    private void atur(){
        
        setModal(true);
        setLocationRelativeTo(null);
        setTitle(title);
        getContentPane().add(jv);
        GraphicsDevice gd= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }
    
}
