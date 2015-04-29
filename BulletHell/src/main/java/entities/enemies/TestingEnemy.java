package entities.enemies;

import java.awt.Polygon;
import java.util.Random;

import reference.Config;
import main.GameManager;
import main.Main;
import entities.EntityBase;

public class TestingEnemy extends EntityBase {

	private Random rand = new Random();
	private Boolean isAtDestination = true;
	private double destinationX, destinationY, aX, aY, vX, vY;
	int[] xPoints, yPoints;

	public TestingEnemy(String imageName, int x, int y, double aX, double aY) {
		super(imageName);
		this.x = x;
		this.y = y;
		this.aX = aX;
		this.aY = aY;
		xPoints = new int[16];
		yPoints = new int[16];

		hitBox = new Polygon();
	}

	public boolean update() {
		updateHitBox();
		if (GameManager.count % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
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
				System.out.println(destinationX + ", " + destinationY);
			}
			else if (closeToLocation(destinationX, destinationY)) {
				isAtDestination = true;
			}
			else {
				if (destinationX - x > 0) aX = Math.abs(aX);
				else if (destinationX - x < 0) aX = Math.abs(aX) * -1;
				if (destinationY - y > 0) aY = Math.abs(aY);
				else if (destinationY - y < 0) aY = Math.abs(aY) * -1;
				vX += aX;
				vY += aY;
				if (isInBounds(x + vX, y + vY)) {
					x += vX;
					y += vY;
				}
				else {
					vX = 0;
					vY = 0;
				}
			}
			return false;
		}
		return true;
	}

	private boolean closeToLocation(double x, double y) {
		double variance = 100;
		if (this.x > x - variance && this.x < x + variance && this.y > y - variance && this.y < y + variance) return true;
		else return false;
	}

	private void updateHitBox() {
		updateXPoints();
		updateYPoints();
		hitBox = new Polygon(xPoints, yPoints, 15);

		System.out.println("\n\n\n\n\n");

	}

	private void updateXPoints() {
		int radius = this.getImage().getWidth() / 2;
		for (int i = 1; i < 16; i++) {
			xPoints[i - 1] = (int) (x + radius * Math.cos((i * 24) / (Math.PI * 2)));
			System.out.print((int) (x + radius * Math.cos((i * 24) / (Math.PI * 2))) + ", ");
		}
		System.out.println();
	}

	private void updateYPoints() {
		int radius = this.getImage().getWidth() / 2;
		for (int i = 1; i < 16; i++) {
			yPoints[i - 1] = (int) (y + radius * Math.cos((i * 24) / (Math.PI * 2)));
			System.out.print((int) (y + radius * Math.cos((i * 24) / (Math.PI * 2))) + ", ");
		}
	}

}
