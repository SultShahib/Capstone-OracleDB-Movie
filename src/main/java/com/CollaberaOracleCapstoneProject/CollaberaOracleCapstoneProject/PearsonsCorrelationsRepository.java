//package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface PearsonsCorrelationsRepository extends JpaRepository<PearsonsCorrelations, Integer> {
//
//
////    Testing TODO: REFACTOR
////    @Query("Select movie_id from pearsons_correlations where ID_[YOUR MOVIE ID] > 0.5")
//@Query("SELECT movie_id FROM pearsons_correlations WHERE ID_:id > 0.1")
//public List<Integer> findByMovieId(@Param("id") String id);
//
//}
