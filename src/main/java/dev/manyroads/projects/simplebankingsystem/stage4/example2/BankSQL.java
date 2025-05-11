package dev.manyroads.projects.simplebankingsystem.stage4.example2;

import java.sql.SQLException;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;

public class BankSQL {
    Scanner sc = new Scanner(System.in);
    SQLiteDataSource dataSource;

    public BankSQL(String[] args) {
        // oof so wild
        final String dbname = args[1];
        createDB(dbname);
    }

    private void createDB(String dbname) {
        final String url = "jdbc:sqlite:" + dbname;

        dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
                        "id INTEGER PRIMARY KEY, " +
                        "number TEXT, " +
                        "pin TEXT, " +
                        "balance INTEGER DEFAULT 0" + ")");
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

    void createCard() {
        final Card card = new Card();

        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                st.executeUpdate(String.format("INSERT INTO card (number, pin) VALUES ('%s', '%s')",
                        card.getNumber(),
                        card.getPin()));
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Your card has been created");
        System.out.println("Your card number:\n" + card.getNumber() + "\n");
        System.out.println("Your card PIN:\n" + card.getPin() + "\n");
    }

    public void menu() {
        while (true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            final String option = sc.nextLine();
            System.out.println();

            if ("1".equals(option)) {
                createCard();
            } else if ("2".equals(option)) {
                if (login()) {
                    System.out.println("Bye!");
                    return;
                }
            } else {
                System.out.println("Bye!");
                return;
            }
        }

    }

    private boolean login() {
        System.out.println("Enter your card number:");
        final String cardNumber = sc.nextLine();
        System.out.println("Enter your PIN:");
        final String cardPIN = sc.nextLine();

        final int res = loginCheck(cardNumber, cardPIN);
        if (res == -1) {
            System.out.println("\nWrong card number or PIN!\n");
            return false;
        }

        final AccountUI acc = new AccountUI(dataSource, res);
        return acc.init();
    }

    private int loginCheck(String cardNumber, String cardPIN) {
        try (var c = dataSource.getConnection()) {
            try (var st = c.createStatement()) {
                try (var card = st.executeQuery(
                        String.format("SELECT * FROM card WHERE number = '%s' and pin = '%s'", cardNumber, cardPIN))) {
                    if (card.next()) {
                        return card.getInt("id");
                    }
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}