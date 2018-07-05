package unisa.tilemaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by rabouvong on 21/5/17.
 */

public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Sound clickS;

    GameCore game; // Note it's "MyGdxGame" not "Game"
    public static Texture backgroundTexture;


    public MenuScreen(GameCore game) {
        this.game = game;
        clickS = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));

    }

    public void create() {

        Gdx.app.log("MenuScreen: ", "menuScreen create");
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("uidata/uiskin.json"));
        stage = new Stage();
        final TextButton buttonPlay = new TextButton("Start", skin, "default");
        final TextButton buttonGamelevel = new TextButton("Game Level", skin, "default");
        final TextButton buttonExit = new TextButton("Exit", skin, "default");
        backgroundTexture = new Texture("Menu-background.jpg");

        buttonPlay.setWidth(100f);
        buttonPlay.setHeight(50f);
        buttonGamelevel.setWidth(100f);
        buttonGamelevel.setHeight(50f);
        buttonExit.setWidth(100f);
        buttonExit.setHeight(50f);

        buttonPlay.setPosition(Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 + 100f);
        buttonGamelevel.setPosition(Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 - 50f);//
        buttonExit.setPosition(Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 - 200f);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickS.play();
                buttonPlay.setText("You clicked the button");
                Gdx.app.log("MenuScreen: ", "About to call gameScreen");

                             game.setScreen(GameCore.gameScreen);
                Gdx.app.log("MenuScreen: ", "gameScreen started");
            }
        });
        buttonGamelevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickS.play();
                buttonPlay.setText("You clicked the button");
                Gdx.app.log("MenuScreen: ", "About to call gameScreen");

                //             game.setScreen(MyGdxGame.gameScreen);
                Gdx.app.log("MenuScreen: ", "gameScreen started");
            }
        });
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickS.play();
                buttonExit.setText("You clicked the button");
                Gdx.app.exit();
                Gdx.app.log("MenuScreen: ", "About to call gameScreen");

                //             game.setScreen(MyGdxGame.gameScreen);
                Gdx.app.log("MenuScreen: ", "gameScreen started");
            }
        });
        stage.addActor(buttonPlay);
        stage.addActor(buttonGamelevel);
        stage.addActor(buttonExit);
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