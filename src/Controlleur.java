import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Controlleur  implements KeyListener,ActionListener {
	
	private static volatile Controlleur instance;
	private static GestionTetrominos gt;
	private static StartView log;
	public boolean gameState = true;
	
	public Controlleur(GestionTetrominos gt, StartView log){
	this.gt = gt;
	this.log = log;
	}
/**
 * @author Jasmin
 * {@link Controlleur}gère les inputs claviers du joueur bref le controlleur
 * @gameState variable pour l'état du jeu si il est en pause ou autre
 */
	@Override
		public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		    int key = e.getKeyCode();

		    if (key == KeyEvent.VK_LEFT) {
		    	if(gameState){
		        gt.clean();
		        gt.moveLEFT();
		        gt.repaintTetrominos();
		    	}
		    }

		    if (key == KeyEvent.VK_RIGHT) {
		    	if(gameState){
		    	 gt.clean();
		    	 gt.moveRIGHT();
		    	 gt.repaintTetrominos();}
		    }

		    if (key == KeyEvent.VK_UP) {
		    	if(gameState){
		    	 gt.clean();
		    	gt.rotation();
		    	 gt.repaintTetrominos();}
		    }

		    if (key == KeyEvent.VK_DOWN) {
		    	if(gameState){
		    	 gt.clean();
		    	 gt.moveDOWN();
		    	 gt.repaintTetrominos();}
		    }
		    if(key ==  KeyEvent.VK_P){
		    	gt.pauseGame();
		    }
		    if(key == KeyEvent.VK_ENTER){
		    	if(gameState){
		    	gt.restartGame();
		    	}
		    }
		}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static Controlleur getInstance()
	{
		{
			synchronized(Controlleur.class)
			{
				instance=new Controlleur(gt, log);
			}
		}
		
		return instance;
	}
	
	public void setGameState(boolean pause){
		this.gameState = pause;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == StartView.START_GAME){
			gt.start(log.getDiff(),log.getMusic());
		}
		
	}


}
