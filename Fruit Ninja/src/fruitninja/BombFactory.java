package fruitninja;


public class BombFactory {
    
    public GameObject getBombs(String type){
       if(type.equals(null))
            return null; 
        if(type.equals("Dangerous")){
            return new DangerousBomb();
        }
        else if(type.equals("Fatal"))
            return new FatalBomb();
        else if(type.equals("ScoreBomb"))
            return new ScoreBomb();
        return null;
    }
    
}
