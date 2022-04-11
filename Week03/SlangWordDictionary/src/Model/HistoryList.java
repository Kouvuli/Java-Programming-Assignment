package Model;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class HistoryList {
    private List<History> wordList;
    private List<History> definitionList;
    public HistoryList() {
        this.wordList = new ArrayList<>();
        this.definitionList = new ArrayList<>();
    }
    public void addWordHistory(String s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        History his=new History(s,dtf.format(now));
        wordList.add(his);
    }
    public void addDefHistory(String keyword,List<String> str){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        History his=new History(keyword+":"+String.join("| ",str),dtf.format(now));
        definitionList.add(his);
    }
    public void printHistoryList(){
        System.out.println("Keywords:");
        for (History word: wordList) {
            word.printHistory();
        }
        System.out.println("\n\nDefinitions:");
        for (History word: definitionList) {
            word.printHistory();
        }
    }
}