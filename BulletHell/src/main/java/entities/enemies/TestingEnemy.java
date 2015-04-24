package entities.enemies;

import java.util.Random;

import reference.Config;
import main.Main;
import entities.EntityBase;

public class TestingEnemy extends EntityBase {

	private Random rand = new Random();
	private Boolean isAtDestination = false;
	private double destinationX, destinationY, aX, aY, vX, vY;

	public TestingEnemy(String imageName, int x, int y, double aX, double aY) {
		super(imageName);
		this.x = x;
		this.y = y;
		this.aX = aX;
		this.aY = aY;
	}

	public boolean update() {
		if (isAtDestination) {
			int i = rand.nextInt(100);
			if (i < 10) {
				destinationX = Main.f.getPanel().getGM().getPlayer().getX();
				destinationY = rand.nextInt(Config.height - 300);
			}
			else if (i > 10 && i < 30) {
				destinationY = 0;
				destinationX = rand.nextInt(Config.width);
			}
			else if (i > 30 && i < 35) {
				destinationY = Main.f.getPanel().getGM().getPlayer().getY();
				destinationX = Main.f.getPanel().getGM().getPlayer().getX();
			}
			else {
				destinationX = rand.nextInt(Config.width);
				destinationY = rand.nextInt(Config.height - rand.nextInt(500));
			}
			isAtDestination = false;
		}
		else if (closeToLocation(destinationX, destinationY)) {
			isAtDestination = false;
		}
		else {
			if (destinationX - x > 0) aX = Math.abs(aX);
			else if (destinationX - x < 0) aX = Math.abs(aX)*-1;
			if (destinationY - y > 0) aY =  Math.abs(aY);
			else if (destinationY - y < 0) aY = Math.abs(aY)*-1;
			vX += aX;
			vY += aY;
			x += vX;
			y += vY;
		}
		return false;
	}

	private boolean closeToLocation(double x, double y) {
		if (this.x > x - 6 && this.x < x + 6 && this.y > y - 6 && this.y < y + 6) return true;
		else return false;
	}

}
