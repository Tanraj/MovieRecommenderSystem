import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters){
        double numRaters = 0.0;
        for(Rater rater : RaterDatabase.getRaters()){
            if(rater.hasRating(id)){
                numRaters += 1.0;
            }
        }
        int overallRating = 0;
        if(numRaters >= minimalRaters){
            for(Rater rater : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> meMovies = me.getItemsRated();
        ArrayList<String> rMovies = r.getItemsRated();
        double retVal = 0.0;
        ArrayList<String> common = new ArrayList<String>(meMovies);
        common.retainAll(rMovies);
        if(common.size() == 0){
            return -1.0;
        }
        for(String movie : common){
            double meRating = me.getRating(movie) - 5.0;
            double rRating = r.getRating(movie) - 5.0;
            double dotProd = meRating * rRating;
            retVal +=dotProd;
        }
     
        return retVal;
    
    
}
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(r.getID() != me.getID()){
                double closeness = dotProduct(me,r);
                if(closeness > 0.0){
                list.add(new Rating(r.getID(),closeness));
            }
        }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating>getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> retVal = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        
       
        for(String s : movies){
            double sum = 0.0;
            double actualSimilarRaters = 0.0;
            for(int i = 0; i<numSimilarRaters; i++){
                Rater rater = RaterDatabase.getRater(similarRaters.get(i).getItem());
                double rating = rater.getRating(s);
                if(rating > 0.0){
                double weightedAvg = similarRaters.get(i).getValue() * rating;
                int num = rater.numRatings();
                sum+=weightedAvg;
                actualSimilarRaters += 1.0;
            }
            }
            if(actualSimilarRaters >= minimalRaters){
                double avg = sum/actualSimilarRaters;
                Rating r = new Rating(s,avg);
                retVal.add(r);
            }
        }
        Collections.sort(retVal,Collections.reverseOrder());
        return retVal;
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter f){
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       ArrayList<Rating> retVal = new ArrayList<Rating>();
       ArrayList<Rating> similarRaters = getSimilarities(id);
            
        for(String s : movies){
            double sum = 0.0;
            double actualSimilarRaters = 0.0;
            if(f.satisfies(s)){
            for(int i = 0; i<numSimilarRaters; i++){
                Rater rater = RaterDatabase.getRater(similarRaters.get(i).getItem());
                double rating = rater.getRating(s);
                if(rating > 0.0){
                double weightedAvg = similarRaters.get(i).getValue() * rating;
                int num = rater.numRatings();
                sum+=weightedAvg;
                actualSimilarRaters += 1.0;
            }
            }
            if(actualSimilarRaters >= minimalRaters){
                double avg = sum/actualSimilarRaters;
                Rating r = new Rating(s,avg);
                retVal.add(r);
            }
        }
    }
        Collections.sort(retVal,Collections.reverseOrder());
        return retVal;
    }
      
    }

