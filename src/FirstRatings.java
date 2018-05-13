
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> retVal = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec : fr.getCSVParser()){
            String id = rec.get(0);
            String title = rec.get(1);
            String year = rec.get(2);
            String country = rec.get(3);
            String genres = rec.get(4);
            String director = rec.get(5);
            int minutes = Integer.parseInt(rec.get(6));
            String poster = rec.get(7);
            Movie input = new Movie(id,title,year,genres,director,country,poster,minutes);
            retVal.add(input);
        }
        return retVal;
    }

    public void testLoadMovies(){
        ArrayList<Movie> local = loadMovies("data/ratedmovies_short.csv");
        
        for(Movie movie: local){
            System.out.println(movie);
        }
        System.out.println("Number of movies is " + local.size());
    
        //how many movies include Comedy genre
        int numComedies = 0;
        for(Movie movie : local){
            String genre = movie.getGenres();
            
        }
        //how many movies are greater than 150 minutes in length
        int exceedMinutes = 0;
        for(Movie movie : local){
             if(movie.getMinutes() > 150){
                 exceedMinutes += 1;
             }
        }
        System.out.println("Number of movies greater than 150 minutes: " + exceedMinutes);            
        
}
    
    
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> retVal = new ArrayList<>();
        HashMap<String,ArrayList<Rating>> map = new HashMap<String,ArrayList<Rating>>();
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec : fr.getCSVParser()){
            String myId = rec.get(0);
            Rater rater = new EfficientRater(myId);
            //System.out.println("NEW ID: " + myId);
            if(map.containsKey(myId)){
                           // System.out.println("MADE IT");
            map.get(myId).add(new Rating(rec.get(1),Double.parseDouble(rec.get(2))));
        }
        else{
            ArrayList<Rating> a = new ArrayList<Rating>();
            a.add(new Rating(rec.get(1),Double.parseDouble(rec.get(2))));
            map.put(myId,a);
        }
        }
        
        for(String ID : map.keySet()){
                            Rater rater = new EfficientRater(ID);
            for(Rating rating: map.get(ID)){
            rater.addRating(rating.getItem(), rating.getValue());
                  
        }
        retVal.add(rater);
        }
        return retVal;
   
    }
   
    public void testLoadRaters(){
            ArrayList<Rater> local = loadRaters("data/ratings_short.csv");
            System.out.println("Total Number of Raters: " + local.size()); 
            // for(Rater rater: local){
            // System.out.print("Rater's ID: " +rater.getID() + " " );
            // System.out.println("Number of Ratings: " +rater.numRatings());
    
            // ArrayList<String> items = rater.getItemsRated();
            // for(String s : items){
                            // System.out.print("Movie ID: " + s + " ");
                            // System.out.println("Movie's rating: " +rater.getRating(s));
            // }
          
            
        // }
        
        //Number of ratings for a particular rater; rater 2 has 3 ratings
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(Rater rater : local){
            int ratings = rater.numRatings();
            map.put(Integer.parseInt(rater.getID()),ratings);
        }
        System.out.println("Number of ratings for a particular rater; rater 2 has 3 ratings:" 
        + map.get(2));
        
        //Maximum number of ratings by any rater
        int maxNoRatings = -1;
        for (Integer i : map.keySet()){
            if(map.get(i) > maxNoRatings){
            maxNoRatings = map.get(i);
        }
        }
        
        ArrayList<Integer> ratersWithMaxRatings = new ArrayList<Integer>();
        for (Integer i : map.keySet()){
            if(map.get(i).equals(maxNoRatings)){
                ratersWithMaxRatings.add(i);
            }
        }
        
        //for ratings_short.csv, should only be one rater with 3 ratings
        for(Integer i : ratersWithMaxRatings){
        System.out.println("Rater " + i + " has " + map.get(i) + " ratings");
        }
        
        
        //number of ratings a particular movie has
        String movieID = "1798709";
        int numRatingsMovie = 0;
        for(Rater rater : local){
            if (rater.hasRating(movieID)){
            numRatingsMovie += 1;
        }
        }
        System.out.println("Number of ratings a particular movie has: " + numRatingsMovie);
        
        //how many different movies have been rated
        ArrayList<String> al = new ArrayList<String>();
        int numMoviesRated = 0;
        for(Rater rater : local){
            for(String s : rater.getItemsRated()){
            if(!al.contains(s)){
            numMoviesRated +=1;
            al.add(s);
            }
            }
        }
        System.out.println("Number of different movies: " + numMoviesRated);
    }
}
