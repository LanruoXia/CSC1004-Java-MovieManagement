package org.example.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.example.MyListener;
import org.example.models.ModelList;
import org.example.models.Movie;

import java.io.IOException;
import java.net.URL;
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

    private String currentUser;


    private ModelList modelList = new ModelList();
    private ObservableList<Movie> movies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieHbox.toFront();
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
            }
            for (int i = 0; i < movies.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/filmCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                filmCardController filmCardController = fxmlLoader.getController();
                filmCardController.setData(movies.get(i), myListener);
                if(col == 3){
                    col = 0;
                    row++;
                }

                MovieGrid.add(anchorPane, col, row);
                col++;
                MovieGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                MovieGrid.setPrefWidth(700);
                MovieGrid.setMaxWidth(Region.USE_PREF_SIZE);

                MovieGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                MovieGrid.setPrefHeight(850);
                MovieGrid.setMaxHeight(Region.USE_PREF_SIZE);
                MovieGrid.setPadding(new Insets(30, 30, 30, 30));
                GridPane.setMargin(anchorPane, new Insets(50, 30, 30, 30));
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void setName(String name){
        this.currentUser = name;
        userNameText.setText("Welcome, " + currentUser);
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


    }
    public void handleBackToBrowser(){
        movieHbox.toFront();
    }
}
