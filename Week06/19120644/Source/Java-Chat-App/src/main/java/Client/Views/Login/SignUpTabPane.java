package Client.Views.Login;

import Server.DAO.AccountDAO;
import Server.Entities.Account;
import Server.Services.AccountService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpTabPane extends JPanel {
    JTextField usernameTField;
    JPasswordField passwordPField;
    JPasswordField retypePasswordPField;
    JButton nextBtn;
    JButton cancelBtn;
    public SignUpTabPane(){
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
        jPanel1.add(Box.createRigidArea(new Dimension(50,0)));
        jPanel1.add(usernameTField);

        JPanel jPanel2=new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.X_AXIS));

        JLabel passwordLb=new JLabel("Password:");
        jPanel2.add(passwordLb);
        passwordPField=new JPasswordField();
        passwordPField.setPreferredSize(new Dimension(200,25));
        passwordPField.setMaximumSize(new Dimension(300,25));
        passwordPField.setMinimumSize(new Dimension(100,25));
        jPanel2.add(Box.createRigidArea(new Dimension(53,0)));
        jPanel2.add(passwordPField);


        JPanel jPanel3=new JPanel();
        jPanel3.setLayout(new BoxLayout(jPanel3,BoxLayout.X_AXIS));

        JLabel retypePasswordLb =new JLabel("Retype Password:");

        jPanel3.add(retypePasswordLb);
        retypePasswordPField=new JPasswordField();
        retypePasswordPField.setPreferredSize(new Dimension(200,25));
        retypePasswordPField.setMaximumSize(new Dimension(300,25));
        retypePasswordPField.setMinimumSize(new Dimension(100,25));
        jPanel3.add(Box.createRigidArea(new Dimension(10,0)));
        jPanel3.add(retypePasswordPField);


        content.add(jPanel1);
        jPanel1.setBorder(new EmptyBorder(5,5,0,5));
        content.add(jPanel2);
        jPanel2.setBorder(new EmptyBorder(5,5,0,5));
        content.add(jPanel3);
        jPanel3.setBorder(new EmptyBorder(5,5,0,5));


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
                }else if (!String.valueOf(passwordPField.getPassword()).equals(String.valueOf(retypePasswordPField.getPassword()))){
                    JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Retype password is not correct!");
                }
                else{
                    AccountService service=new AccountService();
                    char[] a=passwordPField.getPassword();
                    String newPassword=String.valueOf(a);
                    AccountDAO dao=new AccountDAO();
                    Account account=null;
                    try{
                        account=dao.getAccountByUsername(usernameTField.getText());

                    }catch (Exception exception){
                        exception.printStackTrace();
                    }

                    if(account==null){
                        service.signUpAccount(usernameTField.getText(),newPassword);
                        resetDataInput();
                        JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Sign Up Success!");
                    }else{
                        JOptionPane.showMessageDialog(((Component) e.getSource()).getParent(), "Username already exists!");
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

    public void resetDataInput(){
        usernameTField.setText("");
        passwordPField.setText("");
        retypePasswordPField.setText("");
    }
    public boolean isContainSpecialCharacter(){
        char[] a= passwordPField.getPassword();
        char[] b= retypePasswordPField.getPassword();
        if (usernameTField.getText().contains(",")||usernameTField.getText().contains("--")||usernameTField.getText().contains("'")||usernameTField.getText().contains("SELECT")||usernameTField.getText().contains("UNION")||usernameTField.getText().contains("UPDATE")||usernameTField.getText().contains("INSERT")||usernameTField.getText().contains("=")){
            return true;

        }

        else if(String.valueOf(a).contains(",")||String.valueOf(a).contains("--")||String.valueOf(a).contains("'")||String.valueOf(a).contains("SELECT")||String.valueOf(a).contains("UNION")||String.valueOf(a).contains("UPDATE")||String.valueOf(a).contains("INSERT")||String.valueOf(a).contains("=")){
            return true;
        }
        else if(String.valueOf(b).contains(",")||String.valueOf(b).contains("--")||String.valueOf(b).contains("'")||String.valueOf(b).contains("SELECT")||String.valueOf(b).contains("UNION")||String.valueOf(b).contains("UPDATE")||String.valueOf(b).contains("INSERT")||String.valueOf(b).contains("=")){
            return true;
        }
        return false;
    }

    public boolean isInputEmpty(){
        if(usernameTField.getText().isEmpty()){
            return true;
        }else if (String.valueOf(passwordPField.getPassword()).isEmpty()){
            return true;
        }else if(String.valueOf(retypePasswordPField.getPassword()).isEmpty()){
            return true;
        }
        return false;
    }
}
