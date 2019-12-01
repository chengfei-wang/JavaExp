package dev.tty.nfcv.lottery.draw;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Config.init();
        new LotteryDrawFrame().display();
    }
}
