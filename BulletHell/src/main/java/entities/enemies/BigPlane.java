package entities.enemies;

import graphics.ImageLoader;

import java.awt.Rectangle;

import reference.Config;

public class BigPlane extends EnemyBase{
	
	public static final int RoF=1, numBullets = 8;
	
	public BigPlane(int x){
		super(400);
		setImage(Config.scale(ImageLoader.bigPlane0,1.5,1.5));
		this.x = x;
		y = 100;
		vy = .5;
	}
	
	
	
	
	@Override
	public void updateHitBox() {
		hitBox = new Rectangle((int) x - 30, (int) y - 22, 60, 22);
	}
}
