package org.example.controller;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import org.example.models.ModelList;
import org.example.models.Movie;
import org.example.models.User;
import org.example.utils.JdbcUtils;
import org.example.utils.StringUtil;
import org.example.utils.ViewUtils;

import javax.imageio.ImageIO;
import javax.management.modelmbean.ModelMBean;
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

    @FXML
    private TextArea introText;

    @FXML
    private TextArea repoIntroText;
    @FXML
    private ScrollPane statsScrollPane;

    @FXML
    private PieChart agePieChart;
    @FXML
    private BarChart<?, ?> ratingBarChart;
    @FXML
    private PieChart TypePieChart;

    private ModelList modelList = new ModelList();
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
        //load movie table
        refreshMovieTable();
        //load plots in statistics page
        loadAgePieChart();
        loadRatingBarChart();
        loadMovieType();

    }
    /**
     * initialize or refresh the movie table view
     */
    public void refreshMovieTable(){
        //set up the columns of movie repoTableView
        movieIdColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("nameWithYear"));
        //load data
        modelList.loadMovies();
        movies = modelList.getMovieModelList();
        repoTableView.setItems(movies);
        repoTableView.getSelectionModel().selectFirst();
    }
    //create lists that contains all objects of movies and users in database
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<User> users = FXCollections.observableArrayList();
    /**
     * load all users, set them into objects, and add them into observablelist;
     */
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
//    private ObservableList<Movie> movies = FXCollections.observableArrayList();
//    public void refreshRepoList() throws SQLException {
//        ResultSet rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.movie_repo");
//        movies.clear();
////        ResultSetMetaData metaData = rs.getMetaData();
////        try {
////            List<Movie> rawData = jdbcUtils.findMoreProResult(sql, params, Magazine.class);
////            magazinesData.addAll(rawData);
////            magazineTable.setItems(magazinesData);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        while (rs.next()){
//            Movie newmovie = new Movie();
//            System.out.println(rs.getInt("idmovies"));
//            newmovie.setId(rs.getInt("idmovies"));
//            newmovie.setName(rs.getString("name"));
//            newmovie.setPoster_path(rs.getString("poster_path"));
//            newmovie.setYear(Integer.parseInt(rs.getString("year")));
//            newmovie.setNameWithYear(rs.getString("name") + " (" + rs.getString("year") + ")");
//            movies.add(newmovie);
//        }
//        repoTableView.setItems(movies);
//
//    }

    @FXML
    public void deleteMovie(){
        Movie selected = repoTableView.getSelectionModel().getSelectedItem();
        modelList.delete(selected);
        String name = selected.getName();
        String year = Integer.toString(selected.getYear());
        JdbcUtils jdbcUtils = new JdbcUtils();
        String delete = "DELETE FROM movieadmin.movie_repo WHERE name = \"" + name + "\" and year =\"" + year +"\"";
        try {
            jdbcUtils.executeQueryStmt(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadRepoVBox(){
    }
    /**
     * get the selected movie in movie table view and show information of the selected movie
     */
    @FXML
    public void getItem(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("selected");
        Movie selectedMovie = repoTableView.getSelectionModel().getSelectedItem();
//        repoTableView.getSelectionModel().selectFirst();
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        if(selectedMovie != null){
            try {
                System.out.println(selectedMovie.getName());
                String movie = selectedMovie.getName();
                int year = selectedMovie.getYear();
                String yearString = Integer.toString(year);
                ResultSet rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.movie_repo WHERE movie = '"
                        +movie+ "' and year = '" + yearString+ "'");
//                ResultSet rs = JdbcUtils.getQueryResult("select * from movieadmin.movie_repo");
                Statement statement = dbConn.createStatement();
                rs.next();
                Image image = new Image(rs.getString("poster_path"));
                repoImage.setImage(image);
                repoMovieLabel.setText(rs.getString("movie"));
                repoIdLabel.setText(rs.getString("idmovies"));
                repoGenreLabel.setText(rs.getString("genre"));
                repoYearLabel.setText(rs.getString("year"));
                repoCoLabel.setText(rs.getString("country"));
                repoDireLabel.setText(rs.getString("director"));
                repoIntroText.setText(rs.getString("introduction"));
                repoIntroText.setPrefRowCount(10);
                repoIntroText.setPrefColumnCount(100);
                repoIntroText.setWrapText(true);
                repoIntroText.setPrefWidth(140);
                repoIntroText.setEditable(false);
                rs.getString("rating");
                if(!rs.wasNull()){
                    repoRatingLabel.setText(rs.getString("rating"));
                }else{
                    repoRatingLabel.setText("No rating yet");
                }
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
        String intro = introText.getText();
        String insertNewMovie = "INSERT INTO movieadmin.movie_repo (movie, genre, year, country, director, poster_path, introduction) VALUES (\"" + name + "\",\"" + genre + "\",\"" + year + "\",\"" + country + "\",\"" + director + "\",\"" + imagePath + "\",\"" + intro + "\")";
        try {
            if(!StringUtil.isEmpty(name) && !StringUtil.isEmpty(genre) &&!StringUtil.isEmpty(year) &&
                    !StringUtil.isEmpty(country) && !StringUtil.isEmpty(director) && !StringUtil.isEmpty(imagePath) && !StringUtil.isEmpty(intro)){
                JdbcUtils jdbcUtils = new JdbcUtils();
                jdbcUtils.executeQueryStmt(insertNewMovie);
                savePoster();
                refreshMovieTable();
                addMovieInfo.setText("Adding succeeded!");
                addMovieInfo.setVisible(true);
                nameText.clear();
                genreChoice.setValue(null);
                yearText.clear();
                countryText.clear();
                directorText.clear();
                introText.clear();
                movieImage.setImage(null);


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
    public void clickPaneStats(){
        paneStats.toFront();
    }

    public void loadAgePieChart(){
        int group1 = 0;
        int group2 = 0;
        int group3 = 0;
        int group4 = 0;
        int group5 = 0;
        int group6 = 0;
        int group7 = 0;
        String getAge = "SELECT age FROM movieadmin.useraccounts";
        try {
            ResultSet age_rs = JdbcUtils.getQueryResult(getAge);
            while (age_rs.next()){
                int age = Integer.parseInt(age_rs.getString(1));
                if(age < 10){
                    group1++;
                }else if(age < 20){
                    group2++;
                }else if(age < 30){
                    group3++;
                }else if(age < 40){
                    group4++;
                }else if(age < 50){
                    group5++;
                }else if(age < 60){
                    group6++;
                }else {
                    group7++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<PieChart.Data> agePieChartData = FXCollections.observableArrayList(
                new PieChart.Data("0-10", group1),
                new PieChart.Data("10-20", group2),
                new PieChart.Data("20-30", group3),
                new PieChart.Data("30-40", group4),
                new PieChart.Data("40-50", group5),
                new PieChart.Data("50-60", group6),
                new PieChart.Data("60 and above", group7));

        agePieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                "age:", data.getName()," ", data.pieValueProperty()
                        )
                )
        );
        agePieChart.getData().addAll(agePieChartData);
        agePieChart.setTitle("User Age Distribution");
    }

    public void loadRatingBarChart(){
        modelList.loadMovies();
        ObservableList<Movie> movies = modelList.getMovieModelList();
        XYChart.Series ratingSeries = new XYChart.Series();
        for(int i = 0; i < movies.size(); i++){
            String rating = movies.get(i).getRating();
            String movieName = movies.get(i).getNameWithYear();
            double ratingNum = 0.0;
            if(!rating.equals("No rating yet")){
                ratingNum = Double.parseDouble(rating);
            }

            ratingSeries.getData().add(new XYChart.Data(ratingNum, movieName));;

        }
        ratingBarChart.getData().addAll(ratingSeries);

    }
    public void loadMovieType(){
        ObservableList<PieChart.Data> genrePieChartData = FXCollections.observableArrayList();
        String countGenre = "SELECT genre, COUNT(*) FROM movie_repo GROUP BY genre";
        try {
            ResultSet genre_rs = JdbcUtils.getQueryResult(countGenre);
            while(genre_rs.next()){
                PieChart.Data data = new PieChart.Data(genre_rs.getString(1), genre_rs.getInt(2));
                genrePieChartData.add(data);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TypePieChart.getData().addAll(genrePieChartData);
        TypePieChart.setTitle("Genre Distribution");



//        agenrePieChartData.forEach(data ->
//                data.nameProperty().bind(
//                        Bindings.concat(
//                                "age:", data.getName()," ", data.pieValueProperty()
//                        )
//                )
//        );

    }


}

//"Action", "Drama", "Comedy", "Horror", "Sci-Fi", "Romance", "Fantasy", "Western", "Thriller"


