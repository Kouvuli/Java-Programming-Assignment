package Client.Utils;



import Client.Views.Chat.ChatPane;
import Client.Views.Chat.MessageRow;
import Client.Views.Chat.UserListPane;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MessageUtil {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String username;
    public MessageUtil(Socket socket, String username){
        try{
            this.socket=socket;
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username=username;
        } catch (IOException e) {
            closeAll(socket,bufferedWriter,bufferedReader);

        }
    }
    public void sendMessage(){
        try{

            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner=new Scanner(System.in);
            while (socket.isConnected()){
                String messageToSend=scanner.nextLine();
                bufferedWriter.write(username+": "+messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {

            closeAll(socket,bufferedWriter,bufferedReader);
        }
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while(socket.isConnected()){
                    try{
                        msgFromGroupChat=bufferedReader.readLine();

                        if(msgFromGroupChat.contains("All Users")){
                            String[] allUsers = msgFromGroupChat.split(":")[1].split(" ");
                            if(UserListPane.getUsernames()!=null){

                                UserListPane.getUsernames().clear();
                            }
                            for (String a:allUsers) {

                                UserListPane.getUsernames().addElement(a);
                            }
                        }
                        else if(msgFromGroupChat.contains("left the chat")){
                            String userLeft=msgFromGroupChat.split(" ")[0];
                            UserListPane.getUsernames().removeElement(userLeft);
                            JLabel jLabel=new JLabel(msgFromGroupChat);
                            ChatPane.getMessageList().add(new MessageRow(jLabel, Component.LEFT_ALIGNMENT));
                            ChatPane.getMessageList().validate();
                            closeAll(socket,bufferedWriter,bufferedReader);
                        }
                        else{
                            JLabel jLabel=new JLabel(msgFromGroupChat);
                            ChatPane.getMessageList().add(new MessageRow(jLabel,Component.LEFT_ALIGNMENT));
                            ChatPane.getMessageList().validate();
                        }

                    } catch (IOException e) {
                        closeAll(socket,bufferedWriter,bufferedReader);

                    }
                }
            }
        }).start();
    }
//    boolean isAlreadyContain(String user){
//        UserListPane.usernames.
//    }
    public void closeAll(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try{
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if (bufferedWriter!=null){
                bufferedWriter.close();
            }
            if (socket!=null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public String getUsername() {
        return username;
    }
}
