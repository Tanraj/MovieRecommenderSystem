/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        for(Movie movie : myMovies){
            String id = movie.getID();
            Double avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                Rating rating = new Rating(id,avg);
                retVal.add(rating);
            }
        }
        return retVal;
    }
    
    public String getTitle(String id){
        for(Movie movie : myMovies){
            if(movie.getID().equals(id)){
                return movie.getTitle();
            }
        }
        return "ID NOT FOUND";
      
    }
    
    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
        
    }
}