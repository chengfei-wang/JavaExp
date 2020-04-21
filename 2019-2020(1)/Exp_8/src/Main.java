import java.security.SecureRandom;

public class Main {


    public static void main(String[] args) {
        t3();
    }

    static void t1() {
        class PrintLetter extends Thread {
            @Override
            public void run() {
                char a = 'a';
                do {
                    try {
                        System.out.println(a);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (a++ < 'z');
            }
        }
        new PrintLetter().start(); // 继承于Thread
        new Thread(() -> {
            char a = 'a';
            do {
                try {
                    System.out.println(a);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (a++ < 'z');
        }).start(); // 实现Runnable接口
    }

    static void t2() {
        class PrintThread extends Thread {
            String name;
            PrintThread(String name) {
                this.name = name;
                this.start();
            }
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + "正在运行");
                }
            }
        }
        PrintThread t1 = new PrintThread("线程1");
        PrintThread t2 = new PrintThread("线程2");
    }

    static void t3() {
        ATMHelper u1 = new ATMHelper("甲", 100);
        ATMHelper u2 = new ATMHelper("乙", -50);
        try {
            u1.start();
            u2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ATMHelper extends Thread {
    static int m = 200;

    int change;
    String name;

    ATMHelper(String name, int change) {
        this.name = name;
        this.change = change;
    }

    synchronized void change() throws Exception {
        System.out.println(name + ": " + change);
        if (change + m < 0) {
            throw new Exception("余额不足");
        } else {
            m = change + m;
        }
    }

    @Override
    public void run() {
        try {
            change();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}