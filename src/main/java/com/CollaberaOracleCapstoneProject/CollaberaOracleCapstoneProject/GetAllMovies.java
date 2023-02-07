package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllMovies {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
    static final String USER = "adminaziz";
    static final String PASS = "sMArt123_x";
    public List<String> getAllMovies(String number_of_rows) {
     List<String> movieTMDBID = new ArrayList<>();

        System.out.println("Initiaing methodd of getting all movies tmdbid");

        movieTMDBID = RunQuery("select tmdbid from filtered_movies_medium_tmdbid fetch first " + number_of_rows + "rows only", "tmdbid");

        System.out.println("Fetching first 450 rows of tmdbid complete");

        return movieTMDBID;
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