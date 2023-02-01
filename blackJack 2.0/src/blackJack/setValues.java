package blackJack;

import java.util.ArrayList;
import java.util.List;

public class setValues {
	private static int totalPlayer;
	private static int totalDealer;
	private static int totalSplit;
	private static int softAPlayer;
	private static int hardAPlayer;
	private static int softASplit;
	private static int hardASplit;
	private static int softADealer;
	private static int hardADealer;
	private static int valueCard1;
	private static int valueCard2;
	private static int valueCard3;
	private static int valueCard4;
	private static int totalRounds;
	private static float totalMatches;
	private static float totalPlayerVictorys;
	private static float totalDealerVictorys;
	private static float totalDraws;
	private static double wallet;
	private static double bet;
	private static String card3;
	private static String card4;
	private static String roundResult;
	private static String splitResult;
	private static List<String> playerHand = new ArrayList<>();
	private static List<String> dealerHand = new ArrayList<>();
	private static List<String> splitHand = new ArrayList<>();
	public double setWallet(double value) {
		wallet = value;
		return wallet;
	}
	public double addWallet(double value) {
		wallet += value;
		return wallet;
	}
	public double getWallet() {
		return wallet;
	}
	public double setBet(double value) {
		bet += value;
		if (value == 0) bet = 0;
		return bet;
	}
	public double getBet() {
		return bet;
	}
	public int setTotalPlayer(int value) {
		totalPlayer += value;
		if (value == 0) totalPlayer = 0;
		return totalPlayer;
	}
	public int setRemoveTotalPlayer(int value) {
		totalPlayer = value;
		return totalPlayer;
	}
	public int setTotalDealer(int value) {
		totalDealer += value;
		if (value == 0) totalDealer = 0;
		return totalDealer;
	}
	public int setTotalSplit(int value) {
		totalSplit += value;
		if (value == 0) totalSplit = 0;
		return totalSplit;
	}
	public int setSoftAPlayer(int value) {
		softAPlayer += value;
		if (value == 0) softAPlayer = 0;
		return softAPlayer;
	}
	public int setHardAPlayer(int value) {
		hardAPlayer += value;
		if (value == 0) hardAPlayer = 0;
		return hardAPlayer;
	}
	public int setSoftADealer(int value) {
		softADealer += value;
		if (value == 0) softADealer = 0;
		return softADealer;
	}
	public int setHardADealer(int value) {
		hardADealer += value;
		if (value == 0) hardADealer = 0;
		return hardADealer;
	}
	public int setSoftASplit(int value) {
		softASplit += value;
		if (value == 0) softASplit = 0;
		return softASplit;
	}
	public int setHardASplit(int value) {
		hardASplit += value;
		if (value == 0) hardASplit = 0;
		return hardASplit;
	}
	public int totalPlayer() {
		return totalPlayer;
	}
	public int totalDealer() {
		return totalDealer;
	}
	public int totalSplit() {
		return totalSplit;
	}
	public int softAPlayer() {
		return softAPlayer;
	}
	public int hardAPlayer() {
		return hardAPlayer;
	}
	public int softADealer() {
		return softADealer;
	}
	public int hardADealer() {
		return hardADealer;
	}
	public int softASplit() {
		return softASplit;
	}
	public int hardASplit() {
		return hardASplit;
	}
	public void setPlayerHand(String card) {
		playerHand.add(card);
	}
	public void setRemovePlayerHand(int card) {
		playerHand.remove(card);
	}
	public void setDealerHand(String card) {
		dealerHand.add(card);
	}
	public void setSplitHand(String card) {
		splitHand.add(card);
	}
	public void clearPlayerHand() {
		playerHand.clear();
	}
	public void clearDealerHand() {
		dealerHand.clear();
	}
	public void clearSplitHand() {
		splitHand.clear();
	}
	public void getPlayerHand(List<String> hand) {
		hand.clear();
		for (String object : playerHand) {
			hand.add(object);
		}
	}
	public void getDealerHand(List<String> hand) {
		hand.clear();
		for (String object : dealerHand) {
			hand.add(object);
		}
	}
	public void getSplitHand(List<String> hand) {
		hand.clear();
		for (String object : splitHand) {
			hand.add(object);
		}
	}
	public String setCard3(String card) {
		card3 = card;
		return card3;
	}
	public String setCard4(String card) {
		card4 = card;
		return card4;
	}
	public int setValue1(int value) {
		valueCard1 = value;
		return valueCard1;
	}
	public int setValue2(int value) {
		valueCard2 = value;
		return valueCard2;
	}
	public int setValue3(int value) {
		valueCard3 = value;
		return valueCard3;
	}
	public int setValue4(int value) {
		valueCard4 = value;
		return valueCard4;
	}
	public String card3() {
		return card3;
	}
	public String card4() {
		return card4;
	}
	public int value1() {
		return valueCard1;
	}
	public int value2() {
		return valueCard2;
	}
	public int value3() {
		return valueCard3;
	}
	public int value4() {
		return valueCard4;
	}
	public String setRoundResult(String res) {
		roundResult = res;
		return roundResult;
	}
	public String setRoundSplit(String res) {
		splitResult = res;
		return splitResult;
	}
	public String getRoundResult() {
		return roundResult;
	}
	public String getRoundSplit() {
		return splitResult;
	}
	public float setTotalPlayerVictorys(float value) {
		totalPlayerVictorys += value;
		return totalPlayerVictorys;
	}
	public float setTotalDealerVictorys(float value) {
		totalDealerVictorys += value;
		return totalDealerVictorys;
	}
	public float setTotalDraws(float value) {
		totalDraws += value;
		return totalDraws;
	}
	public float getTotalPlayerVictorys() {
		return totalPlayerVictorys;
	}
	public float getTotalDealerVictorys() {
		return totalDealerVictorys;
	}
	public float getTotalDraws() {
		return totalDraws;
	}
	public int getTotalRounds() {
		totalRounds += 1;
		return totalRounds;
	}
	public float getTotalMatches() {
		totalMatches = totalPlayerVictorys + totalDealerVictorys + totalDraws;
		return totalMatches;
	}
}