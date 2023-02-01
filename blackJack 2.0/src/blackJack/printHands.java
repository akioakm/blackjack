package blackJack;

import java.util.ArrayList;
import java.util.List;

public class printHands {
	setValues def = new setValues();
	private List<String> playerHand = new ArrayList<>();
	private List<String> dealerHand = new ArrayList<>();
	private List<String> splitHand = new ArrayList<>();
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getDealerHand(dealerHand);
		def.getSplitHand(splitHand);
	}
	public void printHand() {
		getHand();
		System.out.println("---------------------------------------------------------------");
		System.out.printf("- Dealer hand: ");
		if ((def.softADealer() > 0 && def.totalDealer() <= 11) && def.hardADealer() <= 21) {
			for(int i = 0; i < dealerHand.size(); i++) System.out.print(dealerHand.get(i) + "  ");
			System.out.printf("| (%d)/(%d)\n", def.totalDealer(), def.hardADealer());
		} else {
			for(int i = 0; i < dealerHand.size(); i++) System.out.print(dealerHand.get(i) + "  ");
			System.out.printf("| (%d)\n", def.totalDealer());
		}
		System.out.printf("- Your hand: ");
		for(int i = 0; i < playerHand.size(); i++) System.out.print(playerHand.get(i) + "  ");
		System.out.printf("| (%d)\n", def.totalPlayer() );
		System.out.println("---------------------------------------------------------------");
	}
	public void printDealerHand() {
		getHand();
		System.out.println("---------------------------------------------------------------");
		System.out.printf("- Dealer hand: ");
		if ((def.softADealer() > 0 && def.totalDealer() <= 11) && def.hardADealer() <= 21) {
			for(int i = 0; i < dealerHand.size(); i++) System.out.print(dealerHand.get(i) + "  ");
			System.out.printf("| (%d)/(%d)\n", def.totalDealer(), def.hardADealer());
		} else {
			for(int i = 0; i < dealerHand.size(); i++) System.out.print(dealerHand.get(i) + "  ");
			System.out.printf("| (%d)\n", def.totalDealer());
		}
	}
	public void dealerHidden() throws InterruptedException {
		getHand();
		System.out.println("---------------------------------------------------------------");
		if (def.softADealer() > 0 && def.totalDealer() <= 11) {
			System.out.printf("- Dealer hand: %s, ? | (%d)/(%d)\n", def.card3(), def.softADealer(), def.hardADealer());
		} else if (def.softADealer() > 1 && def.totalDealer() <= 11) {
			System.out.printf("- Dealer hand: %s, ? | (%d)/(%d)\n", def.card3(), (def.softADealer()-1), (def.hardADealer()-1));
		}
		else System.out.printf("- Dealer hand: %s, ? | (%d)\n", dealerHand.get(0), def.value3());
	}
	public void printPlayerHand() {
		getHand();
		System.out.printf("- Your hand: ");
		if ((def.softAPlayer() > 0 && def.totalPlayer() <= 11) && def.hardAPlayer() <= 21) {
			for(int i = 0; i < playerHand.size(); i++) System.out.print(playerHand.get(i) + "  ");
			System.out.printf("| (%d)/(%d)\n", def.totalPlayer(), def.hardAPlayer());
		}
		else {
			for(int i = 0; i < playerHand.size(); i++) System.out.print(playerHand.get(i) + "  ");
			System.out.printf("| (%d)\n", def.totalPlayer() );
		}
		System.out.println("---------------------------------------------------------------");
	}
	public void printSplitHand() {
		getHand();
		System.out.printf("- First hand: ");
		if ((def.softAPlayer() > 0 && def.totalPlayer() <= 11) && def.hardAPlayer() <= 21) {
			for(int i = 0; i < playerHand.size(); i++) System.out.print(playerHand.get(i) + "  ");
			System.out.printf("| (%d)/(%d)\n", def.totalPlayer(), def.hardAPlayer());
		}
		else {
			for(int i = 0; i < playerHand.size(); i++) System.out.print(playerHand.get(i) + "  ");
			System.out.printf("| (%d)\n", def.totalPlayer() );
		}
		System.out.printf("- Second hand: ");
		if ((def.softASplit() > 0 && def.totalSplit() <= 11) && def.hardASplit() <= 21) {
			for(int i = 0; i < splitHand.size(); i++) System.out.print(splitHand.get(i) + "  ");
			System.out.printf("| (%d)/(%d)\n", def.totalSplit(), def.hardASplit());
		}
		else {
			for(int i = 0; i < splitHand.size(); i++) System.out.print(splitHand.get(i) + "  ");
			System.out.printf("| (%d)\n", def.totalSplit() );
		}
		System.out.println("---------------------------------------------------------------");
	}
}