package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class GetMovies {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
    static final String USER = "adminaziz";
    static final String PASS = "sMArt123_x";
    public List<String> getMovies(String movieName) {
        List<String> movieID = new ArrayList<>();
        List<String> tmdbID = new ArrayList<>();
        List<String> tmdbIDs = new ArrayList<>();
        List<String> testIDS = new ArrayList<>();


        tmdbIDs = RunQuery("select tmdbid from filtered_movies_medium_tmdbid where lower(title) like '%"+movieName.toLowerCase() +"%'", "tmdbid");

//        testIDS = RunQuery("select * from filtered_movies_medium_tmdbid where lower(title) like '%"+movieName.toLowerCase() +"%'", "movieid");

            System.out.println("The imdb ID's for the respected movies are: ");
        for(int i = 0; i < tmdbIDs.size(); i++) {
            System.out.println(tmdbIDs.get(i));
        }

        if(movieID.size() > 1) {
//            System.out.println("The Movie ids are greateer than 1. Selecting the imdbid matcing the movie id");
//            for(int i = 0; i < movieID.size(); i++) {
//                tmdbID = RunQuery("select tmdbid from filtered_movies_medium_tmdbid where movieid =" + movieID.get(i), "tmdbid");
//                tmdbIDs.add(tmdbID.get(0));
//            }
//            System.out.println("The tmdbids for the MovieIDS greater than one are ");
//            for(int i = 0; i < tmdbIDs.size(); i++) {
//                System.out.println(tmdbIDs.get(i));
//            }

            return tmdbIDs;
        } else if(tmdbIDs.size() == 0) {
            System.out.println("No movies imdbid found");
            tmdbIDs = null;
            return tmdbIDs;
        } else {
//            System.out.println("The tmdbID is ");
//            System.out.println(movieID);
//
//            tmdbID = RunQuery("select tmdbid from filtered_movies_medium_tmdbid where movieid =" + movieID.get(0), "tmdbid");

            return tmdbIDs;
        }

}
    static List<String> RunQuery (String myQuery, String myColumn){
        Connection conn = null;
        Statement stmt = null;
        List<String> result = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = myQuery;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.add(rs.getString(myColumn));
            }
            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }
}