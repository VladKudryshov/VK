package com.VladKudryshov.VK.frame;

import com.VladKudryshov.VK.outh.OuthVK;
import com.VladKudryshov.VK.response.ResponseServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameOuth extends JFrame {
    JPanel panel = null;
    JTextField password = new JTextField(20);
    JTextField login = new JTextField(20);


    public FrameOuth(){
        super("Авторизация");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300,400));
        setVisible(true);
        GeneratePanel();
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void GeneratePanel(){
        panel = new JPanel(new GridBagLayout());
        panel.add(new JLabel("First name:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        panel.add(login, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
        panel.add(new JLabel("Last name:"), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        panel.add(password, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JButton enter = new JButton("Войти");
        panel.add(enter, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResponseServer response = new ResponseServer();
                response.setUrl("https://oauth.vk.com/token?grant_type=password&client_id=2274003&client_secret=hHbZxrka2uZ6jB1inYsH&username="+login.getText()+"&password="+password.getText());
                new FrameAccount(new OuthVK(response.ResponseServerVK()));
                setVisible(false);
            }
        });
    }
}
