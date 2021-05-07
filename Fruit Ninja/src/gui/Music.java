package gui;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Music {
    
     public void playStartGame() {
	        String path = "backGround.wav";
	        Media media = new Media(new File(path).toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
              // mediaPlayer.play();
               mediaPlayer.setCycleCount(20);
	 }
      public void playArcade() {
	        String path = "bomb.wav";
	        Media media = new Media(new File(path).toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
	 }
       public void playWohoo() {
	        String path = "woohoo.wav";
	        Media media = new Media(new File(path).toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
	 }
        public void playShit() {
	        String path = "shit.wav";
	        Media media = new Media(new File(path).toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
	 }
         public void playSlap() {
	        String path = "slap.wav";
	        Media media = new Media(new File(path).toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
	 }
}