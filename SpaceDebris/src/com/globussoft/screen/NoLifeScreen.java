package com.globussoft.screen;

import java.util.concurrent.TimeUnit;

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
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.globussoft.SDhelper.AssetLoader;
import com.gobussoft.spacedebris.SpaceDebrisGame;

public class NoLifeScreen implements Screen {

	private SpaceDebrisGame game;
	private SpriteBatch batch;
	Texture wait;
	TextureRegion bg, background, timefornextlife, starY;
	private Stage stage;
	private Skin skin, newgameskin, refill_skin;
	private TextureAtlas atlas, newgameatlas, refilllife_atlas;
	Button nomorelifes, morelife, askfriends, back, newgame;
	/*
	 * private Array<Rectangle> stars; private long lastDropTime;
	 */

	long coundowntime = AssetLoader.getCountdownTime();
	long timeforlife;

	public NoLifeScreen(SpaceDebrisGame game) {

		morelife = null;
		nomorelifes = null;
		askfriends = null;
		newgame = null;
		back = null;
		batch = null;
		skin = null;
		atlas = null;
		stage = null;
		newgameatlas = null;
		refill_skin = null;
		refilllife_atlas = null;
		newgameskin = null;
		bg = null;
		background = null;
		timefornextlife = null;
		starY = null;
		// AssetLoader.loadExtraLifescreen();
		if (Gdx.graphics.getWidth() > 1020f) {
			wait = new Texture(Gdx.files.internal("data/dont_wait_xxhdpi.png"));
		} else {
			wait = new Texture(Gdx.files.internal("data/dont_wait.png"));
		}

		System.out.println("((((((((nolife screen)))))))))"
				+ AssetLoader.getExtraLife());
		this.game = game;
		batch = new SpriteBatch();
		bg = AssetLoader.bg;
		// stars = new Array<Rectangle>();
		// spawnStars();
		if (Constantsvalue.lifealive == true) {
			LifeBackTimer();
			System.out.println("inside constant value lifeOver");
			Constantsvalue.lifealive = false;
			Timer.instance().start();
		} else {
			// LifeBackTimer();
			Timer.instance().start();
		}

		if (Gdx.graphics.getWidth() > 1020f) {

			starY = AssetLoader.bigstar;
		}

		else {

			starY = AssetLoader.star;
		}

		game.fbinterface.loadAdmob();
	}

	private void spawnStars() {
		Rectangle star1 = new Rectangle();
		star1.x = MathUtils.random(0, Gdx.graphics.getWidth() - 64);
		star1.y = Gdx.graphics.getHeight();
		star1.width = 256;
		star1.height = 256;
		/*
		 * stars.add(star1); lastDropTime = TimeUtils.nanoTime();
		 */
		// System.out.println("stars1");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		stage.act(delta);
		/*
		 * batch.begin(); for(Rectangle star1: stars) { batch.draw(starY,
		 * star1.x, star1.y); } if(TimeUtils.nanoTime() - lastDropTime >
		 * 900000000) spawnStars(); // System.out.println("star1");
		 * Iterator<Rectangle> iter1 = stars.iterator(); while(iter1.hasNext())
		 * { Rectangle star1 = iter1.next(); star1.y -= 100 *
		 * Gdx.graphics.getDeltaTime(); if(star1.y + 32 < 0) iter1.remove(); }
		 * batch.end();
		 */

		batch.begin();
		stage.draw();
		batch.end();

		batch.begin();

		batch.draw(timefornextlife, Gdx.graphics.getWidth() / 2f
				- timefornextlife.getRegionWidth() / 2f,
				Gdx.graphics.getHeight() - timefornextlife.getRegionHeight()
						* 5, timefornextlife.getRegionWidth(),
				timefornextlife.getRegionHeight());

		AssetLoader.extralifefont.draw(batch, AssetLoader.getLifeBackTimer(),
				Gdx.graphics.getWidth() / 2 - timefornextlife.getRegionWidth()
						/ 5f,
				Gdx.graphics.getHeight() - timefornextlife.getRegionHeight()
						* 5.5f);

		batch.draw(wait, Gdx.graphics.getWidth() / 2f - wait.getWidth() / 2f,
				Gdx.graphics.getHeight() - wait.getHeight() * 5.5f,
				wait.getWidth(), wait.getHeight());

		batch.end();

		if (Constantsvalue.isfbshared == true) {
			Constantsvalue.isfbshared = false;
			game.setScreen(new LevelScreen(game));
			Timer.instance().clear();
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {

		if (Constantsvalue.islifeover == false) {
			timeforlife = System.currentTimeMillis()
					- AssetLoader.getSystemTime();
			coundowntime = coundowntime - timeforlife;
			if (coundowntime > 300000 || coundowntime <= 0) {
				coundowntime = 0;
			}
		}

		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		// nomore life
		Gdx.input.setInputProcessor(stage);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("nomorelifes_hdpi");

		System.out.println("screen width" + Gdx.graphics.getWidth());
		System.out.println("screen height" + Gdx.graphics.getHeight());

		nomorelifes = new Button(buttonStyle);
		nomorelifes.setWidth(nomorelifes.getWidth());
		nomorelifes.setHeight(nomorelifes.getHeight());
		nomorelifes.setX(Gdx.graphics.getWidth() / 2f - nomorelifes.getWidth()
				/ 2f);
		nomorelifes
				.setY(Gdx.graphics.getHeight() - nomorelifes.getHeight() * 2);

		nomorelifes.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// game.setScreen(new LevelScreen(game));
			}
		});

		// more life

		if (Gdx.graphics.getWidth() > 1020f) {
			ButtonStyle buttonStyle1 = new ButtonStyle();
			buttonStyle1.up = skin.getDrawable("morelives_hdpi");

			morelife = new Button(buttonStyle1);
			morelife.setWidth(morelife.getWidth());
			morelife.setHeight(morelife.getHeight());
			morelife.setX(Gdx.graphics.getWidth() / 2f - morelife.getWidth()
					/ 2f);
			morelife.setY(Gdx.graphics.getHeight() / 2 - morelife.getHeight()
					* 3.5f);

			morelife.addListener(new InputListener() {

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
			// ****************************************************
			ButtonStyle buttonStyle2 = new ButtonStyle();
			buttonStyle2.up = skin.getDrawable("new game");

			newgame = new Button(buttonStyle2);
			newgame.setWidth(newgame.getWidth());
			newgame.setHeight(newgame.getHeight());
			newgame.setX(Gdx.graphics.getWidth() - newgame.getWidth());
			newgame.setY(1f);

			newgame.addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					// game.setScreen(new StoreScreen(game));
					game.fbinterface.displayInterstitial();
				}
			});

			// ask friend
			ButtonStyle buttonStyle3 = new ButtonStyle();

			buttonStyle3.up = refill_skin.getDrawable("refill_life");
			askfriends = new Button(buttonStyle3);
			askfriends.setWidth(askfriends.getWidth() - askfriends.getWidth()
					/ 5f);
			askfriends.setHeight(askfriends.getHeight()
					- askfriends.getHeight() / 6f);
			askfriends.setX(Gdx.graphics.getWidth() / 2f
					- askfriends.getWidth() / 2f);
			askfriends.setY(Gdx.graphics.getHeight() / 2
					- askfriends.getHeight());
			/*
			 * askfriends.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f
			 * - askfriends.getWidth() / 2f, Gdx.graphics.getHeight() / 2 -
			 * askfriends.getHeight()*2, 2f));
			 */
			askfriends.addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					/*
					 * game.setScreen(new LevelScreen(game));
					 * AssetLoader.setExtraLife(5);
					 */

					game.fbinterface.asklife();
					Constantsvalue.isfromshare = true;
				}
			});

		}

		else {

			// ask friend
			ButtonStyle buttonStyle2 = new ButtonStyle();

			buttonStyle2.up = refill_skin.getDrawable("refill_life");
			askfriends = new Button(buttonStyle2);
			askfriends.setWidth(askfriends.getWidth());
			askfriends.setHeight(askfriends.getHeight());
			askfriends.setX(Gdx.graphics.getWidth() / 2f
					- askfriends.getWidth() / 2f);
			askfriends.setY(Gdx.graphics.getHeight() / 2
					- askfriends.getHeight() * 2);
			/*
			 * askfriends.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 2f
			 * - askfriends.getWidth() / 2f, Gdx.graphics.getHeight() / 2 -
			 * askfriends.getHeight()*2, 2f));
			 */
			askfriends.addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					/*
					 * game.setScreen(new LevelScreen(game));
					 * AssetLoader.setExtraLife(5);
					 */

					game.fbinterface.asklife();
					Constantsvalue.isfromshare = true;
				}
			});
			ButtonStyle buttonStyle1 = new ButtonStyle();
			buttonStyle1.up = skin.getDrawable("morelives_hdpi");

			morelife = new Button(buttonStyle1);
			morelife.setWidth(morelife.getWidth());
			morelife.setHeight(morelife.getHeight());
			morelife.setX(Gdx.graphics.getWidth() / 2f - morelife.getWidth()
					/ 2f);
			morelife.setY(Gdx.graphics.getHeight() / 2 - morelife.getHeight()
					* 3.5f);

			morelife.addListener(new InputListener() {

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

			ButtonStyle buttonStyle3 = new ButtonStyle();
			buttonStyle3.up = newgameskin.getDrawable("new game");

			newgame = new Button(buttonStyle3);
			newgame.setWidth(newgame.getWidth());
			newgame.setHeight(newgame.getHeight());
			newgame.setX(Gdx.graphics.getWidth() - newgame.getWidth());
			newgame.setY(1f);

			newgame.addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					game.fbinterface.displayInterstitial();
				}
			});
		}
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
				game.setScreen(new LevelScreen(game));
				dispose();
			}
		});

		stage.addActor(nomorelifes);
		stage.addActor(morelife);
		stage.addActor(askfriends);
		stage.addActor(back);
		stage.addActor(newgame);

	}

	@Override
	public void show() {

		// testing for big size images

		System.out.println("call interstial ad of no life screen");
		game.fbinterface.displayInterstitial();

		if (Gdx.graphics.getWidth() > 1020f) {
			atlas = new TextureAtlas("data/nomorelife.atlas");
			timefornextlife = atlas.findRegion("nextlifeText");

			refilllife_atlas = new TextureAtlas(
					"data/refilllifeatlas_big.atlas");
		}

		else {
			atlas = new TextureAtlas("data/ImagePackV2");
			newgameatlas = new TextureAtlas("data/newgame.atlas");
			newgameskin = new Skin();
			newgameskin.addRegions(newgameatlas);
			timefornextlife = newgameatlas.findRegion("nextlifeText");
			refilllife_atlas = new TextureAtlas("data/refilllife.atlas");

		}

		refill_skin = new Skin();
		refill_skin.addRegions(refilllife_atlas);
		skin = new Skin();
		skin.addRegions(atlas);
		game.fbinterface.showbanner();

	}

	@Override
	public void hide() {
		System.out.println("HIDE -Nolife screen");
		Constantsvalue.islifeover = false;
		game.fbinterface.destroybanner();
		// Timer.instance().clear();
		AssetLoader.setSystemTime(System.currentTimeMillis());

	}

	@Override
	public void pause() {
		Constantsvalue.islifeover = false;

		AssetLoader.setSystemTime(System.currentTimeMillis());

		Timer.instance().stop();
		System.out.println("inside on pause of life over..");
	}

	@Override
	public void resume() {

		Timer.instance().start();
		AssetLoader.setSystemTime(System.currentTimeMillis());
	}

	@Override
	public void dispose() {

		batch.dispose();
		// AssetLoader.disposeExtraLifescreen();
		skin.dispose();

		atlas.dispose();

		stage.dispose();

		refill_skin.dispose();

		refilllife_atlas.dispose();

		if (Gdx.graphics.getWidth() < 1020f) {
			newgameatlas.dispose();
			System.out.println("nolife dispose7");
			newgameskin.dispose();
		}

		// Constantsvalue.islifeover = false;

		// Timer.instance().clear();

	}

	public void LifeBackTimer() {

		Timer.schedule(new Task() {

			@Override
			public void run() {

				if (coundowntime > 300000 || coundowntime <= 0) {
					coundowntime = 0;
				}
				String hms = String.format(
						"%02d:%02d:%02d",
						TimeUnit.MILLISECONDS.toHours(coundowntime),
						TimeUnit.MILLISECONDS.toMinutes(coundowntime)
								- TimeUnit.HOURS
										.toMinutes(TimeUnit.MILLISECONDS
												.toHours(coundowntime)),
						TimeUnit.MILLISECONDS.toSeconds(coundowntime)
								- TimeUnit.MINUTES
										.toSeconds(TimeUnit.MILLISECONDS
												.toMinutes(coundowntime)));

				if (coundowntime != 0) {
					coundowntime = coundowntime - 1000;
					AssetLoader.setCountdownTime(coundowntime);
					AssetLoader.setLifeBackTimer(hms);
				}

				else if (coundowntime == 0 && AssetLoader.getExtraLife() < 10) {
					Timer.instance().clear();
					AssetLoader.setLifeBackTimer(hms);
					AssetLoader.setExtraLife(AssetLoader.getExtraLife() + 1);
					AssetLoader.setCountdownTime(300000);
					coundowntime = AssetLoader.getCountdownTime();
					LifeBackTimer();
				}

				else {
					game.setScreen(new LevelScreen(game));
					dispose();
					Timer.instance().clear();
				}

			}
		}, 0, 1);
	}
}
