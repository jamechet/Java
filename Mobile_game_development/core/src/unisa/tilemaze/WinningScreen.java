package unisa.tilemaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by rabouvong on 12/6/17.
 */

public class WinningScreen implements Screen {
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Sound clickS;
    private SpriteBatch scoreBatch;
    private BitmapFont lifeFont;
    private BitmapFont scoreFont;
    private BitmapFont timeFont;
    private BitmapFont missionComplete;
    Integer worldTimer;
    Integer scorePoint;
    Integer lifePoint;


    GameCore game; // Note it's "MyGdxGame" not "Game"
    public static Texture backgroundTexture;


    public WinningScreen(GameCore game) {
        this.game = game;
        clickS = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));

    }

    public void create() {

        Gdx.app.log("MenuScreen: ", "menuScreen create");
        scoreBatch = new SpriteBatch();
        // Score timer title
        scoreBatch = new SpriteBatch();
        timeFont = new BitmapFont();
        lifeFont = new BitmapFont();
        scoreFont = new BitmapFont();
        missionComplete = new BitmapFont();

        timeFont.setColor(Color.GREEN);
        lifeFont.setColor(Color.GREEN);
        scoreFont.setColor(Color.GREEN);
        missionComplete.setColor(Color.GREEN);

        worldTimer = game.gameScreen.worldTimer;
        scorePoint = game.gameScreen.scorePoint;
        lifePoint = game.gameScreen.lifePoint;

        timeFont.scale(1);
        lifeFont.scale(1);
        scoreFont.scale(1);
        missionComplete.scale(2);

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("uidata/uiskin.json"));
        stage = new Stage();
        final TextButton buttonNextLevel = new TextButton("Next Level", skin, "default");
        final TextButton buttonMainmenu = new TextButton("Main Menu", skin, "default");
        backgroundTexture = new Texture("Menu-background.jpg");


        buttonNextLevel.setWidth(100f);
        buttonNextLevel.setHeight(50f);
        buttonMainmenu.setWidth(100f);
        buttonMainmenu.setHeight(50f);

        buttonNextLevel.setPosition(Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 - 50f);//
        buttonMainmenu.setPosition(Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 - 150f);
        buttonNextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickS.play();
                buttonNextLevel.setText("You clicked the button");
                Gdx.app.log("MenuScreen: ", "About to call gameScreen");

                //             game.setScreen(MyGdxGame.gameScreen);
                Gdx.app.log("MenuScreen: ", "gameScreen started");
            }
        });
        buttonMainmenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickS.play();
                game.setScreen(GameCore.menuScreen);
                Gdx.app.log("Game Summary: ", "About to call Menu Screen");

                //             game.setScreen(MyGdxGame.gameScreen);
                Gdx.app.log("MenuScreen: ", "gameScreen started");
            }
        });
        stage.addActor(buttonNextLevel);
        stage.addActor(buttonMainmenu);
        Gdx.input.setInputProcessor(stage);

    }

    public void render(float f) {
        Gdx.app.log("MenuScreen: ", "menuScreen render");
        Gdx.app.log("MenuScreen: ", "About to call gameScreen");
        Gdx.app.log("MenuScreen: ", "gameScreen started");
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
        batch.end();
        scoreBatch.begin();

        lifeFont.draw(scoreBatch, "Life:   " + String.format("%3d", lifePoint), Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 + 50f);
        timeFont.draw(scoreBatch, "Time:  " + String.format("%03d", worldTimer), Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 + 100f);
        scoreFont.draw(scoreBatch, "Score: " + String.format("%03d", scorePoint), Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 + 150f);
        lifeFont.draw(scoreBatch, " MISSION IS COMPLETED" , Gdx.graphics.getWidth() / 2 - 150f, Gdx.graphics.getHeight() / 2 + 200f);
        scoreBatch.end();
    }


    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
        Gdx.app.log("MenuScreen: ", "menuScreen show called");
        create();
    }

    @Override
    public void hide() {
        Gdx.app.log("MenuScreen: ", "menuScreen hide called");
    }



}
