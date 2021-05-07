package fruitninja;

public class SliceCommand implements Command {
    private GameObject gameobj;
public SliceCommand(){
    
} 
public void setObject(GameObject gameobj)
{
    this.gameobj=gameobj;
}
    @Override
    public void execute() {
        gameobj.slice();
    }
    
}
