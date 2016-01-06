package com.globussoft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.globussoft.SDhelper.AssetLoader;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class StoreScreen implements Screen {

	private SpaceDebrisGame game;
	private SpriteBatch batch;

	TextureRegion background, store,storeimage,money;
	
	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas, levelscreenatlas,buylifeatlas;
	Button buy, back;
	public int result;
	private boolean clickstatus = true;

	public StoreScreen(SpaceDebrisGame game) {

		atlas=null;
		levelscreenatlas=null;
		skin=null;
		batch=null;
		stage=null;
		this.game = game;
		batch = new SpriteBatch();

		if (Gdx.graphics.getWidth()>1020f) {
			buylifeatlas=new TextureAtlas("data/buylife_xxhdpi.atlas");
			}
		else {
			buylifeatlas=new TextureAtlas("data/buylife_hdpi.atlas");
		}
		money=buylifeatlas.findRegion("500_lives");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		batch.begin();
		batch.draw(AssetLoader.bg, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch.end();
		
		batch.begin();
		stage.draw();
		batch.end();

		batch.begin();
		batch.draw(money, Gdx.graphics.getWidth() / 2 - money.getRegionWidth()
				/ 2,
				Gdx.graphics.getHeight() / 2 + money.getRegionHeight() / 2,
				money.getRegionWidth(), money.getRegionHeight());
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		// buy button

		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("buy_hdpi");

		buy = new Button(buttonStyle);
		buy.setWidth(buy.getWidth());
		buy.setHeight(buy.getHeight());
		buy.setX(Gdx.graphics.getWidth() / 2f - buy.getWidth() / 2f);
		buy.setY(Gdx.graphics.getHeight() / 2 - buy.getHeight() / 2);

		buy.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (clickstatus == true) {
					clickstatus = false;
					game.fbinterface.purchaseLife();

				}
			}
		});

		// back button

		ButtonStyle buttonStyle1 = new ButtonStyle();
		buttonStyle1.up = skin.getDrawable("back_hdpi");

		back = new Button(buttonStyle1);
		back.setWidth(back.getWidth());
		back.setX(Gdx.graphics.getWidth());
		back.setY(Gdx.graphics.getHeight() - back.getHeight()*1.5f);
		back.addAction(Actions.moveTo(1f,
				Gdx.graphics.getHeight() - back.getHeight()*1.5f, 3f));

		back.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new MenuScreen(game));
				dispose();
			}
		});

		stage.addActor(buy);
		stage.addActor(back);

	}

	@Override
	public void show() {

		if(Gdx.graphics.getWidth()>1020f)
		{

			atlas = new TextureAtlas("data/store.atlas");
			
			store = atlas.findRegion("storescreen_hdpi");
		}
		else {
			atlas = new TextureAtlas("data/ImagePackV2");
			levelscreenatlas = new TextureAtlas("data/levelscreen");
			
			store = levelscreenatlas.findRegion("storescreen_hdpi");
		}
		

		skin = new Skin();
		skin.addRegions(atlas);
		
		
		game.fbinterface.showbanner();
		
		
		
	}

	@Override
	public void hide() {
		game.fbinterface.destroybanner();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

		clickstatus = true;
	}

	@Override
	public void resume() {
		System.out.println("inside storescreen resume");

	}

	@Override
	public void dispose() {
		if(Gdx.graphics.getWidth()<1020f)
		{
			levelscreenatlas.dispose();
		}
		atlas.dispose();
		skin.dispose();
		batch.dispose();
		stage.dispose();
		buylifeatlas.dispose();
	}

}
