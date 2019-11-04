package p1;

import java.util.Calendar;
import java.util.Date;

public class Student extends Person {
    private String school, id, major, cls;
    private int grade;

    public Student(String name, char sex, Date birth, String school, String id, String major, int grade, String cls) {
        super(name, sex, birth);
        this.school = school;
        this.id = id;
        this.major = major;
        this.cls = cls;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"
                +"学校 "+school+", 学号 "+id+", 专业 "+major+", 年级 "+grade+", 班级"+cls;
    }
}
