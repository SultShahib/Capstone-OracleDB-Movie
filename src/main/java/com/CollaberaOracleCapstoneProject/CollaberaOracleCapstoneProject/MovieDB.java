//package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class MovieDB {
//    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    static final String DB_URL = "jdbc:oracle:thin:@oracle-aziz.cilyihqptvjt.us-east-1.rds.amazonaws.com:1521:ORCL";
//    static final String USER = "adminaziz";
//    static final String PASS = "sMArt123_x";
//
//    public static void main(String[] args) {
//
//        List<String> similarIDs = new ArrayList<>();
//        List<String> similarTitle = new ArrayList<>();
//        List<String> movieNames = new ArrayList<>();
//        List<String> movieIDs = new ArrayList<>();
//        List<String> movieID = new ArrayList<>();
//
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Type the name of the movie you wanna check out: ");
//        String movieName = myObj.nextLine();  // Read user input
//        System.out.println("Please wait....");
//
//        movieNames = RunQuery("select title from movies where lower(title) like '%"+movieName.toLowerCase() +"%'", "title");
//        if (movieNames.size() >1){
//            System.out.println("Your search for " + movieName + " returned:" );
//            for (int i = 0 ; i < movieNames.size(); i++){
//                System.out.println(movieNames.get(i));
//            }
//            System.out.println("\n...so please be specific (copy paste if need be) and run the program again!");
//        } else if (movieNames.size()==0) {
//            System.out.println("Your search for " + movieName + "returned no results!!!" );
//        }
//        else{
//            movieID = RunQuery("select movieid from movies where lower(title) like '"+movieName.toLowerCase() +"'", "movieid");
//
//            similarIDs = RunQuery("Select movie_id from pearsons_correlations where ID_"+ movieID.get(0) +" > 0.5", "movie_id");
//
//            System.out.println("Since you liked "+movieName+", you may also like: ");
//            for (int i = 0 ; i < similarIDs.size(); i++){
//                similarTitle = RunQuery("select title from movies where movieid = "+similarIDs.get(i), "title");
//                //System.out.println(similarIDs.get(i));
//                for (int j = 0 ; j < similarTitle.size(); j++){
//                    System.out.println(similarTitle.get(j));
//                }
//            }
//
//        }
//
//
//
//    }
//
//    static List<String> RunQuery(String myQuery, String myColumn){
//        Connection conn = null;
//        Statement stmt = null;
//        List<String> result = new ArrayList<>();
//        try {
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            stmt = conn.createStatement();
//
//            String sql = myQuery;
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                result.add(rs.getString(myColumn));
//            }
//            rs.close();
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null)
//                    conn.close();
//            } catch (SQLException se) {
//            }
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//
//        return result;
//    }
//}