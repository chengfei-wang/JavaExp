package nfcv.computer.network;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new UserLoginApp();
    }
}

class ButtonInfoShowDemo extends JFrame {
    ButtonInfoShowDemo() {
        setLayout(new FlowLayout());
        setSize(300, 100);
        setResizable(false);
        JButton b1 = new JButton("b1");
        b1.setSize(40, 40);
        JButton b2 = new JButton("b2");
        b2.setSize(40, 40);
        JTextField text = new JTextField(6);
        text.setSize(20, 40);
        add(b1);
        add(b2);
        add(text);
        b1.addActionListener(e -> text.setText("b1"));
        b2.addActionListener(e -> text.setText("b2"));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class GuessNumber extends JFrame {
    int num;
    GuessNumber() {
        setLayout(null);
        setSize(300, 150);
        setResizable(false);
        num = (int) (new Random().nextDouble() * 100 + 1);
        System.out.println(num);
        JLabel hint = new JLabel("请猜数字");
        add(hint);
        JTextField res = new JTextField(8);
        add(res);
        JButton submit = new JButton("提交");
        add(submit);
        hint.setBounds(5, 0, 300, 20);
        res.setBounds(5, 30, 300, 20);
        submit.setBounds(5, 80, 300, 20);
        submit.addActionListener(e -> {
            try{
                int n = Integer.parseInt(res.getText());
                if (n > num) {
                    hint.setText("输入的数偏大");
                } else if (n < num) {
                    hint.setText("输入的数偏小");
                } else {
                    hint.setText("恭喜你猜对了");
                }
            } catch (Exception ex) {
                hint.setText("请输入一个数字");
            }

        });
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class UserLoginApp extends JFrame {
    enum Type {
        Stu, Tea;

        @Override
        public String toString() {
            if (this == Stu) {
                return "学生用户";
            } else {
                return "教师用户";
            }
        }
    }
    UserLoginApp() {
        setLayout(null);
        setSize(200, 160);
        setResizable(false);
        JLabel userType = new JLabel("用户类型");
        userType.setBounds(0, 0, 60, 30);
        JComboBox<Type> chooseUser = new JComboBox<>();
        DefaultComboBoxModel<Type> model = (DefaultComboBoxModel<Type>) chooseUser.getModel();
        model.addAll(new ArrayList<>(Arrays.asList(Type.Stu, Type.Tea)));
        chooseUser.setBounds(60, 0, 100, 30);
        chooseUser.setSelectedIndex(0);
        add(userType);
        add(chooseUser);

        JLabel labelId = new JLabel("用户名");
        JLabel labelPwd = new JLabel("密  码");
        JTextField id = new JTextField();
        JTextField pwd = new JTextField();
        labelId.setBounds(0, 30, 60, 30);
        labelPwd.setBounds(0, 60, 60, 30);
        add(labelId);
        add(labelPwd);
        id.setBounds(60, 30, 100, 30);
        pwd.setBounds(60, 60, 100, 30);
        add(id);
        add(pwd);
        JButton login = new JButton("登录");
        JButton cancel = new JButton("取消");
        JButton exit = new JButton("退出");
        login.setBounds(0, 90, 60, 30);
        cancel.setBounds(60, 90, 60, 30);
        exit.setBounds(120, 90, 60, 30);
        add(login);
        add(cancel);
        add(exit);
        login.addActionListener(e -> {
            if (id.getText().equals("")) {
                System.out.println("用户名不可为空");
            } else if (pwd.getText().equals("")) {
                System.out.println("密码不可为空");
            } else {
                if (model.getSelectedItem() == Type.Stu && id.getText().equals("s") && pwd.getText().equals("s")) {
                    System.out.println("学生用户登录成功");
                } else if (model.getSelectedItem() == Type.Tea && id.getText().equals("t") && pwd.getText().equals("t")) {
                    System.out.println("教师用户登录成功");
                } else {
                    System.out.println("用户名不存在或者密码不正确");
                }
            }
        });
        cancel.addActionListener(e -> {id.setText(""); pwd.setText("");} );
        exit.addActionListener(e -> System.exit(0));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}