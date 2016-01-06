package com.globussoft.gameworld;

import GameObjects.Constantsvalue;
import GameObjects.Debris;
import GameObjects.ScrollHandler;
import GameObjects.SpaceMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.globussoft.SDhelper.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;
	// Game Objects
	private SpaceMan man;
	private Debris debris0, debris1, debris2, debris3, debris4,points,superpowerpoint;
	private ScrollHandler scroller;
	// Game Assets
	private TextureRegion bg,bg1,bg2,bg3, spaceman, Debris0, Debris1, Debris2, Debris3,
			Debris4,retry,tap,extrapoints,superpower;
	// Tween stuff
   public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();
		
	}

	private void initGameObjects() {
		man = myWorld.getMan();
		scroller = myWorld.getScroller();
		debris0 = scroller.getDebris0();
		debris1 = scroller.getDebris1();
		debris2 = scroller.getDebris2();
		debris3 = scroller.getDebris3();
		debris4 = scroller.getDebris4();
        points=scroller.getPoints();
        superpowerpoint=scroller.getsuperpowerPoints();
	}

	private void initAssets() {
	
		if (Constantsvalue.selected_level>-1 && Constantsvalue.selected_level<5) {
			bg=AssetLoader.bg1;
		}
		else if (Constantsvalue.selected_level>4 && Constantsvalue.selected_level<13) {
			bg=AssetLoader.bg2;
		}
		else if (Constantsvalue.selected_level>15 && Constantsvalue.selected_level<19) {
			bg=AssetLoader.bg3;
		}
		else {
			bg = AssetLoader.bg;
		}
		
		spaceman = AssetLoader.man;
		Debris0 = AssetLoader.Debris0;
		Debris1 = AssetLoader.Debris1;
		Debris2 = AssetLoader.Debris2;
		Debris3 = AssetLoader.Debris3;
		Debris4 = AssetLoader.Debris4;
		
		retry=AssetLoader.retry;
		tap=AssetLoader.tap;
		
		extrapoints=AssetLoader.points;
		superpower=AssetLoader.superpower;
	}

	// adjust height manually here.

	private void drawDebris() {

		batcher.draw(extrapoints,points.getX() , points.getY(), points.getWidth(), points.getHeight());
		batcher.draw(superpower,superpowerpoint.getX() , superpowerpoint.getY(), superpowerpoint.getWidth(), superpowerpoint.getHeight());
		
		
		batcher.draw(Debris0, debris0.getX(), debris0.getY(),
				debris0.getWidth(), debris0.getHeight());
		
       
		batcher.draw(Debris0, debris0.getX(),
				debris0.getY() + debris0.getHeight() + 45, debris0.getWidth(),
				midPointY + 66 - (debris0.getHeight() + 5));

		batcher.draw(Debris1, debris1.getX(), debris1.getY(),
				debris1.getWidth(), debris1.getHeight());

		batcher.draw(Debris1, debris1.getX(),
				debris1.getY() + debris1.getHeight() + 45, debris1.getWidth(),
				midPointY + 66 - (debris1.getHeight() + 5));

		batcher.draw(Debris2, debris2.getX(), debris2.getY(),
				debris2.getWidth(), debris2.getHeight());
		batcher.draw(Debris2, debris2.getX(),
				debris2.getY() + debris2.getHeight() + 45, debris2.getWidth(),
				midPointY + 66 - (debris2.getHeight() + 5));

		batcher.draw(Debris3, debris3.getX(), debris3.getY(),
				debris3.getWidth(), debris3.getHeight());
		batcher.draw(Debris3, debris3.getX(),
				debris3.getY() + debris3.getHeight() + 45, debris3.getWidth(),
				midPointY + 66 - (debris3.getHeight() + 5));

		batcher.draw(Debris4, debris4.getX(), debris4.getY(),
				debris4.getWidth(), debris4.getHeight());
		batcher.draw(Debris4, debris4.getX(),
				debris4.getY() + debris4.getHeight() + 45, debris4.getWidth(),
				midPointY + 66 - (debris3.getHeight()));
		
		
		
		}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// shapeRenderer.begin(ShapeType.Filled);
		// shapeRenderer.end();
		batcher.begin();
		// drawDebris();
		batcher.disableBlending();
		batcher.draw(bg, 0, 0, 136, midPointY + 110);
		// enableblending will remove the black background
		batcher.enableBlending();
		batcher.enableBlending();
		drawDebris();

		
		
		
		if(Constantsvalue.issuperpower)
		{
			if (Constantsvalue.superpowertimer>3) {
				batcher.draw(AssetLoader.supermanAnimation1.getKeyFrame(runTime), man.getX(), man.getY(), 27,
						27);
			}
			else {
				batcher.draw(AssetLoader.supermanAnimation2.getKeyFrame(runTime), man.getX(), man.getY(), 26,
						26);
			}
			
		}
		else {
			batcher.draw(spaceman, man.getX(), man.getY(), man.getWidth(),
					man.getHeight());
		}

		
		
		
		if (myWorld.isReady()) {
          batcher.draw(tap, (136 / 2) - (42), 120, tap.getRegionWidth()/4, tap.getRegionWidth()/4);
			
			
		} else {

		       if (myWorld.isGameOver() || myWorld.isHighScore()) {
                 batcher.draw(retry, 68-retry.getRegionWidth()/8, midPointY+38, retry.getRegionWidth()/4, retry.getRegionHeight()/4);
	                String score = myWorld.getScore() + "";
	                // Draw shadow first
	                AssetLoader.shadow.draw(batcher, score,
	                        (136 / 2) - (3 * score.length()), 12);
	                // Draw text
	                AssetLoader.font.draw(batcher, score,
	                        (136 / 2) - (3 * score.length() - 1), 11);
      }
			// Convert integer into String
			String score = myWorld.getScore() + "";
			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
					-(3 * score.length()), 12);
			// Draw text
			AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
					- (3 * score.length() - 1), 11);
			
			
		String life=AssetLoader.getExtraLife()+"";
			AssetLoader.lifefont.draw(batcher, "life:"+life, 92, 10);

			
			String leveluptime=Constantsvalue.leveluptimer+"";
			
			//AssetLoader.lifefont.draw(batcher, "00 : "+leveluptime,(136 /3)+5, 27);
			AssetLoader.numberfont.draw(batcher, "00 : "+leveluptime,(136 /3)+5, 27);
		}
		
		
		
		batcher.end();

		
	}

	public void GameRenderdispose(){
	
		batcher.dispose();
		shapeRenderer.dispose();
	}
}
