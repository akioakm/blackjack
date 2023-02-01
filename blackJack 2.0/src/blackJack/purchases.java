package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class purchases {
	printHands print = new printHands();
	setValues def = new setValues();
	cardBoard cards = new cardBoard();
	private List<String> playerHand = new ArrayList<>();
	private List<String> dealerHand = new ArrayList<>();
	private List<String> splitHand = new ArrayList<>();
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getDealerHand(dealerHand);
		def.getSplitHand(splitHand);
	}
	public void buyPlayerCardProcess(deck match) throws InterruptedException {
		getHand();
		String card;
		match.dealerPurchasing();
		TimeUnit.SECONDS.sleep(1);
		card = match.getCard();
		match.dealerGet(card);
		int valueCard = match.getValueCard(card);
		if (playerHand.size() == 0 && splitHand.size() == 0) def.setValue1(valueCard);
		else if (playerHand.size() == 1 && splitHand.size() == 0) def.setValue2(valueCard);
		TimeUnit.SECONDS.sleep(1);
		cards.playerCard(card, valueCard);
		TimeUnit.SECONDS.sleep(1);
	}
	public void buyDealerCardProcess(deck match) throws InterruptedException {
		getHand();
		String card;
		match.dealerPurchasing();
		TimeUnit.SECONDS.sleep(1);
		card = match.getCard();
		int valueCard = match.getValueCard(card);
		if (dealerHand.size() == 1) {
			match.dealerDealtCard();
			TimeUnit.SECONDS.sleep(1);
			print.dealerHidden();
			def.setCard4(card);
			def.setValue4(valueCard);
		} else {
			if (dealerHand.size() == 0) {
				def.setCard3(card);
				def.setValue3(valueCard);
			}
			match.dealerGet(card);
			TimeUnit.SECONDS.sleep(1);
			cards.dealerCard(card, valueCard);
			TimeUnit.SECONDS.sleep(1);
		}
	}
	public void buySplitCardProcess(deck match) throws InterruptedException {
		String card;
		match.dealerPurchasing();
		TimeUnit.SECONDS.sleep(1);
		card = match.getCard();
		match.dealerGet(card);
		int valueCard = match.getValueCard(card);
		TimeUnit.SECONDS.sleep(1);
		cards.splitCard(card, valueCard);
		TimeUnit.SECONDS.sleep(1);
	}
	public void board(deck match) throws InterruptedException {
		purchases buyCards = new purchases();
		getHand();
		while (def.totalDealer() < 17) {
			if ((dealerHand.contains("Ás") && (def.totalDealer()+10 <= 21 && def.totalDealer()+10 >= 17))) {
				def.setTotalDealer(10);
				break;
			} else buyCards.buyDealerCardProcess(match);
		}
		if (splitHand.size() == 0) print.printHand();
		else {
			print.printDealerHand();
			print.printSplitHand();
		}
	}
}