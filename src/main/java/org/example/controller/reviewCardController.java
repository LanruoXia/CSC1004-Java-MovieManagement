package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.models.Review;


public class reviewCardController {

    @FXML
    private ImageView commentImage;

    @FXML
    private TextArea commentText;


    @FXML
    private Label ratingLabel;

    @FXML
    private Label userLabel;
    @FXML
    private Label headlineText;
    @FXML
    private VBox reviewCardBox;

    private String currentUser;

    private Review review;

    public void setData(Review review){
        this.currentUser = review.getUsername();
        this.review = review;
        userLabel.setText(currentUser);
        ratingLabel.setText(Integer.toString(review.getRating()));
        headlineText.setText(review.getHeadline());
        commentText.setText(review.getComment());
        System.out.println("xasdasddad" + review.getImage_path());
        if(review.getImage_path() != null){
            Image image = new Image((review.getImage_path()));
            commentImage.setImage(image);
        }
        commentText.setPrefRowCount(100);
        commentText.setPrefColumnCount(150);
        commentText.setWrapText(true);
        commentText.setPrefWidth(551);
        commentText.setPrefHeight(123);
        commentText.setEditable(false);

    }
}
