package cardgame;
// This Game Class  is the Game engine

public class Game {
	public static void main(String[] args) {
        Game objGame = new Game();  // creating object to access the method in other class
        objGame.runTournament();     
        }
/**runs one tournament and plays multiple games until tournament has ended
creating the object and accessing the method in other class**/ 
	
     public void runTournament() {
    	Operations operation = new Operations();
    	operation.initializeTournament();
    	operation.addPlayersToPlayerList();
    	operation.addPlayerCardsToAllPlayerCardsList();
    	operation.addPlayerPointsToList();

        while (!operation.isTournamentOver()) {
        	operation.playGame();
        } 
}
}
