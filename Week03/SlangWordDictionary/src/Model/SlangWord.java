package Model;


import java.io.*;
import java.util.Map;

public class SlangWord {
    private String word;
    private SlangDefinition definition;

    public SlangWord(String word, SlangDefinition definition) {
        this.word = word;
        this.definition = definition;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefinition(SlangDefinition definition) {
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public SlangDefinition getDefinition() {
        return definition;
    }
}
