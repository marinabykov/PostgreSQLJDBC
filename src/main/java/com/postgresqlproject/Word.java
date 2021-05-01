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
public class Word {
    String text;
    int wordId;
    int wordLength;
    
    
    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }
    

    public String getWordText() {
        return text;
    }

    public void setWordText(String word) {
        this.text = word;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
    
    public String toString()
    {
        return text;
    }
}
