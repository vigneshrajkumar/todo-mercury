package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Todo");

        try{
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/fxml/App.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



//    public void vAdd(ActionEvent actionEvent) {
//
//        String taskDescription = vTask.getText();
//        vTask.setText("");
//        vTaskList.getItems().add(new Task(taskDescription));
//
//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem deleteItem = new MenuItem();
//        deleteItem.setOnAction(di -> {
//            vTaskList.getItems().remove(vTaskList.getFocusModel().getFocusedIndex());
//            System.out.println("deleting" + vTaskList.getFocusModel().getFocusedIndex());
//        });
//
//        deleteItem.textProperty().setValue("Delete Task");
//        contextMenu.getItems().addAll(deleteItem);
//        vTaskList.setContextMenu(contextMenu);
//
//    }
}
