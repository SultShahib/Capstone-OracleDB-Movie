package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class GetMovies {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@database-2.cmxecweo1rn2.ap-southeast-1.rds.amazonaws.com:1521:ORCL";
    static final String USER = "Admin";
    static final String PASS = "Password123";
    public List<String> getMovies(String movieName) {
        List<String> movieID = new ArrayList<>();
        List<String> tmdbID = new ArrayList<>();
        List<String> tmdbIDs = new ArrayList<>();
        List<String> testIDS = new ArrayList<>();


        tmdbIDs = ExecuteQuery("select tmdbid from filtered_movies_medium_tmdb where lower(title) like '%"+movieName.toLowerCase() +"%'", "tmdbid");

        for(int i = 0; i < tmdbIDs.size(); i++) {
            System.out.println(tmdbIDs.get(i));
        }

        if(movieID.size() > 1) {
            return tmdbIDs;
        } else if(tmdbIDs.size() == 0) {
            tmdbIDs = null;
            return tmdbIDs;
        } else {
            return tmdbIDs;
        }

}
    static List<String> ExecuteQuery (String myQuery, String myColumn){
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