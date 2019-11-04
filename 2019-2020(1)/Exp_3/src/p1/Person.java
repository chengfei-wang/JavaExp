package p1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String name;
    private char sex;
    private Date birth;

    Person(String name, char sex, Date birth) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "姓名 "+name+", 性别 "+sex+", 出生日期 "+new SimpleDateFormat("YYYY/MM/dd").format(birth);
    }

    public void printInfo() {
        System.out.println(this);
    }
}


