/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atk.swingmakeover;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Muhammad Dicky Java
 */
public class Button extends JButton{
     private static final long serialVersionUID = -1L;
     private boolean over;

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }
     
     
  public Button()
  {
     setOpaque(false);
     setBorderPainted(false);
     setContentAreaFilled(false);
     setFocusPainted(false);
     setBorder(new EmptyBorder(5, 10, 5, 10));
     setForeground(Color.black);
    addMouseListener(new MouseAdapter()
   {
       @Override
       public void mouseEntered(MouseEvent e){
             super.mouseEntered(e);
            Button.this.setOver(true);
        }

       @Override
       public void mouseExited(MouseEvent e){
            super.mouseExited(e);
            Button.this.setOver(false);
        }
   });
  }

    @Override
  protected void paintComponent(Graphics g)
   {
       ButtonModel buttonModel=getModel();
Graphics2D g2;
     super.paintComponent(g);

    if (isEnabled()) {
      g2 = (Graphics2D)g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
       Color dark = new Color(1.0F, 1.0F, 1.0F, 0.0F);
       Color light = new Color(1.0F, 1.0F, 1.0F, 0.3F);
       GradientPaint paint = new GradientPaint(0.0F, 0.0F, light, 0.0F, getHeight() / 2, dark);
       g2.setPaint(paint);
       if(buttonModel.isRollover()){
            g2.setPaint(new GradientPaint(0, 0, new Color(0, 0, 0, 0), 0,getHeight(), Color.BLACK));
            if(buttonModel.isPressed()){
                g2.setPaint(new GradientPaint(0, 0, Color.black, 0, getHeight(), Color.black));
                setForeground(Color.black);
            }else{
                setForeground(Color.black);
            }
        }

       g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
       g2.setColor(Color.black);
       g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());

       g2.dispose();
     } else {
       g2 = (Graphics2D)g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      Color light = new Color(1.0F, 1.0F, 1.0F, 0.3F);
      g2.setColor(light);
      g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
      g2.setColor(Color.black);
      g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
 
      g2.dispose();
    }
 }
   }


