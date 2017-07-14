package com.kjellVos.school.single;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by kjell on 3-7-2017.
 */
public class SingleModel {
    Connection connection;
    ObservableList data;

    public SingleModel(String host, String username, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/hitdossier", username, password);
        } catch (SQLException e) {
            //TODO Error handling
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //TODO Error handling
            e.printStackTrace();
        }
    }

    public void loadData(){
        data = FXCollections.observableArrayList();

        String sql = "SELECT single.id, titel, artiest.id AS artiestId, naam FROM single LEFT JOIN artiest ON single.artiest = artiest.id;";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO Error handling
        }

        if (statement != null) {
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    data.add(
                            new Single(
                                    resultSet.getInt("id"),
                                    resultSet.getString("titel"),
                                    resultSet.getInt("artiestId"),
                                    resultSet.getString("naam")
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }
    }

    public ObservableList getData() {
        return data;
    }

    public SingleInfo getDataBySingle(int id) {
        int positie = 0, jaar = 0, week = 0, aantalWeken = 0;

        String sql = "SELECT MIN(jaar), MIN(week), COUNT(hitlijst_notering.single), MIN(positie) FROM single\n" +
                "LEFT JOIN hitlijst_notering ON single.id = hitlijst_notering.single\n" +
                "LefT JOIN hitlijst_editie ON hitlijst_editie.id = hitlijst_notering.hitlijst_editie\n" +
                "WHERE single.id = '" + id + "';";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO Error handling
        }

        if (statement != null) {
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    jaar = resultSet.getInt("MIN(jaar)");
                    week = resultSet.getInt("MIN(week)");
                    aantalWeken = resultSet.getInt("COUNT(hitlijst_notering.single)");
                    positie = resultSet.getInt("MIN(positie)");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }

        return new SingleInfo(getTitelById(id), getNaamById(id), jaar, week, aantalWeken, positie);
    }

    public String getNaamById(int id){
        String naam = "";
        String sql = "SELECT naam FROM single\n" +
                "LEFT JOIN artiest ON single.artiest = artiest.id\n" +
                "WHERE single.id = '" + id + "';";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO Error handling
        }

        if (statement != null) {
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    naam = resultSet.getString("naam");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }
        return naam;
    }

    public String getTitelById(int id){
        String titel = "";
        String sql = "SELECT titel FROM single WHERE id = '" + id + "';";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO Error handling
        }

        if (statement != null) {
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    titel = resultSet.getString("titel");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }
        return titel;
    }
}
