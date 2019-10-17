package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

class LoginFrame {
    private JFrame frame= new JFrame("iChat: Login");

    JTextField userText = new JTextField(20);
    JPasswordField passwordText = new JPasswordField(20);
    JButton registerButton = new JButton("注册");
    JButton loginButton = new JButton("登录");

    LoginFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(290, 170);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("用户名");
        userLabel.setBounds(10,20,60,25);
        panel.add(userLabel);

        userText.setBounds(80,20,185,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("密    码");
        passwordLabel.setBounds(10,50,60,25);
        panel.add(passwordLabel);

        passwordText.setBounds(80,50,185,25);
        panel.add(passwordText);

        registerButton.setBounds(10, 80, 90, 25);
        panel.add(registerButton);

        loginButton.setBounds(175, 80, 90, 25);
        panel.add(loginButton);
    }

    void show() {
        frame.setVisible(true);
    }

    void dismiss() {
        frame.setVisible(false);
    }

}

class App implements ActionListener {
    private LoginFrame loginFrame = new LoginFrame();
    void init() {
        var userInfo = Util.getFields();
        if (userInfo != null) {
            System.out.println("用户缓存存在");
            //todo...
        } else {
            System.out.println("暂无用户缓存");
            loginFrame.show();
            loginFrame.loginButton.addActionListener(this);
            loginFrame.registerButton.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginFrame.loginButton) {
            System.out.println("LOGIN");
            var userId = loginFrame.userText.getText();
            var password = loginFrame.passwordText.getPassword();
            System.out.println("UID: "+userId+"/PWD: "+ Util.getMD5(new String(password)));
        } else if (e.getSource() == loginFrame.registerButton) {
            System.out.println("REGISTER");
        }
    }
}

class O{

}

public class Main {
    public static void main(String[] args) {
//        var map = new HashMap<Util.Field, String>();
//        map.put(Util.Field.NICKNAME, "WCF");
//        map.put(Util.Field.TOKEN, "et871te81h1hd119j2d");
//        map.put(Util.Field.USERID, "2008153477");
//        Util.setFields(map);

        App app = new App();
        app.init();

//        Object o = make(6);
//        var inner =make(9);
//
//        System.out.println(inner.getClass());
//
//        System.out.println(o.toString());
//        o.m();
//
//        int[][] i = new int[4][];
    }


//    private static Object make(int i) {
//        class I {
//            private I(int i) {
//                this.i = i;
//            }
//
//            public void m() {
//                System.out.println("1323");
//            }
//
//            @Override
//            public String toString() {
//                return "class I "+i;
//            }
//
//            private int i;
//        }
//
//        return new I(i);
//    }
}