package GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.globussoft.gameworld.GameWorld;

public class Debris extends Scrollable {

	
	
	private Random r;
	private Rectangle debrisUp, debrisDown;
	private Circle pointCercle,superpowerCercle;

	public static int VERTICAL_GAP = 45;
	
	private boolean isScored = false;
	public Debris(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		debrisUp = new Rectangle();
		debrisDown = new Rectangle();
		pointCercle=new Circle();
		superpowerCercle=new Circle();
        
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);
        // The set() method allows you to set the top left corner's x, y
		// coordinates,
		// along with the width and height of the rectangle
	debrisUp.set(position.x, position.y, width, height);
		debrisDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				height+50);
		//i added 50 with height
		pointCercle.set(position.x+10, position.y+15, 12f);
		superpowerCercle.set(position.x+10, position.y+15, 12f);
	}
	
	@Override
	public void animationUpdate(float delta)
	{
		super.animationUpdate(delta);
		debrisUp.set(position.x, position.y, width, height);
		debrisDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				height+50);
	}
	
	@Override
	public void animation2Update(float delta)
	{
		super.animation2Update(delta);
		debrisUp.set(position.x, position.y, width, height);
		debrisDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				height+50);
	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		isScored = false;
	
	}
	
	
	
	//colision detection main logic
	
	public boolean collides(SpaceMan man) {
        if (position.x < man.getX() + man.getWidth()) {
            return (Intersector.overlaps(man.getBoundingCircle(), debrisUp)
                    || Intersector.overlaps(man.getBoundingCircle(), debrisDown));
        }
        return false;
    }
	
	//colission detection for extra point
	public boolean pointcollide(SpaceMan man){
	   if(position.x < man.getX() + man.getWidth()) {
	   
		   return Intersector.overlaps(man.getBoundingCircle(), pointCercle);
	   }
	return false;
	}
	
	//collision dettecttion for super power
	
	public boolean superpowerCollide(SpaceMan man)
	{
		if(position.x < man.getX() + man.getWidth()) {
			   
			   return Intersector.overlaps(man.getBoundingCircle(), pointCercle);
		   }
		return false;
	}

	/**
	 * Intersector.overlaps() calls (which returns true if the circle argument object collides with a
	 *  rectangle argument object)
	 *
	 * 
	 */
	public void onRestart(float x, float scrollSpeed) {
	    velocity.x = scrollSpeed;
	    reset(x);

	}
	
	public boolean isScored() {
	    return isScored;
	}

	public void setScored(boolean b) {
	    isScored = b;
	}
	public Rectangle getDebrisUp() {
		return debrisUp;
	}

	public void setDebrisUp(Rectangle debrisUp) {
		this.debrisUp = debrisUp;
	}

	public Rectangle getDebrisDown() {
		return debrisDown;
	}

	public void setDebrisDown(Rectangle debrisDown) {
		this.debrisDown = debrisDown;
	}
	
	public Circle getPointCercle(){
		return pointCercle;
		
	}
	public Circle getsuperpowerCercle(){
		return superpowerCercle;
		
	}
	
}
