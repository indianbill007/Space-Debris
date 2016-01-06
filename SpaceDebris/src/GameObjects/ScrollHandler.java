package GameObjects;

import java.util.Random;

import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.gameworld.GameWorld;

public class ScrollHandler {

	// ScrollHandler will create all five objects that we need.
	private GameWorld gameWorld;
	private Debris debris0, debris1, debris2, debris3, debris4, points,
			superPower;

	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll and also determine
	// the size of the gap between each pair of pipes.

	// Capital letters are used by convention when naming constants.
	public static int SCROLL_SPEED = -59;

	public static int Debris_GAP = 49;
	public static int i = 0;

	// Constructor receives a float that tells us where we need to create our
	// Ground and debris objects.
	public ScrollHandler(GameWorld gameWorld, float yPos) {
		
	 if(Constantsvalue.selected_level >25) {
		SCROLL_SPEED = -67;
	     }
		else {
			SCROLL_SPEED = -59;
		}

		System.out.println("++++++++++++++++++++" + SCROLL_SPEED);
		this.gameWorld = gameWorld;

		debris0 = new Debris(210, 0, 22, 60, SCROLL_SPEED, yPos);
		debris1 = new Debris(debris0.getTailX() + Debris_GAP, 0, 22, 70,
				SCROLL_SPEED, yPos);
		debris2 = new Debris(debris1.getTailX() + Debris_GAP, 0, 22, 60,
				SCROLL_SPEED, yPos);
		debris3 = new Debris(debris2.getTailX() + Debris_GAP, 0, 22, 70,
				SCROLL_SPEED, yPos);
		debris4 = new Debris(debris3.getTailX() + Debris_GAP, 0, 22, 60,
				SCROLL_SPEED, yPos);
		points = new Debris(debris4.getTailX() + Debris_GAP, 60, 20, 20, -80,
				yPos);

		superPower = new Debris((points.getTailX() + Debris_GAP) * 2, 60, 20,
				20, -80, yPos);
		i = randInt(0, 5);
	}

	public void update(float delta) {

		if (Constantsvalue.selected_level > 1
				&& Constantsvalue.selected_level < 5) {
			switch (i) {
			case 0:
				debris0.update(delta);
				debris1.update(delta);
				debris2.animationUpdate(delta);
				debris4.update(delta);
				debris3.update(delta);
				break;
			case 1:
				debris0.update(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.animationUpdate(delta);
				break;

			case 2:
				debris0.update(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.animationUpdate(delta);
				debris4.update(delta);

				break;

			case 3:
				debris0.update(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.animationUpdate(delta);
				break;

			case 4:
				debris0.update(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.animationUpdate(delta);
				debris4.update(delta);

				break;

			default:
				debris0.update(delta);
				debris1.update(delta);
				debris2.animationUpdate(delta);
				debris3.update(delta);
				debris4.update(delta);
				break;
			}

		}

		else if (Constantsvalue.selected_level > 4
				&& Constantsvalue.selected_level < 9) {
			switch (i) {
			case 0:
				debris0.animationUpdate(delta);
				debris1.update(delta);
				debris2.animation2Update(delta);
				debris3.update(delta);
				debris4.update(delta);

				break;
			case 1:
				debris0.update(delta);
				debris1.update(delta);
				debris2.animationUpdate(delta);
				debris3.update(delta);

				debris4.animation2Update(delta);
				break;

			case 2:
				debris0.update(delta);
				debris1.animationUpdate(delta);
				debris2.update(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);

				break;

			case 3:
				debris0.update(delta);
				debris1.animationUpdate(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.animation2Update(delta);
				break;

			case 4:
				debris0.animationUpdate(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);

				break;

			default:
				debris0.animationUpdate(delta);
				debris1.update(delta);
				debris2.animation2Update(delta);
				debris3.update(delta);
				debris4.update(delta);
				break;
			}

		}

		else if (Constantsvalue.selected_level > 8
				&& Constantsvalue.selected_level < 14) {
			switch (i) {
			case 0:
				debris0.animationUpdate(delta);
				debris1.animationUpdate(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.update(delta);

				break;
			case 1:
				debris0.update(delta);
				debris1.update(delta);
				debris2.animationUpdate(delta);
				debris3.animationUpdate(delta);
				debris4.update(delta);

				break;

			case 2:
				debris0.update(delta);
				debris1.animationUpdate(delta);
				debris2.animationUpdate(delta);
				debris3.update(delta);
				debris4.update(delta);
				
				break;

			case 3:
				debris0.animationUpdate(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.update(delta);
				
				debris4.animationUpdate(delta);
				break;

			default:
				debris0.update(delta);
				
				debris1.update(delta);
				
				debris2.animationUpdate(delta);
				debris3.animationUpdate(delta);
				debris4.update(delta);
				break;
			}
		}

		else if (Constantsvalue.selected_level > 13
				&& Constantsvalue.selected_level < 15) {
			switch (i) {
			case 0:
				debris0.animationUpdate(delta);
				debris1.animation2Update(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.update(delta);
				break;
			case 1:
				debris0.update(delta);
				debris1.update(delta);
				
				debris2.animationUpdate(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);
				break;

			case 2:
				debris0.update(delta);
				debris1.animationUpdate(delta);
				debris2.animation2Update(delta);
                debris3.update(delta);
				debris4.update(delta);
				
				
				break;

			case 3:
				debris0.animationUpdate(delta);
				debris1.update(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.animation2Update(delta);
				break;

			default:
				debris0.update(delta);
				debris1.update(delta);
				debris2.animationUpdate(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);
				break;
			}
		}
		else if (Constantsvalue.selected_level > 14
				&& Constantsvalue.selected_level < 17) {
		
				debris0.animationUpdate(delta);
				debris1.animation2Update(delta);
				debris2.update(delta);
				debris3.update(delta);
				debris4.update(delta);
			
		}
		else if (Constantsvalue.selected_level > 16 && Constantsvalue.selected_level < 19) {
		
			
				debris0.update(delta);
				debris1.animationUpdate(delta);
				debris2.animation2Update(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);
			
		}
		
		

		else if (Constantsvalue.selected_level > 18
				&& Constantsvalue.selected_level < 29) {
			
			switch (i) {
			case 0:
				
				debris0.animation2Update(delta);
				debris1.update(delta);
				debris2.animationUpdate(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);
				break;
			
			default:

				debris0.animation2Update(delta);
				debris1.update(delta);
				debris2.animation2Update(delta);
				debris3.update(delta);
				debris4.animationUpdate(delta);
				break;
			}
		}

		else if (Constantsvalue.selected_level > 28
				&& Constantsvalue.selected_level < 35) {
			switch (i) {
			case 0:
				
				debris0.animationUpdate(delta);
				debris1.animationUpdate(delta);
				debris2.update(delta);
				debris3.animation2Update(delta);
				debris4.update(delta);
				break;
			default:

				debris0.animation2Update(delta);
				debris1.update(delta);
				debris2.animation2Update(delta);
				debris3.update(delta);
				debris4.animationUpdate(delta);
				break;
			}
		} 
		else if (Constantsvalue.selected_level >34
				&& Constantsvalue.selected_level < 43) {
			switch (i) {
			case 0:
				
				debris0.animation2Update(delta);
				debris1.animationUpdate(delta);
				debris2.animation2Update(delta);
				debris3.animationUpdate(delta);
				debris4.update(delta);
				break;
			default:

				debris0.animation2Update(delta);
				debris1.animation2Update(delta);
				debris2.animationUpdate(delta);
				debris3.animation2Update(delta);
				debris4.animationUpdate(delta);
				break;
			}
		}
		
		
		else {
			debris0.update(delta);
			debris1.update(delta);
			debris2.update(delta);
			debris3.update(delta);
			debris4.update(delta);

		}

		points.update(delta);

		superPower.update(delta);

		if (debris0.isScrolledLeft()) {
			debris0.reset(debris4.getTailX() + Debris_GAP);
		} else if (debris1.isScrolledLeft()) {
			debris1.reset(debris0.getTailX() + Debris_GAP);

		} else if (debris2.isScrolledLeft()) {

			debris2.reset(debris1.getTailX() + Debris_GAP);

		} else if (debris3.isScrolledLeft()) {
			debris3.reset(debris2.getTailX() + Debris_GAP);

		} else if (debris4.isScrolledLeft()) {
			debris4.reset(debris3.getTailX() + Debris_GAP);
		}

		else if (points.isScrolledLeft()) {
			int i = randInt(0, 5);

			switch (i) {
			case 0:
				points.onRestart(1500, -97);

				break;
			case 1:
				points.onRestart(1600, -89);
				break;

			case 2:
				points.onRestart(1600, -89);

				break;

			case 3:
				points.onRestart(2000, -87);
				break;

			case 4:
				points.onRestart(200, -125);
				break;

			default:
				points.onRestart(3000, -135);
				break;
			}
		}

		/*
		 * else if (superPower.isScrolledLeft()) { int i= randInt(0,5);
		 * 
		 * 
		 * 
		 * switch (i) { case 0:points.onRestart(900, -97);
		 * 
		 * break; case 1:points.onRestart(1200, -89); break;
		 * 
		 * case 2:points.onRestart(1900, -89);
		 * 
		 * break;
		 * 
		 * case 3:points.onRestart(1000, -87); break;
		 * 
		 * case 4:points.onRestart(400, -125); break;
		 * 
		 * default:points.onRestart(2000, -135); break; } }
		 */

	}

	public void stop() {
		debris0.stop();
		debris1.stop();
		debris2.stop();
		debris3.stop();
		debris4.stop();

	}

	public boolean collides(SpaceMan man) {
		if (!debris0.isScored()
				&& debris0.getX() + (debris0.getWidth() / 2) < man.getX()
						+ man.getWidth()) {
			addScore(1);
			debris0.setScored(true);
			if (AssetLoader.ismusicon()) {
				AssetLoader.coin.play();
			}
			

			System.out.println("inside score1");
		} else if (!debris1.isScored()
				&& debris1.getX() + (debris1.getWidth() / 2) < man.getX()
						+ man.getWidth()) {
			addScore(1);
			debris1.setScored(true);
			
			if (AssetLoader.ismusicon()) {
				AssetLoader.coin.play();
			}

		} else if (!debris2.isScored()
				&& debris2.getX() + (debris2.getWidth() / 2) < man.getX()
						+ man.getWidth()) {

			addScore(1);
			debris2.setScored(true);
			if (AssetLoader.ismusicon()) {
				AssetLoader.coin.play();
			}
		} else if (!debris3.isScored()
				&& debris3.getX() + (debris3.getWidth() / 2) < man.getX()
						+ man.getWidth()) {
			addScore(1);
			debris3.setScored(true);
			if (AssetLoader.ismusicon()) {
				AssetLoader.coin.play();
			}
		}

		else if (!debris4.isScored()
				&& debris4.getX() + (debris4.getWidth() / 2) < man.getX()
						+ man.getWidth()) {
			addScore(1);

			debris4.setScored(true);
			if (AssetLoader.ismusicon()) {
				AssetLoader.coin.play();
			}

		}

		return (debris0.collides(man) || debris1.collides(man)
				|| debris2.collides(man) || debris3.collides(man) || debris4
					.collides(man));
	}

	public boolean pointCollide(SpaceMan man) {

		return points.pointcollide(man);

	}

	public boolean SuperPowerCollide(SpaceMan man) {
		return superPower.superpowerCollide(man);
	}

	public void onRestart() {

		debris0.onRestart(210, SCROLL_SPEED);
		debris1.onRestart(debris0.getTailX() + Debris_GAP, SCROLL_SPEED);
		debris2.onRestart(debris1.getTailX() + Debris_GAP, SCROLL_SPEED);
		debris3.onRestart(debris2.getTailX() + Debris_GAP, SCROLL_SPEED);
		debris4.onRestart(debris3.getTailX() + Debris_GAP, SCROLL_SPEED);

	}

	// restart the extrapoint scroller
	public void onPointRestart() {

		int i = randInt(0, 5);

		switch (i) {
		case 0:
			points.onRestart(500, -91);

			break;
		case 1:
			points.onRestart(800, -97);
			break;

		case 2:
			points.onRestart(1000, -98);

			break;

		case 3:
			points.onRestart(2000, -87);
			break;

		case 4:
			points.onRestart(500, -120);

			break;

		default:
			points.onRestart(3000, -135);
			break;
		}
	}

	// restart the extrapoint scroller
	public void onSuperPowerRestart() {

		int i = randInt(0, 5);

		switch (i) {
		case 0:
			superPower.onRestart(900, -91);

			break;
		case 1:
			superPower.onRestart(500, -97);
			break;

		case 2:
			superPower.onRestart(700, -98);

			break;

		case 3:
			superPower.onRestart(1500, -87);
			break;

		case 4:
			superPower.onRestart(200, -120);

			break;

		default:
			superPower.onRestart(2000, -135);
			break;
		}
	}

	private void addScore(int increment) {
		gameWorld.addScore(increment);
	}

	public Debris getDebris0() {
		return debris0;
	}

	public Debris getDebris1() {
		return debris1;
	}

	public Debris getDebris2() {
		return debris2;
	}

	public Debris getDebris3() {

		return debris3;
	}

	public Debris getDebris4() {

		return debris4;
	}

	public Debris getPoints() {
		return points;
	}

	public Debris getsuperpowerPoints() {
		return superPower;
	}

	// generate random number for calling extra point in random time

	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}