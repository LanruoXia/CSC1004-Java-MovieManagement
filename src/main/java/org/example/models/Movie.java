package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty genre;
    private IntegerProperty year;
    private StringProperty director;
    private StringProperty country;
    private StringProperty rating;

    private StringProperty poster_path;

    private StringProperty nameWithYear;

    private StringProperty introduction;

    public String getNameWithYear() {
        return nameWithYear.get();
    }

    public StringProperty nameWithYearProperty() {
        return nameWithYear;
    }

    public void setNameWithYear(String nameWithYear) {
        this.nameWithYear.set(nameWithYear);
    }

    public Movie() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.genre = new SimpleStringProperty();
        this.year = new SimpleIntegerProperty();
        this.director = new SimpleStringProperty();
        this.rating = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
        this.poster_path = new SimpleStringProperty();
        this.nameWithYear = new SimpleStringProperty();
        this.introduction = new SimpleStringProperty();
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getPoster_path() {
        return poster_path.get();
    }

    public StringProperty poster_pathProperty() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path.set(poster_path);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getIntroduction() {
        return introduction.get();
    }

    public StringProperty introductionProperty() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getDirector() {
        return director.get();
    }

    public StringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }
}
