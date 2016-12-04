package com.VladKudryshov.VK.frame;

import com.VladKudryshov.VK.model.AccountVK;
import com.VladKudryshov.VK.outh.OuthVK;
import com.VladKudryshov.VK.response.ResponseServer;
import com.VladKudryshov.VK.wall.WallVk;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class FrameAccount extends JFrame{
    private JPanel panel;

    private JLabel name;
    private JLabel lastname;
    private JLabel online;

    private URL photo;

    private String token;
    private int userId;

    private ArrayList postId;
    OuthVK outh = null;
    AccountVK account = null;
    public FrameAccount(OuthVK outh) {
        super("l9l9l9l9l9");
        this.outh = outh;
        SetValuesOuth();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,400));
        setVisible(true);
        GeneratePanel();
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }



    private void GeneratePanel(){
        SetValues();
        Propeties();
        panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500,400));
        panel.setVisible(true);
        panel.add(new JLabel(new ImageIcon(photo)),new GridBagConstraints(0, 0, 1, 3, 0, 3, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(new JLabel("Друзья: "+account.getFriends()), new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(new JLabel("Группы: "+account.getGroups()), new GridBagConstraints(2, 2, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(new JLabel("Видео: "+account.getVideos()), new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(new JLabel("Аудио: "+account.getAudios()), new GridBagConstraints(2, 3, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(name, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(lastname, new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(online, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 0), 0, 0));
        JButton btnOk = new JButton("Очистить стену");
        JButton wall = new JButton("получить список кто репостнул");
        panel.add(btnOk, new GridBagConstraints(0, 3, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(wall, new GridBagConstraints(0, 4, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    ResponseServer responseServer = new ResponseServer();
                    for (int i = 0; i < postId.size(); i++) {
                        responseServer.setUrl("https://api.vk.com/method/wall.delete?owner_id="+userId+"&post_id="+postId.get(i)+"&access_token="+token+"&v=5.59");
                        System.out.println(responseServer.ResponseServerVK());
                        System.out.println(i+" Success");
                        for (int j = 0; j < 1000; j++) {
                        }
                    }
                }catch (Exception el){
                    System.out.println("Ошибка");
                }
            }
        });


        wall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResponseServer responseServer = new ResponseServer();
                    responseServer.setUrl("https://api.vk.com/method/wall.get?domain=exp.music&count=3&offset=1&v=5.59");
                JsonElement jelement = new JsonParser().parse(responseServer.ResponseServerVK());
                JsonObject jobject = jelement.getAsJsonObject();
                jobject = jobject.getAsJsonObject("response");
                JsonArray jarray = jobject.getAsJsonArray("items");
                jobject = jarray.get(0).getAsJsonObject();
                System.out.println(jobject.get("id"));
                jobject = jarray.get(1).getAsJsonObject();
                System.out.println(jobject.get("id"));
                jobject = jarray.get(2).getAsJsonObject();
                System.out.println(jobject.get("id"));

            }
        });
    }


    private void Propeties(){
        PropetiesLabel(name);
        PropetiesLabel(lastname);
    }
    private void PropetiesLabel(JLabel label){
        label.setFont(new Font("Dialog",2,22));
        label.setAlignmentY(Component.TOP_ALIGNMENT);
    }

    private void SetValuesElement(){
        ResponseServer response = new ResponseServer();
        response.setUrl("https://api.vk.com/method/users.get?user_id="+userId+"&fields=photo_100,online,counters&access_token="+token+"&v=5.59");
        System.out.println(response.ResponseServerVK());
        account = new AccountVK(response.ResponseServerVK());
        name = new JLabel(account.getFirstName());
        lastname = new JLabel(account.getLastName());
        try{
            photo = new URL(account.getUrlPhoto());
        }catch (Exception e){}
        online = new JLabel(account.getOnline());
        response.setUrl("https://api.vk.com/method/wall.get?owner_id="+userId+"&count=100&v=5.59");
        WallVk wallVk = new WallVk(response.ResponseServerVK());
        this.postId = wallVk.getPosts();
    }

    private void SetValuesOuth(){
        this.token = outh.getToken();
        this.userId = outh.getUserId();
    }

    private void SetValues(){
        SetValuesOuth();
        SetValuesElement();
    }



}
