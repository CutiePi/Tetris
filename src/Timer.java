import java.awt.event.KeyEvent;

public class Timer implements Runnable {
	/**
	 * @author Jasmin
	 * classe qui s'occupe de faire descendre le tetrominos a toute les x milliseconde
	 */
	private static GestionTetrominos gt;
	private boolean pause = false;
	private int intDiff;
	boolean booNewPiece = true;
	
	public Timer(GestionTetrominos gt, int intDiff){
		this.gt = gt;
		this.intDiff = intDiff;
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
	    	 if(booNewPiece){
	    		 gt.newTetro();
	    		 booNewPiece = false;
	    	 }
	    	 gt.clean();
	    	 gt.moveDOWN();
	    	 gt.repaintTetrominos();
	    	 try {
					Thread.sleep(350);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void setPause(boolean pausing){
		this.pause = pausing;
	}
	
	public boolean getPause(){
		return pause;
	}
	
	public void setDiff(int diff){
		this.intDiff = diff;
	}
	
	public void setcreateNew(boolean newPiece){
		this.booNewPiece = newPiece;
	}

}
