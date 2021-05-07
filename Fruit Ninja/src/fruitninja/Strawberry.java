package fruitninja;


import fruitninja.GameObject;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Strawberry implements GameObject{
    
    private int xLoc = 10;
    private int yLoc = 500;
    private int initvel=6;
    private int fallvel=10;
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
        return Enum.Strawberry;
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
        return 120;
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
        Image img = new Image("strawberry.png");
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
        Image img = new Image("strawberrysliced.png");
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