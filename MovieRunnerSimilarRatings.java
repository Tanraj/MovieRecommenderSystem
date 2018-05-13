import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings){
            System.out.println(rating.getValue() +" " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
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
    
    public void printSimilarRatings(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> similar = sr.getSimilarRatings("65",20,5);
        System.out.println("Top Rated Movie: " + MovieDatabase.getTitle(similar.get(0).getItem())); 
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        Filter filter = new GenreFilter("Action");
        ArrayList<Rating> similar = sr.getSimilarRatingsByFilter("65",20,5,filter);
        for(Rating r : similar){
        System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        System.out.println("\t " + MovieDatabase.getGenres(r.getItem()));
    }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        Filter filter = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        ArrayList<Rating> similar = sr.getSimilarRatingsByFilter("1034",10,3,filter);
        for(Rating r : similar){
        System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        System.out.println("\t " + MovieDatabase.getDirector(r.getItem()));
    }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Adventure"));
        filter.addFilter(new MinutesFilter(100,200));
        ArrayList<Rating> similar = sr.getSimilarRatingsByFilter("65",10,5,filter);
        for(Rating r : similar){
        System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getMinutes(r.getItem()) + " " + r.getValue());
        System.out.println("\t " + MovieDatabase.getGenres(r.getItem()));
    }
    }
    
    public void printSimilarRatingsByYearAndMinutes(){
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(2000));
        filter.addFilter(new MinutesFilter(80,100));
        ArrayList<Rating> similar = sr.getSimilarRatingsByFilter("65",10,5,filter);
        for(Rating r : similar){
        System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getMinutes(r.getItem()));
    }
    }
}
