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
public class PhraseAppearance {
    
    int songId;
    String songName;
    String filePath;
    int startPosition;
    int startPositionLastWord;
    int appearanceNumberInSong;
    
    public PhraseAppearance(int songId, String songName, String filePath, 
                            int startPosition, int startPositionLastWord, 
                            int appearanceNumberInSong) {
            this.songId = songId;
            this.songName = songName;
            this.filePath = filePath;
            this.startPosition = startPosition;
            this.startPositionLastWord = startPositionLastWord;
            this.appearanceNumberInSong = appearanceNumberInSong;
    }
    
    public int getAppearanceNumberInSong() {
        return appearanceNumberInSong;
    }

    public void setAppearanceNumberInSong(int appearanceNumberInSong) {
        this.appearanceNumberInSong = appearanceNumberInSong;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getStartPositionLastWord() {
        return startPositionLastWord;
    }

    public void setStartPositionLastWord(int startPositionLastWord) {
        this.startPositionLastWord = startPositionLastWord;
    }
    
}
