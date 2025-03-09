import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // Replace 'testdb' with your DB name
        String user = "root"; // Your database username
        String password = ""; // Your database password

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Create table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(50), "
                    + "email VARCHAR(100))";
            stmt.execute(createTableSQL);
            System.out.println("Table created successfully!");

            // Insert sample data
            String insertSQL = "INSERT INTO Users (name, email) VALUES ('John Doe', 'johndoe@example.com')";
            stmt.executeUpdate(insertSQL);
            System.out.println("Data inserted successfully!");

            // Retrieve and display data
            String selectSQL = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   ", Name: " + rs.getString("name") + 
                                   ", Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

