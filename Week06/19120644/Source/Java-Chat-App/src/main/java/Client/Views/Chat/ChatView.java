package Client.Views.Chat;

import Client.Utils.FileUtil;
import Client.Utils.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ChatView extends JPanel {
    private MessageUtil messageUtil;
    public FileUtil fileUtil;
    public ChatView(MessageUtil messageUtil, FileUtil fileUtil) {
        this.messageUtil = messageUtil;
        this.fileUtil=fileUtil;
    }

    public void addComponentsToPane(Container pane) {
//        SwingUtilities.invokeLater();
        JFrame parent = (JFrame) SwingUtilities.getRoot(pane);
        ChatPane chatPane = new ChatPane(messageUtil,fileUtil);
        chatPane.setPreferredSize(new Dimension((int) (parent.getWidth() * 0.7), parent.getHeight()));
        JPanel jPanel1=new JPanel();
        chatPane.addComponentsToPane(jPanel1);
        chatPane.addListeners();

        UserListPane userListPane = new UserListPane(messageUtil,fileUtil);
        userListPane.setPreferredSize(new Dimension((int) (parent.getWidth() * 0.3), parent.getHeight()));
        JPanel jPanel=new JPanel();
        userListPane.addComponentsToPane(jPanel);
        userListPane.addListeners();
        JSplitPane chatView = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        chatView.add(userListPane);
        chatView.add(chatPane);

        chatView.setDividerLocation(100);
        chatView.setDividerSize(5);
        pane.add(chatView);
    }

    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setMinimumSize(new Dimension(600, 400));
        //Create and set up the content pane.
        addComponentsToPane(frame.getContentPane());


        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
