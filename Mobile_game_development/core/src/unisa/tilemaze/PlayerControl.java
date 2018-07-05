package unisa.tilemaze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by rabouvong on 1/5/17.
 */

public class PlayerControl {
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 4;
    float stateTime;
    Sprite playerSprite;
    Vector2 playerDelta;
    Rectangle playerDeltaRectangle;
    TextureRegion[] playerFrame;
    TextureRegion currentFrame;
    Animation moveAnimation;


    private Texture playerTexture;

    public PlayerControl(){
        //Textures
        playerTexture = new Texture("123.png");
        TextureRegion[][] tmp = TextureRegion.split(playerTexture, playerTexture.getWidth() / FRAME_COLS, playerTexture.getHeight() / FRAME_ROWS);
        playerFrame = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) { playerFrame[index++] = tmp[i][j];
            }
        }
        moveAnimation = new Animation(1f, playerFrame);
        stateTime = 0f;
        currentFrame = moveAnimation.getKeyFrame(10, true);

        //Player
        playerSprite = new Sprite(currentFrame);
        playerSprite.setSize(35, 35);
        playerDelta = new Vector2();
        playerDeltaRectangle = new Rectangle(0, 0, playerSprite.getWidth(), playerSprite.getHeight());


    }
    public float getStateTime(){
        return stateTime;
    }
    public Sprite getPlayerSprite(){
        return playerSprite;
    }
    public Texture getPlayerTexture(){
        return playerTexture;
    }
    public Rectangle getPlayerDeltaRectangle(){
        return playerDeltaRectangle;
    }
    public Vector2 getPlayerDelta(){
        return playerDelta;
    }
}
