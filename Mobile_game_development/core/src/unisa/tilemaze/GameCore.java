package unisa.tilemaze;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**UniSA Tiled-LibGDX implementation prac.
 *
 * TiledMap implementation sourced from
 * http://www.gamefromscratch.com/post/2014/04/16/LibGDX-Tutorial-11-Tiled-Maps-Part-1-Simple-Orthogonal-Maps.aspx
 * follow link for more details.*/
public class GameCore extends Game implements ApplicationListener {

    public static GameBoardScreen gameScreen;
    public static MenuScreen menuScreen;
    public static GameOverScreen gameOverScreen;
    public static WinningScreen gameWinningScreen;
    @Override
    public void create() {
        Gdx.app.log("GameCore: "," create");
        gameScreen = new GameBoardScreen(this);
        menuScreen = new MenuScreen(this);
        gameOverScreen = new GameOverScreen(this);
        gameWinningScreen = new WinningScreen(this);
        Gdx.app.log("GameCore: ","about to change screen to menuScreen");
        // Change screens to the menu
        setScreen(menuScreen);
        Gdx.app.log("GameCore: ","changed screen to menuScreen");


    }
    @Override
    public void dispose() {super.dispose();}

    @Override
    // this method calls the super class render
    // which in turn calls the render of the actual screen being used
    public void render() {super.render();}

    @Override
    public void resize(int width, int height) { super.resize(width, height);}

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
