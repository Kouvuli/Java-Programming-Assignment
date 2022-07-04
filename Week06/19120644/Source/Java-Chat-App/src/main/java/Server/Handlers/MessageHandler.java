package Server.Handlers;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class MessageHandler implements Runnable {
    private static  ArrayList<MessageHandler> messageHandlers =new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public static ArrayList<MessageHandler> getClientHandlers() {
        return messageHandlers;
    }

    public String getClientUsername() {
        return clientUsername;
    }



    public MessageHandler(Socket socket){
        try {

            this.socket=socket;
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            messageHandlers.add(this);


            this.clientUsername = bufferedReader.readLine();
            broadcastMessage(clientUsername+" has entered the chat!");

            String allCurrUserList="All Users:";
            for (MessageHandler messageHandler : messageHandlers) {
                allCurrUserList+= messageHandler.getClientUsername()+" ";
            }
            sendAllMessage(allCurrUserList);

        }catch (IOException e){
            closeAll(socket,bufferedWriter,bufferedReader);
        }
    }

    public void closeAll(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        removeClientHandler();

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

    public void broadcastMessage(String message) {
        for (MessageHandler messageHandler : messageHandlers){
            try{
                if (!messageHandler.clientUsername.equals(clientUsername)){
                    messageHandler.bufferedWriter.write(message);
                    messageHandler.bufferedWriter.newLine();
                    messageHandler.bufferedWriter.flush();

                }
            }catch (IOException e){
                closeAll(socket,bufferedWriter,bufferedReader);
            }
        }
    }
    public void sendAllMessage(String message){
        for (MessageHandler messageHandler : messageHandlers){
            try{
                messageHandler.bufferedWriter.write(message);
                messageHandler.bufferedWriter.newLine();
                messageHandler.bufferedWriter.flush();
            }catch (IOException e){
                closeAll(socket,bufferedWriter,bufferedReader);
            }
        }
    }

    public void removeClientHandler(){
        messageHandlers.remove(this);
        broadcastMessage(clientUsername+" has left the chat");
    }
    @Override
    public void run() {
        String messageFromClient="";
        while (socket.isConnected()){
            try{
                messageFromClient=bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeAll(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }

}
