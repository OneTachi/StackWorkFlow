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
        readData(currentDate);
        date.setText(currentDate);
        Text startingText = (Text) task.getChildren().getLast();
        if (!listOfTasks.isEmpty()) {
            startingText.setText(listOfTasks.pop());
        }
    }

    public void nextTask() {
        //TODO create animation
        String newTask;
        if (!listOfTasks.isEmpty()) {
            newTask = listOfTasks.pop();
        }
        else {
            newTask = "Add a task!";
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
        // Add current task to be saved. "Add a Task" does not add an exception. It will be there regardless.
        Text taskNode = (Text) task.getChildren().getLast();
        listOfTasks.addFirst(taskNode.getText());
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
            // Grab current date to compare on launch
            currentTime = new Date();
            outputStream.writeObject(dateFormat.format(currentTime));

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
    public void readData(String currentDate) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("save.dat"))) {
            // Check if task list is old or not
            String pastDate = (String) inputStream.readObject();
            if (pastDate.equals(currentDate)) {
                // The only object within the file must be an ArrayDeque, so we can safely ignore the warning.
                listOfTasks = (ArrayDeque<String>) inputStream.readObject();
            } else {
                listOfTasks = new ArrayDeque<>();
            }
        } catch (IOException e) {
            // Create new ArrayDeque in case there isn't already one saved.
            listOfTasks = new ArrayDeque<>();
        } catch (ClassNotFoundException e) {
            System.out.println("Object on read is not ArrayDeque!");
        } catch (ClassCastException e) {
            System.out.println("Cast Exception");
            listOfTasks = new ArrayDeque<>();
        }
    }

    /**
     * Flips whether the text box is visible or hidden.
     */
    public void flipVisibleTextField() {
        boolean visible = input.isVisible();
        input.setVisible(!visible);
    }
}
