package Client.Views.Chat;

import Client.Utils.FileUtil;
import Client.Utils.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChatPane extends JPanel {
    private MessageUtil messageUtil;
    private FileUtil fileUtil;
//    private JList<MessageCell> messageList;
    private static JPanel messageList;
//    public static DefaultListModel model;
    private JTextField messageInput;
    private JButton sendBtn;
    private JButton fileBtn;
    private File fileToSend;
    public ChatPane(MessageUtil messageUtil, FileUtil fileUtil) {
        this.messageUtil = messageUtil;
        this.fileUtil=fileUtil;
    }
    public void addComponentsToPane(Container pane) {

        setLayout(new BorderLayout());
//        model=new DefaultListModel();
        messageList=new JPanel();
        messageList.setLayout(new BoxLayout(messageList,BoxLayout.Y_AXIS));
//        messageList=new JList<>(model);
        JScrollPane messageScrollPane=new JScrollPane(messageList);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(messageScrollPane,BorderLayout.CENTER);


        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BorderLayout());
        messageInput=new JTextField();
        messageInput.setPreferredSize(new Dimension((int)( this.getPreferredSize().width*0.6),25));
//        messageInput.setMaximumSize(new Dimension(300,25));
//        messageInput.setMinimumSize(new Dimension(100,25));
//        jPanel1.add(Box.createRigidArea(new Dimension(10,0)));
//        jPanel1.add(usernameTField);
        sendBtn=new JButton("Send");
        sendBtn.setPreferredSize(new Dimension((int) (this.getPreferredSize().width * 0.2), 25));


        fileBtn=new JButton("File");

        fileBtn.setPreferredSize(new Dimension((int) (this.getPreferredSize().width * 0.2), 25));
        jPanel.add(fileBtn,BorderLayout.LINE_START);
        jPanel.add(messageInput,BorderLayout.CENTER);
        jPanel.add(sendBtn,BorderLayout.LINE_END);
        add(jPanel,BorderLayout.PAGE_END);
    }
    public void addListeners(){
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!messageInput.getText().isEmpty()){
                    try {
                        JLabel jLabel=new JLabel("(You): "+messageInput.getText());
                        jLabel.setForeground(Color.DARK_GRAY.brighter());
                        MessageRow messageRow=new MessageRow(jLabel,Component.LEFT_ALIGNMENT);
                        messageList.add(messageRow);
                        messageList.validate();
//                        model.addElement("(You): "+messageInput.getText());
//                        messageList.setAlignmentX(JList.LEFT_ALIGNMENT);
                        messageUtil.getBufferedWriter().write(messageUtil.getUsername() + ":" + messageInput.getText());
                        messageUtil.getBufferedWriter().newLine();
                        messageUtil.getBufferedWriter().flush();
                        messageInput.setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        fileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.setDialogTitle("Choose a file to send");
                if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    fileToSend=jFileChooser.getSelectedFile();
                    fileUtil.sendFile(fileToSend);
                    JLabel hyperlink=new JLabel("(You): "+fileToSend.getName());
                    hyperlink.setForeground(Color.DARK_GRAY.brighter());
                    hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    hyperlink.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // the user clicks on the label
                            try {
                                FileInputStream fileInputStream=new FileInputStream(fileToSend.getAbsolutePath());
                                byte[] fileContentBytes=new byte[(int)fileToSend.length()];
                                fileInputStream.read(fileContentBytes);
                                JFrame downloadWindow=fileUtil.createNewFrame(fileToSend.getName(),fileContentBytes);
                                downloadWindow.setVisible(true);
                                downloadWindow.setLocationRelativeTo(null);
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }

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
        });
    }

    public static JPanel getMessageList() {
        return messageList;
    }
}
