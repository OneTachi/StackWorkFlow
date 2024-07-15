package com.example.stackworkflow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainController
{
    Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @FXML
    private Label date;

    @FXML
    private StackPane task;

    @FXML
    public void initialize() {
        String currentDate = dateFormat.format(currentTime);
        date.setText(currentDate);
        Rectangle exampleRect = new Rectangle(100, 50, Paint.valueOf("Blue"));
        Text exampleText = new Text("Example Task");
        task.getChildren().add(exampleRect);
        task.getChildren().add(exampleText);
    }


}
