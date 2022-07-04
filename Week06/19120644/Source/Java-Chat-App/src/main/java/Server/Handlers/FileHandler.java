package Server.Handlers;

import Server.Models.MyFile;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class FileHandler implements Runnable{
    private static ArrayList<MyFile> myFiles=new ArrayList<>();
    private static ArrayList<FileHandler> fileHandlers=new ArrayList<>();
    private Socket socket;
    private static int fileId=0;
    private DataInputStream dataInputStream;
    private String clientUsername;
    private DataOutputStream dataOutputStream;
    public FileHandler(Socket socket){
        try {

            this.socket=socket;
            this.dataInputStream=new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            fileHandlers.add(this);
            int clientUsernameLength=dataInputStream.readInt();
            byte[] clientUsernameBytes=new byte[clientUsernameLength];
            dataInputStream.readFully(clientUsernameBytes,0,clientUsernameLength);
            this.clientUsername=new String(clientUsernameBytes);


        }catch (IOException e){
            closeAll(socket,dataInputStream,dataOutputStream);
        }
    }
    public String getFileExtension(String fileName){

        int i=fileName.lastIndexOf(".");
        if(i>0){
            return fileName.substring(i+1);
        }
        else{
            return "no extension found!";
        }


    }
    public void broadcastMessage(MyFile fileToSend) {
        for (FileHandler fileHandler : fileHandlers){
            try{
                if (!fileHandler.getClientUsername().equals(clientUsername)){
                    fileHandler.dataOutputStream.writeInt(fileToSend.getId());
                    fileHandler.dataOutputStream.writeInt(fileToSend.getNameLength());
                    fileHandler.dataOutputStream.write(fileToSend.getName());
                    fileHandler.dataOutputStream.writeInt(fileToSend.getDataLength());
                    fileHandler.dataOutputStream.write(fileToSend.getData());
                    fileHandler.dataOutputStream.writeInt(fileToSend.getClientUsernameLength());
                    fileHandler.dataOutputStream.write(fileToSend.getClientUsername());


                }
            }catch (IOException e){
                closeAll(socket,dataInputStream,dataOutputStream);
            }
        }
    }
    public void closeAll(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        removeFileHandler();

        try{
            if(dataInputStream!=null){
                dataInputStream.close();
            }
            if (dataOutputStream!=null){
                dataOutputStream.close();
            }
            if (socket!=null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeFileHandler(){
        fileHandlers.remove(this);
//        broadcastMessage(clientUsername+" has left the chat");
    }
    public String getClientUsername() {
        return clientUsername;
    }

    @Override
    public void run() {
        String messageFromClient="";
        while (socket.isConnected()){
            try{

                int fileNameLength=dataInputStream.readInt();
                if(fileNameLength>0){
                    byte[] fileNameByte=new byte[fileNameLength];
                    dataInputStream.readFully(fileNameByte,0,fileNameLength);
                    String fileName=new String(fileNameByte);

                    int fileContentLength=dataInputStream.readInt();
                    if(fileContentLength>0){
                        byte[] fileContentByte=new byte[fileContentLength];
                        dataInputStream.readFully(fileContentByte,0,fileContentLength);
                        MyFile newFile=new MyFile(fileId,fileNameByte,fileNameLength,fileContentByte,fileContentLength,getFileExtension(fileName),clientUsername.getBytes(),clientUsername.getBytes().length);
                        myFiles.add(newFile);
                        broadcastMessage(newFile);
                    }
                }
                fileId++;
            }catch (IOException e){
                closeAll(socket, dataInputStream, dataOutputStream);
                break;
            }
        }
    }
}
