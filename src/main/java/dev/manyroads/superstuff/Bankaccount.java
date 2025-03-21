package dev.manyroads.superstuff;

/**
 * You are given a class named BankAccount. The class has two fields: number and balance.
 * Define two classes which inherit from the BankAccount:
 * <p>
 * CheckingAccount containing the double field fee.
 * SavingsAccount containing the double field interestRate.
 * Each new class should have a constructor with three parameters to initialize all fields:
 * <p>
 * CheckingAccount(String number, Long balance, double fee)
 * SavingsAccount(String number, Long balance, double interestRate)
 * Do not forget to invoke the superclass constructor when creating objects.
 * <p>
 * Do not make the fields private.
 */
class BankAccount {

    protected String number;
    protected Long balance;

    public BankAccount(String number, Long balance) {
        this.number = number;
        this.balance = balance;
    }
}

class CheckingAccount extends BankAccount {
    double fee;

    public CheckingAccount(String number, Long balance, double fee) {
        super(number, balance);
        this.fee = fee;
    }
}

class SavingsAccount extends BankAccount {
    double interestRate;

    public SavingsAccount(String number, Long balance,double interestRate) {
        super(number, balance);
        this.interestRate = interestRate;
    }
}