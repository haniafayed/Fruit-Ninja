package fruitninja;



public class ScoreObserver implements Observer{
private int score;

    @Override
    public void update(GameObject gameobj) {
    if(score>=0)
     { if(gameobj.getObjectType()==Enum.Banana && gameobj.isSliced()==true){
          score+=10;
          gameobj.setScoring(false);
           
      }
      else if(gameobj.getObjectType()==Enum.Mango && gameobj.isSliced()==true){
          score+=10;
          gameobj.setScoring(false);
      }
      else if(gameobj.getObjectType()==Enum.Watermelon && gameobj.isSliced()==true){
          score+=10;
          gameobj.setScoring(false);
      }
      else if(gameobj.getObjectType()==Enum.Strawberry && gameobj.isSliced()==true){
          score+=25;
          gameobj.setScoring(false);
      }
      else if(gameobj.getObjectType()==Enum.Dragonfruit && gameobj.isSliced()==true){
          score+=25;
          gameobj.setScoring(false);
      }
      else if(gameobj.getObjectType()==Enum.ScoreBomb && gameobj.isSliced()==true)
      {
          score-=10;
          gameobj.setScoring(false);
      }
      else if(gameobj.getObjectType()==Enum.AppleBAM && gameobj.isSliced()==true)
          score+=30;
          gameobj.setScoring(false);
     }
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score){
        this.score=score;
    }
   
    
}
