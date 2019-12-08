package dev.tty.nfcv.lottery.draw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class Model {
    static class User {
        String name;
        String phone;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return Objects.equals(phone, user.phone);
        }

        User(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        @Override
        public int hashCode() {
            return phone != null ? phone.hashCode() : 0;
        }

        @Override
        public String toString() {
            return String.format("name='%s', phone='%s'", name, phone);
        }
    }

    static class Result {
        String name;
        String phone;
        String prize;

        enum Prize {
            FIRST, SECOND, THIRD;

            @Override
            public String toString() {
                switch (this) {
                    case FIRST:
                        return "一等奖";
                    case SECOND:
                        return "二等奖";
                    case THIRD:
                        return "三等奖";
                }
                return "未中奖";
            }
        }

        @Override
        public String toString() {
            return String.format("name='%s', phone='%s', prize='%s'", name, phone, prize);
        }

        public Result(String name, String phone, String prize) {
            this.name = name;
            this.phone = phone;
            this.prize = prize;
        }

        public static String trim(String phone) {
            if (phone.length() < 2) {
                phone = "**";
            }
            return phone.substring(0, phone.length()-2)+"**";
        }

        static ArrayList<Result> getResult(int first, int second, int third, ArrayList<User> users) {
            Collections.shuffle(users);
            ArrayList<Result> results = new ArrayList<>();
            for (User user : users) {
                if (first > 0) {
                    results.add(new Result(user.name, user.phone, Prize.FIRST.toString()));
                    first--;
                } else if (second > 0) {
                    results.add(new Result(user.name, user.phone, Prize.SECOND.toString()));
                    second--;
                } else if (third > 0) {
                    results.add(new Result(user.name, user.phone, Prize.THIRD.toString()));
                    third--;
                } else {
                    break;
                }
            }
            return results;
        }
    }

    static class FullRecord {
        String theme;
        ArrayList<User> users;
        ArrayList<Result> results;
        Date time;

        public FullRecord(String theme, ArrayList<User> users, ArrayList<Result> results, Date time) {
            this.theme = theme;
            this.users = users;
            this.results = results;
            this.time = time;
        }

        @Override
        public String toString() {
            if (theme.equals("无记录")) {
                return theme;
            }
            return "主题-" + theme + "-" + time;
        }
    }
}
