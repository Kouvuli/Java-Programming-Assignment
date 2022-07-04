package Client.Views.Chat;

import Client.Utils.FileUtil;
import Client.Utils.MessageUtil;
import Client.Views.Login.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserListPane extends JPanel {
    private static JList<String> usernameList;
    private static DefaultListModel usernames=new DefaultListModel();
    private JButton logOutBtn;
    private MessageUtil messageUtil;
    private FileUtil fileUtil;
    public UserListPane(MessageUtil messageUtil, FileUtil fileUtil) {
        this.messageUtil=messageUtil;
        this.fileUtil=fileUtil;
    }
    public void addComponentsToPane(Container pane) {
        setLayout(new BorderLayout());
//        List<String> usernames=new ArrayList<>();
        usernameList = new JList<>(usernames);
        JLabel label=new JLabel("Online");
        JScrollPane usernameScrollPane = new JScrollPane(usernameList);
        logOutBtn=new JButton("Log Out");
//        logOutBtn.setBorder(new EmptyBorder(5,5,5,5));
//        usernameScrollPane.setPreferredSize(new Dimension(this.getPreferredSize().width, ));
        add(usernameScrollPane, BorderLayout.CENTER);
        add(logOutBtn,BorderLayout.PAGE_END);
        add(label,BorderLayout.PAGE_START);

    }
    public void addListeners(){
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                messageUtil.closeAll(messageUtil.getSocket(),messageUtil.getBufferedWriter(), messageUtil.getBufferedReader());
//                fileUtil.closeAll(fileUtil.getSocket(),fileUtil.getDataInputStream(),fileUtil.getDataOutputStream());
                Container frame = logOutBtn.getParent();
                do
                    frame = frame.getParent();
                while (!(frame instanceof JFrame));
                ((JFrame) frame).dispose();
                LoginView loginView=new LoginView();
                loginView.createAndShowGUI();
            }
        });
    }


    public static DefaultListModel getUsernames() {
        return usernames;
    }
}
