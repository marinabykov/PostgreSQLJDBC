/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marina Bykov
 */
public class WordAppearancesPage extends javax.swing.JFrame {
    private List<WordAppearance> wordAppearances;
    private String word;
    /**
     * Creates new form WordAppearancesPage
     */
    public WordAppearancesPage(String word, List<WordAppearance> wordAppearances) {
        this.word = word;
        this.wordAppearances = wordAppearances;
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
        wordAppearancesTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        songText = new javax.swing.JTextArea();
        firstLabel = new javax.swing.JLabel();
        secondLavel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        wordAppearancesTable.setModel(new WordAppearancesTableModel(wordAppearances));
        wordAppearancesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wordAppearancesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(wordAppearancesTable);

        songText.setColumns(20);
        songText.setRows(5);
        jScrollPane2.setViewportView(songText);

        firstLabel.setText("In this table you can see all appearances of word: " + word);

        secondLavel.setText("Please select row to see word in text.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(secondLavel)
                    .addComponent(firstLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(firstLabel)
                .addGap(18, 18, 18)
                .addComponent(secondLavel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wordAppearancesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wordAppearancesTableMouseClicked
        //get selected word appearance from table
        int row = wordAppearancesTable.getSelectedRow();
        WordAppearancesTableModel model = 
                (WordAppearancesTableModel)wordAppearancesTable.getModel();    
        WordAppearance selectedWordAppearance = model.getWordAppearanceByRow(row);
        //Open text of selectedWordAppearance file 
        Reader reader = null;
        try {
            int wordPosition = selectedWordAppearance.getWordPosition();
            reader = new FileReader(new File(selectedWordAppearance.getSongPath()));
            songText.read(reader, null);
            Rectangle viewRect = songText.modelToView(wordPosition);
            // Scroll to make the rectangle visible
            songText.scrollRectToVisible(viewRect);
            // Focus the text area, otherwise the highlighting won't show up
            songText.requestFocusInWindow();
            // Highlight the text
            songText.setCaretPosition(wordPosition + word.length());
            songText.moveCaretPosition(wordPosition);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
            "Failed to open text file",
            "Failed to open text file",
            JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_wordAppearancesTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel firstLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel secondLavel;
    private javax.swing.JTextArea songText;
    private javax.swing.JTable wordAppearancesTable;
    // End of variables declaration//GEN-END:variables
}