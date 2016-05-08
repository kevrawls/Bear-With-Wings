//Bear With Wings
//Author: Kevin Rawls
//Full game available for download at bearwithwings.com

/*Basic outline of what this game does:
	So the bear(or whatever) starts out in a main hub world and can 
	then go to smaller hub worlds in which there are 5 doors. For example the first hub world(which
	you can access through the main hub world) has four multicolored doors that lead to levels. The fifth 
	door is the door leading to the boss fight. In the main levels there are keys to collect,
    1 for each level. Once the player has collected all keys, they gain access to the final door and 
	gain an ability. Once they beat the boss, using the new ability, they gain access to the next world. 
	The whole game revolves around doors and connecting the levels via doors as well as using new abilities
	gained from previous worlds to beat new worlds.
*/

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import javax.swing.*;


public class BearWithWings extends JFrame implements Runnable{

		private int mx, my; //mouse x and mouse y
		private int x, y, xDirection, yDirection;
		private int jumpCount;
		private int moveCount;
		private int tempJumpCount;
		private int crystalCount;
		private int levelCrystals;
		private int levelTime;
		private int W_WIDTH = 1300; //Window Width of the game
		private int W_HEIGHT = 700; //Window Height of the game
		private int xsideScrollCount;
		private int ysideScrollCount;
		private int jumpHoldCount;
		private int fallCount;
		private int tempFallCount;
		private int jumpedOnEnemyCount;
		private int leftFlyCount;
		private int rightFlyCount;
		private int upFlyCount;
		private int downFlyCount;
		private int tempLeftCount;
		private int tempRightCount;
		private int tempUpCount;
		private int tempDownCount;
		private int flyCrystalCount;
		private int currentX;
		private int currentY;
		private int EHoldCount;
		private int PHoldCount;
		private int doorx;
		private int doory;
		private int doubleJump = 1;
		private int doubleJumpCount;
		private int waitCount;
		private int waitNum;
		private int superCrystalCount;
		private int bearDeadCount;
      private int enemy1AnimationCount;
		
		private int bossFlashCount;
		private int bossFlashTempCount;
		private int bossDecideCount;
		private int bossHorizontalCount;
		private int bossJumpTempCount;
		private int bossJumpCount;
		private int bossDeathCount;
		private int creditCount;
		private int creditTempCount;
      
      private int bearMovingRightCount;
      private int bearMovingLeftCount;
      private int bearFlyingCount;
		
		private String level = "hub";
		private String lastDoor = "hubDoor1";
		private String alertMessage = "";
		private String keySound = "C:/Users/Kevin/Music/item get.wav";
		private String coinSound = "C:/Users/Kevin/Music/coin.wav";
		private String titleScreenMusicURL = "C:/Users/Kevin/Music/cave story.wav";
		private String wingFlap = "C:/Users/Kevin/Music/wing flap.wav";
		private String stompSound = "C:/Users/Kevin/Music/stomp.wav";
		private String hub1URL = "C:/Users/Kevin/Music/mega man 5 stage select.wav";
		private String redURL = "C:/Users/Kevin/Music/batman.wav";
		private String blueURL = "C:/Users/Kevin/Music/fantasy zone.wav";
		private String mainHubURL = "C:/Users/Kevin/Music/mr gimmick.wav";
		private String greenURL = "C:/Users/Kevin/Music/duck tales.wav";
		private String bossScream = "C:/Users/Kevin/Music/boss scream.wav";
		private String bossThemeURL = "C:/Users/Kevin/Music/zelda ganon theme.wav";
		private String bossBattleURL = "C:/Users/Kevin/Music/zelda ganon battle.wav";
		private String smilesAndTears = "C:/Users/Kevin/Music/smiles and tears.wav";
		private String bearYell = "C:/Users/Kevin/Music/zelda menu.wav";
		private String yellowURL = "C:/Users/Kevin/Music/mega man 2.wav";
		private String flyURL = "C:/Users/Kevin/Music/slide theme.wav";
		private String superCrystalGet = "C:/Users/Kevin/Music/dragon coin.wav";
		
		private Color background = new Color(0, 150, 250);
		private Color fireball = Color.RED;
		
		private ImageIcon left;
		private ImageIcon right;
		private ImageIcon rightMove;
		private ImageIcon doorClosed = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/hub door.gif");
		private ImageIcon doorOpened;
		private ImageIcon smallTree = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/Trees.gif");
		private ImageIcon bigTree = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/Bigger tree.gif");
		private ImageIcon enemyIcon1Up = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/Enemy 2.gif");
      private ImageIcon enemyIcon1Down = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/Enemy 2.1.gif");
		private ImageIcon enemyIcon2Left = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/enemy 3.gif");
		private ImageIcon enemyIcon2Right = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/enemy 3.1.gif");
		private ImageIcon enemyIcon3Right = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/enemy 4.2.gif");
		private ImageIcon enemyIcon3Left = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/enemy 4.1.gif");
		private ImageIcon enemyIcon4Right = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/enemy 5.2.gif");
		private ImageIcon enemyIcon4Left = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/enemy 5.1.gif");
		private ImageIcon platformIcon = new ImageIcon("F:/Pictures/grassyplatform1.gif");
		private ImageIcon orangeKey= new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/orange key.gif");
		private ImageIcon blueKey = new ImageIcon("C:/Users/Kevin/Desktop/bww/blue key.gif");
		private ImageIcon blueDoorIcon = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/blue door.gif");
		private ImageIcon hub1DoorIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/hub1 door.gif");
		private ImageIcon redDoorIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/red door.gif");
		private ImageIcon redKey =  new ImageIcon("C:/Users/Kevin/Desktop/bww/red key.gif");
		private ImageIcon greenDoorIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/green door.gif");
		private ImageIcon greenKey =  new ImageIcon("C:/Users/Kevin/Desktop/bww/green key.gif");
		private ImageIcon bossDoorIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/boss door.gif");
		private ImageIcon boss1LeftIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/boss1 left.gif");
		private ImageIcon boss1RightIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/boss1 right.gif");
		private ImageIcon yellowDoorIcon =  new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/yellow door.gif");
		private ImageIcon yellowKey =  new ImageIcon("C:/Users/Kevin/Desktop/bww/yellow key.gif");
		private ImageIcon bearRight = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 RIGHT.gif"); 
      private ImageIcon bearLeft = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 LEFT.gif");
      private ImageIcon bearJumpUpRight = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 JUMP2 RIGHT.gif"); 
      private ImageIcon bearJumpUpLeft = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 JUMP2 LEFT.gif");
      private ImageIcon bearJumpDownRight = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 JUMP1 RIGHT.gif"); 
      private ImageIcon bearJumpDownLeft = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 JUMP1 LEFT.gif"); 
      
      private ImageIcon bearMoveOneRight = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 MOVE 1 RIGHT.gif"); 
      private ImageIcon bearMoveOneLeft = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 MOVE1 LEFT.gif");
      private ImageIcon bearMoveTwoRight = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 MOVE2 RIGHT.gif"); 
      private ImageIcon bearMoveTwoLeft = new ImageIcon("C:/Users/Kevin/Desktop/bww/BEAR2 MOVE2 LEFT.gif");
      private ImageIcon crystal = new ImageIcon("C:/Users/Kevin/Desktop/bww/crystal6.gif");
      private ImageIcon superCrystal = new ImageIcon("C:/Users/Kevin/Desktop/bww/crystal.gif");
      private ImageIcon powerCrystal = new ImageIcon("C:/Users/Kevin/Desktop/bww/power crystal.gif");   
      private ImageIcon fireBall = new ImageIcon("C:/Users/Kevin/Desktop/bww/fireball.gif");
      private ImageIcon fireBall2 = new ImageIcon("C:/Users/Kevin/Desktop/bww/fireball 2.gif");     
        
		
      private Image door;
		private Image dbImage;
		private Image character;
		private Graphics dbg;
		
		private Rectangle startButton = new Rectangle(500, 400, 300, 50);
		private Rectangle continueButton = new Rectangle(450, 300, 100, 25);
		private Rectangle bear;
		private Rectangle winSquare;
		private ArrayList<Platform> platformSet;
		private Set<MovingPlatform> movingPlatformSet;
		private ArrayList<Platform> crystalSet;	
		private ArrayList<Enemy> enemySet;
		private ArrayList<Fireball> fireballSet;
		private ArrayList<Platform> deathAreaSet;
		private ArrayList<Platform> flyCrystalSet;
		private ArrayList<Platform> keySet;
		private ArrayList<Rectangle> offplatformSet;
		private ArrayList<Platform> doorSet;
		private ArrayList<Platform> blueCrystalSet;	
		private ArrayList<Platform> redCrystalSet;	
		private ArrayList<Platform> greenCrystalSet;	
		private ArrayList<Platform> yellowCrystalSet;	
		
		private boolean play = true;
		private boolean hasGameStarted;
		private boolean hasFire; //determines whether the bear has acquire fireballs
		private boolean readyToFire;
		private boolean startHover; //check if the mouse is hovering over the Start Button
		private boolean continueHover; //check if the mouse is hovering over the Continue Button
		private boolean gameStart = true;
		private boolean hub = true; //True if the level has started
		private boolean win; //True if the level has won
		private boolean isTouching; //True if bear is touching an object
		private boolean isJumping; //True if bear is jumping
		private boolean exitJump;
		private boolean falling;
		private boolean fireRight;
		private boolean pressingRight;
		private boolean movingRight;
		//toggles between ice and fire, false for fireball, true for ice
		private boolean iceTrue = false;
		private boolean hasSideScrolled;
		private boolean isHoldingJumpKey;
		private boolean fallStart;
		private boolean shouldReverse;
		private boolean isPressingLeft;
		private boolean isPressingRight;
		private boolean jumpedOnEnemy;
		private boolean isFlying;
		private boolean isPressingUp;
		private boolean isPressingDown;
		private boolean isInvincible;
		private boolean ateFlyCrystal;
		private boolean debug;
		private boolean isHoldingE;
		private boolean isHoldingP;
		private boolean paused;
		private boolean door1;
		private boolean blueDoor;
		private boolean redDoor;
		private boolean redDoor2;
		private boolean redDoor3;
		private boolean bossDoor;
		private boolean greenDoor;
		private boolean greenDoor2;
		private boolean greenDoor3;
		private boolean greenDoor4;
		private boolean yellowDoor;
		private boolean yellowDoor2;
		private boolean yellowDoor3;
		private boolean yellowDoor4;
		private boolean inFrontOfDoor;
		private boolean wait;
		private boolean barrierDown;
		private boolean credits;
		private boolean enteredBlue;
		private boolean enteredRed;
		private boolean enteredGreen;
		private boolean enteredYellow;
		private boolean hasBlueKey;
		private boolean hasRedKey;
		private boolean hasGreenKey;
		private boolean hasYellowKey;
		private boolean alert;
		private boolean bearDead;
		private boolean bearYelling;
		private boolean firstEnemyOnScreen;
      private boolean enemyAnimation1;
      private boolean hasGivenPlayerAMillionCrystals;
      private boolean used100Crystals;
		
		
		
		//Boss battle booleans
		private boolean hasEnteredBossArea;
		private boolean bossDeciding;
		private boolean bossFlash;
		private boolean bossHorizontal;
		private boolean bossMovedHorizontal;
		private boolean bossJump;
		private boolean bossJumping;
		private boolean bossDying;
		private boolean bossDead;
		
		private boolean alert1;
		private boolean alert2;
		private boolean alert3;
		private boolean alert4;
		private boolean alert5;
		private boolean alert6;
		private boolean alert7;
		private boolean alert8;
		
		private Clip titleScreenMusic = getMusic(titleScreenMusicURL);
		private Clip hub1Music = getMusic(hub1URL);
		private Clip redMusic = getMusic(redURL);
		private Clip blueMusic = getMusic(blueURL);
		private Clip mainHubMusic = getMusic(mainHubURL);
		private Clip greenMusic = getMusic(greenURL);
		private Clip bossTheme = getMusic(bossThemeURL);
		private Clip bossBattle = getMusic(bossBattleURL);
		private Clip creditsSong = getMusic(smilesAndTears);
		private Clip yellowMusic = getMusic(yellowURL);
		private Clip flyMusic = getMusic(flyURL);
		
		
		public static final int BEAR_SPEED = 1; //In milliseconds
		public static final int BEAR_HEIGHT = 57;
		public static final int BEAR_WIDTH = 53;
		
		private static final long serialVersionUID = 1L;
		
		//3 parameters, font name, type of font(bold/italic/etc) and size
		Font font = new Font("Arial", Font.ITALIC, 20);
		
		//Runs the game, executes the movement if pressed and continues to run every speed milliseconds.
		public void run() {
			while(play){
			try {
					move();
					Thread.sleep(BEAR_SPEED);
					if(!win && hasGameStarted && !paused){
						levelTime += 1;
					}
				}catch(Exception e){
					System.out.println("Calvin is a honorable gentleman");
				}	
			}
		}
		
		public class MouseHandler extends MouseAdapter {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int mx = e.getX();
				int my = e.getY();
				if(mx > startButton.x && mx < startButton.x + startButton.width
						&& my > startButton.y && my < startButton.y + startButton.height){
					startHover = true;
				} else if ((mx > continueButton.x && mx < continueButton.x + continueButton.width
						&& my > continueButton.y && my < continueButton.y + continueButton.height) && win){
					continueHover = true;
				} else {
					startHover = false;
					continueHover = false;
				}
			}
			@Override
			public void mousePressed(MouseEvent e){
				mx = e.getX();
				my = e.getY();
				if(mx > startButton.x && mx < startButton.x + startButton.width
						&& my > startButton.y && my < startButton.y + startButton.height){
					hasGameStarted = true;
				} else if((mx > continueButton.x && mx < continueButton.x + continueButton.width
						&& my > continueButton.y && my < continueButton.y + continueButton.height) && win){
					//level++;
					win = false;
					hub = true;
				}
			}
		
			 // for mouse dragging
			public void mouseReleased(MouseEvent e){
				
			}
		}
	
		public void move() {
			if(!paused && !wait){
			if(isOnTopOfPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT) && (!isPressingDown &&(!isFlying || !debug))){ 
				falling = false;
				y -= 2;
				
				if(jumpCount > 0 && !isHoldingJumpKey){
					isJumping = false;
					exitJump = false;
					
					jumpCount = 0;
				}
				if(jumpCount > 0 && exitJump){
					isJumping = false;
					exitJump = false;
					
					jumpCount = 0;
				}
			}
			if(isOnTopOfPlatform(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
				
			}
			if(isFlying || debug){
				fly();
			}
			if(isHoldingJumpKey){
				jumpHoldCount++;
			}
			if(isHoldingE){
				EHoldCount++;
			}
			if(isMovingPlatformBottomTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT) && 
			!isTouchingPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
				y += 1;
				exitJump = true;
			}
			moveCount++;
			if(moveCount == 2){
				moveCount = 0;
				isTouching = false;
//				x += xDirection;
//				y += yDirection;
				if(isPressingLeft && !isFlying && !debug){
					x -= 1;
				}
				if(isPressingRight && !isFlying && !debug){
					x += 1;
				}
				if(isLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
					x -= 1;
				}
				if(isRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
					x += 1;
				}
				if(isTouchingPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
					isTouching = true;
				}
				
								if(!isJumping){
					if(!isTouching && !isFlying && !debug){ 
						falling = true;
						y += 1;
					} else {
						falling = false;
						doubleJumpCount = 0;
					}
					
				}
				for(Platform rect : platformSet){
					Rectangle temp = new Rectangle(rect.x, rect.y, rect.width, rect.height);
					if(bear.intersects(temp) && !isJumping && !exitJump && rect.width != 1 && rect.width != 2 && !isFlying && !debug){
						y += 1;
					}
				}
				for(MovingPlatform mp : movingPlatformSet){
					Rectangle temp = new Rectangle(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight());
					if(bear.intersects(temp) && !isJumping && !exitJump && !isFlying && !debug){
						y += 1;
					}
				}
				
			}
			if((isRightTouching(x,y, BEAR_WIDTH, BEAR_HEIGHT) || 
			isMovingPlatformRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)) &&
			(!isLeftTouching(x + 1, y, BEAR_WIDTH, BEAR_HEIGHT) &&
			!isLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)	)){
				x += 1;		
//				isHoldingJumpKey = false;
//				jumpHoldCount = 0;
//				exitJump = true;
			}	
			if((isLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT) || 
			isMovingPlatformLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)) &&
			(!isRightTouching(x - 1, y, BEAR_WIDTH, BEAR_HEIGHT)&&
			!isRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)	)){
				x -= 1;		
				
//				isHoldingJumpKey = false;
//				jumpHoldCount = 0;
//				exitJump = true;
			}
			if(isMovingPlatformLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT) || 
			isMovingPlatformRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
				
			}
			if(isJumping){
				jump();
			}
			if(!isJumping){
				exitJump = false;
				
				
				
			//Bounds of the frame
			if(x <= 10){
				x = 10;
			}
			if(x >= W_WIDTH - BEAR_WIDTH - 10){
				x = W_WIDTH - BEAR_WIDTH - 10;
			}
			if(y <= 20){
				y = 20;
			}
			if(y >= W_HEIGHT){
				y = W_HEIGHT;
			}
			} 
			//for some reason this has to be in move :/
			if(level.equals("blue")){
				sideScroll(0, 10750, 0, 1000); 
			} else if(level.equals("hub")){
				sideScroll(0, 0, 0, 0); 
			} else if(level.equals("hub1")){
				sideScroll(0, 0, 0, 0); 
			} else if(level.equals("red") || level.equals("red2")){
				sideScroll(-5000, 5000, 0, 6000); 
			} else if(level.equals("green")){
				sideScroll(-5000, 5000, -6000, 6000); 
			} else if(level.equals("yellow")){
				sideScroll(-5000, 20000, -12000, 12000); 
			} else if(level.equals("boss")){
				sideScroll(0, 5000, 0, 1000); 
			}
			if(isMovingPlatformBottomTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT) && 
			!isTouchingPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
				y += 1;
				exitJump = true;
			}
			killEnemy();
			}
			
			
		}
		public void jump(){
			//JUMPING
			if(!exitJump){
				 if(jumpCount <= 70){
					tempJumpCount++;
					if(tempJumpCount == 2){
						y -= 1;
						tempJumpCount = 0;
						jumpCount++;
					}
				}else if(jumpCount > 70 && jumpCount <= 90){
					tempJumpCount++;
					if(tempJumpCount == 3){
						y -= 1;
						tempJumpCount = 0;
						jumpCount++;
					}
				}else if(jumpCount > 90 && jumpCount <= 110){
					tempJumpCount++;
					if(tempJumpCount == 4){
						y -= 1;
						tempJumpCount = 0;
						jumpCount++;
					}
				}else if(jumpCount > 110 && jumpCount <= 134){
					tempJumpCount++;
					if(tempJumpCount == 5){
						y -= 1;
						tempJumpCount = 0;
						jumpCount++;
					}
				}

				else if(jumpCount > 134 && jumpCount <= 140){
					tempJumpCount++;
					if(tempJumpCount == 15){
						y -= 1;
						tempJumpCount = 0;
						jumpCount++;
					}
				}
				if(jumpCount == 138){
					tempJumpCount = 0;
				}
				//EXITING THE JUMP
				if(jumpCount >= 138){
					exitJump = true;
				}	
			}	
			if(exitJump){ 
				 if(jumpCount < 139 && jumpCount >= 134){
					tempJumpCount++;
					if(tempJumpCount == 15){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					}
				}
				 else if(jumpCount < 134 && jumpCount >= 128){
					tempJumpCount++;
					if(tempJumpCount == 7){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					} 
				}else if(jumpCount < 128 && jumpCount >= 122){
					tempJumpCount++;
					if(tempJumpCount == 6){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					}
				}
				else if(jumpCount < 122 && jumpCount >= 116){
					tempJumpCount++;
					if(tempJumpCount == 5){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					}
				}else if(jumpCount < 116 && jumpCount >= 110){
					tempJumpCount++;
					if(tempJumpCount == 4){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					}
				}
				else if(jumpCount < 110 && jumpCount >= 60){
					tempJumpCount++;
					if(tempJumpCount == 3){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					}
				}else if(jumpCount < 60 && jumpCount >= 0){
					tempJumpCount++;
					if(tempJumpCount == 2){
						y += 1;
						tempJumpCount = 0;
						jumpCount--;
					}
				}
				else {
					isJumping = false;
					jumpCount = 0;
					if(!isTouchingPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
						falling = true;
					}	
					exitJump = false;
					
				}
			}		
			if(isBottomTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
				exitJump = true;
			}
			
			if(x >= W_WIDTH - 10){
				x -= 1;
			} else if(x <= 10){
				x += 1;
			}
		}
		
		// This method is FLY!
		public void fly(){
			//speeding up left
			if(isPressingLeft){
				leftFlyCount++;
				if(leftFlyCount < 50){
					tempLeftCount++;
					if(tempLeftCount ==  10){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount >= 50 && leftFlyCount < 100){
					if(leftFlyCount == 50){
						tempLeftCount = 0;
					}
					tempLeftCount++;
					if(tempLeftCount == 7){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount >= 100 && leftFlyCount < 150){
					if(leftFlyCount == 100){
						tempLeftCount = 0;
					}
					tempLeftCount++;
					if(tempLeftCount == 5){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount >= 150 && leftFlyCount < 300){
					if(leftFlyCount == 150){
						tempLeftCount = 0;
					}
					tempLeftCount++;
					if(tempLeftCount == 3){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount >= 300 && leftFlyCount < 400){
					if(leftFlyCount == 300){
						tempLeftCount = 0;
					}
					tempLeftCount++;
					if(tempLeftCount == 2){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount >= 400){
						x -= 1;
						leftFlyCount = 400;
				}
			}
			// slowing down left
			if(!isPressingLeft && leftFlyCount > 0){
				leftFlyCount--;
				if(leftFlyCount <= 400 && leftFlyCount >= 300){
					tempLeftCount++;
					if(tempLeftCount == 2){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount < 300 && leftFlyCount >= 150){
					tempLeftCount++;
					if(tempLeftCount == 3){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount < 150 && leftFlyCount >= 100){
					tempLeftCount++;
					if(tempLeftCount == 5){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount < 100 && leftFlyCount >= 50){
					tempLeftCount++;
					if(tempLeftCount == 7){
						tempLeftCount = 0;
						x -= 1;
					}
				} else if(leftFlyCount < 50 && leftFlyCount > 0){
					tempLeftCount++;
					if(tempLeftCount == 10){
						tempLeftCount = 0;
						x -= 1;
					}
				}
			}
			//speeding up right
			if(isPressingRight){
				rightFlyCount++;
				if(rightFlyCount < 50){
					tempRightCount++;
					if(tempRightCount ==  10){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount >= 50 && rightFlyCount < 100){
					if(rightFlyCount == 50){
						tempRightCount = 0;
					}
					tempRightCount++;
					if(tempRightCount == 7){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount >= 100 && rightFlyCount < 150){
					if(rightFlyCount == 100){
						tempRightCount = 0;
					}
					tempRightCount++;
					if(tempRightCount == 5){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount >= 150 && rightFlyCount < 300){
					if(rightFlyCount == 150){
						tempRightCount = 0;
					}
					tempRightCount++;
					if(tempRightCount == 3){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount >= 300 && rightFlyCount < 400){
					if(rightFlyCount == 300){
						tempRightCount = 0;
					}
					tempRightCount++;
					if(tempRightCount == 2){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount >= 400){
						x += 1;
						rightFlyCount = 400;
				}
			}
			// slowing down right
			if(!isPressingRight && rightFlyCount > 0){
				rightFlyCount--;
				if(rightFlyCount <= 400 && rightFlyCount >= 300){
					tempRightCount++;
					if(tempRightCount == 2){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount < 300 && rightFlyCount >= 150){
					tempRightCount++;
					if(tempRightCount == 3){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount < 150 && rightFlyCount >= 100){
					tempRightCount++;
					if(tempRightCount == 5){
						tempRightCount = 0;
						x += 1;
					}
				} else if(rightFlyCount < 100 && rightFlyCount >= 50){
					tempRightCount++;
					if(tempRightCount == 7){
						tempRightCount = 0;
						x += 1;
					}
				} else if(leftFlyCount < 50 && leftFlyCount > 0){
					tempRightCount++;
					if(tempRightCount == 10){
						tempRightCount = 0;
						x += 1;
					}
				}
			}
			//speeding up up
			if(isPressingUp){
				upFlyCount++;
				if(upFlyCount < 50){
					tempUpCount++;
					if(tempUpCount ==  10){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount >= 50 && upFlyCount < 100){
					if(upFlyCount == 50){
						tempUpCount = 0;
					}
					tempUpCount++;
					if(tempUpCount == 7){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount >= 100 && upFlyCount < 150){
					if(upFlyCount == 100){
						tempUpCount = 0;
					}
					tempUpCount++;
					if(tempUpCount == 5){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount >= 150 && upFlyCount < 300){
					if(upFlyCount == 150){
						tempUpCount = 0;
					}
					tempUpCount++;
					if(tempUpCount == 3){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount >= 300 && upFlyCount < 400){
					if(upFlyCount == 300){
						tempUpCount = 0;
					}
					tempUpCount++;
					if(tempUpCount == 2){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount >= 400){
						y -= 1;
						upFlyCount = 400;
				}
			}
			// slowing down up
			if(!isPressingUp && upFlyCount > 0){
				upFlyCount--;
				if(upFlyCount <= 400 && upFlyCount >= 300){
					tempUpCount++;
					if(tempUpCount == 2){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount < 300 && upFlyCount >= 150){
					tempUpCount++;
					if(tempUpCount == 3){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount < 150 && upFlyCount >= 100){
					tempUpCount++;
					if(tempUpCount == 5){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount < 100 && upFlyCount >= 50){
					tempUpCount++;
					if(tempUpCount == 7){
						tempUpCount = 0;
						y -= 1;
					}
				} else if(upFlyCount < 50 && upFlyCount > 0){
					tempUpCount++;
					if(tempUpCount == 10){
						tempUpCount = 0;
						y -= 1;
					}
				}
			}
			//speeding up down
			if(isPressingDown && !isOnTopOfPlatform(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
				downFlyCount++;
				if(downFlyCount < 50){
					tempDownCount++;
					if(tempDownCount ==  10){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount >= 50 && downFlyCount < 100){
					if(downFlyCount == 50){
						tempDownCount = 0;
					}
					tempDownCount++;
					if(tempDownCount == 7){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount >= 100 && downFlyCount < 150){
					if(downFlyCount == 100){
						tempDownCount = 0;
					}
					tempDownCount++;
					if(tempDownCount == 5){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount >= 150 && downFlyCount < 300){
					if(downFlyCount == 150){
						tempDownCount = 0;
					}
					tempDownCount++;
					if(tempDownCount == 3){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount >= 300 && downFlyCount < 400){
					if(downFlyCount == 300){
						tempDownCount = 0;
					}
					tempDownCount++;
					if(tempDownCount == 2){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount >= 400){
						y += 1;
						downFlyCount = 400;
				}
			}
			// slowing down down
			if(!isPressingDown && downFlyCount > 0 && !isOnTopOfPlatform(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
				downFlyCount--;
				if(downFlyCount <= 400 && downFlyCount >= 300){
					tempDownCount++;
					if(tempDownCount == 2){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount < 300 && downFlyCount >= 150){
					tempDownCount++;
					if(tempDownCount == 3){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount < 150 && downFlyCount >= 100){
					tempDownCount++;
					if(tempDownCount == 5){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount < 100 && downFlyCount >= 50){
					tempDownCount++;
					if(tempDownCount == 7){
						tempDownCount = 0;
						y += 1;
					}
				} else if(downFlyCount < 50 && downFlyCount > 0){
					tempDownCount++;
					if(tempDownCount == 10){
						tempDownCount = 0;
						y += 1;
					}
				}
			}
			if(isOnTopOfPlatform(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
				downFlyCount = 0;
			}
			if(isBottomTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
				y += 1;
			}
		}
		
		public boolean isTouchingPlatform(int x, int y, int width, int height){
			for(Platform rect : platformSet){
				if((rect.x + rect.width >= x && rect.x <= x + width) &&
				(rect.y + rect.height >= y + 1 && rect.y <= y + height + 1)){
					falling = false;
					
					return true;	
				}
			}	
			for(MovingPlatform mp : movingPlatformSet){
				if((mp.getX() + mp.getWidth() >= x && mp.getX() <= x + width) &&
				(mp.getY() + mp.getHeight() >= y + 1 && mp.getY() <= y + height + 1)){
					//falling = false;
					return true;	
				}
			}
			return false;
		}
		
		
		// OK This is just a separate touching method to make sure the bear can't jump
		// infinitely if an enemy is on the screen
		public boolean isEnemyTouchingPlatform(int x, int y, int width, int height){
			for(Platform rect : platformSet){
				if((rect.x + rect.width >= x && rect.x <= x + width) &&
				(rect.y + rect.height >= y + 1 && rect.y <= y + height + 1)){
					return true;	
				}
			}	
			for(MovingPlatform mp : movingPlatformSet){
				if((mp.getX() + mp.getWidth() >= x && mp.getX() <= x + width) &&
				(mp.getY() + mp.getHeight() >= y + 1 && mp.getY() <= y + height + 1)){
					return true;	
				}
			}
			return false;
		}
		
		public boolean isMovingPlatformTouching(int x, int y, int width, int height){
			for(MovingPlatform mp : movingPlatformSet){
				if((mp.getX() + mp.getWidth() >= x && mp.getX() <= x + width) &&
				(mp.getY() + mp.getHeight() >= y + 1 && mp.getY() <= y + height + 1)){
					return true;	
				}
			}
			return false;
		}
		
		//You may be asking yourself, why does this even exist? Well, DON'T ASK QUESTIONS! GET BACK TO WORK DRONE!
		public void slantedPlatformAdjust(){
			y-=1;
		}
		
		public boolean isRightTouching(int x, int y, int width, int height){
			for(Platform rect : platformSet){
				if(rect.width == 2 || rect.width == 1){
					if( x == rect.x + rect.width + 1 && y <= rect.y + rect.height + 1 && 
					y + height >= rect.y + 3){
						return true;
					}
				}  else if( x == rect.x + rect.width && y <= rect.y + rect.height + 1 && 
				y + height >= rect.y - 1 && !rect.type.equals("2")){
					return true;
				}
				if(x == rect.x + rect.width + 1 && y <= rect.y + rect.height + 1 && 
					y + height >= rect.y - 2 && rect.width == 1){
					slantedPlatformAdjust();
				}
			}
			return false;
		}
		
		
		public boolean isLeftTouching(int x, int y, int width, int height){
			for(Platform rect : platformSet){
				if(x + width + 1 == rect.x && y <= rect.y + rect.height + 1 && 
						y + height >= rect.y - 2 && rect.width == 1){
						slantedPlatformAdjust();
					}
				if(rect.width == 2 || rect.width == 1){
					if(x + width + 1 == rect.x && y <= rect.y + rect.height + 1 && 
					y + height >= rect.y + 3){
						return true;
					}
				}  else if( x + width + 1 == rect.x && y <= rect.y + rect.height + 1 && 
					y + height >= rect.y - 1 && !rect.type.equals("2")){
					return true;
				}
				
			}
			return false;
		}
		
		public boolean isMovingPlatformRightTouching(int x, int y, int width, int height){
			for(MovingPlatform mp : movingPlatformSet){
				if( x == mp.getX() + mp.getWidth() && y <= mp.getY()+ mp.getHeight() && 
				y + height >= mp.getY()&& !mp.getType().equals("2")){
					return true;
				}
			}
			return false;
		}
		
		public boolean isMovingPlatformLeftTouching(int x, int y, int width, int height){
			for(MovingPlatform mp : movingPlatformSet){
				if( x + width + 1 == mp.getX() && y <= mp.getY() + mp.getHeight() + 1 && 
				y + height >= mp.getY() - 1 && !mp.getType().equals("2")){
						
					return true;
				}
			}
			return false;
		}
		
		public boolean isOnTopOfPlatform(int x, int y, int width, int height){
			for(Platform rect : platformSet){
				if(rect.x + rect.width > x + 1 && rect.x < x + width - 1 &&
				rect.y == y + height - 1){
					falling = false;
					return true;	
				}
			}	
			for(MovingPlatform mp : movingPlatformSet){
				if((mp.getX() + mp.getWidth() >= x + 1 && mp.getX() <= x + width - 1) &&
				(mp.getY() == y + height)){
					falling = false;
					return true;	
				}
			}
			return false;
		}
		public boolean isBottomTouching(int x, int y, int width, int height){
			for(Platform rect : platformSet){
				if((rect.x + rect.width >= x && rect.x <= x + width) &&
				(rect.y + rect.height + 1 == y) && !rect.type.equals("2")){
					//falling = false;
					return true;	
				}
			}	
			return false;
		}
		public boolean isMovingPlatformBottomTouching(int x, int y, int width, int height){
			for(MovingPlatform mp : movingPlatformSet){
				if((mp.getX() + mp.getWidth() >= x && mp.getX() <= x + width) &&
				(mp.getY() + mp.getHeight() + 1 == y )&& !mp.getType().equals("2")){
					falling = true;	
					return true;
				}
			}
			return false;
		}
		public boolean isEnemyTouching(int x, int y, int width, int height){
			for(Enemy enemy : enemySet){
				if((enemy.getX() + enemy.getWidth() >= x && enemy.getX() <= x + width) &&
				(enemy.getY() + enemy.getHeight()>= y + 1 && enemy.getY() <= y + height + 1)){
					if((x != enemy.getX() || y != enemy.getY() || width != enemy.getWidth() || height != enemy.getHeight()) && !enemy.isDead()){
						return true;
					}	
				}
			}
			return false;
		}
		public boolean isOverSlantedPlatform(int x,int y, int width, int height){
			for(Platform rect : platformSet){
				if((rect.width == 2 || rect.width == 1) && (y + height == rect.y - 2 || y + height == rect.y - 1) 
				&& (x == rect.x + 1 || x + width == rect.x + 1)){
					return true;
				}
			}
			return false;
		}
		
		public boolean isOnTopOfEnemy(int x,int y, int width, int height){
			for(Enemy enemy : enemySet){
				if(x <= enemy.getX() + enemy.getWidth() + 1 && x + width >= enemy.getX() && y + height == enemy.getY() 
				&& !enemy.isDead()){
					return true;
				}
			}
			return false;
		}
		
		public void setXDirection(int xdir){
			xDirection = xdir;
		}
		
		public void setYDirection(int ydir){
			yDirection = ydir;
		}
		
		//Allows the pressing of keyboard keys to initiate actions within the JFrame
		public class AL extends KeyAdapter{
			public void keyPressed(KeyEvent e){
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_SPACE && !isFlying && (((!falling || isOverSlantedPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT)) 
				&& jumpCount == 0 && !isHoldingJumpKey && !exitJump && !isFlying && !alert) 
				|| (doubleJumpCount < doubleJump && !isHoldingJumpKey && !falling))){
					doubleJumpCount++;
					jumpCount = 0;
					tempJumpCount = 0;
					isJumping = true;
					exitJump = false;
					isHoldingJumpKey = true;
					if(!bossDead && !wait && !alert){
						playSound(wingFlap);
					}	
				}
				if(keyCode == KeyEvent.VK_LEFT && !isRightTouching(x,y,BEAR_WIDTH, BEAR_HEIGHT)){
					//setXDirection(-1);
					isPressingLeft = true;
				}
				
				if(keyCode == KeyEvent.VK_RIGHT && !isLeftTouching(x,y,BEAR_WIDTH, BEAR_HEIGHT)){
					movingRight = true;
					//setXDirection(+1);
					isPressingRight = true;
				} 

				if(keyCode == KeyEvent.VK_DOWN ){
					setYDirection(+1);
					isPressingDown = true;
				}
				if(keyCode == KeyEvent.VK_UP ){
					isPressingUp = true;
				}
				
				if(keyCode == KeyEvent.VK_SPACE && !bossDead){
					alert = false;
					paused = false;
				}
				
				if(keyCode == KeyEvent.VK_SHIFT){
					iceTrue = !iceTrue;
					if(iceTrue){
						fireball = Color.BLUE;
					} else {
						fireball = Color.red;
					}
				}
				
				if(keyCode == KeyEvent.VK_CONTROL && hasFire && fireballSet.size() <= 0){
					int fx = 0;
					int fy = y + BEAR_HEIGHT / 2 + 5 ;
               /*
					if(character == right.getImage() || character == rightMove.getImage()){
						fireRight = true;
						fx = x + BEAR_WIDTH;
					} else if (character == left.getImage()){
						fx = x;
						fireRight = false;
					}
               */
					Fireball fireball = new Fireball (fx, fy, 10, 10, fireRight);
					fireballSet.add(fireball);
					//System.out.println(fireballSet.size());
				}
				if(keyCode == KeyEvent.VK_R && !bossDying){
					//resetLevel();	
				}
				if(keyCode == KeyEvent.VK_UP){
					doorTest();
				}
				if(keyCode == KeyEvent.VK_P && hasGameStarted && !bossDead){
					if(!wait){
						if(!isHoldingP){
							if(paused){
								paused = false;
							} else {
								paused = true;
							}
							isHoldingP = true;
						} 	
					}
				}
				if(keyCode == KeyEvent.VK_X && debug){
					//debug = false;
				} else if(keyCode == KeyEvent.VK_X && !debug){
					//debug = true;
				}
				if(keyCode == KeyEvent.VK_F && crystalCount >= 100){
					ateFlyCrystal = true;
					isFlying = true;
               used100Crystals = true;
               isInvincible = true;
					crystalCount -= 100;
				}
			}
			public void keyReleased(KeyEvent e){
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_LEFT){
					//setXDirection(0);
					isPressingLeft = false;
				}
				if(keyCode == KeyEvent.VK_RIGHT ){
					pressingRight = false;
					movingRight = false;
					//setXDirection(0);
					isPressingRight = false;
				}
				if(keyCode == KeyEvent.VK_UP){
					setYDirection(0);
					isPressingUp = false;
				}
				if(keyCode == KeyEvent.VK_DOWN){
					setYDirection(0);
					isPressingDown = false;
				}
				if(keyCode == KeyEvent.VK_SPACE){
					isHoldingJumpKey = false;
					jumpHoldCount = 0;
					exitJump = true;
					tempJumpCount = 0;
				}
				if(keyCode == KeyEvent.VK_UP){
					isHoldingE = false;
					EHoldCount = 0;
				}
				if(keyCode == KeyEvent.VK_P){
					isHoldingP = false;
				}
			}		
	}
		
		//Constructs the game
		public BearWithWings(){
			super();
	        //this.setTitle();
	        //this.setUndecorated(true);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setVisible(true); 
		  	
			//allows for mouse usage
			this.addMouseListener(new MouseHandler());
			this.addMouseMotionListener(new MouseHandler());
			
			//Load images of the Bear
			
			//left = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/BEAR WITH WINGS LEFT.gif");
			//right = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/BEAR WITH WINGS RIGHT.gif");
			//rightMove = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/BEAR WITH WINGS RIGHT.gif");
			//doorOpened =
			character = bearRight.getImage();
			//Game Properties
			platformSet = new ArrayList<Platform>();
			crystalSet = new ArrayList<Platform>();
			movingPlatformSet = new HashSet<MovingPlatform>();
			fireballSet = new ArrayList<Fireball>();
			enemySet = new ArrayList<Enemy>();
			deathAreaSet = new ArrayList<Platform>();
			flyCrystalSet = new ArrayList<Platform>();
			keySet = new ArrayList<Platform>();
			offplatformSet = new ArrayList<Rectangle>();
			doorSet = new ArrayList<Platform>();
			blueCrystalSet = new  ArrayList<Platform>();	
			redCrystalSet = new  ArrayList<Platform>();	
			greenCrystalSet = new  ArrayList<Platform>();	
			yellowCrystalSet = new  ArrayList<Platform>();
			//lowest point
			
			addKeyListener(new AL());
			setTitle("Bear With Wings");
			setIconImage(enemyIcon1Up.getImage());
			//Sets color of the background
			//setBackground(background);
			//Sets the size of the game window
			setSize(W_WIDTH, W_HEIGHT);
			//Determines if the player can resize the window
			//setResizable(true);
			setVisible(true);
			//determines how if the instance will exit upon closing
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//starting point for the bear
			//hub = true;
		}
		
		//Double buffering
		public void paint(Graphics g){
			dbImage = createImage(getWidth(), getHeight());
			dbg = dbImage.getGraphics();
			paintComponent(dbg);
			g.drawImage(dbImage, 0, 0, this);
		}
		
		//Draws the obstacles within the level
		public void drawLevel(Graphics g){
			if(!enemySet.isEmpty()){
				enemyTest();
			}
			if(!deathAreaSet.isEmpty()){
				deathAreaTest(g);
			}
			
				if(hub || blueDoor || door1 || redDoor || redDoor2 || redDoor3 
				|| greenDoor || greenDoor2 || greenDoor3 || greenDoor4 || 
				bossDoor || yellowDoor || yellowDoor2 || yellowDoor3 || yellowDoor4){
					levelTime = 0;
					//levelCrystals = 1;
					levelCrystals = 0;
					bear = new Rectangle(x, y, BEAR_WIDTH, BEAR_HEIGHT);
					platformSet.clear();
					crystalSet.clear();
					movingPlatformSet.clear();
					deathAreaSet.clear();
					flyCrystalSet.clear();
					keySet.clear();
					offplatformSet.clear();
					enemySet.clear();
					doorSet.clear();
					flyCrystalCount = 0;
					isInvincible = false;
					isFlying = false;
               used100Crystals = false;
					ateFlyCrystal = false;
					bearDead = false;
					bearDeadCount = 0;
					paused = false;
					bearYelling = false;
				}	
			 if(level.equals("hub")){
				 setBackground(new Color(0, 150, 250));
				if(hub){
					if(gameStart){
						x = 200;
						gameStart = false;
					} else {
						x = 670;
					}	
						y = 616;
						ysideScrollCount = -220;
						xsideScrollCount = 0;
						createHubWorld(g);
						adjustLevel();
						y = y + ysideScrollCount;
						hub = false;
						character = bearRight.getImage();
					}
				drawHubWorld(g);
				} else if(level.equals("hub1")){
					setBackground(new Color(0, 150, 250));
					if(door1){
						x = 350;
						y = 640;
						ysideScrollCount = -220;
						xsideScrollCount = 0;
						createWorld1Hub(g);
						adjustLevel();
						y = y + ysideScrollCount;
						door1 = false;
						character = bearRight.getImage();
					}
					
					drawWorld1Hub(g);
				} else if (level.equals("blue")){
					setBackground(new Color(0, 150, 250));
					if(blueDoor){
						x = 550;
						y = 590;
						ysideScrollCount = -185;
						xsideScrollCount = 0;
						blueDoor = false;
						createBlueLevel(g);
						if(enteredBlue){
							for(Platform crystal : blueCrystalSet){
								crystalSet.add(crystal);
							}
						}
						enteredBlue = true;
						adjustLevel();
						y = y + ysideScrollCount;
						
					}
					drawBlueLevel(g);
			} else if (level.equals("red")){
					setBackground(new Color(170, 170, 170));
					if(redDoor){
					x = 650;
					y = 390;
					ysideScrollCount = 0;
					xsideScrollCount = 0;
					createRedLevel(g);
					if(enteredRed){
						for(Platform crystal : redCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredRed = true;
					adjustLevel();
					y = y + ysideScrollCount;
					level = "red";
					redDoor = false;
				} else if(redDoor2){
					x = 631;
					y = 420;
					ysideScrollCount = 1180;
					xsideScrollCount = 300;
					createRedLevel(g);
					if(enteredRed){
						for(Platform crystal : redCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredRed = true;
					adjustLevel();
					level = "red";
					redDoor2 = false;
				} else if(redDoor3){
					x = 631;
					y = 420;
					ysideScrollCount = 5480;
					xsideScrollCount = -580;
					createRedLevel(g);
					if(enteredRed){
						for(Platform crystal : redCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredRed = true;
					adjustLevel();
					level = "red";
					redDoor3 = false;
				}
				drawRedLevel(g);
			} else if(level.equals("green")){
				setBackground(new Color(0, 0, 100));
				if(greenDoor){
					x = 631;
					y = 420;
					ysideScrollCount = -220;
					xsideScrollCount = 0;
					createGreenLevel();
					if(enteredGreen){
						for(Platform crystal : greenCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredGreen = true;
					adjustLevel();
					greenDoor = false;
				} else if(greenDoor2){
					x = 631;
					y = 420;
					ysideScrollCount = -5350;
					xsideScrollCount = 20;
					createGreenLevel();
					if(enteredGreen){
						for(Platform crystal : greenCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredGreen = true;
					adjustLevel();
					greenDoor2 = false;
				}else if(greenDoor3){
					x = 631;
					y = 420;
					ysideScrollCount = -3630;
					xsideScrollCount = 250;
					createGreenLevel();
					if(enteredGreen){
						for(Platform crystal : greenCrystalSet){
							crystalSet.add(crystal);
						}
					}
					character = bearLeft.getImage();
					enteredGreen = true;
					adjustLevel();
					greenDoor3 = false;
				}else if(greenDoor4){
					x = 631;
					y = 420;
					ysideScrollCount = -2900;
					xsideScrollCount = -280;
					createGreenLevel();
					if(enteredGreen){
						for(Platform crystal : greenCrystalSet){
							crystalSet.add(crystal);
						}
					}
					character = bearRight.getImage();
					enteredGreen = true;
					adjustLevel();
					greenDoor4 = false;
				}
				drawGreenLevel(g);
			}else if(level.equals("yellow")){
				setBackground(new Color(140, 0, 160));
				if(yellowDoor){
					x = 631;
					y = 418;
					ysideScrollCount = -220;
					xsideScrollCount = 0;
					createYellowLevel(g);
					if(enteredYellow){
						for(Platform crystal : yellowCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredYellow = true;
					adjustLevel();
					yellowDoor = false;
				} else if(yellowDoor2){
					x = 631;
					y = 418;
					ysideScrollCount = 1480;
					xsideScrollCount = 0;
					createYellowLevel(g);
					if(enteredYellow){
						for(Platform crystal : yellowCrystalSet){
							crystalSet.add(crystal);
						}
					}
					character = bearRight.getImage();
					enteredYellow = true;
					adjustLevel();
					yellowDoor2 = false;
				} else if(yellowDoor3){
					x = 631;
					y = 418;
					ysideScrollCount = -2120;
					xsideScrollCount = 12750;
					createYellowLevel(g);
					if(enteredYellow){
						for(Platform crystal : yellowCrystalSet){
							crystalSet.add(crystal);
						}
					}
					enteredYellow = true;
					adjustLevel();
					character = bearLeft.getImage();
					yellowDoor3 = false;
				} else if(yellowDoor4){
					x = 631;
					y = 418;
					ysideScrollCount = -400;
					xsideScrollCount = 12950;
					createYellowLevel(g);
					if(enteredYellow){
						for(Platform crystal : yellowCrystalSet){
							crystalSet.add(crystal);
						}
					}
					character = bearLeft.getImage();
					enteredYellow = true;
					adjustLevel();
					yellowDoor4 = false;
				}
				drawYellowLevel(g);
			} else if (level.equals("boss")){
				setBackground(new Color(30, 30, 30));
				if(bossDoor){
					x = 630;
					y = 418;
					ysideScrollCount = -220;
					xsideScrollCount = 0;
					createBossLevel1();
					adjustLevel();
					
					bossDoor = false;
				}
				drawBossLevel1(g);
		}
			 flyCrystalTest();
		}
		public void adjustLevel(){
			 	for(Platform rect : platformSet){
					rect.x = rect.origX - xsideScrollCount;
					rect.y = rect.origY + ysideScrollCount;
				}
			 	for(Platform rect : deathAreaSet){
					rect.x = rect.origX - xsideScrollCount;
					rect.y = rect.origY + ysideScrollCount;
				}
				for(Platform door : doorSet){
					door.x = door.origX - xsideScrollCount;
					door.y = door.origY + ysideScrollCount;
				}
				for(Enemy enemy : enemySet){
					enemy.setX(enemy.getOrigX() - xsideScrollCount);
					enemy.setY(enemy.getOrigY() + ysideScrollCount);
				}
				for(MovingPlatform mp : movingPlatformSet){
					mp.setX(mp.getOrigX() - xsideScrollCount);
					mp.setY(mp.getOrigY() + ysideScrollCount);
				}
				for(Platform crystal : crystalSet){
					crystal.x = crystal.origX - xsideScrollCount;
					crystal.y = crystal.origY + ysideScrollCount;
				}
		}
		
		// This sets the moving platforms to true
		public void resetLevel(){
			if(lastDoor.equals("door1")){
				door1 = true;
			} else if(lastDoor.equals("blueDoor")){
				blueDoor = true;
			} else if(lastDoor.equals("hubDoor1")){
				hub = true;
			}  else if(lastDoor.equals("redDoor")){
				redDoor = true;
			} else if(lastDoor.equals("redDoor2")){
				redDoor2 = true;
			} else if(lastDoor.equals("redDoor3")){
				redDoor3 = true;
			} else if(lastDoor.equals("greenDoor")){
				greenDoor = true;
			} else if(lastDoor.equals("greenDoor2")){
				greenDoor2 = true;
			} else if(lastDoor.equals("greenDoor3")){
				greenDoor3 = true;
			} else if(lastDoor.equals("greenDoor4")){
				greenDoor4 = true;
			} else if(lastDoor.equals("yellowDoor")){
				yellowDoor = true;
			} else if(lastDoor.equals("yellowDoor2")){
				yellowDoor2 = true;
			} else if(lastDoor.equals("yellowDoor3")){
				yellowDoor3 = true;
			} else if(lastDoor.equals("yellowDoor4")){
				yellowDoor4 = true;
			} else if(lastDoor.equals("bossDoor")){
				bossDoor = true;
				adjustLevel();
				bossJump = false;
				bossJumpCount = 0;
				barrierDown = false;
				hasEnteredBossArea = false; 
				bossMovedHorizontal = false;
				bossHorizontal = false;
				bossHorizontalCount = 0;
				bossDecideCount = 0;
			}
			for(MovingPlatform mp : movingPlatformSet){
				mp.setMovingNegative(true);
			}		
			enemySet.clear();
			xsideScrollCount = 0;
			ysideScrollCount = 0;
			flyCrystalCount = 0;
		}
		
		// This side scrolls the bear, you pass in two numbers representing the x bounds
		// and two numbers representing the y bounds. It's set to have the side scroll thresholds be 
		// 800 pixels and 100 pixels. 
		public void sideScroll(int xmin, int xmax, int ymin, int ymax){
			boolean moved = false;
			if(x >= ((W_WIDTH / 2) + 20) && xsideScrollCount + x + (W_WIDTH/2) - 83 < xmax){
				for(Enemy enemy : enemySet){
					enemy.setX(enemy.getX() - 1);
				}
				for(MovingPlatform mp : movingPlatformSet){
					mp.setX(mp.getX() - 1);
				}
				moved = true;
				x -= 1;
				xsideScrollCount += 1;
				hasSideScrolled = true;
			}
			if(xsideScrollCount > xmin && x <= ((W_WIDTH / 2) - 20) ){
				for(Enemy enemy : enemySet){
					enemy.setX(enemy.getX() + 1);
				}
				for(MovingPlatform mp : movingPlatformSet){
					mp.setX(mp.getX() + 1);
				}
				moved = true;
				x += 1;
				xsideScrollCount -= 1;
				if(xsideScrollCount == xmin){
					hasSideScrolled = false;
				}
			} 
			if(y <= (W_HEIGHT / 2) - 71 && ysideScrollCount  - (W_HEIGHT / 2) + 10 < ymax){
				for(Enemy enemy : enemySet){
					enemy.setY(enemy.getY() + 1);
				}
				for(MovingPlatform mp : movingPlatformSet){
					mp.setY(mp.getY() + 1);
				}
				moved = true;
				y += 1;
				ysideScrollCount += 1;
				hasSideScrolled = true;
			}
			if(ysideScrollCount + y + (W_HEIGHT / 2) + 100 > ymin && y >= (W_HEIGHT / 2) + 70 ){
				for(Enemy enemy : enemySet){
					enemy.setY(enemy.getY() - 1);
				}
				for(MovingPlatform mp : movingPlatformSet){
					mp.setY(mp.getY() - 1);
				}
				moved = true;
				y -= 1;
				ysideScrollCount -= 1;
				if(ysideScrollCount == xmin){
					hasSideScrolled = false;
				}
			}
			if(moved){
				for(Platform rect : platformSet){
					rect.x = rect.origX - xsideScrollCount;
					rect.y = rect.origY + ysideScrollCount;
				}
				for(Platform da : deathAreaSet){
					da.x = da.origX - xsideScrollCount;
					da.y = da.origY + ysideScrollCount;
				}
				for(Platform key : keySet){
					key.x = key.origX - xsideScrollCount;
					key.y = key.origY + ysideScrollCount;
				}
				for(Platform fc : flyCrystalSet){
					fc.x = fc.origX - xsideScrollCount;
					fc.y = fc.origY + ysideScrollCount;
				}
				for(Platform c : crystalSet){
					c.x = c.origX - xsideScrollCount;
					c.y = c.origY + ysideScrollCount;
				}
				for(Platform door : doorSet){
					door.x = door.origX - xsideScrollCount;
					door.y = door.origY + ysideScrollCount;
				}
			}
			
		}
		
//		public void removeRectangles(){
//			for(int i = 0; i < platformSet.size(); i++){
//			if(!((platformSet.get(i).x < W_WIDTH && platformSet.get(i).x + platformSet.get(i).width > 0 && 
//			platformSet.get(i).y + platformSet.get(i).height > 0 && platformSet.get(i).y < W_HEIGHT) || 
//			(platformSet.get(i).x <= 10 && platformSet.get(i).x + platformSet.get(i).width >= W_WIDTH))){
//				offplatformSet.add(platformSet.get(i));
//				platformSet.remove(i);
//			}
//		}
//		for(int i = 0; i < offplatformSet.size(); i++){
//			if((offplatformSet.get(i).x < W_WIDTH && offplatformSet.get(i).x + offplatformSet.get(i).width > 0 && 
//			offplatformSet.get(i).y + offplatformSet.get(i).height > 0 && offplatformSet.get(i).y < W_HEIGHT) || 
//			(offplatformSet.get(i).x <= 10 && offplatformSet.get(i).x + offplatformSet.get(i).width >= W_WIDTH)){
//				platformSet.add(offplatformSet.get(i));
//				offplatformSet.remove(i);
//			}	
//		}
//		}
		
		public void drawEnemy(Graphics g){
			for(Enemy enemy : enemySet){
				if(!enemy.isDead()){
					if(enemy.getType() == 1){
                  if(enemyAnimation1){
						   g.drawImage(enemyIcon1Up.getImage(), enemy.getX() - 7, enemy.getY() -11, this);
                  } else {
                   g.drawImage(enemyIcon1Down.getImage(), enemy.getX() - 7, enemy.getY() -11, this);
                  }
					} else if(enemy.getType() == 2){
						if(enemy.isMovingNegative()){
							g.drawImage(enemyIcon2Right.getImage(), enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight(), this);
						} else {
							g.drawImage(enemyIcon2Left.getImage(), enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight(), this);
						}	
					} else if(enemy.getType() == 3){
						if(enemy.isMovingNegative()){
							g.drawImage(enemyIcon3Right.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
						} else {
							g.drawImage(enemyIcon3Left.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
						}
					} else if(enemy.getType() == 4){
						if(x + xsideScrollCount < 2000){
							g.drawImage(boss1LeftIcon.getImage(), enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight(), this);
						} else {
							g.drawImage(boss1RightIcon.getImage(), enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight(), this);
						}
					} else if(enemy.getType() == 5){
						g.setColor(Color.RED);
                  if( x + xsideScrollCount >= 2000){
                     g.drawImage(fireBall2.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                  } else {
						   g.drawImage(fireBall.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), this);
                  }
					} else if(enemy.getType() == 6){
						if(enemy.isMovingNegative()){
							g.drawImage(enemyIcon4Right.getImage(), enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight(), this);
						} else {
							g.drawImage(enemyIcon4Left.getImage(), enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight(), this);
						}
					}
					//g.drawRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
				}	
			}
		}
		
		public void enemyTest(){
			for(int i = 0; i < enemySet.size(); i++){
				if(!enemySet.get(i).isDead()){
					enemyMove(enemySet.get(i));
					Rectangle temp = new Rectangle(enemySet.get(i).getX(), enemySet.get(i)
					.getY(), enemySet.get(i).getWidth(), enemySet.get(i).getHeight());
               if((character == bearJumpUpRight.getImage() || character == bearJumpUpLeft.getImage())){
                  bear = new Rectangle(x, y + 20, BEAR_WIDTH, BEAR_HEIGHT - 20);
               }
					if(bear.intersects(temp) && !bossFlash && !bossDying && !bossDead){
						if(isInvincible){
							enemySet.get(i).setDeath(true);
							playSound(stompSound);
						} else {
							bearDead = true;
							if(!bearYelling){
								playSound(bearYell);
								bearYelling = true;
							}
						}
					}
				}	
			}
		}
		
		public void drawBackgroundArt(Graphics g, int xshift, int yshift){
			//tree = firstTree.getImage();
			if(level.equals("blue")){
				g.drawImage(smallTree.getImage(), 250 + xshift, 522 - yshift , this);
				g.drawImage(smallTree.getImage(), 620 + xshift, 522 - yshift, this);
				
			}	
			
		}
		
		public void drawForegroundArt(Graphics g, int xshift, int yshift){
			if(level.equals("blue")){
				g.drawImage(bigTree.getImage(), 0 + xshift, 394 - yshift, this);
				g.drawImage(bigTree.getImage(), 750 + xshift, 394 - yshift , this);
			}
		}
		public void addSlantedPlatform(int x1, int y1,int x2, int y2, int height, Graphics g){
			double slope = ((double)(y2-y1)/(double)(x2-x1));
			int platforms = x2 - x1;
			if(slope == 1.0){
				for(int i = 0; i < platforms; i++){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + i, 2, height - i, "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1, height - i, "one"));
					}
				}
			} else if(slope == -1.0){
				for(int i = 0; i < platforms; i++){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 - i, 2,height - platforms - i, "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - i, 1, height - platforms - i, "one"));
					}
				}
			} else if(slope == 0.5){
				for(int i = 0; i < platforms; i+=2){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + (i/2), 2,height - (i/2), "one"));
						platformSet.add(new Platform(x1 + i, y1 + (i/2), 2, height - (i/2), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1,  height - (i/2), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + i, 1, height - (i/2), "one"));
					}
				}
			}else if(slope == -0.5){
				for(int i = 0; i < platforms; i+=2){
					if( i != platforms - 2){
						platformSet.add(new Platform(x1 + i - 1, y1 - (i/2), 2, height - ((platforms/2) - (i/2)), "one"));
						platformSet.add(new Platform(x1 + i, y1 - (i/2), 2, height - ((platforms/2) - (i/2)), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - (i/2), 1, height - ((platforms/2) - (i/2)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/2), 1, height - ((platforms/2) - (i/2)), "one"));
					}
				}
			} else if(slope == 0.3333333333333333){
				for(int i = 0; i < platforms; i+=3){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + (i/3), 2, height - (i/3), "one"));
						platformSet.add(new Platform(x1 + i, y1 + (i/3), 2, height - (i/3), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + (i/3), 2, height -(i/3), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1, height - (i/3), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + i, 1, height - (i/3), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + i, 1, height - (i/3), "one"));
					}
					
				}
			} else if(slope == -0.3333333333333333){
				for(int i = 0; i < platforms; i+=3){
					if(i != platforms-3){
						platformSet.add(new Platform(x1 + i - 1, y1 - (i/3), 2, height - ((platforms/3) - (i/3)), "one"));
						platformSet.add(new Platform(x1 + i, y1 - (i/3), 2, height - ((platforms/3) - (i/3)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/3), 2, height - ((platforms/3) - (i/3)), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - (i/3), 1,   height - ((platforms/3) - (i/3)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/3), 1,  height - ((platforms/3) - (i/3)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/3), 1,  height - ((platforms/3) - (i/3)), "one"));
					}
					
				}
			} else if(slope == 0.25){
				for(int i = 0; i < platforms; i+=4){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + (i/4), 2, height - (i/4), "one"));
						platformSet.add(new Platform(x1 + i, y1 + (i/4), 2, height - (i/4), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + (i/4), 2, height -(i/4), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + (i/4), 2, height -(i/4), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1, height - (i/4), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + i, 1, height - (i/4), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + i, 1, height - (i/4), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + i, 1, height - (i/4), "one"));
					}
				}
			} else if(slope == -0.25){
				for(int i = 0; i < platforms; i+=4){
					if( i != platforms - 4){
						platformSet.add(new Platform(x1 + i - 1, y1 - (i/4), 2, height - ((platforms/4) - (i/4)), "one"));
						platformSet.add(new Platform(x1 + i, y1 - (i/4), 2, height - ((platforms/4) - (i/4)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/4), 2, height - ((platforms/4) - (i/4)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/4), 2, height - ((platforms/4) - (i/4)), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - (i/4), 1, height - ((platforms/4) - (i/4)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/4), 1, height - ((platforms/4) - (i/4)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/4), 1, height - ((platforms/4) - (i/4)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/4), 1, height - ((platforms/4) - (i/4)), "one"));
					}
				}
			}  else if(slope == 0.2){
				for(int i = 0; i < platforms; i+=5){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + (i/5), 2, height - (i/5), "one"));
						platformSet.add(new Platform(x1 + i, y1 + (i/5), 2, height - (i/5), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + (i/5), 2, height -(i/5), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + (i/5), 2, height -(i/5), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + (i/5), 2, height -(i/5), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1, height - (i/5), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + i, 1, height - (i/5), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + i, 1, height - (i/5), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + i, 1, height - (i/5), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 + i, 1, height - (i/5), "one"));
					}
				}
			} else if(slope == -0.2){
				for(int i = 0; i < platforms; i+=5){
					if( i != platforms - 5){
						platformSet.add(new Platform(x1 + i - 1, y1 - (i/5), 2, height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i, y1 - (i/5), 2, height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/5), 2,height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/5), 2,height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/5), 2,height - ((platforms/5) - (i/5)), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - (i/5), 1, height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/5), 1,height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/5), 1, height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/5), 1, height - ((platforms/5) - (i/5)), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 - (i/5), 1, height - ((platforms/5) - (i/5)), "one"));
					}
				}
			} else if(slope == 0.16666666666666666){
				for(int i = 0; i < platforms; i+=6){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + (i/6), 2, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i, y1 + (i/6), 2, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + (i/6), 2, height -(i/6), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + (i/6), 2, height -(i/6), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + (i/6), 2, height -(i/6), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 + (i/6), 2, height -(i/6), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + i, 1, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + i, 1, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + i, 1, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 + i, 1, height - (i/6), "one"));
						platformSet.add(new Platform(x1 + i + 5, y1 + i, 1, height - (i/6), "one"));
					}
				}
			} else if(slope == -0.16666666666666666){
				for(int i = 0; i < platforms; i+=6){
					if( i != platforms - 6){
						platformSet.add(new Platform(x1 + i - 1, y1 - (i/6), 2, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i, y1 - (i/6), 2, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/6), 2, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/6), 2, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/6), 2, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 - (i/6), 2, height -((platforms/6) - (i/6)), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - (i/6), 1, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/6), 1, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/6), 1, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/6), 1, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 - (i/6), 1, height -((platforms/6) - (i/6)), "one"));
						platformSet.add(new Platform(x1 + i + 5, y1 - (i/6), 1, height -((platforms/6) - (i/6)), "one"));
					}
				}
			} else if(slope == 0.14285714285714285){
				for(int i = 0; i < platforms; i+=7){
					if(i > 0 && i != platforms){
						platformSet.add(new Platform(x1 + i - 1, y1 + (i/7), 2, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i, y1 + (i/7), 2, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + (i/7), 2, height -(i/7), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + (i/7), 2, height -(i/7), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + (i/7), 2, height -(i/7), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 + (i/7), 2, height -(i/7), "one"));
						platformSet.add(new Platform(x1 + i + 5, y1 + (i/7), 2, height -(i/7), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 + i, 1, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 + i, 1, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 + i, 1, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 + i, 1, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 + i, 1, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 5, y1 + i, 1, height - (i/7), "one"));
						platformSet.add(new Platform(x1 + i + 6, y1 + i, 1, height - (i/7), "one"));
					}
				}
			} else if(slope == -0.14285714285714285){
				for(int i = 0; i < platforms; i+=7){
					if( i != platforms - 7){
						platformSet.add(new Platform(x1 + i - 1, y1 - (i/7), 2, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i, y1 - (i/7), 2,  height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/7), 2, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/7), 2, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/7), 2, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 - (i/7), 2, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 5, y1 - (i/7), 2, height - ((platforms/7)-(i/7)), "one"));
					} else {
						platformSet.add(new Platform(x1 + i, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 1, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 2, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 3, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 4, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 5, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
						platformSet.add(new Platform(x1 + i + 6, y1 - (i/7), 1, height - ((platforms/7)-(i/7)), "one"));
					}
				}
			}		
		}
		
		public void deathAreaTest(Graphics g){
			for(Platform da : deathAreaSet){
				
				if(bear.intersects(new Rectangle(da.x, da.y, da.width, da.height)) && !isInvincible){
					bearDead = true;
					if(!bearYelling){
						playSound(bearYell);
						bearYelling = true;
					}
					
				}
			}			
		}
		public void flyCrystalTest(){
			for(int i = 0; i < flyCrystalSet.size(); i++){
				if(bear.intersects(new Rectangle(flyCrystalSet.get(i).x, flyCrystalSet.get(i).y, 
				flyCrystalSet.get(i).width, flyCrystalSet.get(i).height))){
					flyCrystalSet.remove(i);
					isFlying = true;
					isInvincible = true;
					ateFlyCrystal = true;
				}
			}
			if(ateFlyCrystal && !alert){
				flyCrystalCount++;
				if( flyCrystalCount == 8500){
					isFlying = false;
               used100Crystals = false;

					isInvincible = false;
					ateFlyCrystal = false;
					flyCrystalCount = 0;
				}
			}
		}
		public void keyTest(){
			for(int i = 0; i < keySet.size(); i++){
				if(bear.intersects(new Rectangle(keySet.get(i).x, keySet.get(i).y, keySet.get(i).width, keySet.get(i).height))){
					keySet.remove(i);
					playSound(keySound);
					if(level.equals("blue")){
						hasBlueKey = true;
					} else if (level.equals("red")){
						hasRedKey = true;
					} else if (level.equals("green")){
						hasGreenKey = true;
					} else if (level.equals("yellow")){
						hasYellowKey = true;
					}
				}
			}
		}
		public void drawDoor(Graphics g){
			for(Platform door : doorSet){
				if(door.type.equals("hub")){
					g.drawImage(doorClosed.getImage(), door.x, door.y, door.width, door.height, this);
				} else if(door.type.equals("blue")){
					g.drawImage(blueDoorIcon.getImage(), door.x, door.y, door.width, door.height, this);
				} else if(door.type.equals("hub1")){
					g.drawImage(hub1DoorIcon.getImage(), door.x, door.y, door.width, door.height, this);
				} else if(door.type.equals("red") || door.type.equals("red2") || door.type.equals("red3")){
					g.drawImage(redDoorIcon.getImage(), door.x, door.y, door.width, door.height, this);
				} else if(door.type.equals("green") || door.type.equals("green2") || door.type.equals("green3") || door.type.equals("green4")){
					g.drawImage(greenDoorIcon.getImage(), door.x, door.y, door.width, door.height, this);
				} else if(door.type.equals("boss")){
					g.drawImage(bossDoorIcon.getImage(), door.x, door.y, door.width, door.height, this);
				} else if(door.type.equals("yellow") || door.type.equals("yellow2") || door.type.equals("yellow3")|| door.type.equals("yellow4")){
					g.drawImage(yellowDoorIcon.getImage(), door.x, door.y, door.width, door.height, this);
				}
			}
		}
		public void doorTest(){
			for(int i = 0; i < doorSet.size(); i++){
				Rectangle temp = new Rectangle(doorSet.get(i).x, doorSet.get(i).y, doorSet.get(i).width, doorSet.get(i).height);
				if(bear.intersects(temp)){
					if(doorSet.get(i).type.equals("hub1")){
							door1 = true;
							lastDoor = "door1";
							level = "hub1";
							setBackground(new Color(0, 150, 250));
						} else if(doorSet.get(i).type.equals("blue")){
							blueDoor = true;
							level = "blue";
							lastDoor = "blueDoor";
						} else if(doorSet.get(i).type.equals("hub")){
							hub = true;
							level = "hub";
							lastDoor = "hubDoor1";
						} else if(doorSet.get(i).type.equals("red")){
							redDoor = true;
							level = "red";
							lastDoor = "redDoor";
							setBackground(new Color(170, 170, 170));
						} else if(doorSet.get(i).type.equals("red2")){
							redDoor2 = true;
							level = "red";
							lastDoor = "redDoor2";
							setBackground(new Color(170, 170, 170));
						} else if(doorSet.get(i).type.equals("red3")){
							redDoor3 = true;
							level = "red";
							lastDoor = "redDoor3";
							setBackground(new Color(170, 170, 170));
						} else if(doorSet.get(i).type.equals("green")){
							greenDoor = true;
							level = "green";
							lastDoor = "greenDoor";
							setBackground(new Color(0, 0, 100));
						} else if(doorSet.get(i).type.equals("green2")){
							greenDoor2 = true;
							level = "green";
							lastDoor = "greenDoor2";
							setBackground(new Color(0, 0, 100));
						} else if(doorSet.get(i).type.equals("green3")){
							greenDoor3 = true;
							level = "green";
							lastDoor = "greenDoor3";
							setBackground(new Color(0, 0, 100));
						} else if(doorSet.get(i).type.equals("green4")){
							greenDoor4 = true;
							level = "green";
							lastDoor = "greenDoor4";
							setBackground(new Color(0, 0, 100));
						} else if(doorSet.get(i).type.equals("boss") && hasBlueKey && hasRedKey && hasGreenKey && hasYellowKey){
							bossDoor = true;
							level = "boss";
							lastDoor = "bossDoor";
						} else if(doorSet.get(i).type.equals("yellow")){
							yellowDoor = true;
							level = "yellow";
							lastDoor = "yellowDoor";
						} else if(doorSet.get(i).type.equals("yellow2")){
							yellowDoor2 = true;
							level = "yellow";
							lastDoor = "yellowDoor2";
						} else if(doorSet.get(i).type.equals("yellow3")){
							yellowDoor3 = true;
							level = "yellow";
							lastDoor = "yellowDoor3";
						} else if(doorSet.get(i).type.equals("yellow4")){
							yellowDoor4 = true;
							level = "yellow";
							lastDoor = "yellowDoor4";
						}
					}
			}
		}
		public void inFrontOfDoorTest(Graphics g){
			g.setColor(Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD, 22));
			for(int i = 0; i < doorSet.size(); i++){
				Rectangle temp = new Rectangle(doorSet.get(i).x, doorSet.get(i).y, doorSet.get(i).width, doorSet.get(i).height);
				if(bear.intersects(temp)){
					if(!(doorSet.get(i).type.equals("boss"))){
						g.drawString("[^]", doorSet.get(i).x + 6, doorSet.get(i).y - 10);
                  g.drawString("l", doorSet.get(i).x + 17, doorSet.get(i).y - 10);
					}
					if(doorSet.get(i).type.equals("boss") && hasBlueKey && hasRedKey && hasGreenKey && hasYellowKey){
						g.drawString("[^]", doorSet.get(i).x + 10, doorSet.get(i).y - 16);
                  g.drawString("l", doorSet.get(i).x + 21, doorSet.get(i).y - 16);
					}
				}
			}	
		}
		public void killEnemy(){
			for(int i = 0; i < enemySet.size(); i++){
				if(enemySet.get(i).getX() + enemySet.get(i).getWidth() >= x + 1 && enemySet.get(i).getX() <= x + BEAR_WIDTH - 1 &&
				(enemySet.get(i).getY() == y + BEAR_HEIGHT ||  enemySet.get(i).getY() - 1 == y + BEAR_HEIGHT) && !enemySet.get(i).isDead()){
					if(enemySet.get(i).getType() != 4){
						enemySet.get(i).setDeath(true);
						playSound(stompSound);
					} else {
						playSound(bossScream);
						if(!bossFlash){
							enemySet.get(i).setHealth(enemySet.get(i).getHealth() - 1);
							if(enemySet.get(i).getHealth() == 0){
								bossDying = true;
							}
						}
					}	
					if(!isInvincible && !bossFlash){
						jumpedOnEnemy = true;
						isJumping = true;
						exitJump = false;
						jumpCount = 75;
						doubleJumpCount = 0;
						tempJumpCount = 0;
					}
					if(enemySet.get(i).getType() == 4){
						bossFlash = true;
					}
				}
				for(Platform da : deathAreaSet){
					Rectangle temp = new Rectangle(da.x, da.y, da.width, da.height);
					Rectangle temp2 = new Rectangle(enemySet.get(i).getX(), enemySet.get(i).getY(),
					enemySet.get(i).getWidth(), enemySet.get(i).getHeight());
					
					if(temp.intersects(temp2)){
						enemySet.get(i).setDeath(true);
					}
				}
			}
			if(jumpedOnEnemy && jumpedOnEnemyCount < 175){
				jumpedOnEnemyCount++;
			} 
			if(jumpedOnEnemyCount == 175){
				jumpedOnEnemy = false;
				jumpedOnEnemyCount = 0;
				exitJump = true;
			}
			
		}
		
		public void drawLevel0(Graphics g){
			
			if(platformSet.isEmpty()){
				Platform obs1 = new Platform(100, 380, 100, 100, "one");
				Platform obs2 = new Platform(700, 380, 100, 100, "one");
				Platform ground = new Platform(-500, 480, 2300, 50, "one");
				platformSet.add(ground);
				platformSet.add(obs1);
				platformSet.add(obs2);
			}
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			if(enemySet.isEmpty()){
//				Enemy enemy1 = new Enemy(300, 436, 50, 44, 3, 1, true);
//				Enemy enemy2 = new Enemy(600, 436, 50, 44, 3, 1, true);
//				enemySet.add(enemy1);
//				enemySet.add(enemy2);
			}
			if(!enemySet.isEmpty()){
				enemyTest();
			}
			drawEnemy(g);
			
		}
		
		public void createHubWorld(Graphics g){
			if(platformSet.isEmpty()){
				addPlatform(-1000, 700, 3000, 1000, "", "platform");
				addPlatform(-100, -100, 200, 1000, "", "platform");
				addPlatform(1200, -100, 200, 1000, "", "platform");
				addPlatform(-100, 200, 2000, 100, "", "platform");
			}
			if(doorSet.isEmpty()){
				addPlatform(600, 636, 40, 65, "hub1", "door");
			}
			
		}
		
		public void drawHubWorld(Graphics g){
			g.setColor(new Color(0, 200,90));
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			drawDoor(g);
			//doorTest(g);
		}
		
		public void createWorld1Hub(Graphics g){
			if(platformSet.isEmpty()){
				addPlatform(-1000, 700, 3000, 1000, "one", "platform");
				addPlatform(-1000, -100, 1050, 2000, "one", "platform");
				addPlatform(-1000, -100, 3000, 400, "one", "platform");
				addPlatform(1250, -100, 500, 2000, "one", "platform");
			}
			if(doorSet.isEmpty()){
				addPlatform(250, 636, 40, 65, "hub", "door");
				addPlatform(500, 636, 40, 65, "blue", "door");
				addPlatform(650, 636, 40, 65, "red", "door");
				addPlatform(800, 636, 40, 65, "green", "door");
				addPlatform(950, 636, 40, 65, "yellow", "door");
				addPlatform(1100, 620, 50, 80, "boss", "door");
			}
			
		}
		
		public void drawWorld1Hub(Graphics g){
			g.setColor(new Color(0, 170, 0));
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			if(hasBlueKey){
				g.drawImage(blueKey.getImage(), 505, 500 + ysideScrollCount, 25, 60, this);
			}
			if(hasRedKey){
				g.drawImage(redKey.getImage(), 655, 500 + ysideScrollCount, 25, 60, this);
			}
			if(hasGreenKey){
				g.drawImage(greenKey.getImage(), 805, 500 + ysideScrollCount, 25, 60, this);
			}
			if(hasYellowKey){
				g.drawImage(yellowKey.getImage(), 955, 500 + ysideScrollCount, 25, 60, this);
			}
			drawDoor(g);
			//doorTest(g);
		}
		
		public void createBlueLevel(Graphics g){
			if(platformSet.isEmpty()){
				Platform obs1 = new Platform(1750, 550, 200, 30, "one");
				Platform obs2 = new Platform(1400, 450, 180, 30, "one");
				Platform obs3 = new Platform(1800, 350, 220, 30, "one");
				Platform obs4 = new Platform(2250, 300, 400, 500, "one");
				Platform obs5 = new Platform(2500, 370, 1000, 400, "one");
				Platform obs6 = new Platform(3400, 300, 300, 400, "one");
				Platform obs7 = new Platform(3900, 450, 100, 100, "one");
				Platform obs8 = new Platform(4300, 400, 100, 30, "one");
				//Platform obs9 = new Platform(6080, 550, 120, 100, "one");
				Platform obs10 = new Platform(4840, 420, 250, 30, "one");
				Platform obs11 = new Platform(5080, 310, 250, 30, "one");
				Platform obs12 = new Platform(5320, 200, 250, 30, "one");
				Platform obs13 = new Platform(5560, 90, 250, 30, "one");
				Platform obs14 = new Platform(5750, -30, 1200, 30, "one");
				//Platform obs15 = new Platform(6199, 450, 130, 300, "one");
				//Platform obs16 = new Platform(6700, 450, 130, 300, "one");
				//Platform obs17 = new Platform(6829, 550, 120, 300, "one");
				Platform obs18 = new Platform(9000, 600, 80, 1000, "one");
				Platform obs19 = new Platform(9079, 550, 80, 2000, "one");
				Platform obs20 = new Platform(9158, 500, 80, 2500, "one");
				Platform obs21 = new Platform(9237, 450, 80, 3500, "one");
				Platform obs22 = new Platform(9316, 400, 80, 4000, "one");
				Platform obs23 = new Platform(9395, 350, 80, 4500, "one");
				Platform obs24 = new Platform(9474, 300, 80, 5000, "one");
				Platform obs25 = new Platform(9553, 250, 80, 5500, "one");
				Platform obs26 = new Platform(9632, 200, 80, 6000, "one");
				Platform obs27 = new Platform(9711, 150, 80, 6000, "one");
				Platform obs28 = new Platform(9790, 100, 80, 6500, "one");
				Platform obs29 = new Platform(9869, 50, 80, 7000, "one");
				Platform obs30 = new Platform(9948, 0, 80, 7500, "one");
				Platform obs31 = new Platform(10027, -50, 80, 8000, "one");
				Platform obs32 = new Platform(10106, -100, 80, 8500, "one");
				Platform obs33 = new Platform(10185, -150, 80, 9000, "one");
				Platform obs34 = new Platform(10264, -200, 80, 9500, "one");
				Platform obs35 = new Platform(10343, -250, 500, 1000, "one");
				Platform obs36 = new Platform(10400, -270, 50, 20, "one");
				Platform obs37 = new Platform(1500, 250, 100, 20, "one");
				Platform obs38 = new Platform(3980, 200, 70, 20, "one");
				Platform obs39 = new Platform(4450, 170, 50, 20, "one");
				Platform obs40 = new Platform(4150, 700, 60, 10, "one");
				Platform obs41 = new Platform(4240, 800, 60, 10, "one");
				Platform obs42 = new Platform(4150, 900, 60, 10, "one");
				Platform obs43 = new Platform(4240, 1000, 60, 10, "one");
				Platform obs44 = new Platform(4150, 1200, 60, 10, "one");
				Platform obs45 = new Platform(4950, 1350, 100, 400, "one");
				Platform obs46 = new Platform(5250, 1300, 100, 400, "one");
				Platform obs47 = new Platform(5550, 1450, 100, 400, "one");
				Platform obs48 = new Platform(5850, 1350, 100, 400, "one");
				Platform obs49 = new Platform(6150, 1250, 100, 400, "one");
				Platform obs50 = new Platform(8033, 1150, 60, 10, "one");
				Platform obs51 = new Platform(7950, 1040, 60, 10, "one");
				Platform obs52 = new Platform(8033, 930, 60, 10, "one");
				Platform obs53 = new Platform(7950, 820, 60, 10, "one");
				Platform obs54 = new Platform(8033, 710, 60, 10, "one");
				Platform obs55 = new Platform(8350, 550, 80, 20, "one");
				Platform obs56 = new Platform(8700, 550, 80, 20, "one");
				Platform obs57 = new Platform(8515, 450, 100, 20, "one");
				Platform ground1 = new Platform(-500, 650, 4650, 1500, "one");
				Platform ground2 = new Platform(4300, 650, 3650, 400, "one");
	        	Platform ground3 = new Platform(4150, 1400, 600, 2000, "one");
	        	Platform ground4 = new Platform(8093, 650, 1000, 1000, "one");
				platformSet.add(ground1);
				platformSet.add(ground2);
			   platformSet.add(ground3);
				platformSet.add(ground4);
				platformSet.add(obs1);
				platformSet.add(obs2);
				platformSet.add(obs3);
				platformSet.add(obs4);
				platformSet.add(obs5);
				platformSet.add(obs6);
				platformSet.add(obs7);
				platformSet.add(obs8);
				//platformSet.add(obs9);
				platformSet.add(obs10);
				platformSet.add(obs11);
				platformSet.add(obs12);
				platformSet.add(obs13);
				platformSet.add(obs14);
				//platformSet.add(obs15);
				//platformSet.add(obs16);
				//platformSet.add(obs17);
				platformSet.add(obs18);
				platformSet.add(obs19);
				platformSet.add(obs20);
				platformSet.add(obs21);
				platformSet.add(obs22);
				platformSet.add(obs23);
				platformSet.add(obs24);
				platformSet.add(obs25);
				platformSet.add(obs26);
				platformSet.add(obs27);
				platformSet.add(obs28);
				platformSet.add(obs29);
				platformSet.add(obs30);
				platformSet.add(obs31);
				platformSet.add(obs32);
				platformSet.add(obs33);
				platformSet.add(obs34);
				platformSet.add(obs35);
				platformSet.add(obs36);
				platformSet.add(obs37);
				platformSet.add(obs38);
				platformSet.add(obs39);
				platformSet.add(obs40);
				platformSet.add(obs41);
				platformSet.add(obs42);
				platformSet.add(obs43);
				platformSet.add(obs44);
				platformSet.add(obs45);
				platformSet.add(obs46);
				platformSet.add(obs47);
				platformSet.add(obs48);
				platformSet.add(obs49);
				platformSet.add(obs50);
				platformSet.add(obs51);
				platformSet.add(obs52);
				platformSet.add(obs53);
				platformSet.add(obs54);
				platformSet.add(obs55);
				platformSet.add(obs56);
				platformSet.add(obs57);
				addSlantedPlatform(5900, 650, 6500, 450, 400, g);
				addSlantedPlatform(6600, 400, 6700, 350, 400, g);
				addSlantedPlatform(6900, 350, 7000, 400, 400, g);
				addSlantedPlatform(7100, 450, 7700, 650, 400, g);
				addSlantedPlatform(6400, 1400, 7000, 1250, 400, g);
				addSlantedPlatform(6997, 1250, 7497, 1350, 400, g);
				addSlantedPlatform(7494, 1350, 8094, 1250, 400, g);
				
				addPlatform(4300, 645, 20, 20, "", "platform");
				addPlatform(8093, 645, 20, 20, "", "platform");
				addPlatform(7930, 645, 20, 20, "", "platform");
			}
			if(!enteredBlue){
				addPlatform(1545, 200, 10, 10, "first", "crystal");
				addPlatform(0, 0, 0, 0, "", "crystal");
				addPlatform(4008, 140, 10, 10, "", "crystal");
				addPlatform(4240, 60, 10, 10, "", "crystal");
				addPlatform(4468, 120, 10, 10, "", "crystal");
				addPlatform(4785, 160, 10, 10, "", "crystal");
				addPlatform(5020, 265, 10, 10, "", "crystal");
				addPlatform(5260, 155, 10, 10, "", "crystal");
				addPlatform(5500, 40, 10, 10, "", "crystal");
				addPlatform(5690, -80, 10, 10, "", "crystal");
				addPlatform(6000, -140, 10, 10, "", "crystal");
				addPlatform(6080, -180, 10, 10, "", "crystal");
				addPlatform(6160, -200, 10, 10, "", "crystal");
				addPlatform(6240, -180, 10, 10, "", "crystal");
				addPlatform(6320, -140, 10, 10, "", "crystal");
				addPlatform(6500, -140, 10, 10, "", "crystal");
				addPlatform(6580, -180, 10, 10, "", "crystal");
				addPlatform(6660, -200, 10, 10, "", "crystal");
				addPlatform(6740, -180, 10, 10, "", "crystal");
				addPlatform(6820, -140, 10, 10, "", "crystal");
				addPlatform(7220, -100, 10, 10, "", "crystal");
				addPlatform(5250, 540, 10, 10, "", "crystal");
				addPlatform(5330, 500, 10, 10, "", "crystal");
				addPlatform(5410, 480, 10, 10, "", "crystal");
				addPlatform(5490, 500, 10, 10, "", "crystal");
				addPlatform(5570, 540, 10, 10, "", "crystal");
				addPlatform(5595, 1360, 10, 10, "", "crystal");
				addPlatform(5140, 1200, 10, 10, "", "crystal");
				addPlatform(5440, 1200, 10, 10, "", "crystal");
				addPlatform(5740, 1270, 10, 10, "", "crystal");
				addPlatform(6040, 1200, 10, 10, "", "crystal");
				addPlatform(5595, 1300, 10, 10, "", "crystal");
				addPlatform(5595, 1330, 10, 10, "", "crystal");
				addPlatform(9200, 50, 10, 10, "", "crystal");
				addPlatform(9280, 130, 10, 10, "", "crystal");
				addPlatform(9120, -30, 10, 10, "", "crystal");
				addPlatform(9040, -110, 10, 10, "", "crystal");
				addPlatform(8960, -190, 10, 10, "", "crystal");
				addPlatform(9360, 50, 10, 10, "", "crystal");
				addPlatform(9440, -30, 10, 10, "", "crystal");
				addPlatform(9520, -110, 10, 10, "", "crystal");
				addPlatform(9600, -190, 10, 10, "", "crystal");
				addPlatform(9680, -270, 10, 10, "", "crystal");
				addPlatform(9760, -350, 10, 10, "", "crystal");
				addPlatform(9280, -30, 10, 10, "", "crystal");
				addPlatform(9360, -110, 10, 10, "", "crystal");
				addPlatform(9440, -190, 10, 10, "", "crystal");
				addPlatform(9520, -270, 10, 10, "", "crystal");
				addPlatform(9600, -350, 10, 10, "", "crystal");
				addPlatform(9680, -430, 10, 10, "", "crystal");
				addPlatform(9200, -110, 10, 10, "", "crystal");
				addPlatform(9280, -190, 10, 10, "", "crystal");
				addPlatform(9360, -270, 10, 10, "", "crystal");
				addPlatform(9440, -350, 10, 10, "", "crystal");
				addPlatform(9520, -430, 10, 10, "", "crystal");
				addPlatform(9600, -510, 10, 10, "", "crystal");
				addPlatform(9120, -190, 10, 10, "", "crystal");
				addPlatform(9200, -270, 10, 10, "", "crystal");
				addPlatform(9280, -350, 10, 10, "", "crystal");
				addPlatform(9360, -430, 10, 10, "", "crystal");
				addPlatform(9440, -510, 10, 10, "", "crystal");
				addPlatform(9520, -590, 10, 10, "", "crystal");
				addPlatform(9040, -270, 10, 10, "", "crystal");
				addPlatform(9120, -350, 10, 10, "", "crystal");
				addPlatform(9200, -430, 10, 10, "", "crystal");
				addPlatform(9280, -510, 10, 10, "", "crystal");
				addPlatform(9360, -590, 10, 10, "", "crystal");
				addPlatform(9440, -670, 10, 10, "", "crystal");
				addPlatform(7970, 540, 10, 10, "", "crystal");
				addPlatform(8020, 520, 10, 10, "", "crystal");
				addPlatform(8070, 540, 10, 10, "", "crystal");
				addPlatform(8880, -270, 10, 10, "", "crystal");
				addPlatform(8960, -350, 10, 10, "", "crystal");
				addPlatform(9040, -430, 10, 10, "", "crystal");
				addPlatform(9120, -510, 10, 10, "", "crystal");
				addPlatform(9200, -590, 10, 10, "", "crystal");
				addPlatform(9280, -670, 10, 10, "", "crystal");
				addPlatform(9360, -750, 10, 10, "", "crystal");
				addPlatform(6730, 250, 10, 10, "", "crystal");
				addPlatform(6860, 250, 10, 10, "", "crystal");
				addPlatform(6795, 220, 10, 10, "", "crystal");
			} 
			
			
			if(enemySet.isEmpty()){
				Enemy enemy1 = new Enemy(3000, 200, 50, 44, 2, 1, 0, false);
				Enemy enemy2 = new Enemy(4400, 500, 40, 70, 3, 2, 0, false);
				Enemy enemy3 = new Enemy(5800, -80, 50, 44, 3, 1, 0, false);
				Enemy enemy4 = new Enemy(5880, -80, 50, 44, 3, 1, 0, false);
				Enemy enemy5 = new Enemy(6000, 500, 40, 70, 2, 2, 0, false);
				Enemy enemy6 = new Enemy(5600, 500, 40, 70, 2, 2, 0, false);
				Enemy enemy7 = new Enemy(9400, 300, 50, 44, 2, 1, 0, false);
				Enemy enemy8 = new Enemy(9600, 100, 50, 44, 2, 1, 0, false);
				Enemy enemy9 = new Enemy(9800, -100, 50, 44, 2, 1, 0, false);
				//Enemy enemy10 = new Enemy(8210, 500, 50, 44, 2, 1, false);
				Enemy enemy11 = new Enemy(7500, 1200, 50, 44, 2, 1, 0, false);
				Enemy enemy12 = new Enemy(7900, 1100, 50, 44, 2, 1, 0, false);
				enemySet.add(enemy1);
				enemySet.add(enemy2);
				enemySet.add(enemy3);
				enemySet.add(enemy4);
				enemySet.add(enemy5);
				enemySet.add(enemy6);
				enemySet.add(enemy7);
				enemySet.add(enemy8);
				enemySet.add(enemy9);
				//enemySet.add(enemy10);
				enemySet.add(enemy11);
				enemySet.add(enemy12);
			}
			
			
			if(deathAreaSet.isEmpty()){
				Platform da1 = new Platform(6500, 600, 100, 50, "da");
				Platform da2 = new Platform(6700, 600, 200, 50, "da");
				Platform da3 = new Platform(7000, 600, 100, 50, "da");
				Platform da4 = new Platform(4000, 1500, 4000, 1000, "da");
				deathAreaSet.add(da1);
				deathAreaSet.add(da2);
				deathAreaSet.add(da3);
				deathAreaSet.add(da4);
			}
			
			if(doorSet.isEmpty()){
				Platform door1 = new Platform(400, 585, 40, 65, "hub1");
				Platform door2 = new Platform(10600, -315, 40, 65, "hub1");
				doorSet.add(door1);
				doorSet.add(door2);
			}
			if(flyCrystalSet.isEmpty()){
				Platform fc0 = new Platform(0, 0, 0, 0, "fc");
				Platform fc1 = new Platform(8550, 380, 30, 25, "fc");
				flyCrystalSet.add(fc0);
				flyCrystalSet.add(fc1);
			}
			if(!hasBlueKey){
				Platform zeroKey = new Platform(0, 0, 0, 0, "key");
				Platform blueKey = new Platform(10410, -332, 25, 60, "key");
				keySet.add(blueKey);
				keySet.add(zeroKey);
			}
			
			
		}
		public void drawBlueLevel(Graphics g){
			g.setColor(Color.RED);
			for(Platform da : deathAreaSet){
				g.fillRect(da.x, da.y, da.width, da.height);
			}
			g.setColor(new Color(50, 100, 0));
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			g.setColor(Color.YELLOW);
			for(Platform cryst : crystalSet){
				//g.fillRect(cryst.x, cryst.y, cryst.width, cryst.height);
            g.drawImage(crystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);

			}
			for(Platform key : keySet){
				g.drawImage(blueKey.getImage(), key.x, key.y, key.width, key.height, this);
			}
			g.setColor(Color.MAGENTA);
			for(Platform fc : flyCrystalSet){
				//g.fillRect(fc.x, fc.y, fc.width, fc.height);
            g.drawImage(powerCrystal.getImage(), fc.x, fc.y, fc.width, fc.height, this);

			}
			crystalRemove();
			drawEnemy(g);
			flyCrystalTest();
			keyTest();
			drawDoor(g);
			//doorTest(g);
		}
		
		public void createRedLevel(Graphics g){
			if(platformSet.isEmpty()){
				Platform obs1 = new Platform(-1000, 600, 6000, 1000, "one");
				Platform obs2 = new Platform(450, 450, 400, 300, "one");
				Platform obs3 = new Platform(1800, 100, 1000, 1500, "one");
				Platform obs4 = new Platform(-2000, 100, 1500, 6000, "one");	
				Platform obs5 = new Platform(1950, 20, 1000, 150, "one");	
				Platform obs6 = new Platform(2100, -60, 1000, 150, "one");	
				Platform obs7 = new Platform(145, -460, 1000, 650, "one");	
				Platform obs8 = new Platform(2250, -1400, 1500, 2000, "one");
				Platform obs9 = new Platform(1700, -170, 200, 10, "2");
				Platform obs10 = new Platform(1470, -275, 200, 10, "2");
				Platform obs11 = new Platform(1240, -380, 200, 10, "2");
				Platform obs12 = new Platform(145, -3100, 1000, 1600, "one");
				Platform obs13 = new Platform(-2000, 20, 1350, 150, "one");	
				Platform obs14 = new Platform(-2000, -60, 1200, 150, "one");	
				Platform obs15 = new Platform(-1700, -1900, 750, 600, "one");
				Platform obs16 = new Platform(-600, -170, 200, 10, "2");
				Platform obs17 = new Platform(-370, -275, 200, 10, "2");
				Platform obs18 = new Platform(-140, -380, 200, 10, "2");
				Platform obs19 = new Platform(1400, -500, 200, 10, "2");
				Platform obs20 = new Platform(1700, -600, 200, 10, "2");
				Platform obs21 = new Platform(1900, -700, 200, 10, "2");
				Platform obs22 = new Platform(1200, -720, 200, 10, "2");
				Platform obs23 = new Platform(1600, -800, 200, 10, "2");
				Platform obs24 = new Platform(1400, -880, 200, 10, "2");
				Platform obs25 = new Platform(1800, -1000, 200, 10, "2");
				Platform obs26 = new Platform(1350, -1100, 200, 10, "2");
				Platform obs27 = new Platform(1900, -1100, 200, 10, "2");
				Platform obs28 = new Platform(1650, -1200, 200, 10, "2");
				Platform obs29 = new Platform(1250, -1320, 200, 10, "2");
				Platform obs30 = new Platform(2000, -1320, 200, 10, "2");
				Platform obs31 = new Platform(1780, -1400, 200, 10, "2");
				Platform obs32 = new Platform(1400, -1430, 200, 10, "2");
				Platform obs33 = new Platform(1650, -1500, 200, 10, "2");
				Platform obs34 = new Platform(1900, -1610, 200, 10, "2");
				Platform obs35 = new Platform(1200, -1740, 200, 10, "2");
				Platform obs36 = new Platform(1600, -1680, 200, 10, "2");
				Platform obs37 = new Platform(1850, -1800, 200, 10, "2");
				Platform obs38 = new Platform(1650, -1900, 200, 10, "2");
				Platform obs39 = new Platform(1400, -2000, 200, 10, "2");
				Platform obs40 = new Platform(1780, -2020, 200, 10, "2");
				Platform obs41 = new Platform(2000, -2130, 200, 10, "2");
				Platform obs42 = new Platform(1550, -2100, 200, 10, "2");
				Platform obs43 = new Platform(1250, -2250, 200, 10, "2");
				Platform obs44 = new Platform(1850, -2260, 200, 10, "2");
				Platform obs45 = new Platform(1600, -2350, 200, 10, "2");
				Platform obs46 = new Platform(1300, -2450, 200, 10, "2");
				Platform obs47 = new Platform(1700, -2550, 200, 10, "2");
				Platform obs48 = new Platform(2000, -2650, 200, 10, "2");
				Platform obs49 = new Platform(1600, -2750, 200, 10, "2");
				Platform obs50 = new Platform(1250, -2850, 200, 10, "2");
				Platform obs51 = new Platform(1400, -2950, 200, 10, "2");
				Platform obs52 = new Platform(1180, -3050, 200, 10, "2");
				//other side
				Platform obs53 = new Platform(-600, -485, 200, 10, "2");
				Platform obs54 = new Platform(-800, -600, 200, 10, "2");
				Platform obs55 = new Platform(-400, -670, 200, 10, "2");
				Platform obs56 = new Platform(-650, -780, 200, 10, "2");
				Platform obs57 = new Platform(-150, -780, 200, 10, "2");
				Platform obs58 = new Platform(-450, -900, 200, 10, "2");
				Platform obs59 = new Platform(-850, -990, 200, 10, "2");
				Platform obs60 = new Platform(-200, -1010, 200, 10, "2");
				Platform obs61 = new Platform(-550, -1030, 200, 10, "2");
				Platform obs62 = new Platform(-750, -1150, 200, 10, "2");
				Platform obs63 = new Platform(-300, -1130, 200, 10, "2");
				Platform obs64 = new Platform(-400, -1250, 200, 10, "2");
				Platform obs65 = new Platform(-200, -1350, 200, 10, "2");
				Platform obs66 = new Platform(-600, -1450, 200, 10, "2");
				Platform obs67 = new Platform(-250, -1550, 200, 10, "2");
				Platform obs68 = new Platform(-900, -1560, 200, 10, "2");
				Platform obs69 = new Platform(-400, -1650, 200, 10, "2");
				Platform obs70 = new Platform(-800, -1680, 200, 10, "2");
				Platform obs71 = new Platform(-500, -1800, 200, 10, "2");
				Platform obs72 = new Platform(-150, -1730, 200, 10, "2");
				Platform obs73 = new Platform(-900, -1880, 200, 10, "2");
				Platform obs74 = new Platform(-250, -2000, 200, 10, "2");
				Platform obs75 = new Platform(-650, -1980, 200, 10, "2");
				Platform obs76 = new Platform(-600, -2100, 200, 10, "2");
				Platform obs77 = new Platform(-820, -2200, 200, 10, "2");
				Platform obs78 = new Platform(-300, -2220, 200, 10, "2");
				Platform obs79 = new Platform(-500, -2300, 200, 10, "2");
				Platform obs80 = new Platform(-150, -2360, 200, 10, "2");
				Platform obs81 = new Platform(-650, -2400, 200, 10, "2");
				Platform obs82 = new Platform(-300, -2480, 200, 10, "2");
				Platform obs83 = new Platform(-900, -2480, 200, 10, "2");
				Platform obs84 = new Platform(-500, -2590, 200, 10, "2");
				Platform obs85 = new Platform(-700, -2700, 200, 10, "2");
				Platform obs86 = new Platform(-200, -2720, 200, 10, "2");
				Platform obs87 = new Platform(-450, -2800, 200, 10, "2");
				Platform obs88 = new Platform(-200, -2870, 200, 10, "2");
				Platform obs89 = new Platform(-500, -2980, 200, 10, "2");
				Platform obs90 = new Platform(-180, -3030, 200, 10, "2");
				Platform obs91 = new Platform(503, -3159, 282, 100, "one");
				Platform obs92 = new Platform(-1000, -5000, 1145, 1600, "one");
				Platform obs93 = new Platform(1145, -5000, 1300, 1600, "one");
				Platform obs94 = new Platform(200, -4850, 180, 10, "2");
				Platform obs95 = new Platform(900, -4850, 180, 10, "2");
				Platform obs96 = new Platform(450, -4900, 380, 30, "one");
				Platform obs97 = new Platform(540, -4910, 40, 20, "one");
				Platform obs98 = new Platform(145, -1600, 750, 940, "one");
				Platform obs99 = new Platform(1140, -1600, 5, 940, "one");
				Platform obs100 = new Platform(890, -700, 250, 40, "one");
				Platform obs101 = new Platform(1040, -800, 100, 8, "one");
				Platform obs102 = new Platform(890, -900, 100, 8, "one");
				Platform obs103 = new Platform(1040, -1000, 100, 8, "one");
				Platform obs104 = new Platform(890, -1100, 100, 8, "one");
				Platform obs105 = new Platform(1040, -1200, 100, 8, "one");
				Platform obs106 = new Platform(890, -1300, 100, 8, "one");
				Platform obs107 = new Platform(-2700, -1230, 1750, 2000, "one");
				Platform obs108 = new Platform(-3000, -2230, 750, 2000, "one");
				Platform obs109 = new Platform(-2250, -1600, 200, 20, "one");
				Platform obs110 = new Platform(-1900, -1600, 200, 20, "one");
				Platform obs111 = new Platform(-2190, -1700, 100, 15, "one");
				Platform obs112 = new Platform(-1860, -1700, 100, 15, "one");
				Platform obs113 = new Platform(-2025, -1820, 100, 15, "one");
				Platform obs114 = new Platform(-1700, -5000, 750, 3030, "one");
				Platform obs115 = new Platform(-2500, -5000, 1150, 2930, "one");
				Platform obs116 = new Platform(2250, -1970, 600, 500, "one");
				Platform obs117 = new Platform(3300, -3500, 600, 3000, "one");
				Platform obs118 = new Platform(2250, -5000, 1600, 2930, "one");
				Platform obs119 = new Platform(3130, -1500, 250, 30, "one");
				Platform obs120 = new Platform(2750, -1600, 250, 30, "one");
				Platform obs121 = new Platform(3130, -1700, 250, 30, "one");
				Platform obs122 = new Platform(2750, -1800, 250, 30, "one");
				Platform obs123 = new Platform(3130, -1900, 250, 30, "one");
				Platform obs124 = new Platform(2250, -2000, 750, 30, "one");
				Platform obs125 = new Platform(145, -5100, 100, 100, "one");
				Platform obs126 = new Platform(245, -5200, 100, 100, "one");
				Platform obs127 = new Platform(1045, -5100, 100, 100, "one");
				Platform obs128 = new Platform(945, -5200, 100, 100, "one");
				Platform obs129 = new Platform(345, -5300, 600, 100, "one");
				Platform obs130 = new Platform(-2000, -6000, 1950, 1100, "one");
				Platform obs131 = new Platform(1340, -6000, 1950, 1100, "one");
				Platform obs132 = new Platform(-2000, -7000, 5000, 1300, "one");
				platformSet.add(obs1);
				platformSet.add(obs2);
				platformSet.add(obs3);
				platformSet.add(obs4);
				platformSet.add(obs5);
				platformSet.add(obs6);
				platformSet.add(obs7);
				platformSet.add(obs8);
				platformSet.add(obs9);
				platformSet.add(obs10);
				platformSet.add(obs11);
				platformSet.add(obs12);
				platformSet.add(obs13);
				platformSet.add(obs14);
				platformSet.add(obs15);
				platformSet.add(obs16);
				platformSet.add(obs17);
				platformSet.add(obs18);
				platformSet.add(obs19);
				platformSet.add(obs20);
				platformSet.add(obs21);
				platformSet.add(obs22);
				platformSet.add(obs23);
				platformSet.add(obs24);
				platformSet.add(obs25);
				platformSet.add(obs26);
				platformSet.add(obs27);
				platformSet.add(obs28);
				platformSet.add(obs29);
				platformSet.add(obs30);
				platformSet.add(obs31);
				platformSet.add(obs32);
				platformSet.add(obs33);
				platformSet.add(obs34);
				platformSet.add(obs35);
				platformSet.add(obs36);
				platformSet.add(obs37);
				platformSet.add(obs38);
				platformSet.add(obs39);
				platformSet.add(obs40);
				platformSet.add(obs41);
				platformSet.add(obs42);
				platformSet.add(obs43);
				platformSet.add(obs44);
				platformSet.add(obs45);
				platformSet.add(obs46);
				platformSet.add(obs47);
				platformSet.add(obs48);
				platformSet.add(obs49);
				platformSet.add(obs50);
				platformSet.add(obs51);
				platformSet.add(obs52);
				platformSet.add(obs53);
				platformSet.add(obs54);
				platformSet.add(obs55);
				platformSet.add(obs56);
				platformSet.add(obs57);
				platformSet.add(obs58);
				platformSet.add(obs59);
				platformSet.add(obs60);
				platformSet.add(obs61);
				platformSet.add(obs62);
				platformSet.add(obs63);
				platformSet.add(obs64);
				platformSet.add(obs65);
				platformSet.add(obs66);
				platformSet.add(obs67);
				platformSet.add(obs68);
				platformSet.add(obs69);
				platformSet.add(obs70);
				platformSet.add(obs71);
				platformSet.add(obs72);
				platformSet.add(obs73);
				platformSet.add(obs74);
				platformSet.add(obs75);
				platformSet.add(obs76);
				platformSet.add(obs77);
				platformSet.add(obs78);
				platformSet.add(obs79);
				platformSet.add(obs80);
				platformSet.add(obs81);
				platformSet.add(obs82);
				platformSet.add(obs83);
				platformSet.add(obs84);
				platformSet.add(obs85);
				platformSet.add(obs86);
				platformSet.add(obs87);
				platformSet.add(obs88);
				platformSet.add(obs89);
				platformSet.add(obs90);
				platformSet.add(obs91);
				platformSet.add(obs92);
				platformSet.add(obs93);
				platformSet.add(obs94);
				platformSet.add(obs95);
				platformSet.add(obs96);
				platformSet.add(obs97);
				platformSet.add(obs98);
				platformSet.add(obs99);
				platformSet.add(obs100);
				platformSet.add(obs101);
				platformSet.add(obs102);
				platformSet.add(obs103);
				platformSet.add(obs104);
				platformSet.add(obs105);
				platformSet.add(obs106);
				platformSet.add(obs107);
				platformSet.add(obs108);
				platformSet.add(obs109);
				platformSet.add(obs110);
				platformSet.add(obs111);
				platformSet.add(obs112);
				platformSet.add(obs113);
				platformSet.add(obs114);
				platformSet.add(obs115);
				platformSet.add(obs116);
				platformSet.add(obs117);
				platformSet.add(obs118);
				platformSet.add(obs119);
				platformSet.add(obs120);
				platformSet.add(obs121);
				platformSet.add(obs122);
				platformSet.add(obs123);
				platformSet.add(obs124);
				platformSet.add(obs125);
				platformSet.add(obs126);
				platformSet.add(obs127);
				platformSet.add(obs128);
				platformSet.add(obs129);
				platformSet.add(obs130);
				platformSet.add(obs131);
				platformSet.add(obs132);
				addSlantedPlatform(849,450,1449,750,200,g);
				addSlantedPlatform(-149,749,451,449,200,g);
				addSlantedPlatform(145, -3100, 505, -3160, 300, g);
				addSlantedPlatform(784, -3159, 1144, -3099, 300, g);
			}
			if(doorSet.isEmpty()){
				addPlatform(550, 385, 40, 65, "hub1", "door");
				addPlatform(650, -4965, 40, 65, "hub1", "door");
				addPlatform(1070, -765, 40, 65, "green2", "door");
				addPlatform(-20, -5065, 40, 65, "yellow3", "door");
			}
			if(!hasRedKey){
				Platform zeroKey = new Platform(0, 0, 0, 0, "key");
				Platform redKey = new Platform(546, -4970, 25, 60, "key");
				keySet.add(zeroKey);
				keySet.add(redKey);
			}
			if(movingPlatformSet.isEmpty()){
				MovingPlatform mp1 = new MovingPlatform(1400, 100, 300, 20, 450, 2, false, false, "1");
				MovingPlatform mp2 = new MovingPlatform(-400, 100, 300, 20, 450, 2, false, false, "1");
				MovingPlatform mp3 = new MovingPlatform(800, -3260, 150, 10, 450, 2, true, false, "2");
				MovingPlatform mp4 = new MovingPlatform(650, -3350, 150, 10, 250, 2, true, true, "2");
				MovingPlatform mp5 = new MovingPlatform(400, -3350, 150, 10, 250, 2, true, false, "2");
				MovingPlatform mp6 = new MovingPlatform(200, -3470, 150, 10, 550, 1, true, true, "2");
				MovingPlatform mp7 = new MovingPlatform(600, -3580, 150, 10, 350, 3, true, true, "2");
				MovingPlatform mp8 = new MovingPlatform(200, -3650, 150, 10, 350, 2, true, true, "2");
				MovingPlatform mp9 = new MovingPlatform(200, -3760, 150, 10, 650, 1, true, true, "2");
				MovingPlatform mp10 = new MovingPlatform(400, -3880, 150, 10, 350, 3, true, true, "2");
				MovingPlatform mp11 = new MovingPlatform(200, -3980, 150, 10, 250, 1, true, true, "2");
				MovingPlatform mp12 = new MovingPlatform(950, -3990, 150, 10, 250, 1, true, false, "2");
				MovingPlatform mp13 = new MovingPlatform(250, -4100, 150, 10, 450, 1, true, true, "2");
				MovingPlatform mp14 = new MovingPlatform(650, -4200, 150, 10, 250, 2, true, true, "2");
				MovingPlatform mp15 = new MovingPlatform(200, -4300, 150, 10, 350, 1, true, true, "2");
				MovingPlatform mp16 = new MovingPlatform(700, -4350, 150, 10, 200, 3, true, true, "2");
				MovingPlatform mp17 = new MovingPlatform(200, -4450, 150, 10, 700, 1, true, true, "2");
				MovingPlatform mp18 = new MovingPlatform(700, -4550, 150, 10, 700, 1, true, false, "2");
				MovingPlatform mp19 = new MovingPlatform(200, -4650, 150, 10, 400, 1, true, true, "2");
				MovingPlatform mp20 = new MovingPlatform(180, -4750, 150, 10, 800, 2, true, true, "2");
				MovingPlatform mp21 = new MovingPlatform(-2050, -1250, 150, 20, 350, 2, false, true, "1");
				mp1.setCounter(mp1.getLength());
				mp2.setCounter(mp2.getLength());
				mp3.setCounter(mp3.getLength());
				mp5.setCounter(mp5.getLength());
				mp12.setCounter(mp12.getLength());
				mp18.setCounter(mp18.getLength());
				movingPlatformSet.add(mp1);
				movingPlatformSet.add(mp2);
				movingPlatformSet.add(mp3);
				movingPlatformSet.add(mp4);
				movingPlatformSet.add(mp5);
				movingPlatformSet.add(mp6);
				movingPlatformSet.add(mp7);
				movingPlatformSet.add(mp8);
				movingPlatformSet.add(mp9);
				movingPlatformSet.add(mp10);
				movingPlatformSet.add(mp11);
				movingPlatformSet.add(mp12);
				movingPlatformSet.add(mp13);
				movingPlatformSet.add(mp14);
				movingPlatformSet.add(mp15);
				movingPlatformSet.add(mp16);
				movingPlatformSet.add(mp17);
				movingPlatformSet.add(mp18);
				movingPlatformSet.add(mp19);
				movingPlatformSet.add(mp20);
				movingPlatformSet.add(mp21);
			}
			if(enemySet.isEmpty()){
				Enemy enemy1 = new Enemy(1200, -950, 120, 20, 2, 3, 800, true);
				Enemy enemy2 = new Enemy(1200, -1550, 120, 20, 1, 3, 800, true);
				Enemy enemy3 = new Enemy(2000, -2500, 120, 20, 1, 3, 800, false);
				Enemy enemy4 = new Enemy(1200, -2700, 120, 20, 1, 3, 800, true);
				Enemy enemy5 = new Enemy(0, -1400, 120, 20, 2, 3, 800, false);
				Enemy enemy6 = new Enemy(0, -2150, 120, 20, 2, 3, 800, false);
				Enemy enemy7 = new Enemy(-800, -2050, 120, 20, 1, 3, 800, true);
				Enemy enemy8 = new Enemy(0, -2650, 120, 20, 1, 3, 900, false);
				Enemy enemy9 = new Enemy(0, -2930, 120, 20, 2, 3, 500, false);
				enemySet.add(enemy1);
				enemySet.add(enemy2);
				enemySet.add(enemy3);
				enemySet.add(enemy4);
				enemySet.add(enemy5);
				enemySet.add(enemy6);
				enemySet.add(enemy7);
				enemySet.add(enemy8);
				enemySet.add(enemy9);
			}
			if(!enteredRed){
				addPlatform(1050, -500, 10, 10, "", "crystal");
				addPlatform(950, -500, 10, 10, "", "crystal");
				addPlatform(230, -500, 10, 10, "", "crystal");
				addPlatform(330, -500, 10, 10, "", "crystal");
				addPlatform(430, -500, 10, 10, "", "crystal");
				addPlatform(850, -500, 10, 10, "", "crystal");
				addPlatform(520, -560, 10, 10, "", "crystal");
				addPlatform(750, -560, 10, 10, "", "crystal");
				addPlatform(580, -580, 10, 10, "", "crystal");
				addPlatform(690, -580, 10, 10, "", "crystal");
				addPlatform(635, -595, 10, 10, "", "crystal");
				addPlatform(470, -535, 10, 10, "", "crystal");
				addPlatform(805, -535, 10, 10, "", "crystal");
				addPlatform(1585, -650, 10, 10, "", "crystal");
				addPlatform(1885, -840, 10, 10, "", "crystal");
				addPlatform(1270, -740, 10, 10, "", "crystal");
				addPlatform(1320, -740, 10, 10, "", "crystal");
				addPlatform(1295, -760, 10, 10, "", "crystal");
				addPlatform(1650, -1020, 10, 10, "", "crystal");
				addPlatform(1550, -1320, 10, 10, "", "crystal");
				addPlatform(1445, -1130, 10, 10, "", "crystal");
				addPlatform(1800, -1620, 10, 10, "", "crystal");
				addPlatform(1800, -1840, 10, 10, "", "crystal");
				addPlatform(1480, -2100, 10, 10, "", "crystal");
				addPlatform(1900, -2120, 10, 10, "", "crystal");
				addPlatform(1335, -2300, 10, 10, "", "crystal");
				addPlatform(1355, -2300, 10, 10, "", "crystal");
				addPlatform(1335, -2280, 10, 10, "", "crystal");
				addPlatform(1355, -2280, 10, 10, "", "crystal");
				addPlatform(1550, -2580, 10, 10, "", "crystal");
				addPlatform(1900, -2760, 10, 10, "", "crystal");
				addPlatform(1295, -1800, 10, 10, "", "crystal");
				addPlatform(3060, -1640, 10, 10, "", "crystal");
				addPlatform(3060, -1740, 10, 10, "", "crystal");
				addPlatform(3060, -1840, 10, 10, "", "crystal");
				addPlatform(3060, -1940, 10, 10, "", "crystal");
				addPlatform(3210, -1530, 10, 10, "", "crystal");
				addPlatform(3210, -1730, 10, 10, "", "crystal");
				addPlatform(3210, -1930, 10, 10, "", "crystal");
				addPlatform(2920, -1630, 10, 10, "", "crystal");
				addPlatform(2920, -1830, 10, 10, "", "crystal");
				addPlatform(2920, -2030, 10, 10, "", "crystal");
				addPlatform(3060, -2040, 10, 10, "", "crystal");
				addPlatform(1900, -2360, 10, 10, "", "crystal");
				//
				addPlatform(-550, -650, 10, 10, "", "crystal");
				addPlatform(-400, -830, 10, 10, "", "crystal");
				addPlatform(-200, -830, 10, 10, "", "crystal");
				addPlatform(-700, -1700, 10, 10, "", "crystal");
				addPlatform(-730, -1730, 10, 10, "", "crystal");
				addPlatform(-700, -1760, 10, 10, "", "crystal");
				addPlatform(-670, -1730, 10, 10, "", "crystal");
				addPlatform(-730, -1020, 10, 10, "", "crystal");
				addPlatform(-780, -1020, 10, 10, "", "crystal");
				addPlatform(-755, -1045, 10, 10, "", "crystal");
				addPlatform(-180, -1290, 10, 10, "", "crystal");
				addPlatform(-260, -1360, 10, 10, "", "crystal");
				addPlatform(-500, -1200, 10, 10, "", "crystal");
				addPlatform(-270, -1020, 10, 10, "", "crystal");
				addPlatform(-230, -1760, 10, 10, "", "crystal");
				addPlatform(-115, -2020, 10, 10, "", "crystal");
				addPlatform(-155, -2020, 10, 10, "", "crystal");
				addPlatform(-195, -2020, 10, 10, "", "crystal");
				addPlatform(-580, -2290, 10, 10, "", "crystal");
				addPlatform(-240, -2400, 10, 10, "", "crystal");
				addPlatform(-410, -2460, 10, 10, "", "crystal");
				addPlatform(-660, -2540, 10, 10, "", "crystal");
				addPlatform(-260, -2750, 10, 10, "", "crystal");
				addPlatform(-540, -2840, 10, 10, "", "crystal");
				addPlatform(-330, -1570, 10, 10, "", "crystal");
				addPlatform(-1830, -1270, 10, 10, "", "crystal");
				addPlatform(-1790, -1270, 10, 10, "", "crystal");
				addPlatform(-1830, -1320, 10, 10, "", "crystal");
				addPlatform(-1790, -1320, 10, 10, "", "crystal");
				addPlatform(-1830, -1370, 10, 10, "", "crystal");
				addPlatform(-1790, -1370, 10, 10, "", "crystal");
				addPlatform(-1830, -1420, 10, 10, "", "crystal");
				addPlatform(-1790, -1420, 10, 10, "", "crystal");
				addPlatform(-1830, -1470, 10, 10, "", "crystal");
				addPlatform(-1790, -1470, 10, 10, "", "crystal");
				addPlatform(-2180, -1270, 10, 10, "", "crystal");
				addPlatform(-2140, -1270, 10, 10, "", "crystal");
				addPlatform(-2180, -1320, 10, 10, "", "crystal");
				addPlatform(-2140, -1320, 10, 10, "", "crystal");
				addPlatform(-2180, -1370, 10, 10, "", "crystal");
				addPlatform(-2140, -1370, 10, 10, "", "crystal");
				addPlatform(-2180, -1420, 10, 10, "", "crystal");
				addPlatform(-2140, -1420, 10, 10, "", "crystal");
				addPlatform(-2180, -1470, 10, 10, "", "crystal");
				addPlatform(-2140, -1470, 10, 10, "", "crystal");
				addPlatform(-2105, -1750, 10, 10, "", "crystal");
				addPlatform(-2145, -1750, 10, 10, "", "crystal");
				addPlatform(-1815, -1750, 10, 10, "", "crystal");
				addPlatform(-1855, -1750, 10, 10, "", "crystal");
				addPlatform(-2105, -1800, 10, 10, "", "crystal");
				addPlatform(-2145, -1800, 10, 10, "", "crystal");
				addPlatform(-1815, -1800, 10, 10, "", "crystal");
				addPlatform(-1855, -1800, 10, 10, "", "crystal");
				addPlatform(-2105, -1850, 10, 10, "", "crystal");
				addPlatform(-2145, -1850, 10, 10, "", "crystal");
				addPlatform(-1815, -1850, 10, 10, "", "crystal");
				addPlatform(-1855, -1850, 10, 10, "", "crystal");
				addPlatform(-1935, -1900, 10, 10, "", "crystal");
				addPlatform(-1980, -1900, 10, 10, "", "crystal");
				addPlatform(-2025, -1900, 10, 10, "", "crystal");
				addPlatform(-2105, -1900, 10, 10, "", "crystal");
				addPlatform(-2145, -1900, 10, 10, "", "crystal");
				addPlatform(-2065, -1900, 10, 10, "", "crystal");
				addPlatform(-1815, -1900, 10, 10, "", "crystal");
				addPlatform(-1855, -1900, 10, 10, "", "crystal");
				addPlatform(-1895, -1900, 10, 10, "", "crystal");
				addPlatform(-1935, -1950, 10, 10, "", "crystal");
				addPlatform(-1980, -1950, 10, 10, "", "crystal");
				addPlatform(-2025, -1950, 10, 10, "", "crystal");
				addPlatform(-2105, -1950, 10, 10, "", "crystal");
				addPlatform(-2065, -1950, 10, 10, "", "crystal");
				addPlatform(-1855, -1950, 10, 10, "", "crystal");
				addPlatform(-1895, -1950, 10, 10, "", "crystal");
				addPlatform(1020, -850, 10, 10, "", "crystal");
				addPlatform(1020, -950, 10, 10, "", "crystal");
				addPlatform(1020, -1050, 10, 10, "", "crystal");
				addPlatform(1020, -1150, 10, 10, "", "crystal");
				addPlatform(1020, -1250, 10, 10, "", "crystal");
				addPlatform(1000, -1423, 45, 40, "super", "crystal");
				addPlatform(1215, -5083, 45, 40, "super", "crystal");
				
			}
		}
		public void drawRedLevel(Graphics g){
			g.setColor(new Color(0, 90, 40));
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			for(MovingPlatform mp : movingPlatformSet){
				platformMove(mp);
				g.fillRect(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight());
			}
			for(Platform key : keySet){
				g.drawImage(redKey.getImage(), key.x, key.y, key.width, key.height, this);
			}
			g.setColor(Color.YELLOW);
			for(Platform cryst : crystalSet){
				//g.fillRect(crystal.x, crystal.y, crystal.width, crystal.height);
            if(cryst.type.equals("super")){
               g.drawImage(superCrystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);
            } else {
               g.drawImage(crystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);
            }
			}
			crystalRemove();
			drawEnemy(g);
			keyTest();
			drawDoor(g);
			//doorTest(g);
		}
		
		public void createGreenLevel(){
			if(platformSet.isEmpty()){
				addPlatform(50, 700, 1200, 200, "", "platform");
				addPlatform(50, 800, 200, 1100, "", "platform");
				addPlatform(1050, 800, 200, 1100, "", "platform");
				addPlatform(50, 1700, 500, 200, "", "platform");
				addPlatform(750, 1700, 500, 200, "", "platform");
				
				addPlatform(400, 2100, 150, 20, "2", "platform");
				addPlatform(750, 2100, 150, 20, "2", "platform");
				//inside block 1
				addPlatform(350, 1600, 150, 10, "2", "platform");
				addPlatform(800, 1600, 150, 10, "2", "platform");
				addPlatform(450, 1500, 150, 10, "2", "platform");
				addPlatform(700, 1500, 150, 10, "2", "platform");
				addPlatform(575, 1400, 150, 10, "2", "platform");
				addPlatform(450, 1300, 150, 10, "2", "platform");
				addPlatform(700, 1300, 150, 10, "2", "platform");
				addPlatform(350, 1200, 150, 10, "2", "platform");
				addPlatform(800, 1200, 150, 10, "2", "platform");
				addPlatform(450, 1100, 150, 10, "2", "platform");
				addPlatform(700, 1100, 150, 10, "2", "platform");
				addPlatform(350, 1000, 200, 10, "2", "platform");
				addPlatform(750, 1000, 200, 10, "2", "platform");
				//
				addPlatform(-1000, -600, 3000, 900, "", "platform");
				addPlatform(1650, -600, 600, 3000, "", "platform");
				addPlatform(-2000, -600, 1650, 3000, "", "platform");
				addPlatform(-5000, 2300, 5450, 400, "", "platform");
				addPlatform(850, 2300, 5000, 400, "", "platform");
				
				addPlatform(50, 3100, 1200, 200, "", "platform");
				addPlatform(50, 3200, 200, 1100, "", "platform");
				addPlatform(50, 4120, 1200, 180, "", "platform");
				addPlatform(1050, 3200, 200, 1100, "", "platform");
				//inside middle block
				addPlatform(250, 3400, 600, 30, "", "platform");
				addPlatform(450, 3520, 600, 30, "", "platform");
				
				addPlatform(250, 3640, 600, 30, "", "platform");
				addPlatform(450, 3760, 600, 30, "", "platform");
				
				addPlatform(250, 3880, 600, 30, "", "platform");
				addPlatform(450, 4000, 600, 30, "", "platform");
				//
				
				addPlatform(1650, 2600, 400, 900, "", "platform");
				addPlatform(1650, 3900, 400, 5000, "", "platform");
				addPlatform(-2000, -600, 1650, 3000, "", "platform");
				addPlatform(-5000, 2300, 5450, 400, "", "platform");
				addPlatform(-5000, 4700, 5450, 400, "", "platform");
				addPlatform(850, 4700, 5000, 400, "", "platform");
				
				addPlatform(50, 5500, 1200, 200, "", "platform");
				addPlatform(50, 5600, 200, 1100, "", "platform");
				addPlatform(1050, 5600, 200, 1100, "", "platform");
				addPlatform(50, 6500, 500, 200, "", "platform");
				addPlatform(750, 6500, 500, 200, "", "platform");
				//inside bottom block
				addPlatform(300, 6400, 200, 15, "", "platform");
				addPlatform(300, 6385, 15, 15, "", "platform");
				addPlatform(485, 6385, 15, 15, "", "platform");
				
				addPlatform(800, 6400, 200, 15, "", "platform");
				addPlatform(800, 6385, 15, 15, "", "platform");
				addPlatform(985, 6385, 15, 15, "", "platform");
				
				addPlatform(550, 6280, 200, 15, "", "platform");
				addPlatform(550, 6265, 15, 15, "", "platform");
				addPlatform(735, 6265, 15, 15, "", "platform");
				
				addPlatform(300, 6160, 200, 15, "", "platform");
				addPlatform(300, 6145, 15, 15, "", "platform");
				addPlatform(485, 6145, 15, 15, "", "platform");
				
				addPlatform(800, 6160, 200, 15, "", "platform");
				addPlatform(800, 6145, 15, 15, "", "platform");
				addPlatform(985, 6145, 15, 15, "", "platform");
				
				addPlatform(550, 6040, 200, 15, "", "platform");
				addPlatform(550, 6025, 15, 15, "", "platform");
				addPlatform(735, 6025, 15, 15, "", "platform");
				
				addPlatform(300, 5920, 200, 15, "", "platform");
				addPlatform(300, 5905, 15, 15, "", "platform");
				addPlatform(485, 5905, 15, 15, "", "platform");
				
				addPlatform(800, 5920, 200, 15, "", "platform");
				addPlatform(800, 5905, 15, 15, "", "platform");
				addPlatform(985, 5905, 15, 15, "", "platform");
				
				addPlatform(560, 5830, 180, 15, "", "platform");
				//
				addPlatform(300, 7000, 200, 15, "2", "platform");
				addPlatform(800, 7000, 200, 15, "2", "platform");
				
				addPlatform(-750, 2500, 400, 1000, "", "platform");
				addPlatform(-750, 3900, 400, 5000, "", "platform");
				addPlatform(-750, 7100, 3000, 600, "", "platform");
				
				addPlatform(2450, 3100, 1200, 200, "", "platform");
				addPlatform(2450, 3200, 200, 1100, "", "platform");
				addPlatform(3450, 3200, 200, 400, "", "platform");
				addPlatform(3450, 3810, 200, 400, "", "platform");
				addPlatform(2450, 4100, 1200, 200, "", "platform");
				//inside right block
				addPlatform(3300, 3800, 350, 10, "2", "platform");
				addPlatform(3300, 3900, 150, 10, "2", "platform");
				addPlatform(3300, 3695, 150, 10, "2", "platform");
				addPlatform(3300, 3590, 150, 10, "2", "platform");
				addPlatform(3300, 4000, 150, 10, "2", "platform");
				addPlatform(3300, 3500, 150, 10, "2", "platform");
				addPlatform(2800, 3400, 800, 10, "2", "platform");
				addPlatform(3150, 3410, 150, 600, "", "platform");
				addPlatform(2650, 3500, 350, 10, "2", "platform");
				addPlatform(2800, 3600, 400, 10, "2", "platform");
				addPlatform(2650, 3700, 350, 10, "2", "platform");
				addPlatform(3080, 3930, 150, 80, "", "platform");
				addPlatform(2710, 4000, 400, 10, "", "platform");
				addPlatform(2710, 3930, 10, 80, "", "platform");
				
				addPlatform(4050, 2600, 600, 2500, "", "platform");
				addPlatform(-2350, 3100, 1200, 200, "", "platform");
				addPlatform(-2350, 3200, 200, 400, "", "platform");
				addPlatform(-2350, 3800, 200, 400, "", "platform");
				addPlatform(-1350, 3200, 200, 1200, "", "platform");
				addPlatform(-2350, 4200, 1200, 200, "", "platform");
				//inside left block
				addPlatform(-1860, 3850, 200, 20, "", "platform");
				addPlatform(-2050, 3750, 100, 10, "", "platform");
				addPlatform(-1970, 3700, 100, 10, "", "platform");
				addPlatform(-1890, 3660, 100, 10, "", "platform");
				addPlatform(-1810, 3630, 100, 10, "", "platform");
				addPlatform(-1730, 3660, 100, 10, "", "platform");
				addPlatform(-1650, 3700, 100, 10, "", "platform");
				addPlatform(-1570, 3750, 100, 10, "", "platform");
				addPlatform(-2100, 3810, 100, 10, "", "platform");
				addPlatform(-2050, 3870, 100, 10, "", "platform");
				addPlatform(-1970, 3920, 100, 10, "", "platform");
				addPlatform(-1890, 3960, 100, 10, "", "platform");
				addPlatform(-1810, 3990, 100, 10, "", "platform");
				addPlatform(-1730, 3960, 100, 10, "", "platform");
				addPlatform(-1650, 3920, 100, 10, "", "platform");
				addPlatform(-1570, 3870, 100, 10, "", "platform");
				addPlatform(-1430, 3850, 100, 10, "", "platform");
				addPlatform(-2100, 3500, 120, 10, "", "platform");
				addPlatform(-1530, 3500, 120, 10, "", "platform");
				//
				addPlatform(-4000, 2600, 1250, 4000, "", "platform");
				addPlatform(-1000, 5000, 500, 3000, "", "platform");
				addPlatform(1800, 5000, 500, 3000, "", "platform");
				addPlatform(-380, 800, 180, 15, "", "platform");
				addPlatform(-100, 920, 180, 15, "", "platform");
				addPlatform(-380, 1000, 180, 15, "", "platform");
				addPlatform(-100, 1120, 180, 15, "", "platform");
				addPlatform(-380, 1200, 180, 15, "", "platform");
				addPlatform(-100, 1320, 180, 15, "", "platform");
				addPlatform(-380, 1400, 180, 15, "", "platform");
				addPlatform(-100, 1520, 180, 15, "", "platform");
				addPlatform(-380, 1600, 180, 15, "", "platform");
				addPlatform(-100, 1720, 180, 15, "", "platform");
				addPlatform(-380, 1800, 180, 15, "", "platform");
				addPlatform(-100, 1885, 180, 15, "", "platform");
				addPlatform(-380, 2000, 180, 15, "", "platform");
				addPlatform(-100, 2100, 180, 15, "2", "platform");
				addPlatform(150, 2200, 180, 15, "2", "platform");
				addPlatform(1500, 800, 180, 15, "", "platform");
				addPlatform(1220, 920, 180, 15, "", "platform");
				addPlatform(1500, 1000, 180, 15, "", "platform");
				addPlatform(1220, 1120, 180, 15, "", "platform");
				addPlatform(1500, 1200, 180, 15, "", "platform");
				addPlatform(1220, 1320, 180, 15, "", "platform");
				addPlatform(1500, 1400, 180, 15, "", "platform");
				addPlatform(1220, 1520, 180, 15, "", "platform");
				addPlatform(1500, 1600, 180, 15, "", "platform");
				addPlatform(1220, 1720, 180, 15, "", "platform");
				addPlatform(1500, 1800, 180, 15, "", "platform");
				addPlatform(1220, 1885, 180, 15, "", "platform");
				addPlatform(1500, 2000, 180, 15, "", "platform");
				addPlatform(1220, 2100, 180, 15, "2", "platform");
				addPlatform(970, 2200, 180, 15, "2", "platform");
				
				addPlatform(-200, 3180, 100, 15, "2", "platform");
				addPlatform(-370, 3285, 100, 15, "2", "platform");
				addPlatform(-30, 3285, 100, 15, "2", "platform");
				
				addPlatform(-200, 3380, 100, 15, "2", "platform");
				addPlatform(-370, 3485, 100, 15, "2", "platform");
				addPlatform(-30, 3485, 100, 15, "2", "platform");
				
				addPlatform(-370, 3900, 100, 15, "2", "platform");
				addPlatform(-30, 3900, 100, 15, "2", "platform");
				addPlatform(-200, 4000, 100, 15, "2", "platform");
				
				addPlatform(-370, 4100, 100, 15, "2", "platform");
				addPlatform(-30, 4100, 100, 15, "2", "platform");
				addPlatform(-200, 4200, 100, 15, "2", "platform");
				
				addPlatform(-370, 4285, 100, 15, "2", "platform");
				addPlatform(-30, 4285, 100, 15, "2", "platform");
				
				addPlatform(-230, 4400, 180, 15, "2", "platform");
				addPlatform(-230, 4500, 180, 15, "2", "platform");
				addPlatform(-230, 4600, 180, 15, "2", "platform");
				
				addPlatform(1400, 3180, 100, 15, "2", "platform");
				addPlatform(1570, 3285, 100, 15, "2", "platform");
				addPlatform(1230, 3285, 100, 15, "2", "platform");
				
				addPlatform(1400, 3380, 100, 15, "2", "platform");
				addPlatform(1570, 3485, 100, 15, "2", "platform");
				addPlatform(1230, 3485, 100, 15, "2", "platform");
				
				addPlatform(1400, 4000, 100, 15, "2", "platform");
				addPlatform(1570, 3900, 100, 15, "2", "platform");
				addPlatform(1230, 3900, 100, 15, "2", "platform");
				
				addPlatform(1400, 4200, 100, 15, "2", "platform");
				addPlatform(1570, 4100, 100, 15, "2", "platform");
				addPlatform(1230, 4100, 100, 15, "2", "platform");
				
				addPlatform(1570, 4285, 100, 15, "2", "platform");
				addPlatform(1230, 4285, 100, 15, "2", "platform");
				
				addPlatform(1370, 4400, 180, 15, "2", "platform");
				addPlatform(1370, 4500, 180, 15, "2", "platform");
				addPlatform(1370, 4600, 180, 15, "2", "platform");
				
				addPlatform(0, 6980, 180, 15, "2", "platform");
				addPlatform(-100, 6880, 180, 15, "2", "platform");
				addPlatform(-200, 6780, 180, 15, "2", "platform");
				
				addPlatform(1320, 6780, 180, 15, "2", "platform");
				addPlatform(1220, 6880, 180, 15, "2", "platform");
				addPlatform(1120, 6980, 180, 15, "2", "platform");
				//
				addPlatform(2290, 3150, 80, 10, "2", "platform");
				addPlatform(2210, 3220, 80, 10, "2", "platform");
				addPlatform(2130, 3290, 80, 10, "2", "platform");
				addPlatform(2210, 3360, 80, 10, "2", "platform");
				
				addPlatform(2290, 3430, 80, 10, "2", "platform");
				addPlatform(2210, 3500, 80, 10, "2", "platform");
				addPlatform(2130, 3570, 80, 10, "2", "platform");
				addPlatform(2210, 3640, 80, 10, "2", "platform");
				
				addPlatform(2290, 3710, 80, 10, "2", "platform");
				addPlatform(2210, 3780, 80, 10, "2", "platform");
				addPlatform(2130, 3850, 80, 10, "2", "platform");
				addPlatform(2210, 3920, 80, 10, "2", "platform");
				addPlatform(2290, 3990, 80, 10, "2", "platform");
				addPlatform(2210, 4060, 80, 10, "2", "platform");
				addPlatform(2130, 4130, 80, 10, "2", "platform");
				addPlatform(2210, 4200, 80, 10, "2", "platform");
				addPlatform(2290, 4270, 80, 10, "2", "platform");
				addPlatform(2210, 4340, 80, 10, "2", "platform");
				addPlatform(2130, 4410, 80, 10, "2", "platform");
				addPlatform(2210, 4480, 80, 10, "2", "platform");
				addPlatform(2290, 4550, 80, 10, "2", "platform");
				addPlatform(2210, 4620, 80, 10, "2", "platform");
				////////
				addPlatform(3730, 3150, 80, 10, "2", "platform");
				addPlatform(3810, 3220, 80, 10, "2", "platform");
				addPlatform(3890, 3290, 80, 10, "2", "platform");
				addPlatform(3810, 3360, 80, 10, "2", "platform");
				addPlatform(3730, 3430, 80, 10, "2", "platform");
				addPlatform(3810, 3500, 80, 10, "2", "platform");
				addPlatform(3890, 3570, 80, 10, "2", "platform");
				addPlatform(3810, 3640, 80, 10, "2", "platform");
				addPlatform(3730, 3710, 80, 10, "2", "platform");
				addPlatform(3810, 3780, 80, 10, "2", "platform");
				addPlatform(3890, 3850, 80, 10, "2", "platform");
				addPlatform(3810, 3920, 80, 10, "2", "platform");
				addPlatform(3730, 3990, 80, 10, "2", "platform");
				addPlatform(3810, 4060, 80, 10, "2", "platform");
				addPlatform(3890, 4130, 80, 10, "2", "platform");
				addPlatform(3810, 4200, 80, 10, "2", "platform");
				addPlatform(3730, 4270, 80, 10, "2", "platform");
				addPlatform(3810, 4340, 80, 10, "2", "platform");
				addPlatform(3890, 4410, 80, 10, "2", "platform");
				addPlatform(3810, 4480, 80, 10, "2", "platform");
				addPlatform(3730, 4550, 80, 10, "2", "platform");
				addPlatform(3810, 4620, 80, 10, "2", "platform");
				
				addPlatform(-1450, 3050, 50, 80, "", "platform");
				addPlatform(-2100, 3050, 50, 80, "", "platform");
				addPlatform(-1780, 3050, 50, 80, "", "platform");
				
				addPlatform(2600, 3050, 50, 80, "", "platform");
				addPlatform(2900, 3030, 50, 80, "", "platform");
				addPlatform(3200, 3030, 50, 80, "", "platform");
				addPlatform(3450, 3050, 50, 80, "", "platform");
				addPlatform(850, 4650, 50, 80, "", "platform");
				addPlatform(400, 4650, 50, 80, "", "platform");
				
				addPlatform(-1725, 3845, 35, 20, "", "platform");
			}
			if(deathAreaSet.isEmpty()){
				Platform da = new Platform(2720, 3940, 380, 60, "");
				deathAreaSet.add(da);
			}
			if(enemySet.isEmpty()){
				//top block
				addEnemy(300, 1450, 120, 20, 2, 3, 600, true);
				addEnemy(900, 1250, 120, 20, 2, 3, 600, false);
				addEnemy(300, 1050, 120, 20, 2, 3, 600, true);
				//bottom block
				addEnemy(350, 6350, 50, 44, 2, 1, 0, true);
				addEnemy(850, 6300, 40, 70, 3, 2, 0, true);
				addEnemy(630, 6220, 50, 44, 3, 1, 0, true);
				addEnemy(350, 6080, 40, 70, 3, 2, 0, true);
				addEnemy(850, 6080, 50, 44, 3, 1, 0, true);
				addEnemy(670, 5965, 40, 70, 3, 2, 0, true);
				addEnemy(360, 5870, 50, 44, 4, 1, 0, true);
				addEnemy(860, 5870, 50, 44, 4, 1, 0, true);
				//middle block
				addEnemy(610, 3450, 50, 44, 3, 1, 0, false);
				addEnemy(610, 3550, 50, 44, 3, 1, 0, true);
				addEnemy(610, 3690, 50, 44, 3, 1, 0, false);
				addEnemy(610, 3810, 50, 44, 3, 1, 0, true);
				addEnemy(1000, 4630, 50, 44, 2, 1, 0, true);
				addEnemy(200, 4630, 50, 44, 2, 1, 0, true);
				addEnemy(1650, 3850, 120, 20, 2, 3, 260, true);
				addEnemy(-750, 3850, 120, 20, 2, 3, 260, true);
				//left block
				addEnemy(-1550, 3050, 50, 44, 2, 1, 0, false);
				addEnemy(-1850, 3050, 50, 44, 2, 1, 0, false);
				addEnemy(-1500, 4500, 120, 20, 1, 3, 600, false);
				addEnemy(-1600, 4550, 100, 10, 1, 3, 400, false);
				addEnemy(-1300, 4630, 120, 15, 2, 3, 800, false);
				addEnemy(-2280, 4650, 80, 40, 2, 3, 180, true);
				//right block
				addEnemy(2800, 3000, 40, 70, 2, 2, 0, true);
				addEnemy(3100, 3000, 40, 70, 2, 2, 0, true);
				addEnemy(3300, 3000, 40, 70, 2, 2, 0, true);
				addEnemy(2500, 4450, 120, 20, 2, 3, 1000, true);
				addEnemy(2500, 4500, 120, 20, 2, 3, 800, true);
				addEnemy(2500, 4550, 120, 20, 2, 3, 600, true);
				addEnemy(2500, 4600, 120, 20, 2, 3, 400, true);
				addEnemy(2500, 4650, 120, 20, 2, 3, 200, true);
			}
			if(!enteredGreen){
				//top block
				addPlatform(-150, 860, 10, 10, "", "crystal");
				addPlatform(-150, 960, 10, 10, "", "crystal");
				addPlatform(-150, 1060, 10, 10, "", "crystal");
				addPlatform(-150, 1160, 10, 10, "", "crystal");
				addPlatform(-150, 1260, 10, 10, "", "crystal");
				addPlatform(-150, 1360, 10, 10, "", "crystal");
				addPlatform(-150, 1460, 10, 10, "", "crystal");
				addPlatform(-150, 1560, 10, 10, "", "crystal");
				addPlatform(-150, 1660, 10, 10, "", "crystal");
				addPlatform(-150, 1760, 10, 10, "", "crystal");
				addPlatform(-150, 1860, 10, 10, "", "crystal");
				addPlatform(-150, 1960, 10, 10, "", "crystal");
				
				addPlatform(130, 2010, 10, 10, "", "crystal");
				addPlatform(180, 1980, 10, 10, "", "crystal");
				addPlatform(230, 1970, 10, 10, "", "crystal");
				addPlatform(280, 1980, 10, 10, "", "crystal");
				addPlatform(330, 2010, 10, 10, "", "crystal");
				
				addPlatform(950, 2010, 10, 10, "", "crystal");
				addPlatform(1000, 1980, 10, 10, "", "crystal");
				addPlatform(1050, 1970, 10, 10, "", "crystal");
				addPlatform(1100, 1980, 10, 10, "", "crystal");
				addPlatform(1150, 2010, 10, 10, "", "crystal");
				
				addPlatform(1445, 860, 10, 10, "", "crystal");
				addPlatform(1445, 960, 10, 10, "", "crystal");
				addPlatform(1445, 1060, 10, 10, "", "crystal");
				addPlatform(1445, 1160, 10, 10, "", "crystal");
				addPlatform(1445, 1260, 10, 10, "", "crystal");
				addPlatform(1445, 1360, 10, 10, "", "crystal");
				addPlatform(1445, 1460, 10, 10, "", "crystal");
				addPlatform(1445, 1560, 10, 10, "", "crystal");
				addPlatform(1445, 1660, 10, 10, "", "crystal");
				addPlatform(1445, 1760, 10, 10, "", "crystal");
				addPlatform(1445, 1860, 10, 10, "", "crystal");
				addPlatform(1445, 1960, 10, 10, "", "crystal");
				
				addPlatform(530, 1570, 10, 10, "", "crystal");
				addPlatform(750, 1570, 10, 10, "", "crystal");
				addPlatform(880, 1490, 10, 10, "", "crystal");
				addPlatform(410, 1490, 10, 10, "", "crystal");
				addPlatform(530, 1370, 10, 10, "", "crystal");
				addPlatform(750, 1370, 10, 10, "", "crystal");
				addPlatform(530, 1170, 10, 10, "", "crystal");
				addPlatform(750, 1170, 10, 10, "", "crystal");
				addPlatform(880, 1075, 10, 10, "", "crystal");
				addPlatform(410, 1075, 10, 10, "", "crystal");
				addPlatform(620, 1270, 10, 10, "", "crystal");
				addPlatform(670, 1270, 10, 10, "", "crystal");
				
				addPlatform(400, 950, 10, 10, "", "crystal");
				addPlatform(400, 980, 10, 10, "", "crystal");
				addPlatform(440, 950, 10, 10, "", "crystal");
				addPlatform(440, 980, 10, 10, "", "crystal");
				addPlatform(480, 950, 10, 10, "", "crystal");
				addPlatform(480, 980, 10, 10, "", "crystal");
				
				addPlatform(810, 950, 10, 10, "", "crystal");
				addPlatform(810, 980, 10, 10, "", "crystal");
				addPlatform(850, 950, 10, 10, "", "crystal");
				addPlatform(850, 980, 10, 10, "", "crystal");
				addPlatform(890, 950, 10, 10, "", "crystal");
				addPlatform(890, 980, 10, 10, "", "crystal");
				//middle block
				addPlatform(130, 3010, 10, 10, "", "crystal");
				addPlatform(180, 2980, 10, 10, "", "crystal");
				addPlatform(230, 2970, 10, 10, "", "crystal");
				addPlatform(280, 2980, 10, 10, "", "crystal");
				addPlatform(330, 3010, 10, 10, "", "crystal");
				
				addPlatform(950, 3010, 10, 10, "", "crystal");
				addPlatform(1000, 2980, 10, 10, "", "crystal");
				addPlatform(1050, 2970, 10, 10, "", "crystal");
				addPlatform(1100, 2980, 10, 10, "", "crystal");
				addPlatform(1150, 3010, 10, 10, "", "crystal");
				
				addPlatform(-250, 3230, 10, 10, "", "crystal");
				addPlatform(-60, 3230, 10, 10, "", "crystal");
				addPlatform(-250, 3330, 10, 10, "", "crystal");
				addPlatform(-60, 3330, 10, 10, "", "crystal");
				addPlatform(-250, 3430, 10, 10, "", "crystal");
				addPlatform(-60, 3430, 10, 10, "", "crystal");
				
				addPlatform(-250, 3960, 10, 10, "", "crystal");
				addPlatform(-60, 3960, 10, 10, "", "crystal");
				addPlatform(-250, 4060, 10, 10, "", "crystal");
				addPlatform(-60, 4060, 10, 10, "", "crystal");
				addPlatform(-250, 4160, 10, 10, "", "crystal");
				addPlatform(-60, 4160, 10, 10, "", "crystal");
				
				addPlatform(-140, 4555, 10, 10, "", "crystal");
				addPlatform(-140, 4455, 10, 10, "", "crystal");
				addPlatform(-140, 4355, 10, 10, "", "crystal");
				// right side
				addPlatform(1350, 3230, 10, 10, "", "crystal");
				addPlatform(1540, 3230, 10, 10, "", "crystal");
				addPlatform(1350, 3330, 10, 10, "", "crystal");
				addPlatform(1540, 3330, 10, 10, "", "crystal");
				addPlatform(1350, 3430, 10, 10, "", "crystal");
				addPlatform(1540, 3430, 10, 10, "", "crystal");
				
				addPlatform(1350, 3960, 10, 10, "", "crystal");
				addPlatform(1540, 3960, 10, 10, "", "crystal");
				addPlatform(1350, 4060, 10, 10, "", "crystal");
				addPlatform(1540, 4060, 10, 10, "", "crystal");
				addPlatform(1350, 4160, 10, 10, "", "crystal");
				addPlatform(1540, 4160, 10, 10, "", "crystal");
				
				addPlatform(1460, 4555, 10, 10, "", "crystal");
				addPlatform(1460, 4455, 10, 10, "", "crystal");
				addPlatform(1460, 4355, 10, 10, "", "crystal");
				
				addPlatform(1720, 3800, 10, 10, "", "crystal");
				addPlatform(1770, 3770, 10, 10, "", "crystal");
				addPlatform(1820, 3760, 10, 10, "", "crystal");
				addPlatform(1870, 3770, 10, 10, "", "crystal");
				addPlatform(1920, 3800, 10, 10, "", "crystal");
				
				addPlatform(-650, 3800, 10, 10, "", "crystal");
				addPlatform(-600, 3770, 10, 10, "", "crystal");
				addPlatform(-550, 3760, 10, 10, "", "crystal");
				addPlatform(-500, 3770, 10, 10, "", "crystal");
				addPlatform(-450, 3800, 10, 10, "", "crystal");
				
				//right block
				addPlatform(2245, 3180, 10, 10, "", "crystal");
				addPlatform(2245, 3320, 10, 10, "", "crystal");
				addPlatform(2245, 3460, 10, 10, "", "crystal");
				addPlatform(2245, 3600, 10, 10, "", "crystal");
				addPlatform(2245, 3740, 10, 10, "", "crystal");
				addPlatform(2245, 3880, 10, 10, "", "crystal");
				addPlatform(2245, 4020, 10, 10, "", "crystal");
				addPlatform(2245, 4160, 10, 10, "", "crystal");
				addPlatform(2245, 4300, 10, 10, "", "crystal");
				addPlatform(2245, 4440, 10, 10, "", "crystal");
				addPlatform(2245, 4580, 10, 10, "", "crystal");
				
				addPlatform(2700, 2950, 10, 10, "", "crystal");
				addPlatform(2740, 2910, 10, 10, "", "crystal");
				addPlatform(2770, 2880, 10, 10, "", "crystal");
				addPlatform(2800, 2910, 10, 10, "", "crystal");
				addPlatform(2840, 2950, 10, 10, "", "crystal");
				
				addPlatform(3000, 2950, 10, 10, "", "crystal");
				addPlatform(3040, 2910, 10, 10, "", "crystal");
				addPlatform(3070, 2880, 10, 10, "", "crystal");
				addPlatform(3100, 2910, 10, 10, "", "crystal");
				addPlatform(3140, 2950, 10, 10, "", "crystal");
				
				addPlatform(3300, 2950, 10, 10, "", "crystal");
				addPlatform(3340, 2910, 10, 10, "", "crystal");
				addPlatform(3370, 2880, 10, 10, "", "crystal");
				addPlatform(3400, 2910, 10, 10, "", "crystal");
				addPlatform(3440, 2950, 10, 10, "", "crystal");
				
				addPlatform(3845, 3180, 10, 10, "", "crystal");
				addPlatform(3845, 3320, 10, 10, "", "crystal");
				addPlatform(3845, 3460, 10, 10, "", "crystal");
				addPlatform(3845, 3600, 10, 10, "", "crystal");
				addPlatform(3845, 3740, 10, 10, "", "crystal");
				addPlatform(3845, 3880, 10, 10, "", "crystal");
				addPlatform(3845, 4020, 10, 10, "", "crystal");
				addPlatform(3845, 4160, 10, 10, "", "crystal");
				addPlatform(3845, 4300, 10, 10, "", "crystal");
				addPlatform(3845, 4440, 10, 10, "", "crystal");
				addPlatform(3845, 4580, 10, 10, "", "crystal");
				
				addPlatform(2720, 3400, 10, 10, "", "crystal");
				addPlatform(2900, 3340, 10, 10, "", "crystal");
				addPlatform(2900, 3440, 10, 10, "", "crystal");
				addPlatform(2900, 3540, 10, 10, "", "crystal");
				addPlatform(2900, 3640, 10, 10, "", "crystal");
				addPlatform(3080, 3500, 10, 10, "", "crystal");
				addPlatform(2720, 3600, 10, 10, "", "crystal");
				addPlatform(3363, 4040, 45, 40, "super", "crystal");
				
				//left block
				addPlatform(-2040, 2950, 10, 10, "", "crystal");
				addPlatform(-1980, 2910, 10, 10, "", "crystal");
				addPlatform(-1920, 2890, 10, 10, "", "crystal");
				addPlatform(-1860, 2910, 10, 10, "", "crystal");
				addPlatform(-1800, 2950, 10, 10, "", "crystal");
				
				addPlatform(-1720, 2950, 10, 10, "", "crystal");
				addPlatform(-1660, 2910, 10, 10, "", "crystal");
				addPlatform(-1600, 2890, 10, 10, "", "crystal");
				addPlatform(-1540, 2910, 10, 10, "", "crystal");
				addPlatform(-1480, 2950, 10, 10, "", "crystal");
				
				addPlatform(-2025, 3440, 10, 10, "", "crystal");
				addPlatform(-2045, 3440, 10, 10, "", "crystal");
				addPlatform(-2065, 3440, 10, 10, "", "crystal");
				addPlatform(-2025, 3460, 10, 10, "", "crystal");
				addPlatform(-2045, 3460, 10, 10, "", "crystal");
				addPlatform(-2065, 3460, 10, 10, "", "crystal");
				addPlatform(-2025, 3480, 10, 10, "", "crystal");
				addPlatform(-2045, 3480, 10, 10, "", "crystal");
				addPlatform(-2065, 3480, 10, 10, "", "crystal");
				
				addPlatform(-1455, 3440, 10, 10, "", "crystal");
				addPlatform(-1475, 3440, 10, 10, "", "crystal");
				addPlatform(-1495, 3440, 10, 10, "", "crystal");
				addPlatform(-1455, 3460, 10, 10, "", "crystal");
				addPlatform(-1475, 3460, 10, 10, "", "crystal");
				addPlatform(-1495, 3460, 10, 10, "", "crystal");
				addPlatform(-1455, 3480, 10, 10, "", "crystal");
				addPlatform(-1475, 3480, 10, 10, "", "crystal");
				addPlatform(-1495, 3480, 10, 10, "", "crystal");
				
				addPlatform(-960, 3100, 10, 10, "", "crystal");
				addPlatform(-960, 3300, 10, 10, "", "crystal");
				addPlatform(-960, 3500, 10, 10, "", "crystal");
				addPlatform(-960, 3700, 10, 10, "", "crystal");
				addPlatform(-960, 3900, 10, 10, "", "crystal");
				addPlatform(-960, 4100, 10, 10, "", "crystal");
				addPlatform(-960, 4300, 10, 10, "", "crystal");
				addPlatform(-960, 4500, 10, 10, "", "crystal");
				
				addPlatform(-2560, 3100, 10, 10, "", "crystal");
				addPlatform(-2560, 3300, 10, 10, "", "crystal");
				addPlatform(-2560, 3500, 10, 10, "", "crystal");
				addPlatform(-2560, 3700, 10, 10, "", "crystal");
				addPlatform(-2560, 3900, 10, 10, "", "crystal");
				addPlatform(-2560, 4100, 10, 10, "", "crystal");
				addPlatform(-2560, 4300, 10, 10, "", "crystal");
				addPlatform(-2560, 4500, 10, 10, "", "crystal");
				
				//bottom block
				addPlatform(130, 5410, 10, 10, "", "crystal");
				addPlatform(180, 5380, 10, 10, "", "crystal");
				addPlatform(230, 5370, 10, 10, "", "crystal");
				addPlatform(280, 5380, 10, 10, "", "crystal");
				addPlatform(330, 5410, 10, 10, "", "crystal");
				
				addPlatform(950, 5410, 10, 10, "", "crystal");
				addPlatform(1000, 5380, 10, 10, "", "crystal");
				addPlatform(1050, 5370, 10, 10, "", "crystal");
				addPlatform(1100, 5380, 10, 10, "", "crystal");
				addPlatform(1150, 5410, 10, 10, "", "crystal");
				
				addPlatform(230, 6900, 10, 10, "", "crystal");
				addPlatform(25, 6840, 10, 10, "", "crystal");
				addPlatform(-75, 6740, 10, 10, "", "crystal");
				addPlatform(530, 6900, 10, 10, "", "crystal");
				addPlatform(760, 6900, 10, 10, "", "crystal");
				addPlatform(1060, 6900, 10, 10, "", "crystal");
				addPlatform(1260, 6840, 10, 10, "", "crystal");
				addPlatform(1360, 6740, 10, 10, "", "crystal");
				
				addPlatform(1540, 5560, 10, 10, "", "crystal");
				addPlatform(1540, 5760, 10, 10, "", "crystal");
				addPlatform(1540, 5960, 10, 10, "", "crystal");
				addPlatform(1360, 6160, 10, 10, "", "crystal");
				addPlatform(1360, 6360, 10, 10, "", "crystal");
				addPlatform(1360, 6560, 10, 10, "", "crystal");
				
				addPlatform(-250, 5560, 10, 10, "", "crystal");
				addPlatform(-250, 5760, 10, 10, "", "crystal");
				addPlatform(-250, 5960, 10, 10, "", "crystal");
				addPlatform(-70, 6160, 10, 10, "", "crystal");
				addPlatform(-70, 6360, 10, 10, "", "crystal");
				addPlatform(-70, 6560, 10, 10, "", "crystal");
				
				
			}
			if(movingPlatformSet.isEmpty()){
				MovingPlatform mp1 = new MovingPlatform(500, 3050, 150, 20, 375, 2, false, true, "");
				MovingPlatform mp43 = new MovingPlatform(650, 2300, 150, 20, 375, 2, false, false, "");
				MovingPlatform mp2 = new MovingPlatform(-220, 3850, 150, 20, 300, 2, false, true, "");
				MovingPlatform mp3 = new MovingPlatform(1380, 3850, 150, 20, 300, 2, false, true, "");
				MovingPlatform mp4 = new MovingPlatform(500, 5440, 150, 20, 375, 2, false, true, "");
				MovingPlatform mp44 = new MovingPlatform(650, 4690, 150, 20, 375, 2, false, false, "");
				
				MovingPlatform mp5 = new MovingPlatform(-150, 5500, 150, 20, 600, 2, false, false, "");
				MovingPlatform mp6 = new MovingPlatform(-300, 6700, 150, 20, 600, 2, false, true, "");
				MovingPlatform mp7 = new MovingPlatform(1300, 5500, 150, 20, 600, 2, false, false, "");
				MovingPlatform mp8 = new MovingPlatform(1450, 6700, 150, 20, 600, 2, false, true, "");
				MovingPlatform mp9 = new MovingPlatform(-2500, 3150, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp10 = new MovingPlatform(-2700, 3250, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp11 = new MovingPlatform(-2500, 3350, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp12 = new MovingPlatform(-2700, 3450, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp13 = new MovingPlatform(-2500, 3550, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp14 = new MovingPlatform(-2700, 3650, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp15 = new MovingPlatform(-2500, 3750, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp16 = new MovingPlatform(-2700, 3850, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp17 = new MovingPlatform(-2500, 3950, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp18 = new MovingPlatform(-2700, 4050, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp19 = new MovingPlatform(-2500, 4150, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp20 = new MovingPlatform(-2700, 4250, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp21 = new MovingPlatform(-2500, 4350, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp22 = new MovingPlatform(-2700, 4450, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp23 = new MovingPlatform(-2500, 4550, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp24 = new MovingPlatform(-2700, 4650, 100, 10, 200, 2, true, true, "2");
				/////
				MovingPlatform mp25 = new MovingPlatform(-1100, 3150, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp26 = new MovingPlatform(-900, 3250, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp27 = new MovingPlatform(-1100, 3350, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp28 = new MovingPlatform(-900, 3450, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp29 = new MovingPlatform(-1100, 3550, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp30 = new MovingPlatform(-900, 3650, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp31 = new MovingPlatform(-1100, 3750, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp32 = new MovingPlatform(-900, 3850, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp33 = new MovingPlatform(-1100, 3950, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp34 = new MovingPlatform(-900, 4050, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp35 = new MovingPlatform(-1100, 4150, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp36 = new MovingPlatform(-900, 4250, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp37 = new MovingPlatform(-1100, 4350, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp38 = new MovingPlatform(-900, 4450, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp39 = new MovingPlatform(-1100, 4550, 100, 10, 200, 2, true, true, "2");
				MovingPlatform mp40 = new MovingPlatform(-900, 4650, 100, 10, 200, 2, true, false, "2");
				MovingPlatform mp41 = new MovingPlatform(600, 2050, 100, 15, 350, 2, false, true, "");
				MovingPlatform mp42 = new MovingPlatform(600, 6950, 100, 15, 450, 2, false, true, "");
				mp5.setCounter(mp5.getLength());
				mp7.setCounter(mp7.getLength());
				mp9.setCounter(mp9.getLength());
				mp11.setCounter(mp11.getLength());
				mp13.setCounter(mp13.getLength());
				mp15.setCounter(mp15.getLength());
				mp17.setCounter(mp17.getLength());
				mp19.setCounter(mp19.getLength());
				mp21.setCounter(mp21.getLength());
				mp23.setCounter(mp23.getLength());
				mp26.setCounter(mp26.getLength());
				mp28.setCounter(mp28.getLength());
				mp30.setCounter(mp30.getLength());
				mp32.setCounter(mp32.getLength());
				mp34.setCounter(mp34.getLength());
				mp36.setCounter(mp36.getLength());
				mp38.setCounter(mp38.getLength());
				mp40.setCounter(mp40.getLength());
				mp43.setCounter(mp43.getLength());
				mp44.setCounter(mp44.getLength());
				movingPlatformSet.add(mp1);
				movingPlatformSet.add(mp2);
				movingPlatformSet.add(mp3);
				movingPlatformSet.add(mp4);
				movingPlatformSet.add(mp5);
				movingPlatformSet.add(mp6);
				movingPlatformSet.add(mp7);
				movingPlatformSet.add(mp8);
				movingPlatformSet.add(mp9);
				movingPlatformSet.add(mp10);
				movingPlatformSet.add(mp11);
				movingPlatformSet.add(mp12);
				movingPlatformSet.add(mp13);
				movingPlatformSet.add(mp14);
				movingPlatformSet.add(mp15);
				movingPlatformSet.add(mp16);
				movingPlatformSet.add(mp17);
				movingPlatformSet.add(mp18);
				movingPlatformSet.add(mp19);
				movingPlatformSet.add(mp20);
				movingPlatformSet.add(mp21);
				movingPlatformSet.add(mp22);
				movingPlatformSet.add(mp23);
				movingPlatformSet.add(mp24);
				movingPlatformSet.add(mp25);
				movingPlatformSet.add(mp26);
				movingPlatformSet.add(mp27);
				movingPlatformSet.add(mp28);
				movingPlatformSet.add(mp29);
				movingPlatformSet.add(mp30);
				movingPlatformSet.add(mp31);
				movingPlatformSet.add(mp32);
				movingPlatformSet.add(mp33);
				movingPlatformSet.add(mp34);
				movingPlatformSet.add(mp35);
				movingPlatformSet.add(mp36);
				movingPlatformSet.add(mp37);
				movingPlatformSet.add(mp38);
				movingPlatformSet.add(mp39);
				movingPlatformSet.add(mp40);
				movingPlatformSet.add(mp41);
				movingPlatformSet.add(mp42);
				movingPlatformSet.add(mp43);
				movingPlatformSet.add(mp44);
				
			}
			
			if(!hasGreenKey){
				addPlatform(-1720, 3785, 25, 60, "green", "key");
			}
			if(doorSet.isEmpty()){
				addPlatform(530, 635, 40, 65, "hub1", "door");
				addPlatform(585, 5765, 40, 65, "red2", "door");
				addPlatform(-1830, 3785, 40, 65, "hub1", "door");
				addPlatform(980, 4055, 40, 65, "yellow4", "door");
				addPlatform(280, 3335, 40, 65, "yellow2", "door");
			}
		}
		
		public void drawGreenLevel(Graphics g){
			g.setColor(Color.RED);
			for(Platform da : deathAreaSet){
				g.fillRect(da.x, da.y, da.width, da.height);
			}
			g.setColor(new Color(0, 100, 0));
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			for(MovingPlatform mp : movingPlatformSet){
				platformMove(mp);
				g.fillRect(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight());
			}
			g.setColor(Color.YELLOW);
			for(Platform cryst : crystalSet){
				//g.fillRect(crystal.x, crystal.y, crystal.width, crystal.height);
            if(cryst.type.equals("super")){
               g.drawImage(superCrystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);
            } else {
               g.drawImage(crystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);
            }
			}
			for(Platform key : keySet){
				g.drawImage(greenKey.getImage(), key.x, key.y, key.width, key.height, this);
			}
			keyTest();
			crystalRemove();
			drawDoor(g);
			drawEnemy(g);
		}
		
		public void createYellowLevel(Graphics g){
			if(platformSet.isEmpty()){
				addPlatform(-1000, 700, 6700, 2000, "", "platform");
				addPlatform(-1000, -1000, 1300, 3000, "", "platform");
				
				addPlatform(1500, 600, 150, 10, "2", "platform");
				addPlatform(1750, 600, 150, 10, "2", "platform");
				addPlatform(1750, 500, 150, 10, "2", "platform");
				addPlatform(2000, 600, 150, 10, "2", "platform");
				addPlatform(2000, 500, 150, 10, "2", "platform");
				addPlatform(2000, 400, 150, 10, "2", "platform");
				addPlatform(2250, 600, 150, 10, "2", "platform");
				addPlatform(2250, 500, 150, 10, "2", "platform");
				addPlatform(2250, 400, 150, 10, "2", "platform");
				addPlatform(2250, 300, 150, 10, "2", "platform");
				
				addPlatform(2500, 600, 150, 10, "2", "platform");
				addPlatform(2500, 500, 150, 10, "2", "platform");
				addPlatform(2500, 400, 150, 10, "2", "platform");
				addPlatform(2500, 300, 150, 10, "2", "platform");
				addPlatform(2500, 200, 150, 10, "2", "platform");
				
				addPlatform(2750, 600, 150, 10, "2", "platform");
				addPlatform(2750, 500, 150, 10, "2", "platform");
				addPlatform(2750, 400, 150, 10, "2", "platform");
				addPlatform(2750, 300, 150, 10, "2", "platform");
				addPlatform(2750, 200, 150, 10, "2", "platform");
				addPlatform(2750, 100, 150, 10, "2", "platform");
				
				addPlatform(3000, 600, 150, 10, "2", "platform");
				addPlatform(3000, 500, 150, 10, "2", "platform");
				addPlatform(3000, 400, 150, 10, "2", "platform");
				addPlatform(3000, 300, 150, 10, "2", "platform");
				addPlatform(3000, 200, 150, 10, "2", "platform");
				addPlatform(3000, 100, 150, 10, "2", "platform");
				addPlatform(3000, 0, 150, 10, "2", "platform");
				
				addPlatform(3250, 600, 150, 10, "2", "platform");
				addPlatform(3250, 500, 150, 10, "2", "platform");
				addPlatform(3250, 400, 150, 10, "2", "platform");
				addPlatform(3250, 300, 150, 10, "2", "platform");
				addPlatform(3250, 200, 150, 10, "2", "platform");
				addPlatform(3250, 100, 150, 10, "2", "platform");
				addPlatform(3250, 0, 150, 10, "2", "platform");
				addPlatform(3250, -100, 150, 10, "2", "platform");
				
				addPlatform(3500, 600, 150, 10, "2", "platform");
				addPlatform(3500, 500, 150, 10, "2", "platform");
				addPlatform(3500, 400, 150, 10, "2", "platform");
				addPlatform(3500, 300, 150, 10, "2", "platform");
				addPlatform(3500, 200, 150, 10, "2", "platform");
				addPlatform(3500, 100, 150, 10, "2", "platform");
				addPlatform(3500, 0, 150, 10, "2", "platform");
				addPlatform(3500, -100, 150, 10, "2", "platform");
				addPlatform(3500, -200, 150, 10, "2", "platform");
				
				addPlatform(3800, 200, 2500, 250, "", "platform");
				addPlatform(3800, -300, 3100, 250, "", "platform");
				addPlatform(6000, 300, 300, 1000, "", "platform");
				addPlatform(6600, -100, 300, 1400, "", "platform");
				
				addPlatform(7200, -600, 300, 1900, "", "platform");
				addPlatform(7480, -700, 300, 1900, "", "platform");
				addPlatform(7760, -800, 300, 1900, "", "platform");
				addPlatform(8040, -900, 300, 1900, "", "platform");
				addPlatform(8320, -1000, 300, 1900, "", "platform");
				addPlatform(8600, -1100, 300, 1700, "", "platform");
				addPlatform(8880, -1300, 1500, 1900, "", "platform");
				
				addPlatform(5600, 1700, 1600, 2000, "", "platform");
				
				addPlatform(7380, 1580, 300, 100, "", "platform");
				addPlatform(7660, 1480, 300, 200, "", "platform");
				addPlatform(7940, 1380, 300, 300, "", "platform");
				addPlatform(8220, 1280, 300, 400, "", "platform");
				addPlatform(8500, 1180, 300, 500, "", "platform");
				//this one
				addPlatform(8780, 1080, 1500, 430, "", "platform");
				//
				
				addPlatform(7180, 1820, 300, 1000, "", "platform");
				addPlatform(7460, 1900, 300, 1000, "", "platform");
				addPlatform(7740, 2000, 300, 1000, "", "platform");
				addPlatform(8020, 2100, 300, 1000, "", "platform");
				addPlatform(8300, 2200, 300, 1000, "", "platform");
				addPlatform(8580, 2300, 400, 1600, "", "platform");
				
				addPlatform(10260, 1180, 250, 520, "", "platform");
				addPlatform(10490, 1280, 250, 700, "", "platform");
				addPlatform(10720, 1380, 250, 500, "", "platform");
				addPlatform(10950, 1480, 250, 300, "", "platform");
				addPlatform(11150, 1580, 250, 100, "", "platform");
				
				addPlatform(7660, 1670, 300, 80, "", "platform");
				addPlatform(7940, 1670, 300, 160, "", "platform");
				addPlatform(8220, 1670, 300, 240, "", "platform");
				addPlatform(8500, 1670, 300, 320, "", "platform");
				
				addPlatform(10660, 2200, 2000, 1000, "", "platform");
				addPlatform(10890, 2100, 1550, 200, "", "platform");
				addPlatform(11120, 2000, 1100, 300, "", "platform");
				addPlatform(11350, 1900, 650, 400, "", "platform");
				addPlatform(11550, 1800, 250, 500, "", "platform");
				
				addPlatform(10380, -1000, 2590, 1900, "", "platform");
				addPlatform(10630, -1000, 2090, 2000, "", "platform");
				addPlatform(10880, -1000, 1590, 2100, "", "platform");
				addPlatform(11130, -1000, 1090, 2200, "", "platform");
				addPlatform(11380, -1000, 590, 2400, "", "platform");
				
				addPlatform(11950, 1580, 250, 100, "", "platform");
				addPlatform(12660, 2300, 220, 1000, "", "platform");
				addPlatform(12880, 2400, 220, 1000, "", "platform");
				addPlatform(13100, 2500, 220, 1000, "", "platform");
				addPlatform(13320, 2600, 220, 1000, "", "platform");

				addPlatform(12150, 1480, 250, 300, "", "platform");
				addPlatform(12380, 1380, 250, 500, "", "platform");
				addPlatform(12610, 1280, 250, 700, "", "platform");
				addPlatform(12840, 1180, 250, 900, "", "platform");
				addPlatform(13070, 1080, 250, 1100, "", "platform");
				addPlatform(13300, 980, 250, 1300, "", "platform");
				addPlatform(13530, 880, 250, 2500, "", "platform");
				
				addPlatform(12969, -1000, 251, 1800, "", "platform");
				addPlatform(13220, -1000, 250, 1700, "", "platform");
				addPlatform(13470, -1000, 300, 1600, "", "platform");
				addPlatform(13770, -1000, 1000, 6000, "", "platform");
				
				addPlatform(11400, 1670, 100, 10, "", "platform");
				addPlatform(11850, 1670, 100, 10, "", "platform");
				//upper area
				addPlatform(-1000, -2000, 1500, 1010, "", "platform");
				addPlatform(500, -8900, 1000, 7500, "", "platform");
				addPlatform(2050, -8290, 1000, 7310, "", "platform");
				addPlatform(800, -10000, 3000, 1300, "", "platform");
				addPlatform(2650, -9000, 1000, 1300, "", "platform");
				addPlatform(2300, -8310, 70, 50, "", "platform");
				
				addPlatform(1950, -1120, 100, 10, "2", "platform");
				addPlatform(1945, -1240, 105, 10, "2", "platform");
				
				addPlatform(1940, -1360, 110, 10, "2", "platform");
				addPlatform(1500, -1460, 110, 10, "", "platform");
				
				addPlatform(1940, -1560, 110, 10, "", "platform");
				addPlatform(1500, -1660, 110, 10, "", "platform");
				
				addPlatform(1940, -1760, 110, 10, "", "platform");
				addPlatform(1500, -1860, 110, 10, "", "platform");
				
				addPlatform(1940, -1960, 110, 10, "", "platform");
				addPlatform(1500, -2060, 110, 10, "", "platform");
				
				addPlatform(1940, -2160, 110, 10, "", "platform");
				addPlatform(1500, -2260, 110, 10, "", "platform");
				
				addPlatform(1940, -2360, 110, 10, "", "platform");
				addPlatform(1500, -2460, 110, 10, "", "platform");
				
				addPlatform(1940, -2560, 110, 10, "", "platform");
				addPlatform(1500, -2660, 110, 10, "", "platform");
				
				addPlatform(1940, -2760, 110, 10, "", "platform");
				addPlatform(1500, -2860, 110, 10, "", "platform");
				
				addPlatform(1940, -2960, 110, 10, "", "platform");
				addPlatform(1500, -3060, 110, 10, "", "platform");
				
				addPlatform(1940, -3160, 110, 10, "", "platform");
				addPlatform(1500, -3260, 110, 10, "", "platform");
				
				addPlatform(1940, -3360, 110, 10, "", "platform");
				addPlatform(1500, -3460, 110, 10, "", "platform");
				
				addPlatform(1940, -3560, 110, 10, "", "platform");
				addPlatform(1500, -3660, 110, 10, "", "platform");
				
				addPlatform(1940, -3760, 110, 10, "", "platform");
				addPlatform(1500, -3860, 110, 10, "", "platform");
				
				addPlatform(1940, -3960, 110, 10, "", "platform");
				addPlatform(1500, -4060, 110, 10, "", "platform");
				
				addPlatform(1940, -4160, 110, 10, "", "platform");
				addPlatform(1500, -4260, 110, 10, "", "platform");
				
				addPlatform(1940, -4360, 110, 10, "", "platform");
				addPlatform(1500, -4460, 110, 10, "", "platform");
				
				addPlatform(1940, -4560, 110, 10, "", "platform");
				addPlatform(1500, -4660, 110, 10, "", "platform");
				
				addPlatform(1940, -4760, 110, 10, "", "platform");
				addPlatform(1500, -4860, 110, 10, "", "platform");
				
				addPlatform(1940, -4960, 110, 10, "", "platform");
				addPlatform(1500, -5060, 110, 10, "", "platform");
				
				addPlatform(1940, -5160, 110, 10, "", "platform");
				addPlatform(1500, -5260, 110, 10, "", "platform");
				
				addPlatform(1940, -5360, 110, 10, "", "platform");
				addPlatform(1500, -5460, 110, 10, "", "platform");
				
				addPlatform(1940, -5560, 110, 10, "", "platform");
				addPlatform(1500, -5660, 110, 10, "", "platform");
				
				addPlatform(1940, -5760, 110, 10, "", "platform");
				addPlatform(1500, -5860, 110, 10, "", "platform");
				
				addPlatform(1940, -5960, 110, 10, "", "platform");
				addPlatform(1500, -6060, 110, 10, "", "platform");
				
				addPlatform(1940, -6160, 110, 10, "", "platform");
				addPlatform(1500, -6260, 110, 10, "", "platform");
				
				addPlatform(1940, -6360, 110, 10, "", "platform");
				addPlatform(1500, -6460, 110, 10, "", "platform");
				
				addPlatform(1940, -6560, 110, 10, "", "platform");
				addPlatform(1500, -6660, 110, 10, "", "platform");
				
				addPlatform(1940, -6760, 110, 10, "", "platform");
				addPlatform(1500, -6860, 110, 10, "", "platform");
				
				addPlatform(1940, -6960, 110, 10, "", "platform");
				addPlatform(1500, -7060, 110, 10, "", "platform");
				
				addPlatform(1940, -7160, 110, 10, "", "platform");
				addPlatform(1500, -7260, 110, 10, "", "platform");
				
				addPlatform(1940, -7360, 110, 10, "", "platform");
				addPlatform(1500, -7460, 110, 10, "", "platform");
				
				addPlatform(1940, -7560, 110, 10, "", "platform");
				addPlatform(1500, -7660, 110, 10, "", "platform");
				
				addPlatform(1940, -7760, 110, 10, "", "platform");
				addPlatform(1500, -7860, 110, 10, "", "platform");
				
				addPlatform(1940, -7960, 110, 10, "", "platform");
				addPlatform(1500, -8060, 110, 10, "", "platform");
				
				addPlatform(1940, -8160, 110, 10, "", "platform");
				addPlatform(1500, -8260, 110, 10, "", "platform");
				
				addPlatform(1940, -8300, 1100, 10, "", "platform");
				
				//top corridor
				addPlatform(4000, -370, 70, 70, "", "platform");
				addPlatform(4200, -550, 70, 70, "", "platform");
				addPlatform(4400, -370, 70, 70, "", "platform");
				addPlatform(4600, -550, 70, 70, "", "platform");
				addPlatform(4800, -370, 70, 70, "", "platform");
				addPlatform(5000, -551, 70, 71, "", "platform");
				addPlatform(5200, -370, 70, 70, "", "platform");
				addPlatform(5400, -550, 70, 70, "", "platform");
				addPlatform(5600, -370, 72, 70, "", "platform");
				addPlatform(6371, -419, 30, 150, "", "platform");
				addSlantedPlatform(5672, -320, 6372 ,-420 , 150, g);
				addSlantedPlatform(6400, -419, 6900 ,-319 , 150, g);
				addPlatform(6900, -200, 80, 10, "", "platform");
				addPlatform(7120, -100, 80, 10, "", "platform");
				addPlatform(6900, 0, 80, 10, "", "platform");
				addPlatform(7120, 100, 80, 10, "", "platform");
				addPlatform(6900, 200, 80, 10, "", "platform");
				addPlatform(7120, 300, 80, 10, "", "platform");
				addPlatform(6900, 400, 80, 10, "", "platform");
				addPlatform(7120, 500, 80, 10, "", "platform");
				addPlatform(6900, 600, 80, 10, "", "platform");
				addPlatform(7120, 700, 80, 10, "", "platform");
				addPlatform(6900, 800, 80, 10, "", "platform");
				addPlatform(7120, 900, 80, 10, "", "platform");
				addPlatform(6900, 1000, 80, 10, "", "platform");
				addPlatform(7120, 1100, 80, 10, "", "platform");
				addPlatform(6900, 1200, 80, 10, "", "platform");
				addPlatform(5070, -485, 15, 5, "", "platform");
				addPlatform(5110, -680, 20, 5, "", "platform");
				addPlatform(5035, -800, 20, 5, "", "platform");
				addPlatform(5110, -920, 20, 5, "", "platform");
				addPlatform(5035, -1040, 20, 5, "", "platform");
				addPlatform(5110, -1160, 20, 5, "", "platform");
				addPlatform(5035, -1280, 20, 5, "", "platform");
				addPlatform(5110, -1400, 20, 5, "", "platform");
				addPlatform(5000, -2000, 200, 400, "", "platform");
				//middle corridor
				
				addSlantedPlatform(4100, 200, 4400, 100, 100, g);
				addPlatform(4399, 101, 301, 100, "", "platform");
				addSlantedPlatform(4699, 101, 4999, 201, 100, g);
				addSlantedPlatform(5300, 200, 5600, 100, 100, g);
				addPlatform(5599, 101, 301, 100, "", "platform");
				addSlantedPlatform(5899, 101, 6199, 201, 100, g);
				
				addPlatform(6400, 320, 100, 10, "2", "platform");
				addPlatform(6300, 420, 100, 10, "2", "platform");
				addPlatform(6500, 420, 100, 10, "2", "platform");
				
				addPlatform(6400, 520, 100, 10, "2", "platform");
				addPlatform(6300, 620, 100, 10, "2", "platform");
				addPlatform(6500, 620, 100, 10, "2", "platform");
				
				addPlatform(6400, 720, 100, 10, "2", "platform");
				addPlatform(6300, 820, 100, 10, "2", "platform");
				addPlatform(6500, 820, 100, 10, "2", "platform");
				
				addPlatform(6400, 920, 100, 10, "2", "platform");
				addPlatform(6300, 1020, 100, 10, "2", "platform");
				addPlatform(6500, 1020, 100, 10, "2", "platform");
				
				addPlatform(6400, 1120, 100, 10, "2", "platform");
				addPlatform(6300, 1220, 100, 10, "2", "platform");
				addPlatform(6500, 1220, 100, 10, "2", "platform");
				
				//bottom corridor
				
				addPlatform(3900, 610, 150, 20, "", "platform");
				addPlatform(4150, 570, 150, 20, "", "platform");
				addPlatform(4400, 530, 150, 20, "", "platform");
				addPlatform(4650, 570, 150, 20, "", "platform");
				addPlatform(4900, 610, 150, 20, "", "platform");
				addPlatform(5150, 570, 150, 20, "", "platform");
				addPlatform(5400, 530, 150, 20, "", "platform");
				
				addPlatform(5700, 800, 80, 10, "2", "platform");
				addPlatform(5920, 800, 80, 10, "2", "platform");
				addPlatform(5700, 930, 80, 10, "2", "platform");
				addPlatform(5920, 930, 80, 10, "2", "platform");
				addPlatform(5700, 1060, 80, 10, "2", "platform");
				addPlatform(5920, 1060, 80, 10, "2", "platform");
				addPlatform(5700, 1190, 80, 10, "2", "platform");
				addPlatform(5920, 1190, 80, 10, "2", "platform");
				
				addPlatform(-1000, -1000, 6035, 450, "", "platform");
				addPlatform(4000, -2000, 1035, 1450, "", "platform");
				addPlatform(5130, -2000, 6000, 1450, "", "platform");
				
				addSlantedPlatform(8781, 1080, 9381, 980, 120,  g);
				addPlatform(9380, 981, 301, 121, "", "platform");
				addSlantedPlatform(9680, 981, 10280, 1081, 120,  g);
				addPlatform(9100, 850, 150, 10, "2", "platform");
				addPlatform(8900, 750, 150, 10, "2", "platform");
				addPlatform(9200, 690, 200, 10, "2", "platform");
				addPlatform(9810, 850, 150, 10, "2", "platform");
				addPlatform(10010, 750, 150, 10, "2", "platform");
				addPlatform(9670, 690, 200, 10, "2", "platform");
				addPlatform(9470, 770, 130, 10, "2", "platform");
				addPlatform(9800, 1870, 30, 8, "", "platform");
				addPlatform(9700, 1750, 30, 8, "", "platform");
				addPlatform(9300, 1750, 30, 8, "", "platform");
				addPlatform(9130, 1650, 30, 8, "", "platform");
				addPlatform(9300, 1590, 800, 8, "", "platform");
				addPlatform(9520, 1830, 30, 8, "", "platform");
				addPlatform(8799, 1510, 280, 120, "", "platform");
				addPlatform(10050, 1510, 250, 88, "", "platform");
				addPlatform(3800, 195, 20, 20, "", "platform");
				addPlatform(6280, 195, 20, 20, "", "platform");
				
				addPlatform(1700, -1040, 120, 200, "", "platform");
				addPlatform(1650, -1020, 120, 200, "", "platform");
				addPlatform(1750, -1020, 120, 200, "", "platform");
			}
			if(enemySet.isEmpty()){
				addEnemy(4150, 520, 40, 50, 4, 6, 110, true);
				addEnemy(4900, 560, 40, 50, 5, 6, 110, true);
				addEnemy(4550, 50, 50, 44, 3, 1, 110, false);
				addEnemy(5150, 100, 40, 70, 4, 2, 110, false);
				addEnemy(5750, 50, 50, 44, 3, 1, 110, false);
				addEnemy(4500, -350, 50, 44, 3, 1, 110, true);
				addEnemy(5270, -350, 40, 50, 4, 6, 290, true);
				addEnemy(6620, -470, 200, 30, 3, 3, 900, false);
				addEnemy(8840, 800, 120, 20, 3, 3, 500, true);
				addEnemy(9730, 780, 120, 20, 4, 3, 300, true);
				addEnemy(8040, 2050, 40, 50, 4, 6, 240, true);
				addEnemy(10740, 1330, 40, 50, 4, 6, 180, true);
				addEnemy(12440, 2150, 40, 50, 4, 6, 180, true);
				addEnemy(12880, 2350, 40, 50, 5, 6, 180, true);
				addEnemy(12380, 1330, 40, 50, 5, 6, 190, true);
				addEnemy(12840, 1130, 40, 50, 5, 6, 190, true);
				addEnemy(13300, 930, 40, 50, 5, 6, 190, true);
			}
			if(!enteredYellow){
				addPlatform(3570, -50, 10, 10, "", "crystal");
				addPlatform(3570, 150, 10, 10, "", "crystal");
				addPlatform(3570, 350, 10, 10, "", "crystal");
				addPlatform(3570, 550, 10, 10, "", "crystal");
				addPlatform(3320, 50, 10, 10, "", "crystal");
				addPlatform(3320, 250, 10, 10, "", "crystal");
				addPlatform(3320, 450, 10, 10, "", "crystal");
				addPlatform(3070, 550, 10, 10, "", "crystal");
				addPlatform(3070, 150, 10, 10, "", "crystal");
				addPlatform(3070, 350, 10, 10, "", "crystal");
				addPlatform(2820, 450, 10, 10, "", "crystal");
				addPlatform(2820, 250, 10, 10, "", "crystal");
				addPlatform(2320, 450, 10, 10, "", "crystal");
				addPlatform(2570, 350, 10, 10, "", "crystal");
				addPlatform(2570, 550, 10, 10, "", "crystal");
				addPlatform(2070, 550, 10, 10, "", "crystal");
				
				addPlatform(3940, -430, 10, 10, "", "crystal");
				addPlatform(4120, -430, 10, 10, "", "crystal");
				
				addPlatform(4340, -430, 10, 10, "", "crystal");
				addPlatform(4520, -430, 10, 10, "", "crystal");
				
				addPlatform(4740, -430, 10, 10, "", "crystal");
				addPlatform(4920, -430, 10, 10, "", "crystal");
				
				addPlatform(5140, -430, 10, 10, "", "crystal");
				addPlatform(5320, -430, 10, 10, "", "crystal");
				
				addPlatform(5540, -430, 10, 10, "", "crystal");
				addPlatform(5720, -430, 10, 10, "", "crystal");
				
				addPlatform(6935, -260, 10, 10, "", "crystal");
				addPlatform(7155, -160, 10, 10, "", "crystal");
				
				addPlatform(6935, -60, 10, 10, "", "crystal");
				addPlatform(7155, 40, 10, 10, "", "crystal");
				
				addPlatform(6935, 140, 10, 10, "", "crystal");
				addPlatform(7155, 240, 10, 10, "", "crystal");
				
				addPlatform(6935, 340, 10, 10, "", "crystal");
				addPlatform(7155, 440, 10, 10, "", "crystal");
				
				addPlatform(6935, 540, 10, 10, "", "crystal");
				addPlatform(7155, 640, 10, 10, "", "crystal");
				
				addPlatform(6935, 740, 10, 10, "", "crystal");
				addPlatform(7155, 840, 10, 10, "", "crystal");
				
				addPlatform(6935, 940, 10, 10, "", "crystal");
				addPlatform(7155, 1040, 10, 10, "", "crystal");
				
				addPlatform(6935, 1140, 10, 10, "", "crystal");
				//middle 
				addPlatform(4480, 0, 10, 10, "", "crystal");
				addPlatform(4540, 0, 10, 10, "", "crystal");
				addPlatform(4600, 0, 10, 10, "", "crystal");
				
				addPlatform(5080, 100, 10, 10, "", "crystal");
				addPlatform(5140, 100, 10, 10, "", "crystal");
				addPlatform(5200, 100, 10, 10, "", "crystal");
				
				addPlatform(5680, 0, 10, 10, "", "crystal");
				addPlatform(5740, 0, 10, 10, "", "crystal");
				addPlatform(5800, 0, 10, 10, "", "crystal");
				
				addPlatform(6345, 380, 10, 10, "", "crystal");
				addPlatform(6545, 380, 10, 10, "", "crystal");
				
				addPlatform(6345, 580, 10, 10, "", "crystal");
				addPlatform(6545, 580, 10, 10, "", "crystal");
				
				addPlatform(6345, 780, 10, 10, "", "crystal");
				addPlatform(6545, 780, 10, 10, "", "crystal");
				
				addPlatform(6345, 980, 10, 10, "", "crystal");
				addPlatform(6545, 980, 10, 10, "", "crystal");
				
				addPlatform(6345, 1180, 10, 10, "", "crystal");
				addPlatform(6545, 1180, 10, 10, "", "crystal");
				//bottom
				addPlatform(4090, 540, 10, 10, "", "crystal");
				addPlatform(4350, 490, 10, 10, "", "crystal");
				addPlatform(4470, 490, 10, 10, "", "crystal");
				addPlatform(4600, 490, 10, 10, "", "crystal");
				addPlatform(4850, 540, 10, 10, "", "crystal");
				addPlatform(5090, 540, 10, 10, "", "crystal");
				addPlatform(5340, 490, 10, 10, "", "crystal");
				addPlatform(5470, 490, 10, 10, "", "crystal");
				addPlatform(4720, 520, 10, 10, "", "crystal");
				addPlatform(5220, 520, 10, 10, "", "crystal");
				
				addPlatform(5840, 800, 10, 10, "", "crystal");
				addPlatform(5840, 930, 10, 10, "", "crystal");
				addPlatform(5840, 1060, 10, 10, "", "crystal");
				addPlatform(5840, 1190, 10, 10, "", "crystal");
				
				//
				addPlatform(5900, 1600, 10, 10, "", "crystal");
				addPlatform(5970, 1570, 10, 10, "", "crystal");
				addPlatform(6040, 1550, 10, 10, "", "crystal");
				addPlatform(6110, 1570, 10, 10, "", "crystal");
				addPlatform(6180, 1600, 10, 10, "", "crystal");
				
				addPlatform(6300, 1600, 10, 10, "", "crystal");
				addPlatform(6370, 1570, 10, 10, "", "crystal");
				addPlatform(6440, 1550, 10, 10, "", "crystal");
				addPlatform(6510, 1570, 10, 10, "", "crystal");
				addPlatform(6580, 1600, 10, 10, "", "crystal");
				
				addPlatform(6700, 1600, 10, 10, "", "crystal");
				addPlatform(6770, 1570, 10, 10, "", "crystal");
				addPlatform(6840, 1550, 10, 10, "", "crystal");
				addPlatform(6910, 1570, 10, 10, "", "crystal");
				addPlatform(6980, 1600, 10, 10, "", "crystal");
				
				//addPlatform(5080, -620, 10, 10, "", "crystal");
				addPlatform(5080, -750, 10, 10, "", "crystal");
				//addPlatform(5080, -860, 10, 10, "", "crystal");
				addPlatform(5080, -990, 10, 10, "", "crystal");
				//addPlatform(5080, -1100, 10, 10, "", "crystal");
				addPlatform(5080, -1230, 10, 10, "", "crystal");
				addPlatform(5063, -1550, 45, 40, "super", "crystal");
				
				addPlatform(9100, 670, 10, 10, "", "crystal");
				addPlatform(9215, 640, 10, 10, "", "crystal");
				addPlatform(9295, 640, 10, 10, "", "crystal");
				addPlatform(9375, 640, 10, 10, "", "crystal");
				addPlatform(9460, 690, 10, 10, "", "crystal");
				addPlatform(9610, 690, 10, 10, "", "crystal");
				addPlatform(9535, 730, 10, 10, "", "crystal");
				addPlatform(9685, 640, 10, 10, "", "crystal");
				addPlatform(9765, 640, 10, 10, "", "crystal");
				addPlatform(9845, 640, 10, 10, "", "crystal");
				addPlatform(9960, 670, 10, 10, "", "crystal");
				addPlatform(8970, 710, 10, 10, "", "crystal");
				addPlatform(10080, 710, 10, 10, "", "crystal");
				
				addPlatform(9375, 1960, 10, 10, "", "crystal");
				addPlatform(9415, 1960, 10, 10, "", "crystal");
				addPlatform(9395, 1990, 10, 10, "", "crystal");
				addPlatform(9395, 1930, 10, 10, "", "crystal");
				
				addPlatform(10345, 1960, 10, 10, "", "crystal");
				addPlatform(10385, 1960, 10, 10, "", "crystal");
				addPlatform(10365, 1990, 10, 10, "", "crystal");
				addPlatform(10365, 1930, 10, 10, "", "crystal");
				
				addPlatform(9980, 1528, 45, 40, "super", "crystal");
				
				addPlatform(1760, -1700, 10, 10, "", "crystal");
				addPlatform(1710, -1900, 10, 10, "", "crystal");
				addPlatform(1660, -2100, 10, 10, "", "crystal");
				addPlatform(1710, -2300, 10, 10, "", "crystal");
				addPlatform(1760, -2500, 10, 10, "", "crystal");
				addPlatform(1810, -2700, 10, 10, "", "crystal");
				addPlatform(1860, -2900, 10, 10, "", "crystal");
				addPlatform(1810, -3100, 10, 10, "", "crystal");
				
				addPlatform(1760, -3300, 10, 10, "", "crystal");
				addPlatform(1710, -3500, 10, 10, "", "crystal");
				addPlatform(1660, -3700, 10, 10, "", "crystal");
				addPlatform(1710, -3900, 10, 10, "", "crystal");
				addPlatform(1760, -4100, 10, 10, "", "crystal");
				addPlatform(1810, -4300, 10, 10, "", "crystal");
				addPlatform(1860, -4500, 10, 10, "", "crystal");
				addPlatform(1810, -4700, 10, 10, "", "crystal");
				
				addPlatform(1760, -4900, 10, 10, "", "crystal");
				addPlatform(1710, -5100, 10, 10, "", "crystal");
				addPlatform(1660, -5300, 10, 10, "", "crystal");
				addPlatform(1710, -5500, 10, 10, "", "crystal");
				addPlatform(1760, -5700, 10, 10, "", "crystal");
				addPlatform(1810, -5900, 10, 10, "", "crystal");
				addPlatform(1860, -6100, 10, 10, "", "crystal");
				addPlatform(1810, -6300, 10, 10, "", "crystal");
				
				addPlatform(1760, -6500, 10, 10, "", "crystal");
				addPlatform(1710, -6700, 10, 10, "", "crystal");
				addPlatform(1660, -6900, 10, 10, "", "crystal");
				addPlatform(1710, -7100, 10, 10, "", "crystal");
				addPlatform(1760, -7300, 10, 10, "", "crystal");
				addPlatform(1810, -7500, 10, 10, "", "crystal");
				addPlatform(1860, -7700, 10, 10, "", "crystal");
				addPlatform(1810, -7900, 10, 10, "", "crystal");
				
				addPlatform(1760, -8100, 10, 10, "", "crystal");
				addPlatform(1710, -8300, 10, 10, "", "crystal");
			}
			if(movingPlatformSet.isEmpty()){
				addMovingPlatform(9300, 2300, 200, 1000, 200, 3, false, true, "");
				addMovingPlatform(9850, 2200, 200, 1000, 250, 3, false, true, "");
				addMovingPlatform(10280, 2300, 200, 1000, 230, 3, false, true, "");
			}
			if(doorSet.isEmpty()){
				addPlatform(500, 636, 40, 65, "hub1", "door");
				addPlatform(13460, 2535, 40, 65, "red3", "door");
				addPlatform(13680, 815, 40, 65, "green3", "door");
				addPlatform(560, -1065, 40, 65, "green4", "door");
				addPlatform(2450, -8365, 40, 65, "hub1", "door");
			}
			if(deathAreaSet.isEmpty()){
				addPlatform(8980, 2340, 3000, 500, "", "death area");
			}
			if(flyCrystalSet.isEmpty()){
				addPlatform(1745, -1100, 30, 25, "", "flyCrystal");
			}
			if(!hasYellowKey){
				addPlatform(2320, -8370, 30, 60, "yellow", "key");
			}
		}
		
		public void drawYellowLevel(Graphics g){
			g.setColor(Color.RED);
			for(Platform da : deathAreaSet){
				g.fillRect(da.x, da.y, da.width, da.height);
			}
			g.setColor(new Color(0, 70, 0));
			for(Platform rect : platformSet){
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			for(MovingPlatform mp : movingPlatformSet){
				platformMove(mp);
				g.fillRect(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight());
			}
			g.setColor(Color.MAGENTA);
			for(Platform fc : flyCrystalSet){
				//g.fillRect(fc.x, fc.y, fc.width, fc.height);
            g.drawImage(powerCrystal.getImage(), fc.x, fc.y, fc.width, fc.height, this);

			}
			for(Platform key : keySet){
				g.drawImage(yellowKey.getImage(), key.x, key.y, key.width, key.height, this);
			}
			g.setColor(Color.YELLOW);
			for(Platform cryst : crystalSet){
				//g.fillRect(crystal.x, crystal.y, crystal.width, crystal.height);
            if(cryst.type.equals("super")){
               g.drawImage(superCrystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);
            } else {
               g.drawImage(crystal.getImage(), cryst.x, cryst.y, cryst.width, cryst.height, this);
            }
			}
			crystalRemove();
			drawEnemy(g);
			enemyTest();
			keyTest();
			drawDoor(g);
			flyCrystalTest();
		}
		
		public void createBossLevel1(){
			if(platformSet.isEmpty()){
				addPlatform(-1000, 700, 6000, 1000, "", "platform");
				addPlatform(-1000, -1000, 1500, 2000, "", "platform");
				addPlatform(500, -1000, 900, 1500, "", "platform");
				addPlatform(2500, -1000, 1500, 2000, "", "platform");
				addPlatform(1000, -1000, 3000, 500, "", "platform");
			}
			if(enemySet.isEmpty()){
				addEnemy(1950, 500, 100, 200, 2, 4, 0, false);
				
			}
		}
		public void drawBossLevel1(Graphics g){
			
			
				g.setColor(new Color(0, 70, 0));
				for(Platform rect : platformSet){
					g.fillRect(rect.x, rect.y, rect.width, rect.height);
				}
				drawEnemy(g);
			
		}
		
		public void addPlatform(int x, int y, int width, int height, String type, String set){
			Platform platform = new Platform(x, y, width, height, type);
			if(set.equals("platform")){
				platformSet.add(platform);	
			} else if(set.equals("crystal")){
				if(level.equals("blue")){
					blueCrystalSet.add(platform);
				} else if(level.equals("red")){
					redCrystalSet.add(platform);
				} else if(level.equals("green")){
					greenCrystalSet.add(platform);
				} else if(level.equals("yellow")){
					yellowCrystalSet.add(platform);
				}
				crystalSet.add(platform);
			} else if(set.equals("door")){
				doorSet.add(platform);
			} else if(set.equals("death area")){
				deathAreaSet.add(platform);
			} else if(set.equals("key")){
				keySet.add(platform);
			} else if(set.equals("flyCrystal")){
				flyCrystalSet.add(platform);
			}
		}
		public void addEnemy(int x, int y, int width, int height, int speed, int type, int length, boolean movingNegative){
			Enemy enemy = new Enemy(x, y, width, height, speed, type, length, movingNegative);
			enemySet.add(enemy);
		}
		public void addMovingPlatform(int x, int y, int width, int height, int length, int speed, boolean horizontal, boolean movingNegative, String type){
			MovingPlatform mp = new MovingPlatform(x, y, width, height, length, speed, horizontal, movingNegative, type);
			if(!mp.isMovingNegative()){
				mp.setCounter(mp.getLength());
			}
			movingPlatformSet.add(mp);
		}
		
		public void enemyMove(Enemy enemy){
			if(!paused && !wait){
			if((enemy.getX() < W_WIDTH && enemy.getX() + enemy.getWidth() > 0 && enemy.getY() < W_HEIGHT && enemy.getY() > 0) 
			|| enemy.getType() == 3 || enemy.getType() == 5 || enemy.getType() == 6){
				enemy.setMoving(true);
			}
			if((enemy.getX() < W_WIDTH && enemy.getX() + enemy.getWidth() > 0 && enemy.getY() < W_HEIGHT && enemy.getY() > 0)
			&& !firstEnemyOnScreen){
				firstEnemyOnScreen = true;
			}
			if(enemy.getType() != 4){
			if(enemy.isMoving()){
			enemy.setCounter(enemy.getCounter() + 1);
			if(enemy.isMovingNegative()){
				if(enemy.getSpeed() == enemy.getCounter()){
					enemy.setCounter(0);
					enemy.setX(enemy.getX() + 1);
				if(enemy.getType() == 1 || enemy.getType() == 2){	
					if(((isRightTouching(enemy.getX(), enemy.getY() - 2, enemy.getWidth(), enemy.getHeight()) || 
					isLeftTouching(enemy.getX(), enemy.getY() - 2, enemy.getWidth(), enemy.getHeight()) ||
				    isMovingPlatformRightTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()) ||
				    isMovingPlatformLeftTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())) && 
			  	    isEnemyTouchingPlatform(enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight())) ||
				    (isEnemyTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())&& !enemy.isDead())){
						enemy.setMovingNegative(false);
					}
					for(Platform rect : platformSet){
						if( enemy.getX() + enemy.getWidth() + 1  == rect.x && enemy.getY() <= rect.y + rect.height + 4 && 
						enemy.getY() + enemy.getHeight() >= rect.y -4 && (rect.width == 1 || rect.width == 2)){
							enemy.setY(enemy.getY() - 1);
						}
					}
				} else if(enemy.getType() == 3 || enemy.getType() == 6){
					enemy.setLengthCount(enemy.getLengthCount() + 1);
					if(enemy.getLengthCount() == enemy.getLength()){
						enemy.setMovingNegative(false);
						enemy.setLengthCount(0);
					}
				}
			}
				
			} else {
				if(enemy.getSpeed() == enemy.getCounter()){
					enemy.setCounter(0);
					enemy.setX(enemy.getX() - 1);
					if(enemy.getType() == 1 || enemy.getType() == 2){
						if(((isRightTouching(enemy.getX(), enemy.getY() - 2, enemy.getWidth(), enemy.getHeight()) || 
						   isLeftTouching(enemy.getX(), enemy.getY() - 2, enemy.getWidth(), enemy.getHeight()) ||
						   isMovingPlatformRightTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()) ||
						   isMovingPlatformLeftTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())) && 
						   isEnemyTouchingPlatform(enemy.getX(), enemy.getY() + 1, enemy.getWidth(), enemy.getHeight())) ||
						   (isEnemyTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())&& !enemy.isDead())){
							enemy.setMovingNegative(true);
						}	
						for(Platform rect : platformSet){
							if( enemy.getX() - 1  == rect.x + rect.width && enemy.getY() <= rect.y + rect.height + 4 && 
							enemy.getY() + enemy.getHeight() >= rect.y -4 && (rect.width == 1 || rect.width == 2)){
								enemy.setY(enemy.getY() - 1);
							}
						}
					} else if(enemy.getType() == 3 || enemy.getType() == 6){
						enemy.setLengthCount(enemy.getLengthCount() + 1);
						if(enemy.getLengthCount() == enemy.getLength()){
							enemy.setMovingNegative(true);
							enemy.setLengthCount(0);
						}
					}	
				}
			}
			}
			if(!isEnemyTouchingPlatform(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()) 
			&& (enemy.getType() == 1 || enemy.getType() == 2)
			&& (!isOnTopOfEnemy(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()))){
				
				enemy.setY(enemy.getY() + 1);
			}
			}
			if(enemy.getType() == 4){
				bossMove(enemy);
			}
			}
			if(!(enemy.getX() < W_WIDTH && enemy.getX() + enemy.getWidth() > 0 && enemy.getY() < W_HEIGHT && enemy.getY() > 0) 
			&& enemy.getType() == 5){
				enemy.setDeath(true);
			}
		}
		
		// LISTEN UP BITCH TITS, UNLESS YOU HAVE CHECKED IN WITH KEVIN RAWLS YOU HAVE NO AUTHORITY TO CHANGE THIS METHOD IN ANY WAY
		// This is the method that moves a moving platform passed in. It also handles
		// a ton of other stuff in terms of the platforms interactivity with the bear and other obstacles.
		// Just a message for the future, this method is not perfect and I would avoid at all costs having moving platforms
		// interact with each other or enemies. Also, this kind of only works for moving platforms that are
		// initially not moving negative if after you initialize it, you set its counter to its length.
		// Here's Mr bubbles <(^.^<) ^(^.^)^ (>^.^)>
		public void platformMove(MovingPlatform mp){
			if(!paused && !wait){
			if(y + 1 == mp.getY() + mp.getHeight() && 
			x <= mp.getX() + mp.getWidth() && 
			x + BEAR_WIDTH >= mp.getX()&& !mp.getType().equals("2")){
				y += 1;
				mp.setY(mp.getY() -  2);
				mp.setCounter(mp.getCounter() + 2);                                          
				shouldReverse = true;
				//falling = true;
			}
			mp.setSpeedCount(mp.getSpeedCount() + 1);
			if(y == mp.getY() + mp.getHeight() && x <= mp.getX() + mp.getWidth() && x + BEAR_WIDTH >= mp.getX() && isJumping && !mp.getType().equals("2")){
				y += 1;
			}
			if(mp.isMovingNegative()){
				if(x - 1 == mp.getX() + mp.getWidth() && 
				y <= mp.getY()+ mp.getHeight() && 
				y + BEAR_HEIGHT >= mp.getY() && mp.isHorizontal() 
				&& !isLeftTouching(x + 1, y, BEAR_WIDTH, BEAR_HEIGHT)&& !mp.getType().equals("2")){
					x += 1;
				}
				if(x - 1 == mp.getX() + mp.getWidth() && 
				y <= mp.getY()+ mp.getHeight() && 
				y + BEAR_HEIGHT >= mp.getY() && !isJumping
				&& !mp.isHorizontal()){
					x += 1;
				}
				if(mp.getSpeed() == mp.getSpeedCount()){
					mp.setSpeedCount(0);
					if(mp.isHorizontal()){
						mp.setX(mp.getX() + 1);
						if(y + BEAR_HEIGHT == mp.getY() - 1 && 
						x <= mp.getX() + mp.getWidth() &&
						x + BEAR_WIDTH >= mp.getX() &&
						!isLeftTouching(x + 1, y, BEAR_WIDTH, BEAR_HEIGHT) &&
						!isLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){ 
							x += 1;
						}
					} else {
						if(x <= mp.getX() + mp.getWidth() && x + BEAR_WIDTH >= mp.getX() && 
						(y + BEAR_HEIGHT == mp.getY() - 1 || y + BEAR_HEIGHT == mp.getY())){
							y -= 1;
							
						}
						mp.setY(mp.getY() - 1);
					}
					mp.setCounter(mp.getCounter() + 1);		
					if(mp.getCounter() == mp.getLength() || (  !mp.getType().equals("2") &&
						(!falling && !debug && !isFlying &&((mp.getY() + mp.getHeight() == y - 1 || 
						mp.getY() + mp.getHeight() == y ||
						mp.getY() + mp.getHeight() == y + 1)  && 
						(mp.getX() <= x + BEAR_WIDTH  && 
						mp.getX() + mp.getWidth() >= x ) && !falling && (!isJumping || (isJumping && jumpCount <= 3)) &&
						!isMovingPlatformRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT) &&
						!isMovingPlatformLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT))) || 
						(isLeftTouching(x + 1, y, BEAR_WIDTH, BEAR_HEIGHT) &&
						x - 1 == mp.getX() + mp.getWidth() && 
						y <= mp.getY()+ mp.getHeight() && 
						y + BEAR_HEIGHT >= mp.getY())||
						(!mp.isHorizontal() && isOnTopOfPlatform(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight())) ||
						(mp.isHorizontal() && ((isRightTouching(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight()) ||
						isLeftTouching(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight()) || 
						((isMovingPlatformRightTouching(mp.getX() - 1, mp.getY(), mp.getWidth(), mp.getHeight()) ||
						isMovingPlatformLeftTouching(mp.getX() + 1, mp.getY(), mp.getWidth(), mp.getHeight()))))))) 
						){ 
							if(mp.getCounter() != mp.getLength()){
								mp.setCounter(mp.getCounter());
								shouldReverse = false;                                            
							} else {
								mp.setLength(mp.getOrigLength());
								mp.setCounter(mp.getOrigLength());
							}
							mp.setMovingNegative(false);
							mp.setSpeedCount(0);				
						} 
					}		
				} else {
					if(x + BEAR_WIDTH + 2 == mp.getX() && 
					y <= mp.getY() + mp.getHeight() && 
					y + BEAR_HEIGHT >= mp.getY() && 
					mp.isHorizontal() && 
					!isRightTouching(x - 1, y, BEAR_WIDTH, BEAR_HEIGHT) && !mp.getType().equals("2")){
						x -= 1;
					} 
					if(x - 1 == mp.getX() + mp.getWidth() && 
					y <= mp.getY()+ mp.getHeight() && 
					y + BEAR_HEIGHT >= mp.getY() && !isJumping && 
					!mp.isHorizontal()){
						x += 1;
					}
					if(mp.getSpeed() == mp.getSpeedCount()){
						mp.setSpeedCount(0);
						mp.setCounter(mp.getCounter() - 1);
						if(mp.isHorizontal()){
							mp.setX(mp.getX() - 1);
							if(y + BEAR_HEIGHT == mp.getY() - 1 && 
							x <= mp.getX() + mp.getWidth() &&
							x + BEAR_WIDTH >= mp.getX() &&
							!isRightTouching(x - 1, y, BEAR_WIDTH, BEAR_HEIGHT) &&
							!isRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT)){
								x -= 1;
							}
						} else {
							if(x <= mp.getX() + mp.getWidth() && x + BEAR_WIDTH >= mp.getX() && y + BEAR_HEIGHT == mp.getY() - 1){
								y += 1;
								falling = false;
							}
							if(x <= mp.getX() + mp.getWidth() && x + BEAR_WIDTH >= mp.getX() && 
							(y == mp.getY() + mp.getHeight() + 2 || y == mp.getY() + mp.getHeight() + 1) && (isFlying || debug)){
								y+=1;
							}
							mp.setY(mp.getY() + 1);	
						}
						if(mp.getCounter() == 0 ||  (!mp.getType().equals("2")&&
						(!falling && !debug && !isFlying &&((mp.getY() + mp.getHeight() == y - 1 || 
						mp.getY() + mp.getHeight() == y ||
						mp.getY() + mp.getHeight() == y + 1)  && 
						(mp.getX() <= x + BEAR_WIDTH   && 
						mp.getX() + mp.getWidth() >= x ) && !falling && (!isJumping || (isJumping && jumpCount <= 3)) && 
						!isMovingPlatformRightTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT) &&
						!isMovingPlatformLeftTouching(x, y, BEAR_WIDTH, BEAR_HEIGHT))) || 
						((isRightTouching(x - 1, y, BEAR_WIDTH, BEAR_HEIGHT) && 
						x + BEAR_WIDTH + 2 == mp.getX() && 
						y <= mp.getY() + mp.getHeight() && 
						y + BEAR_HEIGHT >= mp.getY())) || shouldReverse ||
						(!mp.isHorizontal() && isOnTopOfPlatform(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight())) ||
						(mp.isHorizontal() && ((isRightTouching(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight()) ||
						isLeftTouching(mp.getX(), mp.getY(), mp.getWidth(), mp.getHeight()) || 
						( (isMovingPlatformRightTouching(mp.getX() - 1, mp.getY(), mp.getWidth(), mp.getHeight()) ||
						isMovingPlatformLeftTouching(mp.getX() + 1, mp.getY(), mp.getWidth(), mp.getHeight()))))))) 
						){ 
							if(mp.getCounter() != 0){
								if(isMovingPlatformTouching(x, y +1, BEAR_WIDTH, BEAR_HEIGHT) && level.equals("green")){
									bearDead = true;
									if(!bearYelling){
										playSound(bearYell);
										bearYelling = true;
									}
								}
								mp.setLength(mp.getOrigLength() - mp.getCounter());
								shouldReverse = false;
							} else {
								mp.setLength(mp.getOrigLength());
							}
							mp.setMovingNegative(true);
							mp.setCounter(0);
							mp.setSpeedCount(0);
						} else {
							for(Enemy enemy : enemySet){
								if(!enemy.isDead()){
									if(mp.getY() + mp.getHeight() == enemy.getY()  && 
									(mp.getX() - 1  <= enemy.getX() + enemy.getWidth()  && 
									mp.getX() + mp.getWidth() >= enemy.getX() ) &&
									!isMovingPlatformRightTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()) &&
									!isMovingPlatformLeftTouching(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())){
										mp.setLength(mp.getOrigLength() - mp.getCounter());
										mp.setMovingNegative(true);
										mp.setCounter(0);
									}	
								}
							}
						}
					}	
				}	
			}
		}
		
		public void bossMove(Enemy boss){
			if(barrierDown){
				if(bossDeciding && !bossFlash){
					bossDecideCount++;
					if(bossDecideCount == 10){
						Random randy = new Random();
						
						if(randy.nextInt(2) == 1){
							bossJump = true;
						} else {
							bossHorizontal = true;
						}	
						bossDecideCount = 0;
						bossDeciding = false;
					}
				}
				if(bossFlash){
					bossMovedHorizontal = false;
					bossHorizontalCount = 0;
					boss.setLengthCount(0);
					bossHorizontal = false;
					bossJump = false;
					bossJumpCount = 0;
					bossFlashCount++;
					if(bossFlashCount == 800){
						bossFlash = false;
						bossFlashCount = 0;
						bossDeciding = true;
						if(bossDying){
							boss.setDeath(true);
							bossDead = true;
						}
						
					}
					bossFlashTempCount++;
					if(bossFlashTempCount >= 10 && bossFlashTempCount < 30){
						boss.setX(100000);
					} else {
						boss.setX(1950);
						adjustLevel();
					}
					if(!bossFlash){
						boss.setX(1950);
						adjustLevel();
					}
					if(bossFlashTempCount >= 30){
						bossFlashTempCount = 0;
					}
				}
				if(bossHorizontal && !bossDead){
					boss.setCounter(boss.getCounter() + 1);
					if(boss.getCounter() == boss.getSpeed()){
						boss.setCounter(0);
						if(!bossMovedHorizontal){
							if(x + xsideScrollCount <= 2000){
								boss.setMovingNegative(true);
							} else {
								boss.setMovingNegative(false);
							}
							bossMovedHorizontal = true;
						}
						if(boss.isMovingNegative()){
							boss.setX(boss.getX() - 1);
							boss.setLengthCount(boss.getLengthCount() + 1);
							if(boss.getLengthCount() == 200){
								boss.setMovingNegative(false);
								boss.setLengthCount(0);
							}
						} else {
							boss.setX(boss.getX() + 1);
							boss.setLengthCount(boss.getLengthCount() + 1);
							if(boss.getLengthCount() == 200){
								boss.setMovingNegative(true);
								boss.setLengthCount(0);
							}
						}
						bossHorizontalCount++;
					}
					
					if(bossHorizontalCount == 400){
						bossHorizontal = false;
						bossDeciding = true;
						bossHorizontalCount = 0;
						bossMovedHorizontal = false;
					}
				}
				if(bossJump && !bossFlash && !bossDead){
					bossJumpTempCount++;
					if(bossJumpTempCount == 2){
						bossJumpTempCount = 0;
						bossJumpCount++;
						if(bossJumping){
							boss.setY(boss.getY() - 1);
						} else {
							boss.setY(boss.getY() + 1);
						}
						if(bossJumpCount <= 200){
							bossJumping = true;
						} else {
							bossJumping = false;
						}
						if(bossJumpCount == 100 && x + xsideScrollCount < 2000){
							addEnemy(1940 - xsideScrollCount , 520 + ysideScrollCount, 40, 40, 1, 5, 0, false);
						}
						if(bossJumpCount == 260 && x + xsideScrollCount < 2000){
							addEnemy(1940 - xsideScrollCount , 500 + ysideScrollCount, 40, 40, 1, 5, 0, false);
						}
						if(bossJumpCount == 380 && x + xsideScrollCount < 2000){
							addEnemy(1940 - xsideScrollCount , 620 + ysideScrollCount, 40, 40, 1, 5, 0, false);
						}
						if(bossJumpCount == 100 && x + xsideScrollCount >= 2000){
							addEnemy(2040 - xsideScrollCount , 520 + ysideScrollCount, 40, 40, 1, 5, 0, true);
						}
						if(bossJumpCount == 260 && x + xsideScrollCount >= 2000){
							addEnemy(2040 - xsideScrollCount , 500 + ysideScrollCount, 40, 40, 1, 5, 0, true);
						}
						if(bossJumpCount == 380 && x + xsideScrollCount >= 2000){
							addEnemy(2040 - xsideScrollCount , 620 + ysideScrollCount, 40, 40, 1, 5, 0, true);
						}
						if(bossJumpCount >= 400){
							bossJump = false;
							bossDeciding = true;
							bossJumpCount = 0;
						}
					}
				}
			} else {
				boss.setLengthCount(0);
			}	
			
		}
		
		public void crystalRemove(){
			
				for(int i = 0; i < crystalSet.size(); i++){
					if(bear.intersects(new Rectangle(crystalSet.get(i).x, crystalSet.get(i).y, 
					crystalSet.get(i).width, crystalSet.get(i).height ))){
						if(level.equals("blue")){
							blueCrystalSet.remove(i);
						} else if(level.equals("red")){
							redCrystalSet.remove(i);
						} else if(level.equals("green")){
							greenCrystalSet.remove(i);
						} else if(level.equals("yellow")){
							yellowCrystalSet.remove(i);
						}
						if(crystalSet.get(i).type.equals("super")){
							superCrystalCount++;
							playSound(superCrystalGet);
						} else {
							crystalCount++;
							playSound(coinSound);
						}	
						crystalSet.remove(i);
						
						
					}
				}
			}
			
		
		
		public boolean hasCollectedCrystals(){
			if(levelCrystals == crystalCount){
				return true;
			} 
			return false;
		}
		
		public void openDoor(Graphics g){
			doorOpened = new ImageIcon("C:/Users/Kevin/Documents/Bear With Wings/BEAR WITH WINGS Images/Bear with Wings Door Opened.gif");
			door = doorOpened.getImage();
//			if(level == 1 && hasOrangeKey){
//				g.drawImage(door, winSquare.x, winSquare.y, this);
//				if(bear.intersects(winSquare)){
//					//win = true;
//				}
//			}
		}
		
		public void bossHandler(){
			if(!hasEnteredBossArea && x + xsideScrollCount >= 1550){	
				waitNum = 500;
				wait = true;
				hasEnteredBossArea = true;
				playSound(bossScream);
			}
			if(!barrierDown && !wait && x + xsideScrollCount >= 1550){
				addPlatform(1300, -500, 100, 2000, "", "platform");
				adjustLevel();
				barrierDown = true;
				bossDeciding = true;
				
			}
		}
		
		public void bossDeath(){
			bossDeathCount++;
			if(bossDeathCount == 500){
				playSound(bossScream);
			} 
			if(bossDeathCount == 800){
				playSound(bossScream);
			}
			if(bossDeathCount == 2000){
				credits = true;
			}
		}
		
		public void credits(Graphics g){
			g.setColor(Color.black);
			g.fillRect(-1000, -1000, 5000, 5000);
			if(bossDeathCount >= 14500){
				creditTempCount++;
				if(creditTempCount == 9 && creditCount < 5800){
					creditCount++;
					creditTempCount = 0;
				}
				g.setColor(Color.WHITE);
				font = new Font("Arial", Font.PLAIN, 50);
				g.setFont(font);
				g.drawString("BEAR WITH WINGS", 430, 800 - creditCount);
				font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				g.drawString("A Game By Kevin Rawls", 500, 1100 - creditCount);
				g.drawString("With Help From Garrett Goody", 465, 1300 - creditCount);
				g.drawString("Additional Help From Calvin Cotton", 435, 1500 - creditCount);
				//g.drawString("Senior Executive Programming Lead Manager: Kevin Rawls", 400, 1700 - creditCount);
				//g.drawString("Play Testers: Keegan McElligott, Brian Helmholz", 330, 1700 - creditCount);
				//g.drawString("Special Thanks: Joachim Blaafjell Holwech", 380, 1900 - creditCount);
				/*g.drawString("JOACHIM WHY DID YOU HAVE TO LEAVE!", 370, 2100 - creditCount);
				g.drawString("Yelling At Kevin: Garrett Goody, Calvin Cotton", 370, 2300 - creditCount);
				g.drawString("Tree And Bear Sprite Designer: Garrett Goody", 380, 2500 - creditCount);
				g.drawString("Convincing Kevin To Not Be Such A Shitty Programmer: Calvin Cotton", 210, 2700 - creditCount);
				g.drawString("Still A Shitty Programmer: Kevin Rawls", 420, 2900 - creditCount);
				g.drawString("Attemting To Comment The Game Code: Garrett Goody", 310, 3100 - creditCount);
				g.drawString("Playing Smash Bros While Kevin Worked On The Game: Garrett Goody, Keegan McElligott", 50, 3300 - creditCount);
				g.drawString("Telling Us We Could Have Atleast Tried: Joachim Blaafjell Holwech", 250, 3500 - creditCount);
				g.drawString("First person to get all 5 super crystals: Jacob Baqqian", 350, 3700 - creditCount);
				g.drawString("AND YOU!", 570, 3900 - creditCount);
            */
            g.drawString("Special Thanks:", 550, 1700 - creditCount);
            g.drawString("Keegan McElligott", 540, 1770 - creditCount);
            g.drawString("Brian Helmholz", 560, 1840 - creditCount);
            g.drawString("Joachim Blaafjell Holwech", 490, 1910 - creditCount);
            g.drawString("Jacob Baqqian", 560, 1980 - creditCount);
            g.drawString("Music:", 610, 2100 - creditCount);
            g.drawString("Cave Story (Studio Pixel(PC), Nicalis(WiiWare/DSiWare/3DS))", 260, 2180 - creditCount);
            g.drawString("Mr. Gimmick (Sunsoft, Famicom)", 450, 2260 - creditCount);
            g.drawString("Mega Man 5 (Capcom, NES)", 475, 2340 - creditCount);
            g.drawString("Fantasy Zone (Tengen, NES)", 473, 2420 - creditCount);
            g.drawString("Batman (SunSoft, NES)", 510, 2500 - creditCount);
            g.drawString("Duck Tales (Capcom, NES)", 480, 2580 - creditCount);
            g.drawString("Mega Man 2 (Capcom, NES)", 474, 2660 - creditCount);
            g.drawString("The Legend Of Zelda, A Link To The Past (Nintendo, SNES)", 270, 2740 - creditCount);
            g.drawString("Earthbound (Nintendo, SNES)", 460, 2820 - creditCount);
            g.drawString("Super Mario Bros (Nintendo, NES)", 430, 2900 - creditCount);
            g.drawString("DiddyKongFan1 (Youtube)", 480, 2980 - creditCount);

				g.drawString("Cast:", 615, 3200 - creditCount);
				
				g.drawImage(enemyIcon1Up.getImage(), 620, 3300 - creditCount, this);
				g.drawString("Goomba", 595, 3400 - creditCount);
				
				g.drawImage(enemyIcon2Right.getImage(), 630, 3600 - creditCount, 40, 70, this);
				g.drawString("Purple Guy", 570, 3700 - creditCount);
				
				g.drawImage(enemyIcon3Right.getImage(), 590, 3900 - creditCount, 120, 25, this);
				g.drawString("Flying Dude", 570, 4000 - creditCount);
				
				g.drawImage(enemyIcon4Right.getImage(), 620, 4200 - creditCount, 40, 50, this);
				g.drawString("Orange Bro", 560, 4300 - creditCount);
				
				g.drawImage(bigTree.getImage(), 370, 4400 - creditCount, this);
				g.drawString("Big Tree", 440, 4720 - creditCount);
				
				g.drawImage(smallTree.getImage(), 820, 4528 - creditCount, this);
				g.drawString("Lil Tree", 830, 4720 - creditCount);
				
				g.drawImage(boss1RightIcon.getImage(), 610, 4870 - creditCount, 100, 200, this);
				g.drawString("The Boss",600, 5150 - creditCount);
				
				g.drawImage(bearRight.getImage(), 630, 5300 - creditCount, this);
				g.drawString("And Bear!", 590, 5420 - creditCount);
				
				g.drawString("And So Bear Defeated The Boss And Saved The World.", 320, 5600 - creditCount);
				g.drawString("And We All Learned A Valuable Lesson.", 410, 5700 - creditCount);
				g.drawString("Thanks For Playing!", 530, 5800 - creditCount);
				font = new Font("Arial", Font.PLAIN, 50);
				g.setFont(font);
				g.drawString("THE END", 550, 6200 - creditCount);
			}	
		}
		
		public void drawAlertBox(Graphics g){
			g.setColor(Color.BLACK);
			g.fillRect(345, 175, 610, 310);
			g.setColor(new Color(200, 200, 200));
			g.fillRect(350, 180, 600, 300);
			font = new Font("Arial", Font.PLAIN, 30);
			g.setFont(font);
			g.setColor(new Color(180, 0, 220));
			if(alertMessage.equals("first crystal")){
				g.drawString("You've Collected A Crystal!", 450, 300);
				g.drawString("Collect 100 And press F To Fly", 430, 350);
			} else if(alertMessage.equals("enemy")){
				g.drawString("Stomp On An Enemy To Squash 'Em", 400, 300);
			} else if(alertMessage.equals("flyCrystal")){
				g.drawString("Boom, You Just Collected a Power Crystal!", 375, 250);
				g.drawString("Use The Arrow Keys To Fly", 470, 300);
				g.drawString("Also You're Invincible", 500, 350);
			}  else if(alertMessage.equals("start")){
				g.drawString("This is Bear, He Has To Save The World", 375, 250);
				g.drawString("But He Hurt His Wing", 500, 300);
				g.drawString("Use The Arrow Keys To Move", 450, 350);
				g.drawString("Press The Space Bar To Jump", 445, 400);
			} else if(alertMessage.equals("hub1")){
				g.setColor(Color.BLACK);
				g.fillRect(285, 175, 670, 310);
				g.setColor(new Color(200, 200, 200));
				g.fillRect(290, 180, 660, 300);
				g.setColor(new Color(180, 0, 220));
				g.drawString("Collect The Four Keys And Enter The Last Door", 310, 330);
			} else if(alertMessage.equals("super crystal")){
				g.drawString("You Just Collected A Super Crystal!", 400, 300);
				g.drawString("Collect All 5 To Unlock Something Cool", 380, 350);
			} else if(alertMessage.equals("all keys")){
				g.drawString("You've Collected All Four Keys!", 440, 280);
				g.drawString("You Can Now Preform A Double Jump", 400, 330);
				g.drawString("Enter The Final Door And Face The Boss", 380, 380);
			} else if(alertMessage.equals("all super crystals")){
				g.drawString("You've Collected All Five Super Crystals!", 380, 280);
				g.drawString("Give Yourself A Good Pat On The Back", 400, 330);
				g.drawString("You've Earned It, Champ", 485, 380);
            if(!hasGivenPlayerAMillionCrystals){
             crystalCount += 1000000;
             hasGivenPlayerAMillionCrystals = true;
            }
			}
			font = new Font("Arial", Font.PLAIN, 20);
			g.setFont(font);
			g.drawString("press space to return to the game", 600, 450);
		}
		
		public void alertBoxHandler(){
			if(crystalCount == 1 && !alert1){
				alertMessage = "first crystal";
				alert = true;
				alert1 = true;
			} else if(firstEnemyOnScreen && !alert2){
				alertMessage = "enemy";
				alert = true;
				alert2 = true;
			} else if(ateFlyCrystal && !alert3 && !used100Crystals){
				alertMessage = "flyCrystal";
				alert = true;
				alert3 = true;
			} else if(level.equals("hub") && !alert4 && hasGameStarted && !hub && !gameStart){
				alertMessage = "start";
				alert = true;
				alert4 = true;
			} else if(level.equals("hub1") && !alert5){
				alertMessage = "hub1";
				alert = true;
				alert5 = true;
			} else if(superCrystalCount == 1 && !alert6){
				alertMessage = "super crystal";
				alert = true;
				alert6 = true;
			} else if(hasBlueKey && hasRedKey && hasGreenKey && hasYellowKey && !alert7){
				alertMessage = "all keys";
				alert = true;
				alert7 = true;
			} else if(superCrystalCount == 5 && !alert8){
				alertMessage = "all super crystals";
				alert = true;
				alert8 = true;
			}
		}
		
		public void bearDeath(){
			
		}
		
		public Clip getMusic(String url) {
			Clip clipy = null;
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
			    clipy = AudioSystem.getClip();
			    clipy.open(audioInputStream);	
			} catch(Exception e) {
			    e.printStackTrace();
		    }
			return clipy;
		}
		public void playSound(String url){
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
			    Clip clipy = AudioSystem.getClip();
			    clipy.open(audioInputStream);	
			    clipy.loop(0);
			} catch(Exception e) {
			    e.printStackTrace();
		    }
		}
		
		public void musicHandler(){
			if(level.equals("hub1") && !hub1Music.isRunning() && !isFlying){
				hub1Music.loop(100000);
			} else if((!level.equals("hub1")|| isFlying) && hub1Music.isRunning()){
				hub1Music.stop();
			} else if(level.equals("red") && !redMusic.isRunning() && !isFlying){
				redMusic.loop(1000000);
			} else if((!level.equals("red")|| isFlying) && redMusic.isRunning()){
				redMusic.stop();
				redMusic.close();
				redMusic = getMusic(redURL);
			} else if(level.equals("blue") && !blueMusic.isRunning() && !isFlying){
				blueMusic.loop(1000000);
			} else if((!level.equals("blue")|| isFlying) && blueMusic.isRunning()){
				blueMusic.stop();
				blueMusic.close();
				blueMusic = getMusic(blueURL);
			} else if(level.equals("hub") && !mainHubMusic.isRunning() && !isFlying){
				mainHubMusic.loop(1000000);
			} else if((!level.equals("hub") || isFlying) && mainHubMusic.isRunning()){
				mainHubMusic.stop();
				mainHubMusic.close();
				mainHubMusic = getMusic(mainHubURL);
			} else if(level.equals("green") && !greenMusic.isRunning() && !isFlying){
				greenMusic.loop(1000000);
			} else if((!level.equals("green") || isFlying) && greenMusic.isRunning()){
				greenMusic.stop();
				greenMusic.close();
				greenMusic = getMusic(greenURL);
			} else if(level.equals("yellow") && !yellowMusic.isRunning() && !isFlying){
				yellowMusic.loop(1000000);
			} else if((!level.equals("yellow") || ateFlyCrystal) && yellowMusic.isRunning()){
				yellowMusic.stop();
				yellowMusic.close();
				yellowMusic = getMusic(yellowURL);
			} else if(level.equals("boss") && !bossTheme.isRunning() && !barrierDown && !hasEnteredBossArea){
				bossTheme.loop(1000000);
			} else if(level.equals("boss") && bossTheme.isRunning() && hasEnteredBossArea){
				bossTheme.stop();
				bossTheme = getMusic(bossThemeURL);
			}else if(level.equals("boss") && !bossBattle.isRunning() && barrierDown && !bossDying){
				bossBattle.loop(1000000);
			} else if(level.equals("boss") && bossBattle.isRunning()  && (bossDying || !barrierDown)){
				bossBattle.stop();
				bossBattle = getMusic(bossBattleURL);
			} else if(level.equals("boss") && credits && bossDeathCount == 3000){
				creditsSong.start();
			} else if(isFlying && !level.equals("boss") && !flyMusic.isRunning()){
				flyMusic.loop(10000);
			} else if(!isFlying &&flyMusic.isRunning()){
				flyMusic.stop();
				flyMusic = getMusic(flyURL);
			}
		}
		
		public void wait(int waitNumber){
			waitCount++;
			if(waitCount == waitNumber){
				wait = false;
				waitCount = 0;
			}
		}
		
		public void paintComponent(Graphics g){
			if(hasGameStarted){
            enemy1AnimationCount++;
            if(enemy1AnimationCount <= 200){
               enemyAnimation1 = true;
            } else if(enemy1AnimationCount <= 400){
               enemyAnimation1 = false;
            } else {
               enemy1AnimationCount = 0;
            }
				if(wait){
					wait(waitNum);
				}
				if(bossDying){
					bossDeath();
				}
				
				if(titleScreenMusic.isRunning()){
					titleScreenMusic.stop();
				}
				if(level.equals("boss")){
					bossHandler();
				}
				musicHandler();
				currentX = x + xsideScrollCount;
				currentY = y - ysideScrollCount;
				font = new Font("Comic Sans MS", Font.PLAIN, 20);
				g.setFont(font);
				
				if(paused && !wait && !bossDead && !alert && !bearDead){
					g.setColor(Color.black);
					g.drawString("Paused Bitch", 600, 230);
				}
				if(bearDead){
					paused = true;
					bearDeadCount++;
					if(bearDeadCount == 200){
						resetLevel();
						
						
					}
				}
				if(bossDead && isTouching){
					paused = true;
				}
				if(hasBlueKey && hasRedKey &&hasGreenKey && hasYellowKey){
					doubleJump = 2;
				}
				drawLevel(g);
				g.setColor(Color.MAGENTA);
				
				//g.drawString("current x: " + currentX + " current y: " + currentY, 230, 50);
				if(x == 670 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					x = 669;
				}
				if(x == 630 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					x = 631;
				}
				if(y == 420 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					y = 419;
				}
				if(y == 279 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					y = 280;
				}
				font = new Font("Arial", Font.PLAIN, 20);
				g.setFont(font);
				//g.drawString("actual x: " + x + " actual y: " + y, 230, 70);
				g.setColor(Color.BLACK);
				g.fillRect(220, 65, 187, 70);
				g.setColor(Color.WHITE);
				g.drawString("Crystals: " + crystalCount, 230, 90);
				g.drawString("Super Crystals: " + superCrystalCount + "/5", 230, 120);
				//g.drawString("Time elapsed: " + levelTime / 1000.0, 230, 110);
				//g.drawString("" +credits, 230, 130);
				if(level.equals("blue")){
					drawBackgroundArt(g, xsideScrollCount * -1, ysideScrollCount * -1);
				}
				if(x == 670 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					x = 669;
				}
				if(x == 630  &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					x = 631;
				}
				if(y == 420 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					y = 419;
				}
				if(y == 279 &&  !isMovingPlatformTouching(x, y + 1, BEAR_WIDTH, BEAR_HEIGHT)){
					y = 280;
				}
				bear = new Rectangle(x, y, BEAR_WIDTH, BEAR_HEIGHT);
				g.setColor(Color.yellow);
				//g.drawRect(x, y, BEAR_WIDTH, BEAR_HEIGHT);
				
					//g.drawImage(character,x + 3, y, BEAR_WIDTH, BEAR_HEIGHT, this);
           if(!bearYelling){
           if(!isFlying){
            if(isJumping && (character == bearRight.getImage() || character == bearMoveOneRight.getImage() || character == bearMoveTwoRight.getImage())){
               character = bearJumpUpRight.getImage();
            }
            if(isJumping && (character == bearLeft.getImage()|| character == bearMoveOneLeft.getImage() || character == bearMoveTwoLeft.getImage())){
               character = bearJumpUpLeft.getImage();
            }
            if((exitJump || falling) && character == bearJumpUpRight.getImage()){
               character = bearJumpDownRight.getImage();
            }
            if((exitJump || falling) && character == bearJumpUpLeft.getImage()){
               character = bearJumpDownLeft.getImage();
            }
            if(isJumping && (character == bearJumpUpRight.getImage() || character == bearJumpUpLeft.getImage())){
               if(isPressingRight){
                  character = bearJumpUpRight.getImage();
               } else if(isPressingLeft){
                  character = bearJumpUpLeft.getImage();
               }
            }
            if((exitJump || falling) && (character == bearJumpDownRight.getImage() || character == bearJumpDownLeft.getImage())){
               if(isPressingRight){
                  character = bearJumpDownRight.getImage();
               } else if(isPressingLeft){
                  character = bearJumpDownLeft.getImage();
               }
            }
            if(isPressingRight && !isJumping && !exitJump && !falling && !(wait || (bossDying && paused))){
               bearMovingRightCount++;
               if(bearMovingRightCount <= 100){
                  character = bearMoveOneRight.getImage();
               } else if(bearMovingRightCount <= 200){
                  character = bearRight.getImage();
               } else if(bearMovingRightCount <= 300){
                  character = bearMoveTwoRight.getImage();
               } else if(bearMovingRightCount <= 400){
                  character = bearRight.getImage();
               } else {
                   bearMovingRightCount = 0;
               }
               if(isPressingRight && isPressingLeft){
                  character = bearRight.getImage();
               }
            } else {
                bearMovingRightCount = 0;
            }
             if(isPressingLeft && !isJumping && !exitJump && !falling && !(wait || (bossDying && paused))){
               bearMovingLeftCount++;
               if(bearMovingLeftCount <= 100){
                  character = bearMoveOneLeft.getImage();
               } else if(bearMovingLeftCount <= 200){
                  character = bearLeft.getImage();
               } else if(bearMovingLeftCount <= 300){
                  character = bearMoveTwoLeft.getImage();
               } else if(bearMovingLeftCount <= 400){
                  character = bearLeft.getImage();
               } else {
                   bearMovingLeftCount = 0;
               }
               if(isPressingRight && isPressingLeft){
                  character = bearRight.getImage();
               }
            } else {
                bearMovingLeftCount = 0;
            }
            if((character == bearJumpUpRight.getImage() && !isJumping) 
            ||(!isPressingRight && (character == bearMoveOneRight.getImage() || character == bearMoveTwoRight.getImage()))){
					character = bearRight.getImage();
				}
				if((character == bearJumpUpLeft.getImage() && !isJumping)
            ||(!isPressingLeft && (character == bearMoveOneLeft.getImage() || character == bearMoveTwoLeft.getImage()))){
					character = bearLeft.getImage();
				}
            if(character == bearJumpDownRight.getImage() && (!exitJump && !falling)){
               character = bearRight.getImage();
            }
            if(character == bearJumpDownLeft.getImage() && (!exitJump && !falling)){
               character = bearLeft.getImage();
            }
            if(falling && !isOverSlantedPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT) && (isPressingRight || character == bearMoveOneRight.getImage() || character == bearMoveTwoRight.getImage()
            || character == bearRight.getImage())){
               character = bearJumpDownRight.getImage();
            }
            if(falling && !isOverSlantedPlatform(x, y, BEAR_WIDTH, BEAR_HEIGHT) && (isPressingLeft || character == bearMoveOneLeft.getImage() || character == bearMoveTwoLeft.getImage()
            || character == bearLeft.getImage())){
               character = bearJumpDownLeft.getImage();
            }
             bearFlyingCount = 0;
            } else {
                if(isPressingLeft){
               character = bearLeft.getImage();
               }
               if(isPressingRight){
               character = bearRight.getImage();
               }
               bearFlyingCount++;
               if((character == bearJumpDownRight.getImage() || character == bearJumpUpRight.getImage()
               || character == bearRight.getImage() || character == bearMoveOneRight.getImage() 
               || character == bearMoveTwoRight.getImage())){
                  if(bearFlyingCount <= 200){
                     character = bearJumpUpRight.getImage();
                  } else if(bearFlyingCount <= 400){
                     character = bearJumpDownRight.getImage();
                  } else if(bearFlyingCount >= 400){
                     bearFlyingCount = 0;
                  }
               }
               
                if((character == bearJumpDownLeft.getImage() || character == bearJumpUpLeft.getImage()
               || character == bearLeft.getImage() || character == bearMoveOneLeft.getImage() 
               || character == bearMoveTwoLeft.getImage())){
                  if(bearFlyingCount <= 200){
                     character = bearJumpUpLeft.getImage();
                  } else if(bearFlyingCount <= 400){
                     character = bearJumpDownLeft.getImage();
                  } else if(bearFlyingCount >= 400){
                     bearFlyingCount = 0;
                  }
               }
            }
            if((wait || (bossDying && paused))&& (character == bearJumpDownRight.getImage() || character == bearJumpUpRight.getImage()
               || character == bearRight.getImage() || character == bearMoveOneRight.getImage() 
               || character == bearMoveTwoRight.getImage())){
               character = bearRight.getImage();
            }/*
            */
            if((wait || (bossDying && paused)) && (character == bearJumpDownLeft.getImage() || character == bearJumpUpLeft.getImage()
               || character == bearLeft.getImage() || character == bearMoveOneLeft.getImage() 
               || character == bearMoveTwoLeft.getImage())){
               character = bearLeft.getImage();
            }
            }
				g.drawImage(character,x, y + 1, BEAR_WIDTH, BEAR_HEIGHT, this);
            
				///////
				if(level.equals("blue")){
					drawForegroundArt(g, xsideScrollCount * -1, ysideScrollCount * -1);
				}
				g.setColor(fireball);
					try {
					for(int i = 0; i < fireballSet.size(); i++){
						if(fireballSet.get(i).getFX() > W_WIDTH || fireballSet.get(i).getFX() < 0){
							fireballSet.remove(i);
						}
						fireballSet.get(i).move();
						g.fillRect(fireballSet.get(i).getFX(),fireballSet.get(i).getFY(),fireballSet.get(i).getFWidth(),fireballSet.get(i).getFHeight());
						if(isTouchingPlatform(fireballSet.get(i).getFX(),fireballSet.get(i).getFY(),fireballSet.get(i).getFWidth(),fireballSet.get(i).getFHeight())){
								fireballSet.remove(i);
							} 
						for(int j = 0; j < enemySet.size(); j++){
							Rectangle temp = new Rectangle(fireballSet.get(i).getFX(), fireballSet.get(i).getFY(),
									fireballSet.get(i).getFWidth(), fireballSet.get(i).getFHeight());
							Rectangle temp2 = new Rectangle(enemySet.get(j).getX(), enemySet.get(j).getY(), 
									enemySet.get(j).getWidth(), enemySet.get(j).getHeight());
							if(temp.intersects(temp2) && !enemySet.get(i).isDead()){
								fireballSet.remove(i);
								enemySet.get(i).setHealth(enemySet.get(i).getHealth() - 1);
								if(enemySet.get(i).getHealth() == 0){
									enemySet.get(i).setDeath(true);
								}
								
							}
						}
					}
					} catch(Exception e){
						System.out.println("Shut your whore mouth");
					}
				
				if(hasCollectedCrystals()){
					openDoor(g);
					if(win){
						g.setColor(Color.DARK_GRAY);
						g.fillRect(0, 0, W_WIDTH, W_HEIGHT);
						g.setColor(Color.orange);
						g.drawString("Click Continue or press Enter to advanced to the next level", 277, 250);
						if(!continueHover){
							g.setColor(Color.red);
						} else {
							g.setColor(Color.cyan);
						}
						g.fillRect(continueButton.x, continueButton.y, continueButton.width, continueButton.height);
						if(!continueHover){
							g.setColor(Color.blue);
						} else {
							g.setColor(Color.orange);
						}
						g.drawString("Continue", continueButton.x + 10, continueButton.y + 19);
						g.setColor(Color.YELLOW);
						g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
						g.drawString("Level " + level + " Complete! :D", 360, 150);
						g.drawString("Time Completed: " + levelTime / 1000.0 + " Seconds", 300, 200);
					}
				} 
			} else {
				setBackground(new Color(50, 50, 50));
				if(!titleScreenMusic.isRunning()){
					titleScreenMusic.loop(1000000);
				}
				font = new Font("Arial", Font.PLAIN, 100);
				g.setFont(font);
				g.setColor(Color.ORANGE);
				g.drawString("BEAR WITH WINGS", 210, 300);
				if(startHover){
					g.setColor(Color.CYAN);
				} else {
					g.setColor(Color.blue);
				}
				g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
				if(startHover){
					g.setColor(Color.red);
				} else {
					g.setColor(Color.yellow);
				}
				
				g.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 40));
				g.drawString("Start Game", startButton.x + 43, startButton.y + 38);
				
			}
			alertBoxHandler();
			if(alert){
				paused = true;
				drawAlertBox(g);
			}
			inFrontOfDoorTest(g);
			if(credits){
				credits(g);
			}
        // g.drawString("(" + (x + xsideScrollCount) + ", " + (y - ysideScrollCount) +  ")", 100, 100);
			repaint();
		}
		
		
		public static void main(String[] args){
			BearWithWings firstGame = new BearWithWings();
			Thread t1 = new Thread(firstGame);
			t1.start();
		}	
	}