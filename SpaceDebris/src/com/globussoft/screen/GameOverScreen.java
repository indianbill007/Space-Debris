package com.globussoft.screen;

import GameObjects.Constantsvalue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.gameworld.GameWorld;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class GameOverScreen implements Screen {
	private GameWorld myWorld;
	SpaceDebrisGame game;
	private Stage stage;
	private SpriteBatch batch;
	TextureRegion bg,gameover;
	private TextureAtlas atlas;
	private Skin skin;
	Vector3 touchPoint;
	Button gameoverbutton;
	private OrthographicCamera cam;
	float gameHeight;
	public GameOverScreen(GameWorld myWorld,SpaceDebrisGame game) {
		float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();  
  
        float gameWidth = 136;
         gameHeight = screenHeight / (screenWidth / gameWidth);
		this.myWorld=myWorld;
		
		
		touchPoint = new Vector3();
		this.game = game;
		batch = new SpriteBatch();
		bg = AssetLoader.gameover;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		batch.setProjectionMatrix(cam.combined);
		
		
		

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
		batch.begin();
		batch.draw(bg, 0, 0, 136,  gameHeight);
		batch.end();
		
		batch.begin();
		  // Convert integer into String
        String score = myWorld.getScore() + "";
        String highScore = AssetLoader.getHighScore() + "";
  
        // Draw text
        AssetLoader.font.draw(batch, score,
                (136 / 2) - (3 * score.length() - 7), gameHeight/2+2);
        
        AssetLoader.font.draw(batch, highScore,
                (136 / 2) - (3 * score.length() - 7), gameHeight/2+40);
        batch.end();
		
		batch.begin();
		stage.draw();
		batch.end();

	}

	
	
	@Override
	public void resize(int width, int height) {
		
		
		
		if (stage == null)
			stage = new Stage(width, height, true);

		stage.clear();
		Gdx.input.setInputProcessor(stage);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("retry_hdpi");
		gameoverbutton = new Button(buttonStyle);
		gameoverbutton.setWidth(gameoverbutton.getWidth());
		gameoverbutton.setHeight(gameoverbutton.getHeight());
		
		gameoverbutton.setX(Gdx.graphics.getWidth()/2-gameoverbutton.getWidth()/2);
		gameoverbutton.setY(20);

		gameoverbutton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {	
				if(AssetLoader.getExtraLife()>0){
				game.setScreen(new LevelScreen(game));
				}
				
				else
				{
					Constantsvalue.islifeover=true;
					
					AssetLoader.setCountdownTime(300000);
					
					game.setScreen(new NoLifeScreen(game));
					
				}
			}	
		});
		
		stage.addActor(gameoverbutton);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		if(Gdx.graphics.getWidth()>1020f)
		{
			atlas = new TextureAtlas("data/retryBig.atlas");
		}
		
		else
		{
			atlas = new TextureAtlas("data/ImagePackV2");	
		}
		skin = new Skin();
		skin.addRegions(atlas);
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void dispose() {
		
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		stage.dispose();

	}

}
