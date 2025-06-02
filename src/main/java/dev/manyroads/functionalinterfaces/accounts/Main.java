package dev.manyroads.functionalinterfaces.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Given a class Account(number: String, balance: Long, locked: boolean), the list accounts of type List<Account>
 * and the method filter(List<T> elems, Predicate<T> predicate) for filtering the given list of type T by
 * the predicate.
 * <p>
 * The class Account has the following get methods: getNumber(), getBalance(), isLocked() for retrieving the values
 * of the corresponding fields.
 * <p>
 * Write code for filtering the accounts list using the filter method in two ways:
 * <p>
 * get all non-empty accounts (balance > 0) and save it to the variable nonEmptyAccounts
 * get all non-locked accounts with too much money (balance >= 100 000 000) and save it to the
 * variable accountsWithTooMuchMoney
 */
class Main {
    static Predicate<Account> isAccountNotEmpty = b -> b.balance > 0;
    static Predicate<Account> isAccountTooBig = b -> !b.locked && b.balance >= 100_000_000;

    public static void printFilteredAccounts(List<Account> accounts) {
        List<Account> nonEmptyAccounts = filter(accounts, isAccountNotEmpty);
        List<Account> accountsWithTooMuchMoney = filter(accounts, isAccountTooBig);

        // Don't change the code below
        nonEmptyAccounts.forEach(a -> System.out.print(a.getNumber() + " "));
        accountsWithTooMuchMoney.forEach(a -> System.out.print(a.getNumber() + " "));
    }

    public static <T> List<T> filter(List<T> elems, Predicate<T> predicate) {
        return elems.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int n = Integer.parseInt(scanner.nextLine());
        final List<Account> accounts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final String[] values = scanner.nextLine().split("\\s+");
            final Account acc = new Account(
                    values[0], Long.parseLong(values[1]), Boolean.parseBoolean(values[2])
            );
            accounts.add(acc);
        }

        printFilteredAccounts(accounts);
    }

    static class Account {
        private String number;
        private long balance;
        private boolean locked;

        public Account(String number, long balance, boolean locked) {
            this.number = number;
            this.balance = balance;
            this.locked = locked;
        }

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "number='" + number + '\'' +
                    ", balance=" + balance +
                    ", isLocked=" + locked +
                    '}';
        }
    }
}
