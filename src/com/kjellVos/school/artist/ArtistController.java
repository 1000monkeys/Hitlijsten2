package com.kjellVos.school.artist;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by kjell on 3-7-2017.
 */
public class ArtistController {
    ArtistModel model;

    @FXML
    TableView tableView;

    @FXML
    TableColumn naamTableColumn;

    @FXML
    TextField textField;

    @SuppressWarnings("Duplicates")
    public void initialize(){
        naamTableColumn.setCellValueFactory(new PropertyValueFactory("naam"));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || newValue == null) {
                setData(model.getData());
            }else{
                FilteredList<Artist> filteredList = model.getData().filtered(p -> ((Artist)p).getNaam().toLowerCase().contains(newValue.toLowerCase()));
                setData(filteredList);
            }
        });

        tableView.setRowFactory(tv -> {
            TableRow<Artist> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Artist rowData = row.getItem();
                    showPopUp(rowData.getId());
                }
            });
            return row;
        });
    }

    private void showPopUp(int id) {
        Dialog dialog = new Dialog();

        ArtistInfo info = model.getDataByArtist(id);
        dialog.setTitle("Info by " + info.getNaam());
        dialog.setHeaderText("Info by " + info.getNaam());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        TableView tableView = new TableView();

        TableColumn titelTableColumn = new TableColumn("Titel");
        titelTableColumn.setCellValueFactory(new PropertyValueFactory("titel"));

        tableView.getColumns().add(titelTableColumn);

        tableView.setItems(info.getSingles());
        tableView.setPrefWidth(400D);

        dialog.getDialogPane().setContent(tableView);

        dialog.getDialogPane().getScene().getWindow().sizeToScene();

        dialog.showAndWait();
    }

    public void setModel(ArtistModel model) {
        this.model = model;
        setData(model.getData());
    }

    public void showInfo(){

    }

    public void setData(ObservableList list){
        tableView.setItems(list);
    }
}
