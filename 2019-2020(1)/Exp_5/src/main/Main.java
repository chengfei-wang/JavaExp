package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        test01();
//        test03(args);
//        test02();
    }

    private static void test01() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String exp = scanner.nextLine();
            String[] items = exp.split("\\+");
            int sum = 0;
            try {
                for (String item : items) {
                    int i = Integer.parseInt(item);
                    sum += i;
                }
                System.out.println(sum);
            } catch (NumberFormatException e) {
                System.out.println("格式错误");
            }
        }
    }

    private static void test02() {
        class Triangle {
            Triangle(double a, double b, double c) {
                if (check(a, b, c)) {
                    System.out.println("可以构成三角形");
                } else {
                    throw new IllegalArgumentException("不能构成三角形");
                }
            }
            private boolean check(double a, double b, double c) {
                return !(a + b <= c) && !(a + c <= b) && !(b + c <= a);
            }
        }
        try {
            new Triangle(1.2, 3, 4);
            new Triangle(10, 2.0, 3.0);
        } catch (IllegalArgumentException e) {
            System.out.println("不能构成三角形");
        }

    }

    private static void test03(String[] args) {
        try {
            switch (args.length) {
                case 1:
                    System.out.println("圆形");
                    System.out.println(new Circle(args).getArea());
                    break;
                case 2:
                    System.out.println("矩形");
                    System.out.println(new Rectangle(args).getArea());
                    break;
                case 3:
                    System.out.println("三角形");
                    System.out.println(new Triangle(args).getArea());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("参数异常");
        }

    }
}

abstract class Shape {
    abstract double getArea();
}
class Circle extends Shape {
    private double r;
    Circle(String[] args) {
        try {
            r = Double.parseDouble(args[0]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("args error");
        }
    }

    @Override
    double getArea() {
        return Math.PI*r;
    }
}
class Rectangle extends Shape {
    Rectangle(String[] args) {
        try {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("args error");
        }
    }
    private double a,b;
    @Override
    double getArea() {
        return a*b;
    }
}
class Triangle extends Shape {
    private double a, b, c;
    Triangle(String[] args) {
        try {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            c = Double.parseDouble(args[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("args error");
        }
    }
    @Override
    double getArea() throws IllegalArgumentException {
        if (check(a, b, c)) {
            double p = (a+b+c)/2;
            return Math.sqrt(p*(p-a)*(p-b)*(p-c));
        } else {
            throw new IllegalArgumentException();
        }
    }
    private boolean check(double a, double b, double c) {
        return !(a + b <= c) && !(a + c <= b) && !(b + c <= a);
    }
}