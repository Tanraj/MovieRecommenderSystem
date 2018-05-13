
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        System.out.println("Number of Movies: " + sr.getMovieSize());
        System.out.println("Number of Raters: " + sr.getRaterSize());
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        Collections.sort(ratings);
        for(Rating rating : ratings){
            System.out.println(sr.getTitle(rating.getItem()) + " has "+ rating.getValue());
        }
    }
    
    public void getAverageRatingOneMovie(){
                SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
                ArrayList<Rating> ratings = sr.getAverageRatings(1);
                for(Rating r : ratings){
                    if(r.getItem().equals("0068646")){
                       System.out.println("The Godfather: " + r.getValue());
                }
            }

    }
   
}
