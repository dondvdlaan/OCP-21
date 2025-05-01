package dev.manyroads.projects.simplebankingsystem.stage3.example1;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Card> listOfClients = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    SQLiteDataSource sds;

    public static void main(String[] args) {
        new Main().go(args);
    }
    void go(String[] args){
        String url = "jdbc:sqlite:".concat(args[1]);
        sds = new SQLiteDataSource();
        sds.setUrl(url);

        try(Connection cn = sds.getConnection()) {
            try (Statement st = cn.createStatement()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS card (id INTEGER PRIMARY KEY, number TEXT NOT NULL, pin TEXT NOT NULL, balance INTEGER DEFAULT 0)");

            } catch (Exception e) {e.printStackTrace();}
        } catch (Exception e) {e.printStackTrace();}

        welcomeText();
    }
    private void welcomeText() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        chooseAction(sc.next());
    }
    private void chooseAction(String input) {
        switch (input){
            case "1": createAccount(); break;
            case "2": logInAccount(); break;
            case "0": exit();
        }
    }
    private void createAccount() {
        Card cd = new Card();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(cd.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(cd.getPin());
        listOfClients.add(cd);

        toDB(cd.getCardNumber(), cd.getPin());
        welcomeText();
    }
    private void toDB(String cardNumber, String pin) {

        try(Connection cn = sds.getConnection()) {
            try (Statement st = cn.createStatement()) {

                st.executeUpdate("INSERT INTO card (number, pin) VALUES ("+ "'" +cardNumber+ "', '" +pin+ "')");

            } catch (Exception e) {e.printStackTrace();}
        } catch (Exception e) {e.printStackTrace();}
    }
    private void logInAccount() {
        System.out.println("Enter your card number:");
        String probeNumber  = sc.next();
        System.out.println("Enter your PIN:");
        String probePin  = sc.next();

        authentication(probeNumber,probePin);
    }
    private void authentication(String probeNumber, String probePin) {

        try(Connection cn = sds.getConnection()) {
            try (Statement st = cn.createStatement()) {

                try (ResultSet currentUser = st.executeQuery("SELECT balance FROM card WHERE number = "+ probeNumber+ " AND pin = " +probePin)) {

                    if (currentUser.next()) {
                        int balance = currentUser.getInt("balance");
                        System.out.println("You have successfully logged in!");
                        insideAccount(balance);
                    }
                    else {
                        System.out.println("Wrong card number or PIN!");
                        welcomeText();
                    }
                } catch (Exception e) {e.printStackTrace();}
            } catch (Exception e) {e.printStackTrace();}
        } catch (Exception e) {e.printStackTrace();}
    }
    private void insideAccount (int balance) {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");

        switch (sc.next()) {
            case "1": System.out.println("Balance: " +balance); insideAccount(balance); break;
            case "2": System.out.println("You have successfully logged out!"); welcomeText(); break;
            case "0": exit();
        }
    }
    private void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
