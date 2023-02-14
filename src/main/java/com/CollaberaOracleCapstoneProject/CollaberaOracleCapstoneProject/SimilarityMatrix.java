package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class SimilarityMatrix {
    private static Map<Integer, Map<Integer, Double>> ratings = new HashMap<>();

    public static void main(String[] args) throws Exception {

        // read CSV file from Movielens
        BufferedReader reader = new BufferedReader(new FileReader("/Users/sultshahib/downloads/ml-latest-small/ratings.csv"));
        String line = reader.readLine();

        // check for comma
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            int userId = Integer.parseInt(values[0]);
            int movieId = Integer.parseInt(values[1]);
            double rating = Double.parseDouble(values[2]);

            if (!ratings.containsKey(movieId)) {
                ratings.put(movieId, new HashMap<>());
            }
            ratings.get(movieId).put(userId, rating);
            System.out.println(ratings.get(movieId).put(userId, rating));
        }

        reader.close();

        Map<Integer, Map<Integer, Double>> filteredRatings = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Double>> movie : ratings.entrySet()) {

//            Calculation is done by only selecting movies that have been rated by atleast 50 users
            if (movie.getValue().size() >= 50) {
                filteredRatings.put(movie.getKey(), movie.getValue());
                System.out.println(filteredRatings.put(movie.getKey(), movie.getValue()));

            }
        }

        int n = filteredRatings.size();
        double[][] similarity = new double[n][n];

        // compare every movie to every other movie. (450 movies : 450 movies)
        int i = 0;
        for (Map.Entry<Integer, Map<Integer, Double>> movie1 : filteredRatings.entrySet()) {
            int j = 0;
            for (Map.Entry<Integer, Map<Integer, Double>> movie2 : filteredRatings.entrySet()) {
                if (i <= j) {
                    similarity[i][j] = similarity[j][i] = calculateSimilarity(movie1.getValue(), movie2.getValue());
                }
                j++;
            }
            i++;
        }

        // save the data into a csv file
        FileWriter writer = new FileWriter("/Users/sultshahib/downloads/similarity_matrix_50.csv");
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.append(similarity[i][j] + ",");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    // Pure implementation of Pearson's Correlation
    // My reference for the algorithm visit: https://www.geeksforgeeks.org/program-find-correlation-coefficient/
    private static double calculateSimilarity(Map<Integer, Double> movie1, Map<Integer, Double> movie2) {
        int n = 0;
        double averageOne = 0;
        double averageTwo = 0;
        double sumOne = 0;
        double sumTwo = 0;
        double sumOneSquare = 0;
        double sumTwoSquare = 0;
        double pointSum = 0;

        for (Map.Entry<Integer, Double> rating1 : movie1.entrySet()) {
            if (movie2.containsKey(rating1.getKey())) {
                n++;
                double r1 = rating1.getValue();
                double r2 = movie2.get(rating1.getKey());
                averageOne += r1;
                averageTwo += r2;
                sumOne += r1;
                sumTwo += r2;
                sumOneSquare += Math.pow(r1, 2);
                sumTwoSquare += Math.pow(r2, 2);
                pointSum += r1 * r2;
            }
        }

        if (n == 0) {
            return 0;
        }

        averageOne /= n;
        averageTwo /= n;

        double num = pointSum - (sumOne * averageTwo + sumTwo * averageOne) / n;
        double den = Math.sqrt((sumOneSquare - 2 * averageOne * sumOne + n * Math.pow(averageOne, 2)) * (sumTwoSquare - 2 * averageTwo * sumTwo + n * Math.pow(averageTwo, 2)));

        if (den == 0) {
            return 0;
        }

        return num / den;
    }
}
