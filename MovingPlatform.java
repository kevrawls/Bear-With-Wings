
import java.awt.Rectangle;


public class MovingPlatform  {
	private int x, y, width, height, speed, length, origLength, counter, speedCount;
	private boolean movingNegative, horizontal;
	private int origX, origY;
	private String type;
	
	
	public MovingPlatform(int x, int y, int width, int height, int length, int speed, boolean horizontal, boolean movingNegative, String type){
		this.setX(x);
		this.setY(y);
		this.origX = x;
		this.origY = y;
		this.type = type;
		this.setWidth(width);
		this.setHeight(height);
		this.setLength(length);
		origLength = length;
		this.setSpeed(speed);
		this.setHorizontal(horizontal);
		this.setMovingNegative(movingNegative);
		counter = 0;
		setSpeedCount(0);
	}


	public boolean isMovingNegative() {
		return movingNegative;
	}


	public void setMovingNegative(boolean movingNegative) {
		this.movingNegative = movingNegative;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public boolean isHorizontal() {
		return horizontal;
	}


	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getOrigLength() {
		return origLength;
	}


	public int getSpeedCount() {
		return speedCount;
	}


	public void setSpeedCount(int speedCount) {
		this.speedCount = speedCount;
	}


	public int getOrigX() {
		return origX;
	}


	public void setOrigX(int origX) {
		this.origX = origX;
	}


	public int getOrigY() {
		return origY;
	}


	public void setOrigY(int origY) {
		this.origY = origY;
	}
	public String getType(){
		return type;
	}

	
}

