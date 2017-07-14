package com.kjellVos.school.menu;

import com.kjellVos.school.artist.ArtistController;
import com.kjellVos.school.artist.ArtistModel;
import com.kjellVos.school.chart.ChartController;
import com.kjellVos.school.chart.ChartModel;
import com.kjellVos.school.single.SingleController;
import com.kjellVos.school.single.SingleModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by kjell on 3-7-2017.
 */
public class MenuController {
    ChartModel chartModel;
    ArtistModel artistModel;
    SingleModel singleModel;

    @FXML
    BorderPane borderPane;

    public void goToChart(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/kjellVos/school/chart/chart.fxml"));
            borderPane.setCenter(loader.load());
            ChartController chartController = loader.getController();
            chartController.setModel(chartModel);
        } catch (IOException e) {
            //TODO Error handling
            e.printStackTrace();
        }
    }

    public void goToSingle(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/kjellVos/school/single/single.fxml"));
            borderPane.setCenter(loader.load());
            SingleController singleController = loader.getController();
            singleController.setModels(singleModel);
        } catch (IOException e) {
            //TODO Error handling
            e.printStackTrace();
        }
    }

    public void goToArtist(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/kjellVos/school/artist/artist.fxml"));
            borderPane.setCenter(loader.load());
            ArtistController artistController = loader.getController();
            artistController.setModel(artistModel);
        } catch (IOException e) {
            //TODO Error handling
            e.printStackTrace();
        }
    }

    public void setModels(ArtistModel artistModel, SingleModel singleModel, ChartModel chartModel) {
        this.artistModel = artistModel;
        this.singleModel = singleModel;
        this.chartModel = chartModel;
    }
}
