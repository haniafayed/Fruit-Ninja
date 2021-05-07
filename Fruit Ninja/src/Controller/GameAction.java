package Controller;
import fruitninja.Command;
import fruitninja.GameObject;
import fruitninja.ScoreObserver;

public interface GameAction {
    
public void updateObjectsLocations(GameObject GO);

public void saveGame(int score,int life,int time);

public int loadGame();

public void saveArcadeGame(int score,int life,int time);

public int loadArcadeGame();

public void ResetGame(ScoreObserver scoring);

public void setCommand(Command command);

public void pressButton();

public void saveScore(int score);

public int loadScore();

public void saveArcadeScore(int score);

public int loadArcadeScore();

}
