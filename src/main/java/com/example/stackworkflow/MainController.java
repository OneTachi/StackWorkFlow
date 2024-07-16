package com.example.stackworkflow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayDeque;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Deque;

public class MainController
{
    Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    Deque<String> listOfTasks = new ArrayDeque<String>();

    @FXML
    private Label date;

    @FXML
    private StackPane task;

    @FXML
    private TextField input;

    @FXML
    public void initialize() {
        String currentDate = dateFormat.format(currentTime);
        date.setText(currentDate);
        Rectangle exampleRect = new Rectangle(100, 50, Paint.valueOf("Blue"));
        Text exampleText = new Text("Example Task");
        task.getChildren().add(exampleRect);
        task.getChildren().add(exampleText);
    }

    public void nextTask() {
        //TODO create animation
        String newTask;
        if (!listOfTasks.isEmpty()) {
            newTask = listOfTasks.pop();
        }
        else {
            newTask = "Add a new task!";
        }

        Text taskNode = (Text) task.getChildren().getLast();
        taskNode.setText(newTask);
    }

    /**
     * Tracks a new task the user specifies in a Text Field.
     */
    public void addTask() {
        // Grabbing text from Text Field & Resetting the Field
        String newTask = input.getText();
        input.clear();
        listOfTasks.add(newTask);
        System.out.println("Added '" + newTask + "'!");
    }
}
