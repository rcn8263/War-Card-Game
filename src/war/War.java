package src.war;

import war.*;

/**
 * The main program for the card game, War.  It is run on the command line as:<br>
 * <br>
 * java War cards-per-player seed<br>
 * <br>
 *
 * @author RIT CS
 * @author Ryan Nowak
 */

public class War {
    /** The maximum number of cards a single player can have */
    public final static int MAX_CARDS_PER_PLAYER = 26;

    private final Player p1;
    private final Player p2;
    private int round;

    /**
     * Initialize the game.
     *
     * @param cardsPerPlayer the number of cards for a single player
     */
    public War(int cardsPerPlayer) {
        Pile initPile = new Pile("Initial");
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                initPile.addCard(new Card(rank, suit));
            }
        }

        initPile.shuffle();
        System.out.println(initPile.toString());

        p1 = new Player(1);
        p2 = new Player(2);

        boolean alternate = true;
        for (int i = 0; i < 2*cardsPerPlayer; i++) {
            if (alternate) {
                p1.addCard(initPile.drawCard(false));
                alternate = false;
            }
            else {
                p2.addCard(initPile.drawCard(false));
                alternate = true;
            }
        }
    }

    /** Play a single round. */
    public void playRound() {
        Pile trick = new Pile("Trick");
        Card card1;
        Card card2;

        System.out.println("ROUND " + round);
        boolean endRound = false;
        while(!endRound) {
            System.out.println(p1.toString());
            System.out.println(p2.toString());

            if (!p2.hasCard()) {
                p1.addCards(trick);
                System.out.println("P1 wins round gets " + trick.toString());
                endRound = true;
                continue;
            }
            else if (!p1.hasCard()) {
                p2.addCards(trick);
                System.out.println("P2 wins round gets " + trick.toString());
                endRound = true;
                continue;
            }

            card1 = p1.drawCard();
            card2 = p2.drawCard();
            card1.setFaceUp();
            card2.setFaceUp();
            System.out.println("P1 card: " + card1.toString());
            System.out.println("P1 card: " + card2.toString());

            trick.addCard(card1);
            trick.addCard(card2);

            if (card1.equals(card2)) {
                System.out.println("WAR!");
            }
            else if (card1.beats(card2)) {
                p1.addCards(trick);
                System.out.println("P1 wins round gets " + trick.toString());
                endRound = true;
            }
            else {
                p2.addCards(trick);
                System.out.println("P2 wins round gets " + trick.toString());
                endRound = true;
            }

        }

        round += 1;
    }

    /** Play the full game. */
    public void playGame() {
        round = 1;

        while (!p1.isWinner() && !p2.isWinner()) {
            playRound();

            if (!p2.hasCard()) {
                p1.setWinner();
            }
            else if (!p1.hasCard()) {
                p2.setWinner();
            }
        }

        System.out.println(p1.toString());
        System.out.println(p2.toString());

        if (p1.isWinner()) {
            System.out.println("P1 WINS!");
        }
        else {
            System.out.println("P2 WINS!");
        }
    }

    /**
     * The main method get the command line arguments, then constructs
     * and plays the game.  The Pile's random number generator and seed
     * need should get set here using Pile.setSeed(seed).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java War cards-per-player seed");
        } else {
            int cardsPerPlayer = Integer.parseInt(args[0]);
            // must be between 1 and 26 cards per player
            if (cardsPerPlayer <= 0 || cardsPerPlayer > MAX_CARDS_PER_PLAYER) {
                System.out.println("cards-per-player must be between 1 and " + MAX_CARDS_PER_PLAYER);
            } else {
                // set the rng seed
                Pile.setSeed(Integer.parseInt(args[1]));
                War war = new War(cardsPerPlayer);
                war.playGame();
            }
        }
    }
}
