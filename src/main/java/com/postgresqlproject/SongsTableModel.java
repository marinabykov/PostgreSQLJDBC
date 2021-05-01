/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;

/**
 *
 * @author Marina Bykov
 */

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;

public class SongsTableModel extends AbstractTableModel{
    private String[] columnNames = {
                "Sond name", "Writer", "File path", "Date of add"
            };
    
    private ArrayList<Song> myList;
    
    public SongsTableModel(List<Song> songsList) {
        myList = (ArrayList<Song>) songsList;
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
                temp = myList.get(rowIndex).getSongWriter();
                break;
            case 2:
                temp = myList.get(rowIndex).getFilePath();
                break;
            case 3:
                temp = myList.get(rowIndex).getDateOfAdd();
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
    
    public void addRow(Song newSong) {
        myList.add(newSong);
        int row = myList.indexOf(newSong);
        for(int column = 0; column < myList.size(); column++) {
            fireTableCellUpdated(row, column);
        }
        fireTableRowsInserted(row, row);
    }    
   
}
