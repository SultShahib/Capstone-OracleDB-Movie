package com.CollaberaOracleCapstoneProject.CollaberaOracleCapstoneProject;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Data
@Entity(name="pearsons_correlations")
@Table(name="pearsons_correlations")
public class PearsonsCorrelations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

    private String ID_;


}
