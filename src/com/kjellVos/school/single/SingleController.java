package com.kjellVos.school.single;

import com.kjellVos.school.artist.Artist;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * Created by kjell on 3-7-2017.
 */
public class SingleController {
    private SingleModel model;

    @FXML
    TableView tableView;

    @FXML
    TextField textField;

    @FXML
    TableColumn naamTableColumn, titelTableColumn;

    @SuppressWarnings("Duplicates")
    public void initialize(){
        naamTableColumn.setCellValueFactory(new PropertyValueFactory("naam"));
        titelTableColumn.setCellValueFactory(new PropertyValueFactory("titel"));

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || newValue == null) {
                setData(model.getData());
            }else{
                FilteredList<Artist> filteredList = model.getData().filtered(p ->  ((Single)p).getTitel().toLowerCase().contains(newValue.toLowerCase()));
                setData(filteredList);
            }
        });

        tableView.setRowFactory(tv -> {
            TableRow<Single> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Single rowData = row.getItem();
                    showPopUp(rowData.getId());
                }
            });
            return row;
        });
    }

    public void setData(ObservableList list){
        tableView.setItems(list);
    }

    public void setModels(SingleModel model) {
        this.model = model;
        setData(model.getData());
    }

    public void showPopUp(int id){
        Dialog dialog = new Dialog();

        SingleInfo info = model.getDataBySingle(id);
        dialog.setTitle("Info by " + info.getTitel() + " from " + info.getArtiest());
        dialog.setHeaderText("Info by " + info.getTitel() + " from " + info.getArtiest());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10D);
        gridPane.setVgap(10D);

        gridPane.add(new Label("Titel:"), 0, 0);
        gridPane.add(new Label(info.getTitel()), 1, 0);
        gridPane.add(new Label("Artiest"), 0, 1);
        gridPane.add(new Label(info.getArtiest()), 1, 1);
        gridPane.add(new Label("Verschijnings jaar:"), 0, 2);
        gridPane.add(new Label(String.valueOf(info.getJaar())), 1, 2);
        gridPane.add(new Label("Verschijnings week:"), 0, 3);
        gridPane.add(new Label(String.valueOf(info.getWeek())), 1, 3);
        gridPane.add(new Label("Aantal weken in de top 40/tip parade:"), 0, 4);
        gridPane.add(new Label(String.valueOf(info.getAantalWeken())), 1, 4);
        gridPane.add(new Label("Hoogste positite"), 0, 5);
        gridPane.add(new Label(String.valueOf(info.getHoogstePositie())), 1, 5);

        dialog.getDialogPane().setContent(gridPane);

        dialog.getDialogPane().getScene().getWindow().sizeToScene();

        dialog.showAndWait();
    }
}
