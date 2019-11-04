package p3;

public class Bird extends Animal {
    public Bird(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void eat() {
        System.out.println("鸟喜欢吃虫子");
    }

    @Override
    public void move() {
        System.out.println("鸟会飞");
    }

    @Override
    public void showInfo() {
        System.out.println("鸟, 名字："+name+", 年龄："+age+", 体重："+weight+" kg");
    }
}
