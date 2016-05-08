
import java.awt.Rectangle;


public class Enemy  {
	private int x, y, width, height, speed, type, health, counter, origX, origY, length, lengthCount;
	private boolean movingNegative, isDead, isMoving;
	
	public Enemy(int x, int y, int width, int height, int speed, int type, int length, boolean movingNegative) {
		this.x = x;
		this.y = y;
		this.setOrigX(x);
		this.setOrigY(y);
		this.setLength(length);
		this.width = width;
		this.height = height;
		this.setSpeed(speed);
		this.setType(type);
		this.setMovingNegative(movingNegative);
		isDead = false;
		setMoving(false);
	    counter = 0;
	    setLengthCount(0);
		if(type == 1){
			health = 1;
		} else if(type == 4){
			health = 3;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int cx){
		x = cx;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isMovingNegative() {
		return movingNegative;
	}

	public void setMovingNegative(boolean movingNegative) {
		this.movingNegative = movingNegative;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setDeath(boolean isDead){
		this.isDead = isDead;
	}
	
	public void setHealth(int health){
		this.health = health;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
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

	public int getLengthCount() {
		return lengthCount;
	}

	public void setLengthCount(int lengthCount) {
		this.lengthCount = lengthCount;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
