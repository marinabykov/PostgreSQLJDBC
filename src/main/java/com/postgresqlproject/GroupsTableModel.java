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
public class GroupsTableModel extends AbstractTableModel{
    private String[] columnNames = {
                "Group name"
            };
    
    private ArrayList<Group> myList;
    
    public GroupsTableModel(List<Group> groupsList) {
        myList = (ArrayList<Group>) groupsList;
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
    
    public Group getGroupByRow(int rowIndex)
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
    
    public Boolean existingGroup(String groupName) {
        for (Group group : myList) {
                if (groupName.equals(group.getGroupName())) {
                    return true;
                }
            }
        return false;
    }
    
    public void addRow(Group newGroup) {
        myList.add(newGroup);
        int row = myList.indexOf(newGroup);
        for(int column = 0; column < myList.size(); column++) {
            fireTableCellUpdated(row, column);
        }
        fireTableRowsInserted(row, row);
    }    
}
