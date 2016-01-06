package com.globussoft.screen;

import GameObjects.Constantsvalue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.globussoft.gameworld.GameWorld;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class LevelClearScreen implements Screen {
	GameWorld world;
	private SpaceDebrisGame game;
	private SpriteBatch batch;

	TextureRegion background;
	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas, levelscreenatlas;
	Button nextbutton, back;

	public LevelClearScreen(GameWorld world, SpaceDebrisGame game) {

		game.fbinterface.loadAdmob();
		batch = null;
		skin = null;
		atlas = null;
		stage = null;
		levelscreenatlas = null;
		this.game = game;
		this.world = world;
		batch = new SpriteBatch();
		
		if ( Constantsvalue.isStoredData) {
			System.out.println("data stored interface called^^^^^^^^^^^^^^^^^^^^^^^^^^");
			Constantsvalue.isStoredData = false;
			game.fbinterface.storeDataInParse(world.getScore(),
					Constantsvalue.selected_level+1);
		}

		if (AssetLoader.getHighScore() == world.getScore()
				&& world.getScore() != 0) {

			System.out.println("sethighscore");
			game.fbinterface.sethighscoreStory(AssetLoader.getHighScore(),
					Constantsvalue.selected_level);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
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
		buttonStyle.up = skin.getDrawable("next");

		nextbutton = new Button(buttonStyle);
		nextbutton.setWidth(nextbutton.getWidth());
		nextbutton.setHeight(nextbutton.getHeight());
		nextbutton.setX(Gdx.graphics.getWidth() / 2f - nextbutton.getWidth()
				/ 2f);
		nextbutton.setY(10f);

		nextbutton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				game.showTopThreeRank(Constantsvalue.selected_level + 1,
						Constantsvalue.updatedscore);
				game.setScreen(new LevelScreen(game));

				game.fbinterface.displayInterstitial();
				dispose();
			}
		});
		ButtonStyle buttonStyleback = new ButtonStyle();
		buttonStyleback.up = skin.getDrawable("back");

		back = new Button(buttonStyleback);
		back.setWidth(back.getWidth());
		back.setHeight(back.getHeight());
		back.setX(Gdx.graphics.getWidth());
		back.setY(Gdx.graphics.getHeight() - back.getHeight() * 1.5f);
		back.addAction(Actions.moveTo(1f,
				Gdx.graphics.getHeight() - back.getHeight(), 3f));
		back.addListener(new InputListener() {

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

		stage.addActor(back);
		stage.addActor(nextbutton);

	}

	@Override
	public void show() {


		game.fbinterface.displayInterstitial();

		if (Gdx.graphics.getWidth() > 1020f) {
			atlas = new TextureAtlas("data/backnext.atlas");
		} else {
			atlas = new TextureAtlas("data/backnextbig.atlas");
		}

		Constantsvalue.updatedscore = world.getScore();
		skin = new Skin();
		skin.addRegions(atlas);
		levelscreenatlas = new TextureAtlas("data/levelscreen");
		background = levelscreenatlas.findRegion("wonderfull_hdpi");

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
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		stage.dispose();
		levelscreenatlas.dispose();

	}

}
