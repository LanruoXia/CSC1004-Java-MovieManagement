package org.example.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.utils.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelList {
    private final ObservableList<Movie> movieModelList;
    public ModelList(){
        this.movieModelList = FXCollections.observableArrayList();
    }

    public void delete(Movie selected) {
        this.movieModelList.remove(selected);
    }

    public ObservableList<Movie> getMovieModelList() {
        return movieModelList;
    }
    public void loadMovies(){
        ResultSet rs = null;
        try {
            rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.movie_repo");
            movieModelList.clear();
            while (rs.next()){
                Movie newmovie = new Movie();
                System.out.println(rs.getInt("idmovies"));
                newmovie.setId(rs.getInt("idmovies"));
                newmovie.setName(rs.getString("name"));
                newmovie.setGenre(rs.getString("genre"));
                newmovie.setCountry(rs.getString("country"));
                newmovie.setDirector(rs.getString("director"));
                newmovie.setPoster_path(rs.getString("poster_path"));
                newmovie.setYear(Integer.parseInt(rs.getString("year")));
                newmovie.setNameWithYear(rs.getString("name") + " (" + rs.getString("year") + ")");
                newmovie.setIntroduction(rs.getString("introduction"));
                if(rs.getString("rating") == null){
                    newmovie.setRating("No rating yet");
                }else {
                    newmovie.setRating(rs.getString("rating"));
                }
                movieModelList.add(newmovie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
