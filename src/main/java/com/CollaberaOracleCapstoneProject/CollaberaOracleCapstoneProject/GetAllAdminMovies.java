package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    @Service
public class GetAllAdminMovies {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        static final String DB_URL = "jdbc:oracle:thin:@database-2.cmxecweo1rn2.ap-southeast-1.rds.amazonaws.com:1521:ORCL";
        static final String USER = "Admin";
        static final String PASS = "Password123";
    public List<String> getAllAdminMovies() {
        List<String> movieTMDBID = new ArrayList<>();

        movieTMDBID = RunQuery("SELECT tmdbid FROM filtered_movies_medium_tmdb_shahib ORDER BY movieid DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY" );

        return movieTMDBID;
    }



    static List<String> RunQuery (String myQuery){
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
                result.add(rs.getString("tmdbid"));

            }

            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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

