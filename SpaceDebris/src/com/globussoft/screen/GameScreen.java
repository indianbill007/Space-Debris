package com.globussoft.screen;

import GameObjects.Constantsvalue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.SDhelper.InputHandler;
import com.globussoft.gameworld.GameRenderer;
import com.globussoft.gameworld.GameWorld;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class GameScreen implements Screen {
	private SpaceDebrisGame game;
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	Animation animation;
	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;
	private Button back;

	float gameHeight;
	float gameWidth;

	public GameScreen(final SpaceDebrisGame game) {
		batch = new SpriteBatch();
		Constantsvalue.lifealive = true;
		//Timer.instance().start();
		this.game = game;
		System.out.println("game screen attached");
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		gameWidth = 136;
		gameHeight = screenHeight / (screenWidth / gameWidth);
		int midPointY = (int) (gameHeight / 2);
		System.out.println("midPointY" + midPointY);
		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		Constantsvalue.runfirsttime = true;
        Constantsvalue.isStoredData=true;
        game.fbinterface.loadAdmob();
        
        System.out.println("GameScreen Constructor");
        Timer.instance().clear();
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
		try {
			stage.act(delta);
			batch.begin();
			stage.draw();
			batch.end();
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	
		if (Constantsvalue.islfezero) {
			Constantsvalue.islfezero=false;
			Constantsvalue.islifeover=true;
			AssetLoader.setCountdownTime(300000);
			game.setScreen(new NoLifeScreen(game));
			if (AssetLoader.ismusicon()==true) {
				AssetLoader.lifeover.play();
			}
			
			//dispose();
		}

		/*
		 * if (AssetLoader.getExtraLife() == 0) {
		 * 
		 * Constantsvalue.islifeover=true;
		 * 
		 * AssetLoader.setCountdownTime(300000);
		 * 
		 * game.setScreen(new NoLifeScreen(game));
		 * 
		 * 
		 * }
		 */
		if (Constantsvalue.leveluptimer<0&& (Constantsvalue.selected_level + 1) == AssetLoader.getLevel()) 
		{
			
			int totalLevel = AssetLoader.getLevel();
			AssetLoader.setLevel(totalLevel + 1);
			Timer.instance().clear();
			game.setScreen(new LevelClearScreen(world, game));
			
			//dispose();

		}
		
		
		if (Constantsvalue.leveluptimer<0&& (Constantsvalue.selected_level + 1) != AssetLoader.getLevel()) 
		{
		
				
			
			Timer.instance().clear();
			game.setScreen(new LevelClearScreen(world, game));
			//dispose();
			System.out.println("levelclear2");
			
		}
		if (Constantsvalue.ishitdebris) 
		{
			System.out.println("life get from assest loder "+ AssetLoader.getExtraLife());
			Timer.instance().stop();
			Constantsvalue.ishitdebris = false;
			GameWorld.ismandie=false;
			game.fbinterface.ChartBoost();
			Constantsvalue.didseenvideo=false;
			if (AssetLoader.ismusicon()==true) {
				AssetLoader.playing_sound.pause();
			}
			
		}
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(new InputHandler(world));
		Gdx.input.setInputProcessor(multiplexer);
		// nomore life
		// Gdx.input.setInputProcessor(stage);
		ButtonStyle buttonStyleback = new ButtonStyle();
		buttonStyleback.up = skin.getDrawable("back_hdpi");
		back = new Button(buttonStyleback);
		back.setWidth(back.getWidth());
		back.setHeight(back.getHeight());
		back.setX(1f);
		back.setY(Gdx.graphics.getHeight() - back.getHeight() * 1.5f);
		// back.addAction(Actions.moveTo(1f,Gdx.graphics.getHeight()-back.getHeight(),
		// 3f));
		back.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new LevelScreen(game));
				//dispose();
				Timer.instance().clear();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

			}
		});
		stage.addActor(back);
	}

	@Override
	public void show() {
		skin = new Skin();
		skin.addRegions(AssetLoader.ImagePackV2);
		if (AssetLoader.ismusicon()==true) {
			AssetLoader.playing_sound.play();
			AssetLoader.playing_sound.setLooping(true);
		}
	
		
		 
	}

	@Override
	public void hide() {
	
		//game.fbinterface.destroybanner();
		if (AssetLoader.ismusicon()==true) {
			AssetLoader.playing_sound.stop();
		}
		
		dispose();
	}

	@Override
	public void pause() {
		System.out.println("inside pause");

		//Timer.instance().stop();
	}

	@Override
	public void resume() {
		System.out.println("inside resume");
		
	}

	@Override
	public void dispose() {
		System.out.println("inside dispose");
		renderer.GameRenderdispose();
	
		//Timer.instance().clear();
	}
}
