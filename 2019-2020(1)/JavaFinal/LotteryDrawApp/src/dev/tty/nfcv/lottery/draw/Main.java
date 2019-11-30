package dev.tty.nfcv.lottery.draw;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config.init();
        new LotteryDrawFrame().display();
    }
}
