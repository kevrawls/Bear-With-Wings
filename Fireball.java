
import java.awt.*;


public class Fireball extends Rectangle implements Shape {
	
	private int x, y, width, height;
	private boolean facingRight;
	
	public Fireball(int x, int y, int width, int height, boolean facingRight){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.facingRight = facingRight;
	}
	
	public void move(){
		if(facingRight){
			x += 1;
		} else {
			x -= 1;
		}
	}
	
	//public boolean intersects(Rectangle rect){
		//if((x <= rect.x + rect.width || x + width >= rect.x) && rect.y <= y && rect.y + rect.height >= y  + height ){
		//	return true;
		//}
		//return false;
		
	//}
	
	public void drawFireball(int x, int y, int width, int height, Graphics g){
		g.fillRect(x, y, width, height);
	}
	
	public int getFX(){
		return x;
	}
	public int getFY(){
		return y;
	}
	public int getFWidth(){
		return width;
	}
	public int getFHeight(){
		return height;
	}
	
}

