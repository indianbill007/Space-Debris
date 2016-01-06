package GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.globussoft.gameworld.GameWorld;

public class Scrollable {

	// Protected is similar to private, but allows inheritance by subclasses.
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledLeft;
	private Vector2 acceleration,acceleration2;
	
	boolean flag=false,flag2=false;
	public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(scrollSpeed,0);
		
		acceleration = new Vector2(0, 40);
		acceleration2=new Vector2(0,65);
		this.width = width;
		this.height = height;
		isScrolledLeft = false;
		
		
	}

	
	
	public void update(float delta) {
		
//this line of code increments the X position of the object with the velocity vector scaled by delta time.
		position.add(velocity.cpy().scl(delta));
	// If the Scrollable object is no longer visible:
		if (position.x + width < 0) {
			isScrolledLeft = true;			
		}
	}
	
	 public void animationUpdate(float delta)
	 {
		 position.add(velocity.cpy().scl(delta));
			if(Constantsvalue.Animationflag1==false){
				if(position.y>-1 && position.y<=61)
				{
					
					position.add(acceleration.cpy().scl(delta));
					if(position.y>59&&position.y<60)
					{
						
						Constantsvalue.Animationflag1=true;
						
					}
				}
				}
				
				if(Constantsvalue.Animationflag1==true)
				{
					
				if(position.y>0 && position.y<=62)
					{
			
						
						
						position.sub(acceleration.cpy().scl(delta));
						
					
						
						if(position.y>0&&position.y<5)
						{
							Constantsvalue.Animationflag1=false;
						}
					}
					
				}
			
				if (position.x + width < 0) {
					isScrolledLeft = true;			
				}
		 
	 }
	 
	 
	 public void animation2Update(float delta)
	 {
		 position.add(velocity.cpy().scl(delta));
			if(Constantsvalue.Animationflag2==false){
				if(position.y>-1 && position.y<=61)
				{
					position.add(acceleration2.cpy().scl(delta));
					if(position.y>59)
					{
						System.out.println("+++++++++++++++++++insdie flag true");
						Constantsvalue.Animationflag2=true;
						
					}
				}
				}
				
				if(Constantsvalue.Animationflag2==true)
				{
					
				if(position.y>0 && position.y<=62)
					{
			            position.sub(acceleration2.cpy().scl(delta));
						if(position.y>0&&position.y<5)
						{
							System.out.println("+++++++++++++++++++insdie flag false");
							Constantsvalue.Animationflag2=false;
						}
					}
					
				}
			
				if (position.x + width < 0) {
					isScrolledLeft = true;			
				}
		 
	 }
	
	// Reset: Should Override in subclass for more specific behavior.
	public void reset(float newX) {
		

		position.x = newX;
		isScrolledLeft = false;
	}

	public void stop() {
		velocity.x = 0;
	}
	
	// Getters for instance variables
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }
    
    public float getTailX() {
        return position.x + width;
    }
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}