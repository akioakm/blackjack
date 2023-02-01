package blackJack;

import java.util.ArrayList;
import java.util.List;

public class cardBoard {
	setValues def = new setValues();
	private List<String> playerHand = new ArrayList<>();
	private List<String> dealerHand = new ArrayList<>();
	private List<String> splitHand = new ArrayList<>();
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getDealerHand(dealerHand);
		def.getSplitHand(splitHand);
	}
	public void playerCard(String card, int valueCard) throws InterruptedException {
		int totalA = 0;
		int total = def.totalPlayer() + valueCard;
		def.setPlayerHand(card);
		getHand();
		for (int i = 0; i < playerHand.size(); i++) if (playerHand.get(i).equals("A")) totalA++;
		if (totalA > 0 && (def.totalPlayer()+valueCard) == 11) {
			def.setTotalPlayer(valueCard+10);
			System.out.printf("The card %s (%d) has been added to your hand.\n", card, def.totalPlayer());
		} else if (totalA > 0 && total < 11 && def.hardAPlayer()+11 < 21) {
			def.setTotalPlayer(valueCard);
			def.setSoftAPlayer(1);
			if (def.hardAPlayer() == 0) def.setHardAPlayer(def.totalPlayer() + 10);
			else def.setHardAPlayer(11);
			System.out.printf("The card %s (%d/%d) has been added to your hand.\n", card, def.totalPlayer(), def.hardAPlayer());
		} else if (totalA > 0 && total < 11 && def.hardAPlayer() >= 11) {
			def.setTotalPlayer(valueCard);
			def.setSoftAPlayer(1);
			def.setHardAPlayer(valueCard);
			System.out.printf("The card %s (%d/%d) has been added to your hand.\n", card, def.totalPlayer(), def.hardAPlayer());
		} else {
			def.setTotalPlayer(valueCard);
			System.out.printf("The card %s (%d) has been added to your hand.\n", card, def.totalPlayer());
		}
	}
	public void dealerCard(String card, int valueCard) throws InterruptedException {
		int totalA = 0;
		int total = def.totalDealer() + valueCard;
		def.setDealerHand(card);
		getHand();
		for (int i = 0; i < dealerHand.size(); i++) if (dealerHand.get(i).equals("A")) totalA++;
		if ((totalA > 0 && (def.totalDealer()+valueCard) == 11)) {
			def.setTotalDealer(10);
			System.out.printf("The card %s (%d) has been added to the Dealer's hand.\n", card, def.totalDealer());
		} else if (totalA > 0 && total < 11 && def.hardADealer()+11 < 21) {
			def.setTotalDealer(valueCard);
			def.setSoftADealer(1);
			if (def.hardADealer() == 0) def.setHardADealer(def.totalDealer() + 10);
			else def.setHardADealer(11);
			System.out.printf("The card %s (%d/%d) has been added to the Dealer's hand.\n", card, def.totalDealer(), def.hardADealer());
		} else if (totalA > 0 && total < 11 && def.hardADealer() >= 11) {
			def.setTotalDealer(valueCard);
			def.setSoftADealer(1);
			def.setHardADealer(valueCard);
			System.out.printf("The card %s (%d/%d) has been added to the Dealer's hand.\n", card, def.totalDealer(), def.hardADealer());
		} else {
			def.setTotalDealer(valueCard);
			System.out.printf("The card %s (%d) has been added to the Dealer's hand.\n", card, def.totalDealer());
		}
	}
	public void splitCard(String card, int valueCard) throws InterruptedException {
		int totalA = 0;
		int total = def.totalSplit() + valueCard;
		def.setSplitHand(card);
		getHand();
		for (int i = 0; i < splitHand.size(); i++) if (splitHand.get(i).equals("A")) totalA++;
		if (totalA > 0 && (def.totalSplit()+valueCard) == 11) {
			def.setTotalSplit(10);
			System.out.printf("The card %s (%d) has been added to your second hand.\n", card, def.totalSplit());
		} else if (totalA > 0 && total < 11 && def.hardASplit()+11 < 21) {
			def.setTotalSplit(valueCard);
			def.setSoftASplit(1);
			if (def.hardASplit() == 0) def.setHardASplit(def.totalSplit() + 10);
			else def.setHardASplit(11);
			System.out.printf("The card %s (%d/%d) has been added to your second hand.\n", card, def.totalSplit(), def.hardASplit());
		} else if (totalA > 0 && total < 11 && def.hardASplit() >= 11) {
			def.setTotalSplit(valueCard);
			def.setSoftASplit(1);
			def.setHardASplit(valueCard);
			System.out.printf("The card %s (%d/%d) has been added to your second hand.\n", card, def.totalSplit(), def.hardASplit());
		}  else {
			def.setTotalSplit(valueCard);
			System.out.printf("The card %s (%d) has been added to your second hand.\n", card, def.totalSplit());
		}
	}
	public void setDealerSecondCard() {
		int contA = 0;
		int total = def.totalDealer() + def.value4();
		def.setDealerHand(def.card4());
		getHand();
		for (int i = 0; i < dealerHand.size(); i++) if (dealerHand.get(i).equals("A")) contA++;
		if (contA > 0 && (def.totalDealer()+def.value4()) == 11) def.setTotalDealer(11);
		else if (contA > 0 && total < 11 && def.hardADealer()+11 < 21) {
			def.setTotalDealer(def.value4());
			def.setSoftADealer(1);
			if (def.hardADealer() == 0) def.setHardADealer(def.totalDealer() + 10);
			else def.setHardADealer(11);
		} else if (contA > 0 && total < 11 && def.hardADealer() >= 11) {
			def.setTotalDealer(def.value4());
			def.setSoftADealer(1);
			def.setHardADealer(def.value4());
		} else def.setTotalDealer(def.value4());
	}
}