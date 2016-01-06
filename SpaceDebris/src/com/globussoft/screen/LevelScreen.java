package com.globussoft.screen;

import java.util.Iterator;

import GameObjects.Constantsvalue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.globussoft.SDhelper.AssetLoader;

import com.gobussoft.spacedebris.SpaceDebrisGame;

public class LevelScreen implements Screen {

	public SpaceDebrisGame game;

	private static Stage stage;
	private SpriteBatch batch;
	TextureRegion bg, levelbackground, timefornextlife, star;
	private TextureAtlas atlas, newgameatlas, musicatlas;
	private Skin skin, musicskin;

	TextButtonStyle buttonStyle;
	TextButton btn;
	Button back, musicon, musicoff;
	int w, h;
	static boolean firstrun = false;
	private Array<Rectangle> stars;
	private long lastDropTime;
	Table container;

	public LevelScreen(final SpaceDebrisGame game) {

		System.out.println("level screen called");

		batch = null;
		stage = null;
		atlas = null;
		skin = null;
		newgameatlas = null;

		System.out.println("<<<level screen life>>>"
				+ AssetLoader.getExtraLife());
		this.game = game;

		// levelbg=new Texture(Gdx.files.internal("data/levelbg.png"));
		Texture.setEnforcePotImages(false);
		batch = new SpriteBatch();
		bg = AssetLoader.bg;
		stars = new Array<Rectangle>();
		spawnStars();
		if (Gdx.graphics.getWidth() > 1020f) {
			levelbackground = new TextureRegion(new Texture("data/levelbg.png"));
			star = AssetLoader.bigstar;
		}

		else if (Gdx.graphics.getWidth() > 710f) {
			levelbackground = new TextureRegion(new Texture(
					"data/level_bg_xhdpi.png"));
			star = AssetLoader.star;
		}

		else {
			levelbackground = AssetLoader.levelBackground;
			star = AssetLoader.star;
		}

		if (AssetLoader.isRunFirstTime() == false) {
			AssetLoader.setExtraLife(10);
			AssetLoader.setIsRunFirst(true);
		}
		Constantsvalue.leveluptimer = 30;

	}

	private void spawnStars() {
		Rectangle star1 = new Rectangle();
		star1.x = MathUtils.random(0, Gdx.graphics.getWidth() - 64);
		star1.y = Gdx.graphics.getHeight();
		star1.width = 256;
		star1.height = 256;
		stars.add(star1);
		lastDropTime = TimeUtils.nanoTime();
		// System.out.println("stars1");
	}

	@Override
	public void resize(int width, int height) {
		w = width;
		h = height;
		firstrun = true;

		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();

		System.out.println("i am in stage == null");
		Gdx.input.setInputProcessor(stage);

		// back button

		ButtonStyle buttonStyleback = new ButtonStyle();
		buttonStyleback.up = skin.getDrawable("back_hdpi");
		back = new Button(buttonStyleback);
		back.setWidth(back.getWidth());
		back.setHeight(back.getHeight());
		back.setX(Gdx.graphics.getWidth());
		back.setY(Gdx.graphics.getHeight() - back.getHeight() * 1.5f);
		back.addAction(Actions.moveTo(1f,
				Gdx.graphics.getHeight() - back.getHeight() * 1.5f, 3f));
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

		ButtonStyle buttonStyle2 = new ButtonStyle();
		buttonStyle2.up = musicskin.getDrawable("music on");

		musicon = new Button(buttonStyle2);
		musicon.setWidth(musicon.getWidth());
		musicon.setHeight(musicon.getHeight());
		musicon.setX(Gdx.graphics.getWidth() - musicon.getWidth() - 5f);
		musicon.setY(Gdx.graphics.getHeight()-musicon.getWidth()*1.5f);

		musicon.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				AssetLoader.setmusicon(false);
			}
		});

		ButtonStyle buttonStyle3 = new ButtonStyle();
		buttonStyle3.up = musicskin.getDrawable("music off");

		musicoff = new Button(buttonStyle3);
		musicoff.setWidth(musicoff.getWidth());
		musicoff.setHeight(musicoff.getHeight());
		musicoff.setX(Gdx.graphics.getWidth() - musicoff.getWidth() - 5f);
		musicoff.setY(Gdx.graphics.getHeight()-musicon.getWidth()* 1.5f);

		musicoff.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				AssetLoader.setmusicon(true);
			}
		});

		Table grid = new Table();

		ScrollPane scroll = new ScrollPane(grid);

		if (Gdx.graphics.getWidth() > 1020f)

		{
			grid.top().left();
			grid.center();
			grid.setPosition(50, Gdx.graphics.getHeight() / 2);
			grid.top().left().pad(10, 10, 10, 10);
			for (int i = 0; i < 40; i++) {
				final int index = i;

				buttonStyle = new TextButtonStyle();
				buttonStyle.up = skin.getDrawable("currentlevel_hdpi");
				buttonStyle.font = AssetLoader.levelbigfont;

				btn = new TextButton("" + (i + 1), buttonStyle);

				if (i > AssetLoader.getLevel() - 1) {
					// btn.setVisible(false);
					buttonStyle.up = skin.getDrawable("locklevel_hdpi");
					buttonStyle.font = AssetLoader.levelfont;
					btn = new TextButton("", buttonStyle);

				}
				if (i % 4 == 0)
					grid.row();
				grid.add(btn).size(180, 180).pad(5, 0, 0, 0).expandX();

				btn.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);

						if (index < AssetLoader.getLevel()) {
							Constantsvalue.selected_level = index;

							if (AssetLoader.getExtraLife() > 0) {
								if (Constantsvalue.isconnecttofb == true) {
									game.showTopThreeScoreDialog(index + 1);
								} else {

									game.setScreen(new GameScreen(game));

									Constantsvalue.leveluptimer = 30;
									dispose();
								}

							} else {

								game.setScreen(new NoLifeScreen(game));
								dispose();
							}
						}

					}
				});
			}
			container = new Table();
			container.setFillParent(true);
			container.row().expand();
			container.add(scroll).height(Gdx.graphics.getHeight() / 2 - 150);
			stage.addActor(container);
		} else {
			grid.setSize(70, 50);
			grid.top().left();
			grid.center();
			grid.setPosition(50, Gdx.graphics.getHeight() / 2);
			grid.top().left().pad(10, 10, 10, 10);
			for (int i = 0; i < 40; i++) {
				final int index = i;

				buttonStyle = new TextButtonStyle();
				buttonStyle.up = skin.getDrawable("currentlevel_hdpi");
				buttonStyle.font = AssetLoader.levelfont;

				btn = new TextButton("" + (i + 1), buttonStyle);

				if (i >= AssetLoader.getLevel()) {
					// btn.setVisible(false);
					buttonStyle.up = skin.getDrawable("locklevel_hdpi");
					buttonStyle.font = AssetLoader.levelfont;
					btn = new TextButton("", buttonStyle);

				}
				if (i % 4 == 0)
					grid.row();
				grid.add(btn).size(100, 100).pad(5, 0, 0, 0).expandX();

				btn.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						System.out.println("Index" + index);
						if (index < AssetLoader.getLevel()) {
							Constantsvalue.selected_level = index;

							if (AssetLoader.getExtraLife() > 0) {
								if (Constantsvalue.isconnecttofb == true) {
									game.showTopThreeScoreDialog(index + 1);

								} else {

									game.setScreen(new GameScreen(game));
									Constantsvalue.leveluptimer = 30;
									dispose();
								}

							} else {

								game.setScreen(new NoLifeScreen(game));
								dispose();
							}
						}

					}
				});
			}

			Table container = new Table();

			container.setFillParent(true);

			container.row().expand();
			container.add(scroll).height(Gdx.graphics.getHeight() / 2 - 60);
			stage.addActor(container);
		}

		stage.addActor(back);

		stage.addActor(musicon);
		stage.addActor(musicoff);
	}

	@Override
	public void render(float delta) {

		if (AssetLoader.ismusicon() == true) {
			musicoff.setVisible(false);
			musicon.setVisible(true);
		} else {
			musicoff.setVisible(true);
			musicon.setVisible(false);
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(bg, 1, 1, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();

		batch.begin();
		for (Rectangle star1 : stars) {

			batch.draw(star, star1.x, star1.y);
		}
		if (TimeUtils.nanoTime() - lastDropTime > 90000000)
			spawnStars();
		// System.out.println("star1");
		Iterator<Rectangle> iter1 = stars.iterator();
		while (iter1.hasNext()) {
			Rectangle star1 = iter1.next();
			star1.y -= 100 * Gdx.graphics.getDeltaTime();
			if (star1.y + 32 < 0)
				iter1.remove();
		}
		batch.end();

		batch.begin();
		batch.draw(
				levelbackground,
				Gdx.graphics.getWidth() / 2 - levelbackground.getRegionWidth()
						/ 2,
				Gdx.graphics.getHeight() / 2
						- levelbackground.getRegionHeight() / 2,
				levelbackground.getRegionWidth(),
				levelbackground.getRegionHeight());
		batch.end();
		try {
			stage.act(delta);
			batch.begin();
			stage.draw();
			batch.end();
		} catch (Exception e) {
			System.out.println("excepption ocurred------" + e);
		}
	}

	@Override
	public void show() {

		if (Gdx.graphics.getWidth() > 1020f) {
			atlas = new TextureAtlas("data/levelscreenbig.atlas");
			musicatlas = new TextureAtlas("data/musicbuttonbig.atlas");
		} else {
			atlas = new TextureAtlas("data/ImagePackV2");
			musicatlas = new TextureAtlas("data/musicbutton.atlas");
		}
		musicskin = new Skin();
		musicskin.addRegions(musicatlas);
		skin = new Skin();
		skin.addRegions(atlas);
		game.fbinterface.showStatus();
		game.fbinterface.synclife();
	}

	@Override
	public void hide() {
		System.out.println("inside hide of hide screen");

	}

	@Override
	public void pause() {
		System.out.println("inside hide of pause screen");
		// dispose();
	}

	@Override
	public void resume() {
		System.out.println("inside hide of resume screen");

	}

	@Override
	public void dispose() {
		musicatlas.dispose();
		musicskin.dispose();
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		batch.dispose();

	}

}
