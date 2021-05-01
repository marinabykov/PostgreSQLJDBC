/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postgresqlproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author Marina Bykov
 */
public class SQLqueries {
    
    
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "123456";
    
    
    Connection conn = this.connect();      
      
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
            "Failed to connect to DB:" + e,
            "Failed to connect to DB",
            JOptionPane.ERROR_MESSAGE);
        }

        return conn;
    }
      
    public void closeConnection() throws SQLException {
        conn.close();
    }
      
    
    public void createTablesAndSequences() throws SQLException, NullPointerException{
        String songsSequence = "CREATE SEQUENCE IF NOT EXISTS songs_increment\n" +
                                "  INCREMENT 1\n" +
                                "  MINVALUE 1\n" +
                                "  MAXVALUE 9223372036854775807\n" +
                                "  START 1\n" +
                                "  CACHE 1;";
        
        String wordsSequence = "CREATE SEQUENCE IF NOT EXISTS words_increment\n" +
                                "  INCREMENT 1\n" +
                                "  MINVALUE 1\n" +
                                "  MAXVALUE 9223372036854775807\n" +
                                "  START 1\n" +  
                                "  CACHE 1";
        
        String groupsSequence = "CREATE SEQUENCE IF NOT EXISTS groups_increment\n" +
                                "  INCREMENT 1\n" +
                                "  MINVALUE 1\n" +
                                "  MAXVALUE 9223372036854775807\n" +
                                "  START 1\n" + 
                                "  CACHE 1;";
        
        String phrasesSequence = "CREATE SEQUENCE IF NOT EXISTS phrases_increment\n" +
                                 "  INCREMENT 1\n" +
                                 "  MINVALUE 1\n" +
                                 "  MAXVALUE 9223372036854775807\n" +
                                 "  START 1\n" +
                                 "  CACHE 1;";
        
        String songsTable = "CREATE TABLE IF NOT EXISTS songs\n" +
                            "(\n" +
                            "  song_id integer NOT NULL DEFAULT nextval('songs_increment'::regclass),\n" +
                            "  song_name text,\n" +
                            "  link_to_file text,\n" +
                            "  song_writer text,\n" +
                            "  date_of_add date DEFAULT ('now'::text)::date,\n" +
                            "  CONSTRAINT \"Songs_pkey\" PRIMARY KEY (song_id)\n" +
                            ")";
        
        String wordsTable = "CREATE TABLE IF NOT EXISTS words\n" +
                            "(\n" +
                            "  word_id integer NOT NULL DEFAULT nextval('words_increment'::regclass),\n" +
                            "  word text,\n" +
                            "  length integer,\n" +
                            "  CONSTRAINT words_pkey PRIMARY KEY (word_id)\n" +
                            ")";
        
        String wordsAppearanceTable =   "CREATE TABLE IF NOT EXISTS words_in_songs\n" +
                                        "(\n" +
                                        "  word_id integer NOT NULL,\n" +
                                        "  song_id integer NOT NULL,\n" +
                                        "  verse integer,\n" +
                                        "  line_in_verse integer,\n" +
                                        "  word_number_in_verse integer,\n" +
                                        "  word_number_in_song integer NOT NULL,\n" +
                                        "  word_number_in_line integer,\n" +
                                        "  line_in_song integer,\n" +
                                        "  word_position integer,\n" +
                                        "  CONSTRAINT words_in_songs_pkey PRIMARY KEY (word_number_in_song, word_id, song_id),\n" +
                                        "  CONSTRAINT words_in_songs_song_id_fkey FOREIGN KEY (song_id)\n" +
                                        "      REFERENCES public.songs (song_id) MATCH SIMPLE\n" +
                                        "      ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                                        "  CONSTRAINT words_in_songs_word_id_fkey FOREIGN KEY (word_id)\n" +
                                        "      REFERENCES public.words (word_id) MATCH SIMPLE\n" +
                                        "      ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                                        ")";
        
        String groupsTable = "CREATE TABLE IF NOT EXISTS groups\n" +
                            "(\n" +
                            "  group_id integer NOT NULL DEFAULT nextval('groups_increment'::regclass),\n" +
                            "  name text,\n" +
                            "  CONSTRAINT groups_pkey PRIMARY KEY (group_id)\n" +
                            ")";
        
        String membersTable =   "CREATE TABLE IF NOT EXISTS member\n" +
                                "(\n" +
                                "  group_id integer NOT NULL,\n" +
                                "  word_id integer NOT NULL,\n" +
                                "  CONSTRAINT member_pkey PRIMARY KEY (group_id, word_id),\n" +
                                "  CONSTRAINT member_group_id_fkey FOREIGN KEY (group_id)\n" +
                                "      REFERENCES public.groups (group_id) MATCH SIMPLE\n" +
                                "      ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                                "  CONSTRAINT member_word_id_fkey FOREIGN KEY (word_id)\n" +
                                "      REFERENCES public.words (word_id) MATCH SIMPLE\n" +
                                "      ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                                ")";
        
        String phrasesTable =   "CREATE TABLE IF NOT EXISTS phrases\n" +
                                "(\n" +
                                "  phrase_id integer NOT NULL DEFAULT nextval('phrases_increment'::regclass),\n" +
                                "  phrase text,\n" +
                                "  number_of_words integer,\n" +
                                "  CONSTRAINT phrases_pkey PRIMARY KEY (phrase_id)\n" +
                                ")";
        
        String consistTable =   "CREATE TABLE IF NOT EXISTS consist\n" +
                                "(\n" +
                                "  phrase_id integer NOT NULL,\n" +
                                "  word_id integer NOT NULL,\n" +
                                "  word_number integer,\n" +
                                "  CONSTRAINT consist_pkey PRIMARY KEY (phrase_id, word_id, word_number),\n" +
                                "  CONSTRAINT consist_phrase_id_fkey FOREIGN KEY (phrase_id)\n" +
                                "      REFERENCES public.phrases (phrase_id) MATCH SIMPLE\n" +
                                "      ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                                "  CONSTRAINT consist_word_id_fkey FOREIGN KEY (word_id)\n" +
                                "      REFERENCES public.words (word_id) MATCH SIMPLE\n" +
                                "      ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                                ")";
        
        String[] queries = {songsSequence, wordsSequence, groupsSequence, 
            phrasesSequence, songsTable, wordsTable, wordsAppearanceTable, 
            groupsTable, membersTable, phrasesTable,consistTable};
        
        if(Objects.isNull(conn)){
            throw new SQLException();
        }
        
        else {
            for(String query: queries){
                Statement stmt = conn.createStatement();
                stmt.execute(query);
                stmt.close();
            }
        }
      
    }  
    
    //Delete all data in tables
    public void emptyTables() throws SQLException {
        String allTablesTruncate = "TRUNCATE TABLE words_in_songs, songs, consist, phrases, member, groups, words";
        
        Statement stmt = conn.createStatement();
        stmt.execute(allTablesTruncate);
        stmt.close();
    }

    //Update sequencesw with new id's
    public void alterSequences(int wordsId, int songsId, int groupsId, int phrasesId) throws SQLException{
        String songsSequence = "ALTER SEQUENCE songs_increment\n" +
                                "  INCREMENT 1\n" +
                                "  MINVALUE 1\n" +
                                "  MAXVALUE 9223372036854775807\n" +
                                "  RESTART " + songsId +
                                "  CACHE 1;";
        
        String wordsSequence = "ALTER SEQUENCE words_increment\n" +
                                "  INCREMENT 1\n" +
                                "  MINVALUE 1\n" +
                                "  MAXVALUE 9223372036854775807\n" +
                                "  RESTART " + wordsId + 
                                "  CACHE 1";
        
        String groupsSequence = "ALTER SEQUENCE groups_increment\n" +
                                "  INCREMENT 1\n" +
                                "  MINVALUE 1\n" +
                                "  MAXVALUE 9223372036854775807\n" +
                                "  RESTART " + groupsId +
                                "  CACHE 1;";
        
        String phrasesSequence = "ALTER SEQUENCE phrases_increment\n" +
                                 "  INCREMENT 1\n" +
                                 "  MINVALUE 1\n" +
                                 "  MAXVALUE 9223372036854775807\n" +
                                 "  RESTART " + phrasesId +
                                 "  CACHE 1;";
        
        String[] queries = {songsSequence, wordsSequence, groupsSequence, phrasesSequence};
        
        for(String query: queries){
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.close();
        }

    }

     
    public void insertBook(Song songToAdd) throws FileNotFoundException, SQLException 
    {
        int wordNumberInSong = 1; 
        int verse = 1;  
        int lineNumberInVerse = 1; 
        int lineNumberInSong = 1; 
        int wordNumberInVerse = 1; 
        int wordNumberInLine = 1; 
        int lengthOfPreviousLines = 0;
        int lengthOfPreviosWords = 0;
        int wordPositionInLine = 1; 
        int wordPositionInSong =  1; 
        int startPosition = 1; 
        File file = new File(songToAdd.getFilePath());
        Scanner myReader = new Scanner(file);
        int songId = addSong(songToAdd);
        int lineLength = 0;
        int positionOfPrevious = 0;
        int lengthOfPrevious = 0;
        int nextPosition = 0;
        while (myReader.hasNextLine()) {
            wordNumberInLine = 1; 
            String line = myReader.nextLine();
            if(line.isEmpty())
            {
                verse++;
                lineNumberInVerse = 1; 
                wordNumberInVerse = 1; 
                lineLength = 0;
                nextPosition++;
            }
            else
            {
                
                String[] data = line.split(" ");
                startPosition = 0; 
                for (int i = 0; i < data.length; i++)
                {    
                    wordNumberInLine = i + 1; 
                    wordPositionInLine = line.indexOf(data[i], startPosition); 
                    //add +1 to position if start from special character that removed
                    wordPositionInSong = data[i].substring(0, 1).matches("[^A-Za-z0-9 ]") 
                            ? nextPosition + 1 : nextPosition ;
                    //Remove special characters except ' 
                    String word = data[i].replaceAll("[-+^?!.:,\";()]*", "");
                    int wordId = getWordId(word.toLowerCase());
                    addWordInSong(wordId, songId, verse, lineNumberInVerse, 
                            wordNumberInVerse, wordNumberInSong, wordNumberInLine, 
                            lineNumberInSong, wordPositionInSong);
                    wordNumberInVerse++;
                    wordNumberInSong++;
                    startPosition = wordPositionInLine + 1;
                    nextPosition = nextPosition + data[i].length() +1 ;
                    lengthOfPrevious = data[i].length();
                }
                lineLength = line.length();
                lineNumberInVerse++;
                lineNumberInSong++;
                
            }
            lengthOfPreviousLines = lengthOfPreviousLines + lineLength + 2;
        }
        myReader.close();
    }
         
    
    private void addWordInSong(int wordId, int songId, int verseNumber, 
            int lineNumberInVerse, int wordNumberInVerse, int wordNumberInSong, int wordNumberInLine, int lineNumberInSong, int wordPosition) throws SQLException
    {
        String insertQuery = "INSERT INTO words_in_songs (word_id, song_id, verse, "
                + "line_in_verse, word_number_in_verse, word_number_in_song, word_number_in_line, line_in_song, word_position) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setInt(1, wordId);
        stmt.setInt(2, songId);
        stmt.setInt(3, verseNumber);
        stmt.setInt(4, lineNumberInVerse);
        stmt.setInt(5, wordNumberInVerse);
        stmt.setInt(6, wordNumberInSong);
        stmt.setInt(7, wordNumberInLine);
        stmt.setInt(8, lineNumberInSong);
        stmt.setInt(9, wordPosition);

        stmt.executeUpdate();
        stmt.close();
    }
    
    
    public int addWord(String wordToAdd) throws SQLException
    {
        int wordId = 0; 
        String insertQuery = "INSERT INTO words (word, length) "
                + "VALUES (?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, wordToAdd);
        stmt.setInt(2, wordToAdd.length());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
                // get the wordId back
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                wordId = rs.getInt(1);
            }
        }  
        
        stmt.close();
        
        return wordId;
    }
     
    public List<Word> getAllWords() throws SQLException
    {
        List<Word> words = new ArrayList<Word>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * " 
         +"FROM words ");
        while (rs.next()) {
            Word word = new Word();
            word.setWordId(rs.getInt("word_id"));
            word.setWordText(rs.getString("word"));
            word.setWordLength(rs.getInt("length"));
            words.add(word);
         }

        return words;
    }
    
    public void addWordsInBatch(List<Word> words) throws SQLException
    { 
        String insertQuery = "INSERT INTO words (word_id, word, length) "
                + "VALUES (?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(Word wordFromList: words) {
            stmt.setInt(1, wordFromList.getWordId());
            stmt.setString(2, wordFromList.getWordText());
            stmt.setInt(3, wordFromList.getWordLength());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    public void addSongsInBatch(List<Song> songs) throws SQLException
    {
        String insertQuery = "INSERT INTO songs (song_id, song_name, link_to_file,"
                + " song_writer, date_of_add) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(Song songFromList: songs) {
            stmt.setInt(1, songFromList.getSongId());
            stmt.setString(2, songFromList.getSongName());
            stmt.setString(3, songFromList.getFilePath());
            stmt.setString(4, songFromList.getSongWriter());
            stmt.setDate(5, (Date) songFromList.getDateOfAdd());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    public void addAppearancesInBatch(List<WordAppearance> appearances) throws SQLException{
        String insertQuery = "INSERT INTO words_in_songs (song_id, word_id, verse, "
                + "line_in_verse, word_number_in_verse, word_number_in_song, "
                + "word_number_in_line, line_in_song, word_position) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(WordAppearance appearanceFromList: appearances) {
            stmt.setInt(1, appearanceFromList.getSongId());
            stmt.setInt(2, appearanceFromList.getWordId());
            stmt.setInt(3, appearanceFromList.getVerseNumber());
            stmt.setInt(4, appearanceFromList.getLineNumberInVerse());
            stmt.setInt(5, appearanceFromList.getWordNumberInVerse());
            stmt.setInt(6, appearanceFromList.getWordNumberInSong());
            stmt.setInt(7, appearanceFromList.getWordNumberInLine());
            stmt.setInt(8, appearanceFromList.getLineNumberInSong());
            stmt.setInt(9, appearanceFromList.getWordPosition());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    public void addGroupsInBatch(List<Group> groups) throws SQLException{
        String insertQuery = "INSERT INTO groups (group_id, name) "
                + "VALUES (?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(Group groupFromList: groups) {
            stmt.setInt(1, groupFromList.getGroupId());
            stmt.setString(2, groupFromList.getGroupName());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    public void addMembersInBatch(List<Member> members) throws SQLException{
        String insertQuery = "INSERT INTO member (group_id, word_id) "
                + "VALUES (?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(Member memberFromList: members) {
            stmt.setInt(1, memberFromList.getGroupId());
            stmt.setInt(2, memberFromList.getWordId());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    public void addPhrasesInBatch(List<Phrase> phrases) throws SQLException{
        String insertQuery = "INSERT INTO phrases (phrase_id, phrase, number_of_words) "
                + "VALUES (?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(Phrase phraseFromList: phrases) {
            stmt.setInt(1, phraseFromList.getPhraseId());
            stmt.setString(2, phraseFromList.getPhraseText());
            stmt.setInt(3, phraseFromList.getNumberOfWords());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    public void addConsistInBatch(List<Consist> consistList) throws SQLException{
        String insertQuery = "INSERT INTO consist (phrase_id, word_id, word_number) "
                + "VALUES (?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        
        for(Consist consistFromList: consistList) {
            stmt.setInt(1, consistFromList.getPhraseId());
            stmt.setInt(2, consistFromList.getWordId());
            stmt.setInt(3, consistFromList.getWordNumber());
            stmt.addBatch();
        }

        stmt.executeBatch();
        
        stmt.close();
    }
    
    
    
    public int addSong(Song songToAdd) throws SQLException
    {
        //add song and return id 
        int songId = 0; 
        String insertQuery = "INSERT INTO songs (song_name, link_to_file, song_writer) "
                + "VALUES (?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, songToAdd.getSongName());
        stmt.setString(2, songToAdd.getFilePath());
        stmt.setString(3, songToAdd.getSongWriter());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            // get the songId back
            ResultSet rs = stmt.getGeneratedKeys(); 
                 if (rs.next()) {
                        songId = rs.getInt(1);
                    }
            }
            stmt.close();
        
        return songId;
    }
    
    
    public int getWordId(String wordToGet) throws SQLException
    {
        //check if word exist in table, return it id. If doesnt, create and return Id
        int wordId = 0; 
        String selectQuery = "SELECT word_id "
                + "FROM words "
                + "WHERE word = ?";
        
        PreparedStatement stmt = conn.prepareStatement(selectQuery);
        stmt.setString(1, wordToGet);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            wordId = rs.getInt("word_id");
        }
        stmt.close();
        
        
        if(wordId == 0)
            wordId = addWord(wordToGet);
        
        return wordId;
    }
 
    
    public List<Song> getAllSongs() throws SQLException
    {
        List<Song> songs = new ArrayList<Song>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * " 
         +"FROM songs ");
        while (rs.next()) {
                Song song = new Song();
                song.setSongId(rs.getInt("song_id"));
                song.setSongName(rs.getString("song_name"));
                song.setFilePath(rs.getString("link_to_file"));
                song.setSongWriter(rs.getString("song_writer"));
                song.setDateOfAdd(rs.getDate("date_of_add"));
                songs.add(song);
         }

        return songs;
    }
    
    
    public List<WordAppearance> getAllWordAppearances() throws SQLException{
        String selectQuery = "SELECT * " 
            +"FROM words_in_songs ";
        
        List<WordAppearance> allWordAppearances = new ArrayList<>();
        
        PreparedStatement stmt = conn.prepareStatement(selectQuery);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            WordAppearance wordAppearance = new WordAppearance();
            wordAppearance.setWordId(rs.getInt("word_id"));
            wordAppearance.setSongId(rs.getInt("song_id"));
            wordAppearance.setVerseNumber(rs.getInt("verse"));
            wordAppearance.setWordNumberInVerse(rs.getInt("word_number_in_verse"));
            wordAppearance.setWordNumberInSong(rs.getInt("word_number_in_song"));
            wordAppearance.setWordNumberInLine(rs.getInt("word_number_in_line"));
            wordAppearance.setLineNumberInSong(rs.getInt("line_in_song"));
            wordAppearance.setWordPosition(rs.getInt("word_position"));
            wordAppearance.setLineNumberInVerse(rs.getInt("line_in_verse"));
            allWordAppearances.add(wordAppearance);
            }
        stmt.close();
        
        return allWordAppearances;
    }
    
    //search by group or by word. when nothing selected - by all words
    public void getWordAppearancesByFilter(Map<String, List<WordAppearance>> appearance, String word, 
            Song song, Integer verseNumber, Integer lineNumberInVerse, 
            Integer wordNumberInVerse, Integer wordNumberInSong, 
            Integer wordNumberInLine, Integer lineNumberInSong) throws SQLException
    {
        String currentWord;
   
        boolean hasWherePart = false;
        String wherePart = "";
        //this index needed to count which parameter number need to be added to preparedStatement
        int currentParameterIndex = 1;
        
        //This part of code with if's is building where part of query. 
        //If some parameter , for example "word" is empty, 
        //will not add "WHERE" part for this parameter
        if(!Objects.isNull(word))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and word = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE word = ? ");
                hasWherePart = true;
            }
        }
        
        if(!Objects.isNull(song))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and song_id = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE song_id = ? ");
                hasWherePart = true;
            }
        }
        
        if(!Objects.isNull(verseNumber))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and verse = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE verse = ? ");
                hasWherePart = true;
            }
        }
        
        if(!Objects.isNull(lineNumberInVerse))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and line_in_verse = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE line_in_verse = ? ");
                hasWherePart = true;
            }
        }   
        
        if(!Objects.isNull(wordNumberInVerse))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and word_number_in_verse = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE word_number_in_verse = ? ");
                hasWherePart = true;
            }
        }   
        
        if(!Objects.isNull(wordNumberInSong))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and word_number_in_song = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE word_number_in_song = ? ");
                hasWherePart = true;
            }
        }   
        
        if(!Objects.isNull(wordNumberInLine))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and word_number_in_line = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE word_number_in_line = ? ");
                hasWherePart = true;
            }
        }  
        
        if(!Objects.isNull(lineNumberInSong))
        {
            if(hasWherePart)
            {
                wherePart = wherePart.concat("and line_in_song = ? ");
            }
            else
            {
                wherePart = wherePart.concat("WHERE line_in_song = ? ");
                hasWherePart = true;
            }
        }   
        
        String selectQuery = "SELECT word, song_name, link_to_file, verse,"
            + " word_number_in_verse, word_number_in_song, word_number_in_line, "
            + " line_in_song, word_position, line_in_verse " 
            +"FROM words NATURAL JOIN words_in_songs NATURAL JOIN songs " 
            + wherePart;
    
        PreparedStatement stmt = conn.prepareStatement(selectQuery);
        
        //This part of code adding parameters for prepared statement, 
        //only for parameters that are not null. 
        if(!Objects.isNull(word))
        {
            stmt.setString(currentParameterIndex, word);
            currentParameterIndex++;
        }
        
        if(!Objects.isNull(song))
        {
            stmt.setInt(currentParameterIndex, song.getSongId());
            currentParameterIndex++;
        }           
        
        if(!Objects.isNull(verseNumber))
        {
            stmt.setInt(currentParameterIndex, verseNumber);
            currentParameterIndex++;
        }           
        
        if(!Objects.isNull(lineNumberInVerse))
        {
            stmt.setInt(currentParameterIndex, lineNumberInVerse);
            currentParameterIndex++;
        }           
        
        if(!Objects.isNull(wordNumberInVerse))
        {
            stmt.setInt(currentParameterIndex,wordNumberInVerse);
            currentParameterIndex++;
        }           
        
        if(!Objects.isNull(wordNumberInSong))
        {
            stmt.setInt(currentParameterIndex, wordNumberInSong);
            currentParameterIndex++;
        }            
            
        if(!Objects.isNull(wordNumberInLine))
        {
            stmt.setInt(currentParameterIndex, wordNumberInLine);
            currentParameterIndex++;
        }          
       
        if(!Objects.isNull(lineNumberInSong))
        {
            stmt.setInt(currentParameterIndex, lineNumberInSong);
            currentParameterIndex++;
        }
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            currentWord = rs.getString("word");
            WordAppearance wordAppearance = new WordAppearance();
            wordAppearance.setSongName(rs.getString("song_name"));
            wordAppearance.setSongPath(rs.getString("link_to_file"));
            wordAppearance.setVerseNumber(rs.getInt("verse"));
            wordAppearance.setWordNumberInVerse(rs.getInt("word_number_in_verse"));
            wordAppearance.setWordNumberInSong(rs.getInt("word_number_in_song"));
            wordAppearance.setWordNumberInLine(rs.getInt("word_number_in_line"));
            wordAppearance.setLineNumberInSong(rs.getInt("line_in_song"));
            wordAppearance.setWordPosition(rs.getInt("word_position"));
            wordAppearance.setLineNumberInVerse(rs.getInt("line_in_verse"));
            
            //add word appearance to existing key word
            if(appearance.containsKey(currentWord))
            {
                appearance.get(currentWord).add(wordAppearance);
            }
            else
            {
                 List<WordAppearance> wordAppearances = new ArrayList<>();
                 wordAppearances.add(wordAppearance);
                 appearance.put(currentWord, wordAppearances);
            }
        }
        stmt.close();
        
    }
    
    public Map<String, List<WordAppearance>> getWordAppearancesIncludingGroupField(Group group, String word, 
            Song song, Integer verseNumber, Integer lineNumberInVerse, 
            Integer wordNumberInVerse, Integer wordNumberInSong, 
            Integer wordNumberInLine, Integer lineNumberInSong) throws SQLException
    {
        Map<String, List<WordAppearance>> appearance = new HashMap();
        
        if(Objects.isNull(group))
        {
            getWordAppearancesByFilter(appearance, word, song, verseNumber, lineNumberInVerse,
                        wordNumberInVerse, wordNumberInSong, wordNumberInLine, lineNumberInSong);
        }
            
        else
        {
            //Get list of words in group
            List<String> groupMembers = new ArrayList<String>();
            String selectQuery = "SELECT word " 
               +"FROM member NATURAL JOIN words "
               +"WHERE group_id = ?";
       
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setInt(1, group.getGroupId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                groupMembers.add(rs.getString("word"));
            }
            stmt.close();
        
            //Get word appearance for each word from group
            for (String wordFromGroup : groupMembers) {
                getWordAppearancesByFilter(appearance, wordFromGroup, song, verseNumber, lineNumberInVerse,
                        wordNumberInVerse, wordNumberInSong, wordNumberInLine, lineNumberInSong);
            }
        }
                
        return appearance;
    }
    
    
  
    public List<Group> getGroups() throws SQLException
    {
        List<Group> groups = new ArrayList<Group>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * " 
         +"FROM groups ");
        while (rs.next()) {
            Group group = new Group();
            group.setGroupId(rs.getInt("group_id"));
            group.setGroupName(rs.getString("name"));
            groups.add(group);
         }


        return groups;
    }

    public int createGroup(String groupName) throws SQLException
    {
        int groupId = 0; 
        String insertQuery = "INSERT INTO groups (name) "
                + "VALUES (?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, groupName);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
                // get the groupId back
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                groupId = rs.getInt(1);
            }
        }  
        
        stmt.close();
        
        return groupId;
    }
    
    public List<Word> getGroupMembers(Group group) throws SQLException
    {
        List<Word> groupMembers = new ArrayList<Word>();
            String selectQuery = "SELECT word , word_id " 
               +"FROM member NATURAL JOIN words "
               +"WHERE group_id = ?";
       
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setInt(1, group.getGroupId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Word word = new Word();
                word.setWordText(rs.getString("word"));
                word.setWordId(rs.getInt("word_id"));
                groupMembers.add(word);
            }
            stmt.close();
            
        return groupMembers;    
    }
    
    public void addGroupMember(Group group, Word wordToAdd) throws SQLException
    {
        String insertQuery = "INSERT INTO member (group_id, word_id) "
                + "VALUES (?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, wordToAdd.getWordId());

        int affectedRows = stmt.executeUpdate();
        stmt.close();
    }
    
    public List<Phrase> getPhrases() throws SQLException
    {
        List<Phrase> phrases = new ArrayList<Phrase>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * " 
         +"FROM phrases ");
        while (rs.next()) {
            Phrase phrase = new Phrase(rs.getString("phrase"), 
                    rs.getInt("number_of_words"), rs.getInt("phrase_id"));
            phrases.add(phrase);
         }

        return phrases;
    }
    
    public int addPhrase(String phraseText) throws SQLException
    {
        int phraseId = 0; 
        int wordId;
        String[] wordsInPhrase = phraseText.split(" ");

        String insertQuery = "INSERT INTO phrases (phrase, number_of_words) "
                + "VALUES (?, ?)";
        
        String insertQueryConsist = "INSERT INTO consist (phrase_id, word_id, word_number) "
                + "VALUES (?, ?, ?)";
        
        
        //Add phrase to phrases
        PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, phraseText);
        stmt.setInt(2, wordsInPhrase.length);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
                // get the phraseId back
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                phraseId = rs.getInt(1);
            }
        }  
        
        stmt.close();
        
        //Add every phrase word to "consist" table
        for(int i=0 ; i < wordsInPhrase.length; i++)
        {
            wordId = getWordId(wordsInPhrase[i].toLowerCase());
            stmt = conn.prepareStatement(insertQueryConsist);
            stmt.setInt(1, phraseId);
            stmt.setInt(2, wordId);
            stmt.setInt(3, i+1);
            affectedRows = stmt.executeUpdate();
            stmt.close();
        }
        
        
        return phraseId;
    }
    
    public List<PhraseAppearance> getPhraseAppearances(int phraseId) throws SQLException
    {
        List<PhraseAppearance> phraseAppearances = new ArrayList<PhraseAppearance>();
        String selectQuery =
                "SELECT song_id, start_position, start_position_last_word, song_name, link_to_file, ROW_NUMBER() OVER(PARTITION BY song_id ORDER BY start_position) AS appearance_number \n" +
                "FROM songs NATURAL JOIN \n" +
                "(SELECT number_of_words, phrase_id, word_number, song_id, line_in_song, word_number_in_song, word_id, word_position, word_number-word_number_in_song, \n" +
                "COUNT(word_number_in_song) OVER (PARTITION BY song_id ,  word_number-word_number_in_song) AS count, \n" +
                "MIN(word_position) OVER (PARTITION BY song_id ,  word_number-word_number_in_song) AS start_position,\n" +
                "MAX(word_position) OVER (PARTITION BY song_id , word_number-word_number_in_song) AS start_position_last_word\n" +
                "FROM phrases NATURAL JOIN (SELECT phrase_id, song_id, word_number, line_in_song, word_number_in_song, word_id, word_position\n" +
                "FROM words_in_songs NATURAL JOIN consist\n" +
                "WHERE phrase_id = ?\n" +
                ") AS WORDS_FROM_PHRASE)\n" +
                "AS CONSECUTIVE_IN_PHRASE_BY_SONG\n" +
                "WHERE count = number_of_words\n" +
                "GROUP BY song_id, start_position, start_position_last_word, song_name, link_to_file\n" +
                "ORDER BY song_id, appearance_number";
        
        PreparedStatement stmt = conn.prepareStatement(selectQuery);
        stmt.setInt(1, phraseId);
        ResultSet rs = stmt.executeQuery();
            
        while (rs.next()) {
            PhraseAppearance phraseAppearance = new PhraseAppearance(
                    rs.getInt("song_id"),rs.getString("song_name"), 
                    rs.getString("link_to_file"), rs.getInt("start_position"), 
                    rs.getInt("start_position_last_word"), rs.getInt("appearance_number"));
            phraseAppearances.add(phraseAppearance);
         }
        
        stmt.close();
            
        return phraseAppearances;
    }
    
    public List<Member> getAllMembers() throws SQLException{
        List<Member> members = new ArrayList<Member>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * " 
         +"FROM member ");
        while (rs.next()) {
            Member member = new Member();
            member.setGroupId(rs.getInt("group_id"));
            member.setWordId(rs.getInt("word_id"));
            members.add(member);
         }

        return members;
    }
    
    public List<Consist> getAllConsist() throws SQLException{
        List<Consist> consistRows = new ArrayList<Consist>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * " 
         +"FROM consist ");
        while (rs.next()) {
            Consist consistRow = new Consist();
            consistRow.setPhraseId(rs.getInt("phrase_id"));
            consistRow.setWordId(rs.getInt("word_id"));
            consistRow.setWordNumber(rs.getInt("word_number"));
            consistRows.add(consistRow);
         }

        return consistRows;
    }

    //Method to update all tables
    void importTables(List<Word> words, List<Song> songs, 
            List<WordAppearance> wordAppearances, List<Group> groups, 
            List<Member> members, List<Phrase> phrases, List<Consist> consistRows,
            int maxWordId, int maxSongId, int maxGroupId, int maxPhraseId) throws SQLException {
         
        //Set auto commit to false and set savePoint in case transaction was failed
        conn.setAutoCommit(false);
        Savepoint save1 = conn.setSavepoint();
        try {
            //Empty all tables and try to update with new data
            emptyTables();
            addWordsInBatch(words);
            addSongsInBatch(songs);
            addAppearancesInBatch(wordAppearances);
            addGroupsInBatch(groups);
            addMembersInBatch(members);
            addPhrasesInBatch(phrases);
            addConsistInBatch(consistRows);
            //update sequence with new id's, bsed on max ids for each table from xml
            alterSequences(maxWordId+1, maxSongId+1, maxGroupId+1, maxPhraseId+1); 
            //commit changes
            conn.commit();
        } catch (SQLException ex) {
            //If during transaction was failure, rollback to save point and set autocommit to true.
            conn.rollback(save1);
            conn.setAutoCommit(true);
            System.out.println("Failed, will throw error");
            throw new SQLException();
            
        }
        conn.setAutoCommit(true);
    }
    
    
    //Statistics queries:
    //Total number of words in songs
    public int getNumberOfWordsInSongs() throws SQLException{
        int count = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) "
                + "AS number_of_words_in_songs\n" +
                "FROM words_in_songs");
        while (rs.next()) {
            count = rs.getInt("number_of_words_in_songs");
         }

        return count;
    }
    
    //Number of  songs
    public int getNumberOfSongs() throws SQLException{
        int count = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) "
                                        + "AS number_of_songs\n" 
                                        + "FROM songs");
        while (rs.next()) {
            count = rs.getInt("number_of_songs");
         }

        return count;
    }
    
    //Average number of words in songs
    public int getAverageNumberOfWordsInSongs() throws SQLException{
        int avg = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT AVG(number_of_words_in_song) "+ 
                "as average_number_of_words_in_songs\n" +
                "FROM (SELECT * FROM \n" +
                "(SELECT song_id, COUNT(*) OVER (PARTITION BY song_id) " + 
                "AS number_of_words_in_song\n" +
                "FROM words_in_songs) as NUM_WORDS_EACH_SONG\n" +
                "GROUP BY song_id, number_of_words_in_song) as NUM_WORDS_IN_GROUP");
        while (rs.next()) {
            avg = rs.getInt("average_number_of_words_in_songs");
         }

        return avg;
    }
    
    //Average number of words in verses (between all songs)
    public int getAverageNumberOfWordsInVerses() throws SQLException{
        int avg = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(               
                "SELECT AVG(number_of_words_in_song_by_verse) " + 
                "as average_number_of_words_in_verse_in_all_songs\n" +
                "FROM (SELECT * FROM \n" +
                "(SELECT song_id, verse, COUNT(*) OVER " + 
                "(PARTITION BY song_id, verse) " + 
                "AS number_of_words_in_song_by_verse\n" +
                "FROM words_in_songs) as NUM_WORDS_EACH_VERSE_BY_SONG\n" +
                "GROUP BY song_id, verse, number_of_words_in_song_by_verse) as NUM_WORDS_IN_GROUP");
        while (rs.next()) {
            avg = rs.getInt("average_number_of_words_in_verse_in_all_songs");
         }

        return avg;
    }
    
    //Average number of chars in words
    public int getAverageNumberOfCharsInWords() throws SQLException{
        int avg = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(               
                    "SELECT AVG(length) as average_number_of_chars_in_words\n" +
                    "FROM words");
        while (rs.next()) {
            avg = rs.getInt("average_number_of_chars_in_words");
         }

        return avg;
    }
    
    //Average number of chars in verses
    public int getAverageNumberOfCharsInVerses() throws SQLException{
        int avg = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(               
                    "SELECT AVG(count_of_chars_per_verse) " + 
                    "AS average_count_of_chars_per_verse " +
                    "FROM (SELECT song_id, verse, count_of_chars_per_verse\n" +
                    "FROM (SELECT song_id, verse, length, SUM(length) OVER " + 
                    "(PARTITION BY song_id, verse) as count_of_chars_per_verse\n" +
                    "FROM words NATURAL JOIN words_in_songs) AS WITH_COUNT_OF_CHARS_IN_VERSE\n" +
                    "GROUP BY  song_id, verse, count_of_chars_per_verse) " + 
                    "AS WITH_COUNT_OF_CHARS_IN_VERSE_GROUPED");
        while (rs.next()) {
            avg = rs.getInt("average_count_of_chars_per_verse");
         }

        return avg;
    }
    
    //Average number of chars in lines
    public int getAverageNumberOfCharsInLines() throws SQLException{
        int avg = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(               
                "SELECT AVG(count_of_chars_per_line) AS average_count_of_chars_per_line " +
                "FROM (SELECT song_id, line_in_song, count_of_chars_per_line\n" +
                "FROM (SELECT song_id, line_in_song, length, SUM(length) " + 
                "OVER (PARTITION BY song_id, line_in_song) as count_of_chars_per_line\n" +
                "FROM words NATURAL JOIN words_in_songs) AS WITH_COUNT_OF_CHARS_IN_LINE\n" +
                "GROUP BY  song_id, line_in_song, count_of_chars_per_line) " + 
                "AS WITH_COUNT_OF_CHARS_IN_LINE_GROUPED");
        while (rs.next()) {
            avg = rs.getInt("average_count_of_chars_per_line");
         }

        return avg;
    }
    
    //Average number of chars in songs
    public int getAverageNumberOfCharsInSongs() throws SQLException{
        int avg = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(               
                "SELECT AVG(count_of_chars_per_song ) AS average_count_of_chars_per_song \n" +
                "FROM (SELECT song_id, count_of_chars_per_song \n" +
                "FROM (SELECT song_id, length, SUM(length) OVER " + 
                "(PARTITION BY song_id) as count_of_chars_per_song\n" +
                "FROM words NATURAL JOIN words_in_songs) AS NUM_OF_CHARS_IN_SONG\n" +
                "GROUP BY song_id, count_of_chars_per_song ) AS NUM_OF_CHARS_IN_SONG_IN_GROUP");
        while (rs.next()) {
            avg = rs.getInt("average_count_of_chars_per_song");
         }

        return avg;
    }
}


