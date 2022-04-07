package cardgame;

import java.util.ArrayList;
import java.util.List;


 //In this strategy the player  checks for either the same suit or rank
 

public class StrategyTwo implements PlayerStrategy {

    ArrayList<Card> playerCards = new ArrayList<>();
    int numDrawCards = 5;
    Card playCard;

    public void init(int playerId, List<Integer> opponentIds) {
        System.out.println("You are player " + playerId);
    }

    public void receiveInitialCards(List<Card> cards) {
        playerCards.clear();
        for (int cardIndex = 0; cardIndex < numDrawCards; cardIndex++) {
            playerCards.add(cards.get(cardIndex));
        }
    }

    public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
        for (int cardIndex = 0; cardIndex < playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).getRank().equals(topPileCard.getRank()) ||
                playerCards.get(cardIndex).getSuit().equals(topPileCard.getSuit())) {
                playCard = playerCards.get(cardIndex);
                System.out.println(playCard.toString());
                return false;
            }
        }
        return true;
    }


    public void receiveCard(Card drawnCard) {
        playerCards.add(drawnCard);
    }

   
    public Card playCard() {
        return playCard;
    }

  
    public Card.Suit declareSuit() {
        return playerCards.get(0).getSuit();
    }

    public void processOpponentActions(List<PlayerTurn> opponentActions) {

    }

    public void reset() {
        playerCards.clear();
    }
}