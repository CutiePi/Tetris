package utils;  
import java.io.File; 
import java.io.IOException;  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.LineEvent; 
import javax.sound.sampled.LineListener; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

/**  *   * Système de musique par Samuel Yvon  * Adapté pour Tetris  * @author Samuel Yvon  
 * 
 * Emprunter à Samuel Yvon credit à lui!!!!
 * Arigato senpai-saM
 * MUSIC BY JOSHUA ROBILLARD AKA Evro
 * Modifier legerement par @author Jasmin pour bien aller dans mon projet
 * *  */ 
public class MusicEngine implements Runnable { 	
	private Clip themeClip; 
	private boolean looper = true;
	public MusicEngine() 	{ 		
		System.out.println("Sound engine pour wav file par SAM & Musique par Joshua EVRO Robillard"); 	
		} 	
	@Override 	public void run() 	{ 
		while(looper){
		try{ 
		AudioInputStream sound = AudioSystem.getAudioInputStream(MusicEngine.class.getResource("themeWAV.wav")); 				
		DataLine.Info soundInfo = new DataLine.Info(Clip.class,sound.getFormat()); 				
		this.themeClip = (Clip) AudioSystem.getLine(soundInfo);
		themeClip.open(sound); 				
		themeClip.loop(Clip.LOOP_CONTINUOUSLY);				
		themeClip.start(); 	}  			
		catch (UnsupportedAudioFileException e) 			
		{ System.err.println("Type de fichier non supporté");} 			
		catch (IOException e){ 	System.err.println("Impossible de lire le fichier!");} 			
		catch (LineUnavailableException e) {System.err.println("Ligne non disponible!");} 		
		looper =false;
	while(true){
		if(themeClip.isRunning())
			looper = true;
	}
	}
	}
	//Ajouter les sons de bases ou une méthode qui lit les fichiers de "l'ancienne facon" 	


/**
 * @author Jasmin
 * stop la musique
 */
public void pause(){
	this.themeClip.stop();
}
/**
 * @author Jasmin
 * redémare la musique
 */
public void resume(){
	this.themeClip.start();
}
}
