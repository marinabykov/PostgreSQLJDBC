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
public class WordsTableModel extends AbstractTableModel{
    private String[] columnNames = {
                "Words"
            };
    
    private ArrayList<Word> myList;
    
    public WordsTableModel(List<Word> wordsList) {
        myList = (ArrayList<Word>) wordsList;
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
    
    public Boolean existingWord(String word) {
        for (Word wordInTable : myList) {
                if (wordInTable.getWordText().equals(word)) {
                    return true;
                }
            }
        return false;
    }
    
    public void addRow(Word newWord) {
        myList.add(newWord);
        int row = myList.indexOf(newWord);
        for(int column = 0; column < myList.size(); column++) {
            fireTableCellUpdated(row, column);
        }
        fireTableRowsInserted(row, row);
    }    
    
    public Word getWordFromTable(String wordToSearch)
    {
        for (Word wordInTable : myList) {
                if (wordInTable.getWordText().equals(wordToSearch)) {
                    return wordInTable;
                }
            }
        return null;
    }
}
