package dev.tty.nfcv.lottery.draw;

import java.io.*;

class Config {
    private static final String APPLICATION_VERSION = "dev0.1";
    static final String APPLICATION_NAME = "幸运观众手机号码抽取器@" + APPLICATION_VERSION;
    static final int WIDTH = 768;
    static final int HEIGHT = 512;
    private static final String DATA_PATH = "/temp/tty/nfcv/lottery/draw/record.dat";
    @SuppressWarnings("ResultOfMethodCallIgnored")
    static void init() throws IOException {
        File data = new File(DATA_PATH);
        if (!data.exists()) {
            data.getParentFile().mkdirs();
            data.createNewFile();
        }
    }

    static String readFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                sb.append((char)ch);
            }
            fis.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeFile(File file, String data) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
