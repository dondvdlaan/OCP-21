package dev.manyroads.projects.simplebankingsystem.stage3.example2;


import javax.sql.DataSource;
import java.sql.*;

public class JdbcCardDAO implements CardDAO {

    private final DataSource dataSource;

    public JdbcCardDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init() {

        try (final Connection connection = dataSource.getConnection();
             final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(DELETE_TABLE);
            stmt.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println("Error while creating table " + e.getMessage());
        }

    }

    @Override
    public void add(Card card) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(INSERT)) {
            stmt.setString(1, card.getNumber());
            stmt.setString(2, card.getPin());
            stmt.setDouble(3, card.getBalance());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding element");
        }
    }

    @Override
    public Card findByNumber(String number) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement stmt = connection.prepareStatement(FIND_BY_NUMBER)) {

            stmt.setString(1, number);

            try (final ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    final String pin = res.getString("pin");
                    final double balance = res.getDouble("balance");
                    return new Card(number, pin, balance);
                } else {
                    return null;
                }

            }
        } catch (SQLException e) {
            System.out.println("Error reading from file");
            throw new DAOException("Error while finding element");
        }
    }
}
