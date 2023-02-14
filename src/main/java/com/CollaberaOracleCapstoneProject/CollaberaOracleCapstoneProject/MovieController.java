package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/RecommendSystem")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    FindSimilarMovies findMovies;
    @Autowired
    GetMovies getMovies;

    @Autowired
    FindSimilarMoviesID findSimilarMoviesID;

    @Autowired
    FindSimilarMoviesTMDBID findSimilarMoviesTMDBID;

    @Autowired
    GetAllMovies getAllMovies;

    @Autowired
    SaveMovie saveMovie;

    @Autowired
    GetAllAdminMovies getAllAdminMovies;

    @Autowired
    AdminDeleteMovie adminDeleteMovie;


    @GetMapping("/hello/{movieName}")
    public ResponseEntity<List<String>> getSimilarMovies(@PathVariable String movieName) {
        List<String> moviesFound = findMovies.findSimilarMovies(movieName);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(moviesFound, responseHeaders, 200);

    }

    @GetMapping("/getMovies/{movieName}")
    public ResponseEntity<List<String>> getMovies(@PathVariable String movieName) {
        List<String> moviesFound = getMovies.getMovies(movieName);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(moviesFound, responseHeaders, 200);

    }


    @GetMapping("/getSimilarMoviesId/{movieName}")
    public ResponseEntity<List<String>> getSimilarMoviesId(@PathVariable String movieName) {
        List<String> moviesImdbId = findSimilarMoviesID.findSimilarMoviesID(movieName);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(moviesImdbId, responseHeaders, 200);

    }

    @GetMapping("/getSimilarMoviesTMDBID/{tmdbid}")
    public ResponseEntity<List<String>> getSimilarMoviesTMDBID(@PathVariable String tmdbid) {
        List<String> moviesImdbId = findSimilarMoviesTMDBID.findSimilarMoviesTMDBID(tmdbid);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(moviesImdbId, responseHeaders, 200);

    }

    @GetMapping("/getAllMovies/{number_of_rows}")
    public ResponseEntity<List<String>> getAllMoviesTMDBID(@PathVariable String number_of_rows) {
        List<String> allMoviesTMDBID = getAllMovies.getAllMovies(number_of_rows);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(allMoviesTMDBID, responseHeaders, 200);

    }

    @GetMapping("/getAdminMovies")
    public ResponseEntity<List<String>> getAllAdminMovies() {
        List<String> allMoviesTMDBID = getAllAdminMovies.getAllAdminMovies();
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(allMoviesTMDBID, responseHeaders, 200);

    }

    @PostMapping("/saveAdminMovie/{tmdbid}")
    public void saveAdminMovie( @PathVariable String tmdbid) {
         saveMovie.saveMovie(tmdbid);

    }

    @DeleteMapping ("/deleteAdminMovie/{tmdbid}")
    public void deleteAdminMovie(@PathVariable String tmdbid) {
         adminDeleteMovie.adminDeleteMovie(tmdbid);

    }
}
