import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;


public class MainBoard extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PacmanData thePac = new PacmanData("pacman", "Player1",1,270,460), thePac2 = new PacmanData("mspacman","Player2",1, 290,460);
	private static Ghosts theRed = new Ghosts("Red",1,270, 220), thePink = new Ghosts("Pink",1, 270,280), 
			theBlue =new Ghosts("Blue",1, 310,280), theOrange =new Ghosts("Orange",1, 230,280);
	private static Points pointSystem = new Points();
	private static ScoreBoard scorePiece = new ScoreBoard(); 
	private static int[][] grid;
	private static JLabel pacman, pacman2;
	private static JLabel pacLives, pacLives2;
	private static JLabel Red, Pink, Blue, Orange;
	private static JLabel fruit, fruitP, ghostP;
	private static JLabel[][] maze;
	private static ArrayList<JLabel> dots = new ArrayList<JLabel>();
	private static ArrayList<Dots> dotData = new ArrayList<Dots>();
	private static JLabel introBoard, playerBoard, scoreBoard, ready;
	private static JLabel optionA, optionB, optionC, optionD, optionE, optionF, optionG, selectOption, pacOption, pacOption2, ghostOption, mazeOption;
	private static ImageIcon introArt, scoreResult, liveIcon, liveIcon2P;
	private static MazePart[][] mazePart;
	private static MazePart mazeEaten;
	private static Mazes fullMaze;
	private static JButton button = new JButton("Start Pac"), button2 = new JButton("Start Ms. Pac"), button3 = new JButton("Start 2P");
	private static JPanel gameBoard;
	private boolean playing, pause, ghostActive, powerActive, dead, chase, fruitActive, fruitFirst, fruitSecond, mazeBig;
	private boolean alive1P, alive2P;
	private static int firstKey = 0, nextKey = 0, dotNum, dotStart, score, ghostCombo, lives, lives2P, color, dotColor, maxPowerTime, level, fruitUse;
	private static int firstKey2P = 0, nextKey2P = 0, sec, powerSec, currentOption, optionLimit, mazeOffset = 0, startX, startY;
	private static int pacStyle, pacStyle2, ghostStyle, mazeStyle, mazeOptions[][];
	private static int eatenLocX1, eatenLocY1,eatenLocX2, eatenLocY2;
	private static String game, player, menu, gameType, mazeList;
	private static Timer ghostTimer, powerTimer, keyTimer, chaseTimer, ghostWhiteTimer, reverseTimer, GhostP, FruitP;
	private Container contentPane = getContentPane();
	private Mazesprite mazewall = new Mazesprite(1,0,0);
	
	public MainBoard(){
		
		super("Pacman");
		//setSize(BOARD_WIDTH, BOARD_HEIGHT);
		playing = false;
		//Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		addKeyListener(this);
		setFocusable(true);
		
		pacman = new JLabel("", JLabel.CENTER);
		pacman.setSize(30,30);
		pacman.setLocation(thePac.getLocation());
		pacman.setIcon(thePac.getImage());
		
		pacman2 = new JLabel("", JLabel.CENTER);
		pacman2.setSize(30,30);
		pacman2.setLocation(thePac2.getLocation());
		pacman2.setIcon(thePac2.getImage());
		
		Red = new JLabel("", JLabel.CENTER);
		Red.setSize(30,30);
		Red.setLocation(theRed.getLocation());
		Red.setIcon(theRed.getImage());
		
		Pink = new JLabel("", JLabel.CENTER);
		Pink.setSize(30,30);
		Pink.setLocation(thePink.getLocation());
		Pink.setIcon(thePink.getImage());
		
		Blue = new JLabel("", JLabel.CENTER);
		Blue.setSize(30,30);
		Blue.setLocation(theBlue.getLocation());
		Blue.setIcon(theBlue.getImage());
		
		Orange = new JLabel("", JLabel.CENTER);
		Orange.setSize(30,30);
		Orange.setLocation(theOrange.getLocation());
		Orange.setIcon(theOrange.getImage());
		
		fruit = new JLabel("", JLabel.CENTER);
		fruit.setSize(30,30);
		fruit.setLocation(270, 340);
		fruit.setVisible(false);
		
		fruitP = new JLabel("", JLabel.CENTER);
		fruitP.setSize(30,20);
		fruitP.setVisible(false);
		
		ghostP = new JLabel("", JLabel.CENTER);
		ghostP.setSize(30, 20);
		ghostP.setVisible(false);
		
		playerBoard = new JLabel("", JLabel.CENTER);
		playerBoard.setSize(155,30);
		playerBoard.setLocation(600, 100);
		BufferedImage combinedImage = new BufferedImage( 
                155, 
                30, 
                BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = combinedImage.createGraphics();
        g.drawImage(scorePiece.getOrangeImage(15).getImage(),0,0,null);
        g.drawImage(scorePiece.getOrangeImage(11).getImage(),25,0,null);
        g.drawImage(scorePiece.getOrangeImage(0).getImage(),50,0,null);
        g.drawImage(scorePiece.getOrangeImage(24).getImage(),75,0,null);
        g.drawImage(scorePiece.getOrangeImage(4).getImage(),100,0,null);
        g.drawImage(scorePiece.getOrangeImage(17).getImage(),125,0,null);
        g.dispose();
		ImageIcon result = new ImageIcon(combinedImage);
		playerBoard.setIcon(result);
		
		scoreBoard = new JLabel("", JLabel.CENTER);
		scoreBoard.setSize(155,30);
		scoreBoard.setLocation(600, 200);
		combinedImage = new BufferedImage( 
                155, 
                30, 
                BufferedImage.TYPE_INT_ARGB );
		g = combinedImage.createGraphics();
        //g.drawImage(scorePiece.getImage(31).getImage(),0,0,null);
        //g.drawImage(scorePiece.getImage(31).getImage(),25,0,null);
        //g.drawImage(scorePiece.getImage(31).getImage(),50,0,null);
        //g.drawImage(scorePiece.getImage(31).getImage(),75,0,null);
        //g.drawImage(scorePiece.getImage(31).getImage(),100,0,null);
        g.drawImage(scorePiece.getWhiteImage(31).getImage(),125,0,null);
        g.dispose();
		scoreResult = new ImageIcon(combinedImage);
		scoreBoard.setIcon(scoreResult);
		
		Mazesprite mazewall = new Mazesprite(1,0,0);
		
		introBoard = new JLabel("", JLabel.CENTER);
		introBoard.setSize(480, 400);
		introBoard.setLocation(50,200);
		combinedImage = new BufferedImage(480,400,BufferedImage.TYPE_INT_ARGB);
		g = combinedImage.createGraphics();
		
		int introGrid[][] = new int[][]{{25,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,24},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{ 2,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36, 3},
										{27,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,26}};
		for(int i = 0, y =0; i < introGrid.length; i++, y=y+20){
			for(int j = 0, x = 0; j < introGrid[i].length; j++, x=x+20){
				g.drawImage((new MazePart(introGrid[i][j],mazewall)).getMazeWall().getImage(),x,y,null);
			}	
		}
	
		g.dispose();
		introArt = new ImageIcon(combinedImage);
		introBoard.setIcon(introArt);
		
		optionA = new JLabel("", JLabel.CENTER);
		optionA.setSize(170, 30);
		optionA.setLocation(160,235);
		int Grid[] = new int[]{15,0,2,-1,12,0,13};
		combinedImage = new BufferedImage(170,30,BufferedImage.TYPE_INT_ARGB);
		g = combinedImage.createGraphics();
		for(int i =0, x=0; i < Grid.length; i++,x+=25){
			if(Grid[i] >= 0){
				g.drawImage(scorePiece.getOrangeImage(Grid[i]).getImage(), x,0,null);
			}
		}
		g.dispose();
		
		ImageIcon optionAIcon = new ImageIcon(combinedImage);
		optionA.setIcon(optionAIcon);
		
		optionB = new JLabel("", JLabel.CENTER);
		optionB.setSize(250, 30);
		optionB.setLocation(160,285);
		Grid = new int[]{12,18,-1,15,0,2,-1,12,0,13};
		combinedImage = new BufferedImage(250,30,BufferedImage.TYPE_INT_ARGB);
		g = combinedImage.createGraphics();
		for(int i =0, x=0; i < Grid.length; i++,x+=25){
			if(Grid[i] >= 0){
				g.drawImage(scorePiece.getPinkImage(Grid[i]).getImage(), x,0,null);
			}
		}
		g.dispose();
		
		ImageIcon optionBIcon = new ImageIcon(combinedImage);
		optionB.setIcon(optionBIcon);
		
		optionC = new JLabel("", JLabel.CENTER);
		optionC.setSize(80, 30);
		optionC.setLocation(160,335);
		Grid = new int[]{0,11,11};
		combinedImage = new BufferedImage(80,30,BufferedImage.TYPE_INT_ARGB);
		g = combinedImage.createGraphics();
		for(int i =0, x=0; i < Grid.length; i++,x+=25){
			if(Grid[i] >= 0){
				g.drawImage(scorePiece.getRedImage(Grid[i]).getImage(), x,0,null);
			}
		}
		g.dispose();
		
		ImageIcon optionCIcon = new ImageIcon(combinedImage);
		optionC.setIcon(optionCIcon);
		
		optionD = new JLabel("", JLabel.CENTER);
		//optionD.setSize(250, 30);
		optionD.setLocation(160,385);
		
		optionE = new JLabel("", JLabel.CENTER);
		//optionE.setSize(250, 30);
		optionE.setLocation(160,435);
		
		optionF = new JLabel("", JLabel.CENTER);
		//optionF.setSize(250, 30);
		optionF.setLocation(160,485);
		
		optionG = new JLabel("", JLabel.CENTER);
		//optionG.setSize(250, 30);
		optionG.setLocation(160,535);
		
		selectOption = new JLabel("", JLabel.CENTER);
		selectOption.setSize(80, 30);
		selectOption.setLocation(75,230);
		selectOption.setIcon(thePac.getLiveImage());
		selectOption.setVisible(true);
		
		
		ready= new JLabel("", JLabel.CENTER);
		ready.setSize(155, 30);
		ready.setLocation(210, 340);
		ready.setVisible(false);
		
		
		combinedImage = new BufferedImage(155,30,BufferedImage.TYPE_INT_ARGB);
		g = combinedImage.createGraphics();
		g.drawImage(scorePiece.getBlueImage(17).getImage(),0,0,null);
        g.drawImage(scorePiece.getBlueImage(4).getImage(),25,0,null);
        g.drawImage(scorePiece.getBlueImage(0).getImage(),50,0,null);
        g.drawImage(scorePiece.getBlueImage(3).getImage(),75,0,null);
        g.drawImage(scorePiece.getBlueImage(24).getImage(),100,0,null);
        g.drawImage(scorePiece.getBlueImage(26).getImage(),125,0,null);
        g.dispose();
        
        ImageIcon readyPiece = new ImageIcon(combinedImage);
        ready.setIcon(readyPiece);
		
		pacLives = new JLabel("",JLabel.CENTER);
		pacLives.setSize(120,40);
		pacLives.setLocation(600,150);
		liveIcon = thePac.getLiveImage();
		
		pacLives2 = new JLabel("",JLabel.CENTER);
		pacLives2.setSize(120,40);
		pacLives2.setLocation(600,300);
		liveIcon2P = thePac2.getLiveImage();
		
		
		pacOption = new JLabel("",JLabel.CENTER);
		pacOption.setSize(30, 30);
		pacOption.setLocation(400,235);
		pacOption.setIcon(thePac.getOptions(0));
		pacOption.setVisible(false);
		
		pacOption2 = new JLabel("",JLabel.CENTER);
		pacOption2.setSize(30, 30);
		pacOption2.setLocation(400,285);
		pacOption2.setIcon(thePac.getOptions(0));
		pacOption2.setVisible(false);
		
		ghostOption = new JLabel("",JLabel.CENTER);
		ghostOption.setSize(30, 30);
		ghostOption.setLocation(400,285);
		ghostOption.setIcon(theRed.getOption(0));
		ghostOption.setVisible(false);
		
		mazeOptions = new int[][]{{-1,-1,-1,0,17,2,0,3,4},
									{-1,-1,-1,18,13,4,18,41,6,4,13,18},
									{-1,-1,-1,1,0,17}};
		
		contentPane.add(optionA);
		contentPane.add(optionB);
		contentPane.add(optionC);
		contentPane.add(optionD);
		contentPane.add(optionE);
		contentPane.add(optionF);
		contentPane.add(optionG);
		contentPane.add(pacOption);
		contentPane.add(pacOption2);
		contentPane.add(ghostOption);
		contentPane.add(selectOption);
		contentPane.add(introBoard);
		contentPane.add(ready);
		contentPane.add(pacman);
		contentPane.add(pacman2);
		contentPane.add(button);
		contentPane.add(button2);
		contentPane.add(button3);
		contentPane.add(Red);
		contentPane.add(Pink);
		contentPane.add(Blue);
		contentPane.add(Orange);
		contentPane.add(fruit);
		contentPane.add(fruitP);
		contentPane.add(ghostP);
		contentPane.add(playerBoard);
		contentPane.add(scoreBoard);
		contentPane.add(pacLives);
		contentPane.add(pacLives2);
		
		/*grid = new int[][]{
				{  1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 35, 34, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,  0},
				{  3, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37,  2},
				{  3, 37, 19, 12, 12, 18, 37, 19, 12, 12, 12, 18, 37, 21, 20, 37, 19, 12, 12, 12, 18, 37, 19, 12, 12, 18, 37,  2},
				{  3, 39, 21, 36, 36, 20, 37, 21, 36, 36, 36, 20, 37, 21, 20, 37, 21, 36, 36, 36, 20, 37, 21, 36, 36, 20, 39,  2},
				{  3, 37, 23, 17, 17, 22, 37, 23, 17, 17, 17, 22, 37, 23, 22, 37, 23, 17, 17, 17, 22, 37, 23, 17, 17, 22, 37,  2},
				{  3, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37,  2},
				{  3, 37, 19, 12, 12, 18, 37, 19, 18, 37, 19, 12, 12, 12, 12, 12, 12, 18, 37, 19, 18, 37, 19, 12, 12, 18, 37,  2},
				{  3, 37, 23, 17, 17, 22, 37, 21, 20, 37, 23, 17, 17, 31, 30, 17, 17, 22, 37, 21, 20, 37, 23, 17, 17, 22, 37,  2},
				{  3, 37, 37, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 37, 37,  2},
				{  5, 11, 11, 11, 11, 18, 37, 21, 32, 12, 12, 18, 36, 21, 20, 36, 19, 12, 12, 33, 20, 37, 19, 11, 11, 11, 11,  4},
				{ 36, 36, 36, 36, 36,  3, 37, 21, 30, 17, 17, 22, 36, 23, 22, 36, 23, 17, 17, 31, 20, 37,  2, 36, 36, 36, 36, 36},
				{ 36, 36, 36, 36, 36,  3, 37, 21, 20, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 21, 20, 37,  2, 36, 36, 36, 36, 36},
				{ 36, 36, 36, 36, 36,  3, 37, 21, 20, 36, 25, 11, 11, 40, 40, 11, 11, 24, 36, 21, 20, 37,  2, 36, 36, 36, 36, 36},
				{ 10, 10, 10, 10, 10, 22, 37, 23, 22, 36,  2, 36, 36, 36, 36, 36, 36,  3, 36, 23, 22, 37, 23, 10, 10, 10, 10, 10},
				{ 36, 36, 36, 36, 36, 36, 37, 36, 36, 36,  2, 36, 36, 36, 36, 36, 36,  3, 36, 36, 36, 37, 36, 36, 36, 36, 36, 36},
				{ 11, 11, 11, 11, 11, 18, 37, 19, 18, 36,  2, 36, 36, 36, 36, 36, 36,  3, 36, 19, 18, 37, 19, 11, 11, 11, 11, 11},
				{ 36, 36, 36, 36, 36,  3, 37, 21, 20, 36, 27, 10, 10, 10, 10, 10, 10, 26, 36, 21, 20, 37,  2, 36, 36, 36, 36, 36},
				{ 36, 36, 36, 36, 36,  3, 37, 21, 20, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 21, 20, 37,  2, 36, 36, 36, 36, 36},
				{ 36, 36, 36, 36, 36,  3, 37, 21, 20, 36, 19, 12, 12, 12, 12, 12, 12, 18, 36, 21, 20, 37,  2, 36, 36, 36, 36, 36},
				{  1, 10, 10, 10, 10, 22, 37, 23, 22, 36, 23, 17, 17, 31, 30, 17, 17, 22, 36, 23, 22, 37, 23, 10, 10, 10, 10,  0},
				{  3, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37,  2},
				{  3, 37, 19, 12, 12, 18, 37, 19, 12, 12, 12, 18, 37, 21, 20, 37, 19, 12, 12, 12, 18, 37, 19, 12, 12, 18, 37,  2},
				{  3, 37, 23, 17, 31, 20, 37, 23, 17, 17, 17, 22, 37, 23, 22, 37, 23, 17, 17, 17, 22, 37, 21, 30, 17, 22, 37,  2},
				{  3, 39, 37, 37, 21, 20, 37, 37, 37, 37, 37, 37, 37, 36, 36, 37, 37, 37, 37, 37, 37, 37, 21, 20, 37, 37, 39,  2},
				{  7, 12, 18, 37, 21, 20, 37, 19, 18, 37, 19, 12, 12, 12, 12, 12, 12, 18, 37, 19, 18, 37, 21, 20, 37, 19, 12,  6},
				{  9, 17, 22, 37, 23, 22, 37, 21, 20, 37, 23, 17, 17, 31, 30, 17, 17, 22, 37, 21, 20, 37, 23, 22, 37, 23, 17,  8},
				{  3, 37, 37, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 21, 20, 37, 37, 37, 37, 37, 37,  2},
				{  3, 37, 19, 12, 12, 12, 12, 33, 32, 12, 12, 18, 37, 21, 20, 37, 19, 12, 12, 33, 32, 12, 12, 12, 12, 18, 37,  2},
				{  3, 37, 23, 17, 17, 17, 17, 17, 17, 17, 17, 22, 37, 23, 22, 37, 23, 17, 17, 17, 17, 17, 17, 17, 17, 22, 37,  2},
				{  3, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37,  2},
				{  5, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,  4}
				};*/
		//28 X 31
		fullMaze = new Mazes();
		level = 1;
		grid = fullMaze.sendMaze(1);
		color = 0;
		dotColor = 0;
		
		mazePart = new MazePart[grid.length][grid[0].length];
		maze = new JLabel[grid.length][grid[0].length];
		
		dotNum = 0;
		dotStart = 0;
		
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(37,28));
		gameBoard.setLocation(5, 5 + mazeOffset);
		gameBoard.setSize(560, 740);
		int startX = 0, startY = 0;
		
		// This is drawing the main maze
		for(int i = 0, y =5; i < mazePart.length; i++, y=y+20){
			for(int j = 0, x = 5; j < mazePart[i].length; j++, x=x+20){
				
				mazePart[i][j] = new MazePart(grid[i][j], mazewall);
				if(mazePart[i][j].getType().equals("Dot") || mazePart[i][j].getType().equals("PowerDot")){
					dotNum++;
					dotStart++;
					
					dotData.add(new Dots(1, mazePart[i][j].getType(), mazewall, j,i));
					dots.add(new JLabel("",JLabel.CENTER));
					dots.get(dots.size()-1).setSize(20,20);
					dots.get(dots.size()-1).setLocation(x, y);
					dots.get(dots.size()-1).setIcon(dotData.get(dotData.size()-1).getDot());
					
					//contentPane.add(dots.get(dots.size()-1), contentPane.getComponentZOrder(gameBoard));
				}
				maze[i][j] = new JLabel("", JLabel.CENTER);
				maze[i][j].setSize(20,20);
				maze[i][j].setLocation(x,y);
				maze[i][j].setIcon(mazePart[i][j].getMazeWall());
				gameBoard.add(maze[i][j]);
				//contentPane.add(maze[i][j]);
				if(grid[i][j] == 42){
					if(startX == 0 && startY == 0){
						startX = maze[i][j].getX() + 5;
						startY = maze[i][j].getY() - 5;
						
					}
				}
				
			}
			
		}
		thePac.setStartLocation(startX, startY);
		pacman.setLocation(thePac.getStartLocation());
		contentPane.add(gameBoard);
		
		mazeEaten = new MazePart(36,mazewall);
		//currentKey = 0;
		dead = false;
		
		keyTimer = new Timer(1, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(firstKey !=0){
					keyAction(firstKey);
				} else{
					keyAction(nextKey);
				}
				
				if(firstKey2P !=0){
					keyAction(firstKey2P);
				} else{
					keyAction(nextKey2P);
				}
				//keyAction(currentKey);
			}
			
		});
		keyTimer.setInitialDelay(0);
		
		sec = 6;
		ghostActive = true;
		powerActive = false;
		
		ghostTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sec--;
				if(sec == 0){
					ghostTimer.stop();
				}
			}
			
		});
		
		maxPowerTime = 6;
		powerTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				powerSec--;
				//System.out.println(powerSec);
				if(powerSec == 0){
					powerTimer.stop();
				}
			}
			
		});
		
		chaseTimer = new Timer(6000, new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				chase = true;
				chaseTimer.stop();
				theRed.reverse();
				thePink.reverse();
				theBlue.reverse();
				theOrange.reverse();
			}
		});
		
		ghostWhiteTimer = new Timer(200, new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				if(theRed.getIndex() == 8){
					theRed.setIndex(10);
				} else if(theRed.getIndex() == 9){
					theRed.setIndex(11);
				} else if(theRed.getIndex() == 10){
					theRed.setIndex(8);
				} else if(theRed.getIndex() == 11){
					theRed.setIndex(9);
				}
				
				if(thePink.getIndex() == 8){
					thePink.setIndex(10);
				} else if(thePink.getIndex() == 9){
					thePink.setIndex(11);
				} else if(thePink.getIndex() == 10){
					thePink.setIndex(8);
				} else if(thePink.getIndex() == 11){
					thePink.setIndex(9);
				}
				
				if(theBlue.getIndex() == 8){
					theBlue.setIndex(10);
				} else if(theBlue.getIndex() == 9){
					theBlue.setIndex(11);
				} else if(theBlue.getIndex() == 10){
					theBlue.setIndex(8);
				} else if(theBlue.getIndex() == 11){
					theBlue.setIndex(9);
				}
				
				if(theOrange.getIndex() == 8){
					theOrange.setIndex(10);
				} else if(theOrange.getIndex() == 9){
					theOrange.setIndex(11);
				} else if(theOrange.getIndex() == 10){
					theOrange.setIndex(8);
				} else if(theOrange.getIndex() == 11){
					theOrange.setIndex(9);
				}
			}
		});
		
		GhostP = new Timer(1000, new ActionListener(){
			
			public void actionPerformed(ActionEvent e){

				ghostP.setVisible(false);
				GhostP.stop();
			}
		});
		
		FruitP = new Timer(1000, new ActionListener(){
			
			public void actionPerformed(ActionEvent e){

				fruitP.setVisible(false);
				FruitP.stop();
			}
		});
		//Mazesprite mazewall = new Mazesprite();
		//maze = new JLabel[13];
		
		/*for(int i = 0, x = 5, y = 5; i < 13; i++, x=x+33, y=y+9){
			Image img = (mazewall.getMazeSprite(i)).getImage();
			ImageIcon newIcon = new ImageIcon(img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
			
			maze[i] = new JLabel("", JLabel.CENTER);
			maze[i].setSize(32,32);
			maze[i].setLocation(x,5);
			maze[i].setIcon(newIcon);
			//maze[i].setIcon(mazewall.getMazeSprite(i));
			contentPane.add(maze[i]);
		}*/
		currentOption = 1;
		game = "menu";
		menu = "menuA";
		player = "1P";
		gameType = "arcade";
		mazeList = "pacman";
		optionLimit = 3;
		pacStyle = 0;
		pacStyle2 = 0;
		ghostStyle = 0;
		mazeStyle = 0;
		eatenLocX1 = -1;
		eatenLocY1 = -1;
		eatenLocX2 = -1;
		eatenLocX2 = -1;
		menuSetup();
	}
	
	void menuSetup(){
		
		if(menu.equals("menuA")){
			
			int Grid[] = new int[]{32,-1,15,11,0,24,4,17};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{33,-1,15,11,0,24,4,17};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(false);
			optionD.setVisible(false);
			optionE.setVisible(false);
			optionF.setVisible(false);
			optionG.setVisible(false);
			optionLimit = 2;
			
		} else if(menu.equals("menuB")){
			
			int Grid[] = new int[]{0,17,2,0,3,4};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{0,3,21,4,13,19,20,17,4};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{5,17,4,4,-1,15,11,0,24};
			optionC.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionC.setIcon(new ImageIcon(combinedImage));
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(false);
			optionE.setVisible(false);
			optionF.setVisible(false);
			optionG.setVisible(false);
			optionLimit = 3;
			
		} else if(menu.equals("menuC")){
			
			int Grid[] = new int[]{2,14,14,15,-1,0,17,2,0,3,4};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{2,14,14,15,-1,0,3,21,4,13,19,20,17,4};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{2,14,12,15,4,19,4,19,8,21,4};
			optionC.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionC.setIcon(new ImageIcon(combinedImage));
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(false);
			optionE.setVisible(false);
			optionF.setVisible(false);
			optionG.setVisible(false);
			optionLimit = 3;
			
		} else if(menu.equals("menuD")){
			
			int Grid[] = new int[]{15,0,2,-1,12,0,13};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{12,18,-1,15,0,2,-1,12,0,13};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{18,19,17,0,13,6,4};
			optionC.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionC.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{1,8,6};
			optionD.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionD.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{12,8,13,8};
			optionE.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionE.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{0,17,17,0,13,6,4,3};
			optionF.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionF.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{0,11,11};
			optionG.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionG.setIcon(new ImageIcon(combinedImage));
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(true);
			optionE.setVisible(true);
			optionF.setVisible(true);
			optionG.setVisible(true);
			optionLimit = 7;
			
		} else if(menu.equals("menuE")){
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(true);
			optionE.setVisible(true);
			optionF.setVisible(true);
			optionG.setVisible(false);
			
		} else if(menu.equals("menuF")){
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(true);
			optionE.setVisible(true);
			optionF.setVisible(true);
			optionG.setVisible(true);
			
		} else if(menu.equals("menuG")){
			
			int Grid[] = new int[]{15,32};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{6,7,14,18,19,18};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{12,0,25,4};
			optionC.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionC.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{-1,-1,-1,0,17,2,0,3,4};
			optionD.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			
			optionD.setIcon(new ImageIcon(combinedImage));
			
			pacOption.setVisible(true);
			
			ghostOption.setLocation(400,285);
			ghostOption.setVisible(true);
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(true);
			optionE.setVisible(false);
			optionF.setVisible(false);
			optionG.setVisible(false);
			optionLimit = 3;
			
		} else if(menu.equals("menuH")){
			
			int Grid[] = new int[]{15,32};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{15, 33};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{6,7,14,18,19,18};
			optionC.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionC.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{12,0,25,4};
			optionD.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionD.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{-1,-1,-1,0,17,2,0,3,4};
			optionE.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionE.setIcon(new ImageIcon(combinedImage));
			
			pacOption.setVisible(true);
			pacOption2.setVisible(true);
			
			ghostOption.setLocation(400,335);
			ghostOption.setVisible(true);
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(true);
			optionD.setVisible(true);
			optionE.setVisible(true);
			optionF.setVisible(false);
			optionG.setVisible(false);
			optionLimit = 4;
		} else if(menu.equals("menuI")) {
			int Grid[] = new int[]{15,11,0,24};
			optionA.setSize(Grid.length * 25, 30);
			BufferedImage combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionA.setIcon(new ImageIcon(combinedImage));
			
			Grid = new int[]{18,19,0,17,19,-1,14,21,4,17};
			optionB.setSize(Grid.length * 25, 30);
			combinedImage = new BufferedImage(Grid.length * 25,30,BufferedImage.TYPE_INT_ARGB);
			g = combinedImage.createGraphics();
			for(int i =0, x=0; i < Grid.length; i++,x+=25){
				if(Grid[i] >= 0){
					g.drawImage(scorePiece.getWhiteImage(Grid[i]).getImage(), x,0,null);
				}
			}
			g.dispose();
			
			optionB.setIcon(new ImageIcon(combinedImage));
			
			optionA.setVisible(true);
			optionB.setVisible(true);
			optionC.setVisible(false);
			optionD.setVisible(false);
			optionE.setVisible(false);
			optionF.setVisible(false);
			optionG.setVisible(false);
			optionLimit = 2;
			
		}
		
			
		currentOption = 1;
		selectOption.setLocation(75,230);
	}
	
	void menuSystem(){
	
		if(menu.equals("menuA")){
			switch(currentOption){
			case 1:
				player = "1P";
				menu = "menuB";
				break;
			case 2:
				player = "2P";
				menu = "menuC";
				break;
			}
		} else if(menu.equals("menuB") || menu.equals("menuC")){
			switch(currentOption){
			case 1:
				gameType = "arcade";
				menu = "menuD";
				break;
			case 2:
				gameType = "adventure";
				menu = "menuE";
				break;
			case 3:
				gameType = "freeplay";
				menu = "menuF";
				break;
			}
		} else if(menu.equals("menuD")){
			switch(currentOption){
			case 1:
				mazeList = "pacman";
				break;
			case 2:
				mazeList = "mspacman";
				break;
			case 3:
				mazeList = "strange";
				break;
			case 4:
				mazeList = "big";
				break;
			case 5:
				mazeList = "mini";
				break;
			case 6:
				mazeList = "arranged";
				break;
			case 7:
				mazeList = "all";
				break;
			}
			if(player.equals("1P")){
				menu = "menuG";
			} else {
				menu = "menuH";
			}
		} else if(menu.equals("menuG") || menu.equals("menuH")){
			menu = "menuI";
		} else if(menu.equals("menuI")){
			switch(currentOption){
			case 1:
				gameStart();
				break;
			case 2:
				menu = "menuA";
				break;
			}
		}
		
		if(!menu.equals("menuG") && !menu.equals("menuH")){
			pacOption.setVisible(false);
			pacOption2.setVisible(false);
			ghostOption.setVisible(false);
		}
	}
	
	public void newMaze(){
		
		fruit.setVisible(false);
		ghostP.setVisible(false);
		fruitP.setVisible(false);
		fruitActive = false;
		fruitFirst = true;
		fruitSecond = true;
		chase = false;
		//chaseTimer.start();
		String mode = "pacman";
		switch(currentOption){
		case 1:
			mode = "pacman";
			break;
		case 2:
			mode = "mspacman";
			break;
		case 3:
			mode = "all";
			break;
			
		}
		if((level % 5) == 0){
			grid = fullMaze.sendMaze(5);
			color = 4;
		} else{
			grid = fullMaze.sendMaze(level%5);
			color = (level % 5) -1;
		}
		int setup[] = fullMaze.MazeSetup(mazeList, level, "order"); 
		grid = fullMaze.sendMaze(setup[0]);
		color = setup[1];
		dotColor = setup[2];
		
		mazeOffset = 0;
		if(fullMaze.mazeSize(setup[0]).equals("big")){
			mazeBig = true;
			mazeSizeOffset();
		} else{
			mazeBig = false;
		}
		/*if((level % 4) == 1){
			grid = fullMaze.sendMaze(1);
			color = 0;
		}else if((level % 4) == 2){
			grid = fullMaze.sendMaze(2);
			color = 1;
		}else if((level % 4) == 3){
			grid = fullMaze.sendMaze(3);
			color = 2;
		}else {
			grid = fullMaze.sendMaze(4);
			color = 3;
		}*/
		mazewall = new Mazesprite(mazeStyle+1,color, dotColor);
		//grid = fullMaze.sendMaze(level);
	
		startX = 0;
		startY = 0;
		

		
		// This is where the new maze is drawn
		Thread t = new Thread() {
			public void run(){
			SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				dotData.clear();
				dotData = new ArrayList<Dots>();
				for(JLabel a: dots){
					contentPane.remove(a);
				}
				dots.clear();
				dots = new ArrayList<JLabel>();
				
				gameBoard.setLocation(5, 5+mazeOffset);

				int a = 0;
				for(int i = 0, y = 5; i < maze.length; i++, y = y+20){
					for(int j = 0, x = 5; j < maze[i].length; j++, x = x +20){
						if(mazeStyle+1 ==1 || mazeStyle+1 ==2){
							mazePart[i][j] = new MazePart(grid[i][j], mazewall);
						} else if(mazeStyle+1 ==3){
							Boolean[] paths = new Boolean[4];
							
							if(j-1 >= 0){
								if(grid[i][j-1] == 36 || grid[i][j-1] == 37 || grid[i][j-1] == 39){
									paths[2] = true;
								} else{
									paths[2] = false;
								}
							}else {
								paths[2] = true;
							}
							
							if(j+1 <= 27){
								if(grid[i][j+1] == 36 || grid[i][j+1] == 37 || grid[i][j+1] == 39){
									paths[3] = true;
								} else{
									paths[3] = false;
								}
							}else {
								paths[3] = true;
							}
							
							if(i-1 >= 0){
								if(grid[i-1][j] == 36 || grid[i-1][j] == 37 || grid[i-1][j] == 39){
									paths[0] = true;
								} else{
									paths[0] = false;
								}
							}else {
								paths[0] = true;
							}
							
							if(i+1 <= 35){
								if(grid[i+1][j] == 36 || grid[i+1][j] == 37 || grid[i+1][j] == 39){
									paths[1] = true;
								} else{
									paths[1] = false;
								}
							}else {
								paths[1] = true;
							}
							
							if(grid[i][j] == 36 || grid[i][j] == 37 || grid[i][j] == 39 || grid[i][j] == 42 || grid[i][j] == 43){
								mazePart[i][j] = new MazePart(grid[i][j], mazewall, paths, j,i);
							} else{
								mazePart[i][j] = new MazePart(grid[i][j], mazewall);
							}
						}
						
						maze[i][j].setIcon(mazePart[i][j].getMazeWall());
						if(mazePart[i][j].getType().equals("Dot") || mazePart[i][j].getType().equals("PowerDot")){
							dotNum++;
							mazePart[i][j].setEatenValid("Not");
							
							dotData.add(new Dots(1, mazePart[i][j].getType(), mazewall,j,i));
							dots.add(new JLabel("",JLabel.CENTER));
							dots.get(dots.size()-1).setSize(20,20);
							dots.get(dots.size()-1).setLocation((int) (j*20)+5, (int) (i*20)+5+mazeOffset);
							dots.get(dots.size()-1).setIcon(dotData.get(dotData.size()-1).getDot());
							
							contentPane.add(dots.get(dots.size()-1), contentPane.getComponentZOrder(gameBoard));
						}
						
						if(grid[i][j] == 42){
							if(player.equals("1P")){
								if(startX == 0 && startY == 0){
									startX = maze[i][j].getX() + 5;
									startY = maze[i][j].getY();
									thePac.setStartLocation(startX, startY);
								}
							} else if(player.equals("2P")){
								if(startX == 0 && startY == 0){
									startX = maze[i][j].getX();
									startY = maze[i][j].getY();
									thePac.setStartLocation(startX, startY);
								} else {
									startX = maze[i][j].getX();
									startY = maze[i][j].getY();
									thePac2.setStartLocation(startX, startY);
								}
							}
							
						}
					}
				}
				//thePac.setStartLocation(startX, startY);
				thePac.respawn();
				pacman.setLocation((int) thePac.getLocation().getX(), (int) thePac.getLocation().getY()+mazeOffset);
				if(player.equals("2P")){
					thePac2.respawn();
					pacman2.setLocation((int) thePac2.getLocation().getX(), (int) thePac2.getLocation().getY()+mazeOffset);
				}
			}
		});}};
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		

		
		switch(level){
		
		case 1:
		case 10:
			maxPowerTime = 6;
			break;
		case 2:
		case 6:
			maxPowerTime = 4;
			break;
		case 3:
			maxPowerTime = 3;
			break;
		case 4:
		case 5:
			maxPowerTime = 2;
			break;
		case 7:
		case 8:
		case 9:
			maxPowerTime = 1;
			break;
		default:
			maxPowerTime = 0;
			break;
		}
		if(alive1P){
			thePac.respawn();
		}
		if(alive2P){
			thePac2.respawn();
		}
		theRed.respawn();
		thePink.respawn();
		theBlue.respawn();
		theOrange.respawn();
		Thread t2 = new Thread(){
			
			public void run() {
			
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				pacman.setLocation((int) thePac.getLocation().getX(), (int) thePac.getLocation().getY()+mazeOffset);
				pacman.setIcon(thePac.getImage());
				pacman2.setLocation((int) thePac2.getLocation().getX(), (int) thePac2.getLocation().getY()+mazeOffset);
				pacman2.setIcon(thePac2.getImage());
				Red.setLocation((int) theRed.getLocation().getX(), (int) theRed.getLocation().getY()+mazeOffset);
				Red.setIcon(theRed.getImage());
				Pink.setLocation((int) thePink.getLocation().getX(), (int) thePink.getLocation().getY()+mazeOffset);
				Pink.setIcon(thePink.getImage());
				Blue.setLocation((int) theBlue.getLocation().getX(), (int) theBlue.getLocation().getY()+mazeOffset);
				Blue.setIcon(theBlue.getImage());
				Orange.setLocation((int) theOrange.getLocation().getX(), (int) theOrange.getLocation().getY()+mazeOffset);
				Orange.setIcon(theOrange.getImage());
				fruit.setLocation(270, 340 + mazeOffset);
				fruitP.setLocation(fruit.getLocation());
			}
		});}};
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eatenLocX1 = -1;
		eatenLocY1 = -1;
		eatenLocX2 = -1;
		eatenLocX2 = -1;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){
				
		}

	}
	
	
	public void getKey(KeyEvent input){
		
		int keyCode = input.getKeyCode();

		if(game.equals("menu")){
			if(keyCode == KeyEvent.VK_UP && currentOption > 1){
				currentOption--;
				selectOption.setLocation(75,selectOption.getY()-50);
			} else if(keyCode == KeyEvent.VK_DOWN && currentOption < optionLimit){
				currentOption++;
				selectOption.setLocation(75,selectOption.getY()+50);
			} else if(keyCode == KeyEvent.VK_ENTER){
				menuSystem();
				if(game.equals("menu")){
					menuSetup();
				}
			} else if(keyCode == KeyEvent.VK_LEFT){
				if((menu.equals("menuG") || menu.equals("menuH")) && currentOption == 1){
					if(pacStyle >0){
						pacStyle--;
					}
				} else if(menu.equals("menuH") && currentOption == 2){
					if(pacStyle2 >0){
						pacStyle2--;
					}
				} else if((menu.equals("menuG") && currentOption == 2) ||(menu.equals("menuH") && currentOption == 3)){
					if(ghostStyle >0){
						ghostStyle--;
					}
				} else if((menu.equals("menuG") && currentOption == 3) ||(menu.equals("menuH") && currentOption == 4)){
					if(mazeStyle >0){
						mazeStyle--;
					}
					if(menu.equals("menuG")){
						optionD.setSize(mazeOptions[mazeStyle].length * 25, 30);
						BufferedImage combinedImage = new BufferedImage(mazeOptions[mazeStyle].length * 25,30,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = combinedImage.createGraphics();
						for(int i =0, x=0; i < mazeOptions[mazeStyle].length; i++,x+=25){
							if(mazeOptions[mazeStyle][i] >= 0){
								g.drawImage(scorePiece.getWhiteImage(mazeOptions[mazeStyle][i]).getImage(), x,0,null);
							}
						}
						
						optionD.setIcon(new ImageIcon(combinedImage));
					} else if(menu.equals("menuH")){
						optionE.setSize(mazeOptions[mazeStyle].length * 25, 30);
						BufferedImage combinedImage = new BufferedImage(mazeOptions[mazeStyle].length * 25,30,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = combinedImage.createGraphics();
						for(int i =0, x=0; i < mazeOptions[mazeStyle].length; i++,x+=25){
							if(mazeOptions[mazeStyle][i] >= 0){
								g.drawImage(scorePiece.getWhiteImage(mazeOptions[mazeStyle][i]).getImage(), x,0,null);
							}
						}
						
						optionE.setIcon(new ImageIcon(combinedImage));
					}
				}

				pacOption.setIcon(thePac.getOptions(pacStyle));
				pacOption2.setIcon(thePac.getOptions(pacStyle2));
				ghostOption.setIcon(theRed.getOption(ghostStyle));
			} else if(keyCode == KeyEvent.VK_RIGHT){
				if((menu.equals("menuG") || menu.equals("menuH")) && currentOption == 1){
					if(pacStyle <4){
						pacStyle++;
					}
				} else if(menu.equals("menuH") && currentOption == 2){
					if(pacStyle2 <4){
						pacStyle2++;
					}
				} else if((menu.equals("menuG") && currentOption == 2) ||(menu.equals("menuH") && currentOption == 3)){
					if(ghostStyle <2){
						ghostStyle++;
					}
				} else if((menu.equals("menuG") && currentOption == 3) ||(menu.equals("menuH") && currentOption == 4)){
					if(mazeStyle <2){
						mazeStyle++;
					}
					if(menu.equals("menuG")){
						optionD.setSize(mazeOptions[mazeStyle].length * 25, 30);
						BufferedImage combinedImage = new BufferedImage(mazeOptions[mazeStyle].length * 25,30,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = combinedImage.createGraphics();
						for(int i =0, x=0; i < mazeOptions[mazeStyle].length; i++,x+=25){
							if(mazeOptions[mazeStyle][i] >= 0){
								g.drawImage(scorePiece.getWhiteImage(mazeOptions[mazeStyle][i]).getImage(), x,0,null);
							}
						}
						
						optionD.setIcon(new ImageIcon(combinedImage));
					} else if(menu.equals("menuH")){
						optionE.setSize(mazeOptions[mazeStyle].length * 25, 30);
						BufferedImage combinedImage = new BufferedImage(mazeOptions[mazeStyle].length * 25,30,BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = combinedImage.createGraphics();
						for(int i =0, x=0; i < mazeOptions[mazeStyle].length; i++,x+=25){
							if(mazeOptions[mazeStyle][i] >= 0){
								g.drawImage(scorePiece.getWhiteImage(mazeOptions[mazeStyle][i]).getImage(), x,0,null);
							}
						}
						
						optionE.setIcon(new ImageIcon(combinedImage));
					}
				}

				pacOption.setIcon(thePac.getOptions(pacStyle));
				pacOption2.setIcon(thePac.getOptions(pacStyle2));
				ghostOption.setIcon(theRed.getOption(ghostStyle));
			}
			
			
		} else {
	        if(keyCode == KeyEvent.VK_LEFT){
	        	if(firstKey ==0){
	        		firstKey = keyCode;
	        	} else if (firstKey != KeyEvent.VK_LEFT && nextKey == 0){
	        		nextKey = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_RIGHT){
	        	if(firstKey ==0){
	        		firstKey = keyCode;
	        	} else if (firstKey != KeyEvent.VK_RIGHT &&nextKey == 0){
	        		nextKey = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_UP){
	        	if(firstKey ==0){
	        		firstKey = keyCode;
	        	} else if (firstKey != KeyEvent.VK_UP &&nextKey == 0){
	        		nextKey = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_DOWN){
	        	if(firstKey ==0){
	        		firstKey = keyCode;
	        	} else if (firstKey != KeyEvent.VK_DOWN &&nextKey == 0){
	        		nextKey = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_A){
	        	if(firstKey2P ==0){
	        		firstKey2P = keyCode;
	        	} else if (firstKey2P != KeyEvent.VK_A && nextKey2P == 0){
	        		nextKey2P = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_D){
	        	if(firstKey2P ==0){
	        		firstKey2P = keyCode;
	        	} else if (firstKey2P != KeyEvent.VK_D &&nextKey2P == 0){
	        		nextKey2P = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_W){
	        	if(firstKey2P ==0){
	        		firstKey2P = keyCode;
	        	} else if (firstKey2P != KeyEvent.VK_W &&nextKey2P == 0){
	        		nextKey2P = keyCode;
	        	}
	        } else if(keyCode == KeyEvent.VK_S){
	        	if(firstKey2P ==0){
	        		firstKey2P = keyCode;
	        	} else if (firstKey2P != KeyEvent.VK_S &&nextKey2P == 0){
	        		nextKey2P = keyCode;
	        	}
	        }else if(keyCode == KeyEvent.VK_P){
	        	System.out.println("here");
	        	if(!pause){
	        		pause = true;
	        		/*ghostTimer.stop();
					powerTimer.stop();
					chaseTimer.stop();
					ghostWhiteTimer.stop();*/
	        	} else{
	        		pause = false;
	        		/*ghostTimer.start();;
					powerTimer.start();
					chaseTimer.start();
					ghostWhiteTimer.start();*/
	        	}
	        }
		}
		/*if(currentKey == 0){
			currentKey = input.getKeyCode();
		}*/
	}
	
	public void mazeSizeOffset(){
		
		int pacLoc = thePac.getLocation().y;
		
		if(pacLoc > 370){
			mazeOffset = -120;
		} else{
			mazeOffset = 0;
		}
		
		/*
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				gameBoard.setLocation(5, 5 + mazeOffset);
				pacman.setLocation((int) thePac.getLocation().getX(), (int) thePac.getLocation().getY()+mazeOffset);
				pacman.setIcon(thePac.getImage());
				pacman2.setLocation((int) thePac2.getLocation().getX(), (int) thePac2.getLocation().getY()+mazeOffset);
				pacman2.setIcon(thePac2.getImage());
				Red.setLocation((int) theRed.getLocation().getX(), (int) theRed.getLocation().getY()+mazeOffset);
				Red.setIcon(theRed.getImage());
				Pink.setLocation((int) thePink.getLocation().getX(), (int) thePink.getLocation().getY()+mazeOffset);
				Pink.setIcon(thePink.getImage());
				Blue.setLocation((int) theBlue.getLocation().getX(), (int) theBlue.getLocation().getY()+mazeOffset);
				Blue.setIcon(theBlue.getImage());
				Orange.setLocation((int) theOrange.getLocation().getX(), (int) theOrange.getLocation().getY()+mazeOffset);
				Orange.setIcon(theOrange.getImage());
				fruit.setLocation(270, 340 + mazeOffset);
				fruitP.setLocation(fruit.getLocation());
			}
		});*/
		
	}
	
	
	public void removeKey(KeyEvent input){
		
		int keyCode = input.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
        	if(firstKey == keyCode){
        		firstKey = 0;
        	} else if (nextKey == keyCode){
        		nextKey = 0;
        	}
        } else if(keyCode == KeyEvent.VK_RIGHT){
        	if(firstKey == keyCode){
        		firstKey = 0;
        	} else if (nextKey == keyCode){
        		nextKey = 0;
        	}
        } else if(keyCode == KeyEvent.VK_UP){
        	if(firstKey == keyCode){
        		firstKey = 0;
        	} else if (nextKey == keyCode){
        		nextKey = 0;
        	}
        } else if(keyCode == KeyEvent.VK_DOWN){
        	if(firstKey == keyCode){
        		firstKey = 0;
        	} else if (nextKey == keyCode){
        		nextKey = 0;
        	}
        } else if(keyCode == KeyEvent.VK_A){
        	if(firstKey2P == keyCode){
        		firstKey2P = 0;
        	} else if (nextKey2P == keyCode){
        		nextKey2P = 0;
        	}
        } else if(keyCode == KeyEvent.VK_D){
        	if(firstKey2P == keyCode){
        		firstKey2P = 0;
        	} else if (nextKey2P == keyCode){
        		nextKey2P = 0;
        	}
        } else if(keyCode == KeyEvent.VK_W){
        	if(firstKey2P == keyCode){
        		firstKey2P = 0;
        	} else if (nextKey2P == keyCode){
        		nextKey2P = 0;
        	}
        } else if(keyCode == KeyEvent.VK_S){
        	if(firstKey2P == keyCode){
        		firstKey2P = 0;
        	} else if (nextKey2P == keyCode){
        		nextKey2P = 0;
        	}
        }
        
        if(firstKey == 0 && nextKey != 0){
        	firstKey = nextKey;
        	nextKey = 0;
        }
        
        if(firstKey2P == 0 && nextKey2P != 0){
        	firstKey2P = nextKey2P;
        	nextKey2P = 0;
        }
		/*if(currentKey == input.getKeyCode()){
			currentKey = 0;
		}*/
	}
	
	public void keyAction(int input){
		
		if(input == KeyEvent.VK_RIGHT){
			if(thePac.getRValid()){
				thePac.setDirection('r');
				thePac.changeDirection();
			}
		}
		if(input == KeyEvent.VK_LEFT){
			if(thePac.getLValid()){
				thePac.setDirection('l');
				thePac.changeDirection();
				//thePac.setIndex(3);
			}
		}
		if(input == KeyEvent.VK_DOWN){
			if(thePac.getDValid()){
				thePac.setDirection('d');
				thePac.changeDirection();
				//thePac.setIndex(7);
			}
		}
		if(input == KeyEvent.VK_UP){
			if(thePac.getUValid()){
				thePac.setDirection('u');
				thePac.changeDirection();
				//thePac.setIndex(5);
			}
		}
		
		if(input == KeyEvent.VK_D){
			if(thePac2.getRValid()){
				thePac2.setDirection('r');
				thePac2.changeDirection();
			}
		}
		if(input == KeyEvent.VK_A){
			if(thePac2.getLValid()){
				thePac2.setDirection('l');
				thePac2.changeDirection();
				//thePac2.setIndex(3);
			}
		}
		if(input == KeyEvent.VK_S){
			if(thePac2.getDValid()){
				thePac2.setDirection('d');
				thePac2.changeDirection();
				//thePac2.setIndex(7);
			}
		}
		if(input == KeyEvent.VK_W){
			if(thePac2.getUValid()){
				thePac2.setDirection('u');
				thePac2.changeDirection();
				//thePac.setIndex(5);
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new MainBoard();
		frame.setPreferredSize(new Dimension(800,1000));
		frame.getContentPane().setBackground( Color.BLACK );
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        } );
	}


	public void gameStart() {
		
		System.out.println(player + " " + gameType + " " + mazeList);
		button.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);/*
		if(e.getSource() == button){
			thePac = new PacmanData("pacman","Player1",2,265,460);
			player = "1P";
		} else if(e.getSource() == button2){
			thePac = new PacmanData("mspacman","Player1",1,265,460);
			player = "1P";
		} else if(e.getSource() == button3){
			thePac = new PacmanData("pacman","Player1",1, 265, 460);
			thePac2 = new PacmanData("mspacman","Player2",2,265,460);
			player = "2P";
		}*/
		switch(pacStyle){
		case 0:
			thePac = new PacmanData("pacman","Player1",1,265,460);
			break;
		case 1:
			thePac = new PacmanData("mspacman","Player1",1,265,460);
			break;
		case 2:
			thePac = new PacmanData("pacman","Player1",2,265,460);
			break;
		case 3:
			thePac = new PacmanData("mspacman","Player1",2,265,460);
			break;
		case 4:
			thePac = new PacmanData("pacman","Player1",3,265,460);
			break;
		}
		
		if(player.equals("2P")){
			switch(pacStyle2){
			case 0:
				thePac2 = new PacmanData("pacman","Player2",1,265,460);
				break;
			case 1:
				thePac2 = new PacmanData("mspacman","Player2",1,265,460);
				break;
			case 2:
				thePac2 = new PacmanData("pacman","Player2",2,265,460);
				break;
			case 3:
				thePac2 = new PacmanData("mspacman","Player2",2,265,460);
				break;
			case 4:
				thePac = new PacmanData("pacman","Player2",3,265,460);
				break;
			}
		}
		
		theRed = new Ghosts("Red",ghostStyle + 1,270, 220);
		thePink = new Ghosts("Pink",ghostStyle + 1, 270,280); 
		theBlue =new Ghosts("Blue",ghostStyle + 1, 310,280);
		theOrange =new Ghosts("Orange",ghostStyle + 1, 230,280);
		liveIcon = thePac.getLiveImage();
		liveIcon2P = thePac2.getLiveImage();
		introBoard.setVisible(false);
		optionA.setVisible(false);
		optionB.setVisible(false);
		optionC.setVisible(false);
		optionD.setVisible(false);
		optionE.setVisible(false);
		optionF.setVisible(false);
		optionG.setVisible(false);
		selectOption.setVisible(false);
		pacOption.setVisible(false);
		pacOption2.setVisible(false);
		ghostOption.setVisible(false);
		game = "play";
		//gamestartlevel
		level = 4;
		score = 0;
		lives = 3;
		ghostCombo = 0;
		dotNum =0;
		pause = false;
		dead = false;
		alive1P = true;
		pacman.setVisible(true);
		if(player.equals("2P")){
			lives2P = 3;
			alive2P = true;
			pacman2.setVisible(true);
		}else{
			alive2P = false;
			lives2P = 0;
			pacman2.setVisible(false);
		}
		Red.setVisible(true);
		Pink.setVisible(true);
		Blue.setVisible(true);
		Orange.setVisible(true);
		newMaze();
		System.out.println("Dot Data :"+ dotData.size());
		if(playing == false){
			playing = true;
			animate();
		}
		else {
			playing = false;
		}
	}
	
	public void checkGhostBox(Ghosts input){
		
		Rectangle box = new Rectangle(270,220, 20,20);
		Rectangle locat = input.getTrue();
		Rectangle leftLocat = input.getLeft();
		Rectangle rightLocat = input.getRight();
		Rectangle upLocat = input.getUp();
		Rectangle downLocat = input.getDown();
		
		if(input.getTrue().intersects(box) && input.getStatus().equals("Eaten")){
			//System.out.println( locat.x + ", " + locat.y);
			if(locat.x == 275 && locat.y == 225){
				
				input.setGoingInBox(true);
				input.setBox(true);
				input.inBox();
				/*switch(input.getDirection()){
					case 'r':
						input.setIndex(0);
						break;
					case 'l':
						input.setIndex(2);
						break;
					case 'u':
						input.setIndex(4);
						break;
					case 'd':
						input.setIndex(6);
						break;
				}*/
				//input.setStatus("Normal");
			}
		}
		
		if(leftLocat.x >= 5 && leftLocat.x < 565 && leftLocat.y >=5 && leftLocat.y < 745){
			if((leftLocat.x- 5) % 20 == 0 &&(leftLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((leftLocat.y - 5)/20)][(int) ((leftLocat.x -5)/20)].getType().equals("Wall")){
					input.setLValid(false);
				}else {
					input.setLValid(true);
				}
			}else if((leftLocat.y-5)%20 == 0){
				Boolean valid;

				valid = mazePart[(int) ((leftLocat.y - 5)/20)][(int) ((leftLocat.x -5+20)/20)].getType().equals("Wall");
				
				if(valid){
				
					input.setLValid(false);
				}else{
					input.setLValid(true);
				}
			}else if ((leftLocat.x - 5) % 20 == 0){
				
				input.setLValid(false);
			}
		}
		if((rightLocat.x >= 5 && rightLocat.x < 565 & rightLocat.y >= 5 && rightLocat.y < 745)){
			if((rightLocat.x- 5) % 20 == 0 &&(rightLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((rightLocat.y - 5)/20)][(int) ((rightLocat.x -5)/20)].getType().equals("Wall")){
					input.setRValid(false);
				}else {
					input.setRValid(true);
				}
			}else if((rightLocat.y-5)%20 == 0){
				Boolean valid;
				valid = mazePart[(int) ((rightLocat.y - 5)/20)][(int) ((rightLocat.x -5-20)/20)].getType().equals("Wall");
				if(valid){
				
					input.setRValid(false);
				}else{
					input.setRValid(true);
				}
			}else if ((rightLocat.x - 5) % 20 == 0){
				
				input.setRValid(false);
			}
		}
		
		if( upLocat.x >= 5 && upLocat.y >=5 && upLocat.x < 565 && upLocat.y < 745){
			if((upLocat.x- 5) % 20 == 0 &&(upLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((upLocat.y - 5)/20)][(int) ((upLocat.x -5)/20)].getType().equals("Wall")){
					input.setUValid(false);
				}else {
					input.setUValid(true);
				}
			}else if((upLocat.x-5)%20 == 0){
				Boolean valid;
				valid = mazePart[(int) ((upLocat.y - 5+20)/20)][(int) ((upLocat.x -5)/20)].getType().equals("Wall");
				if(valid){
				
					input.setUValid(false);
				}else{
					input.setUValid(true);
				}
			}else if ((upLocat.y - 5) % 20 == 0){
				
				input.setUValid(false);
			}
		}
		if(downLocat.y < 745 && downLocat.x < 565 && downLocat.x >=5 &&downLocat.y >= 5){
			if((downLocat.x- 5) % 20 == 0 &&(downLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((downLocat.y - 5)/20)][(int) ((downLocat.x -5)/20)].getType().equals("Wall")){
					input.setDValid(false);
				}else {
					input.setDValid(true);
				}
			}else if((downLocat.x-5)%20 == 0){
				Boolean valid;
				valid = mazePart[(int) ((downLocat.y - 5-20)/20)][(int) ((downLocat.x -5)/20)].getType().equals("Wall");
				if(valid){
					input.setDValid(false);
				}else{
					input.setDValid(true);
				}
			}else if ((downLocat.y - 5) % 20 == 0){
				
				input.setDValid(false);
			}
		}
		if(!input.getBox()){
			if(locat.x >=5 && locat.y >=5 && locat.x < 565 && locat.y <745){
				if((locat.x -5) % 20 ==0 && (locat.y - 5) %20 ==0){
					if(!input.getStatus().equals("Eaten")){
						
						Rectangle pac = null;
						char pacDirection = 0;
						if(alive1P && !alive2P){
							pac = thePac.getTrue();
							pacDirection = thePac.getDirection();
						} else if(!alive1P && alive2P){
							pac = thePac2.getTrue();
							pacDirection = thePac2.getDirection();
						} else{
							int inputX = ((2 * input.getTrue().x) + input.getTrue().width)/2;
							int inputY = ((2 * input.getTrue().y) + input.getTrue().height)/2;
							int pac1X = ((2 * thePac.getTrue().x) + thePac.getTrue().width)/2;
							int pac1Y = ((2 * thePac.getTrue().y) + thePac.getTrue().height)/2;
							int pac2X = ((2 * thePac2.getTrue().x) + thePac2.getTrue().width)/2;
							int pac2Y = ((2 * thePac2.getTrue().y) + thePac2.getTrue().height)/2;
							double distance1 = Math.sqrt(((pac1X - inputX)*(pac1X-inputX)) + ((pac1Y - inputY)*(pac1Y-inputY)));
							double distance2 = Math.sqrt(((pac2X - inputX)*(pac2X-inputX)) + ((pac2Y - inputY)*(pac2Y-inputY)));
							
							if(distance1 < distance2){
								pac = thePac.getTrue();
								pacDirection = thePac.getDirection();
							} else if(distance1 > distance2){
								pac = thePac2.getTrue();
								pacDirection = thePac2.getDirection();
							} else{
								Random rand = new Random();
								int randomNum = rand.nextInt(1);
								switch(randomNum){
								case 0:
									pac = thePac.getTrue();
									pacDirection = thePac.getDirection();
									break;
								case 1:
									pac = thePac2.getTrue();
									pacDirection = thePac2.getDirection();
									break;
								}
							}
						}
						
						if(input.getId().equals("Red")){
							if(chase){
								input.setTarget(pac);
							}else{
								Rectangle corner = new Rectangle(5+(maze[0].length * 20),5,20,20);
								input.setTarget(corner);
							}
						} else if(input.getId().equals("Pink")){
							
							if(chase){
								Rectangle pinkTarget;
								if(pacDirection == 'l'){
									 pinkTarget = new Rectangle(pac.x -40, pac.y,20,20);
									 input.setTarget(pinkTarget);
								}else if(pacDirection == 'r'){
									 pinkTarget = new Rectangle(pac.x +40, pac.y,20,20);
									 input.setTarget(pinkTarget);
								}else if(pacDirection == 'u'){
									 pinkTarget = new Rectangle(pac.x, pac.y - 40,20,20);
									 input.setTarget(pinkTarget);
								}else if(pacDirection == 'd'){
									 pinkTarget = new Rectangle(pac.x, pac.y + 40,20,20);
									 input.setTarget(pinkTarget);
								}
							}else{
								Rectangle corner = new Rectangle(5,5,20,20);
								input.setTarget(corner);
							}
						} else if(input.getId().equals("Blue")){
							
							if(chase){
								int redX = theRed.getTrue().x;
								int redY = theRed.getTrue().y;
								int pacX = pac.x;
								int pacY = pac.y;
								
								int targetX = 0;
								int targetY = 0;
								
								if(redX <= pacX){
									targetX = (pacX - redX) + pacX;
								}else{
									targetX = pacX - (redX - pacX);
								}
								
								if(redY <= pacY){
									targetY = (pacY - redY) + pacY;
								}else{
									targetY = pacY - (redY - pacY);
								}
								
								Rectangle blueTarget = new Rectangle(targetX,targetY,20,20);
								input.setTarget(blueTarget);
							}else{
								Rectangle corner = new Rectangle(5,5+(maze.length * 20),20,20);
								input.setTarget(corner);
							}
						}else{
							if(chase){
								int orangeX = ((2 * theOrange.getTrue().x) + theOrange.getTrue().width)/2;
								int orangeY = ((2 * theOrange.getTrue().y) + theOrange.getTrue().height)/2;
								int pacX = ((2 * pac.x) + pac.width)/2;
								int pacY = ((2 * pac.y) + pac.height)/2;
								double distance = Math.sqrt(((pacX - orangeX)*(pacX-orangeX)) + ((pacY - orangeY)*(pacY-orangeY)));
								if(distance <= 100){
									Rectangle corner = new Rectangle(5+(maze[0].length * 20),5+(maze.length * 20),20,20);
									input.setTarget(corner);
								}
								else{
									input.setTarget(pac);
								}
								
							}else{
								Rectangle corner = new Rectangle(5+(maze[0].length * 20),5+(maze.length * 20),20,20);
								input.setTarget(corner);
							}
						}
						
						
					} else{
						input.setTarget(box);
					}
					input.directionMovement();
					if(input.getStatus().equals("Normal")){
						input.setSpeed(4);
						switch(input.getDirection()){
							case 'r':
								input.setIndex(0);
								break;
							case 'l':
								input.setIndex(2);
								break;
							case 'u':
								input.setIndex(4);
								break;
							case 'd':
								input.setIndex(6);
								break;
						}
					}else if (powerActive && powerSec >2 && input.getStatus().equals("Blue")){
						//input.setIndex(8);
						input.setSpeed(2);
					}else if (powerActive && powerSec <= 2 && input.getStatus().equals("Blue")){
						//input.setIndex(10);
						input.setSpeed(2);
					}else if(!powerActive && input.getStatus().equals("Blue")){
						input.setStatus("Normal");
						input.setSpeed(4);
						switch(input.getDirection()){
							case 'r':
								input.setIndex(0);
								break;
							case 'l':
								input.setIndex(2);
								break;
							case 'u':
								input.setIndex(4);
								break;
							case 'd':
								input.setIndex(6);
								break;
						}
						
					}else{
						switch(input.getDirection()){
							case 'r':
								input.setIndex(12);
								input.setSpeed(5);
								break;
							case 'l':
								input.setIndex(13);
								input.setSpeed(5);
								break;
							case 'u':
								input.setIndex(14);
								input.setSpeed(5);
								break;
							case 'd':
								input.setIndex(15);
								input.setSpeed(5);
								break;
						}
					}
				}
			}else if(locat.x == -15 && input.getDirection() == 'l'){
				input.setLocation(565,(int) locat.y);
			} else if(locat.x == 565 && input.getDirection() == 'r'){
				input.setLocation(-15, (int) locat.y);
			}
		} else if (input.getBox() && !input.getGoingInBox()){
			if(!powerActive && input.getStatus().equals("Blue")){
				input.setStatus("Normal");
				//input.setSpeed(4);
				switch(input.getDirection()){
					case 'r':
						input.setIndex(0);
						break;
					case 'l':
						input.setIndex(2);
						break;
					case 'u':
						input.setIndex(4);
						break;
					case 'd':
						input.setIndex(6);
						break;
				}
				
			}
			if(!input.getOutBox()){
				input.whileBox();
			} else {
				input.outBox();
			}
		} else if (input.getGoingInBox()){
			input.inBox();
		}
		
		
	}
	
	public void checkBox(PacmanData input){
	
		Rectangle locat = input.getTrue();
		
		double xLocat = locat.getX();
		double yLocat = locat.getY();
		
		
		/*if((xLocat- 5) % 20 == 0 &&(yLocat- 5) % 20 == 0){
			System.out.println("Found a cell " + mazePart[(int) ((yLocat - 5)/20)][(int) ((xLocat -5)/20)].getType() + "   "+ xLocat + "," + yLocat);
		}*/
		if((xLocat >=5 && yLocat >=5) && (xLocat <565 && yLocat < 745)){
			String current = mazePart[(int) ((yLocat - 5)/20)][(int) ((xLocat -5)/20)].getType();
			if(current.equals("Dot") || current.equals("PowerDot")){
				if(mazePart[(int) ((yLocat - 5)/20)][(int) ((xLocat -5)/20)].getEatenValid().equals("Not")){
					int xCurrent = (int) ((xLocat-5)/20);
					xCurrent*=20;
					int yCurrent = (int) ((yLocat-5)/20);
					yCurrent*=20;
					
					Rectangle dot = new Rectangle(xCurrent+7,yCurrent+7,6,6);
					if(input.getTrue().contains(dot)){
						
						if(input.equals(thePac)){
							eatenLocX1 = (int) ((xLocat - 5)/20);
							eatenLocY1 = (int) ((yLocat -5)/20);
						} else if (input.equals(thePac2)){
							eatenLocX2 = (int) ((xLocat - 5)/20);
							eatenLocY2 = (int) ((yLocat -5)/20);
						}

						
						mazePart[(int) ((yLocat - 5)/20)][(int) ((xLocat -5)/20)].setEatenValid("Yes");
						dotNum--;
						if(current.equals("PowerDot")){
							powerSec = (int) maxPowerTime;
							ghostCombo = 0;
							powerActive = true;
							if(theRed.getStatus().equals("Normal")){
								theRed.setStatus("Blue");
								theRed.reverse();
							}
							if(thePink.getStatus().equals("Normal")){
								thePink.setStatus("Blue");
								thePink.reverse();
							}
							if(theBlue.getStatus().equals("Normal")){
								theBlue.setStatus("Blue");
								theBlue.reverse();
							}
							if(theOrange.getStatus().equals("Normal")){
								theOrange.setStatus("Blue");
								theOrange.reverse();
							}
							powerTimer.start();
							chase = true;
		
							score+=100;
						}else{
							score+=10;
						}
					}
				}
			}
		} else if( (xLocat <= -15) && input.getDirection() == 'l'){
			input.setLocation(565,(int) yLocat);
		} else if(xLocat >= 565 && input.getDirection() == 'r'){
			input.setLocation(-15, (int) yLocat);
		}
		
		Rectangle leftLocat = input.getLeft();
		Rectangle rightLocat = input.getRight();
		Rectangle upLocat = input.getUp();
		Rectangle downLocat = input.getDown();
		
		if(leftLocat.x >= 5 && leftLocat.x < 565 && leftLocat.y >=5 && leftLocat.y < 745){
			if((leftLocat.x- 5) % 20 == 0 &&(leftLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((leftLocat.y - 5)/20)][(int) ((leftLocat.x -5)/20)].getType().equals("Wall")){
					input.setLValid(false);
				}else {
					input.setLValid(true);
				}
			}else if((leftLocat.y-5)%20 == 0){
				Boolean valid;
				
				try{
					valid = mazePart[(int) ((leftLocat.y - 5)/20)][(int) ((leftLocat.x -5+20)/20)].getType().equals("Wall");
				} catch (Exception ArrayIndexOutOfBoundsException){
					System.out.println("End: " + leftLocat.x + " " + leftLocat.y + " : "+(leftLocat.y - 5)/20 + "   " + (leftLocat.x -5+20)/20);
					valid = false;
				} 
				if(valid){
				
					input.setLValid(false);
				}else{
					input.setLValid(true);
				}
			}else if ((leftLocat.x - 5) % 20 == 0){
				
				input.setLValid(false);
			}
		}
		
		if((rightLocat.x >= 5 && rightLocat.x < 565 & rightLocat.y >= 5 && rightLocat.y < 745)){
			if((rightLocat.x- 5) % 20 == 0 &&(rightLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((rightLocat.y - 5)/20)][(int) ((rightLocat.x -5)/20)].getType().equals("Wall")){
					input.setRValid(false);
				}else {
					input.setRValid(true);
				}
			}else if((rightLocat.y-5)%20 == 0){
				Boolean valid;
				
				valid = mazePart[(int) ((rightLocat.y - 5)/20)][(int) ((rightLocat.x -5-20)/20)].getType().equals("Wall");
				if(valid){
				
					input.setRValid(false);
				}else{
					input.setRValid(true);
				}
			}else if ((rightLocat.x - 5) % 20 == 0){
				
				input.setRValid(false);
			}
		}
		
		if( upLocat.x >= 5 && upLocat.y >=5 && upLocat.x < 565 && upLocat.y < 745){
			if((upLocat.x- 5) % 20 == 0 &&(upLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((upLocat.y - 5)/20)][(int) ((upLocat.x -5)/20)].getType().equals("Wall")){
					input.setUValid(false);
				}else {
					input.setUValid(true);
				}
			}else if((upLocat.x-5)%20 == 0){
				Boolean valid;
				valid = mazePart[(int) ((upLocat.y - 5+20)/20)][(int) ((upLocat.x -5)/20)].getType().equals("Wall");
				if(valid){
				
					input.setUValid(false);
				}else{
					input.setUValid(true);
				}
			}else if ((upLocat.y - 5) % 20 == 0){
				
				input.setUValid(false);
			}
		}
		if(downLocat.y < 745 && downLocat.x < 565 && downLocat.x >=5 &&downLocat.y >= 5){
			if((downLocat.x- 5) % 20 == 0 &&(downLocat.y- 5) % 20 == 0){
				if(mazePart[(int) ((downLocat.y - 5)/20)][(int) ((downLocat.x -5)/20)].getType().equals("Wall")){
					input.setDValid(false);
				}else {
					input.setDValid(true);
				}
			}else if((downLocat.x-5)%20 == 0){
				Boolean valid;
				valid = mazePart[(int) ((downLocat.y - 5-20)/20)][(int) ((downLocat.x -5)/20)].getType().equals("Wall");
				if(valid){
					input.setDValid(false);
				}else{
					input.setDValid(true);
				}
			}else if ((downLocat.y - 5) % 20 == 0){
				
				input.setDValid(false);
			}
		}
	}
	
	public void checkEating(Ghosts input, JLabel ghostInput){
		Rectangle pac = new Rectangle(thePac.getTrue().x+3,thePac.getTrue().y+3,14,14);
		//pac =thePac.getTrue()
		Rectangle pac2 = new Rectangle(thePac2.getTrue().x+3,thePac2.getTrue().y+3,14,14);
		Rectangle ghost = new Rectangle(input.getTrue().x+3,input.getTrue().y+3,14,14);
		
		if(input.getStatus().equals("Normal")){
			if(alive1P){
				if(pac.intersects(ghost)){
					dead = true;
					alive1P = false;
				}
			}
			if(alive2P){
				if(pac2.intersects(ghost)){
					dead = true;
					alive2P = false;
				}
			}
		} else if(powerActive && input.getStatus().equals("Blue")){
			if((alive1P &&pac.intersects(ghost)) || (alive2P && pac2.intersects(ghost))){
				input.setStatus("Eaten");
				switch(input.getDirection()){
					case 'r':
						input.setIndex(12);
						break;
					case 'l':
						input.setIndex(13);
						break;
					case 'u':
						input.setIndex(14);
						break;
					case 'd':
						input.setIndex(15);
						break;
				}
				ghostCombo++;
				if(ghostCombo == 1){
					score+=200;
					ghostP.setLocation((int) input.getLocation().getX(), (int) input.getLocation().getY()+mazeOffset);

					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							ghostP.setIcon(pointSystem.getGhostPoint(0));
						}
					});
					ghostP.setVisible(true);
					
					GhostP.start();
				}else if(ghostCombo == 2){
					score+=400;
					ghostP.setLocation((int) input.getLocation().getX(), (int) input.getLocation().getY()+mazeOffset);
					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							ghostP.setIcon(pointSystem.getGhostPoint(1));
						}
					});
					ghostP.setVisible(true);
					GhostP.start();
				}else if(ghostCombo == 3){
					score+=800;
					ghostP.setLocation((int) input.getLocation().getX(), (int) input.getLocation().getY()+mazeOffset);
					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							ghostP.setIcon(pointSystem.getGhostPoint(2));
						}
					});
					ghostP.setVisible(true);
					GhostP.start();
				}else if(ghostCombo == 4){
					score+=1600;
					ghostP.setLocation((int) input.getLocation().getX(), (int) input.getLocation().getY()+mazeOffset);
					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							ghostP.setIcon(pointSystem.getGhostPoint(3));
						}
					});
					ghostP.setVisible(true);
					GhostP.start();
				}
			}
		}
	}
	
	public void scoreGUI(){
		
		BufferedImage combinedImage = new BufferedImage( 
                155, 
                30, 
                BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = combinedImage.createGraphics();
		
		int tempScore = score;
		int i = 125;
		while(tempScore > 0){
			int remain = tempScore % 10;
			g.drawImage(scorePiece.getWhiteImage(31 + remain).getImage(),i,0,null);
			tempScore = tempScore /10;
			i-=25;
		}
	
        g.dispose();
		scoreResult = new ImageIcon(combinedImage);
	}
	
	public void liveGUI(){

		BufferedImage combinedImage = new BufferedImage( 
                120, 
                40, 
                BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = combinedImage.createGraphics();
		
		for(int i = 1,x = 0; i < lives; i++, x+=35){

			g.drawImage(liveIcon.getImage(),x,0,null);

		}
	
        g.dispose();
		ImageIcon fullLives = new ImageIcon(combinedImage);
		pacLives.setIcon(fullLives);
		
		combinedImage = new BufferedImage( 
                120, 
                40, 
                BufferedImage.TYPE_INT_ARGB );
		g = combinedImage.createGraphics();
		
		for(int i = 1,x = 0; i < lives2P; i++, x+=35){

			g.drawImage(liveIcon2P.getImage(),x,0,null);

		}
	
        g.dispose();
		fullLives = new ImageIcon(combinedImage);
		pacLives2.setIcon(fullLives);
		
		System.out.println("Life count P1: " + lives + " and P2: " + lives2P);
	}
	
	public void fruitEating(){
	
		Rectangle pac = thePac.getTrue();
		Rectangle pac2 = thePac2.getTrue();
		Rectangle fruitCheck = new Rectangle(fruit.getX() +5+mazeOffset, fruit.getY()+5+mazeOffset,20,20);
		
		if((alive1P && pac.intersects(fruitCheck)) || (alive2P && pac2.intersects(fruitCheck))){
			
			switch(fruitUse){
			case 0:
				score+=100;
				break;
			case 1:
				score+=300;
				break;
			case 2:
				score+=500;
				break;
			case 3:
			case 4:
				score+=700;
				break;
			case 5:
				score+=1000;
				break;
			case 6:
			case 7:
				score+=2000;
				break;
			case 8:
				score+=3000;
				break;
			case 9:
			case 10:
				score+=5000;
				break;
			
			}

			
			fruitActive = false;
			fruit.setVisible(false);
			fruitP.setLocation(fruit.getX()+mazeOffset, fruit.getY()+mazeOffset);
			fruitP.setIcon(pointSystem.getFruitPoint(fruitUse));
			fruitP.setVisible(true);
			FruitP.start();
		}
	}
	
	public void animate(){
		Thread worker = new Thread(){
			public void run(){
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						ready.setVisible(true);
					}
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e){
						
				}
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						ready.setVisible(false);
					}
				});
				chaseTimer.start();
				keyTimer.start();
				liveGUI();
				ghostTimer.start();
				int extraLiveScore = 10000;
				while(playing){
					

					if(!pause){
					

						try {
							Thread.sleep(25);
						} catch (InterruptedException e){
								
						}
						//checkBox();
						if(alive1P){
							thePac.movement();
						}
						if(alive2P){
							thePac2.movement();
						}
						theRed.movement();
						thePink.movement();
						theBlue.movement();
						theOrange.movement();
						checkBox(thePac);
						checkBox(thePac2);
						checkGhostBox(theRed);
						checkEating(theRed, Red);
						checkGhostBox(thePink);
						checkEating(thePink, Pink);
						checkGhostBox(theBlue);
						checkEating(theBlue, Blue);
						checkGhostBox(theOrange);
						checkEating(theOrange, Orange);
						scoreGUI();
						
						if(mazeBig){
							mazeSizeOffset();
						}
						
						if(fruitActive){
							fruit.setLocation(270, 340 + mazeOffset);
							fruitEating();
						}
						
						if (powerActive && powerSec <= 2){
							//input.setIndex(10);
							if(!ghostWhiteTimer.isRunning()){
								ghostWhiteTimer.start();
							}
						} else if (powerActive && powerSec > 2){
							if(ghostWhiteTimer.isRunning()){
								ghostWhiteTimer.stop();
							}
						}
						if(!powerActive){
							if(ghostWhiteTimer.isRunning()){
								ghostWhiteTimer.stop();
							}
						}
						
						int eatenLocX1A = eatenLocX1;
						int eatenLocY1A = eatenLocY1;
						int eatenLocX2A = eatenLocX2;
						int eatenLocY2A = eatenLocY2;
						SwingUtilities.invokeLater(new Runnable(){
							public void run() {
								//System.out.println(a + "  " + eatenLocY1 + ": " + (eatenLocX1 != -1) + " " + (eatenLocY1 != -1) + " " + (eatenLocX1 != -1 && eatenLocY1 != -1));
								gameBoard.setLocation(5, 5 + mazeOffset);
								pacman.setLocation((int) thePac.getLocation().getX(), (int) thePac.getLocation().getY()+mazeOffset);
								pacman.setIcon(thePac.getImage());
								pacman2.setLocation((int) thePac2.getLocation().getX(), (int) thePac2.getLocation().getY()+mazeOffset);
								pacman2.setIcon(thePac2.getImage());
								Red.setLocation((int) theRed.getLocation().getX(), (int) theRed.getLocation().getY()+mazeOffset);
								Red.setIcon(theRed.getImage());
								Pink.setLocation((int) thePink.getLocation().getX(), (int) thePink.getLocation().getY()+mazeOffset);
								Pink.setIcon(thePink.getImage());
								Blue.setLocation((int) theBlue.getLocation().getX(), (int) theBlue.getLocation().getY()+mazeOffset);
								Blue.setIcon(theBlue.getImage());
								Orange.setLocation((int) theOrange.getLocation().getX(), (int) theOrange.getLocation().getY()+mazeOffset);
								Orange.setIcon(theOrange.getImage());
								
								for (int i = 0; i < dotData.size(); i++){
									dotData.get(i).movement();
									dots.get(i).setLocation((int) (dotData.get(i).returnXLocation()*20)+5, (int) (dotData.get(i).returnYLocation()*20)+5+mazeOffset);
									dots.get(i).setIcon(dotData.get(i).getDot());
								}
								if(eatenLocX1A != -1 && eatenLocY1A != -1){
									
									for (int i = 0; i < dotData.size(); i++){
										int currentLocX = dotData.get(i).returnXLocation();
										int currentLocY = dotData.get(i).returnYLocation();
										
										if(currentLocX == eatenLocX1A && currentLocY == eatenLocY1A){
											
											dotData.remove(i);
											contentPane.remove( dots.get(i) );
											dots.remove(i);
											break;
										}
									}
									
									//contentPane.remove( dots.get(a) );
									//maze[eatenLocY1A][eatenLocX1A].setIcon(mazeEaten.getMazeWall());
								}
								if(eatenLocX2A != -1 && eatenLocY2A != -1){
									maze[eatenLocY2A][eatenLocX2A].setIcon(mazeEaten.getMazeWall());
								}
								scoreBoard.setIcon(scoreResult);
							}
							
							
						});
	
						eatenLocX1 = -1;
						eatenLocY1 = -1;
						eatenLocX2 = -1;
						eatenLocY2 = -1;
						
						if(score >= extraLiveScore){
							if(alive1P){
								lives++;
							}
							if(alive2P){
								lives2P++;
							}
							extraLiveScore += 10000;
							liveGUI();
							//System.out.println("1up");
						}
						if(sec == 0 && ghostActive){
							ghostTimer.stop();
							thePink.setOutBox(true);
							ghostActive = false;
						}
						
						if(powerSec == 0 && powerActive){
							powerTimer.stop();
							
							ghostCombo = 0;
							powerActive = false;
						}
						
						if(dotNum == 0){
							//playing = false;
							
							level++;
							try {
								Thread.sleep(500);
							} catch (InterruptedException e){
									
							}
							newMaze();
						
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e){
									
							}
							SwingUtilities.invokeLater(new Runnable(){
								public void run() {
									ready.setVisible(true);
								}
							});
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e){
									
							}
							SwingUtilities.invokeLater(new Runnable(){
								public void run() {
									ready.setVisible(false);
								}
							});
							
							//playing = true;
							chaseTimer.start();
						} else {
							//thePink.setOutBox(true);
							if ((dotStart - dotNum) >= (dotStart/3)){
								//theBlue.setOutBox(true);
								if(fruitFirst){
									fruitFirst = false;
									fruitActive = true;
									fruit.setVisible(true);
									if(level > 11){
										Random rand = new Random();
										int randomNum = rand.nextInt(11);
										fruitUse = randomNum;
										
									}else {
										fruitUse = level -1;
									}
									
									fruitP.setVisible(false);
									SwingUtilities.invokeLater(new Runnable(){
										public void run() {
											fruit.setIcon(pointSystem.getFruits(fruitUse));
										}
									});
								}
								
								if((dotStart- dotNum) >= (dotStart/2)){
									//theOrange.setOutBox(true);
									if(fruitSecond){
								
										fruitSecond = false;
										fruitActive = true;
										fruit.setVisible(true);
										if(level > 11){
											Random rand = new Random();
											int randomNum = rand.nextInt(11);
											fruitUse = randomNum;
											
										}else {
											fruitUse = level -1;
										}
										
										fruitP.setVisible(false);
										SwingUtilities.invokeLater(new Runnable(){
											public void run() {
												fruit.setIcon(pointSystem.getFruits(fruitUse));
											}
										});
									}
									
								}
								
							}
						}
						
						switch(level){
						case 1:
							if((dotStart - dotNum) >= (dotStart/4)){
								thePink.setOutBox(true);
								if((dotStart - dotNum) >= (dotStart/3)){
									theBlue.setOutBox(true);
									if((dotStart - dotNum) >= (dotStart/2)){
										theOrange.setOutBox(true);
									}
								}
							}
							break;
						case 2:
						case 3:
							thePink.setOutBox(true);
							if((dotStart - dotNum) >= (dotStart/4)){
								theBlue.setOutBox(true);
								if((dotStart - dotNum) >= (dotStart/3)){
									theOrange.setOutBox(true);
								}
							}
							break;
						case 4:
							thePink.setOutBox(true);
							theBlue.setOutBox(true);
							if((dotStart - dotNum) >= (dotStart/4)){
								theOrange.setOutBox(true);
							}
							break;
						default:
							thePink.setOutBox(true);
							theBlue.setOutBox(true);
							theOrange.setOutBox(true);
								
						}
						
						if(dead){
						
							
							if(!alive1P && lives >0){
								lives--;
								if(lives >0){
									alive1P = true;
								} else {
									pacman.setVisible(false);
								}
							} 
							
							if(!alive2P && lives2P >0){
								lives2P--;
								if(lives2P >0){
									alive2P = true;
								} else {
									pacman2.setVisible(false);
								}
								
							}
							
							System.out.println("After death P1: " + lives + " and P2: " + lives2P);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e){
									
							}
							
							Red.setVisible(false);
							Pink.setVisible(false);
							Blue.setVisible(false);
							Orange.setVisible(false);
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e){
									
							}
							liveGUI();
							if((lives > 0) || (lives2P >0)) 
							{
								Red.setVisible(true);
								Pink.setVisible(true);
								Blue.setVisible(true);
								Orange.setVisible(true);
								thePac.respawn();
								thePac2.respawn();
								theRed.respawn();
								thePink.respawn();
								theBlue.respawn();
								theOrange.respawn();
								dead = false;
								powerActive = false;
								powerSec = 0;
								fruit.setVisible(false);
								ghostP.setVisible(false);
								fruitP.setVisible(false);
								fruitActive = false;
							} else{
								
								playing = false;
								pause = true;
								
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e){
										
								}
								button.setEnabled(true);
								button2.setEnabled(true);
								button3.setEnabled(true);
								introBoard.setVisible(true);
								optionA.setVisible(true);
								optionB.setVisible(true);
								optionC.setVisible(true);
								selectOption.setVisible(true);
								game = "menu";
								menu = "menuA";
								menuSetup();
							}
						}

					}
				}
			}
		};
		worker.start();
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			thePac.setDirection('r');
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			thePac.setDirection('l');
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			thePac.setDirection('d');		
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			thePac.setDirection('u');
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(thePac.getRValid()){
				thePac.setDirection('r');
				thePac.setIndex(1);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(thePac.getLValid()){
				thePac.setDirection('l');
				thePac.setIndex(3);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(thePac.getDValid()){
				thePac.setDirection('d');
				thePac.setIndex(7);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(thePac.getUValid()){
				thePac.setDirection('u');
				thePac.setIndex(5);
			}
		}*/
		getKey(e);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		removeKey(e);
		
	}

}
