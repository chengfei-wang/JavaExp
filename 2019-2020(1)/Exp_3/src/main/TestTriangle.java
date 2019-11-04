package main;

import java.text.DecimalFormat;
import java.util.Random;

public class TestTriangle {

    public static void main(String[] args) {
        test_01();

    }

    private static void test_01() {
        System.out.println("-----------Triangle-----------");
        Random random = new Random();
        double a = random.nextDouble()*100;
        double b = random.nextDouble()*100;
        double c = random.nextDouble()*100;
        Triangle triangle = new Triangle(a, b ,c);
        triangle.getArea();
    }
}


class Triangle {
    private double a, b ,c;
    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean isTriangle() {
        return !(a + b <= c || a + c <= b || b + c <= a);
    }

    double getArea() {
        if (!isTriangle()) {
            System.out.println("边长为"+a+"，"+b+"和"+c+"的三条边无法构成三角形");
            return -1;
        }
        double p = (a+b+c)/2;
        double area = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println("三角形边长："+a+", "+b+", "+c+"，面积"+new DecimalFormat("#.00").format(area));
        return area;
    }

}