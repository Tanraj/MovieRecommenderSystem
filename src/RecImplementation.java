import java.util.*;
/**
 * Write a description of RecImplementation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RecImplementation implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, filter);
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<String> retVal = new ArrayList<String>();

        for (Rating rating : ratings) {
            movies.add(rating.getItem());
        }
        for (int i = 0; i < 20; i++) {
            retVal.add(movies.get(i));
        }
        return retVal;
    }

    @Override
    /**
     * This method returns nothing, but prints out an HTML table of the
     * movies recommended for the given rater.
     *
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For
     * example, you may want to limit the number printed to only the top
     * 20-50 movies recommended or to movies not rater by the given rater.
     *
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which
     * movies you print, what order you print them in, or what information
     * you include about each movie.
     *
     * @param webRaterID the ID of a new Rater that has been already added to
     *        the RaterDatabase with ratings for the movies returned by the
     *        method getItemsToRate()
     */
    public void printRecommendationsFor(String webRaterID) {
        StringBuilder html = new StringBuilder("<table>");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> allRatings = fr.getSimilarRatings(webRaterID, 20, 5);
        ArrayList<Rating> topTwenty = new ArrayList<Rating>();

        for (int i = 0; i < 20; i++){
            topTwenty.add(allRatings.get(i));
        }

        html.append("<th>" + "" + "</th>" );
        html.append("<th>" + "Poster" + "</th>" );
        html.append("<th>" + "Title" + "</th>" );
        html.append("<th>" + "Year" + "</th>" );
        html.append("<th>" + "Country" + "</th>" );
        html.append("<th>" + "Genre" + "</th>" );
        html.append("<th>" + "Director" + "</th>" );
        html.append("<th>" + "Minutes" + "</th>" );

        int i = 1;
        for(Rating rating : topTwenty) {
            html.append("<tr>");
            html.append("<td>" + i + "</td>");
            html.append("<td>" + "<image src =" + MovieDatabase.getPoster(rating.getItem()) + ">" + "</td>");
            html.append("<td>" + MovieDatabase.getTitle(rating.getItem()) + "</td>");
            html.append("<td>" + MovieDatabase.getYear(rating.getItem()) + "</td>");
            html.append("<td>" + MovieDatabase.getCountry(rating.getItem()) + "</td>");
            html.append("<td>" + MovieDatabase.getGenres(rating.getItem()) + "</td>");
            html.append("<td>" + MovieDatabase.getDirector(rating.getItem()) + "</td>");
            html.append("<td>" + MovieDatabase.getMinutes(rating.getItem()) + "</td>");
            html.append("</tr>");
            i++;
        }

        html.append("</table>");

        System.out.println(html.toString());
    }
}
