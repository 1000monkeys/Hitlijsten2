package com.kjellVos.school.artist;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by kjell on 3-7-2017.
 */
public class Artist {
    private SimpleIntegerProperty id;
    private SimpleStringProperty naam;

    public Artist(int id, String naam) {
        this.id = new SimpleIntegerProperty(id);
        this.naam = new SimpleStringProperty(naam);
    }

    public int getId() {
        return id.get();
    }

    public String getNaam() {
        return naam.get();
    }
}
