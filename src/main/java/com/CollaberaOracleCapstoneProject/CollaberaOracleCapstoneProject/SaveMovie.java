package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveMovie  {

        static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
        static final String USER = "adminaziz";
        static final String PASS = "sMArt123_x";
        public void saveMovie(String tmdbid) {
            List<String> movieID = new ArrayList<>();
            List<String> tmdbIDs = new ArrayList<>();
            String values = "('" + tmdbid + "')";


             RunQuery("insert into filtered_movies_tmdb_medium_shahib(tmdbid) values" + values);


        }
        static void RunQuery (String myQuery){
            Connection conn = null;
            Statement stmt = null;
            List<String> result = new ArrayList<>();
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                String sql = myQuery;
                ResultSet rs = stmt.executeQuery(sql);

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

//            return result;
        }
    }

