package zoughted;

public class Player 
{
    
    private String playerName;
    private boolean playerStatus;
    private int totalScore;

    public Player(String playerName, boolean playerStatus) 
    {
        this.playerName = playerName;
        this.playerStatus = playerStatus;
        this.totalScore = 0;
    }

    public String getPlayerName() {
        return playerName;
    }
   
    public boolean isPlayerStatus() {
        return playerStatus;
    }

    public boolean getPlayerStatus() {
        return playerStatus;
    }
    public void setPlayerStatus(boolean playerStatus) {
        this.playerStatus = playerStatus;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int i) {
        this.totalScore = i;
    }

	@Override
	public String toString() {
		return playerName + "\'s ";
	}

    

    
            
}
