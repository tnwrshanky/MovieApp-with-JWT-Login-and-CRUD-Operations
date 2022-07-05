package com.niit.product.service;

import com.niit.product.exception.UserNotFoundException;
import com.niit.product.exception.MovieNotFoundException;
import com.niit.product.model.Movie;
import com.niit.product.model.User;
import com.niit.product.proxy.UserProxy;
import com.niit.product.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private UserProxy userProxy;

    @Autowired
    public MovieServiceImpl(UserProxy userProxy, MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User registerNewUser(User user){
        userProxy.saveUser(user);
        movieRepository.save(user);
        return user;
    }

    @Override
    public boolean saveUserMovie(Movie movie, String userName) throws UserNotFoundException {
        if(movieRepository.findById(userName).isEmpty()){
            throw new UserNotFoundException();
        }
        User customer= movieRepository.findById(userName).get();
        List<Movie> customerMovie =customer.getMovies();
        if(customerMovie ==null){
            customerMovie =new ArrayList<>();
        }
        customerMovie.add(movie);
        customer.setMovies(customerMovie);
        movieRepository.save(customer);
        return true;
    }

    @Override
    public boolean deleteMovieOfAUser(Movie movie, String userName) throws UserNotFoundException, MovieNotFoundException {
        if(movieRepository.findById(userName).isEmpty()){
            throw new UserNotFoundException();
        }
        User user= movieRepository.findById(userName).get();
        List<Movie> movieList =user.getMovies();
        if(movieList ==null){
            throw new MovieNotFoundException();
        }
        if(!movieList.contains(movie)){
            throw new MovieNotFoundException();
        }
        movieList.remove(movie);
        user.setMovies(movieList);
        movieRepository.save(user);
        return true;
    }

    @Override
    public List<Movie> getAllMoviesOfAUser(String userName) throws UserNotFoundException {
        if(movieRepository.findById(userName).get() == null){
            throw new UserNotFoundException();
        }
        User customer= movieRepository.findById(userName).get();
        List<Movie> customerMovie =customer.getMovies();
        return customerMovie;
    }
}
