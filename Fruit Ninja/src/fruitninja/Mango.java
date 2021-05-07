package fruitninja;


import fruitninja.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import static javax.lang.model.element.ElementKind.ENUM;
import static jdk.nashorn.internal.parser.TokenType.ENUM;


public class Mango implements GameObject{
    
    private int xLoc = 10;
    private int yLoc = 500;
    private boolean movedOffScreen;
    private boolean sliceObject=false;
    private Boolean reachedtop=false;
    private int initvel=4;
    private int fallvel=6;
    private List<Observer> myobservers=new ArrayList<Observer>();
    private Boolean scoring=true;
     private Boolean living=true;

    @Override
    public Boolean getLiving() {
        return living;
    }

    @Override

    public void setLiving(Boolean living) {
        this.living = living;
    }

    @Override
    public Enum getObjectType() {
        return Enum.Mango;
    }
    
    @Override
     public void setScoring(Boolean scoring) {
        this.scoring = scoring;
    }
      @Override
    public Boolean getScoring() {
        return scoring;
    }
    @Override
    public int getXlocation() {
        return xLoc;
    }

    @Override
    public int getYlocation() {
        return yLoc;
    }

    @Override
    public int getMaxHeight() {
        return 100;
    }

    @Override
    public int getInitialVelocity() {
       return initvel;
    }

    @Override
    public int getFallingVelocity() {
      return fallvel;
    }

    @Override
    public Boolean isSliced() {
        return sliceObject;
    }

    @Override
    public Boolean hasMovedOffScreen() {
        return this.movedOffScreen;
    }

    @Override
    public void slice() {
        sliceObject=true;
    }

    @Override
    public Image getImage()
    {
        Image img = new Image("mango.png");
        return img;
    }
    
        @Override
    public void setXlocation(int x) {
        this.xLoc=x;
        
    }

    @Override
    public void setYlocation(int y) {
        this.yLoc=y;
    }
    
      @Override
    public void setInitalVelocity(int y) {
        initvel=y;
    }

    @Override
    public void setFallingVelocity(int y) {
        fallvel=y;
    }
    
    @Override
    public void setMovedOffScreen(boolean b) {
        this.movedOffScreen=b;
    }
    
    public Image getImageSliced()
    {
        Image img = new Image("mangosliced.png");
        return img;
    }

    @Override
    public void Register(Observer O) {
         myobservers.add(O);
    }

    @Override
    public void notifyAllobservers() {
         for(int ob=0;ob<myobservers.size();ob++)
            {
                    myobservers.get(ob).update(this);
            }
    }

     @Override
    public Boolean reachedtop() {
       return reachedtop;
    }

    @Override
    public void setreachedtop(Boolean reachedtop) {
        this.reachedtop=reachedtop;
    }
}