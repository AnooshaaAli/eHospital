package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class UIController implements Initializable{
    @FXML
    private ComboBox<String> startTimeComboBox;
    @FXML
    private ComboBox<String> endTimeComboBox;
    @FXML
    private ComboBox<String> ActionComboBox;
    
    public void initialize(URL location, ResourceBundle resources) {
        // Populate the ComboBox with time options
    	
    	if(startTimeComboBox != null) {
            ObservableList<String> timeOptions = generateTimeOptions();
            startTimeComboBox.setItems(timeOptions);
            endTimeComboBox.setItems(timeOptions);
    	}
    	
    	if(ActionComboBox != null) {
            ObservableList<String> crud_ops = FXCollections.observableArrayList("Add", "Retrieve", "Update", "Delete");
            ActionComboBox.setItems(crud_ops);
    	}

    }

    // Method to generate time options from 8:00 AM to 10:00 PM in 30-minute intervals
    private ObservableList<String> generateTimeOptions() {
        ObservableList<String> timeOptions = FXCollections.observableArrayList();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        // Start from 8:00 AM
        LocalTime startTime = LocalTime.of(8, 0);
        // End at 10:00 PM
        LocalTime endTime = LocalTime.of(22, 0);

        while (!startTime.isAfter(endTime)) {
            timeOptions.add(startTime.format(timeFormatter));
            startTime = startTime.plusMinutes(60); // Increment by 30 minutes
        }

        return timeOptions;
    }
}
