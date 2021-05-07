package fruitninja;


import fruitninja.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import static javax.lang.model.element.ElementKind.ENUM;
import static jdk.nashorn.internal.parser.TokenType.ENUM;


public class Watermelon implements GameObject {
    
    private int xLoc = 10;
    private int yLoc = 500;
    private int initvel=6;
    private int fallvel=8;
    private boolean movedOffScreen;
    private boolean sliceObject=false;
    private Boolean reachedtop=false;
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
     public void setScoring(Boolean scoring) {
        this.scoring = scoring;
    }
      @Override
    public Boolean getScoring() {
        return scoring;
    }
    @Override
    public Enum getObjectType() {
        return Enum.Watermelon;
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
        return 80;
    }

    @Override
    public int getInitialVelocity() {
       return 7;
    }

    @Override
    public int getFallingVelocity() {
      return 10;
    }

    @Override
    public Boolean isSliced() {
        return sliceObject;
    }

    @Override
    public Boolean hasMovedOffScreen() {
        return movedOffScreen;
    }

    @Override
    public void slice() {
        sliceObject=true;
    }

    @Override
    public Image getImage()
    {
        Image img = new Image("watermelon.png");
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
    @Override
    public Image getImageSliced()
    {
        Image img = new Image("watermelonsliced.png");
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