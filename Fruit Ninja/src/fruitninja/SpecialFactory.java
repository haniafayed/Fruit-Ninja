package fruitninja;


import fruitninja.Dragonfruit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class SpecialFactory {
    public GameObject getSpecialFruits(String type){
        if(type.equals(null))
            return null; 
        if(type.equals("Strawberry")){
            return new Strawberry();
        }
        else if(type.equals("Dragonfruit"))
            return new Dragonfruit();
        else if(type.equals("AppleBAM"))
            return new AppleBAM();
        return null;
    }
    
}
