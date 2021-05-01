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
public class WordAppearance {
    private int songId;
    private int wordId;
    private String songName;
    private int verseNumber;
    private int lineNumberInVerse;
    private int wordNumberInVerse;
    private int wordNumberInSong;
    private int wordNumberInLine;
    private int lineNumberInSong;
    private String songPath;
    private int wordPosition;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
    
    public int getWordPosition() {
        return wordPosition;
    }

    public void setWordPosition(int wordPosition) {
        this.wordPosition = wordPosition;
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

    public int getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(int verseNumber) {
        this.verseNumber = verseNumber;
    }

    public int getLineNumberInVerse() {
        return lineNumberInVerse;
    }

    public void setLineNumberInVerse(int lineNumberInVerse) {
        this.lineNumberInVerse = lineNumberInVerse;
    }

    public int getWordNumberInVerse() {
        return wordNumberInVerse;
    }

    public void setWordNumberInVerse(int wordNumberInVerse) {
        this.wordNumberInVerse = wordNumberInVerse;
    }

    public int getWordNumberInSong() {
        return wordNumberInSong;
    }

    public void setWordNumberInSong(int wordNumberInSong) {
        this.wordNumberInSong = wordNumberInSong;
    }

    public int getWordNumberInLine() {
        return wordNumberInLine;
    }

    public void setWordNumberInLine(int wordNumberInLine) {
        this.wordNumberInLine = wordNumberInLine;
    }

    public int getLineNumberInSong() {
        return lineNumberInSong;
    }

    public void setLineNumberInSong(int lineNumberInSong) {
        this.lineNumberInSong = lineNumberInSong;
    }
    
    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
    
}
