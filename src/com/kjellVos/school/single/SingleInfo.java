package com.kjellVos.school.single;

/**
 * Created by kjell on 4-7-2017.
 */
public class SingleInfo {
    String titel, artiest;
    int jaar, week, aantalWeken, hoogstePositie;

    public SingleInfo(String titel, String artiest, int jaar, int week, int aantalWeken, int hoogstePositie){
        this.titel = titel;
        this.artiest = artiest;
        this.jaar = jaar;
        this.week = week;
        this.aantalWeken = aantalWeken;
        this.hoogstePositie = hoogstePositie;
    }

    public String getTitel() {
        return titel;
    }

    public String getArtiest() {
        return artiest;
    }

    public int getJaar() {
        return jaar;
    }

    public int getWeek() {
        return week;
    }

    public int getAantalWeken() {
        return aantalWeken;
    }

    public int getHoogstePositie() {
        return hoogstePositie;
    }
}
