package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindSimilarMovies {

        static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
    static final String USER = "adminaziz";
    static final String PASS = "sMArt123_x";
    public List<String> findSimilarMovies(String movieName) {
        List<String> similarIDs = new ArrayList<>();
        List<String> similarTitle = new ArrayList<>();
        List<String> similarTitle2 = new ArrayList<>();
        List<String> movieNames = new ArrayList<>();
        List<String> movieIDs = new ArrayList<>();
        List<String> movieID = new ArrayList<>();


//        movieNames = RunQuery("select title from movies where lower(title) like '%"+movieName.toLowerCase() +"%'", "title");
        movieNames = RunQuery("select movieid from filtered_movies_medium_tmdbid where lower(title) like '%"+movieName.toLowerCase() +"%'", "movieid");
        System.out.println("Testing for errors, delete me later");
        System.out.println("Movie name: " + movieName);
        System.out.println("All movie Names name: " + movieNames);
        System.out.println("Testing: Find similar movies using movie name");

        if (movieNames.size() > 1) {
//            System.out.println("Your search for " + movieName + " returned:" );
//            for (int i = 0 ; i < movieNames.size(); i++){
//                System.out.println(movieNames.get(i));
//            }
//            return movieNames;


//            movieID = RunQuery("select movieid from movies where lower(title) like '" + movieNames.get(0).toLowerCase() +"'", "movieid");

            similarIDs = RunQuery("Select movie_id from pearsons_correlations where ID_"+ movieNames.get(0) +" > 0.5", "movie_id");

            System.out.println("Since you liked "+movieName+", you may also like: ");
            for (int i = 0 ; i < similarIDs.size(); i++){
//                similarTitle2 = RunQuery("select title from movies where movieid = "+similarIDs.get(i), "title");
                similarTitle2 = RunQuery("select title from filtered_movies_medium_tmdbid where movieid = "+similarIDs.get(i), "title");
                similarTitle.add(similarTitle2.get(0));
                //System.out.println(similarIDs.get(i));
                for (int j = 0 ; j < similarTitle.size(); j++){
                    System.out.println(similarTitle.get(j));
                }
            }

            System.out.println(similarTitle.size());
            return similarTitle;
        } else if(movieNames.size() == 0) {
            System.out.println("Your search for " + movieName + "returned no results!!!" );
            return movieNames;
        } else  {


//            movieID = RunQuery("select movieid from movies where lower(title) like '" + movieNames.get(0).toLowerCase() +"'", "movieid");
            movieID = RunQuery("select movieid from filtered_movies_medium_tmdbid where lower(title) like '" + movieNames.get(0).toLowerCase() +"'", "movieid");
            System.out.println("The movieID are ");
            for(int i = 0; i < movieID.size(); i++) {
                System.out.println(movieID.get(i));
            }


            similarIDs = RunQuery("Select movie_id from pearsons_correlations where ID_"+ movieID.get(0) +" > 0.5", "movie_id");
            System.out.println("The SimilarID's are ");
            for(int i = 0; i < similarIDs.size(); i++) {
                System.out.println(similarIDs.get(i));
            }


            System.out.println("Since you liked "+movieName+", you may also like: ");
            for (int i = 0 ; i < similarIDs.size(); i++){
//                similarTitle2 = RunQuery("select title from movies where movieid = "+similarIDs.get(i), "title");
                similarTitle2 = RunQuery("select title from filtered_movies_medium_tmdbid where movieid = "+similarIDs.get(i), "title");
                similarTitle.add(similarTitle2.get(0));
                //System.out.println(similarIDs.get(i));
                for (int j = 0 ; j < similarTitle.size(); j++){
                    System.out.println(similarTitle.get(j));
                }
            }

            System.out.println(similarTitle.size());
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

