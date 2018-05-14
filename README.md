# MovieRecommenderSystem
Java implementation of Duke University's Capstone movie recommender system. 

Given a user for whom recommendations are to be created, uses dot-product between the standardized ratings for movies rated by
both said user and other movie-reviewers to determine the weightage each reviewer's reviews will have on the user. 
Multiplies the dot-product with the each reviewer's movie-review to create a weighted-rating and sorts these ratings in descending order.
The first movie in the list is the one with the highest recommendation.

The "src" folder has the java implementation of the project.
