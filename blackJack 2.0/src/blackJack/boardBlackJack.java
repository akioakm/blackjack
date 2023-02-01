package blackJack;

import java.util.ArrayList;
import java.util.List;

public class boardBlackJack {
	setValues def = new setValues();
	private List<String> playerHand = new ArrayList<>();
	private List<String> dealerHand = new ArrayList<>();
	private List<String> splitHand = new ArrayList<>();
	public void getHand() {
		def.getPlayerHand(playerHand);
		def.getDealerHand(dealerHand);
		def.getSplitHand(splitHand);
	}
	public boolean playerBlackJack() throws InterruptedException {
		getHand();
		if (def.totalPlayer() == 21 && playerHand.size() == 2) {
			if (dealerBlackJack() == true) return false;
			else if (splitHand.size() > 0) {
				if (splitHand.get(0).equals("A")) return false;
				else return true;
			} else return true;
		} else return false;
	}
	public boolean splitBlackJack() throws InterruptedException {
		getHand();
		if (def.totalSplit() == 21 && splitHand.size() == 2) {
			if (def.totalDealer() == 21 && dealerHand.size() == 2) return false;		
			else if (playerHand.get(0).equals("A")) return false;
			else return true;
		} else return false;
	}
	public boolean dealerBlackJack() throws InterruptedException {
		getHand();
		if (def.totalDealer() == 21 && dealerHand.size() == 2) return true;
		else return false;
	}
}