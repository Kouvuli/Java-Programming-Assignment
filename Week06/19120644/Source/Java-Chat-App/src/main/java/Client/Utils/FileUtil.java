package Client.Utils;

import Client.Views.Chat.ChatPane;
import Client.Views.Chat.MessageRow;
import Client.Views.Chat.UserListPane;
import Server.Models.MyFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileUtil {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String username;
    public FileUtil(Socket socket, String username){
        try{
            this.socket=socket;
            this.dataInputStream= new DataInputStream(socket.getInputStream());
            this.dataOutputStream=new DataOutputStream(socket.getOutputStream());
            this.username=username;
        } catch (IOException e) {
            closeAll(socket,dataInputStream,dataOutputStream);

        }
    }
    public void sendFile(File fileToSend){
        try{

            FileInputStream fileInputStream=new FileInputStream(fileToSend.getAbsolutePath());
            byte[] fileNameBytes=fileToSend.getName().getBytes();
            byte[] fileContentBytes=new byte[(int)fileToSend.length()];
            fileInputStream.read(fileContentBytes);


            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileNameBytes);

            dataOutputStream.writeInt(fileContentBytes.length);
            dataOutputStream.write(fileContentBytes);


        } catch (IOException e) {

            closeAll(socket,dataInputStream,dataOutputStream);
        }
    }
    public void listenForFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String msgFromGroupChat;
                while(socket.isConnected()){
                    try{
                        int fileId=dataInputStream.readInt();
                        int fileNameLength=dataInputStream.readInt();
                        if(fileNameLength>0){
                            byte[] fileNameByte=new byte[fileNameLength];
                            dataInputStream.readFully(fileNameByte,0,fileNameLength);
                            String fileName=new String(fileNameByte);

                            int fileContentLength=dataInputStream.readInt();
                            if(fileContentLength>0){
                                byte[] fileContentByte=new byte[fileContentLength];
                                dataInputStream.readFully(fileContentByte,0,fileContentLength);

                                int fileClienUsernameLength=dataInputStream.readInt();
                                byte[]fileClientUsernameBytes=null;
                                if (fileClienUsernameLength>0){
                                    fileClientUsernameBytes=new byte[fileClienUsernameLength];
                                    dataInputStream.readFully(fileClientUsernameBytes,0,fileClienUsernameLength);
                                }
                                String fileClientUsername=new String (fileClientUsernameBytes);
                                JLabel hyperlink=new JLabel(fileClientUsername+":"+fileName);
                                hyperlink.setForeground(Color.BLUE.darker());
                                hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                hyperlink.addMouseListener(new MouseAdapter() {

                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        // the user clicks on the label
                                        JFrame downloadWindow=createNewFrame(fileName,fileContentByte);
                                        downloadWindow.setVisible(true);
                                        downloadWindow.setLocationRelativeTo(null);
                                    }

                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        // the mouse has entered the label
                                    }

                                    @Override
                                    public void mouseExited(MouseEvent e) {
                                        // the mouse has exited the label
                                    }
                                });
                                ChatPane.getMessageList().add(new MessageRow(hyperlink,Component.LEFT_ALIGNMENT));
                                ChatPane.getMessageList().validate();
                            }

                        }

                    } catch (IOException e) {
                        closeAll(socket,dataInputStream,dataOutputStream);

                    }
                }
            }
        }).start();
    }

    public JFrame createNewFrame(String fileName,byte[] fileContent){
        JFrame jFrame=new JFrame("Download file");
        jFrame.setSize(400,200);

        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        JLabel jLabel=new JLabel("Are you sure you want to download "+fileName);
        jLabel.setFont(new Font("Arial",Font.BOLD,20));
        jLabel.setBorder(new EmptyBorder(20,0,10,0));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbYes=new JButton("Yes");
        jbYes.setPreferredSize(new Dimension(70,25));

        JButton jbNo=new JButton("No");
        jbNo.setPreferredSize(new Dimension(50,25));

        JPanel jpButtons=new JPanel();
        jpButtons.setBorder(new EmptyBorder(20,0,10,0));

        jpButtons.add(jbYes);
        jpButtons.add(jbNo);

        jPanel.add(jLabel);
        jPanel.add(jpButtons);

        jFrame.add(jPanel);



        jbNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
        jbYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileOutputStream fileOutputStream=new FileOutputStream(new File(fileName));
                    fileOutputStream.write(fileContent);
                    fileOutputStream.close();
                    jFrame.dispose();
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        });
        return jFrame;
    }
    public void closeAll(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
//        removeClientHandler();

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
    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public String getUsername() {
        return username;
    }
}
