package zoughted;
import java.util.*;


public class Score
{

    private int[] scoreResult;
    private int roundScore;
    private int keepScore;

    public int calculateScoring(ArrayList<Integer> playingSet)
    {//start
        int a = 0;
        int[] gameEnder = new int[6];
        ArrayList<Integer> straight = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6));
        scoreResult = new int[6];
        roundScore = 0;

        //loops through playingSet and finds frequency of each value in set
        for (int x = 0; x < playingSet.size(); x++)
        {
            scoreResult[x] = Collections.frequency(playingSet, x + 1);
        }

        //creates an array of size six then  fills up with that value to check if game ending score is rolled
        for (int y = 0; y < playingSet.size(); y++)
        {
            for (int x = 0; x < playingSet.size(); x++)
            {
                gameEnder[x] = playingSet.get(y);
            }

            if (gameEnder.equals(playingSet.toArray()))
            {
                roundScore = roundScore + 10000;
            }
        }

        if (roundScore == 10000)
        {
            //endgame
        } else
        {
            //checks to see if playingSet contains a straight
            if (playingSet.containsAll(straight))
            {
                roundScore = roundScore + 1500;
                //add or roll again
            } else
            {
                for (int x = 0; x < playingSet.size(); x++)
                {

                    if (scoreResult[x] == 2)
                    {
                        a++;
                    }
                }
                if (a == 3)
                {
                    roundScore = roundScore + 1200;

                } else
                {
                    if (scoreResult[0] >= 3)
                    {

                        roundScore = roundScore + 1000;
                        for (int z = 1; z < playingSet.size(); z++)
                        {
                            if (scoreResult[z] >= 3)
                            {
                                roundScore = roundScore + (100 * (z + 1));
                            }
                        }

                        //checks for either a 1 or 5 rolled to continue     
                        if (scoreResult[0] > 3 && scoreResult[0] < 6)
                        {
                            roundScore = roundScore + ((scoreResult[0] -3) * 100);
                        }
                        if (scoreResult[4] > 0 && scoreResult[4] < 3)
                        {
                            roundScore = roundScore + (scoreResult[4] * 50);
                        } else if (roundScore == 0)
                        {

                        }

                    } else
                    {

                        //calculates score by multiplying the number of instances times 100 times the value of the dice
                        for (int j = 0; j < playingSet.size(); j++)
                        {
                            if (scoreResult[j] >= 3)
                            {
                                roundScore = roundScore + (100 * (j + 1));

                                if (scoreResult[j] >= 4)
                                {
                                    roundScore = roundScore + ((scoreResult[j] - 3) * (100 * (j + 1)));
                                }
                            }
                        }

                        //checks for either a 1 or 5 rolled to continue     
                        if (scoreResult[0] > 0 && scoreResult[0] < 3)
                        {
                            roundScore = roundScore + (scoreResult[0] * 100);
                        }
                        if (scoreResult[4] > 0 && scoreResult[4] < 3)
                        {
                            roundScore = roundScore + (scoreResult[4] * 50);
                        } else if (roundScore == 0)
                        {
                            
                        }

                    }

                }
            }
        }
        
        return roundScore;

    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }
    
    
}