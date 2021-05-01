/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marina Bykov
 */
public class PhraseAppearanceTableModel extends AbstractTableModel{
    private String[] columnNames = {
                "Song name", "Appearance number in song"
            };
    
    private ArrayList<PhraseAppearance> myList;
    
    public PhraseAppearanceTableModel(List<PhraseAppearance> phraseAppearancesList) {
        myList = (ArrayList<PhraseAppearance>) phraseAppearancesList;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        int size;
        if (myList == null) {
            size = 0;
        }
        else {
            size = myList.size();
        }
        return size;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch (columnIndex) {
            case 0:
                temp = myList.get(rowIndex).getSongName();
                break;
            case 1:
                temp = myList.get(rowIndex).getAppearanceNumberInSong();
                break;
        }

        return temp;    
    }    
    
    @Override
     public String getColumnName(int col) {
        return columnNames[col];
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public PhraseAppearance getPhraseAppearanceByRow(int rowIndex)
    {
        return myList.get(rowIndex);
    }
}
