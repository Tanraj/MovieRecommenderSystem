import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings){
            System.out.println(rating.getValue() +" " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1,filter);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings){
            System.out.println(rating.getValue() +" " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        Filter filter = new GenreFilter("Crime");
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1,filter);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings){
            System.out.println("With filter, " + MovieDatabase.getTitle(rating.getItem()) + " has "+ rating.getValue());
            System.out.println("Genres: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        Filter filter = new MinutesFilter(110,170);
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1,filter);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings){
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirector(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        Filter filter = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> rtng = sr.getAverageRatingsByFilter(1,filter);
        System.out.println("found " + rtng.size() + " movies");
        Collections.sort(rtng);
        for(Rating rating : rtng){
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1980));
        filter.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> rtng = sr.getAverageRatingsByFilter(1,filter);
        System.out.println(rtng.size() + " movies matched");
        Collections.sort(rtng);
        for(Rating rating : rtng){
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters filter = new AllFilters();
        filter.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        filter.addFilter(new MinutesFilter(30,170));
        ArrayList<Rating> rtng = sr.getAverageRatingsByFilter(1,filter);
        System.out.println(rtng.size() + " movies matched");
        Collections.sort(rtng);
        for(Rating rating : rtng){
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
   
}
