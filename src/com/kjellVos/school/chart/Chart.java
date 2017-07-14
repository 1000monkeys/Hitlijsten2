package com.kjellVos.school.chart;

import com.kjellVos.school.single.Single;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by kjell on 3-7-2017.
 */
public class Chart {
    private SimpleIntegerProperty hitlijstId, jaar, week, hitlijstEditie;
    private SimpleStringProperty editieNaam;
    private ObservableList<Single> singles;

    public Chart(int hitlijstId, int jaar, int week, int hitlijstEditie, String editieNaam){
        this.hitlijstId = new SimpleIntegerProperty(hitlijstId);
        this.jaar = new SimpleIntegerProperty(jaar);
        this.week = new SimpleIntegerProperty(week);
        this.hitlijstEditie = new SimpleIntegerProperty(hitlijstEditie);
        this.editieNaam = new SimpleStringProperty(editieNaam);
        singles = FXCollections.observableArrayList();
    }

    public int getWeek() {
        return week.get();
    }

    public int getJaar() {
        return jaar.get();
    }

    public int getHitlijstId() {
        return hitlijstId.get();
    }

    public int getHitlijstEditie() {
        return hitlijstEditie.get();
    }

    public String getEditieNaam() {
        return editieNaam.get();
    }

    public ObservableList<Single> getSingles() {
        return singles;
    }

    public void setSingles(ObservableList<Single> singles) {
        this.singles = singles;
    }
}
