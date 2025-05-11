package dev.manyroads.projects.simplebankingsystem.stage4.example3;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class BankDB {
    private static String url;

    private static Connection connect() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void createOrConnectToDBFile() {
        try (Connection ignored = connect()) {
            System.out.println("Database Connection Success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createCardTableIfNotExist() {

        String sql = "CREATE TABLE IF NOT EXISTS card("
                + "id INTEGER PRIMARY KEY, "
                + "number TEXT type UNIQUE, "
                + "PIN TEXT, "
                + "balance INTEGER DEFAULT 0"
                + ");";

        try (Connection con = connect();
             Statement statement = con.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int addNewCard(Card card) {
        String sql = "INSERT INTO card (number, PIN) "
                + "VALUES (" + card.getNumber() + ", " +
                "'" + card.getPIN() + "');";


        try (Connection con = connect();
             Statement statement = con.createStatement()) {

            return statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Card getCardByNumber(String number) {
        String sql = "SELECT * FROM card WHERE number = " + number;

        try (Connection con = connect();
             Statement statement = con.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next()) {
                return null;
            }
            String numberFromDB = rs.getString("number");
            String actualPIN = rs.getString("PIN");

            int balance = rs.getInt("balance");

            return new Card(numberFromDB, actualPIN, balance);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void setUrl(String url) {
        BankDB.url = url;
    }


    public static void updateBalance(Card card, int amount) {
        String sql = "UPDATE card SET balance = ? WHERE number = ?";

        try (Connection con = connect();
             PreparedStatement updateBalance = con.prepareStatement(sql)) {
            updateBalance.setInt(1, amount);
            updateBalance.setString(2, card.getNumber());

            updateBalance.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return boolean true or false, depending on if the transaction is successful.
     *
     * @return true or false
     */
    public static boolean transferMoney(Card sourceCard, Card cardToTransfer, int amount) {
        String sql = "UPDATE card SET BALANCE = ? WHERE number = ?";

        try (Connection con = connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            con.setAutoCommit(false);

            // subtract balance from sourceCard
            preparedStatement.setInt(1, sourceCard.getBalance() - amount);
            preparedStatement.setString(2, sourceCard.getNumber());
            preparedStatement.executeUpdate();

            // add balance to cardToTransfer
            preparedStatement.setInt(1, cardToTransfer.getBalance() + amount);
            preparedStatement.setString(2, cardToTransfer.getNumber());
            preparedStatement.executeUpdate();

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCard(Card loggedInCard) {
        String sql = "DELETE FROM card WHERE number = ?";

        try (Connection con = connect(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, loggedInCard.getNumber());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
