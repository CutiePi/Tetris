

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * Dialogue d'entré choix difficulté et option musique o/n
 * @author Jasmin
 */
public class StartView extends JFrame
{
	
	private JLabel lblTetris;
	private JLabel lblDifficulty;
	private JLabel lblMusic;
	
	private JRadioButton jrbOui,jrbNon,jrbD1,jrbD2,jrbD3;
	private ButtonGroup bgON,bgDiff;
	
	private JButton btnConfirmer;
	
	private JPanel panelTitre;
	private JPanel panelMusic;
	private JPanel panelDiff;
	private JPanel panelStartBtn;
	private Controlleur controlleur = Controlleur.getInstance();
	
	public static final String START_GAME="START_GAME";
	/**
	 * @author Jasmin
	 * {@link StartView}est utilisé pour initialiser le choix de difficulté et l'option musique
	 * Un arrangement visuel tout au plus
	 */
	public StartView()
	{
		 try
		 {
			UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
		 } 
		 catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		 {
			System.err.println("Impossible d'utiliser le look and feel de Windows");
		 }
		//Ce bloc pr�pare le controlleur � recevoir les infos
		//de Start
		this.setSize(new Dimension(400,400));
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.initialize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		//Pour centrer
		Point p=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		p.x-=200;
		p.y-=100;
		//Fin de pour centrer
		this.setLocation(p);
		this.setVisible(true);
		this.setTitle("Tetris v.51.2.0.10.3 Jasmin");
	}
	
	/**
	 * Cette m�thode permet de cr�er l'interface graphique
	 * @author Jasmin Lapointe
	 */
	private void initialize()
	{
		this.panelTitre=new JPanel();
		this.panelTitre.setLayout(new FlowLayout());
		this.panelTitre.setBackground(Color.black);
		
		this.panelMusic=new JPanel();
		this.panelMusic.setBackground(Color.black);
		this.panelMusic.setLayout(new GridLayout(5,2));
		
		this.panelStartBtn=new JPanel();
		
		
		this.lblTetris=new JLabel("Tetris v.51.2.0.10.3 Jasmin");
		this.lblTetris.setForeground(Color.white);
		this.lblTetris.setFont(new Font("Sans comic",Font.BOLD+Font.ITALIC,22));
		ImageIcon icon = new ImageIcon(getClass().getResource("logoTetris.jpg"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance( 75, 75,  java.awt.Image.SCALE_SMOOTH ) ;
		icon.setImage(newimg);
		this.lblTetris.setIcon(icon);
		
		this.lblDifficulty=new JLabel("Difficulté : ");
		this.lblDifficulty.setForeground(Color.white);
		this.lblDifficulty.setBackground(Color.black);
		this.lblDifficulty.setFont(new Font("Sans comic",Font.ITALIC+Font.BOLD,20));
		
		this.lblMusic=new JLabel("Musique O/N : Par Joshua Robillard");
		this.lblMusic.setForeground(Color.white);
		this.lblMusic.setFont(new Font("Sans comic",Font.ITALIC+Font.BOLD,20));
		
		this.btnConfirmer = new JButton("Commencer");
		this.btnConfirmer.addActionListener(controlleur);
		this.btnConfirmer.setActionCommand(START_GAME);
		this.jrbOui = new JRadioButton("Oui");
		this.jrbOui.setSelected(true);
		this.jrbNon = new JRadioButton("Non");
		this.jrbOui.setBackground(Color.black);
		this.jrbOui.setForeground(Color.white);
		this.jrbOui.setFont(new Font("Sans comic",Font.ITALIC,16));
		this.jrbNon.setBackground(Color.black);
		this.jrbNon.setForeground(Color.white);
		this.jrbNon.setFont(new Font("Sans comic",Font.ITALIC,16));
		this.bgON = new ButtonGroup();
		this.bgON.add(jrbOui);
		this.bgON.add(jrbNon);
		
		this.jrbD1 = new JRadioButton("Niveau 1");
		this.jrbD1.setSelected(true);
		this.jrbD1.setBackground(Color.black);
		this.jrbD1.setForeground(Color.white);
		this.jrbD1.setFont(new Font("Sans comic",Font.ITALIC,16));
		this.jrbD2 = new JRadioButton("Niveau 2");
		this.jrbD2.setBackground(Color.black);
		this.jrbD2.setForeground(Color.white);
		this.jrbD2.setFont(new Font("Sans comic",Font.ITALIC,16));
		this.jrbD3 = new JRadioButton("Niveau 3");
		this.jrbD3.setBackground(Color.black);
		this.jrbD3.setForeground(Color.white);
		this.jrbD3.setFont(new Font("Sans comic",Font.ITALIC,16));
		this.bgDiff = new ButtonGroup();
		this.bgDiff.add(jrbD1);
		this.bgDiff.add(jrbD2);
		this.bgDiff.add(jrbD3);
		
		
		this.panelDiff = new JPanel();
		panelDiff.setLayout(new FlowLayout());
		panelDiff.setBackground(Color.black);
		this.panelTitre.add(lblTetris);
		this.panelDiff.add(lblDifficulty);
		this.panelDiff.add(jrbD1);
		this.panelDiff.add(jrbD2);
		this.panelDiff.add(jrbD3);
		this.panelMusic.add(lblMusic);
		this.panelMusic.add(jrbOui);
		this.panelMusic.add(jrbNon);
		this.panelMusic.add(Box.createHorizontalBox());
		
		this.btnConfirmer.setBackground(Color.black);
		this.btnConfirmer.setFont(new Font("Sans comic",Font.ITALIC,14));
		
		this.panelStartBtn.add(btnConfirmer);
		this.panelStartBtn.setBackground(Color.black);
		
		this.add(panelTitre);
		this.add(panelMusic);
		this.add(panelDiff);
		this.add(panelStartBtn);
	}
	/**
	 * @author Jasmin
	 * 
	 * @return la valeur de loption musique et de l'option difficutlé
	 */
	public boolean getMusic(){
		boolean booOnOff = false;
		if(this.jrbOui.isSelected())
			booOnOff = true;
		return booOnOff;
	}
	
	public int getDiff(){
		int intD = 0;
		if(this.jrbD1.isSelected())
			intD=1;
		else if(this.jrbD2.isSelected())
		intD=2;
		else
			intD=3;
		return intD;
	}

	
}
