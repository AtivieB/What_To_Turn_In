
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorSelector extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create text
        Text text = new Text("Adjust the sliders to change my color!");
        text.setFont(new Font(20));

        // Create sliders for RGB values
        Slider redSlider = createSlider();
        Slider greenSlider = createSlider();
        Slider blueSlider = createSlider();

        // Labels
        Label redLabel = new Label("Red");
        Label greenLabel = new Label("Green");
        Label blueLabel = new Label("Blue");

        // Update text color when sliders change
        redSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateColor(text, redSlider, greenSlider, blueSlider));
        greenSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateColor(text, redSlider, greenSlider, blueSlider));
        blueSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateColor(text, redSlider, greenSlider, blueSlider));

        // Layout
        VBox root = new VBox(10, text, redLabel, redSlider, greenLabel, greenSlider, blueLabel, blueSlider);
        root.setStyle("-fx-padding: 20px;");

        // Scene setup
        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Text Color Selector");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createSlider() {
        Slider slider = new Slider(0, 255, 0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        return slider;
    }

    private void updateColor(Text text, Slider red, Slider green, Slider blue) {
        Color color = Color.rgb((int) red.getValue(), (int) green.getValue(), (int) blue.getValue());
        text.setFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
