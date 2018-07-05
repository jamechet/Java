package SnakeThread;



import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * Created by Rabou on 2/06/15.
 */
@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
    public static final Color GREEN = new Color(1666073);
    Random r = new Random();


    // Create background color for the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

     Server server = Server.server;

        g.setColor(Color.white);

        g.fillRect(0, 0, 800, 700);
        
        
        // paint all the snakes  to the graphic with different colours
        if (server.playerOneBuffer.size() > 0) {
             g.setColor(Color.BLUE);
             // paint snake body to the graphic
             for (Point point : server.playerOneBuffer.get(0).snakeParts) {
                 g.fillRect(point.x * Server.SCALE, point.y * Server.SCALE, Server.SCALE, Server.SCALE);
             }
             // paint snake head to the screen
             g.fillRect(server.playerOneBuffer.get(0).head.x * Server.SCALE, server.playerOneBuffer.get(0).head.y * Server.SCALE, Server.SCALE, Server.SCALE);
         }

        // paint all the snakes  to the graphic with different colours
        if (server.playerTwoBuffer.size() > 0) {
             g.setColor(Color.BLUE);
             // paint snake body to the graphic
             for (Point point : server.playerTwoBuffer.get(0).snakeParts) {
                 g.fillRect(point.x * Server.SCALE, point.y * Server.SCALE, Server.SCALE, Server.SCALE);
             }
             // paint snake head to the screen
             g.fillRect(server.playerTwoBuffer.get(0).head.x * Server.SCALE, server.playerTwoBuffer.get(0).head.y * Server.SCALE, Server.SCALE, Server.SCALE);
         }
        
        
       // paint all the snakes  to the graphic with different colours
       if (server.playerThreeBuffer.size() > 0) {
            g.setColor(Color.BLUE);
            // paint snake body to the graphic
            for (Point point : server.playerThreeBuffer.get(0).snakeParts) {
                g.fillRect(point.x * Server.SCALE, point.y * Server.SCALE, Server.SCALE, Server.SCALE);
            }
            // paint snake head to the screen
            g.fillRect(server.playerThreeBuffer.get(0).head.x * Server.SCALE, server.playerThreeBuffer.get(0).head.y * Server.SCALE, Server.SCALE, Server.SCALE);
        }
        
        
       // paint all the snakes  to the graphic with different colours
       if (server.playerFourBuffer.size() > 0) {
            g.setColor(Color.BLUE);
            // paint snake body to the graphic
            for (Point point : server.playerFourBuffer.get(0).snakeParts) {
                g.fillRect(point.x * Server.SCALE, point.y * Server.SCALE, Server.SCALE, Server.SCALE);
            }
            // paint snake head to the screen
            g.fillRect(server.playerFourBuffer.get(0).head.x * Server.SCALE, server.playerFourBuffer.get(0).head.y * Server.SCALE, Server.SCALE, Server.SCALE);
        }
    
        
        
        for(int index = 0; index<server.playerAIBuffer.size(); index++) {
            g.setColor(Color.GREEN);
            // paint snake body to the graphic
            for (Point point : server.playerAIBuffer.get(index).snakeParts) {
                g.fillRect(point.x * Server.SCALE, point.y * Server.SCALE, Server.SCALE, Server.SCALE);
            }
            // paint snake head to the screen
            g.fillRect(server.playerAIBuffer.get(index).head.x * Server.SCALE, server.playerAIBuffer.get(index).head.y * Server.SCALE, Server.SCALE, Server.SCALE);
        }

        //paint food to the screen
        g.setColor(Color.RED);
        g.fillRect(server.cherry.x * Server.SCALE, server.cherry.y * Server.SCALE, Server.SCALE, Server.SCALE);


    }
}
