import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.MusicEngine;

public class GestionTetrominos {
	/**
	 * @author Jasmin
	 * classe mere qui gere tout.
	 * les noms de variables parlent pour eux mêmes
	 */
	private int tabCoord[] = new int[4];
	private int tabLvL[] = new int[4];
	private int tabPieceMoving[] = new int[4];
	private int tabAllPiece[];
	private int intScore = 0;
	private int intFirstPiece =0;
	private int intNiveau = 0;
	private int intNbLigne = 0;
	private int intType =-1;
	private int intType2 = -1;
	private int intNbrPiece = 0;
	private int intRotateI = 1, intRotateS = 1, intRotateZ = 1, intRotateO = 0, intRotateT = 0, intRotateLR = 0, intRotateL = 0;
	private boolean rotate = false;
	private boolean music;
	private Visual test;
	private StartView log;
	private Controlleur control;
	private Timer timer;
	private Thread thTimer, thMusic;
	private Rotations allRot = new Rotations();
	private MusicEngine musicPlayer;
	private ArrayList<Color> listCouleur = new ArrayList<Color>();
	private Color[] tabCouleur = {Color.cyan,Color.blue,Color.orange,Color.yellow,Color.green,Color.red,Color.magenta};
	private Collections list;
	private static GestionTetrominos engine;
	//Tableau pr les états initiauts des tetrominos 
	private boolean tabTetrominos1[][][] = {
			{
				//I
				{true, false, false, false},
				{true, false, false, false},
				{true, false, false, false},
				{true, false, false, false},
			},
			{
				//L inverse
				{false, true, false, false},
				{false, true, false, false},
				{true, true, false, false},
				{false, false, false, false},
			},
			{
				//L
				{true, false, false, false},
				{true, false, false, false},
				{true, true, false, false},
				{false, false, false, false},
			},
			{
				//S 4,14,15,25
				{true, false, false, false},
				{true, true, false, false},
				{false, true, false, false},
				{false, false, false, false},
			},
			{
				//Z
				{false, true, false, false},
				{true, true, false, false},
				{true, false, false, false},
				{false, false, false, false},
			},
			{
				//T
				{true, false, false, false},
				{true, true, false, false},
				{true, false, false, false},
				{false, false, false, false},
			},
			{
				//O
				{true, true, false, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false},
			},
		};
	
	public GestionTetrominos(){
		startLogin();
		for(int i = 0;i<7;i++)
			listCouleur.add(tabCouleur[i]);
			list.shuffle(listCouleur);
	}

	private void startLogin() {
		// TODO Auto-generated method stub
		log = new StartView();
		control = new Controlleur(this,log);
		timer = new Timer(this, 750);
		musicPlayer = new MusicEngine();
	}
	/**
	 * @author Jasmin
	 * generation de nombre 0-6 pour les pieces de 1-7
	 * @return
	 */
	public int generateTetroNum(){
		Random r = new Random();
		int random = r.nextInt();
		if(random<0)
			random*=-1;
		return random%7;
	}
	/**
	 * @author Jasmin
	 * affiche le prochain tetrominos dans le grille designer
	 */
	public void displayNextPiece(){
		int[] tabTemp = new  int[4];
		int[] tabLVLtp = new int[4];
		ArrayList<JButton> temp = test.getListDemo();
		for(JButton i:temp)
			i.setBackground(Color.darkGray);
		int index = 0;
		for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				if(tabTetrominos1[intType2][i][j]==true){
					tabTemp[index]=j+(i*4);
					tabLVLtp[index]=(i*4);
					index++;
				}
					
			}
		}
		for(int i:tabTemp)
			temp.get(i).setBackground(listCouleur.get(intType2));
	}
	
	
	public void generateTetrominos(){
		intRotateI = 1;
		intRotateS = 1;
		intRotateZ = 1;
		intRotateO = 0;
		intRotateT = 0;
		intRotateLR = 0;
		intRotateL = 0;
		int index = 0;
		for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				if(tabTetrominos1[intType][i][j]==true){
					tabCoord[index]=j+4+(i*10);
					tabLvL[index]=(i*10);
					index++;
				}
					
			}
		}
		ArrayList<JButton> temp = test.getListBouton();
		for(int i:tabCoord){
			if(temp.get(i).getBackground()!=Color.darkGray && temp.get(i).isEnabled()!=false){
				gameOver();
			}
			temp.get(i).setBackground(listCouleur.get(intType));
			
		}
		tabPieceMoving = tabCoord;
	}
	//methode si la partie se termine dit "good game well played!"
	//et appel stopGame()
	private void gameOver() {
		// TODO Auto-generated method stub
		if(music)
		musicPlayer.pause();
		JOptionPane.showMessageDialog(null, "Partie terminer GG WP !");
		stopGame();
	}

	//termine la partie et ramene au menu
	private void stopGame() {
		// TODO Auto-generated method stub
		test.jfContainer.dispose();
		if(music)
		musicPlayer.pause();
		GestionTetrominos bf = new GestionTetrominos();
		thTimer.stop();
	}
//repaint la grille apres chaque mouvement
	public void repaintTetrominos(){
		ArrayList<JButton> temp = test.getListBouton();
		for(int i:tabPieceMoving){
			temp.get(i).setBackground(listCouleur.get(intType));
			
		}
	}
	
	public void clean(){
		for(int i:tabPieceMoving){
			ArrayList<JButton> temp = test.getListBouton();
			temp.get(i).setBackground(Color.darkGray);
			
		}
	}
	
	public void moveLEFT(){
		int tabTemp[] = new int[4];
		for(int i = 0;i<4;i++)
			tabTemp[i]=tabPieceMoving[i];
		boolean reset = false;
		for(int i = 0;i<4;i++){
			if(tabPieceMoving[i]-tabLvL[i]!=0)
			if(verifyIfBlocked(-1,i))
				tabPieceMoving[i]-=1;
				else{
					reset =true;
					i=4;
				}
			else{
				reset = true;
				i=4;
			}
		}
		if(reset==true){
			tabPieceMoving=tabTemp;
		}
	}

	public void moveRIGHT(){
		int tabTemp[] = new int[4];
		for(int i = 0;i<4;i++)
			tabTemp[i]=tabPieceMoving[i];
		boolean reset =false;
		for(int i = 0;i<4;i++){
			if(tabPieceMoving[i]-tabLvL[i]!=9)
				if(verifyIfBlocked(1,i))
					tabPieceMoving[i]+=1;
					else{
						reset =true;
						i=4;
					}
			else{
				reset = true;
				i=4;
			}
		}
		if(reset==true){
			tabPieceMoving=tabTemp;
		}
	}
	
	public void moveDOWN(){
		int tabTempPiece[] = new int[4];
		int tabTempLvL[] = new int[4];
		boolean booNewPiece =false;
		for(int i = 0;i<4;i++)
			tabTempPiece[i]=tabPieceMoving[i];
		for(int i = 0;i<4;i++)
			tabTempLvL[i]=tabLvL[i];
		boolean reset =false;
		for(int i = 0;i<4;i++){
			if(tabPieceMoving[i]<190){
				if(verifyIfBlocked(10,i)){
					tabPieceMoving[i]+=10;
					tabLvL[i]+=10;
				}
					else{
						reset =true;
						i=4;
					}
			}
			else{
				reset = true;
				i=4;
			}
		}
		if(reset==true){
			tabPieceMoving=tabTempPiece;
			tabLvL=tabTempLvL;
			booNewPiece = true;
			}
		timer.setcreateNew(booNewPiece);
	}
	
	public boolean verifyIfBlocked(int directions,int index){
		ArrayList<JButton> listTest = test.getListBouton();
		boolean canMove = false;
		if(listTest.get(tabPieceMoving[index]+(1*directions)).isEnabled()==false)
			canMove=true;
		return canMove;
		
	}
	/**
	 * @author Jasmin
	 * rend toute piece de couleur a enabled et appel verify line avant de generer un nouveau tetrominos
	 */
	public void newTetro(){
		makeFocusable();
		if(!(intType>=0)){
			intType=generateTetroNum();
			intType2=generateTetroNum();
		}
		else{
			intType=intType2;
			intType2=generateTetroNum();
		}
		if(intFirstPiece>0)
		verifyLines();
		displayNextPiece();
		generateTetrominos();
		intFirstPiece++;
	}
	
	/**
	 * @author Jasmin
	 * verifie les lignes dans une boucle et accorde le score en conséquence avec la variable type score qui est ensuite passer a updateScore()
	 * utilise les propriété de couleur et enable des bouton
	 */
	public void verifyLines(){
		ArrayList<JButton> temp = test.getListBouton();
		int ligne = 10;
		int intTypeScore = 0;
		boolean lignePleine = true;
		for(int i = 199;i>=0;i--){
			ligne--;
			if(temp.get(i).isEnabled()==false || temp.get(i).getBackground()==Color.darkGray){
				lignePleine =false;
			}
			if(ligne==0 && lignePleine==true){
				int index = i;
				ArrayList<Boolean> test = new ArrayList<Boolean>();
				ArrayList<Color> couleur = new ArrayList<Color>();
				for(int j = 0;j<10;j++){
					test.add(false);
					couleur.add(Color.darkGray);
				}
				for(int j = 0;j<200;j++){
					if(j==index)
						j+=10;
					if(j<200){
						if(temp.get(j).isEnabled()&&temp.get(j).getBackground()!=Color.darkGray){
					test.add(true);
					couleur.add(temp.get(j).getBackground());
						}
						else{
							couleur.add(temp.get(j).getBackground());
							test.add(false);
						}
					}
				}
				for(int j = 0;j<200;j++){
					temp.get(j).setEnabled(test.get(j));
					if(test.get(j))
					temp.get(j).setBackground(couleur.get(j));
					else
						temp.get(j).setBackground(couleur.get(j));
				}
				ligne=10;
				intTypeScore++;
				i+=10;
			}
			if(ligne==0&&lignePleine==false){
				lignePleine=true;
				ligne=10;
			}
		}
		updateScore(intTypeScore);
	}
	
	/**
	 * @author Jasmin
	 * pause la partie suspend les threads music et timer
	 * bloque le keylistening des touches clés durant pause
	 */
	public void pauseGame(){
		if(!timer.getPause()){
		timer.setPause(true);
			thTimer.suspend();
			if(music)
			musicPlayer.pause();
			test.jlGameState.setText("PAUSE");
			test.setGameState(false);
			
		}
		else{
			timer.setPause(false);
			thTimer.resume();
			if(music)
				musicPlayer.resume();
			test.jlGameState.setText("EN COURS");
			test.setGameState(true);
		}
	}
	/**
	 * @author Jasmin
	 * option de retour au menu
	 */
	public void restartGame(){
		pauseGame();
		if (JOptionPane.showConfirmDialog(null, "Voulez vous vraiment retourner au menu?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    //oui
			stopGame();
		} else {
		    //non
			pauseGame();
		}
	}
	
	/**
	 * @author Jasmin
	 * @param diff la difficulté choisie
	 * @param music si il y a music ou pas
	 */
	public void start(int diff, boolean music) {
		// TODO Auto-generated method stub
		test = new Visual();
		int vitesse = 750;
		log.dispose();
		switch (diff){
		case 1 :{
			test.jlNiveau.setText("Niveau : 1");
			vitesse =750;
		}
			break;
		case 2 :{
			test.jlNiveau.setText("Niveau : 2");
			vitesse = 500;
		}
			break;
		case 3 :{
			test.jlNiveau.setText("Niveau : 3");
			vitesse = 350;
		}
			break;
		}
		timer.setDiff(vitesse);
		thTimer = new Thread(timer);
		thTimer.start();
		 this.music = music;
		if(music){
			thMusic = new Thread(musicPlayer);
			thMusic.start();
		}
	}

	/**
	 * @author Jasmin
	 * gestion de rotation de chaque piece
	 * un switch avec rotation utilisant les tableau de la classe rotations pr les mouvements
	 * peu ramener les cas 1,2,5 a une methode commune car seule la valeur intRotate et les tableaux change
	 */
	public void rotation(){
		int tabTempPiece[] = new int[4];
		int tabTempLvL[] = new int[4];
		int intNumberOfRotates=0;
		boolean booTwoRotate =false;
		for(int i = 0;i<4;i++){
			tabTempPiece[i]=tabPieceMoving[i];
			tabTempLvL[i]=tabLvL[i];
		}
		switch(intType){
		case 0 :{
			for(int i = 0;i<4;i++){
				tabTempPiece[i]+=(intRotateI*allRot.tabRotationI1[i]);
				tabTempLvL[i]+=(intRotateI*allRot.tabLvLI1[i]);
			}
			intRotateI*=-1;
		}
			break;
		case 1:{
			int tabTEMPO[] = allRot.tabLR[intRotateLR];
			int tabTEMPOLVL[] = allRot.tabLvLLR[intRotateLR];
			for(int i = 0;i<4;i++){
			tabTempPiece[i]+=(tabTEMPO[i]);
			tabTempLvL[i]+=(tabTEMPOLVL[i]);
		}
		intRotateLR++;
	}
			break;
		case 2:{
			int tabTEMPO[] = allRot.tabL[intRotateL];
			int tabTEMPOLVL[] = allRot.tabLvLL[intRotateL];
			for(int i = 0;i<4;i++){
			tabTempPiece[i]+=(tabTEMPO[i]);
			tabTempLvL[i]+=(tabTEMPOLVL[i]);
		}
		intRotateL++;
	}
			break;
		case 3:{
			for(int i = 0;i<4;i++){
				tabTempPiece[i]+=(intRotateS*allRot.tabRotationS1[i]);
				tabTempLvL[i]+=(intRotateS*allRot.tabLvLS1[i]);
			}
			intRotateS*=-1;
		}
			break;
		case 4:{
			for(int i = 0;i<4;i++){
				tabTempPiece[i]+=(intRotateZ*allRot.tabRotationZ1[i]);
				tabTempLvL[i]+=(intRotateZ*allRot.tabLvLZ1[i]);
			}
			intRotateZ*=-1;
		}
			break;
		case 5:
		{
			int tabTEMPO[] = allRot.tabT[intRotateT];
			int tabTEMPOLVL[] = allRot.tabLvLT[intRotateT];
			for(int i = 0;i<4;i++){
			tabTempPiece[i]+=(tabTEMPO[i]);
			tabTempLvL[i]+=(tabTEMPOLVL[i]);
		}
		intRotateT++;
	}
			break;
		case 6:
			System.out.println("pas de rotation car cube");
			break;
			
		}

		if(checkIfRotatable(tabTempPiece, tabTempLvL)){
			if(verifyIfBlockedRotation(tabTempPiece)){
		tabPieceMoving = tabTempPiece;
		tabLvL = tabTempLvL;
			}
			else{
				intRotateI*=-1;
				intRotateS*=-1;
				intRotateZ*=-1;
				if(intRotateLR!=0 || intRotateL!=0 || intRotateT != 0){
					intRotateLR--;
					intRotateL--;
					intRotateT--;
				}
			}
		}
		else{
			intRotateI*=-1;
			intRotateS*=-1;
			if(intRotateLR!=0 || intRotateL!=0 || intRotateT != 0){
				intRotateLR--;
				intRotateL--;
				intRotateT--;
			}
		}
		if(intRotateLR==4 || intRotateL==4 || intRotateT==4){
			intRotateLR=0;
			intRotateL=0;
			intRotateT=0;
		}
	}
	/**
	 * 
	 * @param tabPiece
	 * @return si peu tourner ou non
	 * verifie si il ya un bloquage de rotation par un tetrominos
	 */
	public boolean verifyIfBlockedRotation(int[] tabPiece){
		ArrayList<JButton> listTest = test.getListBouton();
		boolean canMove = true;
		for(int i = 0;i<4;i++){
		if(listTest.get(tabPiece[i]).isEnabled()==true){
			canMove=false;
		}
		}
		return canMove;
		
	}
	
	/**
	 * @author Jasmin
	 * @param tabPiece
	 * @param tabL
	 * @return si peu tourner ou non
	 * verifie si la rotation est dans les limites de la grille
	 */
	public boolean checkIfRotatable(int[] tabPiece, int[] tabL){
		boolean canRotate = true;
		for(int i = 0;i<4;i++){
			if(!(tabPiece[i]-tabL[i]>=0 && tabPiece[i]-tabL[i]<=9 && tabL[i]>=0 && tabPiece[i]<200))
				canRotate = false;
		}
			return canRotate;
	}
/**
 * =@author Jasmin
 * rend les bouton de couleur a Enabled 
 * les propriétés background et enabled sont utilisé pour la verification et collision
 */
	public void makeFocusable(){
		ArrayList<JButton> listTemp = test.getListBouton();
		for(int i =0;i<listTemp.size();i++){
			if(listTemp.get(i).getBackground()!=Color.darkGray)
				listTemp.get(i).setEnabled(true);
		}
		
	}
	/**
	 * @author Jasmin
	 * @param intTypeScore
	 * incrémente le score et le nombre de ligne effacer
	 */
	public void updateScore(int intTypeScore){
		switch (intTypeScore){
		case 1:
			intScore+=40;
			intNbLigne+=1;
			break;
		case 2:
			intScore+=100;
			intNbLigne+=2;
			break;
		case 3:
			intScore+=300;
			intNbLigne+=3;
			break;
		case 4: 
			intScore+=1200;
			intNbLigne+=4;
			break;
		}
		test.jlScore.setText("Score : " + intScore);
		test.jlNbLigne.setText("Nb lignes : " + intNbLigne);
	}
	
	public static void main(String[] args){
		engine = new GestionTetrominos();
	}
	

}
