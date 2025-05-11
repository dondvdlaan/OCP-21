package dev.manyroads.projects.simplebankingsystem.stage4.example2;

import java.sql.SQLException;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;

public class AccountUI {
    private final SQLiteDataSource dataSource;
    private final int id;
    Scanner sc = new Scanner(System.in);

    public AccountUI(SQLiteDataSource dataSource, int id) {
        this.dataSource = dataSource;
        this.id = id;
    }

    Boolean init() {
        System.out.println("\nYou have successfully logged in!\n");

        while (true) {
            System.out.println("1. Balance");
            System.out.println("2. Add income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            final String option = sc.nextLine();

            if ("1".equals(option)) {
                System.out.println("\nBalance: " + getBalance() + "\n");
            } else if ("2".equals(option)) {
                setIncome();
            } else if ("3".equals(option)) {
                setTransfer();
            } else if ("4".equals(option)) {
                closeAccount();
                return false;
            } else if ("5".equals(option)) {
                System.out.println("\nYou have successfully logged out!\n");
                return false;
            } else if ("0".equals(option)) {
                return true;
            }
        }
    }

    private void closeAccount() {

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                st.executeUpdate("DELETE FROM card WHERE id = " + id);

                System.out.println("The account has been closed!\n");
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTransfer() {

        System.out.println("\nTransfer");
        System.out.println("Enter card number:");
        final String toNumber = sc.nextLine();

        if (!cardCheck(toNumber)) {
            return;
        }

        System.out.println("\nEnter how much money you want to transfer:");
        final int amount = Integer.parseInt(sc.nextLine());

        if (getBalance() < amount) {
            System.out.println("Not enough money!\n");
            return;
        }

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                st.executeUpdate("UPDATE card SET balance = balance + " + amount + " WHERE number = " + toNumber);
                st.executeUpdate("UPDATE card SET balance = balance - " + amount + " WHERE id = " + id);
                System.out.println("Success!\n");
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean cardCheck(String toNumber) {

        if (toNumber.length() != 16 || !Card.generateChecksum(toNumber.substring(0, 15)).equals(toNumber)) {

            System.out.println("Probably you made mistake in the card number. Please try again!\n");
            return false;
        }

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                final var ans = st.executeQuery("select * from card WHERE number = " + toNumber);

                if (!ans.next()) {
                    System.out.println("Such a card does not exist.\n");
                    return false;
                }

                if (id == ans.getInt("id")) {
                    System.out.println("You can't transfer money to the same account!\n");
                    return false;
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void setIncome() {

        System.out.println("\nEnter income:");
        final int income = Integer.parseInt(sc.nextLine());

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                st.executeUpdate("UPDATE card SET balance = balance + " + income + " WHERE id = " + id);

                System.out.println("Income was added!\n");
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

    private int getBalance() {

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                try (var card = st.executeQuery("SELECT * FROM card WHERE id = '" + id + "'")) {
                    if (card.next()) {
                        return card.getInt("balance");
                    }
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
