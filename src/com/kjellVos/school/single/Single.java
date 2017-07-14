package com.kjellVos.school.single;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by kjell on 3-7-2017.
 */
public class Single {
    private SimpleIntegerProperty id, artiestId, positie;
    private SimpleStringProperty titel, naam;

    public Single(int id, String titel, int artiestId, String naam) {
        this.id = new SimpleIntegerProperty(id);
        this.titel = new SimpleStringProperty(titel);
        this.artiestId = new SimpleIntegerProperty(artiestId);
        this.naam = new SimpleStringProperty(naam);
    }

    public Single(int id, int postitie, String titel, int artiestId, String naam) {
        this.id = new SimpleIntegerProperty(id);
        this.positie = new SimpleIntegerProperty(postitie);
        this.titel = new SimpleStringProperty(titel);
        this.artiestId = new SimpleIntegerProperty(artiestId);
        this.naam = new SimpleStringProperty(naam);
    }

    public Single(String titel) {
        this.titel = new SimpleStringProperty(titel);
    }

    public int getId() {
        return id.get();
    }

    public int getPositie() {
        return positie.get();
    }

    public String getTitel() {
        return titel.get();
    }

    public int getArtiestId() {
        return artiestId.get();
    }

    public String getNaam() {
        return naam.get();
    }
}
