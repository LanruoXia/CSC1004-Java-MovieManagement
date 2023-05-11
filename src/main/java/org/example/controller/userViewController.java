package org.example.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.MyListener;
import org.example.models.ModelList;
import org.example.models.Movie;
import org.example.models.Review;
import org.example.utils.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class userViewController implements Initializable {

    @FXML
    private Label BrowseCoText;

    @FXML
    private Label BrowseDireText;

    @FXML
    private Label BrowseGenreText;

    @FXML
    private TextArea BrowseIntroText;

    @FXML
    private GridPane MovieGrid;

    @FXML
    private BorderPane browseBorderPane;

    @FXML
    private Label browseMovieLabel;

    @FXML
    private Label browseRatingLabel;

    @FXML
    private ScrollPane browserScroll;

    @FXML
    private MyListener myListener;

    @FXML
    private Label userNameText;

    @FXML
    private ImageView movieImageView;

    @FXML
    private HBox movieHbox;

    @FXML
    private Label backToBrowserLabel;

    @FXML
    private VBox reviewVBox;

    @FXML
    private ImageView myImage;


    @FXML
    private Label createReview;

    @FXML
    private Label SignOut;

    private String currentUser;

    private Movie currentMovie;
    private Integer currentMovieId;


    private ModelList modelList = new ModelList();
    private ObservableList<Movie> movies;
    private ObservableList<Review> reviews;


    private userViewController currentUserViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(myImage);
        translate.setDuration(Duration.millis(5000));
        translate.setByY(250);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setAutoReverse(true);
        translate.play();
        movieHbox.toFront();
        initMovieRopo();

    }
    public void initMovieRopo(){
        int col = 0;
        int row = 0;
        try {
            modelList.loadMovies();
            movies = modelList.getMovieModelList();
            if(movies.size() > 0){
                myListener = new MyListener() {
                    @Override
                    public void filmCardListener(Movie movie) {
                        setChosenMovie(movie);

                    }
                };

                for (int i = 0; i < movies.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/view/filmCard.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    anchorPane.setPrefSize(172, 251);
                    filmCardController filmCardController = fxmlLoader.getController();
                    filmCardController.setData(movies.get(i), myListener);
                    if(col == 3){
                        col = 0;
                        row++;
                    }
                    MovieGrid.add(anchorPane, col, row);

                    MovieGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    MovieGrid.setPrefWidth(1200);
                    MovieGrid.setMaxWidth(Region.USE_PREF_SIZE);

                    MovieGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    MovieGrid.setPrefHeight(1200);
                    MovieGrid.setMaxHeight(Region.USE_PREF_SIZE);
                    MovieGrid.setPadding(new Insets(10,10,10,10));
                    GridPane.setMargin(anchorPane, new Insets(20,20,20,20));
                    col++;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setViewInfo(String name, userViewController userViewController1){
        this.currentUser = name;
        userNameText.setText("Welcome, " + currentUser);
        this.currentUserViewController = userViewController1;
    }

    public void setChosenMovie(Movie movie){
        browseBorderPane.toFront();
        browseMovieLabel.setText(movie.getNameWithYear());
        browseRatingLabel.setText(movie.getRating());
        Image image = new Image(movie.getPoster_path());
        movieImageView.setImage(image);
        BrowseGenreText.setText(movie.getGenre());
        BrowseDireText.setText(movie.getDirector());
        BrowseCoText.setText(movie.getCountry());
        BrowseIntroText.setText(movie.getIntroduction());
        BrowseIntroText.setPrefRowCount(10);
        BrowseIntroText.setPrefColumnCount(100);
        BrowseIntroText.setWrapText(true);
        BrowseIntroText.setPrefWidth(320);
        BrowseIntroText.setEditable(false);
        this.currentMovie = movie;
        this.currentMovieId = movie.getId();
        try {
            modelList.loadMovies();
            modelList.loadReviews(currentMovieId);
            reviews = modelList.getReviewModelList();
            reviewVBox.getChildren().clear();
            if(reviews.size() > 0){
                for(int i = 0; i < reviews.size(); i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/view/reviewCard.fxml"));
                    try {
                        VBox vBox = fxmlLoader.load();
                        reviewCardController reviewCardController1 = fxmlLoader.getController();
                        reviewCardController1.setData(reviews.get(i));
                        reviewVBox.getChildren().add(vBox);
                        reviewVBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void handleBackToBrowser(){
        initMovieRopo();
        movieHbox.toFront();
        modelList.loadMovies();
    }

    public void handleReviewCreating(){
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(userViewController.class.getResource("/view/reviewCreating.fxml"));
        try {
            root = loader.load();
            reviewCreatingController reviewCreatingController1 = loader.getController();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
            reviewCreatingController1.setInfo(currentMovie, currentUser, currentUserViewController);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initMovieRopo();


    }
    public void handleSignOut(){
        Stage stage = (Stage)SignOut.getScene().getWindow();
        stage.close();
        ViewUtils.openView("view/login.fxml");
    }

}
