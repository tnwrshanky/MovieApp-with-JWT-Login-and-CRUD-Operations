package com.niit.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Movie {
    @Id
    String movieCode;
    String movieName;
    String movieLang;

    public Movie(String movieCode, String movieName, String movieLang) {
        this.movieCode = movieCode;
        this.movieName = movieName;
        this.movieLang = movieLang;
    }
}
