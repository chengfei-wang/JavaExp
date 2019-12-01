package dev.tty.nfcv.lottery.draw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class Config {
    private static final String APPLICATION_VERSION = "rc-1.0";
    static final String APPLICATION_NAME = "幸运观众手机号码抽取器@" + APPLICATION_VERSION;
    static final int WIDTH = 768;
    static final int HEIGHT = 512;
    private static final String DATA_PATH = "/temp/tty/nfcv/lottery/draw/records";
    static ArrayList<Model.FullRecord> fullRecords;
    @SuppressWarnings("ResultOfMethodCallIgnored")
    static void init() {
        File file = new File(DATA_PATH);
        if (file.exists()) {
            fullRecords = Config.read();
            if (fullRecords == null) {
                file.delete();
                init();
            }
        } else {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(new Gson().toJson(new ArrayList<Model.FullRecord>(), new TypeToken<ArrayList<Model.FullRecord>>(){}.getType()).getBytes());
                fos.close();
                fullRecords = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static ArrayList<Model.FullRecord> read() {
        File file = new File(DATA_PATH);
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuilder sb = new StringBuilder();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            fis.close();
            return new Gson().fromJson(sb.toString(), new TypeToken<ArrayList<Model.FullRecord>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static void save() {
        File file = new File(DATA_PATH);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(new Gson().toJson(fullRecords, new TypeToken<ArrayList<Model.FullRecord>>(){}.getType()).getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
