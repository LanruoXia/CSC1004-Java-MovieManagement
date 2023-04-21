package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.MyListener;
import org.example.models.Movie;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class filmCardController {
    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ratingLabel;

    private Movie movie;

    private MyListener myListener;



    public void setData(Movie movie, MyListener mylistener){
        this.movie = movie;
        this.myListener = mylistener;
        nameLabel.setText(movie.getNameWithYear());
        if(movie.getRating() == null){
            ratingLabel.setText("No rating yet");
        }else{
            ratingLabel.setText(movie.getRating());
        }
        Image image = new Image(movie.getPoster_path());
        img.setImage(image);


    }


    public void clickMovie(javafx.scene.input.MouseEvent mouseEvent) {
        myListener.filmCardListener(movie);
    }
}
