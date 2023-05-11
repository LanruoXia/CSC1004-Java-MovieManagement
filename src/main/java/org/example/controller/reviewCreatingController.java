package org.example.controller;

import com.mysql.cj.util.StringUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.models.ModelList;
import org.example.models.Movie;
import org.example.utils.JdbcUtils;
import org.example.utils.StringUtil;
import org.example.utils.ViewUtils;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class reviewCreatingController {
    @FXML
    private TextField headlineText;

    @FXML
    private Label movieName;

    @FXML
    private TextArea reviewText;

    @FXML
    private Button submitButton;

    @FXML
    private Label infoLabel;

    @FXML
    private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10;

    @FXML
    private ImageView reviewImage;
    @FXML
    private Button uploadImageBtn;

    private Image image;


    private String currentUser;

    private int currentRate = 0;

    private Movie movie;

    private userViewController userView;

    private userViewController userViewController1;

    private static String imageName;

    public void setInfo(Movie movie, String user, userViewController userViewController1) {
        this.userViewController1 = userViewController1;
        this.movie = movie;
        this.currentUser = user;
        movieName.setText(movie.getNameWithYear());

    }

    public void rate(ActionEvent event) {
        if (r1.isSelected()) {
            currentRate = 1;
        } else if (r2.isSelected()) {
            currentRate = 2;
        } else if (r3.isSelected()) {
            currentRate = 3;
        } else if (r4.isSelected()) {
            currentRate = 4;
        } else if (r5.isSelected()) {
            currentRate = 5;
        } else if (r6.isSelected()) {
            currentRate = 6;
        } else if (r7.isSelected()) {
            currentRate = 7;
        } else if (r8.isSelected()) {
            currentRate = 8;
        } else if (r9.isSelected()) {
            currentRate = 9;
        } else if (r10.isSelected()) {
            currentRate = 10;
        }

    }

    public void addNewPoster() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open and save an image file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                new FileChooser.ExtensionFilter("All images", "*.jpg"));
        File imageFile = fileChooser.showOpenDialog(submitButton.getScene().getWindow());
        if (imageFile != null) {
            // openFile(file);
            reviewImage.setImage(null);
            image = new Image(imageFile.getPath());
            reviewImage.setImage(image);
            //savePoster(fileName);
            String fileName = imageFile.getName();
            System.out.println(System.getProperty("user.dir"));
            popUpWindow.getOriginalName(fileName);
            ViewUtils.openView("view/renamePoster.fxml");
        }
    }
    public void handleSubmit () {
        if (StringUtil.isEmpty(headlineText.getText()) && StringUtil.isEmpty(reviewText.getText()) && currentRate == 0) {
            infoLabel.setText("Please fill in all your review information!");
            infoLabel.setVisible(true);
        } else {
            int idmovies = movie.getId();
            String movieName = movie.getName();
            String user = currentUser;
            String headline = headlineText.getText();
            String comment = reviewText.getText();
            try {
                JdbcUtils jdbcUtils = new JdbcUtils();
                String verifyRating = "select count(1) from user_ratings where username = '" + user + "' and idmovies = '" + idmovies + "'";
                ResultSet result = JdbcUtils.getQueryResult(verifyRating);
                while (result.next()) {
                    if (result.getInt(1) == 1) {
                        String updateRating = "UPDATE movieadmin.user_ratings SET rating = '" + currentRate + "' WHERE username ='" + currentUser + "' AND idmovies = '" + idmovies + "'";
                        jdbcUtils.executeQueryStmt(updateRating);
                    } else {
                        String createRating = "INSERT INTO movieadmin.user_ratings (username, movie, idmovies, rating) VALUES ('" + currentUser + "','"
                                + movieName + "','" + idmovies + "'," + " " + currentRate + ")";
                        jdbcUtils.executeQueryStmt(createRating);
                    }
                }
                String insertNewReview;
                if(imageName != null) {
                    String imagePath = "images/" + imageName;
                    insertNewReview = "INSERT INTO movieadmin.user_comments (idmovies, movie, username, comment, headline, image_path) VALUES ('" + idmovies + "','"
                            + movieName + "','" + user + "',\"" + comment + "\",\"" + headline + "\",'" + imagePath + "')";
                }else{
                    insertNewReview = "INSERT INTO movieadmin.user_comments (idmovies, movie, username, comment, headline) VALUES ('" + idmovies + "','"
                            + movieName + "','" + user + "',\"" + comment + "\",\"" + headline + "\")";
                }

                jdbcUtils.executeQueryStmt(insertNewReview);
                savePoster();
                infoLabel.setText("Review is created successfully!");
                infoLabel.setVisible(true);
                TimeUnit.SECONDS.sleep(1);
                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();
                userViewController1.setChosenMovie(movie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
    public static void getImageName(String name){
        imageName =name;

    }
    public void savePoster(){
        if (image != null){
            File file = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\images\\"+ imageName);
            try {

                    ImageIO.write(SwingFXUtils.fromFXImage(image, null),"jpg", file);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
