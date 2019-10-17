package main;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        test_01();
        test_02();
        test_03();
    }

    private static void test_01() {
        System.out.println("-----------Triangle-----------");
        Random random = new Random();
        double a = random.nextDouble()*100;
        double b = random.nextDouble()*100;
        double c = random.nextDouble()*100;
        Triangle triangle = new Triangle(a, b ,c);
        System.out.println("三角形边长："+triangle.a+", "+triangle.b+", "+triangle.c+"; 面积："+triangle.area());
    }

    private static void test_02() {
        System.out.println("-----------Complex-----------");
        Complex complex = new Complex(1, 2);
        System.out.println(complex);
        Complex _complex = new Complex(3, 4);
        System.out.println(_complex);
        System.out.println(complex.complexAdd(_complex));
    }

    private static void test_03() {
        System.out.println("-----------Account-----------");
        Account account = new Account("10000", "abc", 100.00);
        account.queryBalance();
        Random random = new Random();
        account.deposit("10000", random.nextDouble()*100);
        account.withdraw("10000", random.nextDouble()*100);
        account.setId("10001");
        account.setOwner("def");
        System.out.println(account.getBalance("10001"));
        account.queryBalance();
    }
}
