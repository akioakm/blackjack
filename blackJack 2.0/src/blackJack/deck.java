package blackJack;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class deck {
	private static boolean addDeck = true;
	private static int deckSize;
	private final List<String> blackjackDeck = new ArrayList<>();
	private Queue<String> cards = new LinkedList<String>();
	private String drawCard;
	private boolean startGame = false;
	setValues def = new setValues();
	public deck(){
		String deck[] = {"2", "2", "2", "2", "3", "3", "3", "3", "4", "4", "4", "4", "5", "5", "5", "5",
		"6", "6", "6", "6", "7", "7", "7", "7", "8", "8", "8", "8", "9", "9", "9", "9", "10", "10", "10", "10",
		"Q", "Q", "Q", "Q", "J", "J", "J", "J", "K", "K", "K", "K", "A", "A", "A", "A"};
		for (String cartinha : deck) blackjackDeck.add(cartinha);
	}
	public boolean startGame() {
		return startGame;
	}
	@SuppressWarnings("resource")
	public void deckSize() {
		Scanner sc = new Scanner(System.in);
		try {
			for (int i = 0; i < 1; i++) {
				System.out.print("Insert the desired deck size (min.1): ");
				deckSize = sc.nextInt();
				if (deckSize <= 0) {
					System.err.println("The deck size can't be below 1!");
					i--;
				}
			}
		} catch (InputMismatchException exception) {
			System.err.println("Insert deck size in positive numbers and integers!!");
			deckSize();
		}
	}
	@SuppressWarnings("resource")
	public void wallet() {
		try {
			Scanner sc = new Scanner(System.in);
			for (int i = 0; i < 1; i++) {
			System.out.print("Insert your wallet balance: ");
			def.setWallet(sc.nextDouble());
			if (def.getWallet() <= 0) {
				System.err.println("You CANNOT enter a null or negative value!!");
				i--;
				}
			}
		} catch(InputMismatchException exception) {
			System.err.println("Your wallet balance MUST have numbers input ONLY!!");
			wallet();
		}
	}
	@SuppressWarnings("resource")
	public void bet() {
		Scanner sc = new Scanner(System.in);
		try{
			for (int i = 0; i < 1; i++) {
				System.out.print("Insert your bet amount: ");
				def.setBet(sc.nextDouble());
				if (def.getBet() > def.getWallet()) {
					System.err.println("Insufficient funds!");
					def.setBet(0);
					i--;
				} else if (def.getBet() < 0) {
					System.err.println("You CANNOT bet a negative amount!");
					def.setBet(0);
					i--;
				} else if (def.getBet() < 1) {
					System.err.println("Minimum bet is R$ 1!");
					def.setBet(0);
					i--;
				} else {
					def.setWallet(def.getWallet() - def.getBet());
				}
			}
			System.out.printf("Value bet! Remaining balance: R$ %.2f\n", def.getWallet());
		} catch(InputMismatchException exception) {
			System.err.println("The bet amount MUST have numbers entered ONLY!!");
			bet();
		}
	}
	public boolean start() throws InterruptedException {
		System.out.println("Starting the game!");
		TimeUnit.SECONDS.sleep(1);
		System.err.println("Shuffling...");
		shuffleCards();
		TimeUnit.SECONDS.sleep(1);
		startGame = true;
		System.err.println("Game Started!");
		TimeUnit.SECONDS.sleep(1);
		return true;
	}
	public void shuffleCards() {
		for (int i = 0; i < deckSize; i++) {
			Collections.shuffle(blackjackDeck);
			for (String littleCards : blackjackDeck) cards.add(littleCards);
		}
	}
	public void dealerPurchasing() {
		System.err.println("The dealer is pulling the card..");
	}
	public void dealerGet(String carta) {
		System.err.println("The card " + carta + " was bought!");
	}
	public void dealerDealtCard() throws InterruptedException {
		System.err.println("The dealer got the card..");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("The dealer has dealt the card upside down!");
		TimeUnit.SECONDS.sleep(1);
	}
	public String getCard() throws InterruptedException {
		if (cards.size() > 0) {
			this.drawCard = cards.poll();
			if ((blackjackDeck.size()/2) == cards.size()) System.err.println("ALERT! Half of the deck has already been drawn!!");
			TimeUnit.SECONDS.sleep(2);
			return drawCard;
		} else {
			System.err.println("No cards in the deck! Adding one more deck...");
			TimeUnit.SECONDS.sleep(2);
			Collections.shuffle(blackjackDeck);
			for (String littleCard : blackjackDeck) cards.add(littleCard);
			System.err.println("Added deck! Continuing the game..");
			TimeUnit.SECONDS.sleep(1);
			this.drawCard = cards.poll();
			TimeUnit.SECONDS.sleep(2);
			addDeck = true;
			return drawCard;
		}
	}
	public int getValueCard(String valor) {
		switch (valor) {
		case "2": return 2;
		case "3": return 3;
		case "4": return 4;
		case "5": return 5;
		case "6": return 6;
		case "7": return 7;
		case "8": return 8;
		case "9": return 9;
		case "10": return 10;
		case "J": return 10;
		case "Q": return 10;
		case "K": return 10;
		case "A": return 1;
		default:
			throw new IllegalArgumentException("ERROR!");
		}
	}
	public void totalRounds() {
		try {
			System.out.println("- Total rounds: " + def.getTotalRounds());
			float porcJogador = (def.getTotalPlayerVictorys()*100)/def.getTotalMatches();
			float porcDealer = (def.getTotalDealerVictorys()*100)/def.getTotalMatches();
			float porcEmpate = (def.getTotalDraws()*100)/def.getTotalMatches();
			char pc = '%';
			System.out.printf("- Total player wins: %d (%.2f%c)\n", (int) def.getTotalPlayerVictorys(), porcJogador, pc);
			System.out.printf("- Total dealer wins: %d (%.2f%c)\n",(int) def.getTotalDealerVictorys(), porcDealer, pc);
			System.out.printf("- Total push: %d (%.2f%c)\n", (int)def.getTotalDraws(), porcEmpate, pc);
			System.out.println("- Total remaining cards in the deck: " + cards.size());
		} catch (ArithmeticException exception) {
			System.err.println("ERROR!");;
		}
	}
	@SuppressWarnings("resource")
	public void endDeck() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		if (cards.size() < 20 && addDeck == true) {
			Collator comp = Collator.getInstance(new Locale("pt", "BR"));
			comp.setStrength(Collator.PRIMARY);
			System.err.println("ALERT! The deck is low on cards!!!");
			TimeUnit.SECONDS.sleep(1);
			for(int i = 0; i < 1; i++) {
				System.out.print("Do you want to add one more deck? (Yes) / (No): ");
				String res = sc.next().trim().toLowerCase();
				res = res.substring(0, 1).toUpperCase().concat(res.substring(1));
				 if(res.equals("Sim") || res.equals("S") || res.equals("Yes") || res.equals("Y")) {
					 System.err.println("Adding one more deck..");
					 TimeUnit.SECONDS.sleep(2);
					 shuffleCards();
					 System.err.println("Deck has been successfully added!");
					 TimeUnit.SECONDS.sleep(2);
				 } else if ((comp.compare(res, "Não") == 0 || res.equals("No") || res.equals("N"))) {
					 TimeUnit.SECONDS.sleep(1);
					 addDeck = false;
					 System.err.println("Continuing the game..!");
					 TimeUnit.SECONDS.sleep(1);
				 } else {
					 System.err.println("Insert a valid answer!");
					 i--;
				 }
			}
		}
	}
	public void offGame() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Thanks for playing! =D");
		startGame = false;
	}
}