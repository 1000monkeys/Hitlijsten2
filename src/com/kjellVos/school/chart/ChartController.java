package com.kjellVos.school.chart;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by kjell on 3-7-2017.
 */
public class ChartController {
    ChartModel model;

    @FXML
    Accordion accordion;

    @FXML
    TableView lijstTableView, editieTableView;

    @FXML
    TableColumn positieTableColumn, naamTableColumn, titelTableColumn, editieTableColumn, jaarTableColumn, weekTableColumn;

    @FXML
    TextField jaarTextField, weekTextField;

    public void initialize() {
        naamTableColumn.setCellValueFactory(new PropertyValueFactory("naam"));
        titelTableColumn.setCellValueFactory(new PropertyValueFactory("titel"));
        positieTableColumn.setCellValueFactory(new PropertyValueFactory("positie"));

        editieTableColumn.setCellValueFactory(new PropertyValueFactory("editieNaam"));
        jaarTableColumn.setCellValueFactory(new PropertyValueFactory("jaar"));
        weekTableColumn.setCellValueFactory(new PropertyValueFactory("week"));

        jaarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || newValue == null) {
                setData(model.getData());
            }else{
                searchLijst();
            }
        });

        weekTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || newValue == null) {
                setData(model.getData());
            }else{
                searchLijst();
            }
        });

        lijstTableView.setRowFactory(tv -> {
            TableRow<Chart> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Chart rowData = row.getItem();
                    editieTableView.setItems(model.getChart(rowData.getHitlijstId(), rowData.getJaar(), rowData.getWeek()));
                    TitledPane pane = accordion.getPanes().get(1);
                    accordion.setExpandedPane(pane);
                }
            });
            return row;
        });

        TitledPane pane = accordion.getPanes().get(0);
        accordion.setExpandedPane(pane);
    }

    private void searchLijst() {
        String jaar = jaarTextField.getText();
        String week = weekTextField.getText();
        if (jaar.length() > 0 && week.length() > 0) {
            FilteredList<Chart> filteredList = model.getData().filtered(p -> String.valueOf(p.getWeek()).contains(week) && String.valueOf(p.getJaar()).contains(jaar));
            setData(filteredList);
        }else if (jaar.length() > 0){
            FilteredList<Chart> filteredList = model.getData().filtered(p -> String.valueOf(p.getJaar()).contains(jaar));
            setData(filteredList);
        }else if (week.length() > 0) {
            FilteredList<Chart> filteredList = model.getData().filtered(p -> String.valueOf(p.getWeek()).contains(week));
            setData(filteredList);
        }else{
            setData(model.getData());
        }
    }

    public void setModel(ChartModel model) {
        this.model = model;
        lijstTableView.setItems(model.getData());
    }

    public void setData(ObservableList list){
        lijstTableView.setItems(list);
    }
}