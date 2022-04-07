package cardgame;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;


 //In this strategy the player only checks for the same exact card index and for eights


 public class StrategyOne implements PlayerStrategy {
    public ArrayList<Card> playerCards = new ArrayList<>();
    int numDrawCards = 5;
    Card playCard;


    public void init(int playerId, List<Integer> opponentIds) {
        System.out.println("You are player " + playerId);
    }

    public void receiveInitialCards(List<Card> cards) {
        for (int cardIndex = 0; cardIndex < numDrawCards; cardIndex++) {
            playerCards.add(cards.get(cardIndex));
        }
    }
//checks for the "eights"
   
    public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
        for (int cardIndex = 0; cardIndex < playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).getRank().equals(Card.Rank.EIGHT)) {
                playCard = playerCards.get(cardIndex);
                System.out.println(playCard.toString());
                return false;
            }
        }
//checks for the "index"
       for (int cardIndex = 0; cardIndex < playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).equals(topPileCard)) {
                playCard = playerCards.get(cardIndex);
                return false;
            }
        }

        return true;
    }

   //Overing the method 
    public void receiveCard(Card drawnCard) {
        playerCards.add(drawnCard);
    }

   
    public Card playCard() {
       System.out.println(playCard);
        return playCard;
    }

   
    public Card.Suit declareSuit() {
        return null;
    }

 
    public void processOpponentActions(List<PlayerTurn> opponentActions) {
    }

    
    public void reset() {
        playerCards.clear();
    }
}
