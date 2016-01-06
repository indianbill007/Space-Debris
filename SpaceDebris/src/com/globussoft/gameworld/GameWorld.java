package com.globussoft.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.screen.LevelScreen;
import com.gobussoft.spacedebris.SpaceDebrisGame;

import GameObjects.Constantsvalue;
import GameObjects.ScrollHandler;
import GameObjects.SpaceMan;

public class GameWorld {
    private SpaceMan man;
	private ScrollHandler scroller;
	private boolean isAlive = true;
	private Rectangle ground;
	public static boolean test=true;
	public int score = 0;
	private GameState currentState;
	 private int midPointY;
	 private  boolean isScored = false;
	 public static boolean ismandie=false; 
	 
	 
	public enum GameState {
	    READY, RUNNING,LIFEOVER, GAMEOVER,HIGHSCORE
	}
	public GameWorld(int midPointY) {
		this.midPointY=midPointY;
		
		man = new SpaceMan(33, midPointY - 5, 18, 15);
		scroller = new ScrollHandler(this,midPointY + 66);
		ground = new Rectangle(0, midPointY + 115, 136, 11);
		
		currentState = GameState.READY;
	}
	
	public GameWorld(){
		
	}
	
	public void update(float delta) {
		
		switch (currentState) {
        case READY:
            updateReady(delta);
            Constantsvalue.didseenvideo=false;
            test=true;
            Constantsvalue.issuperpower=false;
            break;
        case RUNNING:
        	
        	Timer.instance().start();
        	updateRunning(delta);
        	Constantsvalue.didseenvideo=false;
        	//test=true;
        	break;
        default:
        	test=true;
        	updateGameover(delta);  
        	break;
        }
		
		if(Constantsvalue.isclickoncancel==true)
		{
			test=true;
			//Timer.instance().clear();
			
		}
    }
	
	private void updateReady(float delta) {
        // Do nothing for now
    }
	
	private void updateGameover(float delta)
	{
		try {
			if (AssetLoader.ismusicon()==true) {
				AssetLoader.playing_sound.play();
				AssetLoader.playing_sound.setLooping(true);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(Constantsvalue.chatboosterdetail==2)
		{
			
			if(Constantsvalue.didseenvideo)
			{
				Constantsvalue.didseenvideo=false;
				Constantsvalue.chatboosterdetail=0;
				test=true;
				
				AssetLoader.setExtraLife(AssetLoader.getExtraLife()+1);
				restart();
				
			}else
			{
				
				test=true;
				Constantsvalue.didseenvideo=false;
				Constantsvalue.chatboosterdetail=0;
			/*	Timer.instance().clear();*/
				if (AssetLoader.getExtraLife()<1) {
					
					Constantsvalue.islfezero=true;
	
				}
				currentState = GameState.GAMEOVER;
				
				
			}
		}
	}
	
	
	
	
	public void updateRunning(float delta) 
	{
		man.update(delta);
		scroller.update(delta);
		if(scroller.collides(man) && man.isAlive())
		{
			if(test)
			{   
				if (Constantsvalue.leveluptimer>0) {
				test=false;
				
				/*int life=AssetLoader.getExtraLife();
				AssetLoader.setExtraLife(life-1);*/
				Timer.instance().stop();
				scroller.stop();
				
				man.die();
				if (AssetLoader.ismusicon()==true) {
					AssetLoader.dead.play();
				}
				
			}
			}
		}
		if(scroller.pointCollide(man)&& !isScored())
		{  
			
			addScore(10);
			setScored(true);
			scroller.onPointRestart();
			
            setScored(false);
			
		}
		
		if (scroller.SuperPowerCollide(man)) {
			
			Constantsvalue.superpowertimer=10;
			test=false;
			Constantsvalue.issuperpower=true;
			if (AssetLoader.ismusicon()==true) {
				AssetLoader.power.play();
			}
			
		}
	
		
		
		if (Intersector.overlaps(man.getBoundingCircle(), ground)) {
			int life=AssetLoader.getExtraLife();
			if (life>0) {
				AssetLoader.setExtraLife(life-1);
			}
			
			Constantsvalue.ishitdebris=true;
		  scroller.stop();
            man.die();
            man.decelerate();
           currentState = GameState.GAMEOVER;
           if (score > AssetLoader.getHighScore()) {
               AssetLoader.setHighScore(score);
               currentState = GameState.HIGHSCORE;
   }
           
		}
		}
	public SpaceMan getMan() {
		return man;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}
	
	/**
	 * @return the ground
	 */
	public Rectangle getGround() {
		return ground;
	}

	/**
	 * @param ground the ground to set
	 */
	public void setGround(Rectangle ground) {
		this.ground = ground;
	}
	
	public int getScore() {
	    return score;
	}

	public void addScore(int increment) {
	    score += increment;
	    
	}
	
	 public void start() {
	        currentState = GameState.RUNNING;
	    }
	 
	 public boolean isReady() {
	        return currentState == GameState.READY;
	    }
	 public boolean isRunning() {
	        return currentState == GameState.RUNNING;
	    }
	 public boolean isGameOver() {
	        return currentState == GameState.GAMEOVER;
	    }
	 
	 public boolean isHighScore() {
		    return currentState == GameState.HIGHSCORE;
		}

		public  void setScored(boolean b) {
		    isScored = b;
		}
		public boolean isScored() {
		    return isScored;
		}
	 public void restart() {
		  System.out.println("i am in restart method");
	        currentState = GameState.READY;
	        man.onRestart(midPointY - 5);
	        scroller.onRestart();
	        test=true;
	        
	    }
	 
	 public void retry() {
		    System.out.println("i am in retry method");
	        currentState = GameState.READY;
	        man.onRestart(midPointY - 5);
	        scroller.onRestart();
	        //Timer.instance().start();
	        score=0;
	        test=true;
	    }
 
	 public void resume(){
		 System.out.println("i am in resume game over");
		 currentState = GameState.READY;
		 man.onRestart(midPointY - 5);
		 scroller.onRestart();
		 score = 0;
		 test=true;
	 }
	 
	 
	 public void levelUpTimer(){
			
			Timer.schedule(new Task() {
				
				@Override
				public void run() {
					System.out.println("time start");
					if(Constantsvalue.leveluptimer>-1)
					{
						
						Constantsvalue.leveluptimer=Constantsvalue.leveluptimer-1;
						
						if(Constantsvalue.issuperpower)
						{
							Constantsvalue.superpowertimer=Constantsvalue.superpowertimer-1;
							if(Constantsvalue.superpowertimer<1)
							{
								Constantsvalue.issuperpower=false;
								test=true;
							}
							
						}
					}
				}
			}, 0, 1);
			}
	 

}
