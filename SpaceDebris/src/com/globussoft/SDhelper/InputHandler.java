package com.globussoft.SDhelper;

import GameObjects.Constantsvalue;
import GameObjects.SpaceMan;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.globussoft.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	private SpaceMan man;
	private GameWorld myWorld;
	
	
 public InputHandler(GameWorld myWorld){
	  man = myWorld.getMan();
	  this.myWorld =myWorld;
 }
 
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		//System.out.println("touch on background");
		if (myWorld.isReady()) {
			System.out.println("ready=======================READY");
			Constantsvalue.Animationflag1=false;
			Constantsvalue.Animationflag2=false;
			Constantsvalue.didseenvideo=true;
            myWorld.start();

        }
		
		else if(myWorld.isRunning()) {
			
			System.out.println("Running===============RUNNING");
		    if (Constantsvalue.runfirsttime) {
            	Constantsvalue.didseenvideo=true;
            	Timer.instance().clear();
            	Constantsvalue.runfirsttime=false;
            	 Constantsvalue.leveluptimer=30;
                 myWorld.levelUpTimer();
                
			}
            else {
            	Timer.instance().start();
			}
			
		}
		man.onClick();
		
	/*	if ((myWorld.isGameOver()|| myWorld.isHighScore())&& AssetLoader.getExtraLife()==0) {
            // Reset all variables, go to GameState.READ
		       myWorld.restart();
		       System.out.println("inside input handler gameover 1st....");
		       Constantsvalue.didseenvideo=true;
           
        }*/
		 if(myWorld.isGameOver())
		{
			System.out.println("inside input handler gameover=myWorld.isGameOver()|| myWorld.isHighScore(");
			//myWorld.resume();
			 myWorld.retry();
			 Constantsvalue.leveluptimer=30;
			 Constantsvalue.didseenvideo=true;
		}
    return true;
	}
	

   

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
