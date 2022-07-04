package Client.Views.Login;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    JTabbedPane tabbedPane;
    public LoginView() {

        tabbedPane=new JTabbedPane();
        LoginTabPane loginTabPane =new LoginTabPane();
        loginTabPane.addListeners();
        SignUpTabPane signUpTabPane =new SignUpTabPane();
        signUpTabPane.addListeners();
        tabbedPane.add("Login", loginTabPane);
        tabbedPane.add("Sign Up", signUpTabPane);
        add(tabbedPane);

    }
    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,200));
        frame.setMinimumSize(new Dimension(350,200));
        //Create and set up the content pane.
        JComponent newContentPane = new LoginView();

        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
