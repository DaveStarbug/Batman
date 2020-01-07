package com.star.batman.Model.CallBackInterface;

import com.star.batman.Model.Pojo.FilmDetail;

public interface ICallBackGetMovieDetail {

    void GetMovieDetailOnTrueResponse(FilmDetail filmDetail);

    void GetMovieDetailOnFalseResponse(String error);

    void GetMovieDetailOnFailure(String error);

}
