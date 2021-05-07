package fruitninja;


import fruitninja.Banana;


public class FruitFactory {
    
    public GameObject getFruits(String type){
        if(type.equals(null))
            return null; 
        if(type.equals("Mango")){
            return new Mango();
        }
        else if(type.equals("Banana"))
            return new Banana();
        else if(type.equals("Watermelon"))
            return new Watermelon();
        
        return null;
    }
    
}
