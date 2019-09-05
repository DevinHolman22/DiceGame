package zoughted;

import java.util.*;

public class Game {

	private int numPlayers;
	private ArrayList<Integer> chosenNumberList = new ArrayList<>();
	private boolean zought = false;
	
	Dice dice = new Dice();
	ZoughtedGui end;
	
	public void rollDice() {
		Random rand = new Random();
		for (int x = 0; x < 6; x++) {
			if (!dice.getKeepArray(x)) 
			{
				int next = rand.nextInt(6) + 1;
				dice.setPlayingSet(x, next);
			//} else {


				// lblDice1.setIcon(new
				// ImageIcon(ZoughtedApp.class.getResource("/zoughted/Images/die" + next +
				// ".png")));
				// System.out.printf("%s's Dice - #%d:
				// %d\n\n",roster.get(findActivePlayer()).getPlayerName(), x + 1,
				// dice.getPlayingSet(x));
			}
		}

	}

	public void chooseDice() {

		ZoughtedGui lock = new ZoughtedGui();
		if (lock.btnLockDie1.isSelected() == true) 
		{
			dice.setKeepArray(0, true);
		}
		if (lock.btnLockDie2.isSelected()) {
			dice.setKeepArray(1, true);
		}
		if (lock.btnLockDie3.isSelected()) {
			dice.setKeepArray(2, true);
		}
		if (lock.btnLockDie4.isSelected()) {
			dice.setKeepArray(3, true);
		}
		if (lock.btnLockDie5.isSelected()) {
			dice.setKeepArray(4, true);
		}
		if (lock.btnLockDie6.isSelected()) {
			dice.setKeepArray(5, true);
		}

	}

	public ArrayList<Integer> changeChosenNumber(int[] chosenNumber) {
		for (int x = 0; x < chosenNumber.length; x++) {
			chosenNumberList.add(x);
			chosenNumberList.set(x, chosenNumber[x]);
		}

		return chosenNumberList;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public boolean isZought() {
		return zought;
	}

	public void setZought(boolean zought) {
		this.zought = zought;
	}



}
