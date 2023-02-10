package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindSimilarMoviesTMDBID {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
    static final String USER = "adminaziz";
    static final String PASS = "sMArt123_x";
    public List<String> findSimilarMoviesTMDBID(String tmdbid) {
        List<String> similarIDs = new ArrayList<>();
        List<String> similarTitle = new ArrayList<>();
        List<String> similarTitle2 = new ArrayList<>();
        List<String> movieNames = new ArrayList<>();
        List<String> movieIDs = new ArrayList<>();
        List<String> movieID = new ArrayList<>();
        List<String> movieTitle = new ArrayList<>();

        movieTitle = RunQuery("select title from filtered_movies_medium_tmdbid where tmdbid=" + tmdbid, "title");
        movieID = RunQuery("select movieid from filtered_movies_medium_tmdbid where lower(title) like '%"+movieTitle.get(0).toLowerCase() +"%'", "movieid");


        if (movieID.size() > 1) {
            similarIDs = RunQuery("Select movie_id from pearsons_correlations where ID_"+ movieID.get(0) +" > 0.5", "movie_id");

            for (int i = 0 ; i < similarIDs.size(); i++){
                similarTitle2 = RunQuery("select tmdbid from filtered_movies_medium_tmdbid where movieid = "+similarIDs.get(i), "tmdbid");
                similarTitle.add(similarTitle2.get(0));
            }

            return similarTitle;
        } else if(movieID.size() == 0) {
            System.out.println("Your search for " + tmdbid + "returned no results!!!" );
            return movieID;
        } else  {

            similarIDs = RunQuery("Select movie_id from pearsons_correlations where ID_"+ movieID.get(0) +" > 0.5", "movie_id");
            for (int i = 0 ; i < similarIDs.size(); i++){
                similarTitle2 = RunQuery("select tmdbid from filtered_movies_medium_tmdbid where movieid = "+similarIDs.get(i), "tmdbid");
                similarTitle.add(similarTitle2.get(0));
            }
            return similarTitle;
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

