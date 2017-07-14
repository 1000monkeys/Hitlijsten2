package com.kjellVos.school;

import com.kjellVos.school.menu.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage){
        FXMLLoader loader;
        Parent root = null;
        loader = new FXMLLoader(getClass().getResource("loadingScreen.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            //TODO Error handling
            e.printStackTrace();
        }

        LoadingScreenController loadingScreenController = loader.getController();

        primaryStage.setTitle("Hitlijsten Kjell Vos Poging 2");
        primaryStage.setScene(new Scene(root, 228, 62));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        stage = primaryStage;
        if (loadingScreenController.loadData()){
            root = null;
            loader = new FXMLLoader(getClass().getResource("/com/kjellVos/school/menu/menu.fxml"));

            try {
                root = loader.load();
            } catch (IOException e) {
                //TODO Error handling
                e.printStackTrace();
                System.exit(0);
            }

            MenuController menuController = loader.getController();
            menuController.setModels(loadingScreenController.getArtistModel(), loadingScreenController.getSingleModel(), loadingScreenController.getChartModel());
            menuController.goToChart();

            if (root != null) {
                stage.setScene(new Scene(root, 800, 600));
            }
            stage.centerOnScreen();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
