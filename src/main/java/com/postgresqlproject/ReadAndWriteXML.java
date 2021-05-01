/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Marina Bykov
 */
public class ReadAndWriteXML {
    
    public ReadAndWriteXML(){
        
    }
    
    public void exportEntriesToXML(SQLqueries connectedDB) throws ParserConfigurationException, FileNotFoundException, TransformerConfigurationException, TransformerException, SQLException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement("tables");
        document.appendChild(rootElement);
        Element wordsTable = document.createElement("words_table");
        rootElement.appendChild(wordsTable);
        //Get all words and add to xml
        List<Word> words = connectedDB.getAllWords();
        for(Word word: words)
        {
            Element newWordElement = document.createElement("word");
            Element wordId = document.createElement("word_id");
            wordId.appendChild(document.createTextNode(Integer.toString(word.getWordId())));
            Element wordText = document.createElement("word_text");
            wordText.appendChild(document.createTextNode(word.getWordText()));
            Element wordLength = document.createElement("length");
            wordLength.appendChild(document.createTextNode(Integer.toString(word.getWordLength())));
            newWordElement.appendChild(wordId);
            newWordElement.appendChild(wordText);
            newWordElement.appendChild(wordLength);
            wordsTable.appendChild(newWordElement);
        }
        
        //Get songs table
        Element songsTable = document.createElement("songs_table");
        rootElement.appendChild(songsTable);
        List<Song> songs = connectedDB.getAllSongs();
        for(Song song: songs)
        {
            Element newSongElement = document.createElement("song");
            Element songId = document.createElement("song_id");
            songId.appendChild(document.createTextNode(Integer.toString(song.getSongId())));
            Element songName = document.createElement("song_name");
            songName.appendChild(document.createTextNode(song.getSongName()));
            Element linkToFile = document.createElement("link_to_file");
            linkToFile.appendChild(document.createTextNode(song.getFilePath()));
            Element songWriter = document.createElement("song_writer");
            songWriter.appendChild(document.createTextNode(song.getSongWriter()));
            Element date = document.createElement("date_of_add");
            date.appendChild(document.createTextNode(song.getDateOfAdd().toString()));
            
            newSongElement.appendChild(songId);
            newSongElement.appendChild(songName);
            newSongElement.appendChild(linkToFile);
            newSongElement.appendChild(songWriter);
            newSongElement.appendChild(date);
            songsTable.appendChild(newSongElement);
        }
        
        //Get words_in_songs_table table
        Element appearancesTable = document.createElement("words_in_songs_table");
        rootElement.appendChild(appearancesTable);
        List<WordAppearance> wordAppearances = connectedDB.getAllWordAppearances();
        
        for(WordAppearance appearance: wordAppearances)
        {
            Element newAppearanceElement = document.createElement("word_appearance");
            Element songId = document.createElement("song_id");
            songId.appendChild(document.createTextNode(Integer.toString(appearance.getSongId())));
            Element wordId = document.createElement("word_id");
            wordId.appendChild(document.createTextNode(Integer.toString(appearance.getWordId())));
            Element verseNumber = document.createElement("verse");
            verseNumber.appendChild(document.createTextNode(Integer.toString(appearance.getVerseNumber())));
            Element lineInVerse = document.createElement("line_in_verse");
            lineInVerse.appendChild(document.createTextNode(Integer.toString(appearance.getLineNumberInVerse())));
            Element wordNumberInVerse = document.createElement("word_number_in_verse");
            wordNumberInVerse.appendChild(document.createTextNode(Integer.toString(appearance.getWordNumberInVerse())));
            Element wordNumberInSong = document.createElement("word_number_in_song");
            wordNumberInSong.appendChild(document.createTextNode(Integer.toString(appearance.getWordNumberInSong())));
            Element wordNumberInLine = document.createElement("word_number_in_line");
            wordNumberInLine.appendChild(document.createTextNode(Integer.toString(appearance.getWordNumberInLine())));
            Element lineNumberInSong = document.createElement("line_in_song");
            lineNumberInSong.appendChild(document.createTextNode(Integer.toString(appearance.getLineNumberInSong())));
            Element wordPosition = document.createElement("word_position");
            wordPosition.appendChild(document.createTextNode(Integer.toString(appearance.getWordPosition())));
            
            newAppearanceElement.appendChild(songId);
            newAppearanceElement.appendChild(wordId);
            newAppearanceElement.appendChild(verseNumber);
            newAppearanceElement.appendChild(lineInVerse);
            newAppearanceElement.appendChild(wordNumberInVerse);
            newAppearanceElement.appendChild(wordNumberInSong);
            newAppearanceElement.appendChild(wordNumberInLine);
            newAppearanceElement.appendChild(lineNumberInSong);
            newAppearanceElement.appendChild(wordPosition);
            
            appearancesTable.appendChild(newAppearanceElement);
        }
        
        //Get groups table
        Element groupsTable = document.createElement("groups_table");
        rootElement.appendChild(groupsTable);
        List<Group> groups = connectedDB.getGroups();
        for(Group group: groups)
        {
            Element newGroupElement = document.createElement("group");
            Element groupId = document.createElement("group_id");
            groupId.appendChild(document.createTextNode(Integer.toString(group.getGroupId())));
            Element groupName = document.createElement("name");
            groupName.appendChild(document.createTextNode(group.getGroupName()));
            
            newGroupElement.appendChild(groupId);
            newGroupElement.appendChild(groupName);
            groupsTable.appendChild(newGroupElement);
        }
        
        
        //Get group members table
        Element membersTable = document.createElement("members_table");
        rootElement.appendChild(membersTable);
        List<Member> members = connectedDB.getAllMembers();
        for(Member member: members)
        {
            Element newMemberElement = document.createElement("member");
            Element groupId = document.createElement("group_id");
            groupId.appendChild(document.createTextNode(Integer.toString(member.getGroupId())));
            Element wordId = document.createElement("word_id");
            wordId.appendChild(document.createTextNode(Integer.toString(member.getWordId())));
            
            newMemberElement.appendChild(groupId);
            newMemberElement.appendChild(wordId);
            membersTable.appendChild(newMemberElement);
        }
        
        //Get phrases table
        Element phrasesTable = document.createElement("phrases_table");
        rootElement.appendChild(phrasesTable);
        List<Phrase> phrases = connectedDB.getPhrases();
        for(Phrase phrase: phrases)
        {
            Element newPhraseElement = document.createElement("phrase");
            Element phraseId = document.createElement("phrase_id");
            phraseId.appendChild(document.createTextNode(Integer.toString(phrase.getPhraseId())));
            Element phraseText = document.createElement("phrase_text");
            phraseText.appendChild(document.createTextNode(phrase.getPhraseText()));
            Element numberOfWords = document.createElement("number_of_words");
            numberOfWords.appendChild(document.createTextNode(Integer.toString(phrase.getNumberOfWords())));
            
            newPhraseElement.appendChild(phraseId);
            newPhraseElement.appendChild(phraseText);
            newPhraseElement.appendChild(numberOfWords);
            phrasesTable.appendChild(newPhraseElement);
        }
        
        //Get all phrases consist table getAllConsist
        Element consistTable = document.createElement("consist_table");
        rootElement.appendChild(consistTable);
        List<Consist> consistRows = connectedDB.getAllConsist();
        for(Consist consistRow: consistRows)
        {
            Element newConsistElement = document.createElement("consist");
            Element phraseId = document.createElement("phrase_id");
            phraseId.appendChild(document.createTextNode(Integer.toString(consistRow.getPhraseId())));
            Element wordId = document.createElement("word_id");
            wordId.appendChild(document.createTextNode(Integer.toString(consistRow.getWordId())));
            Element wordNumber = document.createElement("word_number");
            wordNumber.appendChild(document.createTextNode(Integer.toString(consistRow.getWordNumber())));
            
            newConsistElement.appendChild(phraseId);
            newConsistElement.appendChild(wordId);
            newConsistElement.appendChild(wordNumber);
            consistTable.appendChild(newConsistElement);
        }
        
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        
        //Show browse save folder and file name. Create and save xml with this file name and path
        FileNameExtensionFilter fileFilter=new FileNameExtensionFilter("xml", "xml");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(fileFilter);
        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if(Objects.nonNull(fileToSave)){
                StreamResult streamResult;
                if (!fileToSave.getName().toLowerCase().endsWith(".xml")) {
                    streamResult = new StreamResult(new File(fileChooser.getSelectedFile()+ ".xml"));
                }
                else {
                    streamResult = new StreamResult(new File(fileChooser.getSelectedFile().toString()));
                }

                transformer.transform(domSource, streamResult);
            }
            
        }
        
    }
    
    public void importEntriesFromXML(SQLqueries connectedDB, File fileImport) throws ParserConfigurationException, SAXException, IOException, SQLException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        DocumentBuilder db = dbf.newDocumentBuilder();  
        Document doc = db.parse(fileImport);  
        doc.getDocumentElement().normalize(); 
        
        //Get all words from xml to list
        NodeList wordsTable = doc.getElementsByTagName("word");
        List<Word> words = new ArrayList<Word>();
        //save maxId for each table that has sequence in order to create sequence after inport
        int maxWordId = 0;

        for (int itr = 0; itr < wordsTable.getLength(); itr++)   {  
            Node node = wordsTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                Word word = new Word();
                word.setWordId(Integer.parseInt(eElement.getElementsByTagName("word_id").item(0).getTextContent()));  
                word.setWordText(eElement.getElementsByTagName("word_text").item(0).getTextContent());  
                word.setWordLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
                words.add(word);
                maxWordId = (maxWordId < word.getWordId()) ? word.getWordId() : maxWordId;
            }
        }
        
        //Get all songs from xml to list
        NodeList songsTable = doc.getElementsByTagName("song");
        List<Song> songs = new ArrayList<Song>();
        int maxSongId = 0;
        
        for (int itr = 0; itr < songsTable.getLength(); itr++)   {  
            Node node = songsTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                Song song = new Song();
                song.setSongId(Integer.parseInt(eElement.getElementsByTagName("song_id").item(0).getTextContent()));  
                song.setSongName(eElement.getElementsByTagName("song_name").item(0).getTextContent());  
                song.setFilePath(eElement.getElementsByTagName("link_to_file").item(0).getTextContent());
                song.setSongWriter(eElement.getElementsByTagName("song_writer").item(0).getTextContent());
                song.setDateOfAdd(Date.valueOf(eElement.getElementsByTagName("date_of_add").item(0).getTextContent()));
                songs.add(song);
                maxSongId = (maxSongId < song.getSongId()) ? song.getSongId() : maxSongId;
            }
        }
        
        //Get all word apperances from xml to list
        NodeList wordAppearanceTable = doc.getElementsByTagName("word_appearance");
        List<WordAppearance> wordAppearances = new ArrayList<WordAppearance>();
        
        for (int itr = 0; itr < wordAppearanceTable.getLength(); itr++)   {  
            Node node = wordAppearanceTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                WordAppearance appearance = new WordAppearance();
                appearance.setSongId(Integer.parseInt(eElement.getElementsByTagName("song_id").item(0).getTextContent()));  
                appearance.setWordId(Integer.parseInt(eElement.getElementsByTagName("word_id").item(0).getTextContent()));  
                appearance.setVerseNumber(Integer.parseInt(eElement.getElementsByTagName("verse").item(0).getTextContent())); 
                appearance.setLineNumberInVerse(Integer.parseInt(eElement.getElementsByTagName("line_in_verse").item(0).getTextContent())); 
                appearance.setWordNumberInVerse(Integer.parseInt(eElement.getElementsByTagName("word_number_in_verse").item(0).getTextContent())); 
                appearance.setWordNumberInSong(Integer.parseInt(eElement.getElementsByTagName("word_number_in_song").item(0).getTextContent()));
                appearance.setWordNumberInLine(Integer.parseInt(eElement.getElementsByTagName("word_number_in_line").item(0).getTextContent()));
                appearance.setLineNumberInSong(Integer.parseInt(eElement.getElementsByTagName("line_in_song").item(0).getTextContent()));
                appearance.setWordPosition(Integer.parseInt(eElement.getElementsByTagName("word_position").item(0).getTextContent()));
                wordAppearances.add(appearance);
            }
        }
        
        //Get all groups from xml to list
        NodeList groupTable = doc.getElementsByTagName("group");
        List<Group> groups = new ArrayList<Group>();
        int maxGroupId = 0;

        for (int itr = 0; itr < groupTable.getLength(); itr++)   {  
            Node node = groupTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                Group group = new Group();
                group.setGroupId(Integer.parseInt(eElement.getElementsByTagName("group_id").item(0).getTextContent()));  
                group.setGroupName(eElement.getElementsByTagName("name").item(0).getTextContent());  
                groups.add(group);
                maxGroupId = (maxGroupId < group.getGroupId()) ? group.getGroupId() : maxGroupId;
            }
        }
        
        //Get all group members from xml to list
        NodeList memberTable = doc.getElementsByTagName("member");
        List<Member> members = new ArrayList<Member>();

        for (int itr = 0; itr < memberTable.getLength(); itr++)   {  
            Node node = memberTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                Member member = new Member();
                member.setGroupId(Integer.parseInt(eElement.getElementsByTagName("group_id").item(0).getTextContent()));  
                member.setWordId(Integer.parseInt(eElement.getElementsByTagName("word_id").item(0).getTextContent())); 
                members.add(member);
            }
        }
        
        //Get all phrases from xml to list
        NodeList phrasesTable = doc.getElementsByTagName("phrase");
        List<Phrase> phrases = new ArrayList<Phrase>();
        int maxPhraseId = 0;

        for (int itr = 0; itr < phrasesTable.getLength(); itr++)   {  
            Node node = phrasesTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                Phrase phrase = new Phrase();
                phrase.setPhraseId(Integer.parseInt(eElement.getElementsByTagName("phrase_id").item(0).getTextContent()));  
                phrase.setPhraseText(eElement.getElementsByTagName("phrase_text").item(0).getTextContent());  
                phrase.setNumberOfWords(Integer.parseInt(eElement.getElementsByTagName("number_of_words").item(0).getTextContent()));
                phrases.add(phrase);
                maxPhraseId = (maxPhraseId < phrase.getPhraseId()) ? phrase.getPhraseId() : maxPhraseId;
            }
        }
        
        //Get all consist from xml to list
        NodeList consistTable = doc.getElementsByTagName("consist");
        List<Consist> consistRows = new ArrayList<Consist>();

        for (int itr = 0; itr < consistTable.getLength(); itr++)   {  
            Node node = consistTable.item(itr);  
            if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                Consist consist = new Consist();
                consist.setPhraseId(Integer.parseInt(eElement.getElementsByTagName("phrase_id").item(0).getTextContent()));  
                consist.setWordId(Integer.parseInt(eElement.getElementsByTagName("word_id").item(0).getTextContent())); 
                consist.setWordNumber(Integer.parseInt(eElement.getElementsByTagName("word_number").item(0).getTextContent())); 
                consistRows.add(consist);
            }
        }
        
        //Impost collected data to db
        connectedDB.importTables(words, songs, wordAppearances, groups, members,
                phrases, consistRows, maxWordId, maxSongId, maxGroupId, maxPhraseId);

    }
    
}
