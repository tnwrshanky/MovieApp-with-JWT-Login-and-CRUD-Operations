package com.niit.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    String name;
    String userName;
    String password;
    String mobileNo;
    List<Movie> movies;

    public User(String name, String userName, String password, String mobileNo, List<Movie> movies) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.mobileNo = mobileNo;
        this.movies = movies;
    }

}
