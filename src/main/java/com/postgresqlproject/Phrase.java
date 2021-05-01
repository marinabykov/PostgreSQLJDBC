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
public class Phrase {
    String phraseText;
    int numberOfWords;
    int phraseId;
    String[] wordsOfPhrase;
    int lastWordLength;

    public Phrase() {
    }
    
    public Phrase(String phrase, int numberOfWords, int phraseId) {
        this.phraseText = phrase;
        this.numberOfWords = numberOfWords;
        this.phraseId = phraseId;
        this.wordsOfPhrase = phrase.split(" ");
        this.lastWordLength = wordsOfPhrase[wordsOfPhrase.length - 1].length();
    }
    
    public Phrase(String phrase, int phraseId) {
        this.phraseText = phrase;
        this.phraseId = phraseId;
        this.wordsOfPhrase = phrase.split(" ");
        this.numberOfWords = wordsOfPhrase.length;
        this.lastWordLength = wordsOfPhrase[wordsOfPhrase.length - 1].length();
    }

    public int getLastWordLength() {
        return lastWordLength;
    }

    public String getPhraseText() {
        return phraseText;
    }

    public void setPhraseText(String phrase) {
        this.phraseText = phrase;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public int getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(int phraseId) {
        this.phraseId = phraseId;
    }
    
    public String toString()
    {
        return phraseText;
    }
}
