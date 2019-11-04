package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        ArrayUtil.test0();
//        Translate.test2();
//        DateOutput.test3();
        ParkingFeeCollection1.test4();
    }
}

class ArrayUtil {
    private static int maxElement(int[] a) {
        int max = a[0];
        for(int e: a) {
            if (e > max) {
                max = e;
            }
        }
        return max;
    }

    private static int average(int[] a) {
        int sum = 0;

        for (int e: a) {
            sum += e;
        }
        return sum/a.length;
    }

    private static int search(int[] a, int k) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                return i;
            }
        }
        return -1;
    }

    public static void test0() {
        int[] num = new int[20];
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();

        do {
            set.add(random.nextInt(40) + 60);
        } while (set.size() < num.length);

        int i = 0;
        for(int e: set) {
            num[i++] = e;
            System.out.println(e);
        }
        System.out.println(Arrays.toString(num));
        System.out.println("max: " + ArrayUtil.maxElement(num));
        System.out.println("avg: " + ArrayUtil.average(num));
        System.out.println("find 70: " + ArrayUtil.search(num, 70));
    }
}

class Translate {
    private static String[] x ={"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"} ;
    private static String[] y ={"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static String[] z ={"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static void test2() {
        ArrayList<String> ax = new ArrayList<String>(Arrays.asList(x));
        ArrayList<String> ay = new ArrayList<String>(Arrays.asList(y));
        ArrayList<String> az = new ArrayList<String>(Arrays.asList(z));
        ArrayList<String> at = new ArrayList<>();
        at.addAll(ax);
        at.addAll(ay);
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入：");
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            try {
                int num = Integer.parseInt(s);

                if (num >= 20 && num < 100) {
                    System.out.print(z[(num/10) -2]);
                    if (num%10 != 0) {
                        System.out.println(" " + x[num - (num/10) * 10]);
                    } else {
                        System.out.println();
                    }
                } else if (num >= 0 && num < 100) {
                    System.out.println(at.get(num));
                } else {
                    System.out.println("...");
                }
            } catch (NumberFormatException e) {
                String[] nums = s.split(" ");
                int sum = 0;
                for (String item : nums) {
                    if (ax.contains(item)) {
                        sum += ax.indexOf(item);
                    } else if (ay.contains(item)) {
                        sum += ay.indexOf(item) + 10;
                    } else if (az.contains(item)) {
                        sum += (az.indexOf(item) + 2) * 10;
                    }
                }
                System.out.println(sum);
            }
        }
    }
}

class DateOutput {
    private static Calendar calendar = Calendar.getInstance();

    static void test3() {
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year + "年");
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(month + "月");
        String season;
        switch (month) {
            case 1:
            case 2:
            case 3:
                season = "春";
                break;
            case 4:
            case 5:
            case 6:
                season = "夏";
                break;
            case 7:
            case 8:
            case 9:
                season = "秋";
                break;
            case 10:
            case 11:
            case 12:
                season = "冬";
                break;
            default:
                season = "?";
        }
        System.out.println(season);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                System.out.println("星期日");
                break;
            case 2:
                System.out.println("星期一");
                break;
            case 3:
                System.out.println("星期二");
                break;
            case 4:
                System.out.println("星期三");
                break;
            case 5:
                System.out.println("星期四");
                break;
            case 6:
                System.out.println("星期五");
                break;
            case 7:
                System.out.println("星期六");
                break;
            default:
                System.out.println("?");
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(sdf.format(calendar.getTime()));
        sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdf.format(calendar.getTime()));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(calendar.getTime()));
    }
}

class ParkingFeeCollection1 {
    static void test4() {
        String[] _in = new String[]{
                "2014-10-08 12:02:13",
                "2014-10-08 13:12:15",
                "2014-10-08 14:52:17",
                "2014-10-08 15:12:15",
                "2014-10-08 16:12:15",
                "2014-10-08 20:12:15",
                "2014-10-08 16:12:15",
                "2014-10-08 17:12:15",
        };

        String[] _out = new String[]{
                "2014-10-08 12:13:56",
                "2014-10-08 13:48:42",
                "2014-10-08 16:28:22",
                "2014-10-08 20:38:49",
                "2014-10-09 07:29:52",
                "2014-10-09 07:45:26",
                "2014-10-09 13:49:53",
                "2014-10-11 15:12:12"
        };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i< _in.length; i++) {
            try {
                Calendar in = Calendar.getInstance();
                Calendar out = Calendar.getInstance();
                System.out.println("停车时间: " + _in[i]);
                in.setTime(sdf.parse(_in[i]));
                System.out.println("离开时间: " + _out[i]);
                out.setTime(sdf.parse(_out[i]));
                long diff = out.getTimeInMillis() - in.getTimeInMillis();
                if (diff < 0) {
                    System.out.println("输入时间有误");
                    return;
                }
                int sum = 0;
                int diffDay = out.get(Calendar.DAY_OF_YEAR) - in.get(Calendar.DAY_OF_YEAR);
                if (diffDay == 0) {
                    int diffMinuit = (int) (diff/60000 - 15);
                    if (diffMinuit < 0) {
                        sum = 0;
                    } else {
                        sum = ((diffMinuit)/30)*5;
                    }
                    if (sum > 30) {
                        sum = 30;
                    }
                    System.out.println(sum);
                } else {
                    sum += (diffDay -1)*30;
                    Calendar c0 = Calendar.getInstance();
                    c0.setTime(in.getTime());
                    c0.set(Calendar.HOUR_OF_DAY, 23);
                    c0.set(Calendar.MINUTE, 59);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(out.getTime());
                    c1.set(Calendar.HOUR_OF_DAY, 0);
                    c1.set(Calendar.MINUTE, 0);
                    int diff0 = (int) (c0.getTimeInMillis() - in.getTimeInMillis())/60000;
                    int diff1 = (int) (out.getTimeInMillis() - c1.getTimeInMillis())/60000;
                    if(diff0>15){
                        int s0 = ((diff0-15)/30)*5;
                        if (s0 > 30) s0 = 30;
                        sum += s0;
                    }
                    int s1 = (diff1/30)*5;
                    if (s1 > 20) {
                        s1 = 30;
                    }
                    sum += s1;

                    System.out.println(sum);
                }

            } catch (ParseException e) {
                System.out.println("输入时间有误");
            }
        }


    }
}

