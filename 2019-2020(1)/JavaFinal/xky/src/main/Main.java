package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/app/story.txt");
        StringBuilder builder = new StringBuilder();
        FileInputStream stream = new FileInputStream(file);
        int ch;
        while ((ch = stream.read()) != -1) {
            builder.append((char)ch);
        }

        ArrayList<String> array = new ArrayList<String>(Arrays.asList(builder.toString().split("[^a-zA-Z0-9]")));
        while(array.remove("")){}
        int num=0;
//        int []a=new int[26];
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String s: array) {
            char c = s.toLowerCase().charAt(0);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        System.out.println(map);

//        System.out.println(Arrays.toString(strings));
    }
}
