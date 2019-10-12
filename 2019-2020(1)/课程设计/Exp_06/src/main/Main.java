package main;

import java.io.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;

class LoginFrame {
    void init() {
        JFrame frame = new JFrame("iChat: Login");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(290, 170);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(10, 80, 80, 25);
        panel.add(registerButton);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(185, 80, 80, 25);
        panel.add(loginButton);

        frame.setVisible(true);
    }
}


class Util {
    enum Field {
        USERID, TOKEN, NICKNAME;
    }

    public static HashMap<Field, String> getFields() {
        try {
            String conf = readFile("/app/iChat/conf");
            HashMap<Field, String> map = new HashMap<Field, String>();
            String[] fields = conf.split("::");
            for(String s : fields) {
                String[] entry = s.split("/");
                switch(entry[0]) {
                    case "USERID":
                        map.put(Field.USERID, entry[1]);
                        break;
                    case "TOKEN":
                        map.put(Field.TOKEN, entry[1]);
                        break;
                    case "NICKNAME":
                        map.put(Field.NICKNAME, entry[1]);
                        break;
                }
            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    static void setFields(HashMap<Field, String> map) {
        String userId = map.get(Field.USERID).replace("/", "").replace(":", "");
        String token = map.get(Field.TOKEN).replace("/", "").replace(":", "");
        String nickname = map.get(Field.NICKNAME).replace("/", "").replace(":", "");
        String conf = "USERID/"+userId+"::TOKEN/"+token+"::NICKNAME/"+nickname;
        writeFile("/app/iChat/conf", conf);
    }

    public static void writeFile(String filename, String content) {
        File file = new File(filename);
        FileWriter fw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file);
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFile(String filename) {
        File file = new File(filename);
        InputStream is = null;
        Reader isr = null;
        try {
            is = new FileInputStream(file);
            isr = new InputStreamReader(is);
            StringBuffer sb = new StringBuffer();
            int index = 0;
            while (-1 != (index = isr.read())) {
                sb.append((char)index);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}


public class Main {
    public static void main(String[] args) {
        // LoginFrame login = new LoginFrame();
        // login.init();
        Util.getFields();
    }
}