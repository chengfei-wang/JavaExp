package dev.tty.nfcv.lottery.draw;

import java.io.File;
import java.io.IOException;

class Config {
    private static final String APPLICATION_VERSION = "dev0.1";
    static final String APPLICATION_NAME = "幸运观众手机号码抽取器@" + APPLICATION_VERSION;
    static final int WIDTH = 768;
    static final int HEIGHT = 512;
    static final String DATA_PATH = "/temp/tty/nfcv/lottery/draw/record.dat";
    static void init() throws IOException {
        File data = new File(DATA_PATH);
        if (!data.exists()) {
            data.getParentFile().mkdirs();
            data.createNewFile();
        }
    }

}
