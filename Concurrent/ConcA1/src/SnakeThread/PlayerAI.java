package SnakeThread;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rabou on 6/06/15.
 */
public class PlayerAI implements Runnable, KeyListener {
    public ArrayList<Point> snakeParts;
    public Point head;
    public int taillength= 2;
    public Server currentServer;
   
    Random number= new Random();
    
 	//Player01
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    

    public int ticks = 0,directionAI = DOWN, score, time;
    
    

    public PlayerAI(Server parentServer){
        head = new Point(10 + number.nextInt(60), 10+ number.nextInt(50));
        snakeParts = new ArrayList<Point>();
        currentServer = parentServer;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {   
    }
    

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}

	@Override
	public void run() {
	}
    
	
    
}
