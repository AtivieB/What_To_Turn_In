import java.sql.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SQLClient extends Application {
    private TextField tfURL = new TextField("jdbc:mysql://localhost:3306/testdb");
    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();
    private TextArea taResults = new TextArea();
    private TextField tfSQLQuery = new TextField();
    private Button btnExecute = new Button("Execute");
    private ComboBox<String> cbDrivers = new ComboBox<>(
            FXCollections.observableArrayList("com.mysql.cj.jdbc.Driver", "org.postgresql.Driver"));

    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        // Top: Database connection fields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("JDBC URL:"), 0, 0);
        gridPane.add(tfURL, 1, 0);
        gridPane.add(new Label("Username:"), 0, 1);
        gridPane.add(tfUsername, 1, 1);
        gridPane.add(new Label("Password:"), 0, 2);
        gridPane.add(pfPassword, 1, 2);
        gridPane.add(new Label("Driver:"), 0, 3);
        gridPane.add(cbDrivers, 1, 3);
        Button btnConnect = new Button("Connect");
        gridPane.add(btnConnect, 1, 4);

        // Center: SQL Input & Execution
        VBox vBoxSQL = new VBox(10, new Label("Enter SQL Command:"), tfSQLQuery, btnExecute);
        vBoxSQL.setAlignment(Pos.CENTER);
        VBox vBoxResults = new VBox(10, new Label("Query Results:"), new ScrollPane(taResults));

        BorderPane root = new BorderPane();
        root.setTop(gridPane);
        root.setCenter(vBoxSQL);
        root.setBottom(vBoxResults);

        // Scene and Stage Setup
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("JDBC SQL Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event Handlers
        btnConnect.setOnAction(e -> connectToDatabase());
        btnExecute.setOnAction(e -> executeSQLQuery());
    }

    private void connectToDatabase() {
        try {
            String driver = cbDrivers.getValue();
            if (driver != null) {
                Class.forName(driver);
            }
            connection = DriverManager.getConnection(tfURL.getText(), tfUsername.getText(), pfPassword.getText());
            taResults.setText("Connected to Database Successfully!");
        } catch (Exception e) {
            taResults.setText("Connection Failed: " + e.getMessage());
        }
    }

    private void executeSQLQuery() {
        if (connection == null) {
            taResults.setText("Not connected to a database!");
            return;
        }

        try (Statement stmt = connection.createStatement()) {
            String sql = tfSQLQuery.getText().trim();
            if (sql.toLowerCase().startsWith("select")) {
                ResultSet rs = stmt.executeQuery(sql);
                StringBuilder result = new StringBuilder();
                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        result.append(rs.getString(i)).append("\t");
                    }
                    result.append("\n");
                }
                taResults.setText(result.toString());
            } else {
                int rowsAffected = stmt.executeUpdate(sql);
                taResults.setText(rowsAffected + " row(s) affected.");
            }
        } catch (SQLException e) {
            taResults.setText("SQL Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
