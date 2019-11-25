package main;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
//        HandInput.inputAndSave();
//        WordCount.count("D:/temp/article.txt");
//        StudentInfoManager.manage();
        CodeStatus.getStatus("D:\\Documents\\Github\\TTY-Community-Kotlin\\src");
    }

}

class HandInput {
    public static void inputAndSave() {
        System.out.println("请输入文件名");
        Scanner scanner = new Scanner(System.in);
        File file;
        do {
            String filename = scanner.nextLine();
            file = new File("D:/"+filename);
            if(file.exists()) {
                System.out.println("文件名重复，请重新输入");
            }
        } while (file.exists());

        try {
            file.createNewFile();
            System.out.println("请输入");
            FileOutputStream fos = new FileOutputStream(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("end#")){
                    break;
                }
                fos.write((line+"\n").getBytes());
            }
            System.out.println("文件写入结束");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WordCount {
    static void count(String filepath) {
        File file = new File(filepath);
        if(!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = fis.read()) != -1) {
                sb.append((char) ch);
            }
            String[] words = sb.toString().split("\\W");
            int num = 0;
            for(String s: words) {
                if(s.equals("hello")) {
                    num++;
                }
            }
            fis.close();
            System.out.println("单词hello出现的次数为"+num+"次");
        } catch (IOException e) {
            System.out.println("文件读取错误");
        }
    }
}

class StudentInfoManager {
    static class Student {
        String id;
        String name;
        String sex;
        Student(String id, String name, String sex) {
            this.id = id;
            this.name = name;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "id: "+id+" name: "+name+" sex: "+sex;
        }
    }
    private static Vector<Student> students = new Vector<>();

    private static void add(Student stu) {
        students.add(stu);
    }

    private static void delete(String stuId) {
        students.removeIf(student -> student.id.equals(stuId));
    }

    private static void show() {
        for(Student stu : students) {
            System.out.println(stu);
        }
    }

    static void manage() {
        while (true) {
            menu();
        }
    }

    private static void menu() {
        System.out.println("菜单：\n1. 添加\n2. 显示\n3. 删除\n4. 退出\n");
        Scanner scanner = new Scanner(System.in);
        try {
            int opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("请输入学号");
                    String id = scanner.next();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    System.out.println("请输入性别（男/女）");
                    String sex = scanner.next();
                    add(new Student(id, name, sex));
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    System.out.println("请输入要删除的学号");
                    String delId = scanner.next();
                    delete(delId);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入异常");
                    break;
            }
        } catch (Exception e) {
            System.out.println("输入异常");
        }

    }
}

class CodeStatus {
    private static int lines = 0;
    private static int javas = 0;
    static void getStatus(String dirPath) {
        lines = 0;
        javas = 0;
        File file = new File(dirPath);
        if(file.exists() && file.isDirectory()) {
            getDirStatus(file);
        } else {
            System.out.println("文件夹路径错误");
        }
        System.out.println(file.getAbsolutePath() + "包含*.java文件数"+javas+"个，代码行数"+lines);
    }

    private static void getDirStatus(File file) {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null) {
                return;
            }
            for(File f : files) {
                getDirStatus(f);
            }
        } else if (file.isFile() && file.getName().endsWith(".java")) {
            javas++;
            lines += getFileStatus(file);
        }

    }

    private static int getFileStatus(File file) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = fis.read()) != -1) {
                sb.append((char) ch);
            }
            String[] content = sb.toString().split("\n");
            int lines = 0;
            for(String line : content) {
                if(line.length() > 0) {
                    lines++;
                }
            }
            return lines;
        } catch (IOException e) {
            return 0;
        }
    }
}