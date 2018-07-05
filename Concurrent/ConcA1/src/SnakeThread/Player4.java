package SnakeThread;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rabou on 6/06/15.
 */
public class Player4 implements Runnable, KeyListener {
    public ArrayList<Point> snakeParts;
    public Point head;
    public int taillength= 2;
    public Server currentServer;
   
    Random number= new Random();
    public Buffer<String> playerFourMessages = new Buffer<String>(10);
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
    private int tempDirection = -100;
    

    public Player4(Server parentServer){
        head = new Point(10 + number.nextInt(60), 10+ number.nextInt(50));
        snakeParts = new ArrayList<Point>();
        currentServer = parentServer;
    }
    public synchronized void cycleMessages(String message){
    	
    	try {
    		if(playerFourMessages.count > 0){
    			System.out.println(playerFourMessages.get());
    		}
    		
			playerFourMessages.put(message);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
    	
    	
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();
        if(direction != -10){
	        if ((i == KeyEvent.VK_LEFT))
	        {
	            direction = LEFT;
	        }
	        if ((i == KeyEvent.VK_RIGHT))
	        {
	            direction = RIGHT;
	        }
	        if ((i == KeyEvent.VK_UP))
	        {
	            direction = UP;
	        }
	        if ((i == KeyEvent.VK_DOWN))
	        {
	            direction = DOWN;
	        }
        
        }

        
    }
    

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}

	public synchronized void updateDirection() throws InterruptedException{
		
		tempDirection = direction;
		currentServer.playerFourMoves.put(direction);				
	}
	
	
	public void run() {
		
		while (!Thread.currentThread().isInterrupted()) {
		    try {
		    	if(direction != tempDirection ){
		    		updateDirection();
		    	}
		    	
		        
		    } catch (InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		
		
		}
	}

	
    
}
