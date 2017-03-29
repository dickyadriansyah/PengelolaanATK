/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atk.swingmakeover;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Jamalludin Husein
 */
public class PanelTransparan extends JPanel{
     private Color color;

    public PanelTransparan() {
        setOpaque(false);
        color=new Color(0f, 0f, 0f, 0f);
    }
    
    public void setBackGround(Color backGround){
        super.setBackground(backGround);
        color=new Color(0f, 0f, 0f, 0f);
        repaint();
    }
    
    @Override
     protected void paintComponent(Graphics graphics){
       super.paintComponent(graphics);
        Graphics2D gd=(Graphics2D) graphics.create();
        gd.setColor(color);
        gd.fillRect(0, 0, getWidth(), getHeight());
        gd.dispose();
   }
}
