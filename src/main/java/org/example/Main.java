package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();

        Thread t1 = new Thread(() -> {
            account.deposit(10);
            account.withdraw(5);
        });
        t1.setName("t1");

        Thread t2 = new Thread(() -> {
            account.deposit(10);
            account.withdraw(10);
        });
        t2.setName("t2");

        t1.start();
        Thread.sleep(100);
        t2.start();

        Thread.sleep(15000);
        System.out.println("Balance: " + account.getBalance());
    }
}
