/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.GameController;
import fruitninja.AppleBAM;
import fruitninja.Arcade;
import fruitninja.DangerousBomb;
import static fruitninja.Enum.ScoreBomb;
import fruitninja.FatalBomb;
import fruitninja.GameObject;
import fruitninja.Level1;
import fruitninja.Level2;
import fruitninja.Level3;
import fruitninja.ScoreBomb;
import fruitninja.ScoreObserver;
import fruitninja.SliceCommand;
import fruitninja.Strategy;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author hania
 */
public class FXMLDocumentController implements Initializable {

    boolean arcadeOn = false;
    @FXML ImageView backtomodes;
    @FXML ImageView backtolevels1;
    @FXML ImageView backtolevels2;
    @FXML ImageView backtolevels3;
    @FXML ImageView backtoarcade;
    @FXML ImageView exit;
    Music musicobject = new Music();

    public void newGameButtonPushed(ActionEvent event) throws IOException {
        Parent tuna = FXMLLoader.load(getClass().getResource("Modes.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        musicobject.playStartGame();
    }

    public void classicButtonPushed(ActionEvent event) throws IOException {

        Parent burger = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        Scene scene2 = new Scene(burger);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

    }

    public void arcadeButtonPushed(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        Strategy arc = new Arcade();
        game.Context(arc);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(190);
        scoreValue.setY(100);

        Text time = new Text(730, 50, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);
        
        Text time2 = new Text(900, 50, "60");
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        root.getChildren().add(time2);

        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);

        AnimationTimer timer = new AnimationTimer() {
            int nona = Integer.parseInt("60");
            int counter = 60;
            boolean reachedTop = false;
            boolean movedOff = true;
            ScoreObserver scoreObserver = new ScoreObserver();
            int t = 0;
            List<GameObject> temps = game.executeStrategy();

            @Override
            public void handle(long now) {

                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    game.setTime(60);
                    nona=game.getTime();
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveArcadeGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
               counter--;
                if (counter == 0) {
                    if (nona == 0) {
                        try {
                            Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                            Scene scene2 = new Scene(burger);
                            Stage window = new Stage();
                            window.setScene(scene2);
                            window.show();
                            counter = 0;
                            movedOff = false;
                            temps.removeAll(temps);

                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        nona--;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    }
                }
                

                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        temps.get(i).Register(scoreObserver);
                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {
                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                        }
                    }
                    if (t == temps.size()) {
                        movedOff = true;
                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                for (int i = 0; i < temps.size(); i++) {
                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {
                                        Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                        Scene scene2 = new Scene(burger);
                                        musicobject.playArcade();
                                        Stage window = new Stage();
                                        window.setScene(scene2);
                                        window.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof ScoreBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                }
                                else if(temps.get(j) instanceof ScoreBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }else
                                {
                                
                                musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                
                                }
                            }

                        }
                    }
                });

            }

        };
        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();

    }

     public void loadArcadeButton(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        Strategy arc = new Arcade();
        game.Context(arc);
        int tempsth=game.loadArcadeGame();
        int nonatemp=game.getTime();

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(190);
        scoreValue.setY(100);

        Text time = new Text(730, 50, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text();
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        time2.setX(900);
        time2.setY(50);
        time2.setText(Integer.toString(nonatemp));
        root.getChildren().add(time2);

        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadArcadeScore());
        highScoreValue.setText(s);
        
        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);

        AnimationTimer timer = new AnimationTimer() {
            
            int nona=nonatemp;
            int counter = 60;
            boolean reachedTop = false;
            boolean movedOff = true;
            boolean getScore=true;
            SliceCommand Sc = new SliceCommand();
            ScoreObserver scoreObserver = new ScoreObserver();
            int t = 0;
            List<GameObject> temps = game.executeStrategy();

            @Override
            public void handle(long now) {
                
                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveArcadeGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                
                if(getScore)
                {
                    getScore=false;
                    scoreObserver.setScore(tempsth);
                }

                counter--;
                if (counter == 0) {
                    if (nona == 0) {
                        try {
                            Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                            Scene scene2 = new Scene(burger);
                            Stage window = new Stage();
                            window.setScene(scene2);
                            window.show();
                            counter = 0;
                            movedOff = false;
                            temps.removeAll(temps);

                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        nona--;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    }
                }

                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        temps.get(i).Register(scoreObserver);
                        temps.get(i).setXlocation(10 + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {
                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                        }
                    }
                    if (t == temps.size()) {
                        movedOff = true;
                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                for (int i = 0; i < temps.size(); i++) {
                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveArcadeScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {
                                        Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                        Scene scene2 = new Scene(burger);
                                        musicobject.playArcade();
                                        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                        window.setScene(scene2);
                                        window.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof ScoreBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                }
                                else if(temps.get(j) instanceof ScoreBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }else
                                {
                                
                                musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                
                                }
                            }

                        }
                    }
                });

            }

        };
        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();

    }
     
     public void popUpArcade(ActionEvent event) throws IOException {
        Parent tuna = FXMLLoader.load(getClass().getResource("popUpArcade.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void easyButtonPushed(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        //SliceCommand sc;
        Strategy level1 = new Level1();
        game.Context(level1);

        Image life1 = new Image("ninja.png");
        Image life2 = new Image("ninja.png");
        Image life3 = new Image("ninja.png");
        gc.drawImage(life1, 400, 10);
        gc.drawImage(life2, 430, 10);
        gc.drawImage(life3, 460, 10);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(170);
        scoreValue.setY(100);
        scoreValue.setText("0");

        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);
        
        Text time = new Text(500, 55, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text(670,55, "0");
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        root.getChildren().add(time2);
        
        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);

        AnimationTimer timer = new AnimationTimer() {
            
            int nona = Integer.parseInt("0");
            int counter = 60;
            boolean reachedTop = false;
            boolean movedOff = true;
            int t = 0;
            List<GameObject> temps = game.executeStrategy();
            ScoreObserver scoreObserver = new ScoreObserver();

            @Override
            public void handle(long now) {

                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                counter--;
                if (counter == 0) {
                        nona++;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    }
                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        if (!(temps.get(i) instanceof DangerousBomb) && !(temps.get(i) instanceof FatalBomb)) {
                            temps.get(i).Register(scoreObserver);
                        }

                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {

                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                            if (temps.get(t).getLiving()) {
                                game.update(temps.get(t));
                            }
                        }

                    }
                    if (t == temps.size()) {
                        movedOff = true;

                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                if (game.getLife() == 3) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                    gc.drawImage(life3, 900, 10);
                }
                if (game.getLife() == 2) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                }
                if (game.getLife() == 1) {
                    gc.drawImage(life1, 800, 10);
                }
                if (game.getLife() == 0) {
                    try {
                           Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                           Scene scene2 = new Scene(burger);
                           Stage window2 = new Stage();
                           window2.setScene(scene2);
                           window2.show();
                        } catch (IOException ex) { 
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stop();
                }

                for (int i = 0; i < temps.size(); i++) {

                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }

                }

                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {musicobject.playArcade();
                                    Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                    Scene scene2 = new Scene(burger);
                                    Stage window2 = new Stage();
                                    window2.setScene(scene2);
                                    window2.show();
                                    
                                    } catch (IOException ex) { 
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    stop();
                                }
                                else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof FatalBomb) && !(temps.get(r) instanceof DangerousBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                }else if(temps.get(j) instanceof FatalBomb||temps.get(j) instanceof DangerousBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }else{
                                
                                musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                            }

                        }
                    }
                });

            }

        };

        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();

    }

    public void mediumButtonPushed(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        SliceCommand sc;
        Strategy level2 = new Level2();
        game.Context(level2);

        Image life1 = new Image("ninja.png");
        Image life2 = new Image("ninja.png");
        Image life3 = new Image("ninja.png");
        gc.drawImage(life1, 400, 20);
        gc.drawImage(life2, 430, 20);
        gc.drawImage(life3, 460, 20);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(170);
        scoreValue.setY(100);

        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);

        Text time = new Text(500, 55, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text(670, 55, "0");
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        root.getChildren().add(time2);
        
        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);

        AnimationTimer timer = new AnimationTimer() {
            
            int nona = Integer.parseInt("0");
            int counter = 60;
            boolean reachedTop = false;
            boolean movedOff = true;
            int t = 0;
            List<GameObject> temps = game.executeStrategy();
            ScoreObserver scoreObserver = new ScoreObserver();

            @Override
            public void handle(long now) {

                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    System.out.println("NONA"+nona);
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                counter--;
                if (counter == 0) {
                        nona++;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    
                }
                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        //temps.get(i).Register(livesObserver);
                        if (!(temps.get(i) instanceof DangerousBomb) && !(temps.get(i) instanceof FatalBomb)) {
                            temps.get(i).Register(scoreObserver);
                        }

                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {

                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                            if (temps.get(t).getLiving()) {
                                game.update(temps.get(t));
                            }
                        }

                    }
                    if (t == temps.size()) {
                        movedOff = true;

                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                if (game.getLife() == 3) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                    gc.drawImage(life3, 900, 10);
                }
                if (game.getLife() == 2) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                }
                if (game.getLife() == 1) {
                    gc.drawImage(life1, 800, 10);
                }
                if (game.getLife() == 0) {
                    try {
                           Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                           Scene scene2 = new Scene(burger);
                           Stage window2 = new Stage();
                           window2.setScene(scene2);
                           window2.show();
                        } catch (IOException ex) { 
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stop();
                }

                for (int i = 0; i < temps.size(); i++) {

                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }

                }

                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {musicobject.playArcade();
                                    Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                    Scene scene2 = new Scene(burger);
                                    Stage window2 = new Stage();
                                    window2.setScene(scene2);
                                    window2.show();
                                    } catch (IOException ex) { 
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    stop();
                                }else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof FatalBomb) && !(temps.get(r) instanceof DangerousBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                } 
                                else if(temps.get(j) instanceof FatalBomb|| temps.get(j) instanceof DangerousBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                                else{
                                    musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                            }

                        }
                    }
                });

            }

        };

        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();

    }

    public void difficultButton(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        SliceCommand sc;
        Strategy level3 = new Level3();
        game.Context(level3);

        Image life1 = new Image("ninja.png");
        Image life2 = new Image("ninja.png");
        Image life3 = new Image("ninja.png");
        gc.drawImage(life1, 400, 20);
        gc.drawImage(life2, 430, 20);
        gc.drawImage(life3, 460, 20);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(170);
        scoreValue.setY(100);

        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);
        
        Text time = new Text(500, 55, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text(670, 55, "0");
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        root.getChildren().add(time2);
        
        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);

        AnimationTimer timer = new AnimationTimer() {

            int nona = Integer.parseInt("0");
            int counter = 60;
            boolean reachedTop = false;
            boolean movedOff = true;
            int t = 0;
            List<GameObject> temps = game.executeStrategy();
            ScoreObserver scoreObserver = new ScoreObserver();

            @Override
            public void handle(long now) {

                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    System.out.println("NONA"+nona);
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                 counter--;
                if (counter == 0) {
                        nona++;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    
                }
                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        //temps.get(i).Register(livesObserver);
                        if (!(temps.get(i) instanceof DangerousBomb) && !(temps.get(i) instanceof FatalBomb)) {
                            temps.get(i).Register(scoreObserver);
                        }

                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {

                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                            if (temps.get(t).getLiving()) {
                                game.update(temps.get(t));
                            }
                        }

                    }
                    if (t == temps.size()) {
                        movedOff = true;

                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                if (game.getLife() == 3) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                    gc.drawImage(life3, 900, 10);
                }
                if (game.getLife() == 2) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                }
                if (game.getLife() == 1) {
                    gc.drawImage(life1, 800, 10);
                }
            if(game.getLife()==0)
            {
               try {
                           Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                           Scene scene2 = new Scene(burger);
                           Stage window2 = new Stage();
                           window2.setScene(scene2);
                           window2.show();
                        } catch (IOException ex) { 
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stop();
            }

                for (int i = 0; i < temps.size(); i++) {

                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }

                }

                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {musicobject.playArcade();
                                    Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                    Scene scene2 = new Scene(burger);
                                    Stage window2 = new Stage();
                                    window2.setScene(scene2);
                                    window2.show();
                                    } catch (IOException ex) { 
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    stop();
                                }else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof FatalBomb) && !(temps.get(r) instanceof DangerousBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                } 
                                else if(temps.get(j) instanceof FatalBomb|| temps.get(j) instanceof DangerousBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                                else{
                                    musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                            }

                        }
                    }
                });

            }

        };

        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();

    }

    public void exitButton(ActionEvent event) throws IOException {
        System.exit(0);
    }
    public void loadLevel1(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        SliceCommand sc;
        Strategy level1 = new Level1();
        game.Context(level1);
        int tempsth=game.loadGame();
        int nonatemp=game.getTime();

        Image life1 = new Image("ninja.png");
        Image life2 = new Image("ninja.png");
        Image life3 = new Image("ninja.png");
        gc.drawImage(life1, 400, 20);
        gc.drawImage(life2, 430, 20);
        gc.drawImage(life3, 460, 20);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(170);
        scoreValue.setY(100);
        String s2=Integer.toString(game.loadGame());
 
        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);

        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);
        
        Text time = new Text(500, 55, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text();
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        time2.setX(670);
        time2.setY(55);
        time2.setText(Integer.toString(nonatemp));
        root.getChildren().add(time2);

        AnimationTimer timer = new AnimationTimer() {

            boolean reachedTop = false;
            boolean movedOff = true;
            boolean getScore=true;
            int nona = nonatemp;
            int counter = 60;
            int t = 0;
            List<GameObject> temps = game.executeStrategy();
            ScoreObserver scoreObserver = new ScoreObserver();

            @Override
            public void handle(long now) {
                
                if(getScore)
                {
                    scoreObserver.setScore(game.loadGame());
                    getScore=false;
                }
  
                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    System.out.println("NONA"+nona);
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                
                counter--;
                if (counter == 0) {

                        nona++;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    }
                
                
                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {

                        if (!(temps.get(i) instanceof DangerousBomb) && !(temps.get(i) instanceof FatalBomb)) {
                            temps.get(i).Register(scoreObserver);
                        }

                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }

                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {

                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                            if (temps.get(t).getLiving()) {
                                game.update(temps.get(t));
                            }
                        }

                    }
                    if (t == temps.size()) {
                        movedOff = true;

                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                if (game.getLife() == 3) {
                    gc.drawImage(life1, 800, 20);
                    gc.drawImage(life2, 850, 20);
                    gc.drawImage(life3, 900, 20);
                }
                if (game.getLife() == 2) {
                    gc.drawImage(life1, 800, 20);
                    gc.drawImage(life2, 850, 20);
                }
                if (game.getLife() == 1) {
                    gc.drawImage(life1, 800, 20);
                }
                 if (game.getLife() == 0) {
                    try {
                           Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                           Scene scene2 = new Scene(burger);
                           Stage window2 = new Stage();
                           window2.setScene(scene2);
                           window2.show();
                        } catch (IOException ex) { 
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stop();
                }

                for (int i = 0; i < temps.size(); i++) {

                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }

                }

                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {musicobject.playArcade();
                                    Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                    Scene scene2 = new Scene(burger);
                                    Stage window2 = new Stage();
                                    window2.setScene(scene2);
                                    window2.show();
                                    
                                    } catch (IOException ex) { 
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    stop();
                                }
                                else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof FatalBomb) && !(temps.get(r) instanceof DangerousBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                }else if(temps.get(j) instanceof FatalBomb||temps.get(j) instanceof DangerousBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }else{
                                
                                musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                            }

                        }
                    }
                });

            }

        };
        timer.start();
                
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }

    public void popUpLevel1(ActionEvent event) throws IOException {
        Parent tuna = FXMLLoader.load(getClass().getResource("popUpLevel1.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Stage window = new Stage();
        window.setScene(scene2);
        window.show();
        
    }
    
    public void loadLevel2(ActionEvent event) throws IOException {
        int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        SliceCommand sc;
        Strategy level2 = new Level2();
        game.Context(level2);
        int tempsth=game.loadGame();
        int nonatemp=game.getTime();

        Image life1 = new Image("ninja.png");
        Image life2 = new Image("ninja.png");
        Image life3 = new Image("ninja.png");
        gc.drawImage(life1, 400, 20);
        gc.drawImage(life2, 430, 20);
        gc.drawImage(life3, 460, 20);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(170);
        scoreValue.setY(100);
        String s2=Integer.toString(game.loadGame());
 
        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);

        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);
        
        Text time = new Text(500, 55, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text();
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        time2.setX(670);
        time2.setY(55);
        time2.setText(Integer.toString(nonatemp));
        root.getChildren().add(time2);

        AnimationTimer timer = new AnimationTimer() {

            boolean reachedTop = false;
            boolean movedOff = true;
            int nona = nonatemp;
            int counter = 60;
            int t = 0;
            List<GameObject> temps = game.executeStrategy();
            ScoreObserver scoreObserver = new ScoreObserver();

            @Override
            public void handle(long now) {

                scoreObserver.setScore(game.loadGame());
  
                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    System.out.println("NONA"+nona);
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                
                counter--;
                if (counter == 0) {

                        nona++;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    }
                
                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        if (!(temps.get(i) instanceof DangerousBomb) && !(temps.get(i) instanceof FatalBomb)) {
                            temps.get(i).Register(scoreObserver);
                        }

                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }
                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {

                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                            if (temps.get(t).getLiving()) {
                                game.update(temps.get(t));
                            }
                        }

                    }
                    if (t == temps.size()) {
                        movedOff = true;

                    }
                }

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                if (game.getLife() == 3) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                    gc.drawImage(life3, 900, 10);
                }
                if (game.getLife() == 2) {
                    gc.drawImage(life1, 800, 10);
                    gc.drawImage(life2, 850, 10);
                }
                if (game.getLife() == 1) {
                    gc.drawImage(life1, 800, 10);
                }
                if (game.getLife() == 0) {
                    try {
                           Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                           Scene scene2 = new Scene(burger);
                           Stage window2 = new Stage();
                           window2.setScene(scene2);
                           window2.show();
                        } catch (IOException ex) { 
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stop();
                
                }

                for (int i = 0; i < temps.size(); i++) {

                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }

                }
                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {musicobject.playArcade();
                                    Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                    Scene scene2 = new Scene(burger);
                                    Stage window2 = new Stage();
                                    window2.setScene(scene2);
                                    window2.show();
                                    } catch (IOException ex) { 
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    stop();
                                }else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof FatalBomb) && !(temps.get(r) instanceof DangerousBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                } 
                                else if(temps.get(j) instanceof FatalBomb|| temps.get(j) instanceof DangerousBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                                else{
                                    musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                            }

                        }
                    }
                });

            }

        };

        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }

    public void popUpLevel2(ActionEvent event) throws IOException {
        Parent tuna = FXMLLoader.load(getClass().getResource("popUpLevel1.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        
    }
    
    public void loadLevel3(ActionEvent event) throws IOException {
       int i;
        boolean reachedTop = false, movedOff = false;
        SliceCommand Sc=new SliceCommand();
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 680);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("bg3.png");
        gc.drawImage(img, 0, 0);
        GameController game = GameController.getinstance();
        SliceCommand sc;
        Strategy level3 = new Level3();
        game.Context(level3);
        int tempsth=game.loadGame();
        int nonatemp=game.getTime();

        Image life1 = new Image("ninja.png");
        Image life2 = new Image("ninja.png");
        Image life3 = new Image("ninja.png");
        gc.drawImage(life1, 400, 20);
        gc.drawImage(life2, 430, 20);
        gc.drawImage(life3, 460, 20);

        Text score = new Text(20, 100, "Score:");
        score.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        score.setFill(Color.YELLOWGREEN);
        root.getChildren().add(score);

        Text scoreValue = new Text();
        scoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        scoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(scoreValue);
        scoreValue.setX(170);
        scoreValue.setY(100);
        String s2=Integer.toString(game.loadGame());
 
        Text highScore = new Text(20, 50, "High Score:");
        highScore.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScore.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScore);

        Text highScoreValue = new Text();
        highScoreValue.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        highScoreValue.setFill(Color.YELLOWGREEN);
        root.getChildren().add(highScoreValue);
        highScoreValue.setX(290);
        highScoreValue.setY(50);
        String s = Integer.toString(game.loadScore());
        highScoreValue.setText(s);

        Image resetImg = new Image("reset3.png");
        ImageView reset = new ImageView(resetImg);
        root.getChildren().add(reset);
        reset.setX(900);
        reset.setY(600);

        Image saveImg = new Image("save2.png");
        ImageView save = new ImageView(saveImg);
        root.getChildren().add(save);
        save.setX(900);
        save.setY(500);

        Image exitImg = new Image("exit.png");
        ImageView exit = new ImageView(exitImg);
        root.getChildren().add(exit);
        exit.setX(20);
        exit.setY(550);
        
        Text time = new Text(500, 55, "Time:");
        time.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time.setFill(Color.RED);
        root.getChildren().add(time);

        Text time2 = new Text();
        time2.setFont(Font.font("forte", FontWeight.BOLD, FontPosture.REGULAR, 55));
        time2.setFill(Color.RED);
        time2.setX(670);
        time2.setY(55);
        time2.setText(Integer.toString(nonatemp));
        root.getChildren().add(time2);

        AnimationTimer timer = new AnimationTimer() {

            boolean reachedTop = false;
            boolean movedOff = true;
            int nona = nonatemp;
            int counter = 60;
            int t = 0;
            List<GameObject> temps = game.executeStrategy();
            ScoreObserver scoreObserver = new ScoreObserver();

            @Override
            public void handle(long now) {

                scoreObserver.setScore(game.loadGame());
  
                reset.setOnMouseClicked(e -> {
                    game.ResetGame(scoreObserver);
                    nona=game.getTime();
                    System.out.println("NONA"+nona);
                    time2.setText(Integer.toString(nona));
                    counter=60;
                });

                save.setOnMouseClicked(e -> {
                    game.saveGame(scoreObserver.getScore(), game.getLife(),nona);
                });

                exit.setOnMouseClicked(e -> {
                    System.exit(0);
                });
                
                counter--;
                if (counter == 0) {

                        nona++;
                        counter = 60;
                        String non = String.valueOf(nona);
                        time2.setText(non);

                    }
                
                if (movedOff) {
                    movedOff = false;
                    temps.removeAll(temps);
                    temps = game.executeStrategy();
                    Random random = new Random();
                    for (int i = 0; i < temps.size(); i++) {
                        if (!(temps.get(i) instanceof DangerousBomb) && !(temps.get(i) instanceof FatalBomb)) {
                            temps.get(i).Register(scoreObserver);
                        }

                        temps.get(i).setXlocation(temps.get(i).getXlocation() + random.nextInt(10) * 60);
                        temps.get(i).setYlocation(600 + random.nextInt(5) * 2);
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }
                }
                System.out.println("Score from file"+s2);
                for (int i = 0; i < temps.size(); i++) {
                    game.updateObjectsLocations(temps.get(i));
                    reachedTop = temps.get(i).reachedtop();
                    for (t = 0; t < temps.size(); t++) {
                        if (!temps.get(t).hasMovedOffScreen()) {

                            break;
                        } else {
                            if (temps.get(t).getScoring()) {
                                temps.get(t).notifyAllobservers();
                            }
                            if (temps.get(t).getLiving()) {
                                game.update(temps.get(t));
                            }
                        }

                    }
                    if (t == temps.size()) {
                        movedOff = true;

                    }
                }
                System.out.println("Score from file"+s2);

                gc.clearRect(0, 0, 1000, 680);
                gc.drawImage(img, 0, 0);
                if (game.getLife() == 3) {
                    gc.drawImage(life1, 800, 20);
                    gc.drawImage(life2, 850, 20);
                    gc.drawImage(life3, 900, 20);
                }
                if (game.getLife() == 2) {
                    gc.drawImage(life1, 800, 20);
                    gc.drawImage(life2, 850, 20);
                }
                if (game.getLife() == 1) {
                    gc.drawImage(life1, 800, 20);
                }
                if (game.getLife() == 0) {
                    try {
                           Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                           Scene scene2 = new Scene(burger);
                           Stage window2 = new Stage();
                           window2.setScene(scene2);
                           window2.show();
                        } catch (IOException ex) { 
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stop();
                
                }

                for (int i = 0; i < temps.size(); i++) {

                    if (temps.get(i).isSliced()) {
                        gc.drawImage(temps.get(i).getImageSliced(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    } else {
                        gc.drawImage(temps.get(i).getImage(), temps.get(i).getXlocation(), temps.get(i).getYlocation());
                    }

                }
                System.out.println("Score from file"+s2);
                scoreValue.setText(Integer.toString(scoreObserver.getScore()));
                if (Integer.parseInt(scoreValue.getText()) > Integer.parseInt(highScoreValue.getText())) {
                    game.saveScore(Integer.parseInt(scoreValue.getText()));
                    highScoreValue.setText(scoreValue.getText());
                }
                System.out.println("Score from file"+s2);
                canvas.setOnMouseDragged(e -> {
                    for (int j = 0; j < temps.size(); j++) {
                        int x = temps.get(j).getXlocation();
                        int y = temps.get(j).getYlocation();
                        if (e.getX() >= x && (int) e.getX() <= x + temps.get(j).getImage().getWidth()) {
                            if (e.getY() >= y && (int) e.getY() <= y + temps.get(j).getImage().getHeight()) {
                                if (temps.get(j) instanceof FatalBomb) {
                                    try {musicobject.playArcade();
                                    Parent burger = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                                    Scene scene2 = new Scene(burger);
                                    Stage window2 = new Stage();
                                    window2.setScene(scene2);
                                    window2.show();
                                    } catch (IOException ex) { 
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    stop();
                                }else  if (temps.get(j) instanceof AppleBAM) {
                                    musicobject.playWohoo();
                                    for(int r=0;r<temps.size();r++)
                                      {
                                        if(!(temps.get(r) instanceof FatalBomb) && !(temps.get(r) instanceof DangerousBomb))
                                         Sc.setObject(temps.get(r));
                                          game.setCommand(Sc);
                                          game.pressButton();
                                      }
                                } 
                                else if(temps.get(j) instanceof FatalBomb|| temps.get(j) instanceof DangerousBomb){
                                    musicobject.playShit();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                                else{
                                    musicobject.playSlap();
                                    Sc.setObject(temps.get(j));
                                    game.setCommand(Sc);
                                    game.pressButton();
                                }
                            }

                        }
                    }
                });

            }

        };

        timer.start();
        Scene scene5 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }

    public void popUpLevel3(ActionEvent event) throws IOException {
        Parent tuna = FXMLLoader.load(getClass().getResource("popUpLevel1.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Stage window = new Stage();
        window.setScene(scene2);
        window.show();
        
    }
    @FXML
    public void backToModes() throws IOException
    {
        Parent tuna = FXMLLoader.load(getClass().getResource("Modes.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) backtomodes.getScene().getWindow();
        window.setScene(scene2);
    }
    
    @FXML
    public void backToLevels() throws IOException
    {
        Parent tuna = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) backtolevels1.getScene().getWindow();
        window.setScene(scene2);
    }
    
    @FXML
    public void backToLevels2() throws IOException
    {
        Parent tuna = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) backtolevels2.getScene().getWindow();
        window.setScene(scene2);
    }
    
    @FXML
    public void backToLevels3() throws IOException
    {
        Parent tuna = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) backtolevels3.getScene().getWindow();
        window.setScene(scene2);
    }
    
    @FXML
    public void backToArcade() throws IOException
    {
        Parent tuna = FXMLLoader.load(getClass().getResource("Modes.fxml"));
        Scene scene2 = new Scene(tuna);
        Stage window = (Stage) backtoarcade.getScene().getWindow();
        window.setScene(scene2);
    }
    
    @FXML
    public void exitGameOver()
    {
        System.exit(0);
    }
}
