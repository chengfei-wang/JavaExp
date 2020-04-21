import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        B b = new B();
//        A a = b;
//        ArrayList<Integer> c = new ArrayList<>();
//        c.add(1);
//        c.add(34);
//        Integer i1 = new Integer(1);
//        Integer i2 = 1;
//
//        System.out.println(i1 == i2);
//        System.out.println(c.toString());
//        System.out.println(a.i);




//        JFrame frame = new JFrame();
//        JButton button = new JButton();
//        button.addActionListener((event) -> {
//            Thread thread = new Thread(() -> {
//
//            });
//        });


//        HashMap<String, String> map = new HashMap<>();
//        map.put("a", "aaa");
//        map.put("b", "bbb");
//        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
//        Map.Entry<String, String> e = it.next();
//        System.out.println(e.getKey()+"->"+ e.getValue());


        HashMap<String, String> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");
        Iterator it = map.entrySet().iterator();
        Map.Entry<String, Character> e = (Map.Entry<String, Character>) it.next();
        System.out.println(e.getKey()+"->"+ e.getValue());


        ArrayList<String> arr = new ArrayList<>();
        arr.add("aaaaa");
        arr.add("bbbnn");

        Iterator iterator = arr.iterator();
        

    }
}

class A {
    int i = 10;
}

@Deprecated
class B extends A {
    int i = 20;
}


class K {

}