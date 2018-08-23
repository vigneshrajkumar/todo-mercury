package Controller;

import Model.Task;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppController {
        @FXML private TextField vTask;
    @FXML private ListView<Task> vTaskList;
    @FXML private Button vAdd;

    @FXML
    private void initialize(){
        System.out.println("initialize() called");

        // explicit add button click
        vAdd.setOnAction((event) -> {
            if (vTask.getText().length() <= 0){
                return;
            }
            Datastore.addTask(new Task(-1, vTask.getText()));
            vTask.setText("");
            refreshListView();
        });

        // press enter action
        vTask.setOnAction(event -> {
            if (vTask.getText().length() <= 0){
                return;
            }
            Datastore.addTask(new Task(-1, vTask.getText()));
            vTask.setText("");
            refreshListView();
        });

        vTaskList.setCellFactory(param -> {
            ListCell<Task> cell = new ListCell<>();

            // what is binding convert?
            cell.textProperty().bind(Bindings.convert(cell.itemProperty()));

            // setting context menu for cell
            ContextMenu menu = new ContextMenu();

            MenuItem deleteTask = new MenuItem();
            deleteTask.setOnAction(del -> {
                Datastore.deleteTask(vTaskList.getSelectionModel().getSelectedItem().getID());
                refreshListView();
            });

            deleteTask.setText("Delete Task");
            menu.getItems().add(deleteTask);


            // explore this
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(menu);
                }
            });

            return cell;
        });

        refreshListView();
    }

    private void refreshListView(){
        // TODO::vr Operation to expensive, need to optimize
        vTaskList.getItems().removeAll(vTaskList.getItems());
        vTaskList.getItems().addAll(Datastore.getTasks());
    }
}
