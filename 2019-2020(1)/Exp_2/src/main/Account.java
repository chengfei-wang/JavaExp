package main;

import java.text.DecimalFormat;

class Account {

    public void setId(String id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void deposit(String id, double amount) {
        if(!this.id.equals(id)) {
            System.out.println("账号未知");
        } else {
            if (amount<0) {
                System.out.println("存款不能小于0");
                return;
            }
            balance+=amount;
            System.out.println("成功存款 "+amount+" 元, 当前余额 "+balance+" 元");
        }
    }

    public void withdraw(String id, double amount) {
        if(!this.id.equals(id)) {
            System.out.println("账号未知");
        } else if(amount > balance) {
            System.out.println("余额不足");
        } else {
            if (amount<0) {
                System.out.println("取款不能小于0");
                return;
            }
            balance-=amount;
            System.out.println("成功取款 "+amount+" 元, 当前余额 "+balance+" 元");
        }
    }

    public void queryBalance(){
        System.out.println("id: "+id+", owner: "+owner+", balance: "+balance);
    }

    public String getBalance(String id) {
        return new DecimalFormat("#.00").format(balance);
    }

    private String id, owner;
    private double balance;
    Account(){
        id = null;
        owner = null;
        balance = 0.00;
    }
    Account(String id, String owner, double amount) {
        this.id = id;
        this.owner = owner;
        this.balance = amount;
    }

}
