package fruitninja;

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
public interface GameObject {

public Enum getObjectType();

public int getXlocation();

public void setXlocation(int x);

public int getYlocation();

public void setYlocation(int y);

public int getMaxHeight();

public int getInitialVelocity();

public int getFallingVelocity();

public Boolean isSliced();

public Boolean hasMovedOffScreen();

public void setMovedOffScreen(boolean b);

public void setLiving(Boolean living);

public Boolean getLiving() ;

public Image getImageSliced();

public void slice();

public void setScoring(Boolean scoring);

public Boolean getScoring();

public Image getImage();

public void setInitalVelocity(int y);

public void setFallingVelocity(int y);

 public void Register(Observer O);
 
public void notifyAllobservers();

public Boolean reachedtop();

public void setreachedtop(Boolean reachedtop);

}