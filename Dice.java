package zoughted;

import java.util.*;

public class Dice 
{
    private int chosenNumber[] = new int[6];
    private boolean keepArray[] = new boolean[6];
    private List<Integer> playingSet = new ArrayList<>(
        Arrays.asList(1, 2, 3, 4, 5, 6));
    private List<Integer> scoringSet = new ArrayList<>(
        Arrays.asList(1, 2, 3, 4, 5, 6));
    private boolean addToTotal;
    
    Scanner in = new Scanner(System.in);
    Random r = new Random();  
    
    public void initializeDice()
    {
        for(int x = 0; x < 6; x++)
        {
            keepArray[x] = false;
        }
    }

    public boolean whichDice()
   {
       String keepScore;
       addToTotal = false;

       for(int x = 0; x < 1; x++)
       {
           keepScore = in.nextLine();
           
           switch(keepScore)
           {
               case "re-roll": addToTotal = false;
                               break;
               case "add":     addToTotal = true;
                               break;
               default:        x--;
           }
       }   
           return addToTotal;
           
   }
    
    public boolean getKeepArray(int x)
    {
        return keepArray[x];
    }
    public boolean[] getKeepArray() {
        return keepArray;
    }
    
    public int[] getChosenNumber() {
        return chosenNumber;
    }

    public void setChosenNumber(int[] chosenNumber) {
        this.chosenNumber = chosenNumber;
    }



    public void setKeepArray(int position, boolean keepArray) {
        this.keepArray[position] = keepArray;
    }

    public List<Integer> getPlayingSet() {
        return playingSet;
    }

    public void setPlayingSet(int position, int num) {
        playingSet.set(position, num);
    }

    public int getScoringSet( int x) {
        return scoringSet.get(x);
    }

    public List<Integer> getScoringSet() {
        return playingSet;
    }
    
    public List<Integer> setScoring(ArrayList<Integer> playingSet) 
    {
        for(int x = 0; x < 6; x++)
        {
            if(keepArray[x])
            {
                scoringSet.set(x, 0);
            }
            else
            {
                scoringSet.set(x, playingSet.get(x));
            }
        }
        
        return scoringSet;
    }

    public boolean isAddToTotal() {
        return addToTotal;
    }

    public void setAddToTotal(boolean addToTotal) {
        this.addToTotal = addToTotal;
    }
    
    public int getPlayingSet(int x) {
        return playingSet.get(x);
    }
    

}
