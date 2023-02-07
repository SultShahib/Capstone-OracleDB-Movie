package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindSimilarMoviesID {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
    static final String USER = "adminaziz";
    static final String PASS = "sMArt123_x";


    @Autowired
    FindSimilarMovies findSimilarMovies;

    @Autowired
    GetMovies getMovies;

    public List<String> findSimilarMoviesID(String name) {
        System.out.println("Starting: findSimilarMoviesID");
        List<String> getSimilarMoviesNames = findSimilarMovies.findSimilarMovies(name);
        List<String> getSimilarMoviesIMDBID = new ArrayList<>();

        if(getSimilarMoviesNames.size() > 1) {
            for(int i = 0; i < getSimilarMoviesNames.size(); i++) {
                List<String> imdbid = getMovies.getMovies(getSimilarMoviesNames.get(i));
                getSimilarMoviesIMDBID.add(imdbid.get(0));
            }

            return getSimilarMoviesIMDBID;
        } else if(getSimilarMoviesNames.size() == 0) {
            System.out.println("Your search for " + name + "returned no results!!!" );
            return getSimilarMoviesIMDBID;
            } else {
            List<String> imdbid = getMovies.getMovies(getSimilarMoviesNames.get(0));
            getSimilarMoviesIMDBID.add(imdbid.get(0));

            return getSimilarMoviesIMDBID;
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
