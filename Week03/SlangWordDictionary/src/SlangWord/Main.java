package SlangWord;

import Model.SlangDefinition;
import Model.SlangWord;
import Model.SlangWordList;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SlangWordList dictionary=new SlangWordList();
        dictionary.readFile("data/slang.txt");
        int selection;
        do {
            printMenu();
            System.out.print("Nhap lua chon cua ban:");
            Scanner sc=new Scanner(System.in);
            selection=sc.nextInt();

            switch (selection){
                case 1:{
                    Scanner scanner=new Scanner(System.in);
                    System.out.print("Nhap slang word:");
                    String keyword=scanner.nextLine();
                    Map.Entry<String, SlangDefinition> entry = dictionary.findBySlangWord(keyword);
                    if (entry!=null){
                        System.out.println(keyword+":"+entry.getValue().getListDefinitions());
                    }
                    else{
                        System.out.println("Khong tim thay tu can tim!");
                    }
                    dictionary.getHistoryList().addWordHistory(keyword);
                    break;
                }
                case 2:{
                    Scanner scanner=new Scanner(System.in);
                    System.out.print("Nhap keyword:");
                    String keyword=scanner.nextLine();
                    dictionary.findByDefinition(keyword);
                    break;
                }
                case 3:{
                    dictionary.getHistoryList().printHistoryList();
                    break;
                }
                case 4:{
                    Scanner scanner=new Scanner(System.in);
                    System.out.print("Nhap keyword moi:");
                    String keyword=scanner.nextLine();
                    Map.Entry<String, SlangDefinition> sw=dictionary.findBySlangWord(keyword);
                    if (sw!=null){
                        int sel = 0;
                        System.out.println("Tu da ton tai!");
                        System.out.println("Chon 1 de ghi de");
                        System.out.println("Chon 2 de ghi de nghia");
                        System.out.print("Nhap lua chon:");
                        sel=scanner.nextInt();
                        switch (sel){
                            case 1:{

                                String newKeyword="";
                                do{
                                    try{
                                        System.out.print("Nhap Slang Word:");
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        newKeyword=br.readLine();
                                        if (dictionary.findBySlangWord(newKeyword)!=null){
                                            System.out.println("Keyword da ton tai!");
                                        }

                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                                while(dictionary.findBySlangWord(newKeyword)!=null);
                                Scanner newScanner=new Scanner(System.in);
                                System.out.print("Nhap nghia moi:");
                                dictionary.removeSlangWord(keyword);
                                dictionary.addNewSlangWord(newKeyword,newScanner.nextLine());
                                break;
                            }
                            case 2:{
                                System.out.print("Nhap nghia moi:");
                                Scanner newScanner=new Scanner(System.in);
                                String newDefinition=newScanner.nextLine();
                                sw.getValue().overwriteDefinition(newDefinition);
                                break;
                            }
                            default:{
                                System.out.println("Nhap sai lua chon!");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.print("Nhap nghia moi:");
                        String newDefinition=scanner.nextLine();
                        SlangDefinition newDefList=new SlangDefinition();
                        newDefList.addDefinition(newDefinition);
                        dictionary.getDictionary().put(keyword,newDefList);

                    }
                    dictionary.writeFile("data/slang.txt");
                    break;
                }
                case 5:{
                    Scanner scanner=new Scanner(System.in);
                    System.out.print("Nhap slang word muon edit:");
                    String keyword=scanner.nextLine();
                    dictionary.updateSlangWord(keyword);
                    dictionary.writeFile("data/slang.txt");
                    break;

                }
                case 6:{
                    Scanner scanner=new Scanner(System.in);
                    System.out.print("Nhap slang word muon xoa:");
                    String keyword=scanner.nextLine();
                    while (dictionary.findBySlangWord(keyword)==null){
                        System.out.println("Slang word khong ton tai!");
                        System.out.print("Nhap lai slang word:");
                        Scanner sn=new Scanner(System.in);
                        keyword=sn.nextLine();
                    }
                    System.out.println("Ban co thuc su muon xoa?");
                    System.out.println("1. Co");
                    System.out.println("2. Khong");
                    int option=scanner.nextInt();
                    switch (option){
                        case 1:{
                            dictionary.removeSlangWord(keyword);
                            System.out.println("Xoa thanh cong");
                            dictionary.writeFile("data/slang.txt");
                            break;
                        }
                        case 2:{
                            System.out.println("Xoa that bai");
                            break;
                        }
                        default:{
                            System.out.println("Nhap sai lua chon!");
                            break;
                        }
                    }
                    break;
                }
                case 7:{
                    dictionary.getDictionary().clear();
                    dictionary.readFile("data/default.txt");
                    dictionary.writeFile("data/slang.txt");
                    break;
                }
                case 8:{
                    SlangWord slangWord=dictionary.getRandomSlangWord();
                    System.out.println(slangWord.getWord()+":"+slangWord.getDefinition().getListDefinitions());
                    break;
                }
                case 9:{
                    Random gen=new Random();
                    String answerInput = null;
                    int answerIndex = gen.nextInt(4);
                    List<SlangWord> answerList=dictionary.getRandomList();
                    SlangWord answer = answerList.get(answerIndex);
                    System.out.println(answer.getWord()+":");
                    System.out.println("A:"+answerList.get(0).getDefinition().getListDefinitions());
                    System.out.println("B:"+answerList.get(1).getDefinition().getListDefinitions());
                    System.out.println("C:"+answerList.get(2).getDefinition().getListDefinitions());
                    System.out.println("D:"+answerList.get(3).getDefinition().getListDefinitions());
                    System.out.println("Nhap lua chon:");
                    Scanner scanner=new Scanner(System.in);
                    String sel=scanner.nextLine();
                    if (answerIndex==0){
                        answerInput="A";
                    }
                    else if (answerIndex==1){
                        answerInput="B";
                    }else if (answerIndex==2){
                        answerInput="C";
                    }
                    else if (answerIndex==3){
                        answerInput="D";
                    }
                    if (sel.toLowerCase().equals(answerInput.toLowerCase())){
                        System.out.println("Chinh xac!");
                    }else{
                        System.out.println("Sai!");
                        System.out.println("Dap an dung la: "+answerInput);
                    }
                    break;
                }
                case 10:{
                    Random gen=new Random();
                    String answerInput = null;
                    int answerIndex = gen.nextInt(4);
                    List<SlangWord> answerList=dictionary.getRandomList();
                    SlangWord answer = answerList.get(answerIndex);
                    System.out.println("Slang word co nghia la \""+answer.getDefinition().getListDefinitions()+"\":");
                    System.out.println("A:"+answerList.get(0).getWord());
                    System.out.println("B:"+answerList.get(1).getWord());
                    System.out.println("C:"+answerList.get(2).getWord());
                    System.out.println("D:"+answerList.get(3).getWord());
                    System.out.println("Nhap lua chon:");
                    Scanner scanner=new Scanner(System.in);
                    String sel=scanner.nextLine();
                    if (answerIndex==0){
                        answerInput="A";
                    }
                    else if (answerIndex==1){
                        answerInput="B";
                    }else if (answerIndex==2){
                        answerInput="C";
                    }
                    else if (answerIndex==3){
                        answerInput="D";
                    }
                    if (sel.toLowerCase().equals(answerInput.toLowerCase())){
                        System.out.println("Chinh xac!");
                    }else{
                        System.out.println("Sai!");
                        System.out.println("Dap an dung la: "+answerInput);
                    }
                    break;

                }
            }
            System.out.println("---------------------------------------------\n");
        }while (selection<=10&&selection>=1);
    }
    public static void printMenu(){
        System.out.println("Chon 1 de tim kiem theo slang word");
        System.out.println("Chon 2 de tim kiem theo definition");
        System.out.println("Chon 3 de hien thi lich su tim kiem");
        System.out.println("Chon 4 de add 1 slang word moi");
        System.out.println("Chon 5 de edit 1 slang word");
        System.out.println("Chon 6 de delete 1 slang word");
        System.out.println("Chon 7 de reset ve danh sach slang word goc");
        System.out.println("Chon 8 de random 1 slang word");
        System.out.println("Chon 9 de thuc hien chuong trinh do definition");
        System.out.println("Chon 10 de thuc hien chuong trinh do slang word");
    }

}