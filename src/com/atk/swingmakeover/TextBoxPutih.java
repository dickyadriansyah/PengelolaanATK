/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atk.swingmakeover;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import usu.widget.glass.TextBoxGlass;

/**
 *
 * @author dicky-java
 */
public class TextBoxPutih extends TextBoxGlass{
    
    private static final long serialVersionUID = -1L;

    public TextBoxPutih() {
     setOpaque(false);
     setBorder(new EmptyBorder(5, 5, 5, 5));
     setForeground(Color.WHITE);
     setCaretColor(Color.WHITE);
     setFont(getFont().deriveFont(Font.BOLD));
     setHorizontalAlignment(LEFT);
    addMouseListener(new MouseAdapter()
   {
@Override
       public void mouseEntered(MouseEvent e)
      {
          TextBoxPutih.this.setCursor(Cursor.getPredefinedCursor(2));
      }

@Override
       public void mouseExited(MouseEvent e)
      {
       TextBoxPutih.this.setCursor(Cursor.getPredefinedCursor(0));
      }
   });
    }
    
    @Override
  protected void paintComponent(Graphics g)
   {
    Graphics2D g2;
     super.paintComponent(g);

    if (isEnabled()) {
      g2 = (Graphics2D)g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
       Color dark = new Color(1.0F, 1.0F, 1.0F, 0.0F);
       Color light = new Color(1.0F, 1.0F, 1.0F, 0.3F);
       GradientPaint paint = new GradientPaint(0.0F, 0.0F, light, 0.0F, getHeight() / 2, dark);
       g2.setPaint(paint);
       g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
       g2.setColor(Color.WHITE);
       g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());

       g2.dispose();
     } else {
       g2 = (Graphics2D)g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      Color light = new Color(1.0F, 1.0F, 1.0F, 0.3F);
      g2.setColor(light);
      g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
      g2.setColor(Color.WHITE);
      g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
 
      g2.dispose();
    }
 }
    
}
