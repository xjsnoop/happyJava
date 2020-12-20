package cn.xujan.domain;

public class Acount {
    private String name;
    private double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Acount{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
