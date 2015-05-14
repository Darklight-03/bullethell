package entities;

import util.Log;

public class EnemyBase extends EntityBase {

	private double health;

	public EnemyBase(String imageName, int health) {
		super(imageName);
		this.health = health;
	}

	public EnemyBase(int health) {
		super();
		this.health = health;
	}

	public double getHealth() {
		return health;
	}

	public boolean damage(double damage) {
		health -= damage;
		if (health <= 0) return false;
		// TODO maybe add something here to do when the enemy dies?
		else return true;
	}

}
