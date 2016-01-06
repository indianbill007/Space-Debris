package GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.globussoft.SDhelper.AssetLoader;

public class SpaceMan {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	private float rotation; // For handling man rotation
	private int width;
	private int height;

	// collision detection
	private Circle boundingCircle;

	private Rectangle boundingrectangle;
    
    
	
   public boolean isAlive;
	
	/**
 * @param isAlive the isAlive to set
 */
public void setAlive(boolean isAlive) {
	this.isAlive = isAlive;
}
	public SpaceMan(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
        boundingrectangle = new Rectangle();
        boundingCircle=new Circle();
        isAlive = true;
      
	}
    public void update(float delta) {
		// acclerate constantly
		velocity.add(acceleration.cpy().scl(delta));
		if (velocity.y > 200) {
			velocity.y = 200;
		}
		
		// CEILING CHECK 
        if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }

		position.add(velocity.cpy().scl(delta));

		// Set the circle's center to be (9, 6) with respect to the man.
		// Set the circle's radius to be 6.5f;
		 boundingCircle.set(position.x + 7, position.y + 6, 5.5f);

//		boundingrectangle.set(position.x+2, position.y+5, 7f, 5.5f);
//	    boundingrectangle.set(position.x+4, position.y+5, 7f, 5.5f);
	
		
		 
		 
	}

	public void onClick() {
		if (isAlive) {
            AssetLoader.fly.play();
            velocity.y = -130;
        }
	}
	
	public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }
	
	public boolean isAlive() {
		   return isAlive;
		}
	
	

//here i made velocity.y = 150.
	
	public void die() {
        isAlive = false;
        velocity.y = 0;
    }
	
	public void decelerate() {
        acceleration.y = 0;
    }
	
	public boolean isFalling() {
        return velocity.y > 110;
    }
	
	 public boolean shouldntFlap() {
	        return velocity.y > 70 || !isAlive;
	    }
	
	
	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}


}
