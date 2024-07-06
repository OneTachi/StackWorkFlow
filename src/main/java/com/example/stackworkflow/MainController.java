package com.example.stackworkflow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainController
{
    Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @FXML
    private Label date;

    @FXML
    public void initialize() {
        String currentDate = dateFormat.format(currentTime);
        date.setText(currentDate);
    }


}
