# The Plan to Make Concordia AI with No Training Data

By creating several neural networks to represent the 
'players' playing the game, we can create a sort of 
'population' centered around evolution in which only
the best players survive. If we initially start with 4
neural network 'players' that have randomly initialized
weights and biases since there is no dataset to train
the neural networks with, we can have them play against 
each other and take the best 2 players. Then we can
make two more neural networks using the weights and biases
of the best 2 players and take the 4 current players and
have them play against each other again. We can repeat this
process several thousand times to produce a dataset
of sufficient size to train a usable neural network to 
play the game with some kind of skill. 

The training data will be represented by the GameState 
class and will be flattened into a single vector so that
it can be used to train the neural networks. This approach 
will require a simple version of the game to be implemented
such that we can offer choices to the neural networks through
some sort of custom API that we will have to create.

Note that the game implementation in this package is completely
separate from the main game implementation. The game implementation
in this package is only used to generate training data for the
neural networks and thus will be extremely simply. 

## New Plan

It seems that my old idea is incredibly difficult and I do not have 
enough time to figure out how to do that. So the new plan is to do this: Make a bunch of fake training data by providing the inputs and generating
a lot of fake outputs using rng and then train the networks for each player and 
having each player play the game. If after so many games a player is not reaching
a certain average victory point quota then it will be killed off. If I have models
that are reaching the quota then I will take the two best ones and make new training
data based off of their training data (taking more of the better model) and repeating
the process until I have a good model.