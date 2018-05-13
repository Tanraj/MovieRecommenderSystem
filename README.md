# MovieRecommenderSystem
Java implementation of a movie recommender system.
Given a user for whom recommendations are to be created, uses dot-product between the standardized ratings for movies rated by
both said user other movie-reviewers to determine the weightage the reviewer's reviews will have on him/her. 
Multiplies the dot-product with the each reviewer's movie-review to create a weighted-average and sorts the average in descending order.
The first movie in the list is the one with the highest recommendation.
