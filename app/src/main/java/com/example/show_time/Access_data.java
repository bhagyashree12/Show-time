package com.example.show_time;

public class Access_data {

    //the java class which has setcontentview should be included in manifest file

    String movie_name;
    String movie_image_url;
    String movie_info;
    String movie_rating;
    String movie_genre;
    String movie_trailor;


    //make the constructor
    public Access_data(String movie_name, String movie_image_url, String movie_info,String movie_genre, String movie_rating, String movie_trailor) {
        this.movie_name = movie_name;
        this.movie_image_url = movie_image_url;
        this.movie_genre = movie_genre;
        this.movie_rating = movie_rating;
        this.movie_trailor = movie_trailor;
        this.movie_info=movie_info;
    }

    public String getMovie_info() {
        return movie_info;
    }

    public String getMovie_rating() {
        return movie_rating;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getMovie_trailor() {
        return movie_trailor;
    }

    public String getMovie_name() {

        return movie_name;
    }

    public String getMovie_image_url() {

        return movie_image_url;
    }
}
