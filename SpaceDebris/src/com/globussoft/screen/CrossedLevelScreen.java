package com.globussoft.screen;

import GameObjects.Constantsvalue;

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.gameworld.GameWorld;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class CrossedLevelScreen implements Screen{
	private SpaceDebrisGame game;
	private SpriteBatch batch;
	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas1;
	private TextureRegion levelBoard;
	private TextureAtlas atlas2;
	Button next, fbshare;
	GameWorld world;
	public CrossedLevelScreen(SpaceDebrisGame game) {
		
		this.game=game;
		batch = new SpriteBatch();
		
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
		batch.draw(levelBoard, Gdx.graphics.getWidth()/2-levelBoard.getRegionWidth()/2, Gdx.graphics.getHeight()/2-levelBoard.getRegionHeight()/2,
				levelBoard.getRegionWidth(), levelBoard.getRegionHeight());
		
		batch.end();
		
		batch.begin();
		stage.draw();
		batch.end();
		
		
		
		batch.begin();
		int prelevel=Constantsvalue.selected_level+1;
		String postlevel=prelevel+"";
		
		String score=Constantsvalue.updatedscore+"";
		
		AssetLoader.blackfont.draw(batch, "Cleared Level - "+postlevel,
				Gdx.graphics.getWidth() / 3.5f, Gdx.graphics.getHeight() / 2+levelBoard.getRegionHeight()/3);
		AssetLoader.blackfont.draw(batch, "Score - "+score,
				Gdx.graphics.getWidth() / 2 - 30, Gdx.graphics.getHeight() / 2+levelBoard.getRegionHeight()/6);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		// nomore life
		Gdx.input.setInputProcessor(stage);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("nex_hdpi");

		next = new Button(buttonStyle);
		next.setWidth(next.getWidth());
		next.setHeight(next.getHeight());
		next.setX(Gdx.graphics.getWidth() / 2f - next.getWidth()
				/ 2f);
		next.setY(next.getHeight()*1.5f);

		next.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new LevelScreen(game));
			}
		});

		// more life

		ButtonStyle buttonStyle1 = new ButtonStyle();
		buttonStyle1.up = skin.getDrawable("share_hdpi");

		fbshare = new Button(buttonStyle1);
		fbshare.setWidth(fbshare.getWidth());
		fbshare.setHeight(fbshare.getHeight());
		fbshare.setX(0f);
		fbshare.setY(Gdx.graphics.getHeight() - fbshare.getHeight() * 4);
		fbshare.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f - fbshare.getWidth() / 2f,Gdx.graphics.getHeight()-fbshare.getHeight()*2, 2f));
		fbshare.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				int prelevel=Constantsvalue.selected_level+1;
				game.fbinterface.shareclearedlevel(Constantsvalue.updatedscore, prelevel);
				
			}
		});
		
		
		stage.addActor(next);
		stage.addActor(fbshare);
	}

	@Override
	public void show() {
		atlas1=new TextureAtlas("data/levelscreen");
		levelBoard=atlas1.findRegion("beatfriend_score_hdpi");
		
		atlas2=new TextureAtlas("data/ImagePackV2");
		skin = new Skin();
		skin.addRegions(atlas2);
		
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
		atlas1.dispose();
		atlas2.dispose();
		stage.dispose();
		
	}

}
