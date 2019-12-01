package dev.tty.nfcv.lottery.draw;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.SplittableRandom;

import static dev.tty.nfcv.lottery.draw.Config.APPLICATION_NAME;
import static dev.tty.nfcv.lottery.draw.Config.fullRecords;

class LotteryDrawFrame extends JFrame implements FunctionPanel.OnStartPressedListener {

    ArrayList<Model.User> users;
    ArrayList<Model.Result> results;
    String theme;

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
        mainMenuBar.recordMenuSave.addActionListener(e -> {
            System.out.println("save-record");
            if (theme == null || theme.equals("") || users == null || results == null) {
                //未完成抽奖
                System.out.println("主题为空或还未完成抽奖，不保存");
                return;
            }
            fullRecords.add(new Model.FullRecord(theme, users, results));
            Config.save();
            System.out.println("保存成功");
        });
        FunctionPanel functionPanel = new FunctionPanel(this);
        listener = new ResultListPanel(this);
    }

    void display() {
        this.setVisible(true);
    }

    @Override
    public void onStartPressed(String theme, int first, int second, int third, ArrayList<Model.User> users) {
        System.out.println("主题: " + theme);
        System.out.println("first: " + first);
        System.out.println("second: " + second);
        System.out.println("third: " + third);
        System.out.println("观众名单");
        for (Model.User user : users) {
            System.out.println(user);
        }
        System.out.println();
        this.theme = theme;
        this.users = users;
        this.results = Model.Result.getResult(first, second, third, users);
        listener.onResult(this.results);
    }
}

class MainMenuBar extends JMenuBar {
    JMenuItem recordMenuSave;
    MainMenuBar(JFrame parent) {
        setBackground(Color.WHITE);
        JMenu recordMenu = new JMenu("记录");
        JMenuItem recordMenuOpen = new JMenuItem("打开历史");
        recordMenu.add(recordMenuOpen);
        recordMenuSave = new JMenuItem("保存记录");
        recordMenu.add(recordMenuSave);
        add(recordMenu);
        parent.setJMenuBar(this);

        recordMenuOpen.addActionListener(e1 -> {
            System.out.println("open-history");
            new HistoryFrame();
        });
    }
}

class FunctionPanel extends JPanel {
    interface OnStartPressedListener {
        void onStartPressed(String theme, int first, int second, int third, ArrayList<Model.User> users);
    }
    private OnStartPressedListener listener;
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
        start.setBounds(4, 148, w - 8, 28);
        start.setBorderPainted(false);
        start.setBackground(Color.WHITE);
        add(start);

        UserTable userTable = new UserTable(this);

        start.addActionListener(e -> {
            listener.onStartPressed(themeField.getText(), firstPrize.size, secondPrize.size, thirdPrize.size, userTable.getUsers());
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
    ResultTable resultTable;
    ResultListPanel(JFrame parent) {
        int w = Config.WIDTH * 2 / 3;
        int h = Config.HEIGHT;
        setBounds(w / 2 + 10, 0, w, h);
        setBackground(Color.WHITE);
        resultTable = new ResultTable(this);
        parent.add(this);
    }

    @Override
    public void onResult(ArrayList<Model.Result> results) {
        System.out.println("获奖名单");
        for (Model.Result result : results) {
            System.out.println(result);
        }
        System.out.println();
        var model = (DefaultTableModel)resultTable.getModel();
        model.getDataVector().clear();
        for (Model.Result result: results) {
            model.addRow(new String[] {result.name, Model.Result.trim(result.phone), result.prize});
        }
    }
}

class UserTable extends JTable {
    DefaultTableModel model;
    UserTable(JPanel parent) {
        super(0, 2);
        setBackground(Color.LIGHT_GRAY);
        JScrollPane pane = new JScrollPane(this);
        pane.setBounds(0, 180, 256, 250);
        getColumnModel().getColumn(0).setHeaderValue("姓名");
        getColumnModel().getColumn(1).setHeaderValue("电话");
        parent.add(pane);
        setRowHeight(25);
        model = (DefaultTableModel)getModel();
        model.addRow(new String[] {"", ""});
        model.addTableModelListener(e -> {
            int rows = getRowCount();
            int row = getSelectedRow();
            int column = getSelectedColumn();
            if (row == rows - 1 && column == 1 && !getValueAt(row, 0).equals("")) {
                model.addRow(new String[] {"", ""});
            } else if (row == rows - 1 && column == 0 && !getValueAt(row, 1).equals("")) {
                model.addRow(new String[] {"", ""});
            } else if (row < rows - 1 && getValueAt(row, 0).equals("") && getValueAt(row, 1).equals("")) {
                model.removeRow(row);
            }
        });
    }

    ArrayList<Model.User> getUsers() {
        HashSet<Model.User> users = new HashSet<>();
        int rows = model.getRowCount();
        for (int i = 0; i < rows; i++) {
            String name = (String) model.getValueAt(i, 0);
            String phone = (String) model.getValueAt(i, 1);
            if (name.equals("") || phone.equals("")) {
                continue;
            }
            users.add(new Model.User(name, phone));
        }
        return new ArrayList<>(users);
    }
}

class ResultTable extends JTable {
    ResultTable(JPanel parent) {
        super(0, 3);
        JScrollPane pane = new JScrollPane(this);
        setEnabled(false);
        setRowHeight(25);
        pane.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        getColumnModel().getColumn(0).setHeaderValue("姓名");
        getColumnModel().getColumn(1).setHeaderValue("电话");
        getColumnModel().getColumn(2).setHeaderValue("奖项");
        parent.add(pane);
    }
}

class HistoryFrame extends JFrame {
    HistoryFrame() {
        System.out.println(fullRecords);
        setSize(600, 400);
        setLayout(null);
        JComboBox<Model.FullRecord> historyComboBox = new JComboBox<>();
        historyComboBox.setBounds(0, 0, 280, 24);
        DefaultComboBoxModel<Model.FullRecord> historyComboBoxModel = (DefaultComboBoxModel<Model.FullRecord>)historyComboBox.getModel();
        historyComboBoxModel.addAll(fullRecords);
        add(historyComboBox);
        if (fullRecords.size() > 0) {
            historyComboBox.setSelectedIndex(0);
        }
        JTable historyTable= new JTable();
        DefaultTableModel historyTableModel = (DefaultTableModel)historyTable.getModel();
        JScrollPane pane = new JScrollPane(historyTable);
        pane.setBounds(0, 30, 600, 360);
        add(pane);
        historyTableModel.addColumn("奖项");
        historyTableModel.addColumn("姓名");
        historyTableModel.addColumn("号码");
        historyTable.setEnabled(false);
        if (fullRecords.size() == 0) {
            historyComboBoxModel.addElement(new Model.FullRecord("无记录", null, null));
        } else {
            for(Model.Result result: fullRecords.get(0).results) {
                historyTableModel.addRow(new String[] {result.prize, result.name, Model.Result.trim(result.phone)});
            }
        }
        historyComboBox.addItemListener(e -> {
            var record = (Model.FullRecord)historyComboBoxModel.getSelectedItem();
            historyTableModel.getDataVector().clear();
            for(Model.Result result: record.results) {
                historyTableModel.addRow(new String[] {result.prize, result.name, Model.Result.trim(result.phone)});
            }
        });
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}