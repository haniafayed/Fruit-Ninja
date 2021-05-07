/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   package fruitninja;


import fruitninja.Enum;
import fruitninja.GameObject;
import fruitninja.Observer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;


/**
 *
 * @author Lap
 */
public class AppleBAM implements GameObject{
    
    private int xLoc = 10;
    private int yLoc = 500;
    private int initvel=4;
    private int fallvel=7;
    private boolean movedOffScreen;
    private boolean sliceObject=false;
    private List<Observer> myobservers=new ArrayList<Observer>();
    private Boolean reachedtop=false;
    private Boolean scoring=true;
     private Boolean living=true;

    public Boolean getLiving() {
        return living;
    }

    public void setLiving(Boolean living) {
        this.living = living;
    }
   
    @Override
    public Enum getObjectType() {
       return Enum.Banana;
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
        return 140;
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
        return movedOffScreen;
    }

    @Override
    public void slice() {
        sliceObject=true;
    }

    @Override
    public Image getImage()
    {
        Image img = new Image("red.png");
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
        Image img = new Image("bit.png");
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

    @Override
    public void setScoring(Boolean scoring) {
        this.scoring=scoring;
    }

    @Override
    public Boolean getScoring() {
        return scoring;
    }

}
