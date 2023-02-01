package blackJack;

import java.util.concurrent.TimeUnit;

public class playGame {
	public static void main(String[] args) {
		try {
			double round = 1;
			finalizeRound finalize = new finalizeRound();
			matchResult result = new matchResult();
			printHands print = new printHands();
			boardBlackJack bj = new boardBlackJack();
			purchases buy = new purchases();
			options option = new options();
			deck deck = new deck();
			System.out.println("Each deck has 52 cards.");
			TimeUnit.SECONDS.sleep(1);
			deck.deckSize();
			deck.wallet();
			deck.bet();	
			deck.start();
			TimeUnit.SECONDS.sleep(2);
			if (deck.startGame() == true) {
				do {
				// player (1):
					buy.buyPlayerCardProcess(deck);
				// dealer (1):
					buy.buyDealerCardProcess(deck);
				// player (2):
					buy.buyPlayerCardProcess(deck);
					TimeUnit.SECONDS.sleep(1);
				// dealer (2):
					buy.buyDealerCardProcess(deck);
				// round_process
					print.printPlayerHand();
					TimeUnit.SECONDS.sleep(1);
					bj.playerBlackJack();
					if (bj.playerBlackJack() == false) {
						option.playerOption();
						option.selectedOption(deck);
					} else result.finalResult(deck);
					TimeUnit.SECONDS.sleep(1);
					deck.totalRounds();
					TimeUnit.SECONDS.sleep(1);
					finalize.endRound();
					TimeUnit.SECONDS.sleep(1);
					round = finalize.newRound(round);
					deck.endDeck();
				} while (round > 0);
					deck.offGame();
				}
			} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}