package unisa.tilemaze;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rabouvong on 10/6/17.
 */

public class Timer_Score_Screen {
    public Stage stage;
    private Viewport viewport;

    public Integer worldTimer;
    private float timeCountDown;
    public Integer score;

    Label timerL;
    Label countDownL;

    public Timer_Score_Screen(){
        worldTimer = 100;
        score = 0;
     //   viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage();
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        timerL = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countDownL = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(timerL).expand().padTop(10);
        table.row();
        table.add(countDownL).expand();
        stage.addActor(table);

    }

}
