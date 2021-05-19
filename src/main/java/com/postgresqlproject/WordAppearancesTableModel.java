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
public class WordAppearancesTableModel extends AbstractTableModel{
    
    private String[] columnNames = {
                "Song name", "Word number in song", "Line in song", "Verse number", 
        "Word number in verse", "Line number in verse", "Word number in line", 
        "File path"
            };
    
    private ArrayList<WordAppearance> myList;
    
    public WordAppearancesTableModel(List<WordAppearance> wordAppearancesList) {
        myList = (ArrayList<WordAppearance>) wordAppearancesList;
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
                temp = myList.get(rowIndex).getWordNumberInSong();
                break;
            case 2:
                temp = myList.get(rowIndex).getLineNumberInSong();
                break;
            case 3:
                temp = myList.get(rowIndex).getVerseNumber();
                break;
            case 4:
                temp = myList.get(rowIndex).getWordNumberInVerse();
                break;
            case 5:
                temp = myList.get(rowIndex).getLineNumberInVerse();
                break;
            case 6:
                temp = myList.get(rowIndex).getWordNumberInLine();
                break;
            case 7:
                temp = myList.get(rowIndex).getSongPath();
                break;    
        }

        return temp;    
    
    }
    
    public WordAppearance getWordAppearanceByRow(int rowIndex)
    {
        return myList.get(rowIndex);
    }
    
    @Override
     public String getColumnName(int col) {
        return columnNames[col];
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
}
