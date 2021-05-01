/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Marina Bykov
 */
public class UploadPage extends javax.swing.JFrame {
    SQLqueries connectedDB;
    List<Song> songsInDB;
 
    /**
     * Creates new form UploadPage
     */
    public UploadPage(SQLqueries currentDB) {
        this.connectedDB = currentDB;
        try { 
            songsInDB = connectedDB.getAllSongs();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
            ex.getMessage(),
            "Failed to get songs from DB",
            JOptionPane.ERROR_MESSAGE);
            songsInDB = new ArrayList<Song>();
        }
        initComponents();
        /*Stream.of(tableObject).forEach(song -> {
        DefaultTableModel model = (DefaultTableModel)songsTable.getModel();
        model.addRow(song);
        });*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        file = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        songsTable = new javax.swing.JTable();
        addSong = new javax.swing.JButton();
        songName = new javax.swing.JLabel();
        songNameField = new javax.swing.JTextField();
        writerField = new javax.swing.JTextField();
        writer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        file.setText("File:");

        fileTextField.setForeground(new java.awt.Color(102, 102, 102));

        browseButton.setText("Browse file");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        songsTable.setModel(new SongsTableModel(songsInDB));
        jScrollPane1.setViewportView(songsTable);

        addSong.setText("Add song");
        addSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongActionPerformed(evt);
            }
        });

        songName.setText("Song Name:");

        writer.setText("Writer:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(writer)
                            .addGap(34, 34, 34)
                            .addComponent(writerField, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(addSong))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(songName)
                            .addComponent(file))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fileTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                            .addComponent(songNameField))
                        .addGap(27, 27, 27)
                        .addComponent(browseButton)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(songName)
                    .addComponent(songNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browseButton)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(file)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(writer)
                    .addComponent(addSong)
                    .addComponent(writerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        FileNameExtensionFilter fileFilter=new FileNameExtensionFilter("txt", "txt");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if(!Objects.isNull(file))
        {
            String fileName = file.getAbsolutePath();
            fileTextField.setText(fileName);
        }
    }//GEN-LAST:event_browseButtonActionPerformed
 
    private void addSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongActionPerformed
        if(fileTextField.getText().isEmpty() || fileTextField.getText().isBlank() ||
           songNameField.getText().isEmpty() || songNameField.getText().isBlank() || 
           writerField.getText().isEmpty() || writerField.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,
            "All fields are mandatory!",
            "Empty mandatory fields",
            JOptionPane.ERROR_MESSAGE);
        }
        
        else {
            Song songToAdd = new Song();
            songToAdd.setFilePath(fileTextField.getText());
            songToAdd.setSongName(songNameField.getText());
            songToAdd.setSongWriter(writerField.getText());  
        
            //Try and catch add song to db. If added, add row in ui:
            try {
                connectedDB.insertBook(songToAdd);      
                songToAdd.setDateOfAdd(new Date());
                SongsTableModel model = (SongsTableModel)songsTable.getModel();
                model.addRow(songToAdd);
                fileTextField.setText(null);
                songNameField.setText(null);
                writerField.setText(null);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Failed to open file",
                JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Failed to add song to DB",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addSongActionPerformed

    /*public void addRowToTable(Object [] rowToAdd)
    {
        DefaultTableModel model = (DefaultTableModel)songsTable.getModel();
        model.addRow(rowToAdd);
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSong;
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel file;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel songName;
    private javax.swing.JTextField songNameField;
    private static javax.swing.JTable songsTable;
    private javax.swing.JLabel writer;
    private javax.swing.JTextField writerField;
    // End of variables declaration//GEN-END:variables
}