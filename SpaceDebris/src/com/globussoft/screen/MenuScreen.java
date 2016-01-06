package com.globussoft.screen;


import java.util.Iterator;

import GameObjects.Constantsvalue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.gameworld.GameWorld;
import com.globussoft.interfaces.facebookinterface;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class MenuScreen implements Screen {

	
	facebookinterface fbinterface;
	private SpaceDebrisGame game;
	private SpriteBatch batch;

	TextureRegion bg, play, spacedebrislogo, button, background, title,facebook,starY,facebooklogot;
	OrthographicCamera camera;
	Vector3 touchPoint;

    private Stage stage;
	private TextureAtlas atlas,logoutatlas,joincontestatlas;
	private Skin skin,logoutskin,joincontestskin;
	Button playbutton, settingbutton, helpbutton, facebookbutton,storebutton,logoutbutton,contestbutton;
	private OrthographicCamera cam;
	float gameHeight, w, h, desiredWidth, scale;
	//star drop
	private Array<Rectangle> stars;
	private long lastDropTime;
    public MenuScreen(SpaceDebrisGame game) {

    	Constantsvalue.isShownStatusDialog=true;
    	
    	batch=null;
		skin=null;
		atlas=null;
		stage=null;
		logoutatlas=null;
		logoutskin=null;
    	
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	stars = new Array<Rectangle>();
	spawnStars();
	float gameWidth = 136;
	gameHeight = screenHeight / (screenWidth / gameWidth);
	touchPoint = new Vector3();
	this.game=game;
	batch = new SpriteBatch();
	if(Gdx.graphics.getWidth()>1020f)
	{
	starY=AssetLoader.bigstar;
	}

	else {
			
			starY=AssetLoader.star;
		}
	
	
	}
    
	private void spawnStars() {
	      Rectangle star1 = new Rectangle();
	      star1.x = MathUtils.random(0, Gdx.graphics.getWidth()-64);
	      star1.y = Gdx.graphics.getHeight();
	      star1.width = 256;
	      star1.height = 256;
	      stars.add(star1);
	      lastDropTime = TimeUtils.nanoTime();
	    //  System.out.println("stars1");
	}
	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);

		stage.clear();
		Gdx.input.setInputProcessor(stage);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("taptoplay_hdpi");

		// play button

		playbutton = new Button(buttonStyle);
		playbutton.setWidth(playbutton.getWidth());
		playbutton.setHeight(playbutton.getHeight());
		playbutton.setX(Gdx.graphics.getWidth() / 2f - playbutton.getWidth()
				/ 2f);
		playbutton.setY(Gdx.graphics.getHeight() / 2f - playbutton.getHeight()
				/ 2f);

		playbutton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new LevelScreen(game));
				dispose();
			}
		});

//		 facebook button
		ButtonStyle buttonStyle2 = new ButtonStyle();
		
		
		buttonStyle2.up = skin.getDrawable("fb_connect_hdpi");
		
		facebookbutton = new Button(buttonStyle2);
		facebookbutton.setWidth(facebookbutton.getWidth());
		facebookbutton.setHeight(facebookbutton.getHeight());
		facebookbutton.setX(Gdx.graphics.getWidth() / 2f -facebookbutton.getWidth()/ 2f);
		//facebookbutton.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f -facebookbutton.getWidth()/ 2f, Gdx.graphics.getHeight() /6, 0.7f, Interpolation.linear));
		facebookbutton.setY(Gdx.graphics.getHeight() /8);
		
		facebookbutton.addListener(new InputListener() {
			
		 @Override
		 public boolean touchDown(InputEvent event, float x, float y,
		 int pointer, int button) {
		 return true;
		 }
		
		 @Override
		 public void touchUp(InputEvent event, float x, float y,
		 int pointer, int button) {
		
			 game.fbinterface.facebookconnectExecution();
	      
		 
		 
		 
		 }
		 });
		
		
//		 logout facebook button
		ButtonStyle buttonStyle4 = new ButtonStyle();
		
		
		buttonStyle4.up = logoutskin.getDrawable("logout");
		
		logoutbutton = new Button(buttonStyle4);
		logoutbutton.setWidth(logoutbutton.getWidth());
		logoutbutton.setHeight(facebookbutton.getHeight());
		logoutbutton.setX(Gdx.graphics.getWidth() / 2f -logoutbutton.getWidth()/ 2f);
		//facebookbutton.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f -facebookbutton.getWidth()/ 2f, Gdx.graphics.getHeight() /6, 0.7f, Interpolation.linear));
		logoutbutton.setY(Gdx.graphics.getHeight() /8);
		
		logoutbutton.addListener(new InputListener() {
			
		 @Override
		 public boolean touchDown(InputEvent event, float x, float y,
		 int pointer, int button) {
		 return true;
		 }
		
		 @Override
		 public void touchUp(InputEvent event, float x, float y,
		 int pointer, int button) {
		
		 game.fbinterface.logout();
		
		 
		 
		 }
		 });		

//		 store button
		ButtonStyle buttonStyle3 = new ButtonStyle();
		
		
		buttonStyle3.up = skin.getDrawable("store_hdpi");
		
		storebutton = new Button(buttonStyle3);
		
		storebutton.setWidth(storebutton.getWidth());
		storebutton.setHeight(storebutton.getHeight());
		storebutton.setX(Gdx.graphics.getWidth() / 2f -storebutton.getWidth()/ 2f);
		//facebookbutton.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f -facebookbutton.getWidth()/ 2f, Gdx.graphics.getHeight() /6, 0.7f, Interpolation.linear));
		storebutton.setY(Gdx.graphics.getHeight() /30);
		
		storebutton.addListener(new InputListener() {
			
		 @Override
		 public boolean touchDown(InputEvent event, float x, float y,
		 int pointer, int button) {
		 return true;
		 }
		
		 @Override
		 public void touchUp(InputEvent event, float x, float y,
		 int pointer, int button) {
		
		 game.setScreen(new StoreScreen(game));
		dispose();
		 }
		 });
		
//		 Joincontest button
		ButtonStyle buttonStyle5 = new ButtonStyle();
		
		
		buttonStyle5.up = joincontestskin.getDrawable("joincontest");
		
		contestbutton = new Button(buttonStyle5);
		
		contestbutton.setWidth(contestbutton.getWidth());
		contestbutton.setHeight(contestbutton.getHeight());
		contestbutton.setX(Gdx.graphics.getWidth() / 2f -contestbutton.getWidth()/ 2f);
		//facebookbutton.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f -facebookbutton.getWidth()/ 2f, Gdx.graphics.getHeight() /6, 0.7f, Interpolation.linear));
		contestbutton.setY(Gdx.graphics.getHeight() /4.5f);
		
		contestbutton.addAction(Actions.repeat(10, Actions.sequence(Actions.fadeOut(3f),Actions.fadeIn(0.5f))));

		contestbutton.addListener(new InputListener() {
			
		 @Override
		 public boolean touchDown(InputEvent event, float x, float y,
		 int pointer, int button) {
		 return true;
		 }
		
		 @Override
		 public void touchUp(InputEvent event, float x, float y,
		 int pointer, int button) {
		
			 game.fbinterface.joincontest();
		 
		 }
		 });	
		
		
		stage.addActor(contestbutton);
		stage.addActor(playbutton);
	    stage.addActor(facebookbutton);
       // stage.addActor(storebutton);
        stage.addActor(logoutbutton);
	}

	@Override
	public void show() {
		
		if(Gdx.graphics.getWidth()>1020f){
		atlas = new TextureAtlas("data/menuxxhdpi.atlas");
		logoutatlas=new TextureAtlas("data/fblogout.atlas");
		joincontestatlas=new TextureAtlas("data/join_contest_xxhdpi.atlas");
		}
		
		else {
			joincontestatlas=new TextureAtlas("data/join_contest.atlas");
			atlas = new TextureAtlas("data/menuhdpi.atlas");
			logoutatlas=new TextureAtlas("data/fblogouthdpi.atlas");
		}
		skin = new Skin();
		skin.addRegions(atlas);
		background = AssetLoader.bg;
		title = atlas.findRegion("ready_hdpi");
		facebook=atlas.findRegion("fb_connect_hdpi");
		
		logoutskin=new Skin();
		logoutskin.addRegions(logoutatlas);
		
		joincontestskin=new Skin();
		joincontestskin.addRegions(joincontestatlas);
		game.fbinterface.showPushDialog();
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(Constantsvalue.isconnecttofb==true)
		{
			facebookbutton.setVisible(false);
			logoutbutton.setVisible(true);
		}
		else {
			facebookbutton.setVisible(true);
			logoutbutton.setVisible(false);
		}
	
		stage.act(delta);
		// batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch.end();
		batch.begin();
		for(Rectangle star1: stars) {
		      batch.draw(starY, star1.x, star1.y);
		   }
		   if(TimeUtils.nanoTime() - lastDropTime > 90000000) 
	     	   spawnStars();
		  // System.out.println("star1");
	        Iterator<Rectangle> iter1 = stars.iterator();
	        while(iter1.hasNext()) {
	           Rectangle star1 = iter1.next();
	           star1.y -= 100 * Gdx.graphics.getDeltaTime();
	           if(star1.y + 32 < 0) iter1.remove();
	        }
	        batch.end();
		batch.begin();
		batch.draw(title, Gdx.graphics.getWidth()/2-title.getRegionWidth()/2,
				Gdx.graphics.getHeight() - title.getRegionHeight()-20,
				title.getRegionWidth(), title.getRegionHeight());
		batch.end();

		batch.begin();
		stage.draw();
		
		
		batch.end();
	}

	@Override
	public void hide() {
	
	}

	@Override
	public void pause() {

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
		logoutatlas.dispose();
		logoutskin.dispose();
		joincontestatlas.dispose();
		joincontestskin.dispose();
	}

}
