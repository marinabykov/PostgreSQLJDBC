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
public class PhrasesTableModel extends AbstractTableModel{
    private String[] columnNames = {
                "Phrases"
            };
    
    private ArrayList<Phrase> myList;
    
    public PhrasesTableModel(List<Phrase> phrasesList) {
        myList = (ArrayList<Phrase>) phrasesList;
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
    
    public Boolean existingPhrase(String phrase) {
        for (Phrase phraseInTable : myList) {
                if (phraseInTable.getPhraseText().equals(phrase)) {
                    return true;
                }
            }
        return false;
    }
    
    public void addRow(Phrase newPhrase) {
        myList.add(newPhrase);
        int row = myList.indexOf(newPhrase);
        for(int column = 0; column < myList.size(); column++) {
            fireTableCellUpdated(row, column);
        }
        fireTableRowsInserted(row, row);
    }    
    
        
    public Phrase getPhraseByRow(int rowIndex)
    {
        return myList.get(rowIndex);
    }
}
