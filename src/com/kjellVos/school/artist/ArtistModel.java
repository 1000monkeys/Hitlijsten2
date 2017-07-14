package com.kjellVos.school.artist;

import com.kjellVos.school.single.Single;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by kjell on 3-7-2017.
 */
public class ArtistModel {
    private Connection connection;
    private ObservableList data;


    public ArtistModel(String host, String username, String password) {
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

        String sql = "SELECT * FROM artiest;";

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
                        new Artist(
                                resultSet.getInt("id"),
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

    public ArtistInfo getDataByArtist(int id) {
        return new ArtistInfo(getNaamById(id), getSinglesById(id));
    }

    public ObservableList getSinglesById(int id){
        ObservableList singles = FXCollections.observableArrayList();

        String sql = "SELECT titel FROM single WHERE artiest = '" + id + "';";

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
                    singles.add(
                            new Single(
                                    resultSet.getString("titel")
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Error handling
            }
        }
        return singles;
    }

    public String getNaamById(int id){
        String naam = "";
        String sql = "SELECT naam FROM artiest WHERE id = '" + id + "';";

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
}
