package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Review {
    private IntegerProperty id;
    private StringProperty movie;
    private IntegerProperty movieId;
    private IntegerProperty userId;
    private StringProperty username;
    private StringProperty comment;
    private IntegerProperty rating;
    private StringProperty headline;

    private StringProperty image_path;

    public String getHeadline() {
        return headline.get();
    }

    public StringProperty headlineProperty() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline.set(headline);
    }

    public Review(){
        this.id = new SimpleIntegerProperty();
        this.movie = new SimpleStringProperty();
        this.movieId = new SimpleIntegerProperty();
        this.userId = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.comment = new SimpleStringProperty();
        this.rating = new SimpleIntegerProperty();
        this.headline = new SimpleStringProperty();
        this.image_path = new SimpleStringProperty();
    }

    public String getImage_path() {
        return image_path.get();
    }

    public StringProperty image_pathProperty() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path.set(image_path);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMovie() {
        return movie.get();
    }

    public StringProperty movieProperty() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie.set(movie);
    }

    public int getMovieId() {
        return movieId.get();
    }

    public IntegerProperty movieIdProperty() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId.set(movieId);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getRating() {
        return rating.get();
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }
}

