package com.solvd;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        String sql = "SELECT * FROM  Concert_Hall.artists WHERE ArtistID=2;";
        String url = "jdbc:mysql://localhost:3306/concert_hall";
        String username ="root";
        String password ="admin";

        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        String artistID = resultSet.getString(1);
        System.out.println(artistID);

    }

}
