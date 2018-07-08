import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.MusicEngine;

public class Visual  extends JFrame implements Runnable {
	/**
	 * l'aspect visuel du jeu 
	 * @author Jasmin
	 * contient tout ce qui est visuel
	 * a une methode tres utile permettant de mute le keylistener setGameState(boolean pause)
	 */
	
	JFrame jfContainer;
	JButton jbCase;
	JPanel jpGrid;
	JPanel jpInfoControl;
	JPanel jpInfoScore;
	JPanel jpInfoNext;
	JPanel jpAllInfo;
	JLabel jlDroite, jlGauche, jlBas, jlHaut, jlPause, jlRestart;
	JLabel jlScore, jlNiveau, jlNbLigne;
	JLabel jlGameState, jlNext;
	JPanel jpNextPiece,jpReduceSize;
	private Font f = new Font("Sans comic",Font.ITALIC,14);
	private ArrayList<JButton> listCase = new ArrayList<>();
	private ArrayList<JButton> listDemo = new ArrayList<>();
	int i = 0;
	private Controlleur controlleur = Controlleur.getInstance();
	
	public Visual(){
		try
		 {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		 } 
		 catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		 {
			System.err.println("Impossible d'utiliser le look and feel de Windows");
		 }
		jfContainer = new JFrame("Tetris v.51.2.0.10.3 Jasmin");
		jfContainer.setFocusable(true);
		jfContainer.addKeyListener(controlleur);
		jfContainer.setSize(550, 800);
		jfContainer.setLayout(new BorderLayout());
		jpGrid = new JPanel();
		jpGrid.setLayout(new GridLayout(20,10,0,0));
		for(int i=0;i<200;i++){
			jbCase = new JButton();
			jbCase.setBackground(Color.darkGray);
			jbCase.setForeground(Color.darkGray);
			jbCase.setFocusable(false);
			jbCase.setEnabled(false);
			listCase.add(jbCase);
			jpGrid.add(jbCase);
		}
		
		jpAllInfo = new JPanel();
		
		jpInfoScore = new JPanel();
		jpInfoScore.setSize(new Dimension(400,300));
		jpInfoScore.setLayout(new BoxLayout(jpInfoScore,BoxLayout.Y_AXIS));
		jpInfoScore.setAlignmentX(RIGHT_ALIGNMENT);
		jpInfoScore.add(jlScore = new JLabel("Score : 0"));
		jpInfoScore.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoScore.add(jlNiveau = new JLabel("Niveau : 1"));
		jpInfoScore.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoScore.add(jlNbLigne = new JLabel("Nb lignes : 0"));
		jpInfoScore.add(Box.createRigidArea(new Dimension(0,25)));
		jlScore.setFont(f);
		jlNiveau.setFont(f);
		jlNbLigne.setFont(f);
		
		jpInfoNext = new JPanel();
		jpInfoNext.setLayout(new BoxLayout(jpInfoNext,BoxLayout.Y_AXIS));
		jpInfoNext.add(jlNext = new JLabel("Suivant"));
		jpInfoNext.add(Box.createRigidArea(new Dimension(0,25)));
		jpNextPiece = new JPanel();
		jpNextPiece.setLayout(new BorderLayout());
		jpReduceSize = new JPanel();
		jpReduceSize.setLayout(new GridLayout(4,4,0,0));
		for(int i = 0;i<16;i++){
			jbCase = new JButton();
			jbCase.setPreferredSize(new Dimension(1,2));
			jbCase.setBackground(Color.darkGray);
			jbCase.setFocusable(false);
			jbCase.setEnabled(false);
			listDemo.add(jbCase);
			jpReduceSize.add(jbCase);
		}
		jpNextPiece.add(jpReduceSize, BorderLayout.CENTER);
		jpInfoNext.add(jpNextPiece);
		jpInfoNext.add(Box.createRigidArea(new Dimension(0,350)));
		
		jpInfoControl = new JPanel();
		jpInfoControl.setLayout(new BoxLayout(jpInfoControl,BoxLayout.Y_AXIS));
		jpInfoControl.setAlignmentX(RIGHT_ALIGNMENT);
		jpInfoControl.add(jlDroite = new JLabel("-> : droite"));
		jpInfoControl.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoControl.add(jlGauche = new JLabel("<- : gauche"));
		jpInfoControl.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoControl.add(jlBas = new JLabel("Bas : descendre"));
		jpInfoControl.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoControl.add(jlHaut = new JLabel("Haut : orienter"));
		jpInfoControl.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoControl.add(jlPause = new JLabel("P : pause"));
		jpInfoControl.add(Box.createRigidArea(new Dimension(0,5)));
		jpInfoControl.add(jlRestart = new JLabel("Enter : recommencer"));
		jlRestart.setPreferredSize(new Dimension(175,15));
		jpInfoControl.add(Box.createRigidArea(new Dimension(0,5)));
		jlDroite.setFont(f);
		jlGauche.setFont(f);
		jlBas.setFont(f);
		jlHaut.setFont(f);
		jlPause.setFont(f);
		jlRestart.setFont(f);
		jpAllInfo.add(jpInfoScore);
		jpAllInfo.add(jlGameState = new JLabel("EN COURS"));
		jpAllInfo.add(Box.createRigidArea(new Dimension(0,15)));
		jlGameState.setFont(f);
		jlGameState.setAlignmentX(RIGHT_ALIGNMENT);
		jpAllInfo.add(jpInfoNext);
		jpAllInfo.add(jpInfoControl);
		
		jpAllInfo.setLayout(new BoxLayout(jpAllInfo,BoxLayout.Y_AXIS));
		jpInfoControl = new JPanel();
		jfContainer.setVisible(true);
		jfContainer.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfContainer.setResizable(false);
		jfContainer.setLocationRelativeTo(null);
		jfContainer.add(jpGrid, BorderLayout.CENTER);
		jfContainer.add(jpAllInfo,BorderLayout.LINE_END);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
	public ArrayList<JButton> getListBouton(){
		return listCase;
	}
	
	public ArrayList<JButton> getListDemo(){
		return listDemo;
	}
	
	public void setGameState(boolean pause){
		controlleur.setGameState(pause);
	}
	
	

}
