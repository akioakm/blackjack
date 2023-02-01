package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class matchResult {
	printHands print = new printHands();
	setValues def = new setValues();
	boardBlackJack bj = new boardBlackJack();
	cardBoard board = new cardBoard();
	purchases buy = new purchases();
	private static List<String> playerHand = new ArrayList<>();
	private static List<String> dealerHand = new ArrayList<>();
	private static List<String> splitHand = new ArrayList<>();
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getDealerHand(dealerHand);
		def.getSplitHand(splitHand);
	}
	public void finalResult(deck game) throws InterruptedException{
		getHand();
		if (def.softAPlayer() > 0 && (def.totalPlayer() +10) <= 21 ) def.setTotalPlayer(10);
		System.err.println("The dealer flipped the second card down..");
		board.setDealerSecondCard();
		if ((dealerHand.contains("Ás") && (def.totalDealer()+10 <= 21 && def.totalDealer()+10 >= 17))) def.setTotalDealer(10);
		bj.dealerBlackJack();
		bj.playerBlackJack();
		bj.splitBlackJack();
		TimeUnit.SECONDS.sleep(1);
		if (def.totalDealer() < 17) {
			if (splitHand.size() == 0) {
				print.printHand();
				TimeUnit.SECONDS.sleep(1);
				buy.board(game);
			} else {
				print.printDealerHand();
				print.printSplitHand();
				TimeUnit.SECONDS.sleep(1);
				buy.board(game);
				TimeUnit.SECONDS.sleep(1);
			}
		} else if ((def.totalDealer() > 17 || bj.dealerBlackJack() == true) && (splitHand.size() > 1)) {
			print.printDealerHand();
			print.printSplitHand();
			TimeUnit.SECONDS.sleep(1);
		} else print.printHand();
		TimeUnit.SECONDS.sleep(1);
		if (bj.dealerBlackJack() == false && bj.playerBlackJack() == false) {
			if (def.totalPlayer() > 21 && def.totalDealer() <= 21) {
				System.err.println("FIRST HAND: BUST! LOSE!!");
				TimeUnit.SECONDS.sleep(1);
				def.setTotalDealerVictorys(1);
				def.setRoundResult("LOSE");
			} else if (def.totalDealer() > 21 && def.totalPlayer() <= 21) {
				System.err.println("DEALER BUST!!");
				TimeUnit.SECONDS.sleep(1);
				System.err.println("FIRST HAND: WIN!!");
				def.setTotalPlayerVictorys(1);
				def.setRoundResult("WIN");
			} else if (def.totalPlayer() > 21 && def.totalDealer() > 21) {
				System.err.println("FIRST HAND: DOUBLE BUST! LOSE!!");
				TimeUnit.SECONDS.sleep(1);
				def.setTotalDealerVictorys(1);
				def.setRoundResult("LOSE");
			} else if (def.totalPlayer() > def.totalDealer()) {
				System.err.println("FIRST HAND: WIN!!");
				TimeUnit.SECONDS.sleep(1);
				def.setTotalPlayerVictorys(1);
				def.setRoundResult("WIN");
			} else if (def.totalDealer() > def.totalPlayer()) {
				System.err.println("FIRST HAND: LOSE!!");
				TimeUnit.SECONDS.sleep(1);
				def.setTotalDealerVictorys(1);
				def.setRoundResult("LOSE");
			} else {
				System.err.println("FIRST HAND: PUSH!!");
				TimeUnit.SECONDS.sleep(1);
				def.setTotalDraws(1);
				def.setRoundResult("PUSH");
			}
		} else if (bj.dealerBlackJack() == true) {
			TimeUnit.SECONDS.sleep(1);
			System.err.println("DEALER BLACKJACK!");
			TimeUnit.SECONDS.sleep(1);
			System.err.println("LOSE!!");
			def.setTotalDealerVictorys(1);
			def.setRoundResult("LOSE");
		} else {
			TimeUnit.SECONDS.sleep(1);
			System.err.println("FIRST HAND: BLACKJACK! WIN!!");
			TimeUnit.SECONDS.sleep(1);
			def.setTotalPlayerVictorys(1);
			def.setRoundResult("BLACKJACK PLAYER");
		} 
		if (splitHand.size() > 1 && bj.dealerBlackJack() == false){
			if (def.softASplit() > 0 && (def.totalSplit() + 10) <= 21 && (def.totalSplit()+10 >= 17)) def.setTotalSplit(10);
			if ((bj.dealerBlackJack() == false && bj.splitBlackJack() == false)) {
				if (def.totalSplit() > 21 && def.totalDealer() <= 21) {
					System.err.println("SECOND HAND: BUST! LOSE!!");
					TimeUnit.SECONDS.sleep(1);
					def.setTotalDealerVictorys(1);
					def.setRoundSplit("LOSE");
				} else if (def.totalDealer() > 21 && def.totalSplit() <= 21) {
					System.err.println("SECOND HAND: WIN!!");
					TimeUnit.SECONDS.sleep(1);
					def.setTotalPlayerVictorys(1);
					def.setRoundSplit("WIN");
				} else if (def.totalSplit() > 21 && def.totalDealer() > 21) {
					System.err.println("SECOND HAND: DOUBLE BUST! LOSE!!");
					TimeUnit.SECONDS.sleep(1);
					def.setTotalDealerVictorys(1);
					def.setRoundSplit("LOSE");
				} else if (def.totalSplit() > def.totalDealer()) {
					System.err.println("SECOND HAND: WIN!!");
					TimeUnit.SECONDS.sleep(1);
					def.setTotalPlayerVictorys(1);
					def.setRoundSplit("WIN");
				} else if (def.totalDealer() > def.totalSplit()) {
					System.err.println("SECOND HAND: LOSE!!");
					TimeUnit.SECONDS.sleep(1);
					def.setTotalDealerVictorys(1);
					def.setRoundSplit("LOSE");
				} else {
					System.err.println("SECOND HAND: PUSH!!");
					TimeUnit.SECONDS.sleep(1);
					def.setTotalDraws(1);
					def.setRoundSplit("PUSH");
				}
			}
		} else if (bj.splitBlackJack() == true && bj.dealerBlackJack() == false) {
				TimeUnit.SECONDS.sleep(1);
				System.err.println("SECOND HAND: BLACKJACK! WIN!!");
				TimeUnit.SECONDS.sleep(1);
				def.setTotalPlayerVictorys(1);
				def.setRoundSplit("BLACKJACK SECOND HAND");
		}
	}
}