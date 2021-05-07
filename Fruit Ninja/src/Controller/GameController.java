
package Controller;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import fruitninja.Banana;
import fruitninja.BombFactory;
import fruitninja.Command;
import fruitninja.DangerousBomb;
import fruitninja.Dragonfruit;
import fruitninja.FatalBomb;
import fruitninja.FruitFactory;
import fruitninja.GameObject;
import fruitninja.Mango;
import fruitninja.ScoreObserver;
import fruitninja.SpecialFactory;
import fruitninja.Strategy;
import fruitninja.Strawberry;
import fruitninja.Watermelon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GameController implements GameAction {
    
 private Strategy strategy;
 private static GameController instance;
 private Command command;
 private int life=3;
 private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
 
 public static GameController getinstance(){
    if(instance==null)
        instance=new GameController();
    return instance;
}
private GameController(){
    
}
 @Override
    public void updateObjectsLocations(GameObject GO) {
      int yloc=GO.getYlocation();
      int xLoc=GO.getXlocation();
      int Initvel=GO.getInitialVelocity();
      int Fallvel=GO.getFallingVelocity();
       if(yloc<GO.getMaxHeight())
           GO.setreachedtop(true);
        if(!GO.reachedtop())
        {
            yloc-=Initvel;
            xLoc+=2;
            GO.setYlocation(yloc);
            GO.setXlocation(xLoc);
        }
        else
        {
            yloc+=Fallvel;
            xLoc+=2;
            GO.setYlocation(yloc);
            GO.setXlocation(xLoc);
        }
        if(yloc>600 && GO.reachedtop()==true)
        {
            GO.setMovedOffScreen(true);
        }    
        else
        {   
            GO.setMovedOffScreen(false);
        }    
    }

    @Override
    public void saveScore(int score) {
         DocumentBuilderFactory documentbuilderfactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder doc=documentbuilderfactory.newDocumentBuilder();
            Document document=doc.newDocument();
            Element root=document.createElement("Fruit");
            Element secondElement=document.createElement("Score");
            String s=Integer.toString(score);
            secondElement.setAttribute("score", s);
            root.appendChild(secondElement);
            document.appendChild(root);
            OutputFormat f=new OutputFormat(document);
            f.setIndenting(true);
            File file=new File("Score.xml");
            FileOutputStream s6=new FileOutputStream(file);
            XMLSerializer d=new XMLSerializer(s6,f); 
            d.serialize(document);
            
    } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (FileNotFoundException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }}

    @Override
    public int loadScore() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int sc=0;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("Score.xml");
            NodeList p = doc.getElementsByTagName("Score");
            
            for(int i=0;i<p.getLength();i++)
            {   
                Node something = p.item(i);
                if(something.getNodeType()==Node.ELEMENT_NODE){
                    Element person=(Element) something;
                    String s = person.getAttribute("score");
                    sc=Integer.parseInt(s);
            }
            
            }
    } catch (SAXException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }
        return sc;    
    }
    
    @Override
    public void saveArcadeScore(int score) {
         DocumentBuilderFactory documentbuilderfactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder doc=documentbuilderfactory.newDocumentBuilder();
            Document document=doc.newDocument();
            Element root=document.createElement("Fruit");
            Element secondElement=document.createElement("Score");
            String s=Integer.toString(score);
            secondElement.setAttribute("score", s);
            root.appendChild(secondElement);
            document.appendChild(root);
            OutputFormat f=new OutputFormat(document);
            f.setIndenting(true);
            File file=new File("ArcadeScore.xml");
            FileOutputStream s6=new FileOutputStream(file);
            XMLSerializer d=new XMLSerializer(s6,f); 
            d.serialize(document);
            
    } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (FileNotFoundException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }}

    @Override
    public int loadArcadeScore() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int sc=0;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("ArcadeScore.xml");
            NodeList p = doc.getElementsByTagName("Score");
            
            for(int i=0;i<p.getLength();i++)
            {   
                Node something = p.item(i);
                if(something.getNodeType()==Node.ELEMENT_NODE){
                    Element person=(Element) something;
                    String s = person.getAttribute("score");
                    sc=Integer.parseInt(s);
            }
            
            }
    } catch (SAXException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }
        return sc;    
    }
    
    
    @Override
    public void saveGame(int score,int life,int time) {
        DocumentBuilderFactory documentbuilderfactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder doc=documentbuilderfactory.newDocumentBuilder();
            Document document=doc.newDocument();
            Element root=document.createElement("Fruit");
            Element mainElement=document.createElement("Item");
            String s1=Integer.toString(score);
            String s2=Integer.toString(life);
            String s3=Integer.toString(time);
            mainElement.setAttribute("score", s1);
            mainElement.setAttribute("lives", s2);
            mainElement.setAttribute("time", s3);
            root.appendChild(mainElement);
            document.appendChild(root);
            OutputFormat f=new OutputFormat(document);
            f.setIndenting(true);
            File file=new File("Game.xml");
            FileOutputStream s6=new FileOutputStream(file);
            XMLSerializer d=new XMLSerializer(s6,f); 
            d.serialize(document);
            
    } catch (FileNotFoundException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }

    @Override
    public int loadGame() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int sc=1;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("Game.xml");
            NodeList p = doc.getElementsByTagName("Item");
            
            for(int i=0;i<p.getLength();i++)
            {   
                Node something = p.item(i);
                if(something.getNodeType()==Node.ELEMENT_NODE){
                    Element person=(Element) something;
                    String s = person.getAttribute("score");
                    sc=Integer.parseInt(s);
                    String s1=person.getAttribute("lives");
                    this.life=Integer.parseInt(s1);  
                    String s2=person.getAttribute("time");
                    this.time=Integer.parseInt(s2); 
            }
            
            }
    } catch (SAXException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }
        return sc;   
    }

    
    @Override
    public void saveArcadeGame(int score,int life,int time) {
        DocumentBuilderFactory documentbuilderfactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder doc=documentbuilderfactory.newDocumentBuilder();
            Document document=doc.newDocument();
            Element root=document.createElement("Fruit");
            Element mainElement=document.createElement("Item");
            String s1=Integer.toString(score);
            String s2=Integer.toString(life);
            String s3=Integer.toString(time);
            mainElement.setAttribute("score", s1);
            mainElement.setAttribute("lives", s2);
            mainElement.setAttribute("time", s3);
            root.appendChild(mainElement);
            document.appendChild(root);
            OutputFormat f=new OutputFormat(document);
            f.setIndenting(true);
            File file=new File("GameArcade.xml");
            FileOutputStream s6=new FileOutputStream(file);
            XMLSerializer d=new XMLSerializer(s6,f); 
            d.serialize(document);
            
    } catch (FileNotFoundException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }

    @Override
    public int loadArcadeGame() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int sc=1;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("GameArcade.xml");
            NodeList p = doc.getElementsByTagName("Item");
            
            for(int i=0;i<p.getLength();i++)
            {   
                Node something = p.item(i);
                if(something.getNodeType()==Node.ELEMENT_NODE){
                    Element person=(Element) something;
                    String s = person.getAttribute("score");
                    sc=Integer.parseInt(s);
                    String s1=person.getAttribute("lives");
                    this.life=Integer.parseInt(s1); 
                    String s2=person.getAttribute("time");
                    this.time=Integer.parseInt(s2); 
            }
            
            }
    } catch (SAXException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
     }
        return sc;   
    }
    
    @Override
    public void ResetGame(ScoreObserver scoring) {
       scoring.setScore(0);
       life=3;
       time=0;
       
    }
     public void update(GameObject gameobj) {
     if(life>0){
       if(gameobj instanceof DangerousBomb && gameobj.isSliced()==true)
       { life=life-1;
           gameobj.setLiving(false);
       }
       
       else if(gameobj instanceof FatalBomb && gameobj.isSliced()==true)
       { 
       life=0; gameobj.setLiving(false);}
       if(gameobj instanceof Banana){
           {
               
               if(gameobj.hasMovedOffScreen()==true && gameobj.isSliced()==false)
                
               { life=life-1;
                   gameobj.setLiving(false);
               }
              
           }
      }
      else if(gameobj instanceof Mango){
           {
               if(gameobj.hasMovedOffScreen()==true && gameobj.isSliced()==false)
               { life=life-1;
                   gameobj.setLiving(false);
               }
           }
      }
       else if(gameobj instanceof Dragonfruit){
           {
               if(gameobj.hasMovedOffScreen()==true && gameobj.isSliced()==false)
               { life=life-1;
                  gameobj.setLiving(false);
               }
           }
      }
       else if(gameobj instanceof Strawberry){
           {
               if(gameobj.hasMovedOffScreen()==true && gameobj.isSliced()==false)
               { life=life-1;
                   gameobj.setLiving(false);
               }
           }
      }
       else if(gameobj instanceof Watermelon){
           {
               if(gameobj.hasMovedOffScreen()==true && gameobj.isSliced()==false)
               { life=life-1;
                   gameobj.setLiving(false);
               }
           }
      }
       else if(gameobj instanceof Banana){
           {
               if(gameobj.hasMovedOffScreen()==true && gameobj.isSliced()==false)
               { life=life-1;
                   gameobj.setLiving(false);
               }
           }
      }
    
    }
    }
    public int getLife() {
    
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
   public void Context(Strategy strategy){
      this.strategy = strategy;
   }

 
   public List<GameObject> executeStrategy(){
      return strategy.createList();
   }

   @Override
    public void setCommand(Command command){
       this.command=command;  
    }
    @Override
    public void pressButton(){
        command.execute();
    }

}

