package SnakeMultiplePlayer;

/**
 * Created by Rabou on 2/06/15.
 */

import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author Jaryt Bustard
 */
public class Snake implements ActionListener, KeyListener
{
    public static Snake snake;
    public JFrame jframe;

    public RenderPanel renderPanel;
    //color for each snake
    public ArrayList<Color>color = new ArrayList<Color>();
    //movement speed
    public Timer timer = new Timer(200, this);
    //store all players
    public ArrayList<Player> buffer = new ArrayList<Player>();
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public ArrayList<Point> snakeParts1 = new ArrayList<Point>();
    //Keyboard
    //Player01
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    //Player02
    public static final int WUP = 0, SDOWN = 1, ALEFT = 2, DRIGHT = 3;
    //Player03
    public static final int TUP =0,  GDOWN = 1, FLEFT=2, HRIGHT =3;
    //Player04
    public static final int IUP=0, KDOWN=1,JLEFT=2, LRIGHT=4;

    public int ticks = 0, direction = DOWN,direction1= SDOWN,direction2 = GDOWN,
            direction3=KDOWN, score, tailLength = 10, time, tailLength1 = 10;

    public Point head, cherry, head1;

    public Random random;

    public boolean over = false, paused;

    public Dimension dim;


    public void rungame()
    {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setResizable(false);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new SnakeMultiplePlayer.RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }
    public void addPlayer(Player player){
        color.add(new Color((int) (Math.random() * 0x1000000)));
        buffer.add(player);
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
    public void player4(int tick, int index){
        if (ticks % 2 == 0 && buffer.get(index).head != null && !over && !paused)
        {
            time++;

            buffer.get(index).snakeParts.add(new Point(buffer.get(index).head.x,  buffer.get(index).head.y));

            if (direction3 == IUP)
            {
                if ( buffer.get(index).head.y - 1 >= 0 && noTailAt(index,buffer.get(index).head.x,  buffer.get(index).head.y - 1))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x,  buffer.get(index).head.y - 1);
                }
                else
                {
                    over = true;

                }
            }

            if (direction3 == KDOWN)
            {
                if (buffer.get(index).head.y + 1 < 67 && noTailAt(index,buffer.get(index).head.x, buffer.get(index).head.y + 1))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x, buffer.get(index).head.y + 1);
                }
                else
                {
                    over = true;
                }
            }

            if (direction3 == JLEFT)
            {
                if (buffer.get(index).head.x - 1 >= 0 && noTailAt(index,buffer.get(index).head.x - 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x - 1, buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (direction3 == LRIGHT)
            {
                if (buffer.get(index).head.x + 1 < 80 && noTailAt(index,buffer.get(index).head.x + 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x + 1,  buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (buffer.get(index).snakeParts.size() >buffer.get(index).taillength )
            {
                buffer.get(index).snakeParts.remove(0);

            }

            if (cherry != null)
            {
                if (buffer.get(index).head.equals(cherry))
                {
                    score += 10;
                    buffer.get(index).taillength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }

        }

    }
    public void player3(int tick, int index){
        if (ticks % 2 == 0 && buffer.get(index).head != null && !over && !paused)
        {
            time++;

            buffer.get(index).snakeParts.add(new Point(buffer.get(index).head.x,  buffer.get(index).head.y));

            if (direction2 == TUP)
            {
                if ( buffer.get(index).head.y - 1 >= 0 && noTailAt(index,buffer.get(index).head.x,  buffer.get(index).head.y - 1))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x,  buffer.get(index).head.y - 1);
                }
                else
                {
                    over = true;

                }
            }

            if (direction2 == GDOWN)
            {
                if (buffer.get(index).head.y + 1 < 67 && noTailAt(index,buffer.get(index).head.x, buffer.get(index).head.y + 1))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x, buffer.get(index).head.y + 1);
                }
                else
                {
                    over = true;
                }
            }

            if (direction2 == FLEFT)
            {
                if (buffer.get(index).head.x - 1 >= 0 && noTailAt(index,buffer.get(index).head.x - 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x - 1, buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (direction2 == HRIGHT)
            {
                if (buffer.get(index).head.x + 1 < 80 && noTailAt(index,buffer.get(index).head.x + 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x + 1,  buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (buffer.get(index).snakeParts.size() >buffer.get(index).taillength )
            {
                buffer.get(index).snakeParts.remove(0);

            }

            if (cherry != null)
            {
                if (buffer.get(index).head.equals(cherry))
                {
                    score += 10;
                    buffer.get(index).taillength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }

        }

    }
    public void player2(int tick, int index){
        if (ticks % 2 == 0 && buffer.get(index).head != null && !over && !paused)
        {
            time++;

            buffer.get(index).snakeParts.add(new Point(buffer.get(index).head.x,  buffer.get(index).head.y));

            if (direction1 == WUP)
            {
                if ( buffer.get(index).head.y - 1 >= 0 && noTailAt(index,buffer.get(index).head.x,  buffer.get(index).head.y - 1))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x,  buffer.get(index).head.y - 1);
                }
                else
                {
                    over = true;

                }
            }

            if (direction1 == SDOWN)
            {
                if (buffer.get(index).head.y + 1 < 67 && noTailAt(index,buffer.get(index).head.x, buffer.get(index).head.y + 1))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x, buffer.get(index).head.y + 1);
                }
                else
                {
                    over = true;
                }
            }

            if (direction1 == ALEFT)
            {
                if (buffer.get(index).head.x - 1 >= 0 && noTailAt(index,buffer.get(index).head.x - 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x - 1, buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (direction1 == DRIGHT)
            {
                if (buffer.get(index).head.x + 1 < 80 && noTailAt(index,buffer.get(index).head.x + 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x + 1,  buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (buffer.get(index).snakeParts.size() >buffer.get(index).taillength )
            {
                buffer.get(index).snakeParts.remove(0);

            }

            if (cherry != null)
            {
                if (buffer.get(index).head.equals(cherry))
                {
                    score += 10;
                    buffer.get(index).taillength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }

        }

    }

    public void player1(int tick, int index){


        if (ticks % 2 == 0 && buffer.get(index).head != null && !over && !paused)
        {
            time++;

            buffer.get(index).snakeParts.add(new Point(buffer.get(index).head.x,  buffer.get(index).head.y));

            if (direction == UP)
            {
                if ( buffer.get(index).head.y - 1 >= 0 && noTailAt(index, buffer.get(index).head.x,  buffer.get(index).head.y - 1))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x,  buffer.get(index).head.y - 1);
                }
                else
                {
                    over = true;

                }
            }

            if (direction == DOWN)
            {
                if (buffer.get(index).head.y + 1 < 67 && noTailAt(index, buffer.get(index).head.x, buffer.get(index).head.y + 1))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x, buffer.get(index).head.y + 1);
                }
                else
                {
                    over = true;
                }
            }

            if (direction == LEFT)
            {
                if (buffer.get(index).head.x - 1 >= 0 && noTailAt(index, buffer.get(index).head.x - 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point(buffer.get(index).head.x - 1, buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (direction == RIGHT)
            {
                if (buffer.get(index).head.x + 1 < 80 && noTailAt(index, buffer.get(index).head.x + 1, buffer.get(index).head.y))
                {
                    buffer.get(index).head = new Point( buffer.get(index).head.x + 1,  buffer.get(index).head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (buffer.get(index).snakeParts.size() >buffer.get(index).taillength )
            {
                buffer.get(index).snakeParts.remove(0);

            }

            if (cherry != null)
            {
                if (buffer.get(index).head.equals(cherry))
                {
                    score += 10;
                    buffer.get(index).taillength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        renderPanel.repaint();
        ticks++;
        if(buffer.size()>0)
            player1(ticks, 0);
        if(buffer.size()>1)
            player2(ticks, 1);
        if(buffer.size()>2)
            player3(ticks, 2);
        if(buffer.size()>3)
            player4(ticks, 3);

    }
    public boolean noTailAt(int index, int x, int y)
    {
        for (Point point : buffer.get(index).snakeParts)
        {
            if (point.equals(new Point(x, y)))
            {
                // to get snake revers hit it own body and die
                return true;
            }
        }
        return true;
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();
        keyPlayer01(i);
        keyPlayer02(i);
        keyPlayer03(i);
        keyPlayer04(i);

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
    public void keyPlayer01(int i){

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
    public void keyPlayer02(int i){

        if(i==KeyEvent.VK_A){
            direction1 = ALEFT;
        }
        if(i==KeyEvent.VK_D){
            direction1 = DRIGHT;
        }
        if(i==KeyEvent.VK_W ){
            direction1 = WUP;
        }
        if(i==KeyEvent.VK_S){
            direction1 = SDOWN;
        }
    }
    public void keyPlayer03(int i){
        if(i==KeyEvent.VK_F){
            direction2 = FLEFT;
        }
        if(i==KeyEvent.VK_H){
            direction2 = HRIGHT;
        }
        if(i==KeyEvent.VK_T ){
            direction2 = TUP;
        }
        if(i==KeyEvent.VK_G){
            direction2 = GDOWN;
        }
    }
    public void keyPlayer04(int i) {
        if (i == KeyEvent.VK_J) {
            direction3 = JLEFT;
        }
        if (i == KeyEvent.VK_L) {
            direction3 = LRIGHT;
        }
        if (i == KeyEvent.VK_I) {
            direction3 = IUP;
        }
        if (i == KeyEvent.VK_K) {
            direction3 = KDOWN;
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
    public static void main(String[] args)
    {
        Player player = new Player();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
         snake = new Snake();
        snake.addPlayer(player);
        snake.addPlayer(player1);
        snake.addPlayer(player2);
        snake.addPlayer(player3);
        snake.rungame();
    }

}