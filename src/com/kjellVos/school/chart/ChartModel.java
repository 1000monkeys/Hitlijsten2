package com.kjellVos.school.chart;

import com.kjellVos.school.single.Single;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by kjell on 3-7-2017.
 */
public class ChartModel {
    private Connection connection;
    private ObservableList data;

    public ChartModel(String host, String username, String password) {
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

    public void loadData() {
        data = FXCollections.observableArrayList();

        String sql =    "SELECT DISTINCT hitlijst_editie, hitlijst, jaar, week, naam \n" +
                        "FROM hitlijst_notering \n" +
                        "LEFT JOIN hitlijst_editie ON hitlijst_notering.hitlijst_editie = hitlijst_editie.id\n" +
                        "LEFT JOIN hitlijst ON hitlijst_editie.hitlijst = hitlijst.id \n" +
                        "ORDER BY jaar, week ASC;";

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
                        new Chart(
                                resultSet.getInt("hitlijst"),
                                resultSet.getInt("jaar"),
                                resultSet.getInt("week"),
                                resultSet.getInt("hitlijst_editie"),
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

    public ObservableList<Chart> getData() {
        return data;
    }

    public ObservableList<Single> getChart(int hitlijst, int jaar, int week) {
        ObservableList<Single> temp = FXCollections.observableArrayList();

        String sql =    "SELECT single.id, hitlijst_notering.positie AS positie, titel, artiest.id AS artiestId, artiest.naam FROM hitlijst_notering \n" +
            "LEFT JOIN hitlijst_editie ON hitlijst_editie.id = hitlijst_notering.hitlijst_editie\n" +
                    "LEFT JOIN single ON hitlijst_notering.single = single.id\n" +
                    "LEFT JOIN artiest ON single.artiest = artiest.id\n" +
                    "LEFT JOIN hitlijst ON hitlijst_editie.hitlijst = hitlijst.id\n" +
                    "WHERE hitlijst = '" + hitlijst + "' AND jaar = '" + jaar + "' AND week = '" + week + "';";
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
                    temp.add(
                            new Single(
                                    resultSet.getInt("id"), resultSet.getInt("positie"), resultSet.getString("titel"), resultSet.getInt("artiestId"), resultSet.getString("naam")
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }

        return temp;
    }
}
