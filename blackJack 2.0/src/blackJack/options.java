package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class options {
	matchResult result = new matchResult();
	printHands print = new printHands();
	setValues def = new setValues();
	purchases buyCards = new purchases();
	private static List<String> playerHand = new ArrayList<>();
	private static List<String> splitHand = new ArrayList<>();	
	private static boolean splitPurchase;
	private static String option;
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getSplitHand(splitHand);
	}
	@SuppressWarnings("resource")
	public String playerOption() throws InterruptedException {
		getHand();
		Scanner sc = new Scanner(System.in);
		if (def.value1() == def.value2()) {
			while(true) {
				System.err.println("- Choose an option:");
				TimeUnit.SECONDS.sleep(1);
				System.out.print("- Hit (ask for one more card)\n"
						+ "- Stand (keep your hand)\n"
						+ "- Double (double the bet and buy only one more card)\n"
						+ "- Split (split your hand and double your bet)\n");
				System.out.print("Option: ");
				option = sc.next().trim().toLowerCase();
				option = option.substring(0, 1).toUpperCase().concat(option.substring(1));
				switch (option) {
				case "Hit": return option;
				case "Stand": return option;
				case "Double": return option;
				case "Split":
					if (def.getWallet() >= def.getBet()) {
						def.setWallet(def.getWallet() - def.getBet());
						System.out.printf("Splitted hand! You bet R$ %.2f\n", def.getBet());
						def.setBet(def.getBet());
						System.out.printf("Current balance R$ %.2f\n", def.getWallet());
						TimeUnit.SECONDS.sleep(1);
						return option;
					} else System.err.println("Insufficient funds!");
				default: 
					System.err.println("Invalid option!");
					TimeUnit.SECONDS.sleep(1);
					break;
				}
			}
		} else {
			while(true) {
				if (splitHand.size() > 0) {
					print.dealerHidden();
					print.printSplitHand();
					TimeUnit.SECONDS.sleep(1);
				}
				System.err.println("- Choose an option:");
				TimeUnit.SECONDS.sleep(1);
				System.out.print("- Hit (ask for one more card)\n"
						+ "- Stand (keep your hand)\n"
						+ "- Double (double the bet and buy only one more card)\n");
				System.out.print("Option: ");
				option = sc.next().trim().toLowerCase();
				option = option.substring(0, 1).toUpperCase().concat(option.substring(1));
				switch (option) {
					case "Hit": return option;
					case "Stand": return option;
					case "Double": {
						if (def.getWallet() >= def.getBet()) {
							def.setWallet(def.getWallet() - def.getBet());
							def.setBet(def.getBet());
							System.err.printf("Amount betted = R$ %.2f | Current Balance R$ %.2f\n", def.getBet(), def.getWallet());
							return option;
						} else System.err.println("Insufficient funds!");
					}
					default:
						System.err.println("Invalid option!");
						TimeUnit.SECONDS.sleep(1);
						break;
				}
			}
		}
	}
	@SuppressWarnings("resource")
	public void selectedOption(deck game) throws InterruptedException {
		String answer = "";
		getHand();
		Scanner sc = new Scanner(System.in);
		if (option.equals("Hit")) {
			buyCards.buyPlayerCardProcess(game);
			for (int i = 0; i < 1; i++) {
				if ((def.totalPlayer() < 21 && splitPurchase == false) ||
						(splitHand.size() > 1 && def.totalSplit() < 21)) {
					print.dealerHidden();
					if (splitHand.size() == 0 && splitPurchase == false) print.printPlayerHand();
					else print.printSplitHand();
					System.out.print("Ask for another card? (hit) / (stand): ");
					answer = sc.next().trim().toLowerCase();
					answer = answer.substring(0, 1).toUpperCase().concat(answer.substring(1));
					if (answer.equals("Hit") && splitPurchase == false) {
						buyCards.buyPlayerCardProcess(game);
						i--;
					} else if (answer.equals("Hit") && splitPurchase == true) {
						buyCards.buySplitCardProcess(game);
						i--;
					} else if (answer.equals("Stand") && splitPurchase == false) {
						if (splitHand.size() == 0) result.finalResult(game);
						else {
							splitPurchase = true;
							buyCards.buySplitCardProcess(game);
							i--;
						}
					} else if (answer.equals("Stand") && splitPurchase == true) result.finalResult(game);
					else {
						System.err.println("Insert a valid answer!");
						i--;
					}
				} else {
					if (splitHand.size() == 0)  result.finalResult(game);
					else if (splitHand.size() == 1) {
						splitPurchase = true;
						buyCards.buySplitCardProcess(game);
						i--;
					}
					else result.finalResult(game);
				}
			}
		} else if (option.equals("Stand")) result.finalResult(game);
		else if (option.equals("Double")) {
			buyCards.buyPlayerCardProcess(game);
			result.finalResult(game);
		} else {
			def.setSplitHand(playerHand.get(1));
			def.setRemovePlayerHand(1);
			int split = def.totalPlayer();
			split /= 2;
			def.setRemoveTotalPlayer(split);
			def.setTotalSplit(split);
			print.dealerHidden();
			print.printSplitHand();
			option = "Hit";
			selectedOption(game);
		}
	}
	public boolean setSplitPurchase(boolean value) {
		splitPurchase = value;
		return splitPurchase;
	}
	public boolean splitPurchase() {
		return splitPurchase;
	}
}