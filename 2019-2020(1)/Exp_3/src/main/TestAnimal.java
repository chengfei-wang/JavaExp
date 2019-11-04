package main;

import p3.Bird;
import p3.Dog;

public class TestAnimal {
    public static void main(String[] args) {
        Dog dog = new Dog("little-dog", 2, 4.3);
        dog.eat();
        dog.move();
        dog.showInfo();
        Bird bird = new Bird("little-bird", 1, 0.10);
        bird.eat();
        bird.move();
        bird.showInfo();
    }
}

