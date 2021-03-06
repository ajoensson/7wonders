package sevenWonders.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sevenWonders.backend.Wonder.StageType;

public class Player {
    public final Wonder wonder;
    private List<Card> buildings = new ArrayList<>();
    private int money;
    public final String name;
    
    // -1 means a loss 0 means draw, otherwise victory
    private int leftMilitaryWins[] = { 0, 0, 0 };
    private int rightMilitaryWins[] = { 0, 0, 0 };

    /**
     * Creates a new player with passed attributes
     * 
     * @param name
     *            The player's name
     * @param wonder
     *            The player's wonder
     */
    public Player(String name, Wonder wonder) {
	this.wonder = wonder;
	this.name = name;
    }

    public int getMoney() {
	return money;
    }

    /**
     * Does not have a access modifier because it should only be accessible from
     * this package.
     */
    void addMoney(int m) {
	money += m;
    }

    /**
     * Does not have a access modifier because it should only be accessible from
     * this package.
     */
    void removeMoney(int m) {
	if (m > money)
	    throw new IllegalArgumentException("Not enough funds");
	money -= m;
    }

    public int[][] getMilitaryWins() {
	return new int[][] { leftMilitaryWins, rightMilitaryWins };
    }

    /**
     * Adds the passed card to the player's buildings. Doesn't check resources.
     * 
     * @param building
     *            Does not have a access modifier because it should only be
     *            accessible from this package.
     */
    void buildCard(Card building) {
	buildings.add(building);
    }
    
    /**
     * Returns a list of the cards the player has played
     */
    public List<Card> getBuildings() {
	return new ArrayList<Card>(buildings);
    }

    private static class HashMapp extends HashMap<Resource, Integer> {}
    private static Random r = new Random();
    public static Player randPlayer() {
        Player p = new Player("anders" + r.nextInt(100), WonderFactory.getRandomWonder(true));
        for (int i = r.nextInt(3); i >= 0; i--) {
            p.leftMilitaryWins[i] = 3;
        }
        for (int i = r.nextInt(3); i >= 0; i--) {
            p.rightMilitaryWins[i] = 3;
        }
        p.addMoney(r.nextInt(15));
        
        List<Card> d = Deck.GetDeck(r.nextInt(3)+1, 3);
        for (int i = r.nextInt(14)+6; i > 0; i--) {
            p.buildCard(d.remove(0));
        }
        
        return p;
    }
}
