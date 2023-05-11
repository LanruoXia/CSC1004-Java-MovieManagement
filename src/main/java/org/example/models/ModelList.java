package org.example.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.utils.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelList {
    private final ObservableList<Movie> movieModelList;
    private final ObservableList<Review> reviewModelList;

    public ModelList(){
        this.movieModelList = FXCollections.observableArrayList();
        this.reviewModelList = FXCollections.observableArrayList();
    }

    public void delete(Movie selected) {
        this.movieModelList.remove(selected);
    }

    public ObservableList<Movie> getMovieModelList() {
        return movieModelList;
    }
    public ObservableList<Review> getReviewModelList(){ return reviewModelList; }
    public void loadMovies(){
        ResultSet rs = null;
        try {
            rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.movie_repo");
            movieModelList.clear();
            while (rs.next()){
                Movie newmovie = new Movie();
                System.out.println(rs.getInt("idmovies"));
                newmovie.setId(rs.getInt("idmovies"));
                newmovie.setName(rs.getString("movie"));
                newmovie.setGenre(rs.getString("genre"));
                newmovie.setCountry(rs.getString("country"));
                newmovie.setDirector(rs.getString("director"));
                newmovie.setPoster_path(rs.getString("poster_path"));
                newmovie.setYear(Integer.parseInt(rs.getString("year")));
                newmovie.setNameWithYear(rs.getString("movie") + " (" + rs.getString("year") + ")");
                newmovie.setIntroduction(rs.getString("introduction"));
                ResultSet rs_rating = null;
                String getRating =  "SELECT AVG(rating) FROM movieadmin.user_ratings WHERE idmovies = " + rs.getInt("idmovies");
                rs_rating = JdbcUtils.getQueryResult(getRating);
                rs_rating.next();
                if(rs_rating.getString(1) == null){
                    newmovie.setRating("No rating yet");
                }else {
                    String ratingNum = rs_rating.getString(1);
                    double rating = Double.parseDouble(ratingNum);
                    String ratingResult = String.format("%.1f",rating);
                    newmovie.setRating(ratingResult);
                    String insertRating = "UPDATE movie_repo SET rating = '" +ratingResult+ "' WHERE idmovies = " + newmovie.getId();
                    JdbcUtils jdbcUtils = new JdbcUtils();
                    jdbcUtils.executeQueryStmt(insertRating);
                }
                movieModelList.add(newmovie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public void loadReviews(Integer movieId) throws SQLException {
        ResultSet rs_comment = null;
        String getComment = "SELECT * FROM movieadmin.user_comments  WHERE idmovies = '" + movieId + "'";
        rs_comment = JdbcUtils.getQueryResult(getComment);
        reviewModelList.clear();
        while(rs_comment.next()){
            Review newReview = new Review();
            newReview.setId(rs_comment.getInt("iduser_reviews"));
            newReview.setMovieId(rs_comment.getInt("idmovies"));
            newReview.setMovie(rs_comment.getString("movie"));
            newReview.setUsername(rs_comment.getString("username"));
            newReview.setComment(rs_comment.getString("comment"));
            newReview.setHeadline(rs_comment.getString("headline"));
            ResultSet rs_rating = null;
            String getRating =  "SELECT * FROM movieadmin.user_ratings  WHERE idmovies = " + movieId + " and username = '" + rs_comment.getString("username")+ "'";
            rs_rating = JdbcUtils.getQueryResult(getRating);
            rs_rating.next();
            newReview.setRating(rs_rating.getInt("rating"));
            if(rs_comment.getString("image_path") != null){
                newReview.setImage_path(rs_comment.getString("image_path"));
            }
            reviewModelList.add(newReview);
        }




    }
}
