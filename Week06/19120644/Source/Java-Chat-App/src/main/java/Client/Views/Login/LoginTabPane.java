package Client.Views.Login;

import Client.Utils.FileUtil;
import Client.Utils.MessageUtil;
import Client.Views.Chat.ChatView;
import Client.Views.Chat.UserListPane;
import Server.Services.AccountService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class LoginTabPane extends JPanel {
    JTextField usernameTField;
    JPasswordField passwordPField;

    JButton nextBtn;
    JButton cancelBtn;
    public LoginTabPane(){
        super(new BorderLayout());
        JPanel content=new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.X_AXIS));

        JLabel usernameLb=new JLabel("Username:");
        jPanel1.add(usernameLb);
        usernameTField=new JTextField();

        usernameTField.setPreferredSize(new Dimension(200,25));
        usernameTField.setMaximumSize(new Dimension(300,25));
        usernameTField.setMinimumSize(new Dimension(100,25));
        jPanel1.add(Box.createRigidArea(new Dimension(10,0)));
        jPanel1.add(usernameTField);

        JPanel jPanel2=new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.X_AXIS));

        JLabel passwordLb=new JLabel("Password:");
        jPanel2.add(passwordLb);
        passwordPField=new JPasswordField();
        passwordPField.setPreferredSize(new Dimension(200,25));
        passwordPField.setMaximumSize(new Dimension(300,25));
        passwordPField.setMinimumSize(new Dimension(100,25));
        jPanel2.add(Box.createRigidArea(new Dimension(12,0)));
        jPanel2.add(passwordPField);

        content.add(jPanel1);
        jPanel1.setBorder(new EmptyBorder(5,5,0,5));
        jPanel2.setBorder(new EmptyBorder(5,5,0,5));

        content.add(jPanel2);
        add(content,BorderLayout.CENTER);


        JPanel buttonBar=new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar,BoxLayout.X_AXIS));


        nextBtn=new JButton("OK");
        nextBtn.setPreferredSize(new Dimension(80, 30));
        nextBtn.setActionCommand("OK");
        cancelBtn = new JButton("Cancel");
        cancelBtn.setPreferredSize(new Dimension(80, 30));
        cancelBtn.setActionCommand("CANCEL");

        buttonBar.add(Box.createHorizontalGlue());
        buttonBar.add(nextBtn);
        buttonBar.add(Box.createRigidArea(new Dimension(5,0)));
        buttonBar.add(cancelBtn);

        add(buttonBar,BorderLayout.PAGE_END);

    }
    public void addListeners(){


        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputEmpty()) {
                    JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Input field is empty!");
                }
                else if (isContainSpecialCharacter()){
                    JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Input field have special character!");
                }
                else{
                    AccountService service=new AccountService();
                    char[] a=passwordPField.getPassword();

                    if (service.authenticateAccount(usernameTField.getText(), String.valueOf(a))) {

                        Socket socket = null;
                        Socket socket1 = null;
                        try {
                            socket = new Socket("localhost", 3241);
                            socket1 = new Socket("localhost", 3242);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        MessageUtil messageUtil = new MessageUtil(socket, usernameTField.getText());
                        messageUtil.listenForMessage();

                        try {
                            messageUtil.getBufferedWriter().write(usernameTField.getText());
                            messageUtil.getBufferedWriter().newLine();
                            messageUtil.getBufferedWriter().flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        FileUtil fileUtil = new FileUtil(socket1, usernameTField.getText());
                        fileUtil.listenForFile();
                        try {
                            fileUtil.getDataOutputStream().writeInt(usernameTField.getText().length());
                            fileUtil.getDataOutputStream().write(usernameTField.getText().getBytes());

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }


                        Container frame = nextBtn.getParent();
                        do
                            frame = frame.getParent();
                        while (!(frame instanceof JFrame));
                        ((JFrame) frame).dispose();
                        ChatView chatView = new ChatView(messageUtil, fileUtil);
                        chatView.createAndShowGUI();


                    } else {
                        JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Username or password is wrong!");
                    }
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container frame = cancelBtn.getParent();
                do
                    frame = frame.getParent();
                while (!(frame instanceof JFrame));
                ((JFrame) frame).dispose();
                System.exit(0);
            }
        });


    }
//    public boolean isInGroupChat(String username){
//        if(UserListPane.getUsernames()==null){
//            return true;
//        }
//        for(int i=0;i<UserListPane.getUsernames().getSize();i++){
//            if(UserListPane.getUsernames().getElementAt(i).equals(username)){
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean isContainSpecialCharacter(){
        char[] a= passwordPField.getPassword();
        if (usernameTField.getText().contains(",")||usernameTField.getText().contains("--")||usernameTField.getText().contains("'")||usernameTField.getText().contains("SELECT")||usernameTField.getText().contains("UNION")||usernameTField.getText().contains("UPDATE")||usernameTField.getText().contains("INSERT")||usernameTField.getText().contains("=")){
            return true;

        }

        else if(String.valueOf(a).contains(",")||String.valueOf(a).contains("--")||String.valueOf(a).contains("'")||String.valueOf(a).contains("SELECT")||String.valueOf(a).contains("UNION")||String.valueOf(a).contains("UPDATE")||String.valueOf(a).contains("INSERT")||String.valueOf(a).contains("=")){
            return true;
        }
        return false;
    }

    public boolean isInputEmpty(){
        if(usernameTField.getText().isEmpty()){
            return true;
        }else if (String.valueOf(passwordPField.getPassword()).isEmpty()){
            return true;
        }
        return false;
    }




}
