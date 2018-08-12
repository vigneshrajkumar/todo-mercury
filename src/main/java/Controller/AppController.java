package Controller;

import Model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppController {

    @FXML private TextField vTask;
    @FXML private ListView<Task> vTaskList;
    @FXML private Button vAdd;

    public AppController(){
        System.out.println("AppController() init called");
    }

    private void addTask(Task task){
        if (vTask.getText().length() <= 0){
            return;
        }
        vTaskList.getItems().add(task);
        vTask.setText("");
    }

    @FXML
    private void initialize(){
        System.out.println("initialize() called");

        // explicit add button click
        vAdd.setOnAction((event) -> {
            addTask(new Task(vTask.getText()));
        });

        // press enter action
        vTask.setOnAction(event -> {
            addTask(new Task(vTask.getText()));
        });

        ContextMenu menu = new ContextMenu();
        MenuItem deleteTask = new MenuItem();
        deleteTask.setOnAction(del -> {
            vTaskList.getItems().remove(vTaskList.getFocusModel().getFocusedIndex());
        });

        deleteTask.textProperty().setValue("Delete Task");
        menu.getItems().add(deleteTask);
        vTaskList.setContextMenu(menu);

    }
}
