/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lap
 */
public class Level2 implements Strategy{

    @Override
    public List<GameObject> createList()
    {
      Random random = new Random();
      List<GameObject> obj = new ArrayList<GameObject>();
      SpecialFactory special = new SpecialFactory();
      int y=random.nextInt(5);
      if(y==0)
          y=2;
       for(int i=0;i<y;i++)
       {
       int x= random.nextInt(8);
       if(x==0)
       {
           BombFactory bombFactory = new BombFactory();
           obj.add((GameObject) bombFactory.getBombs("Dangerous"));
       }if(x==1)
       {
           BombFactory bombFactory = new BombFactory();
            obj.add((GameObject) bombFactory.getBombs("Fatal"));
       }if(x==2)
       {
           FruitFactory fruitfactory = new FruitFactory();
            obj.add((GameObject) fruitfactory.getFruits("Mango"));
       }if(x==3)
       {
           FruitFactory fruitfactory = new FruitFactory();
            obj.add((GameObject) fruitfactory.getFruits("Banana"));
       }
       if(x==4)
       {
           FruitFactory fruitfactory = new FruitFactory();
            obj.add((GameObject) fruitfactory.getFruits("Watermelon"));
        
    }
       if(x==5)
       {
           
           obj.add((GameObject) special.getSpecialFruits("Strawberry"));
        
    }
       if(x==6)
       {
           
           obj.add((GameObject) special.getSpecialFruits("Dragonfruit"));
        
    }
       if(x==7)
           
         obj.add(special.getSpecialFruits("AppleBAM"));
       }
     return obj;
    }
    
}
