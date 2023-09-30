import java.util.concurrent.atomic.AtomicInteger;

public class Player {
	private int x;	//x position of the player
	private AtomicInteger y;
	private int hp;		//health point of the player
	
	public Player(int x, int y, int hp){
		this.x = x;
		this.y = new AtomicInteger(y);
		this.hp = hp;
	}
	
	public void printPlayer(){
		System.out.printf("x position:\t%d\ny position:\t%d\nhealth point:\t%d\n", x, y, hp);
	}
	
	public synchronized void moveLeft(){
		x --;
	}
	public synchronized void moveRight(){
		x ++;
	}

	public void moveUp(){
		y.incrementAndGet();
	}
	public void moveDown(){
		y.decrementAndGet();
	}

	public static Object health = new Object();

	public void loseHealth() {
		synchronized (health) {
			hp--;
		}
	}
	public void gainHealth() {
		synchronized (health) {
			hp++;
		}
	}
	
}
