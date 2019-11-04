package p3;

public abstract class Animal {
    String name;
    int age;
    double weight;
    Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    public abstract void eat();
    public abstract void move();
    public abstract void showInfo();
}
