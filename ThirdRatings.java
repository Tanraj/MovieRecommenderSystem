/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }

    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double numRaters = 0.0;
        for(Rater rater : myRaters){
            if(rater.hasRating(id)){
                numRaters += 1.0;
            }
        }
        int overallRating = 0;
        if(numRaters >= minimalRaters){
            for(Rater rater : myRaters){
            if(rater.hasRating(id)){
                overallRating += rater.getRating(id);
            }
        }
            return overallRating / numRaters;
        }else{
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> retVal = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies){
            Double avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                Rating rating = new Rating(id,avg);
                retVal.add(rating);
            }
        }
        return retVal;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> retVal = new  ArrayList<Rating>();
        ArrayList<String> movieId = MovieDatabase.filterBy(filterCriteria);
        for(String s : movieId){
            Double avg = getAverageByID(s,minimalRaters);
            if(avg != 0.0){
                Rating rating = new Rating(s,avg);
                retVal.add(rating);
            }
        }
        return retVal;
    }
}