# MovieRecommenderSystem
Java implementation of a movie recommender system as a Capstone Project from Duke University's 5-course specialization "Java Programming and Software Engineering Fundamentals Specialization" (citation below).  

Given a user for whom recommendations are to be created, adds dot-products between the standardized ratings for movies rated by both said user and other movie-reviewers to determine the weightage each reviewer's reviews will have on the user. 
Multiplies the dot-product with the each reviewer's movie-review to create a weighted-rating and for a particular movie, takes the average weighted ratings for a set amount of reviewers. Sorting these movies in descending order of weighted average results in the list of movie recommendations for the user.
The first movie in the list is the one with the highest recommendation for the user.

The "src" folder has the java implementation of the project.

Citation:
“Java Programming and Software Engineering Fundamentals.” Coursera, Michigan State University, www.coursera.org/specializations/java-programming#about.
