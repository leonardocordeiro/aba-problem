package org.example;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Account {
    private final AtomicStampedReference<Integer> balance = new AtomicStampedReference<>(0, 0);

    public void deposit(Integer amount) {
        int[] stamps = new int[1];
        Integer currentAmount = balance.get(stamps);
        balance.compareAndSet(currentAmount, currentAmount + amount, stamps[0], ++stamps[0]);
    }

    public void withdraw(Integer amount) {
        int[] stamps = new int[1];
        Integer currentAmount = balance.get(stamps);
        sleep(); // aba will happen here
        balance.compareAndSet(currentAmount, currentAmount - amount, stamps[0], ++stamps[0]);
    }

    public Integer getBalance() {
        int[] stamps = new int[1];
        return balance.get(stamps);
    }

    private void sleep() {
        try {
            if("t1".equals(Thread.currentThread().getName()))
                Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
