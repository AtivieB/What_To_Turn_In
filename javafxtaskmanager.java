
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TaskManagerApp extends Application {
    private ArrayList<String> tasks = new ArrayList<>(); // Stores tasks
    private ListView<String> taskListView = new ListView<>(); // Displays tasks

    @Override
    public void start(Stage primaryStage) {
        // UI Elements
        TextField taskInput = new TextField();
        taskInput.setPromptText("Enter a task");

        Button addButton = new Button("Add Task");
        Button removeButton = new Button("Remove Selected");

        // Event Handlers
        addButton.setOnAction(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                updateTaskList();
                taskInput.clear();
            }
        });

        removeButton.setOnAction(e -> {
            String selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                tasks.remove(selectedTask);
                updateTaskList();
            }
        });

        // Layout
        VBox layout = new VBox(10, taskInput, addButton, removeButton, taskListView);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Scene Setup
        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTaskList() {
        taskListView.getItems().setAll(tasks);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
