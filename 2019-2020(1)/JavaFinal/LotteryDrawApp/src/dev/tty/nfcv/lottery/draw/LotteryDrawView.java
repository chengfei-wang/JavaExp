package dev.tty.nfcv.lottery.draw;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static dev.tty.nfcv.lottery.draw.Config.APPLICATION_NAME;

class LotteryDrawFrame extends JFrame implements FunctionPanel.OnStartPressedListener {

    interface OnResultGenListener {
        void onResult(ArrayList<Model.Result> results);
    }

    OnResultGenListener listener;

    LotteryDrawFrame() {
        super(APPLICATION_NAME);
        setSize(Config.WIDTH + 10, Config.HEIGHT);
        setResizable(false);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainMenuBar mainMenuBar = new MainMenuBar(this);
        FunctionPanel functionPanel = new FunctionPanel(this);
        ResultListPanel userListPanel = new ResultListPanel(this);
        listener = userListPanel;
    }

    void display() {
        this.setVisible(true);
    }

    @Override
    public void onStartPressed(int first, int second, int third) {
        System.out.println("first: " + first);
        System.out.println("second: " + second);
        System.out.println("third: " + third);
        System.out.println();
    }
}

class MainMenuBar extends JMenuBar {
    MainMenuBar(JFrame parent) {
        setBackground(Color.WHITE);
        JMenu recordMenu = new JMenu("记录");
        JMenuItem recordMenuOpen = new JMenuItem("打开");
        recordMenu.add(recordMenuOpen);
        JMenuItem recordMenuSave = new JMenuItem("保存");
        recordMenu.add(recordMenuSave);
        add(recordMenu);
        parent.setJMenuBar(this);

//        JMenu infoMenu = new JMenu("信息");
//        JMenuItem infoMenuEdit = new JMenuItem("录入");
//        infoMenu.add(infoMenuEdit);
//        add(infoMenu);
        recordMenuOpen.addActionListener(e1 -> {

        });
    }
}

class FunctionPanel extends JPanel {
    interface OnStartPressedListener {
        void onStartPressed(int first, int second, int third);
    }
    OnStartPressedListener listener;
    FunctionPanel(JFrame parent) {
        int w = Config.WIDTH / 3;
        int h = Config.HEIGHT;
        setBounds(0, 0, w, h);
        setBackground(Color.WHITE);
        parent.add(this);
        setLayout(null);
        if (parent instanceof OnStartPressedListener) {
            listener = (OnStartPressedListener) parent;
        } else {
            throw new IllegalArgumentException("the parent should implements OnStartPressedListener");
        }
        JLabel themeLabel = new JLabel("主题");
        themeLabel.setBounds(4, 4, 40, 24);
        JTextField themeField = new JTextField(16);
        themeField.setBounds(48, 4, w - 60, 24);
        add(themeLabel);
        add(themeField);
        PrizeItemPanel firstPrize = new PrizeItemPanel(this, "一等奖");
        firstPrize.setBounds(0, 48, w, 32);
        PrizeItemPanel secondPrize = new PrizeItemPanel(this, "二等奖");
        secondPrize.setBounds(0, 80, w, 32);
        PrizeItemPanel thirdPrize = new PrizeItemPanel(this, "三等奖");
        thirdPrize.setBounds(0, 112, w, 32);

        JButton start = new JButton("开始抽奖");
        start.setBounds(4, 172, w - 8, 28);
        start.setBorderPainted(false);
        start.setBackground(Color.WHITE);
        add(start);

        UserTable userTable = new UserTable(this);
        userTable.setBounds(4, 200, w, 200);

        start.addActionListener(e -> {
            listener.onStartPressed(firstPrize.size, secondPrize.size, thirdPrize.size);
        });
    }
}

class PrizeItemPanel extends JPanel {
    int size = 0;
    PrizeItemPanel(JPanel parent, String prizeName) {
        setSize(parent.getWidth(), 32);
        setBackground(Color.WHITE);
        setLayout(null);
        JLabel name = new JLabel(prizeName);
        name.setBounds(4, 4, 96, 24);
        add(name);
        JButton sub = new JButton("-");
        sub.setBorderPainted(false);
        sub.setMargin(new Insets(0, 0, 0, 0));
        sub.setBackground(Color.WHITE);
        JButton add = new JButton("+");
        add.setBorderPainted(false);
        add.setMargin(new Insets(0, 0, 0, 0));
        add.setBackground(Color.WHITE);
        sub.setBounds(152, 4, 24, 24);
        JLabel sizeLabel = new JLabel(String.valueOf(size));
        sizeLabel.setHorizontalAlignment(JLabel.CENTER);
        sizeLabel.setBounds(176, 4, 24, 24);
        add.setBounds(200, 4, 24, 24);
        add(sub);
        add(sizeLabel);
        add(add);
        add.addActionListener(e -> {
            if (size <= 0) {
                size=1;
            } else {
                size++;
            }
            sizeLabel.setText(String.valueOf(size));
        });
        sub.addActionListener(e -> {
            if (size <= 0) {
                size = 0;
            } else {
                size--;
            }
            sizeLabel.setText(String.valueOf(size));
        });
        parent.add(this);
    }
}

class ResultListPanel extends JPanel implements LotteryDrawFrame.OnResultGenListener {
    ResultListPanel(JFrame parent) {
        int w = Config.WIDTH * 2 / 3;
        int h = Config.HEIGHT;
        setBounds(w / 2 + 10, 0, w, h);
        setBackground(Color.WHITE);
        ResultTable resultTable = new ResultTable(this);
        parent.add(this);
    }

    @Override
    public void onResult(ArrayList<Model.Result> results) {

    }
}

class UserTable extends JTable {
    UserTable(JPanel parent) {
        super(0, 2);
        JScrollPane pane = new JScrollPane(this);
        getColumnModel().getColumn(0).setHeaderValue("姓名");
        getColumnModel().getColumn(1).setHeaderValue("电话");
        parent.add(pane);
        parent.add(this);
    }
}

class ResultTable extends JTable {
    ResultTable(JPanel parent) {
        super(0, 3);
        setSize(parent.getWidth(), parent.getHeight());
        JScrollPane pane = new JScrollPane(this);
        getColumnModel().getColumn(0).setHeaderValue("姓名");
        getColumnModel().getColumn(1).setHeaderValue("电话");
        getColumnModel().getColumn(2).setHeaderValue("奖项");
        parent.add(pane);
    }
}