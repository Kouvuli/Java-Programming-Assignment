package Client.Views.Chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MessageRow extends JPanel {
    private String message;
    public MessageRow(Component component,float alignment) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        setLayout(new BorderLayout());
//        setSize(100,20);
//        this.setPreferredSize(new Dimension(0,10));
//        setPreferredSize(new Dimension(400,20));
        setAlignmentX(alignment);
//        JLabel mess=new JLabel(message);
        add(component);
    }

}
