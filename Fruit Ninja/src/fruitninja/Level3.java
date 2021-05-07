
package fruitninja;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level3 implements Strategy{

    @Override
    public List<GameObject> createList() {
         FruitFactory fruitfactory = new FruitFactory();
         SpecialFactory special = new SpecialFactory();
         BombFactory bombFactory = new BombFactory();
          Random random = new Random();
        List<GameObject> obj = new ArrayList<GameObject>();
      int y=random.nextInt(5);
      if(y==0)
          y=2;
       for(int i=0;i<y;i++)
       {int x= random.nextInt(8);
       if(x==0)
       {
           GameObject GO= bombFactory.getBombs("Dangerous");
           GO.setFallingVelocity(GO.getFallingVelocity()+2);
           GO.setInitalVelocity(GO.getInitialVelocity()+2);
           obj.add(GO);
       }if(x==1)
       {
           GameObject GO= bombFactory.getBombs("Fatal");
           GO.setFallingVelocity(GO.getFallingVelocity()+2);
           GO.setInitalVelocity(GO.getInitialVelocity()+2);
           obj.add(GO); 
       }if(x==2)
       {
           
             GameObject GO= fruitfactory.getFruits("Mango");
           GO.setFallingVelocity(GO.getFallingVelocity()+2);
           GO.setInitalVelocity(GO.getInitialVelocity()+2);
            obj.add(GO);
       }if(x==3)
       {
          
           GameObject GO= fruitfactory.getFruits("Watermelon");
           GO.setFallingVelocity(GO.getFallingVelocity()+2);
           GO.setInitalVelocity(GO.getInitialVelocity()+2);
            obj.add(GO);
            
       }
       if(x==4)
       {
           
             GameObject GO= fruitfactory.getFruits("Banana");
            GO.setFallingVelocity(GO.getFallingVelocity()+2);
            GO.setInitalVelocity(GO.getInitialVelocity()+2);
            obj.add(GO);
        
    }
       if(x==5)
       {
           GameObject GO= special.getSpecialFruits("Strawberry");
           GO.setFallingVelocity(GO.getFallingVelocity()+2);
           GO.setInitalVelocity(GO.getInitialVelocity()+2);
           obj.add(GO);
        
    }
       if(x==6)
       {
         GameObject GO= special.getSpecialFruits("Dragonfruit");
         GO.setFallingVelocity(GO.getFallingVelocity()+2);
         GO.setInitalVelocity(GO.getInitialVelocity()+2);
         obj.add(GO);
        
    }
       if(x==7)
       { GameObject GO= special.getSpecialFruits("AppleBAM");
         GO.setFallingVelocity(GO.getFallingVelocity()+2);
         GO.setInitalVelocity(GO.getInitialVelocity()+2);
         obj.add(GO);
       }
     }
     return obj;
    }
}