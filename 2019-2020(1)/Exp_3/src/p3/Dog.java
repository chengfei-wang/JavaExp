package p3;

public class Dog extends Animal {
    public Dog(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void eat() {
        System.out.println("狗喜欢吃肉");
    }

    @Override
    public void move() {
        System.out.println("狗会跑");
    }

    @Override
    public void showInfo() {
        System.out.println("狗, 名字："+name+", 年龄："+age+", 体重："+weight+" kg");
    }
}
