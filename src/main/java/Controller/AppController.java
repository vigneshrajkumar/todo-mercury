package Controller;

import Model.Task;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppController {

    @FXML private Button vRefresh;
    @FXML private MenuItem vSettingsMenuButton;
    @FXML private MenuItem vAboutMenuButton;
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
        });

        // press enter action
        vTask.setOnAction(event -> {
            if (vTask.getText().length() <= 0){
                return;
            }
            Datastore.addTask(new Task(-1, vTask.getText()));
            vTask.setText("");
        });
        
        vRefresh.setOnAction(event -> {
            // TODO::vr Operation to expensive, need to optimize
            vTaskList.getItems().removeAll(vTaskList.getItems());
            vTaskList.getItems().addAll(Datastore.getTasks());
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

        vSettingsMenuButton.setOnAction((event) -> {

            try{
                Stage newStage = new Stage();

                FXMLLoader loader = new FXMLLoader(Application.class.getResource("/fxml/Settings.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                Scene scene = new Scene(page);
                newStage.setScene(scene);
                newStage.setTitle("Todo: Settings");
                newStage.show();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        });

        vAboutMenuButton.setOnAction((event) -> {
            try{

                Stage newStage = new Stage();

                FXMLLoader loader = new FXMLLoader(Application.class.getResource("/fxml/About.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                Scene scene = new Scene(page);
                newStage.setScene(scene);
                newStage.setTitle("Todo: About");
                newStage.show();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }
}
