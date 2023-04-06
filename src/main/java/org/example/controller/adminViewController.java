package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.models.Movie;
import org.example.models.User;
import org.example.utils.JdbcUtils;
import org.example.utils.StringUtil;
import org.example.utils.ViewUtils;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;


public class adminViewController implements Initializable {

//    private popUpWindow popUp;
//
//    @FXML
//    private void init(){
//        popUp.injectAdminController(this);
//    }


    @FXML
    private HBox btnAdmin;

    @FXML
    private HBox btnRepo;

    @FXML
    private HBox btnSignOut;

    @FXML
    private HBox btnStats;

    @FXML
    private HBox btnUsers;

    @FXML
    private AnchorPane paneAdmin;

    @FXML
    private AnchorPane paneRepo;

    @FXML
    private AnchorPane paneSignOut;

    @FXML
    private AnchorPane paneStats;

    @FXML
    private AnchorPane paneUsers;


    @FXML
    private Button cancelButton;


// paneUsers
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Integer> ageColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, Integer> passwordColumn;
    @FXML
    private Button test;

//    paneRepo
    //view movies

    @FXML
    private TableView<Movie> repoTableView;
    @FXML
    private TableColumn<Movie, Integer> movieIdColumn;
    @FXML
    private TableColumn<Movie, String> movieNameColumn;


    //add movie
    @FXML
    private Button cancelAdd;

    @FXML
    private Button confirmAdd;

    @FXML
    private TextField nameText;

    @FXML
    private TextField countryText;
    @FXML
    private TextField directorText;

    @FXML
    private ChoiceBox<String> genreChoice;

    private String[] genre = {"Action", "Drama", "Comedy", "Horror", "Sci-Fi", "Romance", "Fantasy", "Western", "Thriller" };

    @FXML
    private TextField yearText;

    @FXML
    private AnchorPane addMoviePane;

    @FXML
    private Button addNew;

    @FXML
    private Button addPoster;

    @FXML
    private ImageView movieImage;

    @FXML
    private GridPane repoGrid;

    private Image image;

    private String fileName;

    @FXML
    private Label addMovieInfo;

    private static String imageName;//final paster image name for saving.
    @FXML
    private VBox repoVBox;

    @FXML
    private Label repoIdLabel, repoMovieLabel, repoGenreLabel, repoYearLabel, repoCoLabel, repoDireLabel, repoRatingLabel;
    @FXML
    private ImageView repoImage;
    @FXML
    private Button btnDeleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagedash = new Image("images/dashframe.png");
        movieImage.setImage(imagedash);
        paneUsers.toFront();
        genreChoice.getItems().addAll(genre);
        //set up the column of userTableView
        repoTableView.getSelectionModel().selectFirst();
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Address"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Password"));
        //load data
        try {
            refreshUserList();
            userTableView.setItems(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //set up the columns of movie repoTableView
        movieIdColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("nameWithYear"));
        //load data
        try {
            refreshRepoList();
            repoTableView.setItems(movies);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //load repoVBOX




    }
    private ObservableList<User> users = FXCollections.observableArrayList();
    public void refreshUserList() throws SQLException {
        ResultSet rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.useraccounts");
        users.clear();

        while (rs.next()){
            User newuser = new User();
            newuser.setId(rs.getInt("iduseraccounts"));
            newuser.setUsername(rs.getString("username"));
            newuser.setAge(rs.getInt("age"));
            newuser.setGender(rs.getString("gender"));
            newuser.setAddress(rs.getString("address"));
            newuser.setPassword(rs.getInt("password"));
            users.add(newuser);
        }

    }
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    public void refreshRepoList() throws SQLException {
        ResultSet rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.movie_repo");
        movies.clear();
//        ResultSetMetaData metaData = rs.getMetaData();
//        try {
//            List<Movie> rawData = jdbcUtils.findMoreProResult(sql, params, Magazine.class);
//            magazinesData.addAll(rawData);
//            magazineTable.setItems(magazinesData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        while (rs.next()){
            Movie newmovie = new Movie();
            System.out.println(rs.getInt("idmovies"));
            newmovie.setId(rs.getInt("idmovies"));
            newmovie.setName(rs.getString("name"));
            newmovie.setPoster_path(rs.getString("poster_path"));
            newmovie.setYear(Integer.parseInt(rs.getString("year")));
            newmovie.setNameWithYear(rs.getString("name") + " (" + rs.getString("year") + ")");
            movies.add(newmovie);
        }
        repoTableView.setItems(movies);

    }

    public void deleteMovie(){
        Movie selected = repoTableView.getSelectionModel().getSelectedItem();

    }

    public void loadRepoVBox(){
    }
    @FXML
    public void getItem(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("selected");
        Movie selectedMovie = repoTableView.getSelectionModel().getSelectedItem();
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        if(selectedMovie != null){
            try {
                System.out.println(selectedMovie.getName());
                String movie = selectedMovie.getName();
                int year = selectedMovie.getYear();
                String yearString = Integer.toString(year);
                ResultSet rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.movie_repo WHERE name = '"
                        +movie+ "' and year = '" + yearString+ "'");
//                ResultSet rs = JdbcUtils.getQueryResult("select * from movieadmin.movie_repo");
                Statement statement = dbConn.createStatement();
                rs.next();
                Image image = new Image(rs.getString("poster_path"));
                repoImage.setImage(image);
                repoMovieLabel.setText(rs.getString("name"));
                repoIdLabel.setText(rs.getString("idmovies"));
                repoGenreLabel.setText(rs.getString("genre"));
                repoYearLabel.setText(rs.getString("year"));
                repoCoLabel.setText(rs.getString("country"));
                repoDireLabel.setText(rs.getString("director"));
//                repoRatingLabel
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }




    public void testCode() throws SQLException {
        ResultSet rs = null;
        try {
            rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.useraccounts");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(111);
        while (rs.next()){
            User newuser = new User();
            newuser.setId(rs.getInt("iduseraccounts"));
            System.out.println("Hi");
            System.out.println(newuser.getId());
        }
    }


//    @FXML
//    public void handleClicks(javafx.event.ActionEvent event) {
//        if (event.getSource() == btnAdmin) {
//
//        } else if (event.getSource() == btnUsers) {
//
//
//        } else if (event.getSource() == btnRepo) {
//
//        } else if (event.getSource() == btnStats) {
//
//        }else if (event.getSource() == btnStats) {
//
//        }else if (event.getSource() == btnSignOut) {
//            System.out.println("hi");
//            ViewUtils.openView("view/SignOut.fxml");
//
//
//        }
//    }
    public void closeStage(){
        Stage stage = (Stage) btnSignOut.getScene().getWindow();
        stage.close();
    }

    public void clickUser(){paneUsers.toFront();
        System.out.println(imageName);
    }


    public void clickRepo(){
        paneRepo.toFront();
        repoGrid.toFront();

    }
    //Handle movie repository
    public void ClickAddNew(){
        addMoviePane.toFront();
    }

    public void addNewPoster(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open and save an image file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"),
                new FileChooser.ExtensionFilter("PNG image", "*.png"), new FileChooser.ExtensionFilter("All images", "*.jpg", "*.png"));
        File imageFile = fileChooser.showOpenDialog(btnAdmin.getScene().getWindow());
        if (imageFile != null) {
            // openFile(file);
            movieImage.setImage(null);
            image = new Image(imageFile.getPath());
            movieImage.setImage(image);
            fileName = imageFile.getName();
            //savePoster(fileName);
            System.out.println(System.getProperty("user.dir"));
            popUpWindow.getOriginalName(fileName);
            ViewUtils.openView("view/renamePoster.fxml");
        }


    }
    public static void getImageName(String name){
        imageName =name;

    }

    public void savePoster(){
        if (image != null){
            File file = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\images\\"+ imageName);
            try {
                if(imageName.contains(".jpg")){
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null),"jpg", file);
                }else if(imageName.contains(".png")){
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null),"png", file);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void setNewMovie() {
        String  name= nameText.getText();
        String genre = genreChoice.getValue();
        String year = yearText.getText();
        String country = countryText.getText();
        String director = directorText.getText();
        String imagePath = "images/" + imageName;
        String insertNewMovie = "INSERT INTO movieadmin.movie_repo (" + "movie, genre, release_year, country, director, poster_path) " +
                "VALUES ('" + name + "','" + genre + "','" + year + "','" + country + "','" + director + "','" + imagePath + "')";
        try {
            if(!StringUtil.isEmpty(name) && !StringUtil.isEmpty(genre) &&!StringUtil.isEmpty(year) &&
                    !StringUtil.isEmpty(country) && !StringUtil.isEmpty(director) && !StringUtil.isEmpty(imagePath)){
                JdbcUtils jdbcUtils = new JdbcUtils();
                jdbcUtils.executeQueryStmt(insertNewMovie);
                savePoster();
                addMovieInfo.setText("Adding succeeded!");
                addMovieInfo.setVisible(true);
            }else{
                addMovieInfo.setText("Please fill in all the information and upload a poster image!");
                addMovieInfo.setVisible(true);

            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    public void handleCancelAdd(){
        repoGrid.toFront();
    }


    public void clickSignOut(){
        System.out.println("hi");
        ViewUtils.openView("view/SignOut.fxml");
        popUpWindow.getAdmin((Stage) btnAdmin.getScene().getWindow());
    }

}




