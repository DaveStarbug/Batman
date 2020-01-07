package com.star.batman.Model.CallBackInterface;

import com.star.batman.Model.Pojo.FilmWrapper;


public interface ICallBackGetMovies {

    void GetMoviesOnTrueResponse(FilmWrapper filmWrapper);

    void GetMoviesOnFalseResponse(String error);

    void GetMoviesOnFailure(String error);

}
