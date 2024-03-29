import java.awt.*;
import javax.swing.ImageIcon;

/*
 * Simple abstract class used for testing. Subclasses should implement the draw() method
 */

public abstract class GameCore {
	
	protected static final int FONT_SIZE = 24;
	
	private static final DisplayMode POSSIBLE_MODES[] = {
			  	new DisplayMode(800, 600, 16, 0),
		        new DisplayMode(800, 600, 32, 0),
		        new DisplayMode(800, 600, 24, 0),
		        new DisplayMode(640, 480, 16, 0),
		        new DisplayMode(640, 480, 32, 0),
		        new DisplayMode(640, 480, 24, 0),
		        new DisplayMode(1024, 768, 16, 0),
		        new DisplayMode(1024, 768, 32, 0),
		        new DisplayMode(1024, 768, 24, 0),			
	};

	private boolean isRunning;
	protected ScreenManager screen;
	
	/*
	 * Signals the game loop that it is time to quit
	 */
	public void stop() {
		isRunning = false;
	}
	
	/*
	 * Calls init() and gameLoop()
	 */
	public void run() {
		try {
			init();
			gameLoop();
		}
		finally {
			screen.restoreScreen();
			lazilyExit(); 
		}
	}
	
	/*
	 * Exits the Vm from a deamon. The deamon thread awaits 2 seconds then calls System.exit(0). Sincre the Vm should exist when only deamon threads are running, this makes sure 
	 * System.exit(0) is only called if necessary. It's necessary if the Java Sound System is running. 
	 */
	public void lazilyExit() {
		Thread thread = new Thread() {
			public void run() {
				//first, wait for the VM exit on its own
				try {
					Thread.sleep(2000);
				}
				catch(InterruptedException ex) { } 
				//system is still running, so force an exit
				System.exit(0);;
			}
		};
		thread.setDaemon(true);
		thread.start();
	}
	
	/*
	 * Sets full screen mode and initiates and objects
	 */
	public void init() {
		screen = new ScreenManager();
		DisplayMode displayMode = screen.findFirstCompatibleMode(POSSIBLE_MODES);
		screen.setFullScreen(displayMode);
		
		Window window = screen.getFullScreenWindow();
		window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
		window.setBackground(Color.BLUE);
		window.setForeground(Color.WHITE);
		
		isRunning = true;
	}
	
	public Image loadImage(String filename) {
		return new ImageIcon(filename).getImage();
	}
	
	/*
	 * Runs through the game loop until stop() is called.
	 */
	public void gameLoop() {
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		
		while(isRunning) {
			long elapsedTime = System.currentTimeMillis() - currTime;
			currTime += elapsedTime;
			
			//update
			update(elapsedTime);
			
			//draw the screen
			Graphics2D g = screen.getGraphics();
			draw(g);
			g.dispose();
			screen.update();
		}
	}
	
	protected abstract void update(long elapsedTime);

	/*
	 * Updates the state of the game/animation based on the amount of elapsed time that has passed.
	 * 
	 */
	public void udpate(long elapsedTime) {
		//do nothing
	}
	
	/*
	 * Draws to the screen. Subclasses must override this mehtod.
	 */
	public abstract void draw(Graphics2D g);
	
}//end of main GameCore class 
