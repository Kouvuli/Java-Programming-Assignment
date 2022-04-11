package Model;
import java.io.*;
import java.util.*;

public class SlangWordList {
    //    List<SlangWord> list;
    HashMap<String,SlangDefinition> dictionary;
    HistoryList historyList;

    public SlangWordList() {

        dictionary=new HashMap<String,SlangDefinition>();
        historyList=new HistoryList();
    }

    public HistoryList getHistoryList() {
        return historyList;
    }
    public HashMap<String,SlangDefinition>getDictionary(){
        return dictionary;
    }
    public void addNewSlangWord(String key,String definition){
        SlangDefinition definitions = new SlangDefinition();
        definitions.addDefinition(definition);
        dictionary.put(key,definitions);
    }
    public void removeSlangWord(String key){
        dictionary.remove(key);
    }
    public void overwriteSlangWord(String key,String def){
        SlangDefinition definitions=new SlangDefinition();
        definitions.addDefinition(def);
        dictionary.replace(key,definitions);
    }
    public Map.Entry<String,SlangDefinition> findBySlangWord (String keyword){
        for (Map.Entry<String, SlangDefinition> entry : dictionary.entrySet()) {
            if (entry.getKey().toLowerCase().trim().equals(keyword.toLowerCase().trim())){
                String str=entry.getValue().getListDefinitions();
                return entry;
            }
        }
        return null;
    }
    public void findByDefinition(String keyword) {
        List<String>keyList=new ArrayList<>();
        for (Map.Entry<String, SlangDefinition> entry : dictionary.entrySet()) {

            String key= entry.getKey();
            SlangDefinition value=entry.getValue();
            if(value.isContain(keyword)){
                keyList.add(key);
            }
        }
        System.out.println(String.join("| ",keyList));
        historyList.addDefHistory(keyword,keyList);
    }

    public void addDefinition(String keyword,String def){
        dictionary.get(keyword).addDefinition(def);

    }
    public void readFile(String file_name)  {
        try{
            File fin=new File(file_name);
            FileReader fr=new FileReader(fin);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null){
                String word = null;
                SlangDefinition def=new SlangDefinition();
                String[] str = line.split("`");
                word=str[0];
                for (String s:str[1].split("\\|")) {
                    def.addDefinition(s);
                }
                if (!dictionary.containsKey(word)){
                    dictionary.put(word,def);
                }

//                list.add(sl);
            }
            fr.close();
            br.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String file_name){
        File fout=new File(file_name);
        BufferedWriter bw=null;
        try{
            bw=new BufferedWriter(new FileWriter(fout));
            for (Map.Entry<String, SlangDefinition> entry : dictionary.entrySet()) {

                String key= entry.getKey();
                SlangDefinition value=entry.getValue();
                bw.write(key+"`"+value.getListDefinitions());
                bw.newLine();
            }
            bw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {

                // always close the writer
                bw.close();
            }
            catch (Exception e) {
            }
        }
    }
    public void updateSlangWord(String keyword){
        try{
            Map.Entry<String, SlangDefinition> sw= this.findBySlangWord(keyword);
            while(this.findBySlangWord(keyword)==null){
                System.out.println("Keyword khong ton tai!");
                System.out.print("Nhap slang word moi:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                keyword=br.readLine();
            }
            int sel = 0;
            System.out.println("Chon 1 de ghi de");
            System.out.println("Chon 2 de ghi them nghia moi");
            System.out.print("Nhap lua chon:");
            Scanner h=new Scanner(System.in);
            sel=h.nextInt();
            switch (sel){
                case 1:{

                    String newKeyword="";
                    do{
                        try{
                            System.out.print("Nhap Slang Word:");
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            newKeyword=br.readLine();
                            if (findBySlangWord(newKeyword)!=null && !newKeyword.toLowerCase().trim().equals(keyword.toLowerCase().trim())){
                                System.out.println("Keyword da ton tai!");
                            }

                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    while(findBySlangWord(newKeyword)!=null &&!newKeyword.toLowerCase().trim().equals(keyword.toLowerCase().trim()));
                    Scanner newScanner=new Scanner(System.in);
                    System.out.print("Nhap nghia moi:");
                    removeSlangWord(keyword);
                    addNewSlangWord(newKeyword,newScanner.nextLine());
                    break;
                }
                case 2:{
                    System.out.print("Nhap nghia moi:");
                    Scanner newScanner=new Scanner(System.in);
                    String newDefinition=newScanner.nextLine();
                    sw.getValue().addDefinition(newDefinition);
                    break;
                }
                default:{
                    System.out.println("Nhap sai lua chon!");
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public SlangWord getRandomSlangWord(){
        Random generator = new Random();
        List<String> keysAsArray = new ArrayList<String>(dictionary.keySet());

        int randomNumber=generator.nextInt(keysAsArray.size());
        String randomKey=keysAsArray.get(randomNumber);
        SlangDefinition randomValue=dictionary.get(randomKey);
        SlangWord slangWord=new SlangWord(randomKey,randomValue);
        return slangWord;
//        System.out.println(key+":"+randomValue.getListDefinitions());
    }
    public List<SlangWord> getRandomList(){
        List<SlangWord> answersList=new ArrayList<>();
        List<String> keysAsArray = new ArrayList<String>(dictionary.keySet());
        List<SlangDefinition> definitionAsArray = new ArrayList<SlangDefinition>(dictionary.values());
        for (int i=0;i<4;i++){
            int r = (int)Math.floor(Math.random() * keysAsArray.size()) + 1;
            String randomKey=keysAsArray.get(r);
            SlangDefinition randomDefinition=definitionAsArray.get(r);
            SlangWord sw=new SlangWord(randomKey,randomDefinition);
            answersList.add(sw);
        }
        return answersList;
    }

//    private List<String> splitToSlangWord(String st,String word,SlangDefinition sd)
//    {
//        String[] line = st.split("`");
//        word=line[0];
//        SlangWord slangword = new SlangWord(line[0],line[1]);
//        for (String s:line[1].split("|")) {
//            sd.addDefinitions(s);
//        }
//
//    }
}