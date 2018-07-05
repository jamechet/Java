package unisa.tilemaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by rabouvong on 21/5/17.
 */

public class GameBoardScreen implements Screen {
    public enum GameState { PLAYING, COMPLETE }

    public static final float MOVEMENT_SPEED = 100.0f;

    public static final float GOAL_BOB_HEIGHT = 5.0f;
    public static final float SURFACE_CLIP_EPSILON = 0.01f;

    GameState gameState = GameState.PLAYING;

    //Map and rendering
    SpriteBatch spriteBatch;
    SpriteBatch uiBatch; //Second SpriteBatch without camera transforms, for drawing UI
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;

    //Game clock
    long lastTime;
    float elapsedTime;

    //Player Character
    Texture playerTexture;
    Sprite playerSprite;
    Vector2 playerDelta;
    Rectangle playerDeltaRectangle;

    //Goal
    Texture goalTexture;
    Sprite goalSprite;
    Vector2 goalPosition;
    float goalBobSine;

    //Storage class for collision
    Rectangle tileRectangle;

    //UI textures
    Texture buttonSquareTexture;
    Texture buttonSquareDownTexture;
    Texture buttonLongTexture;
    Texture buttonLongDownTexture;

    //UI Buttons
    Button moveLeftButton;
    Button moveRightButton;
    Button moveDownButton;
    Button moveUpButton;
    Button restartButton;

    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 4;
    private static final int NUMBER_MONSTERS = 6;


    TextureRegion[] playerFrame;
    TextureRegion currentFrame;
    Animation moveAnimation;
    float stateTime;

    PlayerControl playerControl;
    Character monster;
    Character monster1;
    ArrayList<Character> allMonster = new ArrayList<Character>();
    int levelPixelWidth;
    int levelPixelHeight;
    float w;
    float h;
    int direction = 1;
    // The class with the menu
    public static MenuScreen menuScreen;
    //Just use this to only restart when the restart button is released instead of immediately as it's pressed
    boolean restartActive;
    BitmapFont timeFont;
    BitmapFont lifeFont;
    BitmapFont scoreFont;
    Integer worldTimer;
    long startTime = 0;
    Integer lifePoint;
    Integer scorePoint;
    Boolean playerCollide = false;

    SpriteBatch scoreBatch;

    // particle effect
    private ParticleEffect particleEffect;
    int count = 1;
    long stateTime1 = 0;
    SpriteBatch batchP;

    // sound
    Sound burnS;
    Sound footStepS;

    GameCore game; // Note it's "MyGdxGame" not "Game"
    // constructor to keep a reference to the main Game class
    public GameBoardScreen(GameCore game){
        this.game = game;
    }

    public void create() {
        Gdx.app.log("GameScreen: ","gameScreen create");
        //LibGDX Settings
   //     Gdx.app.setLogLevel(Application.LOG_DEBUG); //Allows sending messages to Logcat

        //Rendering
        spriteBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        tiledMap = new TmxMapLoader().load("test.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);



        // calculate map pixel to restrict camera inside the bounce
        // calculate map pixel to restrict camera inside the bounce
        MapProperties mapProperties = tiledMap.getProperties();
        int levelWidth = mapProperties.get("width", Integer.class);
        int levelHeight = mapProperties.get("height", Integer.class);
        int tilePixelWidth = mapProperties.get("tilewidth", Integer.class);
        int tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        levelPixelWidth = levelWidth * tilePixelWidth;
        levelPixelHeight = levelHeight * tilePixelHeight;

        //Camera
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        //    camera.setToOrtho(false, w/h * 250, 250);
        camera.setToOrtho(false, w  , h);
        camera.update();



        // Score timer title
        startTime = TimeUtils.nanoTime();
        scoreBatch = new SpriteBatch();
        timeFont = new BitmapFont();
        lifeFont = new BitmapFont();
        scoreFont = new BitmapFont();

        timeFont.setColor(Color.GREEN);
        lifeFont.setColor(Color.GREEN);
        scoreFont.setColor(Color.GREEN);
        worldTimer = 200;
        scorePoint = 0;
        lifePoint = 200;
        timeFont.scale(1);
        lifeFont.scale(1);
        scoreFont.scale(1);


        // all the monsters
        for (int i = 0; i < NUMBER_MONSTERS; i++){
            allMonster.add(new Character("Monster-01.png"));
        }



        playerTexture    = new Texture("123.png");
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


        goalTexture = new Texture("goal.png");
        //   buttonSquareTexture = new Texture("buttonSquare_blue.png");
        buttonSquareTexture = new Texture("goal.png");
        buttonSquareDownTexture = new Texture("buttonSquare_beige_pressed.png");
        buttonLongTexture = new Texture("buttonLong_blue.png");
        buttonLongDownTexture = new Texture("buttonLong_beige_pressed.png");

        //Player
        playerSprite = new Sprite(currentFrame);
        playerSprite.setSize(30, 30);
        playerDelta = new Vector2();
        playerDeltaRectangle = new Rectangle(0, 0, playerSprite.getWidth(), playerSprite.getHeight());

        //Goal
        goalSprite = new Sprite(goalTexture);
        goalPosition = new Vector2(0,0);
        goalBobSine = 0.0f;

        //Collision
        tileRectangle = new Rectangle();
        MapLayer collisionLayer = tiledMap.getLayers().get("Collision");
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) collisionLayer;
        tileRectangle.width = tileLayer.getTileWidth();
        tileRectangle.height = tileLayer.getTileHeight();

        //Buttons
        float buttonSize = h * 0.1f;
        moveLeftButton = new Button(0.0f, buttonSize, buttonSize, buttonSize, new Texture("Key-none-left.png"), new Texture("Key-pressed-left.png"));
        moveRightButton = new Button(buttonSize*2, buttonSize, buttonSize, buttonSize, new Texture("Key-none-right.png"), new Texture("Key-pressed-right.png"));
        moveDownButton = new Button(buttonSize, 0.0f, buttonSize, buttonSize, new Texture("Key-none-down.png"), new Texture("Key-pressed-down.png"));
        moveUpButton = new Button(buttonSize, buttonSize*2, buttonSize, buttonSize, new Texture("Key-none-up.png"), new Texture("Key-pressed-up.png"));
        restartButton = new Button(w/2 - buttonSize*2, h * 0.2f, buttonSize*4, buttonSize, buttonLongTexture, buttonLongDownTexture);


        //Particle effect
        batchP = new SpriteBatch();
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("data/fire2.p"), Gdx.files.internal("data/"));

        // sound effect
        burnS = Gdx.audio.newSound(Gdx.files.internal("sound/burn.mp3"));
        footStepS = Gdx.audio.newSound(Gdx.files.internal("sound/foot-step.mp3"));


       newGame();

    }
    private void newGame() {
        gameState = GameState.PLAYING;

        //Translate camera to center of screen
        camera.position.x = 16;
        camera.position.y = 16;

        lastTime = System.currentTimeMillis();
        elapsedTime = 0.0f;

        MapLayer objectLayer = tiledMap.getLayers().get("Objects");

        //Player start location, loaded from the tilemaze's object layer.
        RectangleMapObject playerObject = (RectangleMapObject) objectLayer.getObjects().get("Player");
        playerSprite.setCenter(playerObject.getRectangle().x, playerObject.getRectangle().y);
        camera.translate(playerSprite.getX(), playerSprite.getY());

        //Goal Location
        RectangleMapObject goalObject = (RectangleMapObject) objectLayer.getObjects().get("Goal");
        goalPosition.x = goalObject.getRectangle().x - 16;
        goalPosition.y = goalObject.getRectangle().y - 16;

        //Monsters Location
        for (int i = 0; i < NUMBER_MONSTERS; i++){
            RectangleMapObject monLocation = (RectangleMapObject) objectLayer.getObjects().get(i+3);
            allMonster.get(i).characterSprite.setCenter(monLocation.getRectangle().x, monLocation.getRectangle().y);
        }

        restartActive = false;
    }

    private void update() {
        //


        // Controller for player
        playerControl();

        // Control monster movement
        for (int i =0 ; i < NUMBER_MONSTERS; i++){
            controlMonsterMovement(allMonster.get(i));
        }


        goalBobSine += elapsedTime;
        goalBobSine %= Math.PI;
        goalSprite.setPosition(goalPosition.x, goalPosition.y + (GOAL_BOB_HEIGHT / 2.0f) -
                (GOAL_BOB_HEIGHT * (float) Math.sin(goalBobSine)));
    }
    public void playerControl(){
        boolean checkTouch = Gdx.input.isTouched();
        int touchX = Gdx.input.getX();
        int touchY = Gdx.input.getY();

        stateTime += Gdx.graphics.getDeltaTime()*8;
        if (stateTime>4){
            stateTime = 0;
        }
        //Update Game State based on input
        switch (gameState) {

            case PLAYING:
                //Poll user for input
                moveLeftButton.update(checkTouch, touchX, touchY);
                moveRightButton.update(checkTouch, touchX, touchY);
                moveDownButton.update(checkTouch, touchX, touchY);
                moveUpButton.update(checkTouch, touchX, touchY);

                int moveX = 0;
                int moveY = 0;
                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || moveLeftButton.isDown) {
                    currentFrame = moveAnimation.getKeyFrame(0+stateTime, true);
                    moveLeftButton.isDown = true;
                    moveX -= 1;
                    footStepS.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || moveRightButton.isDown) {
                    currentFrame = moveAnimation.getKeyFrame(4+stateTime, true);
                    moveRightButton.isDown = true;
                    moveX += 1;
                    footStepS.play();
                }
                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) || moveDownButton.isDown) {
                    footStepS.play();
                    currentFrame = moveAnimation.getKeyFrame(8+stateTime, true);

                    moveDownButton.isDown = true;
                    moveY -= 1;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) || moveUpButton.isDown) {
                    footStepS.play();
                    currentFrame = moveAnimation.getKeyFrame(12+stateTime, true);
                    moveUpButton.isDown = true;
                    moveY += 1;
                }

                playerSprite.setRegion(currentFrame);
                //TODO Determine character movement distance
                playerDelta.x = moveX * MOVEMENT_SPEED * elapsedTime;
                playerDelta.y = moveY * MOVEMENT_SPEED * elapsedTime;

                //Check movement against grid
                if (playerDelta.len2() > 0) { //Don't do anything if we're not moving
                    //Retrieve Collision layer
                    MapLayer collisionLayer = tiledMap.getLayers().get("Collision");
                    TiledMapTileLayer tileLayer = (TiledMapTileLayer) collisionLayer;

                    //TODO Determine bounds to check within
                    // Find top right corner tile
                    int right = (int) Math.ceil(Math.max(playerSprite.getX() + playerSprite.getWidth(),
                            playerSprite.getX() + playerSprite.getWidth() + playerDelta.x));
                    int top = (int) Math.ceil(Math.max(playerSprite.getY() + playerSprite.getHeight(),
                            playerSprite.getY() + playerSprite.getHeight() + playerDelta.y));
                    // Find bottom left corner tile
                    int left = (int) Math.floor(Math.min(playerSprite.getX(),
                            playerSprite.getX() + playerDelta.x));
                    int bottom = (int) Math.floor(Math.min(playerSprite.getY(),
                            playerSprite.getY() + playerDelta.y));

                    // Dividing bounds by tile sizes to retrieve tile indices
                    right /= tileLayer.getTileWidth();
                    left /= tileLayer.getTileWidth();
                    top  /= tileLayer.getTileHeight();
                    bottom /= tileLayer.getTileHeight();



                    //TODO Loop through selected tiles and correct by each axis
                    //EXTRA: Try counting down if moving left or down instead of counting up
                    for (int y = bottom; y <= top; y++){
                        for (int x = left; x <= right; x++){
                            TiledMapTileLayer.Cell targetCell = tileLayer.getCell(x, y);

                            //if  the cell is empty, ignore it
                            if (targetCell == null) continue;

                            tileRectangle.x = x * tileLayer.getTileWidth();
                            tileRectangle.y = y * tileLayer.getTileHeight();
                            playerDeltaRectangle.x  = playerSprite.getX() + playerDelta.x;
                            playerDeltaRectangle.y = playerSprite.getY() + playerDelta.y;

                            //Check to see if the player's destination collides with the tile
                            if (!tileRectangle.overlaps(playerDeltaRectangle)) continue;

                            //Only correct against one axis at a time to prevent from getting caught on corners
                            if (Math.abs(playerDelta.x) > Math.abs(playerDelta.y)) {
                                // Only check in direction the player is actually moving
                                // if the player is not moving in this axis, then there's no need to
                                // check anything.
                                if (playerDelta.x > 0) {
                                    float difference = (playerDeltaRectangle.x + playerDeltaRectangle.width) - tileRectangle.x;
                                    playerDelta.x -= difference + SURFACE_CLIP_EPSILON;
                                }
                                else if (playerDelta.x < 0) {
                                    float difference = (tileRectangle.x + tileRectangle.width) - playerDeltaRectangle.x;
                                    playerDelta.x += difference + SURFACE_CLIP_EPSILON;
                                }
                            }
                            else {
                                if (playerDelta.y > 0){
                                    float difference = (playerDeltaRectangle.y + playerDeltaRectangle.height) - tileRectangle.y;
                                    playerDelta.y -= difference + SURFACE_CLIP_EPSILON;
                                }
                                else if (playerDelta.y <0){
                                    float difference = (tileRectangle.y + tileRectangle.height) -playerDeltaRectangle.y;
                                    playerDelta.y += difference + SURFACE_CLIP_EPSILON;
                                }
                            }
                        }
                    }

                    //TODO Move player
                    playerSprite.translate(playerDelta.x, playerDelta.y);
                    camera.translate(playerDelta.x, playerDelta.y);
                }

                //TODO Check if player has met the winning condition
                if (playerSprite.getBoundingRectangle().overlaps(goalSprite.getBoundingRectangle())){
                    scorePoint = worldTimer;
                    lifePoint = worldTimer + lifePoint;
                    game.setScreen(GameCore.gameWinningScreen);
                    //gameState = GameState.COMPLETE;
                }
                // check if player has hit a monster
                for (int i = 0; i < NUMBER_MONSTERS; i++){
                    if (playerSprite.getBoundingRectangle().overlaps(allMonster.get(i).characterSprite.getBoundingRectangle())){
                   //     gameState = GameState.COMPLETE;
                        burnS.play();
                        playerCollide = true;
                        lifePoint--;
                        if (lifePoint == 0 || worldTimer == 0){
                            game.setScreen(GameCore.gameOverScreen);
                        }

                    }
                }
                break;

            case COMPLETE:
                //Poll for input
                restartButton.update(checkTouch, touchX, touchY);

                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_CENTER) || restartButton.isDown) {
                    restartButton.isDown = true;
                    restartActive = true;
                } else if (restartActive) {
                    newGame();
                }
                break;
        }

    }
    public void controlMonsterMovement(Character object){

        boolean checkTouch = Gdx.input.isTouched();
        int touchX = Gdx.input.getX();
        int touchY = Gdx.input.getY();
        int moveX = 0;
        int moveY = 0;
        object.stateTime += Gdx.graphics.getDeltaTime()*8;
        if (object.stateTime>4){
            object.stateTime = 0;
        }
        if (object.direction == 1) {

            object.currentFrame = object.moveAnimation.getKeyFrame(4 + object.stateTime, true);//4
            moveX -= 1;
            object.characterSprite.setRegion(object.currentFrame);
            object.characterDelta.x = moveX * 100 * elapsedTime;
            object.characterDelta.y = moveY * 100 * elapsedTime;
            direction = wallCollision01(object);
        }
        else if (object.direction == 2){
            object.currentFrame = object.moveAnimation.getKeyFrame(8 + object.stateTime, true);//8
            moveX += 1;
            object.characterSprite.setRegion(object.currentFrame);
            object.characterDelta.x = moveX * 100 * elapsedTime;
            object.characterDelta.y = moveY * 100 * elapsedTime;
            direction = wallCollision01(object);
        }
        else if (object.direction == 3){
            object.currentFrame = object.moveAnimation.getKeyFrame(0 + object.stateTime, true);//0
            moveY -= 1;
            object.characterSprite.setRegion(object.currentFrame);
            object.characterDelta.x = moveX * 100 * elapsedTime;
            object.characterDelta.y = moveY * 100 * elapsedTime;
            direction = wallCollision01(object);
        }
        else if(object.direction == 4) {
            object.currentFrame = object.moveAnimation.getKeyFrame(12 + object.stateTime, true);
            moveY += 1;
            object.characterSprite.setRegion(object.currentFrame);
            object.characterDelta.x = moveX * 100 * elapsedTime;
            object.characterDelta.y = moveY * 100 * elapsedTime;
            direction = wallCollision01(object);
        }
    }
    public int wallCollision01(Character object){
        Sprite playerSprite = object.getCharacterSprite();
        Vector2 playerDelta = object.getCharacterDelta();
        // boolean collided = false;
        //Check movement against grid
        if (object.getCharacterDelta().len2() > 0) { //Don't do anything if we're not moving
            //Retrieve Collision layer
            MapLayer collisionLayer = tiledMap.getLayers().get("Collision");
            TiledMapTileLayer tileLayer = (TiledMapTileLayer) collisionLayer;
            //TODO Determine bounds to check within
            // Find top right corner tile
            int right = (int) Math.ceil(Math.max(playerSprite.getX() +
                            playerSprite.getWidth(),
                    playerSprite.getX() + playerSprite.getWidth() + playerDelta.x));
            int top = (int) Math.ceil(Math.max(playerSprite.getY() +
                            playerSprite.getHeight(),
                    playerSprite.getY() + playerSprite.getHeight() + playerDelta.y));
            // Find bottom left corner tile
            int left = (int) Math.floor(Math.min(playerSprite.getX(),
                    playerSprite.getX() + playerDelta.x));
            int bottom = (int) Math.floor(Math.min(playerSprite.getY(),
                    playerSprite.getY() + playerDelta.y));
            // Dividing bounds by tile sizes to retrieve tile indices
            right /= tileLayer.getTileWidth();
            left /= tileLayer.getTileWidth();
            top /= tileLayer.getTileHeight();
            bottom /= tileLayer.getTileHeight();
            //EXTRA: Try counting down if moving left or down instead of counting up
            for (int y = bottom; y <= top; y++) {
                for (int x = left; x <= right; x++) {
                    TiledMapTileLayer.Cell targetCell = tileLayer.getCell(x, y);
                    //if the cell is empty, ignore it
                    if (targetCell == null) continue;
                    tileRectangle.x = x * tileLayer.getTileWidth();
                    tileRectangle.y = y * tileLayer.getTileHeight();
                    float x1 = playerSprite.getX() + playerDelta.x;
                    float y1 = playerSprite.getY() + playerDelta.y;
                    object.setCharacterDeltaRectangle(x1, y1);
                    //Check to see if the player's destination collides with the tile
                    if (!tileRectangle.overlaps(object.characterDeltaRectangle)) continue;
                    else {
                        if (object.direction == 1){
                            object.direction = 2;
                        }
                        else if (object.direction == 2){
                            object.direction = 3;
                        }
                        else if (object.direction == 3){
                            object.direction = 4;
                        }
                        else if (object.direction == 4){
                            object.direction = 1;
                        }
                    }
                    if (Math.abs(playerDelta.x) > Math.abs(playerDelta.y)) {
                        // Only check in direction the player is actually moving
                        // if the player is not moving in this axis, then there's no need to
                        // check anything.
                        if (playerDelta.x > 0) {
                            float difference = (object.characterDeltaRectangle.x +
                                    object.characterDeltaRectangle.width) - tileRectangle.x;
                            playerDelta.x -= difference + SURFACE_CLIP_EPSILON;
                            object.setCharacterDeltaX(playerDelta.x);
                        } else if (playerDelta.x < 0) {
                            float difference = (tileRectangle.x + tileRectangle.width) -
                                    object.characterDeltaRectangle.x;
                            playerDelta.x += difference + SURFACE_CLIP_EPSILON;
                            object.setCharacterDeltaX(playerDelta.x);
                        }
                    } else {
                        if (playerDelta.y > 0) {
                            float difference = (object.characterDeltaRectangle.y +
                                    object.characterDeltaRectangle.height) - tileRectangle.y;
                            playerDelta.y -= difference + SURFACE_CLIP_EPSILON;
                            object.setCharacterDeltaY(playerDelta.y);
                        } else if (playerDelta.y < 0) {
                            float difference = (tileRectangle.y + tileRectangle.height) -
                                    object.characterDeltaRectangle.y;
                            playerDelta.y += difference + SURFACE_CLIP_EPSILON;
                            object.setCharacterDeltaX(playerDelta.y);
                        }
                    }
                }
            }
            //TODO Move player
            object.setCharacterSpriteCamera(object.getCharacterDelta().x,
                    object.getCharacterDelta().y);
            //disable camera move here
            // camera.translate(object.getCharacterDelta().x, object.getCharacterDelta().y);
        }
        return direction;
    }
    public void render(float f) {
        //Game World Update ------------------------------------------------------------------------

        //TODO Update game clock first
        long currentTime = System.currentTimeMillis();
        elapsedTime = (currentTime - lastTime) / 1000.0f;
        lastTime = currentTime;

        update();

        //Rendering --------------------------------------------------------------------------------

        //Clear the screen every frame before drawing.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Allows transparent sprites/tiles
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // restrict the camera out of bounce from the map
        camera.position.x = Math.min(Math.max(playerSprite.getX(), w / 2), levelPixelWidth - (w /2));
        camera.position.y = Math.min(Math.max(playerSprite.getY(), h / 2), levelPixelHeight - (h / 2));


        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //Apply camera to spritebatch and draw player
    //    spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        for (int i = 0; i < NUMBER_MONSTERS; i++){
            allMonster.get(i).characterSprite.draw(spriteBatch);
        }

        goalSprite.draw(spriteBatch);
        playerSprite.draw(spriteBatch);
        spriteBatch.end();

        //Draw UI
        uiBatch.begin();
        switch(gameState) {
            //if gameState is Running: Draw Controls
            case PLAYING:
                moveLeftButton.draw(uiBatch);
                moveRightButton.draw(uiBatch);
                moveDownButton.draw(uiBatch);
                moveUpButton.draw(uiBatch);
                break;
            //if gameState is Complete: Draw Restart button
            case COMPLETE:
                restartButton.draw(uiBatch);
                break;
        }
        uiBatch.end();
        // update world timer
        if (TimeUtils.timeSinceNanos(startTime) > 1000000000) {
            worldTimer--;
            startTime = TimeUtils.nanoTime();
        }
        scoreBatch.begin();
        lifeFont.draw(scoreBatch, "Life: " + String.format("%1d", lifePoint), Gdx.graphics.getWidth() / 2 - 390f, Gdx.graphics.getHeight() / 2 + 200f);
        timeFont.draw(scoreBatch, "Time: " + String.format("%03d", worldTimer), Gdx.graphics.getWidth() / 2 - 50f, Gdx.graphics.getHeight() / 2 + 200f);
        scoreFont.draw(scoreBatch, "Score: " + String.format("%03d", scorePoint), Gdx.graphics.getWidth() / 2 + 250f, Gdx.graphics.getHeight() / 2 + 200f);
        scoreBatch.end();
        // effect when player collide with monster
        particleEffect.update(Gdx.graphics.getDeltaTime());
        batchP.setProjectionMatrix(camera.combined);
        batchP.begin();
        if (playerCollide == true) {
            particleEffect.setPosition(playerSprite.getX(), playerSprite.getY());
            particleEffect.draw(batchP,Gdx.graphics.getDeltaTime());
            if (TimeUtils.timeSinceNanos(stateTime1) > 1000000000) {
                stateTime1 = TimeUtils.nanoTime();
                if (count == 0){
                    playerCollide = false;
                    count = 1;
                }
                count--;
            }
        }

        batchP.end();
        if (particleEffect.isComplete())
            particleEffect.reset();


    }

    @Override
    public void dispose() { }
    @Override
    public void resize(int width, int height) {
      //  camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width/2f, height/2f, 0);
    }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() {
        Gdx.app.log("GameScreen: ", "show called ");
        create();
    }
    @Override
    public void hide() {
    }


}
