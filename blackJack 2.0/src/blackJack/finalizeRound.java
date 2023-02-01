package blackJack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class finalizeRound {
	setValues def = new setValues();
	options option = new options();
	private static List<String> playerHand = new ArrayList<>();
	private static List<String> dealerHand = new ArrayList<>();
	private static List<String> splitHand = new ArrayList<>();
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getDealerHand(dealerHand);
		def.getSplitHand(splitHand);
	}
	public void endRound() throws InterruptedException {
		def.setTotalPlayer(0);
		def.setTotalSplit(0);
		def.setTotalDealer(0);
		def.setSoftAPlayer(0);
		def.setHardAPlayer(0);
		def.setSoftADealer(0);
		def.setHardADealer(0);
		def.setSoftASplit(0);
		def.setHardASplit(0);
		def.clearPlayerHand();
		def.clearDealerHand();
		def.clearSplitHand();
		option.setSplitPurchase(false);
		String res = def.getRoundResult();
		String resSplit = def.getRoundSplit();
		System.out.println("---------------------------------------------------------------");
		try {
			if (splitHand.size() == 0) {
				if (res.equals("WIN")) {
					def.addWallet(def.getBet()*2);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (def.getBet()*2), def.getWallet());
				} else if (res.equals("PUSH")){
					def.addWallet(def.getBet());
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received + R$ %.2f back in your wallet | Current balance R$ %.2f\n", def.getBet(), def.getWallet());
				} else if (res.equals("BLACKJACK PLAYER")) {
					double newWallet = (def.getBet()*2) + (def.getBet()/2);
					def.addWallet(newWallet);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (newWallet), def.getWallet());
				} else System.out.printf("Saldo atual R$ %.2f\n", def.getWallet());
			} else {
				if (res.equals("WIN") && resSplit.equals("WIN")) {
					def.addWallet(def.getBet()*2);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (def.getBet()*2), def.getWallet());
				} else if ((res.equals("WIN") && resSplit.equals("LOSE")) ||
						(res.equals("LOSE") && resSplit.equals("WIN"))) {
					def.addWallet(def.getBet()/2);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received + R$ %.2f back in your wallet | Current balance R$ %.2f\n", def.getBet(), def.getWallet());		
				} else if (res.equals("PUSH") && resSplit.equals("PUSH")){
					def.addWallet(def.getBet());
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received + R$ %.2f back in your wallet | Current balance R$ %.2f\n", def.getBet(), def.getWallet());	
				} else if ((res.equals("PUSH") && resSplit.equals("WIN")) ||
						(res.equals("WIN") && resSplit.equals("PUSH"))) {
					double profit = (def.getBet()/2) + def.getBet();
					def.addWallet(def.getBet() + profit);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", profit, def.getWallet());
				} else if ((res.equals("PUSH") && resSplit.equals("LOSE")) ||
						((res.equals("LOSE") && resSplit.equals("PUSH")))) {
					def.addWallet(def.getBet()/2);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received + R$ %.2f back in your wallet | Current balance R$ %.2f\n", (def.getBet()/2), def.getWallet());
				} else if (res.equals("BLACKJACK PLAYER") && resSplit.equals("BLACKJACK SECOND HAND")) {
					double newWallet = (def.getBet()*2) + (def.getBet()/2);
					def.addWallet(newWallet*2);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (newWallet), def.getWallet());
				} else if ((res.equals("BLACKJACK PLAYER") && resSplit.equals("WIN")) ||
						(resSplit.equals("BLACKJACK SECOND HAND") && res.equals("WIN"))) {
					double betPlayer = def.getBet()/2;
					double newWallet = ((def.getBet()*2) + (betPlayer/2));
					def.addWallet(newWallet);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (newWallet), def.getWallet());
				} else if ((res.equals("BLACKJACK PLAYER") && resSplit.equals("PUSH")) ||
						(resSplit.equals("BLACKJACK SECOND HAND") && res.equals("PUSH"))) {
					double betPlayer = def.getBet()/2;
					double newWallet = (betPlayer/2) + betPlayer + def.getBet();
					def.addWallet(newWallet);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (newWallet), def.getWallet());
				} else if ((res.equals("BLACKJACK PLAYER") && resSplit.equals("LOSE")) ||
						(resSplit.equals("BLACKJACK SECOND HAND") && res.equals("LOSE"))) {
					double betPlayer = def.getBet()/2;
					double newWallet = def.getBet() + (betPlayer/2);
					def.addWallet(newWallet);
					TimeUnit.SECONDS.sleep(1);
					System.out.printf("You received a profit of + R$ %.2f | Current balance R$ %.2f\n", (newWallet), def.getWallet());
				} else System.out.printf("Current balance R$ %.2f\n", def.getWallet());
			}
		def.setBet(0);
		} catch (NullPointerException exception) {
			System.err.println("ERROR!");
		}
	}
	@SuppressWarnings("resource")
	public double newRound(double round) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		try {
			if (def.getWallet() > 1) {
				for (int i = 0; i < 1; i++) {
					System.out.print("Enter a new bet or 0 to leave the board: ");
					round = sc.nextDouble();
					if (round > def.getWallet()) {
						System.err.println("Insufficient funds!");
						i--;
					} else if (round < 0) {
						System.err.println("You CANNOT bet a negative amount!!");
						i--;
					} else if (round == 0) System.err.println("you left the board!");
					else if (round < 1) {
						System.err.println("The minimum bet is R$ 1!");
						i--;
					} else {
						def.setWallet(def.getWallet() - round);
						def.setBet(round);
						System.out.printf("Amount bet successfully! | Current balance R$ %.2f\n", def.getWallet());
						TimeUnit.SECONDS.sleep(1);
					}
				}
			} else {
				System.err.println("You have no more balance :/");
				return 0;
			}
		} catch(InputMismatchException exception) {
			System.err.println("The bet amount MUST have numbers input ONLY!!");
			newRound(round);
		}
		return round;
	}
}