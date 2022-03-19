package war;

/**
 * Represents a single player in the game.
 *
 * @author Ryan Nowak
 */
public class Player {

    private Pile pile;
    private boolean winner;

    /**
     * Creates a player with their id. Their pile is named based on their id.
     * Initially they are not the winner of the game.
     *
     * @param id the player's id number
     */
    public Player(int id) {
        pile = new Pile('P' + String.valueOf(id));
        winner = false;
    }

    /**
     * Add a card to the bottom of the player's pile.
     *
     * @param card card to add
     */
    public void addCard(Card card) {
        pile.addCard(card);
    }

    /**
     * Add a collection of cards to the bottom of the player's pile.
     *
     * @param cards collection of cards to add
     */
    public void addCards(Pile cards) {
        for (Card card: cards.getCards()) {
            pile.addCard(card);
        }
    }

    /**
     * Removes and returns the card on top of the pile.
     *
     * @return card on top of pile
     */
    public Card drawCard() {
        return pile.drawCard(true);
    }

    /**
     * Checks if there is at least one card in pile.
     *
     * @return whether or not a card is in the pile
     */
    public boolean hasCard() {
        return pile.hasCard();
    }

    /**
     * Checks if this player is the winner.
     *
     * @return whether or not the player is a winner
     */
    public boolean isWinner() {
        return winner;
    }

    /** Makes player a winner. */
    public void setWinner() {
        winner = true;
    }

    /**
     * Returns a string of the pile's name and every card within the pile.
     * The specific format is "{name} pile: first-card second-card ..."
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        return pile.toString();
    }

}
