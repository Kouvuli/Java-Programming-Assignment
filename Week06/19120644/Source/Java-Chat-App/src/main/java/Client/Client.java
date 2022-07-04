package Client;

import Client.Views.Login.LoginView;

import javax.swing.*;

public class Client {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginView=new LoginView();
                loginView.createAndShowGUI();
            }
        });

    }
}
