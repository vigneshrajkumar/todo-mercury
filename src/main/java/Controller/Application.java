package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        appInit();
        launch(args);
    }

    private static void appInit() {

        File f = new File("tasks.db");
        if(f.exists() && !f.isDirectory()) {

            System.out.println("DB found");

        }else{
            System.out.println("DB not found");

            try {

                if(!f.createNewFile()){
                    System.out.println("DB file creation error");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            Datastore.initializeStorage();

        }
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
}
