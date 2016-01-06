package com.gobussoft.spacedebris;

import GameObjects.Constantsvalue;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.interfaces.LifeInterface;
import com.globussoft.interfaces.RankBackInterface;
import com.globussoft.interfaces.RankInterface;
import com.globussoft.interfaces.TopScoreReturnBack;
import com.globussoft.interfaces.TopThreeScoreDialog;
import com.globussoft.interfaces.facebookinterface;
import com.globussoft.screen.GameScreen;
import com.globussoft.screen.SplashScreen;

public class SpaceDebrisGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public facebookinterface fbinterface;

	public LifeInterface lifeInterface;
	public TopThreeScoreDialog topThreeScoreDialog;
	public RankInterface rankInterface;

	private long timeforlife;

	@Override
	public void create() {
		System.out.println("spacedebris start");
		AssetLoader.load();
		System.out.println("above time Total Life)))))))))))))))"
				+ AssetLoader.getExtraLife());
		int lifecount=AssetLoader.getExtraLife();
		timeforlife = System.currentTimeMillis()
				- AssetLoader.getSystemTime();
		if (lifecount <= 0) {

			
			if (timeforlife >= 3000000) {
				AssetLoader.setExtraLife(10);
			} else if (timeforlife >= 2700000) {
				AssetLoader.setExtraLife(9);
			} else if (timeforlife >= 2400000) {
				AssetLoader.setExtraLife(8);
			} else if (timeforlife >= 2100000) {
				AssetLoader.setExtraLife(7);
			} else if (timeforlife >= 1800000) {
				AssetLoader.setExtraLife(6);
			}

			else if (timeforlife >= 1500000) {
				
				AssetLoader.setExtraLife(5);
			} else if (timeforlife >= 1200000) {
				AssetLoader.setExtraLife(4);
			} else if (timeforlife >= 900000) {
				AssetLoader.setExtraLife(3);
			} else if (timeforlife >= 600000) {
				AssetLoader.setExtraLife(2);
			} else if (timeforlife >= 300000) {
				AssetLoader.setExtraLife(1);
			}
		}
		else if(lifecount <10){
			
			if (timeforlife >= 3000000) {
				AssetLoader.setExtraLife(10);
			} else if (timeforlife >= 2700000) {
				AssetLoader.setExtraLife(lifecount+9);
			} else if (timeforlife >= 2400000) {
				AssetLoader.setExtraLife(lifecount+8);
			} else if (timeforlife >= 2100000) {
				AssetLoader.setExtraLife(lifecount+7);
			} else if (timeforlife >= 1800000) {
				AssetLoader.setExtraLife(lifecount+6);
			}

			else if (timeforlife >= 1500000) {
				
				AssetLoader.setExtraLife(lifecount+5);
			} else if (timeforlife >= 1200000) {
				AssetLoader.setExtraLife(lifecount+4);
			} else if (timeforlife >= 900000) {
				AssetLoader.setExtraLife(lifecount+3);
			} else if (timeforlife >= 600000) {
				AssetLoader.setExtraLife(lifecount+2);
			} else if (timeforlife >= 300000) {
				AssetLoader.setExtraLife(lifecount+1);
			}
			
			if (AssetLoader.getExtraLife()>10) {
				AssetLoader.setExtraLife(10);
			}
		}
		setScreen(new SplashScreen(this));

		System.out.println("((((((((((((((((Total Life)))))))))))))))"
				+ AssetLoader.getExtraLife());
	}

	public SpaceDebrisGame(facebookinterface fbinterface,
			LifeInterface lifeInterface,
			TopThreeScoreDialog topThreeScoreDialog,
			RankInterface rankInterface) {
		this.topThreeScoreDialog = topThreeScoreDialog;
		this.fbinterface = fbinterface;

		this.lifeInterface = lifeInterface;
		this.rankInterface = rankInterface;
		
		

	}

	public SpaceDebrisGame() {
	}

	public void callAd() {
		this.fbinterface.displayInterstitial();
	}



	public void showTopThreeRank(final int level, int score) {
		rankInterface.RankExecution(level, score, new RankBackInterface() {

			@Override
			public void clickplay() {
			}
		});
	}

	// top3score dialog
	public void showTopThreeScoreDialog(int level) {

		topThreeScoreDialog.topThreeScore(level, new TopScoreReturnBack() {

			@Override
			public void playclick() {

				Constantsvalue.leveluptimer = 30;
				setScreen(new GameScreen(SpaceDebrisGame.this));

			}
		});
	}


	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
		System.out.println("main dispose");
	}
}
