package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {
//Assigning player 1,2,with strategy one and player 3,4 with strategy two
	   PlayerStrategy playerOne = new StrategyOne();
	    PlayerStrategy playerTwo = new StrategyOne();
	    PlayerStrategy playerThree = new StrategyTwo();
	    PlayerStrategy playerFour = new StrategyTwo();

	    List<PlayerStrategy> players = new ArrayList<>();
	    
//Initialize the player's id
	    
	    public int playerOneID = 0;
	    int playerTwoID = 1;
	    int playerThreeID = 2;
	    int playerFourID = 3;
//Initialize the player's points
	    
	    int playerOnePoints = 0;
	    int playerTwoPoints = 0;
	    int playerThreePoints = 0;
	    int playerFourPoints = 0;

	    ArrayList<Integer> playerPoints = new ArrayList<>();
	    int winningPoints = 200;
//creating  Arraylist for player's cards
	    
	    List<Card> cardDeck = Card.getDeck();
	    ArrayList<Card> playerOneCards = new ArrayList<>();
	    ArrayList<Card> playerTwoCards = new ArrayList<>();
	    ArrayList<Card> playerThreeCards = new ArrayList<>();
	    ArrayList<Card> playerFourCards = new ArrayList<>();
	    ArrayList<ArrayList<Card>> allPlayerCards = new ArrayList<>();
//runs as long as the tournament is not over if a player meets or exceeds 200 points

    int cardDeckPosition;
    public void playGame() {
         initializeGame();

        cardDeckPosition = cardDeck.size() - 1;
        Card topCard = cardDeck.get(cardDeckPosition);
        cardDeck.remove(cardDeckPosition);
        cardDeckPosition = cardDeck.size() - 1;

// plays multiple rounds until cardDeck is empty or if players cards are empty
        
        while (true) {
            for (int playerId = playerOneID; playerId <= playerFourID; playerId++) {
                if (players.get(playerId).shouldDrawCard (topCard, players.get(playerId).declareSuit())) {
                    drawCard(playerId);
                } else {
                    Card cardPlayed = players.get(playerId).playCard();
                    allPlayerCards.get(playerId).remove(cardPlayed);
                    topCard = cardPlayed;
                }
                if (isPlayerCardsEmpty()) {
                	
                    break;
                }
                if (cardDeck.isEmpty()) {
                    break;
                }
            }
        }
    }
//playerId identifies which player draws card 
    
    public void drawCard(int playerId) {
    	 if (cardDeck.size() == 0) {
             return;
         }
         players.get(playerId).receiveCard(cardDeck.get(cardDeckPosition));
         allPlayerCards.get(playerId).add(cardDeck.get(cardDeckPosition));
         cardDeck.remove(cardDeckPosition);
         cardDeckPosition = cardDeck.size() - 1;   	
    }

//checks if each player's cards are empty after each player turn
    
      public boolean isPlayerCardsEmpty() {
        for (int i = 0; i < allPlayerCards.size(); i++) {
           if (allPlayerCards.get(i).isEmpty()) {
               return true;
            }
        }
        return true;
    }

//sets player Id for each player and lists its opponents
   
    public void initializeTournament() {
        ArrayList<Integer> opponentIDPlayerOne = new ArrayList<>();
        opponentIDPlayerOne.add(playerTwoID);
        opponentIDPlayerOne.add(playerThreeID);
        opponentIDPlayerOne.add(playerFourID);

        ArrayList<Integer> opponentIDPlayerTwo = new ArrayList<>();
        opponentIDPlayerTwo.add(playerOneID);
        opponentIDPlayerTwo.add(playerThreeID);
        opponentIDPlayerTwo.add(playerFourID);

        ArrayList<Integer> opponentIDPlayerThree = new ArrayList<>();
        opponentIDPlayerThree.add(playerOneID);
        opponentIDPlayerThree.add(playerTwoID);
        opponentIDPlayerThree.add(playerFourID);

        ArrayList<Integer> opponentIDPlayerFour = new ArrayList<>();
        opponentIDPlayerFour.add(playerOneID);
        opponentIDPlayerFour.add(playerTwoID);
        opponentIDPlayerFour.add(playerThreeID);

        playerOne.init(playerOneID, opponentIDPlayerOne);
        playerTwo.init(playerTwoID, opponentIDPlayerTwo);
        playerThree.init(playerThreeID, opponentIDPlayerThree);
        playerFour.init(playerFourID, opponentIDPlayerFour);
    }

   
// resets state of each players cards by shuffling deck of cards
    
      public void initializeGame() {
        cardDeck = Card.getDeck();
        Collections.shuffle(cardDeck);
        int numPlayerCards = 5;

        for (int index = 0; index < players.size(); index++) {
            players.get(index).reset();
            players.get(index).receiveInitialCards(cardDeck);
           
        }
        
//clearing the cards for all players
        
        playerOneCards.clear();
        playerTwoCards.clear();
        playerThreeCards.clear();
        playerFourCards.clear();
        
//adding cards to all players for upcoming round
        
        for (int cardIndex = 0; cardIndex < numPlayerCards; cardIndex++) {
            playerOneCards.add(cardDeck.get(cardIndex));
            playerTwoCards.add(cardDeck.get(cardIndex));
            playerThreeCards.add(cardDeck.get(cardIndex));
            playerFourCards.add(cardDeck.get(cardIndex));
      
        }
    }
//adds each of the player points to a list
     
    public void addPlayerPointsToList() {
        playerPoints.add(playerOnePoints);
        playerPoints.add(playerTwoPoints);
        playerPoints.add(playerThreePoints);
        playerPoints.add(playerFourPoints);
    }
//adds players to a List of all the players
    
    public void addPlayersToPlayerList() {
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);
    }
  //adds each player's cards to a list
    
    public void addPlayerCardsToAllPlayerCardsList() {
        allPlayerCards.add(playerOneCards);
        allPlayerCards.add(playerTwoCards);
        allPlayerCards.add(playerThreeCards);
        allPlayerCards.add(playerFourCards);
        
    }

//sums all player's points at the end of each game
    
    public boolean isTournamentOver() {
        for (int playerID = 0; playerID < allPlayerCards.size(); playerID++) {
            for (int i = 0;i < allPlayerCards.get(playerID).size(); i ++) {
                int cardPoint =  allPlayerCards.get(playerID).get(i).getPointValue();
                if (playerID == 0) {
                    playerTwoPoints += cardPoint;
                    playerThreePoints += cardPoint;
                    playerFourPoints +=  cardPoint;
                    
                } else if (playerID == 1) {
                    playerOnePoints += cardPoint;
                    playerThreePoints += cardPoint;
                    playerFourPoints += cardPoint;
                    
                } else if (playerID == 2) {
                    playerOnePoints += cardPoint;
                    playerTwoPoints += cardPoint;
                    playerFourPoints += cardPoint;
                    
                } else if (playerID == 3) {
                    playerOnePoints += cardPoint;
                    playerTwoPoints += cardPoint;
                    playerThreePoints += cardPoint;
                    
                }
             
                //checks any player has more than 200 points 
                if (playerPoints.get(playerID) >= winningPoints) {
                	System.out.println("player"+playerID+"has won!!!!...");
                	return true;
                }
            }

        }
        //continues next round of the tournament
        return false;
    }  

}

