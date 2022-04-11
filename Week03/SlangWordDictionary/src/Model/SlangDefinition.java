package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SlangDefinition {
    private List<String> definitions;

    public List<String> getDefinitions() {
        return definitions;
    }

    public SlangDefinition(){
        definitions=new ArrayList<>();
    }
    public SlangDefinition(List<String> definitions) {
        this.definitions = definitions;
    }
    public void addDefinition(String def){
        definitions.add(def.trim());
    }
    public void overwriteDefinition(String def){
        definitions.clear();
        definitions.add(def.trim());
    }
    public String getListDefinitions(){
        return String.join("| ", definitions);
    }
    public boolean isContain(String keyword){
        for (String s:definitions) {
            if (s.toLowerCase().contains(keyword.toLowerCase())){
                return true;
            }
        }
        return false;
    }
//    public void printDefinitions(){
//        for (String s:definitions) {
//            System.out.println(s+"|");
//        }
//
//    }
}
