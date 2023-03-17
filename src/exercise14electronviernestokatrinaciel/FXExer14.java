package exercise14electronviernestokatrinaciel;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXExer14 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Subject math = new Subject("Math", "math.png", 4, 1.75);
        Subject bio = new Subject("Biology", "biology.png", 3, 2.0);
        Subject chem = new Subject("Chemistry", "chemistry.png", 3, 1.5);
        Subject physics = new Subject("Physics", "physics.png", 3, 1.75);
        Subject cs = new Subject("Computer Science", "computer science.png", 1, 1.5);
        Subject socsci = new Subject("Social Science", "social science.png", 1, 1.5);
        Subject eng = new Subject("English", "english.png", 1, 1.75);
        Subject res = new Subject("Research", "research.png", 5, 1.5);
        
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}