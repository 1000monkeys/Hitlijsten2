package com.kjellVos.school.artist;

import javafx.collections.ObservableList;

/**
 * Created by kjell on 4-7-2017.
 */
public class ArtistInfo {
    private String naam;
    private ObservableList singles;

    public ArtistInfo(String naam, ObservableList singles){
        this.naam = naam;
        this.singles = singles;
    }

    public String getNaam() {
        return naam;
    }

    public ObservableList getSingles() {
        return singles;
    }
}
