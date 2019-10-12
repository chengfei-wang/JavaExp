class SuperClass {
    int test() {
        System.out.println("test in SuperClass");
        return 0;
    }
}

class SubClass extends SuperClass {
    @Override
    int test() {
        System.out.println("test in SubClass");
        return 1;
    }
}

public class OverrideTest {
    public static void main(String[] args) {
        SubClass sub = new SubClass();
        sub.test();
        SuperClass super_00 = (SuperClass)sub;
        super_00.test();
        SuperClass super_01 = new SubClass();
        super_01.test();
        SuperClass super_02 = new SuperClass();
        super_02.test();
    }
}