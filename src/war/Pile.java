package war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a single pile of cards in the game.
 *
 * @author Ryan Nowak
 */
public class Pile {

    private ArrayList<Card> cards;
    private String name;
    private static Random rng = new Random();

    /**
     * Creates a pile with the given name. Initially there are no cards in the ArrayList.
     *
     * @param name the pile's name
     */
    public Pile(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    /**
     * Add a card to the bottom of the pile.
     *
     * @param card card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /** Removes all cards from the pile */
    public void clear() {
        cards.clear();
    }

    /**
     * Gets the next card from the top of the pile. If the card is face up, the pile is shuffled.
     * Then return the card on top of the pile.
     *
     * @param faceUp tells whether the card should be set face up when drawn
     * @return card on top of the pile
     */
    public Card drawCard(boolean faceUp) {
        if (cards.get(0).isFaceUp()) {
            shuffle();
            System.out.print(name + " pile: ");
            for (Card card: cards) {
                System.out.print(card.toString() + " ");
            }
            System.out.println();
        }
        Card card = cards.remove(0);
        if (faceUp) {
            card.setFaceUp();
        }
        return card;
    }

    /**
     * Gets the list of cards
     *
     * @return ArrayList of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Checks if there is at least one card in pile. If not, returns false.
     *
     * @return whether or not a card is in the pile
     */
    public boolean hasCard() {
        return !cards.isEmpty();
    }

    /**
     * Sets the seed for the random number generator.
     *
     * @param seed value of seed to give to random number generator
     */
    public static void setSeed(long seed) {
        rng.setSeed(seed);
    }

    /** Shuffles the cards and sets them to be face down. */
    public void shuffle() {
        System.out.println("Shuffling " + name + " pile");
        Collections.shuffle(cards, rng);
        for (Card card: cards) {
            card.setFaceDown();
        }
    }

    /**
     * Returns a string of the pile's name and every card within the pile.
     * The specific format is "{name} pile: first-card second-card ..."
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        String strPile = name + " pile: ";
        for (Card card: cards) {
            strPile += card.toString() + " ";
        }
        return strPile;
    }

}
