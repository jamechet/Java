package SnakeThread;

/**
 * Created by Daniel Stone on 4/06/15.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jaryt Bustard
 */
public class Server implements ActionListener, KeyListener, Runnable
{
	public static Server server;
	public JFrame mainWindow;

	public RenderPanel renderPanel;
	

	//movement speed
	public Timer timer = new Timer(100, this);
	//store all players
	public ArrayList<Player1> playerOneBuffer = new ArrayList<Player1>();
	public ArrayList<Player2> playerTwoBuffer = new ArrayList<Player2>();
	public ArrayList<Player3> playerThreeBuffer = new ArrayList<Player3>();
	public ArrayList<Player4> playerFourBuffer = new ArrayList<Player4>();
	public ArrayList<PlayerAI> playerAIBuffer = new ArrayList<PlayerAI>();

	public Buffer<Integer> playerOneMoves = new Buffer<Integer>(10);
	public Buffer<Integer> playerTwoMoves = new Buffer<Integer>(10);
	public Buffer<Integer> playerThreeMoves = new Buffer<Integer>(10);
	public Buffer<Integer> playerFourMoves = new Buffer<Integer>(10);
	public Buffer<Integer> playerAIMoves = new Buffer<Integer>(10);
	
	public Player1 loggedPlayer1;
	public Player2 loggedPlayer2;
	public Player3 loggedPlayer3;
	public Player4 loggedPlayer4;
	
	
	
	
	//Keyboard
	//Player01
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	//Player02
	public static final int WUP = 0, SDOWN = 1, ALEFT = 2, DRIGHT = 3;
	//Player03
	public static final int TUP =0,  GDOWN = 1, FLEFT=2, HRIGHT =3;
	//Player04
	public static final int IUP=0, KDOWN=1,JLEFT=2, LRIGHT=3;

	public int ticks = 0, direction = DOWN,direction1= SDOWN,direction2 = GDOWN,
			direction3=KDOWN,directionAI = DOWN, score, time;

	public Point cherry;

	public Random random;

	public boolean over = false, paused;

	public Dimension dim;

	public boolean aiAdded = false; 
	
	private int numberOfLoggedInPlayers = -1;
	
	public ExecutorService threadPool = Executors.newFixedThreadPool(4);

	// Daniel Attempt at constructor
	public Server(int loggedPlayers){
		numberOfLoggedInPlayers = loggedPlayers;
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindow = new JFrame("Snake");
		mainWindow.setVisible(true);
		mainWindow.setSize(805, 700);
		mainWindow.setResizable(false);
		mainWindow.setLocation(dim.width / 2 - mainWindow.getWidth() / 2, dim.height / 2 - mainWindow.getHeight() / 2);
		mainWindow.add(renderPanel = new RenderPanel());
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.addKeyListener(this);

		startGame();
	}

	public void addPlayer1(Player1 player1){

		playerOneBuffer.add(player1);
		
	}
	public void addPlayer2(Player2 player2){

		playerTwoBuffer.add(player2);
		
	}
	public void addPlayer3(Player3 player3){

		playerThreeBuffer.add(player3);
		
	}
	public void addPlayer4(Player4 player4){

		playerFourBuffer.add(player4);
		
	}
	public void addAIPlayers(){

		for(int i = 0; i <100;i++){
			PlayerAI aiPlayer = new PlayerAI(this);

			playerAIBuffer.add(aiPlayer);
		}

	}
	public void startGame()
	{
		over = false;
		paused = false;
		time = 0;
		score = 0;
		ticks = 0;
		random = new Random();
		cherry = new Point(random.nextInt(79), random.nextInt(66));
		timer.start();

	}
	public void player4(int tick){
		if (ticks % 2 == 0 && playerFourBuffer.get(0).head != null && !over && !paused)
		{
			time++;
			
			playerFourBuffer.get(0).snakeParts.add(new Point(playerFourBuffer.get(0).head.x,  playerFourBuffer.get(0).head.y));

			if (direction3 == IUP)
			{
				if ( playerFourBuffer.get(0).head.y - 1 >= 0)
				{
					loggedPlayer4.cycleMessages("Player 4 is Moving Up");
					playerFourBuffer.get(0).head = new Point( playerFourBuffer.get(0).head.x,  playerFourBuffer.get(0).head.y - 1);
				}
				else
				{
					over = true;

				}
			}

			if (direction3 == KDOWN)
			{
				if (playerFourBuffer.get(0).head.y + 1 < 67)
				{
					loggedPlayer4.cycleMessages("Player 4 is Moving Down");
					playerFourBuffer.get(0).head = new Point(playerFourBuffer.get(0).head.x, playerFourBuffer.get(0).head.y + 1);
				}
				else
				{
					over = true;
				}
			}

			if (direction3 == JLEFT)
			{
				if (playerFourBuffer.get(0).head.x - 1 >= 0)
				{
					loggedPlayer4.cycleMessages("Player 4 is Moving Left");
					playerFourBuffer.get(0).head = new Point(playerFourBuffer.get(0).head.x - 1, playerFourBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction3 == LRIGHT)
			{
				if (playerFourBuffer.get(0).head.x + 1 < 80)
				{
					loggedPlayer4.cycleMessages("Player 4 is Moving Right");
					playerFourBuffer.get(0).head = new Point( playerFourBuffer.get(0).head.x + 1,  playerFourBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (playerFourBuffer.get(0).snakeParts.size() >playerFourBuffer.get(0).taillength )
			{
				
				playerFourBuffer.get(0).snakeParts.remove(0);

			}

			if (cherry != null)
			{
				if (playerFourBuffer.get(0).head.equals(cherry))
				{
					score += 10;
					playerFourBuffer.get(0).taillength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}

		}

	}
	public void player3(int tick){
		if (ticks % 2 == 0 && playerThreeBuffer.get(0).head != null && !over && !paused)
		{
			time++;

			playerThreeBuffer.get(0).snakeParts.add(new Point(playerThreeBuffer.get(0).head.x,  playerThreeBuffer.get(0).head.y));

			if (direction2 == TUP)
			{
				if ( playerThreeBuffer.get(0).head.y - 1 >= 0)
				{
					loggedPlayer3.cycleMessages("Player 3 is Moving Up");
					playerThreeBuffer.get(0).head = new Point( playerThreeBuffer.get(0).head.x,  playerThreeBuffer.get(0).head.y - 1);
				}
				else
				{
					over = true;

				}
			}

			if (direction2 == GDOWN)
			{
				if (playerThreeBuffer.get(0).head.y + 1 < 67)
				{
					loggedPlayer3.cycleMessages("Player 3 is Moving Down");
					playerThreeBuffer.get(0).head = new Point(playerThreeBuffer.get(0).head.x, playerThreeBuffer.get(0).head.y + 1);
				}
				else
				{
					over = true;
				}
			}

			if (direction2 == FLEFT)
			{
				if (playerThreeBuffer.get(0).head.x - 1 >= 0)
				{
					loggedPlayer3.cycleMessages("Player 3 is Moving Left");
					playerThreeBuffer.get(0).head = new Point(playerThreeBuffer.get(0).head.x - 1, playerThreeBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction2 == HRIGHT)
			{
				if (playerThreeBuffer.get(0).head.x + 1 < 80)
				{
					loggedPlayer3.cycleMessages("Player 3 is Moving Right");
					playerThreeBuffer.get(0).head = new Point( playerThreeBuffer.get(0).head.x + 1,  playerThreeBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (playerThreeBuffer.get(0).snakeParts.size() >playerThreeBuffer.get(0).taillength )
			{
				playerThreeBuffer.get(0).snakeParts.remove(0);

			}

			if (cherry != null)
			{
				if (playerThreeBuffer.get(0).head.equals(cherry))
				{
					score += 10;
					playerThreeBuffer.get(0).taillength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}

		}

	}
	public void player2(int tick){
		if (ticks % 2 == 0 && playerTwoBuffer.get(0).head != null && !over && !paused)
		{
			time++;

			playerTwoBuffer.get(0).snakeParts.add(new Point(playerTwoBuffer.get(0).head.x,  playerTwoBuffer.get(0).head.y));

			if (direction1 == WUP)
			{
				if ( playerTwoBuffer.get(0).head.y - 1 >= 0)	
				{
					loggedPlayer2.cycleMessages("Player 2 is Moving Up");
					playerTwoBuffer.get(0).head = new Point( playerTwoBuffer.get(0).head.x,  playerTwoBuffer.get(0).head.y - 1);
				}
				else
				{
					over = true;

				}
			}

			if (direction1 == SDOWN)
			{
				if (playerTwoBuffer.get(0).head.y + 1 < 67)
					
				{
					loggedPlayer2.cycleMessages("Player 2 is Moving Down");
					playerTwoBuffer.get(0).head = new Point(playerTwoBuffer.get(0).head.x, playerTwoBuffer.get(0).head.y + 1);
				}
				else
				{
					over = true;
				}
			}

			if (direction1 == ALEFT)
			{
				if (playerTwoBuffer.get(0).head.x - 1 >= 0)
				{
					loggedPlayer2.cycleMessages("Player 2 is Moving Left");
					playerTwoBuffer.get(0).head = new Point(playerTwoBuffer.get(0).head.x - 1, playerTwoBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction1 == DRIGHT)
			{
				if (playerTwoBuffer.get(0).head.x + 1 < 80)
				{
					loggedPlayer2.cycleMessages("Player 2 is Moving Right");
					playerTwoBuffer.get(0).head = new Point( playerTwoBuffer.get(0).head.x + 1,  playerTwoBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (playerTwoBuffer.get(0).snakeParts.size() >playerTwoBuffer.get(0).taillength )
			{
				playerTwoBuffer.get(0).snakeParts.remove(0);

			}

			if (cherry != null)
			{
				if (playerTwoBuffer.get(0).head.equals(cherry))
				{
					score += 10;
					playerTwoBuffer.get(0).taillength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}

		}

	}
	public void playerAI(int tick){

		
		for(int index = 0; index < playerAIBuffer.size(); index++){

			directionAI = this.randomWithRange(0, 4);
			if (ticks % 2 == 0 && playerAIBuffer.get(index).head != null && !over && !paused)
			{
				time++;
				
				playerAIBuffer.get(index).snakeParts.add(new Point(playerAIBuffer.get(index).head.x,  playerAIBuffer.get(index).head.y));

				if (directionAI == UP)
				{
					if ( playerAIBuffer.get(index).head.y - 1 >= 0)
					{
						playerAIBuffer.get(index).head = new Point( playerAIBuffer.get(index).head.x,  playerAIBuffer.get(index).head.y - 1);
					}

				}

				if (directionAI == DOWN)
				{
					if (playerAIBuffer.get(index).head.y + 1 < 67)
					{
						playerAIBuffer.get(index).head = new Point(playerAIBuffer.get(index).head.x, playerAIBuffer.get(index).head.y + 1);
					}

				}

				if (directionAI == LEFT)
				{
					if (playerAIBuffer.get(index).head.x - 1 >= 0)
					{
						playerAIBuffer.get(index).head = new Point(playerAIBuffer.get(index).head.x - 1, playerAIBuffer.get(index).head.y);
					}

				}

				if (directionAI == RIGHT)
				{
					if (playerAIBuffer.get(index).head.x + 1 < 80)
					{
						playerAIBuffer.get(index).head = new Point( playerAIBuffer.get(index).head.x + 1,  playerAIBuffer.get(index).head.y);
					}

				}

				if (playerAIBuffer.get(index).snakeParts.size() >playerAIBuffer.get(index).taillength )
				{
					playerAIBuffer.get(index).snakeParts.remove(0);

				}
				if (cherry != null)
				{
					if (playerAIBuffer.get(index).head.equals(cherry))
					{
						score += 10;
						playerAIBuffer.get(index).taillength++;
						cherry.setLocation(random.nextInt(79), random.nextInt(66));
					}
				}
			}
			

		}

	}
	public void player1(int tick){

		
		if (ticks % 2 == 0 && playerOneBuffer.get(0).head != null && !over && !paused)
		{
			time++;

			playerOneBuffer.get(0).snakeParts.add(new Point(playerOneBuffer.get(0).head.x,  playerOneBuffer.get(0).head.y));

			if (direction == UP)
			{
				if ( playerOneBuffer.get(0).head.y - 1 >= 0)
				{
					loggedPlayer1.cycleMessages("Player 1 is Moving Up");
					playerOneBuffer.get(0).head = new Point( playerOneBuffer.get(0).head.x,  playerOneBuffer.get(0).head.y - 1);
				}
				else
				{
					over = true;

				}
			}

			if (direction == DOWN)
			{
				if (playerOneBuffer.get(0).head.y + 1 < 67)
				{
					loggedPlayer1.cycleMessages("Player 1 is Moving Down");
					playerOneBuffer.get(0).head = new Point(playerOneBuffer.get(0).head.x, playerOneBuffer.get(0).head.y + 1);
				}
				else
				{
					over = true;
				}
			}

			if (direction == LEFT)
			{
				if (playerOneBuffer.get(0).head.x - 1 >= 0 )
				{
					loggedPlayer1.cycleMessages("Player 1 is Moving Left");
					playerOneBuffer.get(0).head = new Point(playerOneBuffer.get(0).head.x - 1, playerOneBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction == RIGHT)
			{
				if (playerOneBuffer.get(0).head.x + 1 < 80)
				{
					loggedPlayer1.cycleMessages("Player 1 is Moving Right");
					playerOneBuffer.get(0).head = new Point( playerOneBuffer.get(0).head.x + 1,  playerOneBuffer.get(0).head.y);
				}
				else
				{
					over = true;
				}
			}

			if (playerOneBuffer.get(0).snakeParts.size() >playerOneBuffer.get(0).taillength )
			{
				playerOneBuffer.get(0).snakeParts.remove(0);

			}

			if (cherry != null)
			{
				if (playerOneBuffer.get(0).head.equals(cherry))
				{
					score += 10;
					playerOneBuffer.get(0).taillength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}

		}

	}


	/**
	 * Select the players from the buffer and assign to the gameboard using different key listener
	 * @param arg0
	 */
	 @Override
	 public void actionPerformed(ActionEvent arg0)
	{
		renderPanel.repaint();
		ticks++;
		if(this.numberOfLoggedInPlayers == 1){
			player1(ticks);
			
		}
		if(this.numberOfLoggedInPlayers == 2){
			player1(ticks);
			player2(ticks);
		}
		if(this.numberOfLoggedInPlayers == 3){
			player1(ticks);
			player2(ticks);
			player3(ticks);
		}
		if(this.numberOfLoggedInPlayers == 4){
			player1(ticks);
			player2(ticks);
			player3(ticks);
			player4(ticks);
		}

		playerAI(ticks);



	}

	/* Listen the keyboard pressed and assign the value to keyplayer
	 */
	 @Override
	 public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_SPACE)
		{
			if (over)
			{
				startGame();
			}
			else
			{
				paused = !paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}



	public void run() {
		this.delay();
		if(this.numberOfLoggedInPlayers < 1){
			System.out.println("Error: No players logged in");
		}
		if(this.numberOfLoggedInPlayers == 1){
			loggedPlayer1 =new Player1(this);
			this.addPlayer1(loggedPlayer1);
			mainWindow.addKeyListener(loggedPlayer1);
			Thread p1 = new Thread(loggedPlayer1);
			
			threadPool.execute(p1);
		}
		
		if(this.numberOfLoggedInPlayers == 2){
			loggedPlayer1 =new Player1(this);
			this.addPlayer1(loggedPlayer1);
			mainWindow.addKeyListener(loggedPlayer1);
			Thread p1 = new Thread(loggedPlayer1);
			threadPool.execute(p1);
			
			loggedPlayer2 =new Player2(this);
			this.addPlayer2(loggedPlayer2);
			mainWindow.addKeyListener(loggedPlayer2);
			Thread p2 = new Thread(loggedPlayer2);
			threadPool.execute(p2);
		}
		
		if(this.numberOfLoggedInPlayers == 3){
			loggedPlayer1 =new Player1(this);
			this.addPlayer1(loggedPlayer1);
			mainWindow.addKeyListener(loggedPlayer1);
			Thread p1 = new Thread(loggedPlayer1);
			threadPool.execute(p1);
			
			loggedPlayer2 =new Player2(this);
			this.addPlayer2(loggedPlayer2);
			mainWindow.addKeyListener(loggedPlayer2);
			Thread p2 = new Thread(loggedPlayer2);
			threadPool.execute(p2);
			
			loggedPlayer3 =new Player3(this);
			this.addPlayer3(loggedPlayer3);
			mainWindow.addKeyListener(loggedPlayer3);
			Thread p3 = new Thread(loggedPlayer3);
			threadPool.execute(p3);
		}

		if(this.numberOfLoggedInPlayers == 4){
			loggedPlayer1 =new Player1(this);
			this.addPlayer1(loggedPlayer1);
			mainWindow.addKeyListener(loggedPlayer1);
			Thread p1 = new Thread(loggedPlayer1);
			threadPool.execute(p1);
			
			loggedPlayer2 =new Player2(this);
			this.addPlayer2(loggedPlayer2);
			mainWindow.addKeyListener(loggedPlayer2);
			Thread p2 = new Thread(loggedPlayer2);
			threadPool.execute(p2);
			
			loggedPlayer3 =new Player3(this);
			this.addPlayer3(loggedPlayer3);
			mainWindow.addKeyListener(loggedPlayer3);
			Thread p3 = new Thread(loggedPlayer3);
			threadPool.execute(p3);
			
			loggedPlayer4 =new Player4(this);
			this.addPlayer4(loggedPlayer4);
			mainWindow.addKeyListener(loggedPlayer4);
			Thread p4 = new Thread(loggedPlayer4);
			threadPool.execute(p4);
		}
	

		this.addAIPlayers();

		while (!Thread.currentThread().isInterrupted()) {
			try {

				if(playerOneMoves.count > 0){
					this.direction = playerOneMoves.get();
				}
				if(playerTwoMoves.count > 0){
					this.direction1 = playerTwoMoves.get();
				}
				if(playerThreeMoves.count > 0){
					this.direction2 = playerThreeMoves.get();
				}
				if(playerFourMoves.count > 0){
					this.direction3 = playerFourMoves.get();
				}

			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();		    
			}        	
		}     

	}

	private void delay() {

		int actualDelay;

		try {
			// thread to sleep for random milliseconds
			actualDelay = randomWithRange(0,100);
					//System.out.println("Actual delay is "+actualDelay);
					Thread.sleep(actualDelay);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private int randomWithRange(int min, int max)
	{
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}
}