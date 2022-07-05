package com.niit.product.controller;

import com.niit.product.exception.UserNotFoundException;
import com.niit.product.exception.MovieNotFoundException;
import com.niit.product.model.Movie;
import com.niit.product.model.User;
import com.niit.product.service.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MovieController {
    @Autowired
    MovieServiceImpl productService;

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User user){
        productService.registerNewUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/authorized/savemovie/{userName}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserMovie(@RequestBody Movie movie, @PathVariable String userName) throws UserNotFoundException {
        log.info("status={}, customer={}, body={}", "SUCCESS", userName,  movie.toString());
        productService.saveUserMovie(movie,userName);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @GetMapping("/authorized/getmovie/{userName}")
    public ResponseEntity<?> getAllMoviesOfAUser(@PathVariable String userName) throws UserNotFoundException {
        List<Movie> movieList =productService.getAllMoviesOfAUser(userName);
        return new ResponseEntity<>(movieList,HttpStatus.OK);
    }

    @DeleteMapping("/authorized/deletemovie/{userName}")
    public ResponseEntity<?> deleteMovieOfAUser(@RequestBody Movie movie, @PathVariable String userName) throws UserNotFoundException, MovieNotFoundException {
        productService.deleteMovieOfAUser(movie,userName);
        return new ResponseEntity<>("Movie Deleted",HttpStatus.OK);
    }
}
