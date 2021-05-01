/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marina Bykov
 */
public class GroupsPage extends javax.swing.JFrame {

    private List<Group> groups;
    private List<Word> words;
    private List<Word> groupMembers;
    private SQLqueries connectedDB;
    /**
     * Creates new form GroupsPage
     */
    public GroupsPage(SQLqueries currentDB) {
        this.connectedDB = currentDB;
        try {
            groups = connectedDB.getGroups();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
            ex.getMessage(),
            "Failed to get groups from db",
            JOptionPane.ERROR_MESSAGE);
            groups = new ArrayList<Group>();
        }
        
        try {
            words = connectedDB.getAllWords();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
            ex.getMessage(),
            "Failed to get words from db",
            JOptionPane.ERROR_MESSAGE);
            words = new ArrayList<Word>();
        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        groupsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        wordsTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        wordsInGroupTable = new javax.swing.JTable();
        newGroupTextField = new javax.swing.JTextField();
        groupLabel = new javax.swing.JLabel();
        addGroupButton = new javax.swing.JButton();
        wordLabel = new javax.swing.JLabel();
        wordToAddTextField = new javax.swing.JTextField();
        addWordButton = new javax.swing.JButton();
        selectedGroupLabel = new javax.swing.JLabel();
        groupPageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        groupsTable.setModel(new GroupsTableModel(groups));
        groupsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                groupsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(groupsTable);

        wordsTable.setModel(new WordsTableModel(words));
        wordsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wordsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(wordsTable);

        wordsInGroupTable.setModel(new WordsTableModel(groupMembers));
        jScrollPane3.setViewportView(wordsInGroupTable);

        newGroupTextField.setToolTipText("");

        groupLabel.setText("Create group:");

        addGroupButton.setText("Create group");
        addGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addGroupButtonMouseClicked(evt);
            }
        });

        wordLabel.setText("Add word or select from existing:");

        wordToAddTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                wordToAddTextFieldKeyTyped(evt);
            }
        });

        addWordButton.setText("Add word");
        addWordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addWordButtonMouseClicked(evt);
            }
        });

        selectedGroupLabel.setText("Words in selected group:");

        groupPageLabel.setText("In this page you can edit existing groups or create new. Select group to see group members.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(groupPageLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(groupLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newGroupTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addGroupButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wordLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(wordToAddTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addWordButton)))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectedGroupLabel)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(37, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(groupPageLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupLabel)
                    .addComponent(wordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedGroupLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newGroupTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addGroupButton)
                        .addComponent(wordToAddTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addWordButton)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addGroupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addGroupButtonMouseClicked
        String groupName = newGroupTextField.getText();
        GroupsTableModel model = (GroupsTableModel)groupsTable.getModel();
        if(groupName.isEmpty() || groupName.isBlank()) {
            JOptionPane.showMessageDialog(this,
            "Please add group name",
            "Empty group name",
            JOptionPane.ERROR_MESSAGE);
        }
        else if (model.existingGroup(groupName)){
            JOptionPane.showMessageDialog(this,
            "Existing group. Please add another group name",
            "Existing group",
            JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                int groupId = connectedDB.createGroup(groupName);
                Group newGroup = new Group();
                newGroup.setGroupId(groupId);
                newGroup.setGroupName(groupName);
                model.addRow(newGroup);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                "Failed to add new group",
                "Failed to add new group",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addGroupButtonMouseClicked

    private void addWordButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addWordButtonMouseClicked
        
        //Add word to group was clicked
        int column = 0;
        int row = groupsTable.getSelectedRow();
        Word newGroupMember = new Word();
        WordsTableModel allWordsModel = (WordsTableModel)wordsTable.getModel();
        
        if(row == -1)
        {
            //Show error message if group wasn't selected
            JOptionPane.showMessageDialog(this,
            "Please select group where you want to add member",
            "Select group",
            JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            String wordToAdd = wordToAddTextField.getText();
            //Show error message if didn't write word to add and didn't select word from existing
            if((wordToAdd.isEmpty() || wordToAdd.isBlank()) && wordsTable.getSelectedRow() == -1 ) {
                JOptionPane.showMessageDialog(this,
                "Please add word that you want to add to group or select from list of words",
                "Please select word",
                JOptionPane.ERROR_MESSAGE);
            }
            else {
                //If selected word from list
                if(!(wordsTable.getSelectedRow() == -1))
                {
                    newGroupMember = (Word) allWordsModel.getValueAt(wordsTable.getSelectedRow(), column);
                    
                }
                //If want to add new word
                //and word already exist in list. So select it from list
                else if(allWordsModel.existingWord(wordToAdd)) {
                        newGroupMember = allWordsModel.getWordFromTable(wordToAdd);
                }    
                //Word doesnt exist in list - will add to db
                else {
                    try {
                        int wordId = connectedDB.addWord(wordToAdd);
                        newGroupMember.setWordText(wordToAdd);
                        newGroupMember.setWordId(wordId);
                        allWordsModel.addRow(newGroupMember);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this,
                        "Failed to add new word to DB",
                        "Failed to add new word",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
                //Add this word to group members list and db
                Group selectedGroup = (Group) groupsTable.getModel().getValueAt(groupsTable.getSelectedRow(), column);
                try {
                    connectedDB.addGroupMember(selectedGroup, newGroupMember);
                    WordsTableModel model = (WordsTableModel)wordsInGroupTable.getModel();
                    model.addRow(newGroupMember);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this,
                    "Failed to add word to group, please check if altready exist in group members.",
                    "Failed to add word to group",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }//GEN-LAST:event_addWordButtonMouseClicked

    private void wordsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wordsTableMouseClicked
        //Clear word text field if selected word from existing in table
        wordToAddTextField.setText(null);
    }//GEN-LAST:event_wordsTableMouseClicked

    private void wordToAddTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordToAddTextFieldKeyTyped
        //Clear selection of words table if started to type word to add
        wordsTable.clearSelection();
    }//GEN-LAST:event_wordToAddTextFieldKeyTyped

    private void groupsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupsTableMouseClicked
        //Add new group
        int column = 0;
        Group selectedGroup = (Group) groupsTable.getModel().getValueAt(groupsTable.getSelectedRow(), column);
        try {
            groupMembers = connectedDB.getGroupMembers(selectedGroup);
            wordsInGroupTable.setModel(new WordsTableModel(groupMembers));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
            "Failed to get group members",
            "Failed to get group members",
            JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_groupsTableMouseClicked

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGroupButton;
    private javax.swing.JButton addWordButton;
    private javax.swing.JLabel groupLabel;
    private javax.swing.JLabel groupPageLabel;
    private javax.swing.JTable groupsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField newGroupTextField;
    private javax.swing.JLabel selectedGroupLabel;
    private javax.swing.JLabel wordLabel;
    private javax.swing.JTextField wordToAddTextField;
    private javax.swing.JTable wordsInGroupTable;
    private javax.swing.JTable wordsTable;
    // End of variables declaration//GEN-END:variables
}