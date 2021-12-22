package model;
// Generated Nov 11, 2021 2:40:31 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Movie generated by hbm2java
 */
public class Movie implements java.io.Serializable {

    private Integer idMovie;
    private Filmtype filmtype;
    private String nameMovie;
    private String country;
    private String director;
    private Date timeMovie;
    private String statusMovie;
    private String urlimage;
    private String content;
    private Integer yearOfManufacture;
    private Date dateAdd;
    private Set movieAdmins = new HashSet(0);
    private Set categoryMoives = new HashSet(0);
   
    public Movie() {
    }

    public Movie(Filmtype filmtype, String nameMovie) {
        this.filmtype = filmtype;
        this.nameMovie = nameMovie;
    }

    public Movie(Filmtype filmtype, String nameMovie, String country, String director, Date timeMovie, String statusMovie, String urlimage, String content, Integer yearOfManufacture, Date dateAdd, Set movieAdmins, Set categoryMoives) {
        this.filmtype = filmtype;
        this.nameMovie = nameMovie;
        this.country = country;
        this.director = director;
        this.timeMovie = timeMovie;
        this.statusMovie = statusMovie;
        this.urlimage = urlimage;
        this.content = content;
        this.yearOfManufacture = yearOfManufacture;
        this.dateAdd = dateAdd;
        this.movieAdmins = movieAdmins;
        this.categoryMoives = categoryMoives;
    }
    public Movie(Filmtype filmtype, String nameMovie, String country, String director, Date timeMovie, String statusMovie, String urlimage, String content, Integer yearOfManufacture, Date dateAdd) {
        this.filmtype = filmtype;
        this.nameMovie = nameMovie;
        this.country = country;
        this.director = director;
        this.timeMovie = timeMovie;
        this.statusMovie = statusMovie;
        this.urlimage = urlimage;
        this.content = content;
        this.yearOfManufacture = yearOfManufacture;
        this.dateAdd = dateAdd;
    }

    public Integer getIdMovie() {
        return this.idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public Filmtype getFilmtype() {
        return this.filmtype;
    }

    public void setFilmtype(Filmtype filmtype) {
        this.filmtype = filmtype;
    }

    public String getNameMovie() {
        return this.nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getTimeMovie() {
        return this.timeMovie;
    }

    public void setTimeMovie(Date timeMovie) {
        this.timeMovie = timeMovie;
    }

    public String getStatusMovie() {
        return this.statusMovie;
    }

    public void setStatusMovie(String statusMovie) {
        this.statusMovie = statusMovie;
    }

    public String getUrlimage() {
        return this.urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getYearOfManufacture() {
        return this.yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Date getDateAdd() {
        return this.dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Set getMovieAdmins() {
        return this.movieAdmins;
    }

    public void setMovieAdmins(Set movieAdmins) {
        this.movieAdmins = movieAdmins;
    }

    public Set getCategoryMoives() {
        return this.categoryMoives;
    }

    public void setCategoryMoives(Set categoryMoives) {
        this.categoryMoives = categoryMoives;
    }

    public String getStringCategoryMoives(){
        String stringCategory="";
        for(Object categotyMovie:this.categoryMoives.toArray()){
            stringCategory+=((CategoryMoive)categotyMovie).getCategory().getName()+",";
        }    
        return stringCategory;
    }

    
    

}