package unisa.tilemaze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by rabouvong on 16/5/17.
 */

public class Character {

    public  Texture characterTexture;
    public  TextureRegion[] characterFrame;
    public  Animation moveAnimation;
    public static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 4;
    public float stateTime;
    public TextureRegion currentFrame;
    public Sprite characterSprite;
    public Vector2 characterDelta;
    public Rectangle characterDeltaRectangle;
    public int direction = 1;

    public Character(String image){
        characterTexture = new Texture(image);
        TextureRegion[][] tmp = TextureRegion.split(characterTexture, characterTexture.getWidth() / FRAME_COLS, characterTexture.getHeight() / FRAME_ROWS);
        characterFrame = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) { characterFrame[index++] = tmp[i][j];
            }
        }
        moveAnimation = new Animation(1f, characterFrame);
        stateTime = 0f;
        currentFrame = moveAnimation.getKeyFrame(10, true);

        characterSprite = new Sprite(currentFrame);
        characterSprite.setSize(30, 30);
        characterDelta = new Vector2();
        characterDeltaRectangle = new Rectangle(0, 0, characterSprite.getWidth(), characterSprite.getHeight());


    }
    public Texture getCharacterTexture(){
        return characterTexture;
    }
    public Animation getMoveAnimation(){
        return moveAnimation;
    }
    public float getStateTime(){
        return stateTime;
    }
    public TextureRegion getCurrentFrame(){
        return currentFrame;
    }
    public Vector2 getCharacterDelta(){
        return characterDelta;
    }
    public Sprite getCharacterSprite(){
        return characterSprite;
    }
    public void setCurrentFrame(TextureRegion newFrame){
        currentFrame = newFrame;
    }
    public void setCharacterSprite(TextureRegion newFrame){
        characterSprite.setRegion(newFrame);
    }
    public void setCharacterDelta(float x, float y){
        characterDelta.x = x;
        characterDelta.y = y;
    }
    public void setCharacterDeltaRectangle(float x, float y){
        characterDeltaRectangle.x = x;
        characterDeltaRectangle.y = y;
    }
    public void setCharacterDeltaX(float x){
        characterDelta.x = x;
    }
    public void setCharacterDeltaY(float y){
        characterDelta.y = y;
    }
    public void setCharacterSpriteCamera(float x, float y){
        characterSprite.translate(x, y);
    }
    public void characterDrawSpriteBatch(SpriteBatch sprite){
        characterSprite.draw(sprite);
    }




}
