package com.niit.product.service;

import com.niit.product.exception.UserNotFoundException;
import com.niit.product.exception.MovieNotFoundException;
import com.niit.product.model.Movie;
import com.niit.product.model.User;

import java.util.List;

public interface MovieService {
    public User registerNewUser(User user);
    public boolean saveUserMovie(Movie movie, String userName) throws UserNotFoundException;
    public boolean deleteMovieOfAUser(Movie movie, String userName) throws UserNotFoundException, MovieNotFoundException;
    public List<Movie> getAllMoviesOfAUser(String userName) throws UserNotFoundException;
}
