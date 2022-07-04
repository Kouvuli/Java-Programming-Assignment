package Server;

import Server.Handlers.FileHandler;
import Server.Handlers.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket messageServerSocket;
    private ServerSocket fileServerSocket;
    public Server(ServerSocket messageServerSocket,ServerSocket fileServerSocket){
        this.messageServerSocket=messageServerSocket;
        this.fileServerSocket=fileServerSocket;
    }

    public void startServer(){
        try {
            while(!messageServerSocket.isClosed()&&!fileServerSocket.isClosed()){
                Socket messageSocket=messageServerSocket.accept();
                Socket fileSocket=fileServerSocket.accept();
                System.out.println("A new client has connected");
                MessageHandler messageHandler =new MessageHandler(messageSocket);
                FileHandler fileHandler =new FileHandler(fileSocket);
                Thread thread=new Thread(messageHandler);
                thread.start();

                Thread thread1=new Thread(fileHandler);
                thread1.start();
            }
        }catch (IOException e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket(){
        try{
            if (messageServerSocket!=null){
                messageServerSocket.close();
            }
            if (fileServerSocket!=null){
                fileServerSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1=new ServerSocket(3241);
        ServerSocket serverSocket2=new ServerSocket(3242);
        Server server=new Server(serverSocket1,serverSocket2);
        System.out.println("Waiting for client");
        server.startServer();
    }

}
