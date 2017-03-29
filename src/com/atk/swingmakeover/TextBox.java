/*
 * DILARANG MENGHAPUS ATAU MENGEDIT COPYRIGHT INI.
 * 
 * Copyright 2008 echo.khannedy@gmail.com. 
 * All rights reserved.
 * 
 * Semua isi dalam file ini adalah hak milik dari echo.khannedy@gmail.com
 * Anda tak diperkenankan untuk menggunakan file atau mengubah file
 * ini kecuali anda tidak menghapus atau merubah lisence ini.
 * 
 * File ini dibuat menggunakan :
 * IDE        : NetBeans
 * NoteBook   : Acer Aspire 5920G
 * OS         : Windows Vista
 * Java       : Java 1.6
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
import static javax.swing.SwingConstants.LEFT;
import javax.swing.border.EmptyBorder;
import usu.widget.glass.TextBoxGlass;

/**
 *
 * @author
 * TextBox ini adalah kepunyaan dari eko khannedy serta librarynya juga
 */
public class TextBox extends TextBoxGlass {

    private static final long serialVersionUID = -1L;

    public TextBox() {
     setOpaque(false);
     setBorder(new EmptyBorder(5, 5, 5, 5));
     setForeground(Color.BLACK);
     setCaretColor(Color.BLACK);
     setFont(getFont().deriveFont(Font.BOLD));
     setHorizontalAlignment(LEFT);
    addMouseListener(new MouseAdapter()
   {
@Override
       public void mouseEntered(MouseEvent e)
      {
          TextBox.this.setCursor(Cursor.getPredefinedCursor(2));
      }

@Override
       public void mouseExited(MouseEvent e)
      {
       TextBox.this.setCursor(Cursor.getPredefinedCursor(0));
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
       g2.setColor(Color.BLACK);
       g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());

       g2.dispose();
     } else {
       g2 = (Graphics2D)g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      Color light = new Color(1.0F, 1.0F, 1.0F, 0.3F);
      g2.setColor(light);
      g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
      g2.setColor(Color.BLACK);
      g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
 
      g2.dispose();
    }
 }
}
