package main;

import p1.Student;
import p1.Teacher;

import java.util.Calendar;

public class TestPerson {
    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, Calendar.JANUARY, 1);
        Student student = new Student("stu-name", '男', calendar1.getTime(), "ZJUT", "201800000000", "majorA", 2, "class-01");
        student.printInfo();
        Teacher teacher = new Teacher("tch-name", '男', calendar1.getTime(), "ZJUT", "000000");
        teacher.printInfo();
    }
}
