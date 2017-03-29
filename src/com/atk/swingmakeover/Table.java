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
import java.awt.Font;
import javax.swing.JTable;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author usu
 */
public class Table extends JTable {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

    public Table() {
        super();
        setForeground(Color.WHITE);
        setSelectionBackground(Color.lightGray.brighter());
        setSelectionForeground(Color.BLUE);
        setFont(getFont().deriveFont(Font.BOLD));
        setFillsViewportHeight(true);
        setOpaque(false);
        WidgetUtilities.setAutomaticPopUpMenu(this);
        setAutoCreateRowSorter(true);
        getTableHeader().setReorderingAllowed(false);
    }
}
