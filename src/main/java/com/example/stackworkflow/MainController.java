package com.example.stackworkflow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainController
{
    Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    ArrayDeque<String> listOfTasks = null;

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

    /**
     * Saves tasks to complete for the day. Use on application exit.
     */
    public void saveData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
            outputStream.writeObject(listOfTasks);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //Temp
        } catch (IOException e) {
            e.printStackTrace(); //Temp
        }
    }

    /**
     * Reads saved data and gets tasks back. Use on application start to initialize listOfTasks.
     */
    public void readData() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("save.dat"))) {
            // The only object within the file must be an ArrayDeque, so we can safely ignore the warning.
            listOfTasks = (ArrayDeque<String>) inputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace(); //Temp
        } catch (IOException e) {
            e.printStackTrace(); //Temp
        }
    }
}
