package exercise14electronviernestokatrinaciel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SubjectSearchEngineController implements Initializable {
    @FXML Text name, attributes, end, error;
    @FXML ImageView image;
    @FXML TextField searchInput;
    @FXML Button previous, next, back, search;
    
    Subject displayedSubject;
    Image img;
    int initialIndex = 0;
    
    public void setSubject(Subject s) {
        displayedSubject = s;
        resetDisplay(displayedSubject);
        checkLimit(displayedSubject);
    }
    
    @FXML private void previousSubject() {
        int index = Subject.getSubjectIndex(displayedSubject);
        if(index > 0) {
          index--;
          displayedSubject = Subject.subjectList.get(index);
          resetDisplay(displayedSubject);
        }
        else {
          end.setText("This is the first subject of the list.");
        }
        checkLimit(displayedSubject);
    }
    
    @FXML private void nextSubject() {
        int index = Subject.getSubjectIndex(displayedSubject);
        if(index < Subject.getListLength() - 1) {
          index++;
          displayedSubject = Subject.subjectList.get(index);
          resetDisplay(displayedSubject);
        }
        else {
          end.setText("This is the last subject of the list.");
        }
        checkLimit(displayedSubject);
    }
    
    @FXML private void findSubject() {
        String input = searchInput.getText();
        try{
            displayedSubject = Subject.searchSubject(input);
            resetDisplay(displayedSubject);
            checkLimit(displayedSubject);
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Found");
            alert.setHeaderText("Error");
            alert.setContentText("Subject not found...");
            alert.showAndWait();
        }
    }
    
    @FXML public void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        
        Scene subjectScene = new Scene(root);
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
        thisStage.setScene(subjectScene);
        thisStage.show();
    }
    
    @FXML void checkLimit(Subject s) {
        int index = Subject.subjectList.indexOf(s);
        if (index == 0) {
            previous.setDisable(true);
        }
        else {
            previous.setDisable(false);
        }
            
        if (index == Subject.subjectList.size() - 1) {
            next.setDisable(true);
        }
        else {
            next.setDisable(false);
        }
    }
    
    @FXML void resetDisplay(Subject s) {
        end.setText("");
        error.setText("");
        name.setText(s.getName());
        attributes.setText("Units: " + Double.toString(s.getUnits()) + " | Grade: " + Double.toString(s.getGrade()));
        img = new Image(getClass().getResourceAsStream("imgs/" + s.getImgFileName()));
        image.setImage(img);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayedSubject = Subject.subjectList.get(initialIndex);
        resetDisplay(displayedSubject);
        checkLimit(displayedSubject);
    }    
    
}
