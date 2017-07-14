package com.kjellVos.school;

import com.kjellVos.school.artist.ArtistModel;
import com.kjellVos.school.chart.ChartModel;
import com.kjellVos.school.single.SingleModel;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class LoadingScreenController {
    private final static String HOST = "127.0.0.1";
    private final static String USERNAME = "hitdossier";
    private final static String PASSWORD = "hitdossier";

    @FXML
    ProgressBar progressBar;

    private ChartModel chartModel;
    private SingleModel singleModel;
    private ArtistModel artistModel;

    public LoadingScreenController(){
        chartModel = new ChartModel(HOST, USERNAME, PASSWORD);
        singleModel = new SingleModel(HOST, USERNAME, PASSWORD);
        artistModel = new ArtistModel(HOST, USERNAME, PASSWORD);
    }

    public boolean loadData(){
        progressBar.setProgress(0.0D);
        artistModel.loadData();
        progressBar.setProgress(0.5);
        singleModel.loadData();
        progressBar.setProgress(1D);
        chartModel.loadData();
        return true;
    }

    public ArtistModel getArtistModel() {
        return artistModel;
    }

    public ChartModel getChartModel() {
        return chartModel;
    }

    public SingleModel getSingleModel() {
        return singleModel;
    }
}
