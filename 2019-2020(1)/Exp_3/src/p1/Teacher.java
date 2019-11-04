package p1;

import java.util.Date;

public class Teacher extends Person {
    private String school, id;

    public Teacher(String name, char sex, Date birth, String school, String id) {
        super(name, sex, birth);
        this.school = school;
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"
                +"学校 "+school+", 工号 "+id;
    }
}
